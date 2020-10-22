package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class PureFontTest extends BasicGame
{
    private /* synthetic */ Image image;
    private /* synthetic */ Font font;
    private static /* synthetic */ AppGameContainer container;
    
    @Override
    public void update(final GameContainer lIlllIlIIIII, final int lIlllIIlllll) throws SlickException {
    }
    
    @Override
    public void keyPressed(final int lIlllIIllIlI, final char lIlllIIllIll) {
        if (lIlllIIllIlI == 1) {
            System.exit(0);
        }
    }
    
    public PureFontTest() {
        super("Hiero Font Test");
    }
    
    @Override
    public void render(final GameContainer lIlllIlIIlII, final Graphics lIlllIlIIIll) {
        this.image.draw(0.0f, 0.0f, 800.0f, 600.0f);
        this.font.drawString(100.0f, 32.0f, "On top of old smokey, all");
        this.font.drawString(100.0f, 80.0f, "covered with sand..");
    }
    
    public static void main(final String[] lIlllIIlIlll) {
        try {
            (PureFontTest.container = new AppGameContainer(new PureFontTest())).setDisplayMode(800, 600, false);
            PureFontTest.container.start();
        }
        catch (SlickException lIlllIIllIII) {
            lIlllIIllIII.printStackTrace();
        }
    }
    
    @Override
    public void init(final GameContainer lIlllIlIlIII) throws SlickException {
        this.image = new Image("testdata/sky.jpg");
        this.font = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
    }
}
