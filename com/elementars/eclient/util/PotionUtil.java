package com.elementars.eclient.util;

import net.minecraft.potion.*;
import net.minecraft.init.*;
import java.util.*;

public class PotionUtil
{
    public static /* synthetic */ ArrayList<Pair<Potion, String>> potionMap;
    
    static {
        PotionUtil.potionMap = new ArrayList<Pair<Potion, String>>((Collection<? extends Pair<Potion, String>>)Arrays.asList(new Pair((T)MobEffects.ABSORPTION, (S)"Absorption"), new Pair((T)MobEffects.BLINDNESS, (S)"Blindness"), new Pair((T)MobEffects.FIRE_RESISTANCE, (S)"Fire Resistance"), new Pair((T)MobEffects.GLOWING, (S)"Glowing"), new Pair((T)MobEffects.HASTE, (S)"Haste"), new Pair((T)MobEffects.HEALTH_BOOST, (S)"Health Boost"), new Pair((T)MobEffects.HUNGER, (S)"Hunger"), new Pair((T)MobEffects.INSTANT_DAMAGE, (S)"Instant Damage"), new Pair((T)MobEffects.INSTANT_HEALTH, (S)"Instant Health"), new Pair((T)MobEffects.INVISIBILITY, (S)"Invisibility"), new Pair((T)MobEffects.JUMP_BOOST, (S)"Jump Boost"), new Pair((T)MobEffects.LEVITATION, (S)"Levitation"), new Pair((T)MobEffects.LUCK, (S)"Luck"), new Pair((T)MobEffects.MINING_FATIGUE, (S)"Mining Fatigue"), new Pair((T)MobEffects.NAUSEA, (S)"Nausea"), new Pair((T)MobEffects.NIGHT_VISION, (S)"Night Vision"), new Pair((T)MobEffects.POISON, (S)"Poison"), new Pair((T)MobEffects.REGENERATION, (S)"Regeneration"), new Pair((T)MobEffects.RESISTANCE, (S)"Resistance"), new Pair((T)MobEffects.SATURATION, (S)"Saturation"), new Pair((T)MobEffects.SLOWNESS, (S)"Slowness"), new Pair((T)MobEffects.SPEED, (S)"Speed"), new Pair((T)MobEffects.STRENGTH, (S)"Strength"), new Pair((T)MobEffects.UNLUCK, (S)"Unluck"), new Pair((T)MobEffects.WATER_BREATHING, (S)"Water Breathing"), new Pair((T)MobEffects.WEAKNESS, (S)"Weakness"), new Pair((T)MobEffects.WITHER, (S)"Wither")));
    }
    
    public static String getPotionName(final Potion lIlIlllIIIIllll) {
        for (final Pair<Potion, String> lIlIlllIIIlIIIl : PotionUtil.potionMap) {
            if (lIlIlllIIIlIIIl.key == lIlIlllIIIIllll) {
                return lIlIlllIIIlIIIl.value;
            }
        }
        return null;
    }
}
