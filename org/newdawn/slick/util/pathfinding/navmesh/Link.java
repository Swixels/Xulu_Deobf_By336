package org.newdawn.slick.util.pathfinding.navmesh;

public class Link
{
    private /* synthetic */ Space target;
    private /* synthetic */ float py;
    private /* synthetic */ float px;
    
    public Space getTarget() {
        return this.target;
    }
    
    public float getX() {
        return this.px;
    }
    
    public float distance2(final float lIIIlIllllllllI, final float lIIIlIlllllllIl) {
        final float lIIIllIIIIIIIIl = lIIIlIllllllllI - this.px;
        final float lIIIllIIIIIIIII = lIIIlIlllllllIl - this.py;
        return lIIIllIIIIIIIIl * lIIIllIIIIIIIIl + lIIIllIIIIIIIII * lIIIllIIIIIIIII;
    }
    
    public Link(final float lIIIllIIIIIllII, final float lIIIllIIIIIllll, final Space lIIIllIIIIIlIlI) {
        this.px = lIIIllIIIIIllII;
        this.py = lIIIllIIIIIllll;
        this.target = lIIIllIIIIIlIlI;
    }
    
    public float getY() {
        return this.py;
    }
}
