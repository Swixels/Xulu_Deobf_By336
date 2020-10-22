package com.elementars.eclient.module.core;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.util.*;

public class Global extends Module
{
    public static /* synthetic */ Value<String> command2;
    public static /* synthetic */ Value<Integer> rainbowAmount;
    public static /* synthetic */ Value<Boolean> shortShadow;
    public static /* synthetic */ Value<Boolean> direction;
    public static /* synthetic */ Value<Boolean> coordinates;
    public static /* synthetic */ Value<Integer> rainbowLightness;
    public static /* synthetic */ Value<String> command1;
    public static /* synthetic */ Value<String> lagColor;
    public static /* synthetic */ Value<Integer> rainbowspeed;
    public static /* synthetic */ Value<Boolean> textShadow;
    public static /* synthetic */ Value<Integer> hudAlpha;
    public static /* synthetic */ Value<Integer> rainbowSaturation;
    public static /* synthetic */ Value<String> command3;
    public static /* synthetic */ Value<Integer> rainbowspeed2;
    public static /* synthetic */ Value<Boolean> showLag;
    
    public Global() {
        super("Global", "Stores global settings", 0, Category.CORE, false);
        Global.rainbowAmount = this.register(new Value<Integer>("Gradient Amt", this, 5, 1, 20));
        Global.hudAlpha = this.register(new Value<Integer>("Hud Alpha", this, 255, 0, 255));
        Global.direction = this.register(new Value<Boolean>("Direction", this, true));
        Global.coordinates = this.register(new Value<Boolean>("Coordinates", this, false));
        Global.showLag = this.register(new Value<Boolean>("Show Lag", this, true));
        Global.lagColor = this.register(new Value<String>("Lag Color", this, "Default", new String[] { "Default", "Gui Color" }));
        this.register(new Value<Object>("Rainbow Watermark", this, false));
        this.register(new Value<Object>("Hide Potions", this, true));
        Global.rainbowspeed2 = this.register(new Value<Integer>("Rainbow Speed", this, 5, 1, 100));
        Global.rainbowspeed = this.register(new Value<Integer>("Block Rainbow Speed", this, 2, 1, 50));
        Global.rainbowSaturation = this.register(new Value<Integer>("Rainbow Sat.", this, 255, 0, 255));
        Global.rainbowLightness = this.register(new Value<Integer>("Rainbow Light.", this, 255, 0, 255));
        Global.textShadow = this.register(new Value<Boolean>("Text Shadow", this, true));
        Global.shortShadow = this.register(new Value<Boolean>("Short Shadow", this, false));
        Global.command1 = this.register(new Value<String>("Watermark Color", this, "Purple", ColorTextUtils.colors));
        Global.command2 = this.register(new Value<String>("Bracket Color", this, "Purple", ColorTextUtils.colors));
        Global.command3 = this.register(new Value<String>("Bracket Type", this, "[]", new String[] { "[]", "<>", "()", "{}", "-==-" }));
    }
    
    @Override
    public void onEnable() {
        this.disable();
    }
}
