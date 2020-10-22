package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import net.minecraft.network.play.client.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;
import net.minecraft.init.*;
import com.elementars.eclient.util.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import com.elementars.eclient.module.*;

public class Scaffold extends Module
{
    private final /* synthetic */ Value<Integer> delay;
    /* synthetic */ int delayT;
    private /* synthetic */ double yaw;
    private /* synthetic */ BlockPos block;
    private /* synthetic */ boolean isSpoofingAngles;
    private /* synthetic */ double pitch;
    private /* synthetic */ EnumFacing side;
    private /* synthetic */ boolean rotated;
    
    @EventTarget
    public void onSend(final EventSendPacket lllllllllllllllllIlIIIlllllIlIIl) {
        if (lllllllllllllllllIlIIIlllllIlIIl.getPacket() instanceof CPacketPlayer && this.isSpoofingAngles) {
            ((CPacketPlayer)lllllllllllllllllIlIIIlllllIlIIl.getPacket()).yaw = (float)this.yaw;
            ((CPacketPlayer)lllllllllllllllllIlIIIlllllIlIIl.getPacket()).pitch = (float)this.pitch;
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.delayT > 0) {
            --this.delayT;
        }
    }
    
    @EventTarget
    public void onMove(final PlayerMoveEvent lllllllllllllllllIlIIIllllllIIll) {
        if (lllllllllllllllllIlIIIllllllIIll.getEventState() == Event.State.PRE) {
            this.rotated = false;
            this.block = null;
            this.side = null;
            final BlockPos lllllllllllllllllIlIIIllllllIlll = new BlockPos(Scaffold.mc.player.getPositionVector()).add(0, -1, 0);
            if (Scaffold.mc.world.getBlockState(lllllllllllllllllIlIIIllllllIlll).getBlock() == Blocks.AIR) {
                this.setBlockAndFacing(lllllllllllllllllIlIIIllllllIlll);
                if (this.block != null) {
                    final float[] lllllllllllllllllIlIIIlllllllIlI = BlockInteractionHelper.getDirectionToBlock(this.block.getX(), this.block.getY(), this.block.getZ(), this.side);
                    final float lllllllllllllllllIlIIIlllllllIIl = lllllllllllllllllIlIIIlllllllIlI[0];
                    final float lllllllllllllllllIlIIIlllllllIII = Math.min(90.0f, lllllllllllllllllIlIIIlllllllIlI[1] + 9.0f);
                    this.rotated = true;
                    this.yaw = lllllllllllllllllIlIIIlllllllIIl;
                    this.pitch = lllllllllllllllllIlIIIlllllllIII;
                    this.isSpoofingAngles = true;
                }
            }
        }
        if (lllllllllllllllllIlIIIllllllIIll.getEventState() == Event.State.POST && this.block != null && this.delayT == 0 && Scaffold.mc.player.getHeldItemMainhand() != null && Scaffold.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock && Scaffold.mc.playerController.processRightClickBlock(Scaffold.mc.player, Scaffold.mc.world, this.block, this.side, new Vec3d((double)this.block.getX(), (double)this.block.getY(), (double)this.block.getZ()), EnumHand.MAIN_HAND) == EnumActionResult.SUCCESS) {
            this.delayT = this.delay.getValue();
            Scaffold.mc.player.swingArm(EnumHand.MAIN_HAND);
            Scaffold.mc.player.motionX = 0.0;
            Scaffold.mc.player.motionZ = 0.0;
        }
    }
    
    private void setBlockAndFacing(final BlockPos lllllllllllllllllIlIIIlllllIIlIl) {
        if (Scaffold.mc.world.getBlockState(lllllllllllllllllIlIIIlllllIIlIl.add(0, -1, 0)).getBlock() != Blocks.AIR) {
            this.block = lllllllllllllllllIlIIIlllllIIlIl.add(0, -1, 0);
            this.side = EnumFacing.UP;
        }
        else if (Scaffold.mc.world.getBlockState(lllllllllllllllllIlIIIlllllIIlIl.add(-1, 0, 0)).getBlock() != Blocks.AIR) {
            this.block = lllllllllllllllllIlIIIlllllIIlIl.add(-1, 0, 0);
            this.side = EnumFacing.EAST;
        }
        else if (Scaffold.mc.world.getBlockState(lllllllllllllllllIlIIIlllllIIlIl.add(1, 0, 0)).getBlock() != Blocks.AIR) {
            this.block = lllllllllllllllllIlIIIlllllIIlIl.add(1, 0, 0);
            this.side = EnumFacing.WEST;
        }
        else if (Scaffold.mc.world.getBlockState(lllllllllllllllllIlIIIlllllIIlIl.add(0, 0, -1)).getBlock() != Blocks.AIR) {
            this.block = lllllllllllllllllIlIIIlllllIIlIl.add(0, 0, -1);
            this.side = EnumFacing.SOUTH;
        }
        else if (Scaffold.mc.world.getBlockState(lllllllllllllllllIlIIIlllllIIlIl.add(0, 0, 1)).getBlock() != Blocks.AIR) {
            this.block = lllllllllllllllllIlIIIlllllIIlIl.add(0, 0, 1);
            this.side = EnumFacing.NORTH;
        }
        else {
            this.block = null;
            this.side = null;
        }
    }
    
    public Scaffold() {
        super("Scaffold", "Automatically places blocks below you", 0, Category.MOVEMENT, true);
        this.delay = this.register(new Value<Integer>("Delay", this, 0, 0, 20));
    }
}
