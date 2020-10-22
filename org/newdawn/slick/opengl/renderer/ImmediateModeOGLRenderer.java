package org.newdawn.slick.opengl.renderer;

import java.nio.*;
import org.lwjgl.opengl.*;

public class ImmediateModeOGLRenderer implements SGL
{
    private /* synthetic */ int height;
    private /* synthetic */ float[] current;
    protected /* synthetic */ float alphaScale;
    private /* synthetic */ int width;
    
    @Override
    public void initDisplay(final int llllllllllllllllllIIlIIIlIlIlIlI, final int llllllllllllllllllIIlIIIlIlIlIIl) {
        this.width = llllllllllllllllllIIlIIIlIlIlIlI;
        this.height = llllllllllllllllllIIlIIIlIlIlIIl;
        final String llllllllllllllllllIIlIIIlIlIlIII = GL11.glGetString(7939);
        GL11.glEnable(3553);
        GL11.glShadeModel(7425);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glViewport(0, 0, llllllllllllllllllIIlIIIlIlIlIlI, llllllllllllllllllIIlIIIlIlIlIIl);
        GL11.glMatrixMode(5888);
    }
    
    @Override
    public boolean canTextureMirrorClamp() {
        return GLContext.getCapabilities().GL_EXT_texture_mirror_clamp;
    }
    
    @Override
    public void glPointSize(final float llllllllllllllllllIIIllllllllIII) {
        GL11.glPointSize(llllllllllllllllllIIIllllllllIII);
    }
    
    @Override
    public void glVertex2f(final float llllllllllllllllllIIIllllIIlllIl, final float llllllllllllllllllIIIllllIIlllII) {
        GL11.glVertex2f(llllllllllllllllllIIIllllIIlllIl, llllllllllllllllllIIIllllIIlllII);
    }
    
    @Override
    public void glLoadMatrix(final FloatBuffer llllllllllllllllllIIIlllIllIIlIl) {
        GL11.glLoadMatrix(llllllllllllllllllIIIlllIllIIlIl);
    }
    
    @Override
    public void glTexCoord2f(final float llllllllllllllllllIIIllllIlllIII, final float llllllllllllllllllIIIllllIllIlll) {
        GL11.glTexCoord2f(llllllllllllllllllIIIllllIlllIII, llllllllllllllllllIIIllllIllIlll);
    }
    
    @Override
    public void flush() {
    }
    
    @Override
    public boolean canSecondaryColor() {
        return GLContext.getCapabilities().GL_EXT_secondary_color;
    }
    
    @Override
    public void glTexParameteri(final int llllllllllllllllllIIIllllIIIIlll, final int llllllllllllllllllIIIllllIIIlIIl, final int llllllllllllllllllIIIllllIIIlIII) {
        GL11.glTexParameteri(llllllllllllllllllIIIllllIIIIlll, llllllllllllllllllIIIllllIIIlIIl, llllllllllllllllllIIIllllIIIlIII);
    }
    
    @Override
    public void glTexEnvi(final int llllllllllllllllllIIIllllIllIIII, final int llllllllllllllllllIIIllllIlIllII, final int llllllllllllllllllIIIllllIlIlllI) {
        GL11.glTexEnvi(llllllllllllllllllIIIllllIllIIII, llllllllllllllllllIIIllllIlIllII, llllllllllllllllllIIIllllIlIlllI);
    }
    
    @Override
    public void glCallList(final int llllllllllllllllllIIlIIIlIIIIlIl) {
        GL11.glCallList(llllllllllllllllllIIlIIIlIIIIlIl);
    }
    
    @Override
    public void glPushMatrix() {
        GL11.glPushMatrix();
    }
    
    @Override
    public void glCopyTexImage2D(final int llllllllllllllllllIIlIIIIlIIIlll, final int llllllllllllllllllIIlIIIIIlllllI, final int llllllllllllllllllIIlIIIIlIIIlIl, final int llllllllllllllllllIIlIIIIlIIIlII, final int llllllllllllllllllIIlIIIIlIIIIll, final int llllllllllllllllllIIlIIIIIlllIlI, final int llllllllllllllllllIIlIIIIlIIIIIl, final int llllllllllllllllllIIlIIIIIlllIII) {
        GL11.glCopyTexImage2D(llllllllllllllllllIIlIIIIlIIIlll, llllllllllllllllllIIlIIIIIlllllI, llllllllllllllllllIIlIIIIlIIIlIl, llllllllllllllllllIIlIIIIlIIIlII, llllllllllllllllllIIlIIIIlIIIIll, llllllllllllllllllIIlIIIIIlllIlI, llllllllllllllllllIIlIIIIlIIIIIl, llllllllllllllllllIIlIIIIIlllIII);
    }
    
    public ImmediateModeOGLRenderer() {
        this.current = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
        this.alphaScale = 1.0f;
    }
    
    @Override
    public void glDisable(final int llllllllllllllllllIIlIIIIIllIIIl) {
        GL11.glDisable(llllllllllllllllllIIlIIIIIllIIIl);
    }
    
    @Override
    public void glDeleteTextures(final IntBuffer llllllllllllllllllIIlIIIIIllIlIl) {
        GL11.glDeleteTextures(llllllllllllllllllIIlIIIIIllIlIl);
    }
    
    @Override
    public void glGetTexImage(final int llllllllllllllllllIIlIIIIIIlIIIl, final int llllllllllllllllllIIlIIIIIIIlIll, final int llllllllllllllllllIIlIIIIIIIlIlI, final int llllllllllllllllllIIlIIIIIIIlIIl, final ByteBuffer llllllllllllllllllIIlIIIIIIIlIII) {
        GL11.glGetTexImage(llllllllllllllllllIIlIIIIIIlIIIl, llllllllllllllllllIIlIIIIIIIlIll, llllllllllllllllllIIlIIIIIIIlIlI, llllllllllllllllllIIlIIIIIIIlIIl, llllllllllllllllllIIlIIIIIIIlIII);
    }
    
    @Override
    public void glColorMask(final boolean llllllllllllllllllIIlIIIIlIlIlII, final boolean llllllllllllllllllIIlIIIIlIlIIll, final boolean llllllllllllllllllIIlIIIIlIlIllI, final boolean llllllllllllllllllIIlIIIIlIlIlIl) {
        GL11.glColorMask(llllllllllllllllllIIlIIIIlIlIlII, llllllllllllllllllIIlIIIIlIlIIll, llllllllllllllllllIIlIIIIlIlIllI, llllllllllllllllllIIlIIIIlIlIlIl);
    }
    
    @Override
    public void glColor4f(final float llllllllllllllllllIIlIIIIllIIllI, final float llllllllllllllllllIIlIIIIllIIIII, final float llllllllllllllllllIIlIIIIlIlllll, float llllllllllllllllllIIlIIIIlIllllI) {
        llllllllllllllllllIIlIIIIlIllllI *= (int)this.alphaScale;
        GL11.glColor4f(this.current[0] = llllllllllllllllllIIlIIIIllIIllI, this.current[1] = llllllllllllllllllIIlIIIIllIIIII, this.current[2] = llllllllllllllllllIIlIIIIlIlllll, this.current[3] = llllllllllllllllllIIlIIIIlIllllI);
    }
    
    @Override
    public void glEnd() {
        GL11.glEnd();
    }
    
    @Override
    public void glNewList(final int llllllllllllllllllIIIlllllllllIl, final int llllllllllllllllllIIIllllllllllI) {
        GL11.glNewList(llllllllllllllllllIIIlllllllllIl, llllllllllllllllllIIIllllllllllI);
    }
    
    @Override
    public void glBegin(final int llllllllllllllllllIIlIIIlIIllIII) {
        GL11.glBegin(llllllllllllllllllIIlIIIlIIllIII);
    }
    
    @Override
    public int glGenLists(final int llllllllllllllllllIIlIIIIIlIIlll) {
        return GL11.glGenLists(llllllllllllllllllIIlIIIIIlIIlll);
    }
    
    @Override
    public void glTexSubImage2D(final int llllllllllllllllllIIIlllIIllIIII, final int llllllllllllllllllIIIlllIIlllIII, final int llllllllllllllllllIIIlllIIllIlll, final int llllllllllllllllllIIIlllIIlIllIl, final int llllllllllllllllllIIIlllIIlIllII, final int llllllllllllllllllIIIlllIIlIlIll, final int llllllllllllllllllIIIlllIIllIIll, final int llllllllllllllllllIIIlllIIlIlIIl, final ByteBuffer llllllllllllllllllIIIlllIIllIIIl) {
        GL11.glTexSubImage2D(llllllllllllllllllIIIlllIIllIIII, llllllllllllllllllIIIlllIIlllIII, llllllllllllllllllIIIlllIIllIlll, llllllllllllllllllIIIlllIIlIllIl, llllllllllllllllllIIIlllIIlIllII, llllllllllllllllllIIIlllIIlIlIll, llllllllllllllllllIIIlllIIllIIll, llllllllllllllllllIIIlllIIlIlIIl, llllllllllllllllllIIIlllIIllIIIl);
    }
    
    @Override
    public void glGenTextures(final IntBuffer llllllllllllllllllIIIlllIllIIIIl) {
        GL11.glGenTextures(llllllllllllllllllIIIlllIllIIIIl);
    }
    
    @Override
    public void glClearDepth(final float llllllllllllllllllIIIlllIlllIlll) {
        GL11.glClearDepth((double)llllllllllllllllllIIIlllIlllIlll);
    }
    
    @Override
    public void glBindTexture(final int llllllllllllllllllIIlIIIlIIlIIIl, final int llllllllllllllllllIIlIIIlIIlIIlI) {
        GL11.glBindTexture(llllllllllllllllllIIlIIIlIIlIIIl, llllllllllllllllllIIlIIIlIIlIIlI);
    }
    
    @Override
    public void glClear(final int llllllllllllllllllIIlIIIlIIIIIlI) {
        GL11.glClear(llllllllllllllllllIIlIIIlIIIIIlI);
    }
    
    @Override
    public float[] getCurrentColor() {
        return this.current;
    }
    
    @Override
    public void glClipPlane(final int llllllllllllllllllIIlIIIIllIlllI, final DoubleBuffer llllllllllllllllllIIlIIIIllIllll) {
        GL11.glClipPlane(llllllllllllllllllIIlIIIIllIlllI, llllllllllllllllllIIlIIIIllIllll);
    }
    
    @Override
    public void glScissor(final int llllllllllllllllllIIIlllllIIIIll, final int llllllllllllllllllIIIllllIlllllI, final int llllllllllllllllllIIIllllIllllIl, final int llllllllllllllllllIIIllllIllllII) {
        GL11.glScissor(llllllllllllllllllIIIlllllIIIIll, llllllllllllllllllIIIllllIlllllI, llllllllllllllllllIIIllllIllllIl, llllllllllllllllllIIIllllIllllII);
    }
    
    @Override
    public void glBlendFunc(final int llllllllllllllllllIIlIIIlIIIllII, final int llllllllllllllllllIIlIIIlIIIlIll) {
        GL11.glBlendFunc(llllllllllllllllllIIlIIIlIIIllII, llllllllllllllllllIIlIIIlIIIlIll);
    }
    
    @Override
    public void glDepthMask(final boolean llllllllllllllllllIIIlllIlllIIII) {
        GL11.glDepthMask(llllllllllllllllllIIIlllIlllIIII);
    }
    
    @Override
    public void glClearColor(final float llllllllllllllllllIIlIIIIlllIlll, final float llllllllllllllllllIIlIIIIlllIllI, final float llllllllllllllllllIIlIIIIllllIIl, final float llllllllllllllllllIIlIIIIllllIII) {
        GL11.glClearColor(llllllllllllllllllIIlIIIIlllIlll, llllllllllllllllllIIlIIIIlllIllI, llllllllllllllllllIIlIIIIllllIIl, llllllllllllllllllIIlIIIIllllIII);
    }
    
    @Override
    public void glVertex3f(final float llllllllllllllllllIIIllllIIlIlIl, final float llllllllllllllllllIIIllllIIlIIIl, final float llllllllllllllllllIIIllllIIlIIII) {
        GL11.glVertex3f(llllllllllllllllllIIIllllIIlIlIl, llllllllllllllllllIIIllllIIlIIIl, llllllllllllllllllIIIllllIIlIIII);
    }
    
    @Override
    public void glLineWidth(final float llllllllllllllllllIIlIIIIIIIIlII) {
        GL11.glLineWidth(llllllllllllllllllIIlIIIIIIIIlII);
    }
    
    @Override
    public void glReadPixels(final int llllllllllllllllllIIIllllllIIllI, final int llllllllllllllllllIIIllllllIIlIl, final int llllllllllllllllllIIIllllllIlIll, final int llllllllllllllllllIIIllllllIlIlI, final int llllllllllllllllllIIIllllllIIIlI, final int llllllllllllllllllIIIllllllIlIII, final ByteBuffer llllllllllllllllllIIIllllllIIIII) {
        GL11.glReadPixels(llllllllllllllllllIIIllllllIIllI, llllllllllllllllllIIIllllllIIlIl, llllllllllllllllllIIIllllllIlIll, llllllllllllllllllIIIllllllIlIlI, llllllllllllllllllIIIllllllIIIlI, llllllllllllllllllIIIllllllIlIII, llllllllllllllllllIIIllllllIIIII);
    }
    
    @Override
    public void glRotatef(final float llllllllllllllllllIIIlllllIlIllI, final float llllllllllllllllllIIIlllllIllIIl, final float llllllllllllllllllIIIlllllIllIII, final float llllllllllllllllllIIIlllllIlIlll) {
        GL11.glRotatef(llllllllllllllllllIIIlllllIlIllI, llllllllllllllllllIIIlllllIllIIl, llllllllllllllllllIIIlllllIllIII, llllllllllllllllllIIIlllllIlIlll);
    }
    
    @Override
    public void glDeleteLists(final int llllllllllllllllllIIIlllIlllllII, final int llllllllllllllllllIIIlllIlllllIl) {
        GL11.glDeleteLists(llllllllllllllllllIIIlllIlllllII, llllllllllllllllllIIIlllIlllllIl);
    }
    
    @Override
    public void glTranslatef(final float llllllllllllllllllIIIllllIlIIIll, final float llllllllllllllllllIIIllllIlIIlIl, final float llllllllllllllllllIIIllllIlIIIIl) {
        GL11.glTranslatef(llllllllllllllllllIIIllllIlIIIll, llllllllllllllllllIIIllllIlIIlIl, llllllllllllllllllIIIllllIlIIIIl);
    }
    
    @Override
    public void glSecondaryColor3ubEXT(final byte llllllllllllllllllIIIlllIIlIIIIl, final byte llllllllllllllllllIIIlllIIIlllIl, final byte llllllllllllllllllIIIlllIIIlllll) {
        EXTSecondaryColor.glSecondaryColor3ubEXT(llllllllllllllllllIIIlllIIlIIIIl, llllllllllllllllllIIIlllIIIlllIl, llllllllllllllllllIIIlllIIIlllll);
    }
    
    @Override
    public void glEnable(final int llllllllllllllllllIIlIIIIIlIllIl) {
        GL11.glEnable(llllllllllllllllllIIlIIIIIlIllIl);
    }
    
    @Override
    public void glTexImage2D(final int llllllllllllllllllIIIlllIlIIllII, final int llllllllllllllllllIIIlllIlIIlIll, final int llllllllllllllllllIIIlllIlIIlIlI, final int llllllllllllllllllIIIlllIlIlIIlI, final int llllllllllllllllllIIIlllIlIIlIII, final int llllllllllllllllllIIIlllIlIIIlll, final int llllllllllllllllllIIIlllIlIIllll, final int llllllllllllllllllIIIlllIlIIIlIl, final ByteBuffer llllllllllllllllllIIIlllIlIIIlII) {
        GL11.glTexImage2D(llllllllllllllllllIIIlllIlIIllII, llllllllllllllllllIIIlllIlIIlIll, llllllllllllllllllIIIlllIlIIlIlI, llllllllllllllllllIIIlllIlIlIIlI, llllllllllllllllllIIIlllIlIIlIII, llllllllllllllllllIIIlllIlIIIlll, llllllllllllllllllIIIlllIlIIllll, llllllllllllllllllIIIlllIlIIIlIl, llllllllllllllllllIIIlllIlIIIlII);
    }
    
    @Override
    public void glLoadIdentity() {
        GL11.glLoadIdentity();
    }
    
    @Override
    public void glPopMatrix() {
        GL11.glPopMatrix();
    }
    
    @Override
    public void glEndList() {
        GL11.glEndList();
    }
    
    @Override
    public void glGetInteger(final int llllllllllllllllllIIlIIIIIIllIIl, final IntBuffer llllllllllllllllllIIlIIIIIIllIlI) {
        GL11.glGetInteger(llllllllllllllllllIIlIIIIIIllIIl, llllllllllllllllllIIlIIIIIIllIlI);
    }
    
    @Override
    public void glDepthFunc(final int llllllllllllllllllIIIlllIlllIIll) {
        GL11.glDepthFunc(llllllllllllllllllIIIlllIlllIIll);
    }
    
    @Override
    public void glGetFloat(final int llllllllllllllllllIIlIIIIIlIIIlI, final FloatBuffer llllllllllllllllllIIlIIIIIIlllll) {
        GL11.glGetFloat(llllllllllllllllllIIlIIIIIlIIIlI, llllllllllllllllllIIlIIIIIIlllll);
    }
    
    @Override
    public void enterOrtho(final int llllllllllllllllllIIlIIIlIIlllll, final int llllllllllllllllllIIlIIIlIIllllI) {
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, (double)this.width, (double)this.height, 0.0, 1.0, -1.0);
        GL11.glMatrixMode(5888);
        GL11.glTranslatef((float)((this.width - llllllllllllllllllIIlIIIlIIlllll) / 2), (float)((this.height - llllllllllllllllllIIlIIIlIIllllI) / 2), 0.0f);
    }
    
    @Override
    public void glGetError() {
        GL11.glGetError();
    }
    
    @Override
    public void glScalef(final float llllllllllllllllllIIIlllllIIlIll, final float llllllllllllllllllIIIlllllIIlIlI, final float llllllllllllllllllIIIlllllIIllII) {
        GL11.glScalef(llllllllllllllllllIIIlllllIIlIll, llllllllllllllllllIIIlllllIIlIlI, llllllllllllllllllIIIlllllIIllII);
    }
    
    @Override
    public void setGlobalAlphaScale(final float llllllllllllllllllIIIlllIllIlIll) {
        this.alphaScale = llllllllllllllllllIIIlllIllIlIll;
    }
}
