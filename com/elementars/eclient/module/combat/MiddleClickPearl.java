package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.client.gui.inventory.*;
import com.elementars.eclient.module.misc.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.elementars.eclient.event.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;

public class MiddleClickPearl extends Module
{
    private /* synthetic */ int slot2;
    /* synthetic */ int switchDelay2;
    /* synthetic */ int time;
    /* synthetic */ int switchDelay1;
    public static /* synthetic */ boolean switchInProgress;
    /* synthetic */ boolean didFirst;
    private /* synthetic */ int slot;
    private final /* synthetic */ Value<Integer> delayA;
    /* synthetic */ boolean hasClicked;
    private final /* synthetic */ Value<Integer> delay;
    /* synthetic */ boolean isFinishingTask;
    /* synthetic */ boolean isThrowingPearl;
    private final /* synthetic */ Value<String> mode;
    
    @EventTarget
    public void onMiddleClick(final EventMiddleClick lllllllllllllllllllllIIllllIIlIl) {
        if (MiddleClickPearl.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        final RayTraceResult lllllllllllllllllllllIIllllIIlII = MCF.mc.objectMouseOver;
        if (lllllllllllllllllllllIIllllIIlII.typeOfHit == RayTraceResult.Type.ENTITY) {
            return;
        }
        if (lllllllllllllllllllllIIllllIIlII.typeOfHit == RayTraceResult.Type.BLOCK) {
            return;
        }
        if (this.mode.getValue().equalsIgnoreCase("Instant")) {
            this.throwPearl();
            return;
        }
        this.slot = ((this.getSlot() < 9) ? (this.getSlot() + 36) : this.getSlot());
        this.slot2 = ((this.getSlot2() < 9) ? (this.getSlot2() + 36) : this.getSlot2());
        if (this.getSlot() == -1) {
            this.sendDebugMessage("No pearl found!");
            return;
        }
        if (this.getSlot() != -1 && this.getSlot2() != -1 && MiddleClickPearl.mc.player.getHeldItemMainhand().getItem() != Items.ENDER_PEARL) {
            MiddleClickPearl.mc.addScheduledTask(() -> {
                MiddleClickPearl.switchInProgress = true;
                this.isThrowingPearl = true;
                return;
            });
            this.hasClicked = true;
        }
    }
    
    static {
        MiddleClickPearl.switchInProgress = false;
    }
    
    @Override
    public void onUpdate() {
        if (MiddleClickPearl.switchInProgress) {
            if (this.switchDelay1 > 0) {
                --this.switchDelay1;
            }
            if (this.switchDelay2 > 0) {
                --this.switchDelay2;
            }
            if (!this.didFirst) {
                MiddleClickPearl.mc.playerController.windowClick(0, this.slot, 0, ClickType.PICKUP, (EntityPlayer)MiddleClickPearl.mc.player);
                this.switchDelay1 = this.delayA.getValue();
                this.didFirst = true;
                return;
            }
            if (this.switchDelay1 == 0) {
                MiddleClickPearl.mc.playerController.windowClick(0, this.slot2, 0, ClickType.PICKUP, (EntityPlayer)MiddleClickPearl.mc.player);
                this.switchDelay1 = -1;
                this.switchDelay2 = this.delayA.getValue();
                return;
            }
            if (this.switchDelay2 == 0) {
                MiddleClickPearl.mc.playerController.windowClick(0, this.slot, 0, ClickType.PICKUP, (EntityPlayer)MiddleClickPearl.mc.player);
                this.switchDelay1 = -1;
                this.switchDelay2 = -1;
                MiddleClickPearl.switchInProgress = false;
                this.didFirst = false;
                if (this.isThrowingPearl) {
                    MiddleClickPearl.mc.playerController.processRightClick((EntityPlayer)MiddleClickPearl.mc.player, (World)MiddleClickPearl.mc.world, EnumHand.MAIN_HAND);
                    MiddleClickPearl.mc.player.swingArm(EnumHand.MAIN_HAND);
                    this.isThrowingPearl = false;
                }
                if (this.isFinishingTask) {
                    this.slot = -1;
                    this.slot2 = -1;
                    this.time = 0;
                    this.hasClicked = false;
                    this.isFinishingTask = false;
                    MiddleClickPearl.mc.playerController.updateController();
                }
            }
        }
    }
    
    public static int findHotbarBlock(final Class lllllllllllllllllllllIIllIIlIllI) {
        for (int lllllllllllllllllllllIIllIIllIII = 0; lllllllllllllllllllllIIllIIllIII < 9; ++lllllllllllllllllllllIIllIIllIII) {
            final ItemStack lllllllllllllllllllllIIllIIllIlI = MiddleClickPearl.mc.player.inventory.getStackInSlot(lllllllllllllllllllllIIllIIllIII);
            if (lllllllllllllllllllllIIllIIllIlI != ItemStack.EMPTY) {
                if (lllllllllllllllllllllIIllIIlIllI.isInstance(lllllllllllllllllllllIIllIIllIlI.getItem())) {
                    return lllllllllllllllllllllIIllIIllIII;
                }
                if (lllllllllllllllllllllIIllIIllIlI.getItem() instanceof ItemBlock) {
                    final Block lllllllllllllllllllllIIllIIlllII = ((ItemBlock)lllllllllllllllllllllIIllIIllIlI.getItem()).getBlock();
                    if (lllllllllllllllllllllIIllIIlIllI.isInstance(lllllllllllllllllllllIIllIIlllII)) {
                        return lllllllllllllllllllllIIllIIllIII;
                    }
                }
            }
        }
        return -1;
    }
    
    private void throwPearl() {
        final int lllllllllllllllllllllIIllIllllII = findHotbarBlock(ItemEnderPearl.class);
        final boolean lllllllllllllllllllllIIllIlllIll = MiddleClickPearl.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL;
        if (lllllllllllllllllllllIIllIllllII != -1 || lllllllllllllllllllllIIllIlllIll) {
            final int lllllllllllllllllllllIIllIlllllI = MiddleClickPearl.mc.player.inventory.currentItem;
            if (!lllllllllllllllllllllIIllIlllIll) {
                switchToHotbarSlot(lllllllllllllllllllllIIllIllllII, false);
            }
            MiddleClickPearl.mc.playerController.processRightClick((EntityPlayer)MiddleClickPearl.mc.player, (World)MiddleClickPearl.mc.world, lllllllllllllllllllllIIllIlllIll ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (!lllllllllllllllllllllIIllIlllIll) {
                switchToHotbarSlot(lllllllllllllllllllllIIllIlllllI, false);
            }
        }
    }
    
    public static void switchToHotbarSlot(final int lllllllllllllllllllllIIllIllIIII, final boolean lllllllllllllllllllllIIllIlIllll) {
        if (MiddleClickPearl.mc.player.inventory.currentItem == lllllllllllllllllllllIIllIllIIII || lllllllllllllllllllllIIllIllIIII < 0) {
            return;
        }
        if (lllllllllllllllllllllIIllIlIllll) {
            MiddleClickPearl.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(lllllllllllllllllllllIIllIllIIII));
            MiddleClickPearl.mc.playerController.updateController();
        }
        else {
            MiddleClickPearl.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(lllllllllllllllllllllIIllIllIIII));
            MiddleClickPearl.mc.player.inventory.currentItem = lllllllllllllllllllllIIllIllIIII;
            MiddleClickPearl.mc.playerController.updateController();
        }
    }
    
    public MiddleClickPearl() {
        super("MiddleClickPearl", "Throws a pearl without it being in your hotbar", 0, Category.COMBAT, true);
        this.mode = this.register(new Value<String>("Mode", this, "Switch", new String[] { "Switch", "Instant" }));
        this.delayA = this.register(new Value<Integer>("Click Delay", this, 1, 0, 5)).visibleWhen(lllllllllllllllllllllIIllIIIIIlI -> this.mode.getValue().equalsIgnoreCase("Switch"));
        this.delay = this.register(new Value<Integer>("Switch Delay", this, 10, 0, 80)).visibleWhen(lllllllllllllllllllllIIllIIIIllI -> this.mode.getValue().equalsIgnoreCase("Switch"));
        this.slot = -1;
        this.slot2 = -1;
        this.hasClicked = false;
        this.isFinishingTask = false;
        this.isThrowingPearl = false;
        this.didFirst = false;
        this.switchDelay1 = -1;
        this.switchDelay2 = -1;
    }
    
    int getSlot2() {
        int lllllllllllllllllllllIIlllIIIlll = -1;
        for (int lllllllllllllllllllllIIlllIIlIIl = 45; lllllllllllllllllllllIIlllIIlIIl > -1; --lllllllllllllllllllllIIlllIIlIIl) {
            if (MiddleClickPearl.mc.player.inventory.getStackInSlot(lllllllllllllllllllllIIlllIIlIIl) == MiddleClickPearl.mc.player.getHeldItemMainhand()) {
                lllllllllllllllllllllIIlllIIIlll = lllllllllllllllllllllIIlllIIlIIl;
                break;
            }
        }
        return lllllllllllllllllllllIIlllIIIlll;
    }
    
    public static void switchToHotbarSlot(final Class lllllllllllllllllllllIIllIlIIlIl, final boolean lllllllllllllllllllllIIllIlIIlll) {
        final int lllllllllllllllllllllIIllIlIIllI = findHotbarBlock(lllllllllllllllllllllIIllIlIIlIl);
        if (lllllllllllllllllllllIIllIlIIllI > -1) {
            switchToHotbarSlot(lllllllllllllllllllllIIllIlIIllI, lllllllllllllllllllllIIllIlIIlll);
        }
    }
    
    int getSlot() {
        int lllllllllllllllllllllIIlllIlIllI = -1;
        for (int lllllllllllllllllllllIIlllIllIII = 45; lllllllllllllllllllllIIlllIllIII > -1; --lllllllllllllllllllllIIlllIllIII) {
            if (MiddleClickPearl.mc.player.inventory.getStackInSlot(lllllllllllllllllllllIIlllIllIII).getItem() == Items.ENDER_PEARL) {
                lllllllllllllllllllllIIlllIlIllI = lllllllllllllllllllllIIlllIllIII;
                break;
            }
        }
        return lllllllllllllllllllllIIlllIlIllI;
    }
    
    @EventTarget
    public void onPlayerUpdate(final LocalPlayerUpdateEvent lllllllllllllllllllllIIlllIlllII) {
        if (MiddleClickPearl.mc.player.getHeldItemMainhand().getItem() == Items.ENDER_PEARL && this.hasClicked) {
            ++this.time;
            if (this.time < this.delay.getValue()) {
                return;
            }
            MiddleClickPearl.mc.addScheduledTask(() -> {
                MiddleClickPearl.switchInProgress = true;
                this.isFinishingTask = true;
            });
        }
        else if (this.getSlot() == -1 && this.hasClicked) {
            MiddleClickPearl.mc.addScheduledTask(() -> {
                MiddleClickPearl.switchInProgress = true;
                this.isFinishingTask = true;
            });
        }
    }
}
