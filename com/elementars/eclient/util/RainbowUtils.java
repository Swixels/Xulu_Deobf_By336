package com.elementars.eclient.util;

import com.elementars.eclient.module.core.*;
import java.awt.*;

public class RainbowUtils
{
    static /* synthetic */ float hue;
    public static /* synthetic */ int r;
    private static /* synthetic */ int rgb;
    public static /* synthetic */ int a;
    public static /* synthetic */ int b;
    public static /* synthetic */ int g;
    
    static {
        RainbowUtils.hue = 0.01f;
    }
    
    public static void updateRainbow() {
        RainbowUtils.rgb = Color.HSBtoRGB(RainbowUtils.hue, Global.rainbowSaturation.getValue() / 255.0f, Global.rainbowLightness.getValue() / 255.0f);
        RainbowUtils.a = (RainbowUtils.rgb >>> 24 & 0xFF);
        RainbowUtils.r = (RainbowUtils.rgb >>> 16 & 0xFF);
        RainbowUtils.g = (RainbowUtils.rgb >>> 8 & 0xFF);
        RainbowUtils.b = (RainbowUtils.rgb & 0xFF);
        RainbowUtils.hue += Global.rainbowspeed.getValue() / 1000.0f;
        if (RainbowUtils.hue > 1.0f) {
            --RainbowUtils.hue;
        }
    }
}
