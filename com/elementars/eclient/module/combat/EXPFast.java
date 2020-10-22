package com.elementars.eclient.module.combat;

import net.minecraft.item.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;

public class EXPFast extends Module
{
    @Override
    public void onUpdate() {
        if (!this.isToggled()) {
            return;
        }
        if (Wrapper.getMinecraft().player.inventory.getCurrentItem().getItem() instanceof ItemExpBottle) {
            Wrapper.getMinecraft().rightClickDelayTimer = 0;
        }
    }
    
    public EXPFast() {
        super("EXPFast", "XP fast zoom", 0, Category.COMBAT, true);
    }
}
