package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import java.awt.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;

public class EnchantColor extends Module
{
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Integer> blue;
    
    public static Color getColor(final long lllllIlllIll, final float lllllIllllll) {
        if (Xulu.MODULE_MANAGER.getModuleT(EnchantColor.class).mode.getValue().equalsIgnoreCase("Color")) {
            return new Color(Xulu.MODULE_MANAGER.getModuleT(EnchantColor.class).red.getValue(), Xulu.MODULE_MANAGER.getModuleT(EnchantColor.class).green.getValue(), Xulu.MODULE_MANAGER.getModuleT(EnchantColor.class).blue.getValue());
        }
        final float lllllIlllllI = (System.nanoTime() + lllllIlllIll) / 1.0E10f % 1.0f;
        final long lllllIllllIl = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(lllllIlllllI, 1.0f, 1.0f)), 16);
        final Color lllllIllllII = new Color((int)lllllIllllIl);
        return new Color(lllllIllllII.getRed() / 255.0f * lllllIllllll, lllllIllllII.getGreen() / 255.0f * lllllIllllll, lllllIllllII.getBlue() / 255.0f * lllllIllllll, lllllIllllII.getAlpha() / 255.0f);
    }
    
    public EnchantColor() {
        super("EnchantColor", "Changes the color of the enchantment effect", 0, Category.RENDER, true);
        this.mode = this.register(new Value<String>("Mode", this, "Color", new String[] { "Color", "Rainbow" }));
        this.red = this.register(new Value<Integer>("Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 255, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 255, 0, 255));
    }
}
