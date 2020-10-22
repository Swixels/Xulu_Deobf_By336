package org.newdawn.slick.tests;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class MorphShapeTest extends BasicGame
{
    private /* synthetic */ Shape a;
    private /* synthetic */ Shape c;
    private /* synthetic */ Shape b;
    private /* synthetic */ float time;
    private /* synthetic */ MorphShape morph;
    
    public MorphShapeTest() {
        super("MorphShapeTest");
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllIllIlIlIlIIlIlll, final int llllllllllllllllIllIlIlIlIIlIlII) throws SlickException {
        this.time += llllllllllllllllIllIlIlIlIIlIlII * 0.001f;
        this.morph.setMorphTime(this.time);
    }
    
    public static void main(final String[] llllllllllllllllIllIlIlIlIIIlIIl) {
        try {
            final AppGameContainer llllllllllllllllIllIlIlIlIIIlIll = new AppGameContainer(new MorphShapeTest());
            llllllllllllllllIllIlIlIlIIIlIll.setDisplayMode(800, 600, false);
            llllllllllllllllIllIlIlIlIIIlIll.start();
        }
        catch (SlickException llllllllllllllllIllIlIlIlIIIlIlI) {
            llllllllllllllllIllIlIlIlIIIlIlI.printStackTrace();
        }
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllIllIlIlIlIIlllIl) throws SlickException {
        this.a = new Rectangle(100.0f, 100.0f, 50.0f, 200.0f);
        this.a = this.a.transform(Transform.createRotateTransform(0.1f, 100.0f, 100.0f));
        this.b = new Rectangle(200.0f, 100.0f, 50.0f, 200.0f);
        this.b = this.b.transform(Transform.createRotateTransform(-0.6f, 100.0f, 100.0f));
        this.c = new Rectangle(300.0f, 100.0f, 50.0f, 200.0f);
        this.c = this.c.transform(Transform.createRotateTransform(-0.2f, 100.0f, 100.0f));
        this.morph = new MorphShape(this.a);
        this.morph.addShape(this.b);
        this.morph.addShape(this.c);
        llllllllllllllllIllIlIlIlIIlllIl.setVSync(true);
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllIllIlIlIlIIlIIII, final Graphics llllllllllllllllIllIlIlIlIIIllll) throws SlickException {
        llllllllllllllllIllIlIlIlIIIllll.setColor(Color.green);
        llllllllllllllllIllIlIlIlIIIllll.draw(this.a);
        llllllllllllllllIllIlIlIlIIIllll.setColor(Color.red);
        llllllllllllllllIllIlIlIlIIIllll.draw(this.b);
        llllllllllllllllIllIlIlIlIIIllll.setColor(Color.blue);
        llllllllllllllllIllIlIlIlIIIllll.draw(this.c);
        llllllllllllllllIllIlIlIlIIIllll.setColor(Color.white);
        llllllllllllllllIllIlIlIlIIIllll.draw(this.morph);
    }
}
