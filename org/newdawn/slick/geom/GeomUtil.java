package org.newdawn.slick.geom;

import java.util.*;

public class GeomUtil
{
    public /* synthetic */ float EDGE_SCALE;
    public /* synthetic */ int MAX_POINTS;
    public /* synthetic */ float EPSILON;
    public /* synthetic */ GeomUtilListener listener;
    
    public Shape[] union(Shape lIlIIIllllllIll, Shape lIlIIIllllllIlI) {
        lIlIIIllllllIll = (Exception)((Shape)lIlIIIllllllIll).transform(new Transform());
        lIlIIIllllllIlI = lIlIIIllllllIlI.transform(new Transform());
        if (!((Shape)lIlIIIllllllIll).intersects(lIlIIIllllllIlI)) {
            return new Shape[] { (Shape)lIlIIIllllllIll, lIlIIIllllllIlI };
        }
        boolean lIlIIIllllllllI = false;
        int lIlIIIlllllllIl = 0;
        for (int lIlIIlIIIIIIIll = 0; lIlIIlIIIIIIIll < ((Shape)lIlIIIllllllIll).getPointCount(); ++lIlIIlIIIIIIIll) {
            if (lIlIIIllllllIlI.contains(((Shape)lIlIIIllllllIll).getPoint(lIlIIlIIIIIIIll)[0], ((Shape)lIlIIIllllllIll).getPoint(lIlIIlIIIIIIIll)[1]) && !lIlIIIllllllIlI.hasVertex(((Shape)lIlIIIllllllIll).getPoint(lIlIIlIIIIIIIll)[0], ((Shape)lIlIIIllllllIll).getPoint(lIlIIlIIIIIIIll)[1])) {
                lIlIIIllllllllI = true;
                break;
            }
            if (lIlIIIllllllIlI.hasVertex(((Shape)lIlIIIllllllIll).getPoint(lIlIIlIIIIIIIll)[0], ((Shape)lIlIIIllllllIll).getPoint(lIlIIlIIIIIIIll)[1])) {
                ++lIlIIIlllllllIl;
            }
        }
        for (int lIlIIlIIIIIIIlI = 0; lIlIIlIIIIIIIlI < lIlIIIllllllIlI.getPointCount(); ++lIlIIlIIIIIIIlI) {
            if (((Shape)lIlIIIllllllIll).contains(lIlIIIllllllIlI.getPoint(lIlIIlIIIIIIIlI)[0], lIlIIIllllllIlI.getPoint(lIlIIlIIIIIIIlI)[1]) && !((Shape)lIlIIIllllllIll).hasVertex(lIlIIIllllllIlI.getPoint(lIlIIlIIIIIIIlI)[0], lIlIIIllllllIlI.getPoint(lIlIIlIIIIIIIlI)[1])) {
                lIlIIIllllllllI = true;
                break;
            }
        }
        if (!lIlIIIllllllllI && lIlIIIlllllllIl < 2) {
            return new Shape[] { (Shape)lIlIIIllllllIll, lIlIIIllllllIlI };
        }
        return this.combine((Shape)lIlIIIllllllIll, lIlIIIllllllIlI, false);
    }
    
    public Line getLine(final Shape lIlIIIlIlIllllI, final int lIlIIIlIlIlllIl, final int lIlIIIlIlIlIlIl) {
        final float[] lIlIIIlIlIllIll = lIlIIIlIlIllllI.getPoint(lIlIIIlIlIlllIl);
        final float[] lIlIIIlIlIllIIl = lIlIIIlIlIllllI.getPoint(lIlIIIlIlIlIlIl);
        final Line lIlIIIlIlIllIII = new Line(lIlIIIlIlIllIll[0], lIlIIIlIlIllIll[1], lIlIIIlIlIllIIl[0], lIlIIIlIlIllIIl[1]);
        return lIlIIIlIlIllIII;
    }
    
    public Line getLine(final Shape lIlIIIlIIllIllI, final float lIlIIIlIIllIlII, final float lIlIIIlIIllIIlI, final int lIlIIIlIIllllII) {
        final float[] lIlIIIlIIlllIlI = lIlIIIlIIllIllI.getPoint(lIlIIIlIIllllII);
        final Line lIlIIIlIIlllIII = new Line(lIlIIIlIIllIlII, lIlIIIlIIllIIlI, lIlIIIlIIlllIlI[0], lIlIIIlIIlllIlI[1]);
        return lIlIIIlIIlllIII;
    }
    
    public HitResult intersect(final Shape lIlIIIlIllllIIl, final Line lIlIIIlIlllIIll) {
        float lIlIIIlIlllIlll = Float.MAX_VALUE;
        HitResult lIlIIIlIlllIllI = null;
        for (int lIlIIIlIllllIll = 0; lIlIIIlIllllIll < lIlIIIlIllllIIl.getPointCount(); ++lIlIIIlIllllIll) {
            final int lIlIIIlIllllllI = rationalPoint(lIlIIIlIllllIIl, lIlIIIlIllllIll + 1);
            final Line lIlIIIlIlllllIl = this.getLine(lIlIIIlIllllIIl, lIlIIIlIllllIll, lIlIIIlIllllllI);
            final Vector2f lIlIIIlIlllllII = lIlIIIlIlllIIll.intersect(lIlIIIlIlllllIl, true);
            if (lIlIIIlIlllllII != null) {
                final float lIlIIIlIlllllll = lIlIIIlIlllllII.distance(lIlIIIlIlllIIll.getStart());
                if (lIlIIIlIlllllll < lIlIIIlIlllIlll && lIlIIIlIlllllll > this.EPSILON) {
                    lIlIIIlIlllIllI = new HitResult();
                    lIlIIIlIlllIllI.pt = lIlIIIlIlllllII;
                    lIlIIIlIlllIllI.line = lIlIIIlIlllllIl;
                    lIlIIIlIlllIllI.p1 = lIlIIIlIllllIll;
                    lIlIIIlIlllIllI.p2 = lIlIIIlIllllllI;
                    lIlIIIlIlllIlll = lIlIIIlIlllllll;
                }
            }
        }
        return lIlIIIlIlllIllI;
    }
    
    public Shape[] subtract(Shape lIlIIlIIIlIlIIl, Shape lIlIIlIIIlIlIII) {
        lIlIIlIIIlIlIIl = lIlIIlIIIlIlIIl.transform(new Transform());
        lIlIIlIIIlIlIII = lIlIIlIIIlIlIII.transform(new Transform());
        int lIlIIlIIIlIllII = 0;
        for (int lIlIIlIIIllIIlI = 0; lIlIIlIIIllIIlI < lIlIIlIIIlIlIIl.getPointCount(); ++lIlIIlIIIllIIlI) {
            if (lIlIIlIIIlIlIII.contains(lIlIIlIIIlIlIIl.getPoint(lIlIIlIIIllIIlI)[0], lIlIIlIIIlIlIIl.getPoint(lIlIIlIIIllIIlI)[1])) {
                ++lIlIIlIIIlIllII;
            }
        }
        if (lIlIIlIIIlIllII == lIlIIlIIIlIlIIl.getPointCount()) {
            return new Shape[0];
        }
        if (!lIlIIlIIIlIlIIl.intersects(lIlIIlIIIlIlIII)) {
            return new Shape[] { lIlIIlIIIlIlIIl };
        }
        int lIlIIlIIIlIlIll = 0;
        for (int lIlIIlIIIllIIIl = 0; lIlIIlIIIllIIIl < lIlIIlIIIlIlIII.getPointCount(); ++lIlIIlIIIllIIIl) {
            if (lIlIIlIIIlIlIIl.contains(lIlIIlIIIlIlIII.getPoint(lIlIIlIIIllIIIl)[0], lIlIIlIIIlIlIII.getPoint(lIlIIlIIIllIIIl)[1]) && !this.onPath(lIlIIlIIIlIlIIl, lIlIIlIIIlIlIII.getPoint(lIlIIlIIIllIIIl)[0], lIlIIlIIIlIlIII.getPoint(lIlIIlIIIllIIIl)[1])) {
                ++lIlIIlIIIlIlIll;
            }
        }
        for (int lIlIIlIIIllIIII = 0; lIlIIlIIIllIIII < lIlIIlIIIlIlIIl.getPointCount(); ++lIlIIlIIIllIIII) {
            if (lIlIIlIIIlIlIII.contains(lIlIIlIIIlIlIIl.getPoint(lIlIIlIIIllIIII)[0], lIlIIlIIIlIlIIl.getPoint(lIlIIlIIIllIIII)[1]) && !this.onPath(lIlIIlIIIlIlIII, lIlIIlIIIlIlIIl.getPoint(lIlIIlIIIllIIII)[0], lIlIIlIIIlIlIIl.getPoint(lIlIIlIIIllIIII)[1])) {
                ++lIlIIlIIIlIlIll;
            }
        }
        if (lIlIIlIIIlIlIll < 1) {
            return new Shape[] { lIlIIlIIIlIlIIl };
        }
        return this.combine(lIlIIlIIIlIlIIl, lIlIIlIIIlIlIII, true);
    }
    
    private Shape[] combine(final Shape lIlIIIlllIllIll, final Shape lIlIIIlllIlIllI, final boolean lIlIIIlllIllIIl) {
        if (lIlIIIlllIllIIl) {
            final ArrayList lIlIIIllllIIIII = new ArrayList();
            final ArrayList lIlIIIlllIlllll = new ArrayList();
            for (int lIlIIIllllIlIII = 0; lIlIIIllllIlIII < lIlIIIlllIllIll.getPointCount(); ++lIlIIIllllIlIII) {
                final float[] lIlIIIllllIlIIl = lIlIIIlllIllIll.getPoint(lIlIIIllllIlIII);
                if (lIlIIIlllIlIllI.contains(lIlIIIllllIlIIl[0], lIlIIIllllIlIIl[1])) {
                    lIlIIIlllIlllll.add(new Vector2f(lIlIIIllllIlIIl[0], lIlIIIllllIlIIl[1]));
                    if (this.listener != null) {
                        this.listener.pointExcluded(lIlIIIllllIlIIl[0], lIlIIIllllIlIIl[1]);
                    }
                }
            }
            for (int lIlIIIllllIIIIl = 0; lIlIIIllllIIIIl < lIlIIIlllIllIll.getPointCount(); ++lIlIIIllllIIIIl) {
                final float[] lIlIIIllllIIIll = lIlIIIlllIllIll.getPoint(lIlIIIllllIIIIl);
                final Vector2f lIlIIIllllIIIlI = new Vector2f(lIlIIIllllIIIll[0], lIlIIIllllIIIll[1]);
                if (!lIlIIIlllIlllll.contains(lIlIIIllllIIIlI)) {
                    final Shape lIlIIIllllIIlII = this.combineSingle(lIlIIIlllIllIll, lIlIIIlllIlIllI, true, lIlIIIllllIIIIl);
                    lIlIIIllllIIIII.add(lIlIIIllllIIlII);
                    for (int lIlIIIllllIIlIl = 0; lIlIIIllllIIlIl < lIlIIIllllIIlII.getPointCount(); ++lIlIIIllllIIlIl) {
                        final float[] lIlIIIllllIIlll = lIlIIIllllIIlII.getPoint(lIlIIIllllIIlIl);
                        final Vector2f lIlIIIllllIIllI = new Vector2f(lIlIIIllllIIlll[0], lIlIIIllllIIlll[1]);
                        lIlIIIlllIlllll.add(lIlIIIllllIIllI);
                    }
                }
            }
            return lIlIIIllllIIIII.toArray(new Shape[0]);
        }
        for (int lIlIIIlllIlllIl = 0; lIlIIIlllIlllIl < lIlIIIlllIllIll.getPointCount(); ++lIlIIIlllIlllIl) {
            if (!lIlIIIlllIlIllI.contains(lIlIIIlllIllIll.getPoint(lIlIIIlllIlllIl)[0], lIlIIIlllIllIll.getPoint(lIlIIIlllIlllIl)[1]) && !lIlIIIlllIlIllI.hasVertex(lIlIIIlllIllIll.getPoint(lIlIIIlllIlllIl)[0], lIlIIIlllIllIll.getPoint(lIlIIIlllIlllIl)[1])) {
                final Shape lIlIIIlllIllllI = this.combineSingle(lIlIIIlllIllIll, lIlIIIlllIlIllI, false, lIlIIIlllIlllIl);
                return new Shape[] { lIlIIIlllIllllI };
            }
        }
        return new Shape[] { lIlIIIlllIlIllI };
    }
    
    public void setListener(final GeomUtilListener lIlIIlIIIIIlIlI) {
        this.listener = lIlIIlIIIIIlIlI;
    }
    
    public static int rationalPoint(final Shape lIlIIIlIllIIlll, int lIlIIIlIllIIllI) {
        while (lIlIIIlIllIIllI < 0) {
            lIlIIIlIllIIllI += lIlIIIlIllIIlll.getPointCount();
        }
        while (lIlIIIlIllIIllI >= lIlIIIlIllIIlll.getPointCount()) {
            lIlIIIlIllIIllI -= lIlIIIlIllIIlll.getPointCount();
        }
        return lIlIIIlIllIIllI;
    }
    
    private boolean onPath(final Shape lIlIIlIIIIlIlIl, final float lIlIIlIIIIllIII, final float lIlIIlIIIIlIlll) {
        for (int lIlIIlIIIIllIll = 0; lIlIIlIIIIllIll < lIlIIlIIIIlIlIl.getPointCount() + 1; ++lIlIIlIIIIllIll) {
            final int lIlIIlIIIIlllIl = rationalPoint(lIlIIlIIIIlIlIl, lIlIIlIIIIllIll + 1);
            final Line lIlIIlIIIIlllII = this.getLine(lIlIIlIIIIlIlIl, rationalPoint(lIlIIlIIIIlIlIl, lIlIIlIIIIllIll), lIlIIlIIIIlllIl);
            if (lIlIIlIIIIlllII.distance(new Vector2f(lIlIIlIIIIllIII, lIlIIlIIIIlIlll)) < this.EPSILON * 100.0f) {
                return true;
            }
        }
        return false;
    }
    
    private Shape combineSingle(final Shape lIlIIIllIIlllIl, final Shape lIlIIIllIIlllII, final boolean lIlIIIllIIllIll, final int lIlIIIllIIllIlI) {
        Shape lIlIIIllIlIIlll = lIlIIIllIIlllIl;
        Shape lIlIIIllIlIIllI = lIlIIIllIIlllII;
        int lIlIIIllIlIIlIl = lIlIIIllIIllIlI;
        int lIlIIIllIlIIlII = 1;
        final Polygon lIlIIIllIlIIIll = new Polygon();
        boolean lIlIIIllIlIIIlI = true;
        int lIlIIIllIlIIIIl = 0;
        float lIlIIIllIlIIIII = lIlIIIllIlIIlll.getPoint(lIlIIIllIlIIlIl)[0];
        float lIlIIIllIIlllll = lIlIIIllIlIIlll.getPoint(lIlIIIllIlIIlIl)[1];
        while (!lIlIIIllIlIIIll.hasVertex(lIlIIIllIlIIIII, lIlIIIllIIlllll) || lIlIIIllIlIIIlI || lIlIIIllIlIIlll != lIlIIIllIIlllIl) {
            lIlIIIllIlIIIlI = false;
            if (++lIlIIIllIlIIIIl > this.MAX_POINTS) {
                break;
            }
            lIlIIIllIlIIIll.addPoint(lIlIIIllIlIIIII, lIlIIIllIIlllll);
            if (this.listener != null) {
                this.listener.pointUsed(lIlIIIllIlIIIII, lIlIIIllIIlllll);
            }
            final Line lIlIIIllIlIlllI = this.getLine(lIlIIIllIlIIlll, lIlIIIllIlIIIII, lIlIIIllIIlllll, rationalPoint(lIlIIIllIlIIlll, lIlIIIllIlIIlIl + lIlIIIllIlIIlII));
            final HitResult lIlIIIllIlIllIl = this.intersect(lIlIIIllIlIIllI, lIlIIIllIlIlllI);
            if (lIlIIIllIlIllIl != null) {
                final Line lIlIIIllIllIIlI = lIlIIIllIlIllIl.line;
                final Vector2f lIlIIIllIllIIIl = lIlIIIllIlIllIl.pt;
                lIlIIIllIlIIIII = lIlIIIllIllIIIl.x;
                lIlIIIllIIlllll = lIlIIIllIllIIIl.y;
                if (this.listener != null) {
                    this.listener.pointIntersected(lIlIIIllIlIIIII, lIlIIIllIIlllll);
                }
                if (lIlIIIllIlIIllI.hasVertex(lIlIIIllIlIIIII, lIlIIIllIIlllll)) {
                    lIlIIIllIlIIlIl = lIlIIIllIlIIllI.indexOf(lIlIIIllIllIIIl.x, lIlIIIllIllIIIl.y);
                    lIlIIIllIlIIlII = 1;
                    lIlIIIllIlIIIII = lIlIIIllIllIIIl.x;
                    lIlIIIllIIlllll = lIlIIIllIllIIIl.y;
                    final Shape lIlIIIllIllIllI = lIlIIIllIlIIlll;
                    lIlIIIllIlIIlll = lIlIIIllIlIIllI;
                    lIlIIIllIlIIllI = lIlIIIllIllIllI;
                }
                else {
                    float lIlIIIllIllIIII = lIlIIIllIllIIlI.getDX() / lIlIIIllIllIIlI.length();
                    float lIlIIIllIlIllll = lIlIIIllIllIIlI.getDY() / lIlIIIllIllIIlI.length();
                    lIlIIIllIllIIII *= this.EDGE_SCALE;
                    lIlIIIllIlIllll *= this.EDGE_SCALE;
                    if (lIlIIIllIlIIlll.contains(lIlIIIllIllIIIl.x + lIlIIIllIllIIII, lIlIIIllIllIIIl.y + lIlIIIllIlIllll)) {
                        if (lIlIIIllIIllIll) {
                            if (lIlIIIllIlIIlll == lIlIIIllIIlllII) {
                                lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p2;
                                lIlIIIllIlIIlII = -1;
                            }
                            else {
                                lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p1;
                                lIlIIIllIlIIlII = 1;
                            }
                        }
                        else if (lIlIIIllIlIIlll == lIlIIIllIIlllIl) {
                            lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p2;
                            lIlIIIllIlIIlII = -1;
                        }
                        else {
                            lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p2;
                            lIlIIIllIlIIlII = -1;
                        }
                        final Shape lIlIIIllIllIlIl = lIlIIIllIlIIlll;
                        lIlIIIllIlIIlll = lIlIIIllIlIIllI;
                        lIlIIIllIlIIllI = lIlIIIllIllIlIl;
                    }
                    else if (lIlIIIllIlIIlll.contains(lIlIIIllIllIIIl.x - lIlIIIllIllIIII, lIlIIIllIllIIIl.y - lIlIIIllIlIllll)) {
                        if (lIlIIIllIIllIll) {
                            if (lIlIIIllIlIIlll == lIlIIIllIIlllIl) {
                                lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p2;
                                lIlIIIllIlIIlII = -1;
                            }
                            else {
                                lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p1;
                                lIlIIIllIlIIlII = 1;
                            }
                        }
                        else if (lIlIIIllIlIIlll == lIlIIIllIIlllII) {
                            lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p1;
                            lIlIIIllIlIIlII = 1;
                        }
                        else {
                            lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p1;
                            lIlIIIllIlIIlII = 1;
                        }
                        final Shape lIlIIIllIllIlII = lIlIIIllIlIIlll;
                        lIlIIIllIlIIlll = lIlIIIllIlIIllI;
                        lIlIIIllIlIIllI = lIlIIIllIllIlII;
                    }
                    else {
                        if (lIlIIIllIIllIll) {
                            break;
                        }
                        lIlIIIllIlIIlIl = lIlIIIllIlIllIl.p1;
                        lIlIIIllIlIIlII = 1;
                        final Shape lIlIIIllIllIIll = lIlIIIllIlIIlll;
                        lIlIIIllIlIIlll = lIlIIIllIlIIllI;
                        lIlIIIllIlIIllI = lIlIIIllIllIIll;
                        lIlIIIllIlIIlIl = rationalPoint(lIlIIIllIlIIlll, lIlIIIllIlIIlIl + lIlIIIllIlIIlII);
                        lIlIIIllIlIIIII = lIlIIIllIlIIlll.getPoint(lIlIIIllIlIIlIl)[0];
                        lIlIIIllIIlllll = lIlIIIllIlIIlll.getPoint(lIlIIIllIlIIlIl)[1];
                    }
                }
            }
            else {
                lIlIIIllIlIIlIl = rationalPoint(lIlIIIllIlIIlll, lIlIIIllIlIIlIl + lIlIIIllIlIIlII);
                lIlIIIllIlIIIII = lIlIIIllIlIIlll.getPoint(lIlIIIllIlIIlIl)[0];
                lIlIIIllIIlllll = lIlIIIllIlIIlll.getPoint(lIlIIIllIlIIlIl)[1];
            }
        }
        lIlIIIllIlIIIll.addPoint(lIlIIIllIlIIIII, lIlIIIllIIlllll);
        if (this.listener != null) {
            this.listener.pointUsed(lIlIIIllIlIIIII, lIlIIIllIIlllll);
        }
        return lIlIIIllIlIIIll;
    }
    
    public GeomUtil() {
        this.EPSILON = 1.0E-4f;
        this.EDGE_SCALE = 1.0f;
        this.MAX_POINTS = 10000;
    }
    
    public class HitResult
    {
        public /* synthetic */ Line line;
        public /* synthetic */ Vector2f pt;
        public /* synthetic */ int p1;
        public /* synthetic */ int p2;
    }
}
