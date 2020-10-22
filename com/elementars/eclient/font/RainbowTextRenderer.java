package com.elementars.eclient.font;

import com.elementars.eclient.util.*;
import java.awt.*;
import com.elementars.eclient.module.core.*;

public class RainbowTextRenderer implements Helper
{
    public static /* synthetic */ int a;
    private static /* synthetic */ int rgb;
    public /* synthetic */ int FONT_HEIGHT;
    public static /* synthetic */ int r;
    public static /* synthetic */ int g;
    public static /* synthetic */ int b;
    static /* synthetic */ float hue;
    
    public int updateRainbow(final int llllllllllllllllllIlllllIIllIIlI) {
        float llllllllllllllllllIlllllIIllIIll = Color.RGBtoHSB(new Color(llllllllllllllllllIlllllIIllIIlI).getRed(), new Color(llllllllllllllllllIlllllIIllIIlI).getGreen(), new Color(llllllllllllllllllIlllllIIllIIlI).getBlue(), null)[0];
        llllllllllllllllllIlllllIIllIIll += Global.rainbowAmount.getValue() / 1000.0f;
        if (llllllllllllllllllIlllllIIllIIll > 1.0f) {
            --llllllllllllllllllIlllllIIllIIll;
        }
        return Color.HSBtoRGB(llllllllllllllllllIlllllIIllIIll, Global.rainbowSaturation.getValue() / 255.0f, Global.rainbowLightness.getValue() / 255.0f);
    }
    
    public int getCharWidth(final char llllllllllllllllllIlllllIIIIlIll) {
        return RainbowTextRenderer.fontRenderer.getCharWidth(llllllllllllllllllIlllllIIIIlIll);
    }
    
    static {
        RainbowTextRenderer.hue = 0.01f;
    }
    
    public void updateRainbow() {
        RainbowTextRenderer.rgb = Color.HSBtoRGB(RainbowTextRenderer.hue, Global.rainbowSaturation.getValue() / 255.0f, Global.rainbowLightness.getValue() / 255.0f);
        RainbowTextRenderer.a = (RainbowTextRenderer.rgb >>> 24 & 0xFF);
        RainbowTextRenderer.r = (RainbowTextRenderer.rgb >>> 16 & 0xFF);
        RainbowTextRenderer.g = (RainbowTextRenderer.rgb >>> 8 & 0xFF);
        RainbowTextRenderer.b = (RainbowTextRenderer.rgb & 0xFF);
        RainbowTextRenderer.hue += 1.0E-5f;
        if (RainbowTextRenderer.hue > 1.0f) {
            --RainbowTextRenderer.hue;
        }
    }
    
    public int getStringWidth(final String llllllllllllllllllIlllllIIIIllll) {
        return RainbowTextRenderer.fontRenderer.getStringWidth(llllllllllllllllllIlllllIIIIllll);
    }
    
    public int drawStringWithShadow(final String llllllllllllllllllIlllllIIlIlIlI, final float llllllllllllllllllIlllllIIlIlIIl, final float llllllllllllllllllIlllllIIlIIIll, int llllllllllllllllllIlllllIIlIIlll) {
        if (llllllllllllllllllIlllllIIlIIlll == -1) {
            llllllllllllllllllIlllllIIlIIlll = RainbowTextRenderer.rgb;
            this.updateRainbow();
        }
        else {
            llllllllllllllllllIlllllIIlIIlll = this.updateRainbow(llllllllllllllllllIlllllIIlIIlll);
        }
        RainbowTextRenderer.fontRenderer.drawStringWithShadow(llllllllllllllllllIlllllIIlIlIlI, llllllllllllllllllIlllllIIlIlIIl, llllllllllllllllllIlllllIIlIIIll, llllllllllllllllllIlllllIIlIIlll);
        return llllllllllllllllllIlllllIIlIIlll;
    }
    
    public int drawString(final String llllllllllllllllllIlllllIIIlIllI, final int llllllllllllllllllIlllllIIIlIlIl, final int llllllllllllllllllIlllllIIIlIlII, int llllllllllllllllllIlllllIIIlIIll) {
        if (llllllllllllllllllIlllllIIIlIIll == -1) {
            llllllllllllllllllIlllllIIIlIIll = RainbowTextRenderer.rgb;
        }
        this.updateRainbow();
        RainbowTextRenderer.fontRenderer.drawString(llllllllllllllllllIlllllIIIlIllI, llllllllllllllllllIlllllIIIlIlIl, llllllllllllllllllIlllllIIIlIlII, llllllllllllllllllIlllllIIIlIIll);
        return llllllllllllllllllIlllllIIIlIIll;
    }
    
    public RainbowTextRenderer() {
        this.FONT_HEIGHT = 9;
    }
    
    public int getHeight() {
        return this.FONT_HEIGHT;
    }
}
