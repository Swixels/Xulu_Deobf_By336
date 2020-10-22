package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.layers.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.module.render.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.inventory.*;

@Mixin({ LayerArmorBase.class })
public class MixinLayerArmorBase
{
    @Redirect(method = { "renderEnchantedGlint" }, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.color(FFFF)V", ordinal = 1))
    private static void renderEnchantedGlint(final float red, final float green, final float blue, final float alpha) {
        GlStateManager.color(Xulu.MODULE_MANAGER.getModule(EnchantColor.class).isToggled() ? ((float)EnchantColor.getColor(1L, 1.0f).getRed()) : red, Xulu.MODULE_MANAGER.getModule(EnchantColor.class).isToggled() ? ((float)EnchantColor.getColor(1L, 1.0f).getGreen()) : green, Xulu.MODULE_MANAGER.getModule(EnchantColor.class).isToggled() ? ((float)EnchantColor.getColor(1L, 1.0f).getBlue()) : blue, Xulu.MODULE_MANAGER.getModule(EnchantColor.class).isToggled() ? ((float)EnchantColor.getColor(1L, 1.0f).getAlpha()) : alpha);
    }
    
    @Inject(method = { "renderEnchantedGlint" }, at = { @At("HEAD") }, cancellable = true)
    private static void renderGlint(final RenderLivingBase<?> p_188364_0_, final EntityLivingBase p_188364_1_, final ModelBase model, final float p_188364_3_, final float p_188364_4_, final float p_188364_5_, final float p_188364_6_, final float p_188364_7_, final float p_188364_8_, final float p_188364_9_, final CallbackInfo info) {
        if (Xulu.MODULE_MANAGER.getModuleT(NoRender.class).armor.getValue() && Xulu.MODULE_MANAGER.getModule(NoRender.class).isToggled()) {
            info.cancel();
        }
    }
    
    @Inject(method = { "renderArmorLayer" }, at = { @At("HEAD") }, cancellable = true)
    private void renderArmorLayer(final EntityLivingBase entityLivingBaseIn, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final EntityEquipmentSlot slotIn, final CallbackInfo info) {
        if (Xulu.MODULE_MANAGER.getModuleT(NoRender.class).armor.getValue() && Xulu.MODULE_MANAGER.getModule(NoRender.class).isToggled()) {
            info.cancel();
        }
    }
    
    @Redirect(method = { "renderArmorLayer" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", ordinal = 1))
    private void color(final float red, final float green, final float blue, final float alpha) {
        final NoRender noRender = Xulu.MODULE_MANAGER.getModuleT(NoRender.class);
        if (noRender.armorTrans.getValue() && noRender.isToggled()) {
            GlStateManager.color(red, green, blue, noRender.alpha.getValue() / 255.0f);
        }
        else {
            GlStateManager.color(red, green, blue, alpha);
        }
    }
    
    @Redirect(method = { "renderArmorLayer" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", ordinal = 2))
    private void color2(final float red, final float green, final float blue, final float alpha) {
        final NoRender noRender = Xulu.MODULE_MANAGER.getModuleT(NoRender.class);
        if (noRender.armorTrans.getValue() && noRender.isToggled()) {
            GlStateManager.color(red, green, blue, noRender.alpha.getValue() / 255.0f);
        }
        else {
            GlStateManager.color(red, green, blue, alpha);
        }
    }
}
