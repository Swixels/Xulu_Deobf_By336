package com.elementars.eclient.util;

import java.util.*;

public class EnumUtil
{
    public static <T extends Enum<T>> ArrayList<String> enumConverter(final Class<T> lIIIIlllIlIIl) {
        final ArrayList<String> lIIIIlllIlIll = new ArrayList<String>();
        final List<T> lIIIIlllIlIlI = Arrays.asList(lIIIIlllIlIIl.getEnumConstants());
        lIIIIlllIlIlI.forEach(lIIIIlllIIIII -> lIIIIlllIlIll.add(toTitle(lIIIIlllIIIII.name())));
        return lIIIIlllIlIll;
    }
    
    public static String toTitle(String lIIIIlllIIlII) {
        lIIIIlllIIlII = (Exception)String.valueOf(new StringBuilder().append(Character.toUpperCase(((String)lIIIIlllIIlII).toLowerCase().charAt(0))).append(((String)lIIIIlllIIlII).toLowerCase().substring(1)));
        return (String)lIIIIlllIIlII;
    }
}
