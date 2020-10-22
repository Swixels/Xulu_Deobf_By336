package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import io.netty.util.internal.*;
import com.elementars.eclient.module.*;
import java.awt.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.elementars.eclient.module.combat.*;
import com.elementars.eclient.util.*;
import java.util.*;

public class VoidESP extends Module
{
    private final /* synthetic */ Value<Integer> activateAtY;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<Integer> red;
    private /* synthetic */ ConcurrentSet<BlockPos> voidHoles;
    private final /* synthetic */ Value<Integer> range;
    private final /* synthetic */ Value<String> holeMode;
    private final /* synthetic */ Value<String> renderMode;
    
    public VoidESP() {
        super("VoidESP", "Highlights possible void holes", 0, Category.RENDER, true);
        this.range = this.register(new Value<Integer>("Range", this, 8, 1, 32));
        this.activateAtY = this.register(new Value<Integer>("ActivateAtY", this, 32, 1, 512));
        this.holeMode = this.register(new Value<String>("HoleMode", this, "Sides", new ArrayList<String>(Arrays.asList("Sides", "Above"))));
        this.renderMode = this.register(new Value<String>("RenderMode", this, "Down", new ArrayList<String>(Arrays.asList("Down", "Block"))));
        this.red = this.register(new Value<Integer>("Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 0, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 0, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 128, 0, 255));
    }
    
    @Override
    public String getHudInfo() {
        return this.holeMode.getValue();
    }
    
    private void drawBlock(final BlockPos lllllllllllllllllllllIlIllIlIIlI, final int lllllllllllllllllllllIlIllIllIII, final int lllllllllllllllllllllIlIllIlIIII, final int lllllllllllllllllllllIlIllIIllll) {
        final Color lllllllllllllllllllllIlIllIlIlIl = new Color(lllllllllllllllllllllIlIllIllIII, lllllllllllllllllllllIlIllIlIIII, lllllllllllllllllllllIlIllIIllll, this.alpha.getValue());
        int lllllllllllllllllllllIlIllIlIlII = 0;
        if (this.renderMode.getValue().equalsIgnoreCase("Block")) {
            lllllllllllllllllllllIlIllIlIlII = 63;
        }
        if (this.renderMode.getValue().equalsIgnoreCase("Down")) {
            lllllllllllllllllllllIlIllIlIlII = 1;
        }
        XuluTessellator.drawBox(lllllllllllllllllllllIlIllIlIIlI, lllllllllllllllllllllIlIllIlIlIl.getRGB(), lllllllllllllllllllllIlIllIlIlII);
    }
    
    @Override
    public void onWorldRender(final RenderEvent lllllllllllllllllllllIlIlllIIIll) {
        if (VoidESP.mc.player == null || this.voidHoles == null || this.voidHoles.isEmpty()) {
            return;
        }
        XuluTessellator.prepare(7);
        this.voidHoles.forEach(lllllllllllllllllllllIlIllIIIllI -> this.drawBlock(lllllllllllllllllllllIlIllIIIllI, this.red.getValue(), this.green.getValue(), this.blue.getValue()));
        XuluTessellator.release();
    }
    
    private boolean isAnyBedrock(final BlockPos lllllllllllllllllllllIlIlllIlIll, final BlockPos[] lllllllllllllllllllllIlIlllIllII) {
        final Exception lllllllllllllllllllllIlIlllIlIIl = (Object)lllllllllllllllllllllIlIlllIllII;
        final long lllllllllllllllllllllIlIlllIlIII = lllllllllllllllllllllIlIlllIlIIl.length;
        for (float lllllllllllllllllllllIlIlllIIlll = 0; lllllllllllllllllllllIlIlllIIlll < lllllllllllllllllllllIlIlllIlIII; ++lllllllllllllllllllllIlIlllIIlll) {
            final BlockPos lllllllllllllllllllllIlIlllIllll = lllllllllllllllllllllIlIlllIlIIl[lllllllllllllllllllllIlIlllIIlll];
            if (VoidESP.mc.world.getBlockState(lllllllllllllllllllllIlIlllIlIll.add((Vec3i)lllllllllllllllllllllIlIlllIllll)).getBlock().equals(Blocks.BEDROCK)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void onUpdate() {
        if (VoidESP.mc.player.getPosition().y > this.activateAtY.getValue()) {
            return;
        }
        if (this.voidHoles == null) {
            this.voidHoles = (ConcurrentSet<BlockPos>)new ConcurrentSet();
        }
        else {
            this.voidHoles.clear();
        }
        final List<BlockPos> lllllllllllllllllllllIlIllllllII = BlockInteractionHelper.getCircle(AutoCrystal.getPlayerPos(), 0, this.range.getValue(), false);
        for (final BlockPos lllllllllllllllllllllIlIlllllllI : lllllllllllllllllllllIlIllllllII) {
            if (VoidESP.mc.world.getBlockState(lllllllllllllllllllllIlIlllllllI).getBlock().equals(Blocks.BEDROCK)) {
                continue;
            }
            if (this.isAnyBedrock(lllllllllllllllllllllIlIlllllllI, Offsets.center)) {
                continue;
            }
            boolean lllllllllllllllllllllIlIllllllll = false;
            if (!this.isAnyBedrock(lllllllllllllllllllllIlIlllllllI, Offsets.above)) {
                lllllllllllllllllllllIlIllllllll = true;
            }
            if (this.holeMode.getValue().equalsIgnoreCase("Above")) {
                if (!lllllllllllllllllllllIlIllllllll) {
                    continue;
                }
                this.voidHoles.add((Object)lllllllllllllllllllllIlIlllllllI);
            }
            else {
                boolean lllllllllllllllllllllIllIIIIIIII = false;
                if (!this.isAnyBedrock(lllllllllllllllllllllIlIlllllllI, Offsets.north)) {
                    lllllllllllllllllllllIllIIIIIIII = true;
                }
                if (!this.isAnyBedrock(lllllllllllllllllllllIlIlllllllI, Offsets.east)) {
                    lllllllllllllllllllllIllIIIIIIII = true;
                }
                if (!this.isAnyBedrock(lllllllllllllllllllllIlIlllllllI, Offsets.south)) {
                    lllllllllllllllllllllIllIIIIIIII = true;
                }
                if (!this.isAnyBedrock(lllllllllllllllllllllIlIlllllllI, Offsets.west)) {
                    lllllllllllllllllllllIllIIIIIIII = true;
                }
                if (!this.holeMode.getValue().equalsIgnoreCase("Sides")) {
                    continue;
                }
                if (!lllllllllllllllllllllIlIllllllll && !lllllllllllllllllllllIllIIIIIIII) {
                    continue;
                }
                this.voidHoles.add((Object)lllllllllllllllllllllIlIlllllllI);
            }
        }
    }
    
    private enum HoleMode
    {
        ABOVE, 
        SIDES;
    }
    
    private enum RenderMode
    {
        BLOCK, 
        DOWN;
    }
    
    private static class Offsets
    {
        static final /* synthetic */ BlockPos[] south;
        static final /* synthetic */ BlockPos[] above;
        static final /* synthetic */ BlockPos[] west;
        static final /* synthetic */ BlockPos[] center;
        static final /* synthetic */ BlockPos[] north;
        static final /* synthetic */ BlockPos[] east;
        
        static {
            center = new BlockPos[] { new BlockPos(0, 1, 0), new BlockPos(0, 2, 0) };
            above = new BlockPos[] { new BlockPos(0, 3, 0), new BlockPos(0, 4, 0) };
            aboveStep1 = new BlockPos[] { new BlockPos(0, 3, 0) };
            aboveStep2 = new BlockPos[] { new BlockPos(0, 4, 0) };
            north = new BlockPos[] { new BlockPos(0, 1, -1), new BlockPos(0, 2, -1) };
            east = new BlockPos[] { new BlockPos(1, 1, 0), new BlockPos(1, 2, 0) };
            south = new BlockPos[] { new BlockPos(0, 1, 1), new BlockPos(0, 2, 1) };
            west = new BlockPos[] { new BlockPos(-1, 1, 0), new BlockPos(-1, 2, 0) };
        }
    }
}
