package org.newdawn.slick.opengl.renderer;

import java.nio.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class VAOGLRenderer extends ImmediateModeOGLRenderer
{
    private /* synthetic */ int currentType;
    private /* synthetic */ FloatBuffer textures;
    private /* synthetic */ float[] color;
    private /* synthetic */ FloatBuffer vertices;
    private /* synthetic */ float[] tex;
    private /* synthetic */ float[] texs;
    private /* synthetic */ int vertIndex;
    private /* synthetic */ FloatBuffer colors;
    private /* synthetic */ float[] cols;
    private /* synthetic */ int listMode;
    private /* synthetic */ float[] verts;
    
    @Override
    public void glClipPlane(final int lllllllllllllllllIIllIIIlIIIIlII, final DoubleBuffer lllllllllllllllllIIllIIIlIIIIIll) {
        this.applyBuffer();
        super.glClipPlane(lllllllllllllllllIIllIIIlIIIIlII, lllllllllllllllllIIllIIIlIIIIIll);
    }
    
    @Override
    public void glEnable(final int lllllllllllllllllIIllIIIIllIlIlI) {
        this.applyBuffer();
        super.glEnable(lllllllllllllllllIIllIIIIllIlIlI);
    }
    
    @Override
    public void glBindTexture(final int lllllllllllllllllIIllIIIlIlIIlIl, final int lllllllllllllllllIIllIIIlIlIIlII) {
        this.applyBuffer();
        super.glBindTexture(lllllllllllllllllIIllIIIlIlIIlIl, lllllllllllllllllIIllIIIlIlIIlII);
    }
    
    static {
        TOLERANCE = 20;
        NONE = -1;
        MAX_VERTS = 5000;
    }
    
    @Override
    public void initDisplay(final int lllllllllllllllllIIllIIIllllllIl, final int lllllllllllllllllIIllIIIlllllIIl) {
        super.initDisplay(lllllllllllllllllIIllIIIllllllIl, lllllllllllllllllIIllIIIlllllIIl);
        this.startBuffer();
        GL11.glEnableClientState(32884);
        GL11.glEnableClientState(32888);
        GL11.glEnableClientState(32886);
    }
    
    @Override
    public void glVertex3f(final float lllllllllllllllllIIllIIIlIllIlII, final float lllllllllllllllllIIllIIIlIllIIll, final float lllllllllllllllllIIllIIIlIllIllI) {
        if (this.listMode > 0) {
            super.glVertex3f(lllllllllllllllllIIllIIIlIllIlII, lllllllllllllllllIIllIIIlIllIIll, lllllllllllllllllIIllIIIlIllIllI);
            return;
        }
        this.verts[this.vertIndex * 3 + 0] = lllllllllllllllllIIllIIIlIllIlII;
        this.verts[this.vertIndex * 3 + 1] = lllllllllllllllllIIllIIIlIllIIll;
        this.verts[this.vertIndex * 3 + 2] = lllllllllllllllllIIllIIIlIllIllI;
        this.cols[this.vertIndex * 4 + 0] = this.color[0];
        this.cols[this.vertIndex * 4 + 1] = this.color[1];
        this.cols[this.vertIndex * 4 + 2] = this.color[2];
        this.cols[this.vertIndex * 4 + 3] = this.color[3];
        this.texs[this.vertIndex * 2 + 0] = this.tex[0];
        this.texs[this.vertIndex * 2 + 1] = this.tex[1];
        ++this.vertIndex;
        if (this.vertIndex > 4950 && this.isSplittable(this.vertIndex, this.currentType)) {
            final int lllllllllllllllllIIllIIIlIlllIlI = this.currentType;
            this.applyBuffer();
            this.currentType = lllllllllllllllllIIllIIIlIlllIlI;
        }
    }
    
    @Override
    public void glLineWidth(final float lllllllllllllllllIIllIIIIllIIIlI) {
        this.applyBuffer();
        super.glLineWidth(lllllllllllllllllIIllIIIIllIIIlI);
    }
    
    @Override
    public void glScissor(final int lllllllllllllllllIIllIIIIIllIlII, final int lllllllllllllllllIIllIIIIIllIIll, final int lllllllllllllllllIIllIIIIIllIIlI, final int lllllllllllllllllIIllIIIIIlIllII) {
        this.applyBuffer();
        super.glScissor(lllllllllllllllllIIllIIIIIllIlII, lllllllllllllllllIIllIIIIIllIIll, lllllllllllllllllIIllIIIIIllIIlI, lllllllllllllllllIIllIIIIIlIllII);
    }
    
    @Override
    public void glCallList(final int lllllllllllllllllIIllIIIlIIlIIlI) {
        this.applyBuffer();
        super.glCallList(lllllllllllllllllIIllIIIlIIlIIlI);
    }
    
    @Override
    public void glPushMatrix() {
        this.applyBuffer();
        super.glPushMatrix();
    }
    
    @Override
    public void glNewList(final int lllllllllllllllllIIllIIIIIIIlIIl, final int lllllllllllllllllIIllIIIIIIIlIll) {
        ++this.listMode;
        super.glNewList(lllllllllllllllllIIllIIIIIIIlIIl, lllllllllllllllllIIllIIIIIIIlIll);
    }
    
    private void flushBuffer() {
        if (this.vertIndex == 0) {
            return;
        }
        if (this.currentType == -1) {
            return;
        }
        if (this.vertIndex < 20) {
            GL11.glBegin(this.currentType);
            for (int lllllllllllllllllIIllIIIllllIIll = 0; lllllllllllllllllIIllIIIllllIIll < this.vertIndex; ++lllllllllllllllllIIllIIIllllIIll) {
                GL11.glColor4f(this.cols[lllllllllllllllllIIllIIIllllIIll * 4 + 0], this.cols[lllllllllllllllllIIllIIIllllIIll * 4 + 1], this.cols[lllllllllllllllllIIllIIIllllIIll * 4 + 2], this.cols[lllllllllllllllllIIllIIIllllIIll * 4 + 3]);
                GL11.glTexCoord2f(this.texs[lllllllllllllllllIIllIIIllllIIll * 2 + 0], this.texs[lllllllllllllllllIIllIIIllllIIll * 2 + 1]);
                GL11.glVertex3f(this.verts[lllllllllllllllllIIllIIIllllIIll * 3 + 0], this.verts[lllllllllllllllllIIllIIIllllIIll * 3 + 1], this.verts[lllllllllllllllllIIllIIIllllIIll * 3 + 2]);
            }
            GL11.glEnd();
            this.currentType = -1;
            return;
        }
        this.vertices.clear();
        this.colors.clear();
        this.textures.clear();
        this.vertices.put(this.verts, 0, this.vertIndex * 3);
        this.colors.put(this.cols, 0, this.vertIndex * 4);
        this.textures.put(this.texs, 0, this.vertIndex * 2);
        this.vertices.flip();
        this.colors.flip();
        this.textures.flip();
        GL11.glVertexPointer(3, 0, this.vertices);
        GL11.glColorPointer(4, 0, this.colors);
        GL11.glTexCoordPointer(2, 0, this.textures);
        GL11.glDrawArrays(this.currentType, 0, this.vertIndex);
        this.currentType = -1;
    }
    
    @Override
    public void glBegin(final int lllllllllllllllllIIllIIIlllIIllI) {
        if (this.listMode > 0) {
            super.glBegin(lllllllllllllllllIIllIIIlllIIllI);
            return;
        }
        if (this.currentType != lllllllllllllllllIIllIIIlllIIllI) {
            this.applyBuffer();
            this.currentType = lllllllllllllllllIIllIIIlllIIllI;
        }
    }
    
    @Override
    public void glPopMatrix() {
        this.applyBuffer();
        super.glPopMatrix();
    }
    
    @Override
    public void glClear(final int lllllllllllllllllIIllIIIlIIIlllI) {
        this.applyBuffer();
        super.glClear(lllllllllllllllllIIllIIIlIIIlllI);
    }
    
    @Override
    public void glEndList() {
        --this.listMode;
        super.glEndList();
    }
    
    @Override
    public void glColorMask(final boolean lllllllllllllllllIIllIIIIlllllII, final boolean lllllllllllllllllIIllIIIIllllIll, final boolean lllllllllllllllllIIllIIIIlllIlIl, final boolean lllllllllllllllllIIllIIIIllllIIl) {
        this.applyBuffer();
        super.glColorMask(lllllllllllllllllIIllIIIIlllllII, lllllllllllllllllIIllIIIIllllIll, lllllllllllllllllIIllIIIIlllIlIl, lllllllllllllllllIIllIIIIllllIIl);
    }
    
    @Override
    public void glDisable(final int lllllllllllllllllIIllIIIIllIlllI) {
        this.applyBuffer();
        super.glDisable(lllllllllllllllllIIllIIIIllIlllI);
    }
    
    @Override
    public void flush() {
        super.flush();
        this.applyBuffer();
    }
    
    @Override
    public void glRotatef(final float lllllllllllllllllIIllIIIIlIIlIlI, final float lllllllllllllllllIIllIIIIlIIlllI, final float lllllllllllllllllIIllIIIIlIIlIII, final float lllllllllllllllllIIllIIIIlIIllII) {
        this.applyBuffer();
        super.glRotatef(lllllllllllllllllIIllIIIIlIIlIlI, lllllllllllllllllIIllIIIIlIIlllI, lllllllllllllllllIIllIIIIlIIlIII, lllllllllllllllllIIllIIIIlIIllII);
    }
    
    @Override
    public void glPointSize(final float lllllllllllllllllIIllIIIIlIlllII) {
        this.applyBuffer();
        super.glPointSize(lllllllllllllllllIIllIIIIlIlllII);
    }
    
    @Override
    public void glVertex2f(final float lllllllllllllllllIIllIIIllIIIIIl, final float lllllllllllllllllIIllIIIllIIIIII) {
        if (this.listMode > 0) {
            super.glVertex2f(lllllllllllllllllIIllIIIllIIIIIl, lllllllllllllllllIIllIIIllIIIIII);
            return;
        }
        this.glVertex3f(lllllllllllllllllIIllIIIllIIIIIl, lllllllllllllllllIIllIIIllIIIIII, 0.0f);
    }
    
    @Override
    public void glTexEnvi(final int lllllllllllllllllIIllIIIIIlIIllI, final int lllllllllllllllllIIllIIIIIlIIlIl, final int lllllllllllllllllIIllIIIIIlIIlII) {
        this.applyBuffer();
        super.glTexEnvi(lllllllllllllllllIIllIIIIIlIIllI, lllllllllllllllllIIllIIIIIlIIlIl, lllllllllllllllllIIllIIIIIlIIlII);
    }
    
    private void startBuffer() {
        this.vertIndex = 0;
    }
    
    @Override
    public void glScalef(final float lllllllllllllllllIIllIIIIIllllIl, final float lllllllllllllllllIIllIIIIlIIIIII, final float lllllllllllllllllIIllIIIIIllllll) {
        this.applyBuffer();
        super.glScalef(lllllllllllllllllIIllIIIIIllllIl, lllllllllllllllllIIllIIIIlIIIIII, lllllllllllllllllIIllIIIIIllllll);
    }
    
    private void applyBuffer() {
        if (this.listMode > 0) {
            return;
        }
        if (this.vertIndex != 0) {
            this.flushBuffer();
            this.startBuffer();
        }
        super.glColor4f(this.color[0], this.color[1], this.color[2], this.color[3]);
    }
    
    public VAOGLRenderer() {
        this.currentType = -1;
        this.color = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
        this.tex = new float[] { 0.0f, 0.0f };
        this.verts = new float[15000];
        this.cols = new float[20000];
        this.texs = new float[15000];
        this.vertices = BufferUtils.createFloatBuffer(15000);
        this.colors = BufferUtils.createFloatBuffer(20000);
        this.textures = BufferUtils.createFloatBuffer(10000);
        this.listMode = 0;
    }
    
    @Override
    public void glLoadMatrix(final FloatBuffer lllllllllllllllllIIllIIIIIIIIIIl) {
        this.flushBuffer();
        super.glLoadMatrix(lllllllllllllllllIIllIIIIIIIIIIl);
    }
    
    @Override
    public void glBlendFunc(final int lllllllllllllllllIIllIIIlIIllIIl, final int lllllllllllllllllIIllIIIlIIllIII) {
        this.applyBuffer();
        super.glBlendFunc(lllllllllllllllllIIllIIIlIIllIIl, lllllllllllllllllIIllIIIlIIllIII);
    }
    
    @Override
    public void glEnd() {
        if (this.listMode > 0) {
            super.glEnd();
        }
    }
    
    @Override
    public void glTexCoord2f(final float lllllllllllllllllIIllIIIllIIllIl, final float lllllllllllllllllIIllIIIllIIlIIl) {
        if (this.listMode > 0) {
            super.glTexCoord2f(lllllllllllllllllIIllIIIllIIllIl, lllllllllllllllllIIllIIIllIIlIIl);
            return;
        }
        this.tex[0] = lllllllllllllllllIIllIIIllIIllIl;
        this.tex[1] = lllllllllllllllllIIllIIIllIIlIIl;
    }
    
    @Override
    public void glTranslatef(final float lllllllllllllllllIIllIIIIIIllIlI, final float lllllllllllllllllIIllIIIIIIlIlIl, final float lllllllllllllllllIIllIIIIIIlIlII) {
        this.applyBuffer();
        super.glTranslatef(lllllllllllllllllIIllIIIIIIllIlI, lllllllllllllllllIIllIIIIIIlIlIl, lllllllllllllllllIIllIIIIIIlIlII);
    }
    
    @Override
    public void glColor4f(final float lllllllllllllllllIIllIIIllIlllIl, final float lllllllllllllllllIIllIIIllIlllII, final float lllllllllllllllllIIllIIIllIlIllI, float lllllllllllllllllIIllIIIllIlIlIl) {
        lllllllllllllllllIIllIIIllIlIlIl *= (char)this.alphaScale;
        this.color[0] = lllllllllllllllllIIllIIIllIlllIl;
        this.color[1] = lllllllllllllllllIIllIIIllIlllII;
        this.color[2] = lllllllllllllllllIIllIIIllIlIllI;
        this.color[3] = lllllllllllllllllIIllIIIllIlIlIl;
        if (this.listMode > 0) {
            super.glColor4f(lllllllllllllllllIIllIIIllIlllIl, lllllllllllllllllIIllIIIllIlllII, lllllllllllllllllIIllIIIllIlIllI, lllllllllllllllllIIllIIIllIlIlIl);
        }
    }
    
    @Override
    public float[] getCurrentColor() {
        return this.color;
    }
    
    private boolean isSplittable(final int lllllllllllllllllIIllIIIlIlIllIl, final int lllllllllllllllllIIllIIIlIlIllII) {
        switch (lllllllllllllllllIIllIIIlIlIllII) {
            case 7: {
                return lllllllllllllllllIIllIIIlIlIllIl % 4 == 0;
            }
            case 4: {
                return lllllllllllllllllIIllIIIlIlIllIl % 3 == 0;
            }
            case 6913: {
                return lllllllllllllllllIIllIIIlIlIllIl % 2 == 0;
            }
            default: {
                return false;
            }
        }
    }
}
