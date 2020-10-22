package com.elementars.eclient.module.misc;

import java.util.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.module.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class HopperNuker extends Module
{
    public /* synthetic */ ArrayList<BlockPos> hoppers;
    /* synthetic */ int pickaxeSlot;
    
    public HopperNuker() {
        super("HopperNuker", "Nuker for hoppers", 0, Category.MISC, true);
        this.hoppers = new ArrayList<BlockPos>();
    }
    
    @Override
    public void onUpdate() {
        if (this.isToggled()) {
            final Iterable<BlockPos> lllllllllllllllllllIlIlIIIIIIIII = (Iterable<BlockPos>)BlockPos.getAllInBox(HopperNuker.mc.player.getPosition().add(-5, -5, -5), HopperNuker.mc.player.getPosition().add(5, 5, 5));
            for (final BlockPos lllllllllllllllllllIlIlIIIIIIIIl : lllllllllllllllllllIlIlIIIIIIIII) {
                if (HopperNuker.mc.world.getBlockState(lllllllllllllllllllIlIlIIIIIIIIl).getBlock() == Blocks.HOPPER) {
                    this.pickaxeSlot = -1;
                    for (int lllllllllllllllllllIlIlIIIIIIIlI = 0; lllllllllllllllllllIlIlIIIIIIIlI < 9 && this.pickaxeSlot == -1; ++lllllllllllllllllllIlIlIIIIIIIlI) {
                        final ItemStack lllllllllllllllllllIlIlIIIIIIlII = HopperNuker.mc.player.inventory.getStackInSlot(lllllllllllllllllllIlIlIIIIIIIlI);
                        if (lllllllllllllllllllIlIlIIIIIIlII != ItemStack.EMPTY) {
                            if (lllllllllllllllllllIlIlIIIIIIlII.getItem() instanceof ItemPickaxe) {
                                final ItemPickaxe lllllllllllllllllllIlIlIIIIIIIll = (ItemPickaxe)lllllllllllllllllllIlIlIIIIIIlII.getItem();
                                this.pickaxeSlot = lllllllllllllllllllIlIlIIIIIIIlI;
                            }
                        }
                    }
                    if (this.pickaxeSlot != -1) {
                        HopperNuker.mc.player.inventory.currentItem = this.pickaxeSlot;
                    }
                    HopperNuker.mc.playerController.onPlayerDamageBlock(lllllllllllllllllllIlIlIIIIIIIIl, HopperNuker.mc.player.getHorizontalFacing());
                    HopperNuker.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }
}
