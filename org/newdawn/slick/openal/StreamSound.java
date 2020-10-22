package org.newdawn.slick.openal;

import org.newdawn.slick.util.*;
import java.io.*;
import org.lwjgl.openal.*;
import org.lwjgl.*;
import java.nio.*;

public class StreamSound extends AudioImpl
{
    private /* synthetic */ OpenALStreamPlayer player;
    
    @Override
    public int playAsSoundEffect(final float lIlllIIlIIllllI, final float lIlllIIlIIllIIl, final boolean lIlllIIlIIlllII) {
        return this.playAsMusic(lIlllIIlIIllllI, lIlllIIlIIllIIl, lIlllIIlIIlllII);
    }
    
    @Override
    public void stop() {
        SoundStore.get().setStream(null);
    }
    
    @Override
    public int playAsSoundEffect(final float lIlllIIlIlIllIl, final float lIlllIIlIlIllII, final boolean lIlllIIlIlIlIll, final float lIlllIIlIlIlIlI, final float lIlllIIlIlIlIIl, final float lIlllIIlIlIlIII) {
        return this.playAsMusic(lIlllIIlIlIllIl, lIlllIIlIlIllII, lIlllIIlIlIlIll);
    }
    
    @Override
    public int playAsMusic(final float lIlllIIlIllllll, final float lIlllIIllIIIIlI, final boolean lIlllIIllIIIIIl) {
        try {
            this.cleanUpSource();
            this.player.setup(lIlllIIlIllllll);
            this.player.play(lIlllIIllIIIIIl);
            SoundStore.get().setStream(this.player);
        }
        catch (IOException lIlllIIllIIIlIl) {
            Log.error(String.valueOf(new StringBuilder().append("Failed to read OGG source: ").append(this.player.getSource())));
        }
        return SoundStore.get().getSource(0);
    }
    
    @Override
    public boolean isPlaying() {
        return SoundStore.get().isPlaying(this.player);
    }
    
    public StreamSound(final OpenALStreamPlayer lIlllIIllIIllIl) {
        this.player = lIlllIIllIIllIl;
    }
    
    @Override
    public float getPosition() {
        return this.player.getPosition();
    }
    
    private void cleanUpSource() {
        final SoundStore lIlllIIlIlllIII = SoundStore.get();
        AL10.alSourceStop(lIlllIIlIlllIII.getSource(0));
        final IntBuffer lIlllIIlIllIlll = BufferUtils.createIntBuffer(1);
        for (int lIlllIIlIllIllI = AL10.alGetSourcei(lIlllIIlIlllIII.getSource(0), 4117); lIlllIIlIllIllI > 0; --lIlllIIlIllIllI) {
            AL10.alSourceUnqueueBuffers(lIlllIIlIlllIII.getSource(0), lIlllIIlIllIlll);
        }
        AL10.alSourcei(lIlllIIlIlllIII.getSource(0), 4105, 0);
    }
    
    @Override
    public boolean setPosition(final float lIlllIIlIIlIIIl) {
        return this.player.setPosition(lIlllIIlIIlIIIl);
    }
}
