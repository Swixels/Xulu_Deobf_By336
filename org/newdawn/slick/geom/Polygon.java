package org.newdawn.slick.geom;

import java.util.*;

public class Polygon extends Shape
{
    private /* synthetic */ boolean closed;
    private /* synthetic */ boolean allowDups;
    
    @Override
    public Shape transform(final Transform lllllllllllllllllIllIlllIlIIIlll) {
        this.checkPoints();
        final Polygon lllllllllllllllllIllIlllIlIIlIlI = new Polygon();
        final float[] lllllllllllllllllIllIlllIlIIlIIl = new float[this.points.length];
        lllllllllllllllllIllIlllIlIIIlll.transform(this.points, 0, lllllllllllllllllIllIlllIlIIlIIl, 0, this.points.length / 2);
        lllllllllllllllllIllIlllIlIIlIlI.points = lllllllllllllllllIllIlllIlIIlIIl;
        lllllllllllllllllIllIlllIlIIlIlI.findCenter();
        lllllllllllllllllIllIlllIlIIlIlI.closed = this.closed;
        return lllllllllllllllllIllIlllIlIIlIlI;
    }
    
    @Override
    public void setY(final float lllllllllllllllllIllIlllIIlllIll) {
        super.setY(lllllllllllllllllIllIlllIIlllIll);
        this.pointsDirty = false;
    }
    
    public void setClosed(final boolean lllllllllllllllllIllIlllIIllIIIl) {
        this.closed = lllllllllllllllllIllIlllIIllIIIl;
    }
    
    @Override
    protected void createPoints() {
    }
    
    public Polygon() {
        this.allowDups = false;
        this.closed = true;
        this.points = new float[0];
        this.maxX = -1.4E-45f;
        this.maxY = -1.4E-45f;
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
    }
    
    public Polygon(final float[] lllllllllllllllllIllIlllIllIllll) {
        this.allowDups = false;
        this.closed = true;
        final int lllllllllllllllllIllIlllIlllIIIl = lllllllllllllllllIllIlllIllIllll.length;
        this.points = new float[lllllllllllllllllIllIlllIlllIIIl];
        this.maxX = -1.4E-45f;
        this.maxY = -1.4E-45f;
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.x = Float.MAX_VALUE;
        this.y = Float.MAX_VALUE;
        for (int lllllllllllllllllIllIlllIlllIlII = 0; lllllllllllllllllIllIlllIlllIlII < lllllllllllllllllIllIlllIlllIIIl; ++lllllllllllllllllIllIlllIlllIlII) {
            this.points[lllllllllllllllllIllIlllIlllIlII] = lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII];
            if (lllllllllllllllllIllIlllIlllIlII % 2 == 0) {
                if (lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII] > this.maxX) {
                    this.maxX = lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII];
                }
                if (lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII] < this.minX) {
                    this.minX = lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII];
                }
                if (lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII] < this.x) {
                    this.x = lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII];
                }
            }
            else {
                if (lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII] > this.maxY) {
                    this.maxY = lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII];
                }
                if (lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII] < this.minY) {
                    this.minY = lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII];
                }
                if (lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII] < this.y) {
                    this.y = lllllllllllllllllIllIlllIllIllll[lllllllllllllllllIllIlllIlllIlII];
                }
            }
        }
        this.findCenter();
        this.calculateRadius();
        this.pointsDirty = true;
    }
    
    @Override
    public void setX(final float lllllllllllllllllIllIlllIlIIIIIl) {
        super.setX(lllllllllllllllllIllIlllIlIIIIIl);
        this.pointsDirty = false;
    }
    
    public void setAllowDuplicatePoints(final boolean lllllllllllllllllIllIlllIllIIlII) {
        this.allowDups = lllllllllllllllllIllIlllIllIIlII;
    }
    
    public void addPoint(final float lllllllllllllllllIllIlllIlIlIlIl, final float lllllllllllllllllIllIlllIlIlIlII) {
        if (this.hasVertex(lllllllllllllllllIllIlllIlIlIlIl, lllllllllllllllllIllIlllIlIlIlII) && !this.allowDups) {
            return;
        }
        final ArrayList lllllllllllllllllIllIlllIlIllIII = new ArrayList();
        for (int lllllllllllllllllIllIlllIlIlllIl = 0; lllllllllllllllllIllIlllIlIlllIl < this.points.length; ++lllllllllllllllllIllIlllIlIlllIl) {
            lllllllllllllllllIllIlllIlIllIII.add(new Float(this.points[lllllllllllllllllIllIlllIlIlllIl]));
        }
        lllllllllllllllllIllIlllIlIllIII.add(new Float(lllllllllllllllllIllIlllIlIlIlIl));
        lllllllllllllllllIllIlllIlIllIII.add(new Float(lllllllllllllllllIllIlllIlIlIlII));
        final int lllllllllllllllllIllIlllIlIlIlll = lllllllllllllllllIllIlllIlIllIII.size();
        this.points = new float[lllllllllllllllllIllIlllIlIlIlll];
        for (int lllllllllllllllllIllIlllIlIlllII = 0; lllllllllllllllllIllIlllIlIlllII < lllllllllllllllllIllIlllIlIlIlll; ++lllllllllllllllllIllIlllIlIlllII) {
            this.points[lllllllllllllllllIllIlllIlIlllII] = lllllllllllllllllIllIlllIlIllIII.get(lllllllllllllllllIllIlllIlIlllII);
        }
        if (lllllllllllllllllIllIlllIlIlIlIl > this.maxX) {
            this.maxX = lllllllllllllllllIllIlllIlIlIlIl;
        }
        if (lllllllllllllllllIllIlllIlIlIlII > this.maxY) {
            this.maxY = lllllllllllllllllIllIlllIlIlIlII;
        }
        if (lllllllllllllllllIllIlllIlIlIlIl < this.minX) {
            this.minX = lllllllllllllllllIllIlllIlIlIlIl;
        }
        if (lllllllllllllllllIllIlllIlIlIlII < this.minY) {
            this.minY = lllllllllllllllllIllIlllIlIlIlII;
        }
        this.findCenter();
        this.calculateRadius();
        this.pointsDirty = true;
    }
    
    @Override
    public boolean closed() {
        return this.closed;
    }
    
    public Polygon copy() {
        final float[] lllllllllllllllllIllIlllIIlIlIll = new float[this.points.length];
        System.arraycopy(this.points, 0, lllllllllllllllllIllIlllIIlIlIll, 0, lllllllllllllllllIllIlllIIlIlIll.length);
        return new Polygon(lllllllllllllllllIllIlllIIlIlIll);
    }
}
