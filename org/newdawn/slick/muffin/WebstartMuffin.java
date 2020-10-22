package org.newdawn.slick.muffin;

import org.newdawn.slick.util.*;
import java.io.*;
import java.util.*;

public class WebstartMuffin implements Muffin
{
    @Override
    public HashMap loadFile(final String llllllllllllllllllIllIIIllIIllll) throws IOException {
        final HashMap llllllllllllllllllIllIIIllIlIIII = new HashMap();
        try {
            if (llllllllllllllllllIllIIIllIIllll.endsWith("Number")) {}
        }
        catch (Exception llllllllllllllllllIllIIIllIlIIll) {
            Log.error(llllllllllllllllllIllIIIllIlIIll);
            throw new IOException("Failed to load state from webstart muffin");
        }
        return llllllllllllllllllIllIIIllIlIIII;
    }
    
    @Override
    public void saveFile(final HashMap llllllllllllllllllIllIIIllIllllI, final String llllllllllllllllllIllIIIllIlllIl) throws IOException {
        try {
            final Set llllllllllllllllllIllIIIlllIIIIl = llllllllllllllllllIllIIIllIllllI.keySet();
            for (final String llllllllllllllllllIllIIIlllIIIll : llllllllllllllllllIllIIIlllIIIIl) {
                if (llllllllllllllllllIllIIIllIlllIl.endsWith("Number")) {
                    final long llllllllllllllllllIllIIIllIlIlll = (Object)llllllllllllllllllIllIIIllIllllI.get(llllllllllllllllllIllIIIlllIIIll);
                }
                else {
                    final long llllllllllllllllllIllIIIllIlIlll = (long)(String)llllllllllllllllllIllIIIllIllllI.get(llllllllllllllllllIllIIIlllIIIll);
                }
            }
        }
        catch (Exception llllllllllllllllllIllIIIlllIIIII) {
            Log.error(llllllllllllllllllIllIIIlllIIIII);
            throw new IOException("Failed to store map of state data");
        }
    }
}
