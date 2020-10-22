package com.elementars.eclient.mixin.mixins;

import net.minecraft.network.handshake.client.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ C00Handshake.class })
public class MixinC00Handshake
{
    @Shadow
    int protocolVersion;
    @Shadow
    String ip;
    @Shadow
    int port;
    @Shadow
    EnumConnectionState requestedState;
    
    @Inject(method = { "writePacketData" }, at = { @At("HEAD") }, cancellable = true)
    public void writePacketData(final PacketBuffer buf, final CallbackInfo info) {
        if (Xulu.MODULE_MANAGER.getModuleByName("FakeVanilla").isToggled()) {
            info.cancel();
            buf.writeVarInt(this.protocolVersion);
            buf.writeString(this.ip);
            buf.writeShort(this.port);
            buf.writeVarInt(this.requestedState.getId());
        }
    }
}
