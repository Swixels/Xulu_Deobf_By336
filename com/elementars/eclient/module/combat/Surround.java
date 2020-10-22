package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.init.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class Surround extends Module
{
    private /* synthetic */ boolean firstRun;
    private final /* synthetic */ Value<Integer> blocksPerTick;
    private final /* synthetic */ Value<Boolean> announceUsage;
    private final /* synthetic */ Value<Boolean> rotate;
    /* synthetic */ boolean isDisabling;
    private /* synthetic */ int totalTicksRunning;
    private /* synthetic */ int delayStep;
    /* synthetic */ int cDelay;
    private final /* synthetic */ Value<Integer> tickDelay;
    private /* synthetic */ int offsetStep;
    private final /* synthetic */ Value<Boolean> turnOffCauras;
    /* synthetic */ String caura;
    /* synthetic */ double oldY;
    private final /* synthetic */ Value<Boolean> triggerable;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Value<Boolean> noGlitchBlocks;
    private /* synthetic */ int lastHotbarSlot;
    private /* synthetic */ int playerHotbarSlot;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Integer> timeoutTicks;
    /* synthetic */ boolean hasDisabled;
    
    public int findObiInHotbar() {
        int llIIIllIllIIII = -1;
        for (int llIIIllIllIIlI = 0; llIIIllIllIIlI < 9; ++llIIIllIllIIlI) {
            final ItemStack llIIIllIllIIll = Surround.mc.player.inventory.getStackInSlot(llIIIllIllIIlI);
            if (llIIIllIllIIll != ItemStack.EMPTY && llIIIllIllIIll.getItem() instanceof ItemBlock) {
                final Block llIIIllIllIlII = ((ItemBlock)llIIIllIllIIll.getItem()).getBlock();
                if (llIIIllIllIlII instanceof BlockObsidian) {
                    llIIIllIllIIII = llIIIllIllIIlI;
                    break;
                }
            }
        }
        return llIIIllIllIIII;
    }
    
    private boolean placeBlock(final BlockPos llIIIlllIIIIII) {
        final Block llIIIlllIIlIII = Surround.mc.world.getBlockState(llIIIlllIIIIII).getBlock();
        if (!(llIIIlllIIlIII instanceof BlockAir) && !(llIIIlllIIlIII instanceof BlockLiquid)) {
            return false;
        }
        for (final Entity llIIIlllIIlIll : Surround.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(llIIIlllIIIIII))) {
            if (!(llIIIlllIIlIll instanceof EntityItem) && !(llIIIlllIIlIll instanceof EntityXPOrb)) {
                return false;
            }
        }
        final EnumFacing llIIIlllIIIlll = BlockInteractionHelper.getPlaceableSide(llIIIlllIIIIII);
        if (llIIIlllIIIlll == null) {
            return false;
        }
        final BlockPos llIIIlllIIIllI = llIIIlllIIIIII.offset(llIIIlllIIIlll);
        final EnumFacing llIIIlllIIIlIl = llIIIlllIIIlll.getOpposite();
        if (!BlockInteractionHelper.canBeClicked(llIIIlllIIIllI)) {
            return false;
        }
        final Vec3d llIIIlllIIIlII = new Vec3d((Vec3i)llIIIlllIIIllI).add(0.5, 0.5, 0.5).add(new Vec3d(llIIIlllIIIlIl.getDirectionVec()).scale(0.5));
        final Block llIIIlllIIIIll = Surround.mc.world.getBlockState(llIIIlllIIIllI).getBlock();
        final int llIIIlllIIIIlI = this.findObiInHotbar();
        if (llIIIlllIIIIlI == -1) {
            this.disable();
        }
        if (this.lastHotbarSlot != llIIIlllIIIIlI) {
            Surround.mc.player.inventory.currentItem = llIIIlllIIIIlI;
            this.lastHotbarSlot = llIIIlllIIIIlI;
        }
        if ((!this.isSneaking && BlockInteractionHelper.blackList.contains(llIIIlllIIIIll)) || BlockInteractionHelper.shulkerList.contains(llIIIlllIIIIll)) {
            Surround.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Surround.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.isSneaking = true;
        }
        if (this.rotate.getValue()) {
            BlockInteractionHelper.faceVectorPacketInstant(llIIIlllIIIlII);
        }
        Surround.mc.playerController.processRightClickBlock(Surround.mc.player, Surround.mc.world, llIIIlllIIIllI, llIIIlllIIIlIl, llIIIlllIIIlII, EnumHand.MAIN_HAND);
        Surround.mc.player.swingArm(EnumHand.MAIN_HAND);
        Surround.mc.rightClickDelayTimer = 4;
        if (this.noGlitchBlocks.getValue() && !Surround.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            Surround.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, llIIIlllIIIllI, llIIIlllIIIlIl));
        }
        return true;
    }
    
    @Override
    public void onEnable() {
        if (Surround.mc.player == null) {
            this.disable();
            return;
        }
        this.hasDisabled = false;
        this.oldY = Surround.mc.player.posY;
        this.firstRun = true;
        this.playerHotbarSlot = Surround.mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
        if (this.announceUsage.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN.toString()).append("Enabled!")));
        }
    }
    
    public static boolean isExposed() {
        if (Surround.mc.world == null) {
            return false;
        }
        final BlockPos llIIIllIlIlIlI = new BlockPos(Surround.mc.player.getPositionVector());
        return Surround.mc.world.getBlockState(llIIIllIlIlIlI.add(1, 0, 0)).getBlock() == Blocks.AIR || Surround.mc.world.getBlockState(llIIIllIlIlIlI.add(-1, 0, 0)).getBlock() == Blocks.AIR || Surround.mc.world.getBlockState(llIIIllIlIlIlI.add(0, 0, 1)).getBlock() == Blocks.AIR || Surround.mc.world.getBlockState(llIIIllIlIlIlI.add(0, 0, -1)).getBlock() == Blocks.AIR || Surround.mc.world.getBlockState(llIIIllIlIlIlI.add(0, -1, 0)).getBlock() == Blocks.AIR;
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
        if (Surround.mc.player.posY != this.oldY) {
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
        int llIIIlllIllIll = 0;
        while (llIIIlllIllIll < this.blocksPerTick.getValue()) {
            Vec3d[] llIIIllllIIIII = new Vec3d[0];
            int llIIIlllIlllll = 0;
            if (this.mode.getValue().equalsIgnoreCase("Full")) {
                llIIIllllIIIII = Offsets.FULL;
                llIIIlllIlllll = Offsets.FULL.length;
            }
            if (this.mode.getValue().equalsIgnoreCase("Surround")) {
                llIIIllllIIIII = Offsets.SURROUND;
                llIIIlllIlllll = Offsets.SURROUND.length;
            }
            if (this.mode.getValue().equalsIgnoreCase("Double")) {
                llIIIllllIIIII = Offsets.DOUBLE;
                llIIIlllIlllll = Offsets.DOUBLE.length;
            }
            if (this.mode.getValue().equalsIgnoreCase("FullCity")) {
                llIIIllllIIIII = Offsets.FULLCITY;
                llIIIlllIlllll = Offsets.FULLCITY.length;
            }
            if (this.mode.getValue().equalsIgnoreCase("SurroundCity")) {
                llIIIllllIIIII = Offsets.SURROUNDCITY;
                llIIIlllIlllll = Offsets.SURROUNDCITY.length;
            }
            if (this.offsetStep >= llIIIlllIlllll) {
                this.offsetStep = 0;
                break;
            }
            final BlockPos llIIIlllIllllI = new BlockPos(llIIIllllIIIII[this.offsetStep]);
            final BlockPos llIIIlllIlllIl = new BlockPos(Surround.mc.player.getPositionVector()).add(llIIIlllIllllI.x, llIIIlllIllllI.y, llIIIlllIllllI.z);
            if (this.placeBlock(llIIIlllIlllIl)) {
                ++llIIIlllIllIll;
            }
            ++this.offsetStep;
        }
        if (llIIIlllIllIll > 0) {
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
    
    public Surround() {
        super("Surround", "Surrounds you with obby", 0, Category.COMBAT, true);
        this.mode = this.register(new Value<String>("Mode", this, "Full", new ArrayList<String>(Arrays.asList("Full", "Surround", "Double", "FullCity", "SurroundCity"))));
        this.triggerable = this.register(new Value<Boolean>("Triggerable", this, true));
        this.turnOffCauras = this.register(new Value<Boolean>("Toggle Other Cauras", this, false));
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
    
    private static class Offsets
    {
        private static final /* synthetic */ Vec3d[] SURROUND;
        private static final /* synthetic */ Vec3d[] DOUBLE;
        private static final /* synthetic */ Vec3d[] FULL;
        private static final /* synthetic */ Vec3d[] SURROUNDCITY;
        private static final /* synthetic */ Vec3d[] FULLCITY;
        
        static {
            SURROUND = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0) };
            DOUBLE = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, -1.0) };
            FULL = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(0.0, -1.0, 0.0) };
            SURROUNDCITY = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0) };
            FULLCITY = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(0.0, -1.0, 0.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0) };
        }
    }
    
    private enum Mode
    {
        FULL, 
        SURROUND;
    }
}
