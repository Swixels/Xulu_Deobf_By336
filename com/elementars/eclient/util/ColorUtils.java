package com.elementars.eclient.util;

import java.awt.*;
import java.util.*;

public class ColorUtils
{
    public static int toRGBA(final float[] lllllllllllllllllIlIllIllIlIlIII) {
        if (lllllllllllllllllIlIllIllIlIlIII.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA(lllllllllllllllllIlIllIllIlIlIII[0], lllllllllllllllllIlIllIllIlIlIII[1], lllllllllllllllllIlIllIllIlIlIII[2], lllllllllllllllllIlIllIllIlIlIII[3]);
    }
    
    public static int toRGBA(final double[] lllllllllllllllllIlIllIllIlIIlII) {
        if (lllllllllllllllllIlIllIllIlIIlII.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA((float)lllllllllllllllllIlIllIllIlIIlII[0], (float)lllllllllllllllllIlIllIllIlIIlII[1], (float)lllllllllllllllllIlIllIllIlIIlII[2], (float)lllllllllllllllllIlIllIllIlIIlII[3]);
    }
    
    public static int toRGBA(final double lllllllllllllllllIlIllIllllllIll, final double lllllllllllllllllIlIllIllllllIlI, final double lllllllllllllllllIlIllIllllllIIl, final double lllllllllllllllllIlIllIllllllIII) {
        return toRGBA((float)lllllllllllllllllIlIllIllllllIll, (float)lllllllllllllllllIlIllIllllllIlI, (float)lllllllllllllllllIlIllIllllllIIl, (float)lllllllllllllllllIlIllIllllllIII);
    }
    
    public int colorToHex(final Color lllllllllllllllllIlIllIlllIIlIII) {
        return Integer.decode(String.valueOf(new StringBuilder().append("0x").append(Integer.toHexString(lllllllllllllllllIlIllIlllIIlIII.getRGB()).substring(2))));
    }
    
    public String getColorNameFromHex(final int lllllllllllllllllIlIllIlllIIllll) {
        final int lllllllllllllllllIlIllIlllIlIIll = (lllllllllllllllllIlIllIlllIIllll & 0xFF0000) >> 16;
        final int lllllllllllllllllIlIllIlllIlIIlI = (lllllllllllllllllIlIllIlllIIllll & 0xFF00) >> 8;
        final int lllllllllllllllllIlIllIlllIlIIIl = lllllllllllllllllIlIllIlllIIllll & 0xFF;
        return this.getColorNameFromRgb(lllllllllllllllllIlIllIlllIlIIll, lllllllllllllllllIlIllIlllIlIIlI, lllllllllllllllllIlIllIlllIlIIIl);
    }
    
    public static int toRGBA(final int lllllllllllllllllIlIllIllIlllIIl, final int lllllllllllllllllIlIllIllIllllII, final int lllllllllllllllllIlIllIllIllIlll, final int lllllllllllllllllIlIllIllIllIllI) {
        return (lllllllllllllllllIlIllIllIlllIIl << 16) + (lllllllllllllllllIlIllIllIllllII << 8) + (lllllllllllllllllIlIllIllIllIlll << 0) + (lllllllllllllllllIlIllIllIllIllI << 24);
    }
    
    public static int toRGBA(final float lllllllllllllllllIlIllIllIllIIIl, final float lllllllllllllllllIlIllIllIllIIII, final float lllllllllllllllllIlIllIllIlIlIll, final float lllllllllllllllllIlIllIllIlIlllI) {
        return toRGBA((int)(lllllllllllllllllIlIllIllIllIIIl * 255.0f), (int)(lllllllllllllllllIlIllIllIllIIII * 255.0f), (int)(lllllllllllllllllIlIllIllIlIlIll * 255.0f), (int)(lllllllllllllllllIlIllIllIlIlllI * 255.0f));
    }
    
    public String getColorNameFromColor(final Color lllllllllllllllllIlIllIlllIIIlII) {
        return this.getColorNameFromRgb(lllllllllllllllllIlIllIlllIIIlII.getRed(), lllllllllllllllllIlIllIlllIIIlII.getGreen(), lllllllllllllllllIlIllIlllIIIlII.getBlue());
    }
    
    private ArrayList<ColorName> initColorList() {
        final ArrayList<ColorName> lllllllllllllllllIlIlllIIIIIIllI = new ArrayList<ColorName>();
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("AliceBlue", 240, 248, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("AntiqueWhite", 250, 235, 215));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Aqua", 0, 255, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Aquamarine", 127, 255, 212));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Azure", 240, 255, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Beige", 245, 245, 220));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Bisque", 255, 228, 196));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Black", 0, 0, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("BlanchedAlmond", 255, 235, 205));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Blue", 0, 0, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("BlueViolet", 138, 43, 226));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Brown", 165, 42, 42));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("BurlyWood", 222, 184, 135));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("CadetBlue", 95, 158, 160));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Chartreuse", 127, 255, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Chocolate", 210, 105, 30));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Coral", 255, 127, 80));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("CornflowerBlue", 100, 149, 237));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Cornsilk", 255, 248, 220));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Crimson", 220, 20, 60));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Cyan", 0, 255, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkBlue", 0, 0, 139));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkCyan", 0, 139, 139));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkGoldenRod", 184, 134, 11));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkGray", 169, 169, 169));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkGreen", 0, 100, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkKhaki", 189, 183, 107));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkMagenta", 139, 0, 139));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkOliveGreen", 85, 107, 47));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkOrange", 255, 140, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkOrchid", 153, 50, 204));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkRed", 139, 0, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkSalmon", 233, 150, 122));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkSeaGreen", 143, 188, 143));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkSlateBlue", 72, 61, 139));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkSlateGray", 47, 79, 79));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkTurquoise", 0, 206, 209));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DarkViolet", 148, 0, 211));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DeepPink", 255, 20, 147));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DeepSkyBlue", 0, 191, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DimGray", 105, 105, 105));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("DodgerBlue", 30, 144, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("FireBrick", 178, 34, 34));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("FloralWhite", 255, 250, 240));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("ForestGreen", 34, 139, 34));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Fuchsia", 255, 0, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Gainsboro", 220, 220, 220));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("GhostWhite", 248, 248, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Gold", 255, 215, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("GoldenRod", 218, 165, 32));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Gray", 128, 128, 128));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Green", 0, 128, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("GreenYellow", 173, 255, 47));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("HoneyDew", 240, 255, 240));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("HotPink", 255, 105, 180));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("IndianRed", 205, 92, 92));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Indigo", 75, 0, 130));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Ivory", 255, 255, 240));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Khaki", 240, 230, 140));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Lavender", 230, 230, 250));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LavenderBlush", 255, 240, 245));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LawnGreen", 124, 252, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LemonChiffon", 255, 250, 205));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightBlue", 173, 216, 230));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightCoral", 240, 128, 128));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightCyan", 224, 255, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightGoldenRodYellow", 250, 250, 210));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightGray", 211, 211, 211));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightGreen", 144, 238, 144));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightPink", 255, 182, 193));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightSalmon", 255, 160, 122));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightSeaGreen", 32, 178, 170));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightSkyBlue", 135, 206, 250));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightSlateGray", 119, 136, 153));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightSteelBlue", 176, 196, 222));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LightYellow", 255, 255, 224));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Lime", 0, 255, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("LimeGreen", 50, 205, 50));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Linen", 250, 240, 230));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Magenta", 255, 0, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Maroon", 128, 0, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumAquaMarine", 102, 205, 170));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumBlue", 0, 0, 205));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumOrchid", 186, 85, 211));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumPurple", 147, 112, 219));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumSeaGreen", 60, 179, 113));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumSlateBlue", 123, 104, 238));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumSpringGreen", 0, 250, 154));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumTurquoise", 72, 209, 204));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MediumVioletRed", 199, 21, 133));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MidnightBlue", 25, 25, 112));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MintCream", 245, 255, 250));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("MistyRose", 255, 228, 225));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Moccasin", 255, 228, 181));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("NavajoWhite", 255, 222, 173));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Navy", 0, 0, 128));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("OldLace", 253, 245, 230));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Olive", 128, 128, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("OliveDrab", 107, 142, 35));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Orange", 255, 165, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("OrangeRed", 255, 69, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Orchid", 218, 112, 214));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("PaleGoldenRod", 238, 232, 170));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("PaleGreen", 152, 251, 152));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("PaleTurquoise", 175, 238, 238));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("PaleVioletRed", 219, 112, 147));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("PapayaWhip", 255, 239, 213));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("PeachPuff", 255, 218, 185));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Peru", 205, 133, 63));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Pink", 255, 192, 203));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Plum", 221, 160, 221));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("PowderBlue", 176, 224, 230));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Purple", 128, 0, 128));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Red", 255, 0, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("RosyBrown", 188, 143, 143));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("RoyalBlue", 65, 105, 225));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SaddleBrown", 139, 69, 19));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Salmon", 250, 128, 114));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SandyBrown", 244, 164, 96));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SeaGreen", 46, 139, 87));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SeaShell", 255, 245, 238));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Sienna", 160, 82, 45));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Silver", 192, 192, 192));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SkyBlue", 135, 206, 235));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SlateBlue", 106, 90, 205));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SlateGray", 112, 128, 144));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Snow", 255, 250, 250));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SpringGreen", 0, 255, 127));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("SteelBlue", 70, 130, 180));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Tan", 210, 180, 140));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Teal", 0, 128, 128));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Thistle", 216, 191, 216));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Tomato", 255, 99, 71));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Turquoise", 64, 224, 208));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Violet", 238, 130, 238));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Wheat", 245, 222, 179));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("White", 255, 255, 255));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("WhiteSmoke", 245, 245, 245));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("Yellow", 255, 255, 0));
        lllllllllllllllllIlIlllIIIIIIllI.add(new ColorName("YellowGreen", 154, 205, 50));
        return lllllllllllllllllIlIlllIIIIIIllI;
    }
    
    public static final int changeAlpha(int lllllllllllllllllIlIllIllIIlllII, final int lllllllllllllllllIlIllIllIIllIll) {
        lllllllllllllllllIlIllIllIIlllII &= 0xFFFFFF;
        return (int)(lllllllllllllllllIlIllIllIIllIll << 24 | lllllllllllllllllIlIllIllIIlllII);
    }
    
    public String getColorNameFromRgb(final int lllllllllllllllllIlIllIllllIIIll, final int lllllllllllllllllIlIllIllllIlIIl, final int lllllllllllllllllIlIllIllllIIIIl) {
        final ArrayList<ColorName> lllllllllllllllllIlIllIllllIIlll = this.initColorList();
        ColorName lllllllllllllllllIlIllIllllIIllI = null;
        int lllllllllllllllllIlIllIllllIIlIl = Integer.MAX_VALUE;
        for (final ColorName lllllllllllllllllIlIllIllllIllIl : lllllllllllllllllIlIllIllllIIlll) {
            final int lllllllllllllllllIlIllIllllIllII = lllllllllllllllllIlIllIllllIllIl.computeMSE(lllllllllllllllllIlIllIllllIIIll, lllllllllllllllllIlIllIllllIlIIl, lllllllllllllllllIlIllIllllIIIIl);
            if (lllllllllllllllllIlIllIllllIllII < lllllllllllllllllIlIllIllllIIlIl) {
                lllllllllllllllllIlIllIllllIIlIl = lllllllllllllllllIlIllIllllIllII;
                lllllllllllllllllIlIllIllllIIllI = lllllllllllllllllIlIllIllllIllIl;
            }
        }
        if (lllllllllllllllllIlIllIllllIIllI != null) {
            return lllllllllllllllllIlIllIllllIIllI.getName();
        }
        return "No matched color name.";
    }
    
    public static int[] toRGBAArray(final int lllllllllllllllllIlIllIllIlIIIlI) {
        return new int[] { lllllllllllllllllIlIllIllIlIIIlI >> 16 & 0xFF, lllllllllllllllllIlIllIllIlIIIlI >> 8 & 0xFF, lllllllllllllllllIlIllIllIlIIIlI & 0xFF, lllllllllllllllllIlIllIllIlIIIlI >> 24 & 0xFF };
    }
    
    public class ColorName
    {
        public /* synthetic */ int b;
        public /* synthetic */ String name;
        public /* synthetic */ int r;
        public /* synthetic */ int g;
        
        public int getR() {
            return this.r;
        }
        
        public int getG() {
            return this.g;
        }
        
        public int getB() {
            return this.b;
        }
        
        public ColorName(final String lllllllllllllllllIlIIIIlIIIIllII, final int lllllllllllllllllIlIIIIlIIIIlIll, final int lllllllllllllllllIlIIIIlIIIIIlII, final int lllllllllllllllllIlIIIIlIIIIIIll) {
            this.r = lllllllllllllllllIlIIIIlIIIIlIll;
            this.g = lllllllllllllllllIlIIIIlIIIIIlII;
            this.b = lllllllllllllllllIlIIIIlIIIIIIll;
            this.name = lllllllllllllllllIlIIIIlIIIIllII;
        }
        
        public String getName() {
            return this.name;
        }
        
        public int computeMSE(final int lllllllllllllllllIlIIIIIllllllIl, final int lllllllllllllllllIlIIIIIlllllIII, final int lllllllllllllllllIlIIIIIllllIlll) {
            return ((lllllllllllllllllIlIIIIIllllllIl - this.r) * (lllllllllllllllllIlIIIIIllllllIl - this.r) + (lllllllllllllllllIlIIIIIlllllIII - this.g) * (lllllllllllllllllIlIIIIIlllllIII - this.g) + (lllllllllllllllllIlIIIIIllllIlll - this.b) * (lllllllllllllllllIlIIIIIllllIlll - this.b)) / 3;
        }
    }
    
    public static class Colors
    {
        static {
            RAINBOW = Integer.MIN_VALUE;
            WHITE = ColorUtils.toRGBA(255, 255, 255, 255);
            BLACK = ColorUtils.toRGBA(0, 0, 0, 255);
            RED = ColorUtils.toRGBA(255, 0, 0, 255);
            GREEN = ColorUtils.toRGBA(0, 255, 0, 255);
            BLUE = ColorUtils.toRGBA(0, 0, 255, 255);
            ORANGE = ColorUtils.toRGBA(255, 128, 0, 255);
            PURPLE = ColorUtils.toRGBA(163, 73, 163, 255);
            GRAY = ColorUtils.toRGBA(127, 127, 127, 255);
            DARK_RED = ColorUtils.toRGBA(64, 0, 0, 255);
            YELLOW = ColorUtils.toRGBA(255, 255, 0, 255);
        }
    }
}
