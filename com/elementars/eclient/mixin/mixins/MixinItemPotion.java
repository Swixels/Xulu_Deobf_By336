package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ItemPotion.class })
public class MixinItemPotion
{
    @Inject(method = { "onItemUseFinish" }, at = { @At("HEAD") }, cancellable = true)
    private void onItemUseFinish(final ItemStack stack, final World worldIn, final EntityLivingBase entityLiving, final CallbackInfoReturnable info) {
        final EventDrinkPotion event = new EventDrinkPotion(entityLiving, stack);
        event.call();
    }
}
