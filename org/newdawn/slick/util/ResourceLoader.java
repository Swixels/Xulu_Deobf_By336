package org.newdawn.slick.util;

import java.util.*;
import java.io.*;
import java.net.*;

public class ResourceLoader
{
    private static /* synthetic */ ArrayList locations;
    
    public static InputStream getResourceAsStream(final String lIllIIIIllllIII) {
        InputStream lIllIIIIllllIIl = null;
        for (int lIllIIIIllllIll = 0; lIllIIIIllllIll < ResourceLoader.locations.size(); ++lIllIIIIllllIll) {
            final ResourceLocation lIllIIIIlllllII = ResourceLoader.locations.get(lIllIIIIllllIll);
            lIllIIIIllllIIl = lIllIIIIlllllII.getResourceAsStream(lIllIIIIllllIII);
            if (lIllIIIIllllIIl != null) {
                break;
            }
        }
        if (lIllIIIIllllIIl == null) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Resource not found: ").append(lIllIIIIllllIII)));
        }
        return new BufferedInputStream(lIllIIIIllllIIl);
    }
    
    static {
        (ResourceLoader.locations = new ArrayList()).add(new ClasspathLocation());
        ResourceLoader.locations.add(new FileSystemLocation(new File(".")));
    }
    
    public static void removeResourceLocation(final ResourceLocation lIllIIIlIIIIIIl) {
        ResourceLoader.locations.remove(lIllIIIlIIIIIIl);
    }
    
    public static URL getResource(final String lIllIIIIllIIIII) {
        URL lIllIIIIllIIIIl = null;
        for (int lIllIIIIllIIIll = 0; lIllIIIIllIIIll < ResourceLoader.locations.size(); ++lIllIIIIllIIIll) {
            final ResourceLocation lIllIIIIllIIlII = ResourceLoader.locations.get(lIllIIIIllIIIll);
            lIllIIIIllIIIIl = lIllIIIIllIIlII.getResource(lIllIIIIllIIIII);
            if (lIllIIIIllIIIIl != null) {
                break;
            }
        }
        if (lIllIIIIllIIIIl == null) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Resource not found: ").append(lIllIIIIllIIIII)));
        }
        return lIllIIIIllIIIIl;
    }
    
    public static void addResourceLocation(final ResourceLocation lIllIIIlIIIIlII) {
        ResourceLoader.locations.add(lIllIIIlIIIIlII);
    }
    
    public static void removeAllResourceLocations() {
        ResourceLoader.locations.clear();
    }
    
    public static boolean resourceExists(final String lIllIIIIllIlllI) {
        URL lIllIIIIllIllIl = null;
        for (int lIllIIIIllIllll = 0; lIllIIIIllIllll < ResourceLoader.locations.size(); ++lIllIIIIllIllll) {
            final ResourceLocation lIllIIIIlllIIII = ResourceLoader.locations.get(lIllIIIIllIllll);
            lIllIIIIllIllIl = lIllIIIIlllIIII.getResource(lIllIIIIllIlllI);
            if (lIllIIIIllIllIl != null) {
                return true;
            }
        }
        return false;
    }
}
