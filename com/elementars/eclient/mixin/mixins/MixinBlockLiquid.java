package com.elementars.eclient.mixin.mixins;

import net.minecraftforge.fml.relauncher.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.block.state.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.module.misc.*;
import com.elementars.eclient.module.*;
import net.minecraft.init.*;
import com.elementars.eclient.util.*;
import net.minecraft.block.*;

@SideOnly(Side.CLIENT)
@Mixin(value = { BlockLiquid.class }, priority = 9999)
public class MixinBlockLiquid
{
    @Inject(method = { "modifyAcceleration" }, at = { @At("HEAD") }, cancellable = true)
    public void modifyAcceleration(final World worldIn, final BlockPos pos, final Entity entityIn, final Vec3d motion, final CallbackInfoReturnable<Vec3d> returnable) {
        if (Xulu.MODULE_MANAGER.getModuleByName("Velocity").isToggled()) {
            returnable.setReturnValue(motion);
            returnable.cancel();
        }
    }
    
    @Inject(method = { "canCollideCheck" }, at = { @At("RETURN") }, cancellable = true, require = 1)
    private void IcanCollide(final IBlockState state, final boolean hitIfLiquid, final CallbackInfoReturnable<Boolean> returnable) {
        returnable.setReturnValue(LiquidInteract.INSTANCE.isToggled());
    }
    
    @Inject(method = { "getCollisionBoundingBox" }, at = { @At("HEAD") }, cancellable = true)
    private void getCollision(final IBlockState blockState, final IBlockAccess worldIn, final BlockPos pos, final CallbackInfoReturnable<AxisAlignedBB> cir) {
        if (Xulu.MODULE_MANAGER.getModule(Avoid.class).isToggled() && Avoid.lava.getValue() && (blockState.getBlock() == Blocks.LAVA || blockState.getBlock() == Blocks.FLOWING_LAVA) && Wrapper.getMinecraft().world.getBlockState(new BlockPos(Wrapper.getMinecraft().player.getPositionVector()).add(0, 1, 0)).getBlock() != Blocks.LAVA && Wrapper.getMinecraft().world.getBlockState(new BlockPos(Wrapper.getMinecraft().player.getPositionVector()).add(0, 1, 0)).getBlock() != Blocks.FLOWING_LAVA) {
            cir.setReturnValue(Block.FULL_BLOCK_AABB);
            cir.cancel();
        }
    }
}
