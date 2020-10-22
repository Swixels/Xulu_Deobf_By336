package com.elementars.eclient.mixin.mixins;

import net.minecraft.client.shader.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.misc.*;
import com.elementars.eclient.module.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.culling.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.*;
import com.elementars.eclient.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.util.*;

@Mixin(value = { RenderGlobal.class }, priority = 9999)
public class MixinRenderGlobal
{
    @Shadow
    public ShaderGroup entityOutlineShader;
    @Shadow
    public boolean entityOutlinesRendered;
    @Shadow
    public WorldClient world;
    
    @Redirect(method = { "broadcastSound" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;playSound(DDDLnet/minecraft/util/SoundEvent;Lnet/minecraft/util/SoundCategory;FFZ)V"))
    private void playWitherSpawn(final WorldClient worldClient, final double x, final double y, final double z, final SoundEvent soundIn, final SoundCategory category, final float volume, final float pitch, final boolean distanceDelay) {
        if (!Xulu.MODULE_MANAGER.getModule(AntiSound.class).isToggled() || !Xulu.MODULE_MANAGER.getModuleT(AntiSound.class).witherSpawn.getValue()) {
            this.world.playSound(x, y, z, soundIn, category, volume, pitch, distanceDelay);
        }
    }
    
    @Redirect(method = { "playEvent" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;playSound(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/SoundEvent;Lnet/minecraft/util/SoundCategory;FFZ)V", ordinal = 22))
    private void playWitherShoot(final WorldClient worldClient, final BlockPos pos, final SoundEvent soundIn, final SoundCategory category, final float volume, final float pitch, final boolean distanceDelay) {
        if (!Xulu.MODULE_MANAGER.getModule(AntiSound.class).isToggled() || !Xulu.MODULE_MANAGER.getModuleT(AntiSound.class).wither.getValue()) {
            this.world.playSound(pos, soundIn, category, volume, pitch, distanceDelay);
        }
    }
    
    @Inject(method = { "renderEntities" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;preRenderDamagedBlocks()V", shift = At.Shift.BEFORE) })
    public void renderEntities(final Entity entity, final ICamera camera, final float n, final CallbackInfo callbackInfo) {
        if (Xulu.MODULE_MANAGER.getModuleByName("StorageESP") != null && Xulu.MODULE_MANAGER.getModuleByName("StorageESP").isToggled() && Xulu.VALUE_MANAGER.getValueByMod(Xulu.MODULE_MANAGER.getModuleByName("StorageESP"), "Mode").getValue().equalsIgnoreCase("Shader")) {
            final StorageESP storageESP = (StorageESP)Xulu.MODULE_MANAGER.getModuleByName("StorageESP");
            StorageESP.renderNormal(n);
            OutlineUtils2.VZWQ(Xulu.VALUE_MANAGER.getValueByMod(storageESP, "Line Width").getValue());
            StorageESP.renderNormal(n);
            OutlineUtils2.JLYv();
            StorageESP.renderColor(n);
            OutlineUtils2.feKn();
            OutlineUtils2.mptE(null);
            StorageESP.renderColor(n);
            OutlineUtils2.VdOT();
        }
    }
    
    public void renderNormal(final float n) {
        RenderHelper.enableStandardItemLighting();
        for (final Entity e : Wrapper.getMinecraft().world.loadedEntityList) {
            GL11.glPushMatrix();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            Wrapper.getMinecraft().renderGlobal.renderManager.renderEntity(e, e.posX - Wrapper.getMinecraft().renderManager.renderPosX, e.posY - Wrapper.getMinecraft().renderManager.renderPosY, e.posZ - Wrapper.getMinecraft().renderManager.renderPosZ, e.rotationYaw, n, false);
            GL11.glPopMatrix();
        }
    }
}
