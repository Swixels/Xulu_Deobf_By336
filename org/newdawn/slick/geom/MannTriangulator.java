package org.newdawn.slick.geom;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class MannTriangulator implements Triangulator
{
    private /* synthetic */ Point nextFreePoint;
    protected /* synthetic */ PointBag contour;
    private /* synthetic */ PointBag nextFreePointBag;
    protected /* synthetic */ PointBag holes;
    private /* synthetic */ List triangles;
    
    @Override
    public boolean triangulate() {
        final Vector2f[] lIIIIlIIIlllII = this.triangulate(new Vector2f[0]);
        for (int lIIIIlIIIllllI = 0; lIIIIlIIIllllI < lIIIIlIIIlllII.length && lIIIIlIIIlllII[lIIIIlIIIllllI] != null; ++lIIIIlIIIllllI) {
            this.triangles.add(lIIIIlIIIlllII[lIIIIlIIIllllI]);
        }
        return true;
    }
    
    private Vector2f[] triangulate(Vector2f[] lIIIIlIlIIllII) {
        this.contour.computeAngles();
        for (PointBag lIIIIlIlIlllIl = this.holes; lIIIIlIlIlllIl != null; lIIIIlIlIlllIl = lIIIIlIlIlllIl.next) {
            lIIIIlIlIlllIl.computeAngles();
        }
        while (this.holes != null) {
            Point lIIIIlIlIllIII = this.holes.first;
        Label_0247:
            do {
                if (lIIIIlIlIllIII.angle <= 0.0) {
                    Point lIIIIlIlIllIIl = this.contour.first;
                    do {
                        if (lIIIIlIlIllIII.isInfront(lIIIIlIlIllIIl) && lIIIIlIlIllIIl.isInfront(lIIIIlIlIllIII) && !this.contour.doesIntersectSegment(lIIIIlIlIllIII.pt, lIIIIlIlIllIIl.pt)) {
                            PointBag lIIIIlIlIlllII = this.holes;
                            while (!lIIIIlIlIlllII.doesIntersectSegment(lIIIIlIlIllIII.pt, lIIIIlIlIllIIl.pt)) {
                                if ((lIIIIlIlIlllII = lIIIIlIlIlllII.next) == null) {
                                    final Point lIIIIlIlIllIll = this.getPoint(lIIIIlIlIllIIl.pt);
                                    lIIIIlIlIllIIl.insertAfter(lIIIIlIlIllIll);
                                    final Point lIIIIlIlIllIlI = this.getPoint(lIIIIlIlIllIII.pt);
                                    lIIIIlIlIllIII.insertBefore(lIIIIlIlIllIlI);
                                    lIIIIlIlIllIIl.next = lIIIIlIlIllIII;
                                    lIIIIlIlIllIII.prev = lIIIIlIlIllIIl;
                                    lIIIIlIlIllIlI.next = lIIIIlIlIllIll;
                                    lIIIIlIlIllIll.prev = lIIIIlIlIllIlI;
                                    lIIIIlIlIllIIl.computeAngle();
                                    lIIIIlIlIllIII.computeAngle();
                                    lIIIIlIlIllIll.computeAngle();
                                    lIIIIlIlIllIlI.computeAngle();
                                    this.holes.first = null;
                                    break Label_0247;
                                }
                            }
                        }
                    } while ((lIIIIlIlIllIIl = lIIIIlIlIllIIl.next) != this.contour.first);
                }
            } while ((lIIIIlIlIllIII = lIIIIlIlIllIII.next) != this.holes.first);
            this.holes = this.freePointBag(this.holes);
        }
        final int lIIIIlIlIlIIII = this.contour.countPoints() - 2;
        final int lIIIIlIlIIllll = lIIIIlIlIlIIII * 3 + 1;
        if (lIIIIlIlIIllII.length < lIIIIlIlIIllll) {
            lIIIIlIlIIllII = (Vector2f[])Array.newInstance(lIIIIlIlIIllII.getClass().getComponentType(), lIIIIlIlIIllll);
        }
        int lIIIIlIlIIlllI = 0;
        while (true) {
            Point lIIIIlIlIlIlIl = this.contour.first;
            if (lIIIIlIlIlIlIl == null) {
                break;
            }
            if (lIIIIlIlIlIlIl.next == lIIIIlIlIlIlIl.prev) {
                break;
            }
            do {
                if (lIIIIlIlIlIlIl.angle > 0.0) {
                    final Point lIIIIlIlIlIlll = lIIIIlIlIlIlIl.prev;
                    final Point lIIIIlIlIlIllI = lIIIIlIlIlIlIl.next;
                    if ((lIIIIlIlIlIllI.next == lIIIIlIlIlIlll || (lIIIIlIlIlIlll.isInfront(lIIIIlIlIlIllI) && lIIIIlIlIlIllI.isInfront(lIIIIlIlIlIlll))) && !this.contour.doesIntersectSegment(lIIIIlIlIlIlll.pt, lIIIIlIlIlIllI.pt)) {
                        lIIIIlIlIIllII[lIIIIlIlIIlllI++] = lIIIIlIlIlIlIl.pt;
                        lIIIIlIlIIllII[lIIIIlIlIIlllI++] = lIIIIlIlIlIllI.pt;
                        lIIIIlIlIIllII[lIIIIlIlIIlllI++] = lIIIIlIlIlIlll.pt;
                        break;
                    }
                    continue;
                }
            } while ((lIIIIlIlIlIlIl = lIIIIlIlIlIlIl.next) != this.contour.first);
            final Point lIIIIlIlIlIlII = lIIIIlIlIlIlIl.prev;
            final Point lIIIIlIlIlIIll = lIIIIlIlIlIlIl.next;
            this.contour.first = lIIIIlIlIlIlII;
            lIIIIlIlIlIlIl.unlink();
            this.freePoint(lIIIIlIlIlIlIl);
            lIIIIlIlIlIIll.computeAngle();
            lIIIIlIlIlIlII.computeAngle();
        }
        lIIIIlIlIIllII[lIIIIlIlIIlllI] = null;
        this.contour.clear();
        return lIIIIlIlIIllII;
    }
    
    private void addPoint(final Vector2f lIIIIlIllIIlll) {
        if (this.holes == null) {
            final Point lIIIIlIllIllII = this.getPoint(lIIIIlIllIIlll);
            this.contour.add(lIIIIlIllIllII);
        }
        else {
            final Point lIIIIlIllIlIll = this.getPoint(lIIIIlIllIIlll);
            this.holes.add(lIIIIlIllIlIll);
        }
    }
    
    public MannTriangulator() {
        this.triangles = new ArrayList();
        this.contour = this.getPointBag();
    }
    
    private PointBag getPointBag() {
        final PointBag lIIIIlIlIIIIlI = this.nextFreePointBag;
        if (lIIIIlIlIIIIlI != null) {
            this.nextFreePointBag = lIIIIlIlIIIIlI.next;
            lIIIIlIlIIIIlI.next = null;
            return lIIIIlIlIIIIlI;
        }
        return new PointBag();
    }
    
    @Override
    public int getTriangleCount() {
        return this.triangles.size() / 3;
    }
    
    public void reset() {
        while (this.holes != null) {
            this.holes = this.freePointBag(this.holes);
        }
        this.contour.clear();
        this.holes = null;
    }
    
    private void freePoints(final Point lIIIIlIIlIIlII) {
        lIIIIlIIlIIlII.prev.next = this.nextFreePoint;
        lIIIIlIIlIIlII.prev = null;
        this.nextFreePoint = lIIIIlIIlIIlII;
    }
    
    @Override
    public float[] getTrianglePoint(final int lIIIIIllllllll, final int lIIIIlIIIIIlIl) {
        final Vector2f lIIIIlIIIIIIll = this.triangles.get(lIIIIIllllllll * 3 + lIIIIlIIIIIlIl);
        return new float[] { lIIIIlIIIIIIll.x, lIIIIlIIIIIIll.y };
    }
    
    @Override
    public void addPolyPoint(final float lIIIIlIlllllIl, final float lIIIIlIllllIIl) {
        this.addPoint(new Vector2f(lIIIIlIlllllIl, lIIIIlIllllIIl));
    }
    
    private Point getPoint(final Vector2f lIIIIlIIlIllll) {
        final Point lIIIIlIIllIIIl = this.nextFreePoint;
        if (lIIIIlIIllIIIl != null) {
            this.nextFreePoint = lIIIIlIIllIIIl.next;
            lIIIIlIIllIIIl.next = null;
            lIIIIlIIllIIIl.prev = null;
            lIIIIlIIllIIIl.pt = lIIIIlIIlIllll;
            return lIIIIlIIllIIIl;
        }
        return new Point(lIIIIlIIlIllll);
    }
    
    private void freePoint(final Point lIIIIlIIlIlIII) {
        lIIIIlIIlIlIII.next = this.nextFreePoint;
        this.nextFreePoint = lIIIIlIIlIlIII;
    }
    
    private PointBag freePointBag(final PointBag lIIIIlIIlllIII) {
        final PointBag lIIIIlIIlllIlI = lIIIIlIIlllIII.next;
        lIIIIlIIlllIII.clear();
        lIIIIlIIlllIII.next = this.nextFreePointBag;
        this.nextFreePointBag = lIIIIlIIlllIII;
        return lIIIIlIIlllIlI;
    }
    
    @Override
    public void startHole() {
        final PointBag lIIIIlIlllIIlI = this.getPointBag();
        lIIIIlIlllIIlI.next = this.holes;
        this.holes = lIIIIlIlllIIlI;
    }
    
    private static class Point implements Serializable
    {
        protected /* synthetic */ double nx;
        protected /* synthetic */ Vector2f pt;
        protected /* synthetic */ Point prev;
        protected /* synthetic */ double angle;
        protected /* synthetic */ Point next;
        protected /* synthetic */ double ny;
        
        public void computeAngle() {
            if (this.prev.pt.equals(this.pt)) {
                final Vector2f pt = this.pt;
                pt.x += 0.01f;
            }
            double lllllllllllllllllllIIIlIIlllIIII = this.pt.x - this.prev.pt.x;
            double lllllllllllllllllllIIIlIIllIllll = this.pt.y - this.prev.pt.y;
            final double lllllllllllllllllllIIIlIIllIlllI = this.hypot(lllllllllllllllllllIIIlIIlllIIII, lllllllllllllllllllIIIlIIllIllll);
            lllllllllllllllllllIIIlIIlllIIII /= lllllllllllllllllllIIIlIIllIlllI;
            lllllllllllllllllllIIIlIIllIllll /= lllllllllllllllllllIIIlIIllIlllI;
            if (this.next.pt.equals(this.pt)) {
                final Vector2f pt2 = this.pt;
                pt2.y += 0.01f;
            }
            double lllllllllllllllllllIIIlIIllIllIl = this.next.pt.x - this.pt.x;
            double lllllllllllllllllllIIIlIIllIllII = this.next.pt.y - this.pt.y;
            final double lllllllllllllllllllIIIlIIllIlIll = this.hypot(lllllllllllllllllllIIIlIIllIllIl, lllllllllllllllllllIIIlIIllIllII);
            lllllllllllllllllllIIIlIIllIllIl /= lllllllllllllllllllIIIlIIllIlIll;
            lllllllllllllllllllIIIlIIllIllII /= lllllllllllllllllllIIIlIIllIlIll;
            final double lllllllllllllllllllIIIlIIllIlIlI = -lllllllllllllllllllIIIlIIllIllll;
            final double lllllllllllllllllllIIIlIIllIlIIl = lllllllllllllllllllIIIlIIlllIIII;
            this.nx = (lllllllllllllllllllIIIlIIllIlIlI - lllllllllllllllllllIIIlIIllIllII) * 0.5;
            this.ny = (lllllllllllllllllllIIIlIIllIlIIl + lllllllllllllllllllIIIlIIllIllIl) * 0.5;
            if (this.nx * this.nx + this.ny * this.ny < 1.0E-5) {
                this.nx = lllllllllllllllllllIIIlIIlllIIII;
                this.ny = lllllllllllllllllllIIIlIIllIllII;
                this.angle = 1.0;
                if (lllllllllllllllllllIIIlIIlllIIII * lllllllllllllllllllIIIlIIllIllIl + lllllllllllllllllllIIIlIIllIllll * lllllllllllllllllllIIIlIIllIllII > 0.0) {
                    this.nx = -lllllllllllllllllllIIIlIIlllIIII;
                    this.ny = -lllllllllllllllllllIIIlIIllIllll;
                }
            }
            else {
                this.angle = this.nx * lllllllllllllllllllIIIlIIllIllIl + this.ny * lllllllllllllllllllIIIlIIllIllII;
            }
        }
        
        public void unlink() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.next = null;
            this.prev = null;
        }
        
        public double getAngle(final Point lllllllllllllllllllIIIlIIlIlIlII) {
            final double lllllllllllllllllllIIIlIIlIllIII = lllllllllllllllllllIIIlIIlIlIlII.pt.x - this.pt.x;
            final double lllllllllllllllllllIIIlIIlIlIlll = lllllllllllllllllllIIIlIIlIlIlII.pt.y - this.pt.y;
            final double lllllllllllllllllllIIIlIIlIlIllI = this.hypot(lllllllllllllllllllIIIlIIlIllIII, lllllllllllllllllllIIIlIIlIlIlll);
            return (this.nx * lllllllllllllllllllIIIlIIlIllIII + this.ny * lllllllllllllllllllIIIlIIlIlIlll) / lllllllllllllllllllIIIlIIlIlIllI;
        }
        
        public Point(final Vector2f lllllllllllllllllllIIIlIlIIlIIIl) {
            this.pt = lllllllllllllllllllIIIlIlIIlIIIl;
        }
        
        public void insertBefore(final Point lllllllllllllllllllIIIlIlIIIlIII) {
            this.prev.next = lllllllllllllllllllIIIlIlIIIlIII;
            lllllllllllllllllllIIIlIlIIIlIII.prev = this.prev;
            lllllllllllllllllllIIIlIlIIIlIII.next = this;
            this.prev = lllllllllllllllllllIIIlIlIIIlIII;
        }
        
        public boolean isConcave() {
            return this.angle < 0.0;
        }
        
        private double hypot(final double lllllllllllllllllllIIIlIIlllllII, final double lllllllllllllllllllIIIlIIlllllIl) {
            return Math.sqrt(lllllllllllllllllllIIIlIIlllllII * lllllllllllllllllllIIIlIIlllllII + lllllllllllllllllllIIIlIIlllllIl * lllllllllllllllllllIIIlIIlllllIl);
        }
        
        public void insertAfter(final Point lllllllllllllllllllIIIlIlIIIIIlI) {
            this.next.prev = lllllllllllllllllllIIIlIlIIIIIlI;
            lllllllllllllllllllIIIlIlIIIIIlI.prev = this;
            lllllllllllllllllllIIIlIlIIIIIlI.next = this.next;
            this.next = lllllllllllllllllllIIIlIlIIIIIlI;
        }
        
        public boolean isInfront(final double lllllllllllllllllllIIIlIIlIIIlll, final double lllllllllllllllllllIIIlIIlIIIllI) {
            final boolean lllllllllllllllllllIIIlIIlIIIlIl = (this.prev.pt.y - this.pt.y) * lllllllllllllllllllIIIlIIlIIIlll + (this.pt.x - this.prev.pt.x) * lllllllllllllllllllIIIlIIlIIIllI >= 0.0;
            final boolean lllllllllllllllllllIIIlIIlIIIlII = (this.pt.y - this.next.pt.y) * lllllllllllllllllllIIIlIIlIIIlll + (this.next.pt.x - this.pt.x) * lllllllllllllllllllIIIlIIlIIIllI >= 0.0;
            return (this.angle < 0.0) ? (lllllllllllllllllllIIIlIIlIIIlIl | lllllllllllllllllllIIIlIIlIIIlII) : (lllllllllllllllllllIIIlIIlIIIlIl & lllllllllllllllllllIIIlIIlIIIlII);
        }
        
        public boolean isInfront(final Point lllllllllllllllllllIIIlIIIlllIll) {
            return this.isInfront(lllllllllllllllllllIIIlIIIlllIll.pt.x - this.pt.x, lllllllllllllllllllIIIlIIIlllIll.pt.y - this.pt.y);
        }
    }
    
    protected class PointBag implements Serializable
    {
        protected /* synthetic */ Point first;
        protected /* synthetic */ PointBag next;
        
        public int countPoints() {
            if (this.first == null) {
                return 0;
            }
            int lllllllllllllllllIIlllIlIllllIIl = 0;
            Point lllllllllllllllllIIlllIlIllllIII = this.first;
            do {
                ++lllllllllllllllllIIlllIlIllllIIl;
            } while ((lllllllllllllllllIIlllIlIllllIII = lllllllllllllllllIIlllIlIllllIII.next) != this.first);
            return lllllllllllllllllIIlllIlIllllIIl;
        }
        
        public void clear() {
            if (this.first != null) {
                MannTriangulator.this.freePoints(this.first);
                this.first = null;
            }
        }
        
        public boolean contains(final Vector2f lllllllllllllllllIIlllIlIlllIIIl) {
            return this.first != null && (this.first.prev.pt.equals(lllllllllllllllllIIlllIlIlllIIIl) || this.first.pt.equals(lllllllllllllllllIIlllIlIlllIIIl));
        }
        
        public boolean doesIntersectSegment(final Vector2f lllllllllllllllllIIlllIllIIIlIlI, final Vector2f lllllllllllllllllIIlllIllIIIlIIl) {
            final double lllllllllllllllllIIlllIllIIIllIl = lllllllllllllllllIIlllIllIIIlIIl.x - lllllllllllllllllIIlllIllIIIlIlI.x;
            final double lllllllllllllllllIIlllIllIIIllII = lllllllllllllllllIIlllIllIIIlIIl.y - lllllllllllllllllIIlllIllIIIlIlI.y;
            Point lllllllllllllllllIIlllIllIIlIIIl = this.first;
            while (true) {
                final Point lllllllllllllllllIIlllIllIIlIIlI = lllllllllllllllllIIlllIllIIlIIIl.next;
                if (lllllllllllllllllIIlllIllIIlIIIl.pt != lllllllllllllllllIIlllIllIIIlIlI && lllllllllllllllllIIlllIllIIlIIlI.pt != lllllllllllllllllIIlllIllIIIlIlI && lllllllllllllllllIIlllIllIIlIIIl.pt != lllllllllllllllllIIlllIllIIIlIIl && lllllllllllllllllIIlllIllIIlIIlI.pt != lllllllllllllllllIIlllIllIIIlIIl) {
                    final double lllllllllllllllllIIlllIllIIlIlIl = lllllllllllllllllIIlllIllIIlIIlI.pt.x - lllllllllllllllllIIlllIllIIlIIIl.pt.x;
                    final double lllllllllllllllllIIlllIllIIlIlII = lllllllllllllllllIIlllIllIIlIIlI.pt.y - lllllllllllllllllIIlllIllIIlIIIl.pt.y;
                    final double lllllllllllllllllIIlllIllIIlIIll = lllllllllllllllllIIlllIllIIIllIl * lllllllllllllllllIIlllIllIIlIlII - lllllllllllllllllIIlllIllIIIllII * lllllllllllllllllIIlllIllIIlIlIl;
                    if (Math.abs(lllllllllllllllllIIlllIllIIlIIll) > 1.0E-5) {
                        final double lllllllllllllllllIIlllIllIIllIIl = lllllllllllllllllIIlllIllIIlIIIl.pt.x - lllllllllllllllllIIlllIllIIIlIlI.x;
                        final double lllllllllllllllllIIlllIllIIllIII = lllllllllllllllllIIlllIllIIlIIIl.pt.y - lllllllllllllllllIIlllIllIIIlIlI.y;
                        final double lllllllllllllllllIIlllIllIIlIlll = (lllllllllllllllllIIlllIllIIlIlII * lllllllllllllllllIIlllIllIIllIIl - lllllllllllllllllIIlllIllIIlIlIl * lllllllllllllllllIIlllIllIIllIII) / lllllllllllllllllIIlllIllIIlIIll;
                        final double lllllllllllllllllIIlllIllIIlIllI = (lllllllllllllllllIIlllIllIIIllII * lllllllllllllllllIIlllIllIIllIIl - lllllllllllllllllIIlllIllIIIllIl * lllllllllllllllllIIlllIllIIllIII) / lllllllllllllllllIIlllIllIIlIIll;
                        if (lllllllllllllllllIIlllIllIIlIlll >= 0.0 && lllllllllllllllllIIlllIllIIlIlll <= 1.0 && lllllllllllllllllIIlllIllIIlIllI >= 0.0 && lllllllllllllllllIIlllIllIIlIllI <= 1.0) {
                            return true;
                        }
                    }
                }
                if (lllllllllllllllllIIlllIllIIlIIlI == this.first) {
                    return false;
                }
                lllllllllllllllllIIlllIllIIlIIIl = lllllllllllllllllIIlllIllIIlIIlI;
            }
        }
        
        public void add(final Point lllllllllllllllllIIlllIllIlIlllI) {
            if (this.first != null) {
                this.first.insertBefore(lllllllllllllllllIIlllIllIlIlllI);
            }
            else {
                this.first = lllllllllllllllllIIlllIllIlIlllI;
                lllllllllllllllllIIlllIllIlIlllI.next = lllllllllllllllllIIlllIllIlIlllI;
                lllllllllllllllllIIlllIllIlIlllI.prev = lllllllllllllllllIIlllIllIlIlllI;
            }
        }
        
        public void computeAngles() {
            if (this.first == null) {
                return;
            }
            Point lllllllllllllllllIIlllIllIlIlIlI = this.first;
            do {
                lllllllllllllllllIIlllIllIlIlIlI.computeAngle();
            } while ((lllllllllllllllllIIlllIllIlIlIlI = lllllllllllllllllIIlllIllIlIlIlI.next) != this.first);
        }
    }
}
