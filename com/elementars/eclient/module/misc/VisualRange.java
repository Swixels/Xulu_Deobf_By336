package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import net.minecraft.entity.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.util.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import java.util.function.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class VisualRange extends Module
{
    public /* synthetic */ ArrayList<String> names;
    private final /* synthetic */ Value<Boolean> color;
    private final /* synthetic */ Value<Boolean> mode;
    public /* synthetic */ ArrayList<String> removal;
    private final /* synthetic */ Value<Boolean> ignoreFriends;
    private final /* synthetic */ Value<Boolean> sf;
    private final /* synthetic */ Value<Boolean> vr;
    private final /* synthetic */ Value<String> selectcolor;
    public /* synthetic */ ArrayList<String> names2;
    private /* synthetic */ int delay;
    private final /* synthetic */ Value<Integer> delayN;
    private final /* synthetic */ Value<String> message;
    private final /* synthetic */ Value<Boolean> watermark;
    
    private void testLeave() {
        this.names.forEach(lIIllIlIllIllI -> {
            if (!this.names2.contains(lIIllIlIllIllI)) {
                this.removal.add(lIIllIlIllIllI);
            }
            return;
        });
        this.removal.forEach(lIIllIlIlllIlI -> this.names.remove(lIIllIlIlllIlI));
        this.removal.clear();
    }
    
    private void sendMessage(final Entity lIIllIllIIIIlI) {
        if (this.mode.getValue() && this.delay == 0) {
            if (this.sf.getValue() && Friends.isFriend(lIIllIllIIIIlI.getName())) {
                return;
            }
            VisualRange.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append("/msg ").append(lIIllIllIIIIlI.getName()).append(" ").append(this.message.getValue())));
            this.delay = this.delayN.getValue() * 20;
        }
        if (this.vr.getValue()) {
            if (this.ignoreFriends.getValue() && Friends.isFriend(lIIllIllIIIIlI.getName())) {
                return;
            }
            if (this.watermark.getValue()) {
                if (this.color.getValue()) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.selectcolor.getValue())).append(lIIllIllIIIIlI.getName()).append(" entered visual range")));
                }
                else {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(lIIllIllIIIIlI.getName()).append(" entered visual range")));
                }
            }
            else if (this.color.getValue()) {
                Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.selectcolor.getValue())).append(lIIllIllIIIIlI.getName()).append(" entered visual range")));
            }
            else {
                Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(lIIllIllIIIIlI.getName()).append(" entered visual range")));
            }
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.delay > 0) {
            --this.delay;
        }
    }
    
    @Override
    public void onWorldRender(final RenderEvent lIIllIllIlIIII) {
        this.names2.clear();
        Minecraft.getMinecraft().world.loadedEntityList.stream().filter(EntityUtil::isLiving).filter(lIIllIlIlIllII -> !EntityUtil.isFakeLocalPlayer(lIIllIlIlIllII)).filter(lIIllIlIlIlllI -> lIIllIlIlIlllI instanceof EntityPlayer).filter(lIIllIlIllIIlI -> !(lIIllIlIllIIlI instanceof EntityPlayerSP)).forEach(this::testName);
        this.testLeave();
    }
    
    private void testName(final Entity lIIllIllIIlIIl) {
        this.names2.add(lIIllIllIIlIIl.getName());
        if (!this.names.contains(lIIllIllIIlIIl.getName())) {
            this.sendMessage(lIIllIllIIlIIl);
            this.names.add(lIIllIllIIlIIl.getName());
        }
    }
    
    public VisualRange() {
        super("VisualRange", "Alerts people appearing in your visual range", 0, Category.MISC, true);
        this.mode = this.register(new Value<Boolean>("Send Message", this, false));
        this.sf = this.register(new Value<Boolean>("No Friend Send", this, false));
        this.message = this.register(new Value<String>("Message", this, "hello uwu", new ArrayList<String>(Arrays.asList("Change this in the settings"))));
        this.delayN = this.register(new Value<Integer>("Delay", this, 15, 1, 30));
        this.ignoreFriends = this.register(new Value<Boolean>("Ignore Friends", this, false));
        this.vr = this.register(new Value<Boolean>("VisualRange", this, true));
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.color = this.register(new Value<Boolean>("Color", this, false));
        this.selectcolor = this.register(new Value<String>("Color Pick", this, "LightGreen", ColorTextUtils.colors));
        this.names = new ArrayList<String>();
        this.names2 = new ArrayList<String>();
        this.removal = new ArrayList<String>();
    }
}
