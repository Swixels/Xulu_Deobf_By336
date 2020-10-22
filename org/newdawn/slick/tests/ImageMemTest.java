package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class ImageMemTest extends BasicGame
{
    @Override
    public void init(final GameContainer llllllllllllllllIlIIllllllIllllI) throws SlickException {
        try {
            Image llllllllllllllllIlIIlllllllIIIIl = new Image(2400, 2400);
            llllllllllllllllIlIIlllllllIIIIl.getGraphics();
            llllllllllllllllIlIIlllllllIIIIl.destroy();
            llllllllllllllllIlIIlllllllIIIIl = new Image(2400, 2400);
            llllllllllllllllIlIIlllllllIIIIl.getGraphics();
        }
        catch (Exception llllllllllllllllIlIIlllllllIIIII) {
            llllllllllllllllIlIIlllllllIIIII.printStackTrace();
        }
    }
    
    public ImageMemTest() {
        super("Image Memory Test");
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllIlIIllllllIllIII, final int llllllllllllllllIlIIllllllIlIlll) {
    }
    
    public static void main(final String[] llllllllllllllllIlIIllllllIlIIll) {
        try {
            final AppGameContainer llllllllllllllllIlIIllllllIlIlIl = new AppGameContainer(new ImageMemTest());
            llllllllllllllllIlIIllllllIlIlIl.setDisplayMode(800, 600, false);
            llllllllllllllllIlIIllllllIlIlIl.start();
        }
        catch (SlickException llllllllllllllllIlIIllllllIlIlII) {
            llllllllllllllllIlIIllllllIlIlII.printStackTrace();
        }
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllIlIIllllllIllIll, final Graphics llllllllllllllllIlIIllllllIllIlI) {
    }
}
