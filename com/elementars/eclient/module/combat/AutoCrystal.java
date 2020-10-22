package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.entity.item.*;
import java.util.concurrent.*;
import com.elementars.eclient.guirewrite.elements.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.enemy.*;
import net.minecraft.network.play.server.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.item.*;
import com.elementars.eclient.*;
import com.elementars.eclient.command.*;
import java.awt.*;
import com.elementars.eclient.event.*;
import net.minecraft.network.play.client.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.util.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import com.elementars.eclient.module.*;
import com.mojang.realmsclient.gui.*;
import com.elementars.eclient.event.events.*;
import java.util.*;

public class AutoCrystal extends Module
{
    private final /* synthetic */ Value<Integer> placeTick;
    private final /* synthetic */ Value<Integer> facePlace;
    /* synthetic */ EnumFacing f;
    private final /* synthetic */ Value<String> fastType;
    private final /* synthetic */ Value<String> echatcolor;
    private final /* synthetic */ Value<Float> range;
    private final /* synthetic */ Value<Integer> armorDmg;
    private final /* synthetic */ Value<Boolean> explode;
    private final /* synthetic */ Value<Boolean> oneHole;
    private /* synthetic */ BlockPos renderOld;
    private final /* synthetic */ List<BlockPos> placedCrystals;
    private static /* synthetic */ float newPitch;
    private final /* synthetic */ Value<Boolean> fast;
    private final /* synthetic */ Value<Integer> minDmg;
    private /* synthetic */ int newSlot;
    private final /* synthetic */ Value<Boolean> antiWeakness;
    private /* synthetic */ int placeCounter;
    private final /* synthetic */ Value<Boolean> toggleOff;
    private final /* synthetic */ Value<Boolean> smoothEsp;
    private final /* synthetic */ List<Entity> ignoreList;
    private final /* synthetic */ Value<Float> walls;
    private final /* synthetic */ Value<Boolean> damageWhite;
    private /* synthetic */ Map<EntityEnderCrystal, Integer> retryMap;
    private final /* synthetic */ Value<Boolean> watermark;
    private /* synthetic */ boolean switchCooldown;
    private final /* synthetic */ Value<String> dchatcolor;
    private final /* synthetic */ Value<Boolean> sync;
    public /* synthetic */ boolean isActive;
    private static /* synthetic */ double yaw;
    private final /* synthetic */ Value<Integer> smoothSpeed;
    private final /* synthetic */ Value<Integer> espA;
    private static /* synthetic */ boolean isSpoofingAngles;
    private final /* synthetic */ Value<Integer> hitRetryDelay;
    private final /* synthetic */ Value<Boolean> spam;
    private final /* synthetic */ Value<Float> ER;
    private final /* synthetic */ Value<Boolean> noSuicide;
    private final /* synthetic */ Value<Boolean> chat;
    private /* synthetic */ int waitCounter;
    private final /* synthetic */ Value<Integer> espF;
    private /* synthetic */ Map<EntityEnderCrystal, Integer> attemptMap;
    private final /* synthetic */ Value<Integer> waitTick;
    private /* synthetic */ boolean isAttacking;
    private final /* synthetic */ Value<Integer> toggleHealth;
    private final /* synthetic */ Value<Boolean> pre;
    private static /* synthetic */ float newYaw;
    private final /* synthetic */ Value<Boolean> raytrace;
    private final /* synthetic */ Value<Integer> maxSelfDmg;
    private /* synthetic */ BlockPos render;
    private final /* synthetic */ Value<String> rotateMode;
    private final /* synthetic */ Value<Boolean> renderDamage;
    private final /* synthetic */ Value<Boolean> armor;
    private final /* synthetic */ Value<Integer> espB;
    private final /* synthetic */ Value<Boolean> autoSwitch;
    private final /* synthetic */ Value<Boolean> randRotations;
    private static /* synthetic */ double pitch;
    private final /* synthetic */ Value<Boolean> renderBoolean;
    private final /* synthetic */ Value<Boolean> place;
    private final /* synthetic */ Value<Boolean> noGappleSwitch;
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<Integer> espR;
    private final /* synthetic */ Value<Float> placeRange;
    private final /* synthetic */ Value<Integer> espG;
    private final /* synthetic */ Value<String> rendermode;
    private final /* synthetic */ Value<Boolean> rotate;
    private final /* synthetic */ Value<Boolean> nodesync;
    private /* synthetic */ Entity renderEnt;
    private final /* synthetic */ Value<Integer> hitAttempts;
    private /* synthetic */ int oldSlot;
    /* synthetic */ ConcurrentHashMap<BlockPos, Integer> fadeList;
    private final /* synthetic */ Value<String> explodeMode;
    public static /* synthetic */ boolean isRand;
    
    private void doAutoCrystal() {
        AutoCrystal.isRand = this.randRotations.getValue();
        this.isActive = false;
        if (AutoCrystal.mc.player == null || AutoCrystal.mc.player.isDead) {
            return;
        }
        if (this.shouldPause()) {
            resetRotation();
            return;
        }
        if (AutoCrystal.mc.player.getHealth() <= this.toggleHealth.getValue() && this.toggleOff.getValue()) {
            this.toggle();
        }
        if (this.fast.getValue() && this.waitTick.getValue() > 0) {
            if (this.waitCounter < this.waitTick.getValue()) {
                ++this.waitCounter;
            }
            else {
                this.waitCounter = 0;
            }
        }
        this.fadeList.forEach((lIIIIllIlIIlIII, lIIIIllIlIIlIlI) -> {
            if (lIIIIllIlIIlIlI <= 0) {
                this.fadeList.remove(lIIIIllIlIIlIII);
            }
            else {
                this.fadeList.put(lIIIIllIlIIlIII, lIIIIllIlIIlIlI - this.smoothSpeed.getValue());
            }
            return;
        });
        int lIIIlIlIIlllIII = (AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) ? AutoCrystal.mc.player.inventory.currentItem : -1;
        if (lIIIlIlIIlllIII == -1) {
            for (int lIIIlIlIlIIIlll = 0; lIIIlIlIlIIIlll < 9; ++lIIIlIlIlIIIlll) {
                if (AutoCrystal.mc.player.inventory.getStackInSlot(lIIIlIlIlIIIlll).getItem() == Items.END_CRYSTAL) {
                    lIIIlIlIIlllIII = lIIIlIlIlIIIlll;
                    break;
                }
            }
        }
        this.retryMap.forEach((lIIIIllIlIlIIIl, lIIIIllIlIlIIll) -> {
            if (lIIIIllIlIlIIIl.isDead) {
                this.retryMap.remove(lIIIIllIlIlIIIl);
            }
            else if (this.retryMap.get(lIIIIllIlIlIIIl) != 0) {
                this.retryMap.put(lIIIIllIlIlIIIl, lIIIIllIlIlIIll - 1);
            }
            return;
        });
        EntityEnderCrystal lIIIlIlIIllIlll = null;
        final List<EntityEnderCrystal> lIIIlIlIIllIllI = new ArrayList<EntityEnderCrystal>();
        for (final Entity lIIIlIlIlIIIlIl : AutoCrystal.mc.world.loadedEntityList) {
            if (lIIIlIlIlIIIlIl instanceof EntityEnderCrystal) {
                final EntityEnderCrystal lIIIlIlIlIIIllI = (EntityEnderCrystal)lIIIlIlIlIIIlIl;
                if (AutoCrystal.mc.player.getDistance((Entity)lIIIlIlIlIIIllI) > this.range.getValue()) {
                    continue;
                }
                if (!this.checkCrystal((Entity)lIIIlIlIlIIIllI)) {
                    continue;
                }
                if (this.attemptMap.containsKey(lIIIlIlIlIIIllI) && this.attemptMap.get(lIIIlIlIlIIIllI).equals(this.hitAttempts.getValue())) {
                    if (this.retryMap.containsKey(lIIIlIlIlIIIllI) && this.retryMap.get(lIIIlIlIlIIIllI) == 0) {
                        this.attemptMap.put(lIIIlIlIlIIIllI, 0);
                        this.retryMap.remove(lIIIlIlIlIIIllI);
                    }
                    else {
                        if (!this.retryMap.containsKey(lIIIlIlIlIIIllI)) {
                            this.retryMap.put(lIIIlIlIlIIIllI, this.hitRetryDelay.getValue());
                            continue;
                        }
                        continue;
                    }
                }
                lIIIlIlIIllIllI.add(lIIIlIlIlIIIllI);
            }
        }
        if (!lIIIlIlIIllIllI.isEmpty()) {
            lIIIlIlIIllIllI.sort(Comparator.comparing(lIIIIllIlIllIlI -> AutoCrystal.mc.player.getDistance(lIIIIllIlIllIlI)));
            lIIIlIlIIllIlll = lIIIlIlIIllIllI.get(0);
        }
        if (this.explode.getValue() && lIIIlIlIIllIlll != null && (!this.fast.getValue() || this.waitCounter == 0)) {
            if (!AutoCrystal.mc.player.canEntityBeSeen((Entity)lIIIlIlIIllIlll) && AutoCrystal.mc.player.getDistance((Entity)lIIIlIlIIllIlll) > this.walls.getValue()) {
                PvPInfo.attack = false;
                return;
            }
            if (!this.fast.getValue() && this.waitTick.getValue() > 0) {
                if (this.waitCounter < this.waitTick.getValue()) {
                    ++this.waitCounter;
                    PvPInfo.attack = false;
                    return;
                }
                this.waitCounter = 0;
            }
            if (this.antiWeakness.getValue() && AutoCrystal.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
                if (!this.isAttacking) {
                    this.oldSlot = AutoCrystal.mc.player.inventory.currentItem;
                    PvPInfo.attack = true;
                    this.isAttacking = true;
                }
                this.newSlot = -1;
                for (int lIIIlIlIlIIIIll = 0; lIIIlIlIlIIIIll < 9; ++lIIIlIlIlIIIIll) {
                    final ItemStack lIIIlIlIlIIIlII = AutoCrystal.mc.player.inventory.getStackInSlot(lIIIlIlIlIIIIll);
                    if (lIIIlIlIlIIIlII != ItemStack.EMPTY) {
                        if (lIIIlIlIlIIIlII.getItem() instanceof ItemSword) {
                            this.newSlot = lIIIlIlIlIIIIll;
                            break;
                        }
                        if (lIIIlIlIlIIIlII.getItem() instanceof ItemTool) {
                            this.newSlot = lIIIlIlIlIIIIll;
                            break;
                        }
                    }
                }
                if (this.newSlot != -1) {
                    AutoCrystal.mc.player.inventory.currentItem = this.newSlot;
                    this.switchCooldown = true;
                }
            }
            PvPInfo.attack = true;
            this.isActive = true;
            if (this.rotate.getValue()) {
                this.lookAtPacket(lIIIlIlIIllIlll.posX, lIIIlIlIIllIlll.posY, lIIIlIlIIllIlll.posZ, (EntityPlayer)AutoCrystal.mc.player);
            }
            if (this.spam.getValue()) {
                for (int lIIIlIlIlIIIIlI = 0; lIIIlIlIlIIIIlI < 50; ++lIIIlIlIlIIIIlI) {
                    if (this.sync.getValue()) {
                        AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)lIIIlIlIIllIlll));
                    }
                    else {
                        AutoCrystal.mc.playerController.attackEntity((EntityPlayer)AutoCrystal.mc.player, (Entity)lIIIlIlIIllIlll);
                    }
                }
            }
            else if (this.sync.getValue()) {
                AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)lIIIlIlIIllIlll));
            }
            else {
                AutoCrystal.mc.playerController.attackEntity((EntityPlayer)AutoCrystal.mc.player, (Entity)lIIIlIlIIllIlll);
            }
            AutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.isActive = false;
            if (this.fast.getValue()) {
                final String s = this.fastType.getValue();
                switch (s) {
                    case "Instant": {
                        lIIIlIlIIllIlll.setDead();
                        break;
                    }
                    case "Ignore": {
                        this.ignoreList.add((Entity)lIIIlIlIIllIlll);
                        break;
                    }
                }
            }
            if (this.attemptMap.containsKey(lIIIlIlIIllIlll)) {
                this.attemptMap.put(lIIIlIlIIllIlll, this.attemptMap.get(lIIIlIlIIllIlll) + 1);
            }
            else {
                this.attemptMap.put(lIIIlIlIIllIlll, 1);
            }
        }
        else {
            resetRotation();
            if (this.oldSlot != -1) {
                AutoCrystal.mc.player.inventory.currentItem = this.oldSlot;
                this.oldSlot = -1;
            }
            this.isAttacking = false;
            this.isActive = false;
            if (this.placeTick.getValue() > 0) {
                if (this.placeCounter < this.placeTick.getValue()) {
                    ++this.placeCounter;
                    PvPInfo.place = false;
                    return;
                }
                this.placeCounter = 0;
            }
            boolean lIIIlIlIIllIlIl = false;
            if (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                lIIIlIlIIllIlIl = true;
            }
            else if (lIIIlIlIIlllIII == -1) {
                return;
            }
            final List<BlockPos> lIIIlIlIIllIlII = this.findCrystalBlocks();
            final List<Entity> lIIIlIlIIllIIll = new ArrayList<Entity>();
            final List<Entity> lIIIlIlIIllIIlI = new ArrayList<Entity>();
            for (final EntityPlayer lIIIlIlIlIIIIIl : AutoCrystal.mc.world.playerEntities) {
                if (Friends.isFriend(lIIIlIlIlIIIIIl.getName())) {
                    continue;
                }
                lIIIlIlIIllIIll.add((Entity)lIIIlIlIlIIIIIl);
            }
            lIIIlIlIIllIIll.sort(Comparator.comparing(lIIIIllIlIlllII -> AutoCrystal.mc.player.getDistance(lIIIIllIlIlllII)));
            lIIIlIlIIllIIll.removeIf(lIIIIllIlIlllll -> AutoCrystal.mc.player.getDistance(lIIIIllIlIlllll) > this.ER.getValue());
            if (this.pre.getValue()) {
                for (final EntityPlayer lIIIlIlIlIIIIII : AutoCrystal.mc.world.playerEntities) {
                    if (!Friends.isFriend(lIIIlIlIlIIIIII.getName())) {
                        if (!Enemies.isEnemy(lIIIlIlIlIIIIII.getName())) {
                            continue;
                        }
                        lIIIlIlIIllIIlI.add((Entity)lIIIlIlIlIIIIII);
                    }
                }
                lIIIlIlIIllIIlI.sort(Comparator.comparing(lIIIIllIllIIlIl -> AutoCrystal.mc.player.getDistance(lIIIIllIllIIlIl)));
                lIIIlIlIIllIIlI.removeIf(lIIIIllIllIlIII -> AutoCrystal.mc.player.getDistance(lIIIIllIllIlIII) > this.ER.getValue());
                if (!lIIIlIlIIllIIlI.isEmpty()) {
                    lIIIlIlIIllIIll.clear();
                    lIIIlIlIIllIIll.addAll(lIIIlIlIIllIIlI);
                }
            }
            BlockPos lIIIlIlIIllIIIl = null;
            double lIIIlIlIIllIIII = 0.5;
            final double lIIIlIlIIlIllll = 6.9696969E7;
            for (final Entity lIIIlIlIIlllIll : lIIIlIlIIllIIll) {
                if (lIIIlIlIIlllIll == AutoCrystal.mc.player) {
                    continue;
                }
                if (((EntityLivingBase)lIIIlIlIIlllIll).getHealth() <= 0.0f || lIIIlIlIIlllIll.isDead) {
                    continue;
                }
                if (AutoCrystal.mc.player == null) {
                    continue;
                }
                for (final BlockPos lIIIlIlIIllllII : lIIIlIlIIllIlII) {
                    final double lIIIlIlIIlllllI = lIIIlIlIIlllIll.getDistanceSq(lIIIlIlIIllllII);
                    if (!lIIIlIlIIlllIll.isDead) {
                        if (((EntityLivingBase)lIIIlIlIIlllIll).getHealth() <= 0.0f) {
                            continue;
                        }
                        if (lIIIlIlIIlllllI >= 169.0) {
                            continue;
                        }
                        final double lIIIlIlIIllllIl = calculateDamage(lIIIlIlIIllllII.getX() + 0.5, lIIIlIlIIllllII.getY() + 1, lIIIlIlIIllllII.getZ() + 0.5, lIIIlIlIIlllIll);
                        if (lIIIlIlIIllllIl < this.minDmg.getValue() && ((EntityLivingBase)lIIIlIlIIlllIll).getHealth() + ((EntityLivingBase)lIIIlIlIIlllIll).getAbsorptionAmount() > ((AutoTotem.isFullArmor((EntityPlayer)lIIIlIlIIlllIll) && !this.isArmorLow((EntityPlayer)lIIIlIlIIlllIll)) ? this.facePlace.getValue() : 36)) {
                            continue;
                        }
                        if (lIIIlIlIIllllIl <= lIIIlIlIIllIIII) {
                            continue;
                        }
                        final double lIIIlIlIIllllll = calculateDamage(lIIIlIlIIllllII.getX() + 0.5, lIIIlIlIIllllII.getY() + 1, lIIIlIlIIllllII.getZ() + 0.5, (Entity)AutoCrystal.mc.player);
                        if (lIIIlIlIIllllll > lIIIlIlIIllllIl && lIIIlIlIIllllIl >= ((EntityLivingBase)lIIIlIlIIlllIll).getHealth()) {
                            continue;
                        }
                        if (lIIIlIlIIllllll - 0.5 > AutoCrystal.mc.player.getHealth()) {
                            continue;
                        }
                        if (lIIIlIlIIllllll > this.maxSelfDmg.getValue()) {
                            continue;
                        }
                        lIIIlIlIIllIIII = lIIIlIlIIllllIl;
                        lIIIlIlIIllIIIl = lIIIlIlIIllllII;
                        this.renderEnt = lIIIlIlIIlllIll;
                    }
                }
            }
            if (lIIIlIlIIllIIII == 0.5) {
                this.render = null;
                this.renderEnt = null;
                resetRotation();
                return;
            }
            this.render = lIIIlIlIIllIIIl;
            if (this.place.getValue()) {
                if (AutoCrystal.mc.player == null) {
                    PvPInfo.place = false;
                    return;
                }
                this.isActive = true;
                if (this.rotate.getValue()) {
                    this.lookAtPacket(lIIIlIlIIllIIIl.getX() + 0.5, lIIIlIlIIllIIIl.getY() - 0.5, lIIIlIlIIllIIIl.getZ() + 0.5, (EntityPlayer)AutoCrystal.mc.player);
                }
                final RayTraceResult lIIIlIlIIlllIlI = AutoCrystal.mc.world.rayTraceBlocks(new Vec3d(AutoCrystal.mc.player.posX, AutoCrystal.mc.player.posY + AutoCrystal.mc.player.getEyeHeight(), AutoCrystal.mc.player.posZ), new Vec3d(lIIIlIlIIllIIIl.getX() + 0.5, lIIIlIlIIllIIIl.getY() - 0.5, lIIIlIlIIllIIIl.getZ() + 0.5));
                if (this.raytrace.getValue()) {
                    if (lIIIlIlIIlllIlI == null || lIIIlIlIIlllIlI.sideHit == null) {
                        lIIIlIlIIllIIIl = null;
                        this.f = null;
                        this.render = null;
                        resetRotation();
                        this.isActive = false;
                        PvPInfo.place = false;
                        return;
                    }
                    this.f = lIIIlIlIIlllIlI.sideHit;
                }
                if (!lIIIlIlIIllIlIl && AutoCrystal.mc.player.inventory.currentItem != lIIIlIlIIlllIII) {
                    if (this.autoSwitch.getValue()) {
                        if (this.noGappleSwitch.getValue() && this.isEatingGap()) {
                            this.isActive = false;
                            resetRotation();
                            PvPInfo.place = false;
                            return;
                        }
                        this.isActive = true;
                        AutoCrystal.mc.player.inventory.currentItem = lIIIlIlIIlllIII;
                        resetRotation();
                        this.switchCooldown = true;
                    }
                    PvPInfo.place = false;
                    return;
                }
                if (this.switchCooldown) {
                    this.switchCooldown = false;
                    PvPInfo.place = false;
                    return;
                }
                if (lIIIlIlIIllIIIl != null && AutoCrystal.mc.player != null) {
                    PvPInfo.place = true;
                    this.isActive = true;
                    if (this.raytrace.getValue() && this.f != null) {
                        AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(lIIIlIlIIllIIIl, this.f, lIIIlIlIIllIlIl ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    }
                    else {
                        AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(lIIIlIlIIllIIIl, EnumFacing.UP, lIIIlIlIIllIlIl ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    }
                    TargetPlayers.addTargetedPlayer(this.renderEnt.getName());
                }
                this.isActive = false;
            }
        }
    }
    
    public static void setPlayerRotations(final float lIIIlIIIIIllIII, final float lIIIlIIIIIllIIl) {
        AutoCrystal.mc.player.rotationYaw = lIIIlIIIIIllIII;
        AutoCrystal.mc.player.rotationYawHead = lIIIlIIIIIllIII;
        AutoCrystal.mc.player.rotationPitch = lIIIlIIIIIllIIl;
    }
    
    private static float getDamageMultiplied(final float lIIIlIIIIlIIllI) {
        final int lIIIlIIIIlIIlIl = AutoCrystal.mc.world.getDifficulty().getId();
        return lIIIlIIIIlIIllI * ((lIIIlIIIIlIIlIl == 0) ? 0.0f : ((lIIIlIIIIlIIlIl == 2) ? 1.0f : ((lIIIlIIIIlIIlIl == 1) ? 0.5f : 1.5f)));
    }
    
    private static void resetRotation() {
        if (AutoCrystal.isSpoofingAngles) {
            AutoCrystal.yaw = AutoCrystal.mc.player.rotationYaw;
            AutoCrystal.pitch = AutoCrystal.mc.player.rotationPitch;
            AutoCrystal.isSpoofingAngles = false;
        }
    }
    
    public static void updateRotations() {
        AutoCrystal.newYaw = AutoCrystal.mc.player.rotationYaw;
        AutoCrystal.newPitch = AutoCrystal.mc.player.rotationPitch;
    }
    
    private List<BlockPos> findCrystalBlocks() {
        final NonNullList<BlockPos> lIIIlIIlIIIllll = (NonNullList<BlockPos>)NonNullList.create();
        lIIIlIIlIIIllll.addAll((Collection)this.getSphere(getPlayerPos(), this.placeRange.getValue(), this.placeRange.getValue().intValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::canPlaceCrystal).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)lIIIlIIlIIIllll;
    }
    
    private boolean isEatingGap() {
        return AutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemAppleGold && AutoCrystal.mc.player.isHandActive();
    }
    
    @Override
    public void onDisable() {
        Xulu.EVENT_MANAGER.unregister(this);
        PvPInfo.place = false;
        PvPInfo.attack = false;
        this.render = null;
        this.renderEnt = null;
        resetRotation();
        this.isActive = false;
        this.ignoreList.clear();
        this.attemptMap.clear();
        this.retryMap.clear();
        if (this.chat.getValue()) {
            if (this.watermark.getValue()) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.dchatcolor.getValue())).append("AutoCrystal OFF")));
            }
            else {
                Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.dchatcolor.getValue())).append("AutoCrystal OFF")));
            }
        }
    }
    
    @Override
    public void onRender() {
        if (this.render == null || AutoCrystal.mc.player == null) {
            return;
        }
        if (!this.renderBoolean.getValue()) {
            return;
        }
        if (this.renderDamage.getValue()) {
            int lIIIlIlIIIlIlIl = this.espR.getValue();
            int lIIIlIlIIIlIlII = this.espG.getValue();
            int lIIIlIlIIIlIIll = this.espB.getValue();
            if (this.rainbow.getValue()) {
                lIIIlIlIIIlIlIl = RainbowUtils.r;
                lIIIlIlIIIlIlII = RainbowUtils.g;
                lIIIlIlIIIlIIll = RainbowUtils.b;
            }
            final Plane lIIIlIlIIIlIIlI = VectorUtils.toScreen(this.render.getX() + 0.5, this.render.getY() + 0.5, this.render.getZ() + 0.5);
            final float lIIIlIlIIIlIIIl = calculateDamage(this.render.getX() + 0.5, this.render.getY() + 1, this.render.getZ() + 0.5, this.renderEnt);
            final String lIIIlIlIIIlIIII = String.valueOf(MathUtil.round(lIIIlIlIIIlIIIl, 1));
            AutoCrystal.fontRenderer.drawStringWithShadow(lIIIlIlIIIlIIII, (float)lIIIlIlIIIlIIlI.getX() - AutoCrystal.fontRenderer.getStringWidth(lIIIlIlIIIlIIII) / 2, (float)lIIIlIlIIIlIIlI.getY() - AutoCrystal.fontRenderer.FONT_HEIGHT / 2, ((boolean)this.damageWhite.getValue()) ? -1 : new Color(lIIIlIlIIIlIlIl, lIIIlIlIIIlIlII, lIIIlIlIIIlIIll).getRGB());
        }
    }
    
    @EventTarget
    public void onMotionPre(final MotionEvent lIIIlIIIIIlIlII) {
        if (this.rotate.getValue() && this.rotateMode.getValue().equalsIgnoreCase("New")) {
            updateRotations();
            this.doAutoCrystal();
        }
    }
    
    @EventTarget
    public void onSend(final EventSendPacket lIIIIllllIlllll) {
        final Packet lIIIIllllIllllI = lIIIIllllIlllll.getPacket();
        if (lIIIIllllIllllI instanceof CPacketPlayer && this.rotate.getValue() && AutoCrystal.isSpoofingAngles) {
            ((CPacketPlayer)lIIIIllllIllllI).yaw = (float)AutoCrystal.yaw;
            ((CPacketPlayer)lIIIIllllIllllI).pitch = (float)AutoCrystal.pitch;
        }
        if (lIIIIllllIlllll.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            final CPacketPlayerTryUseItemOnBlock lIIIIlllllIIIIl = (CPacketPlayerTryUseItemOnBlock)lIIIIllllIlllll.getPacket();
            if (AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                synchronized (this.placedCrystals) {
                    this.placedCrystals.add(lIIIIlllllIIIIl.getPos());
                }
            }
        }
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(AutoCrystal.mc.player.posX), Math.floor(AutoCrystal.mc.player.posY), Math.floor(AutoCrystal.mc.player.posZ));
    }
    
    public static void restoreRotations() {
        AutoCrystal.mc.player.rotationYaw = AutoCrystal.newYaw;
        AutoCrystal.mc.player.rotationYawHead = AutoCrystal.newYaw;
        AutoCrystal.mc.player.rotationPitch = AutoCrystal.newPitch;
        resetRotation();
    }
    
    public static float calculateDamage(final EntityEnderCrystal lIIIlIIIIIllllI, final Entity lIIIlIIIIIlllIl) {
        return calculateDamage(lIIIlIIIIIllllI.posX, lIIIlIIIIIllllI.posY, lIIIlIIIIIllllI.posZ, lIIIlIIIIIlllIl);
    }
    
    private void lookAtPacket(final double lIIIlIIlIllIllI, final double lIIIlIIlIllIlIl, final double lIIIlIIlIlIllll, final EntityPlayer lIIIlIIlIlIlllI) {
        final double[] lIIIlIIlIllIIlI = calculateLookAt(lIIIlIIlIllIllI, lIIIlIIlIllIlIl, lIIIlIIlIlIllll, lIIIlIIlIlIlllI);
        setYawAndPitch((float)lIIIlIIlIllIIlI[0], (float)lIIIlIIlIllIIlI[1]);
    }
    
    public static double[] calculateLookAt(final double lIIIIllllllIIIl, final double lIIIIlllllIllll, final double lIIIIlllllllIIl, final EntityPlayer lIIIIlllllllIII) {
        double lIIIIllllllIlll = lIIIIlllllllIII.posX - lIIIIllllllIIIl;
        double lIIIIllllllIllI = lIIIIlllllllIII.posY - lIIIIlllllIllll;
        double lIIIIllllllIlIl = lIIIIlllllllIII.posZ - lIIIIlllllllIIl;
        final double lIIIIllllllIlII = Math.sqrt(lIIIIllllllIlll * lIIIIllllllIlll + lIIIIllllllIllI * lIIIIllllllIllI + lIIIIllllllIlIl * lIIIIllllllIlIl);
        lIIIIllllllIlll /= lIIIIllllllIlII;
        lIIIIllllllIllI /= lIIIIllllllIlII;
        lIIIIllllllIlIl /= lIIIIllllllIlII;
        double lIIIIllllllIIll = Math.asin(lIIIIllllllIllI);
        double lIIIIllllllIIlI = Math.atan2(lIIIIllllllIlIl, lIIIIllllllIlll);
        lIIIIllllllIIll = lIIIIllllllIIll * 180.0 / 3.141592653589793;
        lIIIIllllllIIlI = lIIIIllllllIIlI * 180.0 / 3.141592653589793;
        lIIIIllllllIIlI += 90.0;
        return new double[] { lIIIIllllllIIlI, lIIIIllllllIIll };
    }
    
    @EventTarget
    public void onRecieve(final EventReceivePacket lIIIIllllIIllll) {
        if (lIIIIllllIIllll.getPacket() instanceof SPacketSoundEffect && this.nodesync.getValue()) {
            final SPacketSoundEffect lIIIIllllIlIIIl = (SPacketSoundEffect)lIIIIllllIIllll.getPacket();
            if (lIIIIllllIlIIIl.getCategory() == SoundCategory.BLOCKS && lIIIIllllIlIIIl.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                for (final Entity lIIIIllllIlIIlI : Minecraft.getMinecraft().world.loadedEntityList) {
                    if (lIIIIllllIlIIlI instanceof EntityEnderCrystal) {
                        if (lIIIIllllIlIIlI.getDistance(lIIIIllllIlIIIl.getX(), lIIIIllllIlIIIl.getY(), lIIIIllllIlIIIl.getZ()) <= 6.0) {
                            lIIIIllllIlIIlI.setDead();
                        }
                        final SPacketSoundEffect sPacketSoundEffect;
                        this.placedCrystals.removeIf(lIIIIlllIllllIl -> lIIIIlllIllllIl.getDistance((int)sPacketSoundEffect.getX(), (int)sPacketSoundEffect.getY(), (int)sPacketSoundEffect.getZ()) <= 6.0);
                    }
                }
            }
        }
    }
    
    public static float getBlastReduction(final EntityLivingBase lIIIlIIIIlIllll, float lIIIlIIIIlIlllI, final Explosion lIIIlIIIIllIIII) {
        if (lIIIlIIIIlIllll instanceof EntityPlayer) {
            final EntityPlayer lIIIlIIIIllIllI = (EntityPlayer)lIIIlIIIIlIllll;
            final DamageSource lIIIlIIIIllIlIl = DamageSource.causeExplosionDamage(lIIIlIIIIllIIII);
            lIIIlIIIIlIlllI = (CombatRules.getDamageAfterAbsorb((float)(lIIIlIIIIlIlllI ? 1 : 0), (float)lIIIlIIIIllIllI.getTotalArmorValue(), (float)lIIIlIIIIllIllI.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()) != 0.0f);
            final int lIIIlIIIIllIlII = EnchantmentHelper.getEnchantmentModifierDamage(lIIIlIIIIllIllI.getArmorInventoryList(), lIIIlIIIIllIlIl);
            final float lIIIlIIIIllIIll = MathHelper.clamp((float)lIIIlIIIIllIlII, 0.0f, 20.0f);
            lIIIlIIIIlIlllI *= (1.0f - lIIIlIIIIllIIll / 25.0f != 0.0f);
            if (lIIIlIIIIlIllll.isPotionActive(Potion.getPotionById(11))) {
                lIIIlIIIIlIlllI -= ((lIIIlIIIIlIlllI ? 1 : 0) / 4.0f != 0.0f);
            }
            return lIIIlIIIIlIlllI ? 1 : 0;
        }
        lIIIlIIIIlIlllI = (CombatRules.getDamageAfterAbsorb((float)(lIIIlIIIIlIlllI ? 1 : 0), (float)lIIIlIIIIlIllll.getTotalArmorValue(), (float)lIIIlIIIIlIllll.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()) != 0.0f);
        return lIIIlIIIIlIlllI ? 1 : 0;
    }
    
    private boolean isArmorLow(final EntityPlayer lIIIlIIllIIIlIl) {
        if (!this.armor.getValue()) {
            return false;
        }
        for (final ItemStack lIIIlIIllIIIlll : lIIIlIIllIIIlIl.getArmorInventoryList()) {
            final float lIIIlIIllIIlIlI = (lIIIlIIllIIIlll.getMaxDamage() - (float)lIIIlIIllIIIlll.getItemDamage()) / lIIIlIIllIIIlll.getMaxDamage();
            final float lIIIlIIllIIlIIl = 1.0f - lIIIlIIllIIlIlI;
            final int lIIIlIIllIIlIII = 100 - (int)(lIIIlIIllIIlIIl * 100.0f);
            if (lIIIlIIllIIlIII <= this.armorDmg.getValue()) {
                return true;
            }
        }
        return false;
    }
    
    public List<BlockPos> getSphere(final BlockPos lIIIlIIIlllIlll, final float lIIIlIIIllIllII, final int lIIIlIIIlllIlIl, final boolean lIIIlIIIllIlIlI, final boolean lIIIlIIIllIlIIl, final int lIIIlIIIllIlIII) {
        final List<BlockPos> lIIIlIIIlllIIIl = new ArrayList<BlockPos>();
        final int lIIIlIIIlllIIII = lIIIlIIIlllIlll.getX();
        final int lIIIlIIIllIllll = lIIIlIIIlllIlll.getY();
        final int lIIIlIIIllIlllI = lIIIlIIIlllIlll.getZ();
        for (int lIIIlIIIllllIIl = lIIIlIIIlllIIII - (int)lIIIlIIIllIllII; lIIIlIIIllllIIl <= lIIIlIIIlllIIII + lIIIlIIIllIllII; ++lIIIlIIIllllIIl) {
            for (int lIIIlIIIllllIlI = lIIIlIIIllIlllI - (int)lIIIlIIIllIllII; lIIIlIIIllllIlI <= lIIIlIIIllIlllI + lIIIlIIIllIllII; ++lIIIlIIIllllIlI) {
                for (int lIIIlIIIllllIll = lIIIlIIIllIlIIl ? (lIIIlIIIllIllll - (int)lIIIlIIIllIllII) : lIIIlIIIllIllll; lIIIlIIIllllIll < (lIIIlIIIllIlIIl ? (lIIIlIIIllIllll + lIIIlIIIllIllII) : ((float)(lIIIlIIIllIllll + lIIIlIIIlllIlIl))); ++lIIIlIIIllllIll) {
                    final double lIIIlIIIlllllII = (lIIIlIIIlllIIII - lIIIlIIIllllIIl) * (lIIIlIIIlllIIII - lIIIlIIIllllIIl) + (lIIIlIIIllIlllI - lIIIlIIIllllIlI) * (lIIIlIIIllIlllI - lIIIlIIIllllIlI) + (lIIIlIIIllIlIIl ? ((lIIIlIIIllIllll - lIIIlIIIllllIll) * (lIIIlIIIllIllll - lIIIlIIIllllIll)) : 0);
                    if (lIIIlIIIlllllII < lIIIlIIIllIllII * lIIIlIIIllIllII && (!lIIIlIIIllIlIlI || lIIIlIIIlllllII >= (lIIIlIIIllIllII - 1.0f) * (lIIIlIIIllIllII - 1.0f))) {
                        final BlockPos lIIIlIIIlllllIl = new BlockPos(lIIIlIIIllllIIl, lIIIlIIIllllIll + lIIIlIIIllIlIII, lIIIlIIIllllIlI);
                        lIIIlIIIlllIIIl.add(lIIIlIIIlllllIl);
                    }
                }
            }
        }
        return lIIIlIIIlllIIIl;
    }
    
    private boolean canPlaceCrystal(final BlockPos lIIIlIIlIlIIIll) {
        final BlockPos lIIIlIIlIlIIllI = lIIIlIIlIlIIIll.add(0, 1, 0);
        final BlockPos lIIIlIIlIlIIlIl = lIIIlIIlIlIIIll.add(0, 2, 0);
        return (AutoCrystal.mc.world.getBlockState(lIIIlIIlIlIIIll).getBlock() == Blocks.BEDROCK || AutoCrystal.mc.world.getBlockState(lIIIlIIlIlIIIll).getBlock() == Blocks.OBSIDIAN) && AutoCrystal.mc.world.getBlockState(lIIIlIIlIlIIllI).getBlock() == Blocks.AIR && (this.oneHole.getValue() || AutoCrystal.mc.world.getBlockState(lIIIlIIlIlIIlIl).getBlock() == Blocks.AIR) && this.isTrulyEmpty(AutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(lIIIlIIlIlIIllI))) && this.isTrulyEmpty(AutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(lIIIlIIlIlIIlIl)));
    }
    
    private boolean checkCrystal(final Entity lIIIlIIllIllIII) {
        if (lIIIlIIllIllIII == null) {
            return false;
        }
        if (this.noSuicide.getValue()) {
            final double lIIIlIIllIllllI = calculateDamage(lIIIlIIllIllIII.posX, lIIIlIIllIllIII.posY, lIIIlIIllIllIII.posZ, (Entity)AutoCrystal.mc.player);
            if (lIIIlIIllIllllI - 0.5 > AutoCrystal.mc.player.getHealth()) {
                return false;
            }
        }
        final char lIIIlIIllIlIlll = (char)this.explodeMode.getValue();
        String lIIIlIIllIlIllI = (String)(-1);
        switch (((String)lIIIlIIllIlIlll).hashCode()) {
            case 350978074: {
                if (((String)lIIIlIIllIlIlll).equals("OnlyOwn")) {
                    lIIIlIIllIlIllI = (String)0;
                    break;
                }
                break;
            }
            case 65921: {
                if (((String)lIIIlIIllIlIlll).equals("All")) {
                    lIIIlIIllIlIllI = (String)1;
                    break;
                }
                break;
            }
        }
        switch (lIIIlIIllIlIllI) {
            case 0L: {
                if (this.render != null && this.render.getDistance((int)lIIIlIIllIllIII.posX, (int)lIIIlIIllIllIII.posY, (int)lIIIlIIllIllIII.posZ) <= 3.0) {
                    return true;
                }
                if (!this.placedCrystals.isEmpty()) {
                    synchronized (this.placedCrystals) {
                        try {
                            for (final BlockPos lIIIlIIllIlllIl : this.placedCrystals) {
                                if (lIIIlIIllIlllIl.getDistance((int)lIIIlIIllIllIII.posX, (int)lIIIlIIllIllIII.posY, (int)lIIIlIIllIllIII.posZ) <= 3.0) {
                                    return true;
                                }
                            }
                        }
                        catch (Exception lIIIlIIllIlllII) {
                            lIIIlIIllIlllII.printStackTrace();
                        }
                    }
                    break;
                }
                break;
            }
            case 1L: {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void onWorldRender(final RenderEvent lIIIlIIllllIlIl) {
        if (this.render == null || AutoCrystal.mc.player == null) {
            return;
        }
        if (!this.renderBoolean.getValue()) {
            return;
        }
        if (this.renderOld != null && this.renderOld != this.render) {
            this.fadeList.put(this.renderOld, this.espA.getValue());
        }
        int lIIIlIIllllIlII = this.espR.getValue();
        int lIIIlIIllllIIll = this.espG.getValue();
        int lIIIlIIllllIIlI = this.espB.getValue();
        if (this.rainbow.getValue()) {
            lIIIlIIllllIlII = RainbowUtils.r;
            lIIIlIIllllIIll = RainbowUtils.g;
            lIIIlIIllllIIlI = RainbowUtils.b;
        }
        if (this.rendermode.getValue().equalsIgnoreCase("Solid")) {
            XuluTessellator.prepare(7);
            XuluTessellator.drawBox(this.render, lIIIlIIllllIlII, lIIIlIIllllIIll, lIIIlIIllllIIlI, this.espA.getValue(), 63);
            XuluTessellator.release();
        }
        else if (this.rendermode.getValue().equalsIgnoreCase("Outline")) {
            final IBlockState lIIIlIIllllllll = AutoCrystal.mc.world.getBlockState(this.render);
            final Vec3d lIIIlIIlllllllI = MathUtil.interpolateEntity((Entity)AutoCrystal.mc.player, AutoCrystal.mc.getRenderPartialTicks());
            XuluTessellator.drawBoundingBox(lIIIlIIllllllll.getSelectedBoundingBox((World)AutoCrystal.mc.world, this.render).grow(0.0020000000949949026).offset(-lIIIlIIlllllllI.x, -lIIIlIIlllllllI.y, -lIIIlIIlllllllI.z), 1.5f, lIIIlIIllllIlII, lIIIlIIllllIIll, lIIIlIIllllIIlI, this.espA.getValue());
        }
        else if (this.rendermode.getValue().equalsIgnoreCase("Full")) {
            final IBlockState lIIIlIIllllllIl = AutoCrystal.mc.world.getBlockState(this.render);
            final Vec3d lIIIlIIllllllII = MathUtil.interpolateEntity((Entity)AutoCrystal.mc.player, AutoCrystal.mc.getRenderPartialTicks());
            XuluTessellator.drawFullBox(lIIIlIIllllllIl.getSelectedBoundingBox((World)AutoCrystal.mc.world, this.render).grow(0.0020000000949949026).offset(-lIIIlIIllllllII.x, -lIIIlIIllllllII.y, -lIIIlIIllllllII.z), this.render, 1.5f, lIIIlIIllllIlII, lIIIlIIllllIIll, lIIIlIIllllIIlI, this.espA.getValue(), this.espF.getValue());
        }
        if (this.smoothEsp.getValue()) {
            for (final BlockPos lIIIlIIllllIlll : this.fadeList.keySet()) {
                if (this.fadeList.get(lIIIlIIllllIlll) < 0) {
                    this.fadeList.remove(lIIIlIIllllIlll);
                }
                else if (this.rendermode.getValue().equalsIgnoreCase("Solid")) {
                    XuluTessellator.prepare(7);
                    XuluTessellator.drawBox(lIIIlIIllllIlll, lIIIlIIllllIlII, lIIIlIIllllIIll, lIIIlIIllllIIlI, this.fadeList.get(lIIIlIIllllIlll), 63);
                    XuluTessellator.release();
                }
                else if (this.rendermode.getValue().equalsIgnoreCase("Outline")) {
                    final IBlockState lIIIlIIlllllIll = AutoCrystal.mc.world.getBlockState(lIIIlIIllllIlll);
                    final Vec3d lIIIlIIlllllIlI = MathUtil.interpolateEntity((Entity)AutoCrystal.mc.player, AutoCrystal.mc.getRenderPartialTicks());
                    XuluTessellator.drawBoundingBox(lIIIlIIlllllIll.getSelectedBoundingBox((World)AutoCrystal.mc.world, lIIIlIIllllIlll).grow(0.0020000000949949026).offset(-lIIIlIIlllllIlI.x, -lIIIlIIlllllIlI.y, -lIIIlIIlllllIlI.z), 1.5f, lIIIlIIllllIlII, lIIIlIIllllIIll, lIIIlIIllllIIlI, this.fadeList.get(lIIIlIIllllIlll));
                }
                else {
                    if (!this.rendermode.getValue().equalsIgnoreCase("Full")) {
                        continue;
                    }
                    final IBlockState lIIIlIIlllllIIl = AutoCrystal.mc.world.getBlockState(lIIIlIIllllIlll);
                    final Vec3d lIIIlIIlllllIII = MathUtil.interpolateEntity((Entity)AutoCrystal.mc.player, AutoCrystal.mc.getRenderPartialTicks());
                    XuluTessellator.drawFullBox(lIIIlIIlllllIIl.getSelectedBoundingBox((World)AutoCrystal.mc.world, lIIIlIIllllIlll).grow(0.0020000000949949026).offset(-lIIIlIIlllllIII.x, -lIIIlIIlllllIII.y, -lIIIlIIlllllIII.z), this.render, 1.5f, lIIIlIIllllIlII, lIIIlIIllllIIll, lIIIlIIllllIIlI, this.fadeList.get(lIIIlIIllllIlll), this.espF.getValue());
                }
            }
        }
        this.renderOld = this.render;
    }
    
    public AutoCrystal() {
        super("AutoCrystal", "Xulu AutoCrystal", 0, Category.COMBAT, true);
        this.placedCrystals = new ArrayList<BlockPos>();
        this.switchCooldown = false;
        this.isAttacking = false;
        this.oldSlot = -1;
        this.explode = this.register(new Value<Boolean>("Hit", this, true));
        this.explodeMode = this.register(new Value<String>("Hit Mode", this, "All", new String[] { "All", "OnlyOwn" }));
        this.sync = this.register(new Value<Boolean>("Sync Break", this, true));
        this.spam = this.register(new Value<Boolean>("Spam", this, true));
        this.hitAttempts = this.register(new Value<Integer>("Hit Attempts", this, 5, 0, 20));
        this.hitRetryDelay = this.register(new Value<Integer>("Retry Delay", this, 2, 0, 20));
        this.waitTick = this.register(new Value<Integer>("Tick Delay", this, 1, 0, 20));
        this.placeTick = this.register(new Value<Integer>("Place Delay", this, 1, 0, 20));
        this.range = this.register(new Value<Float>("Hit Range", this, 5.0f, 0.0f, 10.0f));
        this.walls = this.register(new Value<Float>("Walls Range", this, 3.5f, 0.0f, 10.0f));
        this.ER = this.register(new Value<Float>("Enemy Range", this, 5.0f, 0.0f, 10.0f));
        this.pre = this.register(new Value<Boolean>("Prioritize Enemies", this, false));
        this.antiWeakness = this.register(new Value<Boolean>("Anti Weakness", this, true));
        this.nodesync = this.register(new Value<Boolean>("No Desync", this, true));
        this.place = this.register(new Value<Boolean>("Place", this, true));
        this.oneHole = this.register(new Value<Boolean>("1.13 Mode", this, false));
        this.noSuicide = this.register(new Value<Boolean>("No Suicide", this, true));
        this.autoSwitch = this.register(new Value<Boolean>("Auto Switch", this, true));
        this.placeRange = this.register(new Value<Float>("Place Range", this, 5.0f, 0.0f, 10.0f));
        this.minDmg = this.register(new Value<Integer>("Min Damage", this, 5, 0, 40));
        this.facePlace = this.register(new Value<Integer>("Faceplace HP", this, 6, 0, 40));
        this.armor = this.register(new Value<Boolean>("Armor Place", this, true));
        this.armorDmg = this.register(new Value<Integer>("Armor %", this, 15, 0, 100));
        this.raytrace = this.register(new Value<Boolean>("Raytrace", this, false));
        this.rotate = this.register(new Value<Boolean>("Rotate", this, true));
        this.rotateMode = this.register(new Value<String>("Rot. Mode", this, "New", new String[] { "Old", "New" })).visibleWhen(lIIIIllIlIIIlII -> this.rotate.getValue());
        this.randRotations = this.register(new Value<Boolean>("Random Rotations", this, true));
        this.fast = this.register(new Value<Boolean>("Fast Mode", this, false));
        this.fastType = this.register(new Value<String>("Fast Type", this, "Instant", new String[] { "Instant", "Ignore" }));
        this.toggleOff = this.register(new Value<Boolean>("Toggle Off", this, false));
        this.toggleHealth = this.register(new Value<Integer>("Toggle Off Health", this, 10, 0, 20));
        this.chat = this.register(new Value<Boolean>("Toggle Msgs", this, true));
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.echatcolor = this.register(new Value<String>("Enable Color", this, "White", ColorTextUtils.colors));
        this.dchatcolor = this.register(new Value<String>("Disable Color", this, "White", ColorTextUtils.colors));
        this.renderDamage = this.register(new Value<Boolean>("Render Damage", this, false));
        this.damageWhite = this.register(new Value<Boolean>("Damage Color White", this, false));
        this.renderBoolean = this.register(new Value<Boolean>("Render", this, true));
        this.rendermode = this.register(new Value<String>("RenderMode", this, "Solid", new ArrayList<String>(Arrays.asList("Solid", "Outline", "Full"))));
        this.rainbow = this.register(new Value<Boolean>("Esp Rainbow", this, false));
        this.espR = this.register(new Value<Integer>("Esp Red", this, 200, 0, 255));
        this.espG = this.register(new Value<Integer>("Esp Green", this, 50, 0, 255));
        this.espB = this.register(new Value<Integer>("Esp Blue", this, 200, 0, 255));
        this.espA = this.register(new Value<Integer>("Esp Alpha", this, 50, 0, 255));
        this.espF = this.register(new Value<Integer>("Full Alpha", this, 80, 0, 255));
        this.maxSelfDmg = this.register(new Value<Integer>("Max Self Dmg", this, 10, 0, 36));
        this.noGappleSwitch = this.register(new Value<Boolean>("No Gap Switch", this, false));
        this.smoothEsp = this.register(new Value<Boolean>("Smooth ESP", this, false));
        this.smoothSpeed = this.register(new Value<Integer>("Smooth Speed", this, 5, 1, 20));
        this.isActive = false;
        this.fadeList = new ConcurrentHashMap<BlockPos, Integer>();
        this.attemptMap = new WeakHashMap<EntityEnderCrystal, Integer>();
        this.retryMap = new WeakHashMap<EntityEnderCrystal, Integer>();
        this.ignoreList = new ArrayList<Entity>();
    }
    
    @Override
    public String getHudInfo() {
        if (this.renderEnt == null) {
            return null;
        }
        if (AutoCrystal.mc.player.getDistance(this.renderEnt) <= this.range.getValue()) {
            return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append(this.renderEnt.getName()));
        }
        return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(this.renderEnt.getName()));
    }
    
    public static float calculateDamage(final double lIIIlIIIlIIlIII, final double lIIIlIIIlIIIllI, final double lIIIlIIIlIIIlIl, final Entity lIIIlIIIlIIIlII) {
        final float lIIIlIIIlIIllll = 12.0f;
        final double lIIIlIIIlIIlllI = lIIIlIIIlIIIlII.getDistance(lIIIlIIIlIIlIII, lIIIlIIIlIIIllI, lIIIlIIIlIIIlIl) / lIIIlIIIlIIllll;
        final Vec3d lIIIlIIIlIIllIl = new Vec3d(lIIIlIIIlIIlIII, lIIIlIIIlIIIllI, lIIIlIIIlIIIlIl);
        final double lIIIlIIIlIIllII = lIIIlIIIlIIIlII.world.getBlockDensity(lIIIlIIIlIIllIl, lIIIlIIIlIIIlII.getEntityBoundingBox());
        final double lIIIlIIIlIIlIll = (1.0 - lIIIlIIIlIIlllI) * lIIIlIIIlIIllII;
        final float lIIIlIIIlIIlIlI = (float)(int)((lIIIlIIIlIIlIll * lIIIlIIIlIIlIll + lIIIlIIIlIIlIll) / 2.0 * 7.0 * lIIIlIIIlIIllll + 1.0);
        double lIIIlIIIlIIlIIl = 1.0;
        if (lIIIlIIIlIIIlII instanceof EntityLivingBase) {
            lIIIlIIIlIIlIIl = getBlastReduction((EntityLivingBase)lIIIlIIIlIIIlII, getDamageMultiplied(lIIIlIIIlIIlIlI), new Explosion((World)AutoCrystal.mc.world, (Entity)null, lIIIlIIIlIIlIII, lIIIlIIIlIIIllI, lIIIlIIIlIIIlIl, 6.0f, false, true));
        }
        return (float)lIIIlIIIlIIlIIl;
    }
    
    @EventTarget
    public void onMotionPost(final MotionEventPost lIIIlIIIIIlIIII) {
        if (this.rotate.getValue() && this.rotateMode.getValue().equalsIgnoreCase("New")) {
            restoreRotations();
        }
    }
    
    private static void setYawAndPitch(final float lIIIlIIIIIIlIII, final float lIIIlIIIIIIlIlI) {
        final Random lIIIlIIIIIIlIIl = new Random(2L);
        AutoCrystal.yaw = lIIIlIIIIIIlIII + (AutoCrystal.isRand ? (lIIIlIIIIIIlIIl.nextFloat() / 100.0f) : 0.0f);
        AutoCrystal.pitch = lIIIlIIIIIIlIlI + (AutoCrystal.isRand ? (lIIIlIIIIIIlIIl.nextFloat() / 100.0f) : 0.0f);
        AutoCrystal.isSpoofingAngles = true;
    }
    
    private boolean isTrulyEmpty(final List<Entity> lIIIlIIlIIllIII) {
        if (lIIIlIIlIIllIII.isEmpty()) {
            return true;
        }
        boolean lIIIlIIlIIllIlI = true;
        for (final Entity lIIIlIIlIIllIll : lIIIlIIlIIllIII) {
            if (!this.ignoreList.contains(lIIIlIIlIIllIll)) {
                lIIIlIIlIIllIlI = false;
                break;
            }
        }
        return lIIIlIIlIIllIlI;
    }
    
    @Override
    public void onEnable() {
        Xulu.EVENT_MANAGER.register(this);
        this.isActive = false;
        if (this.chat.getValue() && AutoCrystal.mc.player != null) {
            if (this.watermark.getValue()) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.echatcolor.getValue())).append("AutoCrystal ON")));
            }
            else {
                Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.echatcolor.getValue())).append("AutoCrystal ON")));
            }
        }
    }
    
    private boolean shouldPause() {
        return (Xulu.MODULE_MANAGER.getModule(Surround.class).isToggled() && Surround.isExposed() && Xulu.MODULE_MANAGER.getModuleT(Surround.class).findObiInHotbar() != -1) || Xulu.MODULE_MANAGER.getModule(AutoTrap.class).isToggled() || Xulu.MODULE_MANAGER.getModule(HoleFill.class).isToggled() || (Xulu.MODULE_MANAGER.getModule(HoleBlocker.class).isToggled() && HoleBlocker.isExposed() && Xulu.MODULE_MANAGER.getModuleT(Surround.class).findObiInHotbar() != -1);
    }
    
    @Override
    public void onUpdate() {
        if (this.rotateMode.getValue().equalsIgnoreCase("Old")) {
            this.doAutoCrystal();
        }
    }
}
