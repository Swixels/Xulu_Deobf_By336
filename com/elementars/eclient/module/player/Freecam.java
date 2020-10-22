package com.elementars.eclient.module.player;

import dev.xulu.settings.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import com.elementars.eclient.module.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.common.*;
import net.minecraft.world.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Freecam extends Module
{
    private final /* synthetic */ Value<Integer> vspeed;
    private /* synthetic */ float pitch;
    private /* synthetic */ float yaw;
    private final /* synthetic */ Value<Boolean> cancelPackets;
    private /* synthetic */ EntityOtherPlayerMP clonedPlayer;
    private /* synthetic */ Entity ridingEntity;
    private /* synthetic */ double posX;
    private /* synthetic */ double startPosZ;
    private /* synthetic */ float startPitch;
    private /* synthetic */ boolean isRidingEntity;
    private /* synthetic */ float startYaw;
    private final /* synthetic */ Value<Integer> speed;
    private /* synthetic */ double posZ;
    private /* synthetic */ double startPosY;
    private /* synthetic */ double posY;
    private /* synthetic */ double startPosX;
    
    @EventTarget
    public void onPacketSent(final EventSendPacket llllllllllllllllIllIlIllIIIIlIll) {
        if (this.cancelPackets.getValue() && (llllllllllllllllIllIlIllIIIIlIll.getPacket() instanceof CPacketPlayer || llllllllllllllllIllIlIllIIIIlIll.getPacket() instanceof CPacketInput)) {
            llllllllllllllllIllIlIllIIIIlIll.setCancelled(true);
        }
    }
    
    @Override
    public void onUpdate() {
        if (!Wrapper.getMinecraft().player.onGround) {
            Wrapper.getMinecraft().player.motionY = -0.2;
        }
        Wrapper.getMinecraft().player.onGround = true;
        Wrapper.getMinecraft().player.motionY = 0.0;
        Wrapper.getMinecraft().player.noClip = true;
        Wrapper.getMinecraft().player.capabilities.isFlying = true;
        Wrapper.getMinecraft().player.capabilities.setFlySpeed(this.speed.getValue() / 100.0f);
        Wrapper.getMinecraft().player.onGround = false;
        Wrapper.getMinecraft().player.fallDistance = 0.0f;
        if (Freecam.mc.gameSettings.keyBindJump.isKeyDown()) {
            final EntityPlayerSP player = Freecam.mc.player;
            player.motionY += this.vspeed.getValue() / 10.0f;
        }
        if (Freecam.mc.gameSettings.keyBindSneak.isKeyDown()) {
            final EntityPlayerSP player2 = Freecam.mc.player;
            player2.motionY += -this.vspeed.getValue() / 10.0f;
        }
    }
    
    private void playersSpeed(final double llllllllllllllllIllIlIlIllllIIll) {
        if (Wrapper.getMinecraft().player != null) {
            final MovementInput llllllllllllllllIllIlIlIlllllIII = Wrapper.getMinecraft().player.movementInput;
            double llllllllllllllllIllIlIlIllllIlll = llllllllllllllllIllIlIlIlllllIII.moveForward;
            double llllllllllllllllIllIlIlIllllIllI = llllllllllllllllIllIlIlIlllllIII.moveStrafe;
            float llllllllllllllllIllIlIlIllllIlIl = Wrapper.getMinecraft().player.rotationYaw;
            if (llllllllllllllllIllIlIlIllllIlll == 0.0 && llllllllllllllllIllIlIlIllllIllI == 0.0) {
                Wrapper.getMinecraft().player.motionX = 0.0;
                Wrapper.getMinecraft().player.motionZ = 0.0;
            }
            else {
                if (llllllllllllllllIllIlIlIllllIlll != 0.0) {
                    if (llllllllllllllllIllIlIlIllllIllI > 0.0) {
                        llllllllllllllllIllIlIlIllllIlIl += ((llllllllllllllllIllIlIlIllllIlll > 0.0) ? -45 : 45);
                    }
                    else if (llllllllllllllllIllIlIlIllllIllI < 0.0) {
                        llllllllllllllllIllIlIlIllllIlIl += ((llllllllllllllllIllIlIlIllllIlll > 0.0) ? 45 : -45);
                    }
                    llllllllllllllllIllIlIlIllllIllI = 0.0;
                    if (llllllllllllllllIllIlIlIllllIlll > 0.0) {
                        llllllllllllllllIllIlIlIllllIlll = 1.0;
                    }
                    else if (llllllllllllllllIllIlIlIllllIlll < 0.0) {
                        llllllllllllllllIllIlIlIllllIlll = -1.0;
                    }
                }
                Wrapper.getMinecraft().player.motionX = llllllllllllllllIllIlIlIllllIlll * llllllllllllllllIllIlIlIllllIIll * Math.cos(Math.toRadians(llllllllllllllllIllIlIlIllllIlIl + 90.0f)) + llllllllllllllllIllIlIlIllllIllI * llllllllllllllllIllIlIlIllllIIll * Math.sin(Math.toRadians(llllllllllllllllIllIlIlIllllIlIl + 90.0f));
                Wrapper.getMinecraft().player.motionZ = llllllllllllllllIllIlIlIllllIlll * llllllllllllllllIllIlIlIllllIIll * Math.sin(Math.toRadians(llllllllllllllllIllIlIlIllllIlIl + 90.0f)) - llllllllllllllllIllIlIlIllllIllI * llllllllllllllllIllIlIlIllllIIll * Math.cos(Math.toRadians(llllllllllllllllIllIlIlIllllIlIl + 90.0f));
            }
        }
    }
    
    public Freecam() {
        super("Freecam", "Allows you to fly out of your body", 0, Category.PLAYER, true);
        this.cancelPackets = this.register(new Value<Boolean>("Cancel Packets", this, true));
        this.speed = this.register(new Value<Integer>("Speed", this, 11, 1, 20));
        this.vspeed = this.register(new Value<Integer>("V Speed", this, 7, 1, 20));
    }
    
    @EventTarget
    public void onPacketRecived(final EventReceivePacket llllllllllllllllIllIlIllIIIIIIll) {
        if (llllllllllllllllIllIlIllIIIIIIll.getPacket() instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook llllllllllllllllIllIlIllIIIIIlll = (SPacketPlayerPosLook)llllllllllllllllIllIlIllIIIIIIll.getPacket();
            this.startPosX = llllllllllllllllIllIlIllIIIIIlll.getX();
            this.startPosY = llllllllllllllllIllIlIllIIIIIlll.getY();
            this.startPosZ = llllllllllllllllIllIlIllIIIIIlll.getZ();
            this.startPitch = llllllllllllllllIllIlIllIIIIIlll.getPitch();
            this.startYaw = llllllllllllllllIllIlIllIIIIIlll.getYaw();
        }
    }
    
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        if (Wrapper.getMinecraft().player != null) {
            this.isRidingEntity = (Wrapper.getMinecraft().player.getRidingEntity() != null);
            if (Wrapper.getMinecraft().player.getRidingEntity() == null) {
                this.posX = Wrapper.getMinecraft().player.posX;
                this.posY = Wrapper.getMinecraft().player.posY;
                this.posZ = Wrapper.getMinecraft().player.posZ;
            }
            else {
                this.ridingEntity = Wrapper.getMinecraft().player.getRidingEntity();
                Wrapper.getMinecraft().player.dismountRidingEntity();
            }
            this.pitch = Wrapper.getMinecraft().player.rotationPitch;
            this.yaw = Wrapper.getMinecraft().player.rotationYaw;
            this.clonedPlayer = new EntityOtherPlayerMP((World)Wrapper.getMinecraft().world, Wrapper.getMinecraft().getSession().getProfile());
            this.clonedPlayer.copyLocationAndAnglesFrom((Entity)Wrapper.getMinecraft().player);
            this.clonedPlayer.rotationYawHead = Wrapper.getMinecraft().player.rotationYawHead;
            Wrapper.getMinecraft().world.addEntityToWorld(-100, (Entity)this.clonedPlayer);
            Wrapper.getMinecraft().player.noClip = true;
        }
        super.onEnable();
    }
    
    @EventTarget
    public void onMove(final PlayerMoveEvent llllllllllllllllIllIlIlIlllIlIll) {
        if (llllllllllllllllIllIlIlIlllIlIll.getEventState() != Event.State.PRE) {
            return;
        }
        Freecam.mc.player.noClip = true;
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        if (Wrapper.getMinecraft().player != null) {
            Wrapper.getMinecraft().player.setPositionAndRotation(this.posX, this.posY, this.posZ, this.yaw, this.pitch);
            Wrapper.getMinecraft().world.removeEntityFromWorld(-100);
            this.clonedPlayer = null;
            final double posX = 0.0;
            this.posZ = posX;
            this.posY = posX;
            this.posX = posX;
            final float n = 0.0f;
            this.yaw = n;
            this.pitch = n;
            Freecam.mc.player.capabilities.isFlying = false;
            Wrapper.getMinecraft().player.capabilities.setFlySpeed(0.05f);
            Wrapper.getMinecraft().player.noClip = false;
            final EntityPlayerSP player = Wrapper.getMinecraft().player;
            final EntityPlayerSP player2 = Wrapper.getMinecraft().player;
            final EntityPlayerSP player3 = Wrapper.getMinecraft().player;
            final double motionX = 0.0;
            player3.motionZ = motionX;
            player2.motionY = motionX;
            player.motionX = motionX;
            if (this.isRidingEntity) {
                Wrapper.getMinecraft().player.startRiding(this.ridingEntity, true);
            }
        }
        Wrapper.getMinecraft().renderGlobal.loadRenderers();
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onPush(final PlayerSPPushOutOfBlocksEvent llllllllllllllllIllIlIlIllllllll) {
        llllllllllllllllIllIlIlIllllllll.setCanceled(true);
    }
}
