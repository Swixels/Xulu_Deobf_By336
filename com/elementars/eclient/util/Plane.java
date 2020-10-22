package com.elementars.eclient.util;

public class Plane
{
    private final /* synthetic */ double y;
    private final /* synthetic */ double x;
    private final /* synthetic */ boolean visible;
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public Plane(final double lIIIIIIlIlllIIl, final double lIIIIIIlIllIlII, final boolean lIIIIIIlIllIIll) {
        this.x = lIIIIIIlIlllIIl;
        this.y = lIIIIIIlIllIlII;
        this.visible = lIIIIIIlIllIIll;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
}
