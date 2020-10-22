package org.newdawn.slick.util;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;
import java.nio.*;

public class BufferedImageUtil
{
    public static Texture getTexture(final String llIlIIIllllIlI, final BufferedImage llIlIIIllllIIl) throws IOException {
        final Texture llIlIIIllllIII = getTexture(llIlIIIllllIlI, llIlIIIllllIIl, 3553, 6408, 9729, 9729);
        return llIlIIIllllIII;
    }
    
    private static void copyArea(final BufferedImage llIlIIIIllllII, final int llIlIIIIllIIll, final int llIlIIIIllIIlI, final int llIlIIIIllIIIl, final int llIlIIIIlllIII, final int llIlIIIIlIllll, final int llIlIIIIlIlllI) {
        final Graphics2D llIlIIIIllIlIl = (Graphics2D)llIlIIIIllllII.getGraphics();
        llIlIIIIllIlIl.drawImage(llIlIIIIllllII.getSubimage(llIlIIIIllIIll, llIlIIIIllIIlI, llIlIIIIllIIIl, llIlIIIIlllIII), llIlIIIIllIIll + llIlIIIIlIllll, llIlIIIIllIIlI + llIlIIIIlIlllI, null);
    }
    
    public static Texture getTexture(final String llIlIIIlIlllII, final BufferedImage llIlIIIlIllIll, final int llIlIIIlIllIlI, final int llIlIIIlIIllIl, final int llIlIIIlIIllII, final int llIlIIIlIIlIll) throws IOException {
        final ImageIOImageData llIlIIIlIlIllI = new ImageIOImageData();
        int llIlIIIlIlIlIl = 0;
        final int llIlIIIlIlIlII = InternalTextureLoader.createTextureID();
        final TextureImpl llIlIIIlIlIIll = new TextureImpl(llIlIIIlIlllII, llIlIIIlIllIlI, llIlIIIlIlIlII);
        Renderer.get().glEnable(3553);
        Renderer.get().glBindTexture(llIlIIIlIllIlI, llIlIIIlIlIlII);
        final BufferedImage llIlIIIlIlIIlI = llIlIIIlIllIll;
        llIlIIIlIlIIll.setWidth(llIlIIIlIlIIlI.getWidth());
        llIlIIIlIlIIll.setHeight(llIlIIIlIlIIlI.getHeight());
        if (llIlIIIlIlIIlI.getColorModel().hasAlpha()) {
            llIlIIIlIlIlIl = 6408;
        }
        else {
            llIlIIIlIlIlIl = 6407;
        }
        final ByteBuffer llIlIIIlIlIIIl = llIlIIIlIlIllI.imageToByteBuffer(llIlIIIlIlIIlI, false, false, null);
        llIlIIIlIlIIll.setTextureHeight(llIlIIIlIlIllI.getTexHeight());
        llIlIIIlIlIIll.setTextureWidth(llIlIIIlIlIllI.getTexWidth());
        llIlIIIlIlIIll.setAlpha(llIlIIIlIlIllI.getDepth() == 32);
        if (llIlIIIlIllIlI == 3553) {
            Renderer.get().glTexParameteri(llIlIIIlIllIlI, 10241, llIlIIIlIIllII);
            Renderer.get().glTexParameteri(llIlIIIlIllIlI, 10240, llIlIIIlIIlIll);
            if (Renderer.get().canTextureMirrorClamp()) {
                Renderer.get().glTexParameteri(3553, 10242, 34627);
                Renderer.get().glTexParameteri(3553, 10243, 34627);
            }
            else {
                Renderer.get().glTexParameteri(3553, 10242, 10496);
                Renderer.get().glTexParameteri(3553, 10243, 10496);
            }
        }
        Renderer.get().glTexImage2D(llIlIIIlIllIlI, 0, llIlIIIlIIllIl, llIlIIIlIlIIll.getTextureWidth(), llIlIIIlIlIIll.getTextureHeight(), 0, llIlIIIlIlIlIl, 5121, llIlIIIlIlIIIl);
        return llIlIIIlIlIIll;
    }
    
    public static Texture getTexture(final String llIlIIIlllIIII, final BufferedImage llIlIIIllIlIll, final int llIlIIIllIlIlI) throws IOException {
        final Texture llIlIIIllIllIl = getTexture(llIlIIIlllIIII, llIlIIIllIlIll, 3553, 6408, llIlIIIllIlIlI, llIlIIIllIlIlI);
        return llIlIIIllIllIl;
    }
}
