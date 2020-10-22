package dev.xulu.newgui.util;

import com.elementars.eclient.util.*;
import net.minecraft.util.*;

public class FontUtil implements Helper
{
    public static void drawTotalCenteredStringWithShadow(final String llllllllllllllllllIllllIIIllIlll, final double llllllllllllllllllIllllIIIllIIlI, final double llllllllllllllllllIllllIIIllIlIl, final int llllllllllllllllllIllllIIIllIlII) {
        drawStringWithShadow(llllllllllllllllllIllllIIIllIlll, llllllllllllllllllIllllIIIllIIlI - FontUtil.fontRenderer.getStringWidth(llllllllllllllllllIllllIIIllIlll) / 2, llllllllllllllllllIllllIIIllIlIl - FontUtil.fontRenderer.FONT_HEIGHT / 2.0f, llllllllllllllllllIllllIIIllIlII);
    }
    
    public static int getStringWidth(final String llllllllllllllllllIllllIIllllIIl) {
        return FontUtil.fontRenderer.getStringWidth(StringUtils.stripControlCodes(llllllllllllllllllIllllIIllllIIl));
    }
    
    public static int getFontHeight() {
        return FontUtil.fontRenderer.FONT_HEIGHT;
    }
    
    public static void drawTotalCenteredString(final String llllllllllllllllllIllllIIIllllll, final double llllllllllllllllllIllllIIIlllllI, final double llllllllllllllllllIllllIIIllllIl, final int llllllllllllllllllIllllIIIllllII) {
        drawString(llllllllllllllllllIllllIIIllllll, llllllllllllllllllIllllIIIlllllI - FontUtil.fontRenderer.getStringWidth(llllllllllllllllllIllllIIIllllll) / 2, llllllllllllllllllIllllIIIllllIl - FontUtil.fontRenderer.FONT_HEIGHT / 2, llllllllllllllllllIllllIIIllllII);
    }
    
    public static void drawCenteredString(final String llllllllllllllllllIllllIIlIlIlll, final double llllllllllllllllllIllllIIlIllIlI, final double llllllllllllllllllIllllIIlIllIIl, final int llllllllllllllllllIllllIIlIlIlII) {
        drawString(llllllllllllllllllIllllIIlIlIlll, llllllllllllllllllIllllIIlIllIlI - FontUtil.fontRenderer.getStringWidth(llllllllllllllllllIllllIIlIlIlll) / 2, llllllllllllllllllIllllIIlIllIIl, llllllllllllllllllIllllIIlIlIlII);
    }
    
    public static void drawString(final String llllllllllllllllllIllllIIlllIIll, final double llllllllllllllllllIllllIIllIlllI, final double llllllllllllllllllIllllIIllIllIl, final int llllllllllllllllllIllllIIlllIIII) {
        FontUtil.fontRenderer.drawString(llllllllllllllllllIllllIIlllIIll, (int)llllllllllllllllllIllllIIllIlllI, (int)llllllllllllllllllIllllIIllIllIl, llllllllllllllllllIllllIIlllIIII);
    }
    
    public static void drawStringWithShadow(final String llllllllllllllllllIllllIIllIIlll, final double llllllllllllllllllIllllIIllIIllI, final double llllllllllllllllllIllllIIllIIIIl, final int llllllllllllllllllIllllIIllIIlII) {
        FontUtil.fontRenderer.drawStringWithShadow(llllllllllllllllllIllllIIllIIlll, (float)llllllllllllllllllIllllIIllIIllI, (float)llllllllllllllllllIllllIIllIIIIl, llllllllllllllllllIllllIIllIIlII);
    }
    
    public static void drawCenteredStringWithShadow(final String llllllllllllllllllIllllIIlIIlIll, final double llllllllllllllllllIllllIIlIIlIlI, final double llllllllllllllllllIllllIIlIIllIl, final int llllllllllllllllllIllllIIlIIlIII) {
        drawStringWithShadow(llllllllllllllllllIllllIIlIIlIll, llllllllllllllllllIllllIIlIIlIlI - FontUtil.fontRenderer.getStringWidth(llllllllllllllllllIllllIIlIIlIll) / 2, llllllllllllllllllIllllIIlIIllIl, llllllllllllllllllIllllIIlIIlIII);
    }
}
