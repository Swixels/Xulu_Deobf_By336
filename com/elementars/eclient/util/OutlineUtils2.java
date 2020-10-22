package com.elementars.eclient.util;

import java.awt.*;
import net.minecraft.client.shader.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;

public class OutlineUtils2
{
    public static void VdOT() {
        GL11.glPolygonOffset(1.0f, 2000000.0f);
        GL11.glDisable(10754);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(2960);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glEnable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glPopAttrib();
    }
    
    public static void zTaX(final Color lllIlIIlIlIIII) {
        GL11.glColor4d((double)(lllIlIIlIlIIII.getRed() / 255.0f), (double)(lllIlIIlIlIIII.getGreen() / 255.0f), (double)(lllIlIIlIlIIII.getBlue() / 255.0f), (double)(lllIlIIlIlIIII.getAlpha() / 255.0f));
    }
    
    public static void setColor(final Color lllIlIIlIIllIl) {
        GL11.glColor3f(lllIlIIlIIllIl.getRed() / 255.0f, lllIlIIlIIllIl.getGreen() / 255.0f, lllIlIIlIIllIl.getBlue() / 255.0f);
    }
    
    public static void Cvvp(final Framebuffer lllIlIIlIIIlll) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(lllIlIIlIIIlll.depthBuffer);
        final int lllIlIIlIIIllI = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, lllIlIIlIIIllI);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, lllIlIIlIIIllI);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, lllIlIIlIIIllI);
    }
    
    public static void mptE(final EntityLivingBase lllIlIIlIlllll) {
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(10754);
        GL11.glPolygonOffset(1.0f, -2000000.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
    }
    
    public static void jdrH(final EntityLivingBase lllIlIIlIlIIll) {
        if (lllIlIIlIlIIll.isInvisible()) {}
        if (!lllIlIIlIlIIll.isPlayerSleeping() && !lllIlIIlIlIIll.isSneaking() && !lllIlIIlIlIIll.isInvisible()) {
            GL11.glColor3f(1.0f, 0.0f, 0.0f);
        }
        if (lllIlIIlIlIIll.isSneaking() && !lllIlIIlIlIIll.isInvisible()) {
            GL11.glColor3f(0.0f, 0.0f, 1.0f);
        }
        else {
            GL11.glColor3f(255.0f, 137.0f, 0.0f);
        }
    }
    
    public static void JLYv() {
        GL11.glStencilFunc(512, 0, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void MqiP() {
        final Framebuffer lllIlIIlIIlIll = Minecraft.getMinecraft().getFramebuffer();
        if (lllIlIIlIIlIll != null && lllIlIIlIIlIll.depthBuffer > -1) {
            Cvvp(lllIlIIlIIlIll);
            lllIlIIlIIlIll.depthBuffer = -1;
        }
    }
    
    public static void feKn() {
        GL11.glStencilFunc(514, 1, 15);
        GL11.glStencilOp(7680, 7680, 7680);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void SnYv(final Boolean lllIlIIlIllIII, final Boolean lllIlIIlIllIlI, final Boolean lllIlIIlIllIIl) {
        if (lllIlIIlIllIII && !lllIlIIlIllIlI && !lllIlIIlIllIIl) {
            GL11.glColor3f(145.0f, 0.0f, 255.0f);
        }
        else if (!lllIlIIlIllIII && lllIlIIlIllIlI && !lllIlIIlIllIIl) {
            GL11.glColor3f(255.0f, 0.0f, 0.0f);
        }
        else if (!lllIlIIlIllIII && !lllIlIIlIllIlI && lllIlIIlIllIIl) {
            GL11.glColor3f(1.0f, 0.7f, 0.0f);
        }
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(10754);
        GL11.glPolygonOffset(1.0f, -2000000.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
    }
    
    public static void VZWQ(final float lllIlIIllIIIIl) {
        MqiP();
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3008);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lllIlIIllIIIIl);
        GL11.glEnable(2848);
        GL11.glEnable(2960);
        GL11.glClear(1024);
        GL11.glClearStencil(15);
        GL11.glStencilFunc(512, 1, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6913);
    }
}
