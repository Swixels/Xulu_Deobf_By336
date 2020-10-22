package com.elementars.eclient.module.player;

import net.minecraft.network.play.client.*;
import net.minecraft.client.entity.*;
import com.elementars.eclient.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;

public class Blink extends Module
{
    /* synthetic */ Queue<CPacketPlayer> packets;
    private /* synthetic */ EntityOtherPlayerMP clonedPlayer;
    
    @Override
    public void onDisable() {
        Xulu.EVENT_MANAGER.unregister(this);
        while (!this.packets.isEmpty()) {
            Blink.mc.player.connection.sendPacket((Packet)this.packets.poll());
        }
        final EntityPlayer llIIlIIIlIIlII = (EntityPlayer)Blink.mc.player;
        if (llIIlIIIlIIlII != null) {
            Blink.mc.world.removeEntityFromWorld(-100);
            this.clonedPlayer = null;
        }
    }
    
    public Blink() {
        super("Blink", "Hides movement for a short distance", 0, Category.PLAYER, true);
        this.packets = new LinkedList<CPacketPlayer>();
    }
    
    @Override
    public String getHudInfo() {
        return String.valueOf(this.packets.size());
    }
    
    @Override
    public void onEnable() {
        Xulu.EVENT_MANAGER.register(this);
        if (Blink.mc.player != null) {
            this.clonedPlayer = new EntityOtherPlayerMP((World)Blink.mc.world, Blink.mc.getSession().getProfile());
            this.clonedPlayer.copyLocationAndAnglesFrom((Entity)Blink.mc.player);
            this.clonedPlayer.rotationYawHead = Blink.mc.player.rotationYawHead;
            Blink.mc.world.addEntityToWorld(-100, (Entity)this.clonedPlayer);
        }
    }
    
    @EventTarget
    public void onPacket(final EventSendPacket llIIlIIIlIlIll) {
        if (this.isToggled() && llIIlIIIlIlIll.getPacket() instanceof CPacketPlayer) {
            llIIlIIIlIlIll.setCancelled(true);
            this.packets.add((CPacketPlayer)llIIlIIIlIlIll.getPacket());
        }
    }
}
