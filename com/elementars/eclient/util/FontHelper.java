package com.elementars.eclient.util;

import com.elementars.eclient.*;
import com.elementars.eclient.command.*;
import java.awt.*;
import com.elementars.eclient.font.*;
import com.elementars.eclient.font.custom.*;

public class FontHelper
{
    public static void setXdolfFontRenderer(final String llllllllllllllllIlIlIlllIIIIIIlI, final int llllllllllllllllIlIlIlllIIIIIIIl, final int llllllllllllllllIlIlIlllIIIIIlII, final boolean llllllllllllllllIlIlIlllIIIIIIll) {
        try {
            if (Xulu.getCorrectFont(llllllllllllllllIlIlIlllIIIIIIlI) == null && !llllllllllllllllIlIlIlllIIIIIIlI.equalsIgnoreCase("Xulu")) {
                Command.sendChatMessage("Invalid font!");
                return;
            }
            CFontManager.xFontRenderer = new XFontRenderer(new Font(Xulu.getCorrectFont(llllllllllllllllIlIlIlllIIIIIIlI), llllllllllllllllIlIlIlllIIIIIIIl, llllllllllllllllIlIlIlllIIIIIlII), llllllllllllllllIlIlIlllIIIIIIll, 8);
        }
        catch (Exception llllllllllllllllIlIlIlllIIIIIlll) {
            llllllllllllllllIlIlIlllIIIIIlll.printStackTrace();
        }
    }
    
    public static void setCFontRenderer(final String llllllllllllllllIlIlIlllIIIlIIlI, final int llllllllllllllllIlIlIlllIIIlIllI, final int llllllllllllllllIlIlIlllIIIlIlIl, final boolean llllllllllllllllIlIlIlllIIIlIlII, final boolean llllllllllllllllIlIlIlllIIIIlllI) {
        try {
            if (Xulu.getCorrectFont(llllllllllllllllIlIlIlllIIIlIIlI) == null) {
                Command.sendChatMessage("Invalid font!");
                return;
            }
            if (llllllllllllllllIlIlIlllIIIlIIlI.equalsIgnoreCase("Comfortaa Regular")) {
                CFontManager.customFont = new CustomFont(new Font("Comfortaa Regular", llllllllllllllllIlIlIlllIIIlIllI, llllllllllllllllIlIlIlllIIIlIlIl), llllllllllllllllIlIlIlllIIIlIlII, llllllllllllllllIlIlIlllIIIIlllI);
                return;
            }
            CFontManager.customFont = new CustomFont(new Font(Xulu.getCorrectFont(llllllllllllllllIlIlIlllIIIlIIlI), llllllllllllllllIlIlIlllIIIlIllI, llllllllllllllllIlIlIlllIIIlIlIl), llllllllllllllllIlIlIlllIIIlIlII, llllllllllllllllIlIlIlllIIIIlllI);
        }
        catch (Exception llllllllllllllllIlIlIlllIIIllIII) {
            llllllllllllllllIlIlIlllIIIllIII.printStackTrace();
        }
    }
}
