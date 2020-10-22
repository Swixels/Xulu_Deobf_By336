package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import java.util.concurrent.*;
import java.util.*;
import net.minecraftforge.common.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.module.*;

public class PearlAlert extends Module
{
    private final /* synthetic */ Value<String> color;
    private final /* synthetic */ Value<Boolean> watermark;
    /* synthetic */ ConcurrentHashMap<UUID, Integer> uuidMap;
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public String getTitle(final String llllllllllllllllIlllllIIIIllIlIl) {
        if (llllllllllllllllIlllllIIIIllIlIl.equalsIgnoreCase("west")) {
            return "east";
        }
        if (llllllllllllllllIlllllIIIIllIlIl.equalsIgnoreCase("east")) {
            return "west";
        }
        return llllllllllllllllIlllllIIIIllIlIl;
    }
    
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onUpdate() {
        for (final Entity llllllllllllllllIlllllIIIlIIIIII : PearlAlert.mc.world.loadedEntityList) {
            if (llllllllllllllllIlllllIIIlIIIIII instanceof EntityEnderPearl) {
                EntityPlayer llllllllllllllllIlllllIIIlIIIIIl = null;
                for (final EntityPlayer llllllllllllllllIlllllIIIlIIIIlI : PearlAlert.mc.world.playerEntities) {
                    if (llllllllllllllllIlllllIIIlIIIIIl == null || llllllllllllllllIlllllIIIlIIIIII.getDistance((Entity)llllllllllllllllIlllllIIIlIIIIlI) < llllllllllllllllIlllllIIIlIIIIII.getDistance((Entity)llllllllllllllllIlllllIIIlIIIIIl)) {
                        llllllllllllllllIlllllIIIlIIIIIl = llllllllllllllllIlllllIIIlIIIIlI;
                    }
                }
                if (llllllllllllllllIlllllIIIlIIIIIl == null || llllllllllllllllIlllllIIIlIIIIIl.getDistance(llllllllllllllllIlllllIIIlIIIIII) >= 2.0f || this.uuidMap.containsKey(llllllllllllllllIlllllIIIlIIIIII.getUniqueID()) || llllllllllllllllIlllllIIIlIIIIIl.getName().equalsIgnoreCase(PearlAlert.mc.player.getName())) {
                    continue;
                }
                this.uuidMap.put(llllllllllllllllIlllllIIIlIIIIII.getUniqueID(), 200);
                if (this.watermark.getValue()) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(llllllllllllllllIlllllIIIlIIIIIl.getName()).append(" threw a pearl towards ").append(this.getTitle(llllllllllllllllIlllllIIIlIIIIII.getHorizontalFacing().getName())).append("!")));
                }
                else {
                    Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(llllllllllllllllIlllllIIIlIIIIIl.getName()).append(" threw a pearl towards ").append(this.getTitle(llllllllllllllllIlllllIIIlIIIIII.getHorizontalFacing().getName())).append("!")));
                }
            }
        }
        this.uuidMap.forEach((llllllllllllllllIlllllIIIIllIIII, llllllllllllllllIlllllIIIIlIllll) -> {
            if (llllllllllllllllIlllllIIIIlIllll <= 0) {
                this.uuidMap.remove(llllllllllllllllIlllllIIIIllIIII);
            }
            else {
                this.uuidMap.put(llllllllllllllllIlllllIIIIllIIII, llllllllllllllllIlllllIIIIlIllll - 1);
            }
        });
    }
    
    public PearlAlert() {
        super("PearlAlert", "Alerts pearls thrown", 0, Category.COMBAT, true);
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.color = this.register(new Value<String>("Color", this, "White", ColorTextUtils.colors));
        this.uuidMap = new ConcurrentHashMap<UUID, Integer>();
    }
}
