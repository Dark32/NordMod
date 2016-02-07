package ru.nord_core.client.utils;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Сборник полезных функций облегчающих рендер
 */
@SideOnly(Side.CLIENT)
public class RenderUtils {
    /**
     * Рендерим коробку залитую
     *
     * @param aabb коробка
     */
    public static void box(AxisAlignedBB aabb) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        //top
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(aabb.maxX, aabb.maxY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.maxY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.maxY, aabb.maxZ).endVertex();
        worldrenderer.pos(aabb.maxX, aabb.maxY, aabb.maxZ).endVertex();
        tessellator.draw();

//bottom
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(aabb.maxX, aabb.minY, aabb.maxZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.minY, aabb.maxZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.minY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.maxX, aabb.minY, aabb.minZ).endVertex();
        tessellator.draw();
//
////north
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(aabb.maxX, aabb.minY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.minY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.maxY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.maxX, aabb.maxY, aabb.minZ).endVertex();
        tessellator.draw();
//
////south
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(aabb.maxX, aabb.maxY, aabb.maxZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.maxY, aabb.maxZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.minY, aabb.maxZ).endVertex();
        worldrenderer.pos(aabb.maxX, aabb.minY, aabb.maxZ).endVertex();
        tessellator.draw();
//
////west
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(aabb.minX, aabb.maxY, aabb.maxZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.maxY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.minY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.minX, aabb.minY, aabb.maxZ).endVertex();
        tessellator.draw();
//
////east
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(aabb.maxX, aabb.minY, aabb.maxZ).endVertex();
        worldrenderer.pos(aabb.maxX, aabb.minY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.maxX, aabb.maxY, aabb.minZ).endVertex();
        worldrenderer.pos(aabb.maxX, aabb.maxY, aabb.maxZ).endVertex();
        tessellator.draw();
    }

    /**
     * Рендерем другим способом коробку луниями
     *
     * @param aabb коробка
     */
    public static void lines(AxisAlignedBB aabb) {
        GL11.glBegin(GL11.GL_LINES);

/*-top-*/
        GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.minZ);//a - 1
        GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.minZ);//b - 2

        GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.minZ);//a - 2
        GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.maxZ);//b - 3

        GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.maxZ);//a - 3
        GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.maxZ);//b - 4

        GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.maxZ);//a - 4
        GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.minZ);//b - 1

/*-bottom-*/
        GL11.glVertex3d(aabb.minX, aabb.minY, aabb.minZ);//a - 1
        GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.minZ);//b - 2

        GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.minZ);//a - 2
        GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.maxZ);//b - 3

        GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.maxZ);//a - 3
        GL11.glVertex3d(aabb.minX, aabb.minY, aabb.maxZ);//b - 4

        GL11.glVertex3d(aabb.minX, aabb.minY, aabb.maxZ);//a - 4
        GL11.glVertex3d(aabb.minX, aabb.minY, aabb.minZ);//b - 1

/*-side-/-edge-*/
        GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.minZ);//a
        GL11.glVertex3d(aabb.minX, aabb.minY, aabb.minZ);//b

        GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.maxZ);//a
        GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.maxZ);//b

        GL11.glVertex3d(aabb.maxX, aabb.maxY, aabb.minZ);//a
        GL11.glVertex3d(aabb.maxX, aabb.minY, aabb.minZ);//b

        GL11.glVertex3d(aabb.minX, aabb.maxY, aabb.maxZ);//a
        GL11.glVertex3d(aabb.minX, aabb.minY, aabb.maxZ);//b

        GL11.glEnd();
    }


    public static void boxAndLine(AxisAlignedBB aabb, float r1, float g1, float b1, float a1, float r2, float g2, float b2, float a2) {
        GlStateManager.color(r1, g1, b1, a1);
        box(aabb);
        GlStateManager.color(r2, g2, b2, a2);
        lines(aabb);
    }
}
