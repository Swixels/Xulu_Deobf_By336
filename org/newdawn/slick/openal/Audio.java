package org.newdawn.slick.openal;

public interface Audio
{
    int playAsSoundEffect(final float p0, final float p1, final boolean p2, final float p3, final float p4, final float p5);
    
    boolean setPosition(final float p0);
    
    boolean isPlaying();
    
    void stop();
    
    int playAsSoundEffect(final float p0, final float p1, final boolean p2);
    
    int playAsMusic(final float p0, final float p1, final boolean p2);
    
    float getPosition();
    
    int getBufferID();
}
