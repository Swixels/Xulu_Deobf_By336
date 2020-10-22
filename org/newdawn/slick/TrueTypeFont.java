package org.newdawn.slick;

import org.newdawn.slick.opengl.renderer.*;
import java.awt.image.*;
import org.newdawn.slick.util.*;
import java.io.*;
import java.util.*;
import org.newdawn.slick.opengl.*;
import java.awt.*;

public class TrueTypeFont implements Font
{
    private /* synthetic */ Map customChars;
    private /* synthetic */ int fontHeight;
    private /* synthetic */ IntObject[] charArray;
    private /* synthetic */ int fontSize;
    private /* synthetic */ int textureHeight;
    private static final /* synthetic */ SGL GL;
    private /* synthetic */ FontMetrics fontMetrics;
    private /* synthetic */ boolean antiAlias;
    private /* synthetic */ int textureWidth;
    private /* synthetic */ java.awt.Font font;
    private /* synthetic */ Texture fontTexture;
    
    static {
        GL = Renderer.get();
    }
    
    public int getHeight() {
        return this.fontHeight;
    }
    
    @Override
    public void drawString(final float lllllllllllllllllIlIIIIlllIIlllI, final float lllllllllllllllllIlIIIIlllIIIlII, final String lllllllllllllllllIlIIIIlllIIIIll, final Color lllllllllllllllllIlIIIIlllIIlIll, final int lllllllllllllllllIlIIIIlllIIIIIl, final int lllllllllllllllllIlIIIIlllIIlIIl) {
        lllllllllllllllllIlIIIIlllIIlIll.bind();
        this.fontTexture.bind();
        IntObject lllllllllllllllllIlIIIIlllIIlIII = null;
        TrueTypeFont.GL.glBegin(7);
        int lllllllllllllllllIlIIIIlllIIIlll = 0;
        for (int lllllllllllllllllIlIIIIlllIlIIII = 0; lllllllllllllllllIlIIIIlllIlIIII < lllllllllllllllllIlIIIIlllIIIIll.length(); ++lllllllllllllllllIlIIIIlllIlIIII) {
            final int lllllllllllllllllIlIIIIlllIlIIIl = lllllllllllllllllIlIIIIlllIIIIll.charAt(lllllllllllllllllIlIIIIlllIlIIII);
            if (lllllllllllllllllIlIIIIlllIlIIIl < 256) {
                lllllllllllllllllIlIIIIlllIIlIII = this.charArray[lllllllllllllllllIlIIIIlllIlIIIl];
            }
            else {
                lllllllllllllllllIlIIIIlllIIlIII = this.customChars.get(new Character((char)lllllllllllllllllIlIIIIlllIlIIIl));
            }
            if (lllllllllllllllllIlIIIIlllIIlIII != null) {
                if (lllllllllllllllllIlIIIIlllIlIIII >= lllllllllllllllllIlIIIIlllIIIIIl || lllllllllllllllllIlIIIIlllIlIIII <= lllllllllllllllllIlIIIIlllIIlIIl) {
                    this.drawQuad(lllllllllllllllllIlIIIIlllIIlllI + lllllllllllllllllIlIIIIlllIIIlll, lllllllllllllllllIlIIIIlllIIIlII, lllllllllllllllllIlIIIIlllIIlllI + lllllllllllllllllIlIIIIlllIIIlll + lllllllllllllllllIlIIIIlllIIlIII.width, lllllllllllllllllIlIIIIlllIIIlII + lllllllllllllllllIlIIIIlllIIlIII.height, (float)lllllllllllllllllIlIIIIlllIIlIII.storedX, (float)lllllllllllllllllIlIIIIlllIIlIII.storedY, (float)(lllllllllllllllllIlIIIIlllIIlIII.storedX + lllllllllllllllllIlIIIIlllIIlIII.width), (float)(lllllllllllllllllIlIIIIlllIIlIII.storedY + lllllllllllllllllIlIIIIlllIIlIII.height));
                }
                lllllllllllllllllIlIIIIlllIIIlll += lllllllllllllllllIlIIIIlllIIlIII.width;
            }
        }
        TrueTypeFont.GL.glEnd();
    }
    
    private void drawQuad(final float lllllllllllllllllIlIIIlIIIlIlIII, final float lllllllllllllllllIlIIIlIIIIlIllI, final float lllllllllllllllllIlIIIlIIIlIIllI, final float lllllllllllllllllIlIIIlIIIIlIlII, final float lllllllllllllllllIlIIIlIIIIlIIll, final float lllllllllllllllllIlIIIlIIIIlIIlI, final float lllllllllllllllllIlIIIlIIIIlIIIl, final float lllllllllllllllllIlIIIlIIIIlIIII) {
        final float lllllllllllllllllIlIIIlIIIlIIIII = lllllllllllllllllIlIIIlIIIlIIllI - lllllllllllllllllIlIIIlIIIlIlIII;
        final float lllllllllllllllllIlIIIlIIIIlllll = lllllllllllllllllIlIIIlIIIIlIlII - lllllllllllllllllIlIIIlIIIIlIllI;
        final float lllllllllllllllllIlIIIlIIIIllllI = lllllllllllllllllIlIIIlIIIIlIIll / this.textureWidth;
        final float lllllllllllllllllIlIIIlIIIIlllIl = lllllllllllllllllIlIIIlIIIIlIIlI / this.textureHeight;
        final float lllllllllllllllllIlIIIlIIIIlllII = lllllllllllllllllIlIIIlIIIIlIIIl - lllllllllllllllllIlIIIlIIIIlIIll;
        final float lllllllllllllllllIlIIIlIIIIllIll = lllllllllllllllllIlIIIlIIIIlIIII - lllllllllllllllllIlIIIlIIIIlIIlI;
        final float lllllllllllllllllIlIIIlIIIIllIlI = lllllllllllllllllIlIIIlIIIIlllII / this.textureWidth;
        final float lllllllllllllllllIlIIIlIIIIllIIl = lllllllllllllllllIlIIIlIIIIllIll / this.textureHeight;
        TrueTypeFont.GL.glTexCoord2f(lllllllllllllllllIlIIIlIIIIllllI, lllllllllllllllllIlIIIlIIIIlllIl);
        TrueTypeFont.GL.glVertex2f(lllllllllllllllllIlIIIlIIIlIlIII, lllllllllllllllllIlIIIlIIIIlIllI);
        TrueTypeFont.GL.glTexCoord2f(lllllllllllllllllIlIIIlIIIIllllI, lllllllllllllllllIlIIIlIIIIlllIl + lllllllllllllllllIlIIIlIIIIllIIl);
        TrueTypeFont.GL.glVertex2f(lllllllllllllllllIlIIIlIIIlIlIII, lllllllllllllllllIlIIIlIIIIlIllI + lllllllllllllllllIlIIIlIIIIlllll);
        TrueTypeFont.GL.glTexCoord2f(lllllllllllllllllIlIIIlIIIIllllI + lllllllllllllllllIlIIIlIIIIllIlI, lllllllllllllllllIlIIIlIIIIlllIl + lllllllllllllllllIlIIIlIIIIllIIl);
        TrueTypeFont.GL.glVertex2f(lllllllllllllllllIlIIIlIIIlIlIII + lllllllllllllllllIlIIIlIIIlIIIII, lllllllllllllllllIlIIIlIIIIlIllI + lllllllllllllllllIlIIIlIIIIlllll);
        TrueTypeFont.GL.glTexCoord2f(lllllllllllllllllIlIIIlIIIIllllI + lllllllllllllllllIlIIIlIIIIllIlI, lllllllllllllllllIlIIIlIIIIlllIl);
        TrueTypeFont.GL.glVertex2f(lllllllllllllllllIlIIIlIIIlIlIII + lllllllllllllllllIlIIIlIIIlIIIII, lllllllllllllllllIlIIIlIIIIlIllI);
    }
    
    public TrueTypeFont(final java.awt.Font lllllllllllllllllIlIIIlIIlllllll, final boolean lllllllllllllllllIlIIIlIIllllllI) {
        this(lllllllllllllllllIlIIIlIIlllllll, lllllllllllllllllIlIIIlIIllllllI, null);
    }
    
    private void createSet(final char[] lllllllllllllllllIlIIIlIIlIIIlIl) {
        if (lllllllllllllllllIlIIIlIIlIIIlIl != null && lllllllllllllllllIlIIIlIIlIIIlIl.length > 0) {
            this.textureWidth *= 2;
        }
        try {
            final BufferedImage lllllllllllllllllIlIIIlIIlIIllll = new BufferedImage(this.textureWidth, this.textureHeight, 2);
            final Graphics2D lllllllllllllllllIlIIIlIIlIIlllI = (Graphics2D)lllllllllllllllllIlIIIlIIlIIllll.getGraphics();
            lllllllllllllllllIlIIIlIIlIIlllI.setColor(new java.awt.Color(255, 255, 255, 1));
            lllllllllllllllllIlIIIlIIlIIlllI.fillRect(0, 0, this.textureWidth, this.textureHeight);
            int lllllllllllllllllIlIIIlIIlIIllIl = 0;
            int lllllllllllllllllIlIIIlIIlIIllII = 0;
            int lllllllllllllllllIlIIIlIIlIIlIll = 0;
            for (int lllllllllllllllllIlIIIlIIlIIlIlI = (lllllllllllllllllIlIIIlIIlIIIlIl != null) ? lllllllllllllllllIlIIIlIIlIIIlIl.length : 0, lllllllllllllllllIlIIIlIIlIlIIII = 0; lllllllllllllllllIlIIIlIIlIlIIII < 256 + lllllllllllllllllIlIIIlIIlIIlIlI; ++lllllllllllllllllIlIIIlIIlIlIIII) {
                final char lllllllllllllllllIlIIIlIIlIlIIll = (lllllllllllllllllIlIIIlIIlIlIIII < 256) ? ((char)lllllllllllllllllIlIIIlIIlIlIIII) : lllllllllllllllllIlIIIlIIlIIIlIl[lllllllllllllllllIlIIIlIIlIlIIII - 256];
                BufferedImage lllllllllllllllllIlIIIlIIlIlIIlI = this.getFontImage(lllllllllllllllllIlIIIlIIlIlIIll);
                final IntObject lllllllllllllllllIlIIIlIIlIlIIIl = new IntObject();
                lllllllllllllllllIlIIIlIIlIlIIIl.width = lllllllllllllllllIlIIIlIIlIlIIlI.getWidth();
                lllllllllllllllllIlIIIlIIlIlIIIl.height = lllllllllllllllllIlIIIlIIlIlIIlI.getHeight();
                if (lllllllllllllllllIlIIIlIIlIIllII + lllllllllllllllllIlIIIlIIlIlIIIl.width >= this.textureWidth) {
                    lllllllllllllllllIlIIIlIIlIIllII = 0;
                    lllllllllllllllllIlIIIlIIlIIlIll += lllllllllllllllllIlIIIlIIlIIllIl;
                    lllllllllllllllllIlIIIlIIlIIllIl = 0;
                }
                lllllllllllllllllIlIIIlIIlIlIIIl.storedX = lllllllllllllllllIlIIIlIIlIIllII;
                lllllllllllllllllIlIIIlIIlIlIIIl.storedY = lllllllllllllllllIlIIIlIIlIIlIll;
                if (lllllllllllllllllIlIIIlIIlIlIIIl.height > this.fontHeight) {
                    this.fontHeight = lllllllllllllllllIlIIIlIIlIlIIIl.height;
                }
                if (lllllllllllllllllIlIIIlIIlIlIIIl.height > lllllllllllllllllIlIIIlIIlIIllIl) {
                    lllllllllllllllllIlIIIlIIlIIllIl = lllllllllllllllllIlIIIlIIlIlIIIl.height;
                }
                lllllllllllllllllIlIIIlIIlIIlllI.drawImage(lllllllllllllllllIlIIIlIIlIlIIlI, lllllllllllllllllIlIIIlIIlIIllII, lllllllllllllllllIlIIIlIIlIIlIll, null);
                lllllllllllllllllIlIIIlIIlIIllII += lllllllllllllllllIlIIIlIIlIlIIIl.width;
                if (lllllllllllllllllIlIIIlIIlIlIIII < 256) {
                    this.charArray[lllllllllllllllllIlIIIlIIlIlIIII] = lllllllllllllllllIlIIIlIIlIlIIIl;
                }
                else {
                    this.customChars.put(new Character(lllllllllllllllllIlIIIlIIlIlIIll), lllllllllllllllllIlIIIlIIlIlIIIl);
                }
                lllllllllllllllllIlIIIlIIlIlIIlI = null;
            }
            this.fontTexture = BufferedImageUtil.getTexture(this.font.toString(), lllllllllllllllllIlIIIlIIlIIllll);
        }
        catch (IOException lllllllllllllllllIlIIIlIIlIIlIIl) {
            System.err.println("Failed to create font.");
            lllllllllllllllllIlIIIlIIlIIlIIl.printStackTrace();
        }
    }
    
    public TrueTypeFont(final java.awt.Font lllllllllllllllllIlIIIlIlIIIlIIl, final boolean lllllllllllllllllIlIIIlIlIIIlIII, final char[] lllllllllllllllllIlIIIlIlIIIIlll) {
        this.charArray = new IntObject[256];
        this.customChars = new HashMap();
        this.fontSize = 0;
        this.fontHeight = 0;
        this.textureWidth = 512;
        this.textureHeight = 512;
        GLUtils.checkGLContext();
        this.font = lllllllllllllllllIlIIIlIlIIIlIIl;
        this.fontSize = lllllllllllllllllIlIIIlIlIIIlIIl.getSize();
        this.antiAlias = lllllllllllllllllIlIIIlIlIIIlIII;
        this.createSet(lllllllllllllllllIlIIIlIlIIIIlll);
    }
    
    @Override
    public int getWidth(final String lllllllllllllllllIlIIIIllllllIlI) {
        int lllllllllllllllllIlIIIIllllllllI = 0;
        IntObject lllllllllllllllllIlIIIIlllllllIl = null;
        int lllllllllllllllllIlIIIIlllllllII = 0;
        for (int lllllllllllllllllIlIIIlIIIIIIIIl = 0; lllllllllllllllllIlIIIlIIIIIIIIl < lllllllllllllllllIlIIIIllllllIlI.length(); ++lllllllllllllllllIlIIIlIIIIIIIIl) {
            lllllllllllllllllIlIIIIlllllllII = lllllllllllllllllIlIIIIllllllIlI.charAt(lllllllllllllllllIlIIIlIIIIIIIIl);
            if (lllllllllllllllllIlIIIIlllllllII < 256) {
                lllllllllllllllllIlIIIIlllllllIl = this.charArray[lllllllllllllllllIlIIIIlllllllII];
            }
            else {
                lllllllllllllllllIlIIIIlllllllIl = this.customChars.get(new Character((char)lllllllllllllllllIlIIIIlllllllII));
            }
            if (lllllllllllllllllIlIIIIlllllllIl != null) {
                lllllllllllllllllIlIIIIllllllllI += lllllllllllllllllIlIIIIlllllllIl.width;
            }
        }
        return lllllllllllllllllIlIIIIllllllllI;
    }
    
    @Override
    public void drawString(final float lllllllllllllllllIlIIIIllIllIIlI, final float lllllllllllllllllIlIIIIllIllIIIl, final String lllllllllllllllllIlIIIIllIllIIII) {
        this.drawString(lllllllllllllllllIlIIIIllIllIIlI, lllllllllllllllllIlIIIIllIllIIIl, lllllllllllllllllIlIIIIllIllIIII, Color.white);
    }
    
    @Override
    public int getLineHeight() {
        return this.fontHeight;
    }
    
    @Override
    public int getHeight(final String lllllllllllllllllIlIIIIlllllIIII) {
        return this.fontHeight;
    }
    
    private BufferedImage getFontImage(final char lllllllllllllllllIlIIIlIIlllIIlI) {
        final BufferedImage lllllllllllllllllIlIIIlIIlllIIIl = new BufferedImage(1, 1, 2);
        final Graphics2D lllllllllllllllllIlIIIlIIlllIIII = (Graphics2D)lllllllllllllllllIlIIIlIIlllIIIl.getGraphics();
        if (this.antiAlias) {
            lllllllllllllllllIlIIIlIIlllIIII.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        lllllllllllllllllIlIIIlIIlllIIII.setFont(this.font);
        this.fontMetrics = lllllllllllllllllIlIIIlIIlllIIII.getFontMetrics();
        int lllllllllllllllllIlIIIlIIllIllll = this.fontMetrics.charWidth(lllllllllllllllllIlIIIlIIlllIIlI);
        if (lllllllllllllllllIlIIIlIIllIllll <= 0) {
            lllllllllllllllllIlIIIlIIllIllll = 1;
        }
        int lllllllllllllllllIlIIIlIIllIlllI = this.fontMetrics.getHeight();
        if (lllllllllllllllllIlIIIlIIllIlllI <= 0) {
            lllllllllllllllllIlIIIlIIllIlllI = this.fontSize;
        }
        final BufferedImage lllllllllllllllllIlIIIlIIllIllIl = new BufferedImage(lllllllllllllllllIlIIIlIIllIllll, lllllllllllllllllIlIIIlIIllIlllI, 2);
        final Graphics2D lllllllllllllllllIlIIIlIIllIllII = (Graphics2D)lllllllllllllllllIlIIIlIIllIllIl.getGraphics();
        if (this.antiAlias) {
            lllllllllllllllllIlIIIlIIllIllII.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        lllllllllllllllllIlIIIlIIllIllII.setFont(this.font);
        lllllllllllllllllIlIIIlIIllIllII.setColor(java.awt.Color.WHITE);
        final int lllllllllllllllllIlIIIlIIllIlIll = 0;
        final int lllllllllllllllllIlIIIlIIllIlIlI = 0;
        lllllllllllllllllIlIIIlIIllIllII.drawString(String.valueOf(lllllllllllllllllIlIIIlIIlllIIlI), lllllllllllllllllIlIIIlIIllIlIll, lllllllllllllllllIlIIIlIIllIlIlI + this.fontMetrics.getAscent());
        return lllllllllllllllllIlIIIlIIllIllIl;
    }
    
    @Override
    public void drawString(final float lllllllllllllllllIlIIIIllllIIIII, final float lllllllllllllllllIlIIIIlllIlllll, final String lllllllllllllllllIlIIIIlllIllllI, final Color lllllllllllllllllIlIIIIllllIIIlI) {
        this.drawString(lllllllllllllllllIlIIIIllllIIIII, lllllllllllllllllIlIIIIlllIlllll, lllllllllllllllllIlIIIIlllIllllI, lllllllllllllllllIlIIIIllllIIIlI, 0, lllllllllllllllllIlIIIIlllIllllI.length() - 1);
    }
    
    private class IntObject
    {
        public /* synthetic */ int storedY;
        public /* synthetic */ int storedX;
        public /* synthetic */ int width;
        public /* synthetic */ int height;
    }
}
