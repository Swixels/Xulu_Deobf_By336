package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.module.*;

public class ForgeFly extends Module
{
    private final /* synthetic */ Value<Boolean> fenable;
    private final /* synthetic */ Value<Double> speed;
    
    @Override
    public void onUpdate() {
        if (ForgeFly.mc.player.isElytraFlying()) {
            this.enableFly();
        }
        ForgeFly.mc.player.capabilities.setFlySpeed(this.speed.getValue().floatValue());
    }
    
    private void enableFly() {
        if (ForgeFly.mc.player == null || ForgeFly.mc.player.capabilities == null) {
            return;
        }
        ForgeFly.mc.player.capabilities.allowFlying = true;
        ForgeFly.mc.player.capabilities.isFlying = true;
    }
    
    @Override
    public void onEnable() {
        if (this.fenable.getValue()) {
            ForgeFly.mc.addScheduledTask(() -> {
                if (ForgeFly.mc.player != null && !ForgeFly.mc.player.isElytraFlying()) {
                    ForgeFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ForgeFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                }
            });
        }
    }
    
    private void disableFly() {
        if (ForgeFly.mc.player == null || ForgeFly.mc.player.capabilities == null) {
            return;
        }
        final PlayerCapabilities llllIlIIIllIIl = new PlayerCapabilities();
        ForgeFly.mc.playerController.getCurrentGameType().configurePlayerCapabilities(llllIlIIIllIIl);
        final PlayerCapabilities llllIlIIIllIII = ForgeFly.mc.player.capabilities;
        llllIlIIIllIII.allowFlying = llllIlIIIllIIl.allowFlying;
        llllIlIIIllIII.isFlying = (llllIlIIIllIIl.allowFlying && llllIlIIIllIII.isFlying);
        llllIlIIIllIII.setFlySpeed(llllIlIIIllIIl.getFlySpeed());
    }
    
    public ForgeFly() {
        super("ForgeFly", "ForgeHax ElytraFlight", 0, Category.MOVEMENT, true);
        this.fenable = this.register(new Value<Boolean>("Fly on Enable", this, false));
        this.speed = this.register(new Value<Double>("Speed", this, 0.05, 0.0, 10.0));
    }
    
    @Override
    public void onDisable() {
        this.disableFly();
        if (ForgeFly.mc.player != null) {
            ForgeFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ForgeFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
        }
    }
}
