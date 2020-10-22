package org.newdawn.slick.geom;

import java.io.*;

public abstract class Shape implements Serializable
{
    protected /* synthetic */ float maxX;
    protected /* synthetic */ float minY;
    protected /* synthetic */ float x;
    protected /* synthetic */ float maxY;
    protected /* synthetic */ float y;
    protected /* synthetic */ float[] points;
    protected /* synthetic */ boolean trianglesDirty;
    protected /* synthetic */ float boundingCircleRadius;
    protected /* synthetic */ float minX;
    protected /* synthetic */ boolean pointsDirty;
    protected /* synthetic */ float[] center;
    protected transient /* synthetic */ Triangulator tris;
    
    public void setCenterX(final float llllllllllllllllIlllIIIIIIIlIllI) {
        if (this.points == null || this.center == null) {
            this.checkPoints();
        }
        final float llllllllllllllllIlllIIIIIIIllIII = llllllllllllllllIlllIIIIIIIlIllI - this.getCenterX();
        this.setX(this.x + llllllllllllllllIlllIIIIIIIllIII);
    }
    
    public Shape[] subtract(final Shape llllllllllllllllIllIlllIllIIlIlI) {
        return new GeomUtil().subtract(this, llllllllllllllllIllIlllIllIIlIlI);
    }
    
    public boolean hasVertex(final float llllllllllllllllIllIllllIIlIlllI, final float llllllllllllllllIllIllllIIllIIII) {
        if (this.points.length == 0) {
            return false;
        }
        this.checkPoints();
        for (int llllllllllllllllIllIllllIIllIIll = 0; llllllllllllllllIllIllllIIllIIll < this.points.length; llllllllllllllllIllIllllIIllIIll += 2) {
            if (this.points[llllllllllllllllIllIllllIIllIIll] == llllllllllllllllIllIllllIIlIlllI && this.points[llllllllllllllllIllIllllIIllIIll + 1] == llllllllllllllllIllIllllIIllIIII) {
                return true;
            }
        }
        return false;
    }
    
    protected void calculateTriangles() {
        if (!this.trianglesDirty && this.tris != null) {
            return;
        }
        if (this.points.length >= 6) {
            boolean llllllllllllllllIllIllllIIIIlIll = true;
            float llllllllllllllllIllIllllIIIIlIlI = 0.0f;
            for (int llllllllllllllllIllIllllIIIIllIl = 0; llllllllllllllllIllIllllIIIIllIl < this.points.length / 2 - 1; ++llllllllllllllllIllIllllIIIIllIl) {
                final float llllllllllllllllIllIllllIIIlIIIl = this.points[llllllllllllllllIllIllllIIIIllIl * 2];
                final float llllllllllllllllIllIllllIIIlIIII = this.points[llllllllllllllllIllIllllIIIIllIl * 2 + 1];
                final float llllllllllllllllIllIllllIIIIllll = this.points[llllllllllllllllIllIllllIIIIllIl * 2 + 2];
                final float llllllllllllllllIllIllllIIIIlllI = this.points[llllllllllllllllIllIllllIIIIllIl * 2 + 3];
                llllllllllllllllIllIllllIIIIlIlI += llllllllllllllllIllIllllIIIlIIIl * llllllllllllllllIllIllllIIIIlllI - llllllllllllllllIllIllllIIIlIIII * llllllllllllllllIllIllllIIIIllll;
            }
            llllllllllllllllIllIllllIIIIlIlI /= 2.0f;
            llllllllllllllllIllIllllIIIIlIll = (llllllllllllllllIllIllllIIIIlIlI > 0.0f);
            this.tris = new NeatTriangulator();
            for (int llllllllllllllllIllIllllIIIIllII = 0; llllllllllllllllIllIllllIIIIllII < this.points.length; llllllllllllllllIllIllllIIIIllII += 2) {
                this.tris.addPolyPoint(this.points[llllllllllllllllIllIllllIIIIllII], this.points[llllllllllllllllIllIllllIIIIllII + 1]);
            }
            this.tris.triangulate();
        }
        this.trianglesDirty = false;
    }
    
    public boolean closed() {
        return true;
    }
    
    public float getCenterX() {
        this.checkPoints();
        return this.center[0];
    }
    
    public boolean intersects(final Shape llllllllllllllllIllIllllIlIIIIll) {
        this.checkPoints();
        boolean llllllllllllllllIllIllllIlIIlIIl = false;
        final float[] llllllllllllllllIllIllllIlIIlIII = this.getPoints();
        final float[] llllllllllllllllIllIllllIlIIIlll = llllllllllllllllIllIllllIlIIIIll.getPoints();
        int llllllllllllllllIllIllllIlIIIllI = llllllllllllllllIllIllllIlIIlIII.length;
        int llllllllllllllllIllIllllIlIIIlIl = llllllllllllllllIllIllllIlIIIlll.length;
        if (!this.closed()) {
            llllllllllllllllIllIllllIlIIIllI -= 2;
        }
        if (!llllllllllllllllIllIllllIlIIIIll.closed()) {
            llllllllllllllllIllIllllIlIIIlIl -= 2;
        }
        for (int llllllllllllllllIllIllllIlIIllII = 0; llllllllllllllllIllIllllIlIIllII < llllllllllllllllIllIllllIlIIIllI; llllllllllllllllIllIllllIlIIllII += 2) {
            int llllllllllllllllIllIllllIlIIllIl = llllllllllllllllIllIllllIlIIllII + 2;
            if (llllllllllllllllIllIllllIlIIllIl >= llllllllllllllllIllIllllIlIIlIII.length) {
                llllllllllllllllIllIllllIlIIllIl = 0;
            }
            for (int llllllllllllllllIllIllllIlIIlllI = 0; llllllllllllllllIllIllllIlIIlllI < llllllllllllllllIllIllllIlIIIlIl; llllllllllllllllIllIllllIlIIlllI += 2) {
                int llllllllllllllllIllIllllIlIlIIIl = llllllllllllllllIllIllllIlIIlllI + 2;
                if (llllllllllllllllIllIllllIlIlIIIl >= llllllllllllllllIllIllllIlIIIlll.length) {
                    llllllllllllllllIllIllllIlIlIIIl = 0;
                }
                final double llllllllllllllllIllIllllIlIlIIII = ((llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllIl] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII]) * (double)(llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI + 1] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII + 1]) - (llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllIl + 1] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII + 1]) * (llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII])) / ((llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllIl + 1] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII + 1]) * (llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIlIIIl] - llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI]) - (llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllIl] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII]) * (llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIlIIIl + 1] - llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI + 1]));
                final double llllllllllllllllIllIllllIlIIllll = ((llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIlIIIl] - llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI]) * (double)(llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI + 1] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII + 1]) - (llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIlIIIl + 1] - llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI + 1]) * (llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII])) / ((llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllIl + 1] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII + 1]) * (llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIlIIIl] - llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI]) - (llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllIl] - llllllllllllllllIllIllllIlIIlIII[llllllllllllllllIllIllllIlIIllII]) * (llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIlIIIl + 1] - llllllllllllllllIllIllllIlIIIlll[llllllllllllllllIllIllllIlIIlllI + 1]));
                if (llllllllllllllllIllIllllIlIlIIII >= 0.0 && llllllllllllllllIllIllllIlIlIIII <= 1.0 && llllllllllllllllIllIllllIlIIllll >= 0.0 && llllllllllllllllIllIllllIlIIllll <= 1.0) {
                    llllllllllllllllIllIllllIlIIlIIl = true;
                    break;
                }
            }
            if (llllllllllllllllIllIllllIlIIlIIl) {
                break;
            }
        }
        return llllllllllllllllIllIllllIlIIlIIl;
    }
    
    public Vector2f getLocation() {
        return new Vector2f(this.getX(), this.getY());
    }
    
    public float[] getPoints() {
        this.checkPoints();
        return this.points;
    }
    
    public void setLocation(final float llllllllllllllllIlllIIIIIlIIlIIl, final float llllllllllllllllIlllIIIIIlIIlIll) {
        this.setX(llllllllllllllllIlllIIIIIlIIlIIl);
        this.setY(llllllllllllllllIlllIIIIIlIIlIll);
    }
    
    public float[] getCenter() {
        this.checkPoints();
        return this.center;
    }
    
    public void setCenterY(final float llllllllllllllllIlllIIIIIIIIllIl) {
        if (this.points == null || this.center == null) {
            this.checkPoints();
        }
        final float llllllllllllllllIlllIIIIIIIIllII = llllllllllllllllIlllIIIIIIIIllIl - this.getCenterY();
        this.setY(this.y + llllllllllllllllIlllIIIIIIIIllII);
    }
    
    public void setY(float llllllllllllllllIlllIIIIIIlIllll) {
        if (llllllllllllllllIlllIIIIIIlIllll != this.y) {
            final float llllllllllllllllIlllIIIIIIllIIll = llllllllllllllllIlllIIIIIIlIllll - this.y;
            this.y = llllllllllllllllIlllIIIIIIlIllll;
            if (this.points == null || this.center == null) {
                this.checkPoints();
            }
            for (int llllllllllllllllIlllIIIIIIllIlII = 0; llllllllllllllllIlllIIIIIIllIlII < this.points.length / 2; ++llllllllllllllllIlllIIIIIIllIlII) {
                final float[] points = this.points;
                final int n = llllllllllllllllIlllIIIIIIllIlII * 2 + 1;
                points[n] += llllllllllllllllIlllIIIIIIllIIll;
            }
            final float[] center = this.center;
            final int n2 = 1;
            center[n2] += llllllllllllllllIlllIIIIIIllIIll;
            llllllllllllllllIlllIIIIIIlIllll += llllllllllllllllIlllIIIIIIllIIll;
            this.maxY += llllllllllllllllIlllIIIIIIllIIll;
            this.minY += llllllllllllllllIlllIIIIIIllIIll;
            this.trianglesDirty = true;
        }
    }
    
    public float getMaxX() {
        this.checkPoints();
        return this.maxX;
    }
    
    public boolean contains(final float llllllllllllllllIllIllllIllIlIll, final float llllllllllllllllIllIllllIllIlIlI) {
        this.checkPoints();
        if (this.points.length == 0) {
            return false;
        }
        boolean llllllllllllllllIllIllllIlllIIII = false;
        final int llllllllllllllllIllIllllIllIllIl = this.points.length;
        float llllllllllllllllIllIllllIllIllll = this.points[llllllllllllllllIllIllllIllIllIl - 2];
        float llllllllllllllllIllIllllIllIlllI = this.points[llllllllllllllllIllIllllIllIllIl - 1];
        for (int llllllllllllllllIllIllllIlllIlII = 0; llllllllllllllllIllIllllIlllIlII < llllllllllllllllIllIllllIllIllIl; llllllllllllllllIllIllllIlllIlII += 2) {
            final float llllllllllllllllIllIllllIllllIlI = this.points[llllllllllllllllIllIllllIlllIlII];
            final float llllllllllllllllIllIllllIllllIIl = this.points[llllllllllllllllIllIllllIlllIlII + 1];
            float llllllllllllllllIllIllllIllllIII = 0.0f;
            float llllllllllllllllIllIllllIlllIllI = 0.0f;
            float llllllllllllllllIllIllllIlllIlll = 0.0f;
            float llllllllllllllllIllIllllIlllIlIl = 0.0f;
            if (llllllllllllllllIllIllllIllllIlI > llllllllllllllllIllIllllIllIllll) {
                final float llllllllllllllllIllIllllIllllllI = llllllllllllllllIllIllllIllIllll;
                final float llllllllllllllllIllIllllIlllllII = llllllllllllllllIllIllllIllllIlI;
                final float llllllllllllllllIllIllllIlllllIl = llllllllllllllllIllIllllIllIlllI;
                final float llllllllllllllllIllIllllIllllIll = llllllllllllllllIllIllllIllllIIl;
            }
            else {
                llllllllllllllllIllIllllIllllIII = llllllllllllllllIllIllllIllllIlI;
                llllllllllllllllIllIllllIlllIllI = llllllllllllllllIllIllllIllIllll;
                llllllllllllllllIllIllllIlllIlll = llllllllllllllllIllIllllIllllIIl;
                llllllllllllllllIllIllllIlllIlIl = llllllllllllllllIllIllllIllIlllI;
            }
            if (llllllllllllllllIllIllllIllllIlI < llllllllllllllllIllIllllIllIlIll == llllllllllllllllIllIllllIllIlIll <= llllllllllllllllIllIllllIllIllll && (llllllllllllllllIllIllllIllIlIlI - (double)llllllllllllllllIllIllllIlllIlll) * (llllllllllllllllIllIllllIlllIllI - llllllllllllllllIllIllllIllllIII) < (llllllllllllllllIllIllllIlllIlIl - (double)llllllllllllllllIllIllllIlllIlll) * (llllllllllllllllIllIllllIllIlIll - llllllllllllllllIllIllllIllllIII)) {
                llllllllllllllllIllIllllIlllIIII = !llllllllllllllllIllIllllIlllIIII;
            }
            llllllllllllllllIllIllllIllIllll = llllllllllllllllIllIllllIllllIlI;
            llllllllllllllllIllIllllIllIlllI = llllllllllllllllIllIllllIllllIIl;
        }
        return llllllllllllllllIllIllllIlllIIII;
    }
    
    protected void calculateRadius() {
        this.boundingCircleRadius = 0.0f;
        for (int llllllllllllllllIllIllllIIIllllI = 0; llllllllllllllllIllIllllIIIllllI < this.points.length; llllllllllllllllIllIllllIIIllllI += 2) {
            final float llllllllllllllllIllIllllIIIlllll = (this.points[llllllllllllllllIllIllllIIIllllI] - this.center[0]) * (this.points[llllllllllllllllIllIllllIIIllllI] - this.center[0]) + (this.points[llllllllllllllllIllIllllIIIllllI + 1] - this.center[1]) * (this.points[llllllllllllllllIllIllllIIIllllI + 1] - this.center[1]);
            this.boundingCircleRadius = ((this.boundingCircleRadius > llllllllllllllllIllIllllIIIlllll) ? this.boundingCircleRadius : llllllllllllllllIllIllllIIIlllll);
        }
        this.boundingCircleRadius = (float)Math.sqrt(this.boundingCircleRadius);
    }
    
    private float[] getNormal(final float[] llllllllllllllllIllIlllllIllIIlI, final float[] llllllllllllllllIllIlllllIllIIIl) {
        float llllllllllllllllIllIlllllIllIlIl = llllllllllllllllIllIlllllIllIIlI[0] - llllllllllllllllIllIlllllIllIIIl[0];
        float llllllllllllllllIllIlllllIllIlII = llllllllllllllllIllIlllllIllIIlI[1] - llllllllllllllllIllIlllllIllIIIl[1];
        final float llllllllllllllllIllIlllllIllIIll = (float)Math.sqrt(llllllllllllllllIllIlllllIllIlIl * llllllllllllllllIllIlllllIllIlIl + llllllllllllllllIllIlllllIllIlII * llllllllllllllllIllIlllllIllIlII);
        llllllllllllllllIllIlllllIllIlIl /= llllllllllllllllIllIlllllIllIIll;
        llllllllllllllllIllIlllllIllIlII /= llllllllllllllllIllIlllllIllIIll;
        return new float[] { -llllllllllllllllIllIlllllIllIlII, llllllllllllllllIllIlllllIllIlIl };
    }
    
    public float getMaxY() {
        this.checkPoints();
        return this.maxY;
    }
    
    public void preCache() {
        this.checkPoints();
        this.getTriangles();
    }
    
    public boolean contains(final Shape llllllllllllllllIllIllllllIIIIII) {
        if (llllllllllllllllIllIllllllIIIIII.intersects(this)) {
            return false;
        }
        for (int llllllllllllllllIllIllllllIIIlII = 0; llllllllllllllllIllIllllllIIIlII < llllllllllllllllIllIllllllIIIIII.getPointCount(); ++llllllllllllllllIllIllllllIIIlII) {
            final float[] llllllllllllllllIllIllllllIIIlIl = llllllllllllllllIllIllllllIIIIII.getPoint(llllllllllllllllIllIllllllIIIlII);
            if (!this.contains(llllllllllllllllIllIllllllIIIlIl[0], llllllllllllllllIllIllllllIIIlIl[1])) {
                return false;
            }
        }
        return true;
    }
    
    public float[] getPoint(final int llllllllllllllllIllIlllllllIllII) {
        this.checkPoints();
        final float[] llllllllllllllllIllIlllllllIlIll = { this.points[llllllllllllllllIllIlllllllIllII * 2], this.points[llllllllllllllllIllIlllllllIllII * 2 + 1] };
        return llllllllllllllllIllIlllllllIlIll;
    }
    
    public float[] getNormal(final int llllllllllllllllIllIllllllIlllII) {
        final float[] llllllllllllllllIllIllllllIllIll = this.getPoint(llllllllllllllllIllIllllllIlllII);
        final float[] llllllllllllllllIllIllllllIllIlI = this.getPoint((llllllllllllllllIllIllllllIlllII - 1 < 0) ? (this.getPointCount() - 1) : (llllllllllllllllIllIllllllIlllII - 1));
        final float[] llllllllllllllllIllIllllllIllIIl = this.getPoint((llllllllllllllllIllIllllllIlllII + 1 >= this.getPointCount()) ? 0 : (llllllllllllllllIllIllllllIlllII + 1));
        final float[] llllllllllllllllIllIllllllIllIII = this.getNormal(llllllllllllllllIllIllllllIllIlI, llllllllllllllllIllIllllllIllIll);
        final float[] llllllllllllllllIllIllllllIlIlll = this.getNormal(llllllllllllllllIllIllllllIllIll, llllllllllllllllIllIllllllIllIIl);
        if (llllllllllllllllIllIllllllIlllII == 0 && !this.closed()) {
            return llllllllllllllllIllIllllllIlIlll;
        }
        if (llllllllllllllllIllIllllllIlllII == this.getPointCount() - 1 && !this.closed()) {
            return llllllllllllllllIllIllllllIllIII;
        }
        final float llllllllllllllllIllIllllllIlIllI = (llllllllllllllllIllIllllllIllIII[0] + llllllllllllllllIllIllllllIlIlll[0]) / 2.0f;
        final float llllllllllllllllIllIllllllIlIlIl = (llllllllllllllllIllIllllllIllIII[1] + llllllllllllllllIllIllllllIlIlll[1]) / 2.0f;
        final float llllllllllllllllIllIllllllIlIlII = (float)Math.sqrt(llllllllllllllllIllIllllllIlIllI * llllllllllllllllIllIllllllIlIllI + llllllllllllllllIllIllllllIlIlIl * llllllllllllllllIllIllllllIlIlIl);
        return new float[] { llllllllllllllllIllIllllllIlIllI / llllllllllllllllIllIllllllIlIlII, llllllllllllllllIllIllllllIlIlIl / llllllllllllllllIllIllllllIlIlII };
    }
    
    public void setX(float llllllllllllllllIlllIIIIIIlllIll) {
        if (llllllllllllllllIlllIIIIIIlllIll != this.x) {
            final float llllllllllllllllIlllIIIIIIllllll = llllllllllllllllIlllIIIIIIlllIll - this.x;
            this.x = llllllllllllllllIlllIIIIIIlllIll;
            if (this.points == null || this.center == null) {
                this.checkPoints();
            }
            for (int llllllllllllllllIlllIIIIIlIIIIII = 0; llllllllllllllllIlllIIIIIlIIIIII < this.points.length / 2; ++llllllllllllllllIlllIIIIIlIIIIII) {
                final float[] points = this.points;
                final int n = llllllllllllllllIlllIIIIIlIIIIII * 2;
                points[n] += llllllllllllllllIlllIIIIIIllllll;
            }
            final float[] center = this.center;
            final int n2 = 0;
            center[n2] += llllllllllllllllIlllIIIIIIllllll;
            llllllllllllllllIlllIIIIIIlllIll += llllllllllllllllIlllIIIIIIllllll;
            this.maxX += llllllllllllllllIlllIIIIIIllllll;
            this.minX += llllllllllllllllIlllIIIIIIllllll;
            this.trianglesDirty = true;
        }
    }
    
    public Shape[] union(final Shape llllllllllllllllIllIlllIllIIIlII) {
        return new GeomUtil().union(this, llllllllllllllllIllIlllIllIIIlII);
    }
    
    protected void findCenter() {
        this.center = new float[] { 0.0f, 0.0f };
        final int llllllllllllllllIllIllllIIlIIllI = this.points.length;
        for (int llllllllllllllllIllIllllIIlIlIII = 0; llllllllllllllllIllIllllIIlIlIII < llllllllllllllllIllIllllIIlIIllI; llllllllllllllllIllIllllIIlIlIII += 2) {
            final float[] center = this.center;
            final int n = 0;
            center[n] += this.points[llllllllllllllllIllIllllIIlIlIII];
            final float[] center2 = this.center;
            final int n2 = 1;
            center2[n2] += this.points[llllllllllllllllIllIllllIIlIlIII + 1];
        }
        final float[] center3 = this.center;
        final int n3 = 0;
        center3[n3] /= llllllllllllllllIllIllllIIlIIllI / 2;
        final float[] center4 = this.center;
        final int n4 = 1;
        center4[n4] /= llllllllllllllllIllIllllIIlIIllI / 2;
    }
    
    public float getCenterY() {
        this.checkPoints();
        return this.center[1];
    }
    
    public Shape prune() {
        final Polygon llllllllllllllllIllIlllIllIllIll = new Polygon();
        for (int llllllllllllllllIllIlllIllIlllIl = 0; llllllllllllllllIllIlllIllIlllIl < this.getPointCount(); ++llllllllllllllllIllIlllIllIlllIl) {
            final int llllllllllllllllIllIlllIlllIIlIl = (llllllllllllllllIllIlllIllIlllIl + 1 >= this.getPointCount()) ? 0 : (llllllllllllllllIllIlllIllIlllIl + 1);
            final int llllllllllllllllIllIlllIlllIIlII = (llllllllllllllllIllIlllIllIlllIl - 1 < 0) ? (this.getPointCount() - 1) : (llllllllllllllllIllIlllIllIlllIl - 1);
            float llllllllllllllllIllIlllIlllIIIll = this.getPoint(llllllllllllllllIllIlllIllIlllIl)[0] - this.getPoint(llllllllllllllllIllIlllIlllIIlII)[0];
            float llllllllllllllllIllIlllIlllIIIlI = this.getPoint(llllllllllllllllIllIlllIllIlllIl)[1] - this.getPoint(llllllllllllllllIllIlllIlllIIlII)[1];
            float llllllllllllllllIllIlllIlllIIIIl = this.getPoint(llllllllllllllllIllIlllIlllIIlIl)[0] - this.getPoint(llllllllllllllllIllIlllIllIlllIl)[0];
            float llllllllllllllllIllIlllIlllIIIII = this.getPoint(llllllllllllllllIllIlllIlllIIlIl)[1] - this.getPoint(llllllllllllllllIllIlllIllIlllIl)[1];
            final float llllllllllllllllIllIlllIllIlllll = (float)Math.sqrt(llllllllllllllllIllIlllIlllIIIll * llllllllllllllllIllIlllIlllIIIll + llllllllllllllllIllIlllIlllIIIlI * llllllllllllllllIllIlllIlllIIIlI);
            final float llllllllllllllllIllIlllIllIllllI = (float)Math.sqrt(llllllllllllllllIllIlllIlllIIIIl * llllllllllllllllIllIlllIlllIIIIl + llllllllllllllllIllIlllIlllIIIII * llllllllllllllllIllIlllIlllIIIII);
            llllllllllllllllIllIlllIlllIIIll /= llllllllllllllllIllIlllIllIlllll;
            llllllllllllllllIllIlllIlllIIIlI /= llllllllllllllllIllIlllIllIlllll;
            llllllllllllllllIllIlllIlllIIIIl /= llllllllllllllllIllIlllIllIllllI;
            llllllllllllllllIllIlllIlllIIIII /= llllllllllllllllIllIlllIllIllllI;
            if (llllllllllllllllIllIlllIlllIIIll != llllllllllllllllIllIlllIlllIIIIl || llllllllllllllllIllIlllIlllIIIlI != llllllllllllllllIllIlllIlllIIIII) {
                llllllllllllllllIllIlllIllIllIll.addPoint(this.getPoint(llllllllllllllllIllIlllIllIlllIl)[0], this.getPoint(llllllllllllllllIllIlllIllIlllIl)[1]);
            }
        }
        return llllllllllllllllIllIlllIllIllIll;
    }
    
    public Shape() {
        this.pointsDirty = true;
    }
    
    public float getMinX() {
        this.checkPoints();
        return this.minX;
    }
    
    public float getBoundingCircleRadius() {
        this.checkPoints();
        return this.boundingCircleRadius;
    }
    
    public void increaseTriangulation() {
        this.checkPoints();
        this.calculateTriangles();
        this.tris = new OverTriangulator(this.tris);
    }
    
    public float getWidth() {
        return this.maxX - this.minX;
    }
    
    protected final void checkPoints() {
        if (this.pointsDirty) {
            this.createPoints();
            this.findCenter();
            this.calculateRadius();
            if (this.points.length > 0) {
                this.maxX = this.points[0];
                this.maxY = this.points[1];
                this.minX = this.points[0];
                this.minY = this.points[1];
                for (int llllllllllllllllIllIlllIlllllIII = 0; llllllllllllllllIllIlllIlllllIII < this.points.length / 2; ++llllllllllllllllIllIlllIlllllIII) {
                    this.maxX = Math.max(this.points[llllllllllllllllIllIlllIlllllIII * 2], this.maxX);
                    this.maxY = Math.max(this.points[llllllllllllllllIllIlllIlllllIII * 2 + 1], this.maxY);
                    this.minX = Math.min(this.points[llllllllllllllllIllIlllIlllllIII * 2], this.minX);
                    this.minY = Math.min(this.points[llllllllllllllllIllIlllIlllllIII * 2 + 1], this.minY);
                }
            }
            this.pointsDirty = false;
            this.trianglesDirty = true;
        }
    }
    
    public Triangulator getTriangles() {
        this.checkPoints();
        this.calculateTriangles();
        return this.tris;
    }
    
    public int getPointCount() {
        this.checkPoints();
        return this.points.length / 2;
    }
    
    public abstract Shape transform(final Transform p0);
    
    public float getY() {
        return this.y;
    }
    
    public float getHeight() {
        return this.maxY - this.minY;
    }
    
    public float getMinY() {
        this.checkPoints();
        return this.minY;
    }
    
    protected abstract void createPoints();
    
    public float getX() {
        return this.x;
    }
    
    public int indexOf(final float llllllllllllllllIllIlllllIIIllll, final float llllllllllllllllIllIlllllIIIlllI) {
        for (int llllllllllllllllIllIlllllIIlIlII = 0; llllllllllllllllIllIlllllIIlIlII < this.points.length; llllllllllllllllIllIlllllIIlIlII += 2) {
            if (this.points[llllllllllllllllIllIlllllIIlIlII] == llllllllllllllllIllIlllllIIIllll && this.points[llllllllllllllllIllIlllllIIlIlII + 1] == llllllllllllllllIllIlllllIIIlllI) {
                return llllllllllllllllIllIlllllIIlIlII / 2;
            }
        }
        return -1;
    }
    
    public void setLocation(final Vector2f llllllllllllllllIlllIIIIIIlIIIIl) {
        this.setX(llllllllllllllllIlllIIIIIIlIIIIl.x);
        this.setY(llllllllllllllllIlllIIIIIIlIIIIl.y);
    }
    
    public boolean includes(final float llllllllllllllllIllIlllllIlIIIll, final float llllllllllllllllIllIlllllIlIIIlI) {
        if (this.points.length == 0) {
            return false;
        }
        this.checkPoints();
        final Line llllllllllllllllIllIlllllIlIIIIl = new Line(0.0f, 0.0f, 0.0f, 0.0f);
        final Vector2f llllllllllllllllIllIlllllIlIIIII = new Vector2f(llllllllllllllllIllIlllllIlIIIll, llllllllllllllllIllIlllllIlIIIlI);
        for (int llllllllllllllllIllIlllllIlIIlIl = 0; llllllllllllllllIllIlllllIlIIlIl < this.points.length; llllllllllllllllIllIlllllIlIIlIl += 2) {
            int llllllllllllllllIllIlllllIlIIllI = llllllllllllllllIllIlllllIlIIlIl + 2;
            if (llllllllllllllllIllIlllllIlIIllI >= this.points.length) {
                llllllllllllllllIllIlllllIlIIllI = 0;
            }
            llllllllllllllllIllIlllllIlIIIIl.set(this.points[llllllllllllllllIllIlllllIlIIlIl], this.points[llllllllllllllllIllIlllllIlIIlIl + 1], this.points[llllllllllllllllIllIlllllIlIIllI], this.points[llllllllllllllllIllIlllllIlIIllI + 1]);
            if (llllllllllllllllIllIlllllIlIIIIl.on(llllllllllllllllIllIlllllIlIIIII)) {
                return true;
            }
        }
        return false;
    }
}
