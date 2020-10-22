package org.reflections.util;

import java.util.jar.*;
import org.reflections.*;
import java.io.*;
import javax.servlet.*;
import java.net.*;
import java.util.*;

public abstract class ClasspathHelper
{
    public static Collection<URL> forManifest(final URL lllIllIIlIllIl) {
        final Collection<URL> lllIllIIlIlllI = new ArrayList<URL>();
        lllIllIIlIlllI.add(lllIllIIlIllIl);
        try {
            final String lllIllIIllIlII = cleanPath(lllIllIIlIllIl);
            final File lllIllIIllIIll = new File(lllIllIIllIlII);
            final JarFile lllIllIIllIIlI = new JarFile(lllIllIIllIlII);
            URL lllIllIIllIIIl = tryToGetValidUrl(lllIllIIllIIll.getPath(), new File(lllIllIIllIlII).getParent(), lllIllIIllIlII);
            if (lllIllIIllIIIl != null) {
                lllIllIIlIlllI.add(lllIllIIllIIIl);
            }
            final Manifest lllIllIIllIIII = lllIllIIllIIlI.getManifest();
            if (lllIllIIllIIII != null) {
                final String lllIllIIllIlIl = lllIllIIllIIII.getMainAttributes().getValue(new Attributes.Name("Class-Path"));
                if (lllIllIIllIlIl != null) {
                    final double lllIllIIlIIlIl = (Object)lllIllIIllIlIl.split(" ");
                    final Exception lllIllIIlIIlII = (Exception)lllIllIIlIIlIl.length;
                    for (short lllIllIIlIIIll = 0; lllIllIIlIIIll < lllIllIIlIIlII; ++lllIllIIlIIIll) {
                        final String lllIllIIllIllI = lllIllIIlIIlIl[lllIllIIlIIIll];
                        lllIllIIllIIIl = tryToGetValidUrl(lllIllIIllIIll.getPath(), new File(lllIllIIllIlII).getParent(), lllIllIIllIllI);
                        if (lllIllIIllIIIl != null) {
                            lllIllIIlIlllI.add(lllIllIIllIIIl);
                        }
                    }
                }
            }
        }
        catch (IOException ex) {}
        return distinctUrls(lllIllIIlIlllI);
    }
    
    public static Collection<URL> forManifest() {
        return forManifest(forClassLoader());
    }
    
    public static URL forClass(final Class<?> lllIlllIIlIIll, final ClassLoader... lllIlllIIlIIlI) {
        final ClassLoader[] lllIlllIIlIIIl = classLoaders(lllIlllIIlIIlI);
        final String lllIlllIIlIIII = String.valueOf(new StringBuilder().append(lllIlllIIlIIll.getName().replace(".", "/")).append(".class"));
        final Exception lllIlllIIIlIll = (Object)lllIlllIIlIIIl;
        final double lllIlllIIIlIlI = lllIlllIIIlIll.length;
        for (long lllIlllIIIlIIl = 0; lllIlllIIIlIIl < lllIlllIIIlIlI; ++lllIlllIIIlIIl) {
            final ClassLoader lllIlllIIlIlII = lllIlllIIIlIll[lllIlllIIIlIIl];
            try {
                final URL lllIlllIIlIllI = lllIlllIIlIlII.getResource(lllIlllIIlIIII);
                if (lllIlllIIlIllI != null) {
                    final String lllIlllIIlIlll = lllIlllIIlIllI.toExternalForm().substring(0, lllIlllIIlIllI.toExternalForm().lastIndexOf(lllIlllIIlIIll.getPackage().getName().replace(".", "/")));
                    return new URL(lllIlllIIlIlll);
                }
            }
            catch (MalformedURLException lllIlllIIlIlIl) {
                if (Reflections.log != null) {
                    Reflections.log.warn("Could not get URL", (Throwable)lllIlllIIlIlIl);
                }
            }
        }
        return null;
    }
    
    public static String cleanPath(final URL lllIllIIIIIllI) {
        String lllIllIIIIIlll = lllIllIIIIIllI.getPath();
        try {
            lllIllIIIIIlll = URLDecoder.decode(lllIllIIIIIlll, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {}
        if (lllIllIIIIIlll.startsWith("jar:")) {
            lllIllIIIIIlll = lllIllIIIIIlll.substring("jar:".length());
        }
        if (lllIllIIIIIlll.startsWith("file:")) {
            lllIllIIIIIlll = lllIllIIIIIlll.substring("file:".length());
        }
        if (lllIllIIIIIlll.endsWith("!/")) {
            lllIllIIIIIlll = String.valueOf(new StringBuilder().append(lllIllIIIIIlll.substring(0, lllIllIIIIIlll.lastIndexOf("!/"))).append("/"));
        }
        return lllIllIIIIIlll;
    }
    
    public static Collection<URL> forManifest(final Iterable<URL> lllIllIIIllIlI) {
        final Collection<URL> lllIllIIIllIll = new ArrayList<URL>();
        for (final URL lllIllIIIlllIl : lllIllIIIllIlI) {
            lllIllIIIllIll.addAll(forManifest(lllIllIIIlllIl));
        }
        return distinctUrls(lllIllIIIllIll);
    }
    
    private static Collection<URL> distinctUrls(final Collection<URL> lllIlIlllllIII) {
        final Map<String, URL> lllIlIllllIlll = new LinkedHashMap<String, URL>(lllIlIlllllIII.size());
        for (final URL lllIlIlllllIIl : lllIlIlllllIII) {
            lllIlIllllIlll.put(lllIlIlllllIIl.toExternalForm(), lllIlIlllllIIl);
        }
        return lllIlIllllIlll.values();
    }
    
    public static Collection<URL> forWebInfLib(final ServletContext lllIllIlIlIlII) {
        final Collection<URL> lllIllIlIlIIll = new ArrayList<URL>();
        final Set<?> lllIllIlIlIIlI = (Set<?>)lllIllIlIlIlII.getResourcePaths("/WEB-INF/lib");
        if (lllIllIlIlIIlI == null) {
            return lllIllIlIlIIll;
        }
        for (final Object lllIllIlIlIlIl : lllIllIlIlIIlI) {
            try {
                lllIllIlIlIIll.add(lllIllIlIlIlII.getResource((String)lllIllIlIlIlIl));
            }
            catch (MalformedURLException ex) {}
        }
        return distinctUrls(lllIllIlIlIIll);
    }
    
    public static Collection<URL> forPackage(final String lllIllllIIIlII, final ClassLoader... lllIllllIIIIll) {
        return forResource(resourceName(lllIllllIIIlII), lllIllllIIIIll);
    }
    
    public static Collection<URL> forJavaClassPath() {
        final Collection<URL> lllIllIllIIlII = new ArrayList<URL>();
        final String lllIllIllIIIll = System.getProperty("java.class.path");
        if (lllIllIllIIIll != null) {
            final double lllIllIllIIIII = (Object)lllIllIllIIIll.split(File.pathSeparator);
            final float lllIllIlIlllll = lllIllIllIIIII.length;
            for (final String lllIllIllIIlIl : lllIllIllIIIII) {
                try {
                    lllIllIllIIlII.add(new File(lllIllIllIIlIl).toURI().toURL());
                }
                catch (Exception lllIllIllIIllI) {
                    if (Reflections.log != null) {
                        Reflections.log.warn("Could not get URL", (Throwable)lllIllIllIIllI);
                    }
                }
            }
        }
        return distinctUrls(lllIllIllIIlII);
    }
    
    static URL tryToGetValidUrl(final String lllIllIIIlIIlI, final String lllIllIIIIlllI, final String lllIllIIIlIIII) {
        try {
            if (new File(lllIllIIIlIIII).exists()) {
                return new File(lllIllIIIlIIII).toURI().toURL();
            }
            if (new File(String.valueOf(new StringBuilder().append(lllIllIIIIlllI).append(File.separator).append(lllIllIIIlIIII))).exists()) {
                return new File(String.valueOf(new StringBuilder().append(lllIllIIIIlllI).append(File.separator).append(lllIllIIIlIIII))).toURI().toURL();
            }
            if (new File(String.valueOf(new StringBuilder().append(lllIllIIIlIIlI).append(File.separator).append(lllIllIIIlIIII))).exists()) {
                return new File(String.valueOf(new StringBuilder().append(lllIllIIIlIIlI).append(File.separator).append(lllIllIIIlIIII))).toURI().toURL();
            }
            if (new File(new URL(lllIllIIIlIIII).getFile()).exists()) {
                return new File(new URL(lllIllIIIlIIII).getFile()).toURI().toURL();
            }
        }
        catch (MalformedURLException ex) {}
        return null;
    }
    
    public static Collection<URL> forClassLoader() {
        return forClassLoader(classLoaders(new ClassLoader[0]));
    }
    
    public static ClassLoader[] classLoaders(final ClassLoader... lllIllllIIlIIl) {
        if (lllIllllIIlIIl != null && lllIllllIIlIIl.length != 0) {
            return lllIllllIIlIIl;
        }
        final ClassLoader lllIllllIIllII = contextClassLoader();
        final ClassLoader lllIllllIIlIll = staticClassLoader();
        return (lllIllllIIllII != null) ? ((lllIllllIIlIll != null && lllIllllIIllII != lllIllllIIlIll) ? new ClassLoader[] { lllIllllIIllII, lllIllllIIlIll } : new ClassLoader[] { lllIllllIIllII }) : new ClassLoader[0];
    }
    
    public static URL forWebInfClasses(final ServletContext lllIllIlIIIlIl) {
        try {
            final String lllIllIlIIIlll = lllIllIlIIIlIl.getRealPath("/WEB-INF/classes");
            if (lllIllIlIIIlll == null) {
                return lllIllIlIIIlIl.getResource("/WEB-INF/classes");
            }
            final File lllIllIlIIlIII = new File(lllIllIlIIIlll);
            if (lllIllIlIIlIII.exists()) {
                return lllIllIlIIlIII.toURL();
            }
        }
        catch (MalformedURLException ex) {}
        return null;
    }
    
    public static Collection<URL> forResource(final String lllIlllIlIllII, final ClassLoader... lllIlllIlIlIll) {
        final List<URL> lllIlllIlIlllI = new ArrayList<URL>();
        final float lllIlllIlIlIII;
        final ClassLoader[] lllIlllIlIllIl = (Object)(lllIlllIlIlIII = (float)(Object)classLoaders(lllIlllIlIlIll));
        final long lllIlllIlIIlll = lllIlllIlIlIII.length;
        for (short lllIlllIlIIllI = 0; lllIlllIlIIllI < lllIlllIlIIlll; ++lllIlllIlIIllI) {
            final ClassLoader lllIlllIllIIIl = lllIlllIlIlIII[lllIlllIlIIllI];
            try {
                final Enumeration<URL> lllIlllIllIIll = lllIlllIllIIIl.getResources(lllIlllIlIllII);
                while (lllIlllIllIIll.hasMoreElements()) {
                    final URL lllIlllIllIlIl = lllIlllIllIIll.nextElement();
                    final int lllIlllIllIlII = lllIlllIllIlIl.toExternalForm().lastIndexOf(lllIlllIlIllII);
                    if (lllIlllIllIlII != -1) {
                        lllIlllIlIlllI.add(new URL(lllIlllIllIlIl, lllIlllIllIlIl.toExternalForm().substring(0, lllIlllIllIlII)));
                    }
                    else {
                        lllIlllIlIlllI.add(lllIlllIllIlIl);
                    }
                }
            }
            catch (IOException lllIlllIllIIlI) {
                if (Reflections.log != null) {
                    Reflections.log.error(String.valueOf(new StringBuilder().append("error getting resources for ").append(lllIlllIlIllII)), (Throwable)lllIlllIllIIlI);
                }
            }
        }
        return distinctUrls(lllIlllIlIlllI);
    }
    
    private static String resourceName(final String lllIlIllllllll) {
        if (lllIlIllllllll != null) {
            String lllIllIIIIIIIl = lllIlIllllllll.replace(".", "/");
            lllIllIIIIIIIl = lllIllIIIIIIIl.replace("\\", "/");
            if (lllIllIIIIIIIl.startsWith("/")) {
                lllIllIIIIIIIl = lllIllIIIIIIIl.substring(1);
            }
            return lllIllIIIIIIIl;
        }
        return null;
    }
    
    public static ClassLoader staticClassLoader() {
        return Reflections.class.getClassLoader();
    }
    
    public static Collection<URL> forClassLoader(final ClassLoader... lllIllIllllIII) {
        final Collection<URL> lllIllIlllIlll = new ArrayList<URL>();
        final char lllIllIlllIIlI;
        final ClassLoader[] lllIllIlllIllI = (Object)(lllIllIlllIIlI = (char)(Object)classLoaders(lllIllIllllIII));
        final short lllIllIlllIIIl = (short)lllIllIlllIIlI.length;
        for (float lllIllIlllIIII = 0; lllIllIlllIIII < lllIllIlllIIIl; ++lllIllIlllIIII) {
            for (ClassLoader lllIllIllllIIl = lllIllIlllIIlI[lllIllIlllIIII]; lllIllIllllIIl != null; lllIllIllllIIl = lllIllIllllIIl.getParent()) {
                if (lllIllIllllIIl instanceof URLClassLoader) {
                    final URL[] lllIllIllllIlI = ((URLClassLoader)lllIllIllllIIl).getURLs();
                    if (lllIllIllllIlI != null) {
                        lllIllIlllIlll.addAll(Arrays.asList(lllIllIllllIlI));
                    }
                }
            }
        }
        return distinctUrls(lllIllIlllIlll);
    }
    
    public static ClassLoader contextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
