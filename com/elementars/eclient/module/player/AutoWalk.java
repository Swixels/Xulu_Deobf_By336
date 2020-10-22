package com.elementars.eclient.module.player;

import net.minecraft.client.settings.*;
import com.elementars.eclient.module.*;

public class AutoWalk extends Module
{
    @Override
    public void onUpdate() {
        KeyBinding.setKeyBindState(AutoWalk.mc.gameSettings.keyBindForward.getKeyCode(), true);
    }
    
    public AutoWalk() {
        super("AutoWalk", "Automatically walks", 0, Category.PLAYER, true);
    }
}
