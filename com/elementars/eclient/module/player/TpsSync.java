package com.elementars.eclient.module.player;

import com.elementars.eclient.module.*;

public class TpsSync extends Module
{
    private static /* synthetic */ TpsSync INSTANCE;
    
    public static boolean isSync() {
        return TpsSync.INSTANCE.isToggled();
    }
    
    public TpsSync() {
        super("TpsSync", "Syncs client with the tps", 0, Category.PLAYER, true);
        TpsSync.INSTANCE = this;
    }
}
