package org.newdawn.slick.openal;

import java.util.*;
import java.nio.*;
import org.lwjgl.*;
import org.newdawn.slick.util.*;
import java.io.*;
import java.net.*;
import java.security.*;
import org.lwjgl.openal.*;

public class SoundStore
{
    private /* synthetic */ FloatBuffer sourcePos;
    private /* synthetic */ boolean soundWorks;
    private /* synthetic */ HashMap loaded;
    private static /* synthetic */ SoundStore store;
    private /* synthetic */ boolean deferred;
    private /* synthetic */ float musicVolume;
    private /* synthetic */ int sourceCount;
    private /* synthetic */ FloatBuffer sourceVel;
    private /* synthetic */ MODSound mod;
    private /* synthetic */ int maxSources;
    private /* synthetic */ boolean inited;
    private /* synthetic */ boolean sounds;
    private /* synthetic */ float lastCurrentMusicVolume;
    private /* synthetic */ int currentMusic;
    private /* synthetic */ float soundVolume;
    private /* synthetic */ boolean paused;
    private /* synthetic */ OpenALStreamPlayer stream;
    private /* synthetic */ IntBuffer sources;
    private /* synthetic */ boolean music;
    
    public Audio getWAV(final String lIllIlIlIIlIll, final InputStream lIllIlIlIIlllI) throws IOException {
        if (!this.soundWorks) {
            return new NullAudio();
        }
        if (!this.inited) {
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
        }
        if (this.deferred) {
            return new DeferredSound(lIllIlIlIIlIll, lIllIlIlIIlllI, 2);
        }
        int lIllIlIlIIllIl = -1;
        if (this.loaded.get(lIllIlIlIIlIll) != null) {
            lIllIlIlIIllIl = this.loaded.get(lIllIlIlIIlIll);
        }
        else {
            try {
                final IntBuffer lIllIlIlIlIlII = BufferUtils.createIntBuffer(1);
                final WaveData lIllIlIlIlIIll = WaveData.create(lIllIlIlIIlllI);
                AL10.alGenBuffers(lIllIlIlIlIlII);
                AL10.alBufferData(lIllIlIlIlIlII.get(0), lIllIlIlIlIIll.format, lIllIlIlIlIIll.data, lIllIlIlIlIIll.samplerate);
                this.loaded.put(lIllIlIlIIlIll, new Integer(lIllIlIlIlIlII.get(0)));
                lIllIlIlIIllIl = lIllIlIlIlIlII.get(0);
            }
            catch (Exception lIllIlIlIlIIIl) {
                Log.error(lIllIlIlIlIIIl);
                final IOException lIllIlIlIlIIlI = new IOException(String.valueOf(new StringBuilder().append("Failed to load: ").append(lIllIlIlIIlIll)));
                lIllIlIlIlIIlI.initCause(lIllIlIlIlIIIl);
                throw lIllIlIlIlIIlI;
            }
        }
        if (lIllIlIlIIllIl == -1) {
            throw new IOException(String.valueOf(new StringBuilder().append("Unable to load: ").append(lIllIlIlIIlIll)));
        }
        return new AudioImpl(this, lIllIlIlIIllIl);
    }
    
    public Audio getOgg(final String lIllIlIIlIIIlI, final InputStream lIllIlIIlIIIIl) throws IOException {
        if (!this.soundWorks) {
            return new NullAudio();
        }
        if (!this.inited) {
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
        }
        if (this.deferred) {
            return new DeferredSound(lIllIlIIlIIIlI, lIllIlIIlIIIIl, 1);
        }
        int lIllIlIIlIIIII = -1;
        if (this.loaded.get(lIllIlIIlIIIlI) != null) {
            lIllIlIIlIIIII = this.loaded.get(lIllIlIIlIIIlI);
        }
        else {
            try {
                final IntBuffer lIllIlIIlIIlll = BufferUtils.createIntBuffer(1);
                final OggDecoder lIllIlIIlIIllI = new OggDecoder();
                final OggData lIllIlIIlIIlIl = lIllIlIIlIIllI.getData(lIllIlIIlIIIIl);
                AL10.alGenBuffers(lIllIlIIlIIlll);
                AL10.alBufferData(lIllIlIIlIIlll.get(0), (lIllIlIIlIIlIl.channels > 1) ? 4355 : 4353, lIllIlIIlIIlIl.data, lIllIlIIlIIlIl.rate);
                this.loaded.put(lIllIlIIlIIIlI, new Integer(lIllIlIIlIIlll.get(0)));
                lIllIlIIlIIIII = lIllIlIIlIIlll.get(0);
            }
            catch (Exception lIllIlIIlIIlII) {
                Log.error(lIllIlIIlIIlII);
                Sys.alert("Error", String.valueOf(new StringBuilder().append("Failed to load: ").append(lIllIlIIlIIIlI).append(" - ").append(lIllIlIIlIIlII.getMessage())));
                throw new IOException(String.valueOf(new StringBuilder().append("Unable to load: ").append(lIllIlIIlIIIlI)));
            }
        }
        if (lIllIlIIlIIIII == -1) {
            throw new IOException(String.valueOf(new StringBuilder().append("Unable to load: ").append(lIllIlIIlIIIlI)));
        }
        return new AudioImpl(this, lIllIlIIlIIIII);
    }
    
    public boolean musicOn() {
        return this.music;
    }
    
    public void pauseLoop() {
        if (this.soundWorks && this.currentMusic != -1) {
            this.paused = true;
            AL10.alSourcePause(this.currentMusic);
        }
    }
    
    public boolean isMusicPlaying() {
        if (!this.soundWorks) {
            return false;
        }
        final int lIllIlIIIIIIIl = AL10.alGetSourcei(this.sources.get(0), 4112);
        return lIllIlIIIIIIIl == 4114 || lIllIlIIIIIIIl == 4115;
    }
    
    static {
        SoundStore.store = new SoundStore();
    }
    
    private int getMusicSource() {
        return this.sources.get(0);
    }
    
    public Audio getMOD(final InputStream lIllIllIIlIIlI) throws IOException {
        return this.getMOD(lIllIllIIlIIlI.toString(), lIllIllIIlIIlI);
    }
    
    boolean isPlaying(final OpenALStreamPlayer lIllIllIIlllII) {
        return this.stream == lIllIllIIlllII;
    }
    
    public Audio getMOD(final String lIllIllIIllIII) throws IOException {
        return this.getMOD(lIllIllIIllIII, ResourceLoader.getResourceAsStream(lIllIllIIllIII));
    }
    
    public Audio getAIF(final String lIllIlIllIlIll, InputStream lIllIlIllIlIlI) throws IOException {
        lIllIlIllIlIlI = new BufferedInputStream(lIllIlIllIlIlI);
        if (!this.soundWorks) {
            return new NullAudio();
        }
        if (!this.inited) {
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
        }
        if (this.deferred) {
            return new DeferredSound(lIllIlIllIlIll, lIllIlIllIlIlI, 4);
        }
        int lIllIlIllIllIl = -1;
        if (this.loaded.get(lIllIlIllIlIll) != null) {
            lIllIlIllIllIl = this.loaded.get(lIllIlIllIlIll);
        }
        else {
            try {
                final IntBuffer lIllIlIlllIlII = BufferUtils.createIntBuffer(1);
                final AiffData lIllIlIlllIIll = AiffData.create(lIllIlIllIlIlI);
                AL10.alGenBuffers(lIllIlIlllIlII);
                AL10.alBufferData(lIllIlIlllIlII.get(0), lIllIlIlllIIll.format, lIllIlIlllIIll.data, lIllIlIlllIIll.samplerate);
                this.loaded.put(lIllIlIllIlIll, new Integer(lIllIlIlllIlII.get(0)));
                lIllIlIllIllIl = lIllIlIlllIlII.get(0);
            }
            catch (Exception lIllIlIlllIIIl) {
                Log.error(lIllIlIlllIIIl);
                final IOException lIllIlIlllIIlI = new IOException(String.valueOf(new StringBuilder().append("Failed to load: ").append(lIllIlIllIlIll)));
                lIllIlIlllIIlI.initCause(lIllIlIlllIIIl);
                throw lIllIlIlllIIlI;
            }
        }
        if (lIllIlIllIllIl == -1) {
            throw new IOException(String.valueOf(new StringBuilder().append("Unable to load: ").append(lIllIlIllIlIll)));
        }
        return new AudioImpl(this, lIllIlIllIllIl);
    }
    
    void setStream(final OpenALStreamPlayer lIllIlIIIIllIl) {
        if (!this.soundWorks) {
            return;
        }
        this.currentMusic = this.sources.get(0);
        if ((this.stream = lIllIlIIIIllIl) != null) {
            this.mod = null;
        }
        this.paused = false;
    }
    
    public void setCurrentMusicVolume(float lIlllIIIllIlIl) {
        if (lIlllIIIllIlIl < 0.0f) {
            lIlllIIIllIlIl = 0.0f;
        }
        if (lIlllIIIllIlIl > 1.0f) {
            lIlllIIIllIlIl = 1.0f;
        }
        if (this.soundWorks) {
            this.lastCurrentMusicVolume = lIlllIIIllIlIl;
            AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
        }
    }
    
    private int findFreeSource() {
        for (int lIllIlllIIIIlI = 1; lIllIlllIIIIlI < this.sourceCount - 1; ++lIllIlllIIIIlI) {
            final int lIllIlllIIIIll = AL10.alGetSourcei(this.sources.get(lIllIlllIIIIlI), 4112);
            if (lIllIlllIIIIll != 4114 && lIllIlllIIIIll != 4115) {
                return lIllIlllIIIIlI;
            }
        }
        return -1;
    }
    
    public void setMusicVolume(float lIlllIIIlllllI) {
        if (lIlllIIIlllllI < 0.0f) {
            lIlllIIIlllllI = 0.0f;
        }
        if (lIlllIIIlllllI > 1.0f) {
            lIlllIIIlllllI = 1.0f;
        }
        this.musicVolume = lIlllIIIlllllI;
        if (this.soundWorks) {
            AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
        }
    }
    
    public boolean isMusicOn() {
        return this.music;
    }
    
    public int getSource(final int lIlllIIIIlllll) {
        if (!this.soundWorks) {
            return -1;
        }
        if (lIlllIIIIlllll < 0) {
            return -1;
        }
        return this.sources.get(lIlllIIIIlllll);
    }
    
    public void setDeferredLoading(final boolean lIlllIIlIlIIII) {
        this.deferred = lIlllIIlIlIIII;
    }
    
    public Audio getWAV(final String lIllIlIllIIIIl) throws IOException {
        return this.getWAV(lIllIlIllIIIIl, ResourceLoader.getResourceAsStream(lIllIlIllIIIIl));
    }
    
    void stopSource(final int lIllIllllllIlI) {
        AL10.alSourceStop(this.sources.get(lIllIllllllIlI));
    }
    
    public void clear() {
        SoundStore.store = new SoundStore();
    }
    
    public void setMaxSources(final int lIlllIIIIIlllI) {
        this.maxSources = lIlllIIIIIlllI;
    }
    
    public Audio getAIF(final InputStream lIllIlIllllIll) throws IOException {
        return this.getAIF(lIllIlIllllIll.toString(), lIllIlIllllIll);
    }
    
    public boolean soundsOn() {
        return this.sounds;
    }
    
    public void disable() {
        this.inited = true;
    }
    
    public Audio getOgg(final InputStream lIllIlIIlIllll) throws IOException {
        return this.getOgg(lIllIlIIlIllll.toString(), lIllIlIIlIllll);
    }
    
    boolean isPlaying(final int lIllIlllIIlIII) {
        final int lIllIlllIIlIlI = AL10.alGetSourcei(this.sources.get(lIllIlllIIlIII), 4112);
        return lIllIlllIIlIlI == 4114;
    }
    
    public boolean soundWorks() {
        return this.soundWorks;
    }
    
    private SoundStore() {
        this.loaded = new HashMap();
        this.currentMusic = -1;
        this.inited = false;
        this.musicVolume = 1.0f;
        this.soundVolume = 1.0f;
        this.lastCurrentMusicVolume = 1.0f;
        this.sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
        this.sourcePos = BufferUtils.createFloatBuffer(3);
        this.maxSources = 64;
    }
    
    public Audio getOggStream(final URL lIllIlIIllllIl) throws IOException {
        if (!this.soundWorks) {
            return new NullAudio();
        }
        this.setMOD(null);
        this.setStream(null);
        if (this.currentMusic != -1) {
            AL10.alSourceStop(this.sources.get(0));
        }
        this.getMusicSource();
        this.currentMusic = this.sources.get(0);
        return new StreamSound(new OpenALStreamPlayer(this.currentMusic, lIllIlIIllllIl));
    }
    
    public Audio getMOD(final String lIllIllIIIlIll, final InputStream lIllIllIIIlIlI) throws IOException {
        if (!this.soundWorks) {
            return new NullAudio();
        }
        if (!this.inited) {
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
        }
        if (this.deferred) {
            return new DeferredSound(lIllIllIIIlIll, lIllIllIIIlIlI, 3);
        }
        return new MODSound(this, lIllIllIIIlIlI);
    }
    
    int playAsSoundAt(final int lIllIlllIlIlll, final float lIllIlllIlIllI, float lIllIlllIlIlIl, final boolean lIllIlllIlllII, final float lIllIlllIllIll, final float lIllIlllIllIlI, final float lIllIlllIlIIIl) {
        lIllIlllIlIlIl *= this.soundVolume;
        if (lIllIlllIlIlIl == 0.0f) {
            lIllIlllIlIlIl = 0.001f;
        }
        if (!this.soundWorks || !this.sounds) {
            return -1;
        }
        final int lIllIllllIIIIl = this.findFreeSource();
        if (lIllIllllIIIIl == -1) {
            return -1;
        }
        AL10.alSourceStop(this.sources.get(lIllIllllIIIIl));
        AL10.alSourcei(this.sources.get(lIllIllllIIIIl), 4105, lIllIlllIlIlll);
        AL10.alSourcef(this.sources.get(lIllIllllIIIIl), 4099, lIllIlllIlIllI);
        AL10.alSourcef(this.sources.get(lIllIllllIIIIl), 4106, lIllIlllIlIlIl);
        AL10.alSourcei(this.sources.get(lIllIllllIIIIl), 4103, (int)(lIllIlllIlllII ? 1 : 0));
        this.sourcePos.clear();
        this.sourceVel.clear();
        this.sourceVel.put(new float[] { 0.0f, 0.0f, 0.0f });
        this.sourcePos.put(new float[] { lIllIlllIllIll, lIllIlllIllIlI, lIllIlllIlIIIl });
        this.sourcePos.flip();
        this.sourceVel.flip();
        AL10.alSource(this.sources.get(lIllIllllIIIIl), 4100, this.sourcePos);
        AL10.alSource(this.sources.get(lIllIllllIIIIl), 4102, this.sourceVel);
        AL10.alSourcePlay(this.sources.get(lIllIllllIIIIl));
        return lIllIllllIIIIl;
    }
    
    public static SoundStore get() {
        return SoundStore.store;
    }
    
    public float getCurrentMusicVolume() {
        return this.lastCurrentMusicVolume;
    }
    
    public int getSourceCount() {
        return this.sourceCount;
    }
    
    public void setMusicOn(final boolean lIlllIIlIIIlll) {
        if (this.soundWorks) {
            this.music = lIlllIIlIIIlll;
            if (lIlllIIlIIIlll) {
                this.restartLoop();
                this.setMusicVolume(this.musicVolume);
            }
            else {
                this.pauseLoop();
            }
        }
    }
    
    void setMOD(final MODSound lIllIlIIIlIIll) {
        if (!this.soundWorks) {
            return;
        }
        this.currentMusic = this.sources.get(0);
        this.stopSource(0);
        if ((this.mod = lIllIlIIIlIIll) != null) {
            this.stream = null;
        }
        this.paused = false;
    }
    
    public void setSoundsOn(final boolean lIlllIIIIlIlll) {
        if (this.soundWorks) {
            this.sounds = lIlllIIIIlIlll;
        }
    }
    
    int playAsSound(final int lIllIlllllIIll, final float lIllIllllIllIl, final float lIllIlllllIIIl, final boolean lIllIlllllIIII) {
        return this.playAsSoundAt(lIllIlllllIIll, lIllIllllIllIl, lIllIlllllIIIl, lIllIlllllIIII, 0.0f, 0.0f, 0.0f);
    }
    
    public void init() {
        if (this.inited) {
            return;
        }
        Log.info("Initialising sounds..");
        this.inited = true;
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    AL.create();
                    SoundStore.this.soundWorks = true;
                    SoundStore.this.sounds = true;
                    SoundStore.this.music = true;
                    Log.info("- Sound works");
                }
                catch (Exception llIIIIlIIIlllIl) {
                    Log.error("Sound initialisation failure.");
                    Log.error(llIIIIlIIIlllIl);
                    SoundStore.this.soundWorks = false;
                    SoundStore.this.sounds = false;
                    SoundStore.this.music = false;
                }
                return null;
            }
        });
        if (this.soundWorks) {
            this.sourceCount = 0;
            this.sources = BufferUtils.createIntBuffer(this.maxSources);
            while (AL10.alGetError() == 0) {
                final IntBuffer lIlllIIIIIlIII = BufferUtils.createIntBuffer(1);
                try {
                    AL10.alGenSources(lIlllIIIIIlIII);
                    if (AL10.alGetError() != 0) {
                        continue;
                    }
                    ++this.sourceCount;
                    this.sources.put(lIlllIIIIIlIII.get(0));
                    if (this.sourceCount > this.maxSources - 1) {
                        break;
                    }
                    continue;
                }
                catch (OpenALException lIlllIIIIIlIIl) {
                    break;
                }
            }
            Log.info(String.valueOf(new StringBuilder().append("- ").append(this.sourceCount).append(" OpenAL source available")));
            if (AL10.alGetError() != 0) {
                this.sounds = false;
                this.music = false;
                this.soundWorks = false;
                Log.error("- AL init failed");
            }
            else {
                final FloatBuffer lIlllIIIIIIlll = BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f });
                final FloatBuffer lIlllIIIIIIllI = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
                final FloatBuffer lIlllIIIIIIlIl = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
                lIlllIIIIIIlIl.flip();
                lIlllIIIIIIllI.flip();
                lIlllIIIIIIlll.flip();
                AL10.alListener(4100, lIlllIIIIIIlIl);
                AL10.alListener(4102, lIlllIIIIIIllI);
                AL10.alListener(4111, lIlllIIIIIIlll);
                Log.info("- Sounds source generated");
            }
        }
    }
    
    public Audio getWAV(final InputStream lIllIlIlIlllIl) throws IOException {
        return this.getWAV(lIllIlIlIlllIl.toString(), lIllIlIlIlllIl);
    }
    
    public void poll(final int lIllIlIIIIIlll) {
        if (!this.soundWorks) {
            return;
        }
        if (this.paused) {
            return;
        }
        if (this.music) {
            if (this.mod != null) {
                try {
                    this.mod.poll();
                }
                catch (OpenALException lIllIlIIIIlIlI) {
                    Log.error("Error with OpenGL MOD Player on this this platform");
                    Log.error((Throwable)lIllIlIIIIlIlI);
                    this.mod = null;
                }
            }
            if (this.stream != null) {
                try {
                    this.stream.update();
                }
                catch (OpenALException lIllIlIIIIlIIl) {
                    Log.error("Error with OpenGL Streaming Player on this this platform");
                    Log.error((Throwable)lIllIlIIIIlIIl);
                    this.mod = null;
                }
            }
        }
    }
    
    public Audio getAIF(final String lIllIllIIIIIll) throws IOException {
        return this.getAIF(lIllIllIIIIIll, ResourceLoader.getResourceAsStream(lIllIllIIIIIll));
    }
    
    public float getSoundVolume() {
        return this.soundVolume;
    }
    
    public Audio getOggStream(final String lIllIlIlIIIIll) throws IOException {
        if (!this.soundWorks) {
            return new NullAudio();
        }
        this.setMOD(null);
        this.setStream(null);
        if (this.currentMusic != -1) {
            AL10.alSourceStop(this.sources.get(0));
        }
        this.getMusicSource();
        this.currentMusic = this.sources.get(0);
        return new StreamSound(new OpenALStreamPlayer(this.currentMusic, lIllIlIlIIIIll));
    }
    
    public void stopSoundEffect(final int lIllIIllllllII) {
        AL10.alSourceStop(lIllIIllllllII);
    }
    
    public void restartLoop() {
        if (this.music && this.soundWorks && this.currentMusic != -1) {
            this.paused = false;
            AL10.alSourcePlay(this.currentMusic);
        }
    }
    
    public boolean isDeferredLoading() {
        return this.deferred;
    }
    
    public void setSoundVolume(float lIlllIIIlIllll) {
        if (lIlllIIIlIllll < 0.0f) {
            lIlllIIIlIllll = 0.0f;
        }
        this.soundVolume = (float)lIlllIIIlIllll;
    }
    
    public void setMusicPitch(final float lIllIllIlIlIII) {
        if (this.soundWorks) {
            AL10.alSourcef(this.sources.get(0), 4099, lIllIllIlIlIII);
        }
    }
    
    public Audio getOgg(final String lIllIlIIllIlIl) throws IOException {
        return this.getOgg(lIllIlIIllIlIl, ResourceLoader.getResourceAsStream(lIllIlIIllIlIl));
    }
    
    public float getMusicVolume() {
        return this.musicVolume;
    }
    
    void playAsMusic(final int lIllIllIlllIII, final float lIllIllIllIlll, final float lIllIllIllIllI, final boolean lIllIllIllIIIl) {
        this.paused = false;
        this.setMOD(null);
        if (this.soundWorks) {
            if (this.currentMusic != -1) {
                AL10.alSourceStop(this.sources.get(0));
            }
            this.getMusicSource();
            AL10.alSourcei(this.sources.get(0), 4105, lIllIllIlllIII);
            AL10.alSourcef(this.sources.get(0), 4099, lIllIllIllIlll);
            AL10.alSourcei(this.sources.get(0), 4103, (int)(lIllIllIllIIIl ? 1 : 0));
            this.currentMusic = this.sources.get(0);
            if (!this.music) {
                this.pauseLoop();
            }
            else {
                AL10.alSourcePlay(this.sources.get(0));
            }
        }
    }
}
