package org.newdawn.slick.openal;

import org.lwjgl.openal.*;

public class AudioImpl implements Audio
{
    private /* synthetic */ int index;
    private /* synthetic */ SoundStore store;
    private /* synthetic */ float length;
    private /* synthetic */ int buffer;
    
    protected AudioImpl() {
        this.index = -1;
    }
    
    @Override
    public void stop() {
        if (this.index != -1) {
            this.store.stopSource(this.index);
            this.index = -1;
        }
    }
    
    @Override
    public float getPosition() {
        return AL10.alGetSourcef(this.store.getSource(this.index), 4132);
    }
    
    AudioImpl(final SoundStore lllllllllllllllllIIIIlIllIIlllII, final int lllllllllllllllllIIIIlIllIIllIll) {
        this.index = -1;
        this.store = lllllllllllllllllIIIIlIllIIlllII;
        this.buffer = lllllllllllllllllIIIIlIllIIllIll;
        final int lllllllllllllllllIIIIlIllIIllIlI = AL10.alGetBufferi(lllllllllllllllllIIIIlIllIIllIll, 8196);
        final int lllllllllllllllllIIIIlIllIIllIIl = AL10.alGetBufferi(lllllllllllllllllIIIIlIllIIllIll, 8194);
        final int lllllllllllllllllIIIIlIllIIllIII = AL10.alGetBufferi(lllllllllllllllllIIIIlIllIIllIll, 8195);
        final int lllllllllllllllllIIIIlIllIIlIlll = AL10.alGetBufferi(lllllllllllllllllIIIIlIllIIllIll, 8193);
        final int lllllllllllllllllIIIIlIllIIlIllI = lllllllllllllllllIIIIlIllIIllIlI / (lllllllllllllllllIIIIlIllIIllIIl / 8);
        this.length = lllllllllllllllllIIIIlIllIIlIllI / (float)lllllllllllllllllIIIIlIllIIlIlll / lllllllllllllllllIIIIlIllIIllIII;
    }
    
    @Override
    public int playAsMusic(final float lllllllllllllllllIIIIlIlIlIlIlll, final float lllllllllllllllllIIIIlIlIlIlIllI, final boolean lllllllllllllllllIIIIlIlIlIlIlIl) {
        this.store.playAsMusic(this.buffer, lllllllllllllllllIIIIlIlIlIlIlll, lllllllllllllllllIIIIlIlIlIlIllI, lllllllllllllllllIIIIlIlIlIlIlIl);
        this.index = 0;
        return this.store.getSource(0);
    }
    
    public static void pauseMusic() {
        SoundStore.get().pauseLoop();
    }
    
    @Override
    public int playAsSoundEffect(final float lllllllllllllllllIIIIlIlIlllllII, final float lllllllllllllllllIIIIlIlIllllIll, final boolean lllllllllllllllllIIIIlIlIllllIlI) {
        this.index = this.store.playAsSound(this.buffer, lllllllllllllllllIIIIlIlIlllllII, lllllllllllllllllIIIIlIlIllllIll, lllllllllllllllllIIIIlIlIllllIlI);
        return this.store.getSource(this.index);
    }
    
    @Override
    public int getBufferID() {
        return this.buffer;
    }
    
    @Override
    public boolean setPosition(float lllllllllllllllllIIIIlIlIlIIllll) {
        lllllllllllllllllIIIIlIlIlIIllll %= this.length;
        AL10.alSourcef(this.store.getSource(this.index), 4132, lllllllllllllllllIIIIlIlIlIIllll);
        return AL10.alGetError() == 0;
    }
    
    @Override
    public int playAsSoundEffect(final float lllllllllllllllllIIIIlIlIllIllIl, final float lllllllllllllllllIIIIlIlIllIllII, final boolean lllllllllllllllllIIIIlIlIllIlIll, final float lllllllllllllllllIIIIlIlIllIlIlI, final float lllllllllllllllllIIIIlIlIllIlIIl, final float lllllllllllllllllIIIIlIlIllIIIIl) {
        this.index = this.store.playAsSoundAt(this.buffer, lllllllllllllllllIIIIlIlIllIllIl, lllllllllllllllllIIIIlIlIllIllII, lllllllllllllllllIIIIlIlIllIlIll, lllllllllllllllllIIIIlIlIllIlIlI, lllllllllllllllllIIIIlIlIllIlIIl, lllllllllllllllllIIIIlIlIllIIIIl);
        return this.store.getSource(this.index);
    }
    
    @Override
    public boolean isPlaying() {
        return this.index != -1 && this.store.isPlaying(this.index);
    }
    
    public static void restartMusic() {
        SoundStore.get().restartLoop();
    }
}
