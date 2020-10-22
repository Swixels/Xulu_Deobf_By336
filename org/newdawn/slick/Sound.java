package org.newdawn.slick;

import org.newdawn.slick.openal.*;
import org.newdawn.slick.util.*;
import java.io.*;
import java.net.*;

public class Sound
{
    private /* synthetic */ Audio sound;
    
    public void play(final float llIlIllIllIlIlI, final float llIlIllIllIlIIl) {
        this.sound.playAsSoundEffect(llIlIllIllIlIlI, llIlIllIllIlIIl * SoundStore.get().getSoundVolume(), false);
    }
    
    public void stop() {
        this.sound.stop();
    }
    
    public void playAt(final float llIlIllIlIlIlIl, final float llIlIllIlIlIlII, final float llIlIllIlIlIIll, final float llIlIllIlIlIIlI, final float llIlIllIlIIlIll) {
        this.sound.playAsSoundEffect(llIlIllIlIlIlIl, llIlIllIlIlIlII * SoundStore.get().getSoundVolume(), false, llIlIllIlIlIIll, llIlIllIlIlIIlI, llIlIllIlIIlIll);
    }
    
    public void play() {
        this.play(1.0f, 1.0f);
    }
    
    public Sound(final String llIlIllIlllIllI) throws SlickException {
        SoundStore.get().init();
        try {
            if (llIlIllIlllIllI.toLowerCase().endsWith(".ogg")) {
                this.sound = SoundStore.get().getOgg(llIlIllIlllIllI);
            }
            else if (llIlIllIlllIllI.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(llIlIllIlllIllI);
            }
            else if (llIlIllIlllIllI.toLowerCase().endsWith(".aif")) {
                this.sound = SoundStore.get().getAIF(llIlIllIlllIllI);
            }
            else {
                if (!llIlIllIlllIllI.toLowerCase().endsWith(".xm") && !llIlIllIlllIllI.toLowerCase().endsWith(".mod")) {
                    throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
                }
                this.sound = SoundStore.get().getMOD(llIlIllIlllIllI);
            }
        }
        catch (Exception llIlIllIllllIlI) {
            Log.error(llIlIllIllllIlI);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load sound: ").append(llIlIllIlllIllI)));
        }
    }
    
    public void loop() {
        this.loop(1.0f, 1.0f);
    }
    
    public void loop(final float llIlIllIlIIIIII, final float llIlIllIIllllll) {
        this.sound.playAsSoundEffect(llIlIllIlIIIIII, llIlIllIIllllll * SoundStore.get().getSoundVolume(), true);
    }
    
    public Sound(final InputStream llIlIlllIIIllII, final String llIlIlllIIIlllI) throws SlickException {
        SoundStore.get().init();
        try {
            if (llIlIlllIIIlllI.toLowerCase().endsWith(".ogg")) {
                this.sound = SoundStore.get().getOgg(llIlIlllIIIllII);
            }
            else if (llIlIlllIIIlllI.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(llIlIlllIIIllII);
            }
            else if (llIlIlllIIIlllI.toLowerCase().endsWith(".aif")) {
                this.sound = SoundStore.get().getAIF(llIlIlllIIIllII);
            }
            else {
                if (!llIlIlllIIIlllI.toLowerCase().endsWith(".xm") && !llIlIlllIIIlllI.toLowerCase().endsWith(".mod")) {
                    throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
                }
                this.sound = SoundStore.get().getMOD(llIlIlllIIIllII);
            }
        }
        catch (Exception llIlIlllIIlIIIl) {
            Log.error(llIlIlllIIlIIIl);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load sound: ").append(llIlIlllIIIlllI)));
        }
    }
    
    public boolean playing() {
        return this.sound.isPlaying();
    }
    
    public Sound(final URL llIlIlllIIIIIll) throws SlickException {
        SoundStore.get().init();
        final String llIlIlllIIIIIlI = llIlIlllIIIIIll.getFile();
        try {
            if (llIlIlllIIIIIlI.toLowerCase().endsWith(".ogg")) {
                this.sound = SoundStore.get().getOgg(llIlIlllIIIIIll.openStream());
            }
            else if (llIlIlllIIIIIlI.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(llIlIlllIIIIIll.openStream());
            }
            else if (llIlIlllIIIIIlI.toLowerCase().endsWith(".aif")) {
                this.sound = SoundStore.get().getAIF(llIlIlllIIIIIll.openStream());
            }
            else {
                if (!llIlIlllIIIIIlI.toLowerCase().endsWith(".xm") && !llIlIlllIIIIIlI.toLowerCase().endsWith(".mod")) {
                    throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
                }
                this.sound = SoundStore.get().getMOD(llIlIlllIIIIIll.openStream());
            }
        }
        catch (Exception llIlIlllIIIIlIl) {
            Log.error(llIlIlllIIIIlIl);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load sound: ").append(llIlIlllIIIIIlI)));
        }
    }
    
    public void playAt(final float llIlIllIllIIIll, final float llIlIllIlIllllI, final float llIlIllIlIlllIl) {
        this.playAt(1.0f, 1.0f, llIlIllIllIIIll, llIlIllIlIllllI, llIlIllIlIlllIl);
    }
}
