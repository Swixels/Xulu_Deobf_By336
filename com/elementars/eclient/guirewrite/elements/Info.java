package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.*;
import net.minecraft.potion.*;
import com.elementars.eclient.command.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import java.text.*;
import java.util.stream.*;
import java.util.function.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import com.elementars.eclient.module.*;

public class Info extends Element
{
    private final /* synthetic */ Value<Boolean> POTIONS;
    private final /* synthetic */ Value<Boolean> TPS;
    private final /* synthetic */ Value<Boolean> DURABILITY;
    /* synthetic */ DecimalFormat df;
    private final /* synthetic */ Value<String> color2;
    private final /* synthetic */ Value<Boolean> TIME;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Boolean> SERVER_IP;
    private final /* synthetic */ Value<Boolean> t24;
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<Boolean> FPS;
    private final /* synthetic */ Value<Boolean> PING;
    /* synthetic */ DecimalFormat df2;
    private final /* synthetic */ Value<String> order;
    private final /* synthetic */ Value<Boolean> ALPHABETICAL;
    private final /* synthetic */ Value<Boolean> SPEED;
    
    @Override
    public void onRender() {
        float lIIllIlllllIllI = (float)this.y;
        int lIIllIlllllIlIl = ColorUtil.getClickGUIColor().getRGB();
        if (this.rainbow.getValue()) {
            lIIllIlllllIlIl = Xulu.rgb;
        }
        final float lIIllIlllllIIll = Info.mc.timer.tickLength / 1000.0f;
        final ItemStack lIIllIlllllIIlI = Info.mc.player.getHeldItemMainhand();
        final List<String> lIIllIlllllIIII = new ArrayList<String>();
        final Map<String, PotionEffect> lIIllIllllIllll = new HashMap<String, PotionEffect>();
        List<String> lIIllIllllIllIl = new ArrayList<String>();
        if (this.FPS.getValue()) {
            lIIllIlllllIIII.add(String.valueOf(new StringBuilder().append("FPS: ").append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color2.getValue()).substring(1)).append(Minecraft.getDebugFPS())));
        }
        if (this.PING.getValue()) {
            lIIllIlllllIIII.add(String.valueOf(new StringBuilder().append("Ping: ").append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color2.getValue()).substring(1)).append((Info.mc.getConnection() != null && Info.mc.player != null && Info.mc.getConnection().getPlayerInfo(Info.mc.player.entityUniqueID) != null) ? Integer.valueOf(Info.mc.getConnection().getPlayerInfo(Info.mc.player.entityUniqueID).getResponseTime()) : "-1").append("ms")));
        }
        if (this.TPS.getValue()) {
            lIIllIlllllIIII.add(String.valueOf(new StringBuilder().append("TPS: ").append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color2.getValue()).substring(1)).append(this.df2.format(LagCompensator.INSTANCE.getTickRate()))));
        }
        if (this.SPEED.getValue()) {
            lIIllIlllllIIII.add(String.valueOf(new StringBuilder().append("Speed: ").append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color2.getValue()).substring(1)).append(this.df.format(MathHelper.sqrt(Math.pow(coordsDiff('x'), 2.0) + Math.pow(coordsDiff('z'), 2.0)) / lIIllIlllllIIll * 3.6)).append(" hm/h")));
        }
        if (this.TIME.getValue()) {
            lIIllIlllllIIII.add(String.valueOf(new StringBuilder().append("Time: ").append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color2.getValue()).substring(1)).append(this.t24.getValue() ? new SimpleDateFormat("k:mm").format(new Date()) : new SimpleDateFormat("h:mm a").format(new Date()))));
        }
        if (this.DURABILITY.getValue() && this.isToolArmor(lIIllIlllllIIlI.getItem())) {
            lIIllIlllllIIII.add(String.valueOf(new StringBuilder().append("Durability: ").append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color2.getValue()).substring(1)).append(lIIllIlllllIIlI.getMaxDamage() - lIIllIlllllIIlI.getItemDamage())));
        }
        if (this.SERVER_IP.getValue()) {
            lIIllIlllllIIII.add(String.valueOf(new StringBuilder().append("Server IP: ").append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color2.getValue()).substring(1)).append((Info.mc.getCurrentServerData() == null) ? "None" : Info.mc.getCurrentServerData().serverIP)));
        }
        if (this.POTIONS.getValue()) {
            for (final PotionEffect lIIlllIIIIIIlll : Info.mc.player.getActivePotionEffects()) {
                final String lIIlllIIIIIlIII = String.valueOf(new StringBuilder().append(PotionUtil.getPotionName(lIIlllIIIIIIlll.getPotion())).append(" ").append((lIIlllIIIIIIlll.getAmplifier() + 1 != 1) ? String.valueOf(new StringBuilder().append(lIIlllIIIIIIlll.getAmplifier() + 1).append(" ")) : "").append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color2.getValue()).substring(1)).append((this.getTime(lIIlllIIIIIIlll.getDuration() / 20).length() == 1) ? "0" : "").append(this.getTime(lIIlllIIIIIIlll.getDuration() / 20)));
                lIIllIllllIllll.put(lIIlllIIIIIlIII, lIIlllIIIIIIlll);
            }
            if (Xulu.CustomFont) {
                lIIllIllllIllIl = lIIllIllllIllll.keySet().stream().sorted(Comparator.comparing(lIIllIlllIIIIII -> Xulu.cFontRenderer.getStringWidth(lIIllIlllIIIIII))).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
            }
            else {
                lIIllIllllIllIl = lIIllIllllIllll.keySet().stream().sorted(Comparator.comparing((Function<? super Object, ? extends Comparable>)Info.fontRenderer::func_78256_a)).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
            }
            if (this.ALPHABETICAL.getValue()) {
                final String[] lIIlllIIIIIIIIl = lIIllIllllIllIl.toArray(new String[0]);
                for (int lIIlllIIIIIIIII = lIIllIllllIllIl.size(), lIIlllIIIIIIIll = 0; lIIlllIIIIIIIll < lIIlllIIIIIIIII; ++lIIlllIIIIIIIll) {
                    for (int lIIlllIIIIIIlII = lIIlllIIIIIIIll + 1; lIIlllIIIIIIlII < lIIlllIIIIIIIII; ++lIIlllIIIIIIlII) {
                        if (lIIlllIIIIIIIIl[lIIlllIIIIIIIll].compareTo(lIIlllIIIIIIIIl[lIIlllIIIIIIlII]) > 0) {
                            final String lIIlllIIIIIIllI = lIIlllIIIIIIIIl[lIIlllIIIIIIIll];
                            lIIlllIIIIIIIIl[lIIlllIIIIIIIll] = lIIlllIIIIIIIIl[lIIlllIIIIIIlII];
                            lIIlllIIIIIIIIl[lIIlllIIIIIIlII] = lIIlllIIIIIIllI;
                        }
                    }
                }
                lIIllIllllIllIl.clear();
                final int lIIllIlllIllIII = (Object)lIIlllIIIIIIIIl;
                final Exception lIIllIlllIlIllI = (Exception)lIIllIlllIllIII.length;
                for (String lIIllIlllIlIlII = (String)0; lIIllIlllIlIlII < lIIllIlllIlIllI; ++lIIllIlllIlIlII) {
                    final String lIIlllIIIIIIIlI = lIIllIlllIllIII[lIIllIlllIlIlII];
                    try {
                        lIIllIllllIllIl.add(lIIlllIIIIIIIlI);
                    }
                    catch (Exception ex) {}
                }
                if (this.order.getValue().equalsIgnoreCase("Down")) {
                    Collections.reverse(lIIllIllllIllIl);
                }
            }
        }
        this.width = 50.0;
        this.height = 50.0;
        if (Xulu.CustomFont) {
            lIIllIlllllIIII.sort(Comparator.comparing(lIIllIlllIIIIll -> Xulu.cFontRenderer.getStringWidth(lIIllIlllIIIIll)));
            Collections.reverse(lIIllIlllllIIII);
            if (this.order.getValue().equalsIgnoreCase("Down")) {
                lIIllIlllllIllI += 39.0f;
            }
            for (final String lIIllIllllllllI : lIIllIllllIllIl) {
                Xulu.cFontRenderer.drawStringWithShadow(lIIllIllllllllI, this.x - (this.mode.getValue().equalsIgnoreCase("Right") ? 0 : Xulu.cFontRenderer.getStringWidth(lIIllIllllllllI)) + (this.mode.getValue().equalsIgnoreCase("Right") ? 1.0 : (this.getFrame().width - 2.0)), lIIllIlllllIllI + 1.0f, ColorUtils.changeAlpha(lIIllIllllIllll.get(lIIllIllllllllI).getPotion().getLiquidColor(), Global.hudAlpha.getValue()));
                lIIllIlllllIllI += (this.order.getValue().equalsIgnoreCase("Up") ? 10 : -10);
            }
            for (final String lIIllIlllllllII : lIIllIlllllIIII) {
                Xulu.cFontRenderer.drawStringWithShadow(lIIllIlllllllII, this.x - (this.mode.getValue().equalsIgnoreCase("Right") ? 0 : Xulu.cFontRenderer.getStringWidth(lIIllIlllllllII)) + (this.mode.getValue().equalsIgnoreCase("Right") ? 1.0 : (this.getFrame().width - 2.0)), lIIllIlllllIllI + 1.0f, ColorUtils.changeAlpha(lIIllIlllllIlIl, Global.hudAlpha.getValue()));
                lIIllIlllllIllI += (this.order.getValue().equalsIgnoreCase("Up") ? 10 : -10);
            }
        }
        else {
            lIIllIlllllIIII.sort(Comparator.comparing((Function<? super String, ? extends Comparable>)Info.fontRenderer::func_78256_a));
            Collections.reverse(lIIllIlllllIIII);
            if (this.order.getValue().equalsIgnoreCase("Down")) {
                lIIllIlllllIllI += 39.0f;
            }
            for (final String lIIllIllllllIll : lIIllIllllIllIl) {
                Info.fontRenderer.drawStringWithShadow(lIIllIllllllIll, (float)this.x - (this.mode.getValue().equalsIgnoreCase("Right") ? 0 : Info.fontRenderer.getStringWidth(lIIllIllllllIll)) + (float)(this.mode.getValue().equalsIgnoreCase("Right") ? 1.0 : (this.getFrame().width - 2.0)), lIIllIlllllIllI + 1.0f, ColorUtils.changeAlpha(lIIllIllllIllll.get(lIIllIllllllIll).getPotion().getLiquidColor(), Global.hudAlpha.getValue()));
                lIIllIlllllIllI += (this.order.getValue().equalsIgnoreCase("Up") ? 10 : -10);
            }
            for (final String lIIllIllllllIIl : lIIllIlllllIIII) {
                Info.fontRenderer.drawStringWithShadow(lIIllIllllllIIl, (float)this.x - (this.mode.getValue().equalsIgnoreCase("Right") ? 0 : Info.fontRenderer.getStringWidth(lIIllIllllllIIl)) + (float)(this.mode.getValue().equalsIgnoreCase("Right") ? 1.0 : (this.getFrame().width - 2.0)), lIIllIlllllIllI + 1.0f, ColorUtils.changeAlpha(lIIllIlllllIlIl, Global.hudAlpha.getValue()));
                lIIllIlllllIllI += (this.order.getValue().equalsIgnoreCase("Up") ? 10 : -10);
            }
        }
    }
    
    public String getTime(int lIIllIlllIIlIIl) {
        int lIIllIlllIIlIII;
        for (lIIllIlllIIlIII = 0; lIIllIlllIIlIIl > 59; lIIllIlllIIlIIl -= 60, ++lIIllIlllIIlIII) {}
        return String.valueOf(new StringBuilder().append(lIIllIlllIIlIII).append(":").append((this.df.format(lIIllIlllIIlIIl).length() == 1) ? String.valueOf(new StringBuilder().append("0").append(this.df.format(lIIllIlllIIlIIl))) : this.df.format(lIIllIlllIIlIIl)));
    }
    
    public boolean isToolArmor(final Item lIIlllIIIlllllI) {
        return lIIlllIIIlllllI instanceof ItemArmor || lIIlllIIIlllllI == Items.DIAMOND_SWORD || lIIlllIIIlllllI == Items.DIAMOND_PICKAXE || lIIlllIIIlllllI == Items.DIAMOND_AXE || lIIlllIIIlllllI == Items.DIAMOND_SHOVEL || lIIlllIIIlllllI == Items.DIAMOND_HOE || lIIlllIIIlllllI == Items.IRON_SWORD || lIIlllIIIlllllI == Items.IRON_PICKAXE || lIIlllIIIlllllI == Items.IRON_AXE || lIIlllIIIlllllI == Items.IRON_SHOVEL || lIIlllIIIlllllI == Items.IRON_HOE || lIIlllIIIlllllI == Items.GOLDEN_SWORD || lIIlllIIIlllllI == Items.GOLDEN_PICKAXE || lIIlllIIIlllllI == Items.GOLDEN_AXE || lIIlllIIIlllllI == Items.GOLDEN_SHOVEL || lIIlllIIIlllllI == Items.GOLDEN_HOE || lIIlllIIIlllllI == Items.STONE_SWORD || lIIlllIIIlllllI == Items.STONE_PICKAXE || lIIlllIIIlllllI == Items.STONE_AXE || lIIlllIIIlllllI == Items.STONE_SHOVEL || lIIlllIIIlllllI == Items.STONE_HOE || lIIlllIIIlllllI == Items.WOODEN_SWORD || lIIlllIIIlllllI == Items.WOODEN_PICKAXE || lIIlllIIIlllllI == Items.WOODEN_AXE || lIIlllIIIlllllI == Items.WOODEN_SHOVEL || lIIlllIIIlllllI == Items.WOODEN_HOE;
    }
    
    public Info() {
        super("Info");
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.t24 = this.register(new Value<Boolean>("24hr time", this, false));
        this.mode = this.register(new Value<String>("Aligned", this, "Right", new String[] { "Right", "Left" }));
        this.order = this.register(new Value<String>("Ordering", this, "Down", new String[] { "Up", "Down" }));
        this.color2 = this.register(new Value<String>("2nd Color", this, "LightGray", ColorTextUtils.colors));
        this.FPS = this.register(new Value<Boolean>("Fps", this, true));
        this.PING = this.register(new Value<Boolean>("Ping", this, true));
        this.TPS = this.register(new Value<Boolean>("Tps", this, true));
        this.SPEED = this.register(new Value<Boolean>("Speed", this, true));
        this.TIME = this.register(new Value<Boolean>("Time", this, true));
        this.DURABILITY = this.register(new Value<Boolean>("Durability", this, true));
        this.SERVER_IP = this.register(new Value<Boolean>("Server IP", this, true));
        this.POTIONS = this.register(new Value<Boolean>("Potions", this, true));
        this.ALPHABETICAL = this.register(new Value<Boolean>("ABC Potions", this, false));
        this.df = new DecimalFormat("#.#");
        this.df2 = new DecimalFormat("#.###");
    }
    
    public static double coordsDiff(final char lIIlllIIlIIIIIl) {
        switch (lIIlllIIlIIIIIl) {
            case 'x': {
                return Info.mc.player.posX - Info.mc.player.prevPosX;
            }
            case 'z': {
                return Info.mc.player.posZ - Info.mc.player.prevPosZ;
            }
            default: {
                return 0.0;
            }
        }
    }
}
