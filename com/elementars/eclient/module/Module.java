package com.elementars.eclient.module;

import com.elementars.eclient.util.*;
import dev.xulu.settings.*;
import net.minecraft.client.settings.*;
import com.elementars.eclient.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.guirewrite.elements.*;
import org.apache.logging.log4j.*;
import com.elementars.eclient.event.events.*;

public class Module implements Helper
{
    public final /* synthetic */ Value<Bind> bind;
    private /* synthetic */ boolean toggled;
    private /* synthetic */ Category category;
    private /* synthetic */ String displayName;
    public final /* synthetic */ Value<Animation> inAnimation;
    private /* synthetic */ boolean drawn;
    public static /* synthetic */ Module instance;
    private /* synthetic */ String desc;
    protected final /* synthetic */ Logger LOGGER;
    private /* synthetic */ String name;
    
    public void onRender() {
    }
    
    public boolean isToggledAnim() {
        return this.toggled || this.inAnimation.getValue() == Animation.DISABLE;
    }
    
    public String getDisplayName() {
        return (this.displayName == null) ? this.name : this.displayName;
    }
    
    public void onToggle() {
    }
    
    public void setDisplayName(final String lllllllllllllllllIIllIlIIIIllIll) {
        this.displayName = lllllllllllllllllIIllIlIIIIllIll;
    }
    
    public static Module getModule(final Class<? extends Module> lllllllllllllllllIIllIlIIIIIllII) {
        return Xulu.MODULE_MANAGER.getModule(lllllllllllllllllIIllIlIIIIIllII);
    }
    
    protected void sendRawDebugMessage(final String lllllllllllllllllIIllIIlllllllIl) {
        Command.sendRawChatMessage(String.valueOf(new StringBuilder().append("&b[").append(this.name).append("]:&r ").append(lllllllllllllllllIIllIIlllllllIl)));
    }
    
    public void setCategory(final Category lllllllllllllllllIIllIlIIIlIlIlI) {
        this.category = lllllllllllllllllIIllIlIIIlIlIlI;
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        this.onToggle();
        if (this.toggled) {
            if (FeatureList.animation.getValue() && (FeatureList.type.getValue().equalsIgnoreCase("Enable") || FeatureList.type.getValue().equalsIgnoreCase("Both"))) {
                this.inAnimation.setEnumValue("ENABLE");
            }
            this.onEnableR();
        }
        else {
            if (FeatureList.animation.getValue() && (FeatureList.type.getValue().equalsIgnoreCase("Disable") || FeatureList.type.getValue().equalsIgnoreCase("Both"))) {
                this.inAnimation.setEnumValue("DISABLE");
            }
            this.onDisableR();
        }
    }
    
    public static <T extends Module> T getModuleT(final Class<T> lllllllllllllllllIIllIlIIIIIlIIl) {
        return Xulu.MODULE_MANAGER.getModuleT(lllllllllllllllllIIllIlIIIIIlIIl);
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    protected void sendDebugMessage(final String lllllllllllllllllIIllIlIIIIIIlIl) {
        Command.sendChatMessage(String.valueOf(new StringBuilder().append("&b[").append(this.name).append("]:&r ").append(lllllllllllllllllIIllIlIIIIIIlIl)));
    }
    
    public void setKey(final int lllllllllllllllllIIllIlIIIllIIIl) {
        this.bind.getValue().setNum(lllllllllllllllllIIllIlIIIllIIIl);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Module(final String lllllllllllllllllIIllIlIIllIlIIl, final String lllllllllllllllllIIllIlIIllIlllI, final int lllllllllllllllllIIllIlIIllIIlll, final Category lllllllllllllllllIIllIlIIllIIllI, final boolean lllllllllllllllllIIllIlIIllIIlIl) {
        this.LOGGER = LogManager.getLogger("Xulu");
        this.bind = this.register(new Value<Bind>("Bind", this, new Bind(0)));
        this.inAnimation = new Value<Animation>("In Animation", this, Animation.NONE, Animation.values());
        this.name = lllllllllllllllllIIllIlIIllIlIIl;
        this.desc = lllllllllllllllllIIllIlIIllIlllI;
        this.bind.getValue().setNum(lllllllllllllllllIIllIlIIllIIlll);
        this.category = lllllllllllllllllIIllIlIIllIIllI;
        this.toggled = false;
        this.drawn = lllllllllllllllllIIllIlIIllIIlIl;
        this.setup();
        Module.instance = this;
    }
    
    public void onEnable() {
    }
    
    public void initToggle(final boolean lllllllllllllllllIIllIlIIlIlIlII) {
        this.toggled = lllllllllllllllllIIllIlIIlIlIlII;
        this.onToggle();
        if (this.toggled) {
            this.onEnableR();
        }
        else {
            this.onDisableR();
        }
    }
    
    public void setName(final String lllllllllllllllllIIllIlIIIllllIl) {
        this.name = lllllllllllllllllIIllIlIIIllllIl;
    }
    
    public String getHudInfo() {
        return null;
    }
    
    public void onUpdate() {
    }
    
    public int getKey() {
        return this.bind.getValue().getNum();
    }
    
    public void onDisable() {
    }
    
    public boolean isDrawn() {
        return this.drawn;
    }
    
    public void disable() {
        if (this.toggled) {
            this.toggle();
        }
    }
    
    public void onEnableR() {
        Xulu.EVENT_MANAGER.register(this);
        this.onEnable();
    }
    
    public <T> Value<T> register(final Value<T> lllllllllllllllllIIllIlIIIIlIIII) {
        Xulu.VALUE_MANAGER.register(lllllllllllllllllIIllIlIIIIlIIII);
        return lllllllllllllllllIIllIlIIIIlIIII;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public void onWorldRender(final RenderEvent lllllllllllllllllIIllIlIIIIlIlII) {
    }
    
    public void onDisableR() {
        Xulu.EVENT_MANAGER.unregister(this);
        this.onDisable();
    }
    
    public void setup() {
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void destroy() {
    }
    
    public void setDrawn(final boolean lllllllllllllllllIIllIlIIlIIlIIl) {
        this.drawn = lllllllllllllllllIIllIlIIlIIlIIl;
    }
    
    public enum Animation
    {
        ENABLE, 
        DISABLE, 
        NONE;
    }
}
