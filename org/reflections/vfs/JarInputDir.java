package org.reflections.vfs;

import java.net.*;
import java.util.jar.*;
import org.reflections.util.*;
import java.util.*;
import org.reflections.*;
import java.io.*;
import java.util.zip.*;

public class JarInputDir implements Vfs.Dir
{
    /* synthetic */ long nextCursor;
    private final /* synthetic */ URL url;
    /* synthetic */ long cursor;
    /* synthetic */ JarInputStream jarInputStream;
    
    @Override
    public void close() {
        Utils.close(this.jarInputStream);
    }
    
    @Override
    public String getPath() {
        return this.url.getPath();
    }
    
    public JarInputDir(final URL lllllllllllllllllIIllllIIIIIIIIl) {
        this.cursor = 0L;
        this.nextCursor = 0L;
        this.url = lllllllllllllllllIIllllIIIIIIIIl;
    }
    
    @Override
    public Iterable<Vfs.File> getFiles() {
        return () -> new Iterator<Vfs.File>() {
            /* synthetic */ Vfs.File entry;
            
            {
                try {
                    JarInputDir.this.jarInputStream = new JarInputStream(JarInputDir.this.url.openConnection().getInputStream());
                }
                catch (Exception llllllllllllllllIlllIlllllllIlll) {
                    throw new ReflectionsException("Could not open url connection", llllllllllllllllIlllIlllllllIlll);
                }
                this.entry = null;
            }
            
            @Override
            public Vfs.File next() {
                final Vfs.File llllllllllllllllIlllIllllllIlIll = this.entry;
                this.entry = null;
                return llllllllllllllllIlllIllllllIlIll;
            }
            
            private Vfs.File computeNext() {
                try {
                    while (true) {
                        final ZipEntry llllllllllllllllIlllIllllllIIlIl = JarInputDir.this.jarInputStream.getNextJarEntry();
                        if (llllllllllllllllIlllIllllllIIlIl == null) {
                            return null;
                        }
                        long llllllllllllllllIlllIllllllIIlII = llllllllllllllllIlllIllllllIIlIl.getSize();
                        if (llllllllllllllllIlllIllllllIIlII < 0L) {
                            llllllllllllllllIlllIllllllIIlII += 4294967295L;
                        }
                        final JarInputDir this$0 = JarInputDir.this;
                        this$0.nextCursor += llllllllllllllllIlllIllllllIIlII;
                        if (!llllllllllllllllIlllIllllllIIlIl.isDirectory()) {
                            return new JarInputFile(llllllllllllllllIlllIllllllIIlIl, JarInputDir.this, JarInputDir.this.cursor, JarInputDir.this.nextCursor);
                        }
                    }
                }
                catch (IOException llllllllllllllllIlllIllllllIIIll) {
                    throw new ReflectionsException("could not get next zip entry", llllllllllllllllIlllIllllllIIIll);
                }
            }
            
            @Override
            public boolean hasNext() {
                return this.entry != null || (this.entry = this.computeNext()) != null;
            }
        };
    }
}
