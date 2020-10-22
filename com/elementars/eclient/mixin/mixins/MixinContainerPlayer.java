package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ContainerPlayer.class })
public class MixinContainerPlayer
{
    @Inject(method = { "onContainerClosed" }, at = { @At("HEAD") }, cancellable = true)
    public void getPlayerName(final EntityPlayer playerIn, final CallbackInfo ci) {
        final CloseInventoryEvent event = new CloseInventoryEvent();
        event.call();
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}
