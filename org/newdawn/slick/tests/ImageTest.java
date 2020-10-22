package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class ImageTest extends BasicGame
{
    private /* synthetic */ Image scaled;
    private /* synthetic */ Image scaleMe;
    public static /* synthetic */ boolean exitMe;
    private /* synthetic */ Image gif;
    private /* synthetic */ Image tga;
    private /* synthetic */ Image rotImage;
    private /* synthetic */ float rot;
    private /* synthetic */ Image image;
    private /* synthetic */ Image subImage;
    
    public static void main(final String[] lllllllllllllllllIIlIlIlIlIIlIII) {
        final boolean lllllllllllllllllIIlIlIlIlIIIlll = false;
        try {
            ImageTest.exitMe = false;
            if (lllllllllllllllllIIlIlIlIlIIIlll) {
                GameContainer.enableSharedContext();
                ImageTest.exitMe = true;
            }
            AppGameContainer lllllllllllllllllIIlIlIlIlIIlIlI = new AppGameContainer(new ImageTest());
            lllllllllllllllllIIlIlIlIlIIlIlI.setForceExit(!lllllllllllllllllIIlIlIlIlIIIlll);
            lllllllllllllllllIIlIlIlIlIIlIlI.setDisplayMode(800, 600, false);
            lllllllllllllllllIIlIlIlIlIIlIlI.start();
            if (lllllllllllllllllIIlIlIlIlIIIlll) {
                System.out.println("Exit first instance");
                ImageTest.exitMe = false;
                lllllllllllllllllIIlIlIlIlIIlIlI = new AppGameContainer(new ImageTest());
                lllllllllllllllllIIlIlIlIlIIlIlI.setDisplayMode(800, 600, false);
                lllllllllllllllllIIlIlIlIlIIlIlI.start();
            }
        }
        catch (SlickException lllllllllllllllllIIlIlIlIlIIlIIl) {
            lllllllllllllllllIIlIlIlIlIIlIIl.printStackTrace();
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIlIlIlIlIllllI, final Graphics lllllllllllllllllIIlIlIlIlIllIII) {
        lllllllllllllllllIIlIlIlIlIllIII.drawRect(0.0f, 0.0f, (float)this.image.getWidth(), (float)this.image.getHeight());
        this.image.draw(0.0f, 0.0f);
        this.image.draw(500.0f, 0.0f, 200.0f, 100.0f);
        this.scaleMe.draw(500.0f, 100.0f, 200.0f, 100.0f);
        this.scaled.draw(400.0f, 500.0f);
        final Image lllllllllllllllllIIlIlIlIlIlllII = this.scaled.getFlippedCopy(true, false);
        lllllllllllllllllIIlIlIlIlIlllII.draw(520.0f, 500.0f);
        final Image lllllllllllllllllIIlIlIlIlIllIll = lllllllllllllllllIIlIlIlIlIlllII.getFlippedCopy(false, true);
        lllllllllllllllllIIlIlIlIlIllIll.draw(520.0f, 380.0f);
        final Image lllllllllllllllllIIlIlIlIlIllIlI = lllllllllllllllllIIlIlIlIlIllIll.getFlippedCopy(true, false);
        lllllllllllllllllIIlIlIlIlIllIlI.draw(400.0f, 380.0f);
        for (int lllllllllllllllllIIlIlIlIllIIIII = 0; lllllllllllllllllIIlIlIlIllIIIII < 3; ++lllllllllllllllllIIlIlIlIllIIIII) {
            this.subImage.draw((float)(200 + lllllllllllllllllIIlIlIlIllIIIII * 30), 300.0f);
        }
        lllllllllllllllllIIlIlIlIlIllIII.translate(500.0f, 200.0f);
        lllllllllllllllllIIlIlIlIlIllIII.rotate(50.0f, 50.0f, this.rot);
        lllllllllllllllllIIlIlIlIlIllIII.scale(0.3f, 0.3f);
        this.image.draw();
        lllllllllllllllllIIlIlIlIlIllIII.resetTransform();
        this.rotImage.setRotation(this.rot);
        this.rotImage.draw(100.0f, 200.0f);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIlIlIlIllIlIII) throws SlickException {
        final Image image = new Image("testdata/logo.png");
        this.tga = image;
        this.image = image;
        this.rotImage = new Image("testdata/logo.png");
        this.rotImage = this.rotImage.getScaledCopy(this.rotImage.getWidth() / 2, this.rotImage.getHeight() / 2);
        this.scaleMe = new Image("testdata/logo.tga", true, 2);
        this.gif = new Image("testdata/logo.gif");
        this.gif.destroy();
        this.gif = new Image("testdata/logo.gif");
        this.scaled = this.gif.getScaledCopy(120, 120);
        this.subImage = this.image.getSubImage(200, 0, 70, 260);
        this.rot = 0.0f;
        if (ImageTest.exitMe) {
            lllllllllllllllllIIlIlIlIllIlIII.exit();
        }
        final Image lllllllllllllllllIIlIlIlIllIlIlI = this.tga.getSubImage(50, 50, 50, 50);
        System.out.println(lllllllllllllllllIIlIlIlIllIlIlI.getColor(50, 50));
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIlIlIlIlIlIIII, final int lllllllllllllllllIIlIlIlIlIIllll) {
        this.rot += lllllllllllllllllIIlIlIlIlIIllll * 0.1f;
        if (this.rot > 360.0f) {
            this.rot -= 360.0f;
        }
    }
    
    public ImageTest() {
        super("Image Test");
    }
    
    static {
        ImageTest.exitMe = true;
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllIIlIlIlIlIIIIIl, final char lllllllllllllllllIIlIlIlIlIIIIII) {
        if (lllllllllllllllllIIlIlIlIlIIIIIl == 57) {
            if (this.image == this.gif) {
                this.image = this.tga;
            }
            else {
                this.image = this.gif;
            }
        }
    }
}
