package org.newdawn.slick.openal;

import org.lwjgl.*;
import java.io.*;
import javax.sound.sampled.*;
import java.net.*;
import java.nio.*;

public class AiffData
{
    public final /* synthetic */ int format;
    public final /* synthetic */ ByteBuffer data;
    public final /* synthetic */ int samplerate;
    
    public static AiffData create(final InputStream llllllllllllllllIlIIlllllIlIlIIl) {
        try {
            return create(AudioSystem.getAudioInputStream(llllllllllllllllIlIIlllllIlIlIIl));
        }
        catch (Exception llllllllllllllllIlIIlllllIlIlIlI) {
            LWJGLUtil.log((CharSequence)"Unable to create from inputstream");
            llllllllllllllllIlIIlllllIlIlIlI.printStackTrace();
            return null;
        }
    }
    
    public static AiffData create(final byte[] llllllllllllllllIlIIlllllIlIIIll) {
        try {
            return create(AudioSystem.getAudioInputStream(new BufferedInputStream(new ByteArrayInputStream(llllllllllllllllIlIIlllllIlIIIll))));
        }
        catch (Exception llllllllllllllllIlIIlllllIlIIlII) {
            llllllllllllllllIlIIlllllIlIIlII.printStackTrace();
            return null;
        }
    }
    
    public static AiffData create(final AudioInputStream llllllllllllllllIlIIlllllIIIIlll) {
        final AudioFormat llllllllllllllllIlIIlllllIIIlllI = llllllllllllllllIlIIlllllIIIIlll.getFormat();
        int llllllllllllllllIlIIlllllIIIllIl = 0;
        if (llllllllllllllllIlIIlllllIIIlllI.getChannels() == 1) {
            if (llllllllllllllllIlIIlllllIIIlllI.getSampleSizeInBits() == 8) {
                llllllllllllllllIlIIlllllIIIllIl = 4352;
            }
            else {
                if (llllllllllllllllIlIIlllllIIIlllI.getSampleSizeInBits() != 16) {
                    throw new RuntimeException("Illegal sample size");
                }
                llllllllllllllllIlIIlllllIIIllIl = 4353;
            }
        }
        else {
            if (llllllllllllllllIlIIlllllIIIlllI.getChannels() != 2) {
                throw new RuntimeException("Only mono or stereo is supported");
            }
            if (llllllllllllllllIlIIlllllIIIlllI.getSampleSizeInBits() == 8) {
                llllllllllllllllIlIIlllllIIIllIl = 4354;
            }
            else {
                if (llllllllllllllllIlIIlllllIIIlllI.getSampleSizeInBits() != 16) {
                    throw new RuntimeException("Illegal sample size");
                }
                llllllllllllllllIlIIlllllIIIllIl = 4355;
            }
        }
        final byte[] llllllllllllllllIlIIlllllIIIllII = new byte[llllllllllllllllIlIIlllllIIIlllI.getChannels() * (int)llllllllllllllllIlIIlllllIIIIlll.getFrameLength() * llllllllllllllllIlIIlllllIIIlllI.getSampleSizeInBits() / 8];
        int llllllllllllllllIlIIlllllIIIlIll = 0;
        int llllllllllllllllIlIIlllllIIIlIlI = 0;
        try {
            while ((llllllllllllllllIlIIlllllIIIlIll = llllllllllllllllIlIIlllllIIIIlll.read(llllllllllllllllIlIIlllllIIIllII, llllllllllllllllIlIIlllllIIIlIlI, llllllllllllllllIlIIlllllIIIllII.length - llllllllllllllllIlIIlllllIIIlIlI)) != -1 && llllllllllllllllIlIIlllllIIIlIlI < llllllllllllllllIlIIlllllIIIllII.length) {
                llllllllllllllllIlIIlllllIIIlIlI += llllllllllllllllIlIIlllllIIIlIll;
            }
        }
        catch (IOException llllllllllllllllIlIIlllllIIlIIII) {
            return null;
        }
        final ByteBuffer llllllllllllllllIlIIlllllIIIlIIl = convertAudioBytes(llllllllllllllllIlIIlllllIIIlllI, llllllllllllllllIlIIlllllIIIllII, llllllllllllllllIlIIlllllIIIlllI.getSampleSizeInBits() == 16);
        final AiffData llllllllllllllllIlIIlllllIIIlIII = new AiffData(llllllllllllllllIlIIlllllIIIlIIl, llllllllllllllllIlIIlllllIIIllIl, (int)llllllllllllllllIlIIlllllIIIlllI.getSampleRate());
        try {
            llllllllllllllllIlIIlllllIIIIlll.close();
        }
        catch (IOException ex) {}
        return llllllllllllllllIlIIlllllIIIlIII;
    }
    
    public static AiffData create(final String llllllllllllllllIlIIlllllIlIllIl) {
        return create(AiffData.class.getClassLoader().getResource(llllllllllllllllIlIIlllllIlIllIl));
    }
    
    public static AiffData create(final URL llllllllllllllllIlIIlllllIllIIIl) {
        try {
            return create(AudioSystem.getAudioInputStream(new BufferedInputStream(llllllllllllllllIlIIlllllIllIIIl.openStream())));
        }
        catch (Exception llllllllllllllllIlIIlllllIllIIll) {
            LWJGLUtil.log((CharSequence)String.valueOf(new StringBuilder().append("Unable to create from: ").append(llllllllllllllllIlIIlllllIllIIIl)));
            llllllllllllllllIlIIlllllIllIIll.printStackTrace();
            return null;
        }
    }
    
    private static ByteBuffer convertAudioBytes(final AudioFormat llllllllllllllllIlIIllllIllIllll, final byte[] llllllllllllllllIlIIllllIlllIIll, final boolean llllllllllllllllIlIIllllIllIllIl) {
        final ByteBuffer llllllllllllllllIlIIllllIlllIIIl = ByteBuffer.allocateDirect(llllllllllllllllIlIIllllIlllIIll.length);
        llllllllllllllllIlIIllllIlllIIIl.order(ByteOrder.nativeOrder());
        final ByteBuffer llllllllllllllllIlIIllllIlllIIII = ByteBuffer.wrap(llllllllllllllllIlIIllllIlllIIll);
        llllllllllllllllIlIIllllIlllIIII.order(ByteOrder.BIG_ENDIAN);
        if (llllllllllllllllIlIIllllIllIllIl) {
            final ShortBuffer llllllllllllllllIlIIllllIlllIlll = llllllllllllllllIlIIllllIlllIIIl.asShortBuffer();
            final ShortBuffer llllllllllllllllIlIIllllIlllIllI = llllllllllllllllIlIIllllIlllIIII.asShortBuffer();
            while (llllllllllllllllIlIIllllIlllIllI.hasRemaining()) {
                llllllllllllllllIlIIllllIlllIlll.put(llllllllllllllllIlIIllllIlllIllI.get());
            }
        }
        else {
            while (llllllllllllllllIlIIllllIlllIIII.hasRemaining()) {
                byte llllllllllllllllIlIIllllIlllIlIl = llllllllllllllllIlIIllllIlllIIII.get();
                if (llllllllllllllllIlIIllllIllIllll.getEncoding() == AudioFormat.Encoding.PCM_SIGNED) {
                    llllllllllllllllIlIIllllIlllIlIl += 127;
                }
                llllllllllllllllIlIIllllIlllIIIl.put(llllllllllllllllIlIIllllIlllIlIl);
            }
        }
        llllllllllllllllIlIIllllIlllIIIl.rewind();
        return llllllllllllllllIlIIllllIlllIIIl;
    }
    
    public static AiffData create(final ByteBuffer llllllllllllllllIlIIlllllIIlllII) {
        try {
            byte[] llllllllllllllllIlIIlllllIIllllI = null;
            if (llllllllllllllllIlIIlllllIIlllII.hasArray()) {
                llllllllllllllllIlIIlllllIIllllI = llllllllllllllllIlIIlllllIIlllII.array();
            }
            else {
                llllllllllllllllIlIIlllllIIllllI = new byte[llllllllllllllllIlIIlllllIIlllII.capacity()];
                llllllllllllllllIlIIlllllIIlllII.get(llllllllllllllllIlIIlllllIIllllI);
            }
            return create(llllllllllllllllIlIIlllllIIllllI);
        }
        catch (Exception llllllllllllllllIlIIlllllIIlllIl) {
            llllllllllllllllIlIIlllllIIlllIl.printStackTrace();
            return null;
        }
    }
    
    public void dispose() {
        this.data.clear();
    }
    
    private AiffData(final ByteBuffer llllllllllllllllIlIIlllllIlllIll, final int llllllllllllllllIlIIlllllIlllIlI, final int llllllllllllllllIlIIlllllIlllIIl) {
        this.data = llllllllllllllllIlIIlllllIlllIll;
        this.format = llllllllllllllllIlIIlllllIlllIlI;
        this.samplerate = llllllllllllllllIlIIlllllIlllIIl;
    }
}
