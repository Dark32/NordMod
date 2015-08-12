package ru.nord.common.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;

import java.util.*;

/**
 * Класс PacketPipeline. Направляет все зарегистрированные пакетные данные на
 * обработку.
 *
 * @author sirgingalot Некоторый код: cpw
 */
@ChannelHandler.Sharable
public class PacketPipeline
        extends
        MessageToMessageCodec<FMLProxyPacket, PacketAbstract> {

    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private final LinkedList<Class<? extends PacketAbstract>> packets = new LinkedList<Class<? extends PacketAbstract>>();
    private boolean isPostInitialised = false;

    /**
     * Зарегистрировать свой пакет. Дискриминаторы устанавливаются
     * автоматически.
     *
     * @param clazz класс пакета, который необходимо зарегистрировать.
     * @return Вернуть тру, если регистрация была успешна. Отказ может
     * произойти, если 256 пакетов было зарегистрировано или если в
     * реестре уже содержится этот пакет.
     */
    public boolean registerPacket(Class<? extends PacketAbstract> clazz) {
        if (packets.size() > 256) {
            return false;
        }

        if (packets.contains(clazz)) {
            return false;
        }

        if (isPostInitialised) {
            return false;
        }
        packets.add(clazz);
        return true;
    }

    // Кодирование пакета, в том числе настройки дискриминатора.
    @Override
    protected void encode(ChannelHandlerContext ctx, PacketAbstract msg,
                          List<Object> out) throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        Class<? extends PacketAbstract> clazz = msg.getClass();
        if (!packets.contains(msg.getClass())) {
            throw new NullPointerException("No Packet Registered for: "
                    + msg.getClass().getCanonicalName());
        }
        byte discriminator = (byte) this.packets.indexOf(clazz);
        buffer.writeByte(discriminator);
        msg.encodeInto(ctx, buffer);
        FMLProxyPacket proxyPacket = new FMLProxyPacket((PacketBuffer) buffer.copy(), ctx
                .channel().attr(NetworkRegistry.FML_CHANNEL).get());
        out.add(proxyPacket);
    }

    // Декодирования и обработка пакета
    @Override
    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg,
                          List<Object> out) throws Exception {
        ByteBuf payload = msg.payload();
        byte discriminator = payload.readByte();
        Class<? extends PacketAbstract> clazz = this.packets.get(discriminator);
        if (clazz == null) {
            throw new NullPointerException(
                    "No packet registered for discriminator: " + discriminator);
        }
        PacketAbstract pkt = clazz.newInstance();
        pkt.decodeInto(ctx, payload.slice());
        EntityPlayer player;
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT:
                player = this.getClientPlayer();
                pkt.handleClientSide(player);
                break;
            case SERVER:
                INetHandler netHandler = ctx.channel()
                        .attr(NetworkRegistry.NET_HANDLER).get();
                player = ((NetHandlerPlayServer) netHandler).playerEntity;
                pkt.handleServerSide(player);
                break;
            default:
        }
        out.add(pkt);
    }

    // Метод для создания нового канала в FMLInitializationEvent.
    public void initialise() {
        channels = NetworkRegistry.INSTANCE.newChannel(Version.MODID, this);
    }

    // Метод для создания нового канала в FMLPostInitializationEvent.
    // Гарантирует, что пакетные дискриминаторы являются общими между сервером и
    // клиентом с помощью логической сортировки.
    public void postInitialise() {
        if (isPostInitialised) {
            return;
        }
        isPostInitialised = true;
        Collections.sort(packets,
                new Comparator<Class<? extends PacketAbstract>>() {
                    public int compare(Class<? extends PacketAbstract> clazz1,
                                       Class<? extends PacketAbstract> clazz2) {
                        int com = String.CASE_INSENSITIVE_ORDER.compare(
                                clazz1.getCanonicalName(),
                                clazz2.getCanonicalName());
                        if (com == 0) {
                            com = clazz1.getCanonicalName().compareTo(
                                    clazz2.getCanonicalName());
                        }
                        return com;
                    }
                });
    }

    // Простой метод, который возвращает клиенсткого игрока.
    @SideOnly(Side.CLIENT)
    private EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    /**
     * Отправить пакет всем.
     * <p/>
     * По материалам когда cpw's из
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper.
     *
     * @param message Пакет, который нужно отправить.
     */
    public void sendToAll(PacketAbstract message) {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.ALL);
        channels.get(Side.SERVER).writeAndFlush(message);
    }

    /**
     * Отправить пакет определенному игроку
     * <p/>
     * По материалам когда cpw's из
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message Пакет, который нужно отправить.
     * @param player  Игрок, которому нужно отправить пакет.
     */
    public void sendTo(PacketAbstract message, EntityPlayerMP player) {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.PLAYER);
        channels.get(Side.SERVER)
                .attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        channels.get(Side.SERVER).writeAndFlush(message);
    }

    /**
     * Отправить пакет всем в определенном диапазоне точки
     * <p/>
     * По материалам когда cpw's из
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message Пакет, который нужно отправить.
     * @param point   Точки,
     *                {cpw.mods.fml.common.network.NetworkRegistry.TargetPoint} в
     *                которых нужно отправить пакет
     */
    public void sendToAllAround(PacketAbstract message,
                                NetworkRegistry.TargetPoint point) {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        channels.get(Side.SERVER)
                .attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        channels.get(Side.SERVER).writeAndFlush(message);
    }

    /**
     * Отправить пакет в определенном измерении
     * <p/>
     * По материалам когда cpw's из
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message     Пакет, который нужно отправить.
     * @param dimensionId Айди измерения.
     */
    public void sendToDimension(PacketAbstract message, int dimensionId) {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        channels.get(Side.SERVER)
                .attr(FMLOutboundHandler.FML_MESSAGETARGETARGS)
                .set(dimensionId);
        channels.get(Side.SERVER).writeAndFlush(message);
    }

    /**
     * Отправить пакет на сервер.
     * <p/>
     * По материалам когда cpw's из
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message Пакет, который нужно отправить.
     */
    public void sendToServer(PacketAbstract message) {
        channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        channels.get(Side.CLIENT).writeAndFlush(message);
    }
}