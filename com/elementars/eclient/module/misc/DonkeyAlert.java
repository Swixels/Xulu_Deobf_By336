package com.elementars.eclient.module.misc;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.module.*;

public class DonkeyAlert extends Module
{
    /* synthetic */ int delay;
    
    @Override
    public void onUpdate() {
        if (this.delay > 0) {
            --this.delay;
        }
        DonkeyAlert.mc.world.loadedEntityList.forEach(lllllllllllllllllIlIlllllIlIIlII -> {
            if (lllllllllllllllllIlIlllllIlIIlII instanceof EntityDonkey && this.delay == 0) {
                Command.sendChatMessage("Donkey spotted!");
                this.delay = 200;
            }
        });
    }
    
    public DonkeyAlert() {
        super("DonkeyAlert", "Alerts you when a donkey is in your render distance", 0, Category.MISC, true);
    }
    
    @Override
    public void onEnable() {
        this.delay = 0;
    }
}
