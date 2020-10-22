package org.newdawn.slick.util.pathfinding.heuristics;

import org.newdawn.slick.util.pathfinding.*;

public class ManhattanHeuristic implements AStarHeuristic
{
    private /* synthetic */ int minimumCost;
    
    public ManhattanHeuristic(final int lllllllllllllllllIIIIIllllIllIIl) {
        this.minimumCost = lllllllllllllllllIIIIIllllIllIIl;
    }
    
    @Override
    public float getCost(final TileBasedMap lllllllllllllllllIIIIIllllIlIIII, final Mover lllllllllllllllllIIIIIllllIIllll, final int lllllllllllllllllIIIIIllllIIlIIl, final int lllllllllllllllllIIIIIllllIIlIII, final int lllllllllllllllllIIIIIllllIIllII, final int lllllllllllllllllIIIIIllllIIIllI) {
        return (float)(this.minimumCost * (Math.abs(lllllllllllllllllIIIIIllllIIlIIl - lllllllllllllllllIIIIIllllIIllII) + Math.abs(lllllllllllllllllIIIIIllllIIlIII - lllllllllllllllllIIIIIllllIIIllI)));
    }
}
