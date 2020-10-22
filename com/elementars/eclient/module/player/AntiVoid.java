package com.elementars.eclient.module.player;

import dev.xulu.settings.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiVoid extends Module
{
    private /* synthetic */ boolean wasElytraFlying;
    private final /* synthetic */ Value<Integer> y_level;
    private final /* synthetic */ Value<String> mode;
    public /* synthetic */ Set<String> ipList;
    public static /* synthetic */ AntiVoid INSTANCE;
    
    @Override
    public void onEnable() {
        FMLCommonHandler.instance().bus().register((Object)this);
    }
    
    private boolean isOverVoid() {
        return AntiVoid.mc.world.getBlockState(new BlockPos((int)AntiVoid.mc.player.posX, 0, (int)AntiVoid.mc.player.posZ)).getBlock() == Blocks.AIR;
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket llIlIIllIlIIIl) {
        if (llIlIIllIlIIIl.getPacket() instanceof SPacketDisconnect && AntiVoid.mc.getCurrentServerData() != null && AntiVoid.mc.player != null) {
            this.ipList.add(AntiVoid.mc.getCurrentServerData().serverIP);
        }
    }
    
    public AntiVoid() {
        super("AntiVoid", "Prevents death from afk flying", 0, Category.PLAYER, true);
        this.mode = this.register(new Value<String>("Mode", this, "Normal", new String[] { "AFK Elytra", "Normal" }));
        this.y_level = this.register(new Value<Integer>("Y Level", this, 100, 0, 256));
        this.ipList = new HashSet<String>();
        AntiVoid.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (AntiVoid.mc.player != null && AntiVoid.mc.world != null) {
            if (this.mode.getValue().equalsIgnoreCase("Normal")) {
                boolean llIlIIllIllIll = true;
                for (int llIlIIllIlllII = (int)AntiVoid.mc.player.posY; llIlIIllIlllII > -1; --llIlIIllIlllII) {
                    if (AntiVoid.mc.world.getBlockState(new BlockPos(AntiVoid.mc.player.posX, (double)llIlIIllIlllII, AntiVoid.mc.player.posZ)).getBlock() != Blocks.AIR) {
                        llIlIIllIllIll = false;
                        break;
                    }
                }
                if (AntiVoid.mc.player.posY < this.y_level.getValue() && llIlIIllIllIll) {
                    AntiVoid.mc.player.motionY = 0.0;
                }
            }
            else if (AntiVoid.mc.player.isElytraFlying()) {
                this.wasElytraFlying = true;
            }
            else if (AntiVoid.mc.player.posY < this.y_level.getValue() && this.wasElytraFlying && this.isOverVoid()) {
                AntiVoid.mc.world.sendQuittingDisconnectingPacket();
                this.wasElytraFlying = false;
            }
        }
    }
    
    @Override
    public void onDisable() {
        FMLCommonHandler.instance().bus().unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onConnect(final FMLNetworkEvent.ClientConnectedToServerEvent llIlIIllIIlIlI) {
        if (AntiVoid.mc.getCurrentServerData() != null) {
            this.ipList.remove(AntiVoid.mc.getCurrentServerData().serverIP);
        }
    }
    
    @SubscribeEvent
    public void onDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent llIlIIllIIlllI) {
        if (AntiVoid.mc.getCurrentServerData() != null && AntiVoid.mc.player != null) {
            this.ipList.add(AntiVoid.mc.getCurrentServerData().serverIP);
        }
    }
}
