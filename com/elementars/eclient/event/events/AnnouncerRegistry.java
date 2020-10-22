package com.elementars.eclient.event.events;

import net.minecraft.util.math.*;
import net.minecraft.client.*;
import com.elementars.eclient.module.*;
import net.minecraftforge.client.event.*;
import com.elementars.eclient.module.misc.*;
import net.minecraft.util.text.*;
import com.elementars.eclient.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.entity.*;
import net.minecraft.block.*;
import java.text.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraft.item.*;
import com.elementars.eclient.*;

public class AnnouncerRegistry
{
    static /* synthetic */ int blocksWalkerCounter;
    /* synthetic */ int lastWalkedAmount;
    static /* synthetic */ int commandDelay;
    /* synthetic */ BlockPos blockPos;
    static /* synthetic */ long lastPositionUpdate;
    static /* synthetic */ int dropItemDelay;
    static /* synthetic */ int jumpDelay;
    static /* synthetic */ int inventoryDelay;
    /* synthetic */ Item heldBlock;
    /* synthetic */ int kek;
    static /* synthetic */ int blockPlacedDelay;
    static /* synthetic */ int chatDelay;
    static /* synthetic */ int perspectivesDelay;
    /* synthetic */ Minecraft minecraft;
    static /* synthetic */ int attackDelay;
    /* synthetic */ String heldItem;
    static /* synthetic */ int playerListDelay;
    static /* synthetic */ double lastPositionZ;
    private static /* synthetic */ double speed;
    static /* synthetic */ double lastPositionY;
    static /* synthetic */ int blockBrokeDelay;
    static /* synthetic */ double lastPositionX;
    static /* synthetic */ int pauseDelay;
    /* synthetic */ String blockName;
    static /* synthetic */ int crouchedDelay;
    static /* synthetic */ int eattingDelay;
    static /* synthetic */ Module announcer;
    static /* synthetic */ int itemPickUpDelay;
    
    @SubscribeEvent
    public void onMouseClicked(final MouseEvent lllllllllllllllllIIlIIlIlIIlIIlI) {
        if (AnnouncerRegistry.announcer.isToggled() && Mouse.getEventButtonState() && Mouse.getEventButton() == 0 && this.minecraft.objectMouseOver.entityHit != null && AnnouncerRegistry.attackDelay >= 300 && Announcer.attack.getValue()) {
            this.sendMessage(AnnouncerUtil.getAttacked(TextFormatting.getTextWithoutFormattingCodes(this.minecraft.objectMouseOver.entityHit.getName()), this.minecraft.player.getHeldItemMainhand().getDisplayName()));
            AnnouncerRegistry.attackDelay = 0;
        }
    }
    
    public AnnouncerRegistry() {
        this.minecraft = Minecraft.getMinecraft();
        this.heldItem = "";
        this.lastWalkedAmount = 0;
        this.kek = 0;
        this.blockName = "";
    }
    
    static {
        AnnouncerRegistry.dropItemDelay = 0;
        AnnouncerRegistry.itemPickUpDelay = 0;
        AnnouncerRegistry.blockPlacedDelay = 0;
        AnnouncerRegistry.blockBrokeDelay = 0;
        AnnouncerRegistry.chatDelay = 0;
        AnnouncerRegistry.commandDelay = 0;
        AnnouncerRegistry.pauseDelay = 0;
        AnnouncerRegistry.inventoryDelay = 0;
        AnnouncerRegistry.playerListDelay = 0;
        AnnouncerRegistry.perspectivesDelay = 0;
        AnnouncerRegistry.crouchedDelay = 0;
        AnnouncerRegistry.jumpDelay = 0;
        AnnouncerRegistry.attackDelay = 0;
        AnnouncerRegistry.eattingDelay = 0;
    }
    
    @SubscribeEvent
    public void itemPickedUp(final EntityItemPickupEvent lllllllllllllllllIIlIIlIlIllIIlI) {
        if (AnnouncerRegistry.announcer.isToggled() && AnnouncerRegistry.itemPickUpDelay >= 150 && Announcer.pickUpItem.getValue()) {
            final ItemStack lllllllllllllllllIIlIIlIlIllIlII = lllllllllllllllllIIlIIlIlIllIIlI.getItem().getItem();
            this.sendMessage(AnnouncerUtil.getPickedUp(lllllllllllllllllIIlIIlIlIllIlII.getDisplayName()));
            AnnouncerRegistry.itemPickUpDelay = 0;
        }
    }
    
    @SubscribeEvent
    public void itemSmelted(final PlayerEvent.ItemSmeltedEvent lllllllllllllllllIIlIIlIlIlIlIll) {
        if (lllllllllllllllllIIlIIlIlIlIlIll.player instanceof EntityPlayerSP && AnnouncerRegistry.announcer.isToggled() && Announcer.smeltedItem.getValue()) {
            this.sendMessage(AnnouncerUtil.getSmelted(String.valueOf(new StringBuilder().append(lllllllllllllllllIIlIIlIlIlIlIll.smelting.getCount()).append(" ").append(lllllllllllllllllIIlIIlIlIlIlIll.smelting.getDisplayName()))));
        }
    }
    
    @SubscribeEvent
    public void livingUpdate(final TickEvent.PlayerTickEvent lllllllllllllllllIIlIIlIllIIIlII) {
        if (lllllllllllllllllIIlIIlIllIIIlII.player instanceof EntityPlayerSP) {
            if (this.kek >= 1 && AnnouncerRegistry.announcer.isToggled() && this.minecraft.world.getBlockState(this.blockPos).getBlock() instanceof BlockAir && AnnouncerRegistry.blockBrokeDelay >= 300 && Announcer.blockBroke.getValue()) {
                this.sendMessage(AnnouncerUtil.getBlockBreak(this.blockName));
                AnnouncerRegistry.blockBrokeDelay = 0;
                this.kek = 0;
            }
            if (AnnouncerRegistry.announcer.isToggled()) {
                this.heldItem = this.minecraft.player.inventory.getCurrentItem().getDisplayName();
                this.heldBlock = this.minecraft.player.inventory.getCurrentItem().getItem();
                ++AnnouncerRegistry.blocksWalkerCounter;
                ++AnnouncerRegistry.dropItemDelay;
                ++AnnouncerRegistry.itemPickUpDelay;
                ++AnnouncerRegistry.blockPlacedDelay;
                ++AnnouncerRegistry.blockBrokeDelay;
                ++AnnouncerRegistry.chatDelay;
                ++AnnouncerRegistry.commandDelay;
                ++AnnouncerRegistry.pauseDelay;
                ++AnnouncerRegistry.inventoryDelay;
                ++AnnouncerRegistry.playerListDelay;
                ++AnnouncerRegistry.perspectivesDelay;
                ++AnnouncerRegistry.crouchedDelay;
                ++AnnouncerRegistry.jumpDelay;
                ++AnnouncerRegistry.attackDelay;
                ++AnnouncerRegistry.eattingDelay;
                if (AnnouncerRegistry.lastPositionUpdate + 30000L < System.currentTimeMillis() && Announcer.walk.getValue()) {
                    final double lllllllllllllllllIIlIIlIllIIlIll = AnnouncerRegistry.lastPositionX - this.minecraft.player.lastTickPosX;
                    final double lllllllllllllllllIIlIIlIllIIlIlI = AnnouncerRegistry.lastPositionY - this.minecraft.player.lastTickPosY;
                    final double lllllllllllllllllIIlIIlIllIIlIIl = AnnouncerRegistry.lastPositionZ - this.minecraft.player.lastTickPosZ;
                    AnnouncerRegistry.speed = Math.sqrt(lllllllllllllllllIIlIIlIllIIlIll * lllllllllllllllllIIlIIlIllIIlIll + lllllllllllllllllIIlIIlIllIIlIlI * lllllllllllllllllIIlIIlIllIIlIlI + lllllllllllllllllIIlIIlIllIIlIIl * lllllllllllllllllIIlIIlIllIIlIIl);
                    if (AnnouncerRegistry.speed > 0.0 && AnnouncerRegistry.speed <= 5000.0) {
                        this.sendMessage(AnnouncerUtil.getMove(new DecimalFormat("#").format(AnnouncerRegistry.speed)));
                        AnnouncerRegistry.lastPositionUpdate = System.currentTimeMillis();
                        AnnouncerRegistry.lastPositionX = this.minecraft.player.lastTickPosX;
                        AnnouncerRegistry.lastPositionY = this.minecraft.player.lastTickPosY;
                        AnnouncerRegistry.lastPositionZ = this.minecraft.player.lastTickPosZ;
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void playerBlockPlaced(final PlayerInteractEvent.RightClickBlock lllllllllllllllllIIlIIlIlIIlllll) {
        if (AnnouncerRegistry.announcer.isToggled() && AnnouncerRegistry.blockPlacedDelay >= 150 && Announcer.blockPlaced.getValue() && lllllllllllllllllIIlIIlIlIIlllll.getItemStack().getItem() instanceof ItemBlock) {
            this.sendMessage(AnnouncerUtil.getPlaced(lllllllllllllllllIIlIIlIlIIlllll.getItemStack().getDisplayName()));
            AnnouncerRegistry.blockPlacedDelay = 0;
        }
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent lllllllllllllllllIIlIIlIlIIlIllI) {
        if (AnnouncerRegistry.announcer.isToggled()) {
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindDrop.getKeyCode() && !this.heldItem.equals("Air") && AnnouncerRegistry.dropItemDelay >= 150 && Announcer.itemDroped.getValue()) {
                this.sendMessage(AnnouncerUtil.getDropped(this.heldItem));
                AnnouncerRegistry.dropItemDelay = 0;
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindChat.getKeyCode() && AnnouncerRegistry.chatDelay >= 150 && Announcer.openChat.getValue()) {
                this.sendMessage(AnnouncerUtil.getChat());
                AnnouncerRegistry.chatDelay = 0;
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindPickBlock.getKeyCode() && Announcer.pickBlock.getValue()) {
                this.sendMessage(AnnouncerUtil.getPickBlock());
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindCommand.getKeyCode() && AnnouncerRegistry.commandDelay >= 150 && Announcer.command.getValue()) {
                this.sendMessage(AnnouncerUtil.getConsole());
                AnnouncerRegistry.commandDelay = 0;
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindFullscreen.getKeyCode() && Announcer.fullScreen.getValue()) {
                this.sendMessage(AnnouncerUtil.getFullScreen());
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == 1 && AnnouncerRegistry.pauseDelay >= 150 && Announcer.pauseGame.getValue()) {
                this.sendMessage(AnnouncerUtil.getPause());
                AnnouncerRegistry.pauseDelay = 0;
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindInventory.getKeyCode() && AnnouncerRegistry.inventoryDelay >= 150 && Announcer.openInv.getValue()) {
                this.sendMessage(AnnouncerUtil.getInventory());
                AnnouncerRegistry.inventoryDelay = 0;
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindPlayerList.getKeyCode() && AnnouncerRegistry.playerListDelay >= 150 && Announcer.playerList.getValue()) {
                this.sendMessage(AnnouncerUtil.getPlayerList());
                AnnouncerRegistry.playerListDelay = 0;
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindScreenshot.getKeyCode() && Announcer.screenShot.getValue()) {
                this.sendMessage(AnnouncerUtil.getScreenShot());
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindSwapHands.getKeyCode() && Announcer.swapHand.getValue()) {
                this.sendMessage(AnnouncerUtil.getSwappedHands());
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindSneak.getKeyCode() && AnnouncerRegistry.crouchedDelay >= 150 && Announcer.sneak.getValue()) {
                this.sendMessage(AnnouncerUtil.getCrouched());
                AnnouncerRegistry.crouchedDelay = 0;
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindTogglePerspective.getKeyCode() && AnnouncerRegistry.perspectivesDelay >= 150 && Announcer.Perspective.getValue()) {
                this.sendMessage(AnnouncerUtil.getPerspectives());
                AnnouncerRegistry.perspectivesDelay = 0;
            }
            if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == this.minecraft.gameSettings.keyBindJump.getKeyCode() && AnnouncerRegistry.jumpDelay >= 300 && Announcer.jump.getValue()) {
                this.sendMessage(AnnouncerUtil.getJumped());
                AnnouncerRegistry.jumpDelay = 0;
            }
            if (this.minecraft.player.isHandActive() && AnnouncerRegistry.eattingDelay >= 300 && Announcer.eatting.getValue() && (this.minecraft.player.getHeldItemMainhand().getItem() instanceof ItemFood || this.minecraft.player.getHeldItemMainhand().getItem() instanceof ItemAppleGold)) {
                this.sendMessage(AnnouncerUtil.getAte(this.minecraft.player.getHeldItemMainhand().getDisplayName()));
                AnnouncerRegistry.eattingDelay = 0;
            }
        }
    }
    
    @SubscribeEvent
    public void itemCrafted(final PlayerEvent.ItemCraftedEvent lllllllllllllllllIIlIIlIlIlllIlI) {
        if (lllllllllllllllllIIlIIlIlIlllIlI.player instanceof EntityPlayerSP && AnnouncerRegistry.announcer.isToggled() && Announcer.craftedItem.getValue()) {
            this.sendMessage(AnnouncerUtil.getCraft(String.valueOf(new StringBuilder().append(lllllllllllllllllIIlIIlIlIlllIlI.crafting.getCount()).append(" ").append(lllllllllllllllllIIlIIlIlIlllIlI.crafting.getDisplayName()))));
        }
    }
    
    private void sendMessage(final String lllllllllllllllllIIlIIlIllllIlll) {
        if (Announcer.delayy == 0) {
            this.minecraft.player.sendChatMessage(lllllllllllllllllIIlIIlIllllIlll);
            Announcer.delayy = (Announcer.delay.getValue() & 0x14);
        }
    }
    
    public static void initAnnouncer() {
        AnnouncerRegistry.announcer = Xulu.MODULE_MANAGER.getModuleByName("Announcer");
    }
    
    @SubscribeEvent
    public void playerRespawn(final PlayerEvent.PlayerRespawnEvent lllllllllllllllllIIlIIlIlIlIIllI) {
        if (AnnouncerRegistry.announcer.isToggled() && Announcer.respawn.getValue()) {
            this.sendMessage(AnnouncerUtil.getRespawn());
        }
    }
    
    @SubscribeEvent
    public void playerBlockBroke(final PlayerInteractEvent.LeftClickBlock lllllllllllllllllIIlIIlIlIIllIll) {
        this.blockPos = lllllllllllllllllIIlIIlIlIIllIll.getPos();
        this.blockName = this.minecraft.world.getBlockState(lllllllllllllllllIIlIIlIlIIllIll.getPos()).getBlock().getLocalizedName();
        this.kek = 1;
    }
}
