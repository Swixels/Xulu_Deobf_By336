package com.elementars.eclient.module.combat;

import net.minecraft.entity.player.*;
import com.elementars.eclient.module.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;

public class StrengthDetectX extends Module
{
    public static final /* synthetic */ Map<EntityPlayer, Integer> strMap;
    
    public StrengthDetectX() {
        super("StrengthDetectX", "Hope this works", 0, Category.COMBAT, true);
    }
    
    @SubscribeEvent
    public void onPotionColor(final PotionColorCalculationEvent llllllllllllllllIllIIlIIlIIIIlll) {
        if (llllllllllllllllIllIIlIIlIIIIlll.getEntityLiving() instanceof EntityPlayer) {
            this.sendDebugMessage("Yo this event is being fired");
            boolean llllllllllllllllIllIIlIIlIIIlIlI = false;
            for (final PotionEffect llllllllllllllllIllIIlIIlIIIlIll : llllllllllllllllIllIIlIIlIIIIlll.getEffects()) {
                if (llllllllllllllllIllIIlIIlIIIlIll.getPotion() == MobEffects.STRENGTH) {
                    StrengthDetectX.strMap.put((EntityPlayer)llllllllllllllllIllIIlIIlIIIIlll.getEntityLiving(), llllllllllllllllIllIIlIIlIIIlIll.getAmplifier());
                    this.sendRawDebugMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIllIIlIIlIIIIlll.getEntityLiving().getName()).append(" has strength")));
                    llllllllllllllllIllIIlIIlIIIlIlI = true;
                    break;
                }
            }
            if (StrengthDetectX.strMap.containsKey(llllllllllllllllIllIIlIIlIIIIlll.getEntityLiving()) && !llllllllllllllllIllIIlIIlIIIlIlI) {
                StrengthDetectX.strMap.remove(llllllllllllllllIllIIlIIlIIIIlll.getEntityLiving());
                this.sendRawDebugMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIllIIlIIlIIIIlll.getEntityLiving().getName()).append(" no longer has strength")));
            }
        }
    }
    
    @Override
    public void onEnable() {
        StrengthDetectX.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        StrengthDetectX.EVENT_BUS.unregister((Object)this);
    }
    
    static {
        strengthPlayers = new HashSet<EntityPlayer>();
        strMap = new HashMap<EntityPlayer, Integer>();
    }
}
