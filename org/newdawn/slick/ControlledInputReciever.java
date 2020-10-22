package org.newdawn.slick;

public interface ControlledInputReciever
{
    void inputStarted();
    
    void inputEnded();
    
    boolean isAcceptingInput();
    
    void setInput(final Input p0);
}
