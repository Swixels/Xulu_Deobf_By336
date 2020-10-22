package org.newdawn.slick.opengl.pbuffer;

import java.util.*;
import org.newdawn.slick.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.util.*;

public class GraphicsFactory
{
    private static /* synthetic */ boolean pbuffer;
    private static /* synthetic */ boolean fbo;
    private static /* synthetic */ boolean pbufferRT;
    private static /* synthetic */ HashMap graphics;
    private static /* synthetic */ boolean init;
    
    public static boolean usingPBuffer() {
        return !GraphicsFactory.fbo && GraphicsFactory.pbuffer;
    }
    
    public static Graphics getGraphicsForImage(final Image llllllIllIlIII) throws SlickException {
        Graphics llllllIllIlIIl = GraphicsFactory.graphics.get(llllllIllIlIII.getTexture());
        if (llllllIllIlIIl == null) {
            llllllIllIlIIl = createGraphics(llllllIllIlIII);
            GraphicsFactory.graphics.put(llllllIllIlIII.getTexture(), llllllIllIlIIl);
        }
        return llllllIllIlIIl;
    }
    
    public static void setUseFBO(final boolean llllllIllIlllI) {
        GraphicsFactory.fbo = llllllIllIlllI;
    }
    
    public static boolean usingFBO() {
        return GraphicsFactory.fbo;
    }
    
    private static void init() throws SlickException {
        GraphicsFactory.init = true;
        if (GraphicsFactory.fbo) {
            GraphicsFactory.fbo = GLContext.getCapabilities().GL_EXT_framebuffer_object;
        }
        GraphicsFactory.pbuffer = ((Pbuffer.getCapabilities() & 0x1) != 0x0);
        GraphicsFactory.pbufferRT = ((Pbuffer.getCapabilities() & 0x2) != 0x0);
        if (!GraphicsFactory.fbo && !GraphicsFactory.pbuffer && !GraphicsFactory.pbufferRT) {
            throw new SlickException("Your OpenGL card does not support offscreen buffers and hence can't handle the dynamic images required for this application.");
        }
        Log.info(String.valueOf(new StringBuilder().append("Offscreen Buffers FBO=").append(GraphicsFactory.fbo).append(" PBUFFER=").append(GraphicsFactory.pbuffer).append(" PBUFFERRT=").append(GraphicsFactory.pbufferRT)));
    }
    
    static {
        GraphicsFactory.graphics = new HashMap();
        GraphicsFactory.pbuffer = true;
        GraphicsFactory.pbufferRT = true;
        GraphicsFactory.fbo = true;
        GraphicsFactory.init = false;
    }
    
    private static Graphics createGraphics(final Image llllllIlIlllII) throws SlickException {
        init();
        if (GraphicsFactory.fbo) {
            try {
                return new FBOGraphics(llllllIlIlllII);
            }
            catch (Exception llllllIlIllllI) {
                GraphicsFactory.fbo = false;
                Log.warn("FBO failed in use, falling back to PBuffer");
            }
        }
        if (!GraphicsFactory.pbuffer) {
            throw new SlickException("Failed to create offscreen buffer even though the card reports it's possible");
        }
        if (GraphicsFactory.pbufferRT) {
            return new PBufferGraphics(llllllIlIlllII);
        }
        return new PBufferUniqueGraphics(llllllIlIlllII);
    }
    
    public static void releaseGraphicsForImage(final Image llllllIllIIIlI) throws SlickException {
        final Graphics llllllIllIIIll = GraphicsFactory.graphics.remove(llllllIllIIIlI.getTexture());
        if (llllllIllIIIll != null) {
            llllllIllIIIll.destroy();
        }
    }
}
