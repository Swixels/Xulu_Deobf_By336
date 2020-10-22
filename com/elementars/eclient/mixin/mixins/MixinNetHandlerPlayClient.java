package com.elementars.eclient.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.network.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.chunk.*;
import com.elementars.eclient.event.events.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.misc.*;
import com.elementars.eclient.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ NetHandlerPlayClient.class })
public class MixinNetHandlerPlayClient
{
    @Inject(method = { "handleChunkData" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;read(Lnet/minecraft/network/PacketBuffer;IZ)V") }, locals = LocalCapture.CAPTURE_FAILHARD)
    private void read(final SPacketChunkData data, final CallbackInfo info, final Chunk chunk) {
        new ChunkEvent(chunk, data).call();
    }
    
    @Redirect(method = { "handleEntityStatus" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;playSound(DDDLnet/minecraft/util/SoundEvent;Lnet/minecraft/util/SoundCategory;FFZ)V"))
    private void playTotemSound(final WorldClient worldClient, final double x, final double y, final double z, final SoundEvent soundIn, final SoundCategory category, final float volume, final float pitch, final boolean distanceDelay) {
        if (!Xulu.MODULE_MANAGER.getModule(AntiSound.class).isToggled() || !Xulu.MODULE_MANAGER.getModuleT(AntiSound.class).totem.getValue()) {
            worldClient.playSound(x, y, z, soundIn, category, volume, pitch, distanceDelay);
        }
    }
}
