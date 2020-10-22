package org.newdawn.slick.openal;

import java.net.*;
import java.nio.*;
import java.io.*;
import org.lwjgl.*;
import org.lwjgl.openal.*;
import org.newdawn.slick.util.*;

public class OpenALStreamPlayer
{
    private /* synthetic */ IntBuffer bufferNames;
    private /* synthetic */ boolean loop;
    private /* synthetic */ int source;
    private /* synthetic */ float positionOffset;
    private /* synthetic */ AudioInputStream audio;
    private /* synthetic */ URL url;
    private /* synthetic */ float pitch;
    private /* synthetic */ ByteBuffer bufferData;
    private /* synthetic */ int remainingBufferCount;
    private /* synthetic */ IntBuffer unqueued;
    private /* synthetic */ String ref;
    private /* synthetic */ byte[] buffer;
    private /* synthetic */ boolean done;
    
    public String getSource() {
        return (this.url == null) ? this.ref : this.url.toString();
    }
    
    public void play(final boolean lIIlIlIlIlllII) throws IOException {
        this.loop = lIIlIlIlIlllII;
        this.initStreams();
        this.done = false;
        AL10.alSourceStop(this.source);
        this.removeBuffers();
        this.startPlayback();
    }
    
    private void startPlayback() {
        AL10.alSourcei(this.source, 4103, 0);
        AL10.alSourcef(this.source, 4099, this.pitch);
        this.remainingBufferCount = 3;
        for (int lIIlIlIIIllIII = 0; lIIlIlIIIllIII < 3; ++lIIlIlIIIllIII) {
            this.stream(this.bufferNames.get(lIIlIlIIIllIII));
        }
        AL10.alSourceQueueBuffers(this.source, this.bufferNames);
        AL10.alSourcePlay(this.source);
    }
    
    public float getPosition() {
        return this.positionOffset + AL10.alGetSourcef(this.source, 4132);
    }
    
    public boolean setPosition(final float lIIlIlIIIlllll) {
        try {
            if (this.getPosition() > lIIlIlIIIlllll) {
                this.initStreams();
            }
            final float lIIlIlIIlIIlIl = (float)this.audio.getRate();
            float lIIlIlIIlIIlII = 0.0f;
            if (this.audio.getChannels() > 1) {
                final float lIIlIlIIlIlIII = 4.0f;
            }
            else {
                lIIlIlIIlIIlII = 2.0f;
            }
            while (this.positionOffset < lIIlIlIIIlllll) {
                final int lIIlIlIIlIIllI = this.audio.read(this.buffer);
                if (lIIlIlIIlIIllI == -1) {
                    if (this.loop) {
                        this.initStreams();
                    }
                    else {
                        this.done = true;
                    }
                    return false;
                }
                final float lIIlIlIIlIIlll = lIIlIlIIlIIllI / lIIlIlIIlIIlII / lIIlIlIIlIIlIl;
                this.positionOffset += lIIlIlIIlIIlll;
            }
            this.startPlayback();
            return true;
        }
        catch (IOException lIIlIlIIlIIIll) {
            Log.error(lIIlIlIIlIIIll);
            return false;
        }
    }
    
    public void update() {
        if (this.done) {
            return;
        }
        final float lIIlIlIlIIlIII = (float)this.audio.getRate();
        float lIIlIlIlIIIlll = 0.0f;
        if (this.audio.getChannels() > 1) {
            final float lIIlIlIlIIllII = 4.0f;
        }
        else {
            lIIlIlIlIIIlll = 2.0f;
        }
        for (int lIIlIlIlIIIllI = AL10.alGetSourcei(this.source, 4118); lIIlIlIlIIIllI > 0; --lIIlIlIlIIIllI) {
            this.unqueued.clear();
            AL10.alSourceUnqueueBuffers(this.source, this.unqueued);
            final int lIIlIlIlIIlIll = this.unqueued.get(0);
            final float lIIlIlIlIIlIlI = AL10.alGetBufferi(lIIlIlIlIIlIll, 8196) / lIIlIlIlIIIlll / lIIlIlIlIIlIII;
            this.positionOffset += lIIlIlIlIIlIlI;
            if (this.stream(lIIlIlIlIIlIll)) {
                AL10.alSourceQueueBuffers(this.source, this.unqueued);
            }
            else {
                --this.remainingBufferCount;
                if (this.remainingBufferCount == 0) {
                    this.done = true;
                }
            }
        }
        final int lIIlIlIlIIIlIl = AL10.alGetSourcei(this.source, 4112);
        if (lIIlIlIlIIIlIl != 4114) {
            AL10.alSourcePlay(this.source);
        }
    }
    
    public boolean done() {
        return this.done;
    }
    
    private void removeBuffers() {
        final IntBuffer lIIlIlIllIIllI = BufferUtils.createIntBuffer(1);
        for (int lIIlIlIllIIlIl = AL10.alGetSourcei(this.source, 4117); lIIlIlIllIIlIl > 0; --lIIlIlIllIIlIl) {
            AL10.alSourceUnqueueBuffers(this.source, lIIlIlIllIIllI);
        }
    }
    
    public OpenALStreamPlayer(final int lIIlIlIlllIllI, final URL lIIlIlIllllIII) {
        this.buffer = new byte[81920];
        this.bufferData = BufferUtils.createByteBuffer(81920);
        this.unqueued = BufferUtils.createIntBuffer(1);
        this.done = true;
        this.source = lIIlIlIlllIllI;
        this.url = lIIlIlIllllIII;
        this.bufferNames = BufferUtils.createIntBuffer(3);
        AL10.alGenBuffers(this.bufferNames);
    }
    
    public void setup(final float lIIlIlIlIlIllI) {
        this.pitch = lIIlIlIlIlIllI;
    }
    
    static {
        BUFFER_COUNT = 3;
        sectionSize = 81920;
    }
    
    public boolean stream(final int lIIlIlIIllIIlI) {
        try {
            final int lIIlIlIIllIlll = this.audio.read(this.buffer);
            if (lIIlIlIIllIlll != -1) {
                this.bufferData.clear();
                this.bufferData.put(this.buffer, 0, lIIlIlIIllIlll);
                this.bufferData.flip();
                final int lIIlIlIIlllIII = (this.audio.getChannels() > 1) ? 4355 : 4353;
                try {
                    AL10.alBufferData(lIIlIlIIllIIlI, lIIlIlIIlllIII, this.bufferData, this.audio.getRate());
                }
                catch (OpenALException lIIlIlIIlllIIl) {
                    Log.error(String.valueOf(new StringBuilder().append("Failed to loop buffer: ").append(lIIlIlIIllIIlI).append(" ").append(lIIlIlIIlllIII).append(" ").append(lIIlIlIIllIlll).append(" ").append(this.audio.getRate())), (Throwable)lIIlIlIIlllIIl);
                    return false;
                }
            }
            else {
                if (!this.loop) {
                    this.done = true;
                    return false;
                }
                this.initStreams();
                this.stream(lIIlIlIIllIIlI);
            }
            return true;
        }
        catch (IOException lIIlIlIIllIllI) {
            Log.error(lIIlIlIIllIllI);
            return false;
        }
    }
    
    private void initStreams() throws IOException {
        if (this.audio != null) {
            this.audio.close();
        }
        OggInputStream lIIlIlIlllIIII = null;
        if (this.url != null) {
            final OggInputStream lIIlIlIlllIIlI = new OggInputStream(this.url.openStream());
        }
        else {
            lIIlIlIlllIIII = new OggInputStream(ResourceLoader.getResourceAsStream(this.ref));
        }
        this.audio = lIIlIlIlllIIII;
        this.positionOffset = 0.0f;
    }
    
    public OpenALStreamPlayer(final int lIIlIlIlllllll, final String lIIlIllIIIIIIl) {
        this.buffer = new byte[81920];
        this.bufferData = BufferUtils.createByteBuffer(81920);
        this.unqueued = BufferUtils.createIntBuffer(1);
        this.done = true;
        this.source = lIIlIlIlllllll;
        this.ref = lIIlIllIIIIIIl;
        this.bufferNames = BufferUtils.createIntBuffer(3);
        AL10.alGenBuffers(this.bufferNames);
    }
}
