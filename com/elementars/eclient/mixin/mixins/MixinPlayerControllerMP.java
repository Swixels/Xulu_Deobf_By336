package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.module.player.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.event.events.*;

@Mixin({ PlayerControllerMP.class })
public class MixinPlayerControllerMP
{
    @Redirect(method = { "onPlayerDamageBlock" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
    float getPlayerRelativeBlockHardness(final IBlockState state, final EntityPlayer player, final World worldIn, final BlockPos pos) {
        return state.getPlayerRelativeBlockHardness(player, worldIn, pos) * (TpsSync.isSync() ? (LagCompensator.INSTANCE.getTickRate() / 20.0f) : 1.0f);
    }
    
    @Inject(method = { "onPlayerDamageBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void test(final BlockPos posBlock, final EnumFacing directionFacing, final CallbackInfoReturnable info) {
        final EventPlayerDamageBlock eventPlayerDamageBlock = new EventPlayerDamageBlock(posBlock, directionFacing);
        eventPlayerDamageBlock.call();
        if (eventPlayerDamageBlock.isCancelled()) {
            info.cancel();
        }
    }
    
    @Inject(method = { "clickBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void onClickBlock(final BlockPos posBlock, final EnumFacing directionFacing, final CallbackInfoReturnable info) {
        final EventClickBlock eventClickBlock = new EventClickBlock(posBlock, directionFacing);
        eventClickBlock.call();
        if (eventClickBlock.isCancelled()) {
            info.cancel();
        }
    }
    
    @Inject(method = { "resetBlockRemoving" }, at = { @At("HEAD") }, cancellable = true)
    public void onBlockDestroyed(final CallbackInfo info) {
        final EventResetBlockRemoving eventDestroyBlock = new EventResetBlockRemoving();
        eventDestroyBlock.call();
        if (eventDestroyBlock.isCancelled()) {
            info.cancel();
        }
    }
}
