package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import java.awt.*;
import java.awt.event.*;

public class CanvasContainerTest extends BasicGame
{
    private /* synthetic */ Image scaleMe;
    private /* synthetic */ Image gif;
    private /* synthetic */ float rot;
    private /* synthetic */ Image image;
    private /* synthetic */ Image scaled;
    private /* synthetic */ Image tga;
    private /* synthetic */ Image subImage;
    
    @Override
    public void keyPressed(final int lllllllllllllllllllIIlllIlIIIIlI, final char lllllllllllllllllllIIlllIlIIIIIl) {
        if (lllllllllllllllllllIIlllIlIIIIlI == 57) {
            if (this.image == this.gif) {
                this.image = this.tga;
            }
            else {
                this.image = this.gif;
            }
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllllIIlllIlIlllll, final Graphics lllllllllllllllllllIIlllIlIllIIl) {
        this.image.draw(0.0f, 0.0f);
        this.image.draw(500.0f, 0.0f, 200.0f, 100.0f);
        this.scaleMe.draw(500.0f, 100.0f, 200.0f, 100.0f);
        this.scaled.draw(400.0f, 500.0f);
        final Image lllllllllllllllllllIIlllIlIlllIl = this.scaled.getFlippedCopy(true, false);
        lllllllllllllllllllIIlllIlIlllIl.draw(520.0f, 500.0f);
        final Image lllllllllllllllllllIIlllIlIlllII = lllllllllllllllllllIIlllIlIlllIl.getFlippedCopy(false, true);
        lllllllllllllllllllIIlllIlIlllII.draw(520.0f, 380.0f);
        final Image lllllllllllllllllllIIlllIlIllIll = lllllllllllllllllllIIlllIlIlllII.getFlippedCopy(true, false);
        lllllllllllllllllllIIlllIlIllIll.draw(400.0f, 380.0f);
        for (int lllllllllllllllllllIIlllIllIIIIl = 0; lllllllllllllllllllIIlllIllIIIIl < 3; ++lllllllllllllllllllIIlllIllIIIIl) {
            this.subImage.draw((float)(200 + lllllllllllllllllllIIlllIllIIIIl * 30), 300.0f);
        }
        lllllllllllllllllllIIlllIlIllIIl.translate(500.0f, 200.0f);
        lllllllllllllllllllIIlllIlIllIIl.rotate(50.0f, 50.0f, this.rot);
        lllllllllllllllllllIIlllIlIllIIl.scale(0.3f, 0.3f);
        this.image.draw();
        lllllllllllllllllllIIlllIlIllIIl.resetTransform();
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllllIIlllIllIlIIl) throws SlickException {
        final Image image = new Image("testdata/logo.tga");
        this.tga = image;
        this.image = image;
        this.scaleMe = new Image("testdata/logo.tga", true, 2);
        this.gif = new Image("testdata/logo.gif");
        this.scaled = this.gif.getScaledCopy(120, 120);
        this.subImage = this.image.getSubImage(200, 0, 70, 260);
        this.rot = 0.0f;
    }
    
    public CanvasContainerTest() {
        super("Canvas Container Test");
    }
    
    public static void main(final String[] lllllllllllllllllllIIlllIlIIlIII) {
        try {
            final CanvasGameContainer lllllllllllllllllllIIlllIlIIlIll = new CanvasGameContainer(new CanvasContainerTest());
            final Frame lllllllllllllllllllIIlllIlIIlIlI = new Frame("Test");
            lllllllllllllllllllIIlllIlIIlIlI.setLayout(new GridLayout(1, 2));
            lllllllllllllllllllIIlllIlIIlIlI.setSize(500, 500);
            lllllllllllllllllllIIlllIlIIlIlI.add(lllllllllllllllllllIIlllIlIIlIll);
            lllllllllllllllllllIIlllIlIIlIlI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(final WindowEvent lIIlIlIlIIIIlll) {
                    System.exit(0);
                }
            });
            lllllllllllllllllllIIlllIlIIlIlI.setVisible(true);
            lllllllllllllllllllIIlllIlIIlIll.start();
        }
        catch (Exception lllllllllllllllllllIIlllIlIIlIIl) {
            lllllllllllllllllllIIlllIlIIlIIl.printStackTrace();
        }
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllllIIlllIlIlIIIl, final int lllllllllllllllllllIIlllIlIIlllI) {
        this.rot += lllllllllllllllllllIIlllIlIIlllI * 0.1f;
        if (this.rot > 360.0f) {
            this.rot -= 360.0f;
        }
    }
}
