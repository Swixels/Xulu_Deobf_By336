package org.newdawn.slick;

import java.util.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.geom.*;
import org.lwjgl.*;
import java.nio.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.util.*;
import java.security.*;

public class Graphics
{
    public static /* synthetic */ int MODE_ADD;
    public static /* synthetic */ int MODE_SCREEN;
    private /* synthetic */ DoubleBuffer worldClip;
    protected /* synthetic */ int screenHeight;
    private /* synthetic */ int currentDrawingMode;
    private /* synthetic */ Font font;
    private /* synthetic */ ArrayList stack;
    private /* synthetic */ boolean antialias;
    private /* synthetic */ int stackIndex;
    private /* synthetic */ Rectangle clip;
    private /* synthetic */ float sx;
    protected static /* synthetic */ Graphics currentGraphics;
    public static /* synthetic */ int MODE_ALPHA_MAP;
    private /* synthetic */ float sy;
    private /* synthetic */ ByteBuffer readBuffer;
    public static /* synthetic */ int MODE_ALPHA_BLEND;
    protected static /* synthetic */ Font DEFAULT_FONT;
    private static /* synthetic */ LineStripRenderer LSR;
    private /* synthetic */ float lineWidth;
    protected /* synthetic */ int screenWidth;
    private /* synthetic */ Color currentColor;
    public static /* synthetic */ int MODE_COLOR_MULTIPLY;
    public static /* synthetic */ int MODE_NORMAL;
    private /* synthetic */ Rectangle worldClipRecord;
    protected static /* synthetic */ SGL GL;
    private /* synthetic */ boolean pushed;
    
    public void texture(final Shape llIlIlIlIlI, final Image llIlIlIlIIl, final float llIlIlIlIII, final float llIlIlIIIIl, final ShapeFill llIlIlIIllI) {
        this.predraw();
        TextureImpl.bindNone();
        this.currentColor.bind();
        ShapeRenderer.texture(llIlIlIlIlI, llIlIlIlIIl, llIlIlIlIII, llIlIlIIIIl, llIlIlIIllI);
        this.postdraw();
    }
    
    public void setWorldClip(final Rectangle llIIlllIIll) {
        if (llIIlllIIll == null) {
            this.clearWorldClip();
        }
        else {
            this.setWorldClip(llIIlllIIll.getX(), llIIlllIIll.getY(), llIIlllIIll.getWidth(), llIIlllIIll.getHeight());
        }
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void pushTransform() {
        this.predraw();
        FloatBuffer llllIIIllI = null;
        if (this.stackIndex >= this.stack.size()) {
            final FloatBuffer llllIIlIII = BufferUtils.createFloatBuffer(18);
            this.stack.add(llllIIlIII);
        }
        else {
            llllIIIllI = this.stack.get(this.stackIndex);
        }
        Graphics.GL.glGetFloat(2982, llllIIIllI);
        llllIIIllI.put(16, this.sx);
        llllIIIllI.put(17, this.sy);
        ++this.stackIndex;
        this.postdraw();
    }
    
    public void clearClip() {
        this.clip = null;
        this.predraw();
        Graphics.GL.glDisable(3089);
        this.postdraw();
    }
    
    public void drawRoundRect(final float lIlIIllIIlI, final float lIlIIllIIIl, final float lIlIIllIIII, final float lIlIIlIllll, int lIlIIlIlllI, final int lIlIIlIllIl) {
        if (lIlIIlIlllI < 0) {
            throw new IllegalArgumentException("corner radius must be > 0");
        }
        if (lIlIIlIlllI == 0) {
            this.drawRect(lIlIIllIIlI, lIlIIllIIIl, lIlIIllIIII, lIlIIlIllll);
            return;
        }
        final int lIlIIllIlIl = (int)Math.min(lIlIIllIIII, lIlIIlIllll) / 2;
        if (lIlIIlIlllI > lIlIIllIlIl) {
            lIlIIlIlllI = lIlIIllIlIl;
        }
        this.drawLine(lIlIIllIIlI + (float)lIlIIlIlllI, lIlIIllIIIl, lIlIIllIIlI + lIlIIllIIII - (float)lIlIIlIlllI, lIlIIllIIIl);
        this.drawLine(lIlIIllIIlI, lIlIIllIIIl + (float)lIlIIlIlllI, lIlIIllIIlI, lIlIIllIIIl + lIlIIlIllll - (float)lIlIIlIlllI);
        this.drawLine(lIlIIllIIlI + lIlIIllIIII, lIlIIllIIIl + (float)lIlIIlIlllI, lIlIIllIIlI + lIlIIllIIII, lIlIIllIIIl + lIlIIlIllll - (float)lIlIIlIlllI);
        this.drawLine(lIlIIllIIlI + (float)lIlIIlIlllI, lIlIIllIIIl + lIlIIlIllll, lIlIIllIIlI + lIlIIllIIII - (float)lIlIIlIlllI, lIlIIllIIIl + lIlIIlIllll);
        final float lIlIIllIlII = (float)(lIlIIlIlllI * 2);
        this.drawArc(lIlIIllIIlI + lIlIIllIIII - lIlIIllIlII, lIlIIllIIIl + lIlIIlIllll - lIlIIllIlII, lIlIIllIlII, lIlIIllIlII, lIlIIlIllIl, 0.0f, 90.0f);
        this.drawArc(lIlIIllIIlI, lIlIIllIIIl + lIlIIlIllll - lIlIIllIlII, lIlIIllIlII, lIlIIllIlII, lIlIIlIllIl, 90.0f, 180.0f);
        this.drawArc(lIlIIllIIlI + lIlIIllIIII - lIlIIllIlII, lIlIIllIIIl, lIlIIllIlII, lIlIIllIlII, lIlIIlIllIl, 270.0f, 360.0f);
        this.drawArc(lIlIIllIIlI, lIlIIllIIIl, lIlIIllIlII, lIlIIllIlII, lIlIIlIllIl, 180.0f, 270.0f);
    }
    
    public void texture(final Shape llIllIllIIl, final Image llIllIlIlII, final boolean llIllIlIlll) {
        if (llIllIlIlll) {
            this.texture(llIllIllIIl, llIllIlIlII, 1.0f, 1.0f, true);
        }
        else {
            this.texture(llIllIllIIl, llIllIlIlII, 0.01f, 0.01f, false);
        }
    }
    
    public void setColor(final Color lllIIlIllll) {
        if (lllIIlIllll == null) {
            return;
        }
        this.currentColor = new Color(lllIIlIllll);
        this.predraw();
        this.currentColor.bind();
        this.postdraw();
    }
    
    public void fillRoundRect(final float lIlIIIlllIl, final float lIlIIlIIIlI, final float lIlIIIllIll, final float lIlIIIllIlI, final int lIlIIIllIIl) {
        this.fillRoundRect(lIlIIIlllIl, lIlIIlIIIlI, lIlIIIllIll, lIlIIIllIlI, lIlIIIllIIl, 50);
    }
    
    public void flush() {
        if (Graphics.currentGraphics == this) {
            Graphics.currentGraphics.disable();
            Graphics.currentGraphics = null;
        }
    }
    
    public void fillArc(final float lIlIlllIIII, final float lIlIllIllll, final float lIlIllIIIll, final float lIlIllIllIl, final int lIlIllIllII, final float lIlIllIIIII, float lIlIlIlllll) {
        this.predraw();
        TextureImpl.bindNone();
        this.currentColor.bind();
        while (lIlIlIlllll < lIlIllIIIII) {
            lIlIlIlllll += 360.0f;
        }
        final float lIlIllIlIIl = lIlIlllIIII + lIlIllIIIll / 2.0f;
        final float lIlIllIlIII = lIlIllIllll + lIlIllIllIl / 2.0f;
        Graphics.GL.glBegin(6);
        final int lIlIllIIlll = 360 / lIlIllIllII;
        Graphics.GL.glVertex2f(lIlIllIlIIl, lIlIllIlIII);
        for (int lIlIlllIllI = (int)lIlIllIIIII; lIlIlllIllI < (int)(lIlIlIlllll + lIlIllIIlll); lIlIlllIllI += lIlIllIIlll) {
            float lIlIllllIIl = (float)lIlIlllIllI;
            if (lIlIllllIIl > lIlIlIlllll) {
                lIlIllllIIl = lIlIlIlllll;
            }
            final float lIlIllllIII = (float)(lIlIllIlIIl + FastTrig.cos(Math.toRadians(lIlIllllIIl)) * lIlIllIIIll / 2.0);
            final float lIlIlllIlll = (float)(lIlIllIlIII + FastTrig.sin(Math.toRadians(lIlIllllIIl)) * lIlIllIllIl / 2.0);
            Graphics.GL.glVertex2f(lIlIllllIII, lIlIlllIlll);
        }
        Graphics.GL.glEnd();
        if (this.antialias) {
            Graphics.GL.glBegin(6);
            Graphics.GL.glVertex2f(lIlIllIlIIl, lIlIllIlIII);
            if (lIlIlIlllll != 360.0f) {
                lIlIlIlllll -= 10.0f;
            }
            for (int lIlIlllIIlI = (int)lIlIllIIIII; lIlIlllIIlI < (int)(lIlIlIlllll + lIlIllIIlll); lIlIlllIIlI += lIlIllIIlll) {
                float lIlIlllIlIl = (float)lIlIlllIIlI;
                if (lIlIlllIlIl > lIlIlIlllll) {
                    lIlIlllIlIl = lIlIlIlllll;
                }
                final float lIlIlllIlII = (float)(lIlIllIlIIl + FastTrig.cos(Math.toRadians(lIlIlllIlIl + 10.0f)) * lIlIllIIIll / 2.0);
                final float lIlIlllIIll = (float)(lIlIllIlIII + FastTrig.sin(Math.toRadians(lIlIlllIlIl + 10.0f)) * lIlIllIllIl / 2.0);
                Graphics.GL.glVertex2f(lIlIlllIlII, lIlIlllIIll);
            }
            Graphics.GL.glEnd();
        }
        this.postdraw();
    }
    
    public void setClip(final Rectangle llIIlIllIll) {
        if (llIIlIllIll == null) {
            this.clearClip();
            return;
        }
        this.setClip((int)llIIlIllIll.getX(), (int)llIIlIllIll.getY(), (int)llIIlIllIll.getWidth(), (int)llIIlIllIll.getHeight());
    }
    
    private void predraw() {
        setCurrent(this);
    }
    
    public void fillArc(final float lIllIIIlllI, final float lIllIIIllIl, final float lIllIIIllII, final float lIllIIIlIll, final float lIllIIlIIIl, final float lIllIIIlIIl) {
        this.fillArc(lIllIIIlllI, lIllIIIllIl, lIllIIIllII, lIllIIIlIll, 50, lIllIIlIIIl, lIllIIIlIIl);
    }
    
    public void clearWorldClip() {
        this.predraw();
        this.worldClipRecord = null;
        Graphics.GL.glDisable(12288);
        Graphics.GL.glDisable(12289);
        Graphics.GL.glDisable(12290);
        Graphics.GL.glDisable(12291);
        this.postdraw();
    }
    
    public void texture(final Shape llIlllIIIIl, final Image llIlllIIlII, final ShapeFill llIlllIIIll) {
        this.texture(llIlllIIIIl, llIlllIIlII, 0.01f, 0.01f, llIlllIIIll);
    }
    
    public void resetTransform() {
        this.sx = 1.0f;
        this.sy = 1.0f;
        if (this.pushed) {
            this.predraw();
            Graphics.GL.glPopMatrix();
            this.pushed = false;
            this.postdraw();
        }
    }
    
    public void fill(final Shape llIllllIlII) {
        this.predraw();
        TextureImpl.bindNone();
        this.currentColor.bind();
        ShapeRenderer.fill(llIllllIlII);
        this.postdraw();
    }
    
    public static void setCurrent(final Graphics llllIIlllIl) {
        if (Graphics.currentGraphics != llllIIlllIl) {
            if (Graphics.currentGraphics != null) {
                Graphics.currentGraphics.disable();
            }
            (Graphics.currentGraphics = llllIIlllIl).enable();
        }
    }
    
    public void popTransform() {
        if (this.stackIndex == 0) {
            throw new RuntimeException("Attempt to pop a transform that hasn't be pushed");
        }
        this.predraw();
        --this.stackIndex;
        final FloatBuffer llllIIIIII = this.stack.get(this.stackIndex);
        Graphics.GL.glLoadMatrix(llllIIIIII);
        this.sx = llllIIIIII.get(16);
        this.sy = llllIIIIII.get(17);
        this.postdraw();
    }
    
    public void setBackground(final Color lllIllIlIIl) {
        this.predraw();
        Graphics.GL.glClearColor(lllIllIlIIl.r, lllIllIlIIl.g, lllIllIlIIl.b, lllIllIlIIl.a);
        this.postdraw();
    }
    
    public void texture(final Shape llIllIIIlll, final Image llIllIIIllI, final float llIllIIIlIl, final float llIllIIlIIl) {
        this.texture(llIllIIIlll, llIllIIIllI, llIllIIIlIl, llIllIIlIIl, false);
    }
    
    public void drawOval(final float llIIIIlIllI, final float llIIIIllIlI, final float llIIIIllIIl, final float llIIIIlIIll) {
        this.drawOval(llIIIIlIllI, llIIIIllIlI, llIIIIllIIl, llIIIIlIIll, 50);
    }
    
    public void drawGradientLine(final float lllllllIII, final float llllllIlll, final float llllllIllI, final float llllllIlIl, final float lllllIIlll, final float lllllIIllI, final float lllllIIlIl, final float lllllIIlII, final float llllllIIII, final float lllllIllll, final float lllllIIIIl, final float lllllIIIII) {
        this.predraw();
        TextureImpl.bindNone();
        Graphics.GL.glBegin(1);
        Graphics.GL.glColor4f(llllllIllI, llllllIlIl, lllllIIlll, lllllIIllI);
        Graphics.GL.glVertex2f(lllllllIII, llllllIlll);
        Graphics.GL.glColor4f(llllllIIII, lllllIllll, lllllIIIIl, lllllIIIII);
        Graphics.GL.glVertex2f(lllllIIlIl, lllllIIlII);
        Graphics.GL.glEnd();
        this.postdraw();
    }
    
    public void clearAlphaMap() {
        this.pushTransform();
        Graphics.GL.glLoadIdentity();
        final int lllIlllllIl = this.currentDrawingMode;
        this.setDrawMode(Graphics.MODE_ALPHA_MAP);
        this.setColor(new Color(0, 0, 0, 0));
        this.fillRect(0.0f, 0.0f, (float)this.screenWidth, (float)this.screenHeight);
        this.setColor(this.currentColor);
        this.setDrawMode(lllIlllllIl);
        this.popTransform();
    }
    
    public void setDrawMode(final int llllIIIIIll) {
        this.predraw();
        this.currentDrawingMode = llllIIIIIll;
        if (this.currentDrawingMode == Graphics.MODE_NORMAL) {
            Graphics.GL.glEnable(3042);
            Graphics.GL.glColorMask(true, true, true, true);
            Graphics.GL.glBlendFunc(770, 771);
        }
        if (this.currentDrawingMode == Graphics.MODE_ALPHA_MAP) {
            Graphics.GL.glDisable(3042);
            Graphics.GL.glColorMask(false, false, false, true);
        }
        if (this.currentDrawingMode == Graphics.MODE_ALPHA_BLEND) {
            Graphics.GL.glEnable(3042);
            Graphics.GL.glColorMask(true, true, true, false);
            Graphics.GL.glBlendFunc(772, 773);
        }
        if (this.currentDrawingMode == Graphics.MODE_COLOR_MULTIPLY) {
            Graphics.GL.glEnable(3042);
            Graphics.GL.glColorMask(true, true, true, true);
            Graphics.GL.glBlendFunc(769, 768);
        }
        if (this.currentDrawingMode == Graphics.MODE_ADD) {
            Graphics.GL.glEnable(3042);
            Graphics.GL.glColorMask(true, true, true, true);
            Graphics.GL.glBlendFunc(1, 1);
        }
        if (this.currentDrawingMode == Graphics.MODE_SCREEN) {
            Graphics.GL.glEnable(3042);
            Graphics.GL.glColorMask(true, true, true, true);
            Graphics.GL.glBlendFunc(1, 769);
        }
        this.postdraw();
    }
    
    public void getArea(final int lIIIlIIIlll, final int lIIIlIIIllI, final int lIIIlIIIlIl, final int lIIIlIIlIlI, final ByteBuffer lIIIlIIIIll) {
        if (lIIIlIIIIll.capacity() < lIIIlIIIlIl * lIIIlIIlIlI * 4) {
            throw new IllegalArgumentException("Byte buffer provided to get area is not big enough");
        }
        this.predraw();
        Graphics.GL.glReadPixels(lIIIlIIIlll, this.screenHeight - lIIIlIIIllI - lIIIlIIlIlI, lIIIlIIIlIl, lIIIlIIlIlI, 6408, 5121, lIIIlIIIIll);
        this.postdraw();
    }
    
    public void fillOval(final float lIllIlIlIII, final float lIllIlIIlll, final float lIllIlIIllI, final float lIllIlIIlIl, final int lIllIlIIlII) {
        this.fillArc(lIllIlIlIII, lIllIlIIlll, lIllIlIIllI, lIllIlIIlIl, lIllIlIIlII, 0.0f, 360.0f);
    }
    
    public float getLineWidth() {
        return this.lineWidth;
    }
    
    public Rectangle getClip() {
        return this.clip;
    }
    
    public Color getPixel(final int lIIIlIlIllI, final int lIIIlIlIlIl) {
        this.predraw();
        Graphics.GL.glReadPixels(lIIIlIlIllI, this.screenHeight - lIIIlIlIlIl, 1, 1, 6408, 5121, this.readBuffer);
        this.postdraw();
        return new Color(this.translate(this.readBuffer.get(0)), this.translate(this.readBuffer.get(1)), this.translate(this.readBuffer.get(2)), this.translate(this.readBuffer.get(3)));
    }
    
    public void resetFont() {
        this.font = Graphics.DEFAULT_FONT;
    }
    
    void setDimensions(final int llllIIIlIII, final int llllIIIlIlI) {
        this.screenWidth = llllIIIlIII;
        this.screenHeight = llllIIIlIlI;
    }
    
    public Color getColor() {
        return new Color(this.currentColor);
    }
    
    public void drawGradientLine(final float llllIlIIII, final float llllIIllll, final Color llllIIlllI, final float llllIlIlII, final float llllIIllII, final Color llllIIlIll) {
        this.predraw();
        TextureImpl.bindNone();
        Graphics.GL.glBegin(1);
        llllIIlllI.bind();
        Graphics.GL.glVertex2f(llllIlIIII, llllIIllll);
        llllIIlIll.bind();
        Graphics.GL.glVertex2f(llllIlIlII, llllIIllII);
        Graphics.GL.glEnd();
        this.postdraw();
    }
    
    public void drawImage(final Image lIIIlllllll, final float lIIIllllllI, final float lIIIlllIlIl, final float lIIIlllllII, final float lIIIllllIll, final float lIIIlllIIlI, final float lIIIlllIIIl) {
        this.drawImage(lIIIlllllll, lIIIllllllI, lIIIlllIlIl, lIIIllllllI + lIIIlllllll.getWidth(), lIIIlllIlIl + lIIIlllllll.getHeight(), lIIIlllllII, lIIIllllIll, lIIIlllIIlI, lIIIlllIIIl);
    }
    
    public void texture(final Shape llIlllIllll, final Image llIlllIlllI) {
        this.texture(llIlllIllll, llIlllIlllI, 0.01f, 0.01f, false);
    }
    
    public void resetLineWidth() {
        this.predraw();
        Renderer.getLineStripRenderer().setWidth(1.0f);
        Graphics.GL.glLineWidth(1.0f);
        Graphics.GL.glPointSize(1.0f);
        this.postdraw();
    }
    
    public void rotate(final float lllIlIIlIll, final float lllIlIIIllI, final float lllIlIIlIIl) {
        this.checkPush();
        this.predraw();
        this.translate(lllIlIIlIll, lllIlIIIllI);
        Graphics.GL.glRotatef(lllIlIIlIIl, 0.0f, 0.0f, 1.0f);
        this.translate(-lllIlIIlIll, -lllIlIIIllI);
        this.postdraw();
    }
    
    public void draw(final Shape lllIIIIllIl, final ShapeFill lllIIIIllII) {
        this.predraw();
        TextureImpl.bindNone();
        ShapeRenderer.draw(lllIIIIllIl, lllIIIIllII);
        this.currentColor.bind();
        this.postdraw();
    }
    
    public void setFont(final Font lllIIllIllI) {
        this.font = lllIIllIllI;
    }
    
    public void destroy() {
    }
    
    public void setClip(final int llIIllIlIIl, final int llIIllIIIll, final int llIIllIIIlI, final int llIIllIIIIl) {
        this.predraw();
        if (this.clip == null) {
            Graphics.GL.glEnable(3089);
            this.clip = new Rectangle((float)llIIllIlIIl, (float)llIIllIIIll, (float)llIIllIIIlI, (float)llIIllIIIIl);
        }
        else {
            this.clip.setBounds((float)llIIllIlIIl, (float)llIIllIIIll, (float)llIIllIIIlI, (float)llIIllIIIIl);
        }
        Graphics.GL.glScissor(llIIllIlIIl, this.screenHeight - llIIllIIIll - llIIllIIIIl, llIIllIIIlI, llIIllIIIIl);
        this.postdraw();
    }
    
    public void drawImage(final Image lIIIIlIlIll, final float lIIIIlIlIlI, final float lIIIIllIlII, final float lIIIIllIIll, final float lIIIIlIIlll, final float lIIIIlIIllI, final float lIIIIllIIII, final float lIIIIlIIlII, final float lIIIIlIIIll, final Color lIIIIlIIIlI) {
        this.predraw();
        lIIIIlIlIll.draw(lIIIIlIlIlI, lIIIIllIlII, lIIIIllIIll, lIIIIlIIlll, lIIIIlIIllI, lIIIIllIIII, lIIIIlIIlII, lIIIIlIIIll, lIIIIlIIIlI);
        this.currentColor.bind();
        this.postdraw();
    }
    
    public void setAntiAlias(final boolean lIIlllIllII) {
        this.predraw();
        this.antialias = lIIlllIllII;
        Graphics.LSR.setAntiAlias(lIIlllIllII);
        if (lIIlllIllII) {
            Graphics.GL.glEnable(2881);
        }
        else {
            Graphics.GL.glDisable(2881);
        }
        this.postdraw();
    }
    
    public void fillRect(final float llIIIlIlIlI, final float llIIIlIIlII, final float llIIIlIlIII, final float llIIIlIIlll) {
        this.predraw();
        TextureImpl.bindNone();
        this.currentColor.bind();
        Graphics.GL.glBegin(7);
        Graphics.GL.glVertex2f(llIIIlIlIlI, llIIIlIIlII);
        Graphics.GL.glVertex2f(llIIIlIlIlI + llIIIlIlIII, llIIIlIIlII);
        Graphics.GL.glVertex2f(llIIIlIlIlI + llIIIlIlIII, llIIIlIIlII + llIIIlIIlll);
        Graphics.GL.glVertex2f(llIIIlIlIlI, llIIIlIIlII + llIIIlIIlll);
        Graphics.GL.glEnd();
        this.postdraw();
    }
    
    public void drawAnimation(final Animation lIIllIIIlII, final float lIIllIIIIll, final float lIIllIIIIlI) {
        this.drawAnimation(lIIllIIIlII, lIIllIIIIll, lIIllIIIIlI, Color.white);
    }
    
    public void drawImage(final Image lIIlIlIllIl, final float lIIlIlIllII, final float lIIlIlIIlll) {
        this.drawImage(lIIlIlIllIl, lIIlIlIllII, lIIlIlIIlll, Color.white);
    }
    
    public void texture(final Shape llIlIllIllI, final Image llIlIlllIll, final float llIlIllIlII, final float llIlIlllIIl, final boolean llIlIlllIII) {
        this.predraw();
        TextureImpl.bindNone();
        this.currentColor.bind();
        if (llIlIlllIII) {
            ShapeRenderer.textureFit(llIlIllIllI, llIlIlllIll, llIlIllIlII, llIlIlllIIl);
        }
        else {
            ShapeRenderer.texture(llIlIllIllI, llIlIlllIll, llIlIllIlII, llIlIlllIIl);
        }
        this.postdraw();
    }
    
    public void scale(final float lllIlIlIIlI, final float lllIlIlIlII) {
        this.sx *= lllIlIlIIlI;
        this.sy *= lllIlIlIlII;
        this.checkPush();
        this.predraw();
        Graphics.GL.glScalef(lllIlIlIIlI, lllIlIlIlII, 1.0f);
        this.postdraw();
    }
    
    public void setWorldClip(final float llIIlllllll, final float llIIllllllI, final float llIlIIIIIlI, final float llIIlllllII) {
        this.predraw();
        this.worldClipRecord = new Rectangle(llIIlllllll, llIIllllllI, llIlIIIIIlI, llIIlllllII);
        Graphics.GL.glEnable(12288);
        this.worldClip.put(1.0).put(0.0).put(0.0).put(-llIIlllllll).flip();
        Graphics.GL.glClipPlane(12288, this.worldClip);
        Graphics.GL.glEnable(12289);
        this.worldClip.put(-1.0).put(0.0).put(0.0).put(llIIlllllll + llIlIIIIIlI).flip();
        Graphics.GL.glClipPlane(12289, this.worldClip);
        Graphics.GL.glEnable(12290);
        this.worldClip.put(0.0).put(1.0).put(0.0).put(-llIIllllllI).flip();
        Graphics.GL.glClipPlane(12290, this.worldClip);
        Graphics.GL.glEnable(12291);
        this.worldClip.put(0.0).put(-1.0).put(0.0).put(llIIllllllI + llIIlllllII).flip();
        Graphics.GL.glClipPlane(12291, this.worldClip);
        this.postdraw();
    }
    
    public void drawLine(float lllIIIlIlll, float lllIIIlIllI, float lllIIIlIlIl, float lllIIIlIlII) {
        float lllIIIllIIl = this.lineWidth - 1.0f;
        if (Graphics.LSR.applyGLLineFixes()) {
            if (lllIIIlIlll == lllIIIlIlIl) {
                if (lllIIIlIllI > lllIIIlIlII) {
                    final float lllIIlIIIlI = lllIIIlIlII;
                    lllIIIlIlII = lllIIIlIllI;
                    lllIIIlIllI = lllIIlIIIlI;
                }
                final float lllIIlIIIIl = 1.0f / this.sy;
                lllIIIllIIl /= this.sy;
                this.fillRect(lllIIIlIlll - lllIIIllIIl / 2.0f, lllIIIlIllI - lllIIIllIIl / 2.0f, lllIIIllIIl + lllIIlIIIIl, lllIIIlIlII - lllIIIlIllI + lllIIIllIIl + lllIIlIIIIl);
                return;
            }
            if (lllIIIlIllI == lllIIIlIlII) {
                if (lllIIIlIlll > lllIIIlIlIl) {
                    final float lllIIlIIIII = lllIIIlIlIl;
                    lllIIIlIlIl = lllIIIlIlll;
                    lllIIIlIlll = lllIIlIIIII;
                }
                final float lllIIIlllll = 1.0f / this.sx;
                lllIIIllIIl /= this.sx;
                this.fillRect(lllIIIlIlll - lllIIIllIIl / 2.0f, lllIIIlIllI - lllIIIllIIl / 2.0f, lllIIIlIlIl - lllIIIlIlll + lllIIIllIIl + lllIIIlllll, lllIIIllIIl + lllIIIlllll);
                return;
            }
        }
        this.predraw();
        this.currentColor.bind();
        TextureImpl.bindNone();
        Graphics.LSR.start();
        Graphics.LSR.vertex(lllIIIlIlll, lllIIIlIllI);
        Graphics.LSR.vertex(lllIIIlIlIl, lllIIIlIlII);
        Graphics.LSR.end();
        this.postdraw();
    }
    
    public void setLineWidth(final float lIIlllllIII) {
        this.predraw();
        this.lineWidth = lIIlllllIII;
        Graphics.LSR.setWidth(lIIlllllIII);
        Graphics.GL.glPointSize(lIIlllllIII);
        this.postdraw();
    }
    
    public void clear() {
        this.predraw();
        Graphics.GL.glClear(16384);
        this.postdraw();
    }
    
    private int translate(final byte lIIIlIlllll) {
        if (lIIIlIlllll < 0) {
            return 256 + lIIIlIlllll;
        }
        return lIIIlIlllll;
    }
    
    public void fillRoundRect(final float lIlIIIIIlIl, final float lIlIIIIIlII, final float lIlIIIIIIll, final float lIlIIIIlIll, int lIlIIIIIIIl, final int lIlIIIIlIIl) {
        if (lIlIIIIIIIl < 0) {
            throw new IllegalArgumentException("corner radius must be > 0");
        }
        if (lIlIIIIIIIl == 0) {
            this.fillRect(lIlIIIIIlIl, lIlIIIIIlII, lIlIIIIIIll, lIlIIIIlIll);
            return;
        }
        final int lIlIIIIlIII = (int)Math.min(lIlIIIIIIll, lIlIIIIlIll) / 2;
        if (lIlIIIIIIIl > lIlIIIIlIII) {
            lIlIIIIIIIl = lIlIIIIlIII;
        }
        final float lIlIIIIIlll = (float)(lIlIIIIIIIl * 2);
        this.fillRect(lIlIIIIIlIl + (float)lIlIIIIIIIl, lIlIIIIIlII, lIlIIIIIIll - lIlIIIIIlll, (float)lIlIIIIIIIl);
        this.fillRect(lIlIIIIIlIl, lIlIIIIIlII + (float)lIlIIIIIIIl, (float)lIlIIIIIIIl, lIlIIIIlIll - lIlIIIIIlll);
        this.fillRect(lIlIIIIIlIl + lIlIIIIIIll - (float)lIlIIIIIIIl, lIlIIIIIlII + (float)lIlIIIIIIIl, (float)lIlIIIIIIIl, lIlIIIIlIll - lIlIIIIIlll);
        this.fillRect(lIlIIIIIlIl + (float)lIlIIIIIIIl, lIlIIIIIlII + lIlIIIIlIll - (float)lIlIIIIIIIl, lIlIIIIIIll - lIlIIIIIlll, (float)lIlIIIIIIIl);
        this.fillRect(lIlIIIIIlIl + (float)lIlIIIIIIIl, lIlIIIIIlII + (float)lIlIIIIIIIl, lIlIIIIIIll - lIlIIIIIlll, lIlIIIIlIll - lIlIIIIIlll);
        this.fillArc(lIlIIIIIlIl + lIlIIIIIIll - lIlIIIIIlll, lIlIIIIIlII + lIlIIIIlIll - lIlIIIIIlll, lIlIIIIIlll, lIlIIIIIlll, lIlIIIIlIIl, 0.0f, 90.0f);
        this.fillArc(lIlIIIIIlIl, lIlIIIIIlII + lIlIIIIlIll - lIlIIIIIlll, lIlIIIIIlll, lIlIIIIIlll, lIlIIIIlIIl, 90.0f, 180.0f);
        this.fillArc(lIlIIIIIlIl + lIlIIIIIIll - lIlIIIIIlll, lIlIIIIIlII, lIlIIIIIlll, lIlIIIIIlll, lIlIIIIlIIl, 270.0f, 360.0f);
        this.fillArc(lIlIIIIIlIl, lIlIIIIIlII, lIlIIIIIlll, lIlIIIIIlll, lIlIIIIlIIl, 180.0f, 270.0f);
    }
    
    public Graphics() {
        this.sx = 1.0f;
        this.sy = 1.0f;
        this.currentColor = Color.white;
        this.worldClip = BufferUtils.createDoubleBuffer(4);
        this.readBuffer = BufferUtils.createByteBuffer(4);
        this.currentDrawingMode = Graphics.MODE_NORMAL;
        this.lineWidth = 1.0f;
        this.stack = new ArrayList();
    }
    
    public void drawRect(final float llIlIIllIII, final float llIlIIlIIIl, final float llIlIIlIllI, final float llIlIIIllll) {
        final float llIlIIlIlII = this.getLineWidth();
        this.drawLine(llIlIIllIII, llIlIIlIIIl, llIlIIllIII + llIlIIlIllI, llIlIIlIIIl);
        this.drawLine(llIlIIllIII + llIlIIlIllI, llIlIIlIIIl, llIlIIllIII + llIlIIlIllI, llIlIIlIIIl + llIlIIIllll);
        this.drawLine(llIlIIllIII + llIlIIlIllI, llIlIIlIIIl + llIlIIIllll, llIlIIllIII, llIlIIlIIIl + llIlIIIllll);
        this.drawLine(llIlIIllIII, llIlIIlIIIl + llIlIIIllll, llIlIIllIII, llIlIIlIIIl);
    }
    
    public Color getBackground() {
        this.predraw();
        final FloatBuffer lllIllIIlIl = BufferUtils.createFloatBuffer(16);
        Graphics.GL.glGetFloat(3106, lllIllIIlIl);
        this.postdraw();
        return new Color(lllIllIIlIl);
    }
    
    public void drawImage(final Image lIIIIIIlllI, final float lIIIIIIllIl, final float lIIIIIlIlIl, final float lIIIIIlIlII, final float lIIIIIlIIll, final float lIIIIIIlIIl, final float lIIIIIIlIII, final Color lIIIIIIIlll) {
        this.drawImage(lIIIIIIlllI, lIIIIIIllIl, lIIIIIlIlIl, lIIIIIIllIl + lIIIIIIlllI.getWidth(), lIIIIIlIlIl + lIIIIIIlllI.getHeight(), lIIIIIlIlII, lIIIIIlIIll, lIIIIIIlIIl, lIIIIIIlIII, lIIIIIIIlll);
    }
    
    public Graphics(final int llllIIlIIIl, final int llllIIlIIll) {
        this.sx = 1.0f;
        this.sy = 1.0f;
        this.currentColor = Color.white;
        this.worldClip = BufferUtils.createDoubleBuffer(4);
        this.readBuffer = BufferUtils.createByteBuffer(4);
        this.currentDrawingMode = Graphics.MODE_NORMAL;
        this.lineWidth = 1.0f;
        this.stack = new ArrayList();
        if (Graphics.DEFAULT_FONT == null) {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                @Override
                public Object run() {
                    try {
                        Graphics.DEFAULT_FONT = new AngelCodeFont("org/newdawn/slick/data/defaultfont.fnt", "org/newdawn/slick/data/defaultfont.png");
                    }
                    catch (SlickException llIIIIlIlIIIl) {
                        Log.error(llIIIIlIlIIIl);
                    }
                    return null;
                }
            });
        }
        this.font = Graphics.DEFAULT_FONT;
        this.screenWidth = llllIIlIIIl;
        this.screenHeight = llllIIlIIll;
    }
    
    protected void disable() {
    }
    
    public boolean isAntiAlias() {
        return this.antialias;
    }
    
    private void postdraw() {
    }
    
    public void translate(final float lllIIllllIl, final float lllIIllllll) {
        this.checkPush();
        this.predraw();
        Graphics.GL.glTranslatef(lllIIllllIl, lllIIllllll, 0.0f);
        this.postdraw();
    }
    
    public void fillRect(final float llIIlIIIlll, final float llIIlIIIllI, final float llIIIlllIlI, final float llIIIlllIIl, final Image llIIlIIIIll, final float llIIlIIIIlI, final float llIIlIIIIIl) {
        final int llIIlIIIIII = (int)Math.ceil(llIIIlllIlI / llIIlIIIIll.getWidth()) + 2;
        final int llIIIllllll = (int)Math.ceil(llIIIlllIIl / llIIlIIIIll.getHeight()) + 2;
        final Rectangle llIIIlllllI = this.getWorldClip();
        this.setWorldClip(llIIlIIIlll, llIIlIIIllI, llIIIlllIlI, llIIIlllIIl);
        this.predraw();
        for (int llIIlIIlIIl = 0; llIIlIIlIIl < llIIlIIIIII; ++llIIlIIlIIl) {
            for (int llIIlIIlIlI = 0; llIIlIIlIlI < llIIIllllll; ++llIIlIIlIlI) {
                llIIlIIIIll.draw(llIIlIIlIIl * llIIlIIIIll.getWidth() + llIIlIIIlll - llIIlIIIIlI, llIIlIIlIlI * llIIlIIIIll.getHeight() + llIIlIIIllI - llIIlIIIIIl);
            }
        }
        this.postdraw();
        this.setWorldClip(llIIIlllllI);
    }
    
    public Rectangle getWorldClip() {
        return this.worldClipRecord;
    }
    
    protected void enable() {
    }
    
    public void fill(final Shape lllIIIIIIIl, final ShapeFill lllIIIIIIII) {
        this.predraw();
        TextureImpl.bindNone();
        ShapeRenderer.fill(lllIIIIIIIl, lllIIIIIIII);
        this.currentColor.bind();
        this.postdraw();
    }
    
    public void drawImage(final Image lIIllIlIllI, final float lIIllIlIlIl, final float lIIllIIllll, final Color lIIllIlIIll) {
        this.predraw();
        lIIllIlIllI.draw(lIIllIlIlIl, lIIllIIllll, lIIllIlIIll);
        this.currentColor.bind();
        this.postdraw();
    }
    
    private void checkPush() {
        if (!this.pushed) {
            this.predraw();
            Graphics.GL.glPushMatrix();
            this.pushed = true;
            this.postdraw();
        }
    }
    
    public void drawAnimation(final Animation lIIlIllIllI, final float lIIlIlllIlI, final float lIIlIlllIIl, final Color lIIlIllIIll) {
        this.predraw();
        lIIlIllIllI.draw(lIIlIlllIlI, lIIlIlllIIl, lIIlIllIIll);
        this.currentColor.bind();
        this.postdraw();
    }
    
    public void draw(final Shape llIlllllIlI) {
        this.predraw();
        TextureImpl.bindNone();
        this.currentColor.bind();
        ShapeRenderer.draw(llIlllllIlI);
        this.postdraw();
    }
    
    public void drawImage(final Image lIIlIIlIIIl, final float lIIlIIllIlI, final float lIIlIIllIIl, final float lIIlIIllIII, final float lIIlIIlIlll, final float lIIlIIIllII, final float lIIlIIlIlIl, final float lIIlIIIlIlI, final float lIIlIIlIIll) {
        this.predraw();
        lIIlIIlIIIl.draw(lIIlIIllIlI, lIIlIIllIIl, lIIlIIllIII, lIIlIIlIlll, lIIlIIIllII, lIIlIIlIlIl, lIIlIIIlIlI, lIIlIIlIIll);
        this.currentColor.bind();
        this.postdraw();
    }
    
    public void copyArea(final Image lIIIllIIlIl, final int lIIIllIlIIl, final int lIIIllIlIII) {
        final int lIIIllIIlll = lIIIllIIlIl.getTexture().hasAlpha() ? 6408 : 6407;
        lIIIllIIlIl.bind();
        Graphics.GL.glCopyTexImage2D(3553, 0, lIIIllIIlll, lIIIllIlIIl, this.screenHeight - (lIIIllIlIII + lIIIllIIlIl.getHeight()), lIIIllIIlIl.getTexture().getTextureWidth(), lIIIllIIlIl.getTexture().getTextureHeight(), 0);
        lIIIllIIlIl.ensureInverted();
    }
    
    static {
        DEFAULT_SEGMENTS = 50;
        Graphics.GL = Renderer.get();
        Graphics.LSR = Renderer.getLineStripRenderer();
        Graphics.MODE_NORMAL = 1;
        Graphics.MODE_ALPHA_MAP = 2;
        Graphics.MODE_ALPHA_BLEND = 3;
        Graphics.MODE_COLOR_MULTIPLY = 4;
        Graphics.MODE_ADD = 5;
        Graphics.MODE_SCREEN = 6;
        Graphics.currentGraphics = null;
    }
    
    public void drawString(final String lIIllIlllll, final float lIIllIllllI, final float lIIllIlllIl) {
        this.predraw();
        this.font.drawString(lIIllIllllI, lIIllIlllIl, lIIllIlllll, this.currentColor);
        this.postdraw();
    }
    
    public void drawArc(final float lIlllIIllII, final float lIlllIlIllI, final float lIlllIlIlIl, final float lIlllIIlIIl, final int lIlllIlIIll, final float lIlllIlIIlI, float lIlllIIIllI) {
        this.predraw();
        TextureImpl.bindNone();
        this.currentColor.bind();
        while (lIlllIIIllI < lIlllIlIIlI) {
            lIlllIIIllI += 360.0f;
        }
        final float lIlllIlIIII = lIlllIIllII + lIlllIlIlIl / 2.0f;
        final float lIlllIIllll = lIlllIlIllI + lIlllIIlIIl / 2.0f;
        Graphics.LSR.start();
        for (int lIlllIIlllI = 360 / lIlllIlIIll, lIlllIllIIl = (int)lIlllIlIIlI; lIlllIllIIl < (int)(lIlllIIIllI + lIlllIIlllI); lIlllIllIIl += lIlllIIlllI) {
            float lIlllIlllII = (float)lIlllIllIIl;
            if (lIlllIlllII > lIlllIIIllI) {
                lIlllIlllII = lIlllIIIllI;
            }
            final float lIlllIllIll = (float)(lIlllIlIIII + FastTrig.cos(Math.toRadians(lIlllIlllII)) * lIlllIlIlIl / 2.0);
            final float lIlllIllIlI = (float)(lIlllIIllll + FastTrig.sin(Math.toRadians(lIlllIlllII)) * lIlllIIlIIl / 2.0);
            Graphics.LSR.vertex(lIlllIllIll, lIlllIllIlI);
        }
        Graphics.LSR.end();
        this.postdraw();
    }
    
    public void fillOval(final float lIllIllIIll, final float lIllIllIlll, final float lIllIllIllI, final float lIllIllIlIl) {
        this.fillOval(lIllIllIIll, lIllIllIlll, lIllIllIllI, lIllIllIlIl, 50);
    }
    
    public void drawArc(final float lIlllllIIIl, final float lIlllllIIII, final float lIllllIllll, final float lIlllllIlIl, final float lIllllIllIl, final float lIlllllIIll) {
        this.drawArc(lIlllllIIIl, lIlllllIIII, lIllllIllll, lIlllllIlIl, 50, lIllllIllIl, lIlllllIIll);
    }
    
    public void drawOval(final float llIIIIIlIll, final float llIIIIIIlII, final float llIIIIIIIll, final float llIIIIIlIII, final int llIIIIIIlll) {
        this.drawArc(llIIIIIlIll, llIIIIIIlII, llIIIIIIIll, llIIIIIlIII, llIIIIIIlll, 0.0f, 360.0f);
    }
    
    public void drawRoundRect(final float lIlIlIlIIII, final float lIlIlIIlIIl, final float lIlIlIIlllI, final float lIlIlIIIlll, final int lIlIlIIIllI) {
        this.drawRoundRect(lIlIlIlIIII, lIlIlIIlIIl, lIlIlIIlllI, lIlIlIIIlll, lIlIlIIIllI, 50);
    }
}
