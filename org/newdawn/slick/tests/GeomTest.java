package org.newdawn.slick.tests;

import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class GeomTest extends BasicGame
{
    private /* synthetic */ Shape circle3;
    private /* synthetic */ Shape rect2;
    private /* synthetic */ Shape circle;
    private /* synthetic */ Shape circle1;
    private /* synthetic */ Shape rect;
    private /* synthetic */ Shape roundRect2;
    private /* synthetic */ Shape circle4;
    private /* synthetic */ Shape rect1;
    private /* synthetic */ Shape circle2;
    private /* synthetic */ Shape roundRect;
    
    @Override
    public void init(final GameContainer llIlllIIIlIllll) throws SlickException {
    }
    
    @Override
    public void keyPressed(final int llIlllIIIlIIIII, final char llIlllIIIlIIIIl) {
        if (llIlllIIIlIIIII == 1) {
            System.exit(0);
        }
    }
    
    @Override
    public void update(final GameContainer llIlllIIIlIIllI, final int llIlllIIIlIIlIl) {
    }
    
    @Override
    public void render(final GameContainer llIlllIIIlIlIll, final Graphics llIlllIIIlIlIlI) {
        llIlllIIIlIlIlI.setColor(Color.white);
        llIlllIIIlIlIlI.drawString("Red indicates a collision, green indicates no collision", 50.0f, 420.0f);
        llIlllIIIlIlIlI.drawString("White are the targets", 50.0f, 435.0f);
        llIlllIIIlIlIlI.pushTransform();
        llIlllIIIlIlIlI.translate(100.0f, 100.0f);
        llIlllIIIlIlIlI.pushTransform();
        llIlllIIIlIlIlI.translate(-50.0f, -50.0f);
        llIlllIIIlIlIlI.scale(10.0f, 10.0f);
        llIlllIIIlIlIlI.setColor(Color.red);
        llIlllIIIlIlIlI.fillRect(0.0f, 0.0f, 5.0f, 5.0f);
        llIlllIIIlIlIlI.setColor(Color.white);
        llIlllIIIlIlIlI.drawRect(0.0f, 0.0f, 5.0f, 5.0f);
        llIlllIIIlIlIlI.popTransform();
        llIlllIIIlIlIlI.setColor(Color.green);
        llIlllIIIlIlIlI.fillRect(20.0f, 20.0f, 50.0f, 50.0f);
        llIlllIIIlIlIlI.popTransform();
        llIlllIIIlIlIlI.setColor(Color.white);
        llIlllIIIlIlIlI.draw(this.rect);
        llIlllIIIlIlIlI.draw(this.circle);
        llIlllIIIlIlIlI.setColor(this.rect1.intersects(this.rect) ? Color.red : Color.green);
        llIlllIIIlIlIlI.draw(this.rect1);
        llIlllIIIlIlIlI.setColor(this.rect2.intersects(this.rect) ? Color.red : Color.green);
        llIlllIIIlIlIlI.draw(this.rect2);
        llIlllIIIlIlIlI.setColor(this.roundRect.intersects(this.rect) ? Color.red : Color.green);
        llIlllIIIlIlIlI.draw(this.roundRect);
        llIlllIIIlIlIlI.setColor(this.circle1.intersects(this.rect) ? Color.red : Color.green);
        llIlllIIIlIlIlI.draw(this.circle1);
        llIlllIIIlIlIlI.setColor(this.circle2.intersects(this.rect) ? Color.red : Color.green);
        llIlllIIIlIlIlI.draw(this.circle2);
        llIlllIIIlIlIlI.setColor(this.circle3.intersects(this.circle) ? Color.red : Color.green);
        llIlllIIIlIlIlI.fill(this.circle3);
        llIlllIIIlIlIlI.setColor(this.circle4.intersects(this.circle) ? Color.red : Color.green);
        llIlllIIIlIlIlI.draw(this.circle4);
        llIlllIIIlIlIlI.fill(this.roundRect2);
        llIlllIIIlIlIlI.setColor(Color.blue);
        llIlllIIIlIlIlI.draw(this.roundRect2);
        llIlllIIIlIlIlI.setColor(Color.blue);
        llIlllIIIlIlIlI.draw(new Circle(100.0f, 100.0f, 50.0f));
        llIlllIIIlIlIlI.drawRect(50.0f, 50.0f, 100.0f, 100.0f);
    }
    
    public static void main(final String[] llIlllIIIIlllII) {
        try {
            Renderer.setRenderer(2);
            final AppGameContainer llIlllIIIIllllI = new AppGameContainer(new GeomTest());
            llIlllIIIIllllI.setDisplayMode(800, 600, false);
            llIlllIIIIllllI.start();
        }
        catch (SlickException llIlllIIIIlllIl) {
            llIlllIIIIlllIl.printStackTrace();
        }
    }
    
    public GeomTest() {
        super("Geom Test");
        this.rect = new Rectangle(100.0f, 100.0f, 100.0f, 100.0f);
        this.circle = new Circle(500.0f, 200.0f, 50.0f);
        this.rect1 = new Rectangle(150.0f, 120.0f, 50.0f, 100.0f).transform(Transform.createTranslateTransform(50.0f, 50.0f));
        this.rect2 = new Rectangle(310.0f, 210.0f, 50.0f, 100.0f).transform(Transform.createRotateTransform((float)Math.toRadians(45.0), 335.0f, 260.0f));
        this.circle1 = new Circle(150.0f, 90.0f, 30.0f);
        this.circle2 = new Circle(310.0f, 110.0f, 70.0f);
        this.circle3 = new Ellipse(510.0f, 150.0f, 70.0f, 70.0f);
        this.circle4 = new Ellipse(510.0f, 350.0f, 30.0f, 30.0f).transform(Transform.createTranslateTransform(-510.0f, -350.0f)).transform(Transform.createScaleTransform(2.0f, 2.0f)).transform(Transform.createTranslateTransform(510.0f, 350.0f));
        this.roundRect = new RoundedRectangle(50.0f, 175.0f, 100.0f, 100.0f, 20.0f);
        this.roundRect2 = new RoundedRectangle(50.0f, 280.0f, 50.0f, 50.0f, 20.0f, 20, 5);
    }
}
