package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import com.elementars.eclient.event.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import com.elementars.eclient.module.*;

public class AntiSound extends Module
{
    private final /* synthetic */ Value<Boolean> witherHurt;
    public final /* synthetic */ Value<Boolean> wither;
    private final /* synthetic */ Value<Boolean> punchKB;
    private final /* synthetic */ Value<Boolean> witherDeath;
    public final /* synthetic */ Value<Boolean> portal;
    private final /* synthetic */ Value<Boolean> explosion;
    public final /* synthetic */ Value<Boolean> witherSpawn;
    public final /* synthetic */ Value<Boolean> totem;
    public final /* synthetic */ Value<Boolean> elytra;
    private final /* synthetic */ Value<Boolean> punches;
    private final /* synthetic */ Value<Boolean> punchW;
    
    @EventTarget
    public void onRecieve(final EventReceivePacket llllllIIIIlIIII) {
        if (llllllIIIIlIIII.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect llllllIIIIlIIlI = (SPacketSoundEffect)llllllIIIIlIIII.getPacket();
            if (this.shouldCancelSound(llllllIIIIlIIlI.getSound())) {
                llllllIIIIlIIII.setCancelled(true);
            }
        }
    }
    
    @Override
    public void onDisable() {
        AntiSound.EVENT_BUS.unregister((Object)this);
    }
    
    private boolean shouldCancelSound(final SoundEvent llllllIIIIIlIIl) {
        return (llllllIIIIIlIIl == SoundEvents.ENTITY_WITHER_AMBIENT && this.wither.getValue()) || (llllllIIIIIlIIl == SoundEvents.ENTITY_WITHER_SPAWN && this.witherSpawn.getValue()) || (llllllIIIIIlIIl == SoundEvents.ENTITY_WITHER_HURT && this.witherHurt.getValue()) || (llllllIIIIIlIIl == SoundEvents.ENTITY_WITHER_DEATH && this.witherDeath.getValue()) || (llllllIIIIIlIIl == SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE && this.punches.getValue()) || (llllllIIIIIlIIl == SoundEvents.ENTITY_PLAYER_ATTACK_WEAK && this.punchW.getValue()) || (llllllIIIIIlIIl == SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK && this.punchKB.getValue()) || (llllllIIIIIlIIl == SoundEvents.ENTITY_GENERIC_EXPLODE && this.explosion.getValue());
    }
    
    public AntiSound() {
        super("AntiSound", "Blacklists certain annoying sounds", 0, Category.MISC, true);
        this.wither = this.register(new Value<Boolean>("Wither Ambient", this, true));
        this.witherHurt = this.register(new Value<Boolean>("Wither Hurt", this, true));
        this.witherSpawn = this.register(new Value<Boolean>("Wither Spawn", this, false));
        this.witherDeath = this.register(new Value<Boolean>("Wither Death", this, false));
        this.punches = this.register(new Value<Boolean>("Punches", this, true));
        this.punchW = this.register(new Value<Boolean>("Weak Punch", this, true));
        this.punchKB = this.register(new Value<Boolean>("Knockback Punch", this, true));
        this.explosion = this.register(new Value<Boolean>("Explosion", this, false));
        this.totem = this.register(new Value<Boolean>("Totem Pop", this, false));
        this.elytra = this.register(new Value<Boolean>("Elytra Wind", this, true));
        this.portal = this.register(new Value<Boolean>("Nether Portal", this, true));
    }
    
    @Override
    public void onEnable() {
        AntiSound.EVENT_BUS.register((Object)this);
    }
}
