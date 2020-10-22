package org.newdawn.slick.opengl.pbuffer;

import org.newdawn.slick.*;
import org.lwjgl.*;
import java.nio.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;
import org.lwjgl.opengl.*;

public class FBOGraphics extends Graphics
{
    private /* synthetic */ Image image;
    private /* synthetic */ boolean valid;
    private /* synthetic */ int FBO;
    
    @Override
    protected void disable() {
        FBOGraphics.GL.flush();
        this.unbind();
        GL11.glPopClientAttrib();
        GL11.glPopAttrib();
        GL11.glMatrixMode(5888);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5888);
        SlickCallable.leaveSafeBlock();
    }
    
    private void completeCheck() throws SlickException {
        final int llllllllllllllllIlIlIllIllIIIIlI = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
        switch (llllllllllllllllIlIlIllIllIIIIlI) {
            case 36053: {}
            case 36054: {
                throw new SlickException(String.valueOf(new StringBuilder().append("FrameBuffer: ").append(this.FBO).append(", has caused a GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT exception")));
            }
            case 36055: {
                throw new SlickException(String.valueOf(new StringBuilder().append("FrameBuffer: ").append(this.FBO).append(", has caused a GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT exception")));
            }
            case 36057: {
                throw new SlickException(String.valueOf(new StringBuilder().append("FrameBuffer: ").append(this.FBO).append(", has caused a GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT exception")));
            }
            case 36059: {
                throw new SlickException(String.valueOf(new StringBuilder().append("FrameBuffer: ").append(this.FBO).append(", has caused a GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT exception")));
            }
            case 36058: {
                throw new SlickException(String.valueOf(new StringBuilder().append("FrameBuffer: ").append(this.FBO).append(", has caused a GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT exception")));
            }
            case 36060: {
                throw new SlickException(String.valueOf(new StringBuilder().append("FrameBuffer: ").append(this.FBO).append(", has caused a GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT exception")));
            }
            default: {
                throw new SlickException(String.valueOf(new StringBuilder().append("Unexpected reply from glCheckFramebufferStatusEXT: ").append(llllllllllllllllIlIlIllIllIIIIlI)));
            }
        }
    }
    
    @Override
    public void flush() {
        super.flush();
        this.image.flushPixelData();
    }
    
    private void init() throws SlickException {
        final IntBuffer llllllllllllllllIlIlIllIlIlllIIl = BufferUtils.createIntBuffer(1);
        EXTFramebufferObject.glGenFramebuffersEXT(llllllllllllllllIlIlIllIlIlllIIl);
        this.FBO = llllllllllllllllIlIlIllIlIlllIIl.get();
        try {
            final Texture llllllllllllllllIlIlIllIlIllllII = InternalTextureLoader.get().createTexture(this.image.getWidth(), this.image.getHeight(), this.image.getFilter());
            EXTFramebufferObject.glBindFramebufferEXT(36160, this.FBO);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, llllllllllllllllIlIlIllIlIllllII.getTextureID(), 0);
            this.completeCheck();
            this.unbind();
            this.clear();
            this.flush();
            this.drawImage(this.image, 0.0f, 0.0f);
            this.image.setTexture(llllllllllllllllIlIlIllIlIllllII);
        }
        catch (Exception llllllllllllllllIlIlIllIlIlllIll) {
            throw new SlickException("Failed to create new texture for FBO");
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
        final IntBuffer llllllllllllllllIlIlIllIlIlIIIlI = BufferUtils.createIntBuffer(1);
        llllllllllllllllIlIlIllIlIlIIIlI.put(this.FBO);
        llllllllllllllllIlIlIllIlIlIIIlI.flip();
        EXTFramebufferObject.glDeleteFramebuffersEXT(llllllllllllllllIlIlIllIlIlIIIlI);
        this.valid = false;
    }
    
    protected void initGL() {
        GL11.glEnable(3553);
        GL11.glShadeModel(7425);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glViewport(0, 0, this.screenWidth, this.screenHeight);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        this.enterOrtho();
    }
    
    private void bind() {
        EXTFramebufferObject.glBindFramebufferEXT(36160, this.FBO);
        GL11.glReadBuffer(36064);
    }
    
    private void unbind() {
        EXTFramebufferObject.glBindFramebufferEXT(36160, 0);
        GL11.glReadBuffer(1029);
    }
    
    @Override
    protected void enable() {
        if (!this.valid) {
            throw new RuntimeException("Attempt to use a destroy()ed offscreen graphics context.");
        }
        SlickCallable.enterSafeBlock();
        GL11.glPushAttrib(1048575);
        GL11.glPushClientAttrib(-1);
        GL11.glMatrixMode(5889);
        GL11.glPushMatrix();
        GL11.glMatrixMode(5888);
        GL11.glPushMatrix();
        this.bind();
        this.initGL();
    }
    
    public FBOGraphics(final Image llllllllllllllllIlIlIllIllIIlIlI) throws SlickException {
        super(llllllllllllllllIlIlIllIllIIlIlI.getTexture().getTextureWidth(), llllllllllllllllIlIlIllIllIIlIlI.getTexture().getTextureHeight());
        this.valid = true;
        this.image = llllllllllllllllIlIlIllIllIIlIlI;
        Log.debug(String.valueOf(new StringBuilder().append("Creating FBO ").append(llllllllllllllllIlIlIllIllIIlIlI.getWidth()).append("x").append(llllllllllllllllIlIlIllIllIIlIlI.getHeight())));
        final boolean llllllllllllllllIlIlIllIllIIlIIl = GLContext.getCapabilities().GL_EXT_framebuffer_object;
        if (!llllllllllllllllIlIlIllIllIIlIIl) {
            throw new SlickException("Your OpenGL card does not support FBO and hence can't handle the dynamic images required for this application.");
        }
        this.init();
    }
    
    protected void enterOrtho() {
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, (double)this.screenWidth, 0.0, (double)this.screenHeight, 1.0, -1.0);
        GL11.glMatrixMode(5888);
    }
}
