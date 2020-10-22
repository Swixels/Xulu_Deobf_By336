package com.elementars.eclient.mixin.mixins;

import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.command.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiChat.class })
public abstract class MixinGuiChat
{
    @Shadow
    protected GuiTextField inputField;
    @Shadow
    public String historyBuffer;
    @Shadow
    public int sentHistoryCursor;
    
    @Shadow
    public abstract void initGui();
    
    @Inject(method = { "Lnet/minecraft/client/gui/GuiChat;keyTyped(CI)V" }, at = { @At("RETURN") })
    public void returnKeyTyped(final char typedChar, final int keyCode, final CallbackInfo info) {
        if (!(Wrapper.getMinecraft().currentScreen instanceof GuiChat)) {
            return;
        }
        if (this.inputField.getText().startsWith(Command.getPrefix())) {}
    }
}
