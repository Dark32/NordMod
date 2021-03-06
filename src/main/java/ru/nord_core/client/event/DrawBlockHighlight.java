package ru.nord_core.client.event;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import ru.nord.NordBloks;

public class DrawBlockHighlight {
//todo rewrite
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onBlockHighlight(DrawBlockHighlightEvent event) {
        RayTraceResult mop = event.getTarget();
        if (mop != null && mop.typeOfHit == RayTraceResult.Type.BLOCK) {
            EntityPlayer player = event.getPlayer();
            World world = player.worldObj;
            BlockPos pos = mop.getBlockPos();
            if (world.getBlockState(pos).getBlock() == NordBloks.energyCableBlock2) {
                IBlockState state = world.getBlockState(pos);
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.color(1.0F, 0.0F, 0.0F, 0.4F);
                GL11.glLineWidth(2.0F);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
                float f1 = 0.002F;
                BlockPos blockpos = mop.getBlockPos();
                if (state.getMaterial() != Material.air && event.getPlayer().worldObj.getWorldBorder().contains(blockpos))
                {
                    double hitX = mop.hitVec.xCoord - blockpos.getX();
                    double hitY = mop.hitVec.yCoord - blockpos.getY();
                    double hitZ = mop.hitVec.zCoord - blockpos.getZ();

                    double sho = 0.125;
                    double sx = blockpos.getX();
                    double sy = blockpos.getY();
                    double sz = blockpos.getZ();
                    double fx = blockpos.getX();
                    double fy = blockpos.getY();
                    double fz = blockpos.getZ();
                    if (
                            hitX>=sho*3 && hitX<=sho*5 &&
                            hitY>=sho*3 && hitY<=sho*5 &&
                            hitZ>=sho*3 && hitY<=sho*5
                            ){
                        sx +=sho*3;
                        sy +=sho*3;
                        sz +=sho*3;
                        fx+=sho*5;
                        fy+=sho*5;
                        fz+=sho*5;
                    }else if(hitX>sho*5 &&
                            hitY>=sho*3 && hitY<=sho*5 &&
                            hitZ>=sho*3 && hitY<=sho*5
                            ){
                        sx +=sho*5;
                        sy +=sho*3;
                        sz +=sho*3;
                        fx+=sho*8;
                        fy+=sho*5;
                        fz+=sho*5;
                    }else{
                        fx+=1;
                        fy+=1;
                        fz+=1;
                    }
                    AxisAlignedBB aabb = new AxisAlignedBB(sx,sy,sz,fx,fy,fz);
//                    block.setBlockBoundsBasedOnState(event.player.worldObj, blockpos);
                    double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)event.getPartialTicks();
                    double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)event.getPartialTicks();
                    double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)event.getPartialTicks();
//todo test it

                    event.getContext().drawSelectionBoundingBox(
//                            block.getSelectedBoundingBox(event.player.worldObj, blockpos)
                            aabb
                                    .expand(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D)
                                    .offset(-d0, -d1, -d2)
                    );
                }

                GlStateManager.depthMask(true);
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                event.setCanceled(true);

            }

        }
    }


}
