package org.newdawn.slick.opengl;

import org.newdawn.slick.util.*;
import java.security.*;

public class ImageDataFactory
{
    private static /* synthetic */ boolean pngLoaderPropertyChecked;
    private static /* synthetic */ boolean usePngLoader;
    
    private static void checkProperty() {
        if (!ImageDataFactory.pngLoaderPropertyChecked) {
            ImageDataFactory.pngLoaderPropertyChecked = true;
            try {
                AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                    @Override
                    public Object run() {
                        final String lllllllllllllllllIIlIlIlllIIlIII = System.getProperty("org.newdawn.slick.pngloader");
                        if ("false".equalsIgnoreCase(lllllllllllllllllIIlIlIlllIIlIII)) {
                            ImageDataFactory.usePngLoader = false;
                        }
                        Log.info(String.valueOf(new StringBuilder().append("Use Java PNG Loader = ").append(ImageDataFactory.usePngLoader)));
                        return null;
                    }
                });
            }
            catch (Throwable t) {}
        }
    }
    
    static {
        PNG_LOADER = "org.newdawn.slick.pngloader";
        ImageDataFactory.usePngLoader = true;
        ImageDataFactory.pngLoaderPropertyChecked = false;
    }
    
    public static LoadableImageData getImageDataFor(String lllllllllllllllllllllllllIIIIlIl) {
        checkProperty();
        lllllllllllllllllllllllllIIIIlIl = (long)((String)lllllllllllllllllllllllllIIIIlIl).toLowerCase();
        if (((String)lllllllllllllllllllllllllIIIIlIl).endsWith(".tga")) {
            return new TGAImageData();
        }
        if (((String)lllllllllllllllllllllllllIIIIlIl).endsWith(".png")) {
            final CompositeImageData lllllllllllllllllllllllllIIIIlll = new CompositeImageData();
            if (ImageDataFactory.usePngLoader) {
                lllllllllllllllllllllllllIIIIlll.add(new PNGImageData());
            }
            lllllllllllllllllllllllllIIIIlll.add(new ImageIOImageData());
            return lllllllllllllllllllllllllIIIIlll;
        }
        return new ImageIOImageData();
    }
}
