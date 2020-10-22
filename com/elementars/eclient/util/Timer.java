package com.elementars.eclient.util;

public class Timer
{
    private /* synthetic */ long current;
    
    public Timer() {
        this.current = System.currentTimeMillis();
    }
    
    public void reset() {
        this.current = System.currentTimeMillis();
    }
    
    public boolean hasReached(final long lIllllIIIIlIIII) {
        return System.currentTimeMillis() - this.current >= lIllllIIIIlIIII;
    }
    
    public boolean sleep(final long lIlllIllllllIll) {
        if (this.time() >= lIlllIllllllIll) {
            this.reset();
            return true;
        }
        return false;
    }
    
    public long getTimePassed() {
        return System.currentTimeMillis() - this.current;
    }
    
    public long time() {
        return System.currentTimeMillis() - this.current;
    }
    
    public boolean hasReached(final long lIllllIIIIIlIIl, final boolean lIllllIIIIIIlIl) {
        if (lIllllIIIIIIlIl) {
            this.reset();
        }
        return System.currentTimeMillis() - this.current >= lIllllIIIIIlIIl;
    }
}
