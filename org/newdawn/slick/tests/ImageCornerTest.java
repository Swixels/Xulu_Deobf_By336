package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class ImageCornerTest extends BasicGame
{
    private /* synthetic */ int width;
    private /* synthetic */ Image image;
    private /* synthetic */ Image[] images;
    private /* synthetic */ int height;
    
    @Override
    public void update(final GameContainer lIlIIllllllIllI, final int lIlIIllllllIlIl) throws SlickException {
    }
    
    @Override
    public void init(final GameContainer lIlIlIIIIIIllII) throws SlickException {
        this.image = new Image("testdata/logo.png");
        this.width = this.image.getWidth() / 3;
        this.height = this.image.getHeight() / 3;
        this.images = new Image[] { this.image.getSubImage(0, 0, this.width, this.height), this.image.getSubImage(this.width, 0, this.width, this.height), this.image.getSubImage(this.width * 2, 0, this.width, this.height), this.image.getSubImage(0, this.height, this.width, this.height), this.image.getSubImage(this.width, this.height, this.width, this.height), this.image.getSubImage(this.width * 2, this.height, this.width, this.height), this.image.getSubImage(0, this.height * 2, this.width, this.height), this.image.getSubImage(this.width, this.height * 2, this.width, this.height), this.image.getSubImage(this.width * 2, this.height * 2, this.width, this.height) };
        this.images[0].setColor(2, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[1].setColor(3, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[1].setColor(2, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[2].setColor(3, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[3].setColor(1, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[3].setColor(2, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[4].setColor(1, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[4].setColor(0, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[4].setColor(3, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[4].setColor(2, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[5].setColor(0, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[5].setColor(3, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[6].setColor(1, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[7].setColor(1, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[7].setColor(0, 0.0f, 1.0f, 1.0f, 1.0f);
        this.images[8].setColor(0, 0.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void main(final String[] lIlIIlllllllIll) {
        final boolean lIlIIlllllllIlI = false;
        try {
            final AppGameContainer lIlIIllllllllIl = new AppGameContainer(new ImageCornerTest());
            lIlIIllllllllIl.setDisplayMode(800, 600, false);
            lIlIIllllllllIl.start();
        }
        catch (SlickException lIlIIllllllllII) {
            lIlIIllllllllII.printStackTrace();
        }
    }
    
    public ImageCornerTest() {
        super("Image Corner Test");
    }
    
    @Override
    public void render(final GameContainer lIlIlIIIIIIIlII, final Graphics lIlIlIIIIIIIIll) {
        for (int lIlIlIIIIIIIllI = 0; lIlIlIIIIIIIllI < 3; ++lIlIlIIIIIIIllI) {
            for (int lIlIlIIIIIIIlll = 0; lIlIlIIIIIIIlll < 3; ++lIlIlIIIIIIIlll) {
                this.images[lIlIlIIIIIIIllI + lIlIlIIIIIIIlll * 3].draw((float)(100 + lIlIlIIIIIIIllI * this.width), (float)(100 + lIlIlIIIIIIIlll * this.height));
            }
        }
    }
}
