package org.newdawn.slick.util;

import java.security.*;

public final class Log
{
    private static /* synthetic */ LogSystem logSystem;
    private static /* synthetic */ boolean forcedVerbose;
    private static /* synthetic */ boolean verbose;
    
    public static void warn(final String llllIIIllllIlll) {
        Log.logSystem.warn(llllIIIllllIlll);
    }
    
    public static void debug(final String llllIIIlllIlIll) {
        if (Log.verbose || Log.forcedVerbose) {
            Log.logSystem.debug(llllIIIlllIlIll);
        }
    }
    
    public static void info(final String llllIIIlllIlllI) {
        if (Log.verbose || Log.forcedVerbose) {
            Log.logSystem.info(llllIIIlllIlllI);
        }
    }
    
    public static void error(final String llllIIlIIIIIIIl, final Throwable llllIIlIIIIIIII) {
        Log.logSystem.error(llllIIlIIIIIIIl, llllIIlIIIIIIII);
    }
    
    public static void setVerbose(final boolean llllIIlIIIIlIIl) {
        if (Log.forcedVerbose) {
            return;
        }
        Log.verbose = llllIIlIIIIlIIl;
    }
    
    public static void setLogSystem(final LogSystem llllIIlIIIIlIll) {
        Log.logSystem = llllIIlIIIIlIll;
    }
    
    public static void setForcedVerboseOn() {
        Log.forcedVerbose = true;
        Log.verbose = true;
    }
    
    public static void error(final String llllIIIlllllIlI) {
        Log.logSystem.error(llllIIIlllllIlI);
    }
    
    public static void checkVerboseLogSetting() {
        try {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                @Override
                public Object run() {
                    final String lllllllllllllllllIIIIlIIlIlIllII = System.getProperty("org.newdawn.slick.forceVerboseLog");
                    if (lllllllllllllllllIIIIlIIlIlIllII != null && lllllllllllllllllIIIIlIIlIlIllII.equalsIgnoreCase("true")) {
                        Log.setForcedVerboseOn();
                    }
                    return null;
                }
            });
        }
        catch (Throwable t) {}
    }
    
    static {
        forceVerboseProperty = "org.newdawn.slick.forceVerboseLog";
        forceVerbosePropertyOnValue = "true";
        Log.verbose = true;
        Log.forcedVerbose = false;
        Log.logSystem = new DefaultLogSystem();
    }
    
    public static void warn(final String llllIIIllllIIlI, final Throwable llllIIIllllIIll) {
        Log.logSystem.warn(llllIIIllllIIlI, llllIIIllllIIll);
    }
    
    private Log() {
    }
    
    public static void error(final Throwable llllIIIllllllIl) {
        Log.logSystem.error(llllIIIllllllIl);
    }
}
