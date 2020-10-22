package org.newdawn.slick.fills;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class GradientFill implements ShapeFill
{
    private /* synthetic */ Color endCol;
    private /* synthetic */ Vector2f start;
    private /* synthetic */ Color startCol;
    private /* synthetic */ boolean local;
    private /* synthetic */ Vector2f none;
    private /* synthetic */ Vector2f end;
    
    public Vector2f getStart() {
        return this.start;
    }
    
    public void setStart(final float llllIIlIIlIII, final float llllIIlIIIlll) {
        this.setStart(new Vector2f(llllIIlIIlIII, llllIIlIIIlll));
    }
    
    public GradientFill(final Vector2f llllIIllIllII, final Color llllIIllIIlIl, final Vector2f llllIIllIlIlI, final Color llllIIllIIIll, final boolean llllIIllIIIlI) {
        this.none = new Vector2f(0.0f, 0.0f);
        this.local = false;
        this.start = new Vector2f(llllIIllIllII);
        this.end = new Vector2f(llllIIllIlIlI);
        this.startCol = new Color(llllIIllIIlIl);
        this.endCol = new Color(llllIIllIIIll);
        this.local = llllIIllIIIlI;
    }
    
    public void setEnd(final Vector2f llllIIIlIllll) {
        this.end = new Vector2f(llllIIIlIllll);
    }
    
    public GradientFill getInvertedCopy() {
        return new GradientFill(this.start, this.endCol, this.end, this.startCol, this.local);
    }
    
    public void setEndColor(final Color llllIIIlIIlIl) {
        this.endCol = new Color(llllIIIlIIlIl);
    }
    
    public void setStart(final Vector2f llllIIIlllllI) {
        this.start = new Vector2f(llllIIIlllllI);
    }
    
    public GradientFill(final float llllIlIIlIIIl, final float llllIlIIlIIII, final Color llllIlIIlIllI, final float llllIlIIlIlIl, final float llllIlIIIllIl, final Color llllIlIIlIIll) {
        this(llllIlIIlIIIl, llllIlIIlIIII, llllIlIIlIllI, llllIlIIlIlIl, llllIlIIIllIl, llllIlIIlIIll, false);
    }
    
    public Vector2f getEnd() {
        return this.end;
    }
    
    @Override
    public Vector2f getOffsetAt(final Shape lllIllllIllIl, final float lllIllllIllII, final float lllIllllIlIll) {
        return this.none;
    }
    
    public void setEnd(final float llllIIIllIllI, final float llllIIIlllIII) {
        this.setEnd(new Vector2f(llllIIIllIllI, llllIIIlllIII));
    }
    
    public Color colorAt(final float llllIIIIIlIII, final float lllIllllllIlI) {
        final float llllIIIIIIllI = this.end.getX() - this.start.getX();
        final float llllIIIIIIlIl = this.end.getY() - this.start.getY();
        final float llllIIIIIIlII = -llllIIIIIIlIl;
        final float llllIIIIIIIll = llllIIIIIIllI;
        final float llllIIIIIIIlI = llllIIIIIIIll * llllIIIIIIllI - llllIIIIIIlII * llllIIIIIIlIl;
        if (llllIIIIIIIlI == 0.0f) {
            return Color.black;
        }
        float llllIIIIIIIIl = llllIIIIIIlII * (this.start.getY() - lllIllllllIlI) - llllIIIIIIIll * (this.start.getX() - llllIIIIIlIII);
        llllIIIIIIIIl /= llllIIIIIIIlI;
        float llllIIIIIIIII = llllIIIIIIllI * (this.start.getY() - lllIllllllIlI) - llllIIIIIIlIl * (this.start.getX() - llllIIIIIlIII);
        llllIIIIIIIII /= llllIIIIIIIlI;
        float lllIlllllllll = llllIIIIIIIIl;
        if (lllIlllllllll < 0.0f) {
            lllIlllllllll = 0.0f;
        }
        if (lllIlllllllll > 1.0f) {
            lllIlllllllll = 1.0f;
        }
        final float lllIllllllllI = 1.0f - lllIlllllllll;
        final Color lllIlllllllIl = new Color(1, 1, 1, 1);
        lllIlllllllIl.r = lllIlllllllll * this.endCol.r + lllIllllllllI * this.startCol.r;
        lllIlllllllIl.b = lllIlllllllll * this.endCol.b + lllIllllllllI * this.startCol.b;
        lllIlllllllIl.g = lllIlllllllll * this.endCol.g + lllIllllllllI * this.startCol.g;
        lllIlllllllIl.a = lllIlllllllll * this.endCol.a + lllIllllllllI * this.startCol.a;
        return lllIlllllllIl;
    }
    
    public void setLocal(final boolean llllIIlIllIIl) {
        this.local = llllIIlIllIIl;
    }
    
    public void setStartColor(final Color llllIIIlIlIIl) {
        this.startCol = new Color(llllIIIlIlIIl);
    }
    
    public Color getStartColor() {
        return this.startCol;
    }
    
    public Color getEndColor() {
        return this.endCol;
    }
    
    public GradientFill(final float llllIlIIIIIlI, final float llllIlIIIIIIl, final Color llllIIllllIII, final float llllIIlllllll, final float llllIIllllllI, final Color llllIIlllIlIl, final boolean llllIIlllIlII) {
        this(new Vector2f(llllIlIIIIIlI, llllIlIIIIIIl), llllIIllllIII, new Vector2f(llllIIlllllll, llllIIllllllI), llllIIlllIlIl, llllIIlllIlII);
    }
    
    @Override
    public Color colorAt(final Shape llllIIIIllIIl, final float llllIIIIlllII, final float llllIIIIlIlll) {
        if (this.local) {
            return this.colorAt(llllIIIIlllII - llllIIIIllIIl.getCenterX(), llllIIIIlIlll - llllIIIIllIIl.getCenterY());
        }
        return this.colorAt(llllIIIIlllII, llllIIIIlIlll);
    }
}
