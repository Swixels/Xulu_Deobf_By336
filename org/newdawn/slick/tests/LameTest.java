package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class LameTest extends BasicGame
{
    private /* synthetic */ Polygon poly;
    private /* synthetic */ Image image;
    
    public static void main(final String[] lIIlIlllIIIl) {
        try {
            final AppGameContainer lIIlIlllIIll = new AppGameContainer(new LameTest());
            lIIlIlllIIll.setDisplayMode(800, 600, false);
            lIIlIlllIIll.start();
        }
        catch (SlickException lIIlIlllIIlI) {
            lIIlIlllIIlI.printStackTrace();
        }
    }
    
    @Override
    public void render(final GameContainer lIIlIllllIII, final Graphics lIIlIlllIlIl) throws SlickException {
        lIIlIlllIlIl.setColor(Color.white);
        lIIlIlllIlIl.texture(this.poly, this.image);
    }
    
    @Override
    public void update(final GameContainer lIIlIlllllIl, final int lIIlIlllllII) throws SlickException {
    }
    
    public LameTest() {
        super("Lame Test");
        this.poly = new Polygon();
    }
    
    @Override
    public void init(final GameContainer lIIllIIIIIII) throws SlickException {
        this.poly.addPoint(100.0f, 100.0f);
        this.poly.addPoint(120.0f, 100.0f);
        this.poly.addPoint(120.0f, 120.0f);
        this.poly.addPoint(100.0f, 120.0f);
        this.image = new Image("testdata/rocks.png");
    }
}
