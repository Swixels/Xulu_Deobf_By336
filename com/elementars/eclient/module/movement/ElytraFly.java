package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import com.elementars.eclient.module.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.entity.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.event.*;
import net.minecraft.item.*;

public class ElytraFly extends Module
{
    public /* synthetic */ Value<Boolean> allowUp;
    public /* synthetic */ Value<Float> hSpeed;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Value<Integer> devMode;
    public /* synthetic */ Value<Mode> mode;
    public /* synthetic */ Value<Float> speed;
    private /* synthetic */ Double flyHeight;
    public /* synthetic */ Value<String> instaMode;
    public /* synthetic */ Value<Boolean> noKick;
    public /* synthetic */ Value<Float> glide;
    public /* synthetic */ Value<Boolean> disableInLiquid;
    private static /* synthetic */ ElytraFly INSTANCE;
    public /* synthetic */ Value<Boolean> instaFly;
    public /* synthetic */ Value<Boolean> autoStart;
    public /* synthetic */ Value<Float> vSpeed;
    private /* synthetic */ Double posX;
    public /* synthetic */ Value<Boolean> infiniteDura;
    private /* synthetic */ Double posZ;
    private final /* synthetic */ Timer instaTimer;
    public /* synthetic */ Value<Boolean> keepMotion;
    public /* synthetic */ Value<Float> tooBeeSpeed;
    
    @Override
    public void onEnable() {
        if (this.mode.getValue() == Mode.BETTER && !this.autoStart.getValue() && this.devMode.getValue() == 1) {
            ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
        }
        this.flyHeight = null;
        this.posX = null;
        this.posZ = null;
    }
    
    private void runNoKick(final EntityPlayer llllllllllllllllIllllIlIIlIIlIII) {
        if (this.noKick.getValue() && !llllllllllllllllIllllIlIIlIIlIII.isElytraFlying() && llllllllllllllllIllllIlIIlIIlIII.ticksExisted % 4 == 0) {
            llllllllllllllllIllllIlIIlIIlIII.motionY = -0.03999999910593033;
        }
    }
    
    private void freezePlayer(final EntityPlayer llllllllllllllllIllllIlIIlIlIIll) {
        llllllllllllllllIllllIlIIlIlIIll.motionX = 0.0;
        llllllllllllllllIllllIlIIlIlIIll.motionY = 0.0;
        llllllllllllllllIllllIlIIlIlIIll.motionZ = 0.0;
    }
    
    private void setInstance() {
        ElytraFly.INSTANCE = this;
    }
    
    @EventTarget
    public void onSendPacket(final EventSendPacket llllllllllllllllIllllIlIllllIIII) {
        if (llllllllllllllllIllllIlIllllIIII.getPacket() instanceof CPacketPlayer && this.mode.getValue() == Mode.TOOBEE) {
            final CPacketPlayer llllllllllllllllIllllIlIllllIIll = (CPacketPlayer)llllllllllllllllIllllIlIllllIIII.getPacket();
            if (ElytraFly.mc.player.isElytraFlying() && !ElytraFly.mc.player.movementInput.jump && llllllllllllllllIllllIlIllllIIll.pitch < 1.0f) {
                llllllllllllllllIllllIlIllllIIll.pitch = 1.0f;
            }
        }
        if (llllllllllllllllIllllIlIllllIIII.getPacket() instanceof CPacketPlayer && this.mode.getValue() == Mode.BETTER) {
            final CPacketPlayer llllllllllllllllIllllIlIllllIIlI = (CPacketPlayer)llllllllllllllllIllllIlIllllIIII.getPacket();
            if (ElytraFly.mc.player.isElytraFlying() && !ElytraFly.mc.player.movementInput.jump && llllllllllllllllIllllIlIllllIIlI.pitch < 1.0f) {
                llllllllllllllllIllllIlIllllIIlI.pitch = 1.0f;
            }
        }
    }
    
    @Override
    public String getHudInfo() {
        return this.mode.getValue().name();
    }
    
    public ElytraFly() {
        super("ElytraFly", "Makes Elytra Flight better.", 0, Category.MOVEMENT, true);
        this.mode = this.register(new Value<Mode>("Mode", this, Mode.FLY, Mode.values()));
        this.devMode = this.register(new Value<Integer>("Type", this, 2, 1, 3)).visibleWhen(llllllllllllllllIllllIIlllllllll -> this.mode.getValue() == Mode.BYPASS || this.mode.getValue() == Mode.BETTER);
        this.speed = this.register(new Value<Float>("Speed", this, 1.0f, 0.0f, 10.0f)).visibleWhen(llllllllllllllllIllllIlIIIIIIIll -> this.mode.getValue() != Mode.FLY && this.mode.getValue() != Mode.BOOST && this.mode.getValue() != Mode.BETTER && this.mode.getValue() != Mode.OHARE);
        this.vSpeed = this.register(new Value<Float>("VSpeed", this, 0.3f, 0.0f, 10.0f)).visibleWhen(llllllllllllllllIllllIlIIIIIIlll -> this.mode.getValue() == Mode.BETTER || this.mode.getValue() == Mode.OHARE);
        this.hSpeed = this.register(new Value<Float>("HSpeed", this, 1.0f, 0.0f, 10.0f)).visibleWhen(llllllllllllllllIllllIlIIIIIlIll -> this.mode.getValue() == Mode.BETTER || this.mode.getValue() == Mode.OHARE);
        this.glide = this.register(new Value<Float>("Glide", this, 1.0E-4f, 0.0f, 0.2f)).visibleWhen(llllllllllllllllIllllIlIIIIIllll -> this.mode.getValue() == Mode.BETTER);
        this.tooBeeSpeed = this.register(new Value<Float>("TooBeeSpeed", this, 1.8000001f, 1.0f, 2.0f)).visibleWhen(llllllllllllllllIllllIlIIIIlIIll -> this.mode.getValue() == Mode.TOOBEE);
        this.autoStart = this.register(new Value<Boolean>("AutoStart", this, true));
        this.disableInLiquid = this.register(new Value<Boolean>("NoLiquid", this, true));
        this.infiniteDura = this.register(new Value<Boolean>("InfiniteDura", this, false));
        this.noKick = this.register(new Value<Boolean>("NoKick", this, false)).visibleWhen(llllllllllllllllIllllIlIIIIlIlll -> this.mode.getValue() == Mode.PACKET || this.mode.getValue() == Mode.BETTER);
        this.keepMotion = this.register(new Value<Boolean>("KeepMotion", this, true)).visibleWhen(llllllllllllllllIllllIlIIIIllIll -> this.mode.getValue() == Mode.BETTER);
        this.instaFly = this.register(new Value<Boolean>("InstaFly", this, true)).visibleWhen(llllllllllllllllIllllIlIIIlIIIlI -> this.mode.getValue() == Mode.BETTER);
        this.instaMode = this.register(new Value<String>("Takeoff Mode", this, "Static", new String[] { "None", "Static" })).visibleWhen(llllllllllllllllIllllIlIIIlIlIlI -> this.mode.getValue() == Mode.BETTER);
        this.allowUp = this.register(new Value<Boolean>("AllowUp", this, true)).visibleWhen(llllllllllllllllIllllIlIIIllIIIl -> this.mode.getValue() == Mode.BETTER);
        this.timer = new Timer();
        this.instaTimer = new Timer();
        this.setInstance();
    }
    
    @Override
    public void onDisable() {
        if (ElytraFly.mc.player == null || ElytraFly.mc.world == null) {
            return;
        }
        if (this.mode.getValue() == Mode.BETTER && this.devMode.getValue() == 2 && this.keepMotion.getValue()) {
            ElytraFly.mc.player.motionX = this.posX;
            ElytraFly.mc.player.motionZ = this.posZ;
        }
        ElytraFly.mc.timer.tickLength = 50.0f;
        if (ElytraFly.mc.player.capabilities.isCreativeMode) {
            return;
        }
        ElytraFly.mc.player.capabilities.isFlying = false;
    }
    
    private void setMoveSpeed(final PlayerMoveEvent llllllllllllllllIllllIlIlIllIlll, final double llllllllllllllllIllllIlIlIlllIll) {
        double llllllllllllllllIllllIlIlIlllIlI = ElytraFly.mc.player.movementInput.moveForward;
        double llllllllllllllllIllllIlIlIlllIIl = ElytraFly.mc.player.movementInput.moveStrafe;
        float llllllllllllllllIllllIlIlIlllIII = ElytraFly.mc.player.rotationYaw;
        if (llllllllllllllllIllllIlIlIlllIlI == 0.0 && llllllllllllllllIllllIlIlIlllIIl == 0.0) {
            llllllllllllllllIllllIlIlIllIlll.setX(0.0);
            llllllllllllllllIllllIlIlIllIlll.setZ(0.0);
            ElytraFly.mc.player.motionX = 0.0;
            ElytraFly.mc.player.motionZ = 0.0;
        }
        else {
            if (llllllllllllllllIllllIlIlIlllIlI != 0.0) {
                if (llllllllllllllllIllllIlIlIlllIIl > 0.0) {
                    llllllllllllllllIllllIlIlIlllIII += ((llllllllllllllllIllllIlIlIlllIlI > 0.0) ? -45 : 45);
                }
                else if (llllllllllllllllIllllIlIlIlllIIl < 0.0) {
                    llllllllllllllllIllllIlIlIlllIII += ((llllllllllllllllIllllIlIlIlllIlI > 0.0) ? 45 : -45);
                }
                llllllllllllllllIllllIlIlIlllIIl = 0.0;
                if (llllllllllllllllIllllIlIlIlllIlI > 0.0) {
                    llllllllllllllllIllllIlIlIlllIlI = 1.0;
                }
                else if (llllllllllllllllIllllIlIlIlllIlI < 0.0) {
                    llllllllllllllllIllllIlIlIlllIlI = -1.0;
                }
            }
            final double llllllllllllllllIllllIlIlIllllll = llllllllllllllllIllllIlIlIlllIlI * llllllllllllllllIllllIlIlIlllIll * -Math.sin(Math.toRadians(llllllllllllllllIllllIlIlIlllIII)) + llllllllllllllllIllllIlIlIlllIIl * llllllllllllllllIllllIlIlIlllIll * Math.cos(Math.toRadians(llllllllllllllllIllllIlIlIlllIII));
            final double llllllllllllllllIllllIlIlIlllllI = llllllllllllllllIllllIlIlIlllIlI * llllllllllllllllIllllIlIlIlllIll * Math.cos(Math.toRadians(llllllllllllllllIllllIlIlIlllIII)) - llllllllllllllllIllllIlIlIlllIIl * llllllllllllllllIllllIlIlIlllIll * -Math.sin(Math.toRadians(llllllllllllllllIllllIlIlIlllIII));
            llllllllllllllllIllllIlIlIllIlll.setX(llllllllllllllllIllllIlIlIllllll);
            llllllllllllllllIllllIlIlIllIlll.setZ(llllllllllllllllIllllIlIlIlllllI);
            ElytraFly.mc.player.motionX = llllllllllllllllIllllIlIlIllllll;
            ElytraFly.mc.player.motionZ = llllllllllllllllIllllIlIlIlllllI;
        }
    }
    
    static {
        ElytraFly.INSTANCE = new ElytraFly();
    }
    
    public static ElytraFly getInstance() {
        if (ElytraFly.INSTANCE == null) {
            ElytraFly.INSTANCE = new ElytraFly();
        }
        return ElytraFly.INSTANCE;
    }
    
    @EventTarget
    public void onUpdateWalkingPlayer(final MotionEvent llllllllllllllllIllllIlIlIIIIlIl) {
        if (ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
            return;
        }
        if (!ElytraFly.mc.player.isElytraFlying()) {
            if (!ElytraFly.mc.player.onGround && this.instaFly.getValue()) {
                double llllllllllllllllIllllIlIlIIlIlll = ElytraFly.mc.player.posY;
                if (this.noKick.getValue()) {
                    llllllllllllllllIllllIlIlIIlIlll -= this.glide.getValue();
                }
                if ("Static".equals(this.instaMode.getValue())) {
                    ElytraFly.mc.player.setPosition(ElytraFly.mc.player.posX, llllllllllllllllIllllIlIlIIlIlll, ElytraFly.mc.player.posZ);
                    ElytraFly.mc.player.setVelocity(0.0, 0.0, 0.0);
                }
                if (!this.instaTimer.hasReached(1000L)) {
                    return;
                }
                this.instaTimer.reset();
                ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
            }
            return;
        }
        switch (llllllllllllllllIllllIlIlIIIIlIl.getEventState()) {
            case PRE: {
                if (this.disableInLiquid.getValue() && (ElytraFly.mc.player.isInWater() || ElytraFly.mc.player.isInLava())) {
                    if (ElytraFly.mc.player.isElytraFlying()) {
                        ElytraFly.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    }
                    return;
                }
                if (this.autoStart.getValue() && ElytraFly.mc.gameSettings.keyBindJump.isKeyDown() && !ElytraFly.mc.player.isElytraFlying() && ElytraFly.mc.player.motionY < 0.0 && this.timer.hasReached(250L)) {
                    ElytraFly.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    this.timer.reset();
                }
                if (this.mode.getValue() == Mode.BETTER) {
                    final double[] llllllllllllllllIllllIlIlIIlIlII = MathUtil.directionSpeed((this.devMode.getValue() == 1) ? ((double)this.speed.getValue()) : ((double)this.hSpeed.getValue()));
                    switch (this.devMode.getValue()) {
                        case 1: {
                            ElytraFly.mc.player.setVelocity(0.0, 0.0, 0.0);
                            ElytraFly.mc.player.jumpMovementFactor = this.speed.getValue();
                            if (ElytraFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                                final EntityPlayerSP player;
                                final EntityPlayerSP llllllllllllllllIllllIlIlIIlIllI = player = ElytraFly.mc.player;
                                player.motionY += this.speed.getValue();
                            }
                            if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                final EntityPlayerSP player2;
                                final EntityPlayerSP llllllllllllllllIllllIlIlIIlIlIl = player2 = ElytraFly.mc.player;
                                player2.motionY -= this.speed.getValue();
                            }
                            if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                                ElytraFly.mc.player.motionX = llllllllllllllllIllllIlIlIIlIlII[0];
                                ElytraFly.mc.player.motionZ = llllllllllllllllIllllIlIlIIlIlII[1];
                                break;
                            }
                            ElytraFly.mc.player.motionX = 0.0;
                            ElytraFly.mc.player.motionZ = 0.0;
                            break;
                        }
                        case 2: {
                            if (ElytraFly.mc.player.isElytraFlying()) {
                                if (this.flyHeight == null) {
                                    this.flyHeight = ElytraFly.mc.player.posY;
                                }
                                if (this.noKick.getValue()) {
                                    this.flyHeight -= (Double)this.glide.getValue();
                                }
                                this.posX = 0.0;
                                this.posZ = 0.0;
                                if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                                    this.posX = llllllllllllllllIllllIlIlIIlIlII[0];
                                    this.posZ = llllllllllllllllIllllIlIlIIlIlII[1];
                                }
                                if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                    this.flyHeight = ElytraFly.mc.player.posY - this.vSpeed.getValue();
                                }
                                ElytraFly.mc.player.setPosition(ElytraFly.mc.player.posX + this.posX, (double)this.flyHeight, ElytraFly.mc.player.posZ + this.posZ);
                                ElytraFly.mc.player.setVelocity(0.0, 0.0, 0.0);
                                break;
                            }
                            this.flyHeight = null;
                            return;
                        }
                        case 3: {
                            if (ElytraFly.mc.player.isElytraFlying()) {
                                if (this.flyHeight == null || this.posX == null || this.posX == 0.0 || this.posZ == null || this.posZ == 0.0) {
                                    this.flyHeight = ElytraFly.mc.player.posY;
                                    this.posX = ElytraFly.mc.player.posX;
                                    this.posZ = ElytraFly.mc.player.posZ;
                                }
                                if (this.noKick.getValue()) {
                                    this.flyHeight -= (Double)this.glide.getValue();
                                }
                                if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                                    this.posX += llllllllllllllllIllllIlIlIIlIlII[0];
                                    this.posZ += llllllllllllllllIllllIlIlIIlIlII[1];
                                }
                                if (this.allowUp.getValue() && ElytraFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                                    this.flyHeight = ElytraFly.mc.player.posY + this.vSpeed.getValue() / 10.0f;
                                }
                                if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                    this.flyHeight = ElytraFly.mc.player.posY - this.vSpeed.getValue() / 10.0f;
                                }
                                ElytraFly.mc.player.setPosition((double)this.posX, (double)this.flyHeight, (double)this.posZ);
                                ElytraFly.mc.player.setVelocity(0.0, 0.0, 0.0);
                                break;
                            }
                            this.flyHeight = null;
                            this.posX = null;
                            this.posZ = null;
                            return;
                        }
                    }
                }
                final double llllllllllllllllIllllIlIlIIIlIlI = Math.toRadians(ElytraFly.mc.player.rotationYaw);
                if (ElytraFly.mc.player.isElytraFlying()) {
                    switch (this.mode.getValue()) {
                        case VANILLA: {
                            final float llllllllllllllllIllllIlIlIIIllIl = this.speed.getValue() * 0.05f;
                            if (ElytraFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                                final EntityPlayerSP player3;
                                final EntityPlayerSP llllllllllllllllIllllIlIlIIlIIll = player3 = ElytraFly.mc.player;
                                player3.motionY += llllllllllllllllIllllIlIlIIIllIl;
                            }
                            if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                final EntityPlayerSP player4;
                                final EntityPlayerSP llllllllllllllllIllllIlIlIIlIIlI = player4 = ElytraFly.mc.player;
                                player4.motionY -= llllllllllllllllIllllIlIlIIIllIl;
                            }
                            if (ElytraFly.mc.gameSettings.keyBindForward.isKeyDown()) {
                                final EntityPlayerSP player5;
                                final EntityPlayerSP llllllllllllllllIllllIlIlIIlIIIl = player5 = ElytraFly.mc.player;
                                player5.motionX -= Math.sin(llllllllllllllllIllllIlIlIIIlIlI) * llllllllllllllllIllllIlIlIIIllIl;
                                final EntityPlayerSP player6;
                                final EntityPlayerSP llllllllllllllllIllllIlIlIIlIIII = player6 = ElytraFly.mc.player;
                                player6.motionZ += Math.cos(llllllllllllllllIllllIlIlIIIlIlI) * llllllllllllllllIllllIlIlIIIllIl;
                            }
                            if (ElytraFly.mc.gameSettings.keyBindBack.isKeyDown()) {
                                final EntityPlayerSP player7;
                                final EntityPlayerSP llllllllllllllllIllllIlIlIIIllll = player7 = ElytraFly.mc.player;
                                player7.motionX += Math.sin(llllllllllllllllIllllIlIlIIIlIlI) * llllllllllllllllIllllIlIlIIIllIl;
                                final EntityPlayerSP player8;
                                final EntityPlayerSP llllllllllllllllIllllIlIlIIIlllI = player8 = ElytraFly.mc.player;
                                player8.motionZ -= Math.cos(llllllllllllllllIllllIlIlIIIlIlI) * llllllllllllllllIllllIlIlIIIllIl;
                                break;
                            }
                            break;
                        }
                        case PACKET: {
                            this.freezePlayer((EntityPlayer)ElytraFly.mc.player);
                            this.runNoKick((EntityPlayer)ElytraFly.mc.player);
                            final double[] llllllllllllllllIllllIlIlIIIllII = MathUtil.directionSpeed(this.speed.getValue());
                            if (ElytraFly.mc.player.movementInput.jump) {
                                ElytraFly.mc.player.motionY = this.speed.getValue();
                            }
                            if (ElytraFly.mc.player.movementInput.sneak) {
                                ElytraFly.mc.player.motionY = -this.speed.getValue();
                            }
                            if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                                ElytraFly.mc.player.motionX = llllllllllllllllIllllIlIlIIIllII[0];
                                ElytraFly.mc.player.motionZ = llllllllllllllllIllllIlIlIIIllII[1];
                            }
                            ElytraFly.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                            ElytraFly.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                            break;
                        }
                        case BYPASS: {
                            if (this.devMode.getValue() != 3) {
                                break;
                            }
                            if (ElytraFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                                ElytraFly.mc.player.motionY = 0.019999999552965164;
                            }
                            if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                ElytraFly.mc.player.motionY = -0.20000000298023224;
                            }
                            if (ElytraFly.mc.player.ticksExisted % 8 == 0 && ElytraFly.mc.player.posY <= 240.0) {
                                ElytraFly.mc.player.motionY = 0.019999999552965164;
                            }
                            ElytraFly.mc.player.capabilities.isFlying = true;
                            ElytraFly.mc.player.capabilities.setFlySpeed(0.025f);
                            final double[] llllllllllllllllIllllIlIlIIIlIll = MathUtil.directionSpeed(0.5199999809265137);
                            if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                                ElytraFly.mc.player.motionX = llllllllllllllllIllllIlIlIIIlIll[0];
                                ElytraFly.mc.player.motionZ = llllllllllllllllIllllIlIlIIIlIll[1];
                                break;
                            }
                            ElytraFly.mc.player.motionX = 0.0;
                            ElytraFly.mc.player.motionZ = 0.0;
                            break;
                        }
                    }
                }
                if (this.infiniteDura.getValue()) {
                    ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    break;
                }
                break;
            }
            case POST: {
                if (this.infiniteDura.getValue()) {
                    ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    break;
                }
                break;
            }
        }
    }
    
    private double[] forwardStrafeYaw(final double llllllllllllllllIllllIlIIllIlIII, final double llllllllllllllllIllllIlIIllIIIll, final double llllllllllllllllIllllIlIIllIIIlI) {
        final double[] llllllllllllllllIllllIlIIllIIlIl = { llllllllllllllllIllllIlIIllIlIII, llllllllllllllllIllllIlIIllIIIll, llllllllllllllllIllllIlIIllIIIlI };
        if ((llllllllllllllllIllllIlIIllIlIII != 0.0 || llllllllllllllllIllllIlIIllIIIll != 0.0) && llllllllllllllllIllllIlIIllIlIII != 0.0) {
            if (llllllllllllllllIllllIlIIllIIIll > 0.0) {
                final double[] llllllllllllllllIllllIlIIllIllIl = llllllllllllllllIllllIlIIllIIlIl;
                final int llllllllllllllllIllllIlIIllIllII = 2;
                final double[] array = llllllllllllllllIllllIlIIllIllIl;
                final int n = 2;
                array[n] += ((llllllllllllllllIllllIlIIllIlIII > 0.0) ? -45 : 45);
            }
            else if (llllllllllllllllIllllIlIIllIIIll < 0.0) {
                final double[] llllllllllllllllIllllIlIIllIlIll = llllllllllllllllIllllIlIIllIIlIl;
                final int llllllllllllllllIllllIlIIllIlIlI = 2;
                final double[] array2 = llllllllllllllllIllllIlIIllIlIll;
                final int n2 = 2;
                array2[n2] += ((llllllllllllllllIllllIlIIllIlIII > 0.0) ? 45 : -45);
            }
            llllllllllllllllIllllIlIIllIIlIl[1] = 0.0;
            if (llllllllllllllllIllllIlIIllIlIII > 0.0) {
                llllllllllllllllIllllIlIIllIIlIl[0] = 1.0;
            }
            else if (llllllllllllllllIllllIlIIllIlIII < 0.0) {
                llllllllllllllllIllllIlIIllIIlIl[0] = -1.0;
            }
        }
        return llllllllllllllllIllllIlIIllIIlIl;
    }
    
    @Override
    public void onUpdate() {
        if (this.mode.getValue() == Mode.BYPASS && this.devMode.getValue() == 1 && ElytraFly.mc.player.isElytraFlying()) {
            ElytraFly.mc.player.motionX = 0.0;
            ElytraFly.mc.player.motionY = -1.0E-4;
            ElytraFly.mc.player.motionZ = 0.0;
            final double llllllllllllllllIllllIllIIIIIlII = ElytraFly.mc.player.movementInput.moveForward;
            final double llllllllllllllllIllllIllIIIIIIll = ElytraFly.mc.player.movementInput.moveStrafe;
            final double[] llllllllllllllllIllllIllIIIIIIlI = this.forwardStrafeYaw(llllllllllllllllIllllIllIIIIIlII, llllllllllllllllIllllIllIIIIIIll, ElytraFly.mc.player.rotationYaw);
            final double llllllllllllllllIllllIllIIIIIIIl = llllllllllllllllIllllIllIIIIIIlI[0];
            final double llllllllllllllllIllllIllIIIIIIII = llllllllllllllllIllllIllIIIIIIlI[1];
            final double llllllllllllllllIllllIlIllllllll = llllllllllllllllIllllIllIIIIIIlI[2];
            if (llllllllllllllllIllllIllIIIIIlII != 0.0 || llllllllllllllllIllllIllIIIIIIll != 0.0) {
                ElytraFly.mc.player.motionX = llllllllllllllllIllllIllIIIIIIIl * this.speed.getValue() * Math.cos(Math.toRadians(llllllllllllllllIllllIlIllllllll + 90.0)) + llllllllllllllllIllllIllIIIIIIII * this.speed.getValue() * Math.sin(Math.toRadians(llllllllllllllllIllllIlIllllllll + 90.0));
                ElytraFly.mc.player.motionZ = llllllllllllllllIllllIllIIIIIIIl * this.speed.getValue() * Math.sin(Math.toRadians(llllllllllllllllIllllIlIllllllll + 90.0)) - llllllllllllllllIllllIllIIIIIIII * this.speed.getValue() * Math.cos(Math.toRadians(llllllllllllllllIllllIlIllllllll + 90.0));
            }
            if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                ElytraFly.mc.player.motionY = -1.0;
            }
        }
    }
    
    @EventTarget
    public void onLivingUpdate(final LocalPlayerUpdateEvent llllllllllllllllIllllIlIlIlIIIll) {
        if (!ElytraFly.mc.player.isElytraFlying()) {
            return;
        }
        switch (this.mode.getValue()) {
            case BOOST: {
                if (ElytraFly.mc.player.isInWater()) {
                    ElytraFly.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    return;
                }
                if (ElytraFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                    final EntityPlayerSP player;
                    final EntityPlayerSP llllllllllllllllIllllIlIlIlIllII = player = ElytraFly.mc.player;
                    player.motionY += 0.08;
                }
                else if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    final EntityPlayerSP player2;
                    final EntityPlayerSP llllllllllllllllIllllIlIlIlIlIll = player2 = ElytraFly.mc.player;
                    player2.motionY -= 0.04;
                }
                if (ElytraFly.mc.gameSettings.keyBindForward.isKeyDown()) {
                    final float llllllllllllllllIllllIlIlIlIlIlI = (float)Math.toRadians(ElytraFly.mc.player.rotationYaw);
                    final EntityPlayerSP player3;
                    final EntityPlayerSP llllllllllllllllIllllIlIlIlIlIIl = player3 = ElytraFly.mc.player;
                    player3.motionX -= MathHelper.sin(llllllllllllllllIllllIlIlIlIlIlI) * 0.05f;
                    final EntityPlayerSP player4;
                    final EntityPlayerSP llllllllllllllllIllllIlIlIlIlIII = player4 = ElytraFly.mc.player;
                    player4.motionZ += MathHelper.cos(llllllllllllllllIllllIlIlIlIlIlI) * 0.05f;
                    break;
                }
                if (ElytraFly.mc.gameSettings.keyBindBack.isKeyDown()) {
                    final float llllllllllllllllIllllIlIlIlIIlll = (float)Math.toRadians(ElytraFly.mc.player.rotationYaw);
                    final EntityPlayerSP player5;
                    final EntityPlayerSP llllllllllllllllIllllIlIlIlIIllI = player5 = ElytraFly.mc.player;
                    player5.motionX += MathHelper.sin(llllllllllllllllIllllIlIlIlIIlll) * 0.05f;
                    final EntityPlayerSP player6;
                    final EntityPlayerSP llllllllllllllllIllllIlIlIlIIlIl = player6 = ElytraFly.mc.player;
                    player6.motionZ -= MathHelper.cos(llllllllllllllllIllllIlIlIlIIlll) * 0.05f;
                    break;
                }
                break;
            }
            case FLY: {
                ElytraFly.mc.player.capabilities.isFlying = true;
                break;
            }
        }
    }
    
    @EventTarget
    public void onMove(final PlayerMoveEvent llllllllllllllllIllllIlIllIlIIIl) {
        if (this.mode.getValue() == Mode.OHARE) {
            final ItemStack llllllllllllllllIllllIlIllIllIll = ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if (llllllllllllllllIllllIlIllIllIll.getItem() == Items.ELYTRA && ItemElytra.isUsable(llllllllllllllllIllllIlIllIllIll) && ElytraFly.mc.player.isElytraFlying()) {
                llllllllllllllllIllllIlIllIlIIIl.setY(ElytraFly.mc.gameSettings.keyBindJump.isKeyDown() ? ((double)this.vSpeed.getValue()) : (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown() ? (-this.vSpeed.getValue()) : 0.0));
                ElytraFly.mc.player.addVelocity(0.0, ElytraFly.mc.gameSettings.keyBindJump.isKeyDown() ? ((double)this.vSpeed.getValue()) : (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown() ? (-this.vSpeed.getValue()) : 0.0), 0.0);
                ElytraFly.mc.player.rotateElytraX = 0.0f;
                ElytraFly.mc.player.rotateElytraY = 0.0f;
                ElytraFly.mc.player.rotateElytraZ = 0.0f;
                ElytraFly.mc.player.moveVertical = (ElytraFly.mc.gameSettings.keyBindJump.isKeyDown() ? this.vSpeed.getValue() : (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown() ? (-this.vSpeed.getValue()) : 0.0f));
                double llllllllllllllllIllllIlIllIllllI = ElytraFly.mc.player.movementInput.moveForward;
                double llllllllllllllllIllllIlIllIlllIl = ElytraFly.mc.player.movementInput.moveStrafe;
                float llllllllllllllllIllllIlIllIlllII = ElytraFly.mc.player.rotationYaw;
                if (llllllllllllllllIllllIlIllIllllI == 0.0 && llllllllllllllllIllllIlIllIlllIl == 0.0) {
                    llllllllllllllllIllllIlIllIlIIIl.setX(0.0);
                    llllllllllllllllIllllIlIllIlIIIl.setZ(0.0);
                }
                else {
                    if (llllllllllllllllIllllIlIllIllllI != 0.0) {
                        if (llllllllllllllllIllllIlIllIlllIl > 0.0) {
                            llllllllllllllllIllllIlIllIlllII += ((llllllllllllllllIllllIlIllIllllI > 0.0) ? -45 : 45);
                        }
                        else if (llllllllllllllllIllllIlIllIlllIl < 0.0) {
                            llllllllllllllllIllllIlIllIlllII += ((llllllllllllllllIllllIlIllIllllI > 0.0) ? 45 : -45);
                        }
                        llllllllllllllllIllllIlIllIlllIl = 0.0;
                        if (llllllllllllllllIllllIlIllIllllI > 0.0) {
                            llllllllllllllllIllllIlIllIllllI = 1.0;
                        }
                        else if (llllllllllllllllIllllIlIllIllllI < 0.0) {
                            llllllllllllllllIllllIlIllIllllI = -1.0;
                        }
                    }
                    final double llllllllllllllllIllllIlIlllIIIII = Math.cos(Math.toRadians(llllllllllllllllIllllIlIllIlllII + 90.0f));
                    final double llllllllllllllllIllllIlIllIlllll = Math.sin(Math.toRadians(llllllllllllllllIllllIlIllIlllII + 90.0f));
                    llllllllllllllllIllllIlIllIlIIIl.setX(llllllllllllllllIllllIlIllIllllI * this.hSpeed.getValue() * llllllllllllllllIllllIlIlllIIIII + llllllllllllllllIllllIlIllIlllIl * this.hSpeed.getValue() * llllllllllllllllIllllIlIllIlllll);
                    llllllllllllllllIllllIlIllIlIIIl.setZ(llllllllllllllllIllllIlIllIllllI * this.hSpeed.getValue() * llllllllllllllllIllllIlIllIlllll - llllllllllllllllIllllIlIllIlllIl * this.hSpeed.getValue() * llllllllllllllllIllllIlIlllIIIII);
                }
            }
        }
        else if (llllllllllllllllIllllIlIllIlIIIl.getEventState() == Event.State.PRE && this.mode.getValue() == Mode.BYPASS && this.devMode.getValue() == 3) {
            if (ElytraFly.mc.player.isElytraFlying()) {
                llllllllllllllllIllllIlIllIlIIIl.setX(0.0);
                llllllllllllllllIllllIlIllIlIIIl.setY(-1.0E-4);
                llllllllllllllllIllllIlIllIlIIIl.setZ(0.0);
                final double llllllllllllllllIllllIlIllIllIlI = ElytraFly.mc.player.movementInput.moveForward;
                final double llllllllllllllllIllllIlIllIllIIl = ElytraFly.mc.player.movementInput.moveStrafe;
                final double[] llllllllllllllllIllllIlIllIllIII = this.forwardStrafeYaw(llllllllllllllllIllllIlIllIllIlI, llllllllllllllllIllllIlIllIllIIl, ElytraFly.mc.player.rotationYaw);
                final double llllllllllllllllIllllIlIllIlIlll = llllllllllllllllIllllIlIllIllIII[0];
                final double llllllllllllllllIllllIlIllIlIllI = llllllllllllllllIllllIlIllIllIII[1];
                final double llllllllllllllllIllllIlIllIlIlIl = llllllllllllllllIllllIlIllIllIII[2];
                if (llllllllllllllllIllllIlIllIllIlI != 0.0 || llllllllllllllllIllllIlIllIllIIl != 0.0) {
                    llllllllllllllllIllllIlIllIlIIIl.setX(llllllllllllllllIllllIlIllIlIlll * this.speed.getValue() * Math.cos(Math.toRadians(llllllllllllllllIllllIlIllIlIlIl + 90.0)) + llllllllllllllllIllllIlIllIlIllI * this.speed.getValue() * Math.sin(Math.toRadians(llllllllllllllllIllllIlIllIlIlIl + 90.0)));
                    llllllllllllllllIllllIlIllIlIIIl.setY(llllllllllllllllIllllIlIllIlIlll * this.speed.getValue() * Math.sin(Math.toRadians(llllllllllllllllIllllIlIllIlIlIl + 90.0)) - llllllllllllllllIllllIlIllIlIllI * this.speed.getValue() * Math.cos(Math.toRadians(llllllllllllllllIllllIlIllIlIlIl + 90.0)));
                }
                if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    llllllllllllllllIllllIlIllIlIIIl.setY(-1.0);
                }
            }
        }
        else if (this.mode.getValue() == Mode.TOOBEE) {
            if (!ElytraFly.mc.player.isElytraFlying()) {
                return;
            }
            if (ElytraFly.mc.player.movementInput.jump) {
                return;
            }
            if (ElytraFly.mc.player.movementInput.sneak) {
                ElytraFly.mc.player.motionY = -(this.tooBeeSpeed.getValue() / 2.0f);
                llllllllllllllllIllllIlIllIlIIIl.setY(-(this.speed.getValue() / 2.0f));
            }
            else if (llllllllllllllllIllllIlIllIlIIIl.getY() != -1.01E-4) {
                llllllllllllllllIllllIlIllIlIIIl.setY(-1.01E-4);
                ElytraFly.mc.player.motionY = -1.01E-4;
            }
        }
        this.setMoveSpeed(llllllllllllllllIllllIlIllIlIIIl, this.tooBeeSpeed.getValue());
    }
    
    public enum Mode
    {
        FLY, 
        OHARE, 
        PACKET, 
        VANILLA, 
        BOOST, 
        TOOBEE, 
        BETTER, 
        BYPASS;
    }
}
