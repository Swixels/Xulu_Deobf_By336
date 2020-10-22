package org.newdawn.slick.util;

import java.net.*;
import java.io.*;

public class ClasspathLocation implements ResourceLocation
{
    @Override
    public URL getResource(final String llllllllllllllllIlllllIIllIlIIIl) {
        final String llllllllllllllllIlllllIIllIlIIlI = llllllllllllllllIlllllIIllIlIIIl.replace('\\', '/');
        return ResourceLoader.class.getClassLoader().getResource(llllllllllllllllIlllllIIllIlIIlI);
    }
    
    @Override
    public InputStream getResourceAsStream(final String llllllllllllllllIlllllIIllIIllII) {
        final String llllllllllllllllIlllllIIllIIlIll = llllllllllllllllIlllllIIllIIllII.replace('\\', '/');
        return ResourceLoader.class.getClassLoader().getResourceAsStream(llllllllllllllllIlllllIIllIIlIll);
    }
}
