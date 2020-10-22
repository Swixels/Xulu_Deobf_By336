package com.elementars.eclient.util;

import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import java.util.function.*;
import java.util.*;

public class RenderUtils
{
    private final /* synthetic */ Map<Integer, Boolean> glCapMap;
    
    public void disableGlCap(final int lIlIlIlIllIlI) {
        this.setGlCap(lIlIlIlIllIlI, false);
    }
    
    private void glColor(final int lIlIlIllllIlI) {
        final float lIlIlIllllllI = (lIlIlIllllIlI >> 24 & 0xFF) / 255.0f;
        final float lIlIlIlllllIl = (lIlIlIllllIlI >> 16 & 0xFF) / 255.0f;
        final float lIlIlIlllllII = (lIlIlIllllIlI >> 8 & 0xFF) / 255.0f;
        final float lIlIlIllllIll = (lIlIlIllllIlI & 0xFF) / 255.0f;
        GlStateManager.color(lIlIlIlllllIl, lIlIlIlllllII, lIlIlIllllIll, lIlIlIllllllI);
    }
    
    public void enableGlCap(final int lIlIlIllIllll) {
        this.setGlCap(lIlIlIllIllll, true);
    }
    
    public void setGlCap(final int lIlIlIlIIIlII, final boolean lIlIlIlIIIIII) {
        this.glCapMap.put(lIlIlIlIIIlII, GL11.glGetBoolean(lIlIlIlIIIlII));
        this.setGlState(lIlIlIlIIIlII, lIlIlIlIIIIII);
    }
    
    public void glColor(final int lIlIllIIlllIl, final int lIlIllIIllIII, final int lIlIllIIllIll, final int lIlIllIIlIllI) {
        GlStateManager.color(lIlIllIIlllIl / 255.0f, lIlIllIIllIII / 255.0f, lIlIllIIllIll / 255.0f, lIlIllIIlIllI / 255.0f);
    }
    
    public void setGlState(final int lIlIlIIllllII, final boolean lIlIlIIlllIIl) {
        if (lIlIlIIlllIIl) {
            GL11.glEnable(lIlIlIIllllII);
        }
        else {
            GL11.glDisable(lIlIlIIllllII);
        }
    }
    
    public void glColor(final Color lIlIllIIIllll) {
        final float lIlIllIIIlllI = lIlIllIIIllll.getRed() / 255.0f;
        final float lIlIllIIIllIl = lIlIllIIIllll.getGreen() / 255.0f;
        final float lIlIllIIIllII = lIlIllIIIllll.getBlue() / 255.0f;
        final float lIlIllIIIlIll = lIlIllIIIllll.getAlpha() / 255.0f;
        GlStateManager.color(lIlIllIIIlllI, lIlIllIIIllIl, lIlIllIIIllII, lIlIllIIIlIll);
    }
    
    public void resetCaps() {
        this.glCapMap.forEach(this::setGlState);
    }
    
    public void enableGlCap(final int... lIlIlIllIIIlI) {
        final String lIlIlIllIIIIl = (Object)lIlIlIllIIIlI;
        final int lIlIlIllIIIII = lIlIlIllIIIIl.length;
        for (float lIlIlIlIlllll = 0; lIlIlIlIlllll < lIlIlIllIIIII; ++lIlIlIlIlllll) {
            final int lIlIlIllIIllI = lIlIlIllIIIIl[lIlIlIlIlllll];
            this.setGlCap(lIlIlIllIIllI, true);
        }
    }
    
    public RenderUtils() {
        this.glCapMap = new HashMap<Integer, Boolean>();
    }
    
    public void disableGlCap(final int... lIlIlIlIIllll) {
        final char lIlIlIlIIllII = (Object)lIlIlIlIIllll;
        final String lIlIlIlIIlIll = (String)lIlIlIlIIllII.length;
        for (float lIlIlIlIIlIlI = 0; lIlIlIlIIlIlI < lIlIlIlIIlIll; ++lIlIlIlIIlIlI) {
            final int lIlIlIlIlIIIl = lIlIlIlIIllII[lIlIlIlIIlIlI];
            this.setGlCap(lIlIlIlIlIIIl, false);
        }
    }
}
