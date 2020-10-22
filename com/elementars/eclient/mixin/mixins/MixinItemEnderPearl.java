package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ItemEnderPearl.class })
public class MixinItemEnderPearl
{
    @Inject(method = { "onItemRightClick" }, at = { @At("HEAD") }, cancellable = true)
    private void useitemrightclick(final World worldIn, final EntityPlayer playerIn, final EnumHand hand, final CallbackInfoReturnable info) {
        final EventUseItem event = new EventUseItem(playerIn);
        event.call();
    }
}
