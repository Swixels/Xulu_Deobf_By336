package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import java.util.concurrent.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import com.elementars.eclient.command.*;

public class PopCounter extends Module
{
    private final /* synthetic */ Value<Boolean> watermark;
    private final /* synthetic */ Value<Boolean> chat;
    public static /* synthetic */ PopCounter INSTANCE;
    public /* synthetic */ ConcurrentHashMap<EntityPlayer, Integer> popMap;
    private final /* synthetic */ Value<String> ncolor;
    private final /* synthetic */ Value<Boolean> onlyTargets;
    private final /* synthetic */ Value<String> color;
    
    @Override
    public void onUpdate() {
        for (final EntityPlayer llllllllllllllllllllIIlllllllIll : PopCounter.mc.world.playerEntities) {
            if (llllllllllllllllllllIIlllllllIll.getHealth() == 0.0f && this.popMap.containsKey(llllllllllllllllllllIIlllllllIll)) {
                if (this.chat.getValue()) {
                    this.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllllllIIlllllllIll.getName()).append(" has died!")));
                }
                this.popMap.remove(llllllllllllllllllllIIlllllllIll);
            }
        }
    }
    
    @EventTarget
    public void onPop(final EventTotemPop llllllllllllllllllllIIllllllIIIl) {
        if (TargetPlayers.targettedplayers.containsKey(llllllllllllllllllllIIllllllIIIl.getPlayer().getName()) || !this.onlyTargets.getValue()) {
            final int llllllllllllllllllllIIllllllIIll = this.popMap.getOrDefault(llllllllllllllllllllIIllllllIIIl.getPlayer(), 0) + 1;
            if (this.chat.getValue()) {
                this.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllllllIIllllllIIIl.getPlayer().getName()).append(" has popped ").append(ColorTextUtils.getColor(this.ncolor.getValue())).append(llllllllllllllllllllIIllllllIIll).append(ColorTextUtils.getColor(this.color.getValue())).append(" times!")));
            }
            this.popMap.put(llllllllllllllllllllIIllllllIIIl.getPlayer(), llllllllllllllllllllIIllllllIIll);
        }
    }
    
    public PopCounter() {
        super("PopCounter", "Counts how many times your enemy pops", 0, Category.COMBAT, true);
        this.popMap = new ConcurrentHashMap<EntityPlayer, Integer>();
        this.onlyTargets = this.register(new Value<Boolean>("Only Targets", this, true));
        this.chat = this.register(new Value<Boolean>("Send Message", this, true));
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.color = this.register(new Value<String>("Color", this, "White", ColorTextUtils.colors));
        this.ncolor = this.register(new Value<String>("Number Color", this, "White", ColorTextUtils.colors));
        PopCounter.INSTANCE = this;
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket llllllllllllllllllllIIlllllIIlII) {
        if (llllllllllllllllllllIIlllllIIlII.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus llllllllllllllllllllIIlllllIIlll = (SPacketEntityStatus)llllllllllllllllllllIIlllllIIlII.getPacket();
            if (llllllllllllllllllllIIlllllIIlll.getOpCode() == 35 && llllllllllllllllllllIIlllllIIlll.getEntity((World)PopCounter.mc.world) instanceof EntityPlayer) {
                final EntityPlayer llllllllllllllllllllIIlllllIlIIl = (EntityPlayer)llllllllllllllllllllIIlllllIIlll.getEntity((World)PopCounter.mc.world);
                final EventTotemPop llllllllllllllllllllIIlllllIlIII = new EventTotemPop(llllllllllllllllllllIIlllllIlIIl);
                llllllllllllllllllllIIlllllIlIII.call();
            }
        }
    }
    
    public void sendChatMessage(final String llllllllllllllllllllIIllllIlllIl) {
        if (this.watermark.getValue()) {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(llllllllllllllllllllIIllllIlllIl)));
        }
        else {
            Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(llllllllllllllllllllIIllllIlllIl)));
        }
    }
}
