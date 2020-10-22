package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.item.*;
import com.elementars.eclient.module.*;

public class AutoArmor extends Module
{
    private /* synthetic */ Value<Boolean> pif;
    private /* synthetic */ Timer timer;
    private /* synthetic */ Value<Boolean> preserve;
    private /* synthetic */ int[] bestArmorDamage;
    private /* synthetic */ Value<Integer> ms;
    private /* synthetic */ int[] bestArmorSlots;
    private /* synthetic */ Value<Integer> preserveDMG;
    private /* synthetic */ Value<Boolean> replace;
    
    @Override
    public void onUpdate() {
        if (AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        this.searchSlots();
        for (int llllllllllllllllIlIIllIIlIllllIl = 0; llllllllllllllllIlIIllIIlIllllIl < 4; ++llllllllllllllllIlIIllIIlIllllIl) {
            if (this.bestArmorSlots[llllllllllllllllIlIIllIIlIllllIl] != -1) {
                int llllllllllllllllIlIIllIIlIlllllI = this.bestArmorSlots[llllllllllllllllIlIIllIIlIllllIl];
                if (llllllllllllllllIlIIllIIlIlllllI < 9) {
                    llllllllllllllllIlIIllIIlIlllllI += 36;
                }
                if (!AutoArmor.mc.player.inventory.armorItemInSlot(llllllllllllllllIlIIllIIlIllllIl).isEmpty()) {
                    if (AutoArmor.mc.player.inventory.getFirstEmptyStack() == -1 && !AutoTotem.switchInProgress && !Offhand.switchInProgress && !MiddleClickPearl.switchInProgress && this.pif.getValue()) {
                        AutoArmor.mc.playerController.windowClick(AutoArmor.mc.player.inventoryContainer.windowId, 8 - llllllllllllllllIlIIllIIlIllllIl, 0, ClickType.PICKUP, (EntityPlayer)AutoArmor.mc.player);
                        AutoArmor.mc.playerController.windowClick(AutoArmor.mc.player.inventoryContainer.windowId, llllllllllllllllIlIIllIIlIlllllI, 0, ClickType.PICKUP, (EntityPlayer)AutoArmor.mc.player);
                        AutoArmor.mc.playerController.windowClick(AutoArmor.mc.player.inventoryContainer.windowId, 8 - llllllllllllllllIlIIllIIlIllllIl, 0, ClickType.PICKUP, (EntityPlayer)AutoArmor.mc.player);
                        continue;
                    }
                    AutoArmor.mc.playerController.windowClick(AutoArmor.mc.player.inventoryContainer.windowId, 8 - llllllllllllllllIlIIllIIlIllllIl, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
                    if (!this.timer.hasReached(this.ms.getValue())) {
                        return;
                    }
                }
                AutoArmor.mc.playerController.windowClick(AutoArmor.mc.player.inventoryContainer.windowId, llllllllllllllllIlIIllIIlIlllllI, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
                this.timer.reset();
            }
        }
    }
    
    private void searchSlots() {
        this.bestArmorDamage = new int[4];
        this.bestArmorSlots = new int[4];
        Arrays.fill(this.bestArmorDamage, -1);
        Arrays.fill(this.bestArmorSlots, -1);
        for (int llllllllllllllllIlIIllIIlIlIlllI = 0; llllllllllllllllIlIIllIIlIlIlllI < this.bestArmorSlots.length; ++llllllllllllllllIlIIllIIlIlIlllI) {
            final ItemStack llllllllllllllllIlIIllIIlIlIllll = AutoArmor.mc.player.inventory.armorItemInSlot(llllllllllllllllIlIIllIIlIlIlllI);
            if (llllllllllllllllIlIIllIIlIlIllll.getItem() instanceof ItemArmor) {
                final ItemArmor llllllllllllllllIlIIllIIlIllIIII = (ItemArmor)llllllllllllllllIlIIllIIlIlIllll.getItem();
                if (this.preserve.getValue()) {
                    final float llllllllllllllllIlIIllIIlIllIIlI = (llllllllllllllllIlIIllIIlIlIllll.getMaxDamage() - (float)llllllllllllllllIlIIllIIlIlIllll.getItemDamage()) / llllllllllllllllIlIIllIIlIlIllll.getMaxDamage();
                    final int llllllllllllllllIlIIllIIlIllIIIl = (int)(llllllllllllllllIlIIllIIlIllIIlI * 100.0f);
                    if (llllllllllllllllIlIIllIIlIllIIIl > this.preserveDMG.getValue()) {
                        this.bestArmorDamage[llllllllllllllllIlIIllIIlIlIlllI] = llllllllllllllllIlIIllIIlIllIIII.damageReduceAmount;
                    }
                }
                else {
                    this.bestArmorDamage[llllllllllllllllIlIIllIIlIlIlllI] = llllllllllllllllIlIIllIIlIllIIII.damageReduceAmount;
                }
            }
            else if (llllllllllllllllIlIIllIIlIlIllll.isEmpty() && !this.replace.getValue()) {
                this.bestArmorDamage[llllllllllllllllIlIIllIIlIlIlllI] = Integer.MAX_VALUE;
            }
        }
        for (int llllllllllllllllIlIIllIIlIlIlIlI = 0; llllllllllllllllIlIIllIIlIlIlIlI < 36; ++llllllllllllllllIlIIllIIlIlIlIlI) {
            final ItemStack llllllllllllllllIlIIllIIlIlIlIll = AutoArmor.mc.player.inventory.getStackInSlot(llllllllllllllllIlIIllIIlIlIlIlI);
            if (llllllllllllllllIlIIllIIlIlIlIll.getCount() <= 1) {
                if (llllllllllllllllIlIIllIIlIlIlIll.getItem() instanceof ItemArmor) {
                    final ItemArmor llllllllllllllllIlIIllIIlIlIllIl = (ItemArmor)llllllllllllllllIlIIllIIlIlIlIll.getItem();
                    final int llllllllllllllllIlIIllIIlIlIllII = llllllllllllllllIlIIllIIlIlIllIl.armorType.ordinal() - 2;
                    if (this.bestArmorDamage[llllllllllllllllIlIIllIIlIlIllII] < llllllllllllllllIlIIllIIlIlIllIl.damageReduceAmount) {
                        this.bestArmorDamage[llllllllllllllllIlIIllIIlIlIllII] = llllllllllllllllIlIIllIIlIlIllIl.damageReduceAmount;
                        this.bestArmorSlots[llllllllllllllllIlIIllIIlIlIllII] = llllllllllllllllIlIIllIIlIlIlIlI;
                    }
                }
            }
        }
    }
    
    public AutoArmor() {
        super("AutoArmor", "Automatically refills armor", 0, Category.COMBAT, true);
        this.timer = new Timer();
        this.pif = this.register(new Value<Boolean>("Pickup If Full", this, true));
        this.replace = this.register(new Value<Boolean>("Replace Empty", this, true));
        this.preserve = this.register(new Value<Boolean>("Preserve Damaged", this, false));
        this.preserveDMG = this.register(new Value<Integer>("Damage %", this, 5, 0, 100));
        this.ms = this.register(new Value<Integer>("MS delay", this, 500, 0, 1000));
    }
}
