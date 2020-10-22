package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.multiplayer.*;
import com.elementars.eclient.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.module.render.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import com.google.common.base.*;
import com.elementars.eclient.module.misc.*;
import java.util.*;

@Mixin(value = { EntityRenderer.class }, priority = Integer.MAX_VALUE)
public class MixinEntityRenderer
{
    private boolean nightVision;
    
    public MixinEntityRenderer() {
        this.nightVision = false;
    }
    
    @Redirect(method = { "orientCamera" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"))
    public RayTraceResult rayTraceBlocks(final WorldClient world, final Vec3d start, final Vec3d end) {
        if (Xulu.MODULE_MANAGER.getModuleByName("CameraClip").isToggled()) {
            return null;
        }
        return world.rayTraceBlocks(start, end);
    }
    
    @Inject(method = { "hurtCameraEffect" }, at = { @At("HEAD") }, cancellable = true)
    public void hurtCameraEffect(final float ticks, final CallbackInfo info) {
        if (NoHurtCam.shouldDisable()) {
            info.cancel();
        }
    }
    
    @Redirect(method = { "getMouseOver" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List<Entity> getEntitiesInAABBexcluding(final WorldClient worldClient, final Entity entityIn, final AxisAlignedBB boundingBox, final Predicate predicate) {
        if (NoEntityTrace.shouldBlock()) {
            return new ArrayList<Entity>();
        }
        return (List<Entity>)worldClient.getEntitiesInAABBexcluding(entityIn, boundingBox, predicate);
    }
}
