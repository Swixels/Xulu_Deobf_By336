package com.elementars.eclient.module.misc;

import com.elementars.eclient.module.*;

public class NoPacketKick extends Module
{
    private static /* synthetic */ NoPacketKick INSTANCE;
    
    public static boolean isEnabled() {
        return NoPacketKick.INSTANCE.isToggled();
    }
    
    public NoPacketKick() {
        super("NoPacketKick", "Prevents packet kicks", 0, Category.MISC, true);
        NoPacketKick.INSTANCE = this;
    }
}
