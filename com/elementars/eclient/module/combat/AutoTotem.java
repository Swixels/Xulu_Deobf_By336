package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.nbt.*;
import java.util.*;
import net.minecraft.client.gui.inventory.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.*;
import net.minecraft.inventory.*;

public class AutoTotem extends Module
{
    /* synthetic */ int switchDelay1;
    /* synthetic */ int slot;
    public final /* synthetic */ Value<Integer> delay;
    public static /* synthetic */ int offhand_delay;
    public final /* synthetic */ Value<Integer> health;
    /* synthetic */ int switchDelay2;
    /* synthetic */ boolean didFirst;
    public final /* synthetic */ Value<Integer> delayA;
    public static /* synthetic */ boolean switchInProgress;
    
    public static boolean isFullArmor(final EntityPlayer llllllllllllllllIlIlIIlIllllllIl) {
        boolean llllllllllllllllIlIlIIlIllllllII = true;
        int llllllllllllllllIlIlIIlIlllllIll = 0;
        boolean llllllllllllllllIlIlIIlIlllllIlI = false;
        for (final ItemStack llllllllllllllllIlIlIIlIlllllllI : llllllllllllllllIlIlIIlIllllllIl.getArmorInventoryList()) {
            if (llllllllllllllllIlIlIIlIlllllllI.isEmpty()) {
                llllllllllllllllIlIlIIlIllllllII = false;
                break;
            }
            if (llllllllllllllllIlIlIIlIlllllllI.getItem() == Items.DIAMOND_HELMET) {
                ++llllllllllllllllIlIlIIlIlllllIll;
            }
            if (llllllllllllllllIlIlIIlIlllllllI.getItem() == Items.DIAMOND_CHESTPLATE) {
                ++llllllllllllllllIlIlIIlIlllllIll;
            }
            if (llllllllllllllllIlIlIIlIlllllllI.getItem() == Items.DIAMOND_LEGGINGS) {
                ++llllllllllllllllIlIlIIlIlllllIll;
            }
            if (llllllllllllllllIlIlIIlIlllllllI.getItem() == Items.DIAMOND_BOOTS) {
                ++llllllllllllllllIlIlIIlIlllllIll;
            }
            final NBTTagList llllllllllllllllIlIlIIllIIIIIIII = llllllllllllllllIlIlIIlIlllllllI.getEnchantmentTagList();
            final List<String> llllllllllllllllIlIlIIlIllllllll = new ArrayList<String>();
            if (llllllllllllllllIlIlIIllIIIIIIII != null) {
                for (int llllllllllllllllIlIlIIllIIIIIIIl = 0; llllllllllllllllIlIlIIllIIIIIIIl < llllllllllllllllIlIlIIllIIIIIIII.tagCount(); ++llllllllllllllllIlIlIIllIIIIIIIl) {
                    final short llllllllllllllllIlIlIIllIIIIIlII = llllllllllllllllIlIlIIllIIIIIIII.getCompoundTagAt(llllllllllllllllIlIlIIllIIIIIIIl).getShort("id");
                    final short llllllllllllllllIlIlIIllIIIIIIll = llllllllllllllllIlIlIIllIIIIIIII.getCompoundTagAt(llllllllllllllllIlIlIIllIIIIIIIl).getShort("lvl");
                    final Enchantment llllllllllllllllIlIlIIllIIIIIIlI = Enchantment.getEnchantmentByID((int)llllllllllllllllIlIlIIllIIIIIlII);
                    if (llllllllllllllllIlIlIIllIIIIIIlI != null) {
                        llllllllllllllllIlIlIIlIllllllll.add(llllllllllllllllIlIlIIllIIIIIIlI.getTranslatedName((int)llllllllllllllllIlIlIIllIIIIIIll));
                    }
                }
            }
            if (!llllllllllllllllIlIlIIlIllllllll.contains("Blast Protection IV")) {
                continue;
            }
            llllllllllllllllIlIlIIlIlllllIlI = true;
        }
        return llllllllllllllllIlIlIIlIllllllII && llllllllllllllllIlIlIIlIlllllIll == 4 && llllllllllllllllIlIlIIlIlllllIlI;
    }
    
    @Override
    public String getHudInfo() {
        int llllllllllllllllIlIlIIlIlllIIIlI = 0;
        for (int llllllllllllllllIlIlIIlIlllIIlII = 45; llllllllllllllllIlIlIIlIlllIIlII > -1; --llllllllllllllllIlIlIIlIlllIIlII) {
            if (AutoTotem.mc.player.inventory.getStackInSlot(llllllllllllllllIlIlIIlIlllIIlII).getItem() == Items.TOTEM_OF_UNDYING) {
                llllllllllllllllIlIlIIlIlllIIIlI += AutoTotem.mc.player.inventory.getStackInSlot(llllllllllllllllIlIlIIlIlllIIlII).getCount();
            }
        }
        return String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIIlIlllIIIlI).append(""));
    }
    
    static {
        AutoTotem.switchInProgress = false;
        AutoTotem.offhand_delay = 0;
    }
    
    @Override
    public void onUpdate() {
        if (AutoTotem.switchInProgress) {
            return;
        }
        if (AutoTotem.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (AutoTotem.offhand_delay > 0) {
            --AutoTotem.offhand_delay;
        }
        if (this.shouldTotem() && (AutoTotem.mc.player.getHeldItemOffhand() == ItemStack.EMPTY || AutoTotem.mc.player.getHeldItemOffhand().getItem() != Items.TOTEM_OF_UNDYING)) {
            final int llllllllllllllllIlIlIIllIIIllIll = (this.getTotemSlot() < 9) ? (this.getTotemSlot() + 36) : this.getTotemSlot();
            if (this.getTotemSlot() != -1) {
                this.slot = llllllllllllllllIlIlIIllIIIllIll;
                AutoTotem.switchInProgress = true;
                AutoTotem.offhand_delay = this.delay.getValue();
            }
        }
    }
    
    @EventTarget
    public void onPlayerUpdate(final LocalPlayerUpdateEvent llllllllllllllllIlIlIIllIIIlIlIl) {
        if (AutoTotem.switchInProgress) {
            if (this.switchDelay1 > 0) {
                --this.switchDelay1;
            }
            if (this.switchDelay2 > 0) {
                --this.switchDelay2;
            }
            if (!this.didFirst) {
                AutoTotem.mc.playerController.windowClick(0, this.slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                this.switchDelay1 = this.delayA.getValue();
                this.didFirst = true;
                return;
            }
            if (this.switchDelay1 == 0) {
                AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                this.switchDelay1 = -1;
                this.switchDelay2 = this.delayA.getValue();
                return;
            }
            if (this.switchDelay2 == 0) {
                AutoTotem.mc.playerController.windowClick(0, this.slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                this.switchDelay1 = -1;
                this.switchDelay2 = -1;
                this.slot = -1;
                AutoTotem.switchInProgress = false;
                this.didFirst = false;
            }
        }
    }
    
    public AutoTotem() {
        super("AutoTotem", "Automatically places totems in your offhand", 0, Category.COMBAT, true);
        this.health = this.register(new Value<Integer>("Health", this, 20, 1, 22));
        this.delayA = this.register(new Value<Integer>("Delay", this, 1, 0, 5));
        this.delay = this.register(new Value<Integer>("Offhand Delay", this, 5, 0, 20));
        this.didFirst = false;
        this.switchDelay1 = -1;
        this.switchDelay2 = -1;
        this.slot = -1;
    }
    
    int getTotemSlot() {
        int llllllllllllllllIlIlIIlIlllIlIIl = -1;
        for (int llllllllllllllllIlIlIIlIlllIlIll = 45; llllllllllllllllIlIlIIlIlllIlIll > -1; --llllllllllllllllIlIlIIlIlllIlIll) {
            if (AutoTotem.mc.player.inventory.getStackInSlot(llllllllllllllllIlIlIIlIlllIlIll).getItem() == Items.TOTEM_OF_UNDYING) {
                llllllllllllllllIlIlIIlIlllIlIIl = llllllllllllllllIlIlIIlIlllIlIll;
                break;
            }
        }
        return llllllllllllllllIlIlIIlIlllIlIIl;
    }
    
    private boolean shouldTotem() {
        return AutoTotem.mc.player.getHealth() + AutoTotem.mc.player.getAbsorptionAmount() <= (Xulu.MODULE_MANAGER.getModuleT(Offhand.class).conserveGapples.getValue() ? ((!Surround.isExposed() && isFullArmor((EntityPlayer)AutoTotem.mc.player)) ? 6 : this.health.getValue()) : this.health.getValue()) || AutoTotem.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA;
    }
}
