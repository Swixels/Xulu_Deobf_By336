package dev.xulu.clickgui;

import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import java.awt.*;
import java.nio.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;

public final class RenderMethods
{
    public static /* synthetic */ FloatBuffer matModelView;
    public static /* synthetic */ FloatBuffer matProjection;
    
    public static void drawVLine(final float llllllllllllllllllIIIlIIllIllIIl, float llllllllllllllllllIIIlIIllIlIlII, float llllllllllllllllllIIIlIIllIlIIll, final int llllllllllllllllllIIIlIIllIlIIlI) {
        if (llllllllllllllllllIIIlIIllIlIIll < llllllllllllllllllIIIlIIllIlIlII) {
            final float llllllllllllllllllIIIlIIllIllIlI = llllllllllllllllllIIIlIIllIlIlII;
            llllllllllllllllllIIIlIIllIlIlII = llllllllllllllllllIIIlIIllIlIIll;
            llllllllllllllllllIIIlIIllIlIIll = llllllllllllllllllIIIlIIllIllIlI;
        }
        drawRect(llllllllllllllllllIIIlIIllIllIIl, llllllllllllllllllIIIlIIllIlIlII + 1.0f, llllllllllllllllllIIIlIIllIllIIl + 1.0f, llllllllllllllllllIIIlIIllIlIIll, llllllllllllllllllIIIlIIllIlIIlI);
    }
    
    public static void drawBorderedRectReliant(final float llllllllllllllllllIIIlIllIlllIll, final float llllllllllllllllllIIIlIllIllIIll, final float llllllllllllllllllIIIlIllIllIIlI, final float llllllllllllllllllIIIlIllIlllIII, final float llllllllllllllllllIIIlIllIllIlll, final int llllllllllllllllllIIIlIllIlIllll, final int llllllllllllllllllIIIlIllIlIlllI) {
        enableGL2D();
        drawRect(llllllllllllllllllIIIlIllIlllIll, llllllllllllllllllIIIlIllIllIIll, llllllllllllllllllIIIlIllIllIIlI, llllllllllllllllllIIIlIllIlllIII, llllllllllllllllllIIIlIllIlIllll);
        glColor(llllllllllllllllllIIIlIllIlIlllI);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(llllllllllllllllllIIIlIllIllIlll);
        GL11.glBegin(3);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIlllIll, llllllllllllllllllIIIlIllIllIIll);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIlllIll, llllllllllllllllllIIIlIllIlllIII);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIllIIlI, llllllllllllllllllIIIlIllIlllIII);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIllIIlI, llllllllllllllllllIIIlIllIllIIll);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIlllIll, llllllllllllllllllIIIlIllIllIIll);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        disableGL2D();
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB llllllllllllllllllIIIIllllllllIl) {
        if (llllllllllllllllllIIIIllllllllIl == null) {
            return;
        }
        GL11.glBegin(3);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.maxX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.maxX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.maxX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.maxX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.maxX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.maxX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.maxX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.maxX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.minY, llllllllllllllllllIIIIllllllllIl.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIllllllllIl.minX, llllllllllllllllllIIIIllllllllIl.maxY, llllllllllllllllllIIIIllllllllIl.maxZ);
        GL11.glEnd();
    }
    
    public static void glColor(final Color llllllllllllllllllIIIlIIIIlIIlIl) {
        GL11.glColor4f(llllllllllllllllllIIIlIIIIlIIlIl.getRed() / 255.0f, llllllllllllllllllIIIlIIIIlIIlIl.getGreen() / 255.0f, llllllllllllllllllIIIlIIIIlIIlIl.getBlue() / 255.0f, llllllllllllllllllIIIlIIIIlIIlIl.getAlpha() / 255.0f);
    }
    
    public static void drawFullCircle(int llllllllllllllllllIIIlIIIIllIIIl, int llllllllllllllllllIIIlIIIIllIIII, double llllllllllllllllllIIIlIIIIlIllll, final int llllllllllllllllllIIIlIIIIllIllI) {
        llllllllllllllllllIIIlIIIIlIllll *= 2.0;
        llllllllllllllllllIIIlIIIIllIIIl *= (2 != 0);
        llllllllllllllllllIIIlIIIIllIIII *= 2;
        final float llllllllllllllllllIIIlIIIIllIlIl = (llllllllllllllllllIIIlIIIIllIllI >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIIllIlII = (llllllllllllllllllIIIlIIIIllIllI >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIIllIIll = (llllllllllllllllllIIIlIIIIllIllI >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIIllIIlI = (llllllllllllllllllIIIlIIIIllIllI & 0xFF) / 255.0f;
        enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(llllllllllllllllllIIIlIIIIllIlII, llllllllllllllllllIIIlIIIIllIIll, llllllllllllllllllIIIlIIIIllIIlI, llllllllllllllllllIIIlIIIIllIlIl);
        GL11.glBegin(6);
        for (int llllllllllllllllllIIIlIIIIlllIlI = 0; llllllllllllllllllIIIlIIIIlllIlI <= 360; ++llllllllllllllllllIIIlIIIIlllIlI) {
            final double llllllllllllllllllIIIlIIIIllllII = Math.sin(llllllllllllllllllIIIlIIIIlllIlI * 3.141592653589793 / 180.0) * llllllllllllllllllIIIlIIIIlIllll;
            final double llllllllllllllllllIIIlIIIIlllIll = Math.cos(llllllllllllllllllIIIlIIIIlllIlI * 3.141592653589793 / 180.0) * llllllllllllllllllIIIlIIIIlIllll;
            GL11.glVertex2d((double)(llllllllllllllllllIIIlIIIIllIIIl ? 1 : 0) + llllllllllllllllllIIIlIIIIllllII, llllllllllllllllllIIIlIIIIllIIII + llllllllllllllllllIIIlIIIIlllIll);
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
    }
    
    public static void drawGradientRect(final double llllllllllllllllllIIIlIlIIlllIll, final double llllllllllllllllllIIIlIlIIlllIlI, final double llllllllllllllllllIIIlIlIIllllll, final double llllllllllllllllllIIIlIlIIlllIII, final int llllllllllllllllllIIIlIlIIllllIl, final int llllllllllllllllllIIIlIlIIllIllI) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        glColor(llllllllllllllllllIIIlIlIIllllIl);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIllllll, llllllllllllllllllIIIlIlIIlllIlI);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlllIll, llllllllllllllllllIIIlIlIIlllIlI);
        glColor(llllllllllllllllllIIIlIlIIllIllI);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlllIll, llllllllllllllllllIIIlIlIIlllIII);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIllllll, llllllllllllllllllIIIlIlIIlllIII);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public static void drawRoundedRect(float llllllllllllllllllIIIlIllIIIlIIl, float llllllllllllllllllIIIlIllIIIlIII, float llllllllllllllllllIIIlIllIIIIlll, float llllllllllllllllllIIIlIllIIIIllI, final int llllllllllllllllllIIIlIllIIIlIll, final int llllllllllllllllllIIIlIllIIIIlII) {
        enableGL2D();
        llllllllllllllllllIIIlIllIIIlIIl *= (short)2.0f;
        llllllllllllllllllIIIlIllIIIlIII *= 2.0f;
        llllllllllllllllllIIIlIllIIIIlll *= 2.0f;
        llllllllllllllllllIIIlIllIIIIllI *= (String)2.0f;
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawVLine(llllllllllllllllllIIIlIllIIIlIIl, llllllllllllllllllIIIlIllIIIlIII + 1.0f, (float)(llllllllllllllllllIIIlIllIIIIllI - 2.0f), llllllllllllllllllIIIlIllIIIlIll);
        drawVLine(llllllllllllllllllIIIlIllIIIIlll - 1.0f, llllllllllllllllllIIIlIllIIIlIII + 1.0f, (float)(llllllllllllllllllIIIlIllIIIIllI - 2.0f), llllllllllllllllllIIIlIllIIIlIll);
        drawHLine(llllllllllllllllllIIIlIllIIIlIIl + 2.0f, llllllllllllllllllIIIlIllIIIIlll - 3.0f, llllllllllllllllllIIIlIllIIIlIII, llllllllllllllllllIIIlIllIIIlIll);
        drawHLine(llllllllllllllllllIIIlIllIIIlIIl + 2.0f, llllllllllllllllllIIIlIllIIIIlll - 3.0f, (float)(llllllllllllllllllIIIlIllIIIIllI - 1.0f), llllllllllllllllllIIIlIllIIIlIll);
        drawHLine(llllllllllllllllllIIIlIllIIIlIIl + 1.0f, llllllllllllllllllIIIlIllIIIlIIl + 1.0f, llllllllllllllllllIIIlIllIIIlIII + 1.0f, llllllllllllllllllIIIlIllIIIlIll);
        drawHLine(llllllllllllllllllIIIlIllIIIIlll - 2.0f, llllllllllllllllllIIIlIllIIIIlll - 2.0f, llllllllllllllllllIIIlIllIIIlIII + 1.0f, llllllllllllllllllIIIlIllIIIlIll);
        drawHLine(llllllllllllllllllIIIlIllIIIIlll - 2.0f, llllllllllllllllllIIIlIllIIIIlll - 2.0f, (float)(llllllllllllllllllIIIlIllIIIIllI - 2.0f), llllllllllllllllllIIIlIllIIIlIll);
        drawHLine(llllllllllllllllllIIIlIllIIIlIIl + 1.0f, llllllllllllllllllIIIlIllIIIlIIl + 1.0f, (float)(llllllllllllllllllIIIlIllIIIIllI - 2.0f), llllllllllllllllllIIIlIllIIIlIll);
        drawRect(llllllllllllllllllIIIlIllIIIlIIl + 1.0f, llllllllllllllllllIIIlIllIIIlIII + 1.0f, llllllllllllllllllIIIlIllIIIIlll - 1.0f, (float)(llllllllllllllllllIIIlIllIIIIllI - 1.0f), llllllllllllllllllIIIlIllIIIIlII);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
    }
    
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void drawBox(final AxisAlignedBB llllllllllllllllllIIIIlllllllIII) {
        if (llllllllllllllllllIIIIlllllllIII == null) {
            return;
        }
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.maxY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.minX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIII.maxX, llllllllllllllllllIIIIlllllllIII.minY, llllllllllllllllllIIIIlllllllIII.maxZ);
        GL11.glEnd();
    }
    
    public static void drawTriangle(final int llllllllllllllllllIIIllIIIllIlII, final int llllllllllllllllllIIIllIIIllIIll, final int llllllllllllllllllIIIllIIIlIlIIl, final int llllllllllllllllllIIIllIIIllIIIl, final int llllllllllllllllllIIIllIIIllIIII) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        final float llllllllllllllllllIIIllIIIlIllll = (llllllllllllllllllIIIllIIIllIIII >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIllIIIlIlllI = (llllllllllllllllllIIIllIIIllIIII >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIllIIIlIllIl = (llllllllllllllllllIIIllIIIllIIII >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIllIIIlIllII = (llllllllllllllllllIIIllIIIllIIII & 0xFF) / 255.0f;
        GL11.glColor4f(llllllllllllllllllIIIllIIIlIlllI, llllllllllllllllllIIIllIIIlIllIl, llllllllllllllllllIIIllIIIlIllII, llllllllllllllllllIIIllIIIlIllll);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.0f);
        GL11.glShadeModel(7425);
        switch (llllllllllllllllllIIIllIIIlIlIIl) {
            case 0: {
                GL11.glBegin(2);
                GL11.glVertex2d((double)llllllllllllllllllIIIllIIIllIlII, (double)(llllllllllllllllllIIIllIIIllIIll + llllllllllllllllllIIIllIIIllIIIl));
                GL11.glVertex2d((double)(llllllllllllllllllIIIllIIIllIlII + llllllllllllllllllIIIllIIIllIIIl), (double)(llllllllllllllllllIIIllIIIllIIll - llllllllllllllllllIIIllIIIllIIIl));
                GL11.glVertex2d((double)(llllllllllllllllllIIIllIIIllIlII - llllllllllllllllllIIIllIIIllIIIl), (double)(llllllllllllllllllIIIllIIIllIIll - llllllllllllllllllIIIllIIIllIIIl));
                GL11.glEnd();
                GL11.glBegin(4);
                GL11.glVertex2d((double)llllllllllllllllllIIIllIIIllIlII, (double)(llllllllllllllllllIIIllIIIllIIll + llllllllllllllllllIIIllIIIllIIIl));
                GL11.glVertex2d((double)(llllllllllllllllllIIIllIIIllIlII + llllllllllllllllllIIIllIIIllIIIl), (double)(llllllllllllllllllIIIllIIIllIIll - llllllllllllllllllIIIllIIIllIIIl));
                GL11.glVertex2d((double)(llllllllllllllllllIIIllIIIllIlII - llllllllllllllllllIIIllIIIllIIIl), (double)(llllllllllllllllllIIIllIIIllIIll - llllllllllllllllllIIIllIIIllIIIl));
                GL11.glEnd();
                break;
            }
            case 1: {
                GL11.glBegin(2);
                GL11.glVertex2d((double)llllllllllllllllllIIIllIIIllIlII, (double)llllllllllllllllllIIIllIIIllIIll);
                GL11.glVertex2d((double)llllllllllllllllllIIIllIIIllIlII, (double)(llllllllllllllllllIIIllIIIllIIll + llllllllllllllllllIIIllIIIllIIIl / 2));
                GL11.glVertex2d((double)(llllllllllllllllllIIIllIIIllIlII + llllllllllllllllllIIIllIIIllIIIl + llllllllllllllllllIIIllIIIllIIIl / 2), (double)llllllllllllllllllIIIllIIIllIIll);
                GL11.glEnd();
                GL11.glBegin(4);
                GL11.glVertex2d((double)llllllllllllllllllIIIllIIIllIlII, (double)llllllllllllllllllIIIllIIIllIIll);
                GL11.glVertex2d((double)llllllllllllllllllIIIllIIIllIlII, (double)(llllllllllllllllllIIIllIIIllIIll + llllllllllllllllllIIIllIIIllIIIl / 2));
                GL11.glVertex2d((double)(llllllllllllllllllIIIllIIIllIlII + llllllllllllllllllIIIllIIIllIIIl + llllllllllllllllllIIIllIIIllIIIl / 2), (double)llllllllllllllllllIIIllIIIllIIll);
                GL11.glEnd();
            }
            case 3: {
                GL11.glBegin(2);
                GL11.glVertex2d((double)llllllllllllllllllIIIllIIIllIlII, (double)llllllllllllllllllIIIllIIIllIIll);
                GL11.glVertex2d(llllllllllllllllllIIIllIIIllIlII + llllllllllllllllllIIIllIIIllIIIl * 1.25, (double)(llllllllllllllllllIIIllIIIllIIll - llllllllllllllllllIIIllIIIllIIIl / 2));
                GL11.glVertex2d(llllllllllllllllllIIIllIIIllIlII + llllllllllllllllllIIIllIIIllIIIl * 1.25, (double)(llllllllllllllllllIIIllIIIllIIll + llllllllllllllllllIIIllIIIllIIIl / 2));
                GL11.glEnd();
                GL11.glBegin(4);
                GL11.glVertex2d(llllllllllllllllllIIIllIIIllIlII + llllllllllllllllllIIIllIIIllIIIl * 1.25, (double)(llllllllllllllllllIIIllIIIllIIll - llllllllllllllllllIIIllIIIllIIIl / 2));
                GL11.glVertex2d((double)llllllllllllllllllIIIllIIIllIlII, (double)llllllllllllllllllIIIllIIIllIIll);
                GL11.glVertex2d(llllllllllllllllllIIIllIIIllIlII + llllllllllllllllllIIIllIIIllIIIl * 1.25, (double)(llllllllllllllllllIIIllIIIllIIll + llllllllllllllllllIIIllIIIllIIIl / 2));
                GL11.glEnd();
                break;
            }
        }
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawGradientBorderedRectReliant(final float llllllllllllllllllIIIlIllIIlllIl, final float llllllllllllllllllIIIlIllIIlllII, final float llllllllllllllllllIIIlIllIlIIIll, final float llllllllllllllllllIIIlIllIlIIIlI, final float llllllllllllllllllIIIlIllIlIIIIl, final int llllllllllllllllllIIIlIllIIllIII, final int llllllllllllllllllIIIlIllIIlllll, final int llllllllllllllllllIIIlIllIIllllI) {
        enableGL2D();
        drawGradientRect(llllllllllllllllllIIIlIllIIlllIl, llllllllllllllllllIIIlIllIIlllII, llllllllllllllllllIIIlIllIlIIIll, llllllllllllllllllIIIlIllIlIIIlI, llllllllllllllllllIIIlIllIIllllI, llllllllllllllllllIIIlIllIIlllll);
        glColor(llllllllllllllllllIIIlIllIIllIII);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(llllllllllllllllllIIIlIllIlIIIIl);
        GL11.glBegin(3);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIIlllIl, llllllllllllllllllIIIlIllIIlllII);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIIlllIl, llllllllllllllllllIIIlIllIlIIIlI);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIlIIIll, llllllllllllllllllIIIlIllIlIIIlI);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIlIIIll, llllllllllllllllllIIIlIllIIlllII);
        GL11.glVertex2f(llllllllllllllllllIIIlIllIIlllIl, llllllllllllllllllIIIlIllIIlllII);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        disableGL2D();
    }
    
    public static void drawRect(final Rectangle llllllllllllllllllIIIlIlllllllII, final int llllllllllllllllllIIIlIllllllIll) {
        drawRect((float)llllllllllllllllllIIIlIlllllllII.x, (float)llllllllllllllllllIIIlIlllllllII.y, (float)(llllllllllllllllllIIIlIlllllllII.x + llllllllllllllllllIIIlIlllllllII.width), (float)(llllllllllllllllllIIIlIlllllllII.y + llllllllllllllllllIIIlIlllllllII.height), llllllllllllllllllIIIlIllllllIll);
    }
    
    public static Color rainbow(final long llllllllllllllllllIIIllIIllIIIll, final float llllllllllllllllllIIIllIIllIIIlI) {
        final float llllllllllllllllllIIIllIIllIIllI = (System.nanoTime() + llllllllllllllllllIIIllIIllIIIll) / 1.0E10f % 1.0f;
        final long llllllllllllllllllIIIllIIllIIlIl = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(llllllllllllllllllIIIllIIllIIllI, 1.0f, 1.0f)), 16);
        final Color llllllllllllllllllIIIllIIllIIlII = new Color((int)llllllllllllllllllIIIllIIllIIlIl);
        return new Color(llllllllllllllllllIIIllIIllIIlII.getRed() / 255.0f * llllllllllllllllllIIIllIIllIIIlI, llllllllllllllllllIIIllIIllIIlII.getGreen() / 255.0f * llllllllllllllllllIIIllIIllIIIlI, llllllllllllllllllIIIllIIllIIlII.getBlue() / 255.0f * llllllllllllllllllIIIllIIllIIIlI, llllllllllllllllllIIIllIIllIIlII.getAlpha() / 255.0f);
    }
    
    public static int applyTexture(final int llllllllllllllllllIIIllIIIIllIIl, final int llllllllllllllllllIIIllIIIIllIII, final int llllllllllllllllllIIIllIIIIlIIIl, final ByteBuffer llllllllllllllllllIIIllIIIIlIllI, final boolean llllllllllllllllllIIIllIIIIlIlIl, final boolean llllllllllllllllllIIIllIIIIIlllI) {
        GL11.glBindTexture(3553, llllllllllllllllllIIIllIIIIllIIl);
        GL11.glTexParameteri(3553, 10241, llllllllllllllllllIIIllIIIIlIlIl ? 9729 : 9728);
        GL11.glTexParameteri(3553, 10240, llllllllllllllllllIIIllIIIIlIlIl ? 9729 : 9728);
        GL11.glTexParameteri(3553, 10242, llllllllllllllllllIIIllIIIIIlllI ? 10497 : 10496);
        GL11.glTexParameteri(3553, 10243, llllllllllllllllllIIIllIIIIIlllI ? 10497 : 10496);
        GL11.glPixelStorei(3317, 1);
        GL11.glTexImage2D(3553, 0, 32856, llllllllllllllllllIIIllIIIIllIII, llllllllllllllllllIIIllIIIIlIIIl, 0, 6408, 5121, llllllllllllllllllIIIllIIIIlIllI);
        return llllllllllllllllllIIIllIIIIllIIl;
    }
    
    public static double getDiff(final double llllllllllllllllllIIIllIIlIIIlIl, final double llllllllllllllllllIIIllIIlIIIlII, final float llllllllllllllllllIIIllIIIllllll, final double llllllllllllllllllIIIllIIIlllllI) {
        return llllllllllllllllllIIIllIIlIIIlIl + (llllllllllllllllllIIIllIIlIIIlII - llllllllllllllllllIIIllIIlIIIlIl) * llllllllllllllllllIIIllIIIllllll - llllllllllllllllllIIIllIIIlllllI;
    }
    
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static void rectangle(double llllllllllllllllllIIIlIIlIIIIIlI, double llllllllllllllllllIIIlIIlIIIIIIl, double llllllllllllllllllIIIlIIlIIIIIII, double llllllllllllllllllIIIlIIIlllllll, final int llllllllllllllllllIIIlIIIllllllI) {
        if (llllllllllllllllllIIIlIIlIIIIIlI < llllllllllllllllllIIIlIIlIIIIIII) {
            final double llllllllllllllllllIIIlIIlIIIllll = llllllllllllllllllIIIlIIlIIIIIlI;
            llllllllllllllllllIIIlIIlIIIIIlI = llllllllllllllllllIIIlIIlIIIIIII;
            llllllllllllllllllIIIlIIlIIIIIII = llllllllllllllllllIIIlIIlIIIllll;
        }
        if (llllllllllllllllllIIIlIIlIIIIIIl < llllllllllllllllllIIIlIIIlllllll) {
            final double llllllllllllllllllIIIlIIlIIIlllI = llllllllllllllllllIIIlIIlIIIIIIl;
            llllllllllllllllllIIIlIIlIIIIIIl = llllllllllllllllllIIIlIIIlllllll;
            llllllllllllllllllIIIlIIIlllllll = llllllllllllllllllIIIlIIlIIIlllI;
        }
        final float llllllllllllllllllIIIlIIlIIIlIII = (llllllllllllllllllIIIlIIIllllllI >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIlIIIIlll = (llllllllllllllllllIIIlIIIllllllI >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIlIIIIllI = (llllllllllllllllllIIIlIIIllllllI >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIlIIIIlIl = (llllllllllllllllllIIIlIIIllllllI & 0xFF) / 255.0f;
        final Tessellator llllllllllllllllllIIIlIIlIIIIlII = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllllIIIlIIlIIIIIll = llllllllllllllllllIIIlIIlIIIIlII.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(llllllllllllllllllIIIlIIlIIIIlll, llllllllllllllllllIIIlIIlIIIIllI, llllllllllllllllllIIIlIIlIIIIlIl, llllllllllllllllllIIIlIIlIIIlIII);
        llllllllllllllllllIIIlIIlIIIIIll.begin(7, DefaultVertexFormats.POSITION);
        llllllllllllllllllIIIlIIlIIIIIll.pos(llllllllllllllllllIIIlIIlIIIIIlI, llllllllllllllllllIIIlIIIlllllll, 0.0).endVertex();
        llllllllllllllllllIIIlIIlIIIIIll.pos(llllllllllllllllllIIIlIIlIIIIIII, llllllllllllllllllIIIlIIIlllllll, 0.0).endVertex();
        llllllllllllllllllIIIlIIlIIIIIll.pos(llllllllllllllllllIIIlIIlIIIIIII, llllllllllllllllllIIIlIIlIIIIIIl, 0.0).endVertex();
        llllllllllllllllllIIIlIIlIIIIIll.pos(llllllllllllllllllIIIlIIlIIIIIlI, llllllllllllllllllIIIlIIlIIIIIIl, 0.0).endVertex();
        llllllllllllllllllIIIlIIlIIIIlII.draw();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
    }
    
    public static void drawBorderedRect(final float llllllllllllllllllIIIlIllllIIIlI, final float llllllllllllllllllIIIlIlllIllIlI, final float llllllllllllllllllIIIlIlllIllIIl, final float llllllllllllllllllIIIlIlllIlllll, final float llllllllllllllllllIIIlIlllIlIlll, final int llllllllllllllllllIIIlIlllIlllIl, final int llllllllllllllllllIIIlIlllIlIlIl) {
        enableGL2D();
        glColor(llllllllllllllllllIIIlIlllIlllIl);
        drawRect(llllllllllllllllllIIIlIllllIIIlI + llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIllIlI + llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIllIIl - llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIlllll - llllllllllllllllllIIIlIlllIlIlll);
        glColor(llllllllllllllllllIIIlIlllIlIlIl);
        drawRect(llllllllllllllllllIIIlIllllIIIlI + llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIllIlI, llllllllllllllllllIIIlIlllIllIIl - llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIllIlI + llllllllllllllllllIIIlIlllIlIlll);
        drawRect(llllllllllllllllllIIIlIllllIIIlI, llllllllllllllllllIIIlIlllIllIlI, llllllllllllllllllIIIlIllllIIIlI + llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIlllll);
        drawRect(llllllllllllllllllIIIlIlllIllIIl - llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIllIlI, llllllllllllllllllIIIlIlllIllIIl, llllllllllllllllllIIIlIlllIlllll);
        drawRect(llllllllllllllllllIIIlIllllIIIlI + llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIlllll - llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIllIIl - llllllllllllllllllIIIlIlllIlIlll, llllllllllllllllllIIIlIlllIlllll);
        disableGL2D();
    }
    
    public static void drawHLine(float llllllllllllllllllIIIlIIlllIIlII, float llllllllllllllllllIIIlIIlllIIIll, final float llllllllllllllllllIIIlIIlllIIIlI, final int llllllllllllllllllIIIlIIlllIIIIl) {
        if (llllllllllllllllllIIIlIIlllIIIll < llllllllllllllllllIIIlIIlllIIlII) {
            final float llllllllllllllllllIIIlIIlllIlIIl = llllllllllllllllllIIIlIIlllIIlII;
            llllllllllllllllllIIIlIIlllIIlII = llllllllllllllllllIIIlIIlllIIIll;
            llllllllllllllllllIIIlIIlllIIIll = llllllllllllllllllIIIlIIlllIlIIl;
        }
        drawRect(llllllllllllllllllIIIlIIlllIIlII, llllllllllllllllllIIIlIIlllIIIlI, llllllllllllllllllIIIlIIlllIIIll + 1.0f, llllllllllllllllllIIIlIIlllIIIlI + 1.0f, llllllllllllllllllIIIlIIlllIIIIl);
    }
    
    public static void drawGradientHRect(final float llllllllllllllllllIIIlIlIlIIllIl, final float llllllllllllllllllIIIlIlIlIIllII, final float llllllllllllllllllIIIlIlIlIlIIIl, final float llllllllllllllllllIIIlIlIlIIlIlI, final int llllllllllllllllllIIIlIlIlIIlIIl, final int llllllllllllllllllIIIlIlIlIIlIII) {
        enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        glColor(llllllllllllllllllIIIlIlIlIIlIIl);
        GL11.glVertex2f(llllllllllllllllllIIIlIlIlIIllIl, llllllllllllllllllIIIlIlIlIIllII);
        GL11.glVertex2f(llllllllllllllllllIIIlIlIlIIllIl, llllllllllllllllllIIIlIlIlIIlIlI);
        glColor(llllllllllllllllllIIIlIlIlIIlIII);
        GL11.glVertex2f(llllllllllllllllllIIIlIlIlIlIIIl, llllllllllllllllllIIIlIlIlIIlIlI);
        GL11.glVertex2f(llllllllllllllllllIIIlIlIlIlIIIl, llllllllllllllllllIIIlIlIlIIllII);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        disableGL2D();
    }
    
    public static void drawBorderedRect(final Rectangle llllllllllllllllllIIIlIlIllllIll, final float llllllllllllllllllIIIlIlIllllIlI, final int llllllllllllllllllIIIlIlIlllIIIl, final int llllllllllllllllllIIIlIlIllllIII) {
        final float llllllllllllllllllIIIlIlIlllIlll = (float)llllllllllllllllllIIIlIlIllllIll.x;
        final float llllllllllllllllllIIIlIlIlllIllI = (float)llllllllllllllllllIIIlIlIllllIll.y;
        final float llllllllllllllllllIIIlIlIlllIlIl = (float)(llllllllllllllllllIIIlIlIllllIll.x + llllllllllllllllllIIIlIlIllllIll.width);
        final float llllllllllllllllllIIIlIlIlllIlII = (float)(llllllllllllllllllIIIlIlIllllIll.y + llllllllllllllllllIIIlIlIllllIll.height);
        enableGL2D();
        glColor(llllllllllllllllllIIIlIlIlllIIIl);
        drawRect(llllllllllllllllllIIIlIlIlllIlll + llllllllllllllllllIIIlIlIllllIlI, llllllllllllllllllIIIlIlIlllIllI + llllllllllllllllllIIIlIlIllllIlI, llllllllllllllllllIIIlIlIlllIlIl - llllllllllllllllllIIIlIlIllllIlI, llllllllllllllllllIIIlIlIlllIlII - llllllllllllllllllIIIlIlIllllIlI);
        glColor(llllllllllllllllllIIIlIlIllllIII);
        drawRect(llllllllllllllllllIIIlIlIlllIlll + 1.0f, llllllllllllllllllIIIlIlIlllIllI, llllllllllllllllllIIIlIlIlllIlIl - 1.0f, llllllllllllllllllIIIlIlIlllIllI + llllllllllllllllllIIIlIlIllllIlI);
        drawRect(llllllllllllllllllIIIlIlIlllIlll, llllllllllllllllllIIIlIlIlllIllI, llllllllllllllllllIIIlIlIlllIlll + llllllllllllllllllIIIlIlIllllIlI, llllllllllllllllllIIIlIlIlllIlII);
        drawRect(llllllllllllllllllIIIlIlIlllIlIl - llllllllllllllllllIIIlIlIllllIlI, llllllllllllllllllIIIlIlIlllIllI, llllllllllllllllllIIIlIlIlllIlIl, llllllllllllllllllIIIlIlIlllIlII);
        drawRect(llllllllllllllllllIIIlIlIlllIlll + 1.0f, llllllllllllllllllIIIlIlIlllIlII - llllllllllllllllllIIIlIlIllllIlI, llllllllllllllllllIIIlIlIlllIlIl - 1.0f, llllllllllllllllllIIIlIlIlllIlII);
        disableGL2D();
    }
    
    public static void disableGL3D() {
        GL11.glEnable(2896);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
    }
    
    public static void renderCrosses(final AxisAlignedBB llllllllllllllllllIIIIlllllllIll) {
        GL11.glBegin(1);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.maxX, llllllllllllllllllIIIIlllllllIll.maxY, llllllllllllllllllIIIIlllllllIll.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.maxX, llllllllllllllllllIIIIlllllllIll.minY, llllllllllllllllllIIIIlllllllIll.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.maxX, llllllllllllllllllIIIIlllllllIll.maxY, llllllllllllllllllIIIIlllllllIll.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.minX, llllllllllllllllllIIIIlllllllIll.maxY, llllllllllllllllllIIIIlllllllIll.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.minX, llllllllllllllllllIIIIlllllllIll.maxY, llllllllllllllllllIIIIlllllllIll.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.maxX, llllllllllllllllllIIIIlllllllIll.minY, llllllllllllllllllIIIIlllllllIll.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.minX, llllllllllllllllllIIIIlllllllIll.minY, llllllllllllllllllIIIIlllllllIll.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.maxX, llllllllllllllllllIIIIlllllllIll.maxY, llllllllllllllllllIIIIlllllllIll.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.minX, llllllllllllllllllIIIIlllllllIll.minY, llllllllllllllllllIIIIlllllllIll.maxZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.minX, llllllllllllllllllIIIIlllllllIll.maxY, llllllllllllllllllIIIIlllllllIll.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.minX, llllllllllllllllllIIIIlllllllIll.minY, llllllllllllllllllIIIIlllllllIll.minZ);
        GL11.glVertex3d(llllllllllllllllllIIIIlllllllIll.maxX, llllllllllllllllllIIIIlllllllIll.minY, llllllllllllllllllIIIIlllllllIll.maxZ);
        GL11.glEnd();
    }
    
    public static void drawRect(final float llllllllllllllllllIIIlIlllllIIll, final float llllllllllllllllllIIIlIlllllIIlI, final float llllllllllllllllllIIIlIlllllIIIl, final float llllllllllllllllllIIIlIllllIlIll, final int llllllllllllllllllIIIlIllllIlIlI) {
        enableGL2D();
        glColor(llllllllllllllllllIIIlIllllIlIlI);
        drawRect(llllllllllllllllllIIIlIlllllIIll, llllllllllllllllllIIIlIlllllIIlI, llllllllllllllllllIIIlIlllllIIIl, llllllllllllllllllIIIlIllllIlIll);
        disableGL2D();
    }
    
    public static void drawLine(final float llllllllllllllllllIIIllIIIIIlIII, final float llllllllllllllllllIIIllIIIIIIlll, final float llllllllllllllllllIIIllIIIIIIIIl, final float llllllllllllllllllIIIllIIIIIIlIl, final float llllllllllllllllllIIIllIIIIIIlII) {
        GL11.glDisable(3553);
        GL11.glLineWidth(llllllllllllllllllIIIllIIIIIIlII);
        GL11.glBegin(1);
        GL11.glVertex2f(llllllllllllllllllIIIllIIIIIlIII, llllllllllllllllllIIIllIIIIIIlll);
        GL11.glVertex2f(llllllllllllllllllIIIllIIIIIIIIl, llllllllllllllllllIIIllIIIIIIlIl);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public static void drawBorderedRect(float llllllllllllllllllIIIlIlllIIlllI, float llllllllllllllllllIIIlIlllIIIlll, float llllllllllllllllllIIIlIlllIIIllI, float llllllllllllllllllIIIlIlllIIIlIl, final int llllllllllllllllllIIIlIlllIIIlII, final int llllllllllllllllllIIIlIlllIIlIIl) {
        enableGL2D();
        llllllllllllllllllIIIlIlllIIlllI *= 2.0f;
        llllllllllllllllllIIIlIlllIIIllI *= (2.0f != 0.0f);
        llllllllllllllllllIIIlIlllIIIlll *= (String)2.0f;
        llllllllllllllllllIIIlIlllIIIlIl *= (String)2.0f;
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawVLine(llllllllllllllllllIIIlIlllIIlllI, (float)llllllllllllllllllIIIlIlllIIIlll, (float)(llllllllllllllllllIIIlIlllIIIlIl - 1.0f), llllllllllllllllllIIIlIlllIIlIIl);
        drawVLine((llllllllllllllllllIIIlIlllIIIllI ? 1 : 0) - 1.0f, (float)llllllllllllllllllIIIlIlllIIIlll, (float)llllllllllllllllllIIIlIlllIIIlIl, llllllllllllllllllIIIlIlllIIlIIl);
        drawHLine(llllllllllllllllllIIIlIlllIIlllI, (llllllllllllllllllIIIlIlllIIIllI ? 1 : 0) - 1.0f, (float)llllllllllllllllllIIIlIlllIIIlll, llllllllllllllllllIIIlIlllIIlIIl);
        drawHLine(llllllllllllllllllIIIlIlllIIlllI, (llllllllllllllllllIIIlIlllIIIllI ? 1 : 0) - 2.0f, (float)(llllllllllllllllllIIIlIlllIIIlIl - 1.0f), llllllllllllllllllIIIlIlllIIlIIl);
        drawRect(llllllllllllllllllIIIlIlllIIlllI + 1.0f, (float)(llllllllllllllllllIIIlIlllIIIlll + 1.0f), (llllllllllllllllllIIIlIlllIIIllI ? 1 : 0) - 1.0f, (float)(llllllllllllllllllIIIlIlllIIIlIl - 1.0f), llllllllllllllllllIIIlIlllIIIlII);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
    }
    
    public static void enableGL3D(final float llllllllllllllllllIIIllIIIlIIIIl) {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        Minecraft.getMinecraft().entityRenderer.enableLightmap();
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(llllllllllllllllllIIIllIIIlIIIIl);
    }
    
    public static void drawRect(final float llllllllllllllllllIIIlIIlIlIlllI, final float llllllllllllllllllIIIlIIlIllIlIl, final float llllllllllllllllllIIIlIIlIllIlII, final float llllllllllllllllllIIIlIIlIllIIll, final float llllllllllllllllllIIIlIIlIlIlIlI, final float llllllllllllllllllIIIlIIlIlIlIIl, final float llllllllllllllllllIIIlIIlIllIIII, final float llllllllllllllllllIIIlIIlIlIIlll) {
        enableGL2D();
        GL11.glColor4f(llllllllllllllllllIIIlIIlIlIlIlI, llllllllllllllllllIIIlIIlIlIlIIl, llllllllllllllllllIIIlIIlIllIIII, llllllllllllllllllIIIlIIlIlIIlll);
        drawRect(llllllllllllllllllIIIlIIlIlIlllI, llllllllllllllllllIIIlIIlIllIlIl, llllllllllllllllllIIIlIIlIllIlII, llllllllllllllllllIIIlIIlIllIIll);
        disableGL2D();
    }
    
    public static void drawCircle(float llllllllllllllllllIIIlIIIllIIlIl, float llllllllllllllllllIIIlIIIlIlIllI, float llllllllllllllllllIIIlIIIlIlIlIl, final int llllllllllllllllllIIIlIIIllIIIlI, final int llllllllllllllllllIIIlIIIlIlIIll) {
        llllllllllllllllllIIIlIIIlIlIlIl *= (int)2.0f;
        llllllllllllllllllIIIlIIIllIIlIl *= 2.0f;
        llllllllllllllllllIIIlIIIlIlIllI *= 2.0f;
        final float llllllllllllllllllIIIlIIIllIIIII = (llllllllllllllllllIIIlIIIlIlIIll >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIlIlllll = (llllllllllllllllllIIIlIIIlIlIIll >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIlIllllI = (llllllllllllllllllIIIlIIIlIlIIll >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIlIlllIl = (llllllllllllllllllIIIlIIIlIlIIll & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIlIlllII = (float)(6.2831852 / llllllllllllllllllIIIlIIIllIIIlI);
        final float llllllllllllllllllIIIlIIIlIllIll = (float)Math.cos(llllllllllllllllllIIIlIIIlIlllII);
        final float llllllllllllllllllIIIlIIIlIllIlI = (float)Math.sin(llllllllllllllllllIIIlIIIlIlllII);
        float llllllllllllllllllIIIlIIIlIllIIl = llllllllllllllllllIIIlIIIlIlIlIl;
        float llllllllllllllllllIIIlIIIlIllIII = 0.0f;
        enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(llllllllllllllllllIIIlIIIlIlllll, llllllllllllllllllIIIlIIIlIllllI, llllllllllllllllllIIIlIIIlIlllIl, llllllllllllllllllIIIlIIIllIIIII);
        GL11.glBegin(2);
        for (int llllllllllllllllllIIIlIIIllIIllI = 0; llllllllllllllllllIIIlIIIllIIllI < llllllllllllllllllIIIlIIIllIIIlI; ++llllllllllllllllllIIIlIIIllIIllI) {
            GL11.glVertex2f(llllllllllllllllllIIIlIIIlIllIIl + llllllllllllllllllIIIlIIIllIIlIl, llllllllllllllllllIIIlIIIlIllIII + llllllllllllllllllIIIlIIIlIlIllI);
            final float llllllllllllllllllIIIlIIIllIIlll = llllllllllllllllllIIIlIIIlIllIIl;
            llllllllllllllllllIIIlIIIlIllIIl = llllllllllllllllllIIIlIIIlIllIll * llllllllllllllllllIIIlIIIlIllIIl - llllllllllllllllllIIIlIIIlIllIlI * llllllllllllllllllIIIlIIIlIllIII;
            llllllllllllllllllIIIlIIIlIllIII = llllllllllllllllllIIIlIIIlIllIlI * llllllllllllllllllIIIlIIIllIIlll + llllllllllllllllllIIIlIIIlIllIll * llllllllllllllllllIIIlIIIlIllIII;
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
    }
    
    static {
        RenderMethods.matModelView = GLAllocation.createDirectFloatBuffer(16);
        RenderMethods.matProjection = GLAllocation.createDirectFloatBuffer(16);
    }
    
    public static void drawGradientRect(final float llllllllllllllllllIIIlIlIlIlllll, final float llllllllllllllllllIIIlIlIllIIlII, final float llllllllllllllllllIIIlIlIlIlllIl, final float llllllllllllllllllIIIlIlIllIIIlI, final int llllllllllllllllllIIIlIlIllIIIIl, final int llllllllllllllllllIIIlIlIlIllIlI) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        glColor(llllllllllllllllllIIIlIlIllIIIIl);
        GL11.glVertex2d((double)llllllllllllllllllIIIlIlIlIlllll, (double)llllllllllllllllllIIIlIlIllIIIlI);
        GL11.glVertex2d((double)llllllllllllllllllIIIlIlIlIlllIl, (double)llllllllllllllllllIIIlIlIllIIIlI);
        glColor(llllllllllllllllllIIIlIlIlIllIlI);
        GL11.glVertex2d((double)llllllllllllllllllIIIlIlIlIlllIl, (double)llllllllllllllllllIIIlIlIllIIlII);
        GL11.glVertex2d((double)llllllllllllllllllIIIlIlIlIlllll, (double)llllllllllllllllllIIIlIlIllIIlII);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public static void glColor(final float llllllllllllllllllIIIlIIIIIIllIl, final int llllllllllllllllllIIIlIIIIIIIlIl, final int llllllllllllllllllIIIlIIIIIIlIll, final int llllllllllllllllllIIIlIIIIIIlIlI) {
        final float llllllllllllllllllIIIlIIIIIIlIIl = 0.003921569f * llllllllllllllllllIIIlIIIIIIIlIl;
        final float llllllllllllllllllIIIlIIIIIIlIII = 0.003921569f * llllllllllllllllllIIIlIIIIIIlIll;
        final float llllllllllllllllllIIIlIIIIIIIlll = 0.003921569f * llllllllllllllllllIIIlIIIIIIlIlI;
        GL11.glColor4f(llllllllllllllllllIIIlIIIIIIlIIl, llllllllllllllllllIIIlIIIIIIlIII, llllllllllllllllllIIIlIIIIIIIlll, llllllllllllllllllIIIlIIIIIIllIl);
    }
    
    public static void drawRect(final float llllllllllllllllllIIIlIIlIIllllI, final float llllllllllllllllllIIIlIIlIIlllIl, final float llllllllllllllllllIIIlIIlIIlllII, final float llllllllllllllllllIIIlIIlIIllIll) {
        GL11.glBegin(7);
        GL11.glVertex2f(llllllllllllllllllIIIlIIlIIllllI, llllllllllllllllllIIIlIIlIIllIll);
        GL11.glVertex2f(llllllllllllllllllIIIlIIlIIlllII, llllllllllllllllllIIIlIIlIIllIll);
        GL11.glVertex2f(llllllllllllllllllIIIlIIlIIlllII, llllllllllllllllllIIIlIIlIIlllIl);
        GL11.glVertex2f(llllllllllllllllllIIIlIIlIIllllI, llllllllllllllllllIIIlIIlIIlllIl);
        GL11.glEnd();
    }
    
    public static void drawGradientBorderedRect(final double llllllllllllllllllIIIlIlIIlIlllI, final double llllllllllllllllllIIIlIlIIlIllIl, final double llllllllllllllllllIIIlIlIIlIllII, final double llllllllllllllllllIIIlIlIIlIlIll, final float llllllllllllllllllIIIlIlIIlIlIlI, final int llllllllllllllllllIIIlIlIIlIIIlI, final int llllllllllllllllllIIIlIlIIlIlIII, final int llllllllllllllllllIIIlIlIIlIIIII) {
        enableGL2D();
        GL11.glPushMatrix();
        glColor(llllllllllllllllllIIIlIlIIlIIIlI);
        GL11.glLineWidth(1.0f);
        GL11.glBegin(1);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlIlllI, llllllllllllllllllIIIlIlIIlIllIl);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlIlllI, llllllllllllllllllIIIlIlIIlIlIll);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlIllII, llllllllllllllllllIIIlIlIIlIlIll);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlIllII, llllllllllllllllllIIIlIlIIlIllIl);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlIlllI, llllllllllllllllllIIIlIlIIlIllIl);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlIllII, llllllllllllllllllIIIlIlIIlIllIl);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlIlllI, llllllllllllllllllIIIlIlIIlIlIll);
        GL11.glVertex2d(llllllllllllllllllIIIlIlIIlIllII, llllllllllllllllllIIIlIlIIlIlIll);
        GL11.glEnd();
        GL11.glPopMatrix();
        drawGradientRect(llllllllllllllllllIIIlIlIIlIlllI, llllllllllllllllllIIIlIlIIlIllIl, llllllllllllllllllIIIlIlIIlIllII, llllllllllllllllllIIIlIlIIlIlIll, llllllllllllllllllIIIlIlIIlIlIII, llllllllllllllllllIIIlIlIIlIIIII);
        disableGL2D();
    }
    
    public static void drawStrip(final int llllllllllllllllllIIIlIlIIIIlIII, final int llllllllllllllllllIIIlIlIIIIIlll, final float llllllllllllllllllIIIlIIlllllIll, final double llllllllllllllllllIIIlIlIIIIIlIl, final float llllllllllllllllllIIIlIlIIIIIlII, final float llllllllllllllllllIIIlIlIIIIIIll, final int llllllllllllllllllIIIlIlIIIIIIlI) {
        final float llllllllllllllllllIIIlIlIIIIIIIl = (llllllllllllllllllIIIlIlIIIIIIlI >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIlIIIIIIII = (llllllllllllllllllIIIlIlIIIIIIlI >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIllllllll = (llllllllllllllllllIIIlIlIIIIIIlI >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIlllllllI = (llllllllllllllllllIIIlIlIIIIIIlI & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glTranslated((double)llllllllllllllllllIIIlIlIIIIlIII, (double)llllllllllllllllllIIIlIlIIIIIlll, 0.0);
        GL11.glColor4f(llllllllllllllllllIIIlIlIIIIIIII, llllllllllllllllllIIIlIIllllllll, llllllllllllllllllIIIlIIlllllllI, llllllllllllllllllIIIlIlIIIIIIIl);
        GL11.glLineWidth(llllllllllllllllllIIIlIIlllllIll);
        if (llllllllllllllllllIIIlIlIIIIIlIl > 0.0) {
            GL11.glBegin(3);
            for (int llllllllllllllllllIIIlIlIIIIllIl = 0; llllllllllllllllllIIIlIlIIIIllIl < llllllllllllllllllIIIlIlIIIIIlIl; ++llllllllllllllllllIIIlIlIIIIllIl) {
                final float llllllllllllllllllIIIlIlIIIlIIII = (float)(llllllllllllllllllIIIlIlIIIIllIl * (llllllllllllllllllIIIlIlIIIIIlIl * 3.141592653589793 / llllllllllllllllllIIIlIlIIIIIlII));
                final float llllllllllllllllllIIIlIlIIIIllll = (float)(Math.cos(llllllllllllllllllIIIlIlIIIlIIII) * llllllllllllllllllIIIlIlIIIIIIll);
                final float llllllllllllllllllIIIlIlIIIIlllI = (float)(Math.sin(llllllllllllllllllIIIlIlIIIlIIII) * llllllllllllllllllIIIlIlIIIIIIll);
                GL11.glVertex2f(llllllllllllllllllIIIlIlIIIIllll, llllllllllllllllllIIIlIlIIIIlllI);
            }
            GL11.glEnd();
        }
        if (llllllllllllllllllIIIlIlIIIIIlIl < 0.0) {
            GL11.glBegin(3);
            for (int llllllllllllllllllIIIlIlIIIIlIIl = 0; llllllllllllllllllIIIlIlIIIIlIIl > llllllllllllllllllIIIlIlIIIIIlIl; --llllllllllllllllllIIIlIlIIIIlIIl) {
                final float llllllllllllllllllIIIlIlIIIIllII = (float)(llllllllllllllllllIIIlIlIIIIlIIl * (llllllllllllllllllIIIlIlIIIIIlIl * 3.141592653589793 / llllllllllllllllllIIIlIlIIIIIlII));
                final float llllllllllllllllllIIIlIlIIIIlIll = (float)(Math.cos(llllllllllllllllllIIIlIlIIIIllII) * -llllllllllllllllllIIIlIlIIIIIIll);
                final float llllllllllllllllllIIIlIlIIIIlIlI = (float)(Math.sin(llllllllllllllllllIIIlIlIIIIllII) * -llllllllllllllllllIIIlIlIIIIIIll);
                GL11.glVertex2f(llllllllllllllllllIIIlIlIIIIlIll, llllllllllllllllllIIIlIlIIIIlIlI);
            }
            GL11.glEnd();
        }
        disableGL2D();
        GL11.glDisable(3479);
        GL11.glPopMatrix();
    }
    
    public static void drawHLine(float llllllllllllllllllIIIlIIllIIIlII, float llllllllllllllllllIIIlIIllIIIIll, final float llllllllllllllllllIIIlIIllIIIIlI, final int llllllllllllllllllIIIlIIllIIIIIl, final int llllllllllllllllllIIIlIIllIIIlIl) {
        if (llllllllllllllllllIIIlIIllIIIIll < llllllllllllllllllIIIlIIllIIIlII) {
            final float llllllllllllllllllIIIlIIllIIlIlI = (float)llllllllllllllllllIIIlIIllIIIlII;
            llllllllllllllllllIIIlIIllIIIlII = llllllllllllllllllIIIlIIllIIIIll;
            llllllllllllllllllIIIlIIllIIIIll = llllllllllllllllllIIIlIIllIIlIlI;
        }
        drawGradientRect((float)llllllllllllllllllIIIlIIllIIIlII, llllllllllllllllllIIIlIIllIIIIlI, llllllllllllllllllIIIlIIllIIIIll + 1.0f, llllllllllllllllllIIIlIIllIIIIlI + 1.0f, llllllllllllllllllIIIlIIllIIIIIl, llllllllllllllllllIIIlIIllIIIlIl);
    }
    
    public static void enableGL3D() {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4353);
        GL11.glDisable(2896);
    }
    
    public static Color blend(final Color llllllllllllllllllIIIllIIlIlIlll, final Color llllllllllllllllllIIIllIIlIlIllI, final float llllllllllllllllllIIIllIIlIIlllI) {
        final float llllllllllllllllllIIIllIIlIlIlII = 1.0f - llllllllllllllllllIIIllIIlIIlllI;
        final float[] llllllllllllllllllIIIllIIlIlIIll = new float[3];
        final float[] llllllllllllllllllIIIllIIlIlIIlI = new float[3];
        llllllllllllllllllIIIllIIlIlIlll.getColorComponents(llllllllllllllllllIIIllIIlIlIIll);
        llllllllllllllllllIIIllIIlIlIllI.getColorComponents(llllllllllllllllllIIIllIIlIlIIlI);
        final Color llllllllllllllllllIIIllIIlIlIIIl = new Color(llllllllllllllllllIIIllIIlIlIIll[0] * llllllllllllllllllIIIllIIlIIlllI + llllllllllllllllllIIIllIIlIlIIlI[0] * llllllllllllllllllIIIllIIlIlIlII, llllllllllllllllllIIIllIIlIlIIll[1] * llllllllllllllllllIIIllIIlIIlllI + llllllllllllllllllIIIllIIlIlIIlI[1] * llllllllllllllllllIIIllIIlIlIlII, llllllllllllllllllIIIllIIlIlIIll[2] * llllllllllllllllllIIIllIIlIIlllI + llllllllllllllllllIIIllIIlIlIIlI[2] * llllllllllllllllllIIIllIIlIlIlII);
        return llllllllllllllllllIIIllIIlIlIIIl;
    }
    
    public static void glColor(final int llllllllllllllllllIIIlIIIIIllllI) {
        final float llllllllllllllllllIIIlIIIIIlllIl = (llllllllllllllllllIIIlIIIIIllllI >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIIIlllII = (llllllllllllllllllIIIlIIIIIllllI >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIIIllIll = (llllllllllllllllllIIIlIIIIIllllI >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllllIIIlIIIIIllIlI = (llllllllllllllllllIIIlIIIIIllllI & 0xFF) / 255.0f;
        GL11.glColor4f(llllllllllllllllllIIIlIIIIIlllII, llllllllllllllllllIIIlIIIIIllIll, llllllllllllllllllIIIlIIIIIllIlI, llllllllllllllllllIIIlIIIIIlllIl);
    }
}
