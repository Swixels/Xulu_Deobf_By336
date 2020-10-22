package org.newdawn.slick.tests;

import org.newdawn.slick.util.*;
import org.newdawn.slick.*;

public class FontTest extends BasicGame
{
    private /* synthetic */ Image image;
    private /* synthetic */ AngelCodeFont font;
    private static /* synthetic */ AppGameContainer container;
    private /* synthetic */ AngelCodeFont font2;
    
    public FontTest() {
        super("Font Test");
    }
    
    public static void main(final String[] lllllllllllllllllllIIIIlIIIIlIll) {
        try {
            (FontTest.container = new AppGameContainer(new FontTest())).setDisplayMode(800, 600, false);
            FontTest.container.start();
        }
        catch (SlickException lllllllllllllllllllIIIIlIIIIllII) {
            lllllllllllllllllllIIIIlIIIIllII.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllllIIIIlIIIIllll, final char lllllllllllllllllllIIIIlIIIlIIII) {
        if (lllllllllllllllllllIIIIlIIIIllll == 1) {
            System.exit(0);
        }
        if (lllllllllllllllllllIIIIlIIIIllll == 57) {
            try {
                FontTest.container.setDisplayMode(640, 480, false);
            }
            catch (SlickException lllllllllllllllllllIIIIlIIIlIIll) {
                Log.error(lllllllllllllllllllIIIIlIIIlIIll);
            }
        }
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllllIIIIlIIlIIlII) throws SlickException {
        this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
        this.font2 = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
        this.image = new Image("testdata/demo2_00.tga", false);
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllllIIIIlIIIllllI, final Graphics lllllllllllllllllllIIIIlIIIllIlI) {
        this.font.drawString(80.0f, 5.0f, "A Font Example", Color.red);
        this.font.drawString(100.0f, 32.0f, "We - AV - Here is a more complete line that hopefully");
        this.font.drawString(100.0f, (float)(36 + this.font.getHeight("We Here is a more complete line that hopefully")), "will show some kerning.");
        this.font2.drawString(80.0f, 85.0f, "A Font Example", Color.red);
        this.font2.drawString(100.0f, 132.0f, "We - AV - Here is a more complete line that hopefully");
        this.font2.drawString(100.0f, (float)(136 + this.font2.getHeight("We - Here is a more complete line that hopefully")), "will show some kerning.");
        this.image.draw(100.0f, 400.0f);
        final String lllllllllllllllllllIIIIlIIIlllII = "Testing Font";
        this.font2.drawString(100.0f, 300.0f, lllllllllllllllllllIIIIlIIIlllII);
        lllllllllllllllllllIIIIlIIIllIlI.setColor(Color.white);
        lllllllllllllllllllIIIIlIIIllIlI.drawRect(100.0f, (float)(300 + this.font2.getYOffset(lllllllllllllllllllIIIIlIIIlllII)), (float)this.font2.getWidth(lllllllllllllllllllIIIIlIIIlllII), (float)(this.font2.getHeight(lllllllllllllllllllIIIIlIIIlllII) - this.font2.getYOffset(lllllllllllllllllllIIIIlIIIlllII)));
        this.font.drawString(500.0f, 300.0f, lllllllllllllllllllIIIIlIIIlllII);
        lllllllllllllllllllIIIIlIIIllIlI.setColor(Color.white);
        lllllllllllllllllllIIIIlIIIllIlI.drawRect(500.0f, (float)(300 + this.font.getYOffset(lllllllllllllllllllIIIIlIIIlllII)), (float)this.font.getWidth(lllllllllllllllllllIIIIlIIIlllII), (float)(this.font.getHeight(lllllllllllllllllllIIIIlIIIlllII) - this.font.getYOffset(lllllllllllllllllllIIIIlIIIlllII)));
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllllIIIIlIIIlIlll, final int lllllllllllllllllllIIIIlIIIlIllI) throws SlickException {
    }
}
