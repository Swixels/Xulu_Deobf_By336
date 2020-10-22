package org.newdawn.slick;

public interface KeyListener extends ControlledInputReciever
{
    void keyReleased(final int p0, final char p1);
    
    void keyPressed(final int p0, final char p1);
}
