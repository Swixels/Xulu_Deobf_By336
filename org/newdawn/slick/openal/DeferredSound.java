package org.newdawn.slick.openal;

import org.newdawn.slick.util.*;
import java.io.*;
import org.newdawn.slick.loading.*;

public class DeferredSound extends AudioImpl implements DeferredResource
{
    private /* synthetic */ Audio target;
    private /* synthetic */ InputStream in;
    private /* synthetic */ int type;
    private /* synthetic */ String ref;
    
    @Override
    public String getDescription() {
        return this.ref;
    }
    
    private void checkTarget() {
        if (this.target == null) {
            throw new RuntimeException("Attempt to use deferred sound before loading");
        }
    }
    
    @Override
    public int playAsSoundEffect(final float llllllllllllllllllIlIlIIlIIlIlII, final float llllllllllllllllllIlIlIIlIIlIIll, final boolean llllllllllllllllllIlIlIIlIIlIIlI, final float llllllllllllllllllIlIlIIlIIlIIIl, final float llllllllllllllllllIlIlIIlIIlIIII, final float llllllllllllllllllIlIlIIlIIIllll) {
        this.checkTarget();
        return this.target.playAsSoundEffect(llllllllllllllllllIlIlIIlIIlIlII, llllllllllllllllllIlIlIIlIIlIIll, llllllllllllllllllIlIlIIlIIlIIlI, llllllllllllllllllIlIlIIlIIlIIIl, llllllllllllllllllIlIlIIlIIlIIII, llllllllllllllllllIlIlIIlIIIllll);
    }
    
    static {
        OGG = 1;
        WAV = 2;
        AIF = 4;
        MOD = 3;
    }
    
    @Override
    public void stop() {
        this.checkTarget();
        this.target.stop();
    }
    
    @Override
    public int playAsMusic(final float llllllllllllllllllIlIlIIlIlIlIll, final float llllllllllllllllllIlIlIIlIlIlIlI, final boolean llllllllllllllllllIlIlIIlIlIllIl) {
        this.checkTarget();
        return this.target.playAsMusic(llllllllllllllllllIlIlIIlIlIlIll, llllllllllllllllllIlIlIIlIlIlIlI, llllllllllllllllllIlIlIIlIlIllIl);
    }
    
    @Override
    public void load() throws IOException {
        final boolean llllllllllllllllllIlIlIIlIlllIlI = SoundStore.get().isDeferredLoading();
        SoundStore.get().setDeferredLoading(false);
        if (this.in != null) {
            switch (this.type) {
                case 1: {
                    this.target = SoundStore.get().getOgg(this.in);
                    break;
                }
                case 2: {
                    this.target = SoundStore.get().getWAV(this.in);
                    break;
                }
                case 3: {
                    this.target = SoundStore.get().getMOD(this.in);
                    break;
                }
                case 4: {
                    this.target = SoundStore.get().getAIF(this.in);
                    break;
                }
                default: {
                    Log.error(String.valueOf(new StringBuilder().append("Unrecognised sound type: ").append(this.type)));
                    break;
                }
            }
        }
        else {
            switch (this.type) {
                case 1: {
                    this.target = SoundStore.get().getOgg(this.ref);
                    break;
                }
                case 2: {
                    this.target = SoundStore.get().getWAV(this.ref);
                    break;
                }
                case 3: {
                    this.target = SoundStore.get().getMOD(this.ref);
                    break;
                }
                case 4: {
                    this.target = SoundStore.get().getAIF(this.ref);
                    break;
                }
                default: {
                    Log.error(String.valueOf(new StringBuilder().append("Unrecognised sound type: ").append(this.type)));
                    break;
                }
            }
        }
        SoundStore.get().setDeferredLoading(llllllllllllllllllIlIlIIlIlllIlI);
    }
    
    public DeferredSound(final String llllllllllllllllllIlIlIIllIIllll, final InputStream llllllllllllllllllIlIlIIllIIlIlI, final int llllllllllllllllllIlIlIIllIIllIl) {
        this.ref = llllllllllllllllllIlIlIIllIIllll;
        this.type = llllllllllllllllllIlIlIIllIIllIl;
        if (llllllllllllllllllIlIlIIllIIllll.equals(llllllllllllllllllIlIlIIllIIlIlI.toString())) {
            this.in = llllllllllllllllllIlIlIIllIIlIlI;
        }
        LoadingList.get().add(this);
    }
    
    @Override
    public int playAsSoundEffect(final float llllllllllllllllllIlIlIIlIIlllll, final float llllllllllllllllllIlIlIIlIIllllI, final boolean llllllllllllllllllIlIlIIlIlIIIIl) {
        this.checkTarget();
        return this.target.playAsSoundEffect(llllllllllllllllllIlIlIIlIIlllll, llllllllllllllllllIlIlIIlIIllllI, llllllllllllllllllIlIlIIlIlIIIIl);
    }
    
    @Override
    public boolean isPlaying() {
        this.checkTarget();
        return this.target.isPlaying();
    }
}
