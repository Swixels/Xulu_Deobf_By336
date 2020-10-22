package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import com.elementars.eclient.event.*;

public class NoRender extends Module
{
    private final /* synthetic */ Value<Boolean> paint;
    public final /* synthetic */ Value<Boolean> armor;
    private final /* synthetic */ Value<Boolean> object;
    public final /* synthetic */ Value<Boolean> armorTrans;
    private final /* synthetic */ Value<Boolean> gentity;
    public final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<Boolean> fire;
    private final /* synthetic */ Value<Boolean> mob;
    private final /* synthetic */ Value<Boolean> xp;
    
    @SubscribeEvent
    public void onBlockOverlay(final RenderBlockOverlayEvent lllllllllllllllllIIllllIIlllIIIl) {
        if (this.fire.getValue() && lllllllllllllllllIIllllIIlllIIIl.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) {
            lllllllllllllllllIIllllIIlllIIIl.setCanceled(true);
        }
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public NoRender() {
        super("NoRender", "Prevents rendering of certain things", 0, Category.RENDER, true);
        this.mob = this.register(new Value<Boolean>("Mob", this, false));
        this.gentity = this.register(new Value<Boolean>("GEntity", this, false));
        this.armor = this.register(new Value<Boolean>("Armor", this, false));
        this.armorTrans = this.register(new Value<Boolean>("Armor Transparency", this, false));
        this.alpha = this.register(new Value<Integer>("Transparency", this, 255, 0, 255));
        this.object = this.register(new Value<Boolean>("Object", this, false));
        this.xp = this.register(new Value<Boolean>("XP", this, false));
        this.paint = this.register(new Value<Boolean>("Paintings", this, false));
        this.fire = this.register(new Value<Boolean>("Fire", this, true));
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket lllllllllllllllllIIllllIIllllIII) {
        final Packet lllllllllllllllllIIllllIIllllIlI = lllllllllllllllllIIllllIIllllIII.getPacket();
        if ((lllllllllllllllllIIllllIIllllIlI instanceof SPacketSpawnMob && this.mob.getValue()) || (lllllllllllllllllIIllllIIllllIlI instanceof SPacketSpawnGlobalEntity && this.gentity.getValue()) || (lllllllllllllllllIIllllIIllllIlI instanceof SPacketSpawnObject && this.object.getValue()) || (lllllllllllllllllIIllllIIllllIlI instanceof SPacketSpawnExperienceOrb && this.xp.getValue()) || (lllllllllllllllllIIllllIIllllIlI instanceof SPacketSpawnPainting && this.paint.getValue())) {
            lllllllllllllllllIIllllIIllllIII.setCancelled(true);
        }
    }
}
