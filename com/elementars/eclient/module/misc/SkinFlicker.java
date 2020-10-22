package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class SkinFlicker extends Module
{
    private final /* synthetic */ Value<Integer> slowness;
    private /* synthetic */ int len;
    private static final /* synthetic */ EnumPlayerModelParts[] PARTS_VERTICAL;
    private final /* synthetic */ Value<String> mode;
    private /* synthetic */ Random r;
    private static final /* synthetic */ EnumPlayerModelParts[] PARTS_HORIZONTAL;
    
    static {
        PARTS_HORIZONTAL = new EnumPlayerModelParts[] { EnumPlayerModelParts.LEFT_SLEEVE, EnumPlayerModelParts.JACKET, EnumPlayerModelParts.HAT, EnumPlayerModelParts.LEFT_PANTS_LEG, EnumPlayerModelParts.RIGHT_PANTS_LEG, EnumPlayerModelParts.RIGHT_SLEEVE };
        PARTS_VERTICAL = new EnumPlayerModelParts[] { EnumPlayerModelParts.HAT, EnumPlayerModelParts.JACKET, EnumPlayerModelParts.LEFT_SLEEVE, EnumPlayerModelParts.RIGHT_SLEEVE, EnumPlayerModelParts.LEFT_PANTS_LEG, EnumPlayerModelParts.RIGHT_PANTS_LEG };
    }
    
    @Override
    public void onUpdate() {
        if (this.mode.getValue().equalsIgnoreCase("Random")) {
            if (SkinFlicker.mc.player.ticksExisted % this.slowness.getValue() != 0) {
                return;
            }
            SkinFlicker.mc.gameSettings.switchModelPartEnabled(EnumPlayerModelParts.values()[this.r.nextInt(this.len)]);
        }
        else {
            int llllllllllllllllIlllIIlIIIlllIII = SkinFlicker.mc.player.ticksExisted / this.slowness.getValue() % (SkinFlicker.PARTS_HORIZONTAL.length * 2);
            boolean llllllllllllllllIlllIIlIIIllIlll = false;
            if (llllllllllllllllIlllIIlIIIlllIII >= SkinFlicker.PARTS_HORIZONTAL.length) {
                llllllllllllllllIlllIIlIIIllIlll = true;
                llllllllllllllllIlllIIlIIIlllIII -= SkinFlicker.PARTS_HORIZONTAL.length;
            }
            SkinFlicker.mc.gameSettings.setModelPartEnabled(this.mode.getValue().equalsIgnoreCase("Vertical") ? SkinFlicker.PARTS_VERTICAL[llllllllllllllllIlllIIlIIIlllIII] : SkinFlicker.PARTS_HORIZONTAL[llllllllllllllllIlllIIlIIIlllIII], llllllllllllllllIlllIIlIIIllIlll);
        }
    }
    
    public SkinFlicker() {
        super("SkinFlicker", "Toggles skin layers", 0, Category.MISC, true);
        this.mode = this.register(new Value<String>("Mode", this, "Horizontal", new ArrayList<String>(Arrays.asList("Horizontal", "Vertical", "Random"))));
        this.slowness = this.register(new Value<Integer>("Slowness", this, 2, 1, 5));
        this.r = new Random();
        this.len = EnumPlayerModelParts.values().length;
    }
}
