package org.newdawn.slick.font;

import java.awt.font.*;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;
import java.nio.*;
import java.util.*;
import org.newdawn.slick.font.effects.*;
import java.awt.*;
import java.awt.image.*;

public class GlyphPage
{
    private static /* synthetic */ ByteBuffer scratchByteBuffer;
    private final /* synthetic */ UnicodeFont unicodeFont;
    private /* synthetic */ int pageX;
    public static /* synthetic */ FontRenderContext renderContext;
    private final /* synthetic */ List pageGlyphs;
    private /* synthetic */ int pageY;
    private static /* synthetic */ IntBuffer scratchIntBuffer;
    private final /* synthetic */ int pageWidth;
    private /* synthetic */ int rowHeight;
    private static final /* synthetic */ SGL GL;
    private final /* synthetic */ Image pageImage;
    private static /* synthetic */ Graphics2D scratchGraphics;
    private static /* synthetic */ BufferedImage scratchImage;
    private final /* synthetic */ int pageHeight;
    private /* synthetic */ boolean orderAscending;
    
    public GlyphPage(final UnicodeFont llIllIlIlIlIII, final int llIllIlIlIlIll, final int llIllIlIlIlIlI) throws SlickException {
        this.pageGlyphs = new ArrayList(32);
        this.unicodeFont = llIllIlIlIlIII;
        this.pageWidth = llIllIlIlIlIll;
        this.pageHeight = llIllIlIlIlIlI;
        this.pageImage = new Image(llIllIlIlIlIll, llIllIlIlIlIlI);
    }
    
    public int loadGlyphs(final List llIllIlIIIllll, final int llIllIlIIIlIlI) throws SlickException {
        if (this.rowHeight != 0 && llIllIlIIIlIlI == -1) {
            int llIllIlIIlIlll = this.pageX;
            int llIllIlIIlIllI = this.pageY;
            int llIllIlIIlIlIl = this.rowHeight;
            final Iterator llIllIlIIllIII = this.getIterator(llIllIlIIIllll);
            while (llIllIlIIllIII.hasNext()) {
                final Glyph llIllIlIIllIll = llIllIlIIllIII.next();
                final int llIllIlIIllIlI = llIllIlIIllIll.getWidth();
                final int llIllIlIIllIIl = llIllIlIIllIll.getHeight();
                if (llIllIlIIlIlll + llIllIlIIllIlI >= this.pageWidth) {
                    llIllIlIIlIlll = 0;
                    llIllIlIIlIllI += llIllIlIIlIlIl;
                    llIllIlIIlIlIl = llIllIlIIllIIl;
                }
                else if (llIllIlIIllIIl > llIllIlIIlIlIl) {
                    llIllIlIIlIlIl = llIllIlIIllIIl;
                }
                if (llIllIlIIlIllI + llIllIlIIlIlIl >= this.pageWidth) {
                    return 0;
                }
                llIllIlIIlIlll += llIllIlIIllIlI;
            }
        }
        Color.white.bind();
        this.pageImage.bind();
        int llIllIlIIIllIl = 0;
        final Iterator llIllIlIIlIIIl = this.getIterator(llIllIlIIIllll);
        while (llIllIlIIlIIIl.hasNext()) {
            final Glyph llIllIlIIlIlII = llIllIlIIlIIIl.next();
            final int llIllIlIIlIIll = Math.min(256, llIllIlIIlIlII.getWidth());
            final int llIllIlIIlIIlI = Math.min(256, llIllIlIIlIlII.getHeight());
            if (this.rowHeight == 0) {
                this.rowHeight = llIllIlIIlIIlI;
            }
            else if (this.pageX + llIllIlIIlIIll >= this.pageWidth) {
                if (this.pageY + this.rowHeight + llIllIlIIlIIlI >= this.pageHeight) {
                    break;
                }
                this.pageX = 0;
                this.pageY += this.rowHeight;
                this.rowHeight = llIllIlIIlIIlI;
            }
            else if (llIllIlIIlIIlI > this.rowHeight) {
                if (this.pageY + llIllIlIIlIIlI >= this.pageHeight) {
                    break;
                }
                this.rowHeight = llIllIlIIlIIlI;
            }
            this.renderGlyph(llIllIlIIlIlII, llIllIlIIlIIll, llIllIlIIlIIlI);
            this.pageGlyphs.add(llIllIlIIlIlII);
            this.pageX += llIllIlIIlIIll;
            llIllIlIIlIIIl.remove();
            if (++llIllIlIIIllIl == llIllIlIIIlIlI) {
                this.orderAscending = !this.orderAscending;
                break;
            }
        }
        TextureImpl.bindNone();
        this.orderAscending = !this.orderAscending;
        return llIllIlIIIllIl;
    }
    
    static {
        MAX_GLYPH_SIZE = 256;
        GL = Renderer.get();
        (GlyphPage.scratchByteBuffer = ByteBuffer.allocateDirect(262144)).order(ByteOrder.LITTLE_ENDIAN);
        GlyphPage.scratchIntBuffer = GlyphPage.scratchByteBuffer.asIntBuffer();
        GlyphPage.scratchImage = new BufferedImage(256, 256, 2);
        (GlyphPage.scratchGraphics = (Graphics2D)GlyphPage.scratchImage.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GlyphPage.scratchGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        GlyphPage.scratchGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        GlyphPage.renderContext = GlyphPage.scratchGraphics.getFontRenderContext();
    }
    
    private Iterator getIterator(final List llIllIIllIIlIl) {
        if (this.orderAscending) {
            return llIllIIllIIlIl.iterator();
        }
        final ListIterator llIllIIllIIlll = llIllIIllIIlIl.listIterator(llIllIIllIIlIl.size());
        return new Iterator() {
            @Override
            public void remove() {
                llIllIIllIIlll.remove();
            }
            
            @Override
            public Object next() {
                return llIllIIllIIlll.previous();
            }
            
            @Override
            public boolean hasNext() {
                return llIllIIllIIlll.hasPrevious();
            }
        };
    }
    
    public List getGlyphs() {
        return this.pageGlyphs;
    }
    
    public Image getImage() {
        return this.pageImage;
    }
    
    public static Graphics2D getScratchGraphics() {
        return GlyphPage.scratchGraphics;
    }
    
    private void renderGlyph(final Glyph llIllIIlllIIlI, final int llIllIIlllIlll, final int llIllIIlllIllI) throws SlickException {
        GlyphPage.scratchGraphics.setComposite(AlphaComposite.Clear);
        GlyphPage.scratchGraphics.fillRect(0, 0, 256, 256);
        GlyphPage.scratchGraphics.setComposite(AlphaComposite.SrcOver);
        GlyphPage.scratchGraphics.setColor(java.awt.Color.white);
        final Iterator llIllIIllllIll = this.unicodeFont.getEffects().iterator();
        while (llIllIIllllIll.hasNext()) {
            llIllIIllllIll.next().draw(GlyphPage.scratchImage, GlyphPage.scratchGraphics, this.unicodeFont, llIllIIlllIIlI);
        }
        llIllIIlllIIlI.setShape(null);
        final WritableRaster llIllIIlllIlIl = GlyphPage.scratchImage.getRaster();
        final int[] llIllIIlllIlII = new int[llIllIIlllIlll];
        for (int llIllIIllllIlI = 0; llIllIIllllIlI < llIllIIlllIllI; ++llIllIIllllIlI) {
            llIllIIlllIlIl.getDataElements(0, llIllIIllllIlI, llIllIIlllIlll, 1, llIllIIlllIlII);
            GlyphPage.scratchIntBuffer.put(llIllIIlllIlII);
        }
        GlyphPage.GL.glTexSubImage2D(3553, 0, this.pageX, this.pageY, llIllIIlllIlll, llIllIIlllIllI, 32993, 5121, GlyphPage.scratchByteBuffer);
        GlyphPage.scratchIntBuffer.clear();
        llIllIIlllIIlI.setImage(this.pageImage.getSubImage(this.pageX, this.pageY, llIllIIlllIlll, llIllIIlllIllI));
    }
}
