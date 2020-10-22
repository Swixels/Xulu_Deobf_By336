package com.elementars.eclient.module.player;

import dev.xulu.settings.*;
import com.elementars.eclient.util.*;
import net.minecraft.init.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class AutoReplenish extends Module
{
    /* synthetic */ Value<Integer> tickdelay;
    private /* synthetic */ int delay_step;
    /* synthetic */ Value<Integer> threshold;
    /* synthetic */ Value<String> mode;
    
    private Pair<Integer, Integer> findReplenishableHotbarSlot() {
        Pair<Integer, Integer> llllllllllllllllllIIlIIllIIllllI = null;
        for (final Map.Entry<Integer, ItemStack> llllllllllllllllllIIlIIllIlIIIII : this.get_hotbar().entrySet()) {
            final ItemStack llllllllllllllllllIIlIIllIlIIIIl = llllllllllllllllllIIlIIllIlIIIII.getValue();
            if (!llllllllllllllllllIIlIIllIlIIIIl.isEmpty) {
                if (llllllllllllllllllIIlIIllIlIIIIl.getItem() == Items.AIR) {
                    continue;
                }
                if (!llllllllllllllllllIIlIIllIlIIIIl.isStackable()) {
                    continue;
                }
                final int llllllllllllllllllIIlIIllIIllIII = (int)this.mode.getValue();
                short llllllllllllllllllIIlIIllIIlIlll = -1;
                switch (((String)llllllllllllllllllIIlIIllIIllIII).hashCode()) {
                    case -1820702691: {
                        if (((String)llllllllllllllllllIIlIIllIIllIII).equals("Crystals")) {
                            llllllllllllllllllIIlIIllIIlIlll = 0;
                            break;
                        }
                        break;
                    }
                    case 2840: {
                        if (((String)llllllllllllllllllIIlIIllIIllIII).equals("Xp")) {
                            llllllllllllllllllIIlIIllIIlIlll = 1;
                            break;
                        }
                        break;
                    }
                    case 2076577: {
                        if (((String)llllllllllllllllllIIlIIllIIllIII).equals("Both")) {
                            llllllllllllllllllIIlIIllIIlIlll = 2;
                            break;
                        }
                        break;
                    }
                }
                switch (llllllllllllllllllIIlIIllIIlIlll) {
                    case 0: {
                        if (llllllllllllllllllIIlIIllIlIIIIl.getItem() != Items.END_CRYSTAL) {
                            continue;
                        }
                        break;
                    }
                    case 1: {
                        if (llllllllllllllllllIIlIIllIlIIIIl.getItem() != Items.EXPERIENCE_BOTTLE) {
                            continue;
                        }
                        break;
                    }
                    case 2: {
                        if (llllllllllllllllllIIlIIllIlIIIIl.getItem() != Items.END_CRYSTAL) {
                            continue;
                        }
                        if (llllllllllllllllllIIlIIllIlIIIIl.getItem() != Items.EXPERIENCE_BOTTLE) {
                            continue;
                        }
                        break;
                    }
                }
                if (llllllllllllllllllIIlIIllIlIIIIl.stackSize >= llllllllllllllllllIIlIIllIlIIIIl.getMaxStackSize()) {
                    continue;
                }
                if (llllllllllllllllllIIlIIllIlIIIIl.stackSize > this.threshold.getValue()) {
                    continue;
                }
                final int llllllllllllllllllIIlIIllIlIIIlI = this.findCompatibleInventorySlot(llllllllllllllllllIIlIIllIlIIIIl);
                if (llllllllllllllllllIIlIIllIlIIIlI == -1) {
                    continue;
                }
                llllllllllllllllllIIlIIllIIllllI = new Pair<Integer, Integer>(llllllllllllllllllIIlIIllIlIIIlI, llllllllllllllllllIIlIIllIlIIIII.getKey());
            }
        }
        return llllllllllllllllllIIlIIllIIllllI;
    }
    
    private Map<Integer, ItemStack> get_inventory() {
        return this.get_inv_slots(9, 35);
    }
    
    private int findCompatibleInventorySlot(final ItemStack llllllllllllllllllIIlIIllIIIlIlI) {
        int llllllllllllllllllIIlIIllIIIlIIl = -1;
        int llllllllllllllllllIIlIIllIIIlIII = 999;
        for (final Map.Entry<Integer, ItemStack> llllllllllllllllllIIlIIllIIIllII : this.get_inventory().entrySet()) {
            final ItemStack llllllllllllllllllIIlIIllIIIllIl = llllllllllllllllllIIlIIllIIIllII.getValue();
            if (!llllllllllllllllllIIlIIllIIIllIl.isEmpty) {
                if (llllllllllllllllllIIlIIllIIIllIl.getItem() == Items.AIR) {
                    continue;
                }
                if (!this.isCompatibleStacks(llllllllllllllllllIIlIIllIIIlIlI, llllllllllllllllllIIlIIllIIIllIl)) {
                    continue;
                }
                final int llllllllllllllllllIIlIIllIIIlllI = ((ItemStack)AutoReplenish.mc.player.inventoryContainer.getInventory().get((int)llllllllllllllllllIIlIIllIIIllII.getKey())).stackSize;
                if (llllllllllllllllllIIlIIllIIIlIII <= llllllllllllllllllIIlIIllIIIlllI) {
                    continue;
                }
                llllllllllllllllllIIlIIllIIIlIII = llllllllllllllllllIIlIIllIIIlllI;
                llllllllllllllllllIIlIIllIIIlIIl = llllllllllllllllllIIlIIllIIIllII.getKey();
            }
        }
        return llllllllllllllllllIIlIIllIIIlIIl;
    }
    
    @Override
    public void onUpdate() {
        if (AutoReplenish.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (this.delay_step < this.tickdelay.getValue()) {
            ++this.delay_step;
            return;
        }
        this.delay_step = 0;
        final Pair<Integer, Integer> llllllllllllllllllIIlIIllIllIIII = this.findReplenishableHotbarSlot();
        if (llllllllllllllllllIIlIIllIllIIII == null) {
            return;
        }
        final int llllllllllllllllllIIlIIllIlIllll = llllllllllllllllllIIlIIllIllIIII.getKey();
        final int llllllllllllllllllIIlIIllIlIlllI = llllllllllllllllllIIlIIllIllIIII.getValue();
        AutoReplenish.mc.playerController.windowClick(0, llllllllllllllllllIIlIIllIlIllll, 0, ClickType.PICKUP, (EntityPlayer)AutoReplenish.mc.player);
        AutoReplenish.mc.playerController.windowClick(0, llllllllllllllllllIIlIIllIlIlllI, 0, ClickType.PICKUP, (EntityPlayer)AutoReplenish.mc.player);
        AutoReplenish.mc.playerController.windowClick(0, llllllllllllllllllIIlIIllIlIllll, 0, ClickType.PICKUP, (EntityPlayer)AutoReplenish.mc.player);
        AutoReplenish.mc.playerController.updateController();
    }
    
    public AutoReplenish() {
        super("AutoReplenish", "Automatically replenishes stacks in the hotbar", 0, Category.PLAYER, true);
        this.mode = this.register(new Value<String>("Mode", this, "All", new String[] { "All", "Crystals", "Xp", "Both" }));
        this.threshold = this.register(new Value<Integer>("Threshold", this, 32, 1, 63));
        this.tickdelay = this.register(new Value<Integer>("Delay", this, 2, 1, 10));
        this.delay_step = 0;
    }
    
    private Map<Integer, ItemStack> get_hotbar() {
        return this.get_inv_slots(36, 44);
    }
    
    private Map<Integer, ItemStack> get_inv_slots(int llllllllllllllllllIIlIIlIllIlIII, final int llllllllllllllllllIIlIIlIllIIlII) {
        final Map<Integer, ItemStack> llllllllllllllllllIIlIIlIllIIllI = new HashMap<Integer, ItemStack>();
        while (llllllllllllllllllIIlIIlIllIlIII <= llllllllllllllllllIIlIIlIllIIlII) {
            llllllllllllllllllIIlIIlIllIIllI.put(llllllllllllllllllIIlIIlIllIlIII, (ItemStack)AutoReplenish.mc.player.inventoryContainer.getInventory().get(llllllllllllllllllIIlIIlIllIlIII));
            ++llllllllllllllllllIIlIIlIllIlIII;
        }
        return llllllllllllllllllIIlIIlIllIIllI;
    }
    
    private boolean isCompatibleStacks(final ItemStack llllllllllllllllllIIlIIlIllllIII, final ItemStack llllllllllllllllllIIlIIlIlllIlll) {
        if (!llllllllllllllllllIIlIIlIllllIII.getItem().equals(llllllllllllllllllIIlIIlIlllIlll.getItem())) {
            return false;
        }
        if (llllllllllllllllllIIlIIlIllllIII.getItem() instanceof ItemBlock && llllllllllllllllllIIlIIlIlllIlll.getItem() instanceof ItemBlock) {
            final Block llllllllllllllllllIIlIIlIllllIll = ((ItemBlock)llllllllllllllllllIIlIIlIllllIII.getItem()).getBlock();
            final Block llllllllllllllllllIIlIIlIllllIlI = ((ItemBlock)llllllllllllllllllIIlIIlIlllIlll.getItem()).getBlock();
            if (!llllllllllllllllllIIlIIlIllllIll.material.equals(llllllllllllllllllIIlIIlIllllIlI.material)) {
                return false;
            }
        }
        return llllllllllllllllllIIlIIlIllllIII.getDisplayName().equals(llllllllllllllllllIIlIIlIlllIlll.getDisplayName()) && llllllllllllllllllIIlIIlIllllIII.getItemDamage() == llllllllllllllllllIIlIIlIlllIlll.getItemDamage();
    }
}
