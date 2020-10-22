package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.init.*;

public class NoEntityTrace extends Module
{
    private final /* synthetic */ Value<Boolean> pickaxe;
    private final /* synthetic */ Value<String> mode;
    private static /* synthetic */ NoEntityTrace INSTANCE;
    
    public NoEntityTrace() {
        super("NoEntityTrace", "Keeps mining through an entity", 0, Category.MISC, true);
        this.mode = this.register(new Value<String>("Mode", this, "Dynamic", new ArrayList<String>(Arrays.asList("Dynamic", "Static"))));
        this.pickaxe = this.register(new Value<Boolean>("Pickaxe Only", this, true));
        NoEntityTrace.INSTANCE = this;
    }
    
    public static boolean shouldBlock() {
        return NoEntityTrace.INSTANCE.isToggled() && (NoEntityTrace.INSTANCE.mode.getValue().equalsIgnoreCase("Static") || NoEntityTrace.mc.playerController.isHittingBlock) && (!NoEntityTrace.INSTANCE.pickaxe.getValue() || NoEntityTrace.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE);
    }
}
