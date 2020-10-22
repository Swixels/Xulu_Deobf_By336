package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.client.*;
import com.elementars.eclient.event.*;

public class CustomChat extends Module
{
    private final /* synthetic */ Value<Boolean> commands;
    private final /* synthetic */ Value<Boolean> mode;
    
    public CustomChat() {
        super("CustomChat", "Appends XULU to the end of your chat messages", 0, Category.MISC, true);
        this.commands = this.register(new Value<Boolean>("Commands", this, false));
        this.mode = this.register(new Value<Boolean>("2b2t Mode", this, false));
    }
    
    @EventTarget
    public void onPacket(final EventSendPacket llllllllllllllllIllIlIIllllIlllI) {
        if (llllllllllllllllIllIlIIllllIlllI.getPacket() instanceof CPacketChatMessage) {
            String llllllllllllllllIllIlIIlllllIIII = ((CPacketChatMessage)llllllllllllllllIllIlIIllllIlllI.getPacket()).getMessage();
            if (llllllllllllllllIllIlIIlllllIIII.startsWith("/") && !this.commands.getValue()) {
                return;
            }
            llllllllllllllllIllIlIIlllllIIII = String.valueOf(new StringBuilder().append(llllllllllllllllIllIlIIlllllIIII).append(this.mode.getValue() ? " | X U L U" : " \u23d0 \u166d \u144c \u14aa \u144c"));
            if (llllllllllllllllIllIlIIlllllIIII.length() >= 256) {
                llllllllllllllllIllIlIIlllllIIII = llllllllllllllllIllIlIIlllllIIII.substring(0, 256);
            }
            ((CPacketChatMessage)llllllllllllllllIllIlIIllllIlllI.getPacket()).message = llllllllllllllllIllIlIIlllllIIII;
        }
    }
}
