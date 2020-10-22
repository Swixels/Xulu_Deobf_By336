package org.newdawn.slick.geom;

import java.io.*;

public interface Triangulator extends Serializable
{
    float[] getTrianglePoint(final int p0, final int p1);
    
    int getTriangleCount();
    
    void addPolyPoint(final float p0, final float p1);
    
    void startHole();
    
    boolean triangulate();
}
