package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import com.elementars.eclient.event.*;
import net.minecraft.potion.*;
import com.mojang.realmsclient.gui.*;
import java.nio.charset.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;
import com.elementars.eclient.module.*;

public class Strafe extends Module
{
    /* synthetic */ Value<Double> extraYBoost;
    private /* synthetic */ double motionSpeed;
    private /* synthetic */ int currentState;
    private /* synthetic */ double prevDist;
    /* synthetic */ Value<Boolean> jumpDetect;
    /* synthetic */ Value<Boolean> accelerationTimer;
    /* synthetic */ Value<Boolean> speedDetect;
    /* synthetic */ Value<Boolean> chat;
    private static /* synthetic */ String[] llIlIIl;
    private static /* synthetic */ int[] llIllII;
    /* synthetic */ Value<Boolean> autoSprint;
    /* synthetic */ Value<Double> multiplier;
    /* synthetic */ Value<Integer> timerSpeed;
    
    private static boolean lIIlIlIII(final int llIIIlIIllIIllI) {
        return llIIIlIIllIIllI <= 0;
    }
    
    private static int lIIlIIIIl(final float llIIIlIIllIllIl, final float llIIIlIIllIllII) {
        final float llIIIlIIllIlIll;
        return ((llIIIlIIllIlIll = llIIIlIIllIllIl - llIIIlIIllIllII) == 0.0f) ? 0 : ((llIIIlIIllIlIll < 0.0f) ? -1 : 1);
    }
    
    private static int lIIlIIllI(final float llIIIlIIlIllllI, final float llIIIlIIllIIIII) {
        final float llIIIlIIlIlllll;
        return ((llIIIlIIlIlllll = llIIIlIIlIllllI - llIIIlIIllIIIII) == 0.0f) ? 0 : ((llIIIlIIlIlllll < 0.0f) ? -1 : 1);
    }
    
    @Override
    public void onUpdate() {
        if (isNull(Strafe.mc.player)) {
            return;
        }
        this.prevDist = Math.sqrt((Strafe.mc.player.posX - Strafe.mc.player.prevPosX) * (Strafe.mc.player.posX - Strafe.mc.player.prevPosX) + (Strafe.mc.player.posZ - Strafe.mc.player.prevPosZ) * (Strafe.mc.player.posZ - Strafe.mc.player.prevPosZ));
        if (lIIlIIlII(((boolean)this.accelerationTimer.getValue()) ? 1 : 0)) {
            Strafe.mc.timer.tickLength = 50.0f / this.timerSpeed.getValue();
        }
        else if (lIIlIIlII(lIIlIIIIl(Strafe.mc.timer.tickLength, 50.0f))) {
            Strafe.mc.timer.tickLength = 50.0f;
        }
        if (lIIlIIlIl(Strafe.mc.player.isSprinting() ? 1 : 0) && lIIlIIlII(((boolean)this.autoSprint.getValue()) ? 1 : 0)) {
            Strafe.mc.player.setSprinting(Strafe.llIllII[1] != 0);
            Strafe.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Strafe.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
    }
    
    private static boolean lIIlIIlII(final int llIIIlIIlIIIlll) {
        return llIIIlIIlIIIlll != 0;
    }
    
    private static boolean lIIlIIlIl(final int llIIIIlllIIIlIl) {
        return llIIIIlllIIIlIl == 0;
    }
    
    @EventTarget
    public void onMove(final PlayerMoveEvent llIIIIlllIlIlll) {
        if (llIIIIlllIlIlll.getEventState() != Event.State.PRE) {
            return;
        }
        if (!isNull(Strafe.mc.player)) {
            switch (this.currentState) {
                case 0: {
                    this.currentState += Strafe.llIllII[1];
                    this.prevDist = 0.0;
                    break;
                }
                case 2: {
                    double llIIIIlllIlllII = 0.40123128 + this.extraYBoost.getValue();
                    if ((!lIIlIIlIl(lIIlIIllI(Strafe.mc.player.moveForward, 0.0f)) || lIIlIIlII(lIIlIIllI(Strafe.mc.player.moveStrafing, 0.0f))) && lIIlIIlII(Strafe.mc.player.onGround ? 1 : 0)) {
                        if (lIIlIIlII(Strafe.mc.player.isPotionActive(MobEffects.JUMP_BOOST) ? 1 : 0) && lIIlIIlII(((boolean)this.jumpDetect.getValue()) ? 1 : 0)) {
                            llIIIIlllIlllII += (Strafe.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + Strafe.llIllII[1]) * 0.1f;
                        }
                        llIIIIlllIlIlll.setY(Strafe.mc.player.motionY = llIIIIlllIlllII);
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
                    if ((!lIIlIlIII(Strafe.mc.world.getCollisionBoxes((Entity)Strafe.mc.player, Strafe.mc.player.getEntityBoundingBox().offset(0.0, Strafe.mc.player.motionY, 0.0)).size()) || lIIlIIlII(Strafe.mc.player.collidedVertically ? 1 : 0)) && lIIlIlIIl(this.currentState)) {
                        if (lIIlIIlIl(lIIlIIllI(Strafe.mc.player.moveForward, 0.0f)) && lIIlIIlIl(lIIlIIllI(Strafe.mc.player.moveStrafing, 0.0f))) {
                            this.currentState = Strafe.llIllII[0];
                        }
                        else {
                            this.currentState = Strafe.llIllII[1];
                        }
                    }
                    this.motionSpeed = this.prevDist - this.prevDist / 159.0;
                    break;
                }
            }
            this.motionSpeed = Math.max(this.motionSpeed, this.getBaseMotionSpeed());
            double llIIIIlllIllIll = Strafe.mc.player.movementInput.moveForward;
            double llIIIIlllIllIlI = Strafe.mc.player.movementInput.moveStrafe;
            final double llIIIIlllIllIIl = Strafe.mc.player.rotationYaw;
            if (lIIlIIlIl(lIIlIIlll(llIIIIlllIllIll, 0.0)) && lIIlIIlIl(lIIlIIlll(llIIIIlllIllIlI, 0.0))) {
                llIIIIlllIlIlll.setX(0.0);
                llIIIIlllIlIlll.setZ(0.0);
            }
            if (lIIlIIlII(lIIlIIlll(llIIIIlllIllIll, 0.0)) && lIIlIIlII(lIIlIIlll(llIIIIlllIllIlI, 0.0))) {
                llIIIIlllIllIll *= Math.sin(0.7853981633974483);
                llIIIIlllIllIlI *= Math.cos(0.7853981633974483);
            }
            llIIIIlllIlIlll.setX((llIIIIlllIllIll * this.motionSpeed * -Math.sin(Math.toRadians(llIIIIlllIllIIl)) + llIIIIlllIllIlI * this.motionSpeed * Math.cos(Math.toRadians(llIIIIlllIllIIl))) * (this.multiplier.getValue() * 0.99));
            llIIIIlllIlIlll.setZ((llIIIIlllIllIll * this.motionSpeed * Math.cos(Math.toRadians(llIIIIlllIllIIl)) - llIIIIlllIllIlI * this.motionSpeed * -Math.sin(Math.toRadians(llIIIIlllIllIIl))) * (this.multiplier.getValue() * 0.99));
            this.currentState += Strafe.llIllII[1];
        }
        llIIIIlllIlIlll.setCancelled(true);
    }
    
    private double getBaseMotionSpeed() {
        double llIIIIlllIIlIll = 0.272;
        if (lIIlIIlII(Strafe.mc.player.isPotionActive(MobEffects.SPEED) ? 1 : 0) && lIIlIIlII(((boolean)this.speedDetect.getValue()) ? 1 : 0)) {
            final int llIIIIlllIIllIl = Objects.requireNonNull(Strafe.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier();
            llIIIIlllIIlIll *= 1.0 + 0.2 * llIIIIlllIIllIl;
        }
        return llIIIIlllIIlIll;
    }
    
    @Override
    public void onEnable() {
        if (this.chat.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Enabled!")));
        }
    }
    
    private static int lIIlIIlll(final double llIIIlIIlIIllll, final double llIIIlIIlIlIIIl) {
        final double llIIIlIIlIlIIII;
        return ((llIIIlIIlIlIIII = llIIIlIIlIIllll - llIIIlIIlIlIIIl) == 0.0) ? 0 : ((llIIIlIIlIlIIII < 0.0) ? -1 : 1);
    }
    
    @Override
    public String getHudInfo() {
        return "Speed";
    }
    
    private static void lIIlIIIII() {
        (Strafe.llIllII = new int[10])[0] = 0;
        Strafe.llIllII[1] = " ".length();
        Strafe.llIllII[2] = "  ".length();
        Strafe.llIllII[3] = "   ".length();
        Strafe.llIllII[4] = 10;
        Strafe.llIllII[5] = 4;
        Strafe.llIllII[6] = 5;
        Strafe.llIllII[7] = 6;
        Strafe.llIllII[8] = 7;
        Strafe.llIllII[9] = 8;
    }
    
    private static void lIIIllllI() {
        (Strafe.llIlIIl = new String[Strafe.llIllII[8]])[Strafe.llIllII[0]] = lIIIllIII("n9pHF6SFvkOs6iUr+fnXgA==", "GmCTC");
        Strafe.llIlIIl[Strafe.llIllII[1]] = lIIIllIII("4noHmwJ5F40+cu8qBPcyzA==", "CVFaT");
        Strafe.llIlIIl[Strafe.llIllII[2]] = lIIIllIII("R+hGwU+dCgQQcUdIkD9ZYaUO+QBhMxiN", "RjGgZ");
        Strafe.llIlIIl[Strafe.llIllII[3]] = lIIIllIII("Dk9SQuIPQSn5I8lWMj8Z+w==", "dWNML");
        Strafe.llIlIIl[Strafe.llIllII[5]] = lIIIllIII("rPWGh7vSeiSJWWJOJQfq5wdZ8fI6Y9G+", "QkkkG");
        Strafe.llIlIIl[Strafe.llIllII[6]] = lIIIllIII("6BSD78RsHX6yVgm/4JINjBgTGCxZfgXF", "rXpxu");
        Strafe.llIlIIl[Strafe.llIllII[7]] = lIIIlllIl("ENR8rJxJYtA86kRMf8iVlQ==", "RTxXY");
    }
    
    private static String lIIIlllIl(final String llIIIIllIlIlllI, final String llIIIIllIlIllIl) {
        try {
            final SecretKeySpec llIIIIllIllIIll = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(llIIIIllIlIllIl.getBytes(StandardCharsets.UTF_8)), Strafe.llIllII[9]), "DES");
            final Cipher llIIIIllIllIIlI = Cipher.getInstance("DES");
            llIIIIllIllIIlI.init(Strafe.llIllII[2], llIIIIllIllIIll);
            return new String(llIIIIllIllIIlI.doFinal(Base64.getDecoder().decode(llIIIIllIlIlllI.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception llIIIIllIllIIIl) {
            llIIIIllIllIIIl.printStackTrace();
            return null;
        }
    }
    
    static {
        lIIlIIIII();
        lIIIllllI();
    }
    
    public Strafe() {
        super("Strafe", "Speed mode strafe", 0, Category.MOVEMENT, true);
        this.multiplier = this.register(new Value<Double>("Multiplier", this, 1.0, 0.1, 2.0));
        this.autoSprint = this.register(new Value<Boolean>("Auto Sprint", this, false));
        this.accelerationTimer = this.register(new Value<Boolean>("Acceleration Timer", this, false));
        this.timerSpeed = this.register(new Value<Integer>("Timer Speed", this, 1, 0, 10));
        this.speedDetect = this.register(new Value<Boolean>("Speed Detect", this, true));
        this.jumpDetect = this.register(new Value<Boolean>("Leaping Detect", this, true));
        this.extraYBoost = this.register(new Value<Double>("Extra Y Boost", this, 0.0, 0.0, 1.0));
        this.chat = this.register(new Value<Boolean>("Toggle msgs", this, false));
    }
    
    private static String lIIIllIII(final String llIIIIllIllllIl, final String llIIIIllIlllIlI) {
        try {
            final SecretKeySpec llIIIIlllIIIIII = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(llIIIIllIlllIlI.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher llIIIIllIllllll = Cipher.getInstance("Blowfish");
            llIIIIllIllllll.init(Strafe.llIllII[2], llIIIIlllIIIIII);
            return new String(llIIIIllIllllll.doFinal(Base64.getDecoder().decode(llIIIIllIllllIl.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception llIIIIllIlllllI) {
            llIIIIllIlllllI.printStackTrace();
            return null;
        }
    }
    
    private static boolean isNull(final Object llIIIlIIlIIlIlI) {
        return llIIIlIIlIIlIlI == null;
    }
    
    private static boolean lIIlIlIIl(final int llIIIlIIlIllIlI) {
        return llIIIlIIlIllIlI > 0;
    }
    
    @Override
    public void onDisable() {
        if (this.chat.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Disabled!")));
        }
    }
}
