package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import io.netty.channel.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;
import com.elementars.eclient.event.events.*;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    public void IchannelRead0(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callback) {
        final EventReceivePacket event = new EventReceivePacket(packet);
        event.call();
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    public void IsendPacket(final Packet<?> packet, final CallbackInfo callback) {
        final EventSendPacket event = new EventSendPacket(packet);
        event.call();
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
