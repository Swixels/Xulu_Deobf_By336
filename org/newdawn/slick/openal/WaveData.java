package org.newdawn.slick.openal;

import java.net.*;
import javax.sound.sampled.*;
import org.lwjgl.*;
import java.io.*;
import java.nio.*;

public class WaveData
{
    public final /* synthetic */ int format;
    public final /* synthetic */ int samplerate;
    public final /* synthetic */ ByteBuffer data;
    
    public static WaveData create(final AudioInputStream lIIlIllIlIIlIII) {
        final AudioFormat lIIlIllIlIIllll = lIIlIllIlIIlIII.getFormat();
        int lIIlIllIlIIlllI = 0;
        if (lIIlIllIlIIllll.getChannels() == 1) {
            if (lIIlIllIlIIllll.getSampleSizeInBits() == 8) {
                lIIlIllIlIIlllI = 4352;
            }
            else {
                if (lIIlIllIlIIllll.getSampleSizeInBits() != 16) {
                    throw new RuntimeException("Illegal sample size");
                }
                lIIlIllIlIIlllI = 4353;
            }
        }
        else {
            if (lIIlIllIlIIllll.getChannels() != 2) {
                throw new RuntimeException("Only mono or stereo is supported");
            }
            if (lIIlIllIlIIllll.getSampleSizeInBits() == 8) {
                lIIlIllIlIIlllI = 4354;
            }
            else {
                if (lIIlIllIlIIllll.getSampleSizeInBits() != 16) {
                    throw new RuntimeException("Illegal sample size");
                }
                lIIlIllIlIIlllI = 4355;
            }
        }
        final byte[] lIIlIllIlIIllIl = new byte[lIIlIllIlIIllll.getChannels() * (int)lIIlIllIlIIlIII.getFrameLength() * lIIlIllIlIIllll.getSampleSizeInBits() / 8];
        int lIIlIllIlIIllII = 0;
        int lIIlIllIlIIlIll = 0;
        try {
            while ((lIIlIllIlIIllII = lIIlIllIlIIlIII.read(lIIlIllIlIIllIl, lIIlIllIlIIlIll, lIIlIllIlIIllIl.length - lIIlIllIlIIlIll)) != -1 && lIIlIllIlIIlIll < lIIlIllIlIIllIl.length) {
                lIIlIllIlIIlIll += lIIlIllIlIIllII;
            }
        }
        catch (IOException lIIlIllIlIlIIIl) {
            return null;
        }
        final ByteBuffer lIIlIllIlIIlIlI = convertAudioBytes(lIIlIllIlIIllIl, lIIlIllIlIIllll.getSampleSizeInBits() == 16);
        final WaveData lIIlIllIlIIlIIl = new WaveData(lIIlIllIlIIlIlI, lIIlIllIlIIlllI, (int)lIIlIllIlIIllll.getSampleRate());
        try {
            lIIlIllIlIIlIII.close();
        }
        catch (IOException ex) {}
        return lIIlIllIlIIlIIl;
    }
    
    public static WaveData create(final String lIIlIllIllIllll) {
        return create(WaveData.class.getClassLoader().getResource(lIIlIllIllIllll));
    }
    
    public static WaveData create(final ByteBuffer lIIlIllIlIlllII) {
        try {
            byte[] lIIlIllIlIlllll = null;
            if (lIIlIllIlIlllII.hasArray()) {
                lIIlIllIlIlllll = lIIlIllIlIlllII.array();
            }
            else {
                lIIlIllIlIlllll = new byte[lIIlIllIlIlllII.capacity()];
                lIIlIllIlIlllII.get(lIIlIllIlIlllll);
            }
            return create(lIIlIllIlIlllll);
        }
        catch (Exception lIIlIllIlIllllI) {
            lIIlIllIlIllllI.printStackTrace();
            return null;
        }
    }
    
    public void dispose() {
        this.data.clear();
    }
    
    private WaveData(final ByteBuffer lIIlIlllIIIIIII, final int lIIlIllIllllIll, final int lIIlIllIllllIlI) {
        this.data = lIIlIlllIIIIIII;
        this.format = lIIlIllIllllIll;
        this.samplerate = lIIlIllIllllIlI;
    }
    
    public static WaveData create(final URL lIIlIllIlllIIll) {
        try {
            return create(AudioSystem.getAudioInputStream(new BufferedInputStream(lIIlIllIlllIIll.openStream())));
        }
        catch (Exception lIIlIllIlllIlII) {
            LWJGLUtil.log((CharSequence)String.valueOf(new StringBuilder().append("Unable to create from: ").append(lIIlIllIlllIIll)));
            lIIlIllIlllIlII.printStackTrace();
            return null;
        }
    }
    
    public static WaveData create(final byte[] lIIlIllIllIIlII) {
        try {
            return create(AudioSystem.getAudioInputStream(new BufferedInputStream(new ByteArrayInputStream(lIIlIllIllIIlII))));
        }
        catch (Exception lIIlIllIllIIlIl) {
            lIIlIllIllIIlIl.printStackTrace();
            return null;
        }
    }
    
    public static WaveData create(final InputStream lIIlIllIllIlIlI) {
        try {
            return create(AudioSystem.getAudioInputStream(lIIlIllIllIlIlI));
        }
        catch (Exception lIIlIllIllIlIll) {
            LWJGLUtil.log((CharSequence)"Unable to create from inputstream");
            lIIlIllIllIlIll.printStackTrace();
            return null;
        }
    }
    
    private static ByteBuffer convertAudioBytes(final byte[] lIIlIllIIllIlll, final boolean lIIlIllIIllIIlI) {
        final ByteBuffer lIIlIllIIllIlIl = ByteBuffer.allocateDirect(lIIlIllIIllIlll.length);
        lIIlIllIIllIlIl.order(ByteOrder.nativeOrder());
        final ByteBuffer lIIlIllIIllIlII = ByteBuffer.wrap(lIIlIllIIllIlll);
        lIIlIllIIllIlII.order(ByteOrder.LITTLE_ENDIAN);
        if (lIIlIllIIllIIlI) {
            final ShortBuffer lIIlIllIIlllIIl = lIIlIllIIllIlIl.asShortBuffer();
            final ShortBuffer lIIlIllIIlllIII = lIIlIllIIllIlII.asShortBuffer();
            while (lIIlIllIIlllIII.hasRemaining()) {
                lIIlIllIIlllIIl.put(lIIlIllIIlllIII.get());
            }
        }
        else {
            while (lIIlIllIIllIlII.hasRemaining()) {
                lIIlIllIIllIlIl.put(lIIlIllIIllIlII.get());
            }
        }
        lIIlIllIIllIlIl.rewind();
        return lIIlIllIIllIlIl;
    }
}
