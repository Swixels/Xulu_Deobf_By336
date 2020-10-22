package com.elementars.eclient.mixin.mixins;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.*;
import com.elementars.eclient.event.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { RenderLivingBase.class }, priority = Integer.MAX_VALUE)
public abstract class MixinRenderLivingBase
{
    @Shadow
    protected ModelBase mainModel;
    
    @Redirect(method = { "renderModel" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void renderModelWrapper(final ModelBase modelBase, final Entity entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor) {
        final EventModelRender modelrenderpre = new EventModelRender(modelBase, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        modelrenderpre.setState(Event.State.PRE);
        modelrenderpre.call();
        if (modelrenderpre.isCancelled()) {
            return;
        }
        modelBase.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        final EventModelRender modelrenderpost = new EventModelRender(modelBase, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        modelrenderpost.setState(Event.State.POST);
        modelrenderpost.call();
    }
    
    @Inject(method = { "renderLayers" }, at = { @At("RETURN") })
    public void renderLayers(final EntityLivingBase entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleIn, final CallbackInfo ci) {
        final EventPostRenderLayers eventPostRenderLayers = new EventPostRenderLayers(RenderLivingBase.class.cast(this), this.mainModel, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
        eventPostRenderLayers.call();
    }
}
