package org.newdawn.slick.util.pathfinding;

public interface TileBasedMap
{
    int getWidthInTiles();
    
    int getHeightInTiles();
    
    boolean blocked(final PathFindingContext p0, final int p1, final int p2);
    
    void pathFinderVisited(final int p0, final int p1);
    
    float getCost(final PathFindingContext p0, final int p1, final int p2);
}
