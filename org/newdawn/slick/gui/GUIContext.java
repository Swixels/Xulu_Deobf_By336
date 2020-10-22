package org.newdawn.slick.gui;

import org.newdawn.slick.opengl.*;
import org.lwjgl.input.*;
import org.newdawn.slick.*;

public interface GUIContext
{
    void setMouseCursor(final ImageData p0, final int p1, final int p2) throws SlickException;
    
    int getScreenHeight();
    
    int getWidth();
    
    int getScreenWidth();
    
    void setDefaultMouseCursor();
    
    void setMouseCursor(final Cursor p0, final int p1, final int p2) throws SlickException;
    
    Input getInput();
    
    Font getDefaultFont();
    
    void setMouseCursor(final String p0, final int p1, final int p2) throws SlickException;
    
    long getTime();
    
    int getHeight();
}
