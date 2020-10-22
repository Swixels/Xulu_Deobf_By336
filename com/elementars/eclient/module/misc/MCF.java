package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.command.*;
import net.minecraft.entity.*;
import com.elementars.eclient.event.*;

public class MCF extends Module
{
    private final /* synthetic */ Value<Boolean> message;
    
    public MCF() {
        super("MCF", "Middle Click Friends", 0, Category.MISC, true);
        this.message = this.register(new Value<Boolean>("Send Message", this, true));
    }
    
    @EventTarget
    public void onMiddleClick(final EventMiddleClick lllllllllllllllllIlIIIlIllIllllI) {
        final RayTraceResult lllllllllllllllllIlIIIlIllIlllIl = MCF.mc.objectMouseOver;
        if (lllllllllllllllllIlIIIlIllIlllIl.typeOfHit == RayTraceResult.Type.ENTITY) {
            final Entity lllllllllllllllllIlIIIlIlllIIIIl = lllllllllllllllllIlIIIlIllIlllIl.entityHit;
            if (lllllllllllllllllIlIIIlIlllIIIIl instanceof EntityPlayer) {
                final String lllllllllllllllllIlIIIlIlllIIIlI = ((EntityPlayer)lllllllllllllllllIlIIIlIlllIIIIl).getDisplayNameString();
                if (Friends.isFriend(lllllllllllllllllIlIIIlIlllIIIlI)) {
                    Friends.delFriend(lllllllllllllllllIlIIIlIlllIIIlI);
                    if (this.message.getValue()) {
                        Command.sendChatMessage(String.valueOf(new StringBuilder().append("&b").append(lllllllllllllllllIlIIIlIlllIIIlI).append("&r has been unfriended.")));
                    }
                }
                else {
                    Friends.addFriend(lllllllllllllllllIlIIIlIlllIIIlI);
                    if (this.message.getValue()) {
                        Command.sendChatMessage(String.valueOf(new StringBuilder().append("&b").append(lllllllllllllllllIlIIIlIlllIIIlI).append("&r has been friended.")));
                    }
                }
            }
        }
    }
}
