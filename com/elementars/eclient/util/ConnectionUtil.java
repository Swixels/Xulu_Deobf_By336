package com.elementars.eclient.util;

import net.minecraft.network.play.server.*;
import com.google.common.base.*;
import com.elementars.eclient.*;
import java.util.*;
import java.util.function.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.event.events.*;

public class ConnectionUtil
{
    public ConnectionUtil() {
        Xulu.EVENT_MANAGER.register(this);
    }
    
    @EventTarget
    public void onScoreboardEvent(final EventReceivePacket lIIlIlllIlIIIll) {
        if (lIIlIlllIlIIIll.getPacket() instanceof SPacketPlayerListItem) {
            final SPacketPlayerListItem lIIlIlllIlIIlIl = (SPacketPlayerListItem)lIIlIlllIlIIIll.getPacket();
            if (!SPacketPlayerListItem.Action.ADD_PLAYER.equals((Object)lIIlIlllIlIIlIl.getAction()) && !SPacketPlayerListItem.Action.REMOVE_PLAYER.equals((Object)lIIlIlllIlIIlIl.getAction())) {
                return;
            }
            final UUID lIIlIlllIIlIlll;
            final String lIIlIlllIIlIllI;
            final SPacketPlayerListItem sPacketPlayerListItem;
            lIIlIlllIlIIlIl.getEntries().stream().filter(Objects::nonNull).filter(lIIlIlllIIIlllI -> !Strings.isNullOrEmpty(lIIlIlllIIIlllI.getProfile().getName()) || lIIlIlllIIIlllI.getProfile().getId() != null).forEach(lIIlIlllIIllIII -> {
                lIIlIlllIIlIlll = lIIlIlllIIllIII.getProfile().getId();
                lIIlIlllIIlIllI = lIIlIlllIIllIII.getProfile().getName();
                this.fireEvents(sPacketPlayerListItem.getAction(), lIIlIlllIIlIlll, lIIlIlllIIlIllI);
            });
        }
    }
    
    private void fireEvents(final SPacketPlayerListItem.Action lIIlIlllIlIllII, final UUID lIIlIlllIlIlllI, final String lIIlIlllIlIllIl) {
        if (lIIlIlllIlIlllI == null) {
            return;
        }
        switch (lIIlIlllIlIllII) {
            case ADD_PLAYER: {
                final EventPlayerConnect.Join lIIlIlllIllIIlI = new EventPlayerConnect.Join(lIIlIlllIlIlllI, lIIlIlllIlIllIl);
                lIIlIlllIllIIlI.call();
                break;
            }
            case REMOVE_PLAYER: {
                final EventPlayerConnect.Leave lIIlIlllIllIIIl = new EventPlayerConnect.Leave(lIIlIlllIlIlllI, lIIlIlllIlIllIl);
                lIIlIlllIllIIIl.call();
                break;
            }
        }
    }
}
