package com.elementars.eclient.friend;

import java.util.*;

public class Nicknames
{
    private static /* synthetic */ Map<String, String> aliases;
    
    public static void removeNickname(final String llIIlIlIlllllIl) {
        Nicknames.aliases.remove(llIIlIlIlllllIl);
    }
    
    public static void addNickname(final String llIIlIllIIIIIll, final String llIIlIllIIIIIII) {
        Nicknames.aliases.put(llIIlIllIIIIIll, llIIlIllIIIIIII);
    }
    
    public static boolean hasNickname(final String llIIlIlIllllIII) {
        return Nicknames.aliases.containsKey(llIIlIlIllllIII);
    }
    
    static {
        Nicknames.aliases = new HashMap<String, String>();
    }
    
    public static Map<String, String> getAliases() {
        return Nicknames.aliases;
    }
    
    public static String getNickname(final String llIIlIlIllllIll) {
        return Nicknames.aliases.get(llIIlIlIllllIll);
    }
}
