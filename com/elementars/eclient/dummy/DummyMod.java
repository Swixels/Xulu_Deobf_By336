package com.elementars.eclient.dummy;

import com.elementars.eclient.module.*;

public class DummyMod extends Module
{
    /* synthetic */ String info;
    
    public DummyMod(final String llllIllIIIIlI, final String llllIllIIIIIl) {
        super(llllIllIIIIlI, "Dummy", 0, Category.DUMMY, true);
        this.info = llllIllIIIIIl;
    }
    
    @Override
    public String getHudInfo() {
        return this.info;
    }
    
    public DummyMod(final String llllIllIIlIIl) {
        super(llllIllIIlIIl, "Dummy", 0, Category.DUMMY, true);
    }
}
