package com.elementars.eclient.module.combat;

import java.util.*;
import dev.xulu.settings.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import com.elementars.eclient.module.*;

public class HoleBlocker extends Module
{
    private /* synthetic */ int delayStep;
    private /* synthetic */ boolean firstRun;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Value<Boolean> announceUsage;
    private /* synthetic */ int totalTicksRunning;
    private final /* synthetic */ Value<Integer> timeoutTicks;
    private /* synthetic */ int offsetStep;
    private final /* synthetic */ Value<Boolean> triggerable;
    private final /* synthetic */ Value<Integer> tickDelay;
    private final /* synthetic */ Value<Boolean> rotate;
    /* synthetic */ double oldY;
    private final /* synthetic */ Value<Integer> blocksPerTick;
    private /* synthetic */ int playerHotbarSlot;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Value<Boolean> noGlitchBlocks;
    
    @Override
    public void onEnable() {
        if (HoleBlocker.mc.player == null) {
            this.disable();
            return;
        }
        this.oldY = HoleBlocker.mc.player.posY;
        this.firstRun = true;
        this.playerHotbarSlot = HoleBlocker.mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
        if (this.announceUsage.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN.toString()).append("Enabled!")));
        }
    }
    
    private boolean placeBlock(final BlockPos llllllllllllllllIlIlIIIlIllIlllI) {
        final Block llllllllllllllllIlIlIIIlIllIllIl = HoleBlocker.mc.world.getBlockState(llllllllllllllllIlIlIIIlIllIlllI).getBlock();
        if (!(llllllllllllllllIlIlIIIlIllIllIl instanceof BlockAir) && !(llllllllllllllllIlIlIIIlIllIllIl instanceof BlockLiquid)) {
            return false;
        }
        for (final Entity llllllllllllllllIlIlIIIlIlllIIII : HoleBlocker.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(llllllllllllllllIlIlIIIlIllIlllI))) {
            if (!(llllllllllllllllIlIlIIIlIlllIIII instanceof EntityItem) && !(llllllllllllllllIlIlIIIlIlllIIII instanceof EntityXPOrb)) {
                return false;
            }
        }
        final EnumFacing llllllllllllllllIlIlIIIlIllIllII = BlockInteractionHelper.getPlaceableSide(llllllllllllllllIlIlIIIlIllIlllI);
        if (llllllllllllllllIlIlIIIlIllIllII == null) {
            return false;
        }
        final BlockPos llllllllllllllllIlIlIIIlIllIlIll = llllllllllllllllIlIlIIIlIllIlllI.offset(llllllllllllllllIlIlIIIlIllIllII);
        final EnumFacing llllllllllllllllIlIlIIIlIllIlIlI = llllllllllllllllIlIlIIIlIllIllII.getOpposite();
        if (!BlockInteractionHelper.canBeClicked(llllllllllllllllIlIlIIIlIllIlIll)) {
            return false;
        }
        final Vec3d llllllllllllllllIlIlIIIlIllIlIIl = new Vec3d((Vec3i)llllllllllllllllIlIlIIIlIllIlIll).add(0.5, 0.5, 0.5).add(new Vec3d(llllllllllllllllIlIlIIIlIllIlIlI.getDirectionVec()).scale(0.5));
        final Block llllllllllllllllIlIlIIIlIllIlIII = HoleBlocker.mc.world.getBlockState(llllllllllllllllIlIlIIIlIllIlIll).getBlock();
        final int llllllllllllllllIlIlIIIlIllIIlll = this.findObiInHotbar();
        if (llllllllllllllllIlIlIIIlIllIIlll == -1) {
            this.disable();
        }
        if (this.lastHotbarSlot != llllllllllllllllIlIlIIIlIllIIlll) {
            HoleBlocker.mc.player.inventory.currentItem = llllllllllllllllIlIlIIIlIllIIlll;
            this.lastHotbarSlot = llllllllllllllllIlIlIIIlIllIIlll;
        }
        if ((!this.isSneaking && BlockInteractionHelper.blackList.contains(llllllllllllllllIlIlIIIlIllIlIII)) || BlockInteractionHelper.shulkerList.contains(llllllllllllllllIlIlIIIlIllIlIII)) {
            HoleBlocker.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleBlocker.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.isSneaking = true;
        }
        if (this.rotate.getValue()) {
            BlockInteractionHelper.faceVectorPacketInstant(llllllllllllllllIlIlIIIlIllIlIIl);
        }
        HoleBlocker.mc.playerController.processRightClickBlock(HoleBlocker.mc.player, HoleBlocker.mc.world, llllllllllllllllIlIlIIIlIllIlIll, llllllllllllllllIlIlIIIlIllIlIlI, llllllllllllllllIlIlIIIlIllIlIIl, EnumHand.MAIN_HAND);
        HoleBlocker.mc.player.swingArm(EnumHand.MAIN_HAND);
        HoleBlocker.mc.rightClickDelayTimer = 4;
        if (this.noGlitchBlocks.getValue() && !HoleBlocker.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            HoleBlocker.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, llllllllllllllllIlIlIIIlIllIlIll, llllllllllllllllIlIlIIIlIllIlIlI));
        }
        return true;
    }
    
    @Override
    public void onDisable() {
        if (HoleBlocker.mc.player == null) {
            return;
        }
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            HoleBlocker.mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        if (this.isSneaking) {
            HoleBlocker.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleBlocker.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        if (this.announceUsage.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Disabled!")));
        }
    }
    
    private int findObiInHotbar() {
        int llllllllllllllllIlIlIIIlIlIlIIlI = -1;
        for (int llllllllllllllllIlIlIIIlIlIlIlII = 0; llllllllllllllllIlIlIIIlIlIlIlII < 9; ++llllllllllllllllIlIlIIIlIlIlIlII) {
            final ItemStack llllllllllllllllIlIlIIIlIlIlIllI = HoleBlocker.mc.player.inventory.getStackInSlot(llllllllllllllllIlIlIIIlIlIlIlII);
            if (llllllllllllllllIlIlIIIlIlIlIllI != ItemStack.EMPTY && llllllllllllllllIlIlIIIlIlIlIllI.getItem() instanceof ItemBlock) {
                final Block llllllllllllllllIlIlIIIlIlIlIlll = ((ItemBlock)llllllllllllllllIlIlIIIlIlIlIllI.getItem()).getBlock();
                if (llllllllllllllllIlIlIIIlIlIlIlll instanceof BlockObsidian) {
                    llllllllllllllllIlIlIIIlIlIlIIlI = llllllllllllllllIlIlIIIlIlIlIlII;
                    break;
                }
            }
        }
        return llllllllllllllllIlIlIIIlIlIlIIlI;
    }
    
    public HoleBlocker() {
        super("HoleBlocker", "Blocks others from entering your hole", 0, Category.COMBAT, true);
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
    
    public static boolean isExposed() {
        if (HoleBlocker.mc.world == null) {
            return false;
        }
        final BlockPos llllllllllllllllIlIlIIIlIIllIlII = new BlockPos(HoleBlocker.mc.player.getPositionVector());
        switch (HoleBlocker.mc.player.getHorizontalFacing()) {
            case NORTH: {
                final char llllllllllllllllIlIlIIIlIIllIIlI = (Object)Offsets.NORTH;
                final byte llllllllllllllllIlIlIIIlIIllIIIl = (byte)llllllllllllllllIlIlIIIlIIllIIlI.length;
                for (Exception llllllllllllllllIlIlIIIlIIllIIII = (Exception)0; llllllllllllllllIlIlIIIlIIllIIII < llllllllllllllllIlIlIIIlIIllIIIl; ++llllllllllllllllIlIlIIIlIIllIIII) {
                    final Vec3d llllllllllllllllIlIlIIIlIIlllIII = llllllllllllllllIlIlIIIlIIllIIlI[llllllllllllllllIlIlIIIlIIllIIII];
                    if (HoleBlocker.mc.world.getBlockState(llllllllllllllllIlIlIIIlIIllIlII.add(llllllllllllllllIlIlIIIlIIlllIII.x, llllllllllllllllIlIlIIIlIIlllIII.y, llllllllllllllllIlIlIIIlIIlllIII.z)).getBlock() == Blocks.AIR) {
                        return true;
                    }
                }
                break;
            }
            case SOUTH: {
                final char llllllllllllllllIlIlIIIlIIllIIlI = (Object)Offsets.SOUTH;
                final byte llllllllllllllllIlIlIIIlIIllIIIl = (byte)llllllllllllllllIlIlIIIlIIllIIlI.length;
                for (Exception llllllllllllllllIlIlIIIlIIllIIII = (Exception)0; llllllllllllllllIlIlIIIlIIllIIII < llllllllllllllllIlIlIIIlIIllIIIl; ++llllllllllllllllIlIlIIIlIIllIIII) {
                    final Vec3d llllllllllllllllIlIlIIIlIIllIlll = llllllllllllllllIlIlIIIlIIllIIlI[llllllllllllllllIlIlIIIlIIllIIII];
                    if (HoleBlocker.mc.world.getBlockState(llllllllllllllllIlIlIIIlIIllIlII.add(llllllllllllllllIlIlIIIlIIllIlll.x, llllllllllllllllIlIlIIIlIIllIlll.y, llllllllllllllllIlIlIIIlIIllIlll.z)).getBlock() == Blocks.AIR) {
                        return true;
                    }
                }
                break;
            }
            case EAST: {
                final char llllllllllllllllIlIlIIIlIIllIIlI = (Object)Offsets.EAST;
                final byte llllllllllllllllIlIlIIIlIIllIIIl = (byte)llllllllllllllllIlIlIIIlIIllIIlI.length;
                for (Exception llllllllllllllllIlIlIIIlIIllIIII = (Exception)0; llllllllllllllllIlIlIIIlIIllIIII < llllllllllllllllIlIlIIIlIIllIIIl; ++llllllllllllllllIlIlIIIlIIllIIII) {
                    final Vec3d llllllllllllllllIlIlIIIlIIllIllI = llllllllllllllllIlIlIIIlIIllIIlI[llllllllllllllllIlIlIIIlIIllIIII];
                    if (HoleBlocker.mc.world.getBlockState(llllllllllllllllIlIlIIIlIIllIlII.add(llllllllllllllllIlIlIIIlIIllIllI.x, llllllllllllllllIlIlIIIlIIllIllI.y, llllllllllllllllIlIlIIIlIIllIllI.z)).getBlock() == Blocks.AIR) {
                        return true;
                    }
                }
                break;
            }
            case WEST: {
                final char llllllllllllllllIlIlIIIlIIllIIlI = (Object)Offsets.WEST;
                final byte llllllllllllllllIlIlIIIlIIllIIIl = (byte)llllllllllllllllIlIlIIIlIIllIIlI.length;
                for (Exception llllllllllllllllIlIlIIIlIIllIIII = (Exception)0; llllllllllllllllIlIlIIIlIIllIIII < llllllllllllllllIlIlIIIlIIllIIIl; ++llllllllllllllllIlIlIIIlIIllIIII) {
                    final Vec3d llllllllllllllllIlIlIIIlIIllIlIl = llllllllllllllllIlIlIIIlIIllIIlI[llllllllllllllllIlIlIIIlIIllIIII];
                    if (HoleBlocker.mc.world.getBlockState(llllllllllllllllIlIlIIIlIIllIlII.add(llllllllllllllllIlIlIIIlIIllIlIl.x, llllllllllllllllIlIlIIIlIIllIlIl.y, llllllllllllllllIlIlIIIlIIllIlIl.z)).getBlock() == Blocks.AIR) {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }
    
    @Override
    public void onUpdate() {
        if (HoleBlocker.mc.player == null || ModuleManager.isModuleEnabled("Freecam")) {
            return;
        }
        if (this.triggerable.getValue() && this.totalTicksRunning >= this.timeoutTicks.getValue()) {
            this.totalTicksRunning = 0;
            this.disable();
            return;
        }
        if (HoleBlocker.mc.player.posY != this.oldY) {
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
        int llllllllllllllllIlIlIIIllIIIIIIl = 0;
        while (llllllllllllllllIlIlIIIllIIIIIIl < this.blocksPerTick.getValue()) {
            Vec3d[] llllllllllllllllIlIlIIIllIIIIlll = new Vec3d[0];
            int llllllllllllllllIlIlIIIllIIIIllI = 0;
            final String llllllllllllllllIlIlIIIllIIIIlIl = HoleBlocker.mc.player.getHorizontalFacing().getName().toUpperCase();
            if (llllllllllllllllIlIlIIIllIIIIlIl.equalsIgnoreCase("NORTH")) {
                llllllllllllllllIlIlIIIllIIIIlll = Offsets.NORTH;
                llllllllllllllllIlIlIIIllIIIIllI = Offsets.NORTH.length;
            }
            else if (llllllllllllllllIlIlIIIllIIIIlIl.equalsIgnoreCase("EAST")) {
                llllllllllllllllIlIlIIIllIIIIlll = Offsets.EAST;
                llllllllllllllllIlIlIIIllIIIIllI = Offsets.EAST.length;
            }
            else if (llllllllllllllllIlIlIIIllIIIIlIl.equalsIgnoreCase("SOUTH")) {
                llllllllllllllllIlIlIIIllIIIIlll = Offsets.SOUTH;
                llllllllllllllllIlIlIIIllIIIIllI = Offsets.SOUTH.length;
            }
            else if (llllllllllllllllIlIlIIIllIIIIlIl.equalsIgnoreCase("WEST")) {
                llllllllllllllllIlIlIIIllIIIIlll = Offsets.WEST;
                llllllllllllllllIlIlIIIllIIIIllI = Offsets.WEST.length;
            }
            else {
                llllllllllllllllIlIlIIIllIIIIlll = Offsets.EAST;
                llllllllllllllllIlIlIIIllIIIIllI = Offsets.EAST.length;
            }
            if (this.offsetStep >= llllllllllllllllIlIlIIIllIIIIllI) {
                this.offsetStep = 0;
                break;
            }
            final BlockPos llllllllllllllllIlIlIIIllIIIIlII = new BlockPos(llllllllllllllllIlIlIIIllIIIIlll[this.offsetStep]);
            final BlockPos llllllllllllllllIlIlIIIllIIIIIll = new BlockPos(HoleBlocker.mc.player.getPositionVector()).add(llllllllllllllllIlIlIIIllIIIIlII.x, llllllllllllllllIlIlIIIllIIIIlII.y, llllllllllllllllIlIlIIIllIIIIlII.z);
            if (this.placeBlock(llllllllllllllllIlIlIIIllIIIIIll)) {
                ++llllllllllllllllIlIlIIIllIIIIIIl;
            }
            ++this.offsetStep;
        }
        if (llllllllllllllllIlIlIIIllIIIIIIl > 0) {
            if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
                HoleBlocker.mc.player.inventory.currentItem = this.playerHotbarSlot;
                this.lastHotbarSlot = this.playerHotbarSlot;
            }
            if (this.isSneaking) {
                HoleBlocker.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleBlocker.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.isSneaking = false;
            }
        }
        ++this.totalTicksRunning;
    }
    
    private static class Offsets
    {
        private static final /* synthetic */ Vec3d[] EAST;
        private static final /* synthetic */ Vec3d[] WEST;
        private static final /* synthetic */ Vec3d[] SOUTH;
        private static final /* synthetic */ Vec3d[] NORTH;
        
        static {
            NORTH = new Vec3d[] { new Vec3d(0.0, 1.0, 1.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(0.0, 2.0, 0.0) };
            EAST = new Vec3d[] { new Vec3d(-1.0, 1.0, 0.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 0.0) };
            SOUTH = new Vec3d[] { new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(0.0, 2.0, 0.0) };
            WEST = new Vec3d[] { new Vec3d(1.0, 1.0, 0.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 0.0) };
        }
    }
}
