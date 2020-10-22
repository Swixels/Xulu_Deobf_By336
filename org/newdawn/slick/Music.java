package org.newdawn.slick;

import java.util.*;
import java.net.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.openal.*;
import java.io.*;

public class Music
{
    private /* synthetic */ float requiredPosition;
    private /* synthetic */ int fadeDuration;
    private /* synthetic */ int fadeTime;
    private static /* synthetic */ Music currentMusic;
    private /* synthetic */ boolean stopAfterFade;
    private /* synthetic */ ArrayList listeners;
    private /* synthetic */ float fadeStartGain;
    private /* synthetic */ Audio sound;
    private /* synthetic */ boolean playing;
    private /* synthetic */ float fadeEndGain;
    private /* synthetic */ boolean positioning;
    private /* synthetic */ float volume;
    
    public void stop() {
        this.sound.stop();
    }
    
    public void fade(final int llllllllllllllllIllllIIIIlIllIlI, final float llllllllllllllllIllllIIIIlIllIIl, final boolean llllllllllllllllIllllIIIIlIlIlII) {
        this.stopAfterFade = llllllllllllllllIllllIIIIlIlIlII;
        this.fadeStartGain = this.volume;
        this.fadeEndGain = llllllllllllllllIllllIIIIlIllIIl;
        this.fadeDuration = llllllllllllllllIllllIIIIlIllIlI;
        this.fadeTime = llllllllllllllllIllllIIIIlIllIlI;
    }
    
    public void play() {
        this.play(1.0f, 1.0f);
    }
    
    public float getVolume() {
        return this.volume;
    }
    
    public void loop(final float llllllllllllllllIllllIIIlIIIIIlI, final float llllllllllllllllIllllIIIlIIIIlII) {
        this.startMusic(llllllllllllllllIllllIIIlIIIIIlI, llllllllllllllllIllllIIIlIIIIlII, true);
    }
    
    public Music(final String llllllllllllllllIllllIIIlllIIIIl) throws SlickException {
        this(llllllllllllllllIllllIIIlllIIIIl, false);
    }
    
    public void setVolume(float llllllllllllllllIllllIIIIllIIIll) {
        if (llllllllllllllllIllllIIIIllIIIll > 1.0f) {
            llllllllllllllllIllllIIIIllIIIll = 1.0f;
        }
        else if (llllllllllllllllIllllIIIIllIIIll < 0.0f) {
            llllllllllllllllIllllIIIIllIIIll = 0.0f;
        }
        this.volume = llllllllllllllllIllllIIIIllIIIll;
        if (Music.currentMusic == this) {
            SoundStore.get().setCurrentMusicVolume(llllllllllllllllIllllIIIIllIIIll);
        }
    }
    
    public float getPosition() {
        return this.sound.getPosition();
    }
    
    public Music(final URL llllllllllllllllIllllIIIllIIIlll, final boolean llllllllllllllllIllllIIIllIIIllI) throws SlickException {
        this.listeners = new ArrayList();
        this.volume = 1.0f;
        this.requiredPosition = -1.0f;
        SoundStore.get().init();
        final String llllllllllllllllIllllIIIllIIIlIl = llllllllllllllllIllllIIIllIIIlll.getFile();
        try {
            if (llllllllllllllllIllllIIIllIIIlIl.toLowerCase().endsWith(".ogg")) {
                if (llllllllllllllllIllllIIIllIIIllI) {
                    this.sound = SoundStore.get().getOggStream(llllllllllllllllIllllIIIllIIIlll);
                }
                else {
                    this.sound = SoundStore.get().getOgg(llllllllllllllllIllllIIIllIIIlll.openStream());
                }
            }
            else if (llllllllllllllllIllllIIIllIIIlIl.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(llllllllllllllllIllllIIIllIIIlll.openStream());
            }
            else if (llllllllllllllllIllllIIIllIIIlIl.toLowerCase().endsWith(".xm") || llllllllllllllllIllllIIIllIIIlIl.toLowerCase().endsWith(".mod")) {
                this.sound = SoundStore.get().getMOD(llllllllllllllllIllllIIIllIIIlll.openStream());
            }
            else {
                if (!llllllllllllllllIllllIIIllIIIlIl.toLowerCase().endsWith(".aif") && !llllllllllllllllIllllIIIllIIIlIl.toLowerCase().endsWith(".aiff")) {
                    throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
                }
                this.sound = SoundStore.get().getAIF(llllllllllllllllIllllIIIllIIIlll.openStream());
            }
        }
        catch (Exception llllllllllllllllIllllIIIllIIlIIl) {
            Log.error(llllllllllllllllIllllIIIllIIlIIl);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load sound: ").append(llllllllllllllllIllllIIIllIIIlll)));
        }
    }
    
    public boolean playing() {
        return Music.currentMusic == this && this.playing;
    }
    
    public boolean setPosition(final float llllllllllllllllIllllIIIIlIIIlIl) {
        if (this.playing) {
            this.requiredPosition = -1.0f;
            this.positioning = true;
            this.playing = false;
            final boolean llllllllllllllllIllllIIIIlIIIlll = this.sound.setPosition(llllllllllllllllIllllIIIIlIIIlIl);
            this.playing = true;
            this.positioning = false;
            return llllllllllllllllIllllIIIIlIIIlll;
        }
        this.requiredPosition = llllllllllllllllIllllIIIIlIIIlIl;
        return false;
    }
    
    void update(final int llllllllllllllllIllllIIIIlIIllII) {
        if (!this.playing) {
            return;
        }
        if (this.fadeTime > 0) {
            this.fadeTime -= llllllllllllllllIllllIIIIlIIllII;
            if (this.fadeTime < 0) {
                this.fadeTime = 0;
                if (this.stopAfterFade) {
                    this.stop();
                    return;
                }
            }
            final float llllllllllllllllIllllIIIIlIlIIII = (this.fadeEndGain - this.fadeStartGain) * (1.0f - this.fadeTime / (float)this.fadeDuration);
            this.setVolume(this.fadeStartGain + llllllllllllllllIllllIIIIlIlIIII);
        }
    }
    
    public void resume() {
        this.playing = true;
        AudioImpl.restartMusic();
    }
    
    public static void poll(final int llllllllllllllllIllllIIIlllIlIIl) {
        if (Music.currentMusic != null) {
            SoundStore.get().poll(llllllllllllllllIllllIIIlllIlIIl);
            if (!SoundStore.get().isMusicPlaying()) {
                if (!Music.currentMusic.positioning) {
                    final Music llllllllllllllllIllllIIIlllIlIlI = Music.currentMusic;
                    Music.currentMusic = null;
                    llllllllllllllllIllllIIIlllIlIlI.fireMusicEnded();
                }
            }
            else {
                Music.currentMusic.update(llllllllllllllllIllllIIIlllIlIIl);
            }
        }
    }
    
    private void fireMusicEnded() {
        this.playing = false;
        for (int llllllllllllllllIllllIIIlIlIIlIl = 0; llllllllllllllllIllllIIIlIlIIlIl < this.listeners.size(); ++llllllllllllllllIllllIIIlIlIIlIl) {
            this.listeners.get(llllllllllllllllIllllIIIlIlIIlIl).musicEnded(this);
        }
    }
    
    public void play(final float llllllllllllllllIllllIIIlIIIlIll, final float llllllllllllllllIllllIIIlIIIllIl) {
        this.startMusic(llllllllllllllllIllllIIIlIIIlIll, llllllllllllllllIllllIIIlIIIllIl, false);
    }
    
    public void addListener(final MusicListener llllllllllllllllIllllIIIlIlIlllI) {
        this.listeners.add(llllllllllllllllIllllIIIlIlIlllI);
    }
    
    public void removeListener(final MusicListener llllllllllllllllIllllIIIlIlIlIlI) {
        this.listeners.remove(llllllllllllllllIllllIIIlIlIlIlI);
    }
    
    public Music(final InputStream llllllllllllllllIllllIIIllIlIIIl, final String llllllllllllllllIllllIIIllIlIIII) throws SlickException {
        this.listeners = new ArrayList();
        this.volume = 1.0f;
        this.requiredPosition = -1.0f;
        SoundStore.get().init();
        try {
            if (llllllllllllllllIllllIIIllIlIIII.toLowerCase().endsWith(".ogg")) {
                this.sound = SoundStore.get().getOgg(llllllllllllllllIllllIIIllIlIIIl);
            }
            else if (llllllllllllllllIllllIIIllIlIIII.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(llllllllllllllllIllllIIIllIlIIIl);
            }
            else if (llllllllllllllllIllllIIIllIlIIII.toLowerCase().endsWith(".xm") || llllllllllllllllIllllIIIllIlIIII.toLowerCase().endsWith(".mod")) {
                this.sound = SoundStore.get().getMOD(llllllllllllllllIllllIIIllIlIIIl);
            }
            else {
                if (!llllllllllllllllIllllIIIllIlIIII.toLowerCase().endsWith(".aif") && !llllllllllllllllIllllIIIllIlIIII.toLowerCase().endsWith(".aiff")) {
                    throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
                }
                this.sound = SoundStore.get().getAIF(llllllllllllllllIllllIIIllIlIIIl);
            }
        }
        catch (Exception llllllllllllllllIllllIIIllIlIllI) {
            Log.error(llllllllllllllllIllllIIIllIlIllI);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load music: ").append(llllllllllllllllIllllIIIllIlIIII)));
        }
    }
    
    private void startMusic(final float llllllllllllllllIllllIIIIllllIll, float llllllllllllllllIllllIIIIlllIllI, final boolean llllllllllllllllIllllIIIIlllIlIl) {
        if (Music.currentMusic != null) {
            Music.currentMusic.stop();
            Music.currentMusic.fireMusicSwapped(this);
        }
        Music.currentMusic = this;
        if (llllllllllllllllIllllIIIIlllIllI < 0.0f) {
            llllllllllllllllIllllIIIIlllIllI = 0.0f;
        }
        if (llllllllllllllllIllllIIIIlllIllI > 1.0f) {
            llllllllllllllllIllllIIIIlllIllI = 1.0f;
        }
        this.sound.playAsMusic(llllllllllllllllIllllIIIIllllIll, (float)llllllllllllllllIllllIIIIlllIllI, llllllllllllllllIllllIIIIlllIlIl);
        this.playing = true;
        this.setVolume((float)llllllllllllllllIllllIIIIlllIllI);
        if (this.requiredPosition != -1.0f) {
            this.setPosition(this.requiredPosition);
        }
    }
    
    public Music(final URL llllllllllllllllIllllIIIllIllIll) throws SlickException {
        this(llllllllllllllllIllllIIIllIllIll, false);
    }
    
    public void pause() {
        this.playing = false;
        AudioImpl.pauseMusic();
    }
    
    public Music(final String llllllllllllllllIllllIIIlIlllIIl, final boolean llllllllllllllllIllllIIIlIllIlIl) throws SlickException {
        this.listeners = new ArrayList();
        this.volume = 1.0f;
        this.requiredPosition = -1.0f;
        SoundStore.get().init();
        try {
            if (llllllllllllllllIllllIIIlIlllIIl.toLowerCase().endsWith(".ogg")) {
                if (llllllllllllllllIllllIIIlIllIlIl) {
                    this.sound = SoundStore.get().getOggStream(llllllllllllllllIllllIIIlIlllIIl);
                }
                else {
                    this.sound = SoundStore.get().getOgg(llllllllllllllllIllllIIIlIlllIIl);
                }
            }
            else if (llllllllllllllllIllllIIIlIlllIIl.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(llllllllllllllllIllllIIIlIlllIIl);
            }
            else if (llllllllllllllllIllllIIIlIlllIIl.toLowerCase().endsWith(".xm") || llllllllllllllllIllllIIIlIlllIIl.toLowerCase().endsWith(".mod")) {
                this.sound = SoundStore.get().getMOD(llllllllllllllllIllllIIIlIlllIIl);
            }
            else {
                if (!llllllllllllllllIllllIIIlIlllIIl.toLowerCase().endsWith(".aif") && !llllllllllllllllIllllIIIlIlllIIl.toLowerCase().endsWith(".aiff")) {
                    throw new SlickException("Only .xm, .mod, .ogg, and .aif/f are currently supported.");
                }
                this.sound = SoundStore.get().getAIF(llllllllllllllllIllllIIIlIlllIIl);
            }
        }
        catch (Exception llllllllllllllllIllllIIIlIlllIll) {
            Log.error(llllllllllllllllIllllIIIlIlllIll);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load sound: ").append(llllllllllllllllIllllIIIlIlllIIl)));
        }
    }
    
    private void fireMusicSwapped(final Music llllllllllllllllIllllIIIlIIlllII) {
        this.playing = false;
        for (int llllllllllllllllIllllIIIlIIllllI = 0; llllllllllllllllIllllIIIlIIllllI < this.listeners.size(); ++llllllllllllllllIllllIIIlIIllllI) {
            this.listeners.get(llllllllllllllllIllllIIIlIIllllI).musicSwapped(this, llllllllllllllllIllllIIIlIIlllII);
        }
    }
    
    public void loop() {
        this.loop(1.0f, 1.0f);
    }
}
