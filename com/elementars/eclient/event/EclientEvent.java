package com.elementars.eclient.event;

import com.elementars.eclient.util.*;

public class EclientEvent
{
    private final /* synthetic */ float partialTicks;
    private /* synthetic */ Era era;
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public EclientEvent() {
        this.era = Era.PRE;
        this.partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
    }
    
    public Era getEra() {
        return this.era;
    }
    
    public enum Era
    {
        POST, 
        PERI, 
        PRE;
    }
}
