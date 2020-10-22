package com.elementars.eclient.util;

public class Triplet<T, S, U>
{
    /* synthetic */ U third;
    /* synthetic */ S second;
    /* synthetic */ T first;
    
    public Triplet(final T llllllllllllllllllIIlIllllIIIllI, final S llllllllllllllllllIIlIllllIIlIIl, final U llllllllllllllllllIIlIllllIIIlII) {
        this.first = llllllllllllllllllIIlIllllIIIllI;
        this.second = llllllllllllllllllIIlIllllIIlIIl;
        this.third = llllllllllllllllllIIlIllllIIIlII;
    }
    
    public void setFirst(final T llllllllllllllllllIIlIlllIllIlll) {
        this.first = llllllllllllllllllIIlIlllIllIlll;
    }
    
    public void setThird(final U llllllllllllllllllIIlIlllIlIlIIl) {
        this.third = llllllllllllllllllIIlIlllIlIlIIl;
    }
    
    public void setSecond(final S llllllllllllllllllIIlIlllIlIllll) {
        this.second = llllllllllllllllllIIlIlllIlIllll;
    }
    
    public S getSecond() {
        return this.second;
    }
    
    public T getFirst() {
        return this.first;
    }
    
    public U getThird() {
        return this.third;
    }
}
