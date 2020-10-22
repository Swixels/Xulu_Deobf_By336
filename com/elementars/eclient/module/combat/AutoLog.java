package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.client.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;
import com.elementars.eclient.module.*;
import net.minecraftforge.event.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.elementars.eclient.*;

public class AutoLog extends Module
{
    private /* synthetic */ boolean shouldLog;
    /* synthetic */ long lastLog;
    private /* synthetic */ Value<Integer> health;
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
    }
    
    @Override
    public void onUpdate() {
        if (this.shouldLog) {
            this.shouldLog = false;
            if (System.currentTimeMillis() - this.lastLog < 2000L) {
                return;
            }
            Minecraft.getMinecraft().getConnection().handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("AutoLogged")));
        }
    }
    
    public AutoLog() {
        super("AutoLog", "Automatically Logs", 0, Category.COMBAT, true);
        this.health = this.register(new Value<Integer>("Health", this, 6, 0, 36));
        this.shouldLog = false;
        this.lastLog = System.currentTimeMillis();
    }
    
    @SubscribeEvent
    private void onEntity(final EntityJoinWorldEvent lIlIlIIllIllIlI) {
        if (AutoLog.mc.player == null) {
            return;
        }
        if (lIlIlIIllIllIlI.getEntity() instanceof EntityEnderCrystal && AutoLog.mc.player.getHealth() - AutoCrystal.calculateDamage((EntityEnderCrystal)lIlIlIIllIllIlI.getEntity(), (Entity)AutoLog.mc.player) < this.health.getValue()) {
            this.log();
        }
    }
    
    private void log() {
        Xulu.MODULE_MANAGER.getModuleByName("AutoReconnect").disable();
        this.shouldLog = true;
        this.lastLog = System.currentTimeMillis();
    }
}
