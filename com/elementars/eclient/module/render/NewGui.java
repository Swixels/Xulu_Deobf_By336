package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import java.util.*;
import com.elementars.eclient.*;
import net.minecraft.client.gui.*;
import dev.xulu.newgui.*;

public class NewGui extends Module
{
    public static /* synthetic */ Value<Integer> blue;
    public static /* synthetic */ Value<Boolean> rainbowgui;
    public static /* synthetic */ Value<Integer> bgAlpha;
    public static /* synthetic */ Value<Integer> red;
    public static /* synthetic */ Value<Boolean> outline;
    public static /* synthetic */ Value<String> sliderSetting;
    public static /* synthetic */ Value<Boolean> customfont;
    public static /* synthetic */ Value<Integer> green;
    public static /* synthetic */ Value<String> moduleSetting;
    public static /* synthetic */ Value<String> toggleSetting;
    public static /* synthetic */ Value<Integer> rainbowspeed;
    public static /* synthetic */ Value<Boolean> blur;
    public static /* synthetic */ Value<Boolean> resetGui;
    
    public NewGui() {
        super("NewGui", "New gui for the client", 21, Category.CORE, false);
        NewGui.resetGui = this.register(new Value<Boolean>("Reset Gui", this, false));
        NewGui.customfont = this.register(new Value<Boolean>("Custom Font", this, false));
        NewGui.rainbowgui = this.register(new Value<Boolean>("Rainbow ClickGui", this, false));
        NewGui.rainbowspeed = this.register(new Value<Integer>("Rainbow Speed", this, 20, 1, 50));
        NewGui.blur = this.register(new Value<Boolean>("Blur", this, true));
        NewGui.outline = this.register(new Value<Boolean>("Outline", this, true));
        NewGui.moduleSetting = this.register(new Value<String>("Module Setting", this, "Normal", new ArrayList<String>(Arrays.asList("Normal", "MiniButton", "Text"))));
        NewGui.toggleSetting = this.register(new Value<String>("Toggle Setting", this, "Full-box", new ArrayList<String>(Arrays.asList("Checkbox", "Full-box"))));
        NewGui.sliderSetting = this.register(new Value<String>("Slider Setting", this, "Box", new ArrayList<String>(Arrays.asList("Line", "Box"))));
        NewGui.bgAlpha = this.register(new Value<Integer>("Background alpha", this, 130, 0, 255));
        NewGui.red = this.register(new Value<Integer>("GuiRed", this, 255, 0, 255));
        NewGui.green = this.register(new Value<Integer>("GuiGreen", this, 26, 0, 255));
        NewGui.blue = this.register(new Value<Integer>("GuiBlue", this, 42, 0, 255));
    }
    
    @Override
    public void onEnable() {
        NewGui.mc.displayGuiScreen((GuiScreen)Xulu.newGUI);
        this.toggle();
    }
    
    public static void resetGui() {
        if (NewGui.resetGui.getValue()) {
            int lllIIlllIlIllII = 10;
            for (final Panel lllIIlllIlIlllI : NewGUI.getPanels()) {
                lllIIlllIlIlllI.x = 10.0;
                lllIIlllIlIlllI.y = lllIIlllIlIllII;
                lllIIlllIlIllII += 23;
            }
            NewGui.resetGui.setValue(false);
        }
    }
}
