package org.newdawn.slick;

import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.opengl.pbuffer.*;
import org.newdawn.slick.opengl.renderer.*;
import java.io.*;

public class Image implements Renderable
{
    protected /* synthetic */ Texture texture;
    protected /* synthetic */ byte[] pixelData;
    protected /* synthetic */ boolean destroyed;
    protected /* synthetic */ int height;
    protected /* synthetic */ float centerX;
    protected /* synthetic */ Color[] corners;
    protected /* synthetic */ float textureHeight;
    protected /* synthetic */ float textureWidth;
    protected /* synthetic */ float textureOffsetY;
    protected /* synthetic */ String name;
    protected static /* synthetic */ Image inUse;
    private /* synthetic */ boolean flipped;
    private /* synthetic */ Color transparent;
    protected /* synthetic */ float alpha;
    protected /* synthetic */ String ref;
    protected static /* synthetic */ SGL GL;
    protected /* synthetic */ float angle;
    protected /* synthetic */ int width;
    protected /* synthetic */ boolean inited;
    protected /* synthetic */ float centerY;
    private /* synthetic */ int filter;
    protected /* synthetic */ float textureOffsetX;
    
    public void clampTexture() {
        if (Image.GL.canTextureMirrorClamp()) {
            Image.GL.glTexParameteri(3553, 10242, 34627);
            Image.GL.glTexParameteri(3553, 10243, 34627);
        }
        else {
            Image.GL.glTexParameteri(3553, 10242, 10496);
            Image.GL.glTexParameteri(3553, 10243, 10496);
        }
    }
    
    public float getRotation() {
        return this.angle;
    }
    
    public void setFilter(final int lIlIIIllllIlI) {
        this.filter = ((lIlIIIllllIlI == 1) ? 9729 : 9728);
        this.texture.bind();
        Image.GL.glTexParameteri(3553, 10241, this.filter);
        Image.GL.glTexParameteri(3553, 10240, this.filter);
    }
    
    public Texture getTexture() {
        return this.texture;
    }
    
    public Image getScaledCopy(final float lIIIllllIIllI) {
        this.init();
        return this.getScaledCopy((int)(this.width * lIIIllllIIllI), (int)(this.height * lIIIllllIIllI));
    }
    
    Image(final ImageBuffer lIlIIIIllllII, final int lIlIIIIlllIII) {
        this((ImageData)lIlIIIIllllII, lIlIIIIlllIII);
        TextureImpl.bindNone();
    }
    
    public float getTextureHeight() {
        this.init();
        return this.textureHeight;
    }
    
    public void draw(final float lIIllIlIllIIl, final float lIIllIlIllIII, final float lIIllIlIlIlll, final float lIIllIlIlIIIl) {
        this.init();
        this.draw(lIIllIlIllIIl, lIIllIlIllIII, lIIllIlIlIlll, lIIllIlIlIIIl, Color.white);
    }
    
    public void draw(final float lIIlIIllIlIIl, final float lIIlIIlllIIlI, final float lIIlIIllIIlll, final float lIIlIIlllIIII, final float lIIlIIllIllll, final float lIIlIIllIIlII, final float lIIlIIllIIIll, final float lIIlIIllIIIlI, Color lIIlIIllIIIIl) {
        this.init();
        if (this.alpha != 1.0f) {
            if (lIIlIIllIIIIl == null) {
                lIIlIIllIIIIl = Color.white;
            }
            final Color color;
            lIIlIIllIIIIl = (color = new Color((Color)lIIlIIllIIIIl));
            color.a *= this.alpha;
        }
        ((Color)lIIlIIllIIIIl).bind();
        this.texture.bind();
        Image.GL.glTranslatef(lIIlIIllIlIIl, lIIlIIlllIIlI, 0.0f);
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glBegin(7);
        this.drawEmbedded(0.0f, 0.0f, lIIlIIllIIlll - lIIlIIllIlIIl, lIIlIIlllIIII - lIIlIIlllIIlI, lIIlIIllIllll, lIIlIIllIIlII, lIIlIIllIIIll, lIIlIIllIIIlI);
        Image.GL.glEnd();
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(-this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glTranslatef(-lIIlIIllIlIIl, -lIIlIIlllIIlI, 0.0f);
    }
    
    public int getWidth() {
        this.init();
        return this.width;
    }
    
    public Image getFlippedCopy(final boolean lIIIlllIlIIIl, final boolean lIIIlllIlIIII) {
        this.init();
        final Image lIIIlllIIllll = this.copy();
        if (lIIIlllIlIIIl) {
            lIIIlllIIllll.textureOffsetX = this.textureOffsetX + this.textureWidth;
            lIIIlllIIllll.textureWidth = -this.textureWidth;
        }
        if (lIIIlllIlIIII) {
            lIIIlllIIllll.textureOffsetY = this.textureOffsetY + this.textureHeight;
            lIIIlllIIllll.textureHeight = -this.textureHeight;
        }
        return lIIIlllIIllll;
    }
    
    public Image(final String lIlIIlIIIlIIl, final boolean lIlIIlIIIlIII, final int lIlIIlIIIIIlI, final Color lIlIIlIIIIIIl) throws SlickException {
        this.alpha = 1.0f;
        this.inited = false;
        this.filter = 9729;
        this.filter = ((lIlIIlIIIIIlI == 1) ? 9729 : 9728);
        this.transparent = lIlIIlIIIIIIl;
        this.flipped = lIlIIlIIIlIII;
        try {
            this.ref = lIlIIlIIIlIIl;
            int[] lIlIIlIIIllII = null;
            if (lIlIIlIIIIIIl != null) {
                lIlIIlIIIllII = new int[] { (int)(lIlIIlIIIIIIl.r * 255.0f), (int)(lIlIIlIIIIIIl.g * 255.0f), (int)(lIlIIlIIIIIIl.b * 255.0f) };
            }
            this.texture = InternalTextureLoader.get().getTexture(lIlIIlIIIlIIl, lIlIIlIIIlIII, this.filter, lIlIIlIIIllII);
        }
        catch (IOException lIlIIlIIIlIll) {
            Log.error(lIlIIlIIIlIll);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load image from: ").append(lIlIIlIIIlIIl)), lIlIIlIIIlIll);
        }
    }
    
    public void endUse() {
        if (Image.inUse != this) {
            throw new RuntimeException("The sprite sheet is not currently in use");
        }
        Image.inUse = null;
        Image.GL.glEnd();
    }
    
    public Image getSubImage(final int lIIlIllIIIIIl, final int lIIlIllIIIIII, final int lIIlIlIllIlIl, final int lIIlIlIlllllI) {
        this.init();
        final float lIIlIlIllllIl = lIIlIllIIIIIl / (float)this.width * this.textureWidth + this.textureOffsetX;
        final float lIIlIlIllllII = lIIlIllIIIIII / (float)this.height * this.textureHeight + this.textureOffsetY;
        final float lIIlIlIlllIll = lIIlIlIllIlIl / (float)this.width * this.textureWidth;
        final float lIIlIlIlllIlI = lIIlIlIlllllI / (float)this.height * this.textureHeight;
        final Image lIIlIlIlllIIl = new Image();
        lIIlIlIlllIIl.inited = true;
        lIIlIlIlllIIl.texture = this.texture;
        lIIlIlIlllIIl.textureOffsetX = lIIlIlIllllIl;
        lIIlIlIlllIIl.textureOffsetY = lIIlIlIllllII;
        lIIlIlIlllIIl.textureWidth = lIIlIlIlllIll;
        lIIlIlIlllIIl.textureHeight = lIIlIlIlllIlI;
        lIIlIlIlllIIl.width = lIIlIlIllIlIl;
        lIIlIlIlllIIl.height = lIIlIlIlllllI;
        lIIlIlIlllIIl.ref = this.ref;
        lIIlIlIlllIIl.centerX = (float)(lIIlIlIllIlIl / 2);
        lIIlIlIlllIIl.centerY = (float)(lIIlIlIlllllI / 2);
        return lIIlIlIlllIIl;
    }
    
    public void drawFlash(final float lIIlIlllIlIIl, final float lIIlIlllIIlIl) {
        this.drawFlash(lIIlIlllIlIIl, lIIlIlllIIlIl, (float)this.getWidth(), (float)this.getHeight());
    }
    
    public float getCenterOfRotationX() {
        this.init();
        return this.centerX;
    }
    
    public Image(final int lIlIIIllIIlIl, final int lIlIIIllIlIII, final int lIlIIIllIIIll) throws SlickException {
        this.alpha = 1.0f;
        this.inited = false;
        this.filter = 9729;
        this.ref = super.toString();
        this.filter = ((lIlIIIllIIIll == 1) ? 9729 : 9728);
        try {
            this.texture = InternalTextureLoader.get().createTexture(lIlIIIllIIlIl, lIlIIIllIlIII, this.filter);
        }
        catch (IOException lIlIIIllIlIll) {
            Log.error(lIlIIIllIlIll);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to create empty image ").append(lIlIIIllIIlIl).append("x").append(lIlIIIllIlIII)));
        }
        this.init();
    }
    
    public Image(final ImageData lIlIIIIllIIlI) {
        this(lIlIIIIllIIlI, 1);
    }
    
    public String getResourceReference() {
        return this.ref;
    }
    
    public Image(final int lIlIIIlllIlIl, final int lIlIIIlllIIIl) throws SlickException {
        this(lIlIIIlllIlIl, lIlIIIlllIIIl, 2);
    }
    
    public void drawSheared(final float lIIllIIllIlII, final float lIIllIIllIIll, final float lIIllIIllIIlI, final float lIIllIIllIlll, Color lIIllIIllIIII) {
        if (this.alpha != 1.0f) {
            if (lIIllIIllIIII == null) {
                lIIllIIllIIII = Color.white;
            }
            final Color color;
            lIIllIIllIIII = (color = new Color(lIIllIIllIIII));
            color.a *= this.alpha;
        }
        if (lIIllIIllIIII != null) {
            lIIllIIllIIII.bind();
        }
        this.texture.bind();
        Image.GL.glTranslatef(lIIllIIllIlII, lIIllIIllIIll, 0.0f);
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glBegin(7);
        this.init();
        Image.GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
        Image.GL.glVertex3f(0.0f, 0.0f, 0.0f);
        Image.GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
        Image.GL.glVertex3f(lIIllIIllIIlI, (float)this.height, 0.0f);
        Image.GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
        Image.GL.glVertex3f(this.width + lIIllIIllIIlI, this.height + lIIllIIllIlll, 0.0f);
        Image.GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
        Image.GL.glVertex3f((float)this.width, lIIllIIllIlll, 0.0f);
        Image.GL.glEnd();
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(-this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glTranslatef(-lIIllIIllIlII, -lIIllIIllIIll, 0.0f);
    }
    
    public void destroy() throws SlickException {
        if (this.isDestroyed()) {
            return;
        }
        this.destroyed = true;
        this.texture.release();
        GraphicsFactory.releaseGraphicsForImage(this);
    }
    
    public void setImageColor(final float lIlIIIIIllIIl, final float lIlIIIIIlIIll, final float lIlIIIIIlIIlI, final float lIlIIIIIlIllI) {
        this.setColor(0, lIlIIIIIllIIl, lIlIIIIIlIIll, lIlIIIIIlIIlI, lIlIIIIIlIllI);
        this.setColor(1, lIlIIIIIllIIl, lIlIIIIIlIIll, lIlIIIIIlIIlI, lIlIIIIIlIllI);
        this.setColor(3, lIlIIIIIllIIl, lIlIIIIIlIIll, lIlIIIIIlIIlI, lIlIIIIIlIllI);
        this.setColor(2, lIlIIIIIllIIl, lIlIIIIIlIIll, lIlIIIIIlIIlI, lIlIIIIIlIllI);
    }
    
    private int translate(final byte lIIIllIllIllI) {
        if (lIIIllIllIllI < 0) {
            return 256 + lIIIllIllIllI;
        }
        return lIIIllIllIllI;
    }
    
    public void draw(final float lIIlIlIlIIllI, final float lIIlIlIIllllI, final float lIIlIlIlIIlII, final float lIIlIlIIlllII, final float lIIlIlIIllIll, final float lIIlIlIlIIIIl) {
        this.draw(lIIlIlIlIIllI, lIIlIlIIllllI, lIIlIlIlIIllI + this.width, lIIlIlIIllllI + this.height, lIIlIlIlIIlII, lIIlIlIIlllII, lIIlIlIIllIll, lIIlIlIlIIIIl);
    }
    
    public void drawWarped(final float lIIIlllllllII, final float lIIIllllllIll, final float lIIIllllllIlI, final float lIIlIIIIIIIlI, final float lIIIllllllIII, final float lIIlIIIIIIIII, final float lIIIlllllIllI, final float lIIIllllllllI) {
        Color.white.bind();
        this.texture.bind();
        Image.GL.glTranslatef(lIIIlllllllII, lIIIllllllIll, 0.0f);
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glBegin(7);
        this.init();
        Image.GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
        Image.GL.glVertex3f(0.0f, 0.0f, 0.0f);
        Image.GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
        Image.GL.glVertex3f(lIIIllllllIlI - lIIIlllllllII, lIIlIIIIIIIlI - lIIIllllllIll, 0.0f);
        Image.GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
        Image.GL.glVertex3f(lIIIllllllIII - lIIIlllllllII, lIIlIIIIIIIII - lIIIllllllIll, 0.0f);
        Image.GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
        Image.GL.glVertex3f(lIIIlllllIllI - lIIIlllllllII, lIIIllllllllI - lIIIllllllIll, 0.0f);
        Image.GL.glEnd();
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(-this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glTranslatef(-lIIIlllllllII, -lIIIllllllIll, 0.0f);
    }
    
    public void drawEmbedded(final float lIIlIIIlIIIII, final float lIIlIIIllIIIl, final float lIIlIIIIllllI, final float lIIlIIIlIllll, final float lIIlIIIIlllII, final float lIIlIIIlIllIl, final float lIIlIIIIllIlI, final float lIIlIIIlIlIll, final Color lIIlIIIlIlIlI) {
        if (lIIlIIIlIlIlI != null) {
            lIIlIIIlIlIlI.bind();
        }
        final float lIIlIIIlIlIIl = lIIlIIIIllllI - lIIlIIIlIIIII;
        final float lIIlIIIlIlIII = lIIlIIIlIllll - lIIlIIIllIIIl;
        final float lIIlIIIlIIlll = lIIlIIIIllIlI - lIIlIIIIlllII;
        final float lIIlIIIlIIllI = lIIlIIIlIlIll - lIIlIIIlIllIl;
        final float lIIlIIIlIIlIl = lIIlIIIIlllII / this.width * this.textureWidth + this.textureOffsetX;
        final float lIIlIIIlIIlII = lIIlIIIlIllIl / this.height * this.textureHeight + this.textureOffsetY;
        final float lIIlIIIlIIIll = lIIlIIIlIIlll / this.width * this.textureWidth;
        final float lIIlIIIlIIIlI = lIIlIIIlIIllI / this.height * this.textureHeight;
        Image.GL.glTexCoord2f(lIIlIIIlIIlIl, lIIlIIIlIIlII);
        Image.GL.glVertex3f(lIIlIIIlIIIII, lIIlIIIllIIIl, 0.0f);
        Image.GL.glTexCoord2f(lIIlIIIlIIlIl, lIIlIIIlIIlII + lIIlIIIlIIIlI);
        Image.GL.glVertex3f(lIIlIIIlIIIII, lIIlIIIllIIIl + lIIlIIIlIlIII, 0.0f);
        Image.GL.glTexCoord2f(lIIlIIIlIIlIl + lIIlIIIlIIIll, lIIlIIIlIIlII + lIIlIIIlIIIlI);
        Image.GL.glVertex3f(lIIlIIIlIIIII + lIIlIIIlIlIIl, lIIlIIIllIIIl + lIIlIIIlIlIII, 0.0f);
        Image.GL.glTexCoord2f(lIIlIIIlIIlIl + lIIlIIIlIIIll, lIIlIIIlIIlII);
        Image.GL.glVertex3f(lIIlIIIlIIIII + lIIlIIIlIlIIl, lIIlIIIllIIIl, 0.0f);
    }
    
    protected Image(final Image lIlIIllIIIIII) {
        this.alpha = 1.0f;
        this.inited = false;
        this.filter = 9729;
        this.width = lIlIIllIIIIII.getWidth();
        this.height = lIlIIllIIIIII.getHeight();
        this.texture = lIlIIllIIIIII.texture;
        this.textureWidth = lIlIIllIIIIII.textureWidth;
        this.textureHeight = lIlIIllIIIIII.textureHeight;
        this.ref = lIlIIllIIIIII.ref;
        this.textureOffsetX = lIlIIllIIIIII.textureOffsetX;
        this.textureOffsetY = lIlIIllIIIIII.textureOffsetY;
        this.centerX = (float)(this.width / 2);
        this.centerY = (float)(this.height / 2);
        this.inited = true;
    }
    
    public void setAlpha(final float lIIlIllIlIlIl) {
        this.alpha = lIIlIllIlIlIl;
    }
    
    public void setCenterOfRotation(final float lIIllIIIIlIlI, final float lIIllIIIIIllI) {
        this.centerX = lIIllIIIIlIlI;
        this.centerY = lIIllIIIIIllI;
    }
    
    protected Image() {
        this.alpha = 1.0f;
        this.inited = false;
        this.filter = 9729;
    }
    
    public void draw(final float lIIllIIlIlIII, final float lIIllIIlIIlll, final float lIIllIIlIIllI, final float lIIllIIlIIlIl, Color lIIllIIIllllI) {
        if (this.alpha != 1.0f) {
            if (lIIllIIIllllI == null) {
                lIIllIIIllllI = Color.white;
            }
            final Color color;
            lIIllIIIllllI = (color = new Color((Color)lIIllIIIllllI));
            color.a *= this.alpha;
        }
        if (lIIllIIIllllI != null) {
            ((Color)lIIllIIIllllI).bind();
        }
        this.texture.bind();
        Image.GL.glTranslatef(lIIllIIlIlIII, lIIllIIlIIlll, 0.0f);
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glBegin(7);
        this.drawEmbedded(0.0f, 0.0f, lIIllIIlIIllI, lIIllIIlIIlIl);
        Image.GL.glEnd();
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(-this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glTranslatef(-lIIllIIlIlIII, -lIIllIIlIIlll, 0.0f);
    }
    
    public int getHeight() {
        this.init();
        return this.height;
    }
    
    public int getFilter() {
        return this.filter;
    }
    
    protected final void init() {
        if (this.inited) {
            return;
        }
        this.inited = true;
        if (this.texture != null) {
            this.width = this.texture.getImageWidth();
            this.height = this.texture.getImageHeight();
            this.textureOffsetX = 0.0f;
            this.textureOffsetY = 0.0f;
            this.textureWidth = this.texture.getWidth();
            this.textureHeight = this.texture.getHeight();
        }
        this.initImpl();
        this.centerX = (float)(this.width / 2);
        this.centerY = (float)(this.height / 2);
    }
    
    public void rotate(final float lIIlIllIIllIl) {
        this.angle += lIIlIllIIllIl;
        this.angle %= 360.0f;
    }
    
    public void setTexture(final Texture lIIIllIlllIll) {
        this.texture = lIIIllIlllIll;
        this.reinit();
    }
    
    public boolean isDestroyed() {
        return this.destroyed;
    }
    
    public Image(final String lIlIIlIIlIlIl, final boolean lIlIIlIIllIII, final int lIlIIlIIlIlll) throws SlickException {
        this(lIlIIlIIlIlIl, lIlIIlIIllIII, lIlIIlIIlIlll, null);
    }
    
    public void draw(final float lIIllIllIIIll, final float lIIllIllIIlll, final float lIIllIllIIIIl, final Color lIIllIllIIIII) {
        this.init();
        this.draw(lIIllIllIIIll, lIIllIllIIlll, this.width * lIIllIllIIIIl, this.height * lIIllIllIIIIl, lIIllIllIIIII);
    }
    
    public void flushPixelData() {
        this.pixelData = null;
    }
    
    public Color getColor(int lIIIllIlIIlll, int lIIIllIlIIllI) {
        if (this.pixelData == null) {
            this.pixelData = this.texture.getTextureData();
        }
        final int lIIIllIlIlIll = (int)(this.textureOffsetX * this.texture.getTextureWidth());
        final int lIIIllIlIlIlI = (int)(this.textureOffsetY * this.texture.getTextureHeight());
        if (this.textureWidth < 0.0f) {
            lIIIllIlIIlll = lIIIllIlIlIll - lIIIllIlIIlll;
        }
        else {
            lIIIllIlIIlll += lIIIllIlIlIll;
        }
        if (this.textureHeight < 0.0f) {
            lIIIllIlIIllI = lIIIllIlIlIlI - lIIIllIlIIllI;
        }
        else {
            lIIIllIlIIllI = lIIIllIlIlIlI + lIIIllIlIIllI;
        }
        int lIIIllIlIlIIl = (int)(lIIIllIlIIlll + lIIIllIlIIllI * this.texture.getTextureWidth());
        lIIIllIlIlIIl *= (this.texture.hasAlpha() ? 4 : 3);
        if (this.texture.hasAlpha()) {
            return new Color(this.translate(this.pixelData[lIIIllIlIlIIl]), this.translate(this.pixelData[lIIIllIlIlIIl + 1]), this.translate(this.pixelData[lIIIllIlIlIIl + 2]), this.translate(this.pixelData[lIIIllIlIlIIl + 3]));
        }
        return new Color(this.translate(this.pixelData[lIIIllIlIlIIl]), this.translate(this.pixelData[lIIIllIlIlIIl + 1]), this.translate(this.pixelData[lIIIllIlIlIIl + 2]));
    }
    
    public void drawFlash(final float lIIllIIIlIlll, final float lIIllIIIlIIIl, final float lIIllIIIlIlIl, final float lIIllIIIlIlII) {
        this.drawFlash(lIIllIIIlIlll, lIIllIIIlIIIl, lIIllIIIlIlIl, lIIllIIIlIlII, Color.white);
    }
    
    public void setColor(final int lIIllllllllIl, final float lIIllllllIllI, final float lIIllllllIlIl, final float lIIllllllIlII, final float lIIlllllllIIl) {
        if (this.corners == null) {
            this.corners = new Color[] { new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f) };
        }
        this.corners[lIIllllllllIl].r = lIIllllllIllI;
        this.corners[lIIllllllllIl].g = lIIllllllIlIl;
        this.corners[lIIllllllllIl].b = lIIllllllIlII;
        this.corners[lIIllllllllIl].a = lIIlllllllIIl;
    }
    
    public void draw() {
        this.draw(0.0f, 0.0f);
    }
    
    public void draw(final float lIIlIlIIIIllI, final float lIIlIlIIIIlIl, final float lIIlIlIIIIlII, final float lIIlIlIIIIIll, final float lIIlIlIIIIIlI, final float lIIlIlIIIIIIl, final float lIIlIlIIIlIIl, final float lIIlIIlllllll) {
        this.draw(lIIlIlIIIIllI, lIIlIlIIIIlIl, lIIlIlIIIIlII, lIIlIlIIIIIll, lIIlIlIIIIIlI, lIIlIlIIIIIIl, lIIlIlIIIlIIl, lIIlIIlllllll, Color.white);
    }
    
    public float getTextureOffsetY() {
        this.init();
        return this.textureOffsetY;
    }
    
    public void drawFlash(final float lIIlIlllllIII, final float lIIlIllllIlll, final float lIIlIllllIIII, final float lIIlIlllIllll, final Color lIIlIlllIlllI) {
        this.init();
        lIIlIlllIlllI.bind();
        this.texture.bind();
        if (Image.GL.canSecondaryColor()) {
            Image.GL.glEnable(33880);
            Image.GL.glSecondaryColor3ubEXT((byte)(lIIlIlllIlllI.r * 255.0f), (byte)(lIIlIlllIlllI.g * 255.0f), (byte)(lIIlIlllIlllI.b * 255.0f));
        }
        Image.GL.glTexEnvi(8960, 8704, 8448);
        Image.GL.glTranslatef(lIIlIlllllIII, lIIlIllllIlll, 0.0f);
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glBegin(7);
        this.drawEmbedded(0.0f, 0.0f, lIIlIllllIIII, lIIlIlllIllll);
        Image.GL.glEnd();
        if (this.angle != 0.0f) {
            Image.GL.glTranslatef(this.centerX, this.centerY, 0.0f);
            Image.GL.glRotatef(-this.angle, 0.0f, 0.0f, 1.0f);
            Image.GL.glTranslatef(-this.centerX, -this.centerY, 0.0f);
        }
        Image.GL.glTranslatef(-lIIlIlllllIII, -lIIlIllllIlll, 0.0f);
        if (Image.GL.canSecondaryColor()) {
            Image.GL.glDisable(33880);
        }
    }
    
    public Image(final String lIlIIlIlIIIll, final boolean lIlIIlIlIIIlI) throws SlickException {
        this(lIlIIlIlIIIll, lIlIIlIlIIIlI, 1);
    }
    
    public void draw(final float lIIlllIIllIII, final float lIIlllIIlIlll, final Color lIIlllIIllIlI) {
        this.init();
        this.draw(lIIlllIIllIII, lIIlllIIlIlll, (float)this.width, (float)this.height, lIIlllIIllIlI);
    }
    
    public void ensureInverted() {
        if (this.textureHeight > 0.0f) {
            this.textureOffsetY += this.textureHeight;
            this.textureHeight = -this.textureHeight;
        }
    }
    
    public float getCenterOfRotationY() {
        this.init();
        return this.centerY;
    }
    
    @Override
    public void draw(final float lIIlllIlIIllI, final float lIIlllIlIIlIl) {
        this.init();
        this.draw(lIIlllIlIIllI, lIIlllIlIIlIl, (float)this.width, (float)this.height);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setColor(final int lIIlllllIIlll, final float lIIlllllIlIll, final float lIIlllllIlIlI, final float lIIlllllIIlII) {
        if (this.corners == null) {
            this.corners = new Color[] { new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f) };
        }
        this.corners[lIIlllllIIlll].r = lIIlllllIlIll;
        this.corners[lIIlllllIIlll].g = lIIlllllIlIlI;
        this.corners[lIIlllllIIlll].b = lIIlllllIIlII;
    }
    
    public Image(final String lIlIIlIllIIIl) throws SlickException {
        this(lIlIIlIllIIIl, false);
    }
    
    public Image copy() {
        this.init();
        return this.getSubImage(0, 0, this.width, this.height);
    }
    
    public void drawEmbedded(final float lIIlIIlIIllIl, final float lIIlIIlIlIlIl, final float lIIlIIlIIlIll, final float lIIlIIlIlIIll, final float lIIlIIlIlIIlI, final float lIIlIIlIIlIII, final float lIIlIIlIIIlll, final float lIIlIIlIIllll) {
        this.drawEmbedded(lIIlIIlIIllIl, lIIlIIlIlIlIl, lIIlIIlIIlIll, lIIlIIlIlIIll, lIIlIIlIlIIlI, lIIlIIlIIlIII, lIIlIIlIIIlll, lIIlIIlIIllll, null);
    }
    
    @Override
    public String toString() {
        this.init();
        return String.valueOf(new StringBuilder().append("[Image ").append(this.ref).append(" ").append(this.width).append("x").append(this.height).append("  ").append(this.textureOffsetX).append(",").append(this.textureOffsetY).append(",").append(this.textureWidth).append(",").append(this.textureHeight).append("]"));
    }
    
    Image(final ImageBuffer lIlIIIlIIIIIl) {
        this(lIlIIIlIIIIIl, 1);
        TextureImpl.bindNone();
    }
    
    public void startUse() {
        if (Image.inUse != null) {
            throw new RuntimeException("Attempt to start use of a sprite sheet before ending use with another - see endUse()");
        }
        (Image.inUse = this).init();
        Color.white.bind();
        this.texture.bind();
        Image.GL.glBegin(7);
    }
    
    public void drawCentered(final float lIIlllIlIllII, final float lIIlllIlIlIll) {
        this.draw(lIIlllIlIllII - this.getWidth() / 2, lIIlllIlIlIll - this.getHeight() / 2);
    }
    
    protected void reinit() {
        this.inited = false;
        this.init();
    }
    
    protected void initImpl() {
    }
    
    static {
        BOTTOM_RIGHT = 2;
        FILTER_LINEAR = 1;
        TOP_RIGHT = 1;
        FILTER_NEAREST = 2;
        BOTTOM_LEFT = 3;
        TOP_LEFT = 0;
        Image.GL = Renderer.get();
    }
    
    public Image(final ImageData lIlIIIIlIlIII, final int lIlIIIIlIIlll) {
        this.alpha = 1.0f;
        this.inited = false;
        this.filter = 9729;
        try {
            this.filter = ((lIlIIIIlIIlll == 1) ? 9729 : 9728);
            this.texture = InternalTextureLoader.get().getTexture(lIlIIIIlIlIII, this.filter);
            this.ref = this.texture.toString();
        }
        catch (IOException lIlIIIIlIllIl) {
            Log.error(lIlIIIIlIllIl);
        }
    }
    
    public Image(final InputStream lIlIIIlIllIII, final String lIlIIIlIllIll, final boolean lIlIIIlIlIllI) throws SlickException {
        this(lIlIIIlIllIII, lIlIIIlIllIll, lIlIIIlIlIllI, 1);
    }
    
    public void bind() {
        this.texture.bind();
    }
    
    public Image(final InputStream lIlIIIlIIllll, final String lIlIIIlIIlllI, final boolean lIlIIIlIIllIl, final int lIlIIIlIIIlll) throws SlickException {
        this.alpha = 1.0f;
        this.inited = false;
        this.filter = 9729;
        this.load(lIlIIIlIIllll, lIlIIIlIIlllI, lIlIIIlIIllIl, lIlIIIlIIIlll, null);
    }
    
    public void setRotation(final float lIIlIlllIIIIl) {
        this.angle = lIIlIlllIIIIl % 360.0f;
    }
    
    public float getTextureOffsetX() {
        this.init();
        return this.textureOffsetX;
    }
    
    public float getTextureWidth() {
        this.init();
        return this.textureWidth;
    }
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public void drawEmbedded(final float lIIlllIIIlIlI, final float lIIlllIIIlllI, final float lIIlllIIIlIII, final float lIIlllIIIIlll) {
        this.init();
        if (this.corners == null) {
            Image.GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
            Image.GL.glVertex3f(lIIlllIIIlIlI, lIIlllIIIlllI, 0.0f);
            Image.GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
            Image.GL.glVertex3f(lIIlllIIIlIlI, lIIlllIIIlllI + lIIlllIIIIlll, 0.0f);
            Image.GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
            Image.GL.glVertex3f(lIIlllIIIlIlI + lIIlllIIIlIII, lIIlllIIIlllI + lIIlllIIIIlll, 0.0f);
            Image.GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
            Image.GL.glVertex3f(lIIlllIIIlIlI + lIIlllIIIlIII, lIIlllIIIlllI, 0.0f);
        }
        else {
            this.corners[0].bind();
            Image.GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
            Image.GL.glVertex3f(lIIlllIIIlIlI, lIIlllIIIlllI, 0.0f);
            this.corners[3].bind();
            Image.GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
            Image.GL.glVertex3f(lIIlllIIIlIlI, lIIlllIIIlllI + lIIlllIIIIlll, 0.0f);
            this.corners[2].bind();
            Image.GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
            Image.GL.glVertex3f(lIIlllIIIlIlI + lIIlllIIIlIII, lIIlllIIIlllI + lIIlllIIIIlll, 0.0f);
            this.corners[1].bind();
            Image.GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
            Image.GL.glVertex3f(lIIlllIIIlIlI + lIIlllIIIlIII, lIIlllIIIlllI, 0.0f);
        }
    }
    
    public Graphics getGraphics() throws SlickException {
        return GraphicsFactory.getGraphicsForImage(this);
    }
    
    public void setImageColor(final float lIlIIIIIIlIll, final float lIlIIIIIIlIlI, final float lIlIIIIIIlIIl) {
        this.setColor(0, lIlIIIIIIlIll, lIlIIIIIIlIlI, lIlIIIIIIlIIl);
        this.setColor(1, lIlIIIIIIlIll, lIlIIIIIIlIlI, lIlIIIIIIlIIl);
        this.setColor(3, lIlIIIIIIlIll, lIlIIIIIIlIlI, lIlIIIIIIlIIl);
        this.setColor(2, lIlIIIIIIlIll, lIlIIIIIIlIlI, lIlIIIIIIlIIl);
    }
    
    public void setName(final String lIIllllIlllll) {
        this.name = lIIllllIlllll;
    }
    
    public Image getScaledCopy(final int lIIIlllIlllII, final int lIIIlllIllIll) {
        this.init();
        final Image lIIIlllIllllI = this.copy();
        lIIIlllIllllI.width = lIIIlllIlllII;
        lIIIlllIllllI.height = lIIIlllIllIll;
        lIIIlllIllllI.centerX = (float)(lIIIlllIlllII / 2);
        lIIIlllIllllI.centerY = (float)(lIIIlllIllIll / 2);
        return lIIIlllIllllI;
    }
    
    public Image(final String lIlIIlIlIllII, final Color lIlIIlIlIlIll) throws SlickException {
        this(lIlIIlIlIllII, false, 1, lIlIIlIlIlIll);
    }
    
    public void draw(final float lIIllIlllIlIl, final float lIIllIlllIlII, final float lIIllIllIllll) {
        this.init();
        this.draw(lIIllIlllIlIl, lIIllIlllIlII, this.width * lIIllIllIllll, this.height * lIIllIllIllll, Color.white);
    }
    
    public Image(final Texture lIlIIlIllIlll) {
        this.alpha = 1.0f;
        this.inited = false;
        this.filter = 9729;
        this.texture = lIlIIlIllIlll;
        this.ref = lIlIIlIllIlll.toString();
        this.clampTexture();
    }
    
    private void load(final InputStream lIIllllIIllII, final String lIIllllIIIlIl, final boolean lIIllllIIlIlI, final int lIIllllIIIIll, final Color lIIllllIIlIII) throws SlickException {
        this.filter = ((lIIllllIIIIll == 1) ? 9729 : 9728);
        try {
            this.ref = lIIllllIIIlIl;
            int[] lIIllllIIllll = null;
            if (lIIllllIIlIII != null) {
                lIIllllIIllll = new int[] { (int)(lIIllllIIlIII.r * 255.0f), (int)(lIIllllIIlIII.g * 255.0f), (int)(lIIllllIIlIII.b * 255.0f) };
            }
            this.texture = InternalTextureLoader.get().getTexture(lIIllllIIllII, lIIllllIIIlIl, lIIllllIIlIlI, this.filter, lIIllllIIllll);
        }
        catch (IOException lIIllllIIlllI) {
            Log.error(lIIllllIIlllI);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load image from: ").append(lIIllllIIIlIl)), lIIllllIIlllI);
        }
    }
    
    public void drawSheared(final float lIIllIlIIIlIl, final float lIIllIlIIlIIl, final float lIIllIlIIIIll, final float lIIllIlIIIlll) {
        this.drawSheared(lIIllIlIIIlIl, lIIllIlIIlIIl, lIIllIlIIIIll, lIIllIlIIIlll, Color.white);
    }
}
