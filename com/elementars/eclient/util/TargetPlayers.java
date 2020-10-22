package com.elementars.eclient.util;

import java.util.concurrent.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraft.entity.player.*;

public class TargetPlayers implements Helper
{
    public static /* synthetic */ ConcurrentHashMap<String, Integer> targettedplayers;
    
    static {
        TargetPlayers.targettedplayers = new ConcurrentHashMap<String, Integer>();
    }
    
    public static void onUpdate() {
        TargetPlayers.targettedplayers.forEach((lIlIIlllllIllI, lIlIIlllllIlIl) -> {
            if (lIlIIlllllIlIl <= 0) {
                TargetPlayers.targettedplayers.remove(lIlIIlllllIllI);
            }
            else {
                TargetPlayers.targettedplayers.put(lIlIIlllllIllI, lIlIIlllllIlIl - 1);
            }
        });
    }
    
    public static void onAttack(final AttackEntityEvent lIlIlIIIIIlIll) {
        if (lIlIlIIIIIlIll.getTarget() instanceof EntityPlayer) {
            if (((EntityPlayer)lIlIlIIIIIlIll.getTarget()).getHealth() == 0.0f) {
                return;
            }
            TargetPlayers.targettedplayers.put(lIlIlIIIIIlIll.getTarget().getName(), 20);
        }
    }
    
    public static void addTargetedPlayer(final String lIlIlIIIIIIlII) {
        if (lIlIlIIIIIIlII.equalsIgnoreCase(TargetPlayers.mc.player.getName())) {
            return;
        }
        if (TargetPlayers.targettedplayers == null) {
            TargetPlayers.targettedplayers = new ConcurrentHashMap<String, Integer>();
        }
        TargetPlayers.targettedplayers.put(lIlIlIIIIIIlII, 20);
    }
}
