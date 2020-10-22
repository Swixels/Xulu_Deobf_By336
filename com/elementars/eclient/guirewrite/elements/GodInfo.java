package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.friend.*;
import net.minecraft.entity.*;
import java.util.function.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import java.util.*;
import com.elementars.eclient.module.combat.*;
import com.elementars.eclient.module.*;
import java.awt.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;

public class GodInfo extends Element
{
    private final /* synthetic */ Value<Boolean> cf;
    private /* synthetic */ Vec3d[] offset;
    
    @Override
    public void onRender() {
        int llIllllllllIIll = GodInfo.mc.player.inventory.mainInventory.stream().filter(llIlllllIllllII -> llIlllllIllllII.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::func_190916_E).sum();
        if (GodInfo.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            ++llIllllllllIIll;
        }
        final List<String> llIllllllllIIlI = new ArrayList<String>(Arrays.asList("HTR", "PLR", String.valueOf(llIllllllllIIll), String.valueOf(new StringBuilder().append("PING ").append((GodInfo.mc.getConnection() != null && GodInfo.mc.player != null && GodInfo.mc.getConnection().getPlayerInfo(GodInfo.mc.player.entityUniqueID) != null) ? Integer.valueOf(GodInfo.mc.getConnection().getPlayerInfo(GodInfo.mc.player.entityUniqueID).getResponseTime()) : "-1"))));
        final EntityPlayer llIllllllllIIIl = (EntityPlayer)GodInfo.mc.world.playerEntities.stream().filter(llIlllllIlllllI -> !llIlllllIlllllI.getName().equals(GodInfo.mc.player.getName())).filter(llIllllllIIIIlI -> !Friends.isFriend(llIllllllIIIIlI.getName())).min(Comparator.comparing(llIllllllIIIlIl -> GodInfo.mc.player.getDistance(llIllllllIIIlIl))).orElse(null);
        if (llIllllllllIIIl != null) {
            llIllllllllIIlI.add("LBY");
        }
        this.width = GodInfo.fontRenderer.getStringWidth(ListHelper.longest(llIllllllllIIlI)) + 2;
        this.height = (GodInfo.fontRenderer.FONT_HEIGHT + 1) * llIllllllllIIlI.size() + 1;
        float llIllllllllIIII = (float)this.y;
        for (final String llIllllllllIlIl : llIllllllllIIlI) {
            if (this.cf.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(llIllllllllIlIl, (float)this.x + 1.0f, llIllllllllIIII + 1.0f, ColorUtils.changeAlpha(this.getColor(llIllllllllIlIl, llIllllllllIIIl), Global.hudAlpha.getValue()));
            }
            else {
                Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(llIllllllllIlIl, (float)this.x + 1.0f, llIllllllllIIII + 1.0f, ColorUtils.changeAlpha(this.getColor(llIllllllllIlIl, llIllllllllIIIl), Global.hudAlpha.getValue()));
            }
            llIllllllllIIII += 10.0f;
        }
    }
    
    private int getColor(final String llIllllllIIllIl, final EntityPlayer llIllllllIIlIIl) {
        float llIllllllIIIlll = -1;
        switch (llIllllllIIllIl.hashCode()) {
            case 71878: {
                if (llIllllllIIllIl.equals("HTR")) {
                    llIllllllIIIlll = 0;
                    break;
                }
                break;
            }
            case 79318: {
                if (llIllllllIIllIl.equals("PLR")) {
                    llIllllllIIIlll = 1;
                    break;
                }
                break;
            }
            case 75171: {
                if (llIllllllIIllIl.equals("LBY")) {
                    llIllllllIIIlll = 2;
                    break;
                }
                break;
            }
        }
        switch (llIllllllIIIlll) {
            case 0.0f: {
                if (llIllllllIIlIIl != null && GodInfo.mc.player.getDistance((Entity)llIllllllIIlIIl) <= Xulu.VALUE_MANAGER.getValueT("Hit Range", AutoCrystal.class).getValue()) {
                    return new Color(0, 255, 0).getRGB();
                }
                return new Color(255, 0, 0).getRGB();
            }
            case 1.0f: {
                if (llIllllllIIlIIl != null && GodInfo.mc.player.getDistance((Entity)llIllllllIIlIIl) <= Xulu.VALUE_MANAGER.getValueT("Hit Range", AutoCrystal.class).getValue()) {
                    return new Color(0, 255, 0).getRGB();
                }
                return new Color(255, 0, 0).getRGB();
            }
            case 2.0f: {
                if (llIllllllIIlIIl != null && this.checkSurround(llIllllllIIlIIl)) {
                    return new Color(0, 255, 0).getRGB();
                }
                return new Color(255, 0, 0).getRGB();
            }
            default: {
                if (llIllllllIIllIl.startsWith("PING")) {
                    final int llIllllllIlIIIl = Integer.valueOf(llIllllllIIllIl.substring(5));
                    if (llIllllllIlIIIl > 100) {
                        return new Color(255, 0, 0).getRGB();
                    }
                    return new Color(0, 255, 0).getRGB();
                }
                else {
                    try {
                        final int llIllllllIlIIII = Integer.valueOf(llIllllllIIllIl);
                        if (llIllllllIlIIII > 0) {
                            return new Color(0, 255, 0).getRGB();
                        }
                        return new Color(255, 0, 0).getRGB();
                    }
                    catch (Exception llIllllllIIllll) {
                        llIllllllIIllll.printStackTrace();
                        return -1;
                    }
                }
                break;
            }
        }
    }
    
    public boolean checkSurround(final EntityPlayer llIllllllIlllII) {
        if (GodInfo.mc.player == null || GodInfo.mc.world == null) {
            return false;
        }
        boolean llIllllllIllllI = true;
        final int llIllllllIllIlI = (Object)this.offset;
        final boolean llIllllllIllIIl = llIllllllIllIlI.length != 0;
        for (char llIllllllIllIII = '\0'; llIllllllIllIII < (llIllllllIllIIl ? 1 : 0); ++llIllllllIllIII) {
            final Vec3d llIlllllllIIIIl = llIllllllIllIlI[llIllllllIllIII];
            if (GodInfo.mc.world.getBlockState(new BlockPos(llIllllllIlllII.getPositionVector()).add(llIlllllllIIIIl.x, llIlllllllIIIIl.y, llIlllllllIIIIl.z)).getBlock() != Blocks.OBSIDIAN && GodInfo.mc.world.getBlockState(new BlockPos(llIllllllIlllII.getPositionVector()).add(llIlllllllIIIIl.x, llIlllllllIIIIl.y, llIlllllllIIIIl.z)).getBlock() != Blocks.BEDROCK) {
                llIllllllIllllI = false;
            }
        }
        return llIllllllIllllI;
    }
    
    public GodInfo() {
        super("GodInfo");
        this.cf = this.register(new Value<Boolean>("Custom Font", this, false));
        this.offset = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, -1.0, 0.0) };
    }
}
