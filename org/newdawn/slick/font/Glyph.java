package org.newdawn.slick.font;

import java.awt.*;
import org.newdawn.slick.*;
import java.awt.font.*;

public class Glyph
{
    private /* synthetic */ int codePoint;
    private /* synthetic */ boolean isMissing;
    private /* synthetic */ short yOffset;
    private /* synthetic */ Shape shape;
    private /* synthetic */ short width;
    private /* synthetic */ short height;
    private /* synthetic */ Image image;
    
    public Image getImage() {
        return this.image;
    }
    
    public boolean isMissing() {
        return this.isMissing;
    }
    
    public void setShape(final Shape lllllllllllllllllIlIIllIlllllllI) {
        this.shape = lllllllllllllllllIlIIllIlllllllI;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Shape getShape() {
        return this.shape;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getYOffset() {
        return this.yOffset;
    }
    
    public void setImage(final Image lllllllllllllllllIlIIllIllllIlll) {
        this.image = lllllllllllllllllIlIIllIllllIlll;
    }
    
    public Glyph(final int lllllllllllllllllIlIIlllIIlIllII, final Rectangle lllllllllllllllllIlIIlllIIlIlIll, final GlyphVector lllllllllllllllllIlIIlllIIIlllll, final int lllllllllllllllllIlIIlllIIlIlIIl, final UnicodeFont lllllllllllllllllIlIIlllIIlIlIII) {
        this.codePoint = lllllllllllllllllIlIIlllIIlIllII;
        final GlyphMetrics lllllllllllllllllIlIIlllIIlIIlll = lllllllllllllllllIlIIlllIIIlllll.getGlyphMetrics(lllllllllllllllllIlIIlllIIlIlIIl);
        int lllllllllllllllllIlIIlllIIlIIllI = (int)lllllllllllllllllIlIIlllIIlIIlll.getLSB();
        if (lllllllllllllllllIlIIlllIIlIIllI > 0) {
            lllllllllllllllllIlIIlllIIlIIllI = 0;
        }
        int lllllllllllllllllIlIIlllIIlIIlIl = (int)lllllllllllllllllIlIIlllIIlIIlll.getRSB();
        if (lllllllllllllllllIlIIlllIIlIIlIl > 0) {
            lllllllllllllllllIlIIlllIIlIIlIl = 0;
        }
        final int lllllllllllllllllIlIIlllIIlIIlII = lllllllllllllllllIlIIlllIIlIlIll.width - lllllllllllllllllIlIIlllIIlIIllI - lllllllllllllllllIlIIlllIIlIIlIl;
        final int lllllllllllllllllIlIIlllIIlIIIll = lllllllllllllllllIlIIlllIIlIlIll.height;
        if (lllllllllllllllllIlIIlllIIlIIlII > 0 && lllllllllllllllllIlIIlllIIlIIIll > 0) {
            final int lllllllllllllllllIlIIlllIIllIIlI = lllllllllllllllllIlIIlllIIlIlIII.getPaddingTop();
            final int lllllllllllllllllIlIIlllIIllIIIl = lllllllllllllllllIlIIlllIIlIlIII.getPaddingRight();
            final int lllllllllllllllllIlIIlllIIllIIII = lllllllllllllllllIlIIlllIIlIlIII.getPaddingBottom();
            final int lllllllllllllllllIlIIlllIIlIllll = lllllllllllllllllIlIIlllIIlIlIII.getPaddingLeft();
            final int lllllllllllllllllIlIIlllIIlIlllI = 1;
            this.width = (short)(lllllllllllllllllIlIIlllIIlIIlII + lllllllllllllllllIlIIlllIIlIllll + lllllllllllllllllIlIIlllIIllIIIl + lllllllllllllllllIlIIlllIIlIlllI);
            this.height = (short)(lllllllllllllllllIlIIlllIIlIIIll + lllllllllllllllllIlIIlllIIllIIlI + lllllllllllllllllIlIIlllIIllIIII + lllllllllllllllllIlIIlllIIlIlllI);
            this.yOffset = (short)(lllllllllllllllllIlIIlllIIlIlIII.getAscent() + lllllllllllllllllIlIIlllIIlIlIll.y - lllllllllllllllllIlIIlllIIllIIlI);
        }
        this.shape = lllllllllllllllllIlIIlllIIIlllll.getGlyphOutline(lllllllllllllllllIlIIlllIIlIlIIl, (float)(-lllllllllllllllllIlIIlllIIlIlIll.x + lllllllllllllllllIlIIlllIIlIlIII.getPaddingLeft()), (float)(-lllllllllllllllllIlIIlllIIlIlIll.y + lllllllllllllllllIlIIlllIIlIlIII.getPaddingTop()));
        this.isMissing = !lllllllllllllllllIlIIlllIIlIlIII.getFont().canDisplay((char)lllllllllllllllllIlIIlllIIlIllII);
    }
    
    public int getCodePoint() {
        return this.codePoint;
    }
}
