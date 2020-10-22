package me.memeszz.aurora.module.modules.movement;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;

public class FastSwim extends Module
{
    /* synthetic */ int divider;
    private /* synthetic */ Value<Boolean> down;
    private /* synthetic */ Value<Boolean> forward;
    private /* synthetic */ Value<Boolean> up;
    private /* synthetic */ Value<Boolean> sprint;
    private /* synthetic */ Value<Boolean> only2b;
    
    public FastSwim() {
        super("FastSwim", "Allows The Player To Swim Faster Horizontally and Vertically", 0, Category.MOVEMENT, true);
        this.divider = 5;
    }
    
    @Override
    public void setup() {
        this.up = this.register(new Value<Boolean>("FastSwimUp", this, true));
        this.down = this.register(new Value<Boolean>("FastSwimDown", this, true));
        this.forward = this.register(new Value<Boolean>("FastSwimForward", this, true));
        this.sprint = this.register(new Value<Boolean>("AutoSprintInLiquid", this, true));
        this.only2b = this.register(new Value<Boolean>("Only2b", this, true));
    }
    
    @Override
    public void onUpdate() {
        if (this.only2b.getValue() && !FastSwim.mc.isSingleplayer() && FastSwim.mc.getCurrentServerData() != null && FastSwim.mc.getCurrentServerData().serverIP.equalsIgnoreCase("2b2t.org")) {
            if (this.sprint.getValue() && (FastSwim.mc.player.isInLava() || FastSwim.mc.player.isInWater())) {
                FastSwim.mc.player.setSprinting(true);
            }
            if ((FastSwim.mc.player.isInWater() || FastSwim.mc.player.isInLava()) && FastSwim.mc.gameSettings.keyBindJump.isKeyDown() && this.up.getValue()) {
                FastSwim.mc.player.motionY = 0.725 / this.divider;
            }
            if (FastSwim.mc.player.isInWater() || FastSwim.mc.player.isInLava()) {
                if ((this.forward.getValue() && FastSwim.mc.gameSettings.keyBindForward.isKeyDown()) || FastSwim.mc.gameSettings.keyBindLeft.isKeyDown() || FastSwim.mc.gameSettings.keyBindRight.isKeyDown() || FastSwim.mc.gameSettings.keyBindBack.isKeyDown()) {
                    FastSwim.mc.player.jumpMovementFactor = 0.34f / this.divider;
                }
                else {
                    FastSwim.mc.player.jumpMovementFactor = 0.0f;
                }
            }
            if (FastSwim.mc.player.isInWater() && this.down.getValue() && FastSwim.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final int llllllllllllllllllllIIlIIlIlllIl = this.divider * -1;
                FastSwim.mc.player.motionY = 2.2 / llllllllllllllllllllIIlIIlIlllIl;
            }
            if (FastSwim.mc.player.isInLava() && this.down.getValue() && FastSwim.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final int llllllllllllllllllllIIlIIlIlllII = this.divider * -1;
                FastSwim.mc.player.motionY = 0.91 / llllllllllllllllllllIIlIIlIlllII;
            }
        }
        if (!this.only2b.getValue()) {
            if (this.sprint.getValue() && (FastSwim.mc.player.isInLava() || FastSwim.mc.player.isInWater())) {
                FastSwim.mc.player.setSprinting(true);
            }
            if ((FastSwim.mc.player.isInWater() || FastSwim.mc.player.isInLava()) && FastSwim.mc.gameSettings.keyBindJump.isKeyDown() && this.up.getValue()) {
                FastSwim.mc.player.motionY = 0.725 / this.divider;
            }
            if (FastSwim.mc.player.isInWater() || FastSwim.mc.player.isInLava()) {
                if ((this.forward.getValue() && FastSwim.mc.gameSettings.keyBindForward.isKeyDown()) || FastSwim.mc.gameSettings.keyBindLeft.isKeyDown() || FastSwim.mc.gameSettings.keyBindRight.isKeyDown() || FastSwim.mc.gameSettings.keyBindBack.isKeyDown()) {
                    FastSwim.mc.player.jumpMovementFactor = 0.34f / this.divider;
                }
                else {
                    FastSwim.mc.player.jumpMovementFactor = 0.0f;
                }
            }
            if (FastSwim.mc.player.isInWater() && this.down.getValue() && FastSwim.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final int llllllllllllllllllllIIlIIlIllIll = this.divider * -1;
                FastSwim.mc.player.motionY = 2.2 / llllllllllllllllllllIIlIIlIllIll;
            }
            if (FastSwim.mc.player.isInLava() && this.down.getValue() && FastSwim.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final int llllllllllllllllllllIIlIIlIllIlI = this.divider * -1;
                FastSwim.mc.player.motionY = 0.91 / llllllllllllllllllllIIlIIlIllIlI;
            }
        }
    }
}
