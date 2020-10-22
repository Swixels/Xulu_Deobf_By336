package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.item.*;
import com.elementars.eclient.*;
import dev.xulu.newgui.util.*;
import java.util.function.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;

public class PvPInfo extends Element
{
    private /* synthetic */ Vec3d[] offset;
    private final /* synthetic */ Value<Boolean> rainbow;
    public static /* synthetic */ boolean place;
    public static /* synthetic */ boolean attack;
    public static /* synthetic */ boolean surround;
    
    public PvPInfo() {
        super("PvPInfo");
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.offset = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, -1.0, 0.0) };
    }
    
    public String getFromBoolean(final boolean lllllllllllllllllIllIIIllIIIlIll) {
        if (lllllllllllllllllIllIIIllIIIlIll) {
            return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("TRUE"));
        }
        return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("FALSE"));
    }
    
    static {
        PvPInfo.attack = false;
        PvPInfo.place = false;
        PvPInfo.surround = false;
    }
    
    private String getCaura() {
        boolean lllllllllllllllllIllIIIllIIIllll = false;
        if (Xulu.MODULE_MANAGER.getModuleByName("AutoCrystal") != null) {
            lllllllllllllllllIllIIIllIIIllll = Xulu.MODULE_MANAGER.getModuleByName("AutoCrystal").isToggled();
            if (lllllllllllllllllIllIIIllIIIllll) {
                return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("ON"));
            }
        }
        if (Xulu.MODULE_MANAGER.getModuleByName("AutoCrystalO") != null) {
            lllllllllllllllllIllIIIllIIIllll = Xulu.MODULE_MANAGER.getModuleByName("AutoCrystalO").isToggled();
            if (lllllllllllllllllIllIIIllIIIllll) {
                return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("ON"));
            }
        }
        if (Xulu.MODULE_MANAGER.getModuleByName("AutoCrystalX") != null) {
            lllllllllllllllllIllIIIllIIIllll = Xulu.MODULE_MANAGER.getModuleByName("AutoCrystalX").isToggled();
            if (lllllllllllllllllIllIIIllIIIllll) {
                return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("ON"));
            }
        }
        return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("OFF"));
    }
    
    @Override
    public void onRender() {
        this.checkSurround();
        float lllllllllllllllllIllIIlIllIIlIlI = (float)this.y;
        int lllllllllllllllllIllIIlIllIIlIIl = ColorUtil.getClickGUIColor().getRGB();
        int lllllllllllllllllIllIIlIllIIlIII = PvPInfo.mc.player.inventory.mainInventory.stream().filter(lllllllllllllllllIllIIIllIIIIlll -> lllllllllllllllllIllIIIllIIIIlll.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::func_190916_E).sum();
        if (PvPInfo.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            ++lllllllllllllllllIllIIlIllIIlIII;
        }
        final String[] lllllllllllllllllIllIIlIllIIIlll = { String.valueOf(new StringBuilder().append("ATT: ").append(this.getFromBoolean(PvPInfo.attack))), String.valueOf(new StringBuilder().append("PLC: ").append(this.getFromBoolean(PvPInfo.place))), String.valueOf(new StringBuilder().append("FOB: ").append(this.getFromBoolean(PvPInfo.surround))), String.valueOf(new StringBuilder().append("PING: ").append(this.getPing((PvPInfo.mc.getConnection() != null && PvPInfo.mc.player != null && PvPInfo.mc.getConnection().getPlayerInfo(PvPInfo.mc.player.entityUniqueID) != null) ? PvPInfo.mc.getConnection().getPlayerInfo(PvPInfo.mc.player.entityUniqueID).getResponseTime() : -1))), String.valueOf(new StringBuilder().append("TOTEMS: ").append(this.getTotems(lllllllllllllllllIllIIlIllIIlIII))), String.valueOf(new StringBuilder().append("AT: ").append(this.getAutoTrap())), String.valueOf(new StringBuilder().append("SU: ").append(this.getSurround())), String.valueOf(new StringBuilder().append("CA: ").append(this.getCaura())) };
        this.width = PvPInfo.fontRenderer.getStringWidth(ListHelper.longest(lllllllllllllllllIllIIlIllIIIlll)) + 2;
        this.height = (PvPInfo.fontRenderer.FONT_HEIGHT + 1) * lllllllllllllllllIllIIlIllIIIlll.length + 1;
        if (this.rainbow.getValue()) {
            lllllllllllllllllIllIIlIllIIlIIl = Xulu.rgb;
        }
        if (Xulu.CustomFont) {
            final char lllllllllllllllllIllIIlIllIIIIII = (Object)lllllllllllllllllIllIIlIllIIIlll;
            final float lllllllllllllllllIllIIlIlIlllllI = lllllllllllllllllIllIIlIllIIIIII.length;
            for (String lllllllllllllllllIllIIlIlIllllIl = (String)0; lllllllllllllllllIllIIlIlIllllIl < lllllllllllllllllIllIIlIlIlllllI; ++lllllllllllllllllIllIIlIlIllllIl) {
                final String lllllllllllllllllIllIIlIllIIllIl = lllllllllllllllllIllIIlIllIIIIII[lllllllllllllllllIllIIlIlIllllIl];
                Xulu.cFontRenderer.drawStringWithShadow(lllllllllllllllllIllIIlIllIIllIl, this.x + 1.0, lllllllllllllllllIllIIlIllIIlIlI + 1.0f, ColorUtils.changeAlpha(lllllllllllllllllIllIIlIllIIlIIl, Global.hudAlpha.getValue()));
                lllllllllllllllllIllIIlIllIIlIlI += 10.0f;
            }
        }
        else {
            final char lllllllllllllllllIllIIlIllIIIIII = (Object)lllllllllllllllllIllIIlIllIIIlll;
            final float lllllllllllllllllIllIIlIlIlllllI = lllllllllllllllllIllIIlIllIIIIII.length;
            for (String lllllllllllllllllIllIIlIlIllllIl = (String)0; lllllllllllllllllIllIIlIlIllllIl < lllllllllllllllllIllIIlIlIlllllI; ++lllllllllllllllllIllIIlIlIllllIl) {
                final String lllllllllllllllllIllIIlIllIIllII = lllllllllllllllllIllIIlIllIIIIII[lllllllllllllllllIllIIlIlIllllIl];
                PvPInfo.fontRenderer.drawStringWithShadow(lllllllllllllllllIllIIlIllIIllII, (float)this.x + 1.0f, lllllllllllllllllIllIIlIllIIlIlI + 1.0f, ColorUtils.changeAlpha(lllllllllllllllllIllIIlIllIIlIIl, Global.hudAlpha.getValue()));
                lllllllllllllllllIllIIlIllIIlIlI += 10.0f;
            }
        }
    }
    
    private String getPing(final long lllllllllllllllllIllIIlIlIllIlII) {
        if (lllllllllllllllllIllIIlIlIllIlII > 100L) {
            return String.valueOf(new StringBuilder().append("").append(ChatFormatting.RED).append(lllllllllllllllllIllIIlIlIllIlII));
        }
        return String.valueOf(new StringBuilder().append("").append(ChatFormatting.GREEN).append(lllllllllllllllllIllIIlIlIllIlII));
    }
    
    private String getTotems(final int lllllllllllllllllIllIIlIlIllIlll) {
        if (lllllllllllllllllIllIIlIlIllIlll > 0) {
            return String.valueOf(new StringBuilder().append("").append(ChatFormatting.GREEN).append(lllllllllllllllllIllIIlIlIllIlll));
        }
        return String.valueOf(new StringBuilder().append("").append(ChatFormatting.RED).append(lllllllllllllllllIllIIlIlIllIlll));
    }
    
    private String getAutoTrap() {
        if (Xulu.MODULE_MANAGER.getModuleByName("AutoTrap") == null) {
            return "NULL";
        }
        if (Xulu.MODULE_MANAGER.getModuleByName("AutoTrap").isToggled()) {
            return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("ON"));
        }
        return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("OFF"));
    }
    
    public void checkSurround() {
        if (PvPInfo.mc.player == null || PvPInfo.mc.world == null) {
            return;
        }
        boolean lllllllllllllllllIllIIlIllIlllIl = true;
        final short lllllllllllllllllIllIIlIllIllIlI = (Object)this.offset;
        for (long lllllllllllllllllIllIIlIllIllIIl = lllllllllllllllllIllIIlIllIllIlI.length, lllllllllllllllllIllIIlIllIllIII = 0; lllllllllllllllllIllIIlIllIllIII < lllllllllllllllllIllIIlIllIllIIl; ++lllllllllllllllllIllIIlIllIllIII) {
            final Vec3d lllllllllllllllllIllIIlIllIlllll = lllllllllllllllllIllIIlIllIllIlI[lllllllllllllllllIllIIlIllIllIII];
            if (PvPInfo.mc.world.getBlockState(new BlockPos(PvPInfo.mc.player.getPositionVector()).add(lllllllllllllllllIllIIlIllIlllll.x, lllllllllllllllllIllIIlIllIlllll.y, lllllllllllllllllIllIIlIllIlllll.z)).getBlock() != Blocks.OBSIDIAN && PvPInfo.mc.world.getBlockState(new BlockPos(PvPInfo.mc.player.getPositionVector()).add(lllllllllllllllllIllIIlIllIlllll.x, lllllllllllllllllIllIIlIllIlllll.y, lllllllllllllllllIllIIlIllIlllll.z)).getBlock() != Blocks.BEDROCK) {
                lllllllllllllllllIllIIlIllIlllIl = false;
            }
        }
        PvPInfo.surround = lllllllllllllllllIllIIlIllIlllIl;
    }
    
    private String getSurround() {
        if (Xulu.MODULE_MANAGER.getModuleByName("Surround") == null) {
            return "NULL";
        }
        if (Xulu.MODULE_MANAGER.getModuleByName("Surround").isToggled()) {
            return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("ON"));
        }
        return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("OFF"));
    }
}
