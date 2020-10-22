package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class ImageBufferTest extends BasicGame
{
    private /* synthetic */ Image image;
    
    @Override
    public void update(final GameContainer lllIIlIIIIlllll, final int lllIIlIIIIllllI) {
    }
    
    @Override
    public void keyPressed(final int lllIIlIIIIllIll, final char lllIIlIIIIllIlI) {
        if (lllIIlIIIIllIll == 1) {
            System.exit(0);
        }
    }
    
    public static void main(final String[] lllIIlIIIIlIlIl) {
        try {
            final AppGameContainer lllIIlIIIIlIlll = new AppGameContainer(new ImageBufferTest());
            lllIIlIIIIlIlll.setDisplayMode(800, 600, false);
            lllIIlIIIIlIlll.start();
        }
        catch (SlickException lllIIlIIIIlIllI) {
            lllIIlIIIIlIllI.printStackTrace();
        }
    }
    
    @Override
    public void render(final GameContainer lllIIlIIIlIIIll, final Graphics lllIIlIIIlIIIlI) {
        this.image.draw(50.0f, 50.0f);
    }
    
    @Override
    public void init(final GameContainer lllIIlIIIlIlIll) throws SlickException {
        final ImageBuffer lllIIlIIIlIlIlI = new ImageBuffer(320, 200);
        for (int lllIIlIIIlIllIl = 0; lllIIlIIIlIllIl < 320; ++lllIIlIIIlIllIl) {
            for (int lllIIlIIIlIlllI = 0; lllIIlIIIlIlllI < 200; ++lllIIlIIIlIlllI) {
                if (lllIIlIIIlIlllI == 20) {
                    lllIIlIIIlIlIlI.setRGBA(lllIIlIIIlIllIl, lllIIlIIIlIlllI, 255, 255, 255, 255);
                }
                else {
                    lllIIlIIIlIlIlI.setRGBA(lllIIlIIIlIllIl, lllIIlIIIlIlllI, lllIIlIIIlIllIl, lllIIlIIIlIlllI, 0, 255);
                }
            }
        }
        this.image = lllIIlIIIlIlIlI.getImage();
    }
    
    public ImageBufferTest() {
        super("Image Buffer Test");
    }
}
