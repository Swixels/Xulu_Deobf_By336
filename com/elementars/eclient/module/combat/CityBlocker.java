package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import java.util.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.block.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;

public class CityBlocker extends Module
{
    /* synthetic */ double oldY;
    private final /* synthetic */ Value<Boolean> noGlitchBlocks;
    /* synthetic */ String caura;
    private /* synthetic */ int totalTicksRunning;
    private /* synthetic */ int playerHotbarSlot;
    /* synthetic */ boolean hasDisabled;
    private /* synthetic */ boolean firstRun;
    private final /* synthetic */ Value<Integer> timeoutTicks;
    private final /* synthetic */ Value<Boolean> announceUsage;
    private final /* synthetic */ Value<Integer> tickDelay;
    private final /* synthetic */ Value<Boolean> turnOffCauras;
    private /* synthetic */ int offsetStep;
    private final /* synthetic */ Value<Boolean> triggerable;
    /* synthetic */ int cDelay;
    /* synthetic */ boolean isDisabling;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Value<Boolean> rotate;
    private /* synthetic */ int delayStep;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Value<Integer> blocksPerTick;
    
    @Override
    public void onEnable() {
        if (Surround.mc.player == null) {
            this.disable();
            return;
        }
        this.hasDisabled = false;
        this.oldY = CityBlocker.mc.player.posY;
        this.firstRun = true;
        this.playerHotbarSlot = Surround.mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
        if (this.announceUsage.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN.toString()).append("Enabled!")));
        }
    }
    
    public CityBlocker() {
        super("CityBlocker", "Prevents city traps", 0, Category.COMBAT, true);
        this.triggerable = this.register(new Value<Boolean>("Triggerable", this, true));
        this.turnOffCauras = this.register(new Value<Boolean>("Toggle Other Cauras", this, true));
        this.timeoutTicks = this.register(new Value<Integer>("TimeoutTicks", this, 40, 1, 100));
        this.blocksPerTick = this.register(new Value<Integer>("BlocksPerTick", this, 4, 1, 9));
        this.tickDelay = this.register(new Value<Integer>("TickDelay", this, 0, 0, 10));
        this.rotate = this.register(new Value<Boolean>("Rotate", this, true));
        this.noGlitchBlocks = this.register(new Value<Boolean>("NoGlitchBlocks", this, true));
        this.announceUsage = this.register(new Value<Boolean>("AnnounceUsage", this, true));
        this.cDelay = 0;
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        this.offsetStep = 0;
        this.delayStep = 0;
        this.totalTicksRunning = 0;
        this.isSneaking = false;
    }
    
    private int findObiInHotbar() {
        int lllllllllllllllllIIIIlIlIIIIIllI = -1;
        for (int lllllllllllllllllIIIIlIlIIIIlIII = 0; lllllllllllllllllIIIIlIlIIIIlIII < 9; ++lllllllllllllllllIIIIlIlIIIIlIII) {
            final ItemStack lllllllllllllllllIIIIlIlIIIIlIIl = Surround.mc.player.inventory.getStackInSlot(lllllllllllllllllIIIIlIlIIIIlIII);
            if (lllllllllllllllllIIIIlIlIIIIlIIl != ItemStack.EMPTY && lllllllllllllllllIIIIlIlIIIIlIIl.getItem() instanceof ItemBlock) {
                final Block lllllllllllllllllIIIIlIlIIIIlIlI = ((ItemBlock)lllllllllllllllllIIIIlIlIIIIlIIl.getItem()).getBlock();
                if (lllllllllllllllllIIIIlIlIIIIlIlI instanceof BlockObsidian) {
                    lllllllllllllllllIIIIlIlIIIIIllI = lllllllllllllllllIIIIlIlIIIIlIII;
                    break;
                }
            }
        }
        return lllllllllllllllllIIIIlIlIIIIIllI;
    }
    
    @Override
    public void onDisable() {
        if (Surround.mc.player == null) {
            return;
        }
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            Surround.mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        if (this.isSneaking) {
            Surround.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Surround.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        if (this.announceUsage.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Disabled!")));
        }
    }
    
    private boolean placeBlock(final BlockPos lllllllllllllllllIIIIlIlIIIlllll) {
        final Block lllllllllllllllllIIIIlIlIIIllllI = Surround.mc.world.getBlockState(lllllllllllllllllIIIIlIlIIIlllll).getBlock();
        if (!(lllllllllllllllllIIIIlIlIIIllllI instanceof BlockAir) && !(lllllllllllllllllIIIIlIlIIIllllI instanceof BlockLiquid)) {
            return false;
        }
        for (final Entity lllllllllllllllllIIIIlIlIIlIIIIl : Surround.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(lllllllllllllllllIIIIlIlIIIlllll))) {
            if (!(lllllllllllllllllIIIIlIlIIlIIIIl instanceof EntityItem) && !(lllllllllllllllllIIIIlIlIIlIIIIl instanceof EntityXPOrb)) {
                return false;
            }
        }
        final EnumFacing lllllllllllllllllIIIIlIlIIIlllIl = BlockInteractionHelper.getPlaceableSide(lllllllllllllllllIIIIlIlIIIlllll);
        if (lllllllllllllllllIIIIlIlIIIlllIl == null) {
            return false;
        }
        final BlockPos lllllllllllllllllIIIIlIlIIIlllII = lllllllllllllllllIIIIlIlIIIlllll.offset(lllllllllllllllllIIIIlIlIIIlllIl);
        final EnumFacing lllllllllllllllllIIIIlIlIIIllIll = lllllllllllllllllIIIIlIlIIIlllIl.getOpposite();
        if (!BlockInteractionHelper.canBeClicked(lllllllllllllllllIIIIlIlIIIlllII)) {
            return false;
        }
        final Vec3d lllllllllllllllllIIIIlIlIIIllIlI = new Vec3d((Vec3i)lllllllllllllllllIIIIlIlIIIlllII).add(0.5, 0.5, 0.5).add(new Vec3d(lllllllllllllllllIIIIlIlIIIllIll.getDirectionVec()).scale(0.5));
        final Block lllllllllllllllllIIIIlIlIIIllIIl = Surround.mc.world.getBlockState(lllllllllllllllllIIIIlIlIIIlllII).getBlock();
        final int lllllllllllllllllIIIIlIlIIIllIII = this.findObiInHotbar();
        if (lllllllllllllllllIIIIlIlIIIllIII == -1) {
            this.disable();
        }
        if (this.lastHotbarSlot != lllllllllllllllllIIIIlIlIIIllIII) {
            Surround.mc.player.inventory.currentItem = lllllllllllllllllIIIIlIlIIIllIII;
            this.lastHotbarSlot = lllllllllllllllllIIIIlIlIIIllIII;
        }
        if ((!this.isSneaking && BlockInteractionHelper.blackList.contains(lllllllllllllllllIIIIlIlIIIllIIl)) || BlockInteractionHelper.shulkerList.contains(lllllllllllllllllIIIIlIlIIIllIIl)) {
            Surround.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Surround.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.isSneaking = true;
        }
        if (this.rotate.getValue()) {
            BlockInteractionHelper.faceVectorPacketInstant(lllllllllllllllllIIIIlIlIIIllIlI);
        }
        Surround.mc.playerController.processRightClickBlock(Surround.mc.player, Surround.mc.world, lllllllllllllllllIIIIlIlIIIlllII, lllllllllllllllllIIIIlIlIIIllIll, lllllllllllllllllIIIIlIlIIIllIlI, EnumHand.MAIN_HAND);
        Surround.mc.player.swingArm(EnumHand.MAIN_HAND);
        Surround.mc.rightClickDelayTimer = 4;
        if (this.noGlitchBlocks.getValue() && !Surround.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            Surround.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, lllllllllllllllllIIIIlIlIIIlllII, lllllllllllllllllIIIIlIlIIIllIll));
        }
        return true;
    }
    
    @Override
    public void onUpdate() {
        if (this.cDelay > 0) {
            --this.cDelay;
        }
        if (this.cDelay == 0 && this.isDisabling) {
            Xulu.MODULE_MANAGER.getModuleByName(this.caura).toggle();
            this.isDisabling = false;
            this.hasDisabled = true;
        }
        if (Surround.mc.player == null || ModuleManager.isModuleEnabled("Freecam")) {
            return;
        }
        if (Xulu.MODULE_MANAGER.getModuleByName("AutoCrystal") != null && Xulu.MODULE_MANAGER.getModuleByName("AutoCrystal").isToggled() && this.turnOffCauras.getValue() && !this.hasDisabled) {
            this.caura = "AutoCrystal";
            this.cDelay = 19;
            this.isDisabling = true;
            Xulu.MODULE_MANAGER.getModuleByName(this.caura).toggle();
        }
        if (Xulu.MODULE_MANAGER.getModuleByName("AutoCrystalO") != null && Xulu.MODULE_MANAGER.getModuleByName("AutoCrystalO").isToggled() && this.turnOffCauras.getValue() && !this.hasDisabled) {
            this.caura = "AutoCrystalO";
            this.cDelay = 19;
            this.isDisabling = true;
            Xulu.MODULE_MANAGER.getModuleByName(this.caura).toggle();
        }
        if (Xulu.MODULE_MANAGER.getModuleByName("AutoCrystalX") != null && Xulu.MODULE_MANAGER.getModuleByName("AutoCrystalX").isToggled() && this.turnOffCauras.getValue() && !this.hasDisabled) {
            this.caura = "AutoCrystalX";
            this.cDelay = 19;
            this.isDisabling = true;
            Xulu.MODULE_MANAGER.getModuleByName(this.caura).toggle();
        }
        if (this.triggerable.getValue() && this.totalTicksRunning >= this.timeoutTicks.getValue()) {
            this.totalTicksRunning = 0;
            this.disable();
            return;
        }
        if (CityBlocker.mc.player.posY != this.oldY) {
            this.disable();
            return;
        }
        if (!this.firstRun) {
            if (this.delayStep < this.tickDelay.getValue()) {
                ++this.delayStep;
                return;
            }
            this.delayStep = 0;
        }
        if (this.firstRun) {
            this.firstRun = false;
        }
        int lllllllllllllllllIIIIlIlIIllIIIl = 0;
        while (lllllllllllllllllIIIIlIlIIllIIIl < this.blocksPerTick.getValue()) {
            Vec3d[] lllllllllllllllllIIIIlIlIIllIllI = new Vec3d[0];
            int lllllllllllllllllIIIIlIlIIllIlIl = 0;
            lllllllllllllllllIIIIlIlIIllIllI = Offsets.SURROUND;
            lllllllllllllllllIIIIlIlIIllIlIl = Offsets.SURROUND.length;
            if (this.offsetStep >= lllllllllllllllllIIIIlIlIIllIlIl) {
                this.offsetStep = 0;
                break;
            }
            final BlockPos lllllllllllllllllIIIIlIlIIllIlII = new BlockPos(lllllllllllllllllIIIIlIlIIllIllI[this.offsetStep]);
            final BlockPos lllllllllllllllllIIIIlIlIIllIIll = new BlockPos(Surround.mc.player.getPositionVector()).add(lllllllllllllllllIIIIlIlIIllIlII.x, lllllllllllllllllIIIIlIlIIllIlII.y, lllllllllllllllllIIIIlIlIIllIlII.z);
            if (this.placeBlock(lllllllllllllllllIIIIlIlIIllIIll)) {
                ++lllllllllllllllllIIIIlIlIIllIIIl;
            }
            ++this.offsetStep;
        }
        if (lllllllllllllllllIIIIlIlIIllIIIl > 0) {
            if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
                Surround.mc.player.inventory.currentItem = this.playerHotbarSlot;
                this.lastHotbarSlot = this.playerHotbarSlot;
            }
            if (this.isSneaking) {
                Surround.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Surround.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.isSneaking = false;
            }
        }
        ++this.totalTicksRunning;
    }
    
    private enum Mode
    {
        FULL, 
        SURROUND;
    }
    
    private static class Offsets
    {
        private static final /* synthetic */ Vec3d[] SURROUND;
        
        static {
            SURROUND = new Vec3d[] { new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0) };
        }
    }
}
