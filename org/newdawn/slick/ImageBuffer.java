package org.newdawn.slick;

import org.newdawn.slick.opengl.*;
import org.lwjgl.*;
import java.nio.*;

public class ImageBuffer implements ImageData
{
    private /* synthetic */ int texWidth;
    private /* synthetic */ int width;
    private /* synthetic */ int height;
    private /* synthetic */ byte[] rawData;
    private /* synthetic */ int texHeight;
    
    public Image getImage(final int lllllllllllllllllIlIIlIIlIIllIIl) {
        return new Image(this, lllllllllllllllllIlIIlIIlIIllIIl);
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    public byte[] getRGBA() {
        return this.rawData;
    }
    
    public ImageBuffer(final int lllllllllllllllllIlIIlIIllIlIIIl, final int lllllllllllllllllIlIIlIIllIlIIII) {
        this.width = lllllllllllllllllIlIIlIIllIlIIIl;
        this.height = lllllllllllllllllIlIIlIIllIlIIII;
        this.texWidth = this.get2Fold(lllllllllllllllllIlIIlIIllIlIIIl);
        this.texHeight = this.get2Fold(lllllllllllllllllIlIIlIIllIlIIII);
        this.rawData = new byte[this.texWidth * this.texHeight * 4];
    }
    
    @Override
    public int getTexWidth() {
        return this.texWidth;
    }
    
    @Override
    public int getDepth() {
        return 32;
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        final ByteBuffer lllllllllllllllllIlIIlIIlIllllII = BufferUtils.createByteBuffer(this.rawData.length);
        lllllllllllllllllIlIIlIIlIllllII.put(this.rawData);
        lllllllllllllllllIlIIlIIlIllllII.flip();
        return lllllllllllllllllIlIIlIIlIllllII;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    public void setRGBA(final int lllllllllllllllllIlIIlIIlIlIlIII, final int lllllllllllllllllIlIIlIIlIlIllll, final int lllllllllllllllllIlIIlIIlIlIIllI, final int lllllllllllllllllIlIIlIIlIlIllIl, final int lllllllllllllllllIlIIlIIlIlIllII, final int lllllllllllllllllIlIIlIIlIlIIIll) {
        if (lllllllllllllllllIlIIlIIlIlIlIII < 0 || lllllllllllllllllIlIIlIIlIlIlIII >= this.width || lllllllllllllllllIlIIlIIlIlIllll < 0 || lllllllllllllllllIlIIlIIlIlIllll >= this.height) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Specified location: ").append(lllllllllllllllllIlIIlIIlIlIlIII).append(",").append(lllllllllllllllllIlIIlIIlIlIllll).append(" outside of image")));
        }
        final int lllllllllllllllllIlIIlIIlIlIlIlI = (lllllllllllllllllIlIIlIIlIlIlIII + lllllllllllllllllIlIIlIIlIlIllll * this.texWidth) * 4;
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            this.rawData[lllllllllllllllllIlIIlIIlIlIlIlI] = (byte)lllllllllllllllllIlIIlIIlIlIllII;
            this.rawData[lllllllllllllllllIlIIlIIlIlIlIlI + 1] = (byte)lllllllllllllllllIlIIlIIlIlIllIl;
            this.rawData[lllllllllllllllllIlIIlIIlIlIlIlI + 2] = (byte)lllllllllllllllllIlIIlIIlIlIIllI;
            this.rawData[lllllllllllllllllIlIIlIIlIlIlIlI + 3] = (byte)lllllllllllllllllIlIIlIIlIlIIIll;
        }
        else {
            this.rawData[lllllllllllllllllIlIIlIIlIlIlIlI] = (byte)lllllllllllllllllIlIIlIIlIlIIllI;
            this.rawData[lllllllllllllllllIlIIlIIlIlIlIlI + 1] = (byte)lllllllllllllllllIlIIlIIlIlIllIl;
            this.rawData[lllllllllllllllllIlIIlIIlIlIlIlI + 2] = (byte)lllllllllllllllllIlIIlIIlIlIllII;
            this.rawData[lllllllllllllllllIlIIlIIlIlIlIlI + 3] = (byte)lllllllllllllllllIlIIlIIlIlIIIll;
        }
    }
    
    public Image getImage() {
        return new Image(this);
    }
    
    @Override
    public int getTexHeight() {
        return this.texHeight;
    }
    
    private int get2Fold(final int lllllllllllllllllIlIIlIIlIIlIlIl) {
        int lllllllllllllllllIlIIlIIlIIlIlII;
        for (lllllllllllllllllIlIIlIIlIIlIlII = 2; lllllllllllllllllIlIIlIIlIIlIlII < lllllllllllllllllIlIIlIIlIIlIlIl; lllllllllllllllllIlIIlIIlIIlIlII *= 2) {}
        return lllllllllllllllllIlIIlIIlIIlIlII;
    }
}
