package org.newdawn.slick.tests;

import org.newdawn.slick.fills.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class GradientTest extends BasicGame
{
    private /* synthetic */ GradientFill gradient2;
    private /* synthetic */ GradientFill gradient4;
    private /* synthetic */ Polygon poly;
    private /* synthetic */ RoundedRectangle round2;
    private /* synthetic */ RoundedRectangle round;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ GradientFill gradient;
    private /* synthetic */ Rectangle rect;
    private /* synthetic */ Rectangle center;
    private /* synthetic */ float ang;
    
    @Override
    public void update(final GameContainer lIllIIIIIIIllll, final int lIllIIIIIIIllII) {
        this.ang += lIllIIIIIIIllII * 0.01f;
    }
    
    public GradientTest() {
        super("Gradient Test");
    }
    
    @Override
    public void init(final GameContainer lIllIIIIIIllIlI) throws SlickException {
        this.container = lIllIIIIIIllIlI;
        this.rect = new Rectangle(400.0f, 100.0f, 200.0f, 150.0f);
        this.round = new RoundedRectangle(150.0f, 100.0f, 200.0f, 150.0f, 50.0f);
        this.round2 = new RoundedRectangle(150.0f, 300.0f, 200.0f, 150.0f, 50.0f);
        this.center = new Rectangle(350.0f, 250.0f, 100.0f, 100.0f);
        this.poly = new Polygon();
        this.poly.addPoint(400.0f, 350.0f);
        this.poly.addPoint(550.0f, 320.0f);
        this.poly.addPoint(600.0f, 380.0f);
        this.poly.addPoint(620.0f, 450.0f);
        this.poly.addPoint(500.0f, 450.0f);
        this.gradient = new GradientFill(0.0f, -75.0f, Color.red, 0.0f, 75.0f, Color.yellow, true);
        this.gradient2 = new GradientFill(0.0f, -75.0f, Color.blue, 0.0f, 75.0f, Color.white, true);
        this.gradient4 = new GradientFill(-50.0f, -40.0f, Color.green, 50.0f, 40.0f, Color.cyan, true);
    }
    
    public static void main(final String[] lIllIIIIIIIlIII) {
        try {
            Renderer.setRenderer(2);
            final AppGameContainer lIllIIIIIIIlIlI = new AppGameContainer(new GradientTest());
            lIllIIIIIIIlIlI.setDisplayMode(800, 600, false);
            lIllIIIIIIIlIlI.start();
        }
        catch (SlickException lIllIIIIIIIlIIl) {
            lIllIIIIIIIlIIl.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int lIllIIIIIIIIIll, final char lIllIIIIIIIIIlI) {
        if (lIllIIIIIIIIIll == 1) {
            this.container.exit();
        }
    }
    
    @Override
    public void render(final GameContainer lIllIIIIIIlIllI, final Graphics lIllIIIIIIlIlIl) {
        lIllIIIIIIlIlIl.rotate(400.0f, 300.0f, this.ang);
        lIllIIIIIIlIlIl.fill(this.rect, this.gradient);
        lIllIIIIIIlIlIl.fill(this.round, this.gradient);
        lIllIIIIIIlIlIl.fill(this.poly, this.gradient2);
        lIllIIIIIIlIlIl.fill(this.center, this.gradient4);
        lIllIIIIIIlIlIl.setAntiAlias(true);
        lIllIIIIIIlIlIl.setLineWidth(10.0f);
        lIllIIIIIIlIlIl.draw(this.round2, this.gradient2);
        lIllIIIIIIlIlIl.setLineWidth(2.0f);
        lIllIIIIIIlIlIl.draw(this.poly, this.gradient);
        lIllIIIIIIlIlIl.setAntiAlias(false);
        lIllIIIIIIlIlIl.fill(this.center, this.gradient4);
        lIllIIIIIIlIlIl.setAntiAlias(true);
        lIllIIIIIIlIlIl.setColor(Color.black);
        lIllIIIIIIlIlIl.draw(this.center);
        lIllIIIIIIlIlIl.setAntiAlias(false);
    }
}
