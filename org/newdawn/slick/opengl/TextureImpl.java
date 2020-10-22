package org.newdawn.slick.opengl;

import org.newdawn.slick.opengl.renderer.*;
import org.lwjgl.*;
import java.nio.*;
import org.newdawn.slick.util.*;

public class TextureImpl implements Texture
{
    private /* synthetic */ int height;
    private /* synthetic */ boolean alpha;
    private /* synthetic */ int texHeight;
    private /* synthetic */ int textureID;
    static /* synthetic */ Texture lastBind;
    protected static /* synthetic */ SGL GL;
    private /* synthetic */ float heightRatio;
    private /* synthetic */ int texWidth;
    private /* synthetic */ float widthRatio;
    private /* synthetic */ int target;
    private /* synthetic */ String ref;
    private /* synthetic */ String cacheName;
    private /* synthetic */ int width;
    private /* synthetic */ ReloadData reloadData;
    
    public TextureImpl(final String llllllllllllllllIllIIIIllIIIIIIl, final int llllllllllllllllIllIIIIllIIIIIII, final int llllllllllllllllIllIIIIlIllllIll) {
        this.target = llllllllllllllllIllIIIIllIIIIIII;
        this.ref = llllllllllllllllIllIIIIllIIIIIIl;
        this.textureID = llllllllllllllllIllIIIIlIllllIll;
        TextureImpl.lastBind = this;
    }
    
    public void setWidth(final int llllllllllllllllIllIIIIlIlIllIlI) {
        this.width = llllllllllllllllIllIIIIlIlIllIlI;
        this.setWidth();
    }
    
    public void setHeight(final int llllllllllllllllIllIIIIlIllIIIII) {
        this.height = llllllllllllllllIllIIIIlIllIIIII;
        this.setHeight();
    }
    
    public static void bindNone() {
        TextureImpl.lastBind = null;
        TextureImpl.GL.glDisable(3553);
    }
    
    @Override
    public boolean hasAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(final boolean llllllllllllllllIllIIIIlIllIlIll) {
        this.alpha = llllllllllllllllIllIIIIlIllIlIll;
    }
    
    public static Texture getLastBind() {
        return TextureImpl.lastBind;
    }
    
    @Override
    public void bind() {
        if (TextureImpl.lastBind != this) {
            TextureImpl.lastBind = this;
            TextureImpl.GL.glEnable(3553);
            TextureImpl.GL.glBindTexture(this.target, this.textureID);
        }
    }
    
    @Override
    public int getTextureWidth() {
        return this.texWidth;
    }
    
    static {
        TextureImpl.GL = Renderer.get();
    }
    
    @Override
    public String getTextureRef() {
        return this.ref;
    }
    
    public void setCacheName(final String llllllllllllllllIllIIIIlIlllIlll) {
        this.cacheName = llllllllllllllllIllIIIIlIlllIlll;
    }
    
    @Override
    public void setTextureFilter(final int llllllllllllllllIllIIIIlIIIlIIll) {
        this.bind();
        TextureImpl.GL.glTexParameteri(this.target, 10241, llllllllllllllllIllIIIIlIIIlIIll);
        TextureImpl.GL.glTexParameteri(this.target, 10240, llllllllllllllllIllIIIIlIIIlIIll);
    }
    
    public void setTextureData(final int llllllllllllllllIllIIIIlIIIIIIll, final int llllllllllllllllIllIIIIlIIIIIIlI, final int llllllllllllllllIllIIIIlIIIIIlll, final int llllllllllllllllIllIIIIlIIIIIIII, final ByteBuffer llllllllllllllllIllIIIIIllllllll) {
        this.reloadData = new ReloadData();
        this.reloadData.srcPixelFormat = llllllllllllllllIllIIIIlIIIIIIll;
        this.reloadData.componentCount = llllllllllllllllIllIIIIlIIIIIIlI;
        this.reloadData.minFilter = llllllllllllllllIllIIIIlIIIIIlll;
        this.reloadData.magFilter = llllllllllllllllIllIIIIlIIIIIIII;
        this.reloadData.textureBuffer = llllllllllllllllIllIIIIIllllllll;
    }
    
    @Override
    public int getImageWidth() {
        return this.width;
    }
    
    private void setWidth() {
        if (this.texWidth != 0) {
            this.widthRatio = this.width / (float)this.texWidth;
        }
    }
    
    protected TextureImpl() {
    }
    
    public void setTextureID(final int llllllllllllllllIllIIIIlIIlIIlll) {
        this.textureID = llllllllllllllllIllIIIIlIIlIIlll;
    }
    
    @Override
    public float getHeight() {
        return this.heightRatio;
    }
    
    public void reload() {
        if (this.reloadData != null) {
            this.textureID = this.reloadData.reload();
        }
    }
    
    @Override
    public void release() {
        final IntBuffer llllllllllllllllIllIIIIlIIllIIlI = this.createIntBuffer(1);
        llllllllllllllllIllIIIIlIIllIIlI.put(this.textureID);
        llllllllllllllllIllIIIIlIIllIIlI.flip();
        TextureImpl.GL.glDeleteTextures(llllllllllllllllIllIIIIlIIllIIlI);
        if (TextureImpl.lastBind == this) {
            bindNone();
        }
        if (this.cacheName != null) {
            InternalTextureLoader.get().clear(this.cacheName);
        }
        else {
            InternalTextureLoader.get().clear(this.ref);
        }
    }
    
    @Override
    public byte[] getTextureData() {
        final ByteBuffer llllllllllllllllIllIIIIlIIIllIll = BufferUtils.createByteBuffer((this.hasAlpha() ? 4 : 3) * this.texWidth * this.texHeight);
        this.bind();
        TextureImpl.GL.glGetTexImage(3553, 0, this.hasAlpha() ? 6408 : 6407, 5121, llllllllllllllllIllIIIIlIIIllIll);
        final byte[] llllllllllllllllIllIIIIlIIIllIlI = new byte[llllllllllllllllIllIIIIlIIIllIll.limit()];
        llllllllllllllllIllIIIIlIIIllIll.get(llllllllllllllllIllIIIIlIIIllIlI);
        llllllllllllllllIllIIIIlIIIllIll.clear();
        return llllllllllllllllIllIIIIlIIIllIlI;
    }
    
    private void setHeight() {
        if (this.texHeight != 0) {
            this.heightRatio = this.height / (float)this.texHeight;
        }
    }
    
    @Override
    public int getImageHeight() {
        return this.height;
    }
    
    @Override
    public int getTextureHeight() {
        return this.texHeight;
    }
    
    protected IntBuffer createIntBuffer(final int llllllllllllllllIllIIIIlIIlIIIll) {
        final ByteBuffer llllllllllllllllIllIIIIlIIlIIIlI = ByteBuffer.allocateDirect(4 * llllllllllllllllIllIIIIlIIlIIIll);
        llllllllllllllllIllIIIIlIIlIIIlI.order(ByteOrder.nativeOrder());
        return llllllllllllllllIllIIIIlIIlIIIlI.asIntBuffer();
    }
    
    @Override
    public float getWidth() {
        return this.widthRatio;
    }
    
    @Override
    public int getTextureID() {
        return this.textureID;
    }
    
    public void setTextureWidth(final int llllllllllllllllIllIIIIlIIlllllI) {
        this.texWidth = llllllllllllllllIllIIIIlIIlllllI;
        this.setWidth();
    }
    
    public static void unbind() {
        TextureImpl.lastBind = null;
    }
    
    public void setTextureHeight(final int llllllllllllllllIllIIIIlIlIIIIlI) {
        this.texHeight = llllllllllllllllIllIIIIlIlIIIIlI;
        this.setHeight();
    }
    
    private class ReloadData
    {
        private /* synthetic */ int magFilter;
        private /* synthetic */ int minFilter;
        private /* synthetic */ ByteBuffer textureBuffer;
        private /* synthetic */ int componentCount;
        private /* synthetic */ int srcPixelFormat;
        
        public int reload() {
            Log.error(String.valueOf(new StringBuilder().append("Reloading texture: ").append(TextureImpl.this.ref)));
            return InternalTextureLoader.get().reload(TextureImpl.this, this.srcPixelFormat, this.componentCount, this.minFilter, this.magFilter, this.textureBuffer);
        }
    }
}
