package org.newdawn.slick;

import java.io.*;
import org.newdawn.slick.util.*;
import org.lwjgl.*;
import java.nio.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;

public class BigImage extends Image
{
    private /* synthetic */ Image[][] images;
    private static /* synthetic */ Image lastBind;
    private /* synthetic */ int ycount;
    private /* synthetic */ int realHeight;
    private /* synthetic */ int realWidth;
    private /* synthetic */ int xcount;
    protected static /* synthetic */ SGL GL;
    
    public BigImage(final String lllIlIIIllII, final int lllIlIIIllll, final int lllIlIIIlIlI) throws SlickException {
        this.build(lllIlIIIllII, lllIlIIIllll, lllIlIIIlIlI);
    }
    
    private void build(final String lllIIlIlIlll, final int lllIIlIllIlI, final int lllIIlIllIIl) throws SlickException {
        try {
            final LoadableImageData lllIIlIlllll = ImageDataFactory.getImageDataFor(lllIIlIlIlll);
            final ByteBuffer lllIIlIllllI = lllIIlIlllll.loadImage(ResourceLoader.getResourceAsStream(lllIIlIlIlll), false, null);
            this.build(lllIIlIlllll, lllIIlIllllI, lllIIlIllIlI, lllIIlIllIIl);
        }
        catch (IOException lllIIlIlllIl) {
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load: ").append(lllIIlIlIlll)), lllIIlIlllIl);
        }
    }
    
    @Override
    public void drawFlash(final float llIlIIIlIlIl, final float llIlIIIlIlII) {
        this.drawFlash(llIlIIIlIlIl, llIlIIIlIlII, (float)this.width, (float)this.height);
    }
    
    @Override
    public void draw(final float llIlIllIIllI, final float llIlIllIIIlI) {
        this.draw(llIlIllIIllI, llIlIllIIIlI, Color.white);
    }
    
    @Override
    public Image getScaledCopy(final float llIIlllIlllI) {
        return this.getScaledCopy((int)(llIIlllIlllI * this.width), (int)(llIIlllIlllI * this.height));
    }
    
    @Override
    public Image getScaledCopy(final int llIIlllIIIlI, final int llIIlllIIlIl) {
        final BigImage llIIlllIIlII = new BigImage();
        llIIlllIIlII.images = this.images;
        llIIlllIIlII.xcount = this.xcount;
        llIIlllIIlII.ycount = this.ycount;
        llIIlllIIlII.width = llIIlllIIIlI;
        llIIlllIIlII.height = llIIlllIIlIl;
        llIIlllIIlII.realWidth = this.realWidth;
        llIIlllIIlII.realHeight = this.realHeight;
        return llIIlllIIlII;
    }
    
    @Override
    protected void reinit() {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    @Override
    public void draw(final float llIllIlllIll, final float llIllIlIllII, final float llIllIlIlIll, final float llIllIlllIII, final float llIllIlIlIIl, final float llIllIlIlIII, final float llIllIllIlIl, final float llIllIlIIllI) {
        final int llIllIllIIll = (int)(llIllIllIlIl - llIllIlIlIIl);
        final int llIllIllIIlI = (int)(llIllIlIIllI - llIllIlIlIII);
        final Image llIllIllIIIl = this.getSubImage((int)llIllIlIlIIl, (int)llIllIlIlIII, llIllIllIIll, llIllIllIIlI);
        final int llIllIllIIII = (int)(llIllIlIlIll - llIllIlllIll);
        final int llIllIlIllll = (int)(llIllIlllIII - llIllIlIllII);
        llIllIllIIIl.draw(llIllIlllIll, llIllIlIllII, (float)llIllIllIIII, (float)llIllIlIllll);
    }
    
    public static final int getMaxSingleImageSize() {
        final IntBuffer lllIlIlIlIIl = BufferUtils.createIntBuffer(16);
        BigImage.GL.glGetInteger(3379, lllIlIlIlIIl);
        return lllIlIlIlIIl.get(0);
    }
    
    private BigImage() {
        this.inited = true;
    }
    
    @Override
    public Image copy() {
        throw new OperationNotSupportedException("Can't copy big images yet");
    }
    
    @Override
    public void draw(final float llIlIllIllIl, final float llIlIllIllII, final float llIlIllIlIll) {
        this.draw(llIlIllIllIl, llIlIllIllII, llIlIllIlIll, Color.white);
    }
    
    @Override
    public void draw(final float llIllllIIIII, final float llIlllIlIlIl, final float llIlllIllllI, final float llIlllIlIIll, final Color llIlllIlIIlI) {
        final float llIlllIllIll = llIlllIllllI / this.realWidth;
        final float llIlllIllIlI = llIlllIlIIll / this.realHeight;
        BigImage.GL.glTranslatef(llIllllIIIII, llIlllIlIlIl, 0.0f);
        BigImage.GL.glScalef(llIlllIllIll, llIlllIllIlI, 1.0f);
        float llIlllIllIIl = 0.0f;
        float llIlllIllIII = 0.0f;
        for (int llIllllIIIlI = 0; llIllllIIIlI < this.xcount; ++llIllllIIIlI) {
            llIlllIllIII = 0.0f;
            for (int llIllllIIIll = 0; llIllllIIIll < this.ycount; ++llIllllIIIll) {
                final Image llIllllIIlII = this.images[llIllllIIIlI][llIllllIIIll];
                llIllllIIlII.draw(llIlllIllIIl, llIlllIllIII, (float)llIllllIIlII.getWidth(), (float)llIllllIIlII.getHeight(), llIlllIlIIlI);
                llIlllIllIII += llIllllIIlII.getHeight();
                if (llIllllIIIll == this.ycount - 1) {
                    llIlllIllIIl += llIllllIIlII.getWidth();
                }
            }
        }
        BigImage.GL.glScalef(1.0f / llIlllIllIll, 1.0f / llIlllIllIlI, 1.0f);
        BigImage.GL.glTranslatef(-llIllllIIIII, -llIlllIlIlIl, 0.0f);
    }
    
    @Override
    public void draw() {
        this.draw(0.0f, 0.0f);
    }
    
    @Override
    public Image getFlippedCopy(final boolean llIIlllllIII, final boolean llIIlllllIll) {
        final BigImage llIIlllllIlI = new BigImage();
        llIIlllllIlI.images = this.images;
        llIIlllllIlI.xcount = this.xcount;
        llIIlllllIlI.ycount = this.ycount;
        llIIlllllIlI.width = this.width;
        llIIlllllIlI.height = this.height;
        llIIlllllIlI.realWidth = this.realWidth;
        llIIlllllIlI.realHeight = this.realHeight;
        if (llIIlllllIII) {
            final Image[][] llIlIIIIIIIl = llIIlllllIlI.images;
            llIIlllllIlI.images = new Image[this.xcount][this.ycount];
            for (int llIlIIIIIIlI = 0; llIlIIIIIIlI < this.xcount; ++llIlIIIIIIlI) {
                for (int llIlIIIIIIll = 0; llIlIIIIIIll < this.ycount; ++llIlIIIIIIll) {
                    llIIlllllIlI.images[llIlIIIIIIlI][llIlIIIIIIll] = llIlIIIIIIIl[this.xcount - 1 - llIlIIIIIIlI][llIlIIIIIIll].getFlippedCopy(true, false);
                }
            }
        }
        if (llIIlllllIll) {
            final Image[][] llIIlllllllI = llIIlllllIlI.images;
            llIIlllllIlI.images = new Image[this.xcount][this.ycount];
            for (int llIIllllllll = 0; llIIllllllll < this.xcount; ++llIIllllllll) {
                for (int llIlIIIIIIII = 0; llIlIIIIIIII < this.ycount; ++llIlIIIIIIII) {
                    llIIlllllIlI.images[llIIllllllll][llIlIIIIIIII] = llIIlllllllI[llIIllllllll][this.ycount - 1 - llIlIIIIIIII].getFlippedCopy(false, true);
                }
            }
        }
        return llIIlllllIlI;
    }
    
    @Override
    public Texture getTexture() {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    @Override
    public void endUse() {
        if (BigImage.lastBind != null) {
            BigImage.lastBind.endUse();
        }
        BigImage.lastBind = null;
    }
    
    @Override
    public void destroy() throws SlickException {
        for (int llIIIllllIIl = 0; llIIIllllIIl < this.xcount; ++llIIIllllIIl) {
            for (int llIIIllllIlI = 0; llIIIllllIlI < this.ycount; ++llIIIllllIlI) {
                final Image llIIIllllIll = this.images[llIIIllllIIl][llIIIllllIlI];
                llIIIllllIll.destroy();
            }
        }
    }
    
    @Override
    public void setTexture(final Texture llIIlIIlIIII) {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    @Override
    public void draw(final float llIIIlIIlIIl, final float llIIIlIllIII, final float llIIIlIlIlll, final float llIIIlIlIlIl, final float llIIIlIlIlII, final float llIIIlIlIIll, final float llIIIlIIIIll, final float llIIIlIlIIIl, final Color llIIIlIlIIII) {
        final int llIIIlIIllll = (int)(llIIIlIIIIll - llIIIlIlIlII);
        final int llIIIlIIlllI = (int)(llIIIlIlIIIl - llIIIlIlIIll);
        final Image llIIIlIIllIl = this.getSubImage((int)llIIIlIlIlII, (int)llIIIlIlIIll, llIIIlIIllll, llIIIlIIlllI);
        final int llIIIlIIllII = (int)(llIIIlIlIlll - llIIIlIIlIIl);
        final int llIIIlIIlIll = (int)(llIIIlIlIlIl - llIIIlIllIII);
        llIIIlIIllIl.draw(llIIIlIIlIIl, llIIIlIllIII, (float)llIIIlIIllII, (float)llIIIlIIlIll, llIIIlIlIIII);
    }
    
    public BigImage(final String lllIlIIlllll) throws SlickException {
        this(lllIlIIlllll, 2);
    }
    
    @Override
    public void drawEmbedded(final float llIIIIllIllI, final float llIIIIllIlIl, final float llIIIIllIlII, final float llIIIIllIIlI, final float llIIIIllIIII, final float llIIIIlIllll, final float llIIIIlIlllI, final float llIIIIlIllIl, final Color llIIIIlIlIll) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void drawCentered(final float llIIIIlllIlI, final float llIIIIlllIIl) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void draw(final float llIlIllllIlI, final float llIlIllllIIl, final float llIlIllllIII, final float llIlIlllIlll) {
        this.draw(llIlIllllIlI, llIlIllllIIl, llIlIllllIII, llIlIlllIlll, Color.white);
    }
    
    public int getVerticalImageCount() {
        return this.ycount;
    }
    
    @Override
    public void draw(final float llIllllllIlI, final float llIlllllIlII, final float llIllllllIII, final Color llIlllllIIlI) {
        this.draw(llIllllllIlI, llIlllllIlII, this.width * llIllllllIII, this.height * llIllllllIII, llIlllllIIlI);
    }
    
    @Override
    protected void initImpl() {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    @Override
    public Graphics getGraphics() throws SlickException {
        throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
    }
    
    public BigImage(final LoadableImageData lllIIlllIIlI, final ByteBuffer lllIIlllIllI, final int lllIIlllIlIl, final int lllIIllIllll) {
        this.build(lllIIlllIIlI, lllIIlllIllI, lllIIlllIlIl, lllIIllIllll);
    }
    
    private void build(final LoadableImageData lllIIIlIllIl, final ByteBuffer lllIIIlIIlII, final int lllIIIlIIIll, final int lllIIIlIlIlI) {
        final int lllIIIlIlIIl = lllIIIlIllIl.getTexWidth();
        final int lllIIIlIlIII = lllIIIlIllIl.getTexHeight();
        final int width = lllIIIlIllIl.getWidth();
        this.width = width;
        this.realWidth = width;
        final int height = lllIIIlIllIl.getHeight();
        this.height = height;
        this.realHeight = height;
        if (lllIIIlIlIIl <= lllIIIlIlIlI && lllIIIlIlIII <= lllIIIlIlIlI) {
            this.images = new Image[1][1];
            final ImageData lllIIIllllIl = new ImageData() {
                @Override
                public ByteBuffer getImageBufferData() {
                    return lllIIIlIIlII;
                }
                
                @Override
                public int getWidth() {
                    return lllIIIlIlIIl;
                }
                
                @Override
                public int getTexHeight() {
                    return lllIIIlIlIII;
                }
                
                @Override
                public int getHeight() {
                    return lllIIIlIlIII;
                }
                
                @Override
                public int getDepth() {
                    return lllIIIlIllIl.getDepth();
                }
                
                @Override
                public int getTexWidth() {
                    return lllIIIlIlIIl;
                }
            };
            this.images[0][0] = new Image(lllIIIllllIl, lllIIIlIIIll);
            this.xcount = 1;
            this.ycount = 1;
            this.inited = true;
            return;
        }
        this.xcount = (this.realWidth - 1) / lllIIIlIlIlI + 1;
        this.ycount = (this.realHeight - 1) / lllIIIlIlIlI + 1;
        this.images = new Image[this.xcount][this.ycount];
        final int lllIIIlIIlll = lllIIIlIllIl.getDepth() / 8;
        for (int lllIIIlIllll = 0; lllIIIlIllll < this.xcount; ++lllIIIlIllll) {
            for (int lllIIIllIIII = 0; lllIIIllIIII < this.ycount; ++lllIIIllIIII) {
                final int lllIIIlllIlI = (lllIIIlIllll + 1) * lllIIIlIlIlI;
                final int lllIIIlllIIl = (lllIIIllIIII + 1) * lllIIIlIlIlI;
                final int lllIIIlllIII = Math.min(this.realWidth - lllIIIlIllll * lllIIIlIlIlI, lllIIIlIlIlI);
                final int lllIIIllIlll = Math.min(this.realHeight - lllIIIllIIII * lllIIIlIlIlI, lllIIIlIlIlI);
                final int lllIIIllIllI = lllIIIlIlIlI;
                final int lllIIIllIlIl = lllIIIlIlIlI;
                final ByteBuffer lllIIIllIlII = BufferUtils.createByteBuffer(lllIIIlIlIlI * lllIIIlIlIlI * lllIIIlIIlll);
                final int lllIIIllIIll = lllIIIlIllll * lllIIIlIlIlI * lllIIIlIIlll;
                final byte[] lllIIIllIIlI = new byte[lllIIIllIllI * lllIIIlIIlll];
                for (int lllIIIlllIll = 0; lllIIIlllIll < lllIIIllIlIl; ++lllIIIlllIll) {
                    final int lllIIIllllII = (lllIIIllIIII * lllIIIlIlIlI + lllIIIlllIll) * lllIIIlIlIIl * lllIIIlIIlll;
                    lllIIIlIIlII.position(lllIIIllllII + lllIIIllIIll);
                    lllIIIlIIlII.get(lllIIIllIIlI, 0, lllIIIllIllI * lllIIIlIIlll);
                    lllIIIllIlII.put(lllIIIllIIlI);
                }
                lllIIIllIlII.flip();
                final ImageData lllIIIllIIIl = new ImageData() {
                    @Override
                    public ByteBuffer getImageBufferData() {
                        return lllIIIllIlII;
                    }
                    
                    @Override
                    public int getTexWidth() {
                        return lllIIIllIllI;
                    }
                    
                    @Override
                    public int getWidth() {
                        return lllIIIlllIII;
                    }
                    
                    @Override
                    public int getDepth() {
                        return lllIIIlIllIl.getDepth();
                    }
                    
                    @Override
                    public int getTexHeight() {
                        return lllIIIllIlIl;
                    }
                    
                    @Override
                    public int getHeight() {
                        return lllIIIllIlll;
                    }
                };
                this.images[lllIIIlIllll][lllIIIllIIII] = new Image(lllIIIllIIIl, lllIIIlIIIll);
            }
        }
        this.inited = true;
    }
    
    public int getHorizontalImageCount() {
        return this.xcount;
    }
    
    @Override
    public String toString() {
        return "[BIG IMAGE]";
    }
    
    @Override
    public void draw(final float llIllIIlIllI, final float llIllIIlIlIl, final float llIllIIIlIll, final float llIllIIIlIlI, final float llIllIIIlIIl, final float llIllIIlIIIl) {
        final int llIllIIlIIII = (int)(llIllIIIlIIl - llIllIIIlIll);
        final int llIllIIIllll = (int)(llIllIIlIIIl - llIllIIIlIlI);
        this.draw(llIllIIlIllI, llIllIIlIlIl, (float)llIllIIlIIII, (float)llIllIIIllll, llIllIIIlIll, llIllIIIlIlI, llIllIIIlIIl, llIllIIlIIIl);
    }
    
    @Override
    public void ensureInverted() {
        throw new OperationNotSupportedException("Doesn't make sense for tiled operations");
    }
    
    public Image getSubImage(final int llIIlIIIlIII, final int llIIlIIIIlll) {
        return this.images[llIIlIIIlIII][llIIlIIIIlll];
    }
    
    @Override
    public void startUse() {
    }
    
    @Override
    public Color getColor(final int llIlIIIIllII, final int llIlIIIIlIll) {
        throw new OperationNotSupportedException("Can't use big images as buffers");
    }
    
    @Override
    public void bind() {
        throw new OperationNotSupportedException("Can't bind big images yet");
    }
    
    @Override
    public Image getSubImage(final int llIIlIlIllII, final int llIIlIlIlIll, final int llIIlIllIlll, final int llIIlIlIlIIl) {
        final BigImage llIIlIllIlIl = new BigImage();
        llIIlIllIlIl.width = llIIlIllIlll;
        llIIlIllIlIl.height = llIIlIlIlIIl;
        llIIlIllIlIl.realWidth = llIIlIllIlll;
        llIIlIllIlIl.realHeight = llIIlIlIlIIl;
        llIIlIllIlIl.images = new Image[this.xcount][this.ycount];
        float llIIlIllIlII = 0.0f;
        float llIIlIllIIll = 0.0f;
        final int llIIlIllIIlI = llIIlIlIllII + llIIlIllIlll;
        final int llIIlIllIIIl = llIIlIlIlIll + llIIlIlIlIIl;
        int llIIlIllIIII = 0;
        int llIIlIlIllll = 0;
        boolean llIIlIlIlllI = false;
        for (int llIIlIlllIll = 0; llIIlIlllIll < this.xcount; ++llIIlIlllIll) {
            llIIlIllIIll = 0.0f;
            llIIlIlIllll = 0;
            llIIlIlIlllI = false;
            for (int llIIlIllllII = 0; llIIlIllllII < this.ycount; ++llIIlIllllII) {
                final Image llIIllIIIlIl = this.images[llIIlIlllIll][llIIlIllllII];
                final int llIIllIIIlII = (int)(llIIlIllIlII + llIIllIIIlIl.getWidth());
                final int llIIllIIIIll = (int)(llIIlIllIIll + llIIllIIIlIl.getHeight());
                final int llIIllIIIIlI = (int)Math.max((float)llIIlIlIllII, llIIlIllIlII);
                final int llIIllIIIIIl = (int)Math.max((float)llIIlIlIlIll, llIIlIllIIll);
                final int llIIllIIIIII = Math.min(llIIlIllIIlI, llIIllIIIlII);
                final int llIIlIllllll = Math.min(llIIlIllIIIl, llIIllIIIIll);
                final int llIIlIlllllI = llIIllIIIIII - llIIllIIIIlI;
                final int llIIlIllllIl = llIIlIllllll - llIIllIIIIIl;
                if (llIIlIlllllI > 0 && llIIlIllllIl > 0) {
                    final Image llIIllIIIllI = llIIllIIIlIl.getSubImage((int)(llIIllIIIIlI - llIIlIllIlII), (int)(llIIllIIIIIl - llIIlIllIIll), llIIllIIIIII - llIIllIIIIlI, llIIlIllllll - llIIllIIIIIl);
                    llIIlIlIlllI = true;
                    llIIlIllIlIl.images[llIIlIllIIII][llIIlIlIllll] = llIIllIIIllI;
                    ++llIIlIlIllll;
                    llIIlIllIlIl.ycount = Math.max(llIIlIllIlIl.ycount, llIIlIlIllll);
                }
                llIIlIllIIll += llIIllIIIlIl.getHeight();
                if (llIIlIllllII == this.ycount - 1) {
                    llIIlIllIlII += llIIllIIIlIl.getWidth();
                }
            }
            if (llIIlIlIlllI) {
                ++llIIlIllIIII;
                final BigImage bigImage = llIIlIllIlIl;
                ++bigImage.xcount;
            }
        }
        return llIIlIllIlIl;
    }
    
    public BigImage(final String lllIlIIllIlI, final int lllIlIIllIIl) throws SlickException {
        this.build(lllIlIIllIlI, lllIlIIllIIl, getMaxSingleImageSize());
    }
    
    public Image getTile(final int lllIIllIIlll, final int lllIIllIIllI) {
        return this.images[lllIIllIIlll][lllIIllIIllI];
    }
    
    @Override
    public void drawEmbedded(final float llIlIlIIlIII, final float llIlIlIIIlll, final float llIlIlIIllll, final float llIlIlIIIlIl) {
        final float llIlIlIIllIl = llIlIlIIllll / this.realWidth;
        final float llIlIlIIllII = llIlIlIIIlIl / this.realHeight;
        float llIlIlIIlIll = 0.0f;
        float llIlIlIIlIlI = 0.0f;
        for (int llIlIlIlIIll = 0; llIlIlIlIIll < this.xcount; ++llIlIlIlIIll) {
            llIlIlIIlIlI = 0.0f;
            for (int llIlIlIlIlII = 0; llIlIlIlIlII < this.ycount; ++llIlIlIlIlII) {
                final Image llIlIlIlIlIl = this.images[llIlIlIlIIll][llIlIlIlIlII];
                if (BigImage.lastBind == null || llIlIlIlIlIl.getTexture() != BigImage.lastBind.getTexture()) {
                    if (BigImage.lastBind != null) {
                        BigImage.lastBind.endUse();
                    }
                    (BigImage.lastBind = llIlIlIlIlIl).startUse();
                }
                llIlIlIlIlIl.drawEmbedded(llIlIlIIlIll + llIlIlIIlIII, llIlIlIIlIlI + llIlIlIIIlll, (float)llIlIlIlIlIl.getWidth(), (float)llIlIlIlIlIl.getHeight());
                llIlIlIIlIlI += llIlIlIlIlIl.getHeight();
                if (llIlIlIlIlII == this.ycount - 1) {
                    llIlIlIIlIll += llIlIlIlIlIl.getWidth();
                }
            }
        }
    }
    
    @Override
    public void draw(final float lllIIIIIIlll, final float lllIIIIIIIlI, final Color lllIIIIIIlIl) {
        this.draw(lllIIIIIIlll, lllIIIIIIIlI, (float)this.width, (float)this.height, lllIIIIIIlIl);
    }
    
    public BigImage(final LoadableImageData lllIlIIIIIII, final ByteBuffer lllIlIIIIIll, final int lllIIllllllI) {
        this.build(lllIlIIIIIII, lllIlIIIIIll, lllIIllllllI, getMaxSingleImageSize());
    }
    
    @Override
    public void drawSheared(final float llIIIIIIllIl, final float llIIIIIIllII, final float llIIIIIIlIll, final float llIIIIIIlIlI) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void drawFlash(final float llIlIIlIIlII, final float llIlIIlIIIll, final float llIlIIlIlIll, final float llIlIIlIlIlI) {
        final float llIlIIlIlIIl = llIlIIlIlIll / this.realWidth;
        final float llIlIIlIlIII = llIlIIlIlIlI / this.realHeight;
        BigImage.GL.glTranslatef(llIlIIlIIlII, llIlIIlIIIll, 0.0f);
        BigImage.GL.glScalef(llIlIIlIlIIl, llIlIIlIlIII, 1.0f);
        float llIlIIlIIlll = 0.0f;
        float llIlIIlIIllI = 0.0f;
        for (int llIlIIlIllll = 0; llIlIIlIllll < this.xcount; ++llIlIIlIllll) {
            llIlIIlIIllI = 0.0f;
            for (int llIlIIllIIII = 0; llIlIIllIIII < this.ycount; ++llIlIIllIIII) {
                final Image llIlIIllIIIl = this.images[llIlIIlIllll][llIlIIllIIII];
                llIlIIllIIIl.drawFlash(llIlIIlIIlll, llIlIIlIIllI, (float)llIlIIllIIIl.getWidth(), (float)llIlIIllIIIl.getHeight());
                llIlIIlIIllI += llIlIIllIIIl.getHeight();
                if (llIlIIllIIII == this.ycount - 1) {
                    llIlIIlIIlll += llIlIIllIIIl.getWidth();
                }
            }
        }
        BigImage.GL.glScalef(1.0f / llIlIIlIlIIl, 1.0f / llIlIIlIlIII, 1.0f);
        BigImage.GL.glTranslatef(-llIlIIlIIlII, -llIlIIlIIIll, 0.0f);
    }
    
    @Override
    public void drawFlash(final float llIIIIIlIIll, final float llIIIIIlIIlI, final float llIIIIIlIIIl, final float llIIIIIlIIII, final Color llIIIIIIllll) {
        throw new UnsupportedOperationException();
    }
    
    static {
        BigImage.GL = Renderer.get();
    }
    
    @Override
    public void drawEmbedded(final float llIIIIlIIlII, final float llIIIIlIIIlI, final float llIIIIlIIIII, final float llIIIIIllllI, final float llIIIIIlllII, final float llIIIIIllIlI, final float llIIIIIllIII, final float llIIIIIlIllI) {
        throw new UnsupportedOperationException();
    }
}
