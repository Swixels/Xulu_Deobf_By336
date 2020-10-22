package org.newdawn.slick.geom;

public class Line extends Shape
{
    private /* synthetic */ boolean outerEdge;
    private /* synthetic */ Vector2f loc;
    private /* synthetic */ Vector2f other;
    private /* synthetic */ Vector2f proj;
    private /* synthetic */ Vector2f end;
    private /* synthetic */ Vector2f closest;
    private /* synthetic */ Vector2f v2;
    private /* synthetic */ boolean innerEdge;
    private /* synthetic */ float lenSquared;
    private /* synthetic */ Vector2f vec;
    private /* synthetic */ Vector2f v;
    private /* synthetic */ Vector2f start;
    
    public Line(final float lllllllllllllllllllIlIIllIIlIIIl, final float lllllllllllllllllllIlIIllIIlIIII, final float lllllllllllllllllllIlIIllIIIlIlI, final float lllllllllllllllllllIlIIllIIIlllI) {
        this(new Vector2f(lllllllllllllllllllIlIIllIIlIIIl, lllllllllllllllllllIlIIllIIlIIII), new Vector2f(lllllllllllllllllllIlIIllIIIlIlI, lllllllllllllllllllIlIIllIIIlllI));
    }
    
    @Override
    public float getY() {
        return this.getY1();
    }
    
    public float getX1() {
        return this.start.getX();
    }
    
    public Vector2f getEnd() {
        return this.end;
    }
    
    public float length() {
        return this.vec.length();
    }
    
    public void getClosestPoint(final Vector2f lllllllllllllllllllIlIIIllllllIl, final Vector2f lllllllllllllllllllIlIIIllllllII) {
        this.loc.set(lllllllllllllllllllIlIIIllllllIl);
        this.loc.sub(this.start);
        float lllllllllllllllllllIlIIIllllllll = this.vec.dot(this.loc);
        lllllllllllllllllllIlIIIllllllll /= this.vec.lengthSquared();
        if (lllllllllllllllllllIlIIIllllllll < 0.0f) {
            lllllllllllllllllllIlIIIllllllII.set(this.start);
            return;
        }
        if (lllllllllllllllllllIlIIIllllllll > 1.0f) {
            lllllllllllllllllllIlIIIllllllII.set(this.end);
            return;
        }
        lllllllllllllllllllIlIIIllllllII.x = this.start.getX() + lllllllllllllllllllIlIIIllllllll * this.vec.getX();
        lllllllllllllllllllIlIIIllllllII.y = this.start.getY() + lllllllllllllllllllIlIIIllllllll * this.vec.getY();
    }
    
    public Line(final float[] lllllllllllllllllllIlIIlIlllIlII, final float[] lllllllllllllllllllIlIIlIlllIIII) {
        this.loc = new Vector2f(0.0f, 0.0f);
        this.v = new Vector2f(0.0f, 0.0f);
        this.v2 = new Vector2f(0.0f, 0.0f);
        this.proj = new Vector2f(0.0f, 0.0f);
        this.closest = new Vector2f(0.0f, 0.0f);
        this.other = new Vector2f(0.0f, 0.0f);
        this.outerEdge = true;
        this.innerEdge = true;
        this.set(lllllllllllllllllllIlIIlIlllIlII, lllllllllllllllllllIlIIlIlllIIII);
    }
    
    public float distanceSquared(final Vector2f lllllllllllllllllllIlIIlIIIIlIll) {
        this.getClosestPoint(lllllllllllllllllllIlIIlIIIIlIll, this.closest);
        this.closest.sub(lllllllllllllllllllIlIIlIIIIlIll);
        final float lllllllllllllllllllIlIIlIIIIlIlI = this.closest.lengthSquared();
        return lllllllllllllllllllIlIIlIIIIlIlI;
    }
    
    @Override
    public float getX() {
        return this.getX1();
    }
    
    public float getDY() {
        return this.end.getY() - this.start.getY();
    }
    
    public float getY1() {
        return this.start.getY();
    }
    
    public float getX2() {
        return this.end.getX();
    }
    
    @Override
    public boolean intersects(final Shape lllllllllllllllllllIlIIIlIlIlIIl) {
        if (lllllllllllllllllllIlIIIlIlIlIIl instanceof Circle) {
            return lllllllllllllllllllIlIIIlIlIlIIl.intersects(this);
        }
        return super.intersects(lllllllllllllllllllIlIIIlIlIlIIl);
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("[Line ").append(this.start).append(",").append(this.end).append("]"));
    }
    
    @Override
    protected void createPoints() {
        this.points = new float[4];
        this.points[0] = this.getX1();
        this.points[1] = this.getY1();
        this.points[2] = this.getX2();
        this.points[3] = this.getY2();
    }
    
    public void set(final float[] lllllllllllllllllllIlIIlIllIIIlI, final float[] lllllllllllllllllllIlIIlIlIllllI) {
        this.set(lllllllllllllllllllIlIIlIllIIIlI[0], lllllllllllllllllllIlIIlIllIIIlI[1], lllllllllllllllllllIlIIlIlIllllI[0], lllllllllllllllllllIlIIlIlIllllI[1]);
    }
    
    public float distance(final Vector2f lllllllllllllllllllIlIIlIIIlIllI) {
        return (float)Math.sqrt(this.distanceSquared(lllllllllllllllllllIlIIlIIIlIllI));
    }
    
    public Line(final Vector2f lllllllllllllllllllIlIIlIllIlIII, final Vector2f lllllllllllllllllllIlIIlIllIlIlI) {
        this.loc = new Vector2f(0.0f, 0.0f);
        this.v = new Vector2f(0.0f, 0.0f);
        this.v2 = new Vector2f(0.0f, 0.0f);
        this.proj = new Vector2f(0.0f, 0.0f);
        this.closest = new Vector2f(0.0f, 0.0f);
        this.other = new Vector2f(0.0f, 0.0f);
        this.outerEdge = true;
        this.innerEdge = true;
        this.set(lllllllllllllllllllIlIIlIllIlIII, lllllllllllllllllllIlIIlIllIlIlI);
    }
    
    public Line(final float lllllllllllllllllllIlIIlIlllllII, final float lllllllllllllllllllIlIIlIllllIll, final float lllllllllllllllllllIlIIlIllllIlI, final float lllllllllllllllllllIlIIlIlllllll, final boolean lllllllllllllllllllIlIIlIllllllI) {
        this(new Vector2f(lllllllllllllllllllIlIIlIlllllII, lllllllllllllllllllIlIIlIllllIll), new Vector2f(lllllllllllllllllllIlIIlIlllllII + lllllllllllllllllllIlIIlIllllIlI, lllllllllllllllllllIlIIlIllllIll + lllllllllllllllllllIlIIlIlllllll));
    }
    
    public Line(final float lllllllllllllllllllIlIIllIIlllII, final float lllllllllllllllllllIlIIllIIllIII) {
        this(lllllllllllllllllllIlIIllIIlllII, lllllllllllllllllllIlIIllIIllIII, true, true);
    }
    
    public Line(final float lllllllllllllllllllIlIIllIlIIIlI, final float lllllllllllllllllllIlIIllIlIIIIl, final boolean lllllllllllllllllllIlIIllIlIIlIl, final boolean lllllllllllllllllllIlIIllIlIIlII) {
        this(0.0f, 0.0f, lllllllllllllllllllIlIIllIlIIIlI, lllllllllllllllllllIlIIllIlIIIIl);
    }
    
    public float getDX() {
        return this.end.getX() - this.start.getX();
    }
    
    public void set(final float lllllllllllllllllllIlIIlIIlllIIl, final float lllllllllllllllllllIlIIlIIllllll, final float lllllllllllllllllllIlIIlIIlllllI, final float lllllllllllllllllllIlIIlIIllIllI) {
        super.pointsDirty = true;
        this.start.set(lllllllllllllllllllIlIIlIIlllIIl, lllllllllllllllllllIlIIlIIllllll);
        this.end.set(lllllllllllllllllllIlIIlIIlllllI, lllllllllllllllllllIlIIlIIllIllI);
        final float lllllllllllllllllllIlIIlIIllllII = lllllllllllllllllllIlIIlIIlllllI - lllllllllllllllllllIlIIlIIlllIIl;
        final float lllllllllllllllllllIlIIlIIlllIll = lllllllllllllllllllIlIIlIIllIllI - lllllllllllllllllllIlIIlIIllllll;
        this.vec.set(lllllllllllllllllllIlIIlIIllllII, lllllllllllllllllllIlIIlIIlllIll);
        this.lenSquared = lllllllllllllllllllIlIIlIIllllII * lllllllllllllllllllIlIIlIIllllII + lllllllllllllllllllIlIIlIIlllIll * lllllllllllllllllllIlIIlIIlllIll;
    }
    
    public float getY2() {
        return this.end.getY();
    }
    
    @Override
    public Shape transform(final Transform lllllllllllllllllllIlIIIlIllIlII) {
        final float[] lllllllllllllllllllIlIIIlIllIIll = new float[4];
        this.createPoints();
        lllllllllllllllllllIlIIIlIllIlII.transform(this.points, 0, lllllllllllllllllllIlIIIlIllIIll, 0, 2);
        return new Line(lllllllllllllllllllIlIIIlIllIIll[0], lllllllllllllllllllIlIIIlIllIIll[1], lllllllllllllllllllIlIIIlIllIIll[2], lllllllllllllllllllIlIIIlIllIIll[3]);
    }
    
    public boolean on(final Vector2f lllllllllllllllllllIlIIlIIIlIIII) {
        this.getClosestPoint(lllllllllllllllllllIlIIlIIIlIIII, this.closest);
        return lllllllllllllllllllIlIIlIIIlIIII.equals(this.closest);
    }
    
    public Vector2f intersect(final Line lllllllllllllllllllIlIIIllllIIlI) {
        return this.intersect(lllllllllllllllllllIlIIIllllIIlI, false);
    }
    
    public void set(final Vector2f lllllllllllllllllllIlIIlIlIIlIlI, final Vector2f lllllllllllllllllllIlIIlIlIIlIIl) {
        super.pointsDirty = true;
        if (this.start == null) {
            this.start = new Vector2f();
        }
        this.start.set(lllllllllllllllllllIlIIlIlIIlIlI);
        if (this.end == null) {
            this.end = new Vector2f();
        }
        this.end.set(lllllllllllllllllllIlIIlIlIIlIIl);
        this.vec = new Vector2f(lllllllllllllllllllIlIIlIlIIlIIl);
        this.vec.sub(lllllllllllllllllllIlIIlIlIIlIlI);
        this.lenSquared = this.vec.lengthSquared();
    }
    
    public boolean intersect(final Line lllllllllllllllllllIlIIIllIIlIII, final boolean lllllllllllllllllllIlIIIllIIIlll, final Vector2f lllllllllllllllllllIlIIIllIlIlII) {
        final float lllllllllllllllllllIlIIIllIlIIll = this.end.getX() - this.start.getX();
        final float lllllllllllllllllllIlIIIllIlIIlI = lllllllllllllllllllIlIIIllIIlIII.end.getX() - lllllllllllllllllllIlIIIllIIlIII.start.getX();
        final float lllllllllllllllllllIlIIIllIlIIIl = this.end.getY() - this.start.getY();
        final float lllllllllllllllllllIlIIIllIlIIII = lllllllllllllllllllIlIIIllIIlIII.end.getY() - lllllllllllllllllllIlIIIllIIlIII.start.getY();
        final float lllllllllllllllllllIlIIIllIIllll = lllllllllllllllllllIlIIIllIlIIII * lllllllllllllllllllIlIIIllIlIIll - lllllllllllllllllllIlIIIllIlIIlI * lllllllllllllllllllIlIIIllIlIIIl;
        if (lllllllllllllllllllIlIIIllIIllll == 0.0f) {
            return false;
        }
        float lllllllllllllllllllIlIIIllIIlllI = lllllllllllllllllllIlIIIllIlIIlI * (this.start.getY() - lllllllllllllllllllIlIIIllIIlIII.start.getY()) - lllllllllllllllllllIlIIIllIlIIII * (this.start.getX() - lllllllllllllllllllIlIIIllIIlIII.start.getX());
        lllllllllllllllllllIlIIIllIIlllI /= lllllllllllllllllllIlIIIllIIllll;
        float lllllllllllllllllllIlIIIllIIllIl = lllllllllllllllllllIlIIIllIlIIll * (this.start.getY() - lllllllllllllllllllIlIIIllIIlIII.start.getY()) - lllllllllllllllllllIlIIIllIlIIIl * (this.start.getX() - lllllllllllllllllllIlIIIllIIlIII.start.getX());
        lllllllllllllllllllIlIIIllIIllIl /= lllllllllllllllllllIlIIIllIIllll;
        if (lllllllllllllllllllIlIIIllIIIlll && (lllllllllllllllllllIlIIIllIIlllI < 0.0f || lllllllllllllllllllIlIIIllIIlllI > 1.0f || lllllllllllllllllllIlIIIllIIllIl < 0.0f || lllllllllllllllllllIlIIIllIIllIl > 1.0f)) {
            return false;
        }
        final float lllllllllllllllllllIlIIIllIIllII = lllllllllllllllllllIlIIIllIIlllI;
        final float lllllllllllllllllllIlIIIllIIlIll = this.start.getX() + lllllllllllllllllllIlIIIllIIllII * (this.end.getX() - this.start.getX());
        final float lllllllllllllllllllIlIIIllIIlIlI = this.start.getY() + lllllllllllllllllllIlIIIllIIllII * (this.end.getY() - this.start.getY());
        lllllllllllllllllllIlIIIllIlIlII.set(lllllllllllllllllllIlIIIllIIlIll, lllllllllllllllllllIlIIIllIIlIlI);
        return true;
    }
    
    public float lengthSquared() {
        return this.vec.lengthSquared();
    }
    
    public Vector2f intersect(final Line lllllllllllllllllllIlIIIlllIllII, final boolean lllllllllllllllllllIlIIIlllIIlll) {
        final Vector2f lllllllllllllllllllIlIIIlllIlIlI = new Vector2f();
        if (!this.intersect(lllllllllllllllllllIlIIIlllIllII, lllllllllllllllllllIlIIIlllIIlll, lllllllllllllllllllIlIIIlllIlIlI)) {
            return null;
        }
        return lllllllllllllllllllIlIIIlllIlIlI;
    }
    
    @Override
    public boolean closed() {
        return false;
    }
    
    public Vector2f getStart() {
        return this.start;
    }
}
