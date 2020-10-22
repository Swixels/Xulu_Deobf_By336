package com.elementars.eclient.font;

import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import java.awt.image.*;
import net.minecraft.client.renderer.texture.*;

public class XFont
{
    private /* synthetic */ int charOffset;
    private final /* synthetic */ Font font;
    private /* synthetic */ int fontHeight;
    private /* synthetic */ boolean antiAlias;
    public /* synthetic */ int IMAGE_HEIGHT;
    private final /* synthetic */ IntObject[] chars;
    private /* synthetic */ int texID;
    public /* synthetic */ int IMAGE_WIDTH;
    
    public int getStringWidth(final String lIIllIlIlI) {
        int lIIllIlIIl = 0;
        final Exception lIIllIIlIl = (Object)lIIllIlIlI.toCharArray();
        final byte lIIllIIlII = (byte)lIIllIIlIl.length;
        for (float lIIllIIIll = 0; lIIllIIIll < lIIllIIlII; ++lIIllIIIll) {
            final char lIIllIllII = lIIllIIlIl[lIIllIIIll];
            if (lIIllIllII < this.chars.length && lIIllIllII >= '\0') {
                lIIllIlIIl += this.chars[lIIllIllII].width - this.charOffset;
            }
        }
        return lIIllIlIIl / 2;
    }
    
    private void drawQuad(final float lIlIllllll, final float lIlIlllllI, final float lIllIIlIlI, final float lIlIllllII, final float lIllIIlIII, final float lIllIIIlll, final float lIllIIIllI, final float lIllIIIlIl) {
        final float lIllIIIlII = lIllIIlIII / this.IMAGE_WIDTH;
        final float lIllIIIIll = lIllIIIlll / this.IMAGE_HEIGHT;
        final float lIllIIIIlI = lIllIIIllI / this.IMAGE_WIDTH;
        final float lIllIIIIIl = lIllIIIlIl / this.IMAGE_HEIGHT;
        GL11.glBegin(4);
        GL11.glTexCoord2f(lIllIIIlII + lIllIIIIlI, lIllIIIIll);
        GL11.glVertex2d((double)(lIlIllllll + lIllIIlIlI), (double)lIlIlllllI);
        GL11.glTexCoord2f(lIllIIIlII, lIllIIIIll);
        GL11.glVertex2d((double)lIlIllllll, (double)lIlIlllllI);
        GL11.glTexCoord2f(lIllIIIlII, lIllIIIIll + lIllIIIIIl);
        GL11.glVertex2d((double)lIlIllllll, (double)(lIlIlllllI + lIlIllllII));
        GL11.glTexCoord2f(lIllIIIlII, lIllIIIIll + lIllIIIIIl);
        GL11.glVertex2d((double)lIlIllllll, (double)(lIlIlllllI + lIlIllllII));
        GL11.glTexCoord2f(lIllIIIlII + lIllIIIIlI, lIllIIIIll + lIllIIIIIl);
        GL11.glVertex2d((double)(lIlIllllll + lIllIIlIlI), (double)(lIlIlllllI + lIlIllllII));
        GL11.glTexCoord2f(lIllIIIlII + lIllIIIIlI, lIllIIIIll);
        GL11.glVertex2d((double)(lIlIllllll + lIllIIlIlI), (double)lIlIlllllI);
        GL11.glEnd();
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void drawString(final String lIlIlIIlll, double lIlIIlllll, double lIlIIllllI, final Color lIlIlIIlII, final boolean lIlIlIIIll) {
        lIlIIlllll *= (short)2.0;
        lIlIIllllI = (long)(lIlIIllllI * 2.0 - 2.0);
        GL11.glPushMatrix();
        GL11.glScaled(0.25, 0.25, 0.25);
        GlStateManager.bindTexture(this.texID);
        this.glColor(lIlIlIIIll ? lIlIlIIlII.darker().darker().darker() : lIlIlIIlII);
        for (int lIlIlIIIlI = lIlIlIIlll.length(), lIlIlIlIIl = 0; lIlIlIlIIl < lIlIlIIIlI; ++lIlIlIlIIl) {
            final char lIlIlIlIlI = lIlIlIIlll.charAt(lIlIlIlIIl);
            if (lIlIlIlIlI < this.chars.length && lIlIlIlIlI >= '\0') {
                this.drawChar(lIlIlIlIlI, lIlIIlllll, (float)lIlIIllllI);
                lIlIIlllll += (short)(this.chars[lIlIlIlIlI].width - this.charOffset);
            }
        }
        GL11.glPopMatrix();
    }
    
    public void setAntiAlias(final boolean lIIlIllIIl) {
        if (this.antiAlias != lIIlIllIIl) {
            this.antiAlias = lIIlIllIIl;
            this.setupTexture(lIIlIllIIl);
        }
    }
    
    public void drawChar(final char lIllIllllI, final float lIlllIIIIl, final float lIlllIIIII) throws ArrayIndexOutOfBoundsException {
        try {
            this.drawQuad(lIlllIIIIl, lIlllIIIII, (float)this.chars[lIllIllllI].width, (float)this.chars[lIllIllllI].height, (float)this.chars[lIllIllllI].storedX, (float)this.chars[lIllIllllI].storedY, (float)this.chars[lIllIllllI].width, (float)this.chars[lIllIllllI].height);
        }
        catch (Exception lIlllIIlII) {
            lIlllIIlII.printStackTrace();
        }
    }
    
    public void glColor(final Color lIlIIlIIlI) {
        final float lIlIIlIIIl = lIlIIlIIlI.getRed() / 255.0f;
        final float lIlIIlIIII = lIlIIlIIlI.getGreen() / 255.0f;
        final float lIlIIIllll = lIlIIlIIlI.getBlue() / 255.0f;
        final float lIlIIIlllI = lIlIIlIIlI.getAlpha() / 255.0f;
        GL11.glColor4f(lIlIIlIIIl, lIlIIlIIII, lIlIIIllll, lIlIIIlllI);
    }
    
    public int getHeight() {
        return (this.fontHeight - this.charOffset) / 2;
    }
    
    public XFont(final Font llIIllIlII, final boolean llIIllIIII) {
        this.IMAGE_WIDTH = 1024;
        this.IMAGE_HEIGHT = 1024;
        this.chars = new IntObject[2048];
        this.fontHeight = -1;
        this.charOffset = 8;
        this.font = llIIllIlII;
        this.antiAlias = llIIllIIII;
        this.charOffset = 8;
        this.setupTexture(llIIllIIII);
    }
    
    public int getStringHeight(final String lIIlllllII) {
        int lIIllllllI = 1;
        for (final char lIlIIIIIIl : lIIlllllII.toCharArray()) {
            if (lIlIIIIIIl == '\n') {
                ++lIIllllllI;
            }
        }
        return (this.fontHeight - this.charOffset) / 2 * lIIllllllI;
    }
    
    private BufferedImage getFontImage(final char lIllllIlII, final boolean lIllllIIll) {
        final BufferedImage lIlllllllI = new BufferedImage(1, 1, 2);
        final Graphics2D lIllllllIl = (Graphics2D)lIlllllllI.getGraphics();
        if (lIllllIIll) {
            lIllllllIl.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        else {
            lIllllllIl.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
        lIllllllIl.setFont(this.font);
        final FontMetrics lIllllllII = lIllllllIl.getFontMetrics();
        int lIlllllIll = lIllllllII.charWidth(lIllllIlII) + 8;
        if (lIlllllIll <= 0) {
            lIlllllIll = 7;
        }
        int lIlllllIlI = lIllllllII.getHeight() + 3;
        if (lIlllllIlI <= 0) {
            lIlllllIlI = this.font.getSize();
        }
        final BufferedImage lIlllllIIl = new BufferedImage(lIlllllIll, lIlllllIlI, 2);
        final Graphics2D lIlllllIII = (Graphics2D)lIlllllIIl.getGraphics();
        if (lIllllIIll) {
            lIlllllIII.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        else {
            lIlllllIII.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
        lIlllllIII.setFont(this.font);
        lIlllllIII.setColor(Color.WHITE);
        final int lIllllIlll = 3;
        final int lIllllIllI = 1;
        lIlllllIII.drawString(String.valueOf(lIllllIlII), 3, 1 + lIllllllII.getAscent());
        return lIlllllIIl;
    }
    
    public XFont(final Font llIIlllIll, final boolean llIIlllIlI, final int llIIllllIl) {
        this.IMAGE_WIDTH = 1024;
        this.IMAGE_HEIGHT = 1024;
        this.chars = new IntObject[2048];
        this.fontHeight = -1;
        this.charOffset = 8;
        this.font = llIIlllIll;
        this.antiAlias = llIIlllIlI;
        this.charOffset = llIIllllIl;
        this.setupTexture(llIIlllIlI);
    }
    
    public boolean isAntiAlias() {
        return this.antiAlias;
    }
    
    private void setupTexture(final boolean llIIIllllI) {
        if (this.font.getSize() <= 15) {
            this.IMAGE_WIDTH = 256;
            this.IMAGE_HEIGHT = 256;
        }
        if (this.font.getSize() <= 43) {
            this.IMAGE_WIDTH = 512;
            this.IMAGE_HEIGHT = 512;
        }
        else if (this.font.getSize() <= 91) {
            this.IMAGE_WIDTH = 1024;
            this.IMAGE_HEIGHT = 1024;
        }
        else {
            this.IMAGE_WIDTH = 2048;
            this.IMAGE_HEIGHT = 2048;
        }
        final BufferedImage llIIIlllIl = new BufferedImage(this.IMAGE_WIDTH, this.IMAGE_HEIGHT, 2);
        final Graphics2D llIIIlllII = (Graphics2D)llIIIlllIl.getGraphics();
        llIIIlllII.setFont(this.font);
        llIIIlllII.setColor(new Color(255, 255, 255, 0));
        llIIIlllII.fillRect(0, 0, this.IMAGE_WIDTH, this.IMAGE_HEIGHT);
        llIIIlllII.setColor(Color.white);
        int llIIIllIll = 0;
        int llIIIllIlI = 0;
        int llIIIllIIl = 0;
        for (int llIIlIIIIl = 0; llIIlIIIIl < 2048; ++llIIlIIIIl) {
            final char llIIlIIlII = (char)llIIlIIIIl;
            final BufferedImage llIIlIIIll = this.getFontImage(llIIlIIlII, llIIIllllI);
            final IntObject llIIlIIIlI = new IntObject();
            llIIlIIIlI.width = llIIlIIIll.getWidth();
            llIIlIIIlI.height = llIIlIIIll.getHeight();
            if (llIIIllIlI + llIIlIIIlI.width >= this.IMAGE_WIDTH) {
                llIIIllIlI = 0;
                llIIIllIIl += llIIIllIll;
                llIIIllIll = 0;
            }
            llIIlIIIlI.storedX = llIIIllIlI;
            llIIlIIIlI.storedY = llIIIllIIl;
            if (llIIlIIIlI.height > this.fontHeight) {
                this.fontHeight = llIIlIIIlI.height;
            }
            if (llIIlIIIlI.height > llIIIllIll) {
                llIIIllIll = llIIlIIIlI.height;
            }
            this.chars[llIIlIIIIl] = llIIlIIIlI;
            llIIIlllII.drawImage(llIIlIIIll, llIIIllIlI, llIIIllIIl, null);
            llIIIllIlI += llIIlIIIlI.width;
        }
        try {
            this.texID = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), llIIIlllIl, true, true);
        }
        catch (NullPointerException llIIlIIIII) {
            llIIlIIIII.printStackTrace();
        }
    }
    
    private class IntObject
    {
        public /* synthetic */ int storedX;
        public /* synthetic */ int storedY;
        public /* synthetic */ int width;
        public /* synthetic */ int height;
    }
}
