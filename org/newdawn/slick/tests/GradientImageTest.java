package org.newdawn.slick.tests;

import org.newdawn.slick.fills.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class GradientImageTest extends BasicGame
{
    private /* synthetic */ Image image2;
    private /* synthetic */ float ang;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ boolean rotating;
    private /* synthetic */ GradientFill fill;
    private /* synthetic */ Image image1;
    private /* synthetic */ Polygon poly;
    private /* synthetic */ Shape shape;
    
    @Override
    public void init(final GameContainer lllllllllllllllllllIIllIIlIlllll) throws SlickException {
        this.container = lllllllllllllllllllIIllIIlIlllll;
        this.image1 = new Image("testdata/grass.png");
        this.image2 = new Image("testdata/rocks.png");
        this.fill = new GradientFill(-64.0f, 0.0f, new Color(1.0f, 1.0f, 1.0f, 1.0f), 64.0f, 0.0f, new Color(0, 0, 0, 0));
        this.shape = new Rectangle(336.0f, 236.0f, 128.0f, 128.0f);
        this.poly = new Polygon();
        this.poly.addPoint(320.0f, 220.0f);
        this.poly.addPoint(350.0f, 200.0f);
        this.poly.addPoint(450.0f, 200.0f);
        this.poly.addPoint(480.0f, 220.0f);
        this.poly.addPoint(420.0f, 400.0f);
        this.poly.addPoint(400.0f, 390.0f);
    }
    
    public GradientImageTest() {
        super("Gradient Image Test");
        this.rotating = false;
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllllIIllIIlIlIlII, final int lllllllllllllllllllIIllIIlIlIIll) {
        if (this.rotating) {
            this.ang += lllllllllllllllllllIIllIIlIlIIll * 0.1f;
        }
    }
    
    public static void main(final String[] lllllllllllllllllllIIllIIlIIllIl) {
        try {
            final AppGameContainer lllllllllllllllllllIIllIIlIIllll = new AppGameContainer(new GradientImageTest());
            lllllllllllllllllllIIllIIlIIllll.setDisplayMode(800, 600, false);
            lllllllllllllllllllIIllIIlIIllll.start();
        }
        catch (SlickException lllllllllllllllllllIIllIIlIIlllI) {
            lllllllllllllllllllIIllIIlIIlllI.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllllIIllIIlIIIlIl, final char lllllllllllllllllllIIllIIlIIIlll) {
        if (lllllllllllllllllllIIllIIlIIIlIl == 19) {
            this.rotating = !this.rotating;
        }
        if (lllllllllllllllllllIIllIIlIIIlIl == 1) {
            this.container.exit();
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllllIIllIIlIllIll, final Graphics lllllllllllllllllllIIllIIlIllIlI) {
        lllllllllllllllllllIIllIIlIllIlI.drawString("R - Toggle Rotationg", 10.0f, 50.0f);
        lllllllllllllllllllIIllIIlIllIlI.drawImage(this.image1, 100.0f, 236.0f);
        lllllllllllllllllllIIllIIlIllIlI.drawImage(this.image2, 600.0f, 236.0f);
        lllllllllllllllllllIIllIIlIllIlI.translate(0.0f, -150.0f);
        lllllllllllllllllllIIllIIlIllIlI.rotate(400.0f, 300.0f, this.ang);
        lllllllllllllllllllIIllIIlIllIlI.texture(this.shape, this.image2);
        lllllllllllllllllllIIllIIlIllIlI.texture(this.shape, this.image1, this.fill);
        lllllllllllllllllllIIllIIlIllIlI.resetTransform();
        lllllllllllllllllllIIllIIlIllIlI.translate(0.0f, 150.0f);
        lllllllllllllllllllIIllIIlIllIlI.rotate(400.0f, 300.0f, this.ang);
        lllllllllllllllllllIIllIIlIllIlI.texture(this.poly, this.image2);
        lllllllllllllllllllIIllIIlIllIlI.texture(this.poly, this.image1, this.fill);
        lllllllllllllllllllIIllIIlIllIlI.resetTransform();
    }
}
