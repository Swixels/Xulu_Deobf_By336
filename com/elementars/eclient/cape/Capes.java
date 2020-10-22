package com.elementars.eclient.cape;

import java.util.*;
import java.net.*;
import java.io.*;

public class Capes
{
    public static /* synthetic */ ArrayList<String> lines;
    
    public static boolean isCapeUser(final String lllllllllllllllllIllllIlIlIllIll) {
        return Capes.lines.contains(lllllllllllllllllIllllIlIlIllIll);
    }
    
    static {
        Capes.lines = new ArrayList<String>();
    }
    
    public static void getUsersCape() {
        try {
            final URL lllllllllllllllllIllllIlIllIIlII = new URL("https://www.pastebin.com/raw/MiWJDQRF");
            final BufferedReader lllllllllllllllllIllllIlIllIIIll = new BufferedReader(new InputStreamReader(lllllllllllllllllIllllIlIllIIlII.openStream()));
            String lllllllllllllllllIllllIlIllIIIlI;
            while ((lllllllllllllllllIllllIlIllIIIlI = lllllllllllllllllIllllIlIllIIIll.readLine()) != null) {
                Capes.lines.add(lllllllllllllllllIllllIlIllIIIlI);
            }
            lllllllllllllllllIllllIlIllIIIll.close();
        }
        catch (Exception lllllllllllllllllIllllIlIllIIIIl) {
            lllllllllllllllllIllllIlIllIIIIl.printStackTrace();
        }
    }
}
