package org.newdawn.slick.svg;

import org.newdawn.slick.geom.*;

public class Figure
{
    private /* synthetic */ int type;
    private /* synthetic */ Shape shape;
    private /* synthetic */ Transform transform;
    private /* synthetic */ NonGeometricData data;
    
    public Transform getTransform() {
        return this.transform;
    }
    
    public int getType() {
        return this.type;
    }
    
    public Shape getShape() {
        return this.shape;
    }
    
    static {
        ELLIPSE = 1;
        LINE = 2;
        RECTANGLE = 3;
        POLYGON = 5;
        PATH = 4;
    }
    
    public NonGeometricData getData() {
        return this.data;
    }
    
    public Figure(final int lIlIlIIlllllIll, final Shape lIlIlIIlllllIlI, final NonGeometricData lIlIlIIllllIlII, final Transform lIlIlIIllllIIll) {
        this.shape = lIlIlIIlllllIlI;
        this.data = lIlIlIIllllIlII;
        this.type = lIlIlIIlllllIll;
        this.transform = lIlIlIIllllIIll;
    }
}
