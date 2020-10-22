package org.newdawn.slick.openal;

import java.net.*;
import java.io.*;

public class AudioLoader
{
    private static /* synthetic */ boolean inited;
    
    public static void update() {
        init();
        SoundStore.get().poll(0);
    }
    
    public static Audio getStreamingAudio(final String lllIlIlIlIIIll, final URL lllIlIlIlIIIlI) throws IOException {
        init();
        if (lllIlIlIlIIIll.equals("OGG")) {
            return SoundStore.get().getOggStream(lllIlIlIlIIIlI);
        }
        if (lllIlIlIlIIIll.equals("MOD")) {
            return SoundStore.get().getMOD(lllIlIlIlIIIlI.openStream());
        }
        if (lllIlIlIlIIIll.equals("XM")) {
            return SoundStore.get().getMOD(lllIlIlIlIIIlI.openStream());
        }
        throw new IOException(String.valueOf(new StringBuilder().append("Unsupported format for streaming Audio: ").append(lllIlIlIlIIIll)));
    }
    
    private static void init() {
        if (!AudioLoader.inited) {
            SoundStore.get().init();
            AudioLoader.inited = true;
        }
    }
    
    public static Audio getAudio(final String lllIlIlIlIlIIl, final InputStream lllIlIlIlIIllI) throws IOException {
        init();
        if (lllIlIlIlIlIIl.equals("AIF")) {
            return SoundStore.get().getAIF(lllIlIlIlIIllI);
        }
        if (lllIlIlIlIlIIl.equals("WAV")) {
            return SoundStore.get().getWAV(lllIlIlIlIIllI);
        }
        if (lllIlIlIlIlIIl.equals("OGG")) {
            return SoundStore.get().getOgg(lllIlIlIlIIllI);
        }
        throw new IOException(String.valueOf(new StringBuilder().append("Unsupported format for non-streaming Audio: ").append(lllIlIlIlIlIIl)));
    }
    
    static {
        WAV = "WAV";
        OGG = "OGG";
        AIF = "AIF";
        XM = "XM";
        MOD = "MOD";
        AudioLoader.inited = false;
    }
}
