package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import java.util.*;
import com.elementars.eclient.module.*;
import net.minecraftforge.client.event.*;
import com.elementars.eclient.*;
import java.awt.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;

public class AntiFog extends Module
{
    private final /* synthetic */ Value<Float> b;
    private final /* synthetic */ Value<Float> b1;
    private final /* synthetic */ Value<Float> g1;
    private final /* synthetic */ Value<Boolean> color;
    private final /* synthetic */ Value<Float> r2;
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<Float> r;
    private final /* synthetic */ Value<Float> g;
    private final /* synthetic */ Value<Float> r1;
    private final /* synthetic */ Value<Float> g2;
    private final /* synthetic */ Value<Float> b2;
    private final /* synthetic */ Value<Boolean> clear;
    
    public AntiFog() {
        super("AntiFog", "Prevents fog", 0, Category.RENDER, true);
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.r = this.register(new Value<Float>("Red", this, 1.0f, 0.0f, 1.0f));
        this.g = this.register(new Value<Float>("Green", this, 1.0f, 0.0f, 1.0f));
        this.b = this.register(new Value<Float>("Blue", this, 1.0f, 0.0f, 1.0f));
        this.r1 = this.register(new Value<Float>("Nether Red", this, 1.0f, 0.0f, 1.0f));
        this.g1 = this.register(new Value<Float>("Nether Green", this, 1.0f, 0.0f, 1.0f));
        this.b1 = this.register(new Value<Float>("Nether Blue", this, 1.0f, 0.0f, 1.0f));
        this.r2 = this.register(new Value<Float>("End Red", this, 1.0f, 0.0f, 1.0f));
        this.g2 = this.register(new Value<Float>("End Green", this, 1.0f, 0.0f, 1.0f));
        this.b2 = this.register(new Value<Float>("End Blue", this, 1.0f, 0.0f, 1.0f));
        this.clear = this.register(new Value<Boolean>("Remove fog", this, true));
        this.color = this.register(new Value<Boolean>("Color fog", this, true));
    }
    
    @SubscribeEvent
    public void onFogColor(final EntityViewRenderEvent.FogColors llllllllllllllllIlIlIIIlIlIIIIII) {
        if (this.color.getValue()) {
            if (this.rainbow.getValue()) {
                llllllllllllllllIlIlIIIlIlIIIIII.setRed(new Color(Xulu.rgb).getRed() / 255.0f);
                llllllllllllllllIlIlIIIlIlIIIIII.setGreen(new Color(Xulu.rgb).getGreen() / 255.0f);
                llllllllllllllllIlIlIIIlIlIIIIII.setBlue(new Color(Xulu.rgb).getBlue() / 255.0f);
            }
            else if (AntiFog.mc.player.dimension == 0) {
                llllllllllllllllIlIlIIIlIlIIIIII.setRed((float)this.r.getValue());
                llllllllllllllllIlIlIIIlIlIIIIII.setGreen((float)this.g.getValue());
                llllllllllllllllIlIlIIIlIlIIIIII.setBlue((float)this.b.getValue());
            }
            else if (AntiFog.mc.player.dimension == -1) {
                llllllllllllllllIlIlIIIlIlIIIIII.setRed((float)this.r1.getValue());
                llllllllllllllllIlIlIIIlIlIIIIII.setGreen((float)this.g1.getValue());
                llllllllllllllllIlIlIIIlIlIIIIII.setBlue((float)this.b1.getValue());
            }
            else if (AntiFog.mc.player.dimension == 1) {
                llllllllllllllllIlIlIIIlIlIIIIII.setRed((float)this.r2.getValue());
                llllllllllllllllIlIlIIIlIlIIIIII.setGreen((float)this.g2.getValue());
                llllllllllllllllIlIlIIIlIlIIIIII.setBlue((float)this.b2.getValue());
            }
        }
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onFogDensity(final EntityViewRenderEvent.FogDensity llllllllllllllllIlIlIIIlIlIIIlII) {
        if (this.clear.getValue()) {
            llllllllllllllllIlIlIIIlIlIIIlII.setDensity(0.0f);
            llllllllllllllllIlIlIIIlIlIIIlII.setCanceled(true);
        }
    }
}
