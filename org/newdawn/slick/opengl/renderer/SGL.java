package org.newdawn.slick.opengl.renderer;

import java.nio.*;

public interface SGL
{
    void glVertex2f(final float p0, final float p1);
    
    void glGetError();
    
    void glEnd();
    
    void glPointSize(final float p0);
    
    void initDisplay(final int p0, final int p1);
    
    void glTexParameteri(final int p0, final int p1, final int p2);
    
    void glScissor(final int p0, final int p1, final int p2, final int p3);
    
    void glReadPixels(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final ByteBuffer p6);
    
    void glClearColor(final float p0, final float p1, final float p2, final float p3);
    
    void glPopMatrix();
    
    void glSecondaryColor3ubEXT(final byte p0, final byte p1, final byte p2);
    
    float[] getCurrentColor();
    
    void flush();
    
    void glGenTextures(final IntBuffer p0);
    
    void glGetTexImage(final int p0, final int p1, final int p2, final int p3, final ByteBuffer p4);
    
    void glClearDepth(final float p0);
    
    void setGlobalAlphaScale(final float p0);
    
    void glClear(final int p0);
    
    void enterOrtho(final int p0, final int p1);
    
    void glRotatef(final float p0, final float p1, final float p2, final float p3);
    
    void glGetFloat(final int p0, final FloatBuffer p1);
    
    void glBlendFunc(final int p0, final int p1);
    
    void glTexSubImage2D(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final ByteBuffer p8);
    
    void glEnable(final int p0);
    
    void glDeleteTextures(final IntBuffer p0);
    
    void glCallList(final int p0);
    
    void glNewList(final int p0, final int p1);
    
    void glColorMask(final boolean p0, final boolean p1, final boolean p2, final boolean p3);
    
    boolean canTextureMirrorClamp();
    
    boolean canSecondaryColor();
    
    void glTexImage2D(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final ByteBuffer p8);
    
    void glTexEnvi(final int p0, final int p1, final int p2);
    
    void glLoadMatrix(final FloatBuffer p0);
    
    void glBegin(final int p0);
    
    void glLineWidth(final float p0);
    
    void glDeleteLists(final int p0, final int p1);
    
    void glLoadIdentity();
    
    int glGenLists(final int p0);
    
    void glTranslatef(final float p0, final float p1, final float p2);
    
    void glClipPlane(final int p0, final DoubleBuffer p1);
    
    void glBindTexture(final int p0, final int p1);
    
    void glTexCoord2f(final float p0, final float p1);
    
    void glScalef(final float p0, final float p1, final float p2);
    
    void glGetInteger(final int p0, final IntBuffer p1);
    
    void glPushMatrix();
    
    void glDepthFunc(final int p0);
    
    void glDepthMask(final boolean p0);
    
    void glDisable(final int p0);
    
    void glEndList();
    
    void glVertex3f(final float p0, final float p1, final float p2);
    
    void glCopyTexImage2D(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    void glColor4f(final float p0, final float p1, final float p2, final float p3);
}
