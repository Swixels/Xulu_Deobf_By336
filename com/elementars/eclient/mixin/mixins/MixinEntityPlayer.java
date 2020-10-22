package com.elementars.eclient.mixin.mixins;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ EntityPlayer.class })
public abstract class MixinEntityPlayer extends MixinEntityLivingBase
{
    @Shadow
    public InventoryEnderChest enderChest;
}
