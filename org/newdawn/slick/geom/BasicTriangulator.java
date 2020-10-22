package org.newdawn.slick.geom;

import java.util.*;

public class BasicTriangulator implements Triangulator
{
    private /* synthetic */ boolean tried;
    private /* synthetic */ PointList tris;
    private /* synthetic */ PointList poly;
    
    public int getPolyPointCount() {
        return this.poly.size();
    }
    
    private boolean snip(final PointList lIlIIIIIllll, final int lIlIIIIIIIII, final int lIlIIIIIllIl, final int lIIllllllllI, final int lIIlllllllIl, final int[] lIlIIIIIlIlI) {
        final float lIlIIIIIlIII = lIlIIIIIllll.get(lIlIIIIIlIlI[lIlIIIIIIIII]).getX();
        final float lIlIIIIIIlll = lIlIIIIIllll.get(lIlIIIIIlIlI[lIlIIIIIIIII]).getY();
        final float lIlIIIIIIllI = lIlIIIIIllll.get(lIlIIIIIlIlI[lIlIIIIIllIl]).getX();
        final float lIlIIIIIIlIl = lIlIIIIIllll.get(lIlIIIIIlIlI[lIlIIIIIllIl]).getY();
        final float lIlIIIIIIlII = lIlIIIIIllll.get(lIlIIIIIlIlI[lIIllllllllI]).getX();
        final float lIlIIIIIIIll = lIlIIIIIllll.get(lIlIIIIIlIlI[lIIllllllllI]).getY();
        if (1.0E-10f > (lIlIIIIIIllI - lIlIIIIIlIII) * (lIlIIIIIIIll - lIlIIIIIIlll) - (lIlIIIIIIlIl - lIlIIIIIIlll) * (lIlIIIIIIlII - lIlIIIIIlIII)) {
            return false;
        }
        for (int lIlIIIIIlIIl = 0; lIlIIIIIlIIl < lIIlllllllIl; ++lIlIIIIIlIIl) {
            if (lIlIIIIIlIIl != lIlIIIIIIIII && lIlIIIIIlIIl != lIlIIIIIllIl) {
                if (lIlIIIIIlIIl != lIIllllllllI) {
                    final float lIlIIIIlIIlI = lIlIIIIIllll.get(lIlIIIIIlIlI[lIlIIIIIlIIl]).getX();
                    final float lIlIIIIlIIIl = lIlIIIIIllll.get(lIlIIIIIlIlI[lIlIIIIIlIIl]).getY();
                    if (this.insideTriangle(lIlIIIIIlIII, lIlIIIIIIlll, lIlIIIIIIllI, lIlIIIIIIlIl, lIlIIIIIIlII, lIlIIIIIIIll, lIlIIIIlIIlI, lIlIIIIlIIIl)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public BasicTriangulator() {
        this.poly = new PointList();
        this.tris = new PointList();
    }
    
    @Override
    public float[] getTrianglePoint(final int lIlIlIIIIIII, final int lIlIIlllllll) {
        if (!this.tried) {
            throw new RuntimeException("Call triangulate() before accessing triangles");
        }
        return this.tris.get(lIlIlIIIIIII * 3 + lIlIIlllllll).toArray();
    }
    
    @Override
    public boolean triangulate() {
        this.tried = true;
        final boolean lIlIlIIIllIl = this.process(this.poly, this.tris);
        return lIlIlIIIllIl;
    }
    
    private float area(final PointList lIlIIlllIIlI) {
        final int lIlIIlllIIIl = lIlIIlllIIlI.size();
        float lIlIIlllIIII = 0.0f;
        int lIlIIlllIlIl = lIlIIlllIIIl - 1;
        for (int lIlIIlllIlII = 0; lIlIIlllIlII < lIlIIlllIIIl; lIlIIlllIlIl = lIlIIlllIlII++) {
            final Point lIlIIlllIlll = lIlIIlllIIlI.get(lIlIIlllIlIl);
            final Point lIlIIlllIllI = lIlIIlllIIlI.get(lIlIIlllIlII);
            lIlIIlllIIII += lIlIIlllIlll.getX() * lIlIIlllIllI.getY() - lIlIIlllIllI.getX() * lIlIIlllIlll.getY();
        }
        return lIlIIlllIIII * 0.5f;
    }
    
    public float[] getPolyPoint(final int lIlIlIIlIIIl) {
        return new float[] { this.poly.get(lIlIlIIlIIIl).x, this.poly.get(lIlIlIIlIIIl).y };
    }
    
    @Override
    public void startHole() {
    }
    
    private boolean insideTriangle(final float lIlIIIlllIIl, final float lIlIIIlllIII, final float lIlIIIllIlll, final float lIlIIIllIllI, final float lIlIIlIIllII, final float lIlIIIllIlII, final float lIlIIIllIIll, final float lIlIIIllIIlI) {
        final float lIlIIlIIlIII = lIlIIlIIllII - lIlIIIllIlll;
        final float lIlIIlIIIlll = lIlIIIllIlII - lIlIIIllIllI;
        final float lIlIIlIIIllI = lIlIIIlllIIl - lIlIIlIIllII;
        final float lIlIIlIIIlIl = lIlIIIlllIII - lIlIIIllIlII;
        final float lIlIIlIIIlII = lIlIIIllIlll - lIlIIIlllIIl;
        final float lIlIIlIIIIll = lIlIIIllIllI - lIlIIIlllIII;
        final float lIlIIlIIIIlI = lIlIIIllIIll - lIlIIIlllIIl;
        final float lIlIIlIIIIIl = lIlIIIllIIlI - lIlIIIlllIII;
        final float lIlIIlIIIIII = lIlIIIllIIll - lIlIIIllIlll;
        final float lIlIIIllllll = lIlIIIllIIlI - lIlIIIllIllI;
        final float lIlIIIlllllI = lIlIIIllIIll - lIlIIlIIllII;
        final float lIlIIIllllIl = lIlIIIllIIlI - lIlIIIllIlII;
        final float lIlIIIlllIlI = lIlIIlIIlIII * lIlIIIllllll - lIlIIlIIIlll * lIlIIlIIIIII;
        final float lIlIIIllllII = lIlIIlIIIlII * lIlIIlIIIIIl - lIlIIlIIIIll * lIlIIlIIIIlI;
        final float lIlIIIlllIll = lIlIIlIIIllI * lIlIIIllllIl - lIlIIlIIIlIl * lIlIIIlllllI;
        return lIlIIIlllIlI >= 0.0f && lIlIIIlllIll >= 0.0f && lIlIIIllllII >= 0.0f;
    }
    
    private boolean process(final PointList lIIlllIlIllI, final PointList lIIlllIIlllI) {
        lIIlllIIlllI.clear();
        final int lIIlllIlIlII = lIIlllIlIllI.size();
        if (lIIlllIlIlII < 3) {
            return false;
        }
        final int[] lIIlllIlIIll = new int[lIIlllIlIlII];
        if (0.0f < this.area(lIIlllIlIllI)) {
            for (int lIIllllIIIlI = 0; lIIllllIIIlI < lIIlllIlIlII; ++lIIllllIIIlI) {
                lIIlllIlIIll[lIIllllIIIlI] = lIIllllIIIlI;
            }
        }
        else {
            for (int lIIllllIIIIl = 0; lIIllllIIIIl < lIIlllIlIlII; ++lIIllllIIIIl) {
                lIIlllIlIIll[lIIllllIIIIl] = lIIlllIlIlII - 1 - lIIllllIIIIl;
            }
        }
        int lIIlllIlIIlI = lIIlllIlIlII;
        int lIIlllIlIIIl = 2 * lIIlllIlIIlI;
        int lIIlllIllIIl = 0;
        int lIIlllIllIII = lIIlllIlIIlI - 1;
        while (lIIlllIlIIlI > 2) {
            if (0 >= lIIlllIlIIIl--) {
                return false;
            }
            int lIIlllIllIll = lIIlllIllIII;
            if (lIIlllIlIIlI <= lIIlllIllIll) {
                lIIlllIllIll = 0;
            }
            lIIlllIllIII = lIIlllIllIll + 1;
            if (lIIlllIlIIlI <= lIIlllIllIII) {
                lIIlllIllIII = 0;
            }
            int lIIlllIllIlI = lIIlllIllIII + 1;
            if (lIIlllIlIIlI <= lIIlllIllIlI) {
                lIIlllIllIlI = 0;
            }
            if (!this.snip(lIIlllIlIllI, lIIlllIllIll, lIIlllIllIII, lIIlllIllIlI, lIIlllIlIIlI, lIIlllIlIIll)) {
                continue;
            }
            final int lIIllllIIIII = lIIlllIlIIll[lIIlllIllIll];
            final int lIIlllIlllll = lIIlllIlIIll[lIIlllIllIII];
            final int lIIlllIllllI = lIIlllIlIIll[lIIlllIllIlI];
            lIIlllIIlllI.add(lIIlllIlIllI.get(lIIllllIIIII));
            lIIlllIIlllI.add(lIIlllIlIllI.get(lIIlllIlllll));
            lIIlllIIlllI.add(lIIlllIlIllI.get(lIIlllIllllI));
            ++lIIlllIllIIl;
            int lIIlllIlllIl = lIIlllIllIII;
            for (int lIIlllIlllII = lIIlllIllIII + 1; lIIlllIlllII < lIIlllIlIIlI; ++lIIlllIlllII) {
                lIIlllIlIIll[lIIlllIlllIl] = lIIlllIlIIll[lIIlllIlllII];
                ++lIIlllIlllIl;
            }
            --lIIlllIlIIlI;
            lIIlllIlIIIl = 2 * lIIlllIlIIlI;
        }
        return true;
    }
    
    @Override
    public int getTriangleCount() {
        if (!this.tried) {
            throw new RuntimeException("Call triangulate() before accessing triangles");
        }
        return this.tris.size() / 3;
    }
    
    @Override
    public void addPolyPoint(final float lIlIlIIlllII, final float lIlIlIIllIll) {
        final Point lIlIlIIllllI = new Point(lIlIlIIlllII, lIlIlIIllIll);
        if (!this.poly.contains(lIlIlIIllllI)) {
            this.poly.add(lIlIlIIllllI);
        }
    }
    
    private class Point
    {
        private /* synthetic */ float y;
        private /* synthetic */ float[] array;
        private /* synthetic */ float x;
        
        @Override
        public int hashCode() {
            return (int)(this.x * this.y * 31.0f);
        }
        
        public float[] toArray() {
            return this.array;
        }
        
        public float getY() {
            return this.y;
        }
        
        @Override
        public boolean equals(final Object lIIllIIIIIIllIl) {
            if (lIIllIIIIIIllIl instanceof Point) {
                final Point lIIllIIIIIlIIlI = (Point)lIIllIIIIIIllIl;
                return lIIllIIIIIlIIlI.x == this.x && lIIllIIIIIlIIlI.y == this.y;
            }
            return false;
        }
        
        public Point(final float lIIllIIIIllllll, final float lIIllIIIIlllIlI) {
            this.x = lIIllIIIIllllll;
            this.y = lIIllIIIIlllIlI;
            this.array = new float[] { lIIllIIIIllllll, lIIllIIIIlllIlI };
        }
        
        public float getX() {
            return this.x;
        }
    }
    
    private class PointList
    {
        private /* synthetic */ ArrayList points;
        
        public void clear() {
            this.points.clear();
        }
        
        public void remove(final Point lllllllllllllllllIIIIIllIIIllllI) {
            this.points.remove(lllllllllllllllllIIIIIllIIIllllI);
        }
        
        public int size() {
            return this.points.size();
        }
        
        public boolean contains(final Point lllllllllllllllllIIIIIllIIlIlIII) {
            return this.points.contains(lllllllllllllllllIIIIIllIIlIlIII);
        }
        
        public PointList() {
            this.points = new ArrayList();
        }
        
        public void add(final Point lllllllllllllllllIIIIIllIIlIIlII) {
            this.points.add(lllllllllllllllllIIIIIllIIlIIlII);
        }
        
        public Point get(final int lllllllllllllllllIIIIIllIIIlIlIl) {
            return this.points.get(lllllllllllllllllIIIIIllIIIlIlIl);
        }
    }
}
