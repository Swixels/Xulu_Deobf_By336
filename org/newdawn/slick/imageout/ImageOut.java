package org.newdawn.slick.imageout;

import org.newdawn.slick.*;
import java.io.*;

public class ImageOut
{
    public static /* synthetic */ String JPG;
    public static /* synthetic */ String PNG;
    public static /* synthetic */ String TGA;
    
    static {
        DEFAULT_ALPHA_WRITE = false;
        ImageOut.TGA = "tga";
        ImageOut.PNG = "png";
        ImageOut.JPG = "jpg";
    }
    
    public static String[] getSupportedFormats() {
        return ImageWriterFactory.getSupportedFormats();
    }
    
    public static void write(final Image lIIlIlIIIIIIIll, final String lIIlIlIIIIIIIlI, final String lIIlIIlllllllIl, final boolean lIIlIlIIIIIIIII) throws SlickException {
        try {
            write(lIIlIlIIIIIIIll, lIIlIlIIIIIIIlI, new FileOutputStream(lIIlIIlllllllIl), lIIlIlIIIIIIIII);
        }
        catch (IOException lIIlIlIIIIIIlII) {
            throw new SlickException(String.valueOf(new StringBuilder().append("Unable to write to the destination: ").append(lIIlIIlllllllIl)), lIIlIlIIIIIIlII);
        }
    }
    
    public static void write(final Image lIIlIlIIIlllllI, final String lIIlIlIIIllllIl, final OutputStream lIIlIlIIIlllIIl) throws SlickException {
        write(lIIlIlIIIlllllI, lIIlIlIIIllllIl, lIIlIlIIIlllIIl, false);
    }
    
    public static void write(final Image lIIlIlIIIIllIlI, final String lIIlIlIIIIlIllI, final boolean lIIlIlIIIIlIlIl) throws SlickException {
        try {
            final int lIIlIlIIIIlllIl = lIIlIlIIIIlIllI.lastIndexOf(46);
            if (lIIlIlIIIIlllIl < 0) {
                throw new SlickException(String.valueOf(new StringBuilder().append("Unable to determine format from: ").append(lIIlIlIIIIlIllI)));
            }
            final String lIIlIlIIIIlllII = lIIlIlIIIIlIllI.substring(lIIlIlIIIIlllIl + 1);
            write(lIIlIlIIIIllIlI, lIIlIlIIIIlllII, new FileOutputStream(lIIlIlIIIIlIllI), lIIlIlIIIIlIlIl);
        }
        catch (IOException lIIlIlIIIIllIll) {
            throw new SlickException(String.valueOf(new StringBuilder().append("Unable to write to the destination: ").append(lIIlIlIIIIlIllI)), lIIlIlIIIIllIll);
        }
    }
    
    public static void write(final Image lIIlIlIIIllIIIl, final String lIIlIlIIIllIIII, final OutputStream lIIlIlIIIlIllll, final boolean lIIlIlIIIlIlIlI) throws SlickException {
        try {
            final ImageWriter lIIlIlIIIllIIll = ImageWriterFactory.getWriterForFormat(lIIlIlIIIllIIII);
            lIIlIlIIIllIIll.saveImage(lIIlIlIIIllIIIl, lIIlIlIIIllIIII, lIIlIlIIIlIllll, lIIlIlIIIlIlIlI);
        }
        catch (IOException lIIlIlIIIllIIlI) {
            throw new SlickException(String.valueOf(new StringBuilder().append("Unable to write out the image in format: ").append(lIIlIlIIIllIIII)), lIIlIlIIIllIIlI);
        }
    }
    
    public static void write(final Image lIIlIlIIIlIIllI, final String lIIlIlIIIlIIIll) throws SlickException {
        write(lIIlIlIIIlIIllI, lIIlIlIIIlIIIll, false);
    }
    
    public static void write(final Image lIIlIlIIIIIllII, final String lIIlIlIIIIIlllI, final String lIIlIlIIIIIllIl) throws SlickException {
        write(lIIlIlIIIIIllII, lIIlIlIIIIIlllI, lIIlIlIIIIIllIl, false);
    }
}
