package org.newdawn.slick.util;

import org.newdawn.slick.*;

public class FontUtils
{
    public static void drawCenter(final Font lllllllllllllllllllllllllIIIIIlI, final String llllllllllllllllllllllllIllllIIl, final int llllllllllllllllllllllllIllllIII, final int llllllllllllllllllllllllIlllllIl, final int llllllllllllllllllllllllIlllIllI) {
        drawString(lllllllllllllllllllllllllIIIIIlI, llllllllllllllllllllllllIllllIIl, 2, llllllllllllllllllllllllIllllIII, llllllllllllllllllllllllIlllllIl, llllllllllllllllllllllllIlllIllI, Color.white);
    }
    
    public static void drawLeft(final Font lllllllllllllllllllllllllIIlIIII, final String lllllllllllllllllllllllllIIlIlII, final int lllllllllllllllllllllllllIIlIIlI, final int lllllllllllllllllllllllllIIlIIIl) {
        drawString(lllllllllllllllllllllllllIIlIIII, lllllllllllllllllllllllllIIlIlII, 1, lllllllllllllllllllllllllIIlIIlI, lllllllllllllllllllllllllIIlIIIl, 0, Color.white);
    }
    
    public static void drawRight(final Font llllllllllllllllllllllllIIlIlIII, final String llllllllllllllllllllllllIIlIIlll, final int llllllllllllllllllllllllIIlIIllI, final int llllllllllllllllllllllllIIlIIlIl, final int llllllllllllllllllllllllIIlIIlII, final Color llllllllllllllllllllllllIIlIlIIl) {
        drawString(llllllllllllllllllllllllIIlIlIII, llllllllllllllllllllllllIIlIIlll, 3, llllllllllllllllllllllllIIlIIllI, llllllllllllllllllllllllIIlIIlIl, llllllllllllllllllllllllIIlIIlII, llllllllllllllllllllllllIIlIlIIl);
    }
    
    private static int drawJustifiedSpaceSeparatedSubstrings(final Font lllllllllllllllllllllllIllIllllI, final String lllllllllllllllllllllllIlllIIlIl, final int lllllllllllllllllllllllIllIlllII, final int lllllllllllllllllllllllIllIllIll, final int lllllllllllllllllllllllIlllIIIlI) {
        int lllllllllllllllllllllllIlllIIIIl = 0;
        int lllllllllllllllllllllllIlllIIIII = 0;
        int lllllllllllllllllllllllIllIlllll = lllllllllllllllllllllllIllIlllII;
        while (lllllllllllllllllllllllIlllIIIIl < lllllllllllllllllllllllIlllIIlIl.length()) {
            lllllllllllllllllllllllIlllIIIII = lllllllllllllllllllllllIlllIIlIl.indexOf(32, lllllllllllllllllllllllIlllIIIIl);
            if (lllllllllllllllllllllllIlllIIIII == -1) {
                lllllllllllllllllllllllIlllIIIII = lllllllllllllllllllllllIlllIIlIl.length();
            }
            final String lllllllllllllllllllllllIlllIIlll = lllllllllllllllllllllllIlllIIlIl.substring(lllllllllllllllllllllllIlllIIIIl, lllllllllllllllllllllllIlllIIIII);
            lllllllllllllllllllllllIllIllllI.drawString((float)lllllllllllllllllllllllIllIlllll, (float)lllllllllllllllllllllllIllIllIll, lllllllllllllllllllllllIlllIIlll);
            lllllllllllllllllllllllIllIlllll += lllllllllllllllllllllllIllIllllI.getWidth(lllllllllllllllllllllllIlllIIlll) + lllllllllllllllllllllllIlllIIIlI;
            lllllllllllllllllllllllIlllIIIIl = lllllllllllllllllllllllIlllIIIII + 1;
        }
        return lllllllllllllllllllllllIllIlllll;
    }
    
    public static void drawCenter(final Font llllllllllllllllllllllllIllIIlll, final String llllllllllllllllllllllllIllIllII, final int llllllllllllllllllllllllIllIlIll, final int llllllllllllllllllllllllIllIIIlI, final int llllllllllllllllllllllllIllIlIIl, final Color llllllllllllllllllllllllIllIlIII) {
        drawString(llllllllllllllllllllllllIllIIlll, llllllllllllllllllllllllIllIllII, 2, llllllllllllllllllllllllIllIlIll, llllllllllllllllllllllllIllIIIlI, llllllllllllllllllllllllIllIlIIl, llllllllllllllllllllllllIllIlIII);
    }
    
    private static int calculateWidthOfJustifiedSpaceInPixels(final Font lllllllllllllllllllllllIlllllIlI, final String lllllllllllllllllllllllIlllllIIl, final int lllllllllllllllllllllllIllllIIll) {
        int lllllllllllllllllllllllIllllIlll = 0;
        int lllllllllllllllllllllllIllllIllI = 0;
        while (lllllllllllllllllllllllIllllIllI < lllllllllllllllllllllllIlllllIIl.length()) {
            if (lllllllllllllllllllllllIlllllIIl.charAt(lllllllllllllllllllllllIllllIllI++) == ' ') {
                ++lllllllllllllllllllllllIllllIlll;
            }
        }
        if (lllllllllllllllllllllllIllllIlll > 0) {
            lllllllllllllllllllllllIllllIlll = (lllllllllllllllllllllllIllllIIll + lllllllllllllllllllllllIlllllIlI.getWidth(" ") * lllllllllllllllllllllllIllllIlll) / lllllllllllllllllllllllIllllIlll;
        }
        return lllllllllllllllllllllllIllllIlll;
    }
    
    public static void drawRight(final Font llllllllllllllllllllllllIlIIIlII, final String llllllllllllllllllllllllIlIIllIl, final int llllllllllllllllllllllllIlIIlIll, final int llllllllllllllllllllllllIlIIlIIl, final int llllllllllllllllllllllllIlIIIIII) {
        drawString(llllllllllllllllllllllllIlIIIlII, llllllllllllllllllllllllIlIIllIl, 3, llllllllllllllllllllllllIlIIlIll, llllllllllllllllllllllllIlIIlIIl, llllllllllllllllllllllllIlIIIIII, Color.white);
    }
    
    public static final int drawString(final Font llllllllllllllllllllllllIIIIlIII, final String llllllllllllllllllllllllIIIIIlll, final int llllllllllllllllllllllllIIIIIllI, final int llllllllllllllllllllllllIIIIIlIl, final int llllllllllllllllllllllllIIIIIlII, final int llllllllllllllllllllllllIIIIlIll, final Color llllllllllllllllllllllllIIIIIIlI) {
        final int llllllllllllllllllllllllIIIIlIIl = 0;
        if (llllllllllllllllllllllllIIIIIllI == 1) {
            llllllllllllllllllllllllIIIIlIII.drawString((float)llllllllllllllllllllllllIIIIIlIl, (float)llllllllllllllllllllllllIIIIIlII, llllllllllllllllllllllllIIIIIlll, llllllllllllllllllllllllIIIIIIlI);
        }
        else if (llllllllllllllllllllllllIIIIIllI == 2) {
            llllllllllllllllllllllllIIIIlIII.drawString((float)(llllllllllllllllllllllllIIIIIlIl + llllllllllllllllllllllllIIIIlIll / 2 - llllllllllllllllllllllllIIIIlIII.getWidth(llllllllllllllllllllllllIIIIIlll) / 2), (float)llllllllllllllllllllllllIIIIIlII, llllllllllllllllllllllllIIIIIlll, llllllllllllllllllllllllIIIIIIlI);
        }
        else if (llllllllllllllllllllllllIIIIIllI == 3) {
            llllllllllllllllllllllllIIIIlIII.drawString((float)(llllllllllllllllllllllllIIIIIlIl + llllllllllllllllllllllllIIIIlIll - llllllllllllllllllllllllIIIIlIII.getWidth(llllllllllllllllllllllllIIIIIlll)), (float)llllllllllllllllllllllllIIIIIlII, llllllllllllllllllllllllIIIIIlll, llllllllllllllllllllllllIIIIIIlI);
        }
        else if (llllllllllllllllllllllllIIIIIllI == 4) {
            final int llllllllllllllllllllllllIIIlIIIl = llllllllllllllllllllllllIIIIlIll - llllllllllllllllllllllllIIIIlIII.getWidth(llllllllllllllllllllllllIIIIIlll);
            if (llllllllllllllllllllllllIIIlIIIl <= 0) {
                llllllllllllllllllllllllIIIIlIII.drawString((float)llllllllllllllllllllllllIIIIIlIl, (float)llllllllllllllllllllllllIIIIIlII, llllllllllllllllllllllllIIIIIlll, llllllllllllllllllllllllIIIIIIlI);
            }
            return drawJustifiedSpaceSeparatedSubstrings(llllllllllllllllllllllllIIIIlIII, llllllllllllllllllllllllIIIIIlll, llllllllllllllllllllllllIIIIIlIl, llllllllllllllllllllllllIIIIIlII, calculateWidthOfJustifiedSpaceInPixels(llllllllllllllllllllllllIIIIlIII, llllllllllllllllllllllllIIIIIlll, llllllllllllllllllllllllIIIlIIIl));
        }
        return llllllllllllllllllllllllIIIIlIIl;
    }
    
    public class Alignment
    {
        static {
            CENTER = 2;
            LEFT = 1;
            JUSTIFY = 4;
            RIGHT = 3;
        }
    }
}
