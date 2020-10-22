package com.elementars.eclient.module.player;

import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;

public class Multitask extends Module
{
    @EventTarget
    public void onUseItem(final AllowInteractEvent lIIllIIlllIlIII) {
        lIIllIIlllIlIII.usingItem = false;
    }
    
    public Multitask() {
        super("Multitask", "Credit to luchadora client", 0, Category.PLAYER, true);
    }
}
