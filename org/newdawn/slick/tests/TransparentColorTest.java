package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class TransparentColorTest extends BasicGame
{
    private /* synthetic */ Image timage;
    private /* synthetic */ Image image;
    
    public TransparentColorTest() {
        super("Transparent Color Test");
    }
    
    public static void main(final String[] lllllllllllllllllIlIIIIIIlllIllI) {
        try {
            final AppGameContainer lllllllllllllllllIlIIIIIIllllIII = new AppGameContainer(new TransparentColorTest());
            lllllllllllllllllIlIIIIIIllllIII.setDisplayMode(800, 600, false);
            lllllllllllllllllIlIIIIIIllllIII.start();
        }
        catch (SlickException lllllllllllllllllIlIIIIIIlllIlll) {
            lllllllllllllllllIlIIIIIIlllIlll.printStackTrace();
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIlIIIIIlIIIIIII, final Graphics lllllllllllllllllIlIIIIIIlllllIl) {
        lllllllllllllllllIlIIIIIIlllllIl.setBackground(Color.red);
        this.image.draw(10.0f, 10.0f);
        this.timage.draw(10.0f, 310.0f);
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIlIIIIIIllllIll, final int lllllllllllllllllIlIIIIIIllllIlI) {
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIlIIIIIlIIIIlIl) throws SlickException {
        this.image = new Image("testdata/transtest.png");
        this.timage = new Image("testdata/transtest.png", new Color(94, 66, 41, 255));
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllIlIIIIIIlllIIll, final char lllllllllllllllllIlIIIIIIlllIIlI) {
    }
}
