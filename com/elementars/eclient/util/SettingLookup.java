package com.elementars.eclient.util;

import com.elementars.eclient.module.*;
import dev.xulu.settings.*;
import com.elementars.eclient.*;

public class SettingLookup
{
    public static Value getSettingFromMod(final Module lllllllllllllllllIIlIIIllIIlIllI, final String lllllllllllllllllIIlIIIllIIlIlll) {
        for (final Value lllllllllllllllllIIlIIIllIIllIIl : Xulu.VALUE_MANAGER.getValues()) {
            if (Xulu.VALUE_MANAGER.getSettingsByMod(lllllllllllllllllIIlIIIllIIlIllI).contains(lllllllllllllllllIIlIIIllIIllIIl) && lllllllllllllllllIIlIIIllIIllIIl.getName().equals(lllllllllllllllllIIlIIIllIIlIlll)) {
                return lllllllllllllllllIIlIIIllIIllIIl;
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("[Xulu] Error Setting NOT found: '").append(lllllllllllllllllIIlIIIllIIlIlll).append("'!")));
        return null;
    }
}
