package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiIngame.class })
public class MixinGuiIngame
{
    @Inject(method = { "renderPotionEffects" }, at = { @At("HEAD") }, cancellable = true)
    private void onRenderPotionEffects(final CallbackInfo info) {
        if (Xulu.VALUE_MANAGER.getValueByName("Hide Potions").getValue()) {
            info.cancel();
        }
    }
}
