package org.newdawn.slick.opengl;

import org.newdawn.slick.opengl.renderer.*;
import java.nio.*;
import org.newdawn.slick.util.*;
import org.lwjgl.*;
import java.util.*;
import java.io.*;
import java.lang.ref.*;

public class InternalTextureLoader
{
    private /* synthetic */ boolean deferred;
    private /* synthetic */ boolean holdTextureData;
    private /* synthetic */ HashMap texturesNearest;
    private /* synthetic */ HashMap texturesLinear;
    private static final /* synthetic */ InternalTextureLoader loader;
    private /* synthetic */ int dstPixelFormat;
    protected static /* synthetic */ SGL GL;
    
    public static int createTextureID() {
        final IntBuffer llllllllllllllllllllIlIllIIIlIII = createIntBuffer(1);
        InternalTextureLoader.GL.glGenTextures(llllllllllllllllllllIlIllIIIlIII);
        return llllllllllllllllllllIlIllIIIlIII.get(0);
    }
    
    public void setHoldTextureData(final boolean llllllllllllllllllllIlIllIlIIIIl) {
        this.holdTextureData = llllllllllllllllllllIlIllIlIIIIl;
    }
    
    public void set16BitMode() {
        this.dstPixelFormat = 32859;
    }
    
    static {
        InternalTextureLoader.GL = Renderer.get();
        loader = new InternalTextureLoader();
    }
    
    public static IntBuffer createIntBuffer(final int llllllllllllllllllllIlIIIllIlIlI) {
        final ByteBuffer llllllllllllllllllllIlIIIllIlIIl = ByteBuffer.allocateDirect(4 * llllllllllllllllllllIlIIIllIlIlI);
        llllllllllllllllllllIlIIIllIlIIl.order(ByteOrder.nativeOrder());
        return llllllllllllllllllllIlIIIllIlIIl.asIntBuffer();
    }
    
    public Texture getTexture(final String llllllllllllllllllllIlIlIlIIlIIl, final boolean llllllllllllllllllllIlIlIlIIlIII, final int llllllllllllllllllllIlIlIlIIIlll, final int[] llllllllllllllllllllIlIlIlIIIllI) throws IOException {
        final InputStream llllllllllllllllllllIlIlIlIIIlIl = ResourceLoader.getResourceAsStream(llllllllllllllllllllIlIlIlIIlIIl);
        return this.getTexture(llllllllllllllllllllIlIlIlIIIlIl, llllllllllllllllllllIlIlIlIIlIIl, llllllllllllllllllllIlIlIlIIlIII, llllllllllllllllllllIlIlIlIIIlll, llllllllllllllllllllIlIlIlIIIllI);
    }
    
    public static int get2Fold(final int llllllllllllllllllllIlIIIlllIIII) {
        int llllllllllllllllllllIlIIIllIllll;
        for (llllllllllllllllllllIlIIIllIllll = 2; llllllllllllllllllllIlIIIllIllll < llllllllllllllllllllIlIIIlllIIII; llllllllllllllllllllIlIIIllIllll *= 2) {}
        return llllllllllllllllllllIlIIIllIllll;
    }
    
    public static InternalTextureLoader get() {
        return InternalTextureLoader.loader;
    }
    
    public void clear(final String llllllllllllllllllllIlIllIIlIIlI) {
        this.texturesLinear.remove(llllllllllllllllllllIlIllIIlIIlI);
        this.texturesNearest.remove(llllllllllllllllllllIlIllIIlIIlI);
    }
    
    public Texture getTexture(final ImageData llllllllllllllllllllIlIIlIIlIIII, final int llllllllllllllllllllIlIIlIIIllll) throws IOException {
        final int llllllllllllllllllllIlIIlIlIIIIl = 3553;
        final ByteBuffer llllllllllllllllllllIlIIlIlIIIII = llllllllllllllllllllIlIIlIIlIIII.getImageBufferData();
        final int llllllllllllllllllllIlIIlIIlllll = createTextureID();
        final TextureImpl llllllllllllllllllllIlIIlIIllllI = new TextureImpl(String.valueOf(new StringBuilder().append("generated:").append(llllllllllllllllllllIlIIlIIlIIII)), llllllllllllllllllllIlIIlIlIIIIl, llllllllllllllllllllIlIIlIIlllll);
        final int llllllllllllllllllllIlIIlIIlllIl = llllllllllllllllllllIlIIlIIIllll;
        final int llllllllllllllllllllIlIIlIIlllII = llllllllllllllllllllIlIIlIIIllll;
        final boolean llllllllllllllllllllIlIIlIIllIll = false;
        InternalTextureLoader.GL.glBindTexture(llllllllllllllllllllIlIIlIlIIIIl, llllllllllllllllllllIlIIlIIlllll);
        final int llllllllllllllllllllIlIIlIIllIlI = llllllllllllllllllllIlIIlIIlIIII.getWidth();
        final int llllllllllllllllllllIlIIlIIllIIl = llllllllllllllllllllIlIIlIIlIIII.getHeight();
        final boolean llllllllllllllllllllIlIIlIIlIllI = llllllllllllllllllllIlIIlIIlIIII.getDepth() == 32;
        llllllllllllllllllllIlIIlIIllllI.setTextureWidth(llllllllllllllllllllIlIIlIIlIIII.getTexWidth());
        llllllllllllllllllllIlIIlIIllllI.setTextureHeight(llllllllllllllllllllIlIIlIIlIIII.getTexHeight());
        final int llllllllllllllllllllIlIIlIIllIII = llllllllllllllllllllIlIIlIIllllI.getTextureWidth();
        final int llllllllllllllllllllIlIIlIIlIlll = llllllllllllllllllllIlIIlIIllllI.getTextureHeight();
        final int llllllllllllllllllllIlIIlIIlIlIl = llllllllllllllllllllIlIIlIIlIllI ? 6408 : 6407;
        final int llllllllllllllllllllIlIIlIIlIlII = llllllllllllllllllllIlIIlIIlIllI ? 4 : 3;
        llllllllllllllllllllIlIIlIIllllI.setWidth(llllllllllllllllllllIlIIlIIllIlI);
        llllllllllllllllllllIlIIlIIllllI.setHeight(llllllllllllllllllllIlIIlIIllIIl);
        llllllllllllllllllllIlIIlIIllllI.setAlpha(llllllllllllllllllllIlIIlIIlIllI);
        final IntBuffer llllllllllllllllllllIlIIlIIlIIll = BufferUtils.createIntBuffer(16);
        InternalTextureLoader.GL.glGetInteger(3379, llllllllllllllllllllIlIIlIIlIIll);
        final int llllllllllllllllllllIlIIlIIlIIlI = llllllllllllllllllllIlIIlIIlIIll.get(0);
        if (llllllllllllllllllllIlIIlIIllIII > llllllllllllllllllllIlIIlIIlIIlI || llllllllllllllllllllIlIIlIIlIlll > llllllllllllllllllllIlIIlIIlIIlI) {
            throw new IOException("Attempt to allocate a texture to big for the current hardware");
        }
        if (this.holdTextureData) {
            llllllllllllllllllllIlIIlIIllllI.setTextureData(llllllllllllllllllllIlIIlIIlIlIl, llllllllllllllllllllIlIIlIIlIlII, llllllllllllllllllllIlIIlIIlllIl, llllllllllllllllllllIlIIlIIlllII, llllllllllllllllllllIlIIlIlIIIII);
        }
        InternalTextureLoader.GL.glTexParameteri(llllllllllllllllllllIlIIlIlIIIIl, 10241, llllllllllllllllllllIlIIlIIlllIl);
        InternalTextureLoader.GL.glTexParameteri(llllllllllllllllllllIlIIlIlIIIIl, 10240, llllllllllllllllllllIlIIlIIlllII);
        InternalTextureLoader.GL.glTexImage2D(llllllllllllllllllllIlIIlIlIIIIl, 0, this.dstPixelFormat, get2Fold(llllllllllllllllllllIlIIlIIllIlI), get2Fold(llllllllllllllllllllIlIIlIIllIIl), 0, llllllllllllllllllllIlIIlIIlIlIl, 5121, llllllllllllllllllllIlIIlIlIIIII);
        return llllllllllllllllllllIlIIlIIllllI;
    }
    
    public Texture getTexture(final InputStream llllllllllllllllllllIlIlIIllIIll, final String llllllllllllllllllllIlIlIIllIIlI, final boolean llllllllllllllllllllIlIlIIllIIIl, final int llllllllllllllllllllIlIlIIllIlIl) throws IOException {
        return this.getTexture(llllllllllllllllllllIlIlIIllIIll, llllllllllllllllllllIlIlIIllIIlI, llllllllllllllllllllIlIlIIllIIIl, llllllllllllllllllllIlIlIIllIlIl, null);
    }
    
    public Texture createTexture(final int llllllllllllllllllllIlIIllIIlIll, final int llllllllllllllllllllIlIIllIIlIlI) throws IOException {
        return this.createTexture(llllllllllllllllllllIlIIllIIlIll, llllllllllllllllllllIlIIllIIlIlI, 9728);
    }
    
    public Texture getTexture(final File llllllllllllllllllllIlIlIllllIIl, final boolean llllllllllllllllllllIlIlIllllllI, final int llllllllllllllllllllIlIlIlllllIl) throws IOException {
        final String llllllllllllllllllllIlIlIlllllII = llllllllllllllllllllIlIlIllllIIl.getAbsolutePath();
        final InputStream llllllllllllllllllllIlIlIllllIll = new FileInputStream(llllllllllllllllllllIlIlIllllIIl);
        return this.getTexture(llllllllllllllllllllIlIlIllllIll, llllllllllllllllllllIlIlIlllllII, llllllllllllllllllllIlIlIllllllI, llllllllllllllllllllIlIlIlllllIl, null);
    }
    
    private InternalTextureLoader() {
        this.texturesLinear = new HashMap();
        this.texturesNearest = new HashMap();
        this.dstPixelFormat = 6408;
    }
    
    public void reload() {
        Iterator llllllllllllllllllllIlIIIllIIIll = this.texturesLinear.values().iterator();
        while (llllllllllllllllllllIlIIIllIIIll.hasNext()) {
            llllllllllllllllllllIlIIIllIIIll.next().reload();
        }
        llllllllllllllllllllIlIIIllIIIll = this.texturesNearest.values().iterator();
        while (llllllllllllllllllllIlIIIllIIIll.hasNext()) {
            llllllllllllllllllllIlIIIllIIIll.next().reload();
        }
    }
    
    private TextureImpl getTexture(final InputStream llllllllllllllllllllIlIIlllllIII, final String llllllllllllllllllllIlIIlllIIIlI, final int llllllllllllllllllllIlIIlllIIIIl, final int llllllllllllllllllllIlIIlllIIIII, final int llllllllllllllllllllIlIIllIlllll, final boolean llllllllllllllllllllIlIIllIllllI, final int[] llllllllllllllllllllIlIIllllIIlI) throws IOException {
        final LoadableImageData llllllllllllllllllllIlIIllllIIII = ImageDataFactory.getImageDataFor(llllllllllllllllllllIlIIlllIIIlI);
        final ByteBuffer llllllllllllllllllllIlIIllllIIIl = llllllllllllllllllllIlIIllllIIII.loadImage(new BufferedInputStream(llllllllllllllllllllIlIIlllllIII), llllllllllllllllllllIlIIllIllllI, llllllllllllllllllllIlIIllllIIlI);
        final int llllllllllllllllllllIlIIlllIllll = createTextureID();
        final TextureImpl llllllllllllllllllllIlIIlllIlllI = new TextureImpl(llllllllllllllllllllIlIIlllIIIlI, llllllllllllllllllllIlIIlllIIIIl, llllllllllllllllllllIlIIlllIllll);
        InternalTextureLoader.GL.glBindTexture(llllllllllllllllllllIlIIlllIIIIl, llllllllllllllllllllIlIIlllIllll);
        final int llllllllllllllllllllIlIIlllIllIl = llllllllllllllllllllIlIIllllIIII.getWidth();
        final int llllllllllllllllllllIlIIlllIllII = llllllllllllllllllllIlIIllllIIII.getHeight();
        final boolean llllllllllllllllllllIlIIlllIlIIl = llllllllllllllllllllIlIIllllIIII.getDepth() == 32;
        llllllllllllllllllllIlIIlllIlllI.setTextureWidth(llllllllllllllllllllIlIIllllIIII.getTexWidth());
        llllllllllllllllllllIlIIlllIlllI.setTextureHeight(llllllllllllllllllllIlIIllllIIII.getTexHeight());
        final int llllllllllllllllllllIlIIlllIlIll = llllllllllllllllllllIlIIlllIlllI.getTextureWidth();
        final int llllllllllllllllllllIlIIlllIlIlI = llllllllllllllllllllIlIIlllIlllI.getTextureHeight();
        final IntBuffer llllllllllllllllllllIlIIlllIlIII = BufferUtils.createIntBuffer(16);
        InternalTextureLoader.GL.glGetInteger(3379, llllllllllllllllllllIlIIlllIlIII);
        final int llllllllllllllllllllIlIIlllIIlll = llllllllllllllllllllIlIIlllIlIII.get(0);
        if (llllllllllllllllllllIlIIlllIlIll > llllllllllllllllllllIlIIlllIIlll || llllllllllllllllllllIlIIlllIlIlI > llllllllllllllllllllIlIIlllIIlll) {
            throw new IOException("Attempt to allocate a texture to big for the current hardware");
        }
        final int llllllllllllllllllllIlIIlllIIllI = llllllllllllllllllllIlIIlllIlIIl ? 6408 : 6407;
        final int llllllllllllllllllllIlIIlllIIlIl = llllllllllllllllllllIlIIlllIlIIl ? 4 : 3;
        llllllllllllllllllllIlIIlllIlllI.setWidth(llllllllllllllllllllIlIIlllIllIl);
        llllllllllllllllllllIlIIlllIlllI.setHeight(llllllllllllllllllllIlIIlllIllII);
        llllllllllllllllllllIlIIlllIlllI.setAlpha(llllllllllllllllllllIlIIlllIlIIl);
        if (this.holdTextureData) {
            llllllllllllllllllllIlIIlllIlllI.setTextureData(llllllllllllllllllllIlIIlllIIllI, llllllllllllllllllllIlIIlllIIlIl, llllllllllllllllllllIlIIllIlllll, llllllllllllllllllllIlIIlllIIIII, llllllllllllllllllllIlIIllllIIIl);
        }
        final SGL gl = InternalTextureLoader.GL;
        final SGL gl2 = InternalTextureLoader.GL;
        gl.glTexParameteri(llllllllllllllllllllIlIIlllIIIIl, 10241, llllllllllllllllllllIlIIllIlllll);
        final SGL gl3 = InternalTextureLoader.GL;
        final SGL gl4 = InternalTextureLoader.GL;
        gl3.glTexParameteri(llllllllllllllllllllIlIIlllIIIIl, 10240, llllllllllllllllllllIlIIlllIIIII);
        InternalTextureLoader.GL.glTexImage2D(llllllllllllllllllllIlIIlllIIIIl, 0, this.dstPixelFormat, get2Fold(llllllllllllllllllllIlIIlllIllIl), get2Fold(llllllllllllllllllllIlIIlllIllII), 0, llllllllllllllllllllIlIIlllIIllI, 5121, llllllllllllllllllllIlIIllllIIIl);
        return llllllllllllllllllllIlIIlllIlllI;
    }
    
    public void clear() {
        this.texturesLinear.clear();
        this.texturesNearest.clear();
    }
    
    public Texture getTexture(final File llllllllllllllllllllIlIlIllIllII, final boolean llllllllllllllllllllIlIlIllIlIll, final int llllllllllllllllllllIlIlIllIIIll, final int[] llllllllllllllllllllIlIlIllIlIIl) throws IOException {
        final String llllllllllllllllllllIlIlIllIlIII = llllllllllllllllllllIlIlIllIllII.getAbsolutePath();
        final InputStream llllllllllllllllllllIlIlIllIIlll = new FileInputStream(llllllllllllllllllllIlIlIllIllII);
        return this.getTexture(llllllllllllllllllllIlIlIllIIlll, llllllllllllllllllllIlIlIllIlIII, llllllllllllllllllllIlIlIllIlIll, llllllllllllllllllllIlIlIllIIIll, llllllllllllllllllllIlIlIllIlIIl);
    }
    
    public Texture getTexture(final String llllllllllllllllllllIlIlIlIllIIl, final boolean llllllllllllllllllllIlIlIlIlIIll, final int llllllllllllllllllllIlIlIlIlIlll) throws IOException {
        final InputStream llllllllllllllllllllIlIlIlIlIllI = ResourceLoader.getResourceAsStream(llllllllllllllllllllIlIlIlIllIIl);
        return this.getTexture(llllllllllllllllllllIlIlIlIlIllI, llllllllllllllllllllIlIlIlIllIIl, llllllllllllllllllllIlIlIlIlIIll, llllllllllllllllllllIlIlIlIlIlll, null);
    }
    
    public Texture createTexture(final int llllllllllllllllllllIlIIlIlllIll, final int llllllllllllllllllllIlIIlIllllll, final int llllllllllllllllllllIlIIlIlllIIl) throws IOException {
        final ImageData llllllllllllllllllllIlIIlIllllIl = new EmptyImageData(llllllllllllllllllllIlIIlIlllIll, llllllllllllllllllllIlIIlIllllll);
        return this.getTexture(llllllllllllllllllllIlIIlIllllIl, llllllllllllllllllllIlIIlIlllIIl);
    }
    
    public int reload(final TextureImpl llllllllllllllllllllIlIIIlIlIlll, final int llllllllllllllllllllIlIIIlIlIllI, final int llllllllllllllllllllIlIIIlIlIlIl, final int llllllllllllllllllllIlIIIlIlIlII, final int llllllllllllllllllllIlIIIlIlIIll, final ByteBuffer llllllllllllllllllllIlIIIlIlIIlI) {
        final int llllllllllllllllllllIlIIIlIlIIIl = 3553;
        final int llllllllllllllllllllIlIIIlIlIIII = createTextureID();
        InternalTextureLoader.GL.glBindTexture(llllllllllllllllllllIlIIIlIlIIIl, llllllllllllllllllllIlIIIlIlIIII);
        InternalTextureLoader.GL.glTexParameteri(llllllllllllllllllllIlIIIlIlIIIl, 10241, llllllllllllllllllllIlIIIlIlIlII);
        InternalTextureLoader.GL.glTexParameteri(llllllllllllllllllllIlIIIlIlIIIl, 10240, llllllllllllllllllllIlIIIlIlIIll);
        InternalTextureLoader.GL.glTexImage2D(llllllllllllllllllllIlIIIlIlIIIl, 0, this.dstPixelFormat, llllllllllllllllllllIlIIIlIlIlll.getTextureWidth(), llllllllllllllllllllIlIIIlIlIlll.getTextureHeight(), 0, llllllllllllllllllllIlIIIlIlIllI, 5121, llllllllllllllllllllIlIIIlIlIIlI);
        return llllllllllllllllllllIlIIIlIlIIII;
    }
    
    public boolean isDeferredLoading() {
        return this.deferred;
    }
    
    public void setDeferredLoading(final boolean llllllllllllllllllllIlIllIIllIIl) {
        this.deferred = llllllllllllllllllllIlIllIIllIIl;
    }
    
    public TextureImpl getTexture(final InputStream llllllllllllllllllllIlIlIIlIIIII, final String llllllllllllllllllllIlIlIIIlIllI, final boolean llllllllllllllllllllIlIlIIIllllI, final int llllllllllllllllllllIlIlIIIlIlII, final int[] llllllllllllllllllllIlIlIIIlllII) throws IOException {
        if (this.deferred) {
            return new DeferredTexture(llllllllllllllllllllIlIlIIlIIIII, llllllllllllllllllllIlIlIIIlIllI, llllllllllllllllllllIlIlIIIllllI, llllllllllllllllllllIlIlIIIlIlII, llllllllllllllllllllIlIlIIIlllII);
        }
        HashMap llllllllllllllllllllIlIlIIIllIll = this.texturesLinear;
        if (llllllllllllllllllllIlIlIIIlIlII == 9728) {
            llllllllllllllllllllIlIlIIIllIll = this.texturesNearest;
        }
        String llllllllllllllllllllIlIlIIIllIlI = llllllllllllllllllllIlIlIIIlIllI;
        if (llllllllllllllllllllIlIlIIIlllII != null) {
            llllllllllllllllllllIlIlIIIllIlI = String.valueOf(new StringBuilder().append(llllllllllllllllllllIlIlIIIllIlI).append(":").append(llllllllllllllllllllIlIlIIIlllII[0]).append(":").append(llllllllllllllllllllIlIlIIIlllII[1]).append(":").append(llllllllllllllllllllIlIlIIIlllII[2]));
        }
        llllllllllllllllllllIlIlIIIllIlI = String.valueOf(new StringBuilder().append(llllllllllllllllllllIlIlIIIllIlI).append(":").append(llllllllllllllllllllIlIlIIIllllI));
        if (this.holdTextureData) {
            final TextureImpl llllllllllllllllllllIlIlIIlIIlIl = llllllllllllllllllllIlIlIIIllIll.get(llllllllllllllllllllIlIlIIIllIlI);
            if (llllllllllllllllllllIlIlIIlIIlIl != null) {
                return llllllllllllllllllllIlIlIIlIIlIl;
            }
        }
        else {
            final SoftReference llllllllllllllllllllIlIlIIlIIIll = llllllllllllllllllllIlIlIIIllIll.get(llllllllllllllllllllIlIlIIIllIlI);
            if (llllllllllllllllllllIlIlIIlIIIll != null) {
                final TextureImpl llllllllllllllllllllIlIlIIlIIlII = llllllllllllllllllllIlIlIIlIIIll.get();
                if (llllllllllllllllllllIlIlIIlIIlII != null) {
                    return llllllllllllllllllllIlIlIIlIIlII;
                }
                llllllllllllllllllllIlIlIIIllIll.remove(llllllllllllllllllllIlIlIIIllIlI);
            }
        }
        try {
            InternalTextureLoader.GL.glGetError();
        }
        catch (NullPointerException llllllllllllllllllllIlIlIIlIIIlI) {
            throw new RuntimeException("Image based resources must be loaded as part of init() or the game loop. They cannot be loaded before initialisation.");
        }
        final TextureImpl llllllllllllllllllllIlIlIIIllIIl = this.getTexture(llllllllllllllllllllIlIlIIlIIIII, llllllllllllllllllllIlIlIIIlIllI, 3553, llllllllllllllllllllIlIlIIIlIlII, llllllllllllllllllllIlIlIIIlIlII, llllllllllllllllllllIlIlIIIllllI, llllllllllllllllllllIlIlIIIlllII);
        llllllllllllllllllllIlIlIIIllIIl.setCacheName(llllllllllllllllllllIlIlIIIllIlI);
        if (this.holdTextureData) {
            llllllllllllllllllllIlIlIIIllIll.put(llllllllllllllllllllIlIlIIIllIlI, llllllllllllllllllllIlIlIIIllIIl);
        }
        else {
            llllllllllllllllllllIlIlIIIllIll.put(llllllllllllllllllllIlIlIIIllIlI, new SoftReference<TextureImpl>(llllllllllllllllllllIlIlIIIllIIl));
        }
        return llllllllllllllllllllIlIlIIIllIIl;
    }
}
