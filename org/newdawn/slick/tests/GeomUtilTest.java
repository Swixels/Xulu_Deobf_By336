package org.newdawn.slick.tests;

import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class GeomUtilTest extends BasicGame implements GeomUtilListener
{
    private /* synthetic */ int xp;
    private /* synthetic */ ArrayList marks;
    private /* synthetic */ Shape source;
    private /* synthetic */ ArrayList points;
    private /* synthetic */ Shape rect;
    private /* synthetic */ GeomUtil util;
    private /* synthetic */ boolean union;
    private /* synthetic */ ArrayList exclude;
    private /* synthetic */ boolean dynamic;
    private /* synthetic */ Shape cut;
    private /* synthetic */ Circle circle;
    private /* synthetic */ Polygon star;
    private /* synthetic */ Shape[] result;
    private /* synthetic */ int yp;
    
    @Override
    public void init(final GameContainer lllllllllllllllllIllIlIllllIIIII) throws SlickException {
        this.util.setListener(this);
        this.init();
        lllllllllllllllllIllIlIllllIIIII.setVSync(true);
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIllIlIlllIIlIIl, final Graphics lllllllllllllllllIllIlIlllIIlIII) throws SlickException {
        lllllllllllllllllIllIlIlllIIlIII.drawString("Space - toggle movement of cutting shape", 530.0f, 10.0f);
        lllllllllllllllllIllIlIlllIIlIII.drawString("1,2,3 - select cutting shape", 530.0f, 30.0f);
        lllllllllllllllllIllIlIlllIIlIII.drawString("Mouse wheel - rotate shape", 530.0f, 50.0f);
        lllllllllllllllllIllIlIlllIIlIII.drawString("Enter - toggle union/subtract", 530.0f, 70.0f);
        lllllllllllllllllIllIlIlllIIlIII.drawString(String.valueOf(new StringBuilder().append("MODE: ").append(this.union ? "Union" : "Cut")), 530.0f, 200.0f);
        lllllllllllllllllIllIlIlllIIlIII.setColor(Color.green);
        lllllllllllllllllIllIlIlllIIlIII.draw(this.source);
        lllllllllllllllllIllIlIlllIIlIII.setColor(Color.red);
        lllllllllllllllllIllIlIlllIIlIII.draw(this.cut);
        lllllllllllllllllIllIlIlllIIlIII.setColor(Color.white);
        for (int lllllllllllllllllIllIlIlllIlIIII = 0; lllllllllllllllllIllIlIlllIlIIII < this.exclude.size(); ++lllllllllllllllllIllIlIlllIlIIII) {
            final Vector2f lllllllllllllllllIllIlIlllIlIIIl = this.exclude.get(lllllllllllllllllIllIlIlllIlIIII);
            lllllllllllllllllIllIlIlllIIlIII.drawOval(lllllllllllllllllIllIlIlllIlIIIl.x - 3.0f, lllllllllllllllllIllIlIlllIlIIIl.y - 3.0f, 7.0f, 7.0f);
        }
        lllllllllllllllllIllIlIlllIIlIII.setColor(Color.yellow);
        for (int lllllllllllllllllIllIlIlllIIlllI = 0; lllllllllllllllllIllIlIlllIIlllI < this.points.size(); ++lllllllllllllllllIllIlIlllIIlllI) {
            final Vector2f lllllllllllllllllIllIlIlllIIllll = this.points.get(lllllllllllllllllIllIlIlllIIlllI);
            lllllllllllllllllIllIlIlllIIlIII.fillOval(lllllllllllllllllIllIlIlllIIllll.x - 1.0f, lllllllllllllllllIllIlIlllIIllll.y - 1.0f, 3.0f, 3.0f);
        }
        lllllllllllllllllIllIlIlllIIlIII.setColor(Color.white);
        for (int lllllllllllllllllIllIlIlllIIllII = 0; lllllllllllllllllIllIlIlllIIllII < this.marks.size(); ++lllllllllllllllllIllIlIlllIIllII) {
            final Vector2f lllllllllllllllllIllIlIlllIIllIl = this.marks.get(lllllllllllllllllIllIlIlllIIllII);
            lllllllllllllllllIllIlIlllIIlIII.fillOval(lllllllllllllllllIllIlIlllIIllIl.x - 1.0f, lllllllllllllllllIllIlIlllIIllIl.y - 1.0f, 3.0f, 3.0f);
        }
        lllllllllllllllllIllIlIlllIIlIII.translate(0.0f, 300.0f);
        lllllllllllllllllIllIlIlllIIlIII.setColor(Color.white);
        if (this.result != null) {
            for (int lllllllllllllllllIllIlIlllIIlIll = 0; lllllllllllllllllIllIlIlllIIlIll < this.result.length; ++lllllllllllllllllIllIlIlllIIlIll) {
                lllllllllllllllllIllIlIlllIIlIII.draw(this.result[lllllllllllllllllIllIlIlllIIlIll]);
            }
            lllllllllllllllllIllIlIlllIIlIII.drawString(String.valueOf(new StringBuilder().append("Polys:").append(this.result.length)), 10.0f, 100.0f);
            lllllllllllllllllIllIlIlllIIlIII.drawString(String.valueOf(new StringBuilder().append("X:").append(this.xp)), 10.0f, 120.0f);
            lllllllllllllllllIllIlIlllIIlIII.drawString(String.valueOf(new StringBuilder().append("Y:").append(this.yp)), 10.0f, 130.0f);
        }
    }
    
    @Override
    public void mouseWheelMoved(final int lllllllllllllllllIllIlIllIlIIIII) {
        if (this.dynamic) {
            if (lllllllllllllllllIllIlIllIlIIIII < 0) {
                this.cut = this.cut.transform(Transform.createRotateTransform((float)Math.toRadians(10.0), this.cut.getCenterX(), this.cut.getCenterY()));
            }
            else {
                this.cut = this.cut.transform(Transform.createRotateTransform((float)Math.toRadians(-10.0), this.cut.getCenterX(), this.cut.getCenterY()));
            }
        }
    }
    
    private void makeBoolean() {
        this.marks.clear();
        this.points.clear();
        this.exclude.clear();
        this.cut.setCenterX((float)this.xp);
        this.cut.setCenterY((float)this.yp);
        if (this.union) {
            this.result = this.util.union(this.source, this.cut);
        }
        else {
            this.result = this.util.subtract(this.source, this.cut);
        }
    }
    
    public static void main(final String[] lllllllllllllllllIllIlIlllIIIIII) {
        try {
            final AppGameContainer lllllllllllllllllIllIlIlllIIIIlI = new AppGameContainer(new GeomUtilTest());
            lllllllllllllllllIllIlIlllIIIIlI.setDisplayMode(800, 600, false);
            lllllllllllllllllIllIlIlllIIIIlI.start();
        }
        catch (SlickException lllllllllllllllllIllIlIlllIIIIIl) {
            lllllllllllllllllIllIlIlllIIIIIl.printStackTrace();
        }
    }
    
    @Override
    public void pointExcluded(final float lllllllllllllllllIllIlIllIlllIlI, final float lllllllllllllllllIllIlIllIlllIIl) {
        this.exclude.add(new Vector2f(lllllllllllllllllIllIlIllIlllIlI, lllllllllllllllllIllIlIllIlllIIl));
    }
    
    @Override
    public void pointIntersected(final float lllllllllllllllllIllIlIllIlIlllI, final float lllllllllllllllllIllIlIllIllIIII) {
        this.marks.add(new Vector2f(lllllllllllllllllIllIlIllIlIlllI, lllllllllllllllllIllIlIllIllIIII));
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIllIlIlllIlllII, final int lllllllllllllllllIllIlIlllIllIll) throws SlickException {
        if (lllllllllllllllllIllIlIlllIlllII.getInput().isKeyPressed(57)) {
            this.dynamic = !this.dynamic;
        }
        if (lllllllllllllllllIllIlIlllIlllII.getInput().isKeyPressed(28)) {
            this.union = !this.union;
            this.makeBoolean();
        }
        if (lllllllllllllllllIllIlIlllIlllII.getInput().isKeyPressed(2)) {
            this.cut = this.circle;
            this.circle.setCenterX((float)this.xp);
            this.circle.setCenterY((float)this.yp);
            this.makeBoolean();
        }
        if (lllllllllllllllllIllIlIlllIlllII.getInput().isKeyPressed(3)) {
            this.cut = this.rect;
            this.rect.setCenterX((float)this.xp);
            this.rect.setCenterY((float)this.yp);
            this.makeBoolean();
        }
        if (lllllllllllllllllIllIlIlllIlllII.getInput().isKeyPressed(4)) {
            this.cut = this.star;
            this.star.setCenterX((float)this.xp);
            this.star.setCenterY((float)this.yp);
            this.makeBoolean();
        }
        if (this.dynamic) {
            this.xp = lllllllllllllllllIllIlIlllIlllII.getInput().getMouseX();
            this.yp = lllllllllllllllllIllIlIlllIlllII.getInput().getMouseY();
            this.makeBoolean();
        }
    }
    
    public void init() {
        final Polygon lllllllllllllllllIllIlIllllIllIl = new Polygon();
        lllllllllllllllllIllIlIllllIllIl.addPoint(100.0f, 100.0f);
        lllllllllllllllllIllIlIllllIllIl.addPoint(150.0f, 80.0f);
        lllllllllllllllllIllIlIllllIllIl.addPoint(210.0f, 120.0f);
        lllllllllllllllllIllIlIllllIllIl.addPoint(340.0f, 150.0f);
        lllllllllllllllllIllIlIllllIllIl.addPoint(150.0f, 200.0f);
        lllllllllllllllllIllIlIllllIllIl.addPoint(120.0f, 250.0f);
        this.source = lllllllllllllllllIllIlIllllIllIl;
        this.circle = new Circle(0.0f, 0.0f, 50.0f);
        this.rect = new Rectangle(-100.0f, -40.0f, 200.0f, 80.0f);
        this.star = new Polygon();
        float lllllllllllllllllIllIlIllllIllII = 40.0f;
        for (int lllllllllllllllllIllIlIllllIllll = 0; lllllllllllllllllIllIlIllllIllll < 360; lllllllllllllllllIllIlIllllIllll += 30) {
            lllllllllllllllllIllIlIllllIllII = ((lllllllllllllllllIllIlIllllIllII == 40.0f) ? 60.0f : 40.0f);
            final double lllllllllllllllllIllIlIlllllIIIl = Math.cos(Math.toRadians(lllllllllllllllllIllIlIllllIllll)) * lllllllllllllllllIllIlIllllIllII;
            final double lllllllllllllllllIllIlIlllllIIII = Math.sin(Math.toRadians(lllllllllllllllllIllIlIllllIllll)) * lllllllllllllllllIllIlIllllIllII;
            this.star.addPoint((float)lllllllllllllllllIllIlIlllllIIIl, (float)lllllllllllllllllIllIlIlllllIIII);
        }
        this.cut = this.circle;
        this.cut.setLocation(203.0f, 78.0f);
        this.xp = (int)this.cut.getCenterX();
        this.yp = (int)this.cut.getCenterY();
        this.makeBoolean();
    }
    
    @Override
    public void pointUsed(final float lllllllllllllllllIllIlIllIlIlIII, final float lllllllllllllllllIllIlIllIlIIlll) {
        this.points.add(new Vector2f(lllllllllllllllllIllIlIllIlIlIII, lllllllllllllllllIllIlIllIlIIlll));
    }
    
    public GeomUtilTest() {
        super("GeomUtilTest");
        this.points = new ArrayList();
        this.marks = new ArrayList();
        this.exclude = new ArrayList();
        this.util = new GeomUtil();
    }
}
