package com.elementars.eclient.event;

import com.elementars.eclient.*;
import java.lang.reflect.*;
import com.elementars.eclient.util.*;

public abstract class Event
{
    private /* synthetic */ boolean cancelled;
    private /* synthetic */ State state;
    private final /* synthetic */ float partialTicks;
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean lllllllllllllllllIIllllIIIIllIII) {
        this.cancelled = lllllllllllllllllIIllllIIIIllIII;
    }
    
    private static void call(final Event lllllllllllllllllIIllllIIIIIllll) {
        final ArrayHelper<Data> lllllllllllllllllIIllllIIIIIlllI = Xulu.EVENT_MANAGER.get(lllllllllllllllllIIllllIIIIIllll.getClass());
        if (lllllllllllllllllIIllllIIIIIlllI != null) {
            for (final Data lllllllllllllllllIIllllIIIIlIIII : lllllllllllllllllIIllllIIIIIlllI) {
                try {
                    lllllllllllllllllIIllllIIIIlIIII.target.invoke(lllllllllllllllllIIllllIIIIlIIII.source, lllllllllllllllllIIllllIIIIIllll);
                }
                catch (IllegalAccessException lllllllllllllllllIIllllIIIIlIIlI) {
                    lllllllllllllllllIIllllIIIIlIIlI.printStackTrace();
                }
                catch (InvocationTargetException lllllllllllllllllIIllllIIIIlIIIl) {
                    lllllllllllllllllIIllllIIIIlIIIl.printStackTrace();
                }
            }
        }
    }
    
    public State getEventState() {
        return this.state;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public Event call() {
        this.cancelled = false;
        call(this);
        return this;
    }
    
    public Event() {
        this.state = State.PRE;
        this.partialTicks = Wrapper.getMinecraft().getRenderPartialTicks();
    }
    
    public void setState(final State lllllllllllllllllIIllllIIIlIIlII) {
        this.state = lllllllllllllllllIIllllIIIlIIlII;
    }
    
    public enum State
    {
        PRE, 
        POST;
    }
}
