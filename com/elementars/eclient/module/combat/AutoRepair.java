package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import java.util.*;
import com.elementars.eclient.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.inventory.*;

public class AutoRepair extends Module
{
    private /* synthetic */ boolean shouldArmor;
    private /* synthetic */ int mostDamagedSlot;
    private /* synthetic */ int wait;
    private final /* synthetic */ Value<Integer> damage;
    private final /* synthetic */ Value<Integer> delay;
    private /* synthetic */ int mostDamage;
    private /* synthetic */ boolean falg;
    private /* synthetic */ int[] slots;
    private /* synthetic */ int armorCount;
    private /* synthetic */ int counter;
    private /* synthetic */ boolean shouldThrow;
    private /* synthetic */ int lastSlot;
    
    private static Map<Integer, ItemStack> getInventorySlots(int lllllllllllllllllIlIIlIlIlIIllII, final int lllllllllllllllllIlIIlIlIlIIlIll) {
        final Map<Integer, ItemStack> lllllllllllllllllIlIIlIlIlIIllIl = new HashMap<Integer, ItemStack>();
        while (lllllllllllllllllIlIIlIlIlIIllII <= lllllllllllllllllIlIIlIlIlIIlIll) {
            lllllllllllllllllIlIIlIlIlIIllIl.put(lllllllllllllllllIlIIlIlIlIIllII, (ItemStack)AutoRepair.mc.player.inventoryContainer.getInventory().get(lllllllllllllllllIlIIlIlIlIIllII));
            ++lllllllllllllllllIlIIlIlIlIIllII;
        }
        return lllllllllllllllllIlIIlIlIlIIllIl;
    }
    
    @Override
    public void onDisable() {
        if (this.falg) {
            Xulu.MODULE_MANAGER.getModuleByName("EXPFast").toggle();
        }
    }
    
    public boolean isSpace() {
        int lllllllllllllllllIlIIlIlIllIIIII = 0;
        for (final Map.Entry<Integer, ItemStack> lllllllllllllllllIlIIlIlIllIIIlI : getInventory().entrySet()) {
            final ItemStack lllllllllllllllllIlIIlIlIllIIIll = lllllllllllllllllIlIIlIlIllIIIlI.getValue();
            if (lllllllllllllllllIlIIlIlIllIIIll.getItem() == Items.AIR) {
                this.slots[lllllllllllllllllIlIIlIlIllIIIII] = lllllllllllllllllIlIIlIlIllIIIlI.getKey();
                if (++lllllllllllllllllIlIIlIlIllIIIII > 2) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    private static Map<Integer, ItemStack> getInventory() {
        return getInventorySlots(9, 44);
    }
    
    public int findXP() {
        this.lastSlot = AutoRepair.mc.player.inventory.currentItem;
        int lllllllllllllllllIlIIlIlIllIllIl = -1;
        for (int lllllllllllllllllIlIIlIlIllIllll = 0; lllllllllllllllllIlIIlIlIllIllll < 9; ++lllllllllllllllllIlIIlIlIllIllll) {
            final ItemStack lllllllllllllllllIlIIlIlIlllIIII = AutoRepair.mc.player.inventory.getStackInSlot(lllllllllllllllllIlIIlIlIllIllll);
            if (lllllllllllllllllIlIIlIlIlllIIII != ItemStack.EMPTY && lllllllllllllllllIlIIlIlIlllIIII.getItem() instanceof ItemExpBottle) {
                lllllllllllllllllIlIIlIlIllIllIl = lllllllllllllllllIlIIlIlIllIllll;
                break;
            }
        }
        if (lllllllllllllllllIlIIlIlIllIllIl == -1) {
            Command.sendChatMessage("No EXP in hotbar!");
            this.disable();
            return 1;
        }
        return lllllllllllllllllIlIIlIlIllIllIl;
    }
    
    public AutoRepair() {
        super("AutoRepair", "Automatically repairs your armor", 0, Category.COMBAT, true);
        this.delay = this.register(new Value<Integer>("Delay", this, 16, 12, 24));
        this.damage = this.register(new Value<Integer>("Heal Damage %", this, 60, 10, 90));
    }
    
    @Override
    public void onUpdate() {
        if (AutoRepair.mc.player == null || !this.isToggled() || AutoRepair.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (this.shouldThrow) {
            BlockInteractionHelper.lookAtBlock(new BlockPos((Vec3i)AutoRepair.mc.player.getPosition().add(0, -1, 0)));
            AutoRepair.mc.player.inventory.currentItem = this.findXP();
            AutoRepair.mc.playerController.processRightClick((EntityPlayer)AutoRepair.mc.player, (World)AutoRepair.mc.world, EnumHand.MAIN_HAND);
            if (this.isRepaired() || this.counter > 40) {
                this.shouldThrow = false;
                this.shouldArmor = true;
                AutoRepair.mc.player.inventory.currentItem = this.lastSlot;
                Command.sendChatMessage("Finished Repairing");
            }
            ++this.counter;
        }
        if (this.shouldArmor) {
            if (this.wait >= this.delay.getValue()) {
                this.wait = 0;
                AutoRepair.mc.playerController.windowClick(0, this.slots[this.armorCount], 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoRepair.mc.player);
                AutoRepair.mc.playerController.updateController();
                ++this.armorCount;
                if (this.armorCount > 2) {
                    this.armorCount = 0;
                    this.shouldArmor = false;
                    this.disable();
                    return;
                }
            }
            ++this.wait;
        }
    }
    
    public int getMostDamagedSlot() {
        for (final Map.Entry<Integer, ItemStack> lllllllllllllllllIlIIlIllIIIlllI : getArmor().entrySet()) {
            final ItemStack lllllllllllllllllIlIIlIllIIIllll = lllllllllllllllllIlIIlIllIIIlllI.getValue();
            if (lllllllllllllllllIlIIlIllIIIllll.getItemDamage() > this.mostDamage) {
                this.mostDamage = lllllllllllllllllIlIIlIllIIIllll.getItemDamage();
                this.mostDamagedSlot = lllllllllllllllllIlIIlIllIIIlllI.getKey();
            }
        }
        return this.mostDamagedSlot;
    }
    
    @Override
    public void onEnable() {
        this.falg = false;
        this.mostDamage = -1;
        this.mostDamagedSlot = -1;
        this.shouldArmor = false;
        this.armorCount = 0;
        this.slots = new int[3];
        this.wait = 0;
        this.takeOffArmor();
        if (Xulu.MODULE_MANAGER.getModuleByName("EXPFast").isToggled()) {
            this.falg = true;
            Xulu.MODULE_MANAGER.getModuleByName("EXPFast").disable();
        }
    }
    
    public boolean isRepaired() {
        for (final Map.Entry<Integer, ItemStack> lllllllllllllllllIlIIlIlIlllllIl : getArmor().entrySet()) {
            final ItemStack lllllllllllllllllIlIIlIlIllllllI = lllllllllllllllllIlIIlIlIlllllIl.getValue();
            if (lllllllllllllllllIlIIlIlIlllllIl.getKey() == this.mostDamagedSlot) {
                final float lllllllllllllllllIlIIlIllIIIIIIl = this.damage.getValue() / 100.0f;
                final int lllllllllllllllllIlIIlIllIIIIIII = Math.round(lllllllllllllllllIlIIlIlIllllllI.getMaxDamage() * lllllllllllllllllIlIIlIllIIIIIIl);
                final int lllllllllllllllllIlIIlIlIlllllll = lllllllllllllllllIlIIlIlIllllllI.getMaxDamage() - lllllllllllllllllIlIIlIlIllllllI.getItemDamage();
                return lllllllllllllllllIlIIlIllIIIIIII <= lllllllllllllllllIlIIlIlIlllllll;
            }
        }
        return false;
    }
    
    public void takeOffArmor() {
        if (this.isSpace()) {
            this.getMostDamagedSlot();
            if (this.mostDamagedSlot != -1) {
                for (final Map.Entry<Integer, ItemStack> lllllllllllllllllIlIIlIlIlIlIlll : getArmor().entrySet()) {
                    if (lllllllllllllllllIlIIlIlIlIlIlll.getKey() != this.mostDamagedSlot) {
                        AutoRepair.mc.playerController.windowClick(0, (int)lllllllllllllllllIlIIlIlIlIlIlll.getKey(), 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoRepair.mc.player);
                    }
                }
                this.counter = 0;
                this.shouldThrow = true;
                return;
            }
        }
        Command.sendChatMessage("Please ensure there is atleast 3 inv slots open!");
        this.disable();
    }
    
    private static Map<Integer, ItemStack> getArmor() {
        return getInventorySlots(5, 8);
    }
}
