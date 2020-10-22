package org.newdawn.slick.util;

import java.io.*;
import java.util.*;

public class DefaultLogSystem implements LogSystem
{
    public static /* synthetic */ PrintStream out;
    
    @Override
    public void info(final String lIIIlIIllIllI) {
        DefaultLogSystem.out.println(String.valueOf(new StringBuilder().append(new Date()).append(" INFO:").append(lIIIlIIllIllI)));
    }
    
    @Override
    public void error(final String lIIIlIlIIlIlI, final Throwable lIIIlIlIIIllI) {
        this.error(lIIIlIlIIlIlI);
        this.error(lIIIlIlIIIllI);
    }
    
    @Override
    public void warn(final String lIIIlIIlIllIl, final Throwable lIIIlIIlIlIIl) {
        this.warn(lIIIlIIlIllIl);
        lIIIlIIlIlIIl.printStackTrace(DefaultLogSystem.out);
    }
    
    @Override
    public void warn(final String lIIIlIIlllIlI) {
        DefaultLogSystem.out.println(String.valueOf(new StringBuilder().append(new Date()).append(" WARN:").append(lIIIlIIlllIlI)));
    }
    
    @Override
    public void error(final String lIIIlIIllllll) {
        DefaultLogSystem.out.println(String.valueOf(new StringBuilder().append(new Date()).append(" ERROR:").append(lIIIlIIllllll)));
    }
    
    @Override
    public void error(final Throwable lIIIlIlIIIIll) {
        DefaultLogSystem.out.println(String.valueOf(new StringBuilder().append(new Date()).append(" ERROR:").append(lIIIlIlIIIIll.getMessage())));
        lIIIlIlIIIIll.printStackTrace(DefaultLogSystem.out);
    }
    
    static {
        DefaultLogSystem.out = System.out;
    }
    
    @Override
    public void debug(final String lIIIlIIllIIlI) {
        DefaultLogSystem.out.println(String.valueOf(new StringBuilder().append(new Date()).append(" DEBUG:").append(lIIIlIIllIIlI)));
    }
}
