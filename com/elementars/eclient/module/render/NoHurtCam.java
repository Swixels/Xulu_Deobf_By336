package com.elementars.eclient.module.render;

import com.elementars.eclient.module.*;

public class NoHurtCam extends Module
{
    private static /* synthetic */ NoHurtCam INSTANCE;
    
    public NoHurtCam() {
        super("NoHurtCam", "Disables the hurt cam", 0, Category.RENDER, true);
        NoHurtCam.INSTANCE = this;
    }
    
    public static boolean shouldDisable() {
        return NoHurtCam.INSTANCE != null && NoHurtCam.INSTANCE.isToggled();
    }
}
