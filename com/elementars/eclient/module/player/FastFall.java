package com.elementars.eclient.module.player;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.movement.*;
import com.elementars.eclient.util.*;
import net.minecraft.entity.*;

public class FastFall extends Module
{
    public final /* synthetic */ Value<String> webMode;
    public final /* synthetic */ Value<String> fallMode;
    /* synthetic */ int delay;
    public final /* synthetic */ Value<Boolean> falling;
    public final /* synthetic */ Value<Boolean> webs;
    
    public FastFall() {
        super("FastFall", "Immediately hits terminal velocity when falling", 0, Category.PLAYER, true);
        this.falling = this.register(new Value<Boolean>("Fast Fall", this, true));
        this.webs = this.register(new Value<Boolean>("Fast Web", this, true));
        this.fallMode = this.register(new Value<String>("Fall Mode", this, "Normal", new String[] { "Normal", "2b2t" }));
        this.webMode = this.register(new Value<String>("Web Mode", this, "Normal", new String[] { "Normal", "2b2t" }));
    }
    
    @Override
    public void onUpdate() {
        if (this.delay > 0) {
            --this.delay;
        }
        if (this.webs.getValue() && FastFall.mc.player.isInWeb) {
            FastFall.mc.player.motionY = (this.webMode.getValue().equalsIgnoreCase("Normal") ? -3.9200038147008747 : -0.22000000000000003);
        }
        if (this.falling.getValue()) {
            if (FastFall.mc.player.motionY > -0.05999999865889549) {
                this.delay = 20;
            }
            if (FastFall.mc.player.fallDistance > 0.0f && FastFall.mc.player.fallDistance < 1.0f && this.delay == 0 && !Xulu.MODULE_MANAGER.getModule(Strafe.class).isToggled() && !EntityUtil.isInWater((Entity)FastFall.mc.player)) {
                FastFall.mc.player.motionY = (this.fallMode.getValue().equalsIgnoreCase("Normal") ? -3.9200038147008747 : -0.22000000000000003);
            }
        }
    }
}
