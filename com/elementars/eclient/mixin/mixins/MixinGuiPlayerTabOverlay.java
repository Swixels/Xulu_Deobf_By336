package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import java.util.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.client.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiPlayerTabOverlay.class })
public class MixinGuiPlayerTabOverlay
{
    @Redirect(method = { "renderPlayerlist" }, at = @At(value = "INVOKE", target = "Ljava/util/List;subList(II)Ljava/util/List;"))
    public List subList(final List list, final int fromIndex, final int toIndex) {
        return list.subList(fromIndex, ExtraTab.INSTANCE.isToggled() ? Math.min(ExtraTab.INSTANCE.tabSize.getValue(), list.size()) : toIndex);
    }
    
    @Inject(method = { "getPlayerName" }, at = { @At("HEAD") }, cancellable = true)
    public void getPlayerName(final NetworkPlayerInfo networkPlayerInfoIn, final CallbackInfoReturnable returnable) {
        if (ExtraTab.INSTANCE.isToggled()) {
            returnable.cancel();
            returnable.setReturnValue(ExtraTab.getPlayerName(networkPlayerInfoIn));
        }
    }
}
