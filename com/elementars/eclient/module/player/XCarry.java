package com.elementars.eclient.module.player;

import com.elementars.eclient.module.*;
import net.minecraft.network.play.client.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.event.events.*;

public class XCarry extends Module
{
    public XCarry() {
        super("XCarry", "Holds things in your crafting menu", 0, Category.PLAYER, true);
    }
    
    @EventTarget
    public void onPacket(final EventSendPacket llIlllII) {
        if (llIlllII.getPacket() instanceof CPacketCloseWindow) {
            final CPacketCloseWindow llIlllll = (CPacketCloseWindow)llIlllII.getPacket();
            llIlllII.setCancelled(llIlllll.windowId == 0);
        }
    }
    
    @EventTarget
    public void onClose(final CloseInventoryEvent llIlIlll) {
        llIlIlll.setCancelled(true);
    }
}
