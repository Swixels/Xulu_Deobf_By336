package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import java.util.concurrent.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import javax.annotation.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraftforge.event.entity.player.*;

public class AutoEz extends Module
{
    private final /* synthetic */ Value<String> acmessage;
    private final /* synthetic */ Value<Boolean> acmode;
    private final /* synthetic */ Value<Boolean> autocityboss;
    private /* synthetic */ ConcurrentHashMap<String, Integer> targettedplayers;
    private /* synthetic */ ArrayList<String> dummy;
    public static /* synthetic */ AutoEz INSTANCE;
    private /* synthetic */ EntityEnderCrystal crystal;
    private /* synthetic */ ConcurrentHashMap<String, Integer> totemplayers;
    private /* synthetic */ int hasBeenCombat;
    private /* synthetic */ int acdelay;
    private /* synthetic */ int ahdelay;
    private final /* synthetic */ Value<String> message;
    private final /* synthetic */ Value<String> ahmessage;
    private /* synthetic */ ConcurrentHashMap<String, Integer> helmetplayers;
    private final /* synthetic */ Value<Boolean> autoezhelmet;
    private final /* synthetic */ Value<Boolean> ahmode;
    private final /* synthetic */ Value<Boolean> mode;
    
    @Override
    public void onUpdate() {
        if (AutoEz.mc.player.isDead) {
            this.hasBeenCombat = 0;
        }
        for (final Entity lllllllllllllllllllIlIllIIIIIllI : AutoEz.mc.world.getLoadedEntityList()) {
            if (!EntityUtil.isPlayer(lllllllllllllllllllIlIllIIIIIllI)) {
                continue;
            }
            final EntityPlayer lllllllllllllllllllIlIllIIIIlIII = (EntityPlayer)lllllllllllllllllllIlIllIIIIIllI;
            if (lllllllllllllllllllIlIllIIIIlIII.getHealth() > 0.0f) {
                continue;
            }
            final String lllllllllllllllllllIlIllIIIIIlll = lllllllllllllllllllIlIllIIIIlIII.getName();
            if (!TargetPlayers.targettedplayers.containsKey(lllllllllllllllllllIlIllIIIIIlll)) {
                continue;
            }
            if (this.mode.getValue()) {
                this.sendChatMessage(this.message.getValue(), lllllllllllllllllllIlIllIIIIIlll);
            }
            else {
                this.sendChatMessage(this.message.getValue(), null);
            }
            TargetPlayers.targettedplayers.remove(lllllllllllllllllllIlIllIIIIIlll);
        }
        if (this.autocityboss.getValue() && this.acdelay == 0) {
            for (final Entity lllllllllllllllllllIlIllIIIIIIll : AutoEz.mc.world.getLoadedEntityList()) {
                if (!EntityUtil.isPlayer(lllllllllllllllllllIlIllIIIIIIll)) {
                    continue;
                }
                final EntityPlayer lllllllllllllllllllIlIllIIIIIlIl = (EntityPlayer)lllllllllllllllllllIlIllIIIIIIll;
                if (lllllllllllllllllllIlIllIIIIIlIl.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
                    this.totemplayers.put(lllllllllllllllllllIlIllIIIIIlIl.getName(), 20);
                }
                else {
                    final String lllllllllllllllllllIlIllIIIIIlII = lllllllllllllllllllIlIllIIIIIlIl.getName();
                    if (TargetPlayers.targettedplayers.containsKey(lllllllllllllllllllIlIllIIIIIlII) && this.totemplayers.containsKey(lllllllllllllllllllIlIllIIIIIlII) && AutoEz.mc.world.playerEntities.contains(lllllllllllllllllllIlIllIIIIIlIl)) {
                        if (this.acmode.getValue()) {
                            this.sendChatMessage(this.acmessage.getValue(), lllllllllllllllllllIlIllIIIIIlII);
                        }
                        else {
                            this.sendChatMessage(this.acmessage.getValue(), null);
                        }
                        TargetPlayers.targettedplayers.remove(lllllllllllllllllllIlIllIIIIIlII);
                        this.totemplayers.remove(lllllllllllllllllllIlIllIIIIIlII);
                        this.acdelay = 1500;
                        break;
                    }
                    continue;
                }
            }
        }
        if (this.autoezhelmet.getValue() && this.ahdelay == 0) {
            for (final Entity lllllllllllllllllllIlIlIlllllllI : AutoEz.mc.world.getLoadedEntityList()) {
                if (!EntityUtil.isPlayer(lllllllllllllllllllIlIlIlllllllI)) {
                    continue;
                }
                final EntityPlayer lllllllllllllllllllIlIllIIIIIIIl = (EntityPlayer)lllllllllllllllllllIlIlIlllllllI;
                boolean lllllllllllllllllllIlIllIIIIIIII = false;
                for (final ItemStack lllllllllllllllllllIlIllIIIIIIlI : lllllllllllllllllllIlIllIIIIIIIl.getArmorInventoryList()) {
                    if (lllllllllllllllllllIlIllIIIIIIlI != null && lllllllllllllllllllIlIllIIIIIIlI.getItem() == Items.DIAMOND_HELMET) {
                        lllllllllllllllllllIlIllIIIIIIII = true;
                    }
                }
                if (lllllllllllllllllllIlIllIIIIIIII) {
                    this.helmetplayers.put(lllllllllllllllllllIlIllIIIIIIIl.getName(), 20);
                }
                else {
                    final String lllllllllllllllllllIlIlIllllllll = lllllllllllllllllllIlIllIIIIIIIl.getName();
                    if (TargetPlayers.targettedplayers.containsKey(lllllllllllllllllllIlIlIllllllll) && this.helmetplayers.containsKey(lllllllllllllllllllIlIlIllllllll) && AutoEz.mc.world.playerEntities.contains(lllllllllllllllllllIlIllIIIIIIIl)) {
                        if (this.acmode.getValue()) {
                            this.sendChatMessage(this.ahmessage.getValue(), lllllllllllllllllllIlIlIllllllll);
                        }
                        else {
                            this.sendChatMessage(this.ahmessage.getValue(), null);
                        }
                        TargetPlayers.targettedplayers.remove(lllllllllllllllllllIlIlIllllllll);
                        this.helmetplayers.remove(lllllllllllllllllllIlIlIllllllll);
                        this.ahdelay = 1500;
                        break;
                    }
                    continue;
                }
            }
        }
        this.totemplayers.forEach((lllllllllllllllllllIlIlIllIllllI, lllllllllllllllllllIlIlIllIllIlI) -> {
            if (lllllllllllllllllllIlIlIllIllIlI <= 0) {
                this.totemplayers.remove(lllllllllllllllllllIlIlIllIllllI);
            }
            else {
                this.totemplayers.put(lllllllllllllllllllIlIlIllIllllI, lllllllllllllllllllIlIlIllIllIlI - 1);
            }
            return;
        });
        this.helmetplayers.forEach((lllllllllllllllllllIlIlIlllIIlII, lllllllllllllllllllIlIlIlllIIllI) -> {
            if (lllllllllllllllllllIlIlIlllIIllI <= 0) {
                this.helmetplayers.remove(lllllllllllllllllllIlIlIlllIIlII);
            }
            else {
                this.helmetplayers.put(lllllllllllllllllllIlIlIlllIIlII, lllllllllllllllllllIlIlIlllIIllI - 1);
            }
            return;
        });
        if (this.acdelay > 0) {
            --this.acdelay;
        }
        if (this.ahdelay > 0) {
            --this.ahdelay;
        }
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onDeath(final LivingDeathEvent lllllllllllllllllllIlIllIIIlIIll) {
        if (AutoEz.mc.player != null) {
            final EntityLivingBase lllllllllllllllllllIlIllIIIlIlll = lllllllllllllllllllIlIllIIIlIIll.getEntityLiving();
            if (lllllllllllllllllllIlIllIIIlIlll != null && EntityUtil.isPlayer((Entity)lllllllllllllllllllIlIllIIIlIlll)) {
                final EntityPlayer lllllllllllllllllllIlIllIIIllIII = (EntityPlayer)lllllllllllllllllllIlIllIIIlIlll;
                if (lllllllllllllllllllIlIllIIIllIII.getHealth() <= 0.0f) {
                    final String lllllllllllllllllllIlIllIIIllIIl = lllllllllllllllllllIlIllIIIllIII.getName();
                    if (TargetPlayers.targettedplayers.containsKey(lllllllllllllllllllIlIllIIIllIIl)) {
                        if (this.mode.getValue()) {
                            this.sendChatMessage(this.message.getValue(), lllllllllllllllllllIlIllIIIllIIl);
                        }
                        else {
                            this.sendChatMessage(this.message.getValue(), null);
                        }
                        TargetPlayers.targettedplayers.remove(lllllllllllllllllllIlIllIIIllIIl);
                    }
                }
            }
        }
    }
    
    private void sendChatMessage(final String lllllllllllllllllllIlIlIlllIlllI, @Nullable final String lllllllllllllllllllIlIlIllllIIII) {
        final String lllllllllllllllllllIlIlIlllIllll = (lllllllllllllllllllIlIlIllllIIII == null) ? lllllllllllllllllllIlIlIlllIlllI : lllllllllllllllllllIlIlIlllIlllI.replaceAll("NAME", lllllllllllllllllllIlIlIllllIIII);
        AutoEz.mc.player.sendChatMessage(lllllllllllllllllllIlIlIlllIllll);
    }
    
    public AutoEz() {
        super("AutoEZ", "Sends toxic messages for you (use NAME like in Welcome)", 0, Category.COMBAT, true);
        this.dummy = new ArrayList<String>(Arrays.asList("Change this in the settings"));
        this.message = this.register(new Value<String>("Message", this, "NAME has been put in the montage", this.dummy));
        this.mode = this.register(new Value<Boolean>("Names", this, true));
        this.autocityboss = this.register(new Value<Boolean>("AutoCityboss", this, false));
        this.acmessage = this.register(new Value<String>("AC message", this, "NAME ez pop", this.dummy));
        this.acmode = this.register(new Value<Boolean>("AC Names", this, true));
        this.autoezhelmet = this.register(new Value<Boolean>("AutoEZHelmet", this, false));
        this.ahmessage = this.register(new Value<String>("AH message", this, "NAME ez helmet", this.dummy));
        this.ahmode = this.register(new Value<Boolean>("AH Names", this, true));
        this.targettedplayers = new ConcurrentHashMap<String, Integer>();
        this.totemplayers = new ConcurrentHashMap<String, Integer>();
        this.helmetplayers = new ConcurrentHashMap<String, Integer>();
        AutoEz.INSTANCE = this;
    }
    
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onAttack(final AttackEntityEvent lllllllllllllllllllIlIllIIlIIIII) {
        if (this.isToggled() && lllllllllllllllllllIlIllIIlIIIII.getTarget() instanceof EntityEnderCrystal) {
            this.crystal = (EntityEnderCrystal)lllllllllllllllllllIlIllIIlIIIII.getTarget();
        }
        if (this.isToggled() && lllllllllllllllllllIlIllIIlIIIII.getTarget() instanceof EntityPlayer) {
            final EntityPlayer lllllllllllllllllllIlIllIIlIIlII = (EntityPlayer)lllllllllllllllllllIlIllIIlIIIII.getTarget();
            if (lllllllllllllllllllIlIllIIlIIlII.getHealth() <= 0.0f || lllllllllllllllllllIlIllIIlIIlII.isDead) {
                if (this.mode.getValue()) {
                    this.sendChatMessage(this.message.getValue(), lllllllllllllllllllIlIllIIlIIlII.getName());
                }
                else {
                    this.sendChatMessage(this.message.getValue(), null);
                }
            }
        }
    }
}
