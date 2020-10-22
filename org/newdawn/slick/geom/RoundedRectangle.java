package org.newdawn.slick.geom;

import java.util.*;
import org.newdawn.slick.util.*;

public class RoundedRectangle extends Rectangle
{
    private /* synthetic */ float cornerRadius;
    private /* synthetic */ int cornerFlags;
    private /* synthetic */ int segmentCount;
    
    @Override
    public void setHeight(final float llllllllllllllllllIIlIllIlIIlIlI) {
        if (this.height != llllllllllllllllllIIlIllIlIIlIlI) {
            this.height = llllllllllllllllllIIlIllIlIIlIlI;
            this.pointsDirty = true;
        }
    }
    
    @Override
    public float getHeight() {
        return this.height;
    }
    
    public RoundedRectangle(final float llllllllllllllllllIIlIllIllIlIlI, final float llllllllllllllllllIIlIllIllIIIIl, final float llllllllllllllllllIIlIllIllIlIII, final float llllllllllllllllllIIlIllIlIlllll, final float llllllllllllllllllIIlIllIllIIllI, final int llllllllllllllllllIIlIllIllIIlIl, final int llllllllllllllllllIIlIllIlIlllII) {
        super(llllllllllllllllllIIlIllIllIlIlI, llllllllllllllllllIIlIllIllIIIIl, llllllllllllllllllIIlIllIllIlIII, llllllllllllllllllIIlIllIlIlllll);
        if (llllllllllllllllllIIlIllIllIIllI < 0.0f) {
            throw new IllegalArgumentException("corner radius must be >= 0");
        }
        this.x = llllllllllllllllllIIlIllIllIlIlI;
        this.y = llllllllllllllllllIIlIllIllIIIIl;
        this.width = llllllllllllllllllIIlIllIllIlIII;
        this.height = llllllllllllllllllIIlIllIlIlllll;
        this.cornerRadius = llllllllllllllllllIIlIllIllIIllI;
        this.segmentCount = llllllllllllllllllIIlIllIllIIlIl;
        this.pointsDirty = true;
        this.cornerFlags = llllllllllllllllllIIlIllIlIlllII;
    }
    
    public void setCornerRadius(final float llllllllllllllllllIIlIllIlIlIIll) {
        if (llllllllllllllllllIIlIllIlIlIIll >= 0.0f && llllllllllllllllllIIlIllIlIlIIll != this.cornerRadius) {
            this.cornerRadius = llllllllllllllllllIIlIllIlIlIIll;
            this.pointsDirty = true;
        }
    }
    
    public RoundedRectangle(final float llllllllllllllllllIIlIlllIIIIIII, final float llllllllllllllllllIIlIllIllllIII, final float llllllllllllllllllIIlIllIlllIlll, final float llllllllllllllllllIIlIllIlllllIl, final float llllllllllllllllllIIlIllIlllIlIl, final int llllllllllllllllllIIlIllIllllIll) {
        this(llllllllllllllllllIIlIlllIIIIIII, llllllllllllllllllIIlIllIllllIII, llllllllllllllllllIIlIllIlllIlll, llllllllllllllllllIIlIllIlllllIl, llllllllllllllllllIIlIllIlllIlIl, llllllllllllllllllIIlIllIllllIll, 15);
    }
    
    static {
        TOP_LEFT = 1;
        BOTTOM_RIGHT = 4;
        DEFAULT_SEGMENT_COUNT = 25;
        BOTTOM_LEFT = 8;
        TOP_RIGHT = 2;
        ALL = 15;
    }
    
    @Override
    public float getWidth() {
        return this.width;
    }
    
    @Override
    public Shape transform(final Transform llllllllllllllllllIIlIllIIIIIIII) {
        this.checkPoints();
        final Polygon llllllllllllllllllIIlIllIIIIIIll = new Polygon();
        final float[] llllllllllllllllllIIlIllIIIIIIlI = new float[this.points.length];
        llllllllllllllllllIIlIllIIIIIIII.transform(this.points, 0, llllllllllllllllllIIlIllIIIIIIlI, 0, this.points.length / 2);
        llllllllllllllllllIIlIllIIIIIIll.points = llllllllllllllllllIIlIllIIIIIIlI;
        llllllllllllllllllIIlIllIIIIIIll.findCenter();
        return llllllllllllllllllIIlIllIIIIIIll;
    }
    
    public RoundedRectangle(final float llllllllllllllllllIIlIlllIIIllIl, final float llllllllllllllllllIIlIlllIIIllII, final float llllllllllllllllllIIlIlllIIlIIIl, final float llllllllllllllllllIIlIlllIIIlIlI, final float llllllllllllllllllIIlIlllIIIllll) {
        this(llllllllllllllllllIIlIlllIIIllIl, llllllllllllllllllIIlIlllIIIllII, llllllllllllllllllIIlIlllIIlIIIl, llllllllllllllllllIIlIlllIIIlIlI, llllllllllllllllllIIlIlllIIIllll, 25);
    }
    
    @Override
    protected void createPoints() {
        this.maxX = this.x + this.width;
        this.maxY = this.y + this.height;
        this.minX = this.x;
        this.minY = this.y;
        final float llllllllllllllllllIIlIllIIllIllI = this.width - 1.0f;
        final float llllllllllllllllllIIlIllIIllIlIl = this.height - 1.0f;
        if (this.cornerRadius == 0.0f) {
            this.points = new float[8];
            this.points[0] = this.x;
            this.points[1] = this.y;
            this.points[2] = this.x + llllllllllllllllllIIlIllIIllIllI;
            this.points[3] = this.y;
            this.points[4] = this.x + llllllllllllllllllIIlIllIIllIllI;
            this.points[5] = this.y + llllllllllllllllllIIlIllIIllIlIl;
            this.points[6] = this.x;
            this.points[7] = this.y + llllllllllllllllllIIlIllIIllIlIl;
        }
        else {
            float llllllllllllllllllIIlIllIIlllIIl = this.cornerRadius * 2.0f;
            if (llllllllllllllllllIIlIllIIlllIIl > llllllllllllllllllIIlIllIIllIllI) {
                llllllllllllllllllIIlIllIIlllIIl = llllllllllllllllllIIlIllIIllIllI;
                this.cornerRadius = llllllllllllllllllIIlIllIIlllIIl / 2.0f;
            }
            if (llllllllllllllllllIIlIllIIlllIIl > llllllllllllllllllIIlIllIIllIlIl) {
                llllllllllllllllllIIlIllIIlllIIl = llllllllllllllllllIIlIllIIllIlIl;
                this.cornerRadius = llllllllllllllllllIIlIllIIlllIIl / 2.0f;
            }
            final ArrayList llllllllllllllllllIIlIllIIlllIII = new ArrayList();
            if ((this.cornerFlags & 0x1) != 0x0) {
                llllllllllllllllllIIlIllIIlllIII.addAll(this.createPoints(this.segmentCount, this.cornerRadius, this.x + this.cornerRadius, this.y + this.cornerRadius, 180.0f, 270.0f));
            }
            else {
                llllllllllllllllllIIlIllIIlllIII.add(new Float(this.x));
                llllllllllllllllllIIlIllIIlllIII.add(new Float(this.y));
            }
            if ((this.cornerFlags & 0x2) != 0x0) {
                llllllllllllllllllIIlIllIIlllIII.addAll(this.createPoints(this.segmentCount, this.cornerRadius, this.x + llllllllllllllllllIIlIllIIllIllI - this.cornerRadius, this.y + this.cornerRadius, 270.0f, 360.0f));
            }
            else {
                llllllllllllllllllIIlIllIIlllIII.add(new Float(this.x + llllllllllllllllllIIlIllIIllIllI));
                llllllllllllllllllIIlIllIIlllIII.add(new Float(this.y));
            }
            if ((this.cornerFlags & 0x4) != 0x0) {
                llllllllllllllllllIIlIllIIlllIII.addAll(this.createPoints(this.segmentCount, this.cornerRadius, this.x + llllllllllllllllllIIlIllIIllIllI - this.cornerRadius, this.y + llllllllllllllllllIIlIllIIllIlIl - this.cornerRadius, 0.0f, 90.0f));
            }
            else {
                llllllllllllllllllIIlIllIIlllIII.add(new Float(this.x + llllllllllllllllllIIlIllIIllIllI));
                llllllllllllllllllIIlIllIIlllIII.add(new Float(this.y + llllllllllllllllllIIlIllIIllIlIl));
            }
            if ((this.cornerFlags & 0x8) != 0x0) {
                llllllllllllllllllIIlIllIIlllIII.addAll(this.createPoints(this.segmentCount, this.cornerRadius, this.x + this.cornerRadius, this.y + llllllllllllllllllIIlIllIIllIlIl - this.cornerRadius, 90.0f, 180.0f));
            }
            else {
                llllllllllllllllllIIlIllIIlllIII.add(new Float(this.x));
                llllllllllllllllllIIlIllIIlllIII.add(new Float(this.y + llllllllllllllllllIIlIllIIllIlIl));
            }
            this.points = new float[llllllllllllllllllIIlIllIIlllIII.size()];
            for (int llllllllllllllllllIIlIllIIlllIlI = 0; llllllllllllllllllIIlIllIIlllIlI < llllllllllllllllllIIlIllIIlllIII.size(); ++llllllllllllllllllIIlIllIIlllIlI) {
                this.points[llllllllllllllllllIIlIllIIlllIlI] = llllllllllllllllllIIlIllIIlllIII.get(llllllllllllllllllIIlIllIIlllIlI);
            }
        }
        this.findCenter();
        this.calculateRadius();
    }
    
    public float getCornerRadius() {
        return this.cornerRadius;
    }
    
    private List createPoints(final int llllllllllllllllllIIlIllIIIlIlIl, final float llllllllllllllllllIIlIllIIIlllII, final float llllllllllllllllllIIlIllIIIllIll, final float llllllllllllllllllIIlIllIIIlIIlI, final float llllllllllllllllllIIlIllIIIlIIIl, final float llllllllllllllllllIIlIllIIIllIII) {
        final ArrayList llllllllllllllllllIIlIllIIIlIlll = new ArrayList();
        final int llllllllllllllllllIIlIllIIIlIllI = 360 / llllllllllllllllllIIlIllIIIlIlIl;
        for (float llllllllllllllllllIIlIllIIIlllll = llllllllllllllllllIIlIllIIIlIIIl; llllllllllllllllllIIlIllIIIlllll <= llllllllllllllllllIIlIllIIIllIII + llllllllllllllllllIIlIllIIIlIllI; llllllllllllllllllIIlIllIIIlllll += llllllllllllllllllIIlIllIIIlIllI) {
            float llllllllllllllllllIIlIllIIlIIIlI = llllllllllllllllllIIlIllIIIlllll;
            if (llllllllllllllllllIIlIllIIlIIIlI > llllllllllllllllllIIlIllIIIllIII) {
                llllllllllllllllllIIlIllIIlIIIlI = llllllllllllllllllIIlIllIIIllIII;
            }
            final float llllllllllllllllllIIlIllIIlIIIIl = (float)(llllllllllllllllllIIlIllIIIllIll + FastTrig.cos(Math.toRadians(llllllllllllllllllIIlIllIIlIIIlI)) * llllllllllllllllllIIlIllIIIlllII);
            final float llllllllllllllllllIIlIllIIlIIIII = (float)(llllllllllllllllllIIlIllIIIlIIlI + FastTrig.sin(Math.toRadians(llllllllllllllllllIIlIllIIlIIIlI)) * llllllllllllllllllIIlIllIIIlllII);
            llllllllllllllllllIIlIllIIIlIlll.add(new Float(llllllllllllllllllIIlIllIIlIIIIl));
            llllllllllllllllllIIlIllIIIlIlll.add(new Float(llllllllllllllllllIIlIllIIlIIIII));
        }
        return llllllllllllllllllIIlIllIIIlIlll;
    }
    
    @Override
    public void setWidth(final float llllllllllllllllllIIlIllIlIIIIIl) {
        if (llllllllllllllllllIIlIllIlIIIIIl != this.width) {
            this.width = llllllllllllllllllIIlIllIlIIIIIl;
            this.pointsDirty = true;
        }
    }
}
