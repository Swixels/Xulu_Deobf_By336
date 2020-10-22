package com.elementars.eclient.module.movement;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;

public class Flight extends Module
{
    private /* synthetic */ Value<Float> vSpeed;
    private /* synthetic */ Value<Boolean> glide;
    private /* synthetic */ Value<Float> speed;
    private /* synthetic */ Value<Float> glideSpeed;
    private /* synthetic */ Double flyHeight;
    
    @EventTarget
    public void onWalkingUpdate(final MotionEvent lllllllllllllllllIlIlllIIllIIlIl) {
        final double[] lllllllllllllllllIlIlllIIllIIlII = MathUtil.directionSpeed(this.speed.getValue());
        if (ElytraFly.mc.player.isElytraFlying()) {
            if (this.flyHeight == null) {
                this.flyHeight = ElytraFly.mc.player.posY;
            }
            if (this.glide.getValue()) {
                this.flyHeight -= (Double)this.glideSpeed.getValue();
            }
            double lllllllllllllllllIlIlllIIllIlIII = 0.0;
            double lllllllllllllllllIlIlllIIllIIlll = 0.0;
            if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                lllllllllllllllllIlIlllIIllIlIII = lllllllllllllllllIlIlllIIllIIlII[0];
                lllllllllllllllllIlIlllIIllIIlll = lllllllllllllllllIlIlllIIllIIlII[1];
            }
            if (ElytraFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                this.flyHeight = ElytraFly.mc.player.posY + this.vSpeed.getValue();
            }
            if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                this.flyHeight = ElytraFly.mc.player.posY - this.vSpeed.getValue();
            }
            ElytraFly.mc.player.setPosition(ElytraFly.mc.player.posX + lllllllllllllllllIlIlllIIllIlIII, (double)this.flyHeight, ElytraFly.mc.player.posZ + lllllllllllllllllIlIlllIIllIIlll);
            ElytraFly.mc.player.setVelocity(0.0, 0.0, 0.0);
        }
        this.flyHeight = null;
    }
    
    public Flight() {
        super("Flight", "Get off the ground!", 0, Category.MOVEMENT, true);
        this.speed = this.register(new Value<Float>("Speed", this, 10.0f, 0.0f, 20.0f));
        this.vSpeed = this.register(new Value<Float>("V Speed", this, 3.0f, 0.0f, 20.0f));
        this.glide = this.register(new Value<Boolean>("Glide", this, true));
        this.glideSpeed = this.register(new Value<Float>("GlideSpeed", this, 0.25f, 0.0f, 5.0f));
    }
}
