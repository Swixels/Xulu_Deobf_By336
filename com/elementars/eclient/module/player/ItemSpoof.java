package com.elementars.eclient.module.player;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.world.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;

public final class ItemSpoof extends Module
{
    public /* synthetic */ EnumHand hand;
    public /* synthetic */ float facingX;
    private /* synthetic */ boolean send;
    private /* synthetic */ Entity entity;
    public /* synthetic */ float facingZ;
    public /* synthetic */ EnumFacing placedBlockDirection;
    public /* synthetic */ float facingY;
    public /* synthetic */ BlockPos position;
    
    @EventTarget
    public void sendPacket(final EventSendPacket lllllllllllllllllllIlIlIlIIIIlIl) {
        if (lllllllllllllllllllIlIlIlIIIIlIl.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            if (this.send) {
                this.send = false;
                return;
            }
            final CPacketPlayerTryUseItemOnBlock lllllllllllllllllllIlIlIlIIIlIll = (CPacketPlayerTryUseItemOnBlock)lllllllllllllllllllIlIlIlIIIIlIl.getPacket();
            this.position = lllllllllllllllllllIlIlIlIIIlIll.getPos();
            this.placedBlockDirection = lllllllllllllllllllIlIlIlIIIlIll.getDirection();
            this.hand = lllllllllllllllllllIlIlIlIIIlIll.getHand();
            this.facingX = lllllllllllllllllllIlIlIlIIIlIll.getFacingX();
            this.facingY = lllllllllllllllllllIlIlIlIIIlIll.getFacingY();
            this.facingZ = lllllllllllllllllllIlIlIlIIIlIll.getFacingZ();
            if (this.position != null) {
                lllllllllllllllllllIlIlIlIIIIlIl.setCancelled(true);
                final Minecraft lllllllllllllllllllIlIlIlIIIllII = Minecraft.getMinecraft();
                lllllllllllllllllllIlIlIlIIIllII.playerController.windowClick(lllllllllllllllllllIlIlIlIIIllII.player.inventoryContainer.windowId, 9, lllllllllllllllllllIlIlIlIIIllII.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)lllllllllllllllllllIlIlIlIIIllII.player);
                this.send = true;
                lllllllllllllllllllIlIlIlIIIllII.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.position, this.placedBlockDirection, this.hand, this.facingX, this.facingY, this.facingZ));
                lllllllllllllllllllIlIlIlIIIllII.playerController.windowClick(lllllllllllllllllllIlIlIlIIIllII.player.inventoryContainer.windowId, 9, lllllllllllllllllllIlIlIlIIIllII.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)lllllllllllllllllllIlIlIlIIIllII.player);
            }
        }
        if (lllllllllllllllllllIlIlIlIIIIlIl.getPacket() instanceof CPacketPlayerTryUseItem) {
            if (this.send) {
                this.send = false;
                return;
            }
            final CPacketPlayerTryUseItem lllllllllllllllllllIlIlIlIIIlIIl = (CPacketPlayerTryUseItem)lllllllllllllllllllIlIlIlIIIIlIl.getPacket();
            this.hand = lllllllllllllllllllIlIlIlIIIlIIl.getHand();
            if (this.hand != null) {
                lllllllllllllllllllIlIlIlIIIIlIl.setCancelled(true);
                final Minecraft lllllllllllllllllllIlIlIlIIIlIlI = Minecraft.getMinecraft();
                lllllllllllllllllllIlIlIlIIIlIlI.playerController.windowClick(lllllllllllllllllllIlIlIlIIIlIlI.player.inventoryContainer.windowId, 9, lllllllllllllllllllIlIlIlIIIlIlI.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)lllllllllllllllllllIlIlIlIIIlIlI.player);
                this.send = true;
                lllllllllllllllllllIlIlIlIIIlIlI.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(this.hand));
                lllllllllllllllllllIlIlIlIIIlIlI.playerController.windowClick(lllllllllllllllllllIlIlIlIIIlIlI.player.inventoryContainer.windowId, 9, lllllllllllllllllllIlIlIlIIIlIlI.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)lllllllllllllllllllIlIlIlIIIlIlI.player);
            }
        }
        if (lllllllllllllllllllIlIlIlIIIIlIl.getPacket() instanceof CPacketUseEntity) {
            if (this.send) {
                this.send = false;
                return;
            }
            final CPacketUseEntity lllllllllllllllllllIlIlIlIIIIlll = (CPacketUseEntity)lllllllllllllllllllIlIlIlIIIIlIl.getPacket();
            if (lllllllllllllllllllIlIlIlIIIIlll.getAction() == CPacketUseEntity.Action.ATTACK) {
                final Minecraft lllllllllllllllllllIlIlIlIIIlIII = Minecraft.getMinecraft();
                this.entity = lllllllllllllllllllIlIlIlIIIIlll.getEntityFromWorld((World)lllllllllllllllllllIlIlIlIIIlIII.world);
                if (this.entity != null) {
                    lllllllllllllllllllIlIlIlIIIIlIl.setCancelled(true);
                    lllllllllllllllllllIlIlIlIIIlIII.playerController.windowClick(lllllllllllllllllllIlIlIlIIIlIII.player.inventoryContainer.windowId, 9, lllllllllllllllllllIlIlIlIIIlIII.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)lllllllllllllllllllIlIlIlIIIlIII.player);
                    this.send = true;
                    lllllllllllllllllllIlIlIlIIIlIII.player.connection.sendPacket((Packet)new CPacketUseEntity(this.entity));
                    lllllllllllllllllllIlIlIlIIIlIII.playerController.windowClick(lllllllllllllllllllIlIlIlIIIlIII.player.inventoryContainer.windowId, 9, lllllllllllllllllllIlIlIlIIIlIII.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)lllllllllllllllllllIlIlIlIIIlIII.player);
                }
            }
        }
    }
    
    public ItemSpoof() {
        super("ItemSpoof", "Allows you to display a different item server-side(Use the top left slot in your inventory)", 0, Category.PLAYER, true);
    }
}
