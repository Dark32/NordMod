package ru.nord_core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import ru.nord_core.client.utils.RenderUtils;

public class EventForgeClient {

   Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void renderBoxEntity(RenderWorldLastEvent event) {

        double x_fix = -(mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * event.getPartialTicks());
        double y_fix = -(mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * event.getPartialTicks());
        double z_fix = -(mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * event.getPartialTicks());

        GL11.glPushMatrix();
        GL11.glTranslated(x_fix, y_fix, z_fix); //go from cartesian x,y,z coordinates to in-world x,y,z coordinates
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        for (int i = 0; i < mc.theWorld.loadedEntityList.size(); ++i) {
            if (mc.theWorld.loadedEntityList.get(i) instanceof EntityLivingBase) {
//            if (mc.theWorld.loadedEntityList.get(i) instanceof EntityPlayer) {
                EntityLivingBase entity = (EntityLivingBase)mc.theWorld.loadedEntityList.get(i);
                GL11.glColor4f(1.0F, 0.5F, 1.0F, 0.25F);
                RenderUtils.box(entity.getEntityBoundingBox());
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
                RenderUtils.lines(entity.getEntityBoundingBox());
                GL11.glColor4f(1F, 1F, 1F, 1F);

            }
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}