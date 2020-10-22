package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.elementars.eclient.module.*;

public class FovSlider extends Module
{
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Integer> FOV;
    private /* synthetic */ float fov;
    
    @Override
    public void onDisable() {
        FovSlider.EVENT_BUS.unregister((Object)this);
        FovSlider.mc.gameSettings.fovSetting = this.fov;
    }
    
    @SubscribeEvent
    public void fovOn(final EntityViewRenderEvent.FOVModifier llllIIlIlllllIl) {
        if (this.mode.getValue().equalsIgnoreCase("Hand Changer")) {
            llllIIlIlllllIl.setFOV((float)this.FOV.getValue());
        }
    }
    
    @Override
    public void onUpdate() {
        if (!this.isToggled() || FovSlider.mc.world == null) {
            return;
        }
        if (this.mode.getValue().equalsIgnoreCase("Fov Changer")) {
            FovSlider.mc.gameSettings.fovSetting = this.FOV.getValue();
        }
    }
    
    @Override
    public void onEnable() {
        FovSlider.EVENT_BUS.register((Object)this);
        this.fov = FovSlider.mc.gameSettings.fovSetting;
    }
    
    public FovSlider() {
        super("FovSlider", "Better FOV slider", 0, Category.MISC, true);
        this.FOV = this.register(new Value<Integer>("FOV", this, 110, 90, 200));
        this.mode = this.register(new Value<String>("Mode", this, "Fov Changer", new String[] { "Fov Changer", "Hand Changer" }));
    }
}
