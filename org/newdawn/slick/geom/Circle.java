package org.newdawn.slick.geom;

public strictfp class Circle extends Ellipse
{
    public /* synthetic */ float radius;
    
    private strictfp boolean contains(final Line lIIIIIlIlIlllIl) {
        return this.contains(lIIIIIlIlIlllIl.getX1(), lIIIIIlIlIlllIl.getY1()) && this.contains(lIIIIIlIlIlllIl.getX2(), lIIIIIlIlIlllIl.getY2());
    }
    
    @Override
    protected strictfp void findCenter() {
        this.center = new float[2];
        this.center[0] = this.x + this.radius;
        this.center[1] = this.y + this.radius;
    }
    
    public Circle(final float lIIIIlIIIIllIlI, final float lIIIIlIIIIllIIl, final float lIIIIlIIIIlllII) {
        this(lIIIIlIIIIllIlI, lIIIIlIIIIllIIl, lIIIIlIIIIlllII, 50);
    }
    
    @Override
    protected strictfp void calculateRadius() {
        this.boundingCircleRadius = this.radius;
    }
    
    @Override
    public strictfp boolean intersects(final Shape lIIIIIlIlllllll) {
        if (lIIIIIlIlllllll instanceof Circle) {
            final Circle lIIIIIllIIIIlll = (Circle)lIIIIIlIlllllll;
            float lIIIIIllIIIIllI = this.getRadius() + lIIIIIllIIIIlll.getRadius();
            if (Math.abs(lIIIIIllIIIIlll.getCenterX() - this.getCenterX()) > lIIIIIllIIIIllI) {
                return false;
            }
            if (Math.abs(lIIIIIllIIIIlll.getCenterY() - this.getCenterY()) > lIIIIIllIIIIllI) {
                return false;
            }
            lIIIIIllIIIIllI *= lIIIIIllIIIIllI;
            final float lIIIIIllIIIIlIl = Math.abs(lIIIIIllIIIIlll.getCenterX() - this.getCenterX());
            final float lIIIIIllIIIIlII = Math.abs(lIIIIIllIIIIlll.getCenterY() - this.getCenterY());
            return lIIIIIllIIIIllI >= lIIIIIllIIIIlIl * lIIIIIllIIIIlIl + lIIIIIllIIIIlII * lIIIIIllIIIIlII;
        }
        else {
            if (lIIIIIlIlllllll instanceof Rectangle) {
                return this.intersects((Rectangle)lIIIIIlIlllllll);
            }
            return super.intersects(lIIIIIlIlllllll);
        }
    }
    
    public strictfp float getRadius() {
        return this.radius;
    }
    
    private strictfp boolean intersects(final Line lIIIIIIllllIIII) {
        final Vector2f lIIIIIIlllIllll = new Vector2f(lIIIIIIllllIIII.getX1(), lIIIIIIllllIIII.getY1());
        final Vector2f lIIIIIIlllIlllI = new Vector2f(lIIIIIIllllIIII.getX2(), lIIIIIIllllIIII.getY2());
        final Vector2f lIIIIIIlllIllIl = new Vector2f(this.getCenterX(), this.getCenterY());
        final Vector2f lIIIIIIlllIlIll = lIIIIIIlllIlllI.copy().sub(lIIIIIIlllIllll);
        final Vector2f lIIIIIIlllIlIlI = lIIIIIIlllIllIl.copy().sub(lIIIIIIlllIllll);
        final float lIIIIIIlllIlIIl = lIIIIIIlllIlIll.length();
        final float lIIIIIIlllIlIII = lIIIIIIlllIlIlI.dot(lIIIIIIlllIlIll) / lIIIIIIlllIlIIl;
        Vector2f lIIIIIIlllIllII = null;
        if (lIIIIIIlllIlIII < 0.0f) {
            final Vector2f lIIIIIIllllIlll = lIIIIIIlllIllll;
        }
        else if (lIIIIIIlllIlIII > lIIIIIIlllIlIIl) {
            final Vector2f lIIIIIIllllIlIl = lIIIIIIlllIlllI;
        }
        else {
            final Vector2f lIIIIIIllllIIll = lIIIIIIlllIlIll.copy().scale(lIIIIIIlllIlIII / lIIIIIIlllIlIIl);
            lIIIIIIlllIllII = lIIIIIIlllIllll.copy().add(lIIIIIIllllIIll);
        }
        final boolean lIIIIIIlllIIlll = lIIIIIIlllIllIl.copy().sub(lIIIIIIlllIllII).lengthSquared() <= this.getRadius() * this.getRadius();
        return lIIIIIIlllIIlll;
    }
    
    @Override
    public strictfp float getCenterY() {
        return this.getY() + this.radius;
    }
    
    private strictfp boolean intersects(final Rectangle lIIIIIlIIllIIIl) {
        final Rectangle lIIIIIlIIlIllll = lIIIIIlIIllIIIl;
        final Circle lIIIIIlIIlIllIl = this;
        if (lIIIIIlIIlIllll.contains(this.x + this.radius, this.y + this.radius)) {
            return true;
        }
        final float lIIIIIlIIlIlIll = lIIIIIlIIlIllll.getX();
        final float lIIIIIlIIlIlIIl = lIIIIIlIIlIllll.getY();
        final float lIIIIIlIIlIIlll = lIIIIIlIIlIllll.getX() + lIIIIIlIIlIllll.getWidth();
        final float lIIIIIlIIlIIlIl = lIIIIIlIIlIllll.getY() + lIIIIIlIIlIllll.getHeight();
        final Line[] lIIIIIlIIlIIlII = { new Line(lIIIIIlIIlIlIll, lIIIIIlIIlIlIIl, lIIIIIlIIlIIlll, lIIIIIlIIlIlIIl), new Line(lIIIIIlIIlIIlll, lIIIIIlIIlIlIIl, lIIIIIlIIlIIlll, lIIIIIlIIlIIlIl), new Line(lIIIIIlIIlIIlll, lIIIIIlIIlIIlIl, lIIIIIlIIlIlIll, lIIIIIlIIlIIlIl), new Line(lIIIIIlIIlIlIll, lIIIIIlIIlIIlIl, lIIIIIlIIlIlIll, lIIIIIlIIlIlIIl) };
        final float lIIIIIlIIlIIIll = lIIIIIlIIlIllIl.getRadius() * lIIIIIlIIlIllIl.getRadius();
        final Vector2f lIIIIIlIIlIIIlI = new Vector2f(lIIIIIlIIlIllIl.getCenterX(), lIIIIIlIIlIllIl.getCenterY());
        for (int lIIIIIlIIllIlII = 0; lIIIIIlIIllIlII < 4; ++lIIIIIlIIllIlII) {
            final float lIIIIIlIIllIllI = lIIIIIlIIlIIlII[lIIIIIlIIllIlII].distanceSquared(lIIIIIlIIlIIIlI);
            if (lIIIIIlIIllIllI < lIIIIIlIIlIIIll) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public strictfp float getCenterX() {
        return this.getX() + this.radius;
    }
    
    @Override
    public strictfp boolean contains(final float lIIIIIlIllIlIIl, final float lIIIIIlIllIlIII) {
        final float lIIIIIlIllIIlll = lIIIIIlIllIlIIl - this.getCenterX();
        final float lIIIIIlIllIIllI = lIIIIIlIllIlIII - this.getCenterY();
        return lIIIIIlIllIIlll * lIIIIIlIllIIlll + lIIIIIlIllIIllI * lIIIIIlIllIIllI < this.getRadius() * this.getRadius();
    }
    
    public strictfp void setRadius(final float lIIIIIllIlIllll) {
        if (lIIIIIllIlIllll != this.radius) {
            this.pointsDirty = true;
            this.radius = lIIIIIllIlIllll;
            this.setRadii(lIIIIIllIlIllll, lIIIIIllIlIllll);
        }
    }
    
    @Override
    public strictfp float[] getCenter() {
        return new float[] { this.getCenterX(), this.getCenterY() };
    }
    
    public Circle(final float lIIIIIllllllIII, final float lIIIIIlllllIlll, final float lIIIIIlllllllII, final int lIIIIIllllllIll) {
        super(lIIIIIllllllIII, lIIIIIlllllIlll, lIIIIIlllllllII, lIIIIIlllllllII, lIIIIIllllllIll);
        this.x = lIIIIIllllllIII - lIIIIIlllllllII;
        this.y = lIIIIIlllllIlll - lIIIIIlllllllII;
        this.radius = lIIIIIlllllllII;
        this.boundingCircleRadius = lIIIIIlllllllII;
    }
}
