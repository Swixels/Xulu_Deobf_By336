package com.elementars.eclient.util;

import java.util.*;

public class ColorTextUtils
{
    public static /* synthetic */ ArrayList<String> colors;
    
    public static String getColor(final String lllllllllllllllllIIlllIIIlllllIl) {
        String lllllllllllllllllIIlllIIIlllllII = null;
        if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("White")) {
            final String lllllllllllllllllIIlllIIlIIIllIl = "&f";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Red")) {
            final String lllllllllllllllllIIlllIIlIIIllII = "&4";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Blue")) {
            final String lllllllllllllllllIIlllIIlIIIlIll = "&1";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Cyan")) {
            final String lllllllllllllllllIIlllIIlIIIlIlI = "&3";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Pink")) {
            final String lllllllllllllllllIIlllIIlIIIlIIl = "&d";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Black")) {
            final String lllllllllllllllllIIlllIIlIIIlIII = "&0";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Green")) {
            final String lllllllllllllllllIIlllIIlIIIIlll = "&2";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Purple")) {
            final String lllllllllllllllllIIlllIIlIIIIllI = "&5";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Yellow")) {
            final String lllllllllllllllllIIlllIIlIIIIlIl = "&e";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("LightRed")) {
            final String lllllllllllllllllIIlllIIlIIIIlII = "&c";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("LightBlue")) {
            final String lllllllllllllllllIIlllIIlIIIIIll = "&b";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("LightGreen")) {
            final String lllllllllllllllllIIlllIIlIIIIIlI = "&a";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Gold")) {
            final String lllllllllllllllllIIlllIIlIIIIIIl = "&6";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Gray")) {
            final String lllllllllllllllllIIlllIIlIIIIIII = "&8";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("Lavender")) {
            final String lllllllllllllllllIIlllIIIlllllll = "&9";
        }
        else if (lllllllllllllllllIIlllIIIlllllIl.equalsIgnoreCase("LightGray")) {
            final String lllllllllllllllllIIlllIIIllllllI = "&7";
        }
        else {
            lllllllllllllllllIIlllIIIlllllII = "&r";
        }
        return lllllllllllllllllIIlllIIIlllllII;
    }
    
    public static void initColors() {
        (ColorTextUtils.colors = new ArrayList<String>()).add("White");
        ColorTextUtils.colors.add("Black");
        ColorTextUtils.colors.add("Blue");
        ColorTextUtils.colors.add("Green");
        ColorTextUtils.colors.add("Cyan");
        ColorTextUtils.colors.add("Red");
        ColorTextUtils.colors.add("Purple");
        ColorTextUtils.colors.add("Gold");
        ColorTextUtils.colors.add("LightGray");
        ColorTextUtils.colors.add("Gray");
        ColorTextUtils.colors.add("Lavender");
        ColorTextUtils.colors.add("LightGreen");
        ColorTextUtils.colors.add("LightBlue");
        ColorTextUtils.colors.add("LightRed");
        ColorTextUtils.colors.add("Pink");
        ColorTextUtils.colors.add("Yellow");
    }
}
