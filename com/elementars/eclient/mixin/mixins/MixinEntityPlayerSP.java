package com.elementars.eclient.mixin.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.client.network.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.module.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.audio.*;
import com.elementars.eclient.module.misc.*;

@Mixin({ EntityPlayerSP.class })
public abstract class MixinEntityPlayerSP extends MixinAbstractClientPlayer
{
    @Final
    public NetHandlerPlayClient connection;
    
    @Shadow
    @Override
    public abstract void move(final MoverType p0, final double p1, final double p2, final double p3);
    
    @Shadow
    @Override
    public abstract void swingArm(final EnumHand p0);
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;closeScreen()V"))
    public void closeScreen(final EntityPlayerSP entityPlayerSP) {
        if (ModuleManager.isModuleEnabled("PortalChat")) {
            return;
        }
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"))
    public void closeScreen(final Minecraft minecraft, final GuiScreen screen) {
        if (Xulu.MODULE_MANAGER.getModuleByName("PortalChat").isToggled()) {
            return;
        }
    }
    
    @Inject(method = { "onUpdate" }, at = { @At("RETURN") }, cancellable = true)
    private void onUpdatePost(final CallbackInfo callbackInfo) {
        if (Wrapper.getMinecraft().world.isBlockLoaded(new BlockPos(Wrapper.getMinecraft().player.posX, 0.0, Wrapper.getMinecraft().player.posZ))) {
            final EventPreMotionUpdates preMotion = new EventPreMotionUpdates(Wrapper.getMinecraft().player.rotationYaw, Wrapper.getMinecraft().player.rotationPitch, Wrapper.getMinecraft().player.posY);
            preMotion.call();
            if (preMotion.isCancelled()) {
                callbackInfo.cancel();
            }
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    public void onUpdateWalkingPlayer(final CallbackInfo info) {
        final MotionEvent event = new MotionEvent();
        event.call();
        if (event.isCancelled()) {
            info.cancel();
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    public void onUpdateWalkingPlayerPost(final CallbackInfo info) {
        final MotionEventPost event = new MotionEventPost();
        event.call();
    }
    
    @Inject(method = { "move" }, at = { @At("HEAD") }, cancellable = true)
    public void move(final MoverType type, final double x, final double y, final double z, final CallbackInfo info) {
        final PlayerMoveEvent event = new PlayerMoveEvent(type, x, y, z);
        event.setState(Event.State.PRE);
        event.call();
        if (event.isCancelled()) {
            super.move(type, event.getX(), event.getY(), event.getZ());
            info.cancel();
        }
    }
    
    @Inject(method = { "move" }, at = { @At("RETURN") }, cancellable = true)
    public void moveReturn(final MoverType type, final double x, final double y, final double z, final CallbackInfo info) {
        final PlayerMoveEvent event = new PlayerMoveEvent(type, x, y, z);
        event.setState(Event.State.POST);
        event.call();
    }
    
    @Inject(method = { "swingArm" }, at = { @At("HEAD") }, cancellable = true)
    public void swingArm(final EnumHand enumHand, final CallbackInfo info) {
        try {
            if (Xulu.MODULE_MANAGER.getModule(OffhandSwing.class).isToggled()) {
                super.swingArm(EnumHand.OFF_HAND);
                Wrapper.getMinecraft().player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
                info.cancel();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Redirect(method = { "notifyDataManagerChange" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/audio/SoundHandler;playSound(Lnet/minecraft/client/audio/ISound;)V"))
    private void playElytraSound(final SoundHandler soundHandler, final ISound sound) {
        if (!Xulu.MODULE_MANAGER.getModule(AntiSound.class).isToggled() || !Xulu.MODULE_MANAGER.getModuleT(AntiSound.class).elytra.getValue()) {
            soundHandler.playSound(sound);
        }
    }
}
