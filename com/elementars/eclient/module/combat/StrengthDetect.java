package com.elementars.eclient.module.combat;

import net.minecraft.entity.player.*;
import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.world.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.command.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import java.util.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;

public class StrengthDetect extends Module
{
    public static /* synthetic */ List<EntityPlayer> strPlayers;
    private final /* synthetic */ Value<Boolean> watermark;
    public static /* synthetic */ List<EntityPlayer> drinkSet;
    private final /* synthetic */ Value<String> color;
    
    @Override
    public void onUpdate() {
        if (StrengthDetect.mc.player == null) {
            return;
        }
        for (final EntityPlayer lllllllllllllllllIIIlllIlIIIlIll : StrengthDetect.mc.world.playerEntities) {
            for (final PotionEffect lllllllllllllllllIIIlllIlIIIllII : lllllllllllllllllIIIlllIlIIIlIll.getActivePotionEffects()) {
                boolean lllllllllllllllllIIIlllIlIIIllIl = true;
                if (lllllllllllllllllIIIlllIlIIIllII.getPotion() == MobEffects.STRENGTH) {
                    StrengthDetect.strPlayers.add(lllllllllllllllllIIIlllIlIIIlIll);
                    lllllllllllllllllIIIlllIlIIIllIl = false;
                }
                if (lllllllllllllllllIIIlllIlIIIllIl) {
                    StrengthDetect.strPlayers.remove(lllllllllllllllllIIIlllIlIIIlIll);
                }
            }
            if (lllllllllllllllllIIIlllIlIIIlIll.getHealth() == 0.0f && StrengthDetect.strPlayers.contains(lllllllllllllllllIIIlllIlIIIlIll)) {
                StrengthDetect.strPlayers.remove(lllllllllllllllllIIIlllIlIIIlIll);
            }
        }
    }
    
    static {
        StrengthDetect.drinkSet = new ArrayList<EntityPlayer>();
        StrengthDetect.strPlayers = new ArrayList<EntityPlayer>();
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket lllllllllllllllllIIIlllIIllIlllI) {
        if (lllllllllllllllllIIIlllIIllIlllI.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus lllllllllllllllllIIIlllIIllllIll = (SPacketEntityStatus)lllllllllllllllllIIIlllIIllIlllI.getPacket();
            if (lllllllllllllllllIIIlllIIllllIll.getOpCode() == 35 && lllllllllllllllllIIIlllIIllllIll.getEntity((World)StrengthDetect.mc.world) instanceof EntityPlayer) {
                final EntityPlayer lllllllllllllllllIIIlllIIlllllII = (EntityPlayer)lllllllllllllllllIIIlllIIllllIll.getEntity((World)StrengthDetect.mc.world);
                StrengthDetect.strPlayers.remove(lllllllllllllllllIIIlllIIlllllII);
                Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(lllllllllllllllllIIIlllIIlllllII.getName()).append(" no longer has strength!")));
            }
        }
        if (lllllllllllllllllIIIlllIIllIlllI.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect lllllllllllllllllIIIlllIIlllIIlI = (SPacketSoundEffect)lllllllllllllllllIIIlllIIllIlllI.getPacket();
            if (lllllllllllllllllIIIlllIIlllIIlI.sound.getSoundName().toString().equalsIgnoreCase("minecraft:entity.generic.drink")) {
                final List<EntityPlayer> lllllllllllllllllIIIlllIIlllIlll = (List<EntityPlayer>)StrengthDetect.mc.world.getEntitiesWithinAABB((Class)EntityPlayer.class, new AxisAlignedBB(lllllllllllllllllIIIlllIIlllIIlI.getX() - 1.0, lllllllllllllllllIIIlllIIlllIIlI.getY() - 1.0, lllllllllllllllllIIIlllIIlllIIlI.getZ() - 1.0, lllllllllllllllllIIIlllIIlllIIlI.getX() + 1.0, lllllllllllllllllIIIlllIIlllIIlI.getY() + 1.0, lllllllllllllllllIIIlllIIlllIIlI.getZ() + 1.0));
                EntityPlayer lllllllllllllllllIIIlllIIlllIllI = null;
                if (lllllllllllllllllIIIlllIIlllIlll.size() > 1) {
                    for (final EntityPlayer lllllllllllllllllIIIlllIIllllIlI : lllllllllllllllllIIIlllIIlllIlll) {
                        if (lllllllllllllllllIIIlllIIlllIllI == null || lllllllllllllllllIIIlllIIllllIlI.getDistance(lllllllllllllllllIIIlllIIlllIIlI.getX(), lllllllllllllllllIIIlllIIlllIIlI.getY(), lllllllllllllllllIIIlllIIlllIIlI.getZ()) < lllllllllllllllllIIIlllIIlllIllI.getDistance(lllllllllllllllllIIIlllIIlllIIlI.getX(), lllllllllllllllllIIIlllIIlllIIlI.getY(), lllllllllllllllllIIIlllIIlllIIlI.getZ())) {
                            lllllllllllllllllIIIlllIIlllIllI = lllllllllllllllllIIIlllIIllllIlI;
                        }
                    }
                }
                else {
                    lllllllllllllllllIIIlllIIlllIllI = lllllllllllllllllIIIlllIIlllIlll.get(0);
                }
                if (lllllllllllllllllIIIlllIIlllIllI.getHeldItemMainhand().getItem() instanceof ItemPotion) {
                    final List<PotionEffect> lllllllllllllllllIIIlllIIllllIII = (List<PotionEffect>)PotionUtils.getEffectsFromStack(lllllllllllllllllIIIlllIIlllIllI.getHeldItemMainhand());
                    for (final PotionEffect lllllllllllllllllIIIlllIIllllIIl : lllllllllllllllllIIIlllIIllllIII) {
                        if (lllllllllllllllllIIIlllIIllllIIl.getPotion() == MobEffects.STRENGTH) {
                            StrengthDetect.drinkSet.add(lllllllllllllllllIIIlllIIlllIllI);
                        }
                    }
                }
            }
            else if (lllllllllllllllllIIIlllIIlllIIlI.sound.getSoundName().toString().equalsIgnoreCase("minecraft:item.armor.equip_generic")) {
                final List<EntityPlayer> lllllllllllllllllIIIlllIIlllIlII = (List<EntityPlayer>)StrengthDetect.mc.world.getEntitiesWithinAABB((Class)EntityPlayer.class, new AxisAlignedBB(lllllllllllllllllIIIlllIIlllIIlI.getX() - 1.0, lllllllllllllllllIIIlllIIlllIIlI.getY() - 1.0, lllllllllllllllllIIIlllIIlllIIlI.getZ() - 1.0, lllllllllllllllllIIIlllIIlllIIlI.getX() + 1.0, lllllllllllllllllIIIlllIIlllIIlI.getY() + 1.0, lllllllllllllllllIIIlllIIlllIIlI.getZ() + 1.0));
                EntityPlayer lllllllllllllllllIIIlllIIlllIIll = null;
                if (lllllllllllllllllIIIlllIIlllIlII.size() > 1) {
                    for (final EntityPlayer lllllllllllllllllIIIlllIIlllIlIl : lllllllllllllllllIIIlllIIlllIlII) {
                        if (lllllllllllllllllIIIlllIIlllIIll == null || lllllllllllllllllIIIlllIIlllIlIl.getDistance(lllllllllllllllllIIIlllIIlllIIlI.getX(), lllllllllllllllllIIIlllIIlllIIlI.getY(), lllllllllllllllllIIIlllIIlllIIlI.getZ()) < lllllllllllllllllIIIlllIIlllIIll.getDistance(lllllllllllllllllIIIlllIIlllIIlI.getX(), lllllllllllllllllIIIlllIIlllIIlI.getY(), lllllllllllllllllIIIlllIIlllIIlI.getZ())) {
                            lllllllllllllllllIIIlllIIlllIIll = lllllllllllllllllIIIlllIIlllIlIl;
                        }
                    }
                }
                else {
                    lllllllllllllllllIIIlllIIlllIIll = lllllllllllllllllIIIlllIIlllIlII.get(0);
                }
                if (StrengthDetect.drinkSet.contains(lllllllllllllllllIIIlllIIlllIIll) && lllllllllllllllllIIIlllIIlllIIll.getHeldItemMainhand().getItem() == Items.GLASS_BOTTLE) {
                    StrengthDetect.strPlayers.add(lllllllllllllllllIIIlllIIlllIIll);
                    StrengthDetect.drinkSet.remove(lllllllllllllllllIIIlllIIlllIIll);
                    Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(lllllllllllllllllIIIlllIIlllIIll.getName()).append(" has drank a strength pot!")));
                }
            }
        }
    }
    
    public StrengthDetect() {
        super("StrengthDetect", "Detects when someone has strength (BUGGY)", 0, Category.COMBAT, true);
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.color = this.register(new Value<String>("Color", this, "White", ColorTextUtils.colors));
    }
}
