package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.state.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.misc.*;
import com.elementars.eclient.module.*;
import net.minecraft.block.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ BlockCactus.class })
public class MixinBlockCactus
{
    @Inject(method = { "getCollisionBoundingBox" }, at = { @At("HEAD") }, cancellable = true)
    private void getCollision(final IBlockState blockState, final IBlockAccess worldIn, final BlockPos pos, final CallbackInfoReturnable<AxisAlignedBB> cir) {
        if (Xulu.MODULE_MANAGER.getModule(Avoid.class).isToggled() && Avoid.cactus.getValue()) {
            cir.setReturnValue(Block.FULL_BLOCK_AABB);
            cir.cancel();
        }
    }
}
