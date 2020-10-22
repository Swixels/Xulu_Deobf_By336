package com.elementars.eclient.font.custom;

import net.minecraft.client.renderer.texture.*;
import org.lwjgl.opengl.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.geom.*;

public class CFont
{
    protected /* synthetic */ CharData[] charData;
    protected /* synthetic */ int charOffset;
    protected /* synthetic */ boolean fractionalMetrics;
    protected /* synthetic */ int fontHeight;
    protected /* synthetic */ DynamicTexture tex;
    protected /* synthetic */ boolean antiAlias;
    protected /* synthetic */ Font font;
    private /* synthetic */ float imgSize;
    
    public int getStringWidth(final String lllllllllllllllllIlIIIllIIIlIIlI) {
        int lllllllllllllllllIlIIIllIIIlIlII = 0;
        final char lllllllllllllllllIlIIIllIIIlIIII = (Object)lllllllllllllllllIlIIIllIIIlIIlI.toCharArray();
        final char lllllllllllllllllIlIIIllIIIIllll = (char)lllllllllllllllllIlIIIllIIIlIIII.length;
        for (Exception lllllllllllllllllIlIIIllIIIIlllI = (Exception)0; lllllllllllllllllIlIIIllIIIIlllI < lllllllllllllllllIlIIIllIIIIllll; ++lllllllllllllllllIlIIIllIIIIlllI) {
            final char lllllllllllllllllIlIIIllIIIlIlll = lllllllllllllllllIlIIIllIIIlIIII[lllllllllllllllllIlIIIllIIIIlllI];
            if (lllllllllllllllllIlIIIllIIIlIlll < this.charData.length && lllllllllllllllllIlIIIllIIIlIlll >= '\0') {
                lllllllllllllllllIlIIIllIIIlIlII += this.charData[lllllllllllllllllIlIIIllIIIlIlll].width - 8 + this.charOffset;
            }
        }
        return lllllllllllllllllIlIIIllIIIlIlII / 2;
    }
    
    public int getStringHeight(final String lllllllllllllllllIlIIIllIIlIIIll) {
        return this.getHeight();
    }
    
    public void drawChar(final CharData[] lllllllllllllllllIlIIIllIlIlIllI, final char lllllllllllllllllIlIIIllIlIlIIII, final float lllllllllllllllllIlIIIllIlIlIlII, final float lllllllllllllllllIlIIIllIlIlIIll) throws ArrayIndexOutOfBoundsException {
        try {
            this.drawQuad(lllllllllllllllllIlIIIllIlIlIlII, lllllllllllllllllIlIIIllIlIlIIll, (float)lllllllllllllllllIlIIIllIlIlIllI[lllllllllllllllllIlIIIllIlIlIIII].width, (float)lllllllllllllllllIlIIIllIlIlIllI[lllllllllllllllllIlIIIllIlIlIIII].height, (float)lllllllllllllllllIlIIIllIlIlIllI[lllllllllllllllllIlIIIllIlIlIIII].storedX, (float)lllllllllllllllllIlIIIllIlIlIllI[lllllllllllllllllIlIIIllIlIlIIII].storedY, (float)lllllllllllllllllIlIIIllIlIlIllI[lllllllllllllllllIlIIIllIlIlIIII].width, (float)lllllllllllllllllIlIIIllIlIlIllI[lllllllllllllllllIlIIIllIlIlIIII].height);
        }
        catch (Exception lllllllllllllllllIlIIIllIlIllIII) {
            lllllllllllllllllIlIIIllIlIllIII.printStackTrace();
        }
    }
    
    protected void drawQuad(final float lllllllllllllllllIlIIIllIIlllllI, final float lllllllllllllllllIlIIIllIIllIIII, final float lllllllllllllllllIlIIIllIIllllII, final float lllllllllllllllllIlIIIllIIlIlllI, final float lllllllllllllllllIlIIIllIIlIllIl, final float lllllllllllllllllIlIIIllIIlllIIl, final float lllllllllllllllllIlIIIllIIlllIII, final float lllllllllllllllllIlIIIllIIllIlll) {
        final float lllllllllllllllllIlIIIllIIllIllI = lllllllllllllllllIlIIIllIIlIllIl / this.imgSize;
        final float lllllllllllllllllIlIIIllIIllIlIl = lllllllllllllllllIlIIIllIIlllIIl / this.imgSize;
        final float lllllllllllllllllIlIIIllIIllIlII = lllllllllllllllllIlIIIllIIlllIII / this.imgSize;
        final float lllllllllllllllllIlIIIllIIllIIll = lllllllllllllllllIlIIIllIIllIlll / this.imgSize;
        GL11.glTexCoord2f(lllllllllllllllllIlIIIllIIllIllI + lllllllllllllllllIlIIIllIIllIlII, lllllllllllllllllIlIIIllIIllIlIl);
        GL11.glVertex2d((double)(lllllllllllllllllIlIIIllIIlllllI + lllllllllllllllllIlIIIllIIllllII), (double)lllllllllllllllllIlIIIllIIllIIII);
        GL11.glTexCoord2f(lllllllllllllllllIlIIIllIIllIllI, lllllllllllllllllIlIIIllIIllIlIl);
        GL11.glVertex2d((double)lllllllllllllllllIlIIIllIIlllllI, (double)lllllllllllllllllIlIIIllIIllIIII);
        GL11.glTexCoord2f(lllllllllllllllllIlIIIllIIllIllI, lllllllllllllllllIlIIIllIIllIlIl + lllllllllllllllllIlIIIllIIllIIll);
        GL11.glVertex2d((double)lllllllllllllllllIlIIIllIIlllllI, (double)(lllllllllllllllllIlIIIllIIllIIII + lllllllllllllllllIlIIIllIIlIlllI));
        GL11.glTexCoord2f(lllllllllllllllllIlIIIllIIllIllI, lllllllllllllllllIlIIIllIIllIlIl + lllllllllllllllllIlIIIllIIllIIll);
        GL11.glVertex2d((double)lllllllllllllllllIlIIIllIIlllllI, (double)(lllllllllllllllllIlIIIllIIllIIII + lllllllllllllllllIlIIIllIIlIlllI));
        GL11.glTexCoord2f(lllllllllllllllllIlIIIllIIllIllI + lllllllllllllllllIlIIIllIIllIlII, lllllllllllllllllIlIIIllIIllIlIl + lllllllllllllllllIlIIIllIIllIIll);
        GL11.glVertex2d((double)(lllllllllllllllllIlIIIllIIlllllI + lllllllllllllllllIlIIIllIIllllII), (double)(lllllllllllllllllIlIIIllIIllIIII + lllllllllllllllllIlIIIllIIlIlllI));
        GL11.glTexCoord2f(lllllllllllllllllIlIIIllIIllIllI + lllllllllllllllllIlIIIllIIllIlII, lllllllllllllllllIlIIIllIIllIlIl);
        GL11.glVertex2d((double)(lllllllllllllllllIlIIIllIIlllllI + lllllllllllllllllIlIIIllIIllllII), (double)lllllllllllllllllIlIIIllIIllIIII);
    }
    
    public CFont(final Font lllllllllllllllllIlIIIlllIlIIllI, final boolean lllllllllllllllllIlIIIlllIlIIlIl, final boolean lllllllllllllllllIlIIIlllIlIIlII) {
        this.imgSize = 512.0f;
        this.charData = new CharData[256];
        this.fontHeight = -1;
        this.charOffset = 0;
        this.font = lllllllllllllllllIlIIIlllIlIIllI;
        this.antiAlias = lllllllllllllllllIlIIIlllIlIIlIl;
        this.fractionalMetrics = lllllllllllllllllIlIIIlllIlIIlII;
        this.tex = this.setupTexture(lllllllllllllllllIlIIIlllIlIIllI, lllllllllllllllllIlIIIlllIlIIlIl, lllllllllllllllllIlIIIlllIlIIlII, this.charData);
    }
    
    protected DynamicTexture setupTexture(final Font lllllllllllllllllIlIIIlllIIllIlI, final boolean lllllllllllllllllIlIIIlllIIllIIl, final boolean lllllllllllllllllIlIIIlllIIlIIlI, final CharData[] lllllllllllllllllIlIIIlllIIlIIIl) {
        final BufferedImage lllllllllllllllllIlIIIlllIIlIllI = this.generateFontImage(lllllllllllllllllIlIIIlllIIllIlI, lllllllllllllllllIlIIIlllIIllIIl, lllllllllllllllllIlIIIlllIIlIIlI, lllllllllllllllllIlIIIlllIIlIIIl);
        try {
            return new DynamicTexture(lllllllllllllllllIlIIIlllIIlIllI);
        }
        catch (Exception lllllllllllllllllIlIIIlllIIlllII) {
            lllllllllllllllllIlIIIlllIIlllII.printStackTrace();
            return null;
        }
    }
    
    public boolean isFractionalMetrics() {
        return this.fractionalMetrics;
    }
    
    protected BufferedImage generateFontImage(final Font lllllllllllllllllIlIIIllIllllIIl, final boolean lllllllllllllllllIlIIIllIllllIII, final boolean lllllllllllllllllIlIIIllIlllIlll, final CharData[] lllllllllllllllllIlIIIllIllIlIlI) {
        final int lllllllllllllllllIlIIIllIlllIlIl = (int)this.imgSize;
        final BufferedImage lllllllllllllllllIlIIIllIlllIlII = new BufferedImage(lllllllllllllllllIlIIIllIlllIlIl, lllllllllllllllllIlIIIllIlllIlIl, 2);
        final Graphics2D lllllllllllllllllIlIIIllIlllIIll = (Graphics2D)lllllllllllllllllIlIIIllIlllIlII.getGraphics();
        lllllllllllllllllIlIIIllIlllIIll.setFont(lllllllllllllllllIlIIIllIllllIIl);
        lllllllllllllllllIlIIIllIlllIIll.setColor(new Color(255, 255, 255, 0));
        lllllllllllllllllIlIIIllIlllIIll.fillRect(0, 0, lllllllllllllllllIlIIIllIlllIlIl, lllllllllllllllllIlIIIllIlllIlIl);
        lllllllllllllllllIlIIIllIlllIIll.setColor(Color.WHITE);
        lllllllllllllllllIlIIIllIlllIIll.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, lllllllllllllllllIlIIIllIlllIlll ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        lllllllllllllllllIlIIIllIlllIIll.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, lllllllllllllllllIlIIIllIllllIII ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        lllllllllllllllllIlIIIllIlllIIll.setRenderingHint(RenderingHints.KEY_ANTIALIASING, lllllllllllllllllIlIIIllIllllIII ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        final FontMetrics lllllllllllllllllIlIIIllIlllIIlI = lllllllllllllllllIlIIIllIlllIIll.getFontMetrics();
        int lllllllllllllllllIlIIIllIlllIIIl = 0;
        int lllllllllllllllllIlIIIllIlllIIII = 0;
        int lllllllllllllllllIlIIIllIllIllll = 1;
        for (int lllllllllllllllllIlIIIllIllllIll = 0; lllllllllllllllllIlIIIllIllllIll < lllllllllllllllllIlIIIllIllIlIlI.length; ++lllllllllllllllllIlIIIllIllllIll) {
            final char lllllllllllllllllIlIIIllIllllllI = (char)lllllllllllllllllIlIIIllIllllIll;
            final CharData lllllllllllllllllIlIIIllIlllllIl = new CharData();
            final Rectangle2D lllllllllllllllllIlIIIllIlllllII = lllllllllllllllllIlIIIllIlllIIlI.getStringBounds(String.valueOf(lllllllllllllllllIlIIIllIllllllI), lllllllllllllllllIlIIIllIlllIIll);
            lllllllllllllllllIlIIIllIlllllIl.width = lllllllllllllllllIlIIIllIlllllII.getBounds().width + 8;
            lllllllllllllllllIlIIIllIlllllIl.height = lllllllllllllllllIlIIIllIlllllII.getBounds().height;
            if (lllllllllllllllllIlIIIllIlllIIII + lllllllllllllllllIlIIIllIlllllIl.width >= lllllllllllllllllIlIIIllIlllIlIl) {
                lllllllllllllllllIlIIIllIlllIIII = 0;
                lllllllllllllllllIlIIIllIllIllll += lllllllllllllllllIlIIIllIlllIIIl;
                lllllllllllllllllIlIIIllIlllIIIl = 0;
            }
            if (lllllllllllllllllIlIIIllIlllllIl.height > lllllllllllllllllIlIIIllIlllIIIl) {
                lllllllllllllllllIlIIIllIlllIIIl = lllllllllllllllllIlIIIllIlllllIl.height;
            }
            lllllllllllllllllIlIIIllIlllllIl.storedX = lllllllllllllllllIlIIIllIlllIIII;
            lllllllllllllllllIlIIIllIlllllIl.storedY = lllllllllllllllllIlIIIllIllIllll;
            if (lllllllllllllllllIlIIIllIlllllIl.height > this.fontHeight) {
                this.fontHeight = lllllllllllllllllIlIIIllIlllllIl.height;
            }
            lllllllllllllllllIlIIIllIllIlIlI[lllllllllllllllllIlIIIllIllllIll] = lllllllllllllllllIlIIIllIlllllIl;
            lllllllllllllllllIlIIIllIlllIIll.drawString(String.valueOf(lllllllllllllllllIlIIIllIllllllI), lllllllllllllllllIlIIIllIlllIIII + 2, lllllllllllllllllIlIIIllIllIllll + lllllllllllllllllIlIIIllIlllIIlI.getAscent());
            lllllllllllllllllIlIIIllIlllIIII += lllllllllllllllllIlIIIllIlllllIl.width;
        }
        return lllllllllllllllllIlIIIllIlllIlII;
    }
    
    public boolean isAntiAlias() {
        return this.antiAlias;
    }
    
    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font lllllllllllllllllIlIIIlIllllIlII) {
        this.font = lllllllllllllllllIlIIIlIllllIlII;
        this.tex = this.setupTexture(lllllllllllllllllIlIIIlIllllIlII, this.antiAlias, this.fractionalMetrics, this.charData);
    }
    
    public void setFractionalMetrics(final boolean lllllllllllllllllIlIIIlIlllllIll) {
        if (this.fractionalMetrics != lllllllllllllllllIlIIIlIlllllIll) {
            this.fractionalMetrics = lllllllllllllllllIlIIIlIlllllIll;
            this.tex = this.setupTexture(this.font, this.antiAlias, lllllllllllllllllIlIIIlIlllllIll, this.charData);
        }
    }
    
    public void setAntiAlias(final boolean lllllllllllllllllIlIIIllIIIIIlII) {
        if (this.antiAlias != lllllllllllllllllIlIIIllIIIIIlII) {
            this.antiAlias = lllllllllllllllllIlIIIllIIIIIlII;
            this.tex = this.setupTexture(this.font, lllllllllllllllllIlIIIllIIIIIlII, this.fractionalMetrics, this.charData);
        }
    }
    
    protected static class CharData
    {
        public /* synthetic */ int storedY;
        public /* synthetic */ int storedX;
        public /* synthetic */ int width;
        public /* synthetic */ int height;
    }
}
