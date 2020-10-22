package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;

public class Avoid extends Module
{
    public static /* synthetic */ Value<Boolean> lava;
    public static /* synthetic */ Value<Boolean> fire;
    public static /* synthetic */ Value<Boolean> webs;
    public static /* synthetic */ Value<Boolean> cactus;
    
    public Avoid() {
        super("Avoid", "Avoids interactions with certain things", 0, Category.MISC, true);
        Avoid.cactus = this.register(new Value<Boolean>("Cactus", this, true));
        Avoid.fire = this.register(new Value<Boolean>("Fire", this, true));
        Avoid.lava = this.register(new Value<Boolean>("Lava", this, true));
        Avoid.webs = this.register(new Value<Boolean>("Webs", this, true));
    }
}
