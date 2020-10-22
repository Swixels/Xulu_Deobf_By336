package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.block.state.*;
import com.elementars.eclient.friend.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class AutoTrap extends Module
{
    private /* synthetic */ boolean firstRun;
    private /* synthetic */ String lastTickTargetName;
    /* synthetic */ String caura;
    private /* synthetic */ int test;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Value<Boolean> noGlitchBlocks;
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<Boolean> activeInFreecam;
    private /* synthetic */ int delayStep;
    private final /* synthetic */ Value<Integer> red;
    private /* synthetic */ int offsetStep;
    private /* synthetic */ int playerHotbarSlot;
    /* synthetic */ boolean isDisabling;
    private final /* synthetic */ Value<Integer> tickDelay;
    private final /* synthetic */ Value<Integer> oalpha;
    private final /* synthetic */ Value<Float> range;
    private /* synthetic */ Set<BlockPos> placeList;
    /* synthetic */ boolean hasDisabled;
    private final /* synthetic */ Value<Boolean> esp;
    /* synthetic */ int cDelay;
    private final /* synthetic */ Value<Boolean> toggleoff;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Boolean> turnOffCauras;
    private final /* synthetic */ Value<String> cage;
    private final /* synthetic */ Value<Integer> blocksPerTick;
    private final /* synthetic */ Value<Boolean> announceUsage;
    private final /* synthetic */ Value<Boolean> rotate;
    private final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<Integer> green;
    private /* synthetic */ EntityPlayer closestTarget;
    
    @Override
    public void onWorldRender(final RenderEvent lllllllllllllllllIllIIlIlIIlllII) {
        if (this.esp.getValue()) {
            final int lllllllllllllllllIllIIlIlIlIIIII = this.red.getValue();
            final int lllllllllllllllllIllIIlIlIIlllll = this.green.getValue();
            final int lllllllllllllllllIllIIlIlIIllllI = this.blue.getValue();
            for (final BlockPos lllllllllllllllllIllIIlIlIlIIIIl : this.placeList) {
                if (this.mode.getValue().equalsIgnoreCase("Solid")) {
                    XuluTessellator.prepare(7);
                    XuluTessellator.drawBox(lllllllllllllllllIllIIlIlIlIIIIl, lllllllllllllllllIllIIlIlIlIIIII, lllllllllllllllllIllIIlIlIIlllll, lllllllllllllllllIllIIlIlIIllllI, this.alpha.getValue(), 63);
                    XuluTessellator.release();
                }
                else if (this.mode.getValue().equalsIgnoreCase("Outline")) {
                    final IBlockState lllllllllllllllllIllIIlIlIlIIlIl = AutoTrap.mc.world.getBlockState(lllllllllllllllllIllIIlIlIlIIIIl);
                    final Vec3d lllllllllllllllllIllIIlIlIlIIlII = MathUtil.interpolateEntity((Entity)AutoTrap.mc.player, AutoTrap.mc.getRenderPartialTicks());
                    XuluTessellator.drawBoundingBox(lllllllllllllllllIllIIlIlIlIIlIl.getSelectedBoundingBox((World)AutoTrap.mc.world, lllllllllllllllllIllIIlIlIlIIIIl).grow(0.0020000000949949026).offset(-lllllllllllllllllIllIIlIlIlIIlII.x, -lllllllllllllllllIllIIlIlIlIIlII.y, -lllllllllllllllllIllIIlIlIlIIlII.z), 1.5f, lllllllllllllllllIllIIlIlIlIIIII, lllllllllllllllllIllIIlIlIIlllll, lllllllllllllllllIllIIlIlIIllllI, this.alpha.getValue());
                }
                else {
                    if (!this.mode.getValue().equalsIgnoreCase("Full")) {
                        continue;
                    }
                    final IBlockState lllllllllllllllllIllIIlIlIlIIIll = AutoTrap.mc.world.getBlockState(lllllllllllllllllIllIIlIlIlIIIIl);
                    final Vec3d lllllllllllllllllIllIIlIlIlIIIlI = MathUtil.interpolateEntity((Entity)AutoTrap.mc.player, AutoTrap.mc.getRenderPartialTicks());
                    XuluTessellator.drawFullBox(lllllllllllllllllIllIIlIlIlIIIll.getSelectedBoundingBox((World)AutoTrap.mc.world, lllllllllllllllllIllIIlIlIlIIIIl).grow(0.0020000000949949026).offset(-lllllllllllllllllIllIIlIlIlIIIlI.x, -lllllllllllllllllIllIIlIlIlIIIlI.y, -lllllllllllllllllIllIIlIlIlIIIlI.z), lllllllllllllllllIllIIlIlIlIIIIl, 1.5f, lllllllllllllllllIllIIlIlIlIIIII, lllllllllllllllllIllIIlIlIIlllll, lllllllllllllllllIllIIlIlIIllllI, this.alpha.getValue(), this.oalpha.getValue());
                }
            }
        }
    }
    
    public AutoTrap() {
        super("AutoTrap", "Automatically traps people", 0, Category.COMBAT, true);
        this.range = this.register(new Value<Float>("Range", this, 4.5f, 3.5f, 32.0f));
        this.blocksPerTick = this.register(new Value<Integer>("BlocksPerTick", this, 2, 1, 23));
        this.tickDelay = this.register(new Value<Integer>("TickDelay", this, 2, 0, 10));
        this.cage = this.register(new Value<String>("Cage", this, "Trap", new ArrayList<String>(Arrays.asList("Trap", "TrapTop", "TrapFullRoof", "TrapFullRoofTop", "Crystalexa", "Crystal", "CrystalFullRoof"))));
        this.rotate = this.register(new Value<Boolean>("Rotate", this, false));
        this.noGlitchBlocks = this.register(new Value<Boolean>("NoGlitchBlocks", this, true));
        this.activeInFreecam = this.register(new Value<Boolean>("ActiveInFreecam", this, true));
        this.announceUsage = this.register(new Value<Boolean>("AnnounceUsage", this, true));
        this.toggleoff = this.register(new Value<Boolean>("Toggle Off", this, false));
        this.turnOffCauras = this.register(new Value<Boolean>("Toggle Other Cauras", this, false));
        this.esp = this.register(new Value<Boolean>("Show esp", this, false));
        this.mode = this.register(new Value<String>("Esp Mode", this, "Solid", new String[] { "Solid", "Outline", "Full" }));
        this.red = this.register(new Value<Integer>("Red", this, 0, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 255, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 255, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 70, 0, 255));
        this.oalpha = this.register(new Value<Integer>("Outline Alpha", this, 70, 0, 255));
        this.placeList = new HashSet<BlockPos>();
        this.cDelay = 0;
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        this.delayStep = 0;
        this.isSneaking = false;
        this.offsetStep = 0;
    }
    
    private void findClosestTarget() {
        final List<EntityPlayer> lllllllllllllllllIllIIlIIlIlIIlI = (List<EntityPlayer>)AutoTrap.mc.world.playerEntities;
        this.closestTarget = null;
        for (final EntityPlayer lllllllllllllllllIllIIlIIlIlIlII : lllllllllllllllllIllIIlIIlIlIIlI) {
            if (lllllllllllllllllIllIIlIIlIlIlII == AutoTrap.mc.player) {
                continue;
            }
            if (Friends.isFriend(lllllllllllllllllIllIIlIIlIlIlII.getName())) {
                continue;
            }
            if (!EntityUtil.isLiving((Entity)lllllllllllllllllIllIIlIIlIlIlII)) {
                continue;
            }
            if (lllllllllllllllllIllIIlIIlIlIlII.getHealth() <= 0.0f) {
                continue;
            }
            if (this.closestTarget == null) {
                this.closestTarget = lllllllllllllllllIllIIlIIlIlIlII;
            }
            else {
                if (AutoTrap.mc.player.getDistance((Entity)lllllllllllllllllIllIIlIIlIlIlII) >= AutoTrap.mc.player.getDistance((Entity)this.closestTarget)) {
                    continue;
                }
                this.closestTarget = lllllllllllllllllIllIIlIIlIlIlII;
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.test = 0;
        if (AutoTrap.mc.player == null) {
            this.disable();
            return;
        }
        this.hasDisabled = false;
        this.firstRun = true;
        this.playerHotbarSlot = AutoTrap.mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
    }
    
    private boolean placeBlockInRange(final BlockPos lllllllllllllllllIllIIlIIllIlllI, final double lllllllllllllllllIllIIlIIlllIlll) {
        final Block lllllllllllllllllIllIIlIIlllIllI = AutoTrap.mc.world.getBlockState(lllllllllllllllllIllIIlIIllIlllI).getBlock();
        if (!(lllllllllllllllllIllIIlIIlllIllI instanceof BlockAir) && !(lllllllllllllllllIllIIlIIlllIllI instanceof BlockLiquid)) {
            this.placeList.remove(lllllllllllllllllIllIIlIIllIlllI);
            return false;
        }
        for (final Entity lllllllllllllllllIllIIlIIllllIlI : AutoTrap.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(lllllllllllllllllIllIIlIIllIlllI))) {
            if (!(lllllllllllllllllIllIIlIIllllIlI instanceof EntityItem) && !(lllllllllllllllllIllIIlIIllllIlI instanceof EntityXPOrb)) {
                return false;
            }
        }
        final EnumFacing lllllllllllllllllIllIIlIIlllIlIl = BlockInteractionHelper.getPlaceableSide(lllllllllllllllllIllIIlIIllIlllI);
        if (lllllllllllllllllIllIIlIIlllIlIl == null) {
            return false;
        }
        final BlockPos lllllllllllllllllIllIIlIIlllIlII = lllllllllllllllllIllIIlIIllIlllI.offset(lllllllllllllllllIllIIlIIlllIlIl);
        final EnumFacing lllllllllllllllllIllIIlIIlllIIll = lllllllllllllllllIllIIlIIlllIlIl.getOpposite();
        if (!BlockInteractionHelper.canBeClicked(lllllllllllllllllIllIIlIIlllIlII)) {
            return false;
        }
        final Vec3d lllllllllllllllllIllIIlIIlllIIlI = new Vec3d((Vec3i)lllllllllllllllllIllIIlIIlllIlII).add(0.5, 0.5, 0.5).add(new Vec3d(lllllllllllllllllIllIIlIIlllIIll.getDirectionVec()).scale(0.5));
        final Block lllllllllllllllllIllIIlIIlllIIIl = AutoTrap.mc.world.getBlockState(lllllllllllllllllIllIIlIIlllIlII).getBlock();
        if (AutoTrap.mc.player.getPositionVector().distanceTo(lllllllllllllllllIllIIlIIlllIIlI) > lllllllllllllllllIllIIlIIlllIlll) {
            return false;
        }
        final int lllllllllllllllllIllIIlIIlllIIII = this.findObiInHotbar();
        if (lllllllllllllllllIllIIlIIlllIIII == -1) {
            this.disable();
        }
        if (this.lastHotbarSlot != lllllllllllllllllIllIIlIIlllIIII) {
            AutoTrap.mc.player.inventory.currentItem = lllllllllllllllllIllIIlIIlllIIII;
            this.lastHotbarSlot = lllllllllllllllllIllIIlIIlllIIII;
        }
        if ((!this.isSneaking && BlockInteractionHelper.blackList.contains(lllllllllllllllllIllIIlIIlllIIIl)) || BlockInteractionHelper.shulkerList.contains(lllllllllllllllllIllIIlIIlllIIIl)) {
            AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoTrap.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.isSneaking = true;
        }
        if (this.rotate.getValue()) {
            BlockInteractionHelper.faceVectorPacketInstant(lllllllllllllllllIllIIlIIlllIIlI);
        }
        AutoTrap.mc.playerController.processRightClickBlock(AutoTrap.mc.player, AutoTrap.mc.world, lllllllllllllllllIllIIlIIlllIlII, lllllllllllllllllIllIIlIIlllIIll, lllllllllllllllllIllIIlIIlllIIlI, EnumHand.MAIN_HAND);
        AutoTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
        AutoTrap.mc.rightClickDelayTimer = 4;
        if (this.noGlitchBlocks.getValue() && !AutoTrap.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, lllllllllllllllllIllIIlIIlllIlII, lllllllllllllllllIllIIlIIlllIIll));
        }
        return true;
    }
    
    @Override
    public String getHudInfo() {
        if (this.closestTarget != null) {
            return this.closestTarget.getName().toUpperCase();
        }
        return null;
    }
    
    @Override
    public void onUpdate() {
        if (this.cDelay > 0) {
            --this.cDelay;
        }
        if (this.cDelay == 0 && this.isDisabling && Xulu.MODULE_MANAGER.getModuleByName(this.caura) != null) {
            Xulu.MODULE_MANAGER.getModuleByName(this.caura).toggle();
            this.isDisabling = false;
            this.hasDisabled = true;
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
        if (this.toggleoff.getValue()) {
            ++this.test;
            if (this.test == 20) {
                super.toggle();
            }
        }
        if (AutoTrap.mc.player == null) {
            return;
        }
        if (!this.activeInFreecam.getValue() && ModuleManager.isModuleEnabled("Freecam")) {
            return;
        }
        if (!this.firstRun) {
            if (this.delayStep < this.tickDelay.getValue()) {
                ++this.delayStep;
                return;
            }
            this.delayStep = 0;
        }
        this.findClosestTarget();
        if (this.closestTarget == null) {
            if (this.firstRun) {
                this.firstRun = false;
                if (this.announceUsage.getValue()) {
                    this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("enabled").append(ChatFormatting.RESET).append(", waiting for target.")));
                }
            }
            return;
        }
        if (this.firstRun) {
            this.firstRun = false;
            this.lastTickTargetName = this.closestTarget.getName();
            if (this.announceUsage.getValue()) {
                this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("enabled").append(ChatFormatting.RESET).append(", target: ").append(this.lastTickTargetName)));
            }
        }
        else if (!this.lastTickTargetName.equals(this.closestTarget.getName())) {
            this.lastTickTargetName = this.closestTarget.getName();
            this.offsetStep = 0;
            if (this.announceUsage.getValue()) {
                this.sendDebugMessage(String.valueOf(new StringBuilder().append("New target: ").append(this.lastTickTargetName)));
            }
        }
        final List<Vec3d> lllllllllllllllllIllIIlIlIIIlIll = new ArrayList<Vec3d>();
        if (this.cage.getValue().equalsIgnoreCase("Trap")) {
            Collections.addAll(lllllllllllllllllIllIIlIlIIIlIll, Offsets.TRAP);
        }
        if (this.cage.getValue().equalsIgnoreCase("TrapTop")) {
            Collections.addAll(lllllllllllllllllIllIIlIlIIIlIll, Offsets.TRAPTOP);
        }
        if (this.cage.getValue().equalsIgnoreCase("TrapFullRoof")) {
            Collections.addAll(lllllllllllllllllIllIIlIlIIIlIll, Offsets.TRAPFULLROOF);
        }
        if (this.cage.getValue().equalsIgnoreCase("TrapFullRoofTop")) {
            Collections.addAll(lllllllllllllllllIllIIlIlIIIlIll, Offsets.TRAPFULLROOFTOP);
        }
        if (this.cage.getValue().equalsIgnoreCase("Crystalexa")) {
            Collections.addAll(lllllllllllllllllIllIIlIlIIIlIll, Offsets.CRYSTALEXA);
        }
        if (this.cage.getValue().equalsIgnoreCase("Crystal")) {
            Collections.addAll(lllllllllllllllllIllIIlIlIIIlIll, Offsets.CRYSTAL);
        }
        if (this.cage.getValue().equalsIgnoreCase("CrystalFullRoof")) {
            Collections.addAll(lllllllllllllllllIllIIlIlIIIlIll, Offsets.CRYSTALFULLROOF);
        }
        int lllllllllllllllllIllIIlIlIIIlIlI = 0;
        while (lllllllllllllllllIllIIlIlIIIlIlI < this.blocksPerTick.getValue()) {
            if (this.offsetStep >= lllllllllllllllllIllIIlIlIIIlIll.size()) {
                this.offsetStep = 0;
                break;
            }
            final BlockPos lllllllllllllllllIllIIlIlIIIlllI = new BlockPos((Vec3d)lllllllllllllllllIllIIlIlIIIlIll.get(this.offsetStep));
            final BlockPos lllllllllllllllllIllIIlIlIIIllIl = new BlockPos(this.closestTarget.getPositionVector()).down().add(lllllllllllllllllIllIIlIlIIIlllI.x, lllllllllllllllllIllIIlIlIIIlllI.y, lllllllllllllllllIllIIlIlIIIlllI.z);
            this.placeList.add(lllllllllllllllllIllIIlIlIIIllIl);
            if (this.placeBlockInRange(lllllllllllllllllIllIIlIlIIIllIl, this.range.getValue())) {
                ++lllllllllllllllllIllIIlIlIIIlIlI;
            }
            ++this.offsetStep;
        }
        if (lllllllllllllllllIllIIlIlIIIlIlI > 0) {
            if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
                AutoTrap.mc.player.inventory.currentItem = this.playerHotbarSlot;
                this.lastHotbarSlot = this.playerHotbarSlot;
            }
            if (this.isSneaking) {
                AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoTrap.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.isSneaking = false;
            }
        }
    }
    
    @Override
    public void onDisable() {
        if (AutoTrap.mc.player == null) {
            return;
        }
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            AutoTrap.mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        if (this.isSneaking) {
            AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoTrap.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        this.placeList.clear();
        if (this.announceUsage.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Disabled!")));
        }
    }
    
    private int findObiInHotbar() {
        int lllllllllllllllllIllIIlIIlIlllIl = -1;
        for (int lllllllllllllllllIllIIlIIlIlllll = 0; lllllllllllllllllIllIIlIIlIlllll < 9; ++lllllllllllllllllIllIIlIIlIlllll) {
            final ItemStack lllllllllllllllllIllIIlIIllIIIII = AutoTrap.mc.player.inventory.getStackInSlot(lllllllllllllllllIllIIlIIlIlllll);
            if (lllllllllllllllllIllIIlIIllIIIII != ItemStack.EMPTY && lllllllllllllllllIllIIlIIllIIIII.getItem() instanceof ItemBlock) {
                final Block lllllllllllllllllIllIIlIIllIIIIl = ((ItemBlock)lllllllllllllllllIllIIlIIllIIIII.getItem()).getBlock();
                if (lllllllllllllllllIllIIlIIllIIIIl instanceof BlockObsidian) {
                    lllllllllllllllllIllIIlIIlIlllIl = lllllllllllllllllIllIIlIIlIlllll;
                    break;
                }
            }
        }
        return lllllllllllllllllIllIIlIIlIlllIl;
    }
    
    private static class Offsets
    {
        private static final /* synthetic */ Vec3d[] TRAP;
        private static final /* synthetic */ Vec3d[] CRYSTAL;
        private static final /* synthetic */ Vec3d[] TRAPFULLROOFTOP;
        private static final /* synthetic */ Vec3d[] CRYSTALEXA;
        private static final /* synthetic */ Vec3d[] TRAPFULLROOF;
        private static final /* synthetic */ Vec3d[] CRYSTALFULLROOF;
        private static final /* synthetic */ Vec3d[] TRAPTOP;
        
        static {
            TRAP = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0) };
            TRAPTOP = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0), new Vec3d(0.0, 4.0, 0.0) };
            TRAPFULLROOF = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
            TRAPFULLROOFTOP = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0), new Vec3d(0.0, 4.0, 0.0) };
            CRYSTALEXA = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(-1.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 1.0), new Vec3d(1.0, 2.0, -1.0), new Vec3d(-1.0, 2.0, 1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0) };
            CRYSTAL = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 1.0), new Vec3d(1.0, 0.0, -1.0), new Vec3d(-1.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 1.0), new Vec3d(-1.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 1.0), new Vec3d(1.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(-1.0, 2.0, 1.0), new Vec3d(1.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0) };
            CRYSTALFULLROOF = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 1.0), new Vec3d(1.0, 0.0, -1.0), new Vec3d(-1.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 1.0), new Vec3d(-1.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 1.0), new Vec3d(1.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(-1.0, 2.0, 1.0), new Vec3d(1.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
        }
    }
    
    private enum Cage
    {
        TRAPFULLROOF, 
        CRYSTALEXA, 
        TRAP, 
        TRAPTOP, 
        CRYSTAL, 
        CRYSTALFULLROOF, 
        TRAPFULLROOFTOP;
    }
}
