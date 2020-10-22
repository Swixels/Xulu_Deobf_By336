package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.item.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.module.render.*;
import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;

@Mixin({ RenderEntityItem.class })
public class MixinRenderEntityItem
{
    @Inject(method = { "doRender" }, at = { @At("HEAD") })
    private void injectChamsPre(final EntityItem entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo info) {
        if (ItemESP.INSTANCE.isToggled() && ItemESP.INSTANCE.mode.getValue().equalsIgnoreCase("chams")) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -1000000.0f);
        }
    }
    
    @Inject(method = { "doRender" }, at = { @At("RETURN") })
    private <S extends EntityLivingBase> void injectChamsPost(final EntityItem entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo info) {
        if (ItemESP.INSTANCE.isToggled() && ItemESP.INSTANCE.mode.getValue().equalsIgnoreCase("chams")) {
            GL11.glPolygonOffset(1.0f, 1000000.0f);
            GL11.glDisable(32823);
        }
    }
}
