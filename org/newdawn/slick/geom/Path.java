package org.newdawn.slick.geom;

import java.util.*;

public class Path extends Shape
{
    private /* synthetic */ float cx;
    private /* synthetic */ float cy;
    private /* synthetic */ ArrayList hole;
    private /* synthetic */ ArrayList localPoints;
    private /* synthetic */ boolean closed;
    private /* synthetic */ ArrayList holes;
    
    public void startHole(final float lllIlllll, final float lllIllllI) {
        this.hole = new ArrayList();
        this.holes.add(this.hole);
    }
    
    @Override
    protected void createPoints() {
        this.points = new float[this.localPoints.size() * 2];
        for (int llIIlIIII = 0; llIIlIIII < this.localPoints.size(); ++llIIlIIII) {
            final float[] llIIlIIIl = this.localPoints.get(llIIlIIII);
            this.points[llIIlIIII * 2] = llIIlIIIl[0];
            this.points[llIIlIIII * 2 + 1] = llIIlIIIl[1];
        }
    }
    
    public void curveTo(final float llIlIIIII, final float llIlIlIIl, final float llIIllllI, final float llIIlllIl, final float llIlIIllI, final float llIlIIlIl, final int llIlIIlII) {
        if (this.cx == llIlIIIII && this.cy == llIlIlIIl) {
            return;
        }
        final Curve llIlIIIll = new Curve(new Vector2f(this.cx, this.cy), new Vector2f(llIIllllI, llIIlllIl), new Vector2f(llIlIIllI, llIlIIlIl), new Vector2f(llIlIIIII, llIlIlIIl));
        final float llIlIIIlI = 1.0f / llIlIIlII;
        for (int llIlIllII = 1; llIlIllII < llIlIIlII + 1; ++llIlIllII) {
            final float llIlIlllI = llIlIllII * llIlIIIlI;
            final Vector2f llIlIllIl = llIlIIIll.pointAt(llIlIlllI);
            if (this.hole != null) {
                this.hole.add(new float[] { llIlIllIl.x, llIlIllIl.y });
            }
            else {
                this.localPoints.add(new float[] { llIlIllIl.x, llIlIllIl.y });
            }
            this.cx = llIlIllIl.x;
            this.cy = llIlIllIl.y;
        }
        this.pointsDirty = true;
    }
    
    private ArrayList transform(final ArrayList lIlllIllI, final Transform lIlllIIII) {
        final float[] lIlllIlII = new float[lIlllIllI.size() * 2];
        final float[] lIlllIIll = new float[lIlllIllI.size() * 2];
        for (int lIllllIIl = 0; lIllllIIl < lIlllIllI.size(); ++lIllllIIl) {
            lIlllIlII[lIllllIIl * 2] = ((float[])lIlllIllI.get(lIllllIIl))[0];
            lIlllIlII[lIllllIIl * 2 + 1] = ((float[])lIlllIllI.get(lIllllIIl))[1];
        }
        lIlllIIII.transform(lIlllIlII, 0, lIlllIIll, 0, lIlllIllI.size());
        final ArrayList lIlllIIlI = new ArrayList();
        for (int lIllllIII = 0; lIllllIII < lIlllIllI.size(); ++lIllllIII) {
            lIlllIIlI.add(new float[] { lIlllIIll[lIllllIII * 2], lIlllIIll[lIllllIII * 2 + 1] });
        }
        return lIlllIIlI;
    }
    
    public void curveTo(final float lllIIlIII, final float lllIIIlll, final float llIllllll, final float llIlllllI, final float llIllllIl, final float lllIIIIll) {
        this.curveTo(lllIIlIII, lllIIIlll, llIllllll, llIlllllI, llIllllIl, lllIIIIll, 10);
    }
    
    public void close() {
        this.closed = true;
    }
    
    public Path(final float llllIIllI, final float llllIIIlI) {
        this.localPoints = new ArrayList();
        this.holes = new ArrayList();
        this.localPoints.add(new float[] { llllIIllI, llllIIIlI });
        this.cx = llllIIllI;
        this.cy = llllIIIlI;
        this.pointsDirty = true;
    }
    
    @Override
    public boolean closed() {
        return this.closed;
    }
    
    public void lineTo(final float lllIlIlIl, final float lllIlIlII) {
        if (this.hole != null) {
            this.hole.add(new float[] { lllIlIlIl, lllIlIlII });
        }
        else {
            this.localPoints.add(new float[] { lllIlIlIl, lllIlIlII });
        }
        this.cx = lllIlIlIl;
        this.cy = lllIlIlII;
        this.pointsDirty = true;
    }
    
    @Override
    public Shape transform(final Transform llIIIIlIl) {
        final Path llIIIIlII = new Path(this.cx, this.cy);
        llIIIIlII.localPoints = this.transform(this.localPoints, llIIIIlIl);
        for (int llIIIIlll = 0; llIIIIlll < this.holes.size(); ++llIIIIlll) {
            llIIIIlII.holes.add(this.transform(this.holes.get(llIIIIlll), llIIIIlIl));
        }
        llIIIIlII.closed = this.closed;
        return llIIIIlII;
    }
}
