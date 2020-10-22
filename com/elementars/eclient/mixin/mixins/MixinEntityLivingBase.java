package com.elementars.eclient.mixin.mixins;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ EntityLivingBase.class })
public abstract class MixinEntityLivingBase extends MixinEntity
{
    @Shadow
    public void swingArm(final EnumHand hand) {
    }
}
