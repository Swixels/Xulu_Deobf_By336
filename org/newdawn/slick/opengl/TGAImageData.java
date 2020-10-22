package org.newdawn.slick.opengl;

import java.io.*;
import java.nio.*;
import org.lwjgl.*;

public class TGAImageData implements LoadableImageData
{
    private /* synthetic */ int texHeight;
    private /* synthetic */ int width;
    private /* synthetic */ short pixelDepth;
    private /* synthetic */ int texWidth;
    private /* synthetic */ int height;
    
    private int get2Fold(final int llllllllllllllllIlIlllllIlIlIlll) {
        int llllllllllllllllIlIlllllIlIllIII;
        for (llllllllllllllllIlIlllllIlIllIII = 2; llllllllllllllllIlIlllllIlIllIII < llllllllllllllllIlIlllllIlIlIlll; llllllllllllllllIlIlllllIlIllIII *= 2) {}
        return llllllllllllllllIlIlllllIlIllIII;
    }
    
    @Override
    public void configureEdging(final boolean llllllllllllllllIlIlllllIlIlIIll) {
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llllllllllllllllIlIlllllIlllIlIl, boolean llllllllllllllllIlIlllllIlllIlII, boolean llllllllllllllllIlIlllllIlllIIll, final int[] llllllllllllllllIlIllllllIIIlIIl) throws IOException {
        if (llllllllllllllllIlIllllllIIIlIIl != null) {
            llllllllllllllllIlIlllllIlllIIll = 1;
        }
        byte llllllllllllllllIlIllllllIIIlIII = 0;
        byte llllllllllllllllIlIllllllIIIIlll = 0;
        byte llllllllllllllllIlIllllllIIIIllI = 0;
        byte llllllllllllllllIlIllllllIIIIlIl = 0;
        final BufferedInputStream llllllllllllllllIlIllllllIIIIlII = new BufferedInputStream(llllllllllllllllIlIlllllIlllIlIl, 100000);
        final DataInputStream llllllllllllllllIlIllllllIIIIIll = new DataInputStream(llllllllllllllllIlIllllllIIIIlII);
        final short llllllllllllllllIlIllllllIIIIIlI = (short)llllllllllllllllIlIllllllIIIIIll.read();
        final short llllllllllllllllIlIllllllIIIIIIl = (short)llllllllllllllllIlIllllllIIIIIll.read();
        final short llllllllllllllllIlIllllllIIIIIII = (short)llllllllllllllllIlIllllllIIIIIll.read();
        final short llllllllllllllllIlIlllllIlllllll = this.flipEndian(llllllllllllllllIlIllllllIIIIIll.readShort());
        final short llllllllllllllllIlIlllllIllllllI = this.flipEndian(llllllllllllllllIlIllllllIIIIIll.readShort());
        final short llllllllllllllllIlIlllllIlllllIl = (short)llllllllllllllllIlIllllllIIIIIll.read();
        final short llllllllllllllllIlIlllllIlllllII = this.flipEndian(llllllllllllllllIlIllllllIIIIIll.readShort());
        final short llllllllllllllllIlIlllllIllllIll = this.flipEndian(llllllllllllllllIlIllllllIIIIIll.readShort());
        if (llllllllllllllllIlIllllllIIIIIII != 2) {
            throw new IOException("Slick only supports uncompressed RGB(A) TGA images");
        }
        this.width = this.flipEndian(llllllllllllllllIlIllllllIIIIIll.readShort());
        this.height = this.flipEndian(llllllllllllllllIlIllllllIIIIIll.readShort());
        this.pixelDepth = (short)llllllllllllllllIlIllllllIIIIIll.read();
        if (this.pixelDepth == 32) {
            llllllllllllllllIlIlllllIlllIIll = 0;
        }
        this.texWidth = this.get2Fold(this.width);
        this.texHeight = this.get2Fold(this.height);
        final short llllllllllllllllIlIlllllIllllIlI = (short)llllllllllllllllIlIllllllIIIIIll.read();
        if ((llllllllllllllllIlIlllllIllllIlI & 0x20) == 0x0) {
            llllllllllllllllIlIlllllIlllIlII = !llllllllllllllllIlIlllllIlllIlII;
        }
        if (llllllllllllllllIlIllllllIIIIIlI > 0) {
            llllllllllllllllIlIllllllIIIIlII.skip(llllllllllllllllIlIllllllIIIIIlI);
        }
        byte[] llllllllllllllllIlIlllllIllllIIl = null;
        if (this.pixelDepth == 32 || llllllllllllllllIlIlllllIlllIIll != 0) {
            this.pixelDepth = 32;
            llllllllllllllllIlIlllllIllllIIl = new byte[this.texWidth * this.texHeight * 4];
        }
        else {
            if (this.pixelDepth != 24) {
                throw new RuntimeException("Only 24 and 32 bit TGAs are supported");
            }
            llllllllllllllllIlIlllllIllllIIl = new byte[this.texWidth * this.texHeight * 3];
        }
        if (this.pixelDepth == 24) {
            if (llllllllllllllllIlIlllllIlllIlII) {
                for (int llllllllllllllllIlIllllllIIlllll = this.height - 1; llllllllllllllllIlIllllllIIlllll >= 0; --llllllllllllllllIlIllllllIIlllll) {
                    for (int llllllllllllllllIlIllllllIlIIIII = 0; llllllllllllllllIlIllllllIlIIIII < this.width; ++llllllllllllllllIlIllllllIlIIIII) {
                        llllllllllllllllIlIllllllIIIIllI = llllllllllllllllIlIllllllIIIIIll.readByte();
                        llllllllllllllllIlIllllllIIIIlll = llllllllllllllllIlIllllllIIIIIll.readByte();
                        llllllllllllllllIlIllllllIIIlIII = llllllllllllllllIlIllllllIIIIIll.readByte();
                        final int llllllllllllllllIlIllllllIlIIIIl = (llllllllllllllllIlIllllllIlIIIII + llllllllllllllllIlIllllllIIlllll * this.texWidth) * 3;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIlIIIIl] = llllllllllllllllIlIllllllIIIlIII;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIlIIIIl + 1] = llllllllllllllllIlIllllllIIIIlll;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIlIIIIl + 2] = llllllllllllllllIlIllllllIIIIllI;
                    }
                }
            }
            else {
                for (int llllllllllllllllIlIllllllIIlllII = 0; llllllllllllllllIlIllllllIIlllII < this.height; ++llllllllllllllllIlIllllllIIlllII) {
                    for (int llllllllllllllllIlIllllllIIlllIl = 0; llllllllllllllllIlIllllllIIlllIl < this.width; ++llllllllllllllllIlIllllllIIlllIl) {
                        llllllllllllllllIlIllllllIIIIllI = llllllllllllllllIlIllllllIIIIIll.readByte();
                        llllllllllllllllIlIllllllIIIIlll = llllllllllllllllIlIllllllIIIIIll.readByte();
                        llllllllllllllllIlIllllllIIIlIII = llllllllllllllllIlIllllllIIIIIll.readByte();
                        final int llllllllllllllllIlIllllllIIllllI = (llllllllllllllllIlIllllllIIlllIl + llllllllllllllllIlIllllllIIlllII * this.texWidth) * 3;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllllI] = llllllllllllllllIlIllllllIIIlIII;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllllI + 1] = llllllllllllllllIlIllllllIIIIlll;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllllI + 2] = llllllllllllllllIlIllllllIIIIllI;
                    }
                }
            }
        }
        else if (this.pixelDepth == 32) {
            if (llllllllllllllllIlIlllllIlllIlII) {
                for (int llllllllllllllllIlIllllllIIllIIl = this.height - 1; llllllllllllllllIlIllllllIIllIIl >= 0; --llllllllllllllllIlIllllllIIllIIl) {
                    for (int llllllllllllllllIlIllllllIIllIlI = 0; llllllllllllllllIlIllllllIIllIlI < this.width; ++llllllllllllllllIlIllllllIIllIlI) {
                        llllllllllllllllIlIllllllIIIIllI = llllllllllllllllIlIllllllIIIIIll.readByte();
                        llllllllllllllllIlIllllllIIIIlll = llllllllllllllllIlIllllllIIIIIll.readByte();
                        llllllllllllllllIlIllllllIIIlIII = llllllllllllllllIlIllllllIIIIIll.readByte();
                        if (llllllllllllllllIlIlllllIlllIIll != 0) {
                            llllllllllllllllIlIllllllIIIIlIl = -1;
                        }
                        else {
                            llllllllllllllllIlIllllllIIIIlIl = llllllllllllllllIlIllllllIIIIIll.readByte();
                        }
                        final int llllllllllllllllIlIllllllIIllIll = (llllllllllllllllIlIllllllIIllIlI + llllllllllllllllIlIllllllIIllIIl * this.texWidth) * 4;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIll] = llllllllllllllllIlIllllllIIIlIII;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIll + 1] = llllllllllllllllIlIllllllIIIIlll;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIll + 2] = llllllllllllllllIlIllllllIIIIllI;
                        llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIll + 3] = llllllllllllllllIlIllllllIIIIlIl;
                        if (llllllllllllllllIlIllllllIIIIlIl == 0) {
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIll + 2] = 0;
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIll] = (llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIll + 1] = 0);
                        }
                    }
                }
            }
            else {
                for (int llllllllllllllllIlIllllllIIlIllI = 0; llllllllllllllllIlIllllllIIlIllI < this.height; ++llllllllllllllllIlIllllllIIlIllI) {
                    for (int llllllllllllllllIlIllllllIIlIlll = 0; llllllllllllllllIlIllllllIIlIlll < this.width; ++llllllllllllllllIlIllllllIIlIlll) {
                        llllllllllllllllIlIllllllIIIIllI = llllllllllllllllIlIllllllIIIIIll.readByte();
                        llllllllllllllllIlIllllllIIIIlll = llllllllllllllllIlIllllllIIIIIll.readByte();
                        llllllllllllllllIlIllllllIIIlIII = llllllllllllllllIlIllllllIIIIIll.readByte();
                        if (llllllllllllllllIlIlllllIlllIIll != 0) {
                            llllllllllllllllIlIllllllIIIIlIl = -1;
                        }
                        else {
                            llllllllllllllllIlIllllllIIIIlIl = llllllllllllllllIlIllllllIIIIIll.readByte();
                        }
                        final int llllllllllllllllIlIllllllIIllIII = (llllllllllllllllIlIllllllIIlIlll + llllllllllllllllIlIllllllIIlIllI * this.texWidth) * 4;
                        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII] = llllllllllllllllIlIllllllIIIlIII;
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII + 1] = llllllllllllllllIlIllllllIIIIlll;
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII + 2] = llllllllllllllllIlIllllllIIIIllI;
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII + 3] = llllllllllllllllIlIllllllIIIIlIl;
                        }
                        else {
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII] = llllllllllllllllIlIllllllIIIlIII;
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII + 1] = llllllllllllllllIlIllllllIIIIlll;
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII + 2] = llllllllllllllllIlIllllllIIIIllI;
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII + 3] = llllllllllllllllIlIllllllIIIIlIl;
                        }
                        if (llllllllllllllllIlIllllllIIIIlIl == 0) {
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII + 2] = 0;
                            llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII] = (llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIllIII + 1] = 0);
                        }
                    }
                }
            }
        }
        llllllllllllllllIlIlllllIlllIlIl.close();
        if (llllllllllllllllIlIllllllIIIlIIl != null) {
            for (int llllllllllllllllIlIllllllIIlIIll = 0; llllllllllllllllIlIllllllIIlIIll < llllllllllllllllIlIlllllIllllIIl.length; llllllllllllllllIlIllllllIIlIIll += 4) {
                boolean llllllllllllllllIlIllllllIIlIlII = true;
                for (int llllllllllllllllIlIllllllIIlIlIl = 0; llllllllllllllllIlIllllllIIlIlIl < 3; ++llllllllllllllllIlIllllllIIlIlIl) {
                    if (llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIlIIll + llllllllllllllllIlIllllllIIlIlIl] != llllllllllllllllIlIllllllIIIlIIl[llllllllllllllllIlIllllllIIlIlIl]) {
                        llllllllllllllllIlIllllllIIlIlII = false;
                    }
                }
                if (llllllllllllllllIlIllllllIIlIlII) {
                    llllllllllllllllIlIlllllIllllIIl[llllllllllllllllIlIllllllIIlIIll + 3] = 0;
                }
            }
        }
        final ByteBuffer llllllllllllllllIlIlllllIllllIII = BufferUtils.createByteBuffer(llllllllllllllllIlIlllllIllllIIl.length);
        llllllllllllllllIlIlllllIllllIII.put(llllllllllllllllIlIlllllIllllIIl);
        final int llllllllllllllllIlIlllllIlllIlll = this.pixelDepth / 8;
        if (this.height < this.texHeight - 1) {
            final int llllllllllllllllIlIllllllIIlIIIl = (this.texHeight - 1) * (this.texWidth * llllllllllllllllIlIlllllIlllIlll);
            final int llllllllllllllllIlIllllllIIlIIII = (this.height - 1) * (this.texWidth * llllllllllllllllIlIlllllIlllIlll);
            for (int llllllllllllllllIlIllllllIIlIIlI = 0; llllllllllllllllIlIllllllIIlIIlI < this.texWidth * llllllllllllllllIlIlllllIlllIlll; ++llllllllllllllllIlIllllllIIlIIlI) {
                llllllllllllllllIlIlllllIllllIII.put(llllllllllllllllIlIllllllIIlIIIl + llllllllllllllllIlIllllllIIlIIlI, llllllllllllllllIlIlllllIllllIII.get(llllllllllllllllIlIllllllIIlIIlI));
                llllllllllllllllIlIlllllIllllIII.put(llllllllllllllllIlIllllllIIlIIII + this.texWidth * llllllllllllllllIlIlllllIlllIlll + llllllllllllllllIlIllllllIIlIIlI, llllllllllllllllIlIlllllIllllIII.get(this.texWidth * llllllllllllllllIlIlllllIlllIlll + llllllllllllllllIlIllllllIIlIIlI));
            }
        }
        if (this.width < this.texWidth - 1) {
            for (int llllllllllllllllIlIllllllIIIlllI = 0; llllllllllllllllIlIllllllIIIlllI < this.texHeight; ++llllllllllllllllIlIllllllIIIlllI) {
                for (int llllllllllllllllIlIllllllIIIllll = 0; llllllllllllllllIlIllllllIIIllll < llllllllllllllllIlIlllllIlllIlll; ++llllllllllllllllIlIllllllIIIllll) {
                    llllllllllllllllIlIlllllIllllIII.put((llllllllllllllllIlIllllllIIIlllI + 1) * (this.texWidth * llllllllllllllllIlIlllllIlllIlll) - llllllllllllllllIlIlllllIlllIlll + llllllllllllllllIlIllllllIIIllll, llllllllllllllllIlIlllllIllllIII.get(llllllllllllllllIlIllllllIIIlllI * (this.texWidth * llllllllllllllllIlIlllllIlllIlll) + llllllllllllllllIlIllllllIIIllll));
                    llllllllllllllllIlIlllllIllllIII.put(llllllllllllllllIlIllllllIIIlllI * (this.texWidth * llllllllllllllllIlIlllllIlllIlll) + this.width * llllllllllllllllIlIlllllIlllIlll + llllllllllllllllIlIllllllIIIllll, llllllllllllllllIlIlllllIllllIII.get(llllllllllllllllIlIllllllIIIlllI * (this.texWidth * llllllllllllllllIlIlllllIlllIlll) + (this.width - 1) * llllllllllllllllIlIlllllIlllIlll + llllllllllllllllIlIllllllIIIllll));
                }
            }
        }
        llllllllllllllllIlIlllllIllllIII.flip();
        return llllllllllllllllIlIlllllIllllIII;
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llllllllllllllllIlIlllllllIlIIII, final boolean llllllllllllllllIlIlllllllIIllll, final int[] llllllllllllllllIlIlllllllIlIIlI) throws IOException {
        return this.loadImage(llllllllllllllllIlIlllllllIlIIII, llllllllllllllllIlIlllllllIIllll, false, llllllllllllllllIlIlllllllIlIIlI);
    }
    
    @Override
    public int getTexHeight() {
        return this.texHeight;
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llllllllllllllllIlIlllllllIllIlI) throws IOException {
        return this.loadImage(llllllllllllllllIlIlllllllIllIlI, true, null);
    }
    
    @Override
    public int getDepth() {
        return this.pixelDepth;
    }
    
    private short flipEndian(final short llllllllllllllllIlIlllllllllIIlI) {
        final int llllllllllllllllIlIlllllllllIIIl = llllllllllllllllIlIlllllllllIIlI & 0xFFFF;
        return (short)(llllllllllllllllIlIlllllllllIIIl << 8 | (llllllllllllllllIlIlllllllllIIIl & 0xFF00) >>> 8);
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        throw new RuntimeException("TGAImageData doesn't store it's image.");
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public int getTexWidth() {
        return this.texWidth;
    }
}
