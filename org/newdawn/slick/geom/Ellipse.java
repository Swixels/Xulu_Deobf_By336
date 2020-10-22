package org.newdawn.slick.geom;

import java.util.*;
import org.newdawn.slick.util.*;

public class Ellipse extends Shape
{
    private /* synthetic */ int segmentCount;
    private /* synthetic */ float radius2;
    private /* synthetic */ float radius1;
    
    public Ellipse(final float lllllllllllllllllIIlIIIIllllllll, final float lllllllllllllllllIIlIIIIlllllllI, final float lllllllllllllllllIIlIIIIllllllIl, final float lllllllllllllllllIIlIIIIllllllII) {
        this(lllllllllllllllllIIlIIIIllllllll, lllllllllllllllllIIlIIIIlllllllI, lllllllllllllllllIIlIIIIllllllIl, lllllllllllllllllIIlIIIIllllllII, 50);
    }
    
    public void setRadius1(final float lllllllllllllllllIIlIIIIllIllIII) {
        if (lllllllllllllllllIIlIIIIllIllIII != this.radius1) {
            this.radius1 = lllllllllllllllllIIlIIIIllIllIII;
            this.pointsDirty = true;
        }
    }
    
    public void setRadii(final float lllllllllllllllllIIlIIIIlllIIIlI, final float lllllllllllllllllIIlIIIIlllIIlII) {
        this.setRadius1(lllllllllllllllllIIlIIIIlllIIIlI);
        this.setRadius2(lllllllllllllllllIIlIIIIlllIIlII);
    }
    
    public float getRadius2() {
        return this.radius2;
    }
    
    @Override
    protected void createPoints() {
        final ArrayList lllllllllllllllllIIlIIIIlIllllIl = new ArrayList();
        this.maxX = -1.4E-45f;
        this.maxY = -1.4E-45f;
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        final float lllllllllllllllllIIlIIIIlIllllII = 0.0f;
        final float lllllllllllllllllIIlIIIIlIlllIll = 359.0f;
        final float lllllllllllllllllIIlIIIIlIlllIlI = this.x + this.radius1;
        final float lllllllllllllllllIIlIIIIlIlllIIl = this.y + this.radius2;
        final int lllllllllllllllllIIlIIIIlIlllIII = 360 / this.segmentCount;
        for (float lllllllllllllllllIIlIIIIllIIIIII = lllllllllllllllllIIlIIIIlIllllII; lllllllllllllllllIIlIIIIllIIIIII <= lllllllllllllllllIIlIIIIlIlllIll + lllllllllllllllllIIlIIIIlIlllIII; lllllllllllllllllIIlIIIIllIIIIII += lllllllllllllllllIIlIIIIlIlllIII) {
            float lllllllllllllllllIIlIIIIllIIIIll = lllllllllllllllllIIlIIIIllIIIIII;
            if (lllllllllllllllllIIlIIIIllIIIIll > lllllllllllllllllIIlIIIIlIlllIll) {
                lllllllllllllllllIIlIIIIllIIIIll = lllllllllllllllllIIlIIIIlIlllIll;
            }
            final float lllllllllllllllllIIlIIIIllIIIIlI = (float)(lllllllllllllllllIIlIIIIlIlllIlI + FastTrig.cos(Math.toRadians(lllllllllllllllllIIlIIIIllIIIIll)) * this.radius1);
            final float lllllllllllllllllIIlIIIIllIIIIIl = (float)(lllllllllllllllllIIlIIIIlIlllIIl + FastTrig.sin(Math.toRadians(lllllllllllllllllIIlIIIIllIIIIll)) * this.radius2);
            if (lllllllllllllllllIIlIIIIllIIIIlI > this.maxX) {
                this.maxX = lllllllllllllllllIIlIIIIllIIIIlI;
            }
            if (lllllllllllllllllIIlIIIIllIIIIIl > this.maxY) {
                this.maxY = lllllllllllllllllIIlIIIIllIIIIIl;
            }
            if (lllllllllllllllllIIlIIIIllIIIIlI < this.minX) {
                this.minX = lllllllllllllllllIIlIIIIllIIIIlI;
            }
            if (lllllllllllllllllIIlIIIIllIIIIIl < this.minY) {
                this.minY = lllllllllllllllllIIlIIIIllIIIIIl;
            }
            lllllllllllllllllIIlIIIIlIllllIl.add(new Float(lllllllllllllllllIIlIIIIllIIIIlI));
            lllllllllllllllllIIlIIIIlIllllIl.add(new Float(lllllllllllllllllIIlIIIIllIIIIIl));
        }
        this.points = new float[lllllllllllllllllIIlIIIIlIllllIl.size()];
        for (int lllllllllllllllllIIlIIIIlIllllll = 0; lllllllllllllllllIIlIIIIlIllllll < this.points.length; ++lllllllllllllllllIIlIIIIlIllllll) {
            this.points[lllllllllllllllllIIlIIIIlIllllll] = lllllllllllllllllIIlIIIIlIllllIl.get(lllllllllllllllllIIlIIIIlIllllll);
        }
    }
    
    public void setRadius2(final float lllllllllllllllllIIlIIIIllIlIIIl) {
        if (lllllllllllllllllIIlIIIIllIlIIIl != this.radius2) {
            this.radius2 = lllllllllllllllllIIlIIIIllIlIIIl;
            this.pointsDirty = true;
        }
    }
    
    public Ellipse(final float lllllllllllllllllIIlIIIIlllIlllI, final float lllllllllllllllllIIlIIIIllllIIll, final float lllllllllllllllllIIlIIIIlllIllII, final float lllllllllllllllllIIlIIIIllllIIIl, final int lllllllllllllllllIIlIIIIlllIlIlI) {
        this.x = lllllllllllllllllIIlIIIIlllIlllI - lllllllllllllllllIIlIIIIlllIllII;
        this.y = lllllllllllllllllIIlIIIIllllIIll - lllllllllllllllllIIlIIIIllllIIIl;
        this.radius1 = lllllllllllllllllIIlIIIIlllIllII;
        this.radius2 = lllllllllllllllllIIlIIIIllllIIIl;
        this.segmentCount = lllllllllllllllllIIlIIIIlllIlIlI;
        this.checkPoints();
    }
    
    static {
        DEFAULT_SEGMENT_COUNT = 50;
    }
    
    public float getRadius1() {
        return this.radius1;
    }
    
    @Override
    public Shape transform(final Transform lllllllllllllllllIIlIIIIlIlIIIll) {
        this.checkPoints();
        final Polygon lllllllllllllllllIIlIIIIlIlIIllI = new Polygon();
        final float[] lllllllllllllllllIIlIIIIlIlIIlIl = new float[this.points.length];
        lllllllllllllllllIIlIIIIlIlIIIll.transform(this.points, 0, lllllllllllllllllIIlIIIIlIlIIlIl, 0, this.points.length / 2);
        lllllllllllllllllIIlIIIIlIlIIllI.points = lllllllllllllllllIIlIIIIlIlIIlIl;
        lllllllllllllllllIIlIIIIlIlIIllI.checkPoints();
        return lllllllllllllllllIIlIIIIlIlIIllI;
    }
    
    @Override
    protected void findCenter() {
        this.center = new float[2];
        this.center[0] = this.x + this.radius1;
        this.center[1] = this.y + this.radius2;
    }
    
    @Override
    protected void calculateRadius() {
        this.boundingCircleRadius = ((this.radius1 > this.radius2) ? this.radius1 : this.radius2);
    }
}
