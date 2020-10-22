package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ BlockModelRenderer.class })
public class MixinBlockModelRenderer
{
    @Inject(method = { "renderModel(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/renderer/BufferBuilder;ZJ)Z" }, at = { @At("HEAD") }, cancellable = true)
    public void renderModel(final IBlockAccess worldIn, final IBakedModel modelIn, final IBlockState stateIn, final BlockPos posIn, final BufferBuilder buffer, final boolean checkSides, final long rand, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        final EventRenderBlock eventRenderBlock = new EventRenderBlock(worldIn, modelIn, stateIn, posIn, buffer, checkSides, rand);
        eventRenderBlock.call();
        if (eventRenderBlock.isCancelled()) {
            callbackInfoReturnable.setReturnValue(eventRenderBlock.isRenderable());
        }
    }
}
