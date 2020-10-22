package org.newdawn.slick.util.pathfinding.heuristics;

import org.newdawn.slick.util.pathfinding.*;

public class ClosestHeuristic implements AStarHeuristic
{
    @Override
    public float getCost(final TileBasedMap lllllllllllllllllllIIIIIlIllllIl, final Mover lllllllllllllllllllIIIIIlIllllII, final int lllllllllllllllllllIIIIIlIlllIll, final int lllllllllllllllllllIIIIIlIlllIlI, final int lllllllllllllllllllIIIIIlIllIIlI, final int lllllllllllllllllllIIIIIlIlllIII) {
        final float lllllllllllllllllllIIIIIlIllIlll = (float)(lllllllllllllllllllIIIIIlIllIIlI - lllllllllllllllllllIIIIIlIlllIll);
        final float lllllllllllllllllllIIIIIlIllIllI = (float)(lllllllllllllllllllIIIIIlIlllIII - lllllllllllllllllllIIIIIlIlllIlI);
        final float lllllllllllllllllllIIIIIlIllIlIl = (float)Math.sqrt(lllllllllllllllllllIIIIIlIllIlll * lllllllllllllllllllIIIIIlIllIlll + lllllllllllllllllllIIIIIlIllIllI * lllllllllllllllllllIIIIIlIllIllI);
        return lllllllllllllllllllIIIIIlIllIlIl;
    }
}
