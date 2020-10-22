package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.*;
import dev.xulu.clickgui.*;
import net.minecraft.client.gui.*;

public class ExeterGui extends Module
{
    private final /* synthetic */ Value<Integer> rainbowspeed;
    private final /* synthetic */ Value<Boolean> customFont;
    private final /* synthetic */ Value<Boolean> sound;
    private final /* synthetic */ Value<Boolean> rainbow;
    
    public ExeterGui() {
        super("ExeterGui", "Exeter Gui", 0, Category.CORE, true);
        this.customFont = this.register(new Value<Boolean>("Custom Font", this, false));
        this.sound = this.register(new Value<Boolean>("Sound", this, true));
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.rainbowspeed = this.register(new Value<Integer>("Rainbow Speed", this, 20, 1, 50));
    }
    
    public static int getSpeed() {
        return Xulu.MODULE_MANAGER.getModuleT(ExeterGui.class).rainbowspeed.getValue();
    }
    
    public static boolean getRainbow() {
        return Xulu.MODULE_MANAGER.getModuleT(ExeterGui.class).rainbow.getValue();
    }
    
    public static boolean getSound() {
        return Xulu.MODULE_MANAGER.getModuleT(ExeterGui.class).sound.getValue();
    }
    
    public static boolean getCF() {
        return Xulu.MODULE_MANAGER.getModuleT(ExeterGui.class).customFont.getValue();
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        ExeterGui.mc.displayGuiScreen((GuiScreen)ClickGui.getClickGui());
        this.toggle();
    }
}
