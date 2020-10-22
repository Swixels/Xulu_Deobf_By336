package com.elementars.eclient.font;

import java.io.*;
import java.awt.*;

public class FontInit
{
    public void initFonts() {
        try {
            final GraphicsEnvironment llllllllllllllllIlIIllIIllIlIlIl = GraphicsEnvironment.getLocalGraphicsEnvironment();
            llllllllllllllllIlIIllIIllIlIlIl.registerFont(Font.createFont(0, FontInit.class.getResourceAsStream("/fonts/Comfortaa-Regular.ttf")));
            llllllllllllllllIlIIllIIllIlIlIl.registerFont(Font.createFont(0, FontInit.class.getResourceAsStream("/fonts/GOTHIC.TTF")));
            llllllllllllllllIlIIllIIllIlIlIl.registerFont(Font.createFont(0, FontInit.class.getResourceAsStream("/fonts/MODERN SPACE.ttf")));
        }
        catch (IOException | FontFormatException ex2) {
            final Exception ex;
            final Exception llllllllllllllllIlIIllIIllIlIlII = ex;
            llllllllllllllllIlIIllIIllIlIlII.printStackTrace();
        }
    }
}
