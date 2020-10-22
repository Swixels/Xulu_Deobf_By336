package com.elementars.eclient.mixin.mixins;

import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.shader.*;
import net.minecraft.crash.*;
import com.elementars.eclient.*;
import org.spongepowered.asm.mixin.injection.*;
import org.lwjgl.input.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.entity.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.multiplayer.*;

@Mixin(value = { Minecraft.class }, priority = 9999)
public class MixinMinecraft
{
    @Shadow
    public EntityRenderer entityRenderer;
    @Shadow
    public WorldClient world;
    @Shadow
    public GuiScreen currentScreen;
    @Shadow
    public GameSettings gameSettings;
    @Shadow
    public Framebuffer framebuffer;
    
    @Redirect(method = { "run" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayCrashReport(Lnet/minecraft/crash/CrashReport;)V"))
    public void displayCrashReport(final Minecraft minecraft, final CrashReport crashReport) {
        Xulu.save();
    }
    
    @Inject(method = { "middleClickMouse" }, at = { @At("HEAD") })
    private void middleClickMouse(final CallbackInfo callback) {
        final EventMiddleClick eventMiddleClick = new EventMiddleClick();
        eventMiddleClick.call();
    }
    
    @Inject(method = { "shutdownMinecraftApplet" }, at = { @At("HEAD") })
    private void stopClient(final CallbackInfo callbackInfo) {
        Xulu.INSTANCE.stopClient();
    }
    
    @Inject(method = { "runTickKeyboard" }, at = { @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE) })
    private void onKeyboard(final CallbackInfo callbackInfo) {
        final int i = (Keyboard.getEventKey() == 0) ? (Keyboard.getEventCharacter() + '\u0100') : Keyboard.getEventKey();
        if (Keyboard.getEventKeyState()) {
            final EventKey eventKey = new EventKey(i);
            eventKey.call();
        }
    }
    
    @Inject(method = { "getLimitFramerate" }, at = { @At("HEAD") }, cancellable = true)
    private void getFrameRateXulu(final CallbackInfoReturnable<Integer> cir) {
        try {
            if (Xulu.MODULE_MANAGER.getModule(TitleScreenShader.class).isToggled()) {
                cir.setReturnValue((this.world == null && this.currentScreen != null) ? TitleScreenShader.fps.getValue() : Integer.valueOf(this.gameSettings.limitFramerate));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Redirect(method = { "sendClickBlockToController" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"))
    private boolean isHandActiveWrapper(final EntityPlayerSP playerSP) {
        final AllowInteractEvent event = new AllowInteractEvent(playerSP.isHandActive());
        event.call();
        return event.usingItem;
    }
    
    @Redirect(method = { "rightClickMouse" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z", ordinal = 0))
    private boolean isHittingBlockWrapper(final PlayerControllerMP playerControllerMP) {
        final AllowInteractEvent event = new AllowInteractEvent(playerControllerMP.getIsHittingBlock());
        event.call();
        return event.usingItem;
    }
}
