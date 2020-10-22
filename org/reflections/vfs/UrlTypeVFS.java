package org.reflections.vfs;

import java.util.function.*;
import java.util.jar.*;
import java.io.*;
import java.net.*;
import org.reflections.*;
import java.util.regex.*;

public class UrlTypeVFS implements Vfs.UrlType
{
    public static final /* synthetic */ String[] REPLACE_EXTENSION;
    /* synthetic */ Predicate<java.io.File> realFile;
    
    @Override
    public Vfs.Dir createDir(final URL lllIIlIIIlIll) {
        try {
            final URL lllIIlIIIllll = this.adaptURL(lllIIlIIIlIll);
            return new ZipDir(new JarFile(lllIIlIIIllll.getFile()));
        }
        catch (Exception lllIIlIIIllIl) {
            try {
                return new ZipDir(new JarFile(lllIIlIIIlIll.getFile()));
            }
            catch (IOException lllIIlIIIlllI) {
                if (Reflections.log != null) {
                    Reflections.log.warn("Could not get URL", (Throwable)lllIIlIIIllIl);
                    Reflections.log.warn("Could not get URL", (Throwable)lllIIlIIIlllI);
                }
                return null;
            }
        }
    }
    
    public URL adaptURL(final URL lllIIlIIIIIll) throws MalformedURLException {
        if ("vfszip".equals(lllIIlIIIIIll.getProtocol())) {
            return this.replaceZipSeparators(lllIIlIIIIIll.getPath(), this.realFile);
        }
        if ("vfsfile".equals(lllIIlIIIIIll.getProtocol())) {
            return new URL(lllIIlIIIIIll.toString().replace("vfsfile", "file"));
        }
        return lllIIlIIIIIll;
    }
    
    URL replaceZipSeparators(final String lllIIIlllIlIl, final Predicate<java.io.File> lllIIIllllIII) throws MalformedURLException {
        int lllIIIlllIlll = 0;
        while (lllIIIlllIlll != -1) {
            lllIIIlllIlll = this.findFirstMatchOfDeployableExtention(lllIIIlllIlIl, lllIIIlllIlll);
            if (lllIIIlllIlll > 0) {
                final java.io.File lllIIIllllIll = new java.io.File(lllIIIlllIlIl.substring(0, lllIIIlllIlll - 1));
                if (lllIIIllllIII.test(lllIIIllllIll)) {
                    return this.replaceZipSeparatorStartingFrom(lllIIIlllIlIl, lllIIIlllIlll);
                }
                continue;
            }
        }
        throw new ReflectionsException(String.valueOf(new StringBuilder().append("Unable to identify the real zip file in path '").append(lllIIIlllIlIl).append("'.")));
    }
    
    static {
        REPLACE_EXTENSION = new String[] { ".ear/", ".jar/", ".war/", ".sar/", ".har/", ".par/" };
    }
    
    public UrlTypeVFS() {
        this.realFile = (lllIIIlIIlIII -> lllIIIlIIlIII.exists() && lllIIIlIIlIII.isFile());
    }
    
    @Override
    public boolean matches(final URL lllIIlIIlIlII) {
        return "vfszip".equals(lllIIlIIlIlII.getProtocol()) || "vfsfile".equals(lllIIlIIlIlII.getProtocol());
    }
    
    int findFirstMatchOfDeployableExtention(final String lllIIIllIllII, final int lllIIIllIIlll) {
        final Pattern lllIIIllIlIlI = Pattern.compile("\\.[ejprw]ar/");
        final Matcher lllIIIllIlIIl = lllIIIllIlIlI.matcher(lllIIIllIllII);
        if (lllIIIllIlIIl.find(lllIIIllIIlll)) {
            return lllIIIllIlIIl.end();
        }
        return -1;
    }
    
    URL replaceZipSeparatorStartingFrom(final String lllIIIlIlIIlI, final int lllIIIlIlIIIl) throws MalformedURLException {
        final String lllIIIlIlIllI = lllIIIlIlIIlI.substring(0, lllIIIlIlIIIl - 1);
        String lllIIIlIlIlIl = lllIIIlIlIIlI.substring(lllIIIlIlIIIl);
        int lllIIIlIlIlII = 1;
        final double lllIIIlIIllIl = (Object)UrlTypeVFS.REPLACE_EXTENSION;
        final int length = lllIIIlIIllIl.length;
        for (double lllIIIlIIlIll = 0; lllIIIlIIlIll < length; ++lllIIIlIIlIll) {
            for (String lllIIIlIllIll = lllIIIlIIllIl[lllIIIlIIlIll]; lllIIIlIlIlIl.contains(lllIIIlIllIll); lllIIIlIlIlIl = lllIIIlIlIlIl.replace(lllIIIlIllIll, String.valueOf(new StringBuilder().append(lllIIIlIllIll.substring(0, 4)).append("!"))), ++lllIIIlIlIlII) {}
        }
        String lllIIIlIlIIll = "";
        for (int lllIIIlIllIlI = 0; lllIIIlIllIlI < lllIIIlIlIlII; ++lllIIIlIllIlI) {
            lllIIIlIlIIll = String.valueOf(new StringBuilder().append(lllIIIlIlIIll).append("zip:"));
        }
        if (lllIIIlIlIlIl.trim().length() == 0) {
            return new URL(String.valueOf(new StringBuilder().append(lllIIIlIlIIll).append("/").append(lllIIIlIlIllI)));
        }
        return new URL(String.valueOf(new StringBuilder().append(lllIIIlIlIIll).append("/").append(lllIIIlIlIllI).append("!").append(lllIIIlIlIlIl)));
    }
}
