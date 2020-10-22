package org.newdawn.slick.geom;

public class Rectangle extends Shape
{
    protected /* synthetic */ float width;
    protected /* synthetic */ float height;
    
    private boolean intersects(final Circle llllllllllllllllIlllIlIlIIIlIIlI) {
        return llllllllllllllllIlllIlIlIIIlIIlI.intersects((Shape)this);
    }
    
    public void setBounds(final Rectangle llllllllllllllllIlllIlIllllIlllI) {
        this.setBounds(llllllllllllllllIlllIlIllllIlllI.getX(), llllllllllllllllIlllIlIllllIlllI.getY(), llllllllllllllllIlllIlIllllIlllI.getWidth(), llllllllllllllllIlllIlIllllIlllI.getHeight());
    }
    
    @Override
    public boolean intersects(final Shape llllllllllllllllIlllIlIlIIllllII) {
        if (llllllllllllllllIlllIlIlIIllllII instanceof Rectangle) {
            final Rectangle llllllllllllllllIlllIlIlIlIIIIII = (Rectangle)llllllllllllllllIlllIlIlIIllllII;
            return this.x <= llllllllllllllllIlllIlIlIlIIIIII.x + llllllllllllllllIlllIlIlIlIIIIII.width && this.x + this.width >= llllllllllllllllIlllIlIlIlIIIIII.x && this.y <= llllllllllllllllIlllIlIlIlIIIIII.y + llllllllllllllllIlllIlIlIlIIIIII.height && this.y + this.height >= llllllllllllllllIlllIlIlIlIIIIII.y;
        }
        if (llllllllllllllllIlllIlIlIIllllII instanceof Circle) {
            return this.intersects((Circle)llllllllllllllllIlllIlIlIIllllII);
        }
        return super.intersects(llllllllllllllllIlllIlIlIIllllII);
    }
    
    @Override
    public float getHeight() {
        return this.height;
    }
    
    @Override
    public float getWidth() {
        return this.width;
    }
    
    public void setBounds(final float llllllllllllllllIlllIlIlllIllIII, final float llllllllllllllllIlllIlIlllIIlIIl, final float llllllllllllllllIlllIlIlllIIIlll, final float llllllllllllllllIlllIlIlllIIIlIl) {
        this.setX(llllllllllllllllIlllIlIlllIllIII);
        this.setY(llllllllllllllllIlllIlIlllIIlIIl);
        this.setSize(llllllllllllllllIlllIlIlllIIIlll, llllllllllllllllIlllIlIlllIIIlIl);
    }
    
    public Rectangle(final float llllllllllllllllIlllIllIIIllllIl, final float llllllllllllllllIlllIllIIIlllIll, final float llllllllllllllllIlllIllIIIlIlllI, final float llllllllllllllllIlllIllIIIllIlll) {
        this.x = llllllllllllllllIlllIllIIIllllIl;
        this.y = llllllllllllllllIlllIllIIIlllIll;
        this.width = llllllllllllllllIlllIllIIIlIlllI;
        this.height = llllllllllllllllIlllIllIIIllIlll;
        this.maxX = llllllllllllllllIlllIllIIIllllIl + llllllllllllllllIlllIllIIIlIlllI;
        this.maxY = llllllllllllllllIlllIllIIIlllIll + llllllllllllllllIlllIllIIIllIlll;
        this.checkPoints();
    }
    
    public void setHeight(final float llllllllllllllllIlllIlIlIlIlIlll) {
        if (llllllllllllllllIlllIlIlIlIlIlll != this.height) {
            this.pointsDirty = true;
            this.height = llllllllllllllllIlllIlIlIlIlIlll;
            this.maxY = this.y + llllllllllllllllIlllIlIlIlIlIlll;
        }
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("[Rectangle ").append(this.width).append("x").append(this.height).append("]"));
    }
    
    public void setWidth(final float llllllllllllllllIlllIlIlIlIlllIl) {
        if (llllllllllllllllIlllIlIlIlIlllIl != this.width) {
            this.pointsDirty = true;
            this.width = llllllllllllllllIlllIlIlIlIlllIl;
            this.maxX = this.x + llllllllllllllllIlllIlIlIlIlllIl;
        }
    }
    
    @Override
    public boolean contains(final float llllllllllllllllIlllIllIIIIIIlll, final float llllllllllllllllIlllIllIIIIIIlII) {
        return llllllllllllllllIlllIllIIIIIIlll > this.getX() && llllllllllllllllIlllIllIIIIIIlII > this.getY() && llllllllllllllllIlllIllIIIIIIlll < this.maxX && llllllllllllllllIlllIllIIIIIIlII < this.maxY;
    }
    
    @Override
    protected void createPoints() {
        final float llllllllllllllllIlllIlIlIIlIIlll = this.width;
        final float llllllllllllllllIlllIlIlIIlIIlIl = this.height;
        this.points = new float[8];
        this.points[0] = this.x;
        this.points[1] = this.y;
        this.points[2] = this.x + llllllllllllllllIlllIlIlIIlIIlll;
        this.points[3] = this.y;
        this.points[4] = this.x + llllllllllllllllIlllIlIlIIlIIlll;
        this.points[5] = this.y + llllllllllllllllIlllIlIlIIlIIlIl;
        this.points[6] = this.x;
        this.points[7] = this.y + llllllllllllllllIlllIlIlIIlIIlIl;
        this.maxX = this.points[2];
        this.maxY = this.points[5];
        this.minX = this.points[0];
        this.minY = this.points[1];
        this.findCenter();
        this.calculateRadius();
    }
    
    public void scaleGrow(final float llllllllllllllllIlllIlIlIllIlIll, final float llllllllllllllllIlllIlIlIllIlIIl) {
        this.grow(this.getWidth() * (llllllllllllllllIlllIlIlIllIlIll - 1.0f), this.getHeight() * (llllllllllllllllIlllIlIlIllIlIIl - 1.0f));
    }
    
    public void grow(final float llllllllllllllllIlllIlIllIIIIlII, final float llllllllllllllllIlllIlIllIIIIIIl) {
        this.setX(this.getX() - llllllllllllllllIlllIlIllIIIIlII);
        this.setY(this.getY() - llllllllllllllllIlllIlIllIIIIIIl);
        this.setWidth(this.getWidth() + llllllllllllllllIlllIlIllIIIIlII * 2.0f);
        this.setHeight(this.getHeight() + llllllllllllllllIlllIlIllIIIIIIl * 2.0f);
    }
    
    public static boolean contains(final float llllllllllllllllIlllIlIIllIllIll, final float llllllllllllllllIlllIlIIllIllIIl, final float llllllllllllllllIlllIlIIllIlIlll, final float llllllllllllllllIlllIlIIllIlIlIl, final float llllllllllllllllIlllIlIIllIlIIll, final float llllllllllllllllIlllIlIIllIlIIIl) {
        return llllllllllllllllIlllIlIIllIllIll >= llllllllllllllllIlllIlIIllIlIlll && llllllllllllllllIlllIlIIllIllIIl >= llllllllllllllllIlllIlIIllIlIlIl && llllllllllllllllIlllIlIIllIllIll <= llllllllllllllllIlllIlIIllIlIlll + llllllllllllllllIlllIlIIllIlIIll && llllllllllllllllIlllIlIIllIllIIl <= llllllllllllllllIlllIlIIllIlIlIl + llllllllllllllllIlllIlIIllIlIIIl;
    }
    
    public void setSize(final float llllllllllllllllIlllIlIllIlIlIll, final float llllllllllllllllIlllIlIllIllIIII) {
        this.setWidth(llllllllllllllllIlllIlIllIlIlIll);
        this.setHeight(llllllllllllllllIlllIlIllIllIIII);
    }
    
    @Override
    public Shape transform(final Transform llllllllllllllllIlllIlIIlIIllIll) {
        this.checkPoints();
        final Polygon llllllllllllllllIlllIlIIlIlIIIIl = new Polygon();
        final float[] llllllllllllllllIlllIlIIlIIlllll = new float[this.points.length];
        llllllllllllllllIlllIlIIlIIllIll.transform(this.points, 0, llllllllllllllllIlllIlIIlIIlllll, 0, this.points.length / 2);
        llllllllllllllllIlllIlIIlIlIIIIl.points = llllllllllllllllIlllIlIIlIIlllll;
        llllllllllllllllIlllIlIIlIlIIIIl.findCenter();
        llllllllllllllllIlllIlIIlIlIIIIl.checkPoints();
        return llllllllllllllllIlllIlIIlIlIIIIl;
    }
}
