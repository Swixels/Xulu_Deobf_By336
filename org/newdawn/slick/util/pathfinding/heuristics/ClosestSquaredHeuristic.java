package org.newdawn.slick.util.pathfinding.heuristics;

import org.newdawn.slick.util.pathfinding.*;

public class ClosestSquaredHeuristic implements AStarHeuristic
{
    @Override
    public float getCost(final TileBasedMap lIllIlIIlllI, final Mover lIllIlIIllIl, final int lIllIlIIllII, final int lIllIlIIIlIl, final int lIllIlIIlIlI, final int lIllIlIIlIIl) {
        final float lIllIlIIlIII = (float)(lIllIlIIlIlI - lIllIlIIllII);
        final float lIllIlIIIlll = (float)(lIllIlIIlIIl - lIllIlIIIlIl);
        return lIllIlIIlIII * lIllIlIIlIII + lIllIlIIIlll * lIllIlIIIlll;
    }
}
