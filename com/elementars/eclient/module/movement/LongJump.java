package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import me.zero.alpine.listener.*;
import com.mojang.realmsclient.gui.*;
import java.nio.charset.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import com.elementars.eclient.module.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import com.elementars.eclient.event.*;
import net.minecraft.potion.*;
import java.util.*;

public class LongJump extends Module
{
    /* synthetic */ Value<Boolean> speedDetect;
    /* synthetic */ Value<Boolean> jumpDetect;
    private static /* synthetic */ String[] llIlIIl;
    private /* synthetic */ int currentState;
    /* synthetic */ Value<Boolean> autoSprint;
    /* synthetic */ Value<Boolean> accelerationTimer;
    private static /* synthetic */ int[] llIllII;
    /* synthetic */ Value<Boolean> chat;
    /* synthetic */ Value<Double> multiplier;
    /* synthetic */ Value<Integer> timerSpeed;
    private /* synthetic */ double prevDist;
    private /* synthetic */ boolean attempting;
    private /* synthetic */ double motionSpeed;
    /* synthetic */ Value<Double> extraYBoost;
    
    @Override
    public void onDisable() {
        if (this.chat.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Disabled!")));
        }
    }
    
    static {
        lIIlIIIII();
        lIIIllllI();
    }
    
    private static int lIIlIIIIl(final float llllIlllIllllll, final float llllIllllIIIIIl) {
        final float llllIllllIIIIII;
        return ((llllIllllIIIIII = llllIlllIllllll - llllIllllIIIIIl) == 0.0f) ? 0 : ((llllIllllIIIIII < 0.0f) ? -1 : 1);
    }
    
    private static String lIIIllIII(final String llllIllIlllIIll, final String llllIllIlllIIlI) {
        try {
            final SecretKeySpec llllIllIlllIllI = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(llllIllIlllIIlI.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher llllIllIlllIlIl = Cipher.getInstance("Blowfish");
            llllIllIlllIlIl.init(LongJump.llIllII[2], llllIllIlllIllI);
            return new String(llllIllIlllIlIl.doFinal(Base64.getDecoder().decode(llllIllIlllIIll.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception llllIllIlllIlII) {
            llllIllIlllIlII.printStackTrace();
            return null;
        }
    }
    
    public LongJump() {
        super("LongJump", "hop around", 0, Category.MOVEMENT, true);
        this.multiplier = this.register(new Value<Double>("Multiplier", this, 4.1, 1.0, 10.0));
        this.autoSprint = this.register(new Value<Boolean>("Auto Sprint", this, false));
        this.accelerationTimer = this.register(new Value<Boolean>("Acceleration Timer", this, false));
        this.timerSpeed = this.register(new Value<Integer>("Timer Speed", this, 1, 0, 10));
        this.speedDetect = this.register(new Value<Boolean>("Speed Detect", this, true));
        this.jumpDetect = this.register(new Value<Boolean>("Leaping Detect", this, true));
        this.extraYBoost = this.register(new Value<Double>("Extra Y Boost", this, 0.0, 0.0, 1.0));
        this.chat = this.register(new Value<Boolean>("Toggle msgs", this, false));
        this.attempting = false;
    }
    
    @Override
    public String getHudInfo() {
        return "Speed";
    }
    
    @Override
    public void onEnable() {
        if (this.chat.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Enabled!")));
        }
        this.attempting = false;
    }
    
    private static int lIIlIIlll(final double llllIlllIlIIlII, final double llllIlllIlIIllI) {
        final double llllIlllIlIIlIl;
        return ((llllIlllIlIIlIl = llllIlllIlIIlII - llllIlllIlIIllI) == 0.0) ? 0 : ((llllIlllIlIIlIl < 0.0) ? -1 : 1);
    }
    
    @Override
    public void onUpdate() {
        if (isNull(LongJump.mc.player)) {
            return;
        }
        this.prevDist = Math.sqrt((LongJump.mc.player.posX - LongJump.mc.player.prevPosX) * (LongJump.mc.player.posX - LongJump.mc.player.prevPosX) + (LongJump.mc.player.posZ - LongJump.mc.player.prevPosZ) * (LongJump.mc.player.posZ - LongJump.mc.player.prevPosZ));
        if (lIIlIIlII(((boolean)this.accelerationTimer.getValue()) ? 1 : 0)) {
            LongJump.mc.timer.tickLength = 50.0f / this.timerSpeed.getValue();
        }
        else if (lIIlIIlII(lIIlIIIIl(LongJump.mc.timer.tickLength, 50.0f))) {
            LongJump.mc.timer.tickLength = 50.0f;
        }
        if (lIIlIIlIl(LongJump.mc.player.isSprinting() ? 1 : 0) && lIIlIIlII(((boolean)this.autoSprint.getValue()) ? 1 : 0)) {
            LongJump.mc.player.setSprinting(LongJump.llIllII[1] != 0);
            LongJump.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)LongJump.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
    }
    
    private static boolean lIIlIlIIl(final int llllIlllIlIllll) {
        return llllIlllIlIllll > 0;
    }
    
    private static boolean lIIlIIlIl(final int llllIllIllllIll) {
        return llllIllIllllIll == 0;
    }
    
    private static boolean isNull(final Object llllIlllIlIIIII) {
        return llllIlllIlIIIII == null;
    }
    
    @EventTarget
    public void onMove(final PlayerMoveEvent llllIlllIIIlllI) {
        if (llllIlllIIIlllI.getEventState() != Event.State.PRE) {
            return;
        }
        if (!isNull(LongJump.mc.player)) {
            final float llllIlllIIlIIll = LongJump.mc.timer.tickLength / 1000.0f;
            switch (this.currentState) {
                case 0: {
                    this.currentState += LongJump.llIllII[1];
                    this.prevDist = 0.0;
                    break;
                }
                case 2: {
                    double llllIlllIIlIlII = 0.40123128 + this.extraYBoost.getValue();
                    if ((!lIIlIIlIl(lIIlIIllI(LongJump.mc.player.moveForward, 0.0f)) || lIIlIIlII(lIIlIIllI(LongJump.mc.player.moveStrafing, 0.0f))) && lIIlIIlII(LongJump.mc.player.onGround ? 1 : 0)) {
                        if (lIIlIIlII(LongJump.mc.player.isPotionActive(MobEffects.JUMP_BOOST) ? 1 : 0) && lIIlIIlII(((boolean)this.jumpDetect.getValue()) ? 1 : 0)) {
                            llllIlllIIlIlII += (LongJump.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + LongJump.llIllII[1]) * 0.1f;
                        }
                        llllIlllIIIlllI.setY(LongJump.mc.player.motionY = llllIlllIIlIlII);
                        this.motionSpeed *= 2.149;
                        break;
                    }
                    break;
                }
                case 3: {
                    this.motionSpeed = this.prevDist - 0.76 * (this.prevDist - this.getBaseMotionSpeed());
                    break;
                }
                default: {
                    if ((!lIIlIlIII(LongJump.mc.world.getCollisionBoxes((Entity)LongJump.mc.player, LongJump.mc.player.getEntityBoundingBox().offset(0.0, LongJump.mc.player.motionY, 0.0)).size()) || lIIlIIlII(LongJump.mc.player.collidedVertically ? 1 : 0)) && lIIlIlIIl(this.currentState)) {
                        if (lIIlIIlIl(lIIlIIllI(LongJump.mc.player.moveForward, 0.0f)) && lIIlIIlIl(lIIlIIllI(LongJump.mc.player.moveStrafing, 0.0f))) {
                            this.currentState = LongJump.llIllII[0];
                        }
                        else {
                            this.currentState = LongJump.llIllII[1];
                        }
                    }
                    this.motionSpeed = this.prevDist - this.prevDist / 159.0;
                    break;
                }
            }
            this.motionSpeed = Math.max(this.motionSpeed, this.getBaseMotionSpeed());
            double llllIlllIIlIIlI = LongJump.mc.player.movementInput.moveForward;
            double llllIlllIIlIIIl = LongJump.mc.player.movementInput.moveStrafe;
            final double llllIlllIIlIIII = LongJump.mc.player.rotationYaw;
            if (lIIlIIlIl(lIIlIIlll(llllIlllIIlIIlI, 0.0)) && lIIlIIlIl(lIIlIIlll(llllIlllIIlIIIl, 0.0))) {
                llllIlllIIIlllI.setX(0.0);
                llllIlllIIIlllI.setZ(0.0);
            }
            if (lIIlIIlII(lIIlIIlll(llllIlllIIlIIlI, 0.0)) && lIIlIIlII(lIIlIIlll(llllIlllIIlIIIl, 0.0))) {
                llllIlllIIlIIlI *= Math.sin(0.7853981633974483);
                llllIlllIIlIIIl *= Math.cos(0.7853981633974483);
            }
            llllIlllIIIlllI.setX((llllIlllIIlIIlI * this.motionSpeed * -Math.sin(Math.toRadians(llllIlllIIlIIII)) + llllIlllIIlIIIl * this.motionSpeed * Math.cos(Math.toRadians(llllIlllIIlIIII))) * 0.99);
            llllIlllIIIlllI.setZ((llllIlllIIlIIlI * this.motionSpeed * Math.cos(Math.toRadians(llllIlllIIlIIII)) - llllIlllIIlIIIl * this.motionSpeed * -Math.sin(Math.toRadians(llllIlllIIlIIII))) * 0.99);
            this.attempting = true;
            this.currentState += LongJump.llIllII[1];
        }
        llllIlllIIIlllI.setCancelled(true);
    }
    
    private static int lIIlIIllI(final float llllIlllIllIIll, final float llllIlllIllIIlI) {
        final float llllIlllIllIlII;
        return ((llllIlllIllIlII = llllIlllIllIIll - llllIlllIllIIlI) == 0.0f) ? 0 : ((llllIlllIllIlII < 0.0f) ? -1 : 1);
    }
    
    private static String lIIIlllIl(final String llllIllIllIIllI, final String llllIllIllIIlIl) {
        try {
            final SecretKeySpec llllIllIllIlIIl = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(llllIllIllIIlIl.getBytes(StandardCharsets.UTF_8)), LongJump.llIllII[9]), "DES");
            final Cipher llllIllIllIlIII = Cipher.getInstance("DES");
            llllIllIllIlIII.init(LongJump.llIllII[2], llllIllIllIlIIl);
            return new String(llllIllIllIlIII.doFinal(Base64.getDecoder().decode(llllIllIllIIllI.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception llllIllIllIIlll) {
            llllIllIllIIlll.printStackTrace();
            return null;
        }
    }
    
    private static boolean lIIlIlIII(final int llllIlllIlllIll) {
        return llllIlllIlllIll <= 0;
    }
    
    private static boolean lIIlIIlII(final int llllIlllIIlllIl) {
        return llllIlllIIlllIl != 0;
    }
    
    private static void lIIIllllI() {
        (LongJump.llIlIIl = new String[LongJump.llIllII[8]])[LongJump.llIllII[0]] = lIIIllIII("n9pHF6SFvkOs6iUr+fnXgA==", "GmCTC");
        LongJump.llIlIIl[LongJump.llIllII[1]] = lIIIllIII("4noHmwJ5F40+cu8qBPcyzA==", "CVFaT");
        LongJump.llIlIIl[LongJump.llIllII[2]] = lIIIllIII("R+hGwU+dCgQQcUdIkD9ZYaUO+QBhMxiN", "RjGgZ");
        LongJump.llIlIIl[LongJump.llIllII[3]] = lIIIllIII("Dk9SQuIPQSn5I8lWMj8Z+w==", "dWNML");
        LongJump.llIlIIl[LongJump.llIllII[5]] = lIIIllIII("rPWGh7vSeiSJWWJOJQfq5wdZ8fI6Y9G+", "QkkkG");
        LongJump.llIlIIl[LongJump.llIllII[6]] = lIIIllIII("6BSD78RsHX6yVgm/4JINjBgTGCxZfgXF", "rXpxu");
        LongJump.llIlIIl[LongJump.llIllII[7]] = lIIIlllIl("ENR8rJxJYtA86kRMf8iVlQ==", "RTxXY");
    }
    
    private double getBaseMotionSpeed() {
        double llllIlllIIIIIIl = 0.272 * this.multiplier.getValue();
        if (lIIlIIlII(LongJump.mc.player.isPotionActive(MobEffects.SPEED) ? 1 : 0) && lIIlIIlII(((boolean)this.speedDetect.getValue()) ? 1 : 0)) {
            final int llllIlllIIIIIll = Objects.requireNonNull(LongJump.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier();
            llllIlllIIIIIIl *= 1.0 + 0.2 * llllIlllIIIIIll;
        }
        return llllIlllIIIIIIl;
    }
    
    private static void lIIlIIIII() {
        (LongJump.llIllII = new int[10])[0] = 0;
        LongJump.llIllII[1] = " ".length();
        LongJump.llIllII[2] = "  ".length();
        LongJump.llIllII[3] = "   ".length();
        LongJump.llIllII[4] = 10;
        LongJump.llIllII[5] = 4;
        LongJump.llIllII[6] = 5;
        LongJump.llIllII[7] = 6;
        LongJump.llIllII[8] = 7;
        LongJump.llIllII[9] = 8;
    }
}
