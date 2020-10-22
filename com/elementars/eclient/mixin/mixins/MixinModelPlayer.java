package com.elementars.eclient.mixin.mixins;

import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.util.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.module.render.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.model.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;

@Mixin(value = { ModelPlayer.class }, priority = 9999)
public class MixinModelPlayer
{
    @Shadow
    public void render(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
    }
    
    @Inject(method = { "setRotationAngles" }, at = { @At("RETURN") })
    public void setRotationAngles(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn, final CallbackInfo callbackInfo) {
        if (Wrapper.getMinecraft().world != null && Wrapper.getMinecraft().player != null && entityIn instanceof EntityPlayer) {
            Skeleton.addEntity((EntityPlayer)entityIn, (ModelPlayer)this);
        }
    }
    
    @Inject(method = { "render" }, at = { @At("HEAD") }, cancellable = true)
    private void renderPre(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final CallbackInfo ci) {
        final EventModelPlayerRender modelrenderpre = new EventModelPlayerRender((ModelBase)ModelPlayer.class.cast(this), entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        modelrenderpre.setState(Event.State.PRE);
        modelrenderpre.call();
        if (modelrenderpre.isCancelled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "render" }, at = { @At("RETURN") })
    private void renderPost(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final CallbackInfo ci) {
        final EventModelPlayerRender modelrenderpost = new EventModelPlayerRender((ModelBase)ModelPlayer.class.cast(this), entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        modelrenderpost.setState(Event.State.POST);
        modelrenderpost.call();
    }
}
