package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;

public class Sprint extends Module
{
    private final /* synthetic */ Value<String> mode;
    
    @EventTarget
    public void onMove(final PlayerMoveEvent lllllllllllllllllIllIIIlIIllIlII) {
        if (lllllllllllllllllIllIIIlIIllIlII.getEventState() != Event.State.PRE) {
            return;
        }
        if ((Sprint.mc.gameSettings.keyBindForward.isKeyDown() || Sprint.mc.gameSettings.keyBindBack.isKeyDown() || Sprint.mc.gameSettings.keyBindLeft.isKeyDown() || Sprint.mc.gameSettings.keyBindRight.isKeyDown()) && !Sprint.mc.player.isSneaking() && !Sprint.mc.player.collidedHorizontally && Sprint.mc.player.getFoodStats().getFoodLevel() > 6.0f) {
            Sprint.mc.player.setSprinting(true);
            final double[] lllllllllllllllllIllIIIlIIllIllI = MovementUtils.forward2(0.01745329238474369);
            lllllllllllllllllIllIIIlIIllIlII.setX(lllllllllllllllllIllIIIlIIllIllI[0] * 0.20000000298023224);
            lllllllllllllllllIllIIIlIIllIlII.setZ(lllllllllllllllllIllIIIlIIllIllI[1] * 0.20000000298023224);
        }
    }
    
    public Sprint() {
        super("Sprint", "Sprints", 0, Category.MOVEMENT, true);
        this.mode = this.register(new Value<String>("Mode", this, "Legit", new String[] { "Legit", "Rage" }));
    }
    
    @Override
    public void onUpdate() {
        try {
            if (this.mode.getValue().equalsIgnoreCase("Legit") && Sprint.mc.gameSettings.keyBindForward.isKeyDown() && !Sprint.mc.player.isSneaking() && !Sprint.mc.player.isHandActive() && !Sprint.mc.player.collidedHorizontally && Sprint.mc.currentScreen == null && Sprint.mc.player.getFoodStats().getFoodLevel() > 6.0f) {
                Sprint.mc.player.setSprinting(true);
            }
        }
        catch (Exception ex) {}
    }
}
