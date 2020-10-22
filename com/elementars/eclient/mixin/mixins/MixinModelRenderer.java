package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ModelRenderer.class })
public class MixinModelRenderer
{
    @Inject(method = { "render" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;callList(I)V", shift = At.Shift.BEFORE) })
    private void test(final float scale, final CallbackInfo ci) {
        if (ModelRenderer.class.cast(this) == Wrapper.getMinecraft().renderManager.playerRenderer.getMainModel().bipedRightArm) {
            if (Xulu.MODULE_MANAGER.getModule(ViewmodelChanger.class) != null && Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).hand.getValue() && Xulu.MODULE_MANAGER.getModule(ViewmodelChanger.class).isToggled()) {
                GlStateManager.scale((float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).sizex.getValue(), (float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).sizey.getValue(), (float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).sizez.getValue());
                GlStateManager.translate((float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).posX.getValue(), (float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).posY.getValue(), (float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).posZ.getValue());
            }
        }
        else if (ModelRenderer.class.cast(this) == Wrapper.getMinecraft().renderManager.playerRenderer.getMainModel().bipedRightArmwear && Xulu.MODULE_MANAGER.getModule(ViewmodelChanger.class) != null && Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).hand.getValue() && Xulu.MODULE_MANAGER.getModule(ViewmodelChanger.class).isToggled()) {
            GlStateManager.scale((float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).sizex.getValue(), (float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).sizey.getValue(), (float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).sizez.getValue());
            GlStateManager.translate((float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).posX.getValue(), (float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).posY.getValue(), (float)Xulu.MODULE_MANAGER.getModuleT(ViewmodelChanger.class).posZ.getValue());
        }
    }
}
