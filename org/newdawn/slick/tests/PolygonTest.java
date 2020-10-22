package org.newdawn.slick.tests;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class PolygonTest extends BasicGame
{
    private /* synthetic */ Polygon poly;
    private /* synthetic */ boolean in;
    
    public PolygonTest() {
        super("Polygon Test");
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIlIIIIlIlllIlll, final Graphics lllllllllllllllllIlIIIIlIlllIllI) throws SlickException {
        if (this.in) {
            lllllllllllllllllIlIIIIlIlllIllI.setColor(Color.red);
            lllllllllllllllllIlIIIIlIlllIllI.fill(this.poly);
        }
        lllllllllllllllllIlIIIIlIlllIllI.setColor(Color.yellow);
        lllllllllllllllllIlIIIIlIlllIllI.fillOval(this.poly.getCenterX() - 3.0f, this.poly.getCenterY() - 3.0f, 6.0f, 6.0f);
        lllllllllllllllllIlIIIIlIlllIllI.setColor(Color.white);
        lllllllllllllllllIlIIIIlIlllIllI.draw(this.poly);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIlIIIIllIIIIIll) throws SlickException {
        this.poly = new Polygon();
        this.poly.addPoint(300.0f, 100.0f);
        this.poly.addPoint(320.0f, 200.0f);
        this.poly.addPoint(350.0f, 210.0f);
        this.poly.addPoint(280.0f, 250.0f);
        this.poly.addPoint(300.0f, 200.0f);
        this.poly.addPoint(240.0f, 150.0f);
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIlIIIIlIllllllI, final int lllllllllllllllllIlIIIIlIlllllIl) throws SlickException {
        this.in = this.poly.contains((float)lllllllllllllllllIlIIIIlIllllllI.getInput().getMouseX(), (float)lllllllllllllllllIlIIIIlIllllllI.getInput().getMouseY());
        this.poly.setCenterY(0.0f);
    }
    
    public static void main(final String[] lllllllllllllllllIlIIIIlIlllIIII) {
        try {
            final AppGameContainer lllllllllllllllllIlIIIIlIlllIIlI = new AppGameContainer(new PolygonTest(), 640, 480, false);
            lllllllllllllllllIlIIIIlIlllIIlI.start();
        }
        catch (Exception lllllllllllllllllIlIIIIlIlllIIIl) {
            lllllllllllllllllIlIIIIlIlllIIIl.printStackTrace();
        }
    }
}
