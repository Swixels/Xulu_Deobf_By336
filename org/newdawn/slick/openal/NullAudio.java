package org.newdawn.slick.openal;

public class NullAudio implements Audio
{
    @Override
    public float getPosition() {
        return 0.0f;
    }
    
    @Override
    public int playAsSoundEffect(final float lllllllllllllllllIllIIllIllIIlIl, final float lllllllllllllllllIllIIllIllIIlII, final boolean lllllllllllllllllIllIIllIllIIIll, final float lllllllllllllllllIllIIllIllIIIlI, final float lllllllllllllllllIllIIllIllIIIIl, final float lllllllllllllllllIllIIllIllIIIII) {
        return 0;
    }
    
    @Override
    public int playAsSoundEffect(final float lllllllllllllllllIllIIllIllIlIIl, final float lllllllllllllllllIllIIllIllIlIII, final boolean lllllllllllllllllIllIIllIllIIlll) {
        return 0;
    }
    
    @Override
    public int playAsMusic(final float lllllllllllllllllIllIIllIllIllIl, final float lllllllllllllllllIllIIllIllIllII, final boolean lllllllllllllllllIllIIllIllIlIll) {
        return 0;
    }
    
    @Override
    public int getBufferID() {
        return 0;
    }
    
    @Override
    public boolean isPlaying() {
        return false;
    }
    
    @Override
    public void stop() {
    }
    
    @Override
    public boolean setPosition(final float lllllllllllllllllIllIIllIlIllllI) {
        return false;
    }
}
