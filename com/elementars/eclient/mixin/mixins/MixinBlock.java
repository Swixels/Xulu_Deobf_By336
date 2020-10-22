package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.module.render.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

@Mixin(value = { Block.class }, priority = 9999)
public class MixinBlock
{
    @Inject(method = { "isFullCube" }, at = { @At("HEAD") }, cancellable = true)
    public void isFullCube(final IBlockState state, final CallbackInfoReturnable<Boolean> callback) {
        try {
            if (Xray.INSTANCE != null && Xray.INSTANCE.isToggled()) {
                callback.setReturnValue(Xray.shouldXray(Block.class.cast(this)));
                callback.cancel();
            }
        }
        catch (Exception ex) {}
    }
    
    @Inject(method = { "shouldSideBeRendered" }, at = { @At("HEAD") }, cancellable = true)
    public void shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side, final CallbackInfoReturnable<Boolean> callback) {
        try {
            if (Xray.INSTANCE != null && Xray.INSTANCE.isToggled()) {
                callback.setReturnValue(Xray.shouldXray(Block.class.cast(this)));
            }
        }
        catch (Exception ex) {}
    }
    
    @Inject(method = { "isOpaqueCube" }, at = { @At("HEAD") }, cancellable = true)
    public void isOpaqueCube(final IBlockState state, final CallbackInfoReturnable<Boolean> callback) {
        try {
            if (Xray.INSTANCE != null && Xray.INSTANCE.isToggled()) {
                callback.setReturnValue(Xray.shouldXray(Block.class.cast(this)));
            }
        }
        catch (Exception ex) {}
    }
}
