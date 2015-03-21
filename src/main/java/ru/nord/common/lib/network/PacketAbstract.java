package ru.nord.common.lib.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Класс AbstractPacket. Должен быть родителем всех пакетов, желающих использовать PacketPipeline.
 *
 * @author sirgingalot
 */
public abstract class PacketAbstract {
    /**
     * Кодирование пакетные данные в поток ByteBuf. Сложные наборы данных, возможно, потребуют конкретных обработчиков данных(например, ItemStack) (См. {cpw.mods.fml.common.network.ByteBuffUtils})
     *
     * @param ctx    контекст канала
     * @param buffer буфер для кодирования в
     */
    public abstract void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    /**
     * Декодировать пакет данных из потока ByteBuf. Сложные наборы данных, возможно, потребуют конкретных обработчиков данных(например, ItemStack) (См. {cpw.mods.fml.common.network.ByteBuffUtils})
     *
     * @param ctx    контекст канала
     * @param buffer буфер для кодирования из
     */
    public abstract void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    /**
     * Действия пакета на стороне клиента. Обратите внимание, это происходит после завершения декодирования.
     *
     * @param player игрок
     */
    public abstract void handleClientSide(EntityPlayer player);

    /**
     * Действия пакета на стороне сервера. Обратите внимание, это происходит после завершения декодирования.
     *
     * @param player игрок
     */
    public abstract void handleServerSide(EntityPlayer player);
}