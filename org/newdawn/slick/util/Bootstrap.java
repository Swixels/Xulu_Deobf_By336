package org.newdawn.slick.util;

import org.newdawn.slick.*;

public class Bootstrap
{
    public static void runAsApplication(final Game lllllllllllllllllIIlIllIllllIlIl, final int lllllllllllllllllIIlIllIlllllIII, final int lllllllllllllllllIIlIllIllllIlll, final boolean lllllllllllllllllIIlIllIllllIllI) {
        try {
            final AppGameContainer lllllllllllllllllIIlIllIlllllIll = new AppGameContainer(lllllllllllllllllIIlIllIllllIlIl, lllllllllllllllllIIlIllIlllllIII, lllllllllllllllllIIlIllIllllIlll, lllllllllllllllllIIlIllIllllIllI);
            lllllllllllllllllIIlIllIlllllIll.start();
        }
        catch (Exception lllllllllllllllllIIlIllIlllllIlI) {
            lllllllllllllllllIIlIllIlllllIlI.printStackTrace();
        }
    }
}
