package com.elementars.eclient.mixin.mixins;

import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ Render.class })
public abstract class MixinRenderer<T extends Entity>
{
    @Shadow
    protected abstract boolean bindEntityTexture(final T p0);
    
    @Redirect(method = { "doRender" }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/Render;renderOutlines:Z"))
    public boolean doRender(final Render render) {
        return Xulu.MODULE_MANAGER.getModuleByName("OutlineESP").isToggled() && OutlineESP.krOE;
    }
}
