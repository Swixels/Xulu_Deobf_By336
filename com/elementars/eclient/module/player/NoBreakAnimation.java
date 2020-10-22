package com.elementars.eclient.module.player;

import net.minecraft.util.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import java.util.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;
import net.minecraft.network.*;

public class NoBreakAnimation extends Module
{
    private /* synthetic */ EnumFacing lastFacing;
    private /* synthetic */ BlockPos lastPos;
    private /* synthetic */ boolean isMining;
    
    @EventTarget
    public void onSend(final EventSendPacket lllllllllllllllllIllllllIlIIllll) {
        if (lllllllllllllllllIllllllIlIIllll.getPacket() instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging lllllllllllllllllIllllllIlIlIlII = (CPacketPlayerDigging)lllllllllllllllllIllllllIlIIllll.getPacket();
            for (final Entity lllllllllllllllllIllllllIlIlIlIl : NoBreakAnimation.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(lllllllllllllllllIllllllIlIlIlII.getPosition()))) {
                if (lllllllllllllllllIllllllIlIlIlIl instanceof EntityEnderCrystal) {
                    this.resetMining();
                    return;
                }
                if (lllllllllllllllllIllllllIlIlIlIl instanceof EntityLivingBase) {
                    this.resetMining();
                    return;
                }
            }
            if (lllllllllllllllllIllllllIlIlIlII.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                this.isMining = true;
                this.setMiningInfo(lllllllllllllllllIllllllIlIlIlII.getPosition(), lllllllllllllllllIllllllIlIlIlII.getFacing());
            }
            if (lllllllllllllllllIllllllIlIlIlII.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                this.resetMining();
            }
        }
    }
    
    public NoBreakAnimation() {
        super("NoBreakAnimation", "Prevents block break animation server side", 0, Category.PLAYER, true);
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
    }
    
    private void setMiningInfo(final BlockPos lllllllllllllllllIllllllIlIIIlII, final EnumFacing lllllllllllllllllIllllllIlIIIIII) {
        this.lastPos = lllllllllllllllllIllllllIlIIIlII;
        this.lastFacing = lllllllllllllllllIllllllIlIIIIII;
    }
    
    @Override
    public void onUpdate() {
        if (!NoBreakAnimation.mc.gameSettings.keyBindAttack.isKeyDown()) {
            this.resetMining();
            return;
        }
        if (this.isMining && this.lastPos != null && this.lastFacing != null) {
            NoBreakAnimation.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFacing));
        }
    }
    
    public void resetMining() {
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
    }
}
