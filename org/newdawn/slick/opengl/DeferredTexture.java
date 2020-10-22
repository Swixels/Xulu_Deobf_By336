package org.newdawn.slick.opengl;

import org.newdawn.slick.loading.*;
import java.io.*;

public class DeferredTexture extends TextureImpl implements DeferredResource
{
    private /* synthetic */ int[] trans;
    private /* synthetic */ InputStream in;
    private /* synthetic */ String resourceName;
    private /* synthetic */ TextureImpl target;
    private /* synthetic */ boolean flipped;
    private /* synthetic */ int filter;
    
    @Override
    public int getImageHeight() {
        this.checkTarget();
        return this.target.getImageHeight();
    }
    
    @Override
    public void setTextureID(final int llIlIlIIllIllI) {
        this.checkTarget();
        this.target.setTextureID(llIlIlIIllIllI);
    }
    
    @Override
    public void setWidth(final int llIlIlIIlIllII) {
        this.checkTarget();
        this.target.setWidth(llIlIlIIlIllII);
    }
    
    @Override
    public void setHeight(final int llIlIlIlIIIlII) {
        this.checkTarget();
        this.target.setHeight(llIlIlIlIIIlII);
    }
    
    @Override
    public void setAlpha(final boolean llIlIlIlIIlIII) {
        this.checkTarget();
        this.target.setAlpha(llIlIlIlIIlIII);
    }
    
    @Override
    public String getTextureRef() {
        this.checkTarget();
        return this.target.getTextureRef();
    }
    
    @Override
    public int getImageWidth() {
        this.checkTarget();
        return this.target.getImageWidth();
    }
    
    @Override
    public byte[] getTextureData() {
        this.checkTarget();
        return this.target.getTextureData();
    }
    
    @Override
    public String getDescription() {
        return this.resourceName;
    }
    
    @Override
    public void setTextureHeight(final int llIlIlIIllllII) {
        this.checkTarget();
        this.target.setTextureHeight(llIlIlIIllllII);
    }
    
    public DeferredTexture(final InputStream llIlIlIlllllII, final String llIlIllIIIIIIl, final boolean llIlIllIIIIIII, final int llIlIlIlllllll, final int[] llIlIlIllllIII) {
        this.in = llIlIlIlllllII;
        this.resourceName = llIlIllIIIIIIl;
        this.flipped = llIlIllIIIIIII;
        this.filter = llIlIlIlllllll;
        this.trans = llIlIlIllllIII;
        LoadingList.get().add(this);
    }
    
    @Override
    public void setTextureWidth(final int llIlIlIIllIIII) {
        this.checkTarget();
        this.target.setTextureWidth(llIlIlIIllIIII);
    }
    
    @Override
    public void release() {
        this.checkTarget();
        this.target.release();
    }
    
    @Override
    public int getTextureID() {
        this.checkTarget();
        return this.target.getTextureID();
    }
    
    @Override
    public boolean hasAlpha() {
        this.checkTarget();
        return this.target.hasAlpha();
    }
    
    @Override
    public int getTextureWidth() {
        this.checkTarget();
        return this.target.getTextureWidth();
    }
    
    @Override
    public float getWidth() {
        this.checkTarget();
        return this.target.getWidth();
    }
    
    @Override
    public float getHeight() {
        this.checkTarget();
        return this.target.getHeight();
    }
    
    @Override
    public void setTextureFilter(final int llIlIlIIIlllIl) {
        this.checkTarget();
        this.target.setTextureFilter(llIlIlIIIlllIl);
    }
    
    private void checkTarget() {
        if (this.target == null) {
            try {
                this.load();
                LoadingList.get().remove(this);
            }
            catch (IOException llIlIlIllIllll) {
                throw new RuntimeException(String.valueOf(new StringBuilder().append("Attempt to use deferred texture before loading and resource not found: ").append(this.resourceName)));
            }
        }
    }
    
    @Override
    public void load() throws IOException {
        final boolean llIlIlIlllIlII = InternalTextureLoader.get().isDeferredLoading();
        InternalTextureLoader.get().setDeferredLoading(false);
        this.target = InternalTextureLoader.get().getTexture(this.in, this.resourceName, this.flipped, this.filter, this.trans);
        InternalTextureLoader.get().setDeferredLoading(llIlIlIlllIlII);
    }
    
    @Override
    public void bind() {
        this.checkTarget();
        this.target.bind();
    }
    
    @Override
    public int getTextureHeight() {
        this.checkTarget();
        return this.target.getTextureHeight();
    }
}
