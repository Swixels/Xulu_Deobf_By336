package com.elementars.eclient.module.movement;

import net.minecraftforge.common.*;
import com.elementars.eclient.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoSlowDown extends Module
{
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public NoSlowDown() {
        super("NoSlowDown", "Prevents slowdown when using items", 0, Category.MOVEMENT, true);
    }
    
    @SubscribeEvent
    public void onInput(final InputUpdateEvent lllllllllllllllllIllIlIlIlIllIIl) {
        if (NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            final MovementInput movementInput = lllllllllllllllllIllIlIlIlIllIIl.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            final MovementInput movementInput2 = lllllllllllllllllIllIlIlIlIllIIl.getMovementInput();
            movementInput2.moveForward *= 5.0f;
        }
    }
}
