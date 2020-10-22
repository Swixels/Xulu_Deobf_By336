package org.reflections.vfs;

import org.reflections.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;
import java.util.jar.*;
import java.net.*;
import org.reflections.util.*;
import java.io.*;

public abstract class Vfs
{
    private static /* synthetic */ List<UrlType> defaultUrlTypes;
    
    public static Dir fromURL(final URL llllllllllllllllIllIIIlllllIlllI, final List<UrlType> llllllllllllllllIllIIIllllllIIII) {
        for (final UrlType llllllllllllllllIllIIIllllllIllI : llllllllllllllllIllIIIllllllIIII) {
            try {
                if (!llllllllllllllllIllIIIllllllIllI.matches(llllllllllllllllIllIIIlllllIlllI)) {
                    continue;
                }
                final Dir llllllllllllllllIllIIIlllllllIII = llllllllllllllllIllIIIllllllIllI.createDir(llllllllllllllllIllIIIlllllIlllI);
                if (llllllllllllllllIllIIIlllllllIII != null) {
                    return llllllllllllllllIllIIIlllllllIII;
                }
                continue;
            }
            catch (Throwable llllllllllllllllIllIIIllllllIlll) {
                if (Reflections.log == null) {
                    continue;
                }
                Reflections.log.warn(String.valueOf(new StringBuilder().append("could not create Dir using ").append(llllllllllllllllIllIIIllllllIllI).append(" from url ").append(llllllllllllllllIllIIIlllllIlllI.toExternalForm()).append(". skipping.")), llllllllllllllllIllIIIllllllIlll);
            }
        }
        throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not create Vfs.Dir from url, no matching UrlType was found [").append(llllllllllllllllIllIIIlllllIlllI.toExternalForm()).append("]\neither use fromURL(final URL url, final List<UrlType> urlTypes) or use the static setDefaultURLTypes(final List<UrlType> urlTypes) or addDefaultURLTypes(UrlType urlType) with your specialized UrlType.")));
    }
    
    public static List<UrlType> getDefaultUrlTypes() {
        return Vfs.defaultUrlTypes;
    }
    
    public static void setDefaultURLTypes(final List<UrlType> llllllllllllllllIllIIlIIIIIIlIII) {
        Vfs.defaultUrlTypes = llllllllllllllllIllIIlIIIIIIlIII;
    }
    
    public static Dir fromURL(final URL llllllllllllllllIllIIIlllllIIIlI, final UrlType... llllllllllllllllIllIIIlllllIIIll) {
        return fromURL(llllllllllllllllIllIIIlllllIIIlI, Arrays.asList(llllllllllllllllIllIIIlllllIIIll));
    }
    
    public static java.io.File getFile(final URL llllllllllllllllIllIIIllIllIIIII) {
        try {
            final String llllllllllllllllIllIIIllIlllIIIl = llllllllllllllllIllIIIllIllIIIII.toURI().getSchemeSpecificPart();
            final java.io.File llllllllllllllllIllIIIllIlllIIll;
            if ((llllllllllllllllIllIIIllIlllIIll = new java.io.File(llllllllllllllllIllIIIllIlllIIIl)).exists()) {
                return llllllllllllllllIllIIIllIlllIIll;
            }
        }
        catch (URISyntaxException ex) {}
        try {
            String llllllllllllllllIllIIIllIllIllIl = URLDecoder.decode(llllllllllllllllIllIIIllIllIIIII.getPath(), "UTF-8");
            if (llllllllllllllllIllIIIllIllIllIl.contains(".jar!")) {
                llllllllllllllllIllIIIllIllIllIl = llllllllllllllllIllIIIllIllIllIl.substring(0, llllllllllllllllIllIIIllIllIllIl.lastIndexOf(".jar!") + ".jar".length());
            }
            final java.io.File llllllllllllllllIllIIIllIllIllll;
            if ((llllllllllllllllIllIIIllIllIllll = new java.io.File(llllllllllllllllIllIIIllIllIllIl)).exists()) {
                return llllllllllllllllIllIIIllIllIllll;
            }
        }
        catch (UnsupportedEncodingException ex2) {}
        try {
            String llllllllllllllllIllIIIllIllIlIIl = llllllllllllllllIllIIIllIllIIIII.toExternalForm();
            if (llllllllllllllllIllIIIllIllIlIIl.startsWith("jar:")) {
                llllllllllllllllIllIIIllIllIlIIl = llllllllllllllllIllIIIllIllIlIIl.substring("jar:".length());
            }
            if (llllllllllllllllIllIIIllIllIlIIl.startsWith("wsjar:")) {
                llllllllllllllllIllIIIllIllIlIIl = llllllllllllllllIllIIIllIllIlIIl.substring("wsjar:".length());
            }
            if (llllllllllllllllIllIIIllIllIlIIl.startsWith("file:")) {
                llllllllllllllllIllIIIllIllIlIIl = llllllllllllllllIllIIIllIllIlIIl.substring("file:".length());
            }
            if (llllllllllllllllIllIIIllIllIlIIl.contains(".jar!")) {
                llllllllllllllllIllIIIllIllIlIIl = llllllllllllllllIllIIIllIllIlIIl.substring(0, llllllllllllllllIllIIIllIllIlIIl.indexOf(".jar!") + ".jar".length());
            }
            if (llllllllllllllllIllIIIllIllIlIIl.contains(".war!")) {
                llllllllllllllllIllIIIllIllIlIIl = llllllllllllllllIllIIIllIllIlIIl.substring(0, llllllllllllllllIllIIIllIllIlIIl.indexOf(".war!") + ".war".length());
            }
            java.io.File llllllllllllllllIllIIIllIllIlIll;
            if ((llllllllllllllllIllIIIllIllIlIll = new java.io.File(llllllllllllllllIllIIIllIllIlIIl)).exists()) {
                return llllllllllllllllIllIIIllIllIlIll;
            }
            llllllllllllllllIllIIIllIllIlIIl = llllllllllllllllIllIIIllIllIlIIl.replace("%20", " ");
            if ((llllllllllllllllIllIIIllIllIlIll = new java.io.File(llllllllllllllllIllIIIllIllIlIIl)).exists()) {
                return llllllllllllllllIllIIIllIllIlIll;
            }
        }
        catch (Exception ex3) {}
        return null;
    }
    
    private static boolean hasJarFileInPath(final URL llllllllllllllllIllIIIllIlIllIII) {
        return llllllllllllllllIllIIIllIlIllIII.toExternalForm().matches(".*\\.jar(\\!.*|$)");
    }
    
    public static Dir fromURL(final URL llllllllllllllllIllIIlIIIIIIIIIl) {
        return fromURL(llllllllllllllllIllIIlIIIIIIIIIl, Vfs.defaultUrlTypes);
    }
    
    public static void addDefaultURLTypes(final UrlType llllllllllllllllIllIIlIIIIIIIlII) {
        Vfs.defaultUrlTypes.add(0, llllllllllllllllIllIIlIIIIIIIlII);
    }
    
    public static Iterable<File> findFiles(final Collection<URL> llllllllllllllllIllIIIllllIlIIll, final String llllllllllllllllIllIIIllllIIllIl, final Predicate<String> llllllllllllllllIllIIIllllIIllII) {
        final String llllllllllllllllIllIIIllIIlIIlIl;
        String llllllllllllllllIllIIIllIIlIlIIl;
        final Predicate<File> llllllllllllllllIllIIIllllIIllll = llllllllllllllllIllIIIllIIlIIIIl -> {
            llllllllllllllllIllIIIllIIlIIlIl = llllllllllllllllIllIIIllIIlIIIIl.getRelativePath();
            if (llllllllllllllllIllIIIllIIlIIlIl.startsWith(llllllllllllllllIllIIIllllIIllIl)) {
                llllllllllllllllIllIIIllIIlIlIIl = llllllllllllllllIllIIIllIIlIIlIl.substring(llllllllllllllllIllIIIllIIlIIlIl.indexOf(llllllllllllllllIllIIIllllIIllIl) + llllllllllllllllIllIIIllllIIllIl.length());
                return !Utils.isEmpty(llllllllllllllllIllIIIllIIlIlIIl) && llllllllllllllllIllIIIllllIIllII.test(llllllllllllllllIllIIIllIIlIlIIl.substring(1));
            }
            else {
                return false;
            }
        };
        return findFiles(llllllllllllllllIllIIIllllIlIIll, llllllllllllllllIllIIIllllIIllll);
    }
    
    static {
        Vfs.defaultUrlTypes = new ArrayList<UrlType>(Arrays.asList(DefaultUrlTypes.values()));
    }
    
    public static Iterable<File> findFiles(final Collection<URL> llllllllllllllllIllIIIlllIlllIIl, final Predicate<File> llllllllllllllllIllIIIlllIllIlll) {
        return () -> llllllllllllllllIllIIIlllIlllIIl.stream().flatMap(llllllllllllllllIllIIIllIIlllllI -> {
            try {
                return StreamSupport.stream(fromURL(llllllllllllllllIllIIIllIIlllllI).getFiles().spliterator(), false);
            }
            catch (Throwable llllllllllllllllIllIIIllIIllllll) {
                if (Reflections.log != null) {
                    Reflections.log.error(String.valueOf(new StringBuilder().append("could not findFiles for url. continuing. [").append(llllllllllllllllIllIIIllIIlllllI).append("]")), llllllllllllllllIllIIIllIIllllll);
                }
                return Stream.of(new File[0]);
            }
        }).filter((Predicate<? super Object>)llllllllllllllllIllIIIlllIllIlll).iterator();
    }
    
    public enum DefaultUrlTypes implements UrlType
    {
        bundle {
            @Override
            public boolean matches(final URL lIIllIIIl) throws Exception {
                return lIIllIIIl.getProtocol().startsWith("bundle");
            }
            
            @Override
            public Dir createDir(final URL lIIlIllIl) throws Exception {
                return Vfs.fromURL((URL)ClasspathHelper.contextClassLoader().loadClass("org.eclipse.core.runtime.FileLocator").getMethod("resolve", URL.class).invoke(null, lIIlIllIl));
            }
        }, 
        jarFile {
            @Override
            public Dir createDir(final URL lllllIlIllIII) throws Exception {
                return new ZipDir(new JarFile(Vfs.getFile(lllllIlIllIII)));
            }
            
            @Override
            public boolean matches(final URL lllllIlIllIll) {
                return lllllIlIllIll.getProtocol().equals("file") && hasJarFileInPath(lllllIlIllIll);
            }
        }, 
        jboss_vfs {
            @Override
            public boolean matches(final URL lllllllllllllllllIlllllIlIIIllII) {
                return lllllllllllllllllIlllllIlIIIllII.getProtocol().equals("vfs");
            }
            
            @Override
            public Dir createDir(final URL lllllllllllllllllIlllllIlIIIIlII) throws Exception {
                final Object lllllllllllllllllIlllllIlIIIIIll = lllllllllllllllllIlllllIlIIIIlII.openConnection().getContent();
                final Class<?> lllllllllllllllllIlllllIlIIIIIlI = ClasspathHelper.contextClassLoader().loadClass("org.jboss.vfs.VirtualFile");
                final java.io.File lllllllllllllllllIlllllIlIIIIIIl = (java.io.File)lllllllllllllllllIlllllIlIIIIIlI.getMethod("getPhysicalFile", (Class<?>[])new Class[0]).invoke(lllllllllllllllllIlllllIlIIIIIll, new Object[0]);
                final String lllllllllllllllllIlllllIlIIIIIII = (String)lllllllllllllllllIlllllIlIIIIIlI.getMethod("getName", (Class<?>[])new Class[0]).invoke(lllllllllllllllllIlllllIlIIIIIll, new Object[0]);
                java.io.File lllllllllllllllllIlllllIIlllllll = new java.io.File(lllllllllllllllllIlllllIlIIIIIIl.getParentFile(), lllllllllllllllllIlllllIlIIIIIII);
                if (!lllllllllllllllllIlllllIIlllllll.exists() || !lllllllllllllllllIlllllIIlllllll.canRead()) {
                    lllllllllllllllllIlllllIIlllllll = lllllllllllllllllIlllllIlIIIIIIl;
                }
                return lllllllllllllllllIlllllIIlllllll.isDirectory() ? new SystemDir(lllllllllllllllllIlllllIIlllllll) : new ZipDir(new JarFile(lllllllllllllllllIlllllIIlllllll));
            }
        }, 
        jarUrl {
            @Override
            public Dir createDir(final URL lllllIIIIIIllIl) throws Exception {
                try {
                    final URLConnection lllllIIIIIlIIIl = lllllIIIIIIllIl.openConnection();
                    if (lllllIIIIIlIIIl instanceof JarURLConnection) {
                        lllllIIIIIlIIIl.setUseCaches(false);
                        return new ZipDir(((JarURLConnection)lllllIIIIIlIIIl).getJarFile());
                    }
                }
                catch (Throwable t) {}
                final java.io.File lllllIIIIIIlllI = Vfs.getFile(lllllIIIIIIllIl);
                if (lllllIIIIIIlllI != null) {
                    return new ZipDir(new JarFile(lllllIIIIIIlllI));
                }
                return null;
            }
            
            @Override
            public boolean matches(final URL lllllIIIIIlIlIl) {
                return "jar".equals(lllllIIIIIlIlIl.getProtocol()) || "zip".equals(lllllIIIIIlIlIl.getProtocol()) || "wsjar".equals(lllllIIIIIlIlIl.getProtocol());
            }
        }, 
        directory {
            @Override
            public boolean matches(final URL llIIIllIIIlIl) {
                if (llIIIllIIIlIl.getProtocol().equals("file") && !hasJarFileInPath(llIIIllIIIlIl)) {
                    final java.io.File llIIIllIIIlll = Vfs.getFile(llIIIllIIIlIl);
                    return llIIIllIIIlll != null && llIIIllIIIlll.isDirectory();
                }
                return false;
            }
            
            @Override
            public Dir createDir(final URL llIIIllIIIIII) throws Exception {
                return new SystemDir(Vfs.getFile(llIIIllIIIIII));
            }
        }, 
        jarInputStream {
            @Override
            public Dir createDir(final URL lllIlIIIllIIlll) throws Exception {
                return new JarInputDir(lllIlIIIllIIlll);
            }
            
            @Override
            public boolean matches(final URL lllIlIIIllIlIll) throws Exception {
                return lllIlIIIllIlIll.toExternalForm().contains(".jar");
            }
        }, 
        jboss_vfsfile {
            @Override
            public Dir createDir(final URL llIIIlIlIlII) throws Exception {
                return new UrlTypeVFS().createDir(llIIIlIlIlII);
            }
            
            @Override
            public boolean matches(final URL llIIIlIlllll) throws Exception {
                return "vfszip".equals(llIIIlIlllll.getProtocol()) || "vfsfile".equals(llIIIlIlllll.getProtocol());
            }
        };
    }
    
    public interface UrlType
    {
        Dir createDir(final URL p0) throws Exception;
        
        boolean matches(final URL p0) throws Exception;
    }
    
    public interface Dir
    {
        void close();
        
        String getPath();
        
        Iterable<File> getFiles();
    }
    
    public interface File
    {
        InputStream openInputStream() throws IOException;
        
        String getRelativePath();
        
        String getName();
    }
}
