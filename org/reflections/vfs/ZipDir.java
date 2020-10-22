package org.reflections.vfs;

import java.util.zip.*;
import java.util.jar.*;
import java.util.*;
import org.reflections.*;
import java.io.*;

public class ZipDir implements Vfs.Dir
{
    final /* synthetic */ ZipFile jarFile;
    
    @Override
    public Iterable<Vfs.File> getFiles() {
        return () -> this.jarFile.stream().filter(lIIIIIIIlIllIl -> !lIIIIIIIlIllIl.isDirectory()).map(lIIIIIIIlIllll -> new org.reflections.vfs.ZipFile(this, lIIIIIIIlIllll)).iterator();
    }
    
    @Override
    public String toString() {
        return this.jarFile.getName();
    }
    
    @Override
    public String getPath() {
        return this.jarFile.getName();
    }
    
    public ZipDir(final JarFile lIIIIIIlIlIlII) {
        this.jarFile = lIIIIIIlIlIlII;
    }
    
    @Override
    public void close() {
        try {
            this.jarFile.close();
        }
        catch (IOException lIIIIIIlIIIllI) {
            if (Reflections.log != null) {
                Reflections.log.warn("Could not close JarFile", (Throwable)lIIIIIIlIIIllI);
            }
        }
    }
}
