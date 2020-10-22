package com.elementars.eclient.util;

import javax.annotation.*;
import java.util.*;

public class ListHelper
{
    public static String longest(@Nonnull final String[] lIllllIIIIIIll) {
        Objects.requireNonNull(lIllllIIIIIIll);
        final List<String> lIllllIIIIIIlI = Arrays.asList(lIllllIIIIIIll);
        if (lIllllIIIIIIlI.isEmpty()) {
            return null;
        }
        String lIllllIIIIIIIl = lIllllIIIIIIlI.get(0);
        final Exception lIlllIllllllIl = (Object)lIllllIIIIIIll;
        final int lIlllIllllllII = lIlllIllllllIl.length;
        for (String lIlllIlllllIll = (String)0; lIlllIlllllIll < lIlllIllllllII; ++lIlllIlllllIll) {
            final String lIllllIIIIIlII = lIlllIllllllIl[lIlllIlllllIll];
            if (lIllllIIIIIlII.length() > lIllllIIIIIIIl.length()) {
                lIllllIIIIIIIl = lIllllIIIIIlII;
            }
        }
        return lIllllIIIIIIIl;
    }
    
    public static String longest(@Nonnull final List<String> lIllllIIIlIIIl) {
        Objects.requireNonNull(lIllllIIIlIIIl);
        if (lIllllIIIlIIIl.isEmpty()) {
            return null;
        }
        String lIllllIIIlIIII = lIllllIIIlIIIl.get(0);
        for (final String lIllllIIIlIIlI : lIllllIIIlIIIl) {
            if (lIllllIIIlIIlI.length() > lIllllIIIlIIII.length()) {
                lIllllIIIlIIII = lIllllIIIlIIlI;
            }
        }
        return lIllllIIIlIIII;
    }
}
