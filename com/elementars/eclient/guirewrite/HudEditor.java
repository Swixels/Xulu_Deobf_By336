package com.elementars.eclient.guirewrite;

import com.elementars.eclient.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.*;

public class HudEditor extends Module
{
    @Override
    public void onEnable() {
        HudEditor.mc.displayGuiScreen((GuiScreen)Xulu.hud);
        this.toggle();
    }
    
    @Override
    public void setup() {
        this.setKey(0);
    }
    
    public HudEditor() {
        super("HudEditor", "Editor for HUD elements", 0, Category.CORE, false);
    }
}
