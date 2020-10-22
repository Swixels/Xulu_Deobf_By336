package org.newdawn.slick.opengl;

import java.awt.color.*;
import java.util.*;
import java.awt.*;
import java.nio.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ImageIOImageData implements LoadableImageData
{
    private /* synthetic */ int depth;
    private static final /* synthetic */ ColorModel glColorModel;
    private /* synthetic */ boolean edging;
    private /* synthetic */ int width;
    private static final /* synthetic */ ColorModel glAlphaColorModel;
    private /* synthetic */ int height;
    private /* synthetic */ int texWidth;
    private /* synthetic */ int texHeight;
    
    static {
        glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
        glColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public int getDepth() {
        return this.depth;
    }
    
    @Override
    public void configureEdging(final boolean lIIllllIllIllII) {
        this.edging = lIIllllIllIllII;
    }
    
    @Override
    public int getTexHeight() {
        return this.texHeight;
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    private void copyArea(final BufferedImage lIIllllIlllllll, final int lIIllllIllllllI, final int lIIllllIlllllIl, final int lIIllllIlllIlII, final int lIIllllIlllIIll, final int lIIllllIlllIIlI, final int lIIllllIlllIIIl) {
        final Graphics2D lIIllllIllllIII = (Graphics2D)lIIllllIlllllll.getGraphics();
        lIIllllIllllIII.drawImage(lIIllllIlllllll.getSubimage(lIIllllIllllllI, lIIllllIlllllIl, lIIllllIlllIlII, lIIllllIlllIIll), lIIllllIllllllI + lIIllllIlllIIlI, lIIllllIlllllIl + lIIllllIlllIIIl, null);
    }
    
    public ImageIOImageData() {
        this.edging = true;
    }
    
    public ByteBuffer imageToByteBuffer(final BufferedImage lIIlllllIIllIIl, final boolean lIIlllllIIllIII, final boolean lIIlllllIlIIlII, final int[] lIIlllllIIlIllI) {
        ByteBuffer lIIlllllIlIIIlI = null;
        int lIIlllllIIlllll = 2;
        int lIIlllllIIllllI = 2;
        while (lIIlllllIIlllll < lIIlllllIIllIIl.getWidth()) {
            lIIlllllIIlllll *= 2;
        }
        while (lIIlllllIIllllI < lIIlllllIIllIIl.getHeight()) {
            lIIlllllIIllllI *= 2;
        }
        this.width = lIIlllllIIllIIl.getWidth();
        this.height = lIIlllllIIllIIl.getHeight();
        this.texHeight = lIIlllllIIllllI;
        this.texWidth = lIIlllllIIlllll;
        final boolean lIIlllllIIlllIl = lIIlllllIIllIIl.getColorModel().hasAlpha() || lIIlllllIlIIlII;
        BufferedImage lIIlllllIlIIIII = null;
        if (lIIlllllIIlllIl) {
            this.depth = 32;
            final WritableRaster lIIlllllIlIllIl = Raster.createInterleavedRaster(0, lIIlllllIIlllll, lIIlllllIIllllI, 4, null);
            final BufferedImage lIIlllllIlIllII = new BufferedImage(ImageIOImageData.glAlphaColorModel, lIIlllllIlIllIl, false, new Hashtable<Object, Object>());
        }
        else {
            this.depth = 24;
            final WritableRaster lIIlllllIlIIIIl = Raster.createInterleavedRaster(0, lIIlllllIIlllll, lIIlllllIIllllI, 3, null);
            lIIlllllIlIIIII = new BufferedImage(ImageIOImageData.glColorModel, lIIlllllIlIIIIl, false, new Hashtable<Object, Object>());
        }
        final Graphics2D lIIlllllIIlllII = (Graphics2D)lIIlllllIlIIIII.getGraphics();
        if (lIIlllllIIlllIl) {
            lIIlllllIIlllII.setColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));
            lIIlllllIIlllII.fillRect(0, 0, lIIlllllIIlllll, lIIlllllIIllllI);
        }
        if (lIIlllllIIllIII) {
            lIIlllllIIlllII.scale(1.0, -1.0);
            lIIlllllIIlllII.drawImage(lIIlllllIIllIIl, 0, -this.height, null);
        }
        else {
            lIIlllllIIlllII.drawImage(lIIlllllIIllIIl, 0, 0, null);
        }
        if (this.edging) {
            if (this.height < lIIlllllIIllllI - 1) {
                this.copyArea(lIIlllllIlIIIII, 0, 0, this.width, 1, 0, lIIlllllIIllllI - 1);
                this.copyArea(lIIlllllIlIIIII, 0, this.height - 1, this.width, 1, 0, 1);
            }
            if (this.width < lIIlllllIIlllll - 1) {
                this.copyArea(lIIlllllIlIIIII, 0, 0, 1, this.height, lIIlllllIIlllll - 1, 0);
                this.copyArea(lIIlllllIlIIIII, this.width - 1, 0, 1, this.height, 1, 0);
            }
        }
        final byte[] lIIlllllIIllIll = ((DataBufferByte)lIIlllllIlIIIII.getRaster().getDataBuffer()).getData();
        if (lIIlllllIIlIllI != null) {
            for (int lIIlllllIlIlIII = 0; lIIlllllIlIlIII < lIIlllllIIllIll.length; lIIlllllIlIlIII += 4) {
                boolean lIIlllllIlIlIIl = true;
                for (int lIIlllllIlIlIlI = 0; lIIlllllIlIlIlI < 3; ++lIIlllllIlIlIlI) {
                    final int lIIlllllIlIlIll = (lIIlllllIIllIll[lIIlllllIlIlIII + lIIlllllIlIlIlI] < 0) ? (256 + lIIlllllIIllIll[lIIlllllIlIlIII + lIIlllllIlIlIlI]) : lIIlllllIIllIll[lIIlllllIlIlIII + lIIlllllIlIlIlI];
                    if (lIIlllllIlIlIll != lIIlllllIIlIllI[lIIlllllIlIlIlI]) {
                        lIIlllllIlIlIIl = false;
                    }
                }
                if (lIIlllllIlIlIIl) {
                    lIIlllllIIllIll[lIIlllllIlIlIII + 3] = 0;
                }
            }
        }
        lIIlllllIlIIIlI = ByteBuffer.allocateDirect(lIIlllllIIllIll.length);
        lIIlllllIlIIIlI.order(ByteOrder.nativeOrder());
        lIIlllllIlIIIlI.put(lIIlllllIIllIll, 0, lIIlllllIIllIll.length);
        lIIlllllIlIIIlI.flip();
        lIIlllllIIlllII.dispose();
        return lIIlllllIlIIIlI;
    }
    
    @Override
    public int getTexWidth() {
        return this.texWidth;
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        throw new RuntimeException("ImageIOImageData doesn't store it's image.");
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream lIIllllllIlIIll, final boolean lIIllllllIlIllI, final int[] lIIllllllIlIlIl) throws IOException {
        return this.loadImage(lIIllllllIlIIll, lIIllllllIlIllI, false, lIIllllllIlIlIl);
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream lIIllllllIIIIll, final boolean lIIllllllIIlIII, boolean lIIllllllIIIIIl, final int[] lIIllllllIIIllI) throws IOException {
        if (lIIllllllIIIllI != null) {
            lIIllllllIIIIIl = 1;
        }
        final BufferedImage lIIllllllIIIlIl = ImageIO.read(lIIllllllIIIIll);
        return this.imageToByteBuffer(lIIllllllIIIlIl, lIIllllllIIlIII, (boolean)(lIIllllllIIIIIl != 0), lIIllllllIIIllI);
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream lIIllllllIlllIl) throws IOException {
        return this.loadImage(lIIllllllIlllIl, true, null);
    }
}
