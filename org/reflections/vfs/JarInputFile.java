package org.reflections.vfs;

import java.util.zip.*;
import java.io.*;

public class JarInputFile implements Vfs.File
{
    private final /* synthetic */ ZipEntry entry;
    private final /* synthetic */ JarInputDir jarInputDir;
    private final /* synthetic */ long endIndex;
    private final /* synthetic */ long fromIndex;
    
    @Override
    public String getRelativePath() {
        return this.entry.getName();
    }
    
    @Override
    public String getName() {
        final String llIIIIIllIlIlIl = this.entry.getName();
        return llIIIIIllIlIlIl.substring(llIIIIIllIlIlIl.lastIndexOf("/") + 1);
    }
    
    public JarInputFile(final ZipEntry llIIIIIllIlllII, final JarInputDir llIIIIIllIllIll, final long llIIIIIllIllIlI, final long llIIIIIllIllllI) {
        this.entry = llIIIIIllIlllII;
        this.jarInputDir = llIIIIIllIllIll;
        this.fromIndex = llIIIIIllIllIlI;
        this.endIndex = llIIIIIllIllllI;
    }
    
    @Override
    public InputStream openInputStream() throws IOException {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                if (JarInputFile.this.jarInputDir.cursor >= JarInputFile.this.fromIndex && JarInputFile.this.jarInputDir.cursor <= JarInputFile.this.endIndex) {
                    final int llllllllllllllllIlllIIlIIlIlIlIl = JarInputFile.this.jarInputDir.jarInputStream.read();
                    final JarInputDir access$000 = JarInputFile.this.jarInputDir;
                    ++access$000.cursor;
                    return llllllllllllllllIlllIIlIIlIlIlIl;
                }
                return -1;
            }
        };
    }
}
