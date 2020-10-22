package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import java.util.concurrent.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import java.awt.*;
import net.minecraft.block.state.*;
import net.minecraft.client.renderer.*;
import com.elementars.eclient.module.combat.*;
import com.elementars.eclient.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.block.*;

public class HoleESP extends Module
{
    /* synthetic */ int holes;
    private final /* synthetic */ Value<Integer> obiGreen;
    private final /* synthetic */ Value<Boolean> hideOwn;
    private final /* synthetic */ Value<String> drawMode;
    /* synthetic */ ICamera camera;
    private final /* synthetic */ Value<Integer> brockBlue;
    private final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<Integer> maxHoles;
    private final /* synthetic */ Value<String> renderMode;
    private final /* synthetic */ Value<Float> renderDistance;
    private final /* synthetic */ Value<Integer> brockRed;
    private final /* synthetic */ BlockPos[] surroundOffset;
    private final /* synthetic */ Value<Integer> obiBlue;
    private final /* synthetic */ Value<Float> cuboid;
    private final /* synthetic */ Value<Integer> obiRed;
    private /* synthetic */ ConcurrentHashMap<BlockPos, Pair<Boolean, Boolean>> safeHoles;
    private final /* synthetic */ Value<String> holeMode;
    private final /* synthetic */ Value<Boolean> frustum;
    private final /* synthetic */ Value<Integer> brockGreen;
    private final /* synthetic */ Value<Boolean> future;
    private final /* synthetic */ Value<Integer> alpha2;
    private final /* synthetic */ Value<Boolean> max;
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<Boolean> offset;
    
    @Override
    public void onWorldRender(final RenderEvent lIIlIIlIlllIlII) {
        if (HoleESP.mc.player == null || this.safeHoles == null) {
            return;
        }
        if (this.safeHoles.isEmpty()) {
            return;
        }
        if (this.drawMode.getValue().equalsIgnoreCase("Solid")) {
            XuluTessellator.prepare(7);
            this.safeHoles.forEach((lIIlIIIIllIlIIl, lIIlIIIIllIIlIl) -> {
                if (this.offset.getValue()) {
                    lIIlIIIIllIlIIl = lIIlIIIIllIlIIl.add(0, -1, 0);
                }
                if (lIIlIIIIllIIlIl.getKey()) {
                    this.drawBlock(lIIlIIIIllIlIIl, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIIllIIlIl);
                }
                else {
                    this.drawBlock(lIIlIIIIllIlIIl, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIIllIIlIl);
                }
                return;
            });
            XuluTessellator.release();
        }
        else if (this.drawMode.getValue().equalsIgnoreCase("Outline")) {
            this.safeHoles.forEach((lIIlIIIIllIllll, lIIlIIIIllIlllI) -> {
                if (this.offset.getValue()) {
                    lIIlIIIIllIllll = lIIlIIIIllIllll.add(0, -1, 0);
                }
                if (this.renderMode.getValue().equalsIgnoreCase("Solid")) {
                    if (lIIlIIIIllIlllI.getKey()) {
                        this.drawBlockO(lIIlIIIIllIllll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIIllIlllI);
                    }
                    else {
                        this.drawBlockO(lIIlIIIIllIllll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIIllIlllI);
                    }
                }
                else if (this.renderMode.getValue().equalsIgnoreCase("Flat")) {
                    if (lIIlIIIIllIlllI.getKey()) {
                        this.drawBlockOF(lIIlIIIIllIllll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIIllIlllI);
                    }
                    else {
                        this.drawBlockOF(lIIlIIIIllIllll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIIllIlllI);
                    }
                }
            });
        }
        else if (this.drawMode.getValue().equalsIgnoreCase("Full")) {
            this.safeHoles.forEach((lIIlIIIIllllIll, lIIlIIIIlllIlll) -> {
                if (this.offset.getValue()) {
                    lIIlIIIIllllIll = lIIlIIIIllllIll.add(0, -1, 0);
                }
                if (this.renderMode.getValue().equalsIgnoreCase("Solid")) {
                    if (lIIlIIIIlllIlll.getKey()) {
                        XuluTessellator.prepare(7);
                        this.drawBlock(lIIlIIIIllllIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIIlllIlll);
                        XuluTessellator.release();
                        this.drawBlockO(lIIlIIIIllllIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIIlllIlll);
                    }
                    else {
                        XuluTessellator.prepare(7);
                        this.drawBlock(lIIlIIIIllllIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIIlllIlll);
                        XuluTessellator.release();
                        this.drawBlockO(lIIlIIIIllllIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIIlllIlll);
                    }
                }
                else if (this.renderMode.getValue().equalsIgnoreCase("Flat")) {
                    if (lIIlIIIIlllIlll.getKey()) {
                        XuluTessellator.prepare(7);
                        this.drawBlock(lIIlIIIIllllIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIIlllIlll);
                        XuluTessellator.release();
                        this.drawBlockOF(lIIlIIIIllllIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIIlllIlll);
                    }
                    else {
                        XuluTessellator.prepare(7);
                        this.drawBlock(lIIlIIIIllllIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIIlllIlll);
                        XuluTessellator.release();
                        this.drawBlockOF(lIIlIIIIllllIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIIlllIlll);
                    }
                }
            });
        }
        else if (this.drawMode.getValue().equalsIgnoreCase("Cuboid")) {
            this.safeHoles.forEach((lIIlIIIlIIIIlII, lIIlIIIlIIIIIll) -> {
                if (this.offset.getValue()) {
                    lIIlIIIlIIIIlII = lIIlIIIlIIIIlII.add(0, -1, 0);
                }
                if (lIIlIIIlIIIIIll.getKey()) {
                    this.drawBlockCUB(lIIlIIIlIIIIlII, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIlIIIIIll);
                }
                else {
                    this.drawBlockCUB(lIIlIIIlIIIIlII, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIlIIIIIll);
                }
            });
        }
        else if (this.drawMode.getValue().equalsIgnoreCase("Indicator")) {
            this.safeHoles.forEach((lIIlIIIlIIIllIl, lIIlIIIlIIIlIIl) -> {
                if (this.offset.getValue()) {
                    lIIlIIIlIIIllIl = lIIlIIIlIIIllIl.add(0, -1, 0);
                }
                if (lIIlIIIlIIIlIIl.getKey()) {
                    this.drawBlockIndicator(lIIlIIIlIIIllIl, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.brockRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.brockGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.brockBlue.getValue()), lIIlIIIlIIIlIIl);
                }
                else {
                    this.drawBlockIndicator(lIIlIIIlIIIllIl, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)this.obiRed.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)this.obiGreen.getValue()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)this.obiBlue.getValue()), lIIlIIIlIIIlIIl);
                }
            });
        }
    }
    
    public HoleESP() {
        super("HoleESP", "Highlights holes for pvp", 0, Category.RENDER, true);
        this.frustum = this.register(new Value<Boolean>("Frustum", this, true));
        this.hideOwn = this.register(new Value<Boolean>("HideOwn", this, false));
        this.offset = this.register(new Value<Boolean>("Offset Lower", this, false));
        this.future = this.register(new Value<Boolean>("Future Mode", this, false));
        this.renderDistance = this.register(new Value<Float>("RenderDistance", this, 8.0f, 1.0f, 32.0f));
        this.max = this.register(new Value<Boolean>("Maximum Holes", this, false));
        this.maxHoles = this.register(new Value<Integer>("Maximum Num", this, 8, 1, 100));
        this.holeMode = this.register(new Value<String>("Hole Mode", this, "Both", new String[] { "Bedrock", "Obsidian", "Both" }));
        this.renderMode = this.register(new Value<String>("RenderMode", this, "Solid", new ArrayList<String>(Arrays.asList("Solid", "Flat"))));
        this.drawMode = this.register(new Value<String>("DrawMode", this, "Solid", EnumUtil.enumConverter(Modes.class)));
        this.cuboid = this.register(new Value<Float>("Cuboid Height", this, 0.9f, 0.0f, 1.0f));
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.obiRed = this.register(new Value<Integer>("ObiRed", this, 104, 0, 255));
        this.obiGreen = this.register(new Value<Integer>("ObiGreen", this, 12, 0, 255));
        this.obiBlue = this.register(new Value<Integer>("ObiBlue", this, 35, 0, 255));
        this.brockRed = this.register(new Value<Integer>("BrockRed", this, 81, 0, 255));
        this.brockGreen = this.register(new Value<Integer>("BrockGreen", this, 12, 0, 255));
        this.brockBlue = this.register(new Value<Integer>("BrockBlue", this, 104, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 169, 0, 255));
        this.alpha2 = this.register(new Value<Integer>("Outline Alpha", this, 255, 0, 255));
        this.camera = (ICamera)new Frustum();
        this.surroundOffset = new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
    }
    
    private void drawBlockCUB(final BlockPos lIIlIIlIIIlIlII, final int lIIlIIlIIIIIlll, final int lIIlIIlIIIlIIlI, final int lIIlIIlIIIlIIIl, final Pair<Boolean, Boolean> lIIlIIlIIIIIlII) {
        final int lIIlIIlIIIIllll = (this.future.getValue() && !lIIlIIlIIIIIlII.getKey() && lIIlIIlIIIIIlII.getValue()) ? 255 : lIIlIIlIIIIIlll;
        final int lIIlIIlIIIIlllI = (this.future.getValue() && !lIIlIIlIIIIIlII.getKey() && lIIlIIlIIIIIlII.getValue()) ? 255 : lIIlIIlIIIlIIlI;
        final int lIIlIIlIIIIllIl = (this.future.getValue() && !lIIlIIlIIIIIlII.getKey() && lIIlIIlIIIIIlII.getValue()) ? 0 : lIIlIIlIIIlIIIl;
        final IBlockState lIIlIIlIIIIllII = HoleESP.mc.world.getBlockState(lIIlIIlIIIlIlII);
        final Vec3d lIIlIIlIIIIlIll = MathUtil.interpolateEntity((Entity)HoleESP.mc.player, HoleESP.mc.getRenderPartialTicks());
        AxisAlignedBB lIIlIIlIIIIlIlI = lIIlIIlIIIIllII.getSelectedBoundingBox((World)HoleESP.mc.world, lIIlIIlIIIlIlII);
        lIIlIIlIIIIlIlI = lIIlIIlIIIIlIlI.setMaxY(lIIlIIlIIIIlIlI.maxY - 1.0f * this.cuboid.getValue()).grow(0.0020000000949949026).offset(-lIIlIIlIIIIlIll.x, -lIIlIIlIIIIlIll.y, -lIIlIIlIIIIlIll.z);
        XuluTessellator.drawFullBox2(lIIlIIlIIIIlIlI, lIIlIIlIIIlIlII, 1.5f, new Color(lIIlIIlIIIIllll, lIIlIIlIIIIlllI, lIIlIIlIIIIllIl, this.alpha.getValue()).getRGB(), this.alpha2.getValue());
    }
    
    private void drawBlockIndicator(final BlockPos lIIlIIIllllIIII, final int lIIlIIIlllIllll, final int lIIlIIIlllIIIlI, final int lIIlIIIlllIllIl, final Pair<Boolean, Boolean> lIIlIIIlllIIIII) {
        final int lIIlIIIlllIlIll = (this.future.getValue() && !lIIlIIIlllIIIII.getKey() && lIIlIIIlllIIIII.getValue()) ? 255 : lIIlIIIlllIllll;
        final int lIIlIIIlllIlIlI = (this.future.getValue() && !lIIlIIIlllIIIII.getKey() && lIIlIIIlllIIIII.getValue()) ? 255 : lIIlIIIlllIIIlI;
        final int lIIlIIIlllIlIIl = (this.future.getValue() && !lIIlIIIlllIIIII.getKey() && lIIlIIIlllIIIII.getValue()) ? 0 : lIIlIIIlllIllIl;
        final IBlockState lIIlIIIlllIlIII = HoleESP.mc.world.getBlockState(lIIlIIIllllIIII);
        final Vec3d lIIlIIIlllIIlll = MathUtil.interpolateEntity((Entity)HoleESP.mc.player, HoleESP.mc.getRenderPartialTicks());
        AxisAlignedBB lIIlIIIlllIIllI = lIIlIIIlllIlIII.getSelectedBoundingBox((World)HoleESP.mc.world, lIIlIIIllllIIII);
        lIIlIIIlllIIllI = lIIlIIIlllIIllI.setMaxY(lIIlIIIlllIIllI.maxY + ((HoleESP.mc.player.getDistanceSq(lIIlIIIllllIIII) < 10.0) ? 0 : 3)).grow(0.0020000000949949026).offset(-lIIlIIIlllIIlll.x, -lIIlIIIlllIIlll.y, -lIIlIIIlllIIlll.z);
        GlStateManager.enableCull();
        XuluTessellator.drawIndicator(lIIlIIIlllIIllI, new Color(lIIlIIIlllIlIll, lIIlIIIlllIlIlI, lIIlIIIlllIlIIl, this.alpha.getValue()).getRGB(), 63);
    }
    
    @Override
    public void onUpdate() {
        final double lIIlIIllIIIlIll = HoleESP.mc.player.lastTickPosX + (HoleESP.mc.player.posX - HoleESP.mc.player.lastTickPosX) * HoleESP.mc.getRenderPartialTicks();
        final double lIIlIIllIIIlIlI = HoleESP.mc.player.lastTickPosY + (HoleESP.mc.player.posY - HoleESP.mc.player.lastTickPosY) * HoleESP.mc.getRenderPartialTicks();
        final double lIIlIIllIIIlIIl = HoleESP.mc.player.lastTickPosZ + (HoleESP.mc.player.posZ - HoleESP.mc.player.lastTickPosZ) * HoleESP.mc.getRenderPartialTicks();
        this.camera.setPosition(lIIlIIllIIIlIll, lIIlIIllIIIlIlI, lIIlIIllIIIlIIl);
        if (this.safeHoles == null) {
            this.safeHoles = new ConcurrentHashMap<BlockPos, Pair<Boolean, Boolean>>();
        }
        else {
            this.safeHoles.clear();
        }
        final int lIIlIIllIIIlIII = (int)Math.ceil(this.renderDistance.getValue());
        final List<BlockPos> lIIlIIllIIIIlll = BlockInteractionHelper.getSphere(AutoCrystal.getPlayerPos(), (float)lIIlIIllIIIlIII, lIIlIIllIIIlIII, false, true, 0);
        this.holes = 0;
        for (final BlockPos lIIlIIllIIIllIl : lIIlIIllIIIIlll) {
            if (!HoleESP.mc.world.getBlockState(lIIlIIllIIIllIl).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleESP.mc.world.getBlockState(lIIlIIllIIIllIl.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleESP.mc.world.getBlockState(lIIlIIllIIIllIl.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (this.hideOwn.getValue() && lIIlIIllIIIllIl.equals((Object)new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ))) {
                continue;
            }
            if (this.frustum.getValue() && !this.camera.isBoundingBoxInFrustum(HoleESP.mc.world.getBlockState(lIIlIIllIIIllIl).getSelectedBoundingBox((World)HoleESP.mc.world, lIIlIIllIIIllIl))) {
                continue;
            }
            boolean lIIlIIllIIlIIII = true;
            boolean lIIlIIllIIIllll = true;
            boolean lIIlIIllIIIlllI = false;
            final long lIIlIIlIllllIll = (Object)this.surroundOffset;
            final double lIIlIIlIllllIlI = lIIlIIlIllllIll.length;
            for (final BlockPos lIIlIIllIIlIIIl : lIIlIIlIllllIll) {
                final Block lIIlIIllIIlIIlI = HoleESP.mc.world.getBlockState(lIIlIIllIIIllIl.add((Vec3i)lIIlIIllIIlIIIl)).getBlock();
                if (lIIlIIllIIlIIlI != Blocks.BEDROCK) {
                    lIIlIIllIIIllll = false;
                }
                if (lIIlIIllIIlIIlI == Blocks.BEDROCK) {
                    lIIlIIllIIIlllI = true;
                }
                if (lIIlIIllIIlIIlI != Blocks.BEDROCK && lIIlIIllIIlIIlI != Blocks.OBSIDIAN && lIIlIIllIIlIIlI != Blocks.ENDER_CHEST && lIIlIIllIIlIIlI != Blocks.ANVIL) {
                    lIIlIIllIIlIIII = false;
                    break;
                }
            }
            if (!lIIlIIllIIlIIII) {
                continue;
            }
            if (lIIlIIllIIIllll && this.holeMode.getValue().equalsIgnoreCase("Obsidian")) {
                continue;
            }
            if (!lIIlIIllIIIllll && this.holeMode.getValue().equalsIgnoreCase("Bedrock")) {
                continue;
            }
            this.safeHoles.put(lIIlIIllIIIllIl, new Pair<Boolean, Boolean>(lIIlIIllIIIllll, lIIlIIllIIIlllI));
            if (!this.max.getValue()) {
                continue;
            }
            ++this.holes;
            if (this.holes == this.maxHoles.getValue()) {
                return;
            }
        }
    }
    
    private void drawBlockOCUB(final BlockPos lIIlIIIllIIIIII, final int lIIlIIIllIIlIll, final int lIIlIIIlIlllllI, final int lIIlIIIlIllllIl, final Pair<Boolean, Boolean> lIIlIIIllIIlIII) {
        final int lIIlIIIllIIIlll = (this.future.getValue() && !lIIlIIIllIIlIII.getKey() && lIIlIIIllIIlIII.getValue()) ? 255 : lIIlIIIllIIlIll;
        final int lIIlIIIllIIIllI = (this.future.getValue() && !lIIlIIIllIIlIII.getKey() && lIIlIIIllIIlIII.getValue()) ? 255 : lIIlIIIlIlllllI;
        final int lIIlIIIllIIIlIl = (this.future.getValue() && !lIIlIIIllIIlIII.getKey() && lIIlIIIllIIlIII.getValue()) ? 0 : lIIlIIIlIllllIl;
        final IBlockState lIIlIIIllIIIlII = HoleESP.mc.world.getBlockState(lIIlIIIllIIIIII);
        final Vec3d lIIlIIIllIIIIll = MathUtil.interpolateEntity((Entity)HoleESP.mc.player, HoleESP.mc.getRenderPartialTicks());
        AxisAlignedBB lIIlIIIllIIIIlI = lIIlIIIllIIIlII.getSelectedBoundingBox((World)HoleESP.mc.world, lIIlIIIllIIIIII);
        lIIlIIIllIIIIlI = lIIlIIIllIIIIlI.setMaxY(lIIlIIIllIIIIlI.maxY - 1.0f * this.cuboid.getValue()).grow(0.0020000000949949026).offset(-lIIlIIIllIIIIll.x, -lIIlIIIllIIIIll.y, -lIIlIIIllIIIIll.z);
        XuluTessellator.drawBoundingBox(lIIlIIIllIIIIlI, 1.5f, lIIlIIIllIIIlll, lIIlIIIllIIIllI, lIIlIIIllIIIlIl, this.alpha2.getValue());
    }
    
    @Override
    public String getHudInfo() {
        return String.valueOf(new StringBuilder().append(this.renderMode.getValue()).append(", ").append(this.drawMode.getValue()));
    }
    
    private void drawBlock(final BlockPos lIIlIIlIlIlIIIl, final int lIIlIIlIlIlIIII, final int lIIlIIlIlIIIlll, final int lIIlIIlIlIIIllI, final Pair<Boolean, Boolean> lIIlIIlIlIIIlIl) {
        Color lIIlIIlIlIIllII = new Color(lIIlIIlIlIlIIII, lIIlIIlIlIIIlll, lIIlIIlIlIIIllI, this.alpha.getValue());
        if (this.future.getValue() && !lIIlIIlIlIIIlIl.getKey() && lIIlIIlIlIIIlIl.getValue()) {
            lIIlIIlIlIIllII = new Color(255, 255, 0, this.alpha.getValue());
        }
        int lIIlIIlIlIIlIll = 1;
        if (this.renderMode.getValue().equalsIgnoreCase("Solid")) {
            lIIlIIlIlIIlIll = 63;
        }
        XuluTessellator.drawBox(lIIlIIlIlIlIIIl, lIIlIIlIlIIllII.getRGB(), lIIlIIlIlIIlIll);
    }
    
    private boolean isIntermediate(final BlockPos lIIlIIlIllIIllI) {
        boolean lIIlIIlIllIIlIl = false;
        boolean lIIlIIlIllIIlII = false;
        final float lIIlIIlIlIlllll = (Object)this.surroundOffset;
        final byte lIIlIIlIlIllllI = (byte)lIIlIIlIlIlllll.length;
        for (boolean lIIlIIlIlIlllIl = false; (lIIlIIlIlIlllIl ? 1 : 0) < lIIlIIlIlIllllI; ++lIIlIIlIlIlllIl) {
            final BlockPos lIIlIIlIllIlIII = lIIlIIlIlIlllll[lIIlIIlIlIlllIl];
            final Block lIIlIIlIllIlIIl = HoleESP.mc.world.getBlockState(lIIlIIlIllIIllI.add((Vec3i)lIIlIIlIllIlIII)).getBlock();
            if (lIIlIIlIllIlIIl == Blocks.BEDROCK) {
                lIIlIIlIllIIlIl = true;
            }
            else if (lIIlIIlIllIlIIl == Blocks.OBSIDIAN && lIIlIIlIllIlIIl == Blocks.ENDER_CHEST && lIIlIIlIllIlIIl == Blocks.ANVIL) {
                lIIlIIlIllIIlII = true;
            }
        }
        return lIIlIIlIllIIlIl && lIIlIIlIllIIlII;
    }
    
    private void drawBlockOF(final BlockPos lIIlIIIlIlIlIIl, final int lIIlIIIlIlIlIII, final int lIIlIIIlIlIIlll, final int lIIlIIIlIIllIll, final Pair<Boolean, Boolean> lIIlIIIlIlIIlIl) {
        final int lIIlIIIlIlIIlII = (this.future.getValue() && !lIIlIIIlIlIIlIl.getKey() && lIIlIIIlIlIIlIl.getValue()) ? 255 : lIIlIIIlIlIlIII;
        final int lIIlIIIlIlIIIll = (this.future.getValue() && !lIIlIIIlIlIIlIl.getKey() && lIIlIIIlIlIIlIl.getValue()) ? 255 : lIIlIIIlIlIIlll;
        final int lIIlIIIlIlIIIlI = (this.future.getValue() && !lIIlIIIlIlIIlIl.getKey() && lIIlIIIlIlIIlIl.getValue()) ? 0 : lIIlIIIlIIllIll;
        final IBlockState lIIlIIIlIlIIIIl = HoleESP.mc.world.getBlockState(lIIlIIIlIlIlIIl);
        final Vec3d lIIlIIIlIlIIIII = MathUtil.interpolateEntity((Entity)HoleESP.mc.player, HoleESP.mc.getRenderPartialTicks());
        XuluTessellator.drawBoundingBoxFace(lIIlIIIlIlIIIIl.getSelectedBoundingBox((World)HoleESP.mc.world, lIIlIIIlIlIlIIl).grow(0.0020000000949949026).offset(-lIIlIIIlIlIIIII.x, -lIIlIIIlIlIIIII.y, -lIIlIIIlIlIIIII.z), 1.5f, lIIlIIIlIlIIlII, lIIlIIIlIlIIIll, lIIlIIIlIlIIIlI, this.alpha2.getValue());
    }
    
    private void drawBlockO(final BlockPos lIIlIIlIIllIllI, final int lIIlIIlIIllIlIl, final int lIIlIIlIIlIlIIl, final int lIIlIIlIIllIIll, final Pair<Boolean, Boolean> lIIlIIlIIlIIlll) {
        final int lIIlIIlIIllIIIl = (this.future.getValue() && !lIIlIIlIIlIIlll.getKey() && lIIlIIlIIlIIlll.getValue()) ? 255 : lIIlIIlIIllIlIl;
        final int lIIlIIlIIllIIII = (this.future.getValue() && !lIIlIIlIIlIIlll.getKey() && lIIlIIlIIlIIlll.getValue()) ? 255 : lIIlIIlIIlIlIIl;
        final int lIIlIIlIIlIllll = (this.future.getValue() && !lIIlIIlIIlIIlll.getKey() && lIIlIIlIIlIIlll.getValue()) ? 0 : lIIlIIlIIllIIll;
        final IBlockState lIIlIIlIIlIlllI = HoleESP.mc.world.getBlockState(lIIlIIlIIllIllI);
        final Vec3d lIIlIIlIIlIllIl = MathUtil.interpolateEntity((Entity)HoleESP.mc.player, HoleESP.mc.getRenderPartialTicks());
        XuluTessellator.drawBoundingBox(lIIlIIlIIlIlllI.getSelectedBoundingBox((World)HoleESP.mc.world, lIIlIIlIIllIllI).grow(0.0020000000949949026).offset(-lIIlIIlIIlIllIl.x, -lIIlIIlIIlIllIl.y, -lIIlIIlIIlIllIl.z), 1.5f, lIIlIIlIIllIIIl, lIIlIIlIIllIIII, lIIlIIlIIlIllll, this.alpha2.getValue());
    }
    
    public enum Modes
    {
        CUBOID, 
        FULL, 
        INDICATOR, 
        SOLID, 
        OUTLINE;
    }
}
