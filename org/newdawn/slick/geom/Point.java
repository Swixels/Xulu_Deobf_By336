package org.newdawn.slick.geom;

public class Point extends Shape
{
    public Point(final float lIIlIlIlllllllI, final float lIIlIllIIIIIIII) {
        this.x = lIIlIlIlllllllI;
        this.y = lIIlIllIIIIIIII;
        this.checkPoints();
    }
    
    @Override
    public Shape transform(final Transform lIIlIlIllllIlIl) {
        final float[] lIIlIlIllllIlII = new float[this.points.length];
        lIIlIlIllllIlIl.transform(this.points, 0, lIIlIlIllllIlII, 0, this.points.length / 2);
        return new Point(this.points[0], this.points[1]);
    }
    
    @Override
    protected void createPoints() {
        this.points = new float[2];
        this.points[0] = this.getX();
        this.points[1] = this.getY();
        this.maxX = this.x;
        this.maxY = this.y;
        this.minX = this.x;
        this.minY = this.y;
        this.findCenter();
        this.calculateRadius();
    }
    
    @Override
    protected void calculateRadius() {
        this.boundingCircleRadius = 0.0f;
    }
    
    @Override
    protected void findCenter() {
        this.center = new float[2];
        this.center[0] = this.points[0];
        this.center[1] = this.points[1];
    }
}
