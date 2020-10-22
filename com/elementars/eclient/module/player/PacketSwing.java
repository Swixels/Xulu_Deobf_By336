package com.elementars.eclient.module.player;

import com.elementars.eclient.module.*;
import net.minecraft.item.*;

public class PacketSwing extends Module
{
    public PacketSwing() {
        super("PacketSwing", "Swings with packets lol", 0, Category.PLAYER, true);
    }
    
    @Override
    public void onUpdate() {
        if (PacketSwing.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && PacketSwing.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            PacketSwing.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            PacketSwing.mc.entityRenderer.itemRenderer.itemStackMainHand = PacketSwing.mc.player.getHeldItemMainhand();
        }
    }
}
