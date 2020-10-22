package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;
import com.elementars.eclient.event.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.init.*;
import com.elementars.eclient.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import com.elementars.eclient.event.events.*;

public class Search extends Module
{
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<Boolean> render;
    /* synthetic */ ICamera camera;
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<Float> renderDistance;
    private static final /* synthetic */ List<Block> BLOCKS;
    private final /* synthetic */ Value<Boolean> tracers;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Integer> alphaF;
    private final /* synthetic */ Value<Integer> red;
    public final /* synthetic */ Map<BlockPos, Triplet<Integer, Integer, Integer>> posList;
    private final /* synthetic */ Value<Integer> alpha;
    
    public static boolean addBlock(final String lIlllIIIIIlIIII) {
        if (Block.getBlockFromName(lIlllIIIIIlIIII) != null) {
            Search.BLOCKS.add(Block.getBlockFromName(lIlllIIIIIlIIII));
            return true;
        }
        return false;
    }
    
    public static List<Block> getBLOCKS() {
        return Search.BLOCKS;
    }
    
    public static void drawLine(final double lIllIlllIIIllII, final double lIllIlllIIIIIlI, final double lIllIlllIIIIIIl, final double lIllIlllIIIlIIl, final float lIllIllIlllllll, final float lIllIlllIIIIlll, final float lIllIllIlllllIl, final float lIllIlllIIIIlIl) {
        final Vec3d lIllIlllIIIIlII = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
        drawLineFromPosToPos(lIllIlllIIIIlII.x, lIllIlllIIIIlII.y + Search.mc.player.getEyeHeight(), lIllIlllIIIIlII.z, lIllIlllIIIllII, lIllIlllIIIIIlI, lIllIlllIIIIIIl, lIllIlllIIIlIIl, lIllIllIlllllll, lIllIlllIIIIlll, lIllIllIlllllIl, lIllIlllIIIIlIl);
    }
    
    @Override
    public void onDisable() {
        this.posList.clear();
        Search.mc.renderGlobal.loadRenderers();
    }
    
    @EventTarget
    public void onRender(final EventRenderBlock lIllIlllllIIlII) {
        if (Search.BLOCKS.contains(lIllIlllllIIlII.getBlockState().getBlock())) {
            final Vec3d lIllIlllllIlIlI = lIllIlllllIIlII.getBlockState().getOffset(lIllIlllllIIlII.getBlockAccess(), lIllIlllllIIlII.getBlockPos());
            final double lIllIlllllIlIIl = lIllIlllllIIlII.getBlockPos().getX() + lIllIlllllIlIlI.x;
            final double lIllIlllllIlIII = lIllIlllllIIlII.getBlockPos().getY() + lIllIlllllIlIlI.y;
            final double lIllIlllllIIlll = lIllIlllllIIlII.getBlockPos().getZ() + lIllIlllllIlIlI.z;
            final BlockPos lIllIlllllIIllI = new BlockPos(lIllIlllllIlIIl, lIllIlllllIlIII, lIllIlllllIIlll);
            synchronized (this.posList) {
                this.posList.put(lIllIlllllIIllI, this.getColor(lIllIlllllIIlII.getBlockState().getBlock()));
            }
        }
    }
    
    public static void drawLineToBlock(final BlockPos lIllIlllIIllIll, final float lIllIlllIIllIlI, final float lIllIlllIIlllll, final float lIllIlllIIllllI, final float lIllIlllIIlIlll) {
        final Vec3d lIllIlllIIlllII = MathUtil.interpolateEntity((Entity)Search.mc.player, Search.mc.getRenderPartialTicks());
        drawLine(lIllIlllIIllIll.x - lIllIlllIIlllII.x + 0.5, lIllIlllIIllIll.y - lIllIlllIIlllII.y + 0.5, lIllIlllIIllIll.z - lIllIlllIIlllII.z + 0.5, 0.0, lIllIlllIIllIlI, lIllIlllIIlllll, lIllIlllIIllllI, lIllIlllIIlIlll);
    }
    
    private void drawBlockO(final BlockPos lIllIlllIlllIlI, final int lIllIlllIllIIlI, final int lIllIlllIlllIII, final int lIllIlllIllIIII) {
        final IBlockState lIllIlllIllIllI = Search.mc.world.getBlockState(lIllIlllIlllIlI);
        final Vec3d lIllIlllIllIlIl = MathUtil.interpolateEntity((Entity)Search.mc.player, Search.mc.getRenderPartialTicks());
        XuluTessellator.drawBoundingBox(lIllIlllIllIllI.getSelectedBoundingBox((World)Search.mc.world, lIllIlllIlllIlI).offset(-lIllIlllIllIlIl.x, -lIllIlllIllIlIl.y, -lIllIlllIllIlIl.z), 1.5f, lIllIlllIllIIlI, lIllIlllIlllIII, lIllIlllIllIIII, this.alphaF.getValue());
    }
    
    @Override
    public void onEnable() {
        Search.mc.renderGlobal.loadRenderers();
    }
    
    public Search() {
        super("Search", "ESP for a certain block id", 0, Category.RENDER, true);
        this.posList = new HashMap<BlockPos, Triplet<Integer, Integer, Integer>>();
        this.mode = this.register(new Value<String>("Mode", this, "Solid", new ArrayList<String>(Arrays.asList("Solid", "Outline", "Full"))));
        this.renderDistance = this.register(new Value<Float>("RenderDistance", this, 50.0f, 1.0f, 100.0f));
        this.render = this.register(new Value<Boolean>("Render", this, true));
        this.tracers = this.register(new Value<Boolean>("Tracers", this, false));
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.red = this.register(new Value<Integer>("Default Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Default Green", this, 130, 0, 255));
        this.blue = this.register(new Value<Integer>("Default Blue", this, 170, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 70, 0, 255));
        this.alphaF = this.register(new Value<Integer>("Full Alpha", this, 100, 0, 255));
        this.camera = (ICamera)new Frustum();
    }
    
    private Triplet<Integer, Integer, Integer> getColor(final Block lIllIlllIlIlIII) {
        if (lIllIlllIlIlIII == Blocks.DIAMOND_ORE) {
            return new Triplet<Integer, Integer, Integer>(0, 255, 255);
        }
        if (lIllIlllIlIlIII == Blocks.IRON_ORE) {
            return new Triplet<Integer, Integer, Integer>(255, 226, 191);
        }
        if (lIllIlllIlIlIII == Blocks.GOLD_ORE) {
            return new Triplet<Integer, Integer, Integer>(255, 216, 0);
        }
        if (lIllIlllIlIlIII == Blocks.COAL_ORE) {
            return new Triplet<Integer, Integer, Integer>(35, 35, 35);
        }
        if (lIllIlllIlIlIII == Blocks.LAPIS_ORE) {
            return new Triplet<Integer, Integer, Integer>(0, 50, 255);
        }
        if (lIllIlllIlIlIII == Blocks.PORTAL) {
            return new Triplet<Integer, Integer, Integer>(170, 0, 255);
        }
        if (lIllIlllIlIlIII == Blocks.EMERALD_ORE) {
            return new Triplet<Integer, Integer, Integer>(0, 255, 0);
        }
        if (lIllIlllIlIlIII == Blocks.REDSTONE_ORE) {
            return new Triplet<Integer, Integer, Integer>(186, 0, 0);
        }
        if (lIllIlllIlIlIII == Blocks.END_PORTAL_FRAME) {
            return new Triplet<Integer, Integer, Integer>(255, 255, 150);
        }
        return new Triplet<Integer, Integer, Integer>(this.red.getValue(), this.green.getValue(), this.blue.getValue());
    }
    
    public static void drawLineFromPosToPos(final double lIllIllIllIIIlI, final double lIllIllIlIlllll, final double lIllIllIlIllllI, final double lIllIllIlIlllIl, final double lIllIllIllIlIll, final double lIllIllIlIllIll, final double lIllIllIllIlIIl, final float lIllIllIlIllIlI, final float lIllIllIlIllIIl, final float lIllIllIllIIIll, final float lIllIllIllIIIIl) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.5f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(lIllIllIlIllIlI, lIllIllIlIllIIl, lIllIllIllIIIll, lIllIllIllIIIIl);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GlStateManager.disableLighting();
        GL11.glLoadIdentity();
        final boolean lIllIllIllIIlII = Search.mc.gameSettings.viewBobbing;
        Search.mc.gameSettings.viewBobbing = false;
        Search.mc.entityRenderer.orientCamera(Search.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(lIllIllIllIIIlI, lIllIllIlIlllll, lIllIllIlIllllI);
        GL11.glVertex3d(lIllIllIlIlllIl, lIllIllIllIlIll, lIllIllIlIllIll);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glColor3d(1.0, 1.0, 1.0);
        GlStateManager.enableLighting();
        Search.mc.gameSettings.viewBobbing = lIllIllIllIIlII;
    }
    
    static {
        BLOCKS = new ArrayList<Block>(Arrays.asList((Block)Blocks.PORTAL, Blocks.DIAMOND_ORE));
    }
    
    private void drawBlock(final BlockPos lIllIllllIIlIIl, final int lIllIllllIIlIII, final int lIllIllllIIllll, final int lIllIllllIIlllI) {
        final Color lIllIllllIIllIl = new Color(lIllIllllIIlIII, lIllIllllIIllll, lIllIllllIIlllI, this.alpha.getValue());
        final IBlockState lIllIllllIIllII = Search.mc.world.getBlockState(lIllIllllIIlIIl);
        final Vec3d lIllIllllIIlIll = MathUtil.interpolateEntity((Entity)Search.mc.player, Search.mc.getRenderPartialTicks());
        XuluTessellator.drawBox2(lIllIllllIIllII.getSelectedBoundingBox((World)Search.mc.world, lIllIllllIIlIIl).offset(-lIllIllllIIlIll.x, -lIllIllllIIlIll.y, -lIllIllllIIlIll.z), lIllIllllIIllIl.getRGB(), 63);
    }
    
    @Override
    public void onWorldRender(final RenderEvent lIlllIIIIIIIIIl) {
        final double lIlllIIIIIIIIII = Search.mc.player.lastTickPosX + (Search.mc.player.posX - Search.mc.player.lastTickPosX) * lIlllIIIIIIIIIl.getPartialTicks();
        final double lIllIllllllllll = Search.mc.player.lastTickPosY + (Search.mc.player.posY - Search.mc.player.lastTickPosY) * lIlllIIIIIIIIIl.getPartialTicks();
        final double lIllIlllllllllI = Search.mc.player.lastTickPosZ + (Search.mc.player.posZ - Search.mc.player.lastTickPosZ) * lIlllIIIIIIIIIl.getPartialTicks();
        this.camera.setPosition(lIlllIIIIIIIIII, lIllIllllllllll, lIllIlllllllllI);
        if (Search.mc.player == null) {
            return;
        }
        if (this.render.getValue()) {
            if (this.mode.getValue().equalsIgnoreCase("Solid")) {
                XuluTessellator.prepare(7);
                synchronized (this.posList) {
                    this.posList.forEach((lIllIllIIllIllI, lIllIllIIlllIII) -> {
                        if (lIllIllIIllIllI.getDistance((int)Search.mc.player.posX, (int)Search.mc.player.posY, (int)Search.mc.player.posZ) <= this.renderDistance.getValue() && this.camera.isBoundingBoxInFrustum(Search.mc.world.getBlockState(lIllIllIIllIllI).getSelectedBoundingBox((World)Search.mc.world, lIllIllIIllIllI))) {
                            this.drawBlock(lIllIllIIllIllI, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)lIllIllIIlllIII.getFirst()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)lIllIllIIlllIII.getSecond()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)lIllIllIIlllIII.getThird()));
                        }
                        return;
                    });
                }
                XuluTessellator.release();
            }
            else if (this.mode.getValue().equalsIgnoreCase("Outline")) {
                synchronized (this.posList) {
                    this.posList.forEach((lIllIllIlIIIIlI, lIllIllIIlllllI) -> {
                        if (lIllIllIlIIIIlI.getDistance((int)Search.mc.player.posX, (int)Search.mc.player.posY, (int)Search.mc.player.posZ) <= this.renderDistance.getValue()) {
                            this.drawBlockO(lIllIllIlIIIIlI, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)lIllIllIIlllllI.getFirst()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)lIllIllIIlllllI.getSecond()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)lIllIllIIlllllI.getThird()));
                        }
                        return;
                    });
                }
            }
            else if (this.mode.getValue().equalsIgnoreCase("Full")) {
                synchronized (this.posList) {
                    this.posList.forEach((lIllIllIlIIlIll, lIllIllIlIIIlll) -> {
                        XuluTessellator.prepare(7);
                        if (lIllIllIlIIlIll.getDistance((int)Search.mc.player.posX, (int)Search.mc.player.posY, (int)Search.mc.player.posZ) <= this.renderDistance.getValue()) {
                            this.drawBlock(lIllIllIlIIlIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)lIllIllIlIIIlll.getFirst()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)lIllIllIlIIIlll.getSecond()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)lIllIllIlIIIlll.getThird()));
                            this.drawBlockO(lIllIllIlIIlIll, ((boolean)this.rainbow.getValue()) ? RainbowUtils.r : ((int)lIllIllIlIIIlll.getFirst()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.g : ((int)lIllIllIlIIIlll.getSecond()), ((boolean)this.rainbow.getValue()) ? RainbowUtils.b : ((int)lIllIllIlIIIlll.getThird()));
                        }
                        XuluTessellator.release();
                        return;
                    });
                }
            }
        }
        if (this.tracers.getValue()) {
            synchronized (this.posList) {
                this.posList.forEach((lIllIllIlIlIlII, lIllIllIlIlIIll) -> {
                    if (lIllIllIlIlIlII.getDistance((int)Search.mc.player.posX, (int)Search.mc.player.posY, (int)Search.mc.player.posZ) <= this.renderDistance.getValue()) {
                        drawLineToBlock(lIllIllIlIlIlII, ((boolean)this.rainbow.getValue()) ? ((float)RainbowUtils.r) : ((float)lIllIllIlIlIIll.getFirst()), ((boolean)this.rainbow.getValue()) ? ((float)RainbowUtils.g) : ((float)(int)lIllIllIlIlIIll.getSecond()), ((boolean)this.rainbow.getValue()) ? ((float)RainbowUtils.b) : ((float)(int)lIllIllIlIlIIll.getThird()), this.alpha.getValue());
                    }
                });
            }
        }
    }
    
    public static boolean delBlock(final String lIlllIIIIIIlllI) {
        if (Block.getBlockFromName(lIlllIIIIIIlllI) != null) {
            Search.BLOCKS.remove(Block.getBlockFromName(lIlllIIIIIIlllI));
            return true;
        }
        return false;
    }
}
