package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.server.*;
import net.minecraft.entity.player.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.util.*;

public class CoordLogger extends Module
{
    private final /* synthetic */ Value<Boolean> dragon;
    private final /* synthetic */ Value<Boolean> wither;
    private final /* synthetic */ Value<Boolean> portal;
    private /* synthetic */ HashMap<Entity, Vec3d> knownPlayers;
    private final /* synthetic */ Value<Boolean> savetofile;
    private final /* synthetic */ Value<Boolean> tp;
    private final /* synthetic */ Value<Boolean> lightning;
    /* synthetic */ SPacketSoundEffect packet;
    /* synthetic */ SPacketEffect packet2;
    
    @Override
    public void onUpdate() {
        if (!this.tp.getValue()) {
            return;
        }
        if (CoordLogger.mc.player == null) {
            return;
        }
        final List<Entity> llllllllllllllllllIlIlIlIlllIlIl = (List<Entity>)CoordLogger.mc.world.getLoadedEntityList();
        for (final Entity llllllllllllllllllIlIlIlIlllIlll : llllllllllllllllllIlIlIlIlllIlIl) {
            if (llllllllllllllllllIlIlIlIlllIlll instanceof EntityPlayer && !llllllllllllllllllIlIlIlIlllIlll.getName().equals(CoordLogger.mc.player.getName())) {
                final Vec3d llllllllllllllllllIlIlIlIllllIII = new Vec3d(llllllllllllllllllIlIlIlIlllIlll.posX, llllllllllllllllllIlIlIlIlllIlll.posY, llllllllllllllllllIlIlIlIlllIlll.posZ);
                if (this.knownPlayers.containsKey(llllllllllllllllllIlIlIlIlllIlll)) {
                    if (Math.abs(CoordLogger.mc.player.getPositionVector().distanceTo(llllllllllllllllllIlIlIlIllllIII)) >= 128.0 && this.knownPlayers.get(llllllllllllllllllIlIlIlIlllIlll).distanceTo(llllllllllllllllllIlIlIlIllllIII) >= 64.0) {
                        this.sendNotification(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Player ").append(llllllllllllllllllIlIlIlIlllIlll.getName()).append(" moved to Position ").append(llllllllllllllllllIlIlIlIllllIII.toString())));
                    }
                    this.knownPlayers.put(llllllllllllllllllIlIlIlIlllIlll, llllllllllllllllllIlIlIlIllllIII);
                }
                else {
                    this.knownPlayers.put(llllllllllllllllllIlIlIlIlllIlll, llllllllllllllllllIlIlIlIllllIII);
                }
            }
        }
    }
    
    @EventTarget
    public void onPacket(final EventSendPacket llllllllllllllllllIlIlIllIIIIIII) {
        if (this.lightning.getValue() && llllllllllllllllllIlIlIllIIIIIII.getPacket() instanceof SPacketSoundEffect) {
            this.packet = (SPacketSoundEffect)llllllllllllllllllIlIlIllIIIIIII.getPacket();
            if (this.packet.getCategory() == SoundCategory.WEATHER && this.packet.getSound() == SoundEvents.ENTITY_LIGHTNING_THUNDER) {
                this.sendNotification(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Lightning spawned at X").append(this.packet.getX()).append(" Z").append(this.packet.getZ())));
            }
        }
        if (llllllllllllllllllIlIlIllIIIIIII.getPacket() instanceof SPacketEffect) {
            this.packet2 = (SPacketEffect)llllllllllllllllllIlIlIllIIIIIII.getPacket();
            if (this.portal.getValue() && this.packet2.getSoundType() == 1038) {
                this.sendNotification(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("End Portal activated at X").append(this.packet2.getSoundPos().getX()).append(" Y").append(this.packet2.getSoundPos().getY()).append(" Z").append(this.packet2.getSoundPos().getZ())));
            }
            if (this.wither.getValue() && this.packet2.getSoundType() == 1023) {
                this.sendNotification(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Wither spawned at X").append(this.packet2.getSoundPos().getX()).append(" Y").append(this.packet2.getSoundPos().getY()).append(" Z").append(this.packet2.getSoundPos().getZ())));
            }
            if (this.dragon.getValue() && this.packet2.getSoundType() == 1028) {
                this.sendNotification(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Dragon killed at X").append(this.packet2.getSoundPos().getX()).append(" Y").append(this.packet2.getSoundPos().getY()).append(" Z").append(this.packet2.getSoundPos().getZ())));
            }
        }
    }
    
    public CoordLogger() {
        super("CoordLogger", "Logs coords taken from several exploits", 0, Category.MISC, true);
        this.tp = this.register(new Value<Boolean>("TpExploit", this, false));
        this.lightning = this.register(new Value<Boolean>("Thunder", this, false));
        this.portal = this.register(new Value<Boolean>("EndPortal", this, false));
        this.wither = this.register(new Value<Boolean>("Wither", this, false));
        this.dragon = this.register(new Value<Boolean>("Dragon", this, false));
        this.savetofile = this.register(new Value<Boolean>("SaveToFile", this, false));
        this.knownPlayers = new HashMap<Entity, Vec3d>();
    }
    
    private void sendNotification(final String llllllllllllllllllIlIlIlIllIllII) {
        Command.sendChatMessage(llllllllllllllllllIlIlIlIllIllII);
        if (this.savetofile.getValue()) {
            Wrapper.getFileManager().appendTextFile(llllllllllllllllllIlIlIlIllIllII, "CoordLogger.txt");
        }
    }
}
