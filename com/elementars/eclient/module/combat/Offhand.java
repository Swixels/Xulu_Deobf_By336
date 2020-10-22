package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import com.elementars.eclient.module.*;
import com.google.common.collect.*;
import net.minecraft.client.gui.inventory.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.event.*;

public class Offhand extends Module
{
    /* synthetic */ int switchDelay1;
    public static /* synthetic */ boolean switchInProgress;
    public final /* synthetic */ Value<Boolean> conserveGapples;
    /* synthetic */ int slot;
    private final /* synthetic */ Value<String> item;
    private /* synthetic */ Map<String, Item> itemMap;
    private final /* synthetic */ Value<Integer> delay;
    /* synthetic */ int switchDelay2;
    /* synthetic */ boolean didFirst;
    private final /* synthetic */ Value<Boolean> gapOnSword;
    
    private boolean isOk() {
        return Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() > Module.getModuleT(AutoTotem.class).health.getValue() && Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA;
    }
    
    @Override
    public String getHudInfo() {
        int llIIIIIIIIllllI = 0;
        for (int llIIIIIIIlIIIII = 45; llIIIIIIIlIIIII > -1; --llIIIIIIIlIIIII) {
            if (Offhand.mc.player.inventory.getStackInSlot(llIIIIIIIlIIIII).getItem() == this.itemMap.get(this.item.getValue())) {
                llIIIIIIIIllllI += Offhand.mc.player.inventory.getStackInSlot(llIIIIIIIlIIIII).getCount();
            }
        }
        return String.valueOf(new StringBuilder().append(llIIIIIIIIllllI).append(""));
    }
    
    public Offhand() {
        super("Offhand", "Automatically places items in your offhand", 0, Category.COMBAT, true);
        this.itemMap = (Map<String, Item>)Maps.newHashMap();
        this.item = this.register(new Value<String>("Item", this, "Crystals", new String[] { "Crystals", "Gapples" }));
        this.conserveGapples = this.register(new Value<Boolean>("Conserve Gap", this, true));
        this.gapOnSword = this.register(new Value<Boolean>("Gap On Sword", this, false));
        this.delay = this.register(new Value<Integer>("Delay", this, 1, 0, 5));
        this.didFirst = false;
        this.switchDelay1 = -1;
        this.switchDelay2 = -1;
        this.slot = -1;
        this.itemMap.put("Crystals", Items.END_CRYSTAL);
        this.itemMap.put("Gapples", Items.GOLDEN_APPLE);
    }
    
    int getSlot() {
        int llIIIIIIIlIIlll = -1;
        for (int llIIIIIIIlIlIIl = 45; llIIIIIIIlIlIIl > -1; --llIIIIIIIlIlIIl) {
            if (Offhand.mc.player.inventory.getStackInSlot(llIIIIIIIlIlIIl).getItem() == this.itemMap.get(this.getItem())) {
                llIIIIIIIlIIlll = llIIIIIIIlIlIIl;
                break;
            }
        }
        return llIIIIIIIlIIlll;
    }
    
    @Override
    public void onUpdate() {
        if (Offhand.switchInProgress) {
            return;
        }
        if (Offhand.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (AutoTotem.offhand_delay != 0) {
            return;
        }
        if (this.isOk() && Offhand.mc.player.getHeldItemOffhand().getItem() != this.itemMap.get(this.getItem())) {
            final int llIIIIIIIlllIII = (this.getSlot() < 9) ? (this.getSlot() + 36) : this.getSlot();
            if (this.getSlot() != -1) {
                this.slot = llIIIIIIIlllIII;
                Offhand.switchInProgress = true;
            }
        }
    }
    
    @EventTarget
    public void onPlayerUpdate(final LocalPlayerUpdateEvent llIIIIIIIllIIlI) {
        if (Offhand.switchInProgress) {
            if (this.switchDelay1 > 0) {
                --this.switchDelay1;
            }
            if (this.switchDelay2 > 0) {
                --this.switchDelay2;
            }
            if (!this.didFirst) {
                Offhand.mc.playerController.windowClick(0, this.slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                this.switchDelay1 = this.delay.getValue();
                this.didFirst = true;
                return;
            }
            if (this.switchDelay1 == 0) {
                Offhand.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                this.switchDelay1 = -1;
                this.switchDelay2 = this.delay.getValue();
                return;
            }
            if (this.switchDelay2 == 0) {
                Offhand.mc.playerController.windowClick(0, this.slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                this.switchDelay1 = -1;
                this.switchDelay2 = -1;
                this.slot = -1;
                Offhand.switchInProgress = false;
                this.didFirst = false;
            }
        }
    }
    
    static {
        Offhand.switchInProgress = false;
    }
    
    String getItem() {
        if (this.gapOnSword.getValue() && Offhand.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) {
            return "Gapples";
        }
        return this.item.getValue();
    }
}
