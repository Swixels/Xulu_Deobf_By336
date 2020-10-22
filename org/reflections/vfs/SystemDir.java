package org.reflections.vfs;

import org.reflections.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SystemDir implements Vfs.Dir
{
    private final /* synthetic */ java.io.File file;
    
    @Override
    public void close() {
    }
    
    @Override
    public String toString() {
        return this.getPath();
    }
    
    @Override
    public String getPath() {
        if (this.file == null) {
            return "/NO-SUCH-DIRECTORY/";
        }
        return this.file.getPath().replace("\\", "/");
    }
    
    @Override
    public Iterable<Vfs.File> getFiles() {
        if (this.file == null || !this.file.exists()) {
            return (Iterable<Vfs.File>)Collections.emptyList();
        }
        final ReflectionsException ex;
        return () -> {
            try {
                return (Iterator<Vfs.File>)Files.walk(this.file.toPath(), new FileVisitOption[0]).filter(lIIIllllIlIlIlI -> Files.isRegularFile(lIIIllllIlIlIlI, new LinkOption[0])).map(lIIIllllIlIllIl -> new SystemFile(this, lIIIllllIlIllIl.toFile())).iterator();
            }
            catch (IOException lIIIllllIllIllI) {
                new ReflectionsException(String.valueOf(new StringBuilder().append("could not get files for ").append(this.file)), lIIIllllIllIllI);
                throw ex;
            }
        };
    }
    
    public SystemDir(final java.io.File lIIIlllllIIIIll) {
        if (lIIIlllllIIIIll != null && (!lIIIlllllIIIIll.isDirectory() || !lIIIlllllIIIIll.canRead())) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("cannot use dir ").append(lIIIlllllIIIIll)));
        }
        this.file = lIIIlllllIIIIll;
    }
}
