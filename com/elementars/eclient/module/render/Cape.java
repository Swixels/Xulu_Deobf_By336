package com.elementars.eclient.module.render;

import com.elementars.eclient.module.*;

public class Cape extends Module
{
    public static /* synthetic */ Cape INSTANCE;
    
    public static boolean isEnabled() {
        return Cape.INSTANCE.isToggled();
    }
    
    public Cape() {
        super("Capes", "Shows Xulu capes", 0, Category.CORE, true);
        Cape.INSTANCE = this;
    }
}
