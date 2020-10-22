package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import com.elementars.eclient.event.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.module.*;

public class Step extends Module
{
    private final /* synthetic */ double[] twoblockPositions;
    private final /* synthetic */ Value<String> mode;
    private /* synthetic */ int packets;
    private final /* synthetic */ double[] onehalfblockPositions;
    private final /* synthetic */ double[] oneblockPositions;
    
    @EventTarget
    public void onWalkingUpdate(final MotionEvent llllllllllllllllIllIlIlIIlIllllI) {
        if (!Step.mc.player.collidedHorizontally && this.mode.getValue().equalsIgnoreCase("Normal")) {
            return;
        }
        if (!Step.mc.player.onGround || Step.mc.player.isOnLadder() || Step.mc.player.isInWater() || Step.mc.player.isInLava() || Step.mc.player.movementInput.jump || Step.mc.player.noClip) {
            return;
        }
        if (Step.mc.player.moveForward == 0.0f && Step.mc.player.moveStrafing == 0.0f) {
            return;
        }
        if (Step.mc.player.collidedHorizontally && Step.mc.player.onGround) {
            ++this.packets;
        }
        final double llllllllllllllllIllIlIlIIlIlllIl = this.get_n_normal();
        if (this.mode.getValue().equalsIgnoreCase("Normal")) {
            if (llllllllllllllllIllIlIlIIlIlllIl < 0.0 || llllllllllllllllIllIlIlIIlIlllIl > 2.0) {
                return;
            }
            if (llllllllllllllllIllIlIlIIlIlllIl == 2.0 && this.packets > this.twoblockPositions.length - 2) {
                final long llllllllllllllllIllIlIlIIlIllIlI = (Object)this.twoblockPositions;
                final float llllllllllllllllIllIlIlIIlIllIIl = llllllllllllllllIllIlIlIIlIllIlI.length;
                for (Exception llllllllllllllllIllIlIlIIlIllIII = (Exception)0; llllllllllllllllIllIlIlIIlIllIII < llllllllllllllllIllIlIlIIlIllIIl; ++llllllllllllllllIllIlIlIIlIllIII) {
                    final double llllllllllllllllIllIlIlIIllIIIlI = llllllllllllllllIllIlIlIIlIllIlI[llllllllllllllllIllIlIlIIlIllIII];
                    Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + llllllllllllllllIllIlIlIIllIIIlI, Step.mc.player.posZ, true));
                }
                Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.0, Step.mc.player.posZ);
                this.packets = 0;
            }
            if (llllllllllllllllIllIlIlIIlIlllIl == 1.5 && this.packets > this.onehalfblockPositions.length - 2) {
                final long llllllllllllllllIllIlIlIIlIllIlI = (Object)this.onehalfblockPositions;
                final float llllllllllllllllIllIlIlIIlIllIIl = llllllllllllllllIllIlIlIIlIllIlI.length;
                for (Exception llllllllllllllllIllIlIlIIlIllIII = (Exception)0; llllllllllllllllIllIlIlIIlIllIII < llllllllllllllllIllIlIlIIlIllIIl; ++llllllllllllllllIllIlIlIIlIllIII) {
                    final double llllllllllllllllIllIlIlIIllIIIIl = llllllllllllllllIllIlIlIIlIllIlI[llllllllllllllllIllIlIlIIlIllIII];
                    Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + llllllllllllllllIllIlIlIIllIIIIl, Step.mc.player.posZ, true));
                }
                Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + this.onehalfblockPositions[this.onehalfblockPositions.length - 1], Step.mc.player.posZ);
                this.packets = 0;
            }
            if (llllllllllllllllIllIlIlIIlIlllIl == 1.0 && this.packets > this.oneblockPositions.length - 2) {
                final long llllllllllllllllIllIlIlIIlIllIlI = (Object)this.oneblockPositions;
                final float llllllllllllllllIllIlIlIIlIllIIl = llllllllllllllllIllIlIlIIlIllIlI.length;
                for (Exception llllllllllllllllIllIlIlIIlIllIII = (Exception)0; llllllllllllllllIllIlIlIIlIllIII < llllllllllllllllIllIlIlIIlIllIIl; ++llllllllllllllllIllIlIlIIlIllIII) {
                    final double llllllllllllllllIllIlIlIIllIIIII = llllllllllllllllIllIlIlIIlIllIlI[llllllllllllllllIllIlIlIIlIllIII];
                    Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + llllllllllllllllIllIlIlIIllIIIII, Step.mc.player.posZ, true));
                }
                Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + this.oneblockPositions[this.oneblockPositions.length - 1], Step.mc.player.posZ);
                this.packets = 0;
            }
        }
    }
    
    public double get_n_normal() {
        Step.mc.player.stepHeight = 0.5f;
        double llllllllllllllllIllIlIlIIlIlIIII = -1.0;
        final AxisAlignedBB llllllllllllllllIllIlIlIIlIIllll = Step.mc.player.getEntityBoundingBox().offset(0.0, 0.05, 0.0).grow(0.05);
        if (!Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, llllllllllllllllIllIlIlIIlIIllll.offset(0.0, 2.0, 0.0)).isEmpty()) {
            return 100.0;
        }
        for (final AxisAlignedBB llllllllllllllllIllIlIlIIlIlIIlI : Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, llllllllllllllllIllIlIlIIlIIllll)) {
            if (llllllllllllllllIllIlIlIIlIlIIlI.maxY > llllllllllllllllIllIlIlIIlIlIIII) {
                llllllllllllllllIllIlIlIIlIlIIII = llllllllllllllllIllIlIlIIlIlIIlI.maxY;
            }
        }
        return llllllllllllllllIllIlIlIIlIlIIII - Step.mc.player.posY;
    }
    
    public Step() {
        super("Step", "Step up blocks", 0, Category.MOVEMENT, true);
        this.mode = this.register(new Value<String>("Mode", this, "Normal", new String[] { "Normal" }));
        this.oneblockPositions = new double[] { 0.41999998688698, 0.7531999805212 };
        this.onehalfblockPositions = new double[] { 0.41999998688698, 0.7531999805212, 1.00133597911214, 1.16610926093821, 1.24918707874468, 1.1707870772188 };
        this.twoblockPositions = new double[] { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
    }
}
