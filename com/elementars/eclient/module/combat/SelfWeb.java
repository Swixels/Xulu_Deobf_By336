package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.block.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import java.util.*;
import com.elementars.eclient.module.*;

public class SelfWeb extends Module
{
    private final /* synthetic */ Value<Integer> blocksPerTick;
    private final /* synthetic */ Value<Integer> tickDelay;
    private final /* synthetic */ Value<Integer> timeoutTicks;
    private /* synthetic */ int totalTicksRunning;
    private final /* synthetic */ Value<Boolean> noGlitchBlocks;
    /* synthetic */ double oldY;
    private final /* synthetic */ Value<Boolean> announceUsage;
    private final /* synthetic */ Value<String> mode;
    private /* synthetic */ int delayStep;
    private final /* synthetic */ Value<Boolean> rotate;
    private /* synthetic */ int playerHotbarSlot;
    private /* synthetic */ boolean isSneaking;
    private /* synthetic */ int offsetStep;
    private /* synthetic */ boolean firstRun;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Value<Boolean> triggerable;
    
    private int findthefuckingwebnigga() {
        int lIlIllIIlIIlIl = -1;
        for (int lIlIllIIlIIlll = 0; lIlIllIIlIIlll < 9; ++lIlIllIIlIIlll) {
            final ItemStack lIlIllIIlIlIII = SelfWeb.mc.player.inventory.getStackInSlot(lIlIllIIlIIlll);
            if (lIlIllIIlIlIII != ItemStack.EMPTY && lIlIllIIlIlIII.getItem() instanceof ItemBlock) {
                final Block lIlIllIIlIlIIl = ((ItemBlock)lIlIllIIlIlIII.getItem()).getBlock();
                if (lIlIllIIlIlIIl == Blocks.WEB) {
                    lIlIllIIlIIlIl = lIlIllIIlIIlll;
                    break;
                }
            }
        }
        return lIlIllIIlIIlIl;
    }
    
    @Override
    public void onEnable() {
        if (SelfWeb.mc.player == null) {
            this.disable();
            return;
        }
        this.oldY = SelfWeb.mc.player.posY;
        this.firstRun = true;
        this.playerHotbarSlot = SelfWeb.mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
        if (this.announceUsage.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN.toString()).append("Enabled!")));
        }
    }
    
    @Override
    public void onDisable() {
        if (SelfWeb.mc.player == null) {
            return;
        }
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            SelfWeb.mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        if (this.isSneaking) {
            SelfWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfWeb.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        if (this.announceUsage.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Disabled!")));
        }
    }
    
    private boolean placeBlock(final BlockPos lIlIllIIlllllI) {
        final Block lIlIllIIllllIl = SelfWeb.mc.world.getBlockState(lIlIllIIlllllI).getBlock();
        if (!(lIlIllIIllllIl instanceof BlockAir) && !(lIlIllIIllllIl instanceof BlockLiquid)) {
            return false;
        }
        for (final Entity lIlIllIlIIIIII : SelfWeb.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)SelfWeb.mc.player, new AxisAlignedBB(lIlIllIIlllllI))) {
            if (!(lIlIllIlIIIIII instanceof EntityItem) && !(lIlIllIlIIIIII instanceof EntityXPOrb)) {
                return false;
            }
        }
        final EnumFacing lIlIllIIllllII = BlockInteractionHelper.getPlaceableSide(lIlIllIIlllllI);
        if (lIlIllIIllllII == null) {
            return false;
        }
        final BlockPos lIlIllIIlllIll = lIlIllIIlllllI.offset(lIlIllIIllllII);
        final EnumFacing lIlIllIIlllIlI = lIlIllIIllllII.getOpposite();
        if (!BlockInteractionHelper.canBeClicked(lIlIllIIlllIll)) {
            return false;
        }
        final Vec3d lIlIllIIlllIIl = new Vec3d((Vec3i)lIlIllIIlllIll).add(0.5, 0.5, 0.5).add(new Vec3d(lIlIllIIlllIlI.getDirectionVec()).scale(0.5));
        final Block lIlIllIIlllIII = SelfWeb.mc.world.getBlockState(lIlIllIIlllIll).getBlock();
        final int lIlIllIIllIlll = this.findthefuckingwebnigga();
        if (lIlIllIIllIlll == -1) {
            this.disable();
        }
        if (this.lastHotbarSlot != lIlIllIIllIlll) {
            SelfWeb.mc.player.inventory.currentItem = lIlIllIIllIlll;
            this.lastHotbarSlot = lIlIllIIllIlll;
        }
        if ((!this.isSneaking && BlockInteractionHelper.blackList.contains(lIlIllIIlllIII)) || BlockInteractionHelper.shulkerList.contains(lIlIllIIlllIII)) {
            SelfWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfWeb.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.isSneaking = true;
        }
        if (this.rotate.getValue()) {
            BlockInteractionHelper.faceVectorPacketInstant(lIlIllIIlllIIl);
        }
        SelfWeb.mc.playerController.processRightClickBlock(SelfWeb.mc.player, SelfWeb.mc.world, lIlIllIIlllIll, lIlIllIIlllIlI, lIlIllIIlllIIl, EnumHand.MAIN_HAND);
        SelfWeb.mc.player.swingArm(EnumHand.MAIN_HAND);
        SelfWeb.mc.rightClickDelayTimer = 4;
        if (this.noGlitchBlocks.getValue() && !SelfWeb.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            SelfWeb.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, lIlIllIIlllIll, lIlIllIIlllIlI));
        }
        return true;
    }
    
    public SelfWeb() {
        super("SelfWeb", "Places webs on yourself", 0, Category.COMBAT, true);
        this.mode = this.register(new Value<String>("Mode", this, "Single", new ArrayList<String>(Arrays.asList("Single", "Double"))));
        this.triggerable = this.register(new Value<Boolean>("Triggerable", this, true));
        this.timeoutTicks = this.register(new Value<Integer>("TimeoutTicks", this, 40, 1, 100));
        this.blocksPerTick = this.register(new Value<Integer>("BlocksPerTick", this, 4, 1, 9));
        this.tickDelay = this.register(new Value<Integer>("TickDelay", this, 0, 0, 10));
        this.rotate = this.register(new Value<Boolean>("Rotate", this, true));
        this.noGlitchBlocks = this.register(new Value<Boolean>("NoGlitchBlocks", this, true));
        this.announceUsage = this.register(new Value<Boolean>("AnnounceUsage", this, true));
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        this.offsetStep = 0;
        this.delayStep = 0;
        this.totalTicksRunning = 0;
        this.isSneaking = false;
    }
    
    @Override
    public void onUpdate() {
        if (SelfWeb.mc.player == null || ModuleManager.isModuleEnabled("Freecam")) {
            return;
        }
        if (this.triggerable.getValue() && this.totalTicksRunning >= this.timeoutTicks.getValue()) {
            this.totalTicksRunning = 0;
            this.disable();
            return;
        }
        if (SelfWeb.mc.player.posY != this.oldY) {
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
        int lIlIllIlIlIIII = 0;
        while (lIlIllIlIlIIII < this.blocksPerTick.getValue()) {
            Vec3d[] lIlIllIlIlIlIl = new Vec3d[0];
            int lIlIllIlIlIlII = 0;
            if (this.mode.getValue().equalsIgnoreCase("Single")) {
                lIlIllIlIlIlIl = Offsets.SINGLE;
                lIlIllIlIlIlII = Offsets.SINGLE.length;
            }
            if (this.mode.getValue().equalsIgnoreCase("Double")) {
                lIlIllIlIlIlIl = Offsets.DOUBLE;
                lIlIllIlIlIlII = Offsets.DOUBLE.length;
            }
            if (this.offsetStep >= lIlIllIlIlIlII) {
                this.offsetStep = 0;
                break;
            }
            final BlockPos lIlIllIlIlIIll = new BlockPos(lIlIllIlIlIlIl[this.offsetStep]);
            final BlockPos lIlIllIlIlIIlI = new BlockPos(SelfWeb.mc.player.getPositionVector()).add(lIlIllIlIlIIll.x, lIlIllIlIlIIll.y, lIlIllIlIlIIll.z);
            if (this.placeBlock(lIlIllIlIlIIlI)) {
                ++lIlIllIlIlIIII;
            }
            ++this.offsetStep;
        }
        if (lIlIllIlIlIIII > 0) {
            if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
                SelfWeb.mc.player.inventory.currentItem = this.playerHotbarSlot;
                this.lastHotbarSlot = this.playerHotbarSlot;
            }
            if (this.isSneaking) {
                SelfWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfWeb.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.isSneaking = false;
            }
        }
        ++this.totalTicksRunning;
    }
    
    private enum Mode
    {
        HoleBlocker, 
        FULL;
    }
    
    private static class Offsets
    {
        private static final /* synthetic */ Vec3d[] DOUBLE;
        private static final /* synthetic */ Vec3d[] SINGLE;
        
        static {
            SINGLE = new Vec3d[] { new Vec3d(0.0, 0.0, 0.0) };
            DOUBLE = new Vec3d[] { new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.0, 1.0, 0.0) };
        }
    }
}
