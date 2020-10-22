package com.elementars.eclient.mixin.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import org.spongepowered.asm.mixin.*;
import javax.annotation.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.util.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.cape.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ AbstractClientPlayer.class })
public abstract class MixinAbstractClientPlayer extends MixinEntityPlayer
{
    private Minecraft minecraft;
    
    public MixinAbstractClientPlayer() {
        this.minecraft = Minecraft.getMinecraft();
    }
    
    @Shadow
    @Nullable
    protected abstract NetworkPlayerInfo getPlayerInfo();
    
    @Inject(method = { "getLocationCape" }, at = { @At("HEAD") }, cancellable = true)
    public void getLocationCape(final CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
        if (Cape.isEnabled()) {
            final NetworkPlayerInfo info = this.getPlayerInfo();
            if (info != null && Capes.isCapeUser(info.getGameProfile().getName())) {
                callbackInfoReturnable.setReturnValue(new ResourceLocation("textures/eclient/cape.png"));
            }
        }
    }
}
