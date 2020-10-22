package org.newdawn.slick.opengl;

import java.io.*;

public class TextureLoader
{
    public static Texture getTexture(final String lllllllllllllllllIlIIIIlIlIIIlll, final InputStream lllllllllllllllllIlIIIIlIlIIlIIl, final int lllllllllllllllllIlIIIIlIlIIlIII) throws IOException {
        return getTexture(lllllllllllllllllIlIIIIlIlIIIlll, lllllllllllllllllIlIIIIlIlIIlIIl, false, lllllllllllllllllIlIIIIlIlIIlIII);
    }
    
    public static Texture getTexture(final String lllllllllllllllllIlIIIIlIlIllIlI, final InputStream lllllllllllllllllIlIIIIlIlIlIlll) throws IOException {
        return getTexture(lllllllllllllllllIlIIIIlIlIllIlI, lllllllllllllllllIlIIIIlIlIlIlll, false, 9729);
    }
    
    public static Texture getTexture(final String lllllllllllllllllIlIIIIlIlIIIIII, final InputStream lllllllllllllllllIlIIIIlIIlllIll, final boolean lllllllllllllllllIlIIIIlIIlllIlI, final int lllllllllllllllllIlIIIIlIIllllIl) throws IOException {
        return InternalTextureLoader.get().getTexture(lllllllllllllllllIlIIIIlIIlllIll, String.valueOf(new StringBuilder().append(lllllllllllllllllIlIIIIlIIlllIll.toString()).append(".").append(lllllllllllllllllIlIIIIlIlIIIIII)), lllllllllllllllllIlIIIIlIIlllIlI, lllllllllllllllllIlIIIIlIIllllIl);
    }
    
    public static Texture getTexture(final String lllllllllllllllllIlIIIIlIlIlIIII, final InputStream lllllllllllllllllIlIIIIlIlIIllll, final boolean lllllllllllllllllIlIIIIlIlIlIIIl) throws IOException {
        return getTexture(lllllllllllllllllIlIIIIlIlIlIIII, lllllllllllllllllIlIIIIlIlIIllll, lllllllllllllllllIlIIIIlIlIlIIIl, 9729);
    }
}
