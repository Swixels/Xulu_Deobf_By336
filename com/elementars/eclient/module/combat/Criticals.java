package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import com.elementars.eclient.event.*;

public class Criticals extends Module
{
    private final /* synthetic */ Value<String> mode;
    
    public Criticals() {
        super("Criticals", "Automatically crits people", 0, Category.COMBAT, true);
        this.mode = this.register(new Value<String>("Mode", this, "Packet", new String[] { "Jump", "Packet" }));
    }
    
    @EventTarget
    public void sendPacket(final EventSendPacket lllIlllIIIIIllI) {
        if (lllIlllIIIIIllI.getPacket() instanceof CPacketUseEntity) {
            final CPacketUseEntity lllIlllIIIIlIII = (CPacketUseEntity)lllIlllIIIIIllI.getPacket();
            if (lllIlllIIIIlIII.getAction() == CPacketUseEntity.Action.ATTACK && Criticals.mc.player.onGround && !Criticals.mc.gameSettings.keyBindJump.isKeyDown() && lllIlllIIIIlIII.getEntityFromWorld((World)Criticals.mc.world) instanceof EntityLivingBase) {
                if (this.mode.getValue().equalsIgnoreCase("Packet")) {
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.10000000149011612, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                }
                else {
                    Criticals.mc.player.jump();
                }
            }
        }
    }
}
