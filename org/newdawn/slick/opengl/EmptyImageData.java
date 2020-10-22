package org.newdawn.slick.opengl;

import java.nio.*;
import org.lwjgl.*;

public class EmptyImageData implements ImageData
{
    private /* synthetic */ int height;
    private /* synthetic */ int width;
    
    @Override
    public ByteBuffer getImageBufferData() {
        return BufferUtils.createByteBuffer(this.getTexWidth() * this.getTexHeight() * 4);
    }
    
    @Override
    public int getDepth() {
        return 32;
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public int getTexHeight() {
        return InternalTextureLoader.get2Fold(this.height);
    }
    
    @Override
    public int getTexWidth() {
        return InternalTextureLoader.get2Fold(this.width);
    }
    
    public EmptyImageData(final int lllllllllllllllllIlIIlIIlllIllIl, final int lllllllllllllllllIlIIlIIlllIlIIl) {
        this.width = lllllllllllllllllIlIIlIIlllIllIl;
        this.height = lllllllllllllllllIlIIlIIlllIlIIl;
    }
}
