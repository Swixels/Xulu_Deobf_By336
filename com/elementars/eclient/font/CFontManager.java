package com.elementars.eclient.font;

import java.util.regex.*;
import com.elementars.eclient.font.custom.*;
import com.elementars.eclient.util.*;
import java.awt.*;

public class CFontManager
{
    public static /* synthetic */ RainbowTextRenderer rainbowTextRenderer;
    private static final /* synthetic */ Pattern COLOR_CODE_PATTERN;
    private final /* synthetic */ int[] colorCodes;
    public static /* synthetic */ CustomFont customFont;
    public static /* synthetic */ XFontRenderer xFontRenderer;
    
    public float drawStringWithShadow(final String lllllllllllllllllllIlIIIIIIIllIl, double lllllllllllllllllllIlIIIIIIIllII, final double lllllllllllllllllllIlIIIIIIlIIIl, final int lllllllllllllllllllIlIIIIIIlIIII, final boolean lllllllllllllllllllIlIIIIIIIllll) {
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal")) {
            return CFontManager.customFont.drawStringWithShadow(lllllllllllllllllllIlIIIIIIIllIl, lllllllllllllllllllIlIIIIIIIllII, lllllllllllllllllllIlIIIIIIlIIIl - com.elementars.eclient.module.core.CustomFont.fontOffset.getValue(), lllllllllllllllllllIlIIIIIIlIIII);
        }
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Xdolf")) {
            CFontManager.xFontRenderer.drawStringWithShadow(lllllllllllllllllllIlIIIIIIIllIl, (int)lllllllllllllllllllIlIIIIIIIllII, (int)lllllllllllllllllllIlIIIIIIlIIIl - com.elementars.eclient.module.core.CustomFont.fontOffset.getValue(), lllllllllllllllllllIlIIIIIIlIIII);
            return 0.0f;
        }
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Rainbow")) {
            int lllllllllllllllllllIlIIIIIIllIIl = lllllllllllllllllllIlIIIIIIIllll ? lllllllllllllllllllIlIIIIIIlIIII : -1;
            int lllllllllllllllllllIlIIIIIIllIII = 0;
            final char[] lllllllllllllllllllIlIIIIIIlIlll = lllllllllllllllllllIlIIIIIIIllIl.toCharArray();
            final String[] lllllllllllllllllllIlIIIIIIlIllI = CFontManager.COLOR_CODE_PATTERN.split(lllllllllllllllllllIlIIIIIIIllIl);
            int lllllllllllllllllllIlIIIIIIlIlIl = 0;
            final double lllllllllllllllllllIlIIIIIIIIIll = (Object)lllllllllllllllllllIlIIIIIIlIllI;
            final long lllllllllllllllllllIlIIIIIIIIIlI = lllllllllllllllllllIlIIIIIIIIIll.length;
            for (byte lllllllllllllllllllIlIIIIIIIIIIl = 0; lllllllllllllllllllIlIIIIIIIIIIl < lllllllllllllllllllIlIIIIIIIIIlI; ++lllllllllllllllllllIlIIIIIIIIIIl) {
                final String lllllllllllllllllllIlIIIIIIllIlI = lllllllllllllllllllIlIIIIIIIIIll[lllllllllllllllllllIlIIIIIIIIIIl];
                final byte lllllllllllllllllllIIllllllllllI;
                final String[] lllllllllllllllllllIlIIIIIIllIll = (Object)(lllllllllllllllllllIIllllllllllI = (byte)(Object)lllllllllllllllllllIlIIIIIIllIlI.split(""));
                final int length = lllllllllllllllllllIIllllllllllI.length;
                for (boolean lllllllllllllllllllIIlllllllllII = false; (lllllllllllllllllllIIlllllllllII ? 1 : 0) < length; ++lllllllllllllllllllIIlllllllllII) {
                    final String lllllllllllllllllllIlIIIIIIlllll = lllllllllllllllllllIIllllllllllI[lllllllllllllllllllIIlllllllllII];
                    if (lllllllllllllllllllIlIIIIIIllIII == 0) {
                        lllllllllllllllllllIlIIIIIIllIIl = CFontManager.rainbowTextRenderer.drawStringWithShadow(lllllllllllllllllllIlIIIIIIlllll, (float)lllllllllllllllllllIlIIIIIIIllII, (float)lllllllllllllllllllIlIIIIIIlIIIl, lllllllllllllllllllIlIIIIIIllIIl);
                    }
                    else {
                        lllllllllllllllllllIlIIIIIIllIIl = CFontManager.rainbowTextRenderer.updateRainbow(lllllllllllllllllllIlIIIIIIllIIl);
                        Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(lllllllllllllllllllIlIIIIIIlllll, (float)lllllllllllllllllllIlIIIIIIIllII, (float)lllllllllllllllllllIlIIIIIIlIIIl, lllllllllllllllllllIlIIIIIIllIII);
                    }
                    try {
                        lllllllllllllllllllIlIIIIIIIllII += CFontManager.rainbowTextRenderer.getCharWidth(lllllllllllllllllllIlIIIIIIlllll.charAt(0));
                    }
                    catch (Exception lllllllllllllllllllIlIIIIIlIIIII) {
                        lllllllllllllllllllIlIIIIIlIIIII.printStackTrace();
                    }
                    ++lllllllllllllllllllIlIIIIIIlIlIl;
                }
                if (lllllllllllllllllllIlIIIIIIlIlIl < lllllllllllllllllllIlIIIIIIlIlll.length) {
                    final char lllllllllllllllllllIlIIIIIIlllII = lllllllllllllllllllIlIIIIIIlIlll[lllllllllllllllllllIlIIIIIIlIlIl];
                    if (lllllllllllllllllllIlIIIIIIlllII == '§') {
                        final char lllllllllllllllllllIlIIIIIIllllI = lllllllllllllllllllIlIIIIIIlIlll[lllllllllllllllllllIlIIIIIIlIlIl + 1];
                        final int lllllllllllllllllllIlIIIIIIlllIl = "0123456789abcdef".indexOf(lllllllllllllllllllIlIIIIIIllllI);
                        if (lllllllllllllllllllIlIIIIIIlllIl < 0) {
                            if (lllllllllllllllllllIlIIIIIIllllI == 'r') {
                                lllllllllllllllllllIlIIIIIIllIII = 0;
                            }
                        }
                        else {
                            lllllllllllllllllllIlIIIIIIllIII = this.colorCodes[lllllllllllllllllllIlIIIIIIlllIl];
                        }
                        lllllllllllllllllllIlIIIIIIlIlIl += 2;
                    }
                }
            }
            return 0.0f;
        }
        return 0.0f;
    }
    
    public float drawStringWithShadow(final String lllllllllllllllllllIIlllllllIIll, final double lllllllllllllllllllIIlllllllIIlI, final double lllllllllllllllllllIIlllllllIIIl, final int lllllllllllllllllllIIllllllIlIll) {
        return this.drawStringWithShadow(lllllllllllllllllllIIlllllllIIll, lllllllllllllllllllIIlllllllIIlI, lllllllllllllllllllIIlllllllIIIl, lllllllllllllllllllIIllllllIlIll, false);
    }
    
    public float drawCenteredString(final String lllllllllllllllllllIIlllllIIlIll, final float lllllllllllllllllllIIlllllIIIllI, final float lllllllllllllllllllIIlllllIIIlIl, final int lllllllllllllllllllIIlllllIIIlII) {
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal")) {
            return CFontManager.customFont.drawCenteredString(lllllllllllllllllllIIlllllIIlIll, lllllllllllllllllllIIlllllIIIllI, lllllllllllllllllllIIlllllIIIlIl, lllllllllllllllllllIIlllllIIIlII);
        }
        CFontManager.xFontRenderer.drawCenteredString(lllllllllllllllllllIIlllllIIlIll, (int)lllllllllllllllllllIIlllllIIIllI, (int)lllllllllllllllllllIIlllllIIIlIl, lllllllllllllllllllIIlllllIIIlII);
        return 0.0f;
    }
    
    public float getHeight() {
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal")) {
            return (float)CFontManager.customFont.getHeight();
        }
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Rainbow")) {
            return (float)CFontManager.rainbowTextRenderer.getHeight();
        }
        return (float)CFontManager.xFontRenderer.getHeight();
    }
    
    static {
        COLOR_CODE_PATTERN = Pattern.compile("§[0123456789abcdefklmnor]");
        CFontManager.customFont = new CustomFont(new Font("Verdana", 0, 18), true, false);
        CFontManager.xFontRenderer = new XFontRenderer(new Font("Verdana", 0, 36), true, 8);
        CFontManager.rainbowTextRenderer = new RainbowTextRenderer();
    }
    
    public float drawCenteredStringWithShadow(final String lllllllllllllllllllIIlllllIlIlII, final float lllllllllllllllllllIIlllllIlIIll, final float lllllllllllllllllllIIlllllIlIllI, final int lllllllllllllllllllIIlllllIlIlIl) {
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal")) {
            return CFontManager.customFont.drawCenteredStringWithShadow(lllllllllllllllllllIIlllllIlIlII, lllllllllllllllllllIIlllllIlIIll, lllllllllllllllllllIIlllllIlIllI, lllllllllllllllllllIIlllllIlIlIl);
        }
        CFontManager.xFontRenderer.drawCenteredString(lllllllllllllllllllIIlllllIlIlII, (int)lllllllllllllllllllIIlllllIlIIll, (int)lllllllllllllllllllIIlllllIlIllI, lllllllllllllllllllIIlllllIlIlIl, true);
        return 0.0f;
    }
    
    public float drawString(final String lllllllllllllllllllIIllllIlllIII, final double lllllllllllllllllllIIllllIllllII, final double lllllllllllllllllllIIllllIllIllI, final int lllllllllllllllllllIIllllIlllIlI, final boolean lllllllllllllllllllIIllllIlllIIl) {
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal")) {
            return CFontManager.customFont.drawString(lllllllllllllllllllIIllllIlllIII, lllllllllllllllllllIIllllIllllII, lllllllllllllllllllIIllllIllIllI, lllllllllllllllllllIIllllIlllIlI, lllllllllllllllllllIIllllIlllIIl);
        }
        if (lllllllllllllllllllIIllllIlllIIl) {
            return (float)CFontManager.xFontRenderer.drawStringWithShadow(lllllllllllllllllllIIllllIlllIII, (float)lllllllllllllllllllIIllllIllllII, (float)lllllllllllllllllllIIllllIllIllI, lllllllllllllllllllIIllllIlllIlI);
        }
        return (float)CFontManager.xFontRenderer.drawString(lllllllllllllllllllIIllllIlllIII, (float)lllllllllllllllllllIIllllIllllII, (float)lllllllllllllllllllIIllllIllIllI, lllllllllllllllllllIIllllIlllIlI);
    }
    
    public int getStringWidth(final String lllllllllllllllllllIIllllIlIllll) {
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal")) {
            return CFontManager.customFont.getStringWidth(lllllllllllllllllllIIllllIlIllll);
        }
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Rainbow")) {
            return CFontManager.rainbowTextRenderer.getStringWidth(lllllllllllllllllllIIllllIlIllll);
        }
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Xdolf")) {
            return CFontManager.xFontRenderer.getStringWidth(lllllllllllllllllllIIllllIlIllll);
        }
        return 0;
    }
    
    public CFontManager() {
        this.colorCodes = new int[] { 0, 170, 43520, 43690, 11141120, 11141290, 16755200, 11184810, 5592405, 5592575, 5635925, 5636095, 16733525, 16733695, 16777045, 16777215 };
    }
    
    public float drawString(final String lllllllllllllllllllIIllllllIIIIl, final float lllllllllllllllllllIIllllllIIIII, final float lllllllllllllllllllIIllllllIIIll, final int lllllllllllllllllllIIllllllIIIlI) {
        if (com.elementars.eclient.module.core.CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal")) {
            return CFontManager.customFont.drawString(lllllllllllllllllllIIllllllIIIIl, lllllllllllllllllllIIllllllIIIII, lllllllllllllllllllIIllllllIIIll - com.elementars.eclient.module.core.CustomFont.fontOffset.getValue(), lllllllllllllllllllIIllllllIIIlI);
        }
        return (float)CFontManager.xFontRenderer.drawStringWithShadow(lllllllllllllllllllIIllllllIIIIl, lllllllllllllllllllIIllllllIIIII, lllllllllllllllllllIIllllllIIIll - com.elementars.eclient.module.core.CustomFont.fontOffset.getValue(), lllllllllllllllllllIIllllllIIIlI);
    }
}
