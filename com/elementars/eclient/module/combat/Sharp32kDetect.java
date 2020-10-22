package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import com.elementars.eclient.module.*;
import java.util.*;
import com.elementars.eclient.command.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;

public class Sharp32kDetect extends Module
{
    private final /* synthetic */ Value<Boolean> color;
    private /* synthetic */ Set<EntityPlayer> sword;
    private final /* synthetic */ Value<Boolean> watermark;
    public static final /* synthetic */ Minecraft mc;
    
    public Sharp32kDetect() {
        super("32kDetect", "Detects held 32ks", 0, Category.COMBAT, true);
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.color = this.register(new Value<Boolean>("Color", this, false));
        this.sword = Collections.newSetFromMap(new WeakHashMap<EntityPlayer, Boolean>());
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    @Override
    public void onUpdate() {
        for (final EntityPlayer lllllllllllllllllllIlllIIlIlIIlI : Sharp32kDetect.mc.world.playerEntities) {
            if (lllllllllllllllllllIlllIIlIlIIlI.equals((Object)Sharp32kDetect.mc.player)) {
                continue;
            }
            if (this.is32k(lllllllllllllllllllIlllIIlIlIIlI, lllllllllllllllllllIlllIIlIlIIlI.itemStackMainHand) && !this.sword.contains(lllllllllllllllllllIlllIIlIlIIlI)) {
                if (this.watermark.getValue()) {
                    if (this.color.getValue()) {
                        Command.sendChatMessage(String.valueOf(new StringBuilder().append("&4").append(lllllllllllllllllllIlllIIlIlIIlI.getDisplayNameString()).append(" is holding a 32k")));
                    }
                    else {
                        Command.sendChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllllIlllIIlIlIIlI.getDisplayNameString()).append(" is holding a 32k")));
                    }
                }
                else if (this.color.getValue()) {
                    Command.sendRawChatMessage(String.valueOf(new StringBuilder().append("&4").append(lllllllllllllllllllIlllIIlIlIIlI.getDisplayNameString()).append(" is holding a 32k")));
                }
                else {
                    Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllllIlllIIlIlIIlI.getDisplayNameString()).append(" is holding a 32k")));
                }
                this.sword.add(lllllllllllllllllllIlllIIlIlIIlI);
            }
            if (!this.sword.contains(lllllllllllllllllllIlllIIlIlIIlI)) {
                continue;
            }
            if (this.is32k(lllllllllllllllllllIlllIIlIlIIlI, lllllllllllllllllllIlllIIlIlIIlI.itemStackMainHand)) {
                continue;
            }
            if (this.watermark.getValue()) {
                if (this.color.getValue()) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("&2").append(lllllllllllllllllllIlllIIlIlIIlI.getDisplayNameString()).append(" is no longer holding a 32k")));
                }
                else {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllllIlllIIlIlIIlI.getDisplayNameString()).append(" is no longer holding a 32k")));
                }
            }
            else if (this.color.getValue()) {
                Command.sendRawChatMessage(String.valueOf(new StringBuilder().append("&2").append(lllllllllllllllllllIlllIIlIlIIlI.getDisplayNameString()).append(" is no longer holding a 32k")));
            }
            else {
                Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllllIlllIIlIlIIlI.getDisplayNameString()).append(" is no longer holding a 32k")));
            }
            this.sword.remove(lllllllllllllllllllIlllIIlIlIIlI);
        }
    }
    
    private boolean is32k(final EntityPlayer lllllllllllllllllllIlllIIlIllIlI, final ItemStack lllllllllllllllllllIlllIIlIllIIl) {
        if (lllllllllllllllllllIlllIIlIllIIl.getItem() instanceof ItemSword) {
            final NBTTagList lllllllllllllllllllIlllIIlIlllII = lllllllllllllllllllIlllIIlIllIIl.getEnchantmentTagList();
            if (lllllllllllllllllllIlllIIlIlllII != null) {
                for (int lllllllllllllllllllIlllIIlIlllIl = 0; lllllllllllllllllllIlllIIlIlllIl < lllllllllllllllllllIlllIIlIlllII.tagCount(); ++lllllllllllllllllllIlllIIlIlllIl) {
                    if (lllllllllllllllllllIlllIIlIlllII.getCompoundTagAt(lllllllllllllllllllIlllIIlIlllIl).getShort("lvl") >= 32767) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
