package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class BigSpriteSheetTest extends BasicGame
{
    private /* synthetic */ SpriteSheet bigSheet;
    private /* synthetic */ Image original;
    private /* synthetic */ boolean oldMethod;
    
    @Override
    public void update(final GameContainer lllllIIllll, final int lllllIIlllI) throws SlickException {
        if (lllllIIllll.getInput().isKeyPressed(57)) {
            this.oldMethod = !this.oldMethod;
        }
    }
    
    public BigSpriteSheetTest() {
        super("Big SpriteSheet Test");
        this.oldMethod = true;
    }
    
    @Override
    public void render(final GameContainer lllllIllIll, final Graphics lllllIllIlI) {
        if (this.oldMethod) {
            for (int llllllIIIlI = 0; llllllIIIlI < 43; ++llllllIIIlI) {
                for (int llllllIIIll = 0; llllllIIIll < 27; ++llllllIIIll) {
                    this.bigSheet.getSprite(llllllIIIlI, llllllIIIll).draw((float)(10 + llllllIIIlI * 18), (float)(50 + llllllIIIll * 18));
                }
            }
        }
        else {
            this.bigSheet.startUse();
            for (int llllllIIIII = 0; llllllIIIII < 43; ++llllllIIIII) {
                for (int llllllIIIIl = 0; llllllIIIIl < 27; ++llllllIIIIl) {
                    this.bigSheet.renderInUse(10 + llllllIIIII * 18, 50 + llllllIIIIl * 18, llllllIIIII, llllllIIIIl);
                }
            }
            this.bigSheet.endUse();
        }
        lllllIllIlI.drawString("Press space to toggle rendering method", 10.0f, 30.0f);
        lllllIllIll.getDefaultFont().drawString(10.0f, 100.0f, "TEST");
    }
    
    @Override
    public void init(final GameContainer llllllIlIlI) throws SlickException {
        this.original = new BigImage("testdata/bigimage.tga", 2, 256);
        this.bigSheet = new SpriteSheet(this.original, 16, 16);
    }
    
    public static void main(final String[] lllllIlIlII) {
        try {
            final AppGameContainer lllllIlIllI = new AppGameContainer(new BigSpriteSheetTest());
            lllllIlIllI.setDisplayMode(800, 600, false);
            lllllIlIllI.start();
        }
        catch (SlickException lllllIlIlIl) {
            lllllIlIlIl.printStackTrace();
        }
    }
}
