package com.elementars.eclient.module.combat;

import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import com.elementars.eclient.module.*;

public class FastBow extends Module
{
    @Override
    public void onUpdate() {
        if (FastBow.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow && FastBow.mc.player.isHandActive() && FastBow.mc.player.getItemInUseMaxCount() >= 3) {
            FastBow.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, FastBow.mc.player.getHorizontalFacing()));
            FastBow.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(FastBow.mc.player.getActiveHand()));
            FastBow.mc.player.stopActiveHand();
        }
    }
    
    public FastBow() {
        super("FastBow", "Not a slow bow", 0, Category.COMBAT, true);
    }
}
