package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class CurveTest extends BasicGame
{
    private /* synthetic */ Vector2f p2;
    private /* synthetic */ Polygon poly;
    private /* synthetic */ Vector2f p1;
    private /* synthetic */ Curve curve;
    private /* synthetic */ Vector2f c2;
    private /* synthetic */ Vector2f c1;
    
    @Override
    public void init(final GameContainer lllllllllllllllllIlIlllllllIIlIl) throws SlickException {
        lllllllllllllllllIlIlllllllIIlIl.getGraphics().setBackground(Color.white);
        this.curve = new Curve(this.p2, this.c2, this.c1, this.p1);
        this.poly = new Polygon();
        this.poly.addPoint(500.0f, 200.0f);
        this.poly.addPoint(600.0f, 200.0f);
        this.poly.addPoint(700.0f, 300.0f);
        this.poly.addPoint(400.0f, 300.0f);
    }
    
    private void drawMarker(final Graphics lllllllllllllllllIlIllllllIllIlI, final Vector2f lllllllllllllllllIlIllllllIllIll) {
        lllllllllllllllllIlIllllllIllIlI.drawRect(lllllllllllllllllIlIllllllIllIll.x - 5.0f, lllllllllllllllllIlIllllllIllIll.y - 5.0f, 10.0f, 10.0f);
    }
    
    public CurveTest() {
        super("Curve Test");
        this.p1 = new Vector2f(100.0f, 300.0f);
        this.c1 = new Vector2f(100.0f, 100.0f);
        this.c2 = new Vector2f(300.0f, 100.0f);
        this.p2 = new Vector2f(300.0f, 300.0f);
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIlIlllllllIIIIl, final int lllllllllllllllllIlIlllllllIIIII) throws SlickException {
    }
    
    public static void main(final String[] lllllllllllllllllIlIllllllIIlllI) {
        try {
            final AppGameContainer lllllllllllllllllIlIllllllIlIIII = new AppGameContainer(new CurveTest());
            lllllllllllllllllIlIllllllIlIIII.setDisplayMode(800, 600, false);
            lllllllllllllllllIlIllllllIlIIII.start();
        }
        catch (SlickException lllllllllllllllllIlIllllllIIllll) {
            lllllllllllllllllIlIllllllIIllll.printStackTrace();
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIlIllllllIlIlIl, final Graphics lllllllllllllllllIlIllllllIlIlII) throws SlickException {
        lllllllllllllllllIlIllllllIlIlII.setColor(Color.gray);
        this.drawMarker(lllllllllllllllllIlIllllllIlIlII, this.p1);
        this.drawMarker(lllllllllllllllllIlIllllllIlIlII, this.p2);
        lllllllllllllllllIlIllllllIlIlII.setColor(Color.red);
        this.drawMarker(lllllllllllllllllIlIllllllIlIlII, this.c1);
        this.drawMarker(lllllllllllllllllIlIllllllIlIlII, this.c2);
        lllllllllllllllllIlIllllllIlIlII.setColor(Color.black);
        lllllllllllllllllIlIllllllIlIlII.draw(this.curve);
        lllllllllllllllllIlIllllllIlIlII.fill(this.curve);
        lllllllllllllllllIlIllllllIlIlII.draw(this.poly);
        lllllllllllllllllIlIllllllIlIlII.fill(this.poly);
    }
}
