package org.newdawn.slick.muffin;

import java.util.*;
import org.newdawn.slick.util.*;
import java.io.*;

public class FileMuffin implements Muffin
{
    @Override
    public HashMap loadFile(final String llllllllllllllllllIIllIIIlIlllII) throws IOException {
        HashMap llllllllllllllllllIIllIIIlIlllll = new HashMap();
        final String llllllllllllllllllIIllIIIlIllllI = System.getProperty("user.home");
        File llllllllllllllllllIIllIIIlIlllIl = new File(llllllllllllllllllIIllIIIlIllllI);
        llllllllllllllllllIIllIIIlIlllIl = new File(llllllllllllllllllIIllIIIlIlllIl, ".java");
        llllllllllllllllllIIllIIIlIlllIl = new File(llllllllllllllllllIIllIIIlIlllIl, llllllllllllllllllIIllIIIlIlllII);
        if (llllllllllllllllllIIllIIIlIlllIl.exists()) {
            try {
                final FileInputStream llllllllllllllllllIIllIIIllIIlII = new FileInputStream(llllllllllllllllllIIllIIIlIlllIl);
                final ObjectInputStream llllllllllllllllllIIllIIIllIIIll = new ObjectInputStream(llllllllllllllllllIIllIIIllIIlII);
                llllllllllllllllllIIllIIIlIlllll = (HashMap)llllllllllllllllllIIllIIIllIIIll.readObject();
                llllllllllllllllllIIllIIIllIIIll.close();
            }
            catch (EOFException llllllllllllllllllIIllIIIlIllIII) {}
            catch (ClassNotFoundException llllllllllllllllllIIllIIIllIIIlI) {
                Log.error(llllllllllllllllllIIllIIIllIIIlI);
                throw new IOException("Failed to pull state from store - class not found");
            }
        }
        return llllllllllllllllllIIllIIIlIlllll;
    }
    
    @Override
    public void saveFile(final HashMap llllllllllllllllllIIllIIIlllIllI, final String llllllllllllllllllIIllIIIlllIlIl) throws IOException {
        final String llllllllllllllllllIIllIIIlllIlII = System.getProperty("user.home");
        File llllllllllllllllllIIllIIIlllIIll = new File(llllllllllllllllllIIllIIIlllIlII);
        llllllllllllllllllIIllIIIlllIIll = new File(llllllllllllllllllIIllIIIlllIIll, ".java");
        if (!llllllllllllllllllIIllIIIlllIIll.exists()) {
            llllllllllllllllllIIllIIIlllIIll.mkdir();
        }
        llllllllllllllllllIIllIIIlllIIll = new File(llllllllllllllllllIIllIIIlllIIll, llllllllllllllllllIIllIIIlllIlIl);
        final FileOutputStream llllllllllllllllllIIllIIIlllIIlI = new FileOutputStream(llllllllllllllllllIIllIIIlllIIll);
        final ObjectOutputStream llllllllllllllllllIIllIIIlllIIIl = new ObjectOutputStream(llllllllllllllllllIIllIIIlllIIlI);
        llllllllllllllllllIIllIIIlllIIIl.writeObject(llllllllllllllllllIIllIIIlllIllI);
        llllllllllllllllllIIllIIIlllIIIl.close();
    }
}
