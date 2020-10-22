package org.newdawn.slick.geom;

public class Curve extends Shape
{
    private /* synthetic */ Vector2f c1;
    private /* synthetic */ Vector2f c2;
    private /* synthetic */ Vector2f p2;
    private /* synthetic */ int segments;
    private /* synthetic */ Vector2f p1;
    
    public Curve(final Vector2f llllllllllllllllIlIlllIlIIIlIlll, final Vector2f llllllllllllllllIlIlllIlIIIlllII, final Vector2f llllllllllllllllIlIlllIlIIIlIlIl, final Vector2f llllllllllllllllIlIlllIlIIIllIlI, final int llllllllllllllllIlIlllIlIIIlIIll) {
        this.p1 = new Vector2f(llllllllllllllllIlIlllIlIIIlIlll);
        this.c1 = new Vector2f(llllllllllllllllIlIlllIlIIIlllII);
        this.c2 = new Vector2f(llllllllllllllllIlIlllIlIIIlIlIl);
        this.p2 = new Vector2f(llllllllllllllllIlIlllIlIIIllIlI);
        this.segments = llllllllllllllllIlIlllIlIIIlIIll;
        this.pointsDirty = true;
    }
    
    @Override
    public Shape transform(final Transform llllllllllllllllIlIlllIIlllIIIII) {
        final float[] llllllllllllllllIlIlllIIllIlllll = new float[8];
        final float[] llllllllllllllllIlIlllIIllIllllI = new float[8];
        llllllllllllllllIlIlllIIllIlllll[0] = this.p1.x;
        llllllllllllllllIlIlllIIllIlllll[1] = this.p1.y;
        llllllllllllllllIlIlllIIllIlllll[2] = this.c1.x;
        llllllllllllllllIlIlllIIllIlllll[3] = this.c1.y;
        llllllllllllllllIlIlllIIllIlllll[4] = this.c2.x;
        llllllllllllllllIlIlllIIllIlllll[5] = this.c2.y;
        llllllllllllllllIlIlllIIllIlllll[6] = this.p2.x;
        llllllllllllllllIlIlllIIllIlllll[7] = this.p2.y;
        llllllllllllllllIlIlllIIlllIIIII.transform(llllllllllllllllIlIlllIIllIlllll, 0, llllllllllllllllIlIlllIIllIllllI, 0, 4);
        return new Curve(new Vector2f(llllllllllllllllIlIlllIIllIllllI[0], llllllllllllllllIlIlllIIllIllllI[1]), new Vector2f(llllllllllllllllIlIlllIIllIllllI[2], llllllllllllllllIlIlllIIllIllllI[3]), new Vector2f(llllllllllllllllIlIlllIIllIllllI[4], llllllllllllllllIlIlllIIllIllllI[5]), new Vector2f(llllllllllllllllIlIlllIIllIllllI[6], llllllllllllllllIlIlllIIllIllllI[7]));
    }
    
    public Curve(final Vector2f llllllllllllllllIlIlllIlIIlIlIII, final Vector2f llllllllllllllllIlIlllIlIIlIIlll, final Vector2f llllllllllllllllIlIlllIlIIlIlIll, final Vector2f llllllllllllllllIlIlllIlIIlIlIlI) {
        this(llllllllllllllllIlIlllIlIIlIlIII, llllllllllllllllIlIlllIlIIlIIlll, llllllllllllllllIlIlllIlIIlIlIll, llllllllllllllllIlIlllIlIIlIlIlI, 20);
    }
    
    public Vector2f pointAt(final float llllllllllllllllIlIlllIIllllllIl) {
        final float llllllllllllllllIlIlllIlIIIIIllI = 1.0f - llllllllllllllllIlIlllIIllllllIl;
        final float llllllllllllllllIlIlllIlIIIIIlIl = llllllllllllllllIlIlllIIllllllIl;
        final float llllllllllllllllIlIlllIlIIIIIlII = llllllllllllllllIlIlllIlIIIIIllI * llllllllllllllllIlIlllIlIIIIIllI * llllllllllllllllIlIlllIlIIIIIllI;
        final float llllllllllllllllIlIlllIlIIIIIIll = 3.0f * llllllllllllllllIlIlllIlIIIIIllI * llllllllllllllllIlIlllIlIIIIIllI * llllllllllllllllIlIlllIlIIIIIlIl;
        final float llllllllllllllllIlIlllIlIIIIIIlI = 3.0f * llllllllllllllllIlIlllIlIIIIIllI * llllllllllllllllIlIlllIlIIIIIlIl * llllllllllllllllIlIlllIlIIIIIlIl;
        final float llllllllllllllllIlIlllIlIIIIIIIl = llllllllllllllllIlIlllIlIIIIIlIl * llllllllllllllllIlIlllIlIIIIIlIl * llllllllllllllllIlIlllIlIIIIIlIl;
        final float llllllllllllllllIlIlllIlIIIIIIII = this.p1.x * llllllllllllllllIlIlllIlIIIIIlII + this.c1.x * llllllllllllllllIlIlllIlIIIIIIll + this.c2.x * llllllllllllllllIlIlllIlIIIIIIlI + this.p2.x * llllllllllllllllIlIlllIlIIIIIIIl;
        final float llllllllllllllllIlIlllIIllllllll = this.p1.y * llllllllllllllllIlIlllIlIIIIIlII + this.c1.y * llllllllllllllllIlIlllIlIIIIIIll + this.c2.y * llllllllllllllllIlIlllIlIIIIIIlI + this.p2.y * llllllllllllllllIlIlllIlIIIIIIIl;
        return new Vector2f(llllllllllllllllIlIlllIlIIIIIIII, llllllllllllllllIlIlllIIllllllll);
    }
    
    @Override
    public boolean closed() {
        return false;
    }
    
    @Override
    protected void createPoints() {
        final float llllllllllllllllIlIlllIIlllIlIll = 1.0f / this.segments;
        this.points = new float[(this.segments + 1) * 2];
        for (int llllllllllllllllIlIlllIIlllIllIl = 0; llllllllllllllllIlIlllIIlllIllIl < this.segments + 1; ++llllllllllllllllIlIlllIIlllIllIl) {
            final float llllllllllllllllIlIlllIIlllIllll = llllllllllllllllIlIlllIIlllIllIl * llllllllllllllllIlIlllIIlllIlIll;
            final Vector2f llllllllllllllllIlIlllIIlllIlllI = this.pointAt(llllllllllllllllIlIlllIIlllIllll);
            this.points[llllllllllllllllIlIlllIIlllIllIl * 2] = llllllllllllllllIlIlllIIlllIlllI.x;
            this.points[llllllllllllllllIlIlllIIlllIllIl * 2 + 1] = llllllllllllllllIlIlllIIlllIlllI.y;
        }
    }
}
