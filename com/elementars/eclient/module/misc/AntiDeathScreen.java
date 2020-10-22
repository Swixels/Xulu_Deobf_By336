package com.elementars.eclient.module.misc;

import com.elementars.eclient.module.*;
import net.minecraft.client.gui.*;

public class AntiDeathScreen extends Module
{
    public AntiDeathScreen() {
        super("AntiDeathScreen", "AntiDeathScreen apparently", 0, Category.MISC, true);
    }
    
    @Override
    public void onUpdate() {
        if (this.isToggled() && AntiDeathScreen.mc.currentScreen instanceof GuiGameOver && AntiDeathScreen.mc.player.getHealth() > 0.0f) {
            AntiDeathScreen.mc.player.respawnPlayer();
            AntiDeathScreen.mc.displayGuiScreen((GuiScreen)null);
        }
    }
}
