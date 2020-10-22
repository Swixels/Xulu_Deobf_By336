package org.newdawn.slick.util;

import java.net.*;
import java.io.*;

public class FileSystemLocation implements ResourceLocation
{
    private /* synthetic */ File root;
    
    @Override
    public URL getResource(final String lllllllllllllllllIIIlIllIIIlllII) {
        try {
            File lllllllllllllllllIIIlIllIIIlllll = new File(this.root, lllllllllllllllllIIIlIllIIIlllII);
            if (!lllllllllllllllllIIIlIllIIIlllll.exists()) {
                lllllllllllllllllIIIlIllIIIlllll = new File(lllllllllllllllllIIIlIllIIIlllII);
            }
            if (!lllllllllllllllllIIIlIllIIIlllll.exists()) {
                return null;
            }
            return lllllllllllllllllIIIlIllIIIlllll.toURI().toURL();
        }
        catch (IOException lllllllllllllllllIIIlIllIIIllllI) {
            return null;
        }
    }
    
    @Override
    public InputStream getResourceAsStream(final String lllllllllllllllllIIIlIllIIIlIIII) {
        try {
            File lllllllllllllllllIIIlIllIIIlIlIl = new File(this.root, lllllllllllllllllIIIlIllIIIlIIII);
            if (!lllllllllllllllllIIIlIllIIIlIlIl.exists()) {
                lllllllllllllllllIIIlIllIIIlIlIl = new File(lllllllllllllllllIIIlIllIIIlIIII);
            }
            return new FileInputStream(lllllllllllllllllIIIlIllIIIlIlIl);
        }
        catch (IOException lllllllllllllllllIIIlIllIIIlIlII) {
            return null;
        }
    }
    
    public FileSystemLocation(final File lllllllllllllllllIIIlIllIIlIIIll) {
        this.root = lllllllllllllllllIIIlIllIIlIIIll;
    }
}
