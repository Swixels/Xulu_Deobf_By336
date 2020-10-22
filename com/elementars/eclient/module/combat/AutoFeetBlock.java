package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import com.elementars.eclient.module.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class AutoFeetBlock extends Module
{
    private final /* synthetic */ Value<Boolean> rotate;
    private final /* synthetic */ Value<Boolean> noGlitchBlocks;
    /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Value<Long> delay;
    /* synthetic */ int playerHotbarSlot;
    /* synthetic */ boolean isSneaking;
    /* synthetic */ Timer timer;
    
    @Override
    public void onEnable() {
        if (AutoFeetBlock.mc.player == null) {
            this.disable();
            return;
        }
        this.playerHotbarSlot = AutoFeetBlock.mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
        AutoFeetBlock.mc.player.jump();
        this.timer.reset();
    }
    
    @Override
    public void onDisable() {
        if (AutoFeetBlock.mc.player == null) {
            return;
        }
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            AutoFeetBlock.mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        if (this.isSneaking) {
            AutoFeetBlock.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoFeetBlock.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
    }
    
    public AutoFeetBlock() {
        super("AutoFeetBlock", "Automatically phases yourself into a block", 0, Category.COMBAT, true);
        this.delay = this.register(new Value<Long>("MS Delay", this, 160L, 0L, 1000L));
        this.rotate = this.register(new Value<Boolean>("Rotate", this, true));
        this.noGlitchBlocks = this.register(new Value<Boolean>("NoGlitchBlocks", this, true));
        this.timer = new Timer();
    }
    
    private boolean placeBlock(final BlockPos lllllllllllllllllIlIlIlIIIIIIIll) {
        final Block lllllllllllllllllIlIlIlIIIIIIIlI = AutoFeetBlock.mc.world.getBlockState(lllllllllllllllllIlIlIlIIIIIIIll).getBlock();
        if (!(lllllllllllllllllIlIlIlIIIIIIIlI instanceof BlockAir) && !(lllllllllllllllllIlIlIlIIIIIIIlI instanceof BlockLiquid)) {
            return false;
        }
        for (final Entity lllllllllllllllllIlIlIlIIIIIIlIl : AutoFeetBlock.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(lllllllllllllllllIlIlIlIIIIIIIll))) {
            if (!(lllllllllllllllllIlIlIlIIIIIIlIl instanceof EntityItem) && !(lllllllllllllllllIlIlIlIIIIIIlIl instanceof EntityXPOrb)) {
                return false;
            }
        }
        final EnumFacing lllllllllllllllllIlIlIlIIIIIIIIl = BlockInteractionHelper.getPlaceableSide(lllllllllllllllllIlIlIlIIIIIIIll);
        if (lllllllllllllllllIlIlIlIIIIIIIIl == null) {
            return false;
        }
        final BlockPos lllllllllllllllllIlIlIlIIIIIIIII = lllllllllllllllllIlIlIlIIIIIIIll.offset(lllllllllllllllllIlIlIlIIIIIIIIl);
        final EnumFacing lllllllllllllllllIlIlIIlllllllll = lllllllllllllllllIlIlIlIIIIIIIIl.getOpposite();
        if (!BlockInteractionHelper.canBeClicked(lllllllllllllllllIlIlIlIIIIIIIII)) {
            return false;
        }
        final Vec3d lllllllllllllllllIlIlIIllllllllI = new Vec3d((Vec3i)lllllllllllllllllIlIlIlIIIIIIIII).add(0.5, 0.5, 0.5).add(new Vec3d(lllllllllllllllllIlIlIIlllllllll.getDirectionVec()).scale(0.5));
        final Block lllllllllllllllllIlIlIIlllllllIl = AutoFeetBlock.mc.world.getBlockState(lllllllllllllllllIlIlIlIIIIIIIII).getBlock();
        final int lllllllllllllllllIlIlIIlllllllII = this.findObiInHotbar();
        if (lllllllllllllllllIlIlIIlllllllII == -1) {
            this.disable();
        }
        if (this.lastHotbarSlot != lllllllllllllllllIlIlIIlllllllII) {
            AutoFeetBlock.mc.player.inventory.currentItem = lllllllllllllllllIlIlIIlllllllII;
            this.lastHotbarSlot = lllllllllllllllllIlIlIIlllllllII;
        }
        if ((!this.isSneaking && BlockInteractionHelper.blackList.contains(lllllllllllllllllIlIlIIlllllllIl)) || BlockInteractionHelper.shulkerList.contains(lllllllllllllllllIlIlIIlllllllIl)) {
            AutoFeetBlock.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoFeetBlock.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.isSneaking = true;
        }
        if (this.rotate.getValue()) {
            BlockInteractionHelper.faceVectorPacketInstant(lllllllllllllllllIlIlIIllllllllI);
        }
        AutoFeetBlock.mc.playerController.processRightClickBlock(AutoFeetBlock.mc.player, AutoFeetBlock.mc.world, lllllllllllllllllIlIlIlIIIIIIIII, lllllllllllllllllIlIlIIlllllllll, lllllllllllllllllIlIlIIllllllllI, EnumHand.MAIN_HAND);
        AutoFeetBlock.mc.player.swingArm(EnumHand.MAIN_HAND);
        AutoFeetBlock.mc.rightClickDelayTimer = 4;
        if (this.noGlitchBlocks.getValue() && !AutoFeetBlock.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            AutoFeetBlock.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, lllllllllllllllllIlIlIlIIIIIIIII, lllllllllllllllllIlIlIIlllllllll));
        }
        return true;
    }
    
    public int findObiInHotbar() {
        int lllllllllllllllllIlIlIIllllIlIlI = -1;
        for (int lllllllllllllllllIlIlIIllllIllII = 0; lllllllllllllllllIlIlIIllllIllII < 9; ++lllllllllllllllllIlIlIIllllIllII) {
            final ItemStack lllllllllllllllllIlIlIIllllIllIl = AutoFeetBlock.mc.player.inventory.getStackInSlot(lllllllllllllllllIlIlIIllllIllII);
            if (lllllllllllllllllIlIlIIllllIllIl != ItemStack.EMPTY && lllllllllllllllllIlIlIIllllIllIl.getItem() instanceof ItemBlock) {
                final Block lllllllllllllllllIlIlIIllllIlllI = ((ItemBlock)lllllllllllllllllIlIlIIllllIllIl.getItem()).getBlock();
                if (lllllllllllllllllIlIlIIllllIlllI instanceof BlockObsidian) {
                    lllllllllllllllllIlIlIIllllIlIlI = lllllllllllllllllIlIlIIllllIllII;
                    break;
                }
            }
        }
        return lllllllllllllllllIlIlIIllllIlIlI;
    }
    
    @Override
    public void onUpdate() {
        if (this.timer.hasReached(this.delay.getValue())) {
            final BlockPos lllllllllllllllllIlIlIlIIIIlIlII = new BlockPos(0, -1, 0);
            final BlockPos lllllllllllllllllIlIlIlIIIIlIIll = new BlockPos(AutoFeetBlock.mc.player.getPositionVector()).add(lllllllllllllllllIlIlIlIIIIlIlII.x, lllllllllllllllllIlIlIlIIIIlIlII.y, lllllllllllllllllIlIlIlIIIIlIlII.z);
            if (this.placeBlock(lllllllllllllllllIlIlIlIIIIlIIll)) {
                if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
                    AutoFeetBlock.mc.player.inventory.currentItem = this.playerHotbarSlot;
                    this.lastHotbarSlot = this.playerHotbarSlot;
                }
                if (this.isSneaking) {
                    AutoFeetBlock.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoFeetBlock.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    this.isSneaking = false;
                }
                AutoFeetBlock.mc.player.onGround = false;
                AutoFeetBlock.mc.player.motionY = 20.0;
            }
            this.disable();
        }
    }
}
