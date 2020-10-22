package org.newdawn.slick.opengl;

import org.lwjgl.input.*;
import org.newdawn.slick.util.*;
import org.lwjgl.*;
import java.io.*;
import java.nio.*;

public class CursorLoader
{
    private static /* synthetic */ CursorLoader single;
    
    public Cursor getCursor(final String lllIIIllllIlIIl, final int lllIIIllllIlIII, final int lllIIIllllIIIlI) throws LWJGLException, IOException {
        LoadableImageData lllIIIllllIIllI = null;
        lllIIIllllIIllI = ImageDataFactory.getImageDataFor(lllIIIllllIlIIl);
        lllIIIllllIIllI.configureEdging(false);
        final ByteBuffer lllIIIllllIIlIl = lllIIIllllIIllI.loadImage(ResourceLoader.getResourceAsStream(lllIIIllllIlIIl), true, true, null);
        for (int lllIIIllllIllIl = 0; lllIIIllllIllIl < lllIIIllllIIlIl.limit(); lllIIIllllIllIl += 4) {
            final byte lllIIIlllllIIIl = lllIIIllllIIlIl.get(lllIIIllllIllIl);
            final byte lllIIIlllllIIII = lllIIIllllIIlIl.get(lllIIIllllIllIl + 1);
            final byte lllIIIllllIllll = lllIIIllllIIlIl.get(lllIIIllllIllIl + 2);
            final byte lllIIIllllIlllI = lllIIIllllIIlIl.get(lllIIIllllIllIl + 3);
            lllIIIllllIIlIl.put(lllIIIllllIllIl + 2, lllIIIlllllIIIl);
            lllIIIllllIIlIl.put(lllIIIllllIllIl + 1, lllIIIlllllIIII);
            lllIIIllllIIlIl.put(lllIIIllllIllIl, lllIIIllllIllll);
            lllIIIllllIIlIl.put(lllIIIllllIllIl + 3, lllIIIllllIlllI);
        }
        try {
            int lllIIIllllIllII = lllIIIllllIIllI.getHeight() - lllIIIllllIIIlI - 1;
            if (lllIIIllllIllII < 0) {
                lllIIIllllIllII = 0;
            }
            return new Cursor(lllIIIllllIIllI.getTexWidth(), lllIIIllllIIllI.getTexHeight(), lllIIIllllIlIII, lllIIIllllIllII, 1, lllIIIllllIIlIl.asIntBuffer(), (IntBuffer)null);
        }
        catch (Throwable lllIIIllllIlIll) {
            Log.info("Chances are you cursor is too small for this platform");
            throw new LWJGLException(lllIIIllllIlIll);
        }
    }
    
    public Cursor getCursor(final ImageData lllIIIllIlIIlII, final int lllIIIllIlIIlll, final int lllIIIllIlIIllI) throws LWJGLException, IOException {
        final ByteBuffer lllIIIllIlIIlIl = lllIIIllIlIIlII.getImageBufferData();
        for (int lllIIIllIlIllII = 0; lllIIIllIlIllII < lllIIIllIlIIlIl.limit(); lllIIIllIlIllII += 4) {
            final byte lllIIIllIllIIII = lllIIIllIlIIlIl.get(lllIIIllIlIllII);
            final byte lllIIIllIlIllll = lllIIIllIlIIlIl.get(lllIIIllIlIllII + 1);
            final byte lllIIIllIlIlllI = lllIIIllIlIIlIl.get(lllIIIllIlIllII + 2);
            final byte lllIIIllIlIllIl = lllIIIllIlIIlIl.get(lllIIIllIlIllII + 3);
            lllIIIllIlIIlIl.put(lllIIIllIlIllII + 2, lllIIIllIllIIII);
            lllIIIllIlIIlIl.put(lllIIIllIlIllII + 1, lllIIIllIlIllll);
            lllIIIllIlIIlIl.put(lllIIIllIlIllII, lllIIIllIlIlllI);
            lllIIIllIlIIlIl.put(lllIIIllIlIllII + 3, lllIIIllIlIllIl);
        }
        try {
            int lllIIIllIlIlIll = lllIIIllIlIIlII.getHeight() - lllIIIllIlIIllI - 1;
            if (lllIIIllIlIlIll < 0) {
                lllIIIllIlIlIll = 0;
            }
            return new Cursor(lllIIIllIlIIlII.getTexWidth(), lllIIIllIlIIlII.getTexHeight(), lllIIIllIlIIlll, lllIIIllIlIlIll, 1, lllIIIllIlIIlIl.asIntBuffer(), (IntBuffer)null);
        }
        catch (Throwable lllIIIllIlIlIlI) {
            Log.info("Chances are you cursor is too small for this platform");
            throw new LWJGLException(lllIIIllIlIlIlI);
        }
    }
    
    public Cursor getCursor(final ByteBuffer lllIIIlllIIIIll, final int lllIIIlllIIIlll, final int lllIIIlllIIIIIl, final int lllIIIlllIIIIII, final int lllIIIllIllllll) throws IOException, LWJGLException {
        for (int lllIIIlllIIllII = 0; lllIIIlllIIllII < lllIIIlllIIIIll.limit(); lllIIIlllIIllII += 4) {
            final byte lllIIIlllIlIIII = lllIIIlllIIIIll.get(lllIIIlllIIllII);
            final byte lllIIIlllIIllll = lllIIIlllIIIIll.get(lllIIIlllIIllII + 1);
            final byte lllIIIlllIIlllI = lllIIIlllIIIIll.get(lllIIIlllIIllII + 2);
            final byte lllIIIlllIIllIl = lllIIIlllIIIIll.get(lllIIIlllIIllII + 3);
            lllIIIlllIIIIll.put(lllIIIlllIIllII + 2, lllIIIlllIlIIII);
            lllIIIlllIIIIll.put(lllIIIlllIIllII + 1, lllIIIlllIIllll);
            lllIIIlllIIIIll.put(lllIIIlllIIllII, lllIIIlllIIlllI);
            lllIIIlllIIIIll.put(lllIIIlllIIllII + 3, lllIIIlllIIllIl);
        }
        try {
            int lllIIIlllIIlIll = lllIIIllIllllll - lllIIIlllIIIIIl - 1;
            if (lllIIIlllIIlIll < 0) {
                lllIIIlllIIlIll = 0;
            }
            return new Cursor(lllIIIlllIIIIII, lllIIIllIllllll, lllIIIlllIIIlll, lllIIIlllIIlIll, 1, lllIIIlllIIIIll.asIntBuffer(), (IntBuffer)null);
        }
        catch (Throwable lllIIIlllIIlIlI) {
            Log.info("Chances are you cursor is too small for this platform");
            throw new LWJGLException(lllIIIlllIIlIlI);
        }
    }
    
    public Cursor getAnimatedCursor(final String lllIIIllIIlIIII, final int lllIIIllIIIllll, final int lllIIIllIIIIlIl, final int lllIIIllIIIllIl, final int lllIIIllIIIllII, final int[] lllIIIllIIIIIlI) throws IOException, LWJGLException {
        final IntBuffer lllIIIllIIIlIlI = ByteBuffer.allocateDirect(lllIIIllIIIIIlI.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        for (int lllIIIllIIlIIlI = 0; lllIIIllIIlIIlI < lllIIIllIIIIIlI.length; ++lllIIIllIIlIIlI) {
            lllIIIllIIIlIlI.put(lllIIIllIIIIIlI[lllIIIllIIlIIlI]);
        }
        lllIIIllIIIlIlI.flip();
        final LoadableImageData lllIIIllIIIlIIl = new TGAImageData();
        final ByteBuffer lllIIIllIIIlIII = lllIIIllIIIlIIl.loadImage(ResourceLoader.getResourceAsStream(lllIIIllIIlIIII), false, null);
        return new Cursor(lllIIIllIIIllIl, lllIIIllIIIllII, lllIIIllIIIllll, lllIIIllIIIIlIl, lllIIIllIIIIIlI.length, lllIIIllIIIlIII.asIntBuffer(), lllIIIllIIIlIlI);
    }
    
    static {
        CursorLoader.single = new CursorLoader();
    }
    
    private CursorLoader() {
    }
    
    public static CursorLoader get() {
        return CursorLoader.single;
    }
}
