package org.newdawn.slick.openal;

import ibxm.*;
import org.lwjgl.openal.*;
import org.lwjgl.*;
import java.nio.*;
import java.io.*;

public class MODSound extends AudioImpl
{
    private /* synthetic */ SoundStore store;
    
    public void poll() {
    }
    
    @Override
    public int playAsMusic(final float llllllllllllllllIlIllIlIlIlllIII, final float llllllllllllllllIlIllIlIlIllIlII, final boolean llllllllllllllllIlIllIlIlIllIllI) {
        this.cleanUpSource();
        this.store.setCurrentMusicVolume(llllllllllllllllIlIllIlIlIllIlII);
        this.store.setMOD(this);
        return this.store.getSource(0);
    }
    
    @Override
    public float getPosition() {
        throw new RuntimeException("Positioning on modules is not currently supported");
    }
    
    @Override
    public void stop() {
        this.store.setMOD(null);
    }
    
    private void cleanUpSource() {
        AL10.alSourceStop(this.store.getSource(0));
        final IntBuffer llllllllllllllllIlIllIlIlIlIllll = BufferUtils.createIntBuffer(1);
        for (int llllllllllllllllIlIllIlIlIlIlllI = AL10.alGetSourcei(this.store.getSource(0), 4117); llllllllllllllllIlIllIlIlIlIlllI > 0; --llllllllllllllllIlIllIlIlIlIlllI) {
            AL10.alSourceUnqueueBuffers(this.store.getSource(0), llllllllllllllllIlIllIlIlIlIllll);
        }
        AL10.alSourcei(this.store.getSource(0), 4105, 0);
    }
    
    @Override
    public boolean setPosition(final float llllllllllllllllIlIllIlIlIlIIIII) {
        throw new RuntimeException("Positioning on modules is not currently supported");
    }
    
    @Override
    public int playAsSoundEffect(final float llllllllllllllllIlIllIlIlIlIlIII, final float llllllllllllllllIlIllIlIlIlIIlll, final boolean llllllllllllllllIlIllIlIlIlIIllI) {
        return -1;
    }
    
    public MODSound(final SoundStore llllllllllllllllIlIllIlIlIllllII, final InputStream llllllllllllllllIlIllIlIlIlllllI) throws IOException {
        this.store = llllllllllllllllIlIllIlIlIllllII;
    }
}
