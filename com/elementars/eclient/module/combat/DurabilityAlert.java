package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import java.util.concurrent.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.friend.*;
import net.minecraft.item.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.util.*;

public class DurabilityAlert extends Module
{
    private final /* synthetic */ Value<Boolean> ignoreself;
    /* synthetic */ ConcurrentHashMap<String, Integer> players;
    private final /* synthetic */ Value<Boolean> watermark;
    private final /* synthetic */ Value<String> color;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Boolean> ignorefriends;
    
    @Override
    public void onUpdate() {
        for (final EntityPlayer lIIIIIlIlllIIl : DurabilityAlert.mc.world.playerEntities) {
            if (this.ignoreself.getValue() && lIIIIIlIlllIIl.getName().equalsIgnoreCase(DurabilityAlert.mc.player.getName())) {
                return;
            }
            if (this.ignorefriends.getValue() && Friends.isFriend(lIIIIIlIlllIIl.getName())) {
                return;
            }
            for (final ItemStack lIIIIIlIlllIlI : lIIIIIlIlllIIl.getArmorInventoryList()) {
                if (lIIIIIlIlllIlI != null && lIIIIIlIlllIlI.getItem().getDurabilityForDisplay(lIIIIIlIlllIlI) > 0.75 && !this.players.containsKey(lIIIIIlIlllIIl.getName())) {
                    if (this.mode.getValue().equalsIgnoreCase("Chat")) {
                        if (this.watermark.getValue()) {
                            Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(lIIIIIlIlllIIl.getName()).append(" has low durability!")));
                        }
                        else {
                            Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(lIIIIIlIlllIIl.getName()).append(" has low durability!")));
                        }
                    }
                    this.players.put(lIIIIIlIlllIIl.getName(), 1500);
                }
            }
        }
        this.players.forEach((lIIIIIlIlIIIII, lIIIIIlIIlllll) -> {
            if (lIIIIIlIIlllll <= 0) {
                this.players.remove(lIIIIIlIlIIIII);
            }
            else {
                this.players.put(lIIIIIlIlIIIII, lIIIIIlIIlllll - 1);
            }
        });
    }
    
    public DurabilityAlert() {
        super("DurabilityAlert", "Alerts when someone has low durability", 0, Category.COMBAT, true);
        this.players = new ConcurrentHashMap<String, Integer>();
        this.mode = this.register(new Value<String>("Mode", this, "Chat", new String[] { "Chat", "Notification" }));
        this.ignoreself = this.register(new Value<Boolean>("Ignore Self", this, false));
        this.ignorefriends = this.register(new Value<Boolean>("Ignore Friends", this, false));
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.color = this.register(new Value<String>("Color", this, "White", ColorTextUtils.colors));
    }
    
    @Override
    public void onRender() {
        if (this.mode.getValue().equalsIgnoreCase("Notification")) {
            final ScaledResolution lIIIIIlIlIllII = new ScaledResolution(DurabilityAlert.mc);
            int lIIIIIlIlIlIll = (int)(lIIIIIlIlIllII.getScaledHeight() / 2 - lIIIIIlIlIllII.getScaledHeight() / 2 * 0.9) - DurabilityAlert.mc.fontRenderer.FONT_HEIGHT / 2;
            for (final String lIIIIIlIlIllIl : this.players.keySet()) {
                if (this.players.get(lIIIIIlIlIllIl) > 1000) {
                    DurabilityAlert.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color.getValue()).substring(1)).append(lIIIIIlIlIllIl).append(" has low durability!")), (float)(lIIIIIlIlIllII.getScaledWidth() / 2 - DurabilityAlert.mc.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(lIIIIIlIlIllIl).append(" has low durability!"))) / 2), (float)lIIIIIlIlIlIll, ColorUtils.Colors.RED);
                    lIIIIIlIlIlIll += 10;
                }
            }
        }
    }
}
