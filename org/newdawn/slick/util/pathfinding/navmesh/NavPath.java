package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.*;

public class NavPath
{
    private /* synthetic */ ArrayList links;
    
    public float getX(final int lllllllllllllllllIIlIllIllIlIlll) {
        return this.links.get(lllllllllllllllllIIlIllIllIlIlll).getX();
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("[Path length=").append(this.length()).append("]"));
    }
    
    public float getY(final int lllllllllllllllllIIlIllIllIIlllI) {
        return this.links.get(lllllllllllllllllIIlIllIllIIlllI).getY();
    }
    
    public void remove(final int lllllllllllllllllIIlIllIlIlllIII) {
        this.links.remove(lllllllllllllllllIIlIllIlIlllIII);
    }
    
    public void push(final Link lllllllllllllllllIIlIllIlllIIlll) {
        this.links.add(lllllllllllllllllIIlIllIlllIIlll);
    }
    
    public NavPath() {
        this.links = new ArrayList();
    }
    
    public int length() {
        return this.links.size();
    }
}
