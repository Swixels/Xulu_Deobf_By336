package org.newdawn.slick.geom;

import java.util.*;

public class MorphShape extends Shape
{
    private /* synthetic */ ArrayList shapes;
    private /* synthetic */ Shape next;
    private /* synthetic */ float offset;
    private /* synthetic */ Shape current;
    
    public void setMorphTime(final float lllIllIllIlII) {
        int lllIllIlllIII = (int)lllIllIllIlII;
        int lllIllIllIlll = lllIllIlllIII + 1;
        final float lllIllIllIllI = lllIllIllIlII - lllIllIlllIII;
        lllIllIlllIII = this.rational(lllIllIlllIII);
        lllIllIllIlll = this.rational(lllIllIllIlll);
        this.setFrame(lllIllIlllIII, lllIllIllIlll, lllIllIllIllI);
    }
    
    public void updateMorphTime(final float lllIllIlIIlll) {
        this.offset += lllIllIlIIlll;
        if (this.offset < 0.0f) {
            int lllIllIlIllII = this.shapes.indexOf(this.current);
            if (lllIllIlIllII < 0) {
                lllIllIlIllII = this.shapes.size() - 1;
            }
            final int lllIllIlIlIll = this.rational(lllIllIlIllII + 1);
            this.setFrame(lllIllIlIllII, lllIllIlIlIll, this.offset);
            ++this.offset;
        }
        else if (this.offset > 1.0f) {
            int lllIllIlIlIlI = this.shapes.indexOf(this.next);
            if (lllIllIlIlIlI < 1) {
                lllIllIlIlIlI = 0;
            }
            final int lllIllIlIlIIl = this.rational(lllIllIlIlIlI + 1);
            this.setFrame(lllIllIlIlIlI, lllIllIlIlIIl, this.offset);
            --this.offset;
        }
        else {
            this.pointsDirty = true;
        }
    }
    
    public void addShape(final Shape lllIlllIIlIll) {
        if (lllIlllIIlIll.points.length != this.points.length) {
            throw new RuntimeException("Attempt to morph between two shapes with different vertex counts");
        }
        final Shape lllIlllIIllIl = this.shapes.get(this.shapes.size() - 1);
        if (this.equalShapes(lllIlllIIllIl, lllIlllIIlIll)) {
            this.shapes.add(lllIlllIIllIl);
        }
        else {
            this.shapes.add(lllIlllIIlIll);
        }
        if (this.shapes.size() == 2) {
            this.next = this.shapes.get(1);
        }
    }
    
    @Override
    protected void createPoints() {
        if (this.current == this.next) {
            System.arraycopy(this.current.points, 0, this.points, 0, this.points.length);
            return;
        }
        final float[] lllIllIIIIlII = this.current.points;
        final float[] lllIllIIIIIll = this.next.points;
        for (int lllIllIIIIllI = 0; lllIllIIIIllI < this.points.length; ++lllIllIIIIllI) {
            this.points[lllIllIIIIllI] = lllIllIIIIlII[lllIllIIIIllI] * (1.0f - this.offset);
            final float[] points = this.points;
            final int n = lllIllIIIIllI;
            points[n] += lllIllIIIIIll[lllIllIIIIllI] * this.offset;
        }
    }
    
    private boolean equalShapes(final Shape lllIlllIIIIlI, final Shape lllIlllIIIIIl) {
        lllIlllIIIIlI.checkPoints();
        lllIlllIIIIIl.checkPoints();
        for (int lllIlllIIIllI = 0; lllIlllIIIllI < lllIlllIIIIlI.points.length; ++lllIlllIIIllI) {
            if (lllIlllIIIIlI.points[lllIlllIIIllI] != lllIlllIIIIIl.points[lllIlllIIIllI]) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Shape transform(final Transform lllIlIllllIll) {
        this.createPoints();
        final Polygon lllIlIllllIlI = new Polygon(this.points);
        return lllIlIllllIlI;
    }
    
    private int rational(int lllIllIIlIlll) {
        while (lllIllIIlIlll >= this.shapes.size()) {
            lllIllIIlIlll -= this.shapes.size();
        }
        while (lllIllIIlIlll < 0) {
            lllIllIIlIlll += this.shapes.size();
        }
        return lllIllIIlIlll;
    }
    
    private void setFrame(final int lllIllIIIllIl, final int lllIllIIlIIII, final float lllIllIIIlIll) {
        this.current = this.shapes.get(lllIllIIIllIl);
        this.next = this.shapes.get(lllIllIIlIIII);
        this.offset = lllIllIIIlIll;
        this.pointsDirty = true;
    }
    
    public void setExternalFrame(final Shape lllIllIIlllIl) {
        this.current = lllIllIIlllIl;
        this.next = this.shapes.get(0);
        this.offset = 0.0f;
    }
    
    public MorphShape(final Shape lllIlllIlIlll) {
        this.shapes = new ArrayList();
        this.shapes.add(lllIlllIlIlll);
        final float[] lllIlllIlIllI = lllIlllIlIlll.points;
        this.points = new float[lllIlllIlIllI.length];
        this.current = lllIlllIlIlll;
        this.next = lllIlllIlIlll;
    }
}
