package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;

public class Time extends Module
{
    /* synthetic */ long oldTime;
    private final /* synthetic */ Value<Long> time;
    
    @EventTarget
    public void onTime(final EventReceivePacket lIlIlIllIIIllI) {
        if (lIlIlIllIIIllI.getPacket() instanceof SPacketTimeUpdate) {
            this.oldTime = ((SPacketTimeUpdate)lIlIlIllIIIllI.getPacket()).getWorldTime();
            lIlIlIllIIIllI.setCancelled(true);
        }
    }
    
    @Override
    public void onEnable() {
        this.oldTime = Time.mc.world.getWorldTime();
    }
    
    @Override
    public void onDisable() {
        Time.mc.world.setWorldTime(this.oldTime);
    }
    
    @Override
    public void onUpdate() {
        if (Time.mc.world == null) {
            return;
        }
        Time.mc.world.setWorldTime((long)this.time.getValue());
    }
    
    public Time() {
        super("Time", "Clientside sets the time", 0, Category.MISC, true);
        this.time = this.register(new Value<Long>("Time", this, 0L, 0L, 24000L));
    }
}
