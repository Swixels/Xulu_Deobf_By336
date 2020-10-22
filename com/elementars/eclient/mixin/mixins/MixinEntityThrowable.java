package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityThrowable.class })
public class MixinEntityThrowable
{
    @Inject(method = { "shoot(Lnet/minecraft/entity/Entity;FFFFF)V" }, at = { @At("HEAD") }, cancellable = true)
    private void shoot(final Entity entityThrower, final float rotationPitchIn, final float rotationYawIn, final float pitchOffset, final float velocity, final float inaccuracy, final CallbackInfo info) {
        final EventThrow eventThrow = new EventThrow(entityThrower, EntityThrowable.class.cast(this), rotationYawIn);
        eventThrow.call();
        if (eventThrow.isCancelled()) {
            info.cancel();
        }
    }
}
