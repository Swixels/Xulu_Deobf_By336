package org.newdawn.slick.opengl;

import java.nio.*;
import java.io.*;
import java.util.zip.*;
import java.util.*;

public class PNGDecoder
{
    private /* synthetic */ byte[] transPixel;
    public static /* synthetic */ Format LUMINANCE;
    private /* synthetic */ int bitdepth;
    private final /* synthetic */ InputStream input;
    private final /* synthetic */ CRC32 crc;
    public static /* synthetic */ Format ABGR;
    private /* synthetic */ int chunkLength;
    private final /* synthetic */ byte[] buffer;
    private /* synthetic */ int height;
    public static /* synthetic */ Format ALPHA;
    private /* synthetic */ byte[] palette;
    public static /* synthetic */ Format RGB;
    private static final /* synthetic */ byte[] SIGNATURE;
    private /* synthetic */ int bytesPerPixel;
    public static /* synthetic */ Format BGRA;
    private /* synthetic */ byte[] paletteA;
    public static /* synthetic */ Format RGBA;
    private /* synthetic */ int chunkType;
    private /* synthetic */ int chunkRemaining;
    private /* synthetic */ int width;
    private /* synthetic */ int colorType;
    public static /* synthetic */ Format LUMINANCE_ALPHA;
    
    public void decode(final ByteBuffer lllllllllllllllllllllIIlIlIlIllI, final int lllllllllllllllllllllIIlIlIIlIll, final Format lllllllllllllllllllllIIlIlIlIlII) throws IOException {
        final int lllllllllllllllllllllIIlIlIlIIll = lllllllllllllllllllllIIlIlIlIllI.position();
        final int lllllllllllllllllllllIIlIlIlIIlI = (this.width * this.bitdepth + 7) / 8 * this.bytesPerPixel;
        byte[] lllllllllllllllllllllIIlIlIlIIIl = new byte[lllllllllllllllllllllIIlIlIlIIlI + 1];
        byte[] lllllllllllllllllllllIIlIlIlIIII = new byte[lllllllllllllllllllllIIlIlIlIIlI + 1];
        byte[] lllllllllllllllllllllIIlIlIIllll = (byte[])((this.bitdepth < 8) ? new byte[this.width + 1] : null);
        final Inflater lllllllllllllllllllllIIlIlIIlllI = new Inflater();
        try {
            for (int lllllllllllllllllllllIIlIlIllIII = 0; lllllllllllllllllllllIIlIlIllIII < this.height; ++lllllllllllllllllllllIIlIlIllIII) {
                this.readChunkUnzip(lllllllllllllllllllllIIlIlIIlllI, lllllllllllllllllllllIIlIlIlIIIl, 0, lllllllllllllllllllllIIlIlIlIIIl.length);
                this.unfilter(lllllllllllllllllllllIIlIlIlIIIl, lllllllllllllllllllllIIlIlIlIIII);
                lllllllllllllllllllllIIlIlIlIllI.position(lllllllllllllllllllllIIlIlIlIIll + lllllllllllllllllllllIIlIlIllIII * lllllllllllllllllllllIIlIlIIlIll);
                switch (this.colorType) {
                    case 2: {
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.ABGR) {
                            this.copyRGBtoABGR(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.RGBA) {
                            this.copyRGBtoRGBA(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.BGRA) {
                            this.copyRGBtoBGRA(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.RGB) {
                            this.copy(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 6: {
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.ABGR) {
                            this.copyRGBAtoABGR(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.RGBA) {
                            this.copy(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.BGRA) {
                            this.copyRGBAtoBGRA(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.RGB) {
                            this.copyRGBAtoRGB(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 0: {
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.LUMINANCE || lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.ALPHA) {
                            this.copy(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 4: {
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.LUMINANCE_ALPHA) {
                            this.copy(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIlIIIl);
                            break;
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 3: {
                        switch (this.bitdepth) {
                            case 8: {
                                lllllllllllllllllllllIIlIlIIllll = lllllllllllllllllllllIIlIlIlIIIl;
                                break;
                            }
                            case 4: {
                                this.expand4(lllllllllllllllllllllIIlIlIlIIIl, lllllllllllllllllllllIIlIlIIllll);
                                break;
                            }
                            case 2: {
                                this.expand2(lllllllllllllllllllllIIlIlIlIIIl, lllllllllllllllllllllIIlIlIIllll);
                                break;
                            }
                            case 1: {
                                this.expand1(lllllllllllllllllllllIIlIlIlIIIl, lllllllllllllllllllllIIlIlIIllll);
                                break;
                            }
                            default: {
                                throw new UnsupportedOperationException("Unsupported bitdepth for this image");
                            }
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.ABGR) {
                            this.copyPALtoABGR(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIIllll);
                            break;
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.RGBA) {
                            this.copyPALtoRGBA(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIIllll);
                            break;
                        }
                        if (lllllllllllllllllllllIIlIlIlIlII == PNGDecoder.BGRA) {
                            this.copyPALtoBGRA(lllllllllllllllllllllIIlIlIlIllI, lllllllllllllllllllllIIlIlIIllll);
                            break;
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    default: {
                        throw new UnsupportedOperationException("Not yet implemented");
                    }
                }
                final byte[] lllllllllllllllllllllIIlIlIllIIl = lllllllllllllllllllllIIlIlIlIIIl;
                lllllllllllllllllllllIIlIlIlIIIl = lllllllllllllllllllllIIlIlIlIIII;
                lllllllllllllllllllllIIlIlIlIIII = lllllllllllllllllllllIIlIlIllIIl;
            }
        }
        finally {
            lllllllllllllllllllllIIlIlIIlllI.end();
        }
    }
    
    private void unfilterSub(final byte[] llllllllllllllllllllIllllIllllll) {
        final int llllllllllllllllllllIllllIlllllI = this.bytesPerPixel;
        for (int llllllllllllllllllllIlllllIIIIlI = llllllllllllllllllllIllllIlllllI + 1, llllllllllllllllllllIlllllIIIIIl = llllllllllllllllllllIllllIllllll.length; llllllllllllllllllllIlllllIIIIlI < llllllllllllllllllllIlllllIIIIIl; ++llllllllllllllllllllIlllllIIIIlI) {
            final int n = llllllllllllllllllllIlllllIIIIlI;
            llllllllllllllllllllIllllIllllll[n] += llllllllllllllllllllIllllIllllll[llllllllllllllllllllIlllllIIIIlI - llllllllllllllllllllIllllIlllllI];
        }
    }
    
    private int readChunk(final byte[] llllllllllllllllllllIlllIlIIIlII, final int llllllllllllllllllllIlllIIllllll, int llllllllllllllllllllIlllIIlllllI) throws IOException {
        if (llllllllllllllllllllIlllIIlllllI > this.chunkRemaining) {
            llllllllllllllllllllIlllIIlllllI = this.chunkRemaining;
        }
        this.readFully(llllllllllllllllllllIlllIlIIIlII, llllllllllllllllllllIlllIIllllll, llllllllllllllllllllIlllIIlllllI);
        this.crc.update(llllllllllllllllllllIlllIlIIIlII, llllllllllllllllllllIlllIIllllll, llllllllllllllllllllIlllIIlllllI);
        this.chunkRemaining -= llllllllllllllllllllIlllIIlllllI;
        return llllllllllllllllllllIlllIIlllllI;
    }
    
    private void expand1(final byte[] llllllllllllllllllllIlllllIlIlIl, final byte[] llllllllllllllllllllIlllllIlIlII) {
        int llllllllllllllllllllIlllllIllIlI = 1;
        final int llllllllllllllllllllIlllllIllIIl = llllllllllllllllllllIlllllIlIlII.length;
        while (llllllllllllllllllllIlllllIllIlI < llllllllllllllllllllIlllllIllIIl) {
            final int llllllllllllllllllllIlllllIllIll = llllllllllllllllllllIlllllIlIlIl[1 + (llllllllllllllllllllIlllllIllIlI >> 3)] & 0xFF;
            switch (llllllllllllllllllllIlllllIllIIl - llllllllllllllllllllIlllllIllIlI) {
                default: {
                    llllllllllllllllllllIlllllIlIlII[llllllllllllllllllllIlllllIllIlI + 7] = (byte)(llllllllllllllllllllIlllllIllIll & 0x1);
                }
                case 7: {
                    llllllllllllllllllllIlllllIlIlII[llllllllllllllllllllIlllllIllIlI + 6] = (byte)(llllllllllllllllllllIlllllIllIll >> 1 & 0x1);
                }
                case 6: {
                    llllllllllllllllllllIlllllIlIlII[llllllllllllllllllllIlllllIllIlI + 5] = (byte)(llllllllllllllllllllIlllllIllIll >> 2 & 0x1);
                }
                case 5: {
                    llllllllllllllllllllIlllllIlIlII[llllllllllllllllllllIlllllIllIlI + 4] = (byte)(llllllllllllllllllllIlllllIllIll >> 3 & 0x1);
                }
                case 4: {
                    llllllllllllllllllllIlllllIlIlII[llllllllllllllllllllIlllllIllIlI + 3] = (byte)(llllllllllllllllllllIlllllIllIll >> 4 & 0x1);
                }
                case 3: {
                    llllllllllllllllllllIlllllIlIlII[llllllllllllllllllllIlllllIllIlI + 2] = (byte)(llllllllllllllllllllIlllllIllIll >> 5 & 0x1);
                }
                case 2: {
                    llllllllllllllllllllIlllllIlIlII[llllllllllllllllllllIlllllIllIlI + 1] = (byte)(llllllllllllllllllllIlllllIllIll >> 6 & 0x1);
                }
                case 1: {
                    llllllllllllllllllllIlllllIlIlII[llllllllllllllllllllIlllllIllIlI] = (byte)(llllllllllllllllllllIlllllIllIll >> 7);
                    llllllllllllllllllllIlllllIllIlI += 8;
                    continue;
                }
            }
        }
    }
    
    private void unfilter(final byte[] llllllllllllllllllllIlllllIIllII, final byte[] llllllllllllllllllllIlllllIIlIII) throws IOException {
        switch (llllllllllllllllllllIlllllIIllII[0]) {
            case 0: {
                break;
            }
            case 1: {
                this.unfilterSub(llllllllllllllllllllIlllllIIllII);
                break;
            }
            case 2: {
                this.unfilterUp(llllllllllllllllllllIlllllIIllII, llllllllllllllllllllIlllllIIlIII);
                break;
            }
            case 3: {
                this.unfilterAverage(llllllllllllllllllllIlllllIIllII, llllllllllllllllllllIlllllIIlIII);
                break;
            }
            case 4: {
                this.unfilterPaeth(llllllllllllllllllllIlllllIIllII, llllllllllllllllllllIlllllIIlIII);
                break;
            }
            default: {
                throw new IOException(String.valueOf(new StringBuilder().append("invalide filter type in scanline: ").append(llllllllllllllllllllIlllllIIllII[0])));
            }
        }
    }
    
    private void unfilterAverage(final byte[] llllllllllllllllllllIllllIIllIIl, final byte[] llllllllllllllllllllIllllIIllIII) {
        int llllllllllllllllllllIllllIIlllII;
        int llllllllllllllllllllIllllIIllIll;
        for (llllllllllllllllllllIllllIIlllII = this.bytesPerPixel, llllllllllllllllllllIllllIIllIll = 1; llllllllllllllllllllIllllIIllIll <= llllllllllllllllllllIllllIIlllII; ++llllllllllllllllllllIllllIIllIll) {
            final int n = llllllllllllllllllllIllllIIllIll;
            llllllllllllllllllllIllllIIllIIl[n] += (byte)((llllllllllllllllllllIllllIIllIII[llllllllllllllllllllIllllIIllIll] & 0xFF) >>> 1);
        }
        for (int llllllllllllllllllllIllllIlIIIII = llllllllllllllllllllIllllIIllIIl.length; llllllllllllllllllllIllllIIllIll < llllllllllllllllllllIllllIlIIIII; ++llllllllllllllllllllIllllIIllIll) {
            final int n2 = llllllllllllllllllllIllllIIllIll;
            llllllllllllllllllllIllllIIllIIl[n2] += (byte)((llllllllllllllllllllIllllIIllIII[llllllllllllllllllllIllllIIllIll] & 0xFF) + (llllllllllllllllllllIllllIIllIIl[llllllllllllllllllllIllllIIllIll - llllllllllllllllllllIllllIIlllII] & 0xFF) >>> 1);
        }
    }
    
    private void copyPALtoRGBA(final ByteBuffer lllllllllllllllllllllIIIIIllIIIl, final byte[] lllllllllllllllllllllIIIIIllIIII) {
        if (this.paletteA != null) {
            for (int lllllllllllllllllllllIIIIIlllIll = 1, lllllllllllllllllllllIIIIIlllIlI = lllllllllllllllllllllIIIIIllIIII.length; lllllllllllllllllllllIIIIIlllIll < lllllllllllllllllllllIIIIIlllIlI; ++lllllllllllllllllllllIIIIIlllIll) {
                final int lllllllllllllllllllllIIIIlIIIIII = lllllllllllllllllllllIIIIIllIIII[lllllllllllllllllllllIIIIIlllIll] & 0xFF;
                final byte lllllllllllllllllllllIIIIIllllll = this.palette[lllllllllllllllllllllIIIIlIIIIII * 3 + 0];
                final byte lllllllllllllllllllllIIIIIlllllI = this.palette[lllllllllllllllllllllIIIIlIIIIII * 3 + 1];
                final byte lllllllllllllllllllllIIIIIllllIl = this.palette[lllllllllllllllllllllIIIIlIIIIII * 3 + 2];
                final byte lllllllllllllllllllllIIIIIllllII = this.paletteA[lllllllllllllllllllllIIIIlIIIIII];
                lllllllllllllllllllllIIIIIllIIIl.put(lllllllllllllllllllllIIIIIllllll).put(lllllllllllllllllllllIIIIIlllllI).put(lllllllllllllllllllllIIIIIllllIl).put(lllllllllllllllllllllIIIIIllllII);
            }
        }
        else {
            for (int lllllllllllllllllllllIIIIIllIlII = 1, lllllllllllllllllllllIIIIIllIIll = lllllllllllllllllllllIIIIIllIIII.length; lllllllllllllllllllllIIIIIllIlII < lllllllllllllllllllllIIIIIllIIll; ++lllllllllllllllllllllIIIIIllIlII) {
                final int lllllllllllllllllllllIIIIIlllIIl = lllllllllllllllllllllIIIIIllIIII[lllllllllllllllllllllIIIIIllIlII] & 0xFF;
                final byte lllllllllllllllllllllIIIIIlllIII = this.palette[lllllllllllllllllllllIIIIIlllIIl * 3 + 0];
                final byte lllllllllllllllllllllIIIIIllIlll = this.palette[lllllllllllllllllllllIIIIIlllIIl * 3 + 1];
                final byte lllllllllllllllllllllIIIIIllIllI = this.palette[lllllllllllllllllllllIIIIIlllIIl * 3 + 2];
                final byte lllllllllllllllllllllIIIIIllIlIl = -1;
                lllllllllllllllllllllIIIIIllIIIl.put(lllllllllllllllllllllIIIIIlllIII).put(lllllllllllllllllllllIIIIIllIlll).put(lllllllllllllllllllllIIIIIllIllI).put(lllllllllllllllllllllIIIIIllIlIl);
            }
        }
    }
    
    public int getHeight() {
        return this.height;
    }
    
    private void readIHDR() throws IOException {
        this.checkChunkLength(13);
        this.readChunk(this.buffer, 0, 13);
        this.width = this.readInt(this.buffer, 0);
        this.height = this.readInt(this.buffer, 4);
        this.bitdepth = (this.buffer[8] & 0xFF);
        this.colorType = (this.buffer[9] & 0xFF);
        Label_0434: {
            switch (this.colorType) {
                case 0: {
                    if (this.bitdepth != 8) {
                        throw new IOException(String.valueOf(new StringBuilder().append("Unsupported bit depth: ").append(this.bitdepth)));
                    }
                    this.bytesPerPixel = 1;
                    break;
                }
                case 4: {
                    if (this.bitdepth != 8) {
                        throw new IOException(String.valueOf(new StringBuilder().append("Unsupported bit depth: ").append(this.bitdepth)));
                    }
                    this.bytesPerPixel = 2;
                    break;
                }
                case 2: {
                    if (this.bitdepth != 8) {
                        throw new IOException(String.valueOf(new StringBuilder().append("Unsupported bit depth: ").append(this.bitdepth)));
                    }
                    this.bytesPerPixel = 3;
                    break;
                }
                case 6: {
                    if (this.bitdepth != 8) {
                        throw new IOException(String.valueOf(new StringBuilder().append("Unsupported bit depth: ").append(this.bitdepth)));
                    }
                    this.bytesPerPixel = 4;
                    break;
                }
                case 3: {
                    switch (this.bitdepth) {
                        case 1:
                        case 2:
                        case 4:
                        case 8: {
                            this.bytesPerPixel = 1;
                            break Label_0434;
                        }
                        default: {
                            throw new IOException(String.valueOf(new StringBuilder().append("Unsupported bit depth: ").append(this.bitdepth)));
                        }
                    }
                    break;
                }
                default: {
                    throw new IOException(String.valueOf(new StringBuilder().append("unsupported color format: ").append(this.colorType)));
                }
            }
        }
        if (this.buffer[10] != 0) {
            throw new IOException("unsupported compression method");
        }
        if (this.buffer[11] != 0) {
            throw new IOException("unsupported filtering method");
        }
        if (this.buffer[12] != 0) {
            throw new IOException("unsupported interlace method");
        }
    }
    
    private void copyPALtoABGR(final ByteBuffer lllllllllllllllllllllIIIIlIlIllI, final byte[] lllllllllllllllllllllIIIIlIlIlIl) {
        if (this.paletteA != null) {
            for (int lllllllllllllllllllllIIIIllIIIII = 1, lllllllllllllllllllllIIIIlIlllll = lllllllllllllllllllllIIIIlIlIlIl.length; lllllllllllllllllllllIIIIllIIIII < lllllllllllllllllllllIIIIlIlllll; ++lllllllllllllllllllllIIIIllIIIII) {
                final int lllllllllllllllllllllIIIIllIIlIl = lllllllllllllllllllllIIIIlIlIlIl[lllllllllllllllllllllIIIIllIIIII] & 0xFF;
                final byte lllllllllllllllllllllIIIIllIIlII = this.palette[lllllllllllllllllllllIIIIllIIlIl * 3 + 0];
                final byte lllllllllllllllllllllIIIIllIIIll = this.palette[lllllllllllllllllllllIIIIllIIlIl * 3 + 1];
                final byte lllllllllllllllllllllIIIIllIIIlI = this.palette[lllllllllllllllllllllIIIIllIIlIl * 3 + 2];
                final byte lllllllllllllllllllllIIIIllIIIIl = this.paletteA[lllllllllllllllllllllIIIIllIIlIl];
                lllllllllllllllllllllIIIIlIlIllI.put(lllllllllllllllllllllIIIIllIIIIl).put(lllllllllllllllllllllIIIIllIIIlI).put(lllllllllllllllllllllIIIIllIIIll).put(lllllllllllllllllllllIIIIllIIlII);
            }
        }
        else {
            for (int lllllllllllllllllllllIIIIlIllIIl = 1, lllllllllllllllllllllIIIIlIllIII = lllllllllllllllllllllIIIIlIlIlIl.length; lllllllllllllllllllllIIIIlIllIIl < lllllllllllllllllllllIIIIlIllIII; ++lllllllllllllllllllllIIIIlIllIIl) {
                final int lllllllllllllllllllllIIIIlIllllI = lllllllllllllllllllllIIIIlIlIlIl[lllllllllllllllllllllIIIIlIllIIl] & 0xFF;
                final byte lllllllllllllllllllllIIIIlIlllIl = this.palette[lllllllllllllllllllllIIIIlIllllI * 3 + 0];
                final byte lllllllllllllllllllllIIIIlIlllII = this.palette[lllllllllllllllllllllIIIIlIllllI * 3 + 1];
                final byte lllllllllllllllllllllIIIIlIllIll = this.palette[lllllllllllllllllllllIIIIlIllllI * 3 + 2];
                final byte lllllllllllllllllllllIIIIlIllIlI = -1;
                lllllllllllllllllllllIIIIlIlIllI.put(lllllllllllllllllllllIIIIlIllIlI).put(lllllllllllllllllllllIIIIlIllIll).put(lllllllllllllllllllllIIIIlIlllII).put(lllllllllllllllllllllIIIIlIlllIl);
            }
        }
    }
    
    private void copyRGBAtoBGRA(final ByteBuffer lllllllllllllllllllllIIIlIIIIIII, final byte[] lllllllllllllllllllllIIIlIIIIIIl) {
        for (int lllllllllllllllllllllIIIlIIIIlIl = 1, lllllllllllllllllllllIIIlIIIIlII = lllllllllllllllllllllIIIlIIIIIIl.length; lllllllllllllllllllllIIIlIIIIlIl < lllllllllllllllllllllIIIlIIIIlII; lllllllllllllllllllllIIIlIIIIlIl += 4) {
            lllllllllllllllllllllIIIlIIIIIII.put(lllllllllllllllllllllIIIlIIIIIIl[lllllllllllllllllllllIIIlIIIIlIl + 2]).put(lllllllllllllllllllllIIIlIIIIIIl[lllllllllllllllllllllIIIlIIIIlIl + 1]).put(lllllllllllllllllllllIIIlIIIIIIl[lllllllllllllllllllllIIIlIIIIlIl + 0]).put(lllllllllllllllllllllIIIlIIIIIIl[lllllllllllllllllllllIIIlIIIIlIl + 3]);
        }
    }
    
    private void copyRGBtoABGR(final ByteBuffer lllllllllllllllllllllIIlIIIllllI, final byte[] lllllllllllllllllllllIIlIIIlllIl) {
        if (this.transPixel != null) {
            final byte lllllllllllllllllllllIIlIIlIIlll = this.transPixel[1];
            final byte lllllllllllllllllllllIIlIIlIIllI = this.transPixel[3];
            final byte lllllllllllllllllllllIIlIIlIIlIl = this.transPixel[5];
            for (int lllllllllllllllllllllIIlIIlIlIIl = 1, lllllllllllllllllllllIIlIIlIlIII = lllllllllllllllllllllIIlIIIlllIl.length; lllllllllllllllllllllIIlIIlIlIIl < lllllllllllllllllllllIIlIIlIlIII; lllllllllllllllllllllIIlIIlIlIIl += 3) {
                final byte lllllllllllllllllllllIIlIIlIllIl = lllllllllllllllllllllIIlIIIlllIl[lllllllllllllllllllllIIlIIlIlIIl];
                final byte lllllllllllllllllllllIIlIIlIllII = lllllllllllllllllllllIIlIIIlllIl[lllllllllllllllllllllIIlIIlIlIIl + 1];
                final byte lllllllllllllllllllllIIlIIlIlIll = lllllllllllllllllllllIIlIIIlllIl[lllllllllllllllllllllIIlIIlIlIIl + 2];
                byte lllllllllllllllllllllIIlIIlIlIlI = -1;
                if (lllllllllllllllllllllIIlIIlIllIl == lllllllllllllllllllllIIlIIlIIlll && lllllllllllllllllllllIIlIIlIllII == lllllllllllllllllllllIIlIIlIIllI && lllllllllllllllllllllIIlIIlIlIll == lllllllllllllllllllllIIlIIlIIlIl) {
                    lllllllllllllllllllllIIlIIlIlIlI = 0;
                }
                lllllllllllllllllllllIIlIIIllllI.put(lllllllllllllllllllllIIlIIlIlIlI).put(lllllllllllllllllllllIIlIIlIlIll).put(lllllllllllllllllllllIIlIIlIllII).put(lllllllllllllllllllllIIlIIlIllIl);
            }
        }
        else {
            for (int lllllllllllllllllllllIIlIIlIIlII = 1, lllllllllllllllllllllIIlIIlIIIll = lllllllllllllllllllllIIlIIIlllIl.length; lllllllllllllllllllllIIlIIlIIlII < lllllllllllllllllllllIIlIIlIIIll; lllllllllllllllllllllIIlIIlIIlII += 3) {
                lllllllllllllllllllllIIlIIIllllI.put((byte)(-1)).put(lllllllllllllllllllllIIlIIIlllIl[lllllllllllllllllllllIIlIIlIIlII + 2]).put(lllllllllllllllllllllIIlIIIlllIl[lllllllllllllllllllllIIlIIlIIlII + 1]).put(lllllllllllllllllllllIIlIIIlllIl[lllllllllllllllllllllIIlIIlIIlII]);
            }
        }
    }
    
    public PNGDecoder(final InputStream lllllllllllllllllllllIIlllIlIIII) throws IOException {
        this.input = lllllllllllllllllllllIIlllIlIIII;
        this.crc = new CRC32();
        this.buffer = new byte[4096];
        this.readFully(this.buffer, 0, PNGDecoder.SIGNATURE.length);
        if (!checkSignature(this.buffer)) {
            throw new IOException("Not a valid PNG file");
        }
        this.openChunk(1229472850);
        this.readIHDR();
        this.closeChunk();
    Label_0120:
        while (true) {
            this.openChunk();
            switch (this.chunkType) {
                case 1229209940: {
                    break Label_0120;
                }
                case 1347179589: {
                    this.readPLTE();
                    break;
                }
                case 1951551059: {
                    this.readtRNS();
                    break;
                }
            }
            this.closeChunk();
        }
        if (this.colorType == 3 && this.palette == null) {
            throw new IOException("Missing PLTE chunk");
        }
    }
    
    private void copyPALtoBGRA(final ByteBuffer lllllllllllllllllllllIIIIIIIlIIl, final byte[] lllllllllllllllllllllIIIIIIIlIll) {
        if (this.paletteA != null) {
            for (int lllllllllllllllllllllIIIIIIlIllI = 1, lllllllllllllllllllllIIIIIIlIlIl = lllllllllllllllllllllIIIIIIIlIll.length; lllllllllllllllllllllIIIIIIlIllI < lllllllllllllllllllllIIIIIIlIlIl; ++lllllllllllllllllllllIIIIIIlIllI) {
                final int lllllllllllllllllllllIIIIIIllIll = lllllllllllllllllllllIIIIIIIlIll[lllllllllllllllllllllIIIIIIlIllI] & 0xFF;
                final byte lllllllllllllllllllllIIIIIIllIlI = this.palette[lllllllllllllllllllllIIIIIIllIll * 3 + 0];
                final byte lllllllllllllllllllllIIIIIIllIIl = this.palette[lllllllllllllllllllllIIIIIIllIll * 3 + 1];
                final byte lllllllllllllllllllllIIIIIIllIII = this.palette[lllllllllllllllllllllIIIIIIllIll * 3 + 2];
                final byte lllllllllllllllllllllIIIIIIlIlll = this.paletteA[lllllllllllllllllllllIIIIIIllIll];
                lllllllllllllllllllllIIIIIIIlIIl.put(lllllllllllllllllllllIIIIIIllIII).put(lllllllllllllllllllllIIIIIIllIIl).put(lllllllllllllllllllllIIIIIIllIlI).put(lllllllllllllllllllllIIIIIIlIlll);
            }
        }
        else {
            for (int lllllllllllllllllllllIIIIIIIllll = 1, lllllllllllllllllllllIIIIIIIlllI = lllllllllllllllllllllIIIIIIIlIll.length; lllllllllllllllllllllIIIIIIIllll < lllllllllllllllllllllIIIIIIIlllI; ++lllllllllllllllllllllIIIIIIIllll) {
                final int lllllllllllllllllllllIIIIIIlIlII = lllllllllllllllllllllIIIIIIIlIll[lllllllllllllllllllllIIIIIIIllll] & 0xFF;
                final byte lllllllllllllllllllllIIIIIIlIIll = this.palette[lllllllllllllllllllllIIIIIIlIlII * 3 + 0];
                final byte lllllllllllllllllllllIIIIIIlIIlI = this.palette[lllllllllllllllllllllIIIIIIlIlII * 3 + 1];
                final byte lllllllllllllllllllllIIIIIIlIIIl = this.palette[lllllllllllllllllllllIIIIIIlIlII * 3 + 2];
                final byte lllllllllllllllllllllIIIIIIlIIII = -1;
                lllllllllllllllllllllIIIIIIIlIIl.put(lllllllllllllllllllllIIIIIIlIIIl).put(lllllllllllllllllllllIIIIIIlIIlI).put(lllllllllllllllllllllIIIIIIlIIll).put(lllllllllllllllllllllIIIIIIlIIII);
            }
        }
    }
    
    private void unfilterPaeth(final byte[] llllllllllllllllllllIlllIllllIIl, final byte[] llllllllllllllllllllIlllIlllllIl) {
        int llllllllllllllllllllIlllIlllllII;
        int llllllllllllllllllllIlllIllllIll;
        for (llllllllllllllllllllIlllIlllllII = this.bytesPerPixel, llllllllllllllllllllIlllIllllIll = 1; llllllllllllllllllllIlllIllllIll <= llllllllllllllllllllIlllIlllllII; ++llllllllllllllllllllIlllIllllIll) {
            final int n = llllllllllllllllllllIlllIllllIll;
            llllllllllllllllllllIlllIllllIIl[n] += llllllllllllllllllllIlllIlllllIl[llllllllllllllllllllIlllIllllIll];
        }
        for (int llllllllllllllllllllIllllIIIIIII = llllllllllllllllllllIlllIllllIIl.length; llllllllllllllllllllIlllIllllIll < llllllllllllllllllllIllllIIIIIII; ++llllllllllllllllllllIlllIllllIll) {
            final int llllllllllllllllllllIllllIIIIlll = llllllllllllllllllllIlllIllllIIl[llllllllllllllllllllIlllIllllIll - llllllllllllllllllllIlllIlllllII] & 0xFF;
            final int llllllllllllllllllllIllllIIIIllI = llllllllllllllllllllIlllIlllllIl[llllllllllllllllllllIlllIllllIll] & 0xFF;
            int llllllllllllllllllllIllllIIIIlIl = llllllllllllllllllllIlllIlllllIl[llllllllllllllllllllIlllIllllIll - llllllllllllllllllllIlllIlllllII] & 0xFF;
            final int llllllllllllllllllllIllllIIIIlII = llllllllllllllllllllIllllIIIIlll + llllllllllllllllllllIllllIIIIllI - llllllllllllllllllllIllllIIIIlIl;
            int llllllllllllllllllllIllllIIIIIll = llllllllllllllllllllIllllIIIIlII - llllllllllllllllllllIllllIIIIlll;
            if (llllllllllllllllllllIllllIIIIIll < 0) {
                llllllllllllllllllllIllllIIIIIll = -llllllllllllllllllllIllllIIIIIll;
            }
            int llllllllllllllllllllIllllIIIIIlI = llllllllllllllllllllIllllIIIIlII - llllllllllllllllllllIllllIIIIllI;
            if (llllllllllllllllllllIllllIIIIIlI < 0) {
                llllllllllllllllllllIllllIIIIIlI = -llllllllllllllllllllIllllIIIIIlI;
            }
            int llllllllllllllllllllIllllIIIIIIl = llllllllllllllllllllIllllIIIIlII - llllllllllllllllllllIllllIIIIlIl;
            if (llllllllllllllllllllIllllIIIIIIl < 0) {
                llllllllllllllllllllIllllIIIIIIl = -llllllllllllllllllllIllllIIIIIIl;
            }
            if (llllllllllllllllllllIllllIIIIIll <= llllllllllllllllllllIllllIIIIIlI && llllllllllllllllllllIllllIIIIIll <= llllllllllllllllllllIllllIIIIIIl) {
                llllllllllllllllllllIllllIIIIlIl = llllllllllllllllllllIllllIIIIlll;
            }
            else if (llllllllllllllllllllIllllIIIIIlI <= llllllllllllllllllllIllllIIIIIIl) {
                llllllllllllllllllllIllllIIIIlIl = llllllllllllllllllllIllllIIIIllI;
            }
            final int n2 = llllllllllllllllllllIlllIllllIll;
            llllllllllllllllllllIlllIllllIIl[n2] += (byte)llllllllllllllllllllIllllIIIIlIl;
        }
    }
    
    private void unfilterUp(final byte[] llllllllllllllllllllIllllIlIllll, final byte[] llllllllllllllllllllIllllIlIlllI) {
        final int llllllllllllllllllllIllllIlIllIl = this.bytesPerPixel;
        for (int llllllllllllllllllllIllllIllIIlI = 1, llllllllllllllllllllIllllIllIIIl = llllllllllllllllllllIllllIlIllll.length; llllllllllllllllllllIllllIllIIlI < llllllllllllllllllllIllllIllIIIl; ++llllllllllllllllllllIllllIllIIlI) {
            final int n = llllllllllllllllllllIllllIllIIlI;
            llllllllllllllllllllIllllIlIllll[n] += llllllllllllllllllllIllllIlIlllI[llllllllllllllllllllIllllIllIIlI];
        }
    }
    
    private static boolean checkSignature(final byte[] llllllllllllllllllllIllIllllllll) {
        for (int llllllllllllllllllllIlllIIIIIIII = 0; llllllllllllllllllllIlllIIIIIIII < PNGDecoder.SIGNATURE.length; ++llllllllllllllllllllIlllIIIIIIII) {
            if (llllllllllllllllllllIllIllllllll[llllllllllllllllllllIlllIIIIIIII] != PNGDecoder.SIGNATURE[llllllllllllllllllllIlllIIIIIIII]) {
                return false;
            }
        }
        return true;
    }
    
    private void expand2(final byte[] llllllllllllllllllllIllllllIIlIl, final byte[] llllllllllllllllllllIllllllIIlII) {
        int llllllllllllllllllllIllllllIlIlI = 1;
        final int llllllllllllllllllllIllllllIlIIl = llllllllllllllllllllIllllllIIlII.length;
        while (llllllllllllllllllllIllllllIlIlI < llllllllllllllllllllIllllllIlIIl) {
            final int llllllllllllllllllllIllllllIlIll = llllllllllllllllllllIllllllIIlIl[1 + (llllllllllllllllllllIllllllIlIlI >> 2)] & 0xFF;
            switch (llllllllllllllllllllIllllllIlIIl - llllllllllllllllllllIllllllIlIlI) {
                default: {
                    llllllllllllllllllllIllllllIIlII[llllllllllllllllllllIllllllIlIlI + 3] = (byte)(llllllllllllllllllllIllllllIlIll & 0x3);
                }
                case 3: {
                    llllllllllllllllllllIllllllIIlII[llllllllllllllllllllIllllllIlIlI + 2] = (byte)(llllllllllllllllllllIllllllIlIll >> 2 & 0x3);
                }
                case 2: {
                    llllllllllllllllllllIllllllIIlII[llllllllllllllllllllIllllllIlIlI + 1] = (byte)(llllllllllllllllllllIllllllIlIll >> 4 & 0x3);
                }
                case 1: {
                    llllllllllllllllllllIllllllIIlII[llllllllllllllllllllIllllllIlIlI] = (byte)(llllllllllllllllllllIllllllIlIll >> 6);
                    llllllllllllllllllllIllllllIlIlI += 4;
                    continue;
                }
            }
        }
    }
    
    private void openChunk(final int llllllllllllllllllllIlllIlIlIIII) throws IOException {
        this.openChunk();
        if (this.chunkType != llllllllllllllllllllIlllIlIlIIII) {
            throw new IOException(String.valueOf(new StringBuilder().append("Expected chunk: ").append(Integer.toHexString(llllllllllllllllllllIlllIlIlIIII))));
        }
    }
    
    static {
        COLOR_GREYSCALE = 0;
        PLTE = 1347179589;
        COLOR_INDEXED = 3;
        COLOR_TRUEALPHA = 6;
        IHDR = 1229472850;
        IDAT = 1229209940;
        COLOR_GREYALPHA = 4;
        IEND = 1229278788;
        COLOR_TRUECOLOR = 2;
        tRNS = 1951551059;
        PNGDecoder.ALPHA = new Format(1, true);
        PNGDecoder.LUMINANCE = new Format(1, false);
        PNGDecoder.LUMINANCE_ALPHA = new Format(2, true);
        PNGDecoder.RGB = new Format(3, false);
        PNGDecoder.RGBA = new Format(4, true);
        PNGDecoder.BGRA = new Format(4, true);
        PNGDecoder.ABGR = new Format(4, true);
        SIGNATURE = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
    }
    
    private void readChunkUnzip(final Inflater llllllllllllllllllllIlllIIlIlIll, final byte[] llllllllllllllllllllIlllIIlIlIlI, int llllllllllllllllllllIlllIIlIIlII, int llllllllllllllllllllIlllIIlIIIll) throws IOException {
        try {
            do {
                final int llllllllllllllllllllIlllIIlIlllI = llllllllllllllllllllIlllIIlIlIll.inflate(llllllllllllllllllllIlllIIlIlIlI, llllllllllllllllllllIlllIIlIIlII, llllllllllllllllllllIlllIIlIIIll);
                if (llllllllllllllllllllIlllIIlIlllI <= 0) {
                    if (llllllllllllllllllllIlllIIlIlIll.finished()) {
                        throw new EOFException();
                    }
                    if (!llllllllllllllllllllIlllIIlIlIll.needsInput()) {
                        throw new IOException(String.valueOf(new StringBuilder().append("Can't inflate ").append(llllllllllllllllllllIlllIIlIIIll).append(" bytes")));
                    }
                    this.refillInflater(llllllllllllllllllllIlllIIlIlIll);
                }
                else {
                    llllllllllllllllllllIlllIIlIIlII += llllllllllllllllllllIlllIIlIlllI;
                    llllllllllllllllllllIlllIIlIIIll -= llllllllllllllllllllIlllIIlIlllI;
                }
            } while (llllllllllllllllllllIlllIIlIIIll > 0);
        }
        catch (DataFormatException llllllllllllllllllllIlllIIlIllIl) {
            throw (IOException)new IOException("inflate error").initCause(llllllllllllllllllllIlllIIlIllIl);
        }
    }
    
    private void copyRGBAtoRGB(final ByteBuffer lllllllllllllllllllllIIIIlllIIll, final byte[] lllllllllllllllllllllIIIIlllIlII) {
        for (int lllllllllllllllllllllIIIIllllIII = 1, lllllllllllllllllllllIIIIlllIlll = lllllllllllllllllllllIIIIlllIlII.length; lllllllllllllllllllllIIIIllllIII < lllllllllllllllllllllIIIIlllIlll; lllllllllllllllllllllIIIIllllIII += 4) {
            lllllllllllllllllllllIIIIlllIIll.put(lllllllllllllllllllllIIIIlllIlII[lllllllllllllllllllllIIIIllllIII]).put(lllllllllllllllllllllIIIIlllIlII[lllllllllllllllllllllIIIIllllIII + 1]).put(lllllllllllllllllllllIIIIlllIlII[lllllllllllllllllllllIIIIllllIII + 2]);
        }
    }
    
    private void copyRGBtoBGRA(final ByteBuffer lllllllllllllllllllllIIIlIlIIlII, final byte[] lllllllllllllllllllllIIIlIlIIIll) {
        if (this.transPixel != null) {
            final byte lllllllllllllllllllllIIIlIlIlIlI = this.transPixel[1];
            final byte lllllllllllllllllllllIIIlIlIlIIl = this.transPixel[3];
            final byte lllllllllllllllllllllIIIlIlIlIII = this.transPixel[5];
            for (int lllllllllllllllllllllIIIlIlIllII = 1, lllllllllllllllllllllIIIlIlIlIll = lllllllllllllllllllllIIIlIlIIIll.length; lllllllllllllllllllllIIIlIlIllII < lllllllllllllllllllllIIIlIlIlIll; lllllllllllllllllllllIIIlIlIllII += 3) {
                final byte lllllllllllllllllllllIIIlIllIIII = lllllllllllllllllllllIIIlIlIIIll[lllllllllllllllllllllIIIlIlIllII];
                final byte lllllllllllllllllllllIIIlIlIllll = lllllllllllllllllllllIIIlIlIIIll[lllllllllllllllllllllIIIlIlIllII + 1];
                final byte lllllllllllllllllllllIIIlIlIlllI = lllllllllllllllllllllIIIlIlIIIll[lllllllllllllllllllllIIIlIlIllII + 2];
                byte lllllllllllllllllllllIIIlIlIllIl = -1;
                if (lllllllllllllllllllllIIIlIllIIII == lllllllllllllllllllllIIIlIlIlIlI && lllllllllllllllllllllIIIlIlIllll == lllllllllllllllllllllIIIlIlIlIIl && lllllllllllllllllllllIIIlIlIlllI == lllllllllllllllllllllIIIlIlIlIII) {
                    lllllllllllllllllllllIIIlIlIllIl = 0;
                }
                lllllllllllllllllllllIIIlIlIIlII.put(lllllllllllllllllllllIIIlIlIlllI).put(lllllllllllllllllllllIIIlIlIllll).put(lllllllllllllllllllllIIIlIllIIII).put(lllllllllllllllllllllIIIlIlIllIl);
            }
        }
        else {
            for (int lllllllllllllllllllllIIIlIlIIlll = 1, lllllllllllllllllllllIIIlIlIIllI = lllllllllllllllllllllIIIlIlIIIll.length; lllllllllllllllllllllIIIlIlIIlll < lllllllllllllllllllllIIIlIlIIllI; lllllllllllllllllllllIIIlIlIIlll += 3) {
                lllllllllllllllllllllIIIlIlIIlII.put(lllllllllllllllllllllIIIlIlIIIll[lllllllllllllllllllllIIIlIlIIlll + 2]).put(lllllllllllllllllllllIIIlIlIIIll[lllllllllllllllllllllIIIlIlIIlll + 1]).put(lllllllllllllllllllllIIIlIlIIIll[lllllllllllllllllllllIIIlIlIIlll]).put((byte)(-1));
            }
        }
    }
    
    public Format decideTextureFormat(final Format lllllllllllllllllllllIIllIIllIll) {
        switch (this.colorType) {
            case 2: {
                if (lllllllllllllllllllllIIllIIllIll == PNGDecoder.ABGR || lllllllllllllllllllllIIllIIllIll == PNGDecoder.RGBA || lllllllllllllllllllllIIllIIllIll == PNGDecoder.BGRA || lllllllllllllllllllllIIllIIllIll == PNGDecoder.RGB) {
                    return lllllllllllllllllllllIIllIIllIll;
                }
                return PNGDecoder.RGB;
            }
            case 6: {
                if (lllllllllllllllllllllIIllIIllIll == PNGDecoder.ABGR || lllllllllllllllllllllIIllIIllIll == PNGDecoder.RGBA || lllllllllllllllllllllIIllIIllIll == PNGDecoder.BGRA || lllllllllllllllllllllIIllIIllIll == PNGDecoder.RGB) {
                    return lllllllllllllllllllllIIllIIllIll;
                }
                return PNGDecoder.RGBA;
            }
            case 0: {
                if (lllllllllllllllllllllIIllIIllIll == PNGDecoder.LUMINANCE || lllllllllllllllllllllIIllIIllIll == PNGDecoder.ALPHA) {
                    return lllllllllllllllllllllIIllIIllIll;
                }
                return PNGDecoder.LUMINANCE;
            }
            case 4: {
                return PNGDecoder.LUMINANCE_ALPHA;
            }
            case 3: {
                if (lllllllllllllllllllllIIllIIllIll == PNGDecoder.ABGR || lllllllllllllllllllllIIllIIllIll == PNGDecoder.RGBA || lllllllllllllllllllllIIllIIllIll == PNGDecoder.BGRA) {
                    return lllllllllllllllllllllIIllIIllIll;
                }
                return PNGDecoder.RGBA;
            }
            default: {
                throw new UnsupportedOperationException("Not yet implemented");
            }
        }
    }
    
    private void skip(long llllllllllllllllllllIlllIIIIIlII) throws IOException {
        while (llllllllllllllllllllIlllIIIIIlII > 0L) {
            final long llllllllllllllllllllIlllIIIIlIII = this.input.skip((long)llllllllllllllllllllIlllIIIIIlII);
            if (llllllllllllllllllllIlllIIIIlIII < 0L) {
                throw new EOFException();
            }
            llllllllllllllllllllIlllIIIIIlII -= llllllllllllllllllllIlllIIIIlIII;
        }
    }
    
    private void copy(final ByteBuffer lllllllllllllllllllllIIlIIlllIll, final byte[] lllllllllllllllllllllIIlIIlllIlI) {
        lllllllllllllllllllllIIlIIlllIll.put(lllllllllllllllllllllIIlIIlllIlI, 1, lllllllllllllllllllllIIlIIlllIlI.length - 1);
    }
    
    private void readtRNS() throws IOException {
        switch (this.colorType) {
            case 0: {
                this.checkChunkLength(2);
                this.transPixel = new byte[2];
                this.readChunk(this.transPixel, 0, 2);
                break;
            }
            case 2: {
                this.checkChunkLength(6);
                this.transPixel = new byte[6];
                this.readChunk(this.transPixel, 0, 6);
                break;
            }
            case 3: {
                if (this.palette == null) {
                    throw new IOException("tRNS chunk without PLTE chunk");
                }
                this.paletteA = new byte[this.palette.length / 3];
                Arrays.fill(this.paletteA, (byte)(-1));
                this.readChunk(this.paletteA, 0, this.paletteA.length);
                break;
            }
        }
    }
    
    private int readInt(final byte[] llllllllllllllllllllIlllIIIIllIl, final int llllllllllllllllllllIlllIIIIlllI) {
        return llllllllllllllllllllIlllIIIIllIl[llllllllllllllllllllIlllIIIIlllI] << 24 | (llllllllllllllllllllIlllIIIIllIl[llllllllllllllllllllIlllIIIIlllI + 1] & 0xFF) << 16 | (llllllllllllllllllllIlllIIIIllIl[llllllllllllllllllllIlllIIIIlllI + 2] & 0xFF) << 8 | (llllllllllllllllllllIlllIIIIllIl[llllllllllllllllllllIlllIIIIlllI + 3] & 0xFF);
    }
    
    public boolean isRGB() {
        return this.colorType == 6 || this.colorType == 2 || this.colorType == 3;
    }
    
    private void openChunk() throws IOException {
        this.readFully(this.buffer, 0, 8);
        this.chunkLength = this.readInt(this.buffer, 0);
        this.chunkType = this.readInt(this.buffer, 4);
        this.chunkRemaining = this.chunkLength;
        this.crc.reset();
        this.crc.update(this.buffer, 4, 4);
    }
    
    private void expand4(final byte[] llllllllllllllllllllIlllllllIlll, final byte[] llllllllllllllllllllIlllllllIllI) {
        int llllllllllllllllllllIllllllllIlI = 1;
        final int llllllllllllllllllllIllllllllIIl = llllllllllllllllllllIlllllllIllI.length;
        while (llllllllllllllllllllIllllllllIlI < llllllllllllllllllllIllllllllIIl) {
            final int llllllllllllllllllllIllllllllIll = llllllllllllllllllllIlllllllIlll[1 + (llllllllllllllllllllIllllllllIlI >> 1)] & 0xFF;
            switch (llllllllllllllllllllIllllllllIIl - llllllllllllllllllllIllllllllIlI) {
                default: {
                    llllllllllllllllllllIlllllllIllI[llllllllllllllllllllIllllllllIlI + 1] = (byte)(llllllllllllllllllllIllllllllIll & 0xF);
                }
                case 1: {
                    llllllllllllllllllllIlllllllIllI[llllllllllllllllllllIllllllllIlI] = (byte)(llllllllllllllllllllIllllllllIll >> 4);
                    llllllllllllllllllllIllllllllIlI += 2;
                    continue;
                }
            }
        }
    }
    
    private void readFully(final byte[] llllllllllllllllllllIlllIIIllIlI, int llllllllllllllllllllIlllIIIlIlIl, int llllllllllllllllllllIlllIIIlIlII) throws IOException {
        do {
            final int llllllllllllllllllllIlllIIIlllII = this.input.read(llllllllllllllllllllIlllIIIllIlI, llllllllllllllllllllIlllIIIlIlIl, (int)llllllllllllllllllllIlllIIIlIlII);
            if (llllllllllllllllllllIlllIIIlllII < 0) {
                throw new EOFException();
            }
            llllllllllllllllllllIlllIIIlIlIl += llllllllllllllllllllIlllIIIlllII;
            llllllllllllllllllllIlllIIIlIlII -= llllllllllllllllllllIlllIIIlllII;
        } while (llllllllllllllllllllIlllIIIlIlII > 0);
    }
    
    private void copyRGBAtoABGR(final ByteBuffer lllllllllllllllllllllIIIlIIIllIl, final byte[] lllllllllllllllllllllIIIlIIIllII) {
        for (int lllllllllllllllllllllIIIlIIlIIlI = 1, lllllllllllllllllllllIIIlIIlIIIl = lllllllllllllllllllllIIIlIIIllII.length; lllllllllllllllllllllIIIlIIlIIlI < lllllllllllllllllllllIIIlIIlIIIl; lllllllllllllllllllllIIIlIIlIIlI += 4) {
            lllllllllllllllllllllIIIlIIIllIl.put(lllllllllllllllllllllIIIlIIIllII[lllllllllllllllllllllIIIlIIlIIlI + 3]).put(lllllllllllllllllllllIIIlIIIllII[lllllllllllllllllllllIIIlIIlIIlI + 2]).put(lllllllllllllllllllllIIIlIIIllII[lllllllllllllllllllllIIIlIIlIIlI + 1]).put(lllllllllllllllllllllIIIlIIIllII[lllllllllllllllllllllIIIlIIlIIlI]);
        }
    }
    
    public boolean hasAlpha() {
        return this.colorType == 6 || this.paletteA != null || this.transPixel != null;
    }
    
    private void closeChunk() throws IOException {
        if (this.chunkRemaining > 0) {
            this.skip(this.chunkRemaining + 4);
        }
        else {
            this.readFully(this.buffer, 0, 4);
            final int llllllllllllllllllllIlllIlIllllI = this.readInt(this.buffer, 0);
            final int llllllllllllllllllllIlllIlIlllIl = (int)this.crc.getValue();
            if (llllllllllllllllllllIlllIlIlllIl != llllllllllllllllllllIlllIlIllllI) {
                throw new IOException("Invalid CRC");
            }
        }
        this.chunkRemaining = 0;
        this.chunkLength = 0;
        this.chunkType = 0;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    private void refillInflater(final Inflater llllllllllllllllllllIlllIIllIllI) throws IOException {
        while (this.chunkRemaining == 0) {
            this.closeChunk();
            this.openChunk(1229209940);
        }
        final int llllllllllllllllllllIlllIIlllIII = this.readChunk(this.buffer, 0, this.buffer.length);
        llllllllllllllllllllIlllIIllIllI.setInput(this.buffer, 0, llllllllllllllllllllIlllIIlllIII);
    }
    
    private void readPLTE() throws IOException {
        final int llllllllllllllllllllIlllIllIIlll = this.chunkLength / 3;
        if (llllllllllllllllllllIlllIllIIlll < 1 || llllllllllllllllllllIlllIllIIlll > 256 || this.chunkLength % 3 != 0) {
            throw new IOException("PLTE chunk has wrong length");
        }
        this.palette = new byte[llllllllllllllllllllIlllIllIIlll * 3];
        this.readChunk(this.palette, 0, this.palette.length);
    }
    
    private void copyRGBtoRGBA(final ByteBuffer lllllllllllllllllllllIIIllIIlllI, final byte[] lllllllllllllllllllllIIIllIIIllI) {
        if (this.transPixel != null) {
            final byte lllllllllllllllllllllIIIllIlIlIl = this.transPixel[1];
            final byte lllllllllllllllllllllIIIllIlIlII = this.transPixel[3];
            final byte lllllllllllllllllllllIIIllIlIIll = this.transPixel[5];
            for (int lllllllllllllllllllllIIIllIllIII = 1, lllllllllllllllllllllIIIllIlIlll = lllllllllllllllllllllIIIllIIIllI.length; lllllllllllllllllllllIIIllIllIII < lllllllllllllllllllllIIIllIlIlll; lllllllllllllllllllllIIIllIllIII += 3) {
                final byte lllllllllllllllllllllIIIllIlllIl = lllllllllllllllllllllIIIllIIIllI[lllllllllllllllllllllIIIllIllIII];
                final byte lllllllllllllllllllllIIIllIllIll = lllllllllllllllllllllIIIllIIIllI[lllllllllllllllllllllIIIllIllIII + 1];
                final byte lllllllllllllllllllllIIIllIllIlI = lllllllllllllllllllllIIIllIIIllI[lllllllllllllllllllllIIIllIllIII + 2];
                byte lllllllllllllllllllllIIIllIllIIl = -1;
                if (lllllllllllllllllllllIIIllIlllIl == lllllllllllllllllllllIIIllIlIlIl && lllllllllllllllllllllIIIllIllIll == lllllllllllllllllllllIIIllIlIlII && lllllllllllllllllllllIIIllIllIlI == lllllllllllllllllllllIIIllIlIIll) {
                    lllllllllllllllllllllIIIllIllIIl = 0;
                }
                lllllllllllllllllllllIIIllIIlllI.put(lllllllllllllllllllllIIIllIlllIl).put(lllllllllllllllllllllIIIllIllIll).put(lllllllllllllllllllllIIIllIllIlI).put(lllllllllllllllllllllIIIllIllIIl);
            }
        }
        else {
            for (int lllllllllllllllllllllIIIllIlIIlI = 1, lllllllllllllllllllllIIIllIlIIII = lllllllllllllllllllllIIIllIIIllI.length; lllllllllllllllllllllIIIllIlIIlI < lllllllllllllllllllllIIIllIlIIII; lllllllllllllllllllllIIIllIlIIlI += 3) {
                lllllllllllllllllllllIIIllIIlllI.put(lllllllllllllllllllllIIIllIIIllI[lllllllllllllllllllllIIIllIlIIlI]).put(lllllllllllllllllllllIIIllIIIllI[lllllllllllllllllllllIIIllIlIIlI + 1]).put(lllllllllllllllllllllIIIllIIIllI[lllllllllllllllllllllIIIllIlIIlI + 2]).put((byte)(-1));
            }
        }
    }
    
    private void checkChunkLength(final int llllllllllllllllllllIlllIlIIllII) throws IOException {
        if (this.chunkLength != llllllllllllllllllllIlllIlIIllII) {
            throw new IOException("Chunk has wrong size");
        }
    }
    
    public static class Format
    {
        final /* synthetic */ int numComponents;
        final /* synthetic */ boolean hasAlpha;
        
        private Format(final int llllllllllllllllllIIIIIIIlllIlII, final boolean llllllllllllllllllIIIIIIIlllIllI) {
            this.numComponents = llllllllllllllllllIIIIIIIlllIlII;
            this.hasAlpha = llllllllllllllllllIIIIIIIlllIllI;
        }
        
        public int getNumComponents() {
            return this.numComponents;
        }
        
        public boolean isHasAlpha() {
            return this.hasAlpha;
        }
    }
}
