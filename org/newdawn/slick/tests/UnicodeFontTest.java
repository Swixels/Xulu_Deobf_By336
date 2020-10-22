package org.newdawn.slick.tests;

import java.io.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.*;

public class UnicodeFontTest extends BasicGame
{
    private /* synthetic */ UnicodeFont unicodeFont;
    
    public UnicodeFontTest() {
        super("Font Test");
    }
    
    public static void main(final String[] llllllllllllllllllIllIllIIIIIllI) throws SlickException, IOException {
        Input.disableControllers();
        final AppGameContainer llllllllllllllllllIllIllIIIIIlIl = new AppGameContainer(new UnicodeFontTest());
        llllllllllllllllllIllIllIIIIIlIl.setDisplayMode(512, 600, false);
        llllllllllllllllllIllIllIIIIIlIl.setTargetFrameRate(20);
        llllllllllllllllllIllIllIIIIIlIl.start();
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIllIllIIIlIlII, final Graphics llllllllllllllllllIllIllIIIlIIll) {
        llllllllllllllllllIllIllIIIlIIll.setColor(Color.white);
        final String llllllllllllllllllIllIllIIIlIIlI = "This is UnicodeFont!\nIt rockz. Kerning: T,";
        this.unicodeFont.drawString(10.0f, 33.0f, llllllllllllllllllIllIllIIIlIIlI);
        llllllllllllllllllIllIllIIIlIIll.setColor(Color.red);
        llllllllllllllllllIllIllIIIlIIll.drawRect(10.0f, 33.0f, (float)this.unicodeFont.getWidth(llllllllllllllllllIllIllIIIlIIlI), (float)this.unicodeFont.getLineHeight());
        llllllllllllllllllIllIllIIIlIIll.setColor(Color.blue);
        final int llllllllllllllllllIllIllIIIlIIIl = this.unicodeFont.getYOffset(llllllllllllllllllIllIllIIIlIIlI);
        llllllllllllllllllIllIllIIIlIIll.drawRect(10.0f, (float)(33 + llllllllllllllllllIllIllIIIlIIIl), (float)this.unicodeFont.getWidth(llllllllllllllllllIllIllIIIlIIlI), (float)(this.unicodeFont.getHeight(llllllllllllllllllIllIllIIIlIIlI) - llllllllllllllllllIllIllIIIlIIIl));
        this.unicodeFont.addGlyphs("~!@!#!#$%___--");
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllllIllIllIIIIlIlI, final int llllllllllllllllllIllIllIIIIlIIl) throws SlickException {
        this.unicodeFont.loadGlyphs(1);
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIllIllIIIllIlI) throws SlickException {
        llllllllllllllllllIllIllIIIllIlI.setShowFPS(false);
        this.unicodeFont = new UnicodeFont("c:/windows/fonts/arial.ttf", 48, false, false);
        this.unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        llllllllllllllllllIllIllIIIllIlI.getGraphics().setBackground(Color.darkGray);
    }
}
