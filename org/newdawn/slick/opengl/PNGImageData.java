package org.newdawn.slick.opengl;

import java.nio.*;
import java.io.*;
import org.lwjgl.*;

public class PNGImageData implements LoadableImageData
{
    private /* synthetic */ int height;
    private /* synthetic */ ByteBuffer scratch;
    private /* synthetic */ int texHeight;
    private /* synthetic */ int width;
    private /* synthetic */ int texWidth;
    private /* synthetic */ int bitDepth;
    
    @Override
    public int getTexWidth() {
        return this.texWidth;
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llllllllllllllllIlIlIIIIlIIIllII, final boolean llllllllllllllllIlIlIIIIlIIIIlll, final int[] llllllllllllllllIlIlIIIIlIIIIllI) throws IOException {
        return this.loadImage(llllllllllllllllIlIlIIIIlIIIllII, llllllllllllllllIlIlIIIIlIIIIlll, false, llllllllllllllllIlIlIIIIlIIIIllI);
    }
    
    private int toInt(final byte llllllllllllllllIlIlIIIIIlIlIlll) {
        if (llllllllllllllllIlIlIIIIIlIlIlll < 0) {
            return 256 + llllllllllllllllIlIlIIIIIlIlIlll;
        }
        return llllllllllllllllIlIlIIIIIlIlIlll;
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public void configureEdging(final boolean llllllllllllllllIlIlIIIIIlIIlllI) {
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        return this.scratch;
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llllllllllllllllIlIlIIIIlIIlIlII) throws IOException {
        return this.loadImage(llllllllllllllllIlIlIIIIlIIlIlII, false, null);
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llllllllllllllllIlIlIIIIIllIlIll, final boolean llllllllllllllllIlIlIIIIIllIlIlI, boolean llllllllllllllllIlIlIIIIIllIIIll, final int[] llllllllllllllllIlIlIIIIIllIlIII) throws IOException {
        if (llllllllllllllllIlIlIIIIIllIlIII != null) {
            llllllllllllllllIlIlIIIIIllIIIll = true;
            throw new IOException("Transparent color not support in custom PNG Decoder");
        }
        final PNGDecoder llllllllllllllllIlIlIIIIIllIIlll = new PNGDecoder(llllllllllllllllIlIlIIIIIllIlIll);
        if (!llllllllllllllllIlIlIIIIIllIIlll.isRGB()) {
            throw new IOException("Only RGB formatted images are supported by the PNGLoader");
        }
        this.width = llllllllllllllllIlIlIIIIIllIIlll.getWidth();
        this.height = llllllllllllllllIlIlIIIIIllIIlll.getHeight();
        this.texWidth = this.get2Fold(this.width);
        this.texHeight = this.get2Fold(this.height);
        final int llllllllllllllllIlIlIIIIIllIIllI = llllllllllllllllIlIlIIIIIllIIlll.hasAlpha() ? 4 : 3;
        this.bitDepth = (llllllllllllllllIlIlIIIIIllIIlll.hasAlpha() ? 32 : 24);
        this.scratch = BufferUtils.createByteBuffer(this.texWidth * this.texHeight * llllllllllllllllIlIlIIIIIllIIllI);
        llllllllllllllllIlIlIIIIIllIIlll.decode(this.scratch, this.texWidth * llllllllllllllllIlIlIIIIIllIIllI, (llllllllllllllllIlIlIIIIIllIIllI == 4) ? PNGDecoder.RGBA : PNGDecoder.RGB);
        if (this.height < this.texHeight - 1) {
            final int llllllllllllllllIlIlIIIIIllllIII = (this.texHeight - 1) * (this.texWidth * llllllllllllllllIlIlIIIIIllIIllI);
            final int llllllllllllllllIlIlIIIIIlllIlll = (this.height - 1) * (this.texWidth * llllllllllllllllIlIlIIIIIllIIllI);
            for (int llllllllllllllllIlIlIIIIIllllIIl = 0; llllllllllllllllIlIlIIIIIllllIIl < this.texWidth; ++llllllllllllllllIlIlIIIIIllllIIl) {
                for (int llllllllllllllllIlIlIIIIIllllIlI = 0; llllllllllllllllIlIlIIIIIllllIlI < llllllllllllllllIlIlIIIIIllIIllI; ++llllllllllllllllIlIlIIIIIllllIlI) {
                    this.scratch.put(llllllllllllllllIlIlIIIIIllllIII + llllllllllllllllIlIlIIIIIllllIIl + llllllllllllllllIlIlIIIIIllllIlI, this.scratch.get(llllllllllllllllIlIlIIIIIllllIIl + llllllllllllllllIlIlIIIIIllllIlI));
                    this.scratch.put(llllllllllllllllIlIlIIIIIlllIlll + this.texWidth * llllllllllllllllIlIlIIIIIllIIllI + llllllllllllllllIlIlIIIIIllllIIl + llllllllllllllllIlIlIIIIIllllIlI, this.scratch.get(llllllllllllllllIlIlIIIIIlllIlll + llllllllllllllllIlIlIIIIIllllIIl + llllllllllllllllIlIlIIIIIllllIlI));
                }
            }
        }
        if (this.width < this.texWidth - 1) {
            for (int llllllllllllllllIlIlIIIIIlllIlIl = 0; llllllllllllllllIlIlIIIIIlllIlIl < this.texHeight; ++llllllllllllllllIlIlIIIIIlllIlIl) {
                for (int llllllllllllllllIlIlIIIIIlllIllI = 0; llllllllllllllllIlIlIIIIIlllIllI < llllllllllllllllIlIlIIIIIllIIllI; ++llllllllllllllllIlIlIIIIIlllIllI) {
                    this.scratch.put((llllllllllllllllIlIlIIIIIlllIlIl + 1) * (this.texWidth * llllllllllllllllIlIlIIIIIllIIllI) - llllllllllllllllIlIlIIIIIllIIllI + llllllllllllllllIlIlIIIIIlllIllI, this.scratch.get(llllllllllllllllIlIlIIIIIlllIlIl * (this.texWidth * llllllllllllllllIlIlIIIIIllIIllI) + llllllllllllllllIlIlIIIIIlllIllI));
                    this.scratch.put(llllllllllllllllIlIlIIIIIlllIlIl * (this.texWidth * llllllllllllllllIlIlIIIIIllIIllI) + this.width * llllllllllllllllIlIlIIIIIllIIllI + llllllllllllllllIlIlIIIIIlllIllI, this.scratch.get(llllllllllllllllIlIlIIIIIlllIlIl * (this.texWidth * llllllllllllllllIlIlIIIIIllIIllI) + (this.width - 1) * llllllllllllllllIlIlIIIIIllIIllI + llllllllllllllllIlIlIIIIIlllIllI));
                }
            }
        }
        if (!llllllllllllllllIlIlIIIIIllIIlll.hasAlpha() && llllllllllllllllIlIlIIIIIllIIIll) {
            final ByteBuffer llllllllllllllllIlIlIIIIIlllIIII = BufferUtils.createByteBuffer(this.texWidth * this.texHeight * 4);
            for (int llllllllllllllllIlIlIIIIIlllIIIl = 0; llllllllllllllllIlIlIIIIIlllIIIl < this.texWidth; ++llllllllllllllllIlIlIIIIIlllIIIl) {
                for (int llllllllllllllllIlIlIIIIIlllIIlI = 0; llllllllllllllllIlIlIIIIIlllIIlI < this.texHeight; ++llllllllllllllllIlIlIIIIIlllIIlI) {
                    final int llllllllllllllllIlIlIIIIIlllIlII = llllllllllllllllIlIlIIIIIlllIIlI * 3 + llllllllllllllllIlIlIIIIIlllIIIl * this.texHeight * 3;
                    final int llllllllllllllllIlIlIIIIIlllIIll = llllllllllllllllIlIlIIIIIlllIIlI * 4 + llllllllllllllllIlIlIIIIIlllIIIl * this.texHeight * 4;
                    llllllllllllllllIlIlIIIIIlllIIII.put(llllllllllllllllIlIlIIIIIlllIIll, this.scratch.get(llllllllllllllllIlIlIIIIIlllIlII));
                    llllllllllllllllIlIlIIIIIlllIIII.put(llllllllllllllllIlIlIIIIIlllIIll + 1, this.scratch.get(llllllllllllllllIlIlIIIIIlllIlII + 1));
                    llllllllllllllllIlIlIIIIIlllIIII.put(llllllllllllllllIlIlIIIIIlllIIll + 2, this.scratch.get(llllllllllllllllIlIlIIIIIlllIlII + 2));
                    if (llllllllllllllllIlIlIIIIIlllIIIl < this.getHeight() && llllllllllllllllIlIlIIIIIlllIIlI < this.getWidth()) {
                        llllllllllllllllIlIlIIIIIlllIIII.put(llllllllllllllllIlIlIIIIIlllIIll + 3, (byte)(-1));
                    }
                    else {
                        llllllllllllllllIlIlIIIIIlllIIII.put(llllllllllllllllIlIlIIIIIlllIIll + 3, (byte)0);
                    }
                }
            }
            this.bitDepth = 32;
            this.scratch = llllllllllllllllIlIlIIIIIlllIIII;
        }
        if (llllllllllllllllIlIlIIIIIllIlIII != null) {
            for (int llllllllllllllllIlIlIIIIIllIllIl = 0; llllllllllllllllIlIlIIIIIllIllIl < this.texWidth * this.texHeight * 4; llllllllllllllllIlIlIIIIIllIllIl += 4) {
                boolean llllllllllllllllIlIlIIIIIllIlllI = true;
                for (int llllllllllllllllIlIlIIIIIllIllll = 0; llllllllllllllllIlIlIIIIIllIllll < 3; ++llllllllllllllllIlIlIIIIIllIllll) {
                    if (this.toInt(this.scratch.get(llllllllllllllllIlIlIIIIIllIllIl + llllllllllllllllIlIlIIIIIllIllll)) != llllllllllllllllIlIlIIIIIllIlIII[llllllllllllllllIlIlIIIIIllIllll]) {
                        llllllllllllllllIlIlIIIIIllIlllI = false;
                    }
                }
                if (llllllllllllllllIlIlIIIIIllIlllI) {
                    this.scratch.put(llllllllllllllllIlIlIIIIIllIllIl + 3, (byte)0);
                }
            }
        }
        this.scratch.position(0);
        return this.scratch;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public int getTexHeight() {
        return this.texHeight;
    }
    
    @Override
    public int getDepth() {
        return this.bitDepth;
    }
    
    private int get2Fold(final int llllllllllllllllIlIlIIIIIlIlIIIl) {
        int llllllllllllllllIlIlIIIIIlIlIIlI;
        for (llllllllllllllllIlIlIIIIIlIlIIlI = 2; llllllllllllllllIlIlIIIIIlIlIIlI < llllllllllllllllIlIlIIIIIlIlIIIl; llllllllllllllllIlIlIIIIIlIlIIlI *= 2) {}
        return llllllllllllllllIlIlIIIIIlIlIIlI;
    }
}
