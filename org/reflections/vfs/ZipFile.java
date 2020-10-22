package org.reflections.vfs;

import java.util.zip.*;
import java.io.*;

public class ZipFile implements Vfs.File
{
    private final /* synthetic */ ZipEntry entry;
    private final /* synthetic */ ZipDir root;
    
    @Override
    public String getName() {
        final String lllllllllllllllllIlllllIllIIlIll = this.entry.getName();
        return lllllllllllllllllIlllllIllIIlIll.substring(lllllllllllllllllIlllllIllIIlIll.lastIndexOf("/") + 1);
    }
    
    @Override
    public InputStream openInputStream() throws IOException {
        return this.root.jarFile.getInputStream(this.entry);
    }
    
    @Override
    public String getRelativePath() {
        return this.entry.getName();
    }
    
    public ZipFile(final ZipDir lllllllllllllllllIlllllIllIlIIll, final ZipEntry lllllllllllllllllIlllllIllIlIIlI) {
        this.root = lllllllllllllllllIlllllIllIlIIll;
        this.entry = lllllllllllllllllIlllllIllIlIIlI;
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append(this.root.getPath()).append("!").append(java.io.File.separatorChar).append(this.entry.toString()));
    }
}
