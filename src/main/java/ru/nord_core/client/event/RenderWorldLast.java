package ru.nord_core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import ru.nord_core.NordCoreItems;
import ru.nord_core.client.utils.RenderUtils;

public class RenderWorldLast {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onBlockHighlight(RenderWorldLastEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        double shift2 = 0.01d;
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == NordCoreItems.debugStickItem) {
            double px = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) event.partialTicks;
            double py = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) event.partialTicks;
            double pz = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) event.partialTicks;

            NBTTagCompound tag = player.getHeldItem().getTagCompound();
            if (tag != null) {
                boolean pos1 = tag.hasKey("pos1", Constants.NBT.TAG_COMPOUND);
                boolean pos2 = tag.hasKey("pos2", Constants.NBT.TAG_COMPOUND);
                boolean pos3 = tag.hasKey("pos3", Constants.NBT.TAG_COMPOUND);
                double start_x = 0;
                double start_y = 0;
                double start_z = 0;
                double finish_x = 0;
                double finish_y = 0;
                double finish_z = 0;
                double origin_x = 0;
                double origin_y = 0;
                double origin_z = 0;
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
                float transp = 0.15f;
                if (pos1) {
                    NBTTagCompound pos1Tag = tag.getCompoundTag("pos1");
                    start_x = pos1Tag.getInteger("PosX");
                    start_y = pos1Tag.getInteger("PosY");
                    start_z = pos1Tag.getInteger("PosZ");
                    AxisAlignedBB start_aabb = new AxisAlignedBB(
                            start_x, start_y, start_z,
                            start_x + 1, start_y + 1, start_z + 1)
                            .offset(-px, -py, -pz)
                            .expand(shift2, shift2, shift2);
                    GlStateManager.color(1.0F, 0.0F, 0.0F, 1.0F);
                    RenderUtils.lines(start_aabb);
                    GlStateManager.color(1.0F, 0.0F, 0.0F, transp);
                    RenderUtils.box(start_aabb);
                }
                if (pos2) {
                    NBTTagCompound pos2Tag = tag.getCompoundTag("pos2");
                    finish_x = pos2Tag.getInteger("PosX");
                    finish_y = pos2Tag.getInteger("PosY");
                    finish_z = pos2Tag.getInteger("PosZ");
                    AxisAlignedBB finish_aabb = new AxisAlignedBB(
                            finish_x, finish_y, finish_z,
                            finish_x + 1, finish_y + 1, finish_z + 1)
                            .offset(-px, -py, -pz)
                            .expand(shift2, shift2, shift2);
                    GlStateManager.color(0.0F, 0.0F, 1.0F, 1.0F);
                    RenderUtils.lines(finish_aabb);
                    GlStateManager.color(0.0F, 0.0F, 1.0F, transp);
                    RenderUtils.box(finish_aabb);
                }
                if (pos3) {
                    NBTTagCompound pos2Tag = tag.getCompoundTag("pos3");
                    origin_x = pos2Tag.getInteger("PosX");
                    origin_y = pos2Tag.getInteger("PosY");
                    origin_z = pos2Tag.getInteger("PosZ");
                    AxisAlignedBB origin_aabb = new AxisAlignedBB(
                            origin_x, origin_y, origin_z,
                            origin_x + 1, origin_y + 1, origin_z + 1)
                            .offset(-px, -py, -pz)
                            .expand(shift2, shift2, shift2);
                    GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0F);
                    RenderUtils.lines(origin_aabb);
                    GlStateManager.color(0.0F, 0.0F, 0.0F, transp);
                    RenderUtils.box(origin_aabb);
                }
                if (pos1 && pos2) {
                    AxisAlignedBB aabb = new AxisAlignedBB(start_x, start_y, start_z, finish_x, finish_y, finish_z);
                    aabb = new AxisAlignedBB(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX + 1, aabb.maxY + 1, aabb.maxZ + 1)
                            .offset(-px, -py, -pz)
                            .expand(shift2, shift2, shift2);

                    GlStateManager.color(0.0F, 1.0F, 0.0F, transp);
                    RenderUtils.box(aabb);
                    GlStateManager.color(0.0F, 1.0F, 0.0F, 1.0F);
                    GL11.glLineWidth(3.0F);
                    RenderUtils.lines(aabb);


                }
                GlStateManager.depthMask(true);
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
            }
        }
    }


}
