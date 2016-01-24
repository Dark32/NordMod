package ru.nord_core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import ru.nord_core.NordCoreItems;

public class RenderWorldLast {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onBlockHighlight(RenderWorldLastEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        double shift2= 0.01d;
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == NordCoreItems.debugStickItem) {
            double px = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) event.partialTicks;
            double py = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) event.partialTicks;
            double pz = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) event.partialTicks;

            NBTTagCompound tag = player.getHeldItem().getTagCompound();
            if (tag.hasKey("pos1", Constants.NBT.TAG_COMPOUND)
                    && tag.hasKey("pos2", Constants.NBT.TAG_COMPOUND)) {
                NBTTagCompound pos1Tag = tag.getCompoundTag("pos1");
                double start_x = pos1Tag.getInteger("PosX");
                double start_y = pos1Tag.getInteger("PosY");
                double start_z = pos1Tag.getInteger("PosZ");
                NBTTagCompound pos2Tag = tag.getCompoundTag("pos2");
                double finish_x = pos2Tag.getInteger("PosX");
                double finish_y = pos2Tag.getInteger("PosY");
                double finish_z = pos2Tag.getInteger("PosZ");
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.color(0.0F, 1.0F, 0.0F, 1.0F);
                GL11.glLineWidth(3.0F);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
                AxisAlignedBB aabb = new AxisAlignedBB(
                        start_x, start_y, start_z,
                        finish_x, finish_y, finish_z
                );
                aabb = new AxisAlignedBB(
                        aabb.minX, aabb.minY, aabb.minZ,
                        aabb.maxX + 1, aabb.maxY + 1, aabb.maxZ + 1
                );
                //todo test it
                event.context.drawSelectionBoundingBox(
                        aabb
                                .offset(-px, -py, -pz)
                        );
                event.context.drawSelectionBoundingBox(
                        aabb
                                .expand(shift2, shift2, shift2)
                                .offset(-px, -py, -pz)
                        );
                event.context.drawSelectionBoundingBox(
                        aabb
                                .contract(shift2, shift2, shift2)
                                .offset(-px, -py, -pz)
                        );
                GlStateManager.depthMask(true);
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();

            }
        }
    }


}
