package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.*;

public class Space
{
    private /* synthetic */ float width;
    private /* synthetic */ float x;
    private /* synthetic */ HashMap links;
    private /* synthetic */ float y;
    private /* synthetic */ float cost;
    private /* synthetic */ ArrayList linksList;
    private /* synthetic */ float height;
    
    public float getHeight() {
        return this.height;
    }
    
    public void fill(final Space lIllIIIllllIIlI, final float lIllIIIllllIIIl, final float lIllIIIllllIIII, final float lIllIIIllllIlII) {
        if (lIllIIIllllIlII >= this.cost) {
            return;
        }
        this.cost = lIllIIIllllIlII;
        if (lIllIIIllllIIlI == this) {
            return;
        }
        for (int lIllIIIlllllIIl = 0; lIllIIIlllllIIl < this.getLinkCount(); ++lIllIIIlllllIIl) {
            final Link lIllIIIllllllII = this.getLink(lIllIIIlllllIIl);
            final float lIllIIIlllllIll = lIllIIIllllllII.distance2(lIllIIIllllIIIl, lIllIIIllllIIII);
            final float lIllIIIlllllIlI = lIllIIIllllIlII + lIllIIIlllllIll;
            lIllIIIllllllII.getTarget().fill(lIllIIIllllIIlI, lIllIIIllllllII.getX(), lIllIIIllllllII.getY(), lIllIIIlllllIlI);
        }
    }
    
    public void clearCost() {
        this.cost = Float.MAX_VALUE;
    }
    
    public float getX() {
        return this.x;
    }
    
    public boolean hasJoinedEdge(final Space lIllIIlIIllIIlI) {
        if (this.inTolerance(this.x, lIllIIlIIllIIlI.x + lIllIIlIIllIIlI.width) || this.inTolerance(this.x + this.width, lIllIIlIIllIIlI.x)) {
            if (this.y >= lIllIIlIIllIIlI.y && this.y <= lIllIIlIIllIIlI.y + lIllIIlIIllIIlI.height) {
                return true;
            }
            if (this.y + this.height >= lIllIIlIIllIIlI.y && this.y + this.height <= lIllIIlIIllIIlI.y + lIllIIlIIllIIlI.height) {
                return true;
            }
            if (lIllIIlIIllIIlI.y >= this.y && lIllIIlIIllIIlI.y <= this.y + this.height) {
                return true;
            }
            if (lIllIIlIIllIIlI.y + lIllIIlIIllIIlI.height >= this.y && lIllIIlIIllIIlI.y + lIllIIlIIllIIlI.height <= this.y + this.height) {
                return true;
            }
        }
        if (this.inTolerance(this.y, lIllIIlIIllIIlI.y + lIllIIlIIllIIlI.height) || this.inTolerance(this.y + this.height, lIllIIlIIllIIlI.y)) {
            if (this.x >= lIllIIlIIllIIlI.x && this.x <= lIllIIlIIllIIlI.x + lIllIIlIIllIIlI.width) {
                return true;
            }
            if (this.x + this.width >= lIllIIlIIllIIlI.x && this.x + this.width <= lIllIIlIIllIIlI.x + lIllIIlIIllIIlI.width) {
                return true;
            }
            if (lIllIIlIIllIIlI.x >= this.x && lIllIIlIIllIIlI.x <= this.x + this.width) {
                return true;
            }
            if (lIllIIlIIllIIlI.x + lIllIIlIIllIIlI.width >= this.x && lIllIIlIIllIIlI.x + lIllIIlIIllIIlI.width <= this.x + this.width) {
                return true;
            }
        }
        return false;
    }
    
    public Link getLink(final int lIllIIlIIIlIIIl) {
        return this.linksList.get(lIllIIlIIIlIIIl);
    }
    
    public int getLinkCount() {
        return this.linksList.size();
    }
    
    public float getCost() {
        return this.cost;
    }
    
    public Space merge(final Space lIllIIlIIlIlIII) {
        final float lIllIIlIIlIIlll = Math.min(this.x, lIllIIlIIlIlIII.x);
        final float lIllIIlIIlIIllI = Math.min(this.y, lIllIIlIIlIlIII.y);
        float lIllIIlIIlIIlIl = this.width + lIllIIlIIlIlIII.width;
        float lIllIIlIIlIIlII = this.height + lIllIIlIIlIlIII.height;
        if (this.x == lIllIIlIIlIlIII.x) {
            lIllIIlIIlIIlIl = this.width;
        }
        else {
            lIllIIlIIlIIlII = this.height;
        }
        return new Space(lIllIIlIIlIIlll, lIllIIlIIlIIllI, lIllIIlIIlIIlIl, lIllIIlIIlIIlII);
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public float getY() {
        return this.y;
    }
    
    public boolean pickLowestCost(final Space lIllIIIllIlIlll, final NavPath lIllIIIllIlIllI) {
        if (lIllIIIllIlIlll == this) {
            return true;
        }
        if (this.links.size() == 0) {
            return false;
        }
        Link lIllIIIllIllIIl = null;
        for (int lIllIIIllIlllIl = 0; lIllIIIllIlllIl < this.getLinkCount(); ++lIllIIIllIlllIl) {
            final Link lIllIIIllIllllI = this.getLink(lIllIIIllIlllIl);
            if (lIllIIIllIllIIl == null || lIllIIIllIllllI.getTarget().getCost() < lIllIIIllIllIIl.getTarget().getCost()) {
                lIllIIIllIllIIl = lIllIIIllIllllI;
            }
        }
        lIllIIIllIlIllI.push(lIllIIIllIllIIl);
        return lIllIIIllIllIIl.getTarget().pickLowestCost(lIllIIIllIlIlll, lIllIIIllIlIllI);
    }
    
    public void link(final Space lIllIIlIlIIIIlI) {
        if (this.inTolerance(this.x, lIllIIlIlIIIIlI.x + lIllIIlIlIIIIlI.width) || this.inTolerance(this.x + this.width, lIllIIlIlIIIIlI.x)) {
            float lIllIIlIlIIllll = this.x;
            if (this.x + this.width == lIllIIlIlIIIIlI.x) {
                lIllIIlIlIIllll = this.x + this.width;
            }
            final float lIllIIlIlIIlllI = Math.max(this.y, lIllIIlIlIIIIlI.y);
            final float lIllIIlIlIIllIl = Math.min(this.y + this.height, lIllIIlIlIIIIlI.y + lIllIIlIlIIIIlI.height);
            final float lIllIIlIlIIllII = lIllIIlIlIIlllI + (lIllIIlIlIIllIl - lIllIIlIlIIlllI) / 2.0f;
            final Link lIllIIlIlIIlIll = new Link(lIllIIlIlIIllll, lIllIIlIlIIllII, lIllIIlIlIIIIlI);
            this.links.put(lIllIIlIlIIIIlI, lIllIIlIlIIlIll);
            this.linksList.add(lIllIIlIlIIlIll);
        }
        if (this.inTolerance(this.y, lIllIIlIlIIIIlI.y + lIllIIlIlIIIIlI.height) || this.inTolerance(this.y + this.height, lIllIIlIlIIIIlI.y)) {
            float lIllIIlIlIIlIlI = this.y;
            if (this.y + this.height == lIllIIlIlIIIIlI.y) {
                lIllIIlIlIIlIlI = this.y + this.height;
            }
            final float lIllIIlIlIIlIIl = Math.max(this.x, lIllIIlIlIIIIlI.x);
            final float lIllIIlIlIIlIII = Math.min(this.x + this.width, lIllIIlIlIIIIlI.x + lIllIIlIlIIIIlI.width);
            final float lIllIIlIlIIIlll = lIllIIlIlIIlIIl + (lIllIIlIlIIlIII - lIllIIlIlIIlIIl) / 2.0f;
            final Link lIllIIlIlIIIllI = new Link(lIllIIlIlIIIlll, lIllIIlIlIIlIlI, lIllIIlIlIIIIlI);
            this.links.put(lIllIIlIlIIIIlI, lIllIIlIlIIIllI);
            this.linksList.add(lIllIIlIlIIIllI);
        }
    }
    
    private boolean inTolerance(final float lIllIIlIIllIlll, final float lIllIIlIIllIllI) {
        return lIllIIlIIllIlll == lIllIIlIIllIllI;
    }
    
    public boolean contains(final float lIllIIlIIIIIlll, final float lIllIIlIIIIlIIl) {
        return lIllIIlIIIIIlll >= this.x && lIllIIlIIIIIlll < this.x + this.width && lIllIIlIIIIlIIl >= this.y && lIllIIlIIIIlIIl < this.y + this.height;
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("[Space ").append(this.x).append(",").append(this.y).append(" ").append(this.width).append(",").append(this.height).append("]"));
    }
    
    public boolean canMerge(final Space lIllIIlIIIllIII) {
        return this.hasJoinedEdge(lIllIIlIIIllIII) && ((this.x == lIllIIlIIIllIII.x && this.width == lIllIIlIIIllIII.width) || (this.y == lIllIIlIIIllIII.y && this.height == lIllIIlIIIllIII.height));
    }
    
    public Space(final float lIllIIlIllIIllI, final float lIllIIlIllIIlIl, final float lIllIIlIllIlIIl, final float lIllIIlIllIIIll) {
        this.links = new HashMap();
        this.linksList = new ArrayList();
        this.x = lIllIIlIllIIllI;
        this.y = lIllIIlIllIIlIl;
        this.width = lIllIIlIllIlIIl;
        this.height = lIllIIlIllIIIll;
    }
}
