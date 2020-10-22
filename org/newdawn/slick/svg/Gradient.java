package org.newdawn.slick.svg;

import org.newdawn.slick.geom.*;
import java.util.*;
import org.newdawn.slick.*;

public class Gradient
{
    private /* synthetic */ Transform transform;
    private /* synthetic */ Image image;
    private /* synthetic */ float r;
    private /* synthetic */ float y2;
    private /* synthetic */ float x2;
    private /* synthetic */ float y1;
    private /* synthetic */ String name;
    private /* synthetic */ float x1;
    private /* synthetic */ boolean radial;
    private /* synthetic */ String ref;
    private /* synthetic */ ArrayList steps;
    
    public Gradient(final String lllllllllllllllllIIIIIIllIlllIll, final boolean lllllllllllllllllIIIIIIllIlllIIl) {
        this.steps = new ArrayList();
        this.name = lllllllllllllllllIIIIIIllIlllIll;
        this.radial = lllllllllllllllllIIIIIIllIlllIIl;
    }
    
    public void setR(final float lllllllllllllllllIIIIIIlIllllIll) {
        this.r = lllllllllllllllllIIIIIIlIllllIll;
    }
    
    public float getY2() {
        return this.y2;
    }
    
    public Color getColorAt(float lllllllllllllllllIIIIIIlIIIlIlII) {
        if (lllllllllllllllllIIIIIIlIIIlIlII <= 0.0f) {
            return this.steps.get(0).col;
        }
        if (lllllllllllllllllIIIIIIlIIIlIlII > 1.0f) {
            return this.steps.get(this.steps.size() - 1).col;
        }
        for (int lllllllllllllllllIIIIIIlIIIllIII = 1; lllllllllllllllllIIIIIIlIIIllIII < this.steps.size(); ++lllllllllllllllllIIIIIIlIIIllIII) {
            final Step lllllllllllllllllIIIIIIlIIIllIlI = this.steps.get(lllllllllllllllllIIIIIIlIIIllIII - 1);
            final Step lllllllllllllllllIIIIIIlIIIllIIl = this.steps.get(lllllllllllllllllIIIIIIlIIIllIII);
            if (lllllllllllllllllIIIIIIlIIIlIlII <= lllllllllllllllllIIIIIIlIIIllIIl.location) {
                final float lllllllllllllllllIIIIIIlIIIlllIl = lllllllllllllllllIIIIIIlIIIllIIl.location - lllllllllllllllllIIIIIIlIIIllIlI.location;
                lllllllllllllllllIIIIIIlIIIlIlII -= lllllllllllllllllIIIIIIlIIIllIlI.location;
                final float lllllllllllllllllIIIIIIlIIIlllII = lllllllllllllllllIIIIIIlIIIlIlII / lllllllllllllllllIIIIIIlIIIlllIl;
                final Color lllllllllllllllllIIIIIIlIIIllIll = new Color(1, 1, 1, 1);
                lllllllllllllllllIIIIIIlIIIllIll.a = lllllllllllllllllIIIIIIlIIIllIlI.col.a * (1.0f - lllllllllllllllllIIIIIIlIIIlllII) + lllllllllllllllllIIIIIIlIIIllIIl.col.a * lllllllllllllllllIIIIIIlIIIlllII;
                lllllllllllllllllIIIIIIlIIIllIll.r = lllllllllllllllllIIIIIIlIIIllIlI.col.r * (1.0f - lllllllllllllllllIIIIIIlIIIlllII) + lllllllllllllllllIIIIIIlIIIllIIl.col.r * lllllllllllllllllIIIIIIlIIIlllII;
                lllllllllllllllllIIIIIIlIIIllIll.g = lllllllllllllllllIIIIIIlIIIllIlI.col.g * (1.0f - lllllllllllllllllIIIIIIlIIIlllII) + lllllllllllllllllIIIIIIlIIIllIIl.col.g * lllllllllllllllllIIIIIIlIIIlllII;
                lllllllllllllllllIIIIIIlIIIllIll.b = lllllllllllllllllIIIIIIlIIIllIlI.col.b * (1.0f - lllllllllllllllllIIIIIIlIIIlllII) + lllllllllllllllllIIIIIIlIIIllIIl.col.b * lllllllllllllllllIIIIIIlIIIlllII;
                return lllllllllllllllllIIIIIIlIIIllIll;
            }
        }
        return Color.black;
    }
    
    public Transform getTransform() {
        return this.transform;
    }
    
    public void resolve(final Diagram lllllllllllllllllIIIIIIllIIlIlIl) {
        if (this.ref == null) {
            return;
        }
        final Gradient lllllllllllllllllIIIIIIllIIlIlll = lllllllllllllllllIIIIIIllIIlIlIl.getGradient(this.ref);
        for (int lllllllllllllllllIIIIIIllIIllIlI = 0; lllllllllllllllllIIIIIIllIIllIlI < lllllllllllllllllIIIIIIllIIlIlll.steps.size(); ++lllllllllllllllllIIIIIIllIIllIlI) {
            this.steps.add(lllllllllllllllllIIIIIIllIIlIlll.steps.get(lllllllllllllllllIIIIIIllIIllIlI));
        }
    }
    
    public void setY1(final float lllllllllllllllllIIIIIIlIllIIIIl) {
        this.y1 = lllllllllllllllllIIIIIIlIllIIIIl;
    }
    
    public float getX1() {
        return this.x1;
    }
    
    public boolean isRadial() {
        return this.radial;
    }
    
    public void setTransform(final Transform lllllllllllllllllIIIIIIllIlIlIII) {
        this.transform = lllllllllllllllllIIIIIIllIlIlIII;
    }
    
    public void setY2(final float lllllllllllllllllIIIIIIlIlIlIllI) {
        this.y2 = lllllllllllllllllIIIIIIlIlIlIllI;
    }
    
    public void genImage() {
        if (this.image == null) {
            final ImageBuffer lllllllllllllllllIIIIIIllIIIlIlI = new ImageBuffer(128, 16);
            for (int lllllllllllllllllIIIIIIllIIIlIll = 0; lllllllllllllllllIIIIIIllIIIlIll < 128; ++lllllllllllllllllIIIIIIllIIIlIll) {
                final Color lllllllllllllllllIIIIIIllIIIllII = this.getColorAt(lllllllllllllllllIIIIIIllIIIlIll / 128.0f);
                for (int lllllllllllllllllIIIIIIllIIIllIl = 0; lllllllllllllllllIIIIIIllIIIllIl < 16; ++lllllllllllllllllIIIIIIllIIIllIl) {
                    lllllllllllllllllIIIIIIllIIIlIlI.setRGBA(lllllllllllllllllIIIIIIllIIIlIll, lllllllllllllllllIIIIIIllIIIllIl, lllllllllllllllllIIIIIIllIIIllII.getRedByte(), lllllllllllllllllIIIIIIllIIIllII.getGreenByte(), lllllllllllllllllIIIIIIllIIIllII.getBlueByte(), lllllllllllllllllIIIIIIllIIIllII.getAlphaByte());
                }
            }
            this.image = lllllllllllllllllIIIIIIllIIIlIlI.getImage();
        }
    }
    
    public void setX2(final float lllllllllllllllllIIIIIIlIllIlIII) {
        this.x2 = lllllllllllllllllIIIIIIlIllIlIII;
    }
    
    public float getY1() {
        return this.y1;
    }
    
    public Image getImage() {
        this.genImage();
        return this.image;
    }
    
    public void addStep(final float lllllllllllllllllIIIIIIlIIlIlIlI, final Color lllllllllllllllllIIIIIIlIIlIlIII) {
        this.steps.add(new Step(lllllllllllllllllIIIIIIlIIlIlIlI, lllllllllllllllllIIIIIIlIIlIlIII));
    }
    
    public float getR() {
        return this.r;
    }
    
    public void reference(final String lllllllllllllllllIIIIIIllIIlllll) {
        this.ref = lllllllllllllllllIIIIIIllIIlllll;
    }
    
    public void setX1(final float lllllllllllllllllIIIIIIlIlllIlIl) {
        this.x1 = lllllllllllllllllIIIIIIlIlllIlIl;
    }
    
    public float getX2() {
        return this.x2;
    }
    
    private class Step
    {
        /* synthetic */ float location;
        /* synthetic */ Color col;
        
        public Step(final float lIlIlllIlllIIII, final Color lIlIlllIllIlIll) {
            this.location = lIlIlllIlllIIII;
            this.col = lIlIlllIllIlIll;
        }
    }
}
