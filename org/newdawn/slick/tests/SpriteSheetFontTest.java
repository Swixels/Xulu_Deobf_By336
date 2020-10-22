package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.util.*;

public class SpriteSheetFontTest extends BasicGame
{
    private static /* synthetic */ AppGameContainer container;
    private /* synthetic */ Font font;
    
    @Override
    public void update(final GameContainer llIllIIIIlIIIll, final int llIllIIIIlIIIlI) throws SlickException {
    }
    
    @Override
    public void init(final GameContainer llIllIIIIlIllll) throws SlickException {
        final SpriteSheet llIllIIIIlIlllI = new SpriteSheet("testdata/spriteSheetFont.png", 32, 32);
        this.font = new SpriteSheetFont(llIllIIIIlIlllI, ' ');
    }
    
    @Override
    public void render(final GameContainer llIllIIIIlIlIII, final Graphics llIllIIIIlIIlIl) {
        llIllIIIIlIIlIl.setBackground(Color.gray);
        this.font.drawString(80.0f, 5.0f, "A FONT EXAMPLE", Color.red);
        this.font.drawString(100.0f, 50.0f, "A MORE COMPLETE LINE");
    }
    
    public SpriteSheetFontTest() {
        super("SpriteSheetFont Test");
    }
    
    public static void main(final String[] llIllIIIIIlIlll) {
        try {
            (SpriteSheetFontTest.container = new AppGameContainer(new SpriteSheetFontTest())).setDisplayMode(800, 600, false);
            SpriteSheetFontTest.container.start();
        }
        catch (SlickException llIllIIIIIllIII) {
            llIllIIIIIllIII.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int llIllIIIIIlllIl, final char llIllIIIIIlllII) {
        if (llIllIIIIIlllIl == 1) {
            System.exit(0);
        }
        if (llIllIIIIIlllIl == 57) {
            try {
                SpriteSheetFontTest.container.setDisplayMode(640, 480, false);
            }
            catch (SlickException llIllIIIIIlllll) {
                Log.error(llIllIIIIIlllll);
            }
        }
    }
}
