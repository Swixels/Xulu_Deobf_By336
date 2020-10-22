package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.*;
import org.newdawn.slick.util.pathfinding.*;

public class NavMeshBuilder implements PathFindingContext
{
    private /* synthetic */ int sy;
    private /* synthetic */ boolean tileBased;
    private /* synthetic */ int sx;
    private /* synthetic */ float smallestSpace;
    
    private void linkSpaces(final ArrayList lllIlIIIIIIIll) {
        for (int lllIlIIIIIIlIl = 0; lllIlIIIIIIlIl < lllIlIIIIIIIll.size(); ++lllIlIIIIIIlIl) {
            final Space lllIlIIIIIIllI = lllIlIIIIIIIll.get(lllIlIIIIIIlIl);
            for (int lllIlIIIIIIlll = lllIlIIIIIIlIl + 1; lllIlIIIIIIlll < lllIlIIIIIIIll.size(); ++lllIlIIIIIIlll) {
                final Space lllIlIIIIIlIII = lllIlIIIIIIIll.get(lllIlIIIIIIlll);
                if (lllIlIIIIIIllI.hasJoinedEdge(lllIlIIIIIlIII)) {
                    lllIlIIIIIIllI.link(lllIlIIIIIlIII);
                    lllIlIIIIIlIII.link(lllIlIIIIIIllI);
                }
            }
        }
    }
    
    private void subsection(final TileBasedMap lllIIlllIllIll, final Space lllIIlllIllllI, final ArrayList lllIIlllIlllIl) {
        if (!this.clear(lllIIlllIllIll, lllIIlllIllllI)) {
            final float lllIIllllIIIlI = lllIIlllIllllI.getWidth() / 2.0f;
            final float lllIIllllIIIIl = lllIIlllIllllI.getHeight() / 2.0f;
            if (lllIIllllIIIlI < this.smallestSpace && lllIIllllIIIIl < this.smallestSpace) {
                return;
            }
            this.subsection(lllIIlllIllIll, new Space(lllIIlllIllllI.getX(), lllIIlllIllllI.getY(), lllIIllllIIIlI, lllIIllllIIIIl), lllIIlllIlllIl);
            this.subsection(lllIIlllIllIll, new Space(lllIIlllIllllI.getX(), lllIIlllIllllI.getY() + lllIIllllIIIIl, lllIIllllIIIlI, lllIIllllIIIIl), lllIIlllIlllIl);
            this.subsection(lllIIlllIllIll, new Space(lllIIlllIllllI.getX() + lllIIllllIIIlI, lllIIlllIllllI.getY(), lllIIllllIIIlI, lllIIllllIIIIl), lllIIlllIlllIl);
            this.subsection(lllIIlllIllIll, new Space(lllIIlllIllllI.getX() + lllIIllllIIIlI, lllIIlllIllllI.getY() + lllIIllllIIIIl, lllIIllllIIIlI, lllIIllllIIIIl), lllIIlllIlllIl);
        }
        else {
            lllIIlllIlllIl.add(lllIIlllIllllI);
        }
    }
    
    @Override
    public int getSearchDistance() {
        return 0;
    }
    
    private boolean mergeSpaces(final ArrayList lllIlIIIIlIIlI) {
        for (int lllIlIIIIlIlIl = 0; lllIlIIIIlIlIl < lllIlIIIIlIIlI.size(); ++lllIlIIIIlIlIl) {
            final Space lllIlIIIIlIllI = lllIlIIIIlIIlI.get(lllIlIIIIlIlIl);
            for (int lllIlIIIIlIlll = lllIlIIIIlIlIl + 1; lllIlIIIIlIlll < lllIlIIIIlIIlI.size(); ++lllIlIIIIlIlll) {
                final Space lllIlIIIIllIII = lllIlIIIIlIIlI.get(lllIlIIIIlIlll);
                if (lllIlIIIIlIllI.canMerge(lllIlIIIIllIII)) {
                    lllIlIIIIlIIlI.remove(lllIlIIIIlIllI);
                    lllIlIIIIlIIlI.remove(lllIlIIIIllIII);
                    lllIlIIIIlIIlI.add(lllIlIIIIlIllI.merge(lllIlIIIIllIII));
                    return true;
                }
            }
        }
        return false;
    }
    
    public NavMesh build(final TileBasedMap lllIlIIIllIIll) {
        return this.build(lllIlIIIllIIll, true);
    }
    
    @Override
    public int getSourceX() {
        return this.sx;
    }
    
    public boolean clear(final TileBasedMap lllIIlllllIIll, final Space lllIIllllIllIl) {
        if (this.tileBased) {
            return true;
        }
        float lllIIlllllIIIl = 0.0f;
        boolean lllIIlllllIIII = false;
        while (lllIIlllllIIIl < lllIIllllIllIl.getWidth()) {
            float lllIIlllllIllI = 0.0f;
            boolean lllIIlllllIlIl = false;
            while (lllIIlllllIllI < lllIIllllIllIl.getHeight()) {
                this.sx = (int)(lllIIllllIllIl.getX() + lllIIlllllIIIl);
                this.sy = (int)(lllIIllllIllIl.getY() + lllIIlllllIllI);
                if (lllIIlllllIIll.blocked(this, this.sx, this.sy)) {
                    return false;
                }
                lllIIlllllIllI += 0.1f;
                if (lllIIlllllIllI <= lllIIllllIllIl.getHeight() || lllIIlllllIlIl) {
                    continue;
                }
                lllIIlllllIllI = lllIIllllIllIl.getHeight();
                lllIIlllllIlIl = true;
            }
            lllIIlllllIIIl += 0.1f;
            if (lllIIlllllIIIl > lllIIllllIllIl.getWidth() && !lllIIlllllIIII) {
                lllIIlllllIIIl = lllIIllllIllIl.getWidth();
                lllIIlllllIIII = true;
            }
        }
        return true;
    }
    
    @Override
    public int getSourceY() {
        return this.sy;
    }
    
    public NavMesh build(final TileBasedMap lllIlIIIlIIllI, final boolean lllIlIIIlIIIIl) {
        this.tileBased = lllIlIIIlIIIIl;
        final ArrayList lllIlIIIlIIlII = new ArrayList();
        if (lllIlIIIlIIIIl) {
            for (int lllIlIIIlIlIIl = 0; lllIlIIIlIlIIl < lllIlIIIlIIllI.getWidthInTiles(); ++lllIlIIIlIlIIl) {
                for (int lllIlIIIlIlIlI = 0; lllIlIIIlIlIlI < lllIlIIIlIIllI.getHeightInTiles(); ++lllIlIIIlIlIlI) {
                    if (!lllIlIIIlIIllI.blocked(this, lllIlIIIlIlIIl, lllIlIIIlIlIlI)) {
                        lllIlIIIlIIlII.add(new Space((float)lllIlIIIlIlIIl, (float)lllIlIIIlIlIlI, 1.0f, 1.0f));
                    }
                }
            }
        }
        else {
            final Space lllIlIIIlIlIII = new Space(0.0f, 0.0f, (float)lllIlIIIlIIllI.getWidthInTiles(), (float)lllIlIIIlIIllI.getHeightInTiles());
            this.subsection(lllIlIIIlIIllI, lllIlIIIlIlIII, lllIlIIIlIIlII);
        }
        while (this.mergeSpaces(lllIlIIIlIIlII)) {}
        this.linkSpaces(lllIlIIIlIIlII);
        return new NavMesh(lllIlIIIlIIlII);
    }
    
    public NavMeshBuilder() {
        this.smallestSpace = 0.2f;
    }
    
    @Override
    public Mover getMover() {
        return null;
    }
}
