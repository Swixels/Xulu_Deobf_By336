package org.reflections.vfs;

import java.io.*;

public class SystemFile implements Vfs.File
{
    private final /* synthetic */ SystemDir root;
    private final /* synthetic */ java.io.File file;
    
    @Override
    public InputStream openInputStream() {
        try {
            return new FileInputStream(this.file);
        }
        catch (FileNotFoundException llIIIIIlIlIIIlI) {
            throw new RuntimeException(llIIIIIlIlIIIlI);
        }
    }
    
    @Override
    public String getRelativePath() {
        final String llIIIIIlIlIIlll = this.file.getPath().replace("\\", "/");
        if (llIIIIIlIlIIlll.startsWith(this.root.getPath())) {
            return llIIIIIlIlIIlll.substring(this.root.getPath().length() + 1);
        }
        return null;
    }
    
    @Override
    public String getName() {
        return this.file.getName();
    }
    
    @Override
    public String toString() {
        return this.file.toString();
    }
    
    public SystemFile(final SystemDir llIIIIIlIllIIlI, final java.io.File llIIIIIlIllIIIl) {
        this.root = llIIIIIlIllIIlI;
        this.file = llIIIIIlIllIIIl;
    }
}
