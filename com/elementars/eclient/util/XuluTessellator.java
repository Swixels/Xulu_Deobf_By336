package com.elementars.eclient.util;

import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.*;

public class XuluTessellator extends Tessellator
{
    public static /* synthetic */ XuluTessellator INSTANCE;
    
    public static void drawFaceOutline(final BlockPos llllllllllllllllIllIIllllllIlIll, final int llllllllllllllllIllIIlllllllIIII, final int llllllllllllllllIllIIllllllIllll, final int llllllllllllllllIllIIllllllIlllI, final int llllllllllllllllIllIIllllllIIlll, final int llllllllllllllllIllIIllllllIllII) {
        drawFaceOutline(XuluTessellator.INSTANCE.getBuffer(), (float)llllllllllllllllIllIIllllllIlIll.x, (float)llllllllllllllllIllIIllllllIlIll.y, (float)llllllllllllllllIllIIllllllIlIll.z, 1.0f, 1.0f, 1.0f, llllllllllllllllIllIIlllllllIIII, llllllllllllllllIllIIllllllIllll, llllllllllllllllIllIIllllllIlllI, llllllllllllllllIllIIllllllIIlll, llllllllllllllllIllIIllllllIllII);
    }
    
    static {
        XuluTessellator.INSTANCE = new XuluTessellator();
    }
    
    public XuluTessellator() {
        super(2097152);
    }
    
    public static void drawBox(final float llllllllllllllllIllIlIIIIlllllll, final float llllllllllllllllIllIlIIIIllllllI, final float llllllllllllllllIllIlIIIIlllllIl, final int llllllllllllllllIllIlIIIIlllllII, final int llllllllllllllllIllIlIIIIllllIll) {
        final int llllllllllllllllIllIlIIIlIIIIIll = llllllllllllllllIllIlIIIIlllllII >>> 24 & 0xFF;
        final int llllllllllllllllIllIlIIIlIIIIIlI = llllllllllllllllIllIlIIIIlllllII >>> 16 & 0xFF;
        final int llllllllllllllllIllIlIIIlIIIIIIl = llllllllllllllllIllIlIIIIlllllII >>> 8 & 0xFF;
        final int llllllllllllllllIllIlIIIlIIIIIII = llllllllllllllllIllIlIIIIlllllII & 0xFF;
        drawBox(XuluTessellator.INSTANCE.getBuffer(), llllllllllllllllIllIlIIIIlllllll, llllllllllllllllIllIlIIIIllllllI, llllllllllllllllIllIlIIIIlllllIl, 1.0f, 1.0f, 1.0f, llllllllllllllllIllIlIIIlIIIIIlI, llllllllllllllllIllIlIIIlIIIIIIl, llllllllllllllllIllIlIIIlIIIIIII, llllllllllllllllIllIlIIIlIIIIIll, llllllllllllllllIllIlIIIIllllIll);
    }
    
    public static void drawRectOutline(final double llllllllllllllllIllIlIIIllIIllIl, final double llllllllllllllllIllIlIIIllIIllII, final double llllllllllllllllIllIlIIIllIIlIll, final double llllllllllllllllIllIlIIIllIIIlII, final double llllllllllllllllIllIlIIIllIIlIIl, final int llllllllllllllllIllIlIIIllIIlIII) {
        drawRectOutline(llllllllllllllllIllIlIIIllIIllIl - llllllllllllllllIllIlIIIllIIlIIl, llllllllllllllllIllIlIIIllIIllII - llllllllllllllllIllIlIIIllIIlIIl, llllllllllllllllIllIlIIIllIIlIll + llllllllllllllllIllIlIIIllIIlIIl, llllllllllllllllIllIlIIIllIIIlII + llllllllllllllllIllIlIIIllIIlIIl, llllllllllllllllIllIlIIIllIIllIl, llllllllllllllllIllIlIIIllIIllII, llllllllllllllllIllIlIIIllIIlIll, llllllllllllllllIllIlIIIllIIIlII, llllllllllllllllIllIlIIIllIIlIII);
    }
    
    public static void drawForgehaxLines(final BufferBuilder llllllllllllllllIllIIlIIlllllIll, final double llllllllllllllllIllIIlIIlllIllIl, final double llllllllllllllllIllIIlIIlllllIIl, final double llllllllllllllllIllIIlIIlllllIII, final double llllllllllllllllIllIIlIIllllIlll, final double llllllllllllllllIllIIlIIllllIllI, final double llllllllllllllllIllIIlIIllllIlIl, final int llllllllllllllllIllIIlIIlllIIllI, final int llllllllllllllllIllIIlIIlllIIlIl, final int llllllllllllllllIllIIlIIlllIIlII, final int llllllllllllllllIllIIlIIllllIIIl, final int llllllllllllllllIllIIlIIllllIIII) {
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x11) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x12) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x21) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x22) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x5) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x6) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x9) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0xA) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x14) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x24) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIlllllIII).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x18) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIlllIllIl, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
        if ((llllllllllllllllIllIIlIIlllIIllI & 0x28) != 0x0) {
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIlllllIIl, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
            llllllllllllllllIllIIlIIlllllIll.pos(llllllllllllllllIllIIlIIllllIlll, llllllllllllllllIllIIlIIllllIllI, llllllllllllllllIllIIlIIllllIlIl).color(llllllllllllllllIllIIlIIlllIIlII, llllllllllllllllIllIIlIIllllIIIl, llllllllllllllllIllIIlIIllllIIII, llllllllllllllllIllIIlIIlllIIlIl).endVertex();
        }
    }
    
    public static void drawBoxOutline(final BlockPos llllllllllllllllIllIIllllIllllII, final int llllllllllllllllIllIIllllIllIlII, final int llllllllllllllllIllIIllllIlllIlI) {
        final int llllllllllllllllIllIIllllIlllIIl = llllllllllllllllIllIIllllIllIlII >>> 24 & 0xFF;
        final int llllllllllllllllIllIIllllIlllIII = llllllllllllllllIllIIllllIllIlII >>> 16 & 0xFF;
        final int llllllllllllllllIllIIllllIllIlll = llllllllllllllllIllIIllllIllIlII >>> 8 & 0xFF;
        final int llllllllllllllllIllIIllllIllIllI = llllllllllllllllIllIIllllIllIlII & 0xFF;
        drawBoxOutline(llllllllllllllllIllIIllllIllllII, llllllllllllllllIllIIllllIlllIII, llllllllllllllllIllIIllllIllIlll, llllllllllllllllIllIIllllIllIllI, llllllllllllllllIllIIllllIlllIIl, llllllllllllllllIllIIllllIlllIlI);
    }
    
    public static void drawBoxOutline(final BlockPos llllllllllllllllIllIIllllIIIIlll, final int llllllllllllllllIllIIllllIIIllII, final int llllllllllllllllIllIIllllIIIlIll, final int llllllllllllllllIllIIllllIIIIlII, final int llllllllllllllllIllIIllllIIIIIll, final int llllllllllllllllIllIIllllIIIlIII) {
        drawBoxOutline(XuluTessellator.INSTANCE.getBuffer(), (float)llllllllllllllllIllIIllllIIIIlll.x, (float)llllllllllllllllIllIIllllIIIIlll.y, (float)llllllllllllllllIllIIllllIIIIlll.z, 1.0f, 1.0f, 1.0f, llllllllllllllllIllIIllllIIIllII, llllllllllllllllIllIIllllIIIlIll, llllllllllllllllIllIIllllIIIIlII, llllllllllllllllIllIIllllIIIIIll, llllllllllllllllIllIIllllIIIlIII);
    }
    
    public static void drawLines(final BufferBuilder llllllllllllllllIllIIlllIIIIllll, final float llllllllllllllllIllIIlllIIIIlllI, final float llllllllllllllllIllIIlllIIIIllIl, final float llllllllllllllllIllIIlllIIIIllII, final float llllllllllllllllIllIIlllIIIlIlll, final float llllllllllllllllIllIIlllIIIIlIlI, final float llllllllllllllllIllIIlllIIIlIlIl, final int llllllllllllllllIllIIlllIIIlIlII, final int llllllllllllllllIllIIlllIIIlIIll, final int llllllllllllllllIllIIlllIIIlIIlI, final int llllllllllllllllIllIIlllIIIlIIIl, final int llllllllllllllllIllIIlllIIIlIIII) {
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x11) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)llllllllllllllllIllIIlllIIIIllIl, (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)llllllllllllllllIllIIlllIIIIllIl, (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x12) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x21) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)llllllllllllllllIllIIlllIIIIllIl, (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)llllllllllllllllIllIIlllIIIIllIl, (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x22) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x5) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)llllllllllllllllIllIIlllIIIIllIl, (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)llllllllllllllllIllIIlllIIIIllIl, (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x6) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x9) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)llllllllllllllllIllIIlllIIIIllIl, (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)llllllllllllllllIllIIlllIIIIllIl, (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0xA) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x14) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)llllllllllllllllIllIIlllIIIIllIl, (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x24) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)llllllllllllllllIllIIlllIIIIllIl, (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)llllllllllllllllIllIIlllIIIIllII).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x18) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)llllllllllllllllIllIIlllIIIIllIl, (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)llllllllllllllllIllIIlllIIIIlllI, (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
        if ((llllllllllllllllIllIIlllIIIlIIII & 0x28) != 0x0) {
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)llllllllllllllllIllIIlllIIIIllIl, (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
            llllllllllllllllIllIIlllIIIIllll.pos((double)(llllllllllllllllIllIIlllIIIIlllI + llllllllllllllllIllIIlllIIIlIlll), (double)(llllllllllllllllIllIIlllIIIIllIl + llllllllllllllllIllIIlllIIIIlIlI), (double)(llllllllllllllllIllIIlllIIIIllII + llllllllllllllllIllIIlllIIIlIlIl)).color(llllllllllllllllIllIIlllIIIlIlII, llllllllllllllllIllIIlllIIIlIIll, llllllllllllllllIllIIlllIIIlIIlI, llllllllllllllllIllIIlllIIIlIIIl).endVertex();
        }
    }
    
    public static void drawFullBox2(final AxisAlignedBB llllllllllllllllIllIIllIIllIIIII, final BlockPos llllllllllllllllIllIIllIIlIlllll, final float llllllllllllllllIllIIllIIlIllllI, final int llllllllllllllllIllIIllIIlIlIIlI, final int llllllllllllllllIllIIllIIlIllIll, final int llllllllllllllllIllIIllIIlIlIIII, final int llllllllllllllllIllIIllIIlIllIII, final int llllllllllllllllIllIIllIIlIIlllI) {
        prepare(7);
        drawBox2(llllllllllllllllIllIIllIIllIIIII, llllllllllllllllIllIIllIIlIlIIlI, llllllllllllllllIllIIllIIlIllIll, llllllllllllllllIllIIllIIlIlIIII, llllllllllllllllIllIIllIIlIllIII, 63);
        release();
        drawBoundingBox(llllllllllllllllIllIIllIIllIIIII, llllllllllllllllIllIIllIIlIllllI, llllllllllllllllIllIIllIIlIlIIlI, llllllllllllllllIllIIllIIlIllIll, llllllllllllllllIllIIllIIlIlIIII, llllllllllllllllIllIIllIIlIIlllI);
    }
    
    public static void prepareGL() {
        GL11.glBlendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(1.5f);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    public static void drawRectOutline(final double llllllllllllllllIllIlIIIlIlllIII, final double llllllllllllllllIllIlIIIlIllIlll, final double llllllllllllllllIllIlIIIlIlIllII, final double llllllllllllllllIllIlIIIlIllIlIl, final double llllllllllllllllIllIlIIIlIllIlII, final double llllllllllllllllIllIlIIIlIlIlIIl, final double llllllllllllllllIllIlIIIlIlIlIII, final double llllllllllllllllIllIlIIIlIllIIIl, final int llllllllllllllllIllIlIIIlIllIIII) {
        drawRectDouble(llllllllllllllllIllIlIIIlIlllIII, llllllllllllllllIllIlIIIlIllIlll, llllllllllllllllIllIlIIIlIlIllII, llllllllllllllllIllIlIIIlIlIlIIl, llllllllllllllllIllIlIIIlIllIIII);
        drawRectDouble(llllllllllllllllIllIlIIIlIlIlIII, llllllllllllllllIllIlIIIlIlIlIIl, llllllllllllllllIllIlIIIlIlIllII, llllllllllllllllIllIlIIIlIllIlIl, llllllllllllllllIllIlIIIlIllIIII);
        drawRectDouble(llllllllllllllllIllIlIIIlIlllIII, llllllllllllllllIllIlIIIlIllIIIl, llllllllllllllllIllIlIIIlIlIlIII, llllllllllllllllIllIlIIIlIllIlIl, llllllllllllllllIllIlIIIlIllIIII);
        drawRectDouble(llllllllllllllllIllIlIIIlIlllIII, llllllllllllllllIllIlIIIlIlIlIIl, llllllllllllllllIllIlIIIlIllIlII, llllllllllllllllIllIlIIIlIllIIIl, llllllllllllllllIllIlIIIlIllIIII);
    }
    
    public static void drawFullBox(final AxisAlignedBB llllllllllllllllIllIIllIIIllIlll, final BlockPos llllllllllllllllIllIIllIIIllIlIl, final float llllllllllllllllIllIIllIIIllIIll, final int llllllllllllllllIllIIllIIIllIIIl, final int llllllllllllllllIllIIllIIIlIllll) {
        final int llllllllllllllllIllIIllIIIlIlllI = llllllllllllllllIllIIllIIIllIIIl >>> 24 & 0xFF;
        final int llllllllllllllllIllIIllIIIlIllIl = llllllllllllllllIllIIllIIIllIIIl >>> 16 & 0xFF;
        final int llllllllllllllllIllIIllIIIlIllII = llllllllllllllllIllIIllIIIllIIIl >>> 8 & 0xFF;
        final int llllllllllllllllIllIIllIIIlIlIll = llllllllllllllllIllIIllIIIllIIIl & 0xFF;
        drawFullBox(llllllllllllllllIllIIllIIIllIlll, llllllllllllllllIllIIllIIIllIlIl, llllllllllllllllIllIIllIIIllIIll, llllllllllllllllIllIIllIIIlIllIl, llllllllllllllllIllIIllIIIlIllII, llllllllllllllllIllIIllIIIlIlIll, llllllllllllllllIllIIllIIIlIlllI, llllllllllllllllIllIIllIIIlIllll);
    }
    
    public static void drawBox(final BlockPos llllllllllllllllIllIlIIIlIIllIII, final int llllllllllllllllIllIlIIIlIIlIlll, final int llllllllllllllllIllIlIIIlIIlIllI) {
        final int llllllllllllllllIllIlIIIlIIlllII = llllllllllllllllIllIlIIIlIIlIlll >>> 24 & 0xFF;
        final int llllllllllllllllIllIlIIIlIIllIll = llllllllllllllllIllIlIIIlIIlIlll >>> 16 & 0xFF;
        final int llllllllllllllllIllIlIIIlIIllIlI = llllllllllllllllIllIlIIIlIIlIlll >>> 8 & 0xFF;
        final int llllllllllllllllIllIlIIIlIIllIIl = llllllllllllllllIllIlIIIlIIlIlll & 0xFF;
        drawBox(llllllllllllllllIllIlIIIlIIllIII, llllllllllllllllIllIlIIIlIIllIll, llllllllllllllllIllIlIIIlIIllIlI, llllllllllllllllIllIlIIIlIIllIIl, llllllllllllllllIllIlIIIlIIlllII, llllllllllllllllIllIlIIIlIIlIllI);
    }
    
    public static void drawForgehaxLines(final BufferBuilder llllllllllllllllIllIIlIlIIlIIIIl, final double llllllllllllllllIllIIlIlIIlIIIII, final double llllllllllllllllIllIIlIlIIIlIIlI, final double llllllllllllllllIllIIlIlIIIllllI, final double llllllllllllllllIllIIlIlIIIlIIII, final double llllllllllllllllIllIIlIlIIIlllII, final double llllllllllllllllIllIIlIlIIIIlllI, final int llllllllllllllllIllIIlIlIIIllIlI, final int llllllllllllllllIllIIlIlIIIllIIl) {
        final int llllllllllllllllIllIIlIlIIIllIII = llllllllllllllllIllIIlIlIIIllIIl >>> 24 & 0xFF;
        final int llllllllllllllllIllIIlIlIIIlIlll = llllllllllllllllIllIIlIlIIIllIIl >>> 16 & 0xFF;
        final int llllllllllllllllIllIIlIlIIIlIllI = llllllllllllllllIllIIlIlIIIllIIl >>> 8 & 0xFF;
        final int llllllllllllllllIllIIlIlIIIlIlIl = llllllllllllllllIllIIlIlIIIllIIl & 0xFF;
        drawForgehaxLines(llllllllllllllllIllIIlIlIIlIIIIl, llllllllllllllllIllIIlIlIIlIIIII, llllllllllllllllIllIIlIlIIIlIIlI, llllllllllllllllIllIIlIlIIIllllI, llllllllllllllllIllIIlIlIIIlIIII, llllllllllllllllIllIIlIlIIIlllII, llllllllllllllllIllIIlIlIIIIlllI, llllllllllllllllIllIIlIlIIIllIlI, llllllllllllllllIllIIlIlIIIllIII, llllllllllllllllIllIIlIlIIIlIlll, llllllllllllllllIllIIlIlIIIlIllI, llllllllllllllllIllIIlIlIIIlIlIl);
    }
    
    public static void render() {
        XuluTessellator.INSTANCE.draw();
    }
    
    public static void drawLines(final BlockPos llllllllllllllllIllIIlllIIlllllI, final int llllllllllllllllIllIIlllIIllllIl, final int llllllllllllllllIllIIlllIIllIlIl, final int llllllllllllllllIllIIlllIIlllIll, final int llllllllllllllllIllIIlllIIlllIlI, final int llllllllllllllllIllIIlllIIlllIII) {
        drawLines(XuluTessellator.INSTANCE.getBuffer(), (float)llllllllllllllllIllIIlllIIlllllI.x, (float)llllllllllllllllIllIIlllIIlllllI.y, (float)llllllllllllllllIllIIlllIIlllllI.z, 1.0f, 1.0f, 1.0f, llllllllllllllllIllIIlllIIllllIl, llllllllllllllllIllIIlllIIllIlIl, llllllllllllllllIllIIlllIIlllIll, llllllllllllllllIllIIlllIIlllIlI, llllllllllllllllIllIIlllIIlllIII);
    }
    
    public static void drawOutlineLine(double llllllllllllllllIllIlIIIllIlllll, double llllllllllllllllIllIlIIIllIllllI, double llllllllllllllllIllIlIIIllIlllIl, double llllllllllllllllIllIlIIIllIlllII, final float llllllllllllllllIllIlIIIllIllIll, final int llllllllllllllllIllIlIIIlllIIllI) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(llllllllllllllllIllIlIIIllIllIll);
        if (llllllllllllllllIllIlIIIllIlllll < llllllllllllllllIllIlIIIllIlllIl) {
            final double llllllllllllllllIllIlIIIlllIllIl = llllllllllllllllIllIlIIIllIlllll;
            llllllllllllllllIllIlIIIllIlllll = llllllllllllllllIllIlIIIllIlllIl;
            llllllllllllllllIllIlIIIllIlllIl = llllllllllllllllIllIlIIIlllIllIl;
        }
        if (llllllllllllllllIllIlIIIllIllllI < llllllllllllllllIllIlIIIllIlllII) {
            final double llllllllllllllllIllIlIIIlllIllII = llllllllllllllllIllIlIIIllIllllI;
            llllllllllllllllIllIlIIIllIllllI = llllllllllllllllIllIlIIIllIlllII;
            llllllllllllllllIllIlIIIllIlllII = llllllllllllllllIllIlIIIlllIllII;
        }
        final float llllllllllllllllIllIlIIIlllIIlIl = (llllllllllllllllIllIlIIIlllIIllI >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIIlllIIlII = (llllllllllllllllIllIlIIIlllIIllI >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIIlllIIIll = (llllllllllllllllIllIlIIIlllIIllI >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIIlllIIIlI = (llllllllllllllllIllIlIIIlllIIllI & 0xFF) / 255.0f;
        final Tessellator llllllllllllllllIllIlIIIlllIIIIl = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllIllIlIIIlllIIIII = llllllllllllllllIllIlIIIlllIIIIl.getBuffer();
        llllllllllllllllIllIlIIIlllIIIII.begin(3, DefaultVertexFormats.POSITION_COLOR);
        llllllllllllllllIllIlIIIlllIIIII.pos(llllllllllllllllIllIlIIIllIlllll, llllllllllllllllIllIlIIIllIlllII, 0.0).color(llllllllllllllllIllIlIIIlllIIlII, llllllllllllllllIllIlIIIlllIIIll, llllllllllllllllIllIlIIIlllIIIlI, llllllllllllllllIllIlIIIlllIIlIl).endVertex();
        llllllllllllllllIllIlIIIlllIIIII.pos(llllllllllllllllIllIlIIIllIlllIl, llllllllllllllllIllIlIIIllIlllII, 0.0).color(llllllllllllllllIllIlIIIlllIIlII, llllllllllllllllIllIlIIIlllIIIll, llllllllllllllllIllIlIIIlllIIIlI, llllllllllllllllIllIlIIIlllIIlIl).endVertex();
        llllllllllllllllIllIlIIIlllIIIII.pos(llllllllllllllllIllIlIIIllIlllIl, llllllllllllllllIllIlIIIllIllllI, 0.0).color(llllllllllllllllIllIlIIIlllIIlII, llllllllllllllllIllIlIIIlllIIIll, llllllllllllllllIllIlIIIlllIIIlI, llllllllllllllllIllIlIIIlllIIlIl).endVertex();
        llllllllllllllllIllIlIIIlllIIIII.pos(llllllllllllllllIllIlIIIllIlllll, llllllllllllllllIllIlIIIllIllllI, 0.0).color(llllllllllllllllIllIlIIIlllIIlII, llllllllllllllllIllIlIIIlllIIIll, llllllllllllllllIllIlIIIlllIIIlI, llllllllllllllllIllIlIIIlllIIlIl).endVertex();
        llllllllllllllllIllIlIIIlllIIIII.pos(llllllllllllllllIllIlIIIllIlllll, llllllllllllllllIllIlIIIllIlllII, 0.0).color(llllllllllllllllIllIlIIIlllIIlII, llllllllllllllllIllIlIIIlllIIIll, llllllllllllllllIllIlIIIlllIIIlI, llllllllllllllllIllIlIIIlllIIlIl).endVertex();
        llllllllllllllllIllIlIIIlllIIIIl.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBoxOutline(final float llllllllllllllllIllIIllllIlIIlIl, final float llllllllllllllllIllIIllllIlIIlII, final float llllllllllllllllIllIIllllIIllIlI, final int llllllllllllllllIllIIllllIIllIIl, final int llllllllllllllllIllIIllllIIllIII) {
        final int llllllllllllllllIllIIllllIlIIIII = llllllllllllllllIllIIllllIIllIIl >>> 24 & 0xFF;
        final int llllllllllllllllIllIIllllIIlllll = llllllllllllllllIllIIllllIIllIIl >>> 16 & 0xFF;
        final int llllllllllllllllIllIIllllIIllllI = llllllllllllllllIllIIllllIIllIIl >>> 8 & 0xFF;
        final int llllllllllllllllIllIIllllIIlllIl = llllllllllllllllIllIIllllIIllIIl & 0xFF;
        drawBoxOutline(XuluTessellator.INSTANCE.getBuffer(), llllllllllllllllIllIIllllIlIIlIl, llllllllllllllllIllIIllllIlIIlII, llllllllllllllllIllIIllllIIllIlI, 1.0f, 1.0f, 1.0f, llllllllllllllllIllIIllllIIlllll, llllllllllllllllIllIIllllIIllllI, llllllllllllllllIllIIllllIIlllIl, llllllllllllllllIllIIllllIlIIIII, llllllllllllllllIllIIllllIIllIII);
    }
    
    public static void drawRectDouble(double llllllllllllllllIllIlIIllIIIlIII, double llllllllllllllllIllIlIIllIIIIllI, double llllllllllllllllIllIlIIllIIIIlII, double llllllllllllllllIllIlIIllIIIIIll, final int llllllllllllllllIllIlIIllIIlIIIl) {
        if (llllllllllllllllIllIlIIllIIIlIII < llllllllllllllllIllIlIIllIIIIlII) {
            final double llllllllllllllllIllIlIIllIIlIlll = llllllllllllllllIllIlIIllIIIlIII;
            llllllllllllllllIllIlIIllIIIlIII = llllllllllllllllIllIlIIllIIIIlII;
            llllllllllllllllIllIlIIllIIIIlII = llllllllllllllllIllIlIIllIIlIlll;
        }
        if (llllllllllllllllIllIlIIllIIIIllI < llllllllllllllllIllIlIIllIIIIIll) {
            final double llllllllllllllllIllIlIIllIIlIllI = llllllllllllllllIllIlIIllIIIIllI;
            llllllllllllllllIllIlIIllIIIIllI = llllllllllllllllIllIlIIllIIIIIll;
            llllllllllllllllIllIlIIllIIIIIll = llllllllllllllllIllIlIIllIIlIllI;
        }
        final float llllllllllllllllIllIlIIllIIIllll = (llllllllllllllllIllIlIIllIIlIIIl >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIllIIIlllI = (llllllllllllllllIllIlIIllIIlIIIl >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIllIIIllIl = (llllllllllllllllIllIlIIllIIlIIIl >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIllIIIlIll = (llllllllllllllllIllIlIIllIIlIIIl & 0xFF) / 255.0f;
        final Tessellator llllllllllllllllIllIlIIllIIIlIlI = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllIllIlIIllIIIlIIl = llllllllllllllllIllIlIIllIIIlIlI.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(llllllllllllllllIllIlIIllIIIlllI, llllllllllllllllIllIlIIllIIIllIl, llllllllllllllllIllIlIIllIIIlIll, llllllllllllllllIllIlIIllIIIllll);
        llllllllllllllllIllIlIIllIIIlIIl.begin(7, DefaultVertexFormats.POSITION);
        llllllllllllllllIllIlIIllIIIlIIl.pos(llllllllllllllllIllIlIIllIIIlIII, llllllllllllllllIllIlIIllIIIIIll, 0.0).endVertex();
        llllllllllllllllIllIlIIllIIIlIIl.pos(llllllllllllllllIllIlIIllIIIIlII, llllllllllllllllIllIlIIllIIIIIll, 0.0).endVertex();
        llllllllllllllllIllIlIIllIIIlIIl.pos(llllllllllllllllIllIlIIllIIIIlII, llllllllllllllllIllIlIIllIIIIllI, 0.0).endVertex();
        llllllllllllllllIllIlIIllIIIlIIl.pos(llllllllllllllllIllIlIIllIIIlIII, llllllllllllllllIllIlIIllIIIIllI, 0.0).endVertex();
        llllllllllllllllIllIlIIllIIIlIlI.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawBoxOutline(final BufferBuilder llllllllllllllllIllIIlllIlllIllI, final float llllllllllllllllIllIIlllIllIlIIl, final float llllllllllllllllIllIIlllIlllIlII, final float llllllllllllllllIllIIlllIllIIlll, final float llllllllllllllllIllIIlllIlllIIlI, final float llllllllllllllllIllIIlllIlllIIIl, final float llllllllllllllllIllIIlllIlllIIII, final int llllllllllllllllIllIIlllIllIllll, final int llllllllllllllllIllIIlllIllIIIll, final int llllllllllllllllIllIIlllIllIIIlI, final int llllllllllllllllIllIIlllIllIllII, final int llllllllllllllllIllIIlllIllIlIll) {
        if ((llllllllllllllllIllIIlllIllIlIll & 0x1) != 0x0) {
            llllllllllllllllIllIIlllIlllIllI.pos((double)(llllllllllllllllIllIIlllIllIlIIl + llllllllllllllllIllIIlllIlllIIlI), (double)llllllllllllllllIllIIlllIlllIlII, (double)llllllllllllllllIllIIlllIllIIlll).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)(llllllllllllllllIllIIlllIllIlIIl + llllllllllllllllIllIIlllIlllIIlI), (double)llllllllllllllllIllIIlllIlllIlII, llllllllllllllllIllIIlllIllIIlll + 0.02).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)llllllllllllllllIllIIlllIllIlIIl, (double)llllllllllllllllIllIIlllIlllIlII, llllllllllllllllIllIIlllIllIIlll + 0.02).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)llllllllllllllllIllIIlllIllIlIIl, (double)llllllllllllllllIllIIlllIlllIlII, (double)llllllllllllllllIllIIlllIllIIlll).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos(llllllllllllllllIllIIlllIllIlIIl + 0.02, (double)llllllllllllllllIllIIlllIlllIlII, (double)llllllllllllllllIllIIlllIllIIlll).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos(llllllllllllllllIllIIlllIllIlIIl + 0.02, (double)llllllllllllllllIllIIlllIlllIlII, (double)(llllllllllllllllIllIIlllIllIIlll + llllllllllllllllIllIIlllIlllIIII)).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)llllllllllllllllIllIIlllIllIlIIl, (double)llllllllllllllllIllIIlllIlllIlII, (double)(llllllllllllllllIllIIlllIllIIlll + llllllllllllllllIllIIlllIlllIIII)).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)llllllllllllllllIllIIlllIllIlIIl, (double)llllllllllllllllIllIIlllIlllIlII, (double)llllllllllllllllIllIIlllIllIIlll).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)(llllllllllllllllIllIIlllIllIlIIl + llllllllllllllllIllIIlllIlllIIlI), (double)llllllllllllllllIllIIlllIlllIlII, llllllllllllllllIllIIlllIllIIlll + llllllllllllllllIllIIlllIlllIIII - 0.02).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)(llllllllllllllllIllIIlllIllIlIIl + llllllllllllllllIllIIlllIlllIIlI), (double)llllllllllllllllIllIIlllIlllIlII, (double)(llllllllllllllllIllIIlllIllIIlll + llllllllllllllllIllIIlllIlllIIII)).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)llllllllllllllllIllIIlllIllIlIIl, (double)llllllllllllllllIllIIlllIlllIlII, (double)(llllllllllllllllIllIIlllIllIIlll + llllllllllllllllIllIIlllIlllIIII)).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)llllllllllllllllIllIIlllIllIlIIl, (double)llllllllllllllllIllIIlllIlllIlII, llllllllllllllllIllIIlllIllIIlll + llllllllllllllllIllIIlllIlllIIII - 0.02).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)(llllllllllllllllIllIIlllIllIlIIl + llllllllllllllllIllIIlllIlllIIlI), (double)llllllllllllllllIllIIlllIlllIlII, (double)llllllllllllllllIllIIlllIllIIlll).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos((double)(llllllllllllllllIllIIlllIllIlIIl + llllllllllllllllIllIIlllIlllIIlI), (double)llllllllllllllllIllIIlllIlllIlII, (double)(llllllllllllllllIllIIlllIllIIlll + llllllllllllllllIllIIlllIlllIIII)).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos(llllllllllllllllIllIIlllIllIlIIl + llllllllllllllllIllIIlllIlllIIlI - 0.02, (double)llllllllllllllllIllIIlllIlllIlII, (double)(llllllllllllllllIllIIlllIllIIlll + llllllllllllllllIllIIlllIlllIIII)).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
            llllllllllllllllIllIIlllIlllIllI.pos(llllllllllllllllIllIIlllIllIlIIl + llllllllllllllllIllIIlllIlllIIlI - 0.02, (double)llllllllllllllllIllIIlllIlllIlII, (double)llllllllllllllllIllIIlllIllIIlll).color(llllllllllllllllIllIIlllIllIllll, llllllllllllllllIllIIlllIllIIIll, llllllllllllllllIllIIlllIllIIIlI, llllllllllllllllIllIIlllIllIllII).endVertex();
        }
    }
    
    public static void drawLines(final BlockPos llllllllllllllllIllIIlllIlIlIIIl, final int llllllllllllllllIllIIlllIlIlIIII, final int llllllllllllllllIllIIlllIlIIllll) {
        final int llllllllllllllllIllIIlllIlIlIlIl = llllllllllllllllIllIIlllIlIlIIII >>> 24 & 0xFF;
        final int llllllllllllllllIllIIlllIlIlIlII = llllllllllllllllIllIIlllIlIlIIII >>> 16 & 0xFF;
        final int llllllllllllllllIllIIlllIlIlIIll = llllllllllllllllIllIIlllIlIlIIII >>> 8 & 0xFF;
        final int llllllllllllllllIllIIlllIlIlIIlI = llllllllllllllllIllIIlllIlIlIIII & 0xFF;
        drawLines(llllllllllllllllIllIIlllIlIlIIIl, llllllllllllllllIllIIlllIlIlIlII, llllllllllllllllIllIIlllIlIlIIll, llllllllllllllllIllIIlllIlIlIIlI, llllllllllllllllIllIIlllIlIlIlIl, llllllllllllllllIllIIlllIlIIllll);
    }
    
    public static void drawIndicator(final AxisAlignedBB llllllllllllllllIllIIlIlIlIlIllI, final int llllllllllllllllIllIIlIlIlIIllIl, final int llllllllllllllllIllIIlIlIlIIllII, final int llllllllllllllllIllIIlIlIlIlIIll, final int llllllllllllllllIllIIlIlIlIlIIlI, final int llllllllllllllllIllIIlIlIlIlIIIl) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        final Tessellator llllllllllllllllIllIIlIlIlIlIIII = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllIllIIlIlIlIIllll = llllllllllllllllIllIIlIlIlIlIIII.getBuffer();
        llllllllllllllllIllIIlIlIlIIllll.begin(7, DefaultVertexFormats.POSITION_COLOR);
        if ((llllllllllllllllIllIIlIlIlIlIIIl & 0x1) != 0x0) {
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
        }
        if ((llllllllllllllllIllIIlIlIlIlIIIl & 0x4) != 0x0) {
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.maxY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, 0).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.maxY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, 0).endVertex();
        }
        if ((llllllllllllllllIllIIlIlIlIlIIIl & 0x8) != 0x0) {
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.maxY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, 0).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.maxY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, 0).endVertex();
        }
        if ((llllllllllllllllIllIIlIlIlIlIIIl & 0x10) != 0x0) {
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.maxY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, 0).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.minX, llllllllllllllllIllIIlIlIlIlIllI.maxY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, 0).endVertex();
        }
        if ((llllllllllllllllIllIIlIlIlIlIIIl & 0x20) != 0x0) {
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.minY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, llllllllllllllllIllIIlIlIlIlIIlI).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.maxY, llllllllllllllllIllIIlIlIlIlIllI.minZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, 0).endVertex();
            llllllllllllllllIllIIlIlIlIIllll.pos(llllllllllllllllIllIIlIlIlIlIllI.maxX, llllllllllllllllIllIIlIlIlIlIllI.maxY, llllllllllllllllIllIIlIlIlIlIllI.maxZ).color(llllllllllllllllIllIIlIlIlIIllIl, llllllllllllllllIllIIlIlIlIIllII, llllllllllllllllIllIIlIlIlIlIIll, 0).endVertex();
        }
        llllllllllllllllIllIIlIlIlIlIIII.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBox2(final AxisAlignedBB llllllllllllllllIllIIllIllIIlIlI, final int llllllllllllllllIllIIllIlIllllll, final int llllllllllllllllIllIIllIlIlllllI, final int llllllllllllllllIllIIllIllIIIllI, final int llllllllllllllllIllIIllIlIllllII, final int llllllllllllllllIllIIllIllIIIlII) {
        final Tessellator llllllllllllllllIllIIllIllIIIIlI = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllIllIIllIllIIIIIl = llllllllllllllllIllIIllIllIIIIlI.getBuffer();
        llllllllllllllllIllIIllIllIIIIIl.begin(7, DefaultVertexFormats.POSITION_COLOR);
        if ((llllllllllllllllIllIIllIllIIIlII & 0x1) != 0x0) {
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
        }
        if ((llllllllllllllllIllIIllIllIIIlII & 0x2) != 0x0) {
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
        }
        if ((llllllllllllllllIllIIllIllIIIlII & 0x4) != 0x0) {
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
        }
        if ((llllllllllllllllIllIIllIllIIIlII & 0x8) != 0x0) {
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
        }
        if ((llllllllllllllllIllIIllIllIIIlII & 0x10) != 0x0) {
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.minX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
        }
        if ((llllllllllllllllIllIIllIllIIIlII & 0x20) != 0x0) {
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.minY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.minZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
            llllllllllllllllIllIIllIllIIIIIl.pos(llllllllllllllllIllIIllIllIIlIlI.maxX, llllllllllllllllIllIIllIllIIlIlI.maxY, llllllllllllllllIllIIllIllIIlIlI.maxZ).color(llllllllllllllllIllIIllIlIllllll, llllllllllllllllIllIIllIlIlllllI, llllllllllllllllIllIIllIllIIIllI, llllllllllllllllIllIIllIlIllllII).endVertex();
        }
        llllllllllllllllIllIIllIllIIIIlI.draw();
    }
    
    public static void drawFullBox2(final AxisAlignedBB llllllllllllllllIllIIllIlIIIIIll, final BlockPos llllllllllllllllIllIIllIIlllIlll, final float llllllllllllllllIllIIllIlIIIIIIl, final int llllllllllllllllIllIIllIIlllIIll, final int llllllllllllllllIllIIllIIlllllll) {
        final int llllllllllllllllIllIIllIIllllllI = llllllllllllllllIllIIllIIlllIIll >>> 24 & 0xFF;
        final int llllllllllllllllIllIIllIIlllllII = llllllllllllllllIllIIllIIlllIIll >>> 16 & 0xFF;
        final int llllllllllllllllIllIIllIIllllIlI = llllllllllllllllIllIIllIIlllIIll >>> 8 & 0xFF;
        final int llllllllllllllllIllIIllIIllllIIl = llllllllllllllllIllIIllIIlllIIll & 0xFF;
        drawFullBox2(llllllllllllllllIllIIllIlIIIIIll, llllllllllllllllIllIIllIIlllIlll, llllllllllllllllIllIIllIlIIIIIIl, llllllllllllllllIllIIllIIlllllII, llllllllllllllllIllIIllIIllllIlI, llllllllllllllllIllIIllIIllllIIl, llllllllllllllllIllIIllIIllllllI, llllllllllllllllIllIIllIIlllllll);
    }
    
    public static BufferBuilder getBufferBuilder() {
        return XuluTessellator.INSTANCE.getBuffer();
    }
    
    public static void release() {
        render();
        releaseGL();
    }
    
    public static void drawFace(final BlockPos llllllllllllllllIllIlIIIIIIlllll, final int llllllllllllllllIllIlIIIIIIllllI, final int llllllllllllllllIllIlIIIIIIlllIl, final int llllllllllllllllIllIlIIIIIIlllII, final int llllllllllllllllIllIlIIIIIlIIIIl, final int llllllllllllllllIllIlIIIIIlIIIII) {
        drawFace(XuluTessellator.INSTANCE.getBuffer(), (float)llllllllllllllllIllIlIIIIIIlllll.x, (float)llllllllllllllllIllIlIIIIIIlllll.y, (float)llllllllllllllllIllIlIIIIIIlllll.z, 1.0f, 1.0f, 1.0f, llllllllllllllllIllIlIIIIIIllllI, llllllllllllllllIllIlIIIIIIlllIl, llllllllllllllllIllIlIIIIIIlllII, llllllllllllllllIllIlIIIIIlIIIIl, llllllllllllllllIllIlIIIIIlIIIII);
    }
    
    public static void drawFullBoxAA(final AxisAlignedBB llllllllllllllllIllIIlIlllIllIlI, final float llllllllllllllllIllIIlIllllIIIII, final int llllllllllllllllIllIIlIlllIlllll, final int llllllllllllllllIllIIlIlllIllllI, final int llllllllllllllllIllIIlIlllIlllIl, final int llllllllllllllllIllIIlIlllIlIlIl, final int llllllllllllllllIllIIlIlllIllIll) {
        drawBox2(llllllllllllllllIllIIlIlllIllIlI, llllllllllllllllIllIIlIlllIlllll, llllllllllllllllIllIIlIlllIllllI, llllllllllllllllIllIIlIlllIlllIl, llllllllllllllllIllIIlIlllIlIlIl, 63);
        drawBoundingBox(llllllllllllllllIllIIlIlllIllIlI, llllllllllllllllIllIIlIllllIIIII, llllllllllllllllIllIIlIlllIlllll, llllllllllllllllIllIIlIlllIllllI, llllllllllllllllIllIIlIlllIlllIl, llllllllllllllllIllIIlIlllIllIll);
    }
    
    public static void drawFullFace(final AxisAlignedBB llllllllllllllllIllIIlIllIlIlIII, final BlockPos llllllllllllllllIllIIlIllIlIllll, final float llllllllllllllllIllIIlIllIlIIllI, final int llllllllllllllllIllIIlIllIlIIlIl, final int llllllllllllllllIllIIlIllIlIllII, final int llllllllllllllllIllIIlIllIlIIIll, final int llllllllllllllllIllIIlIllIlIlIlI, final int llllllllllllllllIllIIlIllIlIlIIl) {
        prepare(7);
        drawFace(llllllllllllllllIllIIlIllIlIllll, llllllllllllllllIllIIlIllIlIIlIl, llllllllllllllllIllIIlIllIlIllII, llllllllllllllllIllIIlIllIlIIIll, llllllllllllllllIllIIlIllIlIlIlI, 63);
        release();
        drawBoundingBoxFace(llllllllllllllllIllIIlIllIlIlIII, llllllllllllllllIllIIlIllIlIIllI, llllllllllllllllIllIIlIllIlIIlIl, llllllllllllllllIllIIlIllIlIllII, llllllllllllllllIllIIlIllIlIIIll, llllllllllllllllIllIIlIllIlIlIIl);
    }
    
    public static void drawFullBox(final AxisAlignedBB llllllllllllllllIllIIllIIIIIllIl, final BlockPos llllllllllllllllIllIIllIIIIIllII, final float llllllllllllllllIllIIllIIIIlIlII, final int llllllllllllllllIllIIllIIIIIlIIl, final int llllllllllllllllIllIIllIIIIlIIlI, final int llllllllllllllllIllIIllIIIIIIllI, final int llllllllllllllllIllIIllIIIIIIlIl, final int llllllllllllllllIllIIllIIIIIllll) {
        prepare(7);
        drawBox(llllllllllllllllIllIIllIIIIIllII, llllllllllllllllIllIIllIIIIIlIIl, llllllllllllllllIllIIllIIIIlIIlI, llllllllllllllllIllIIllIIIIIIllI, llllllllllllllllIllIIllIIIIIIlIl, 63);
        release();
        drawBoundingBox(llllllllllllllllIllIIllIIIIIllIl, llllllllllllllllIllIIllIIIIlIlII, llllllllllllllllIllIIllIIIIIlIIl, llllllllllllllllIllIIllIIIIlIIlI, llllllllllllllllIllIIllIIIIIIllI, llllllllllllllllIllIIllIIIIIllll);
    }
    
    public static void drawFullBox2(final AxisAlignedBB llllllllllllllllIllIIllIlIlIIlll, final BlockPos llllllllllllllllIllIIllIlIlIIllI, final float llllllllllllllllIllIIllIlIlIIlIl, final int llllllllllllllllIllIIllIlIIlllII) {
        final int llllllllllllllllIllIIllIlIlIIIll = llllllllllllllllIllIIllIlIIlllII >>> 24 & 0xFF;
        final int llllllllllllllllIllIIllIlIlIIIlI = llllllllllllllllIllIIllIlIIlllII >>> 16 & 0xFF;
        final int llllllllllllllllIllIIllIlIlIIIIl = llllllllllllllllIllIIllIlIIlllII >>> 8 & 0xFF;
        final int llllllllllllllllIllIIllIlIlIIIII = llllllllllllllllIllIIllIlIIlllII & 0xFF;
        drawFullBox2(llllllllllllllllIllIIllIlIlIIlll, llllllllllllllllIllIIllIlIlIIllI, llllllllllllllllIllIIllIlIlIIlIl, llllllllllllllllIllIIllIlIlIIIlI, llllllllllllllllIllIIllIlIlIIIIl, llllllllllllllllIllIIllIlIlIIIII, llllllllllllllllIllIIllIlIlIIIll, 255);
    }
    
    public static void drawFullFace(final AxisAlignedBB llllllllllllllllIllIIlIlllIIlIlI, final BlockPos llllllllllllllllIllIIlIlllIIIIII, final float llllllllllllllllIllIIlIlllIIlIII, final int llllllllllllllllIllIIlIlllIIIlll, final int llllllllllllllllIllIIlIlllIIIllI) {
        final int llllllllllllllllIllIIlIlllIIIlIl = llllllllllllllllIllIIlIlllIIIlll >>> 24 & 0xFF;
        final int llllllllllllllllIllIIlIlllIIIlII = llllllllllllllllIllIIlIlllIIIlll >>> 16 & 0xFF;
        final int llllllllllllllllIllIIlIlllIIIIll = llllllllllllllllIllIIlIlllIIIlll >>> 8 & 0xFF;
        final int llllllllllllllllIllIIlIlllIIIIlI = llllllllllllllllIllIIlIlllIIIlll & 0xFF;
        drawFullFace(llllllllllllllllIllIIlIlllIIlIlI, llllllllllllllllIllIIlIlllIIIIII, llllllllllllllllIllIIlIlllIIlIII, llllllllllllllllIllIIlIlllIIIlII, llllllllllllllllIllIIlIlllIIIIll, llllllllllllllllIllIIlIlllIIIIlI, llllllllllllllllIllIIlIlllIIIlIl, llllllllllllllllIllIIlIlllIIIllI);
    }
    
    public static void drawBox2(final AxisAlignedBB llllllllllllllllIllIIllIllllIIll, final int llllllllllllllllIllIIllIlllllIIl, final int llllllllllllllllIllIIllIllllIIIl) {
        final int llllllllllllllllIllIIllIllllIlll = llllllllllllllllIllIIllIlllllIIl >>> 24 & 0xFF;
        final int llllllllllllllllIllIIllIllllIllI = llllllllllllllllIllIIllIlllllIIl >>> 16 & 0xFF;
        final int llllllllllllllllIllIIllIllllIlIl = llllllllllllllllIllIIllIlllllIIl >>> 8 & 0xFF;
        final int llllllllllllllllIllIIllIllllIlII = llllllllllllllllIllIIllIlllllIIl & 0xFF;
        drawBox2(llllllllllllllllIllIIllIllllIIll, llllllllllllllllIllIIllIllllIllI, llllllllllllllllIllIIllIllllIlIl, llllllllllllllllIllIIllIllllIlII, llllllllllllllllIllIIllIllllIlll, llllllllllllllllIllIIllIllllIIIl);
    }
    
    public static void drawIndicator(final AxisAlignedBB llllllllllllllllIllIIlIlIllIIlIl, final int llllllllllllllllIllIIlIlIllIIlII, final int llllllllllllllllIllIIlIlIllIIIll) {
        final int llllllllllllllllIllIIlIlIllIlIIl = llllllllllllllllIllIIlIlIllIIlII >>> 24 & 0xFF;
        final int llllllllllllllllIllIIlIlIllIlIII = llllllllllllllllIllIIlIlIllIIlII >>> 16 & 0xFF;
        final int llllllllllllllllIllIIlIlIllIIlll = llllllllllllllllIllIIlIlIllIIlII >>> 8 & 0xFF;
        final int llllllllllllllllIllIIlIlIllIIllI = llllllllllllllllIllIIlIlIllIIlII & 0xFF;
        drawIndicator(llllllllllllllllIllIIlIlIllIIlIl, llllllllllllllllIllIIlIlIllIlIII, llllllllllllllllIllIIlIlIllIIlll, llllllllllllllllIllIIlIlIllIIllI, llllllllllllllllIllIIlIlIllIlIIl, llllllllllllllllIllIIlIlIllIIIll);
    }
    
    public static void drawFace(final BufferBuilder llllllllllllllllIllIlIIIIIIIlllI, final float llllllllllllllllIllIlIIIIIIIIIIl, final float llllllllllllllllIllIlIIIIIIIllII, final float llllllllllllllllIllIIlllllllllll, final float llllllllllllllllIllIlIIIIIIIlIlI, final float llllllllllllllllIllIlIIIIIIIlIIl, final float llllllllllllllllIllIIlllllllllIl, final int llllllllllllllllIllIIlllllllllII, final int llllllllllllllllIllIIllllllllIll, final int llllllllllllllllIllIlIIIIIIIIlIl, final int llllllllllllllllIllIlIIIIIIIIlII, final int llllllllllllllllIllIIllllllllIII) {
        if ((llllllllllllllllIllIIllllllllIII & 0x1) != 0x0) {
            llllllllllllllllIllIlIIIIIIIlllI.pos((double)(llllllllllllllllIllIlIIIIIIIIIIl + llllllllllllllllIllIlIIIIIIIlIlI), (double)llllllllllllllllIllIlIIIIIIIllII, (double)llllllllllllllllIllIIlllllllllll).color(llllllllllllllllIllIIlllllllllII, llllllllllllllllIllIIllllllllIll, llllllllllllllllIllIlIIIIIIIIlIl, llllllllllllllllIllIlIIIIIIIIlII).endVertex();
            llllllllllllllllIllIlIIIIIIIlllI.pos((double)(llllllllllllllllIllIlIIIIIIIIIIl + llllllllllllllllIllIlIIIIIIIlIlI), (double)llllllllllllllllIllIlIIIIIIIllII, (double)(llllllllllllllllIllIIlllllllllll + llllllllllllllllIllIIlllllllllIl)).color(llllllllllllllllIllIIlllllllllII, llllllllllllllllIllIIllllllllIll, llllllllllllllllIllIlIIIIIIIIlIl, llllllllllllllllIllIlIIIIIIIIlII).endVertex();
            llllllllllllllllIllIlIIIIIIIlllI.pos((double)llllllllllllllllIllIlIIIIIIIIIIl, (double)llllllllllllllllIllIlIIIIIIIllII, (double)(llllllllllllllllIllIIlllllllllll + llllllllllllllllIllIIlllllllllIl)).color(llllllllllllllllIllIIlllllllllII, llllllllllllllllIllIIllllllllIll, llllllllllllllllIllIlIIIIIIIIlIl, llllllllllllllllIllIlIIIIIIIIlII).endVertex();
            llllllllllllllllIllIlIIIIIIIlllI.pos((double)llllllllllllllllIllIlIIIIIIIIIIl, (double)llllllllllllllllIllIlIIIIIIIllII, (double)llllllllllllllllIllIIlllllllllll).color(llllllllllllllllIllIIlllllllllII, llllllllllllllllIllIIllllllllIll, llllllllllllllllIllIlIIIIIIIIlIl, llllllllllllllllIllIlIIIIIIIIlII).endVertex();
        }
    }
    
    public static void drawBoundingBox(final AxisAlignedBB llllllllllllllllIllIIlIllIIllIIl, final float llllllllllllllllIllIIlIllIIlIIIl, final int llllllllllllllllIllIIlIllIIlIIII) {
        final int llllllllllllllllIllIIlIllIIlIllI = llllllllllllllllIllIIlIllIIlIIII >>> 24 & 0xFF;
        final int llllllllllllllllIllIIlIllIIlIlIl = llllllllllllllllIllIIlIllIIlIIII >>> 16 & 0xFF;
        final int llllllllllllllllIllIIlIllIIlIlII = llllllllllllllllIllIIlIllIIlIIII >>> 8 & 0xFF;
        final int llllllllllllllllIllIIlIllIIlIIll = llllllllllllllllIllIIlIllIIlIIII & 0xFF;
        drawBoundingBox(llllllllllllllllIllIIlIllIIllIIl, llllllllllllllllIllIIlIllIIlIIIl, llllllllllllllllIllIIlIllIIlIlIl, llllllllllllllllIllIIlIllIIlIlII, llllllllllllllllIllIIlIllIIlIIll, llllllllllllllllIllIIlIllIIlIllI);
    }
    
    public static void drawFace(final BlockPos llllllllllllllllIllIlIIIIIlllIIl, final int llllllllllllllllIllIlIIIIIlllIII, final int llllllllllllllllIllIlIIIIIllIIII) {
        final int llllllllllllllllIllIlIIIIIllIllI = llllllllllllllllIllIlIIIIIlllIII >>> 24 & 0xFF;
        final int llllllllllllllllIllIlIIIIIllIlIl = llllllllllllllllIllIlIIIIIlllIII >>> 16 & 0xFF;
        final int llllllllllllllllIllIlIIIIIllIlII = llllllllllllllllIllIlIIIIIlllIII >>> 8 & 0xFF;
        final int llllllllllllllllIllIlIIIIIllIIll = llllllllllllllllIllIlIIIIIlllIII & 0xFF;
        drawFace(llllllllllllllllIllIlIIIIIlllIIl, llllllllllllllllIllIlIIIIIllIlIl, llllllllllllllllIllIlIIIIIllIlII, llllllllllllllllIllIlIIIIIllIIll, llllllllllllllllIllIlIIIIIllIllI, llllllllllllllllIllIlIIIIIllIIII);
    }
    
    public static void prepare(final int llllllllllllllllIllIlIIllIlIIlll) {
        prepareGL();
        begin(llllllllllllllllIllIlIIllIlIIlll);
    }
    
    public static void drawBoundingBoxFace(final AxisAlignedBB llllllllllllllllIllIIlIlIIllIllI, final float llllllllllllllllIllIIlIlIIllllIl, final int llllllllllllllllIllIIlIlIIllllII, final int llllllllllllllllIllIIlIlIIlllIll, final int llllllllllllllllIllIIlIlIIllIIlI, final int llllllllllllllllIllIIlIlIIllIIIl) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(llllllllllllllllIllIIlIlIIllllIl);
        final Tessellator llllllllllllllllIllIIlIlIIlllIII = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllIllIIlIlIIllIlll = llllllllllllllllIllIIlIlIIlllIII.getBuffer();
        llllllllllllllllIllIIlIlIIllIlll.begin(3, DefaultVertexFormats.POSITION_COLOR);
        llllllllllllllllIllIIlIlIIllIlll.pos(llllllllllllllllIllIIlIlIIllIllI.minX, llllllllllllllllIllIIlIlIIllIllI.minY, llllllllllllllllIllIIlIlIIllIllI.minZ).color(llllllllllllllllIllIIlIlIIllllII, llllllllllllllllIllIIlIlIIlllIll, llllllllllllllllIllIIlIlIIllIIlI, llllllllllllllllIllIIlIlIIllIIIl).endVertex();
        llllllllllllllllIllIIlIlIIllIlll.pos(llllllllllllllllIllIIlIlIIllIllI.minX, llllllllllllllllIllIIlIlIIllIllI.minY, llllllllllllllllIllIIlIlIIllIllI.maxZ).color(llllllllllllllllIllIIlIlIIllllII, llllllllllllllllIllIIlIlIIlllIll, llllllllllllllllIllIIlIlIIllIIlI, llllllllllllllllIllIIlIlIIllIIIl).endVertex();
        llllllllllllllllIllIIlIlIIllIlll.pos(llllllllllllllllIllIIlIlIIllIllI.maxX, llllllllllllllllIllIIlIlIIllIllI.minY, llllllllllllllllIllIIlIlIIllIllI.maxZ).color(llllllllllllllllIllIIlIlIIllllII, llllllllllllllllIllIIlIlIIlllIll, llllllllllllllllIllIIlIlIIllIIlI, llllllllllllllllIllIIlIlIIllIIIl).endVertex();
        llllllllllllllllIllIIlIlIIllIlll.pos(llllllllllllllllIllIIlIlIIllIllI.maxX, llllllllllllllllIllIIlIlIIllIllI.minY, llllllllllllllllIllIIlIlIIllIllI.minZ).color(llllllllllllllllIllIIlIlIIllllII, llllllllllllllllIllIIlIlIIlllIll, llllllllllllllllIllIIlIlIIllIIlI, llllllllllllllllIllIIlIlIIllIIIl).endVertex();
        llllllllllllllllIllIIlIlIIllIlll.pos(llllllllllllllllIllIIlIlIIllIllI.minX, llllllllllllllllIllIIlIlIIllIllI.minY, llllllllllllllllIllIIlIlIIllIllI.minZ).color(llllllllllllllllIllIIlIlIIllllII, llllllllllllllllIllIIlIlIIlllIll, llllllllllllllllIllIIlIlIIllIIlI, llllllllllllllllIllIIlIlIIllIIIl).endVertex();
        llllllllllllllllIllIIlIlIIlllIII.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
    
    public static void drawBox(final BlockPos llllllllllllllllIllIlIIIIllIlIlI, final int llllllllllllllllIllIlIIIIllIlIIl, final int llllllllllllllllIllIlIIIIllIlIII, final int llllllllllllllllIllIlIIIIllIIlll, final int llllllllllllllllIllIlIIIIllIIllI, final int llllllllllllllllIllIlIIIIllIlIll) {
        drawBox(XuluTessellator.INSTANCE.getBuffer(), (float)llllllllllllllllIllIlIIIIllIlIlI.x, (float)llllllllllllllllIllIlIIIIllIlIlI.y, (float)llllllllllllllllIllIlIIIIllIlIlI.z, 1.0f, 1.0f, 1.0f, llllllllllllllllIllIlIIIIllIlIIl, llllllllllllllllIllIlIIIIllIlIII, llllllllllllllllIllIlIIIIllIIlll, llllllllllllllllIllIlIIIIllIIllI, llllllllllllllllIllIlIIIIllIlIll);
    }
    
    public static void drawFullBoxAA(final AxisAlignedBB llllllllllllllllIllIIlIllllllIII, final float llllllllllllllllIllIIlIlllllIlll, final int llllllllllllllllIllIIlIllllIlllI, final int llllllllllllllllIllIIlIlllllIlIl) {
        final int llllllllllllllllIllIIlIlllllIlII = llllllllllllllllIllIIlIllllIlllI >>> 24 & 0xFF;
        final int llllllllllllllllIllIIlIlllllIIll = llllllllllllllllIllIIlIllllIlllI >>> 16 & 0xFF;
        final int llllllllllllllllIllIIlIlllllIIlI = llllllllllllllllIllIIlIllllIlllI >>> 8 & 0xFF;
        final int llllllllllllllllIllIIlIlllllIIIl = llllllllllllllllIllIIlIllllIlllI & 0xFF;
        drawFullBoxAA(llllllllllllllllIllIIlIllllllIII, llllllllllllllllIllIIlIlllllIlll, llllllllllllllllIllIIlIlllllIIll, llllllllllllllllIllIIlIlllllIIlI, llllllllllllllllIllIIlIlllllIIIl, llllllllllllllllIllIIlIlllllIlII, llllllllllllllllIllIIlIlllllIlIl);
    }
    
    public static void drawBoundingBox(final AxisAlignedBB llllllllllllllllIllIIlIllIIIIIll, final float llllllllllllllllIllIIlIllIIIIIlI, final int llllllllllllllllIllIIlIlIllllIIl, final int llllllllllllllllIllIIlIllIIIIIII, final int llllllllllllllllIllIIlIlIlllIlll, final int llllllllllllllllIllIIlIlIlllIllI) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(llllllllllllllllIllIIlIllIIIIIlI);
        final Tessellator llllllllllllllllIllIIlIlIlllllIl = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllIllIIlIlIlllllII = llllllllllllllllIllIIlIlIlllllIl.getBuffer();
        llllllllllllllllIllIIlIlIlllllII.begin(3, DefaultVertexFormats.POSITION_COLOR);
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.minX, llllllllllllllllIllIIlIllIIIIIll.minY, llllllllllllllllIllIIlIllIIIIIll.minZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.maxX, llllllllllllllllIllIIlIllIIIIIll.minY, llllllllllllllllIllIIlIllIIIIIll.minZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.maxX, llllllllllllllllIllIIlIllIIIIIll.minY, llllllllllllllllIllIIlIllIIIIIll.maxZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.minX, llllllllllllllllIllIIlIllIIIIIll.minY, llllllllllllllllIllIIlIllIIIIIll.maxZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.minX, llllllllllllllllIllIIlIllIIIIIll.minY, llllllllllllllllIllIIlIllIIIIIll.minZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.minX, llllllllllllllllIllIIlIllIIIIIll.maxY, llllllllllllllllIllIIlIllIIIIIll.minZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.maxX, llllllllllllllllIllIIlIllIIIIIll.maxY, llllllllllllllllIllIIlIllIIIIIll.minZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.maxX, llllllllllllllllIllIIlIllIIIIIll.minY, llllllllllllllllIllIIlIllIIIIIll.minZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllIl.draw();
        llllllllllllllllIllIIlIlIlllllII.begin(3, DefaultVertexFormats.POSITION_COLOR);
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.minX, llllllllllllllllIllIIlIllIIIIIll.maxY, llllllllllllllllIllIIlIllIIIIIll.minZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.minX, llllllllllllllllIllIIlIllIIIIIll.maxY, llllllllllllllllIllIIlIllIIIIIll.maxZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.minX, llllllllllllllllIllIIlIllIIIIIll.minY, llllllllllllllllIllIIlIllIIIIIll.maxZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllIl.draw();
        llllllllllllllllIllIIlIlIlllllII.begin(3, DefaultVertexFormats.POSITION_COLOR);
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.minX, llllllllllllllllIllIIlIllIIIIIll.maxY, llllllllllllllllIllIIlIllIIIIIll.maxZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.maxX, llllllllllllllllIllIIlIllIIIIIll.maxY, llllllllllllllllIllIIlIllIIIIIll.maxZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.maxX, llllllllllllllllIllIIlIllIIIIIll.minY, llllllllllllllllIllIIlIllIIIIIll.maxZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllIl.draw();
        llllllllllllllllIllIIlIlIlllllII.begin(3, DefaultVertexFormats.POSITION_COLOR);
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.maxX, llllllllllllllllIllIIlIllIIIIIll.maxY, llllllllllllllllIllIIlIllIIIIIll.maxZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllII.pos(llllllllllllllllIllIIlIllIIIIIll.maxX, llllllllllllllllIllIIlIllIIIIIll.maxY, llllllllllllllllIllIIlIllIIIIIll.minZ).color(llllllllllllllllIllIIlIlIllllIIl, llllllllllllllllIllIIlIllIIIIIII, llllllllllllllllIllIIlIlIlllIlll, llllllllllllllllIllIIlIlIlllIllI).endVertex();
        llllllllllllllllIllIIlIlIlllllIl.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawRectGradient(double llllllllllllllllIllIlIIlIIIIlllI, double llllllllllllllllIllIlIIlIIIIllIl, double llllllllllllllllIllIlIIlIIIIlIll, double llllllllllllllllIllIlIIlIIIIlIIl, final int llllllllllllllllIllIlIIlIIIIlIII, final int llllllllllllllllIllIlIIlIIlIIIlI) {
        if (llllllllllllllllIllIlIIlIIIIlllI < llllllllllllllllIllIlIIlIIIIlIll) {
            final double llllllllllllllllIllIlIIlIIlIllIl = llllllllllllllllIllIlIIlIIIIlllI;
            llllllllllllllllIllIlIIlIIIIlllI = llllllllllllllllIllIlIIlIIIIlIll;
            llllllllllllllllIllIlIIlIIIIlIll = llllllllllllllllIllIlIIlIIlIllIl;
        }
        if (llllllllllllllllIllIlIIlIIIIllIl < llllllllllllllllIllIlIIlIIIIlIIl) {
            final double llllllllllllllllIllIlIIlIIlIllII = llllllllllllllllIllIlIIlIIIIllIl;
            llllllllllllllllIllIlIIlIIIIllIl = llllllllllllllllIllIlIIlIIIIlIIl;
            llllllllllllllllIllIlIIlIIIIlIIl = llllllllllllllllIllIlIIlIIlIllII;
        }
        final float llllllllllllllllIllIlIIlIIlIIIII = (llllllllllllllllIllIlIIlIIIIlIII >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIlIIIllllI = (llllllllllllllllIllIlIIlIIIIlIII >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIlIIIlllIl = (llllllllllllllllIllIlIIlIIIIlIII >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIlIIIlIlll = (llllllllllllllllIllIlIIlIIIIlIII & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIlIIIlIlIl = (llllllllllllllllIllIlIIlIIlIIIlI >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIlIIIlIlII = (llllllllllllllllIllIlIIlIIlIIIlI >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIlIIIlIIll = (llllllllllllllllIllIlIIlIIlIIIlI >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllIllIlIIlIIIlIIlI = (llllllllllllllllIllIlIIlIIlIIIlI & 0xFF) / 255.0f;
        final Tessellator llllllllllllllllIllIlIIlIIIlIIIl = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllIllIlIIlIIIIllll = llllllllllllllllIllIlIIlIIIlIIIl.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        GlStateManager.color(llllllllllllllllIllIlIIlIIIllllI, llllllllllllllllIllIlIIlIIIlllIl, llllllllllllllllIllIlIIlIIIlIlll, llllllllllllllllIllIlIIlIIlIIIII);
        llllllllllllllllIllIlIIlIIIIllll.begin(7, DefaultVertexFormats.POSITION);
        llllllllllllllllIllIlIIlIIIIllll.pos(llllllllllllllllIllIlIIlIIIIlllI, llllllllllllllllIllIlIIlIIIIlIIl, 0.0).endVertex();
        llllllllllllllllIllIlIIlIIIIllll.pos(llllllllllllllllIllIlIIlIIIIlIll, llllllllllllllllIllIlIIlIIIIlIIl, 0.0).endVertex();
        llllllllllllllllIllIlIIlIIIIllll.pos(llllllllllllllllIllIlIIlIIIIlIll, llllllllllllllllIllIlIIlIIIIllIl, 0.0).endVertex();
        llllllllllllllllIllIlIIlIIIIllll.pos(llllllllllllllllIllIlIIlIIIIlllI, llllllllllllllllIllIlIIlIIIIllIl, 0.0).endVertex();
        llllllllllllllllIllIlIIlIIIlIIIl.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GL11.glShadeModel(7424);
    }
    
    public static void drawBox(final BufferBuilder llllllllllllllllIllIlIIIIlIllIII, final float llllllllllllllllIllIlIIIIlIIlIll, final float llllllllllllllllIllIlIIIIlIIlIlI, final float llllllllllllllllIllIlIIIIlIlIlIl, final float llllllllllllllllIllIlIIIIlIIlIII, final float llllllllllllllllIllIlIIIIlIlIIll, final float llllllllllllllllIllIlIIIIlIIIllI, final int llllllllllllllllIllIlIIIIlIIIlIl, final int llllllllllllllllIllIlIIIIlIlIIII, final int llllllllllllllllIllIlIIIIlIIllll, final int llllllllllllllllIllIlIIIIlIIlllI, final int llllllllllllllllIllIlIIIIlIIllIl) {
        if ((llllllllllllllllIllIlIIIIlIIllIl & 0x1) != 0x0) {
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
        }
        if ((llllllllllllllllIllIlIIIIlIIllIl & 0x2) != 0x0) {
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
        }
        if ((llllllllllllllllIllIlIIIIlIIllIl & 0x4) != 0x0) {
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
        }
        if ((llllllllllllllllIllIlIIIIlIIllIl & 0x8) != 0x0) {
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
        }
        if ((llllllllllllllllIllIlIIIIlIIllIl & 0x10) != 0x0) {
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)llllllllllllllllIllIlIIIIlIIlIll, (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
        }
        if ((llllllllllllllllIllIlIIIIlIIllIl & 0x20) != 0x0) {
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)llllllllllllllllIllIlIIIIlIIlIlI, (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)llllllllllllllllIllIlIIIIlIlIlIl).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
            llllllllllllllllIllIlIIIIlIllIII.pos((double)(llllllllllllllllIllIlIIIIlIIlIll + llllllllllllllllIllIlIIIIlIIlIII), (double)(llllllllllllllllIllIlIIIIlIIlIlI + llllllllllllllllIllIlIIIIlIlIIll), (double)(llllllllllllllllIllIlIIIIlIlIlIl + llllllllllllllllIllIlIIIIlIIIllI)).color(llllllllllllllllIllIlIIIIlIIIlIl, llllllllllllllllIllIlIIIIlIlIIII, llllllllllllllllIllIlIIIIlIIllll, llllllllllllllllIllIlIIIIlIIlllI).endVertex();
        }
    }
    
    public static void drawFaceOutline(final BufferBuilder llllllllllllllllIllIIlllllIIlllI, final float llllllllllllllllIllIIlllllIllIIl, final float llllllllllllllllIllIIlllllIllIII, final float llllllllllllllllIllIIlllllIIlIll, final float llllllllllllllllIllIIlllllIIlIlI, final float llllllllllllllllIllIIlllllIlIlIl, final float llllllllllllllllIllIIlllllIlIlII, final int llllllllllllllllIllIIlllllIlIIll, final int llllllllllllllllIllIIlllllIIIlll, final int llllllllllllllllIllIIlllllIIIllI, final int llllllllllllllllIllIIlllllIIIlIl, final int llllllllllllllllIllIIlllllIIllll) {
        if ((llllllllllllllllIllIIlllllIIllll & 0x1) != 0x0) {
            llllllllllllllllIllIIlllllIIlllI.pos((double)(llllllllllllllllIllIIlllllIllIIl + llllllllllllllllIllIIlllllIIlIlI), (double)llllllllllllllllIllIIlllllIllIII, (double)llllllllllllllllIllIIlllllIIlIll).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)(llllllllllllllllIllIIlllllIllIIl + llllllllllllllllIllIIlllllIIlIlI), (double)llllllllllllllllIllIIlllllIllIII, llllllllllllllllIllIIlllllIIlIll + 0.02).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)llllllllllllllllIllIIlllllIllIIl, (double)llllllllllllllllIllIIlllllIllIII, llllllllllllllllIllIIlllllIIlIll + 0.02).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)llllllllllllllllIllIIlllllIllIIl, (double)llllllllllllllllIllIIlllllIllIII, (double)llllllllllllllllIllIIlllllIIlIll).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos(llllllllllllllllIllIIlllllIllIIl + 0.02, (double)llllllllllllllllIllIIlllllIllIII, (double)llllllllllllllllIllIIlllllIIlIll).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos(llllllllllllllllIllIIlllllIllIIl + 0.02, (double)llllllllllllllllIllIIlllllIllIII, (double)(llllllllllllllllIllIIlllllIIlIll + llllllllllllllllIllIIlllllIlIlII)).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)llllllllllllllllIllIIlllllIllIIl, (double)llllllllllllllllIllIIlllllIllIII, (double)(llllllllllllllllIllIIlllllIIlIll + llllllllllllllllIllIIlllllIlIlII)).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)llllllllllllllllIllIIlllllIllIIl, (double)llllllllllllllllIllIIlllllIllIII, (double)llllllllllllllllIllIIlllllIIlIll).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)(llllllllllllllllIllIIlllllIllIIl + llllllllllllllllIllIIlllllIIlIlI), (double)llllllllllllllllIllIIlllllIllIII, llllllllllllllllIllIIlllllIIlIll + llllllllllllllllIllIIlllllIlIlII - 0.02).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)(llllllllllllllllIllIIlllllIllIIl + llllllllllllllllIllIIlllllIIlIlI), (double)llllllllllllllllIllIIlllllIllIII, (double)(llllllllllllllllIllIIlllllIIlIll + llllllllllllllllIllIIlllllIlIlII)).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)llllllllllllllllIllIIlllllIllIIl, (double)llllllllllllllllIllIIlllllIllIII, (double)(llllllllllllllllIllIIlllllIIlIll + llllllllllllllllIllIIlllllIlIlII)).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)llllllllllllllllIllIIlllllIllIIl, (double)llllllllllllllllIllIIlllllIllIII, llllllllllllllllIllIIlllllIIlIll + llllllllllllllllIllIIlllllIlIlII - 0.02).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)(llllllllllllllllIllIIlllllIllIIl + llllllllllllllllIllIIlllllIIlIlI), (double)llllllllllllllllIllIIlllllIllIII, (double)llllllllllllllllIllIIlllllIIlIll).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos((double)(llllllllllllllllIllIIlllllIllIIl + llllllllllllllllIllIIlllllIIlIlI), (double)llllllllllllllllIllIIlllllIllIII, (double)(llllllllllllllllIllIIlllllIIlIll + llllllllllllllllIllIIlllllIlIlII)).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos(llllllllllllllllIllIIlllllIllIIl + llllllllllllllllIllIIlllllIIlIlI - 0.02, (double)llllllllllllllllIllIIlllllIllIII, (double)(llllllllllllllllIllIIlllllIIlIll + llllllllllllllllIllIIlllllIlIlII)).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
            llllllllllllllllIllIIlllllIIlllI.pos(llllllllllllllllIllIIlllllIllIIl + llllllllllllllllIllIIlllllIIlIlI - 0.02, (double)llllllllllllllllIllIIlllllIllIII, (double)llllllllllllllllIllIIlllllIIlIll).color(llllllllllllllllIllIIlllllIlIIll, llllllllllllllllIllIIlllllIIIlll, llllllllllllllllIllIIlllllIIIllI, llllllllllllllllIllIIlllllIIIlIl).endVertex();
        }
    }
    
    public static void begin(final int llllllllllllllllIllIlIIllIlIIlII) {
        XuluTessellator.INSTANCE.getBuffer().begin(llllllllllllllllIllIlIIllIlIIlII, DefaultVertexFormats.POSITION_COLOR);
    }
}
