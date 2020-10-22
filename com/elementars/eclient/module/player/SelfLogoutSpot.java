package com.elementars.eclient.module.player;

import java.util.concurrent.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.network.*;
import com.elementars.eclient.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import com.elementars.eclient.event.*;

public class SelfLogoutSpot extends Module
{
    public static /* synthetic */ SelfLogoutSpot INSTANCE;
    public /* synthetic */ ConcurrentHashMap<String, String> logoutMap;
    
    @Override
    public void onEnable() {
        FMLCommonHandler.instance().bus().register((Object)this);
    }
    
    @SubscribeEvent
    public void onDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent llllllllllllllllIlIlllIIlIIIIlIl) {
        if (SelfLogoutSpot.mc.getCurrentServerData() != null && SelfLogoutSpot.mc.player != null) {
            this.logoutMap.put(SelfLogoutSpot.mc.getCurrentServerData().serverIP, String.valueOf(new StringBuilder().append("X: ").append(Xulu.df.format(SelfLogoutSpot.mc.player.posX)).append(", Y: ").append(Xulu.df.format(SelfLogoutSpot.mc.player.posY)).append(", Z: ").append(Xulu.df.format(SelfLogoutSpot.mc.player.posZ))));
        }
    }
    
    public SelfLogoutSpot() {
        super("SelfLogoutSpot", "Saves your logout spot in case you forget", 0, Category.PLAYER, true);
        this.logoutMap = new ConcurrentHashMap<String, String>();
        SelfLogoutSpot.INSTANCE = this;
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket llllllllllllllllIlIlllIIlIIIllIl) {
        if (llllllllllllllllIlIlllIIlIIIllIl.getPacket() instanceof SPacketDisconnect && SelfLogoutSpot.mc.getCurrentServerData() != null && SelfLogoutSpot.mc.player != null) {
            this.logoutMap.put(SelfLogoutSpot.mc.getCurrentServerData().serverIP, String.valueOf(new StringBuilder().append("X: ").append(Xulu.df.format(SelfLogoutSpot.mc.player.posX)).append(", Y: ").append(Xulu.df.format(SelfLogoutSpot.mc.player.posY)).append(", Z: ").append(Xulu.df.format(SelfLogoutSpot.mc.player.posZ))));
        }
    }
    
    @Override
    public void onDisable() {
        FMLCommonHandler.instance().bus().unregister((Object)this);
    }
}
