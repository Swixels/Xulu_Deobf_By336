package org.newdawn.slick.imageout;

import java.util.*;
import org.newdawn.slick.*;
import javax.imageio.*;

public class ImageWriterFactory
{
    private static /* synthetic */ HashMap writers;
    
    public static ImageWriter getWriterForFormat(final String lllIIllIIllIIl) throws SlickException {
        ImageWriter lllIIllIIllIlI = ImageWriterFactory.writers.get(lllIIllIIllIIl);
        if (lllIIllIIllIlI != null) {
            return lllIIllIIllIlI;
        }
        lllIIllIIllIlI = ImageWriterFactory.writers.get(lllIIllIIllIIl.toLowerCase());
        if (lllIIllIIllIlI != null) {
            return lllIIllIIllIlI;
        }
        lllIIllIIllIlI = ImageWriterFactory.writers.get(lllIIllIIllIIl.toUpperCase());
        if (lllIIllIIllIlI != null) {
            return lllIIllIIllIlI;
        }
        throw new SlickException(String.valueOf(new StringBuilder().append("No image writer available for: ").append(lllIIllIIllIIl)));
    }
    
    static {
        ImageWriterFactory.writers = new HashMap();
        final String[] lllIIllIIlIIll = ImageIO.getWriterFormatNames();
        final ImageIOWriter lllIIllIIlIIlI = new ImageIOWriter();
        for (int lllIIllIIlIlII = 0; lllIIllIIlIlII < lllIIllIIlIIll.length; ++lllIIllIIlIlII) {
            registerWriter(lllIIllIIlIIll[lllIIllIIlIlII], lllIIllIIlIIlI);
        }
        final TGAWriter lllIIllIIlIIIl = new TGAWriter();
        registerWriter("tga", lllIIllIIlIIIl);
    }
    
    public static void registerWriter(final String lllIIllIlIIIIl, final ImageWriter lllIIllIlIIIII) {
        ImageWriterFactory.writers.put(lllIIllIlIIIIl, lllIIllIlIIIII);
    }
    
    public static String[] getSupportedFormats() {
        return (String[])ImageWriterFactory.writers.keySet().toArray(new String[0]);
    }
}
