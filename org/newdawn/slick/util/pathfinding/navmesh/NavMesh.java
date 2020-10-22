package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.*;

public class NavMesh
{
    private /* synthetic */ ArrayList spaces;
    
    public void addSpace(final Space lIIllIIIlIlIllI) {
        this.spaces.add(lIIllIIIlIlIllI);
    }
    
    private void optimize(final NavPath lIIlIllllIIlIll) {
        int lIIlIllllIIllIl = 0;
        while (lIIlIllllIIllIl < lIIlIllllIIlIll.length() - 2) {
            final float lIIlIllllIlIIll = lIIlIllllIIlIll.getX(lIIlIllllIIllIl);
            final float lIIlIllllIlIIlI = lIIlIllllIIlIll.getY(lIIlIllllIIllIl);
            final float lIIlIllllIlIIIl = lIIlIllllIIlIll.getX(lIIlIllllIIllIl + 2);
            final float lIIlIllllIlIIII = lIIlIllllIIlIll.getY(lIIlIllllIIllIl + 2);
            if (this.isClear(lIIlIllllIlIIll, lIIlIllllIlIIlI, lIIlIllllIlIIIl, lIIlIllllIlIIII, 0.1f)) {
                lIIlIllllIIlIll.remove(lIIlIllllIIllIl + 1);
            }
            else {
                ++lIIlIllllIIllIl;
            }
        }
    }
    
    public NavMesh(final ArrayList lIIllIIIllIIIll) {
        this.spaces = new ArrayList();
        this.spaces.addAll(lIIllIIIllIIIll);
    }
    
    private boolean isClear(final float lIIlIllllllIIII, final float lIIlIlllllIllll, final float lIIlIlllllIIlII, final float lIIlIlllllIIIll, final float lIIlIlllllIllII) {
        float lIIlIlllllIlIll = lIIlIlllllIIlII - lIIlIllllllIIII;
        float lIIlIlllllIlIlI = lIIlIlllllIIIll - lIIlIlllllIllll;
        final float lIIlIlllllIlIIl = (float)Math.sqrt(lIIlIlllllIlIll * lIIlIlllllIlIll + lIIlIlllllIlIlI * lIIlIlllllIlIlI);
        lIIlIlllllIlIll *= lIIlIlllllIllII;
        lIIlIlllllIlIll /= lIIlIlllllIlIIl;
        lIIlIlllllIlIlI *= lIIlIlllllIllII;
        lIIlIlllllIlIlI /= lIIlIlllllIlIIl;
        for (int lIIlIlllllIlIII = (int)(lIIlIlllllIlIIl / lIIlIlllllIllII), lIIlIllllllIIlI = 0; lIIlIllllllIIlI < lIIlIlllllIlIII; ++lIIlIllllllIIlI) {
            final float lIIlIllllllIlII = lIIlIllllllIIII + lIIlIlllllIlIll * lIIlIllllllIIlI;
            final float lIIlIllllllIIll = lIIlIlllllIllll + lIIlIlllllIlIlI * lIIlIllllllIIlI;
            if (this.findSpace(lIIlIllllllIlII, lIIlIllllllIIll) == null) {
                return false;
            }
        }
        return true;
    }
    
    public Space findSpace(final float lIIllIIIlIIlIII, final float lIIllIIIlIIlIlI) {
        for (int lIIllIIIlIIllIl = 0; lIIllIIIlIIllIl < this.spaces.size(); ++lIIllIIIlIIllIl) {
            final Space lIIllIIIlIIlllI = this.getSpace(lIIllIIIlIIllIl);
            if (lIIllIIIlIIlllI.contains(lIIllIIIlIIlIII, lIIllIIIlIIlIlI)) {
                return lIIllIIIlIIlllI;
            }
        }
        return null;
    }
    
    public int getSpaceCount() {
        return this.spaces.size();
    }
    
    public Space getSpace(final int lIIllIIIlIllIlI) {
        return this.spaces.get(lIIllIIIlIllIlI);
    }
    
    public NavPath findPath(final float lIIllIIIIlIIIlI, final float lIIllIIIIIllIII, final float lIIllIIIIlIIIII, final float lIIllIIIIIlIlIl, final boolean lIIllIIIIIllllI) {
        final Space lIIllIIIIIlllIl = this.findSpace(lIIllIIIIlIIIlI, lIIllIIIIIllIII);
        final Space lIIllIIIIIlllII = this.findSpace(lIIllIIIIlIIIII, lIIllIIIIIlIlIl);
        if (lIIllIIIIIlllIl == null || lIIllIIIIIlllII == null) {
            return null;
        }
        for (int lIIllIIIIlIIlII = 0; lIIllIIIIlIIlII < this.spaces.size(); ++lIIllIIIIlIIlII) {
            this.spaces.get(lIIllIIIIlIIlII).clearCost();
        }
        lIIllIIIIIlllII.fill(lIIllIIIIIlllIl, lIIllIIIIlIIIII, lIIllIIIIIlIlIl, 0.0f);
        if (lIIllIIIIIlllII.getCost() == Float.MAX_VALUE) {
            return null;
        }
        if (lIIllIIIIIlllIl.getCost() == Float.MAX_VALUE) {
            return null;
        }
        final NavPath lIIllIIIIIllIll = new NavPath();
        lIIllIIIIIllIll.push(new Link(lIIllIIIIlIIIlI, lIIllIIIIIllIII, null));
        if (lIIllIIIIIlllIl.pickLowestCost(lIIllIIIIIlllII, lIIllIIIIIllIll)) {
            lIIllIIIIIllIll.push(new Link(lIIllIIIIlIIIII, lIIllIIIIIlIlIl, null));
            if (lIIllIIIIIllllI) {
                this.optimize(lIIllIIIIIllIll);
            }
            return lIIllIIIIIllIll;
        }
        return null;
    }
    
    public NavMesh() {
        this.spaces = new ArrayList();
    }
}
