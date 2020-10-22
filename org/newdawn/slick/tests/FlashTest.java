package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class FlashTest extends BasicGame
{
    private /* synthetic */ boolean flash;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ Image image;
    
    public FlashTest() {
        super("Flash Test");
    }
    
    @Override
    public void keyPressed(final int lIIIIlIIllIIIlI, final char lIIIIlIIllIIIIl) {
        if (lIIIIlIIllIIIlI == 57) {
            this.flash = !this.flash;
        }
        if (lIIIIlIIllIIIlI == 1) {
            this.container.exit();
        }
    }
    
    public static void main(final String[] lIIIIlIIllIIlll) {
        try {
            final AppGameContainer lIIIIlIIllIlIIl = new AppGameContainer(new FlashTest());
            lIIIIlIIllIlIIl.setDisplayMode(800, 600, false);
            lIIIIlIIllIlIIl.start();
        }
        catch (SlickException lIIIIlIIllIlIII) {
            lIIIIlIIllIlIII.printStackTrace();
        }
    }
    
    @Override
    public void init(final GameContainer lIIIIlIlIIIIlII) throws SlickException {
        this.container = lIIIIlIlIIIIlII;
        this.image = new Image("testdata/logo.tga");
    }
    
    @Override
    public void render(final GameContainer lIIIIlIIllllIlI, final Graphics lIIIIlIIlllIlll) {
        lIIIIlIIlllIlll.drawString("Press space to toggle", 10.0f, 50.0f);
        if (this.flash) {
            this.image.draw(100.0f, 100.0f);
        }
        else {
            this.image.drawFlash(100.0f, 100.0f, (float)this.image.getWidth(), (float)this.image.getHeight(), new Color(1.0f, 0.0f, 1.0f, 1.0f));
        }
    }
    
    @Override
    public void update(final GameContainer lIIIIlIIlllIlIl, final int lIIIIlIIlllIlII) {
    }
}
