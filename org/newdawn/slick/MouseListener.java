package org.newdawn.slick;

public interface MouseListener extends ControlledInputReciever
{
    void mouseDragged(final int p0, final int p1, final int p2, final int p3);
    
    void mouseReleased(final int p0, final int p1, final int p2);
    
    void mouseWheelMoved(final int p0);
    
    void mouseMoved(final int p0, final int p1, final int p2, final int p3);
    
    void mousePressed(final int p0, final int p1, final int p2);
    
    void mouseClicked(final int p0, final int p1, final int p2, final int p3);
}
