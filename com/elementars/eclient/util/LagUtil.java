package com.elementars.eclient.util;

import com.elementars.eclient.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import com.elementars.eclient.event.*;

public class LagUtil
{
    public static /* synthetic */ LagUtil INSTANCE;
    private /* synthetic */ long timeLastTimeUpdate;
    
    public LagUtil() {
        this.timeLastTimeUpdate = -1L;
        Xulu.EVENT_MANAGER.register(this);
    }
    
    public long getLastTimeDiff() {
        if (this.timeLastTimeUpdate != -1L) {
            return System.currentTimeMillis() - this.timeLastTimeUpdate;
        }
        return 0L;
    }
    
    @EventTarget
    public void onPacketPreceived(final EventReceivePacket lIllIIlIlIIIl) {
        if (lIllIIlIlIIIl.getPacket() instanceof SPacketTimeUpdate) {
            this.timeLastTimeUpdate = System.currentTimeMillis();
            LagUtil.INSTANCE.timeLastTimeUpdate = this.timeLastTimeUpdate;
        }
    }
}
