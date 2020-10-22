package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;

public class ViewmodelChanger extends Module
{
    public final /* synthetic */ Value<HandMode> mode;
    public final /* synthetic */ Value<Float> y;
    public final /* synthetic */ Value<Float> alpha;
    public final /* synthetic */ Value<Float> x;
    public final /* synthetic */ Value<Float> sizey;
    public final /* synthetic */ Value<Float> posY;
    public final /* synthetic */ Value<Boolean> pause;
    public final /* synthetic */ Value<Boolean> hand;
    public final /* synthetic */ Value<Float> posZ;
    public final /* synthetic */ Value<Float> sizez;
    public final /* synthetic */ Value<Float> z;
    public final /* synthetic */ Value<Float> posX;
    public final /* synthetic */ Value<Boolean> item;
    public final /* synthetic */ Value<Float> sizex;
    
    public ViewmodelChanger() {
        super("ViewmodelChanger", "Tweaks the players hand", 0, Category.RENDER, true);
        this.mode = this.register(new Value<HandMode>("Mode", this, HandMode.MAINHAND, HandMode.values()));
        this.pause = this.register(new Value<Boolean>("Pause On Eat", this, false));
        this.hand = this.register(new Value<Boolean>("Hand", this, true));
        this.item = this.register(new Value<Boolean>("Item", this, true));
        this.sizex = this.register(new Value<Float>("Size X", this, 1.0f, 0.0f, 2.0f));
        this.sizey = this.register(new Value<Float>("Size Y", this, 1.0f, 0.0f, 2.0f));
        this.sizez = this.register(new Value<Float>("Size Z", this, 1.0f, 0.0f, 2.0f));
        this.x = this.register(new Value<Float>("x", this, 1.0f, 0.0f, 1.0f));
        this.y = this.register(new Value<Float>("y", this, 1.0f, 0.0f, 1.0f));
        this.z = this.register(new Value<Float>("z", this, 1.0f, 0.0f, 1.0f));
        this.posX = this.register(new Value<Float>("X offset", this, 0.0f, -2.0f, 2.0f));
        this.posY = this.register(new Value<Float>("Y offset", this, 0.0f, -2.0f, 2.0f));
        this.posZ = this.register(new Value<Float>("Z offset", this, 0.0f, -2.0f, 2.0f));
        this.alpha = this.register(new Value<Float>("Alpha", this, 1.0f, 0.0f, 1.0f));
    }
    
    @Override
    public void onEnable() {
        ViewmodelChanger.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        ViewmodelChanger.EVENT_BUS.unregister((Object)this);
    }
    
    public enum HandMode
    {
        MAINHAND, 
        BOTH, 
        OFFHAND;
        
        public boolean isOK(final boolean llllllllllllllllllIlllllIllIlIIl) {
            switch (this) {
                case MAINHAND: {
                    return !llllllllllllllllllIlllllIllIlIIl;
                }
                case OFFHAND: {
                    return llllllllllllllllllIlllllIllIlIIl;
                }
                case BOTH: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
    }
}
