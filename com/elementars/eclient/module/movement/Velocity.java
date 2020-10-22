package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import com.elementars.eclient.module.*;

public class Velocity extends Module
{
    private final /* synthetic */ Value<Float> horizontal;
    private final /* synthetic */ Value<Float> vertical;
    
    @EventTarget
    public void onEntityCollision(final EntityEvent.EntityCollision lllllllllllllllllllIIIllIllIIlll) {
        if (lllllllllllllllllllIIIllIllIIlll.getEntity() == Velocity.mc.player) {
            if (this.horizontal.getValue() == 0.0f && this.vertical.getValue() == 0.0f) {
                lllllllllllllllllllIIIllIllIIlll.setCancelled(true);
                return;
            }
            lllllllllllllllllllIIIllIllIIlll.setX(-lllllllllllllllllllIIIllIllIIlll.getX() * this.horizontal.getValue());
            lllllllllllllllllllIIIllIllIIlll.setY(0.0);
            lllllllllllllllllllIIIllIllIIlll.setZ(-lllllllllllllllllllIIIllIllIIlll.getZ() * this.horizontal.getValue());
        }
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket lllllllllllllllllllIIIllIlllIIII) {
        if (Velocity.mc.player == null) {
            return;
        }
        if (lllllllllllllllllllIIIllIlllIIII.getPacket() instanceof SPacketEntityVelocity) {
            final SPacketEntityVelocity lllllllllllllllllllIIIllIlllIIll = (SPacketEntityVelocity)lllllllllllllllllllIIIllIlllIIII.getPacket();
            if (lllllllllllllllllllIIIllIlllIIll.getEntityID() == Velocity.mc.player.entityId) {
                if (this.horizontal.getValue() == 0.0f && this.vertical.getValue() == 0.0f) {
                    lllllllllllllllllllIIIllIlllIIII.setCancelled(true);
                }
                final SPacketEntityVelocity sPacketEntityVelocity = lllllllllllllllllllIIIllIlllIIll;
                sPacketEntityVelocity.motionX *= (int)(Object)this.horizontal.getValue();
                final SPacketEntityVelocity sPacketEntityVelocity2 = lllllllllllllllllllIIIllIlllIIll;
                sPacketEntityVelocity2.motionY *= (int)(Object)this.vertical.getValue();
                final SPacketEntityVelocity sPacketEntityVelocity3 = lllllllllllllllllllIIIllIlllIIll;
                sPacketEntityVelocity3.motionZ *= (int)(Object)this.horizontal.getValue();
            }
        }
        else if (lllllllllllllllllllIIIllIlllIIII.getPacket() instanceof SPacketExplosion) {
            if (this.horizontal.getValue() == 0.0f && this.vertical.getValue() == 0.0f) {
                lllllllllllllllllllIIIllIlllIIII.setCancelled(true);
            }
            final SPacketExplosion sPacketExplosion;
            final SPacketExplosion lllllllllllllllllllIIIllIlllIIlI = sPacketExplosion = (SPacketExplosion)lllllllllllllllllllIIIllIlllIIII.getPacket();
            sPacketExplosion.motionX *= this.horizontal.getValue();
            final SPacketExplosion sPacketExplosion2 = lllllllllllllllllllIIIllIlllIIlI;
            sPacketExplosion2.motionY *= this.vertical.getValue();
            final SPacketExplosion sPacketExplosion3 = lllllllllllllllllllIIIllIlllIIlI;
            sPacketExplosion3.motionZ *= this.horizontal.getValue();
        }
    }
    
    public Velocity() {
        super("Velocity", "Modifies knockback", 0, Category.MOVEMENT, true);
        this.horizontal = this.register(new Value<Float>("Horizontal", this, 0.0f, 0.0f, 100.0f));
        this.vertical = this.register(new Value<Float>("Vertical", this, 0.0f, 0.0f, 100.0f));
    }
}
