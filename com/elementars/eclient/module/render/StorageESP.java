package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import java.time.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.item.*;
import java.awt.*;
import net.minecraft.client.renderer.tileentity.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.tileentity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.util.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;

public class StorageESP extends Module
{
    private final /* synthetic */ Value<Boolean> save;
    private /* synthetic */ int count;
    private final /* synthetic */ Value<String> mode;
    private static /* synthetic */ Value<Boolean> future;
    private final /* synthetic */ Value<Boolean> all;
    private final /* synthetic */ Value<Integer> threshold;
    private /* synthetic */ int delay;
    /* synthetic */ Random random;
    private final /* synthetic */ Value<Float> width;
    
    @Override
    public String getHudInfo() {
        final long lIIIlIllllIIIIl = StorageESP.mc.world.loadedTileEntityList.stream().filter(lIIIlIllIlIIllI -> lIIIlIllIlIIllI instanceof TileEntityChest).count();
        if (this.save.getValue() && (int)lIIIlIllllIIIIl >= this.threshold.getValue() && this.delay == 0) {
            Wrapper.getFileManager().saveStorageESP(String.valueOf(new StringBuilder().append(LocalDate.now().toString()).append("-").append(this.random.nextInt(9999)).append(" - ").append(this.count)), String.valueOf(new StringBuilder().append((int)StorageESP.mc.player.posX).append(" ").append((int)StorageESP.mc.player.posY).append(" ").append((int)StorageESP.mc.player.posZ)), String.valueOf(lIIIlIllllIIIIl));
            this.delay = 4000;
            ++this.count;
        }
        return String.valueOf(new StringBuilder().append("Chests: ").append(lIIIlIllllIIIIl));
    }
    
    private int getEntityColor(final Entity lIIIlIllllIIlIl) {
        if (lIIIlIllllIIlIl instanceof EntityMinecartChest) {
            return ColorUtils.Colors.ORANGE;
        }
        if (lIIIlIllllIIlIl instanceof EntityMinecartHopper) {
            return ColorUtils.Colors.DARK_RED;
        }
        if (lIIIlIllllIIlIl instanceof EntityItemFrame && ((EntityItemFrame)lIIIlIllllIIlIl).getDisplayedItem().getItem() instanceof ItemShulkerBox) {
            return ColorUtils.Colors.YELLOW;
        }
        return -1;
    }
    
    public static void renderColor(final float lIIIlIllIlIlIll) {
        RenderHelper.enableStandardItemLighting();
        for (final TileEntity lIIIlIllIlIllIl : Wrapper.getMinecraft().world.loadedTileEntityList) {
            if (!(lIIIlIllIlIllIl instanceof TileEntityChest) && !(lIIIlIllIlIllIl instanceof TileEntityEnderChest) && !(lIIIlIllIlIllIl instanceof TileEntityShulkerBox) && !(lIIIlIllIlIllIl instanceof TileEntityFurnace) && !(lIIIlIllIlIllIl instanceof TileEntityHopper)) {
                continue;
            }
            if (StorageESP.future.getValue()) {
                OutlineUtils2.setColor(new Color(getTileEntityColorF(lIIIlIllIlIllIl)));
            }
            else {
                OutlineUtils2.setColor(new Color(getTileEntityColor(lIIIlIllIlIllIl)));
            }
            TileEntityRendererDispatcher.instance.render(lIIIlIllIlIllIl, lIIIlIllIlIllIl.getPos().getX() - StorageESP.mc.renderManager.renderPosX, lIIIlIllIlIllIl.getPos().getY() - StorageESP.mc.renderManager.renderPosY, lIIIlIllIlIllIl.getPos().getZ() - StorageESP.mc.renderManager.renderPosZ, lIIIlIllIlIlIll);
        }
    }
    
    int changeAlpha(int lIIIlIllIlllIlI, final int lIIIlIllIlllIIl) {
        lIIIlIllIlllIlI &= 0xFFFFFF;
        return lIIIlIllIlllIIl << 24 | lIIIlIllIlllIlI;
    }
    
    public StorageESP() {
        super("StorageESP", "Highlights storage blocks", 0, Category.RENDER, true);
        this.mode = this.register(new Value<String>("Mode", this, "Solid", new ArrayList<String>(Arrays.asList("Solid", "Full", "Outline", "Shader"))));
        this.all = this.register(new Value<Boolean>("All Tile Entities", this, false));
        this.width = this.register(new Value<Float>("Line Width", this, 1.0f, 1.0f, 10.0f));
        this.save = this.register(new Value<Boolean>("Save coords above threshold", this, false));
        this.threshold = this.register(new Value<Integer>("Threshold", this, 200, 1, 2000));
        this.random = new Random();
        StorageESP.future = this.register(new Value<Boolean>("Future Colors", this, false));
        this.count = 1;
    }
    
    public static int getTileEntityColor(final TileEntity lIIIlIllllIllII) {
        if (lIIIlIllllIllII instanceof TileEntityChest || lIIIlIllllIllII instanceof TileEntityDispenser) {
            return ColorUtils.Colors.ORANGE;
        }
        if (lIIIlIllllIllII instanceof TileEntityShulkerBox) {
            return ColorUtils.toRGBA(255, 0, 95, 255);
        }
        if (lIIIlIllllIllII instanceof TileEntityEnderChest) {
            return ColorUtils.Colors.PURPLE;
        }
        if (lIIIlIllllIllII instanceof TileEntityFurnace) {
            return ColorUtils.Colors.GRAY;
        }
        if (lIIIlIllllIllII instanceof TileEntityHopper) {
            return ColorUtils.Colors.DARK_RED;
        }
        return -1;
    }
    
    public static int getTileEntityColorF(final TileEntity lIIIlIllllIlIlI) {
        if (lIIIlIllllIlIlI instanceof TileEntityChest) {
            return ColorUtils.toRGBA(200, 200, 101, 255);
        }
        if (lIIIlIllllIlIlI instanceof TileEntityShulkerBox) {
            return ColorUtils.toRGBA(180, 21, 99, 255);
        }
        if (lIIIlIllllIlIlI instanceof TileEntityEnderChest) {
            return ColorUtils.toRGBA(155, 0, 200, 255);
        }
        if (lIIIlIllllIlIlI instanceof TileEntityFurnace) {
            return ColorUtils.Colors.GRAY;
        }
        if (lIIIlIllllIlIlI instanceof TileEntityHopper) {
            return ColorUtils.Colors.GRAY;
        }
        return -1;
    }
    
    public static void renderNormal(final float lIIIlIllIllIlII) {
        RenderHelper.enableStandardItemLighting();
        for (final TileEntity lIIIlIllIllIlIl : Wrapper.getMinecraft().world.loadedTileEntityList) {
            if (!(lIIIlIllIllIlIl instanceof TileEntityChest) && !(lIIIlIllIllIlIl instanceof TileEntityEnderChest) && !(lIIIlIllIllIlIl instanceof TileEntityShulkerBox)) {
                continue;
            }
            GL11.glPushMatrix();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            TileEntityRendererDispatcher.instance.render(lIIIlIllIllIlIl, lIIIlIllIllIlIl.getPos().getX() - StorageESP.mc.renderManager.renderPosX, lIIIlIllIllIlIl.getPos().getY() - StorageESP.mc.renderManager.renderPosY, lIIIlIllIllIlIl.getPos().getZ() - StorageESP.mc.renderManager.renderPosZ, lIIIlIllIllIlII);
            GL11.glPopMatrix();
        }
    }
    
    @Override
    public void onWorldRender(final RenderEvent lIIIlIlllIIlIIl) {
        if (this.delay > 0) {
            --this.delay;
        }
        final ArrayList<Triplet<TileEntity, Integer, Integer>> lIIIlIlllIIlIII = new ArrayList<Triplet<TileEntity, Integer, Integer>>();
        GlStateManager.pushMatrix();
        if (this.mode.getValue().equalsIgnoreCase("Shader")) {
            OutlineUtils.checkSetupFBO();
        }
        for (final TileEntity lIIIlIlllIlIIIl : Wrapper.getWorld().loadedTileEntityList) {
            final BlockPos lIIIlIlllIlIlII = lIIIlIlllIlIIIl.getPos();
            int lIIIlIlllIlIIll = 0;
            if (StorageESP.future.getValue()) {
                final int lIIIlIlllIlIllI = getTileEntityColorF(lIIIlIlllIlIIIl);
            }
            else {
                lIIIlIlllIlIIll = getTileEntityColor(lIIIlIlllIlIIIl);
            }
            int lIIIlIlllIlIIlI = 63;
            if (lIIIlIlllIlIIIl instanceof TileEntityChest) {
                final TileEntityChest lIIIlIlllIlIlIl = (TileEntityChest)lIIIlIlllIlIIIl;
                if (lIIIlIlllIlIlIl.adjacentChestZNeg != null) {
                    lIIIlIlllIlIIlI = ~(lIIIlIlllIlIIlI & 0x4);
                }
                if (lIIIlIlllIlIlIl.adjacentChestXPos != null) {
                    lIIIlIlllIlIIlI = ~(lIIIlIlllIlIIlI & 0x20);
                }
                if (lIIIlIlllIlIlIl.adjacentChestZPos != null) {
                    lIIIlIlllIlIIlI = ~(lIIIlIlllIlIIlI & 0x8);
                }
                if (lIIIlIlllIlIlIl.adjacentChestXNeg != null) {
                    lIIIlIlllIlIIlI = ~(lIIIlIlllIlIIlI & 0x10);
                }
            }
            if (lIIIlIlllIlIIll != -1) {
                lIIIlIlllIIlIII.add(new Triplet<TileEntity, Integer, Integer>(lIIIlIlllIlIIIl, lIIIlIlllIlIIll, lIIIlIlllIlIIlI));
            }
        }
        for (final Triplet<TileEntity, Integer, Integer> lIIIlIlllIIlIll : lIIIlIlllIIlIII) {
            try {
                if (this.mode.getValue().equalsIgnoreCase("Solid")) {
                    XuluTessellator.prepare(7);
                    XuluTessellator.drawBox(lIIIlIlllIIlIll.getFirst().getPos(), this.changeAlpha(lIIIlIlllIIlIll.getSecond(), 100), lIIIlIlllIIlIll.getThird());
                    XuluTessellator.release();
                }
                else if (this.mode.getValue().equalsIgnoreCase("Outline")) {
                    final IBlockState lIIIlIlllIlIIII = StorageESP.mc.world.getBlockState(lIIIlIlllIIlIll.getFirst().getPos());
                    final Vec3d lIIIlIlllIIllll = MathUtil.interpolateEntity((Entity)StorageESP.mc.player, StorageESP.mc.getRenderPartialTicks());
                    XuluTessellator.prepare(7);
                    XuluTessellator.drawBoundingBox(lIIIlIlllIlIIII.getSelectedBoundingBox((World)StorageESP.mc.world, lIIIlIlllIIlIll.getFirst().getPos()).grow(0.0020000000949949026).offset(-lIIIlIlllIIllll.x, -lIIIlIlllIIllll.y, -lIIIlIlllIIllll.z), 1.5f, lIIIlIlllIIlIll.getSecond());
                    XuluTessellator.release();
                }
                else if (this.mode.getValue().equalsIgnoreCase("Full")) {
                    final IBlockState lIIIlIlllIIlllI = StorageESP.mc.world.getBlockState(lIIIlIlllIIlIll.getFirst().getPos());
                    final Vec3d lIIIlIlllIIllIl = MathUtil.interpolateEntity((Entity)StorageESP.mc.player, StorageESP.mc.getRenderPartialTicks());
                    XuluTessellator.drawFullBox2(lIIIlIlllIIlllI.getSelectedBoundingBox((World)StorageESP.mc.world, lIIIlIlllIIlIll.getFirst().getPos()).grow(0.0020000000949949026).offset(-lIIIlIlllIIllIl.x, -lIIIlIlllIIllIl.y, -lIIIlIlllIIllIl.z), lIIIlIlllIIlIll.getFirst().getPos(), 1.5f, this.changeAlpha(lIIIlIlllIIlIll.getSecond(), 100));
                }
                else {
                    if (this.mode.getValue().equalsIgnoreCase("Shader")) {
                        continue;
                    }
                    XuluTessellator.prepare(7);
                    XuluTessellator.drawBox(lIIIlIlllIIlIll.getFirst().getPos(), this.changeAlpha(lIIIlIlllIIlIll.getSecond(), 100), lIIIlIlllIIlIll.getThird());
                    XuluTessellator.release();
                }
            }
            catch (Exception lIIIlIlllIIllII) {
                lIIIlIlllIIllII.printStackTrace();
            }
        }
        GlStateManager.popMatrix();
        GlStateManager.enableTexture2D();
    }
    
    public class Triplet<T, U, V>
    {
        private final /* synthetic */ V third;
        private final /* synthetic */ T first;
        private final /* synthetic */ U second;
        
        public V getThird() {
            return this.third;
        }
        
        public T getFirst() {
            return this.first;
        }
        
        public U getSecond() {
            return this.second;
        }
        
        public Triplet(final T llIlIlllIIlll, final U llIlIlllIIIIl, final V llIlIlllIIIII) {
            this.first = llIlIlllIIlll;
            this.second = llIlIlllIIIIl;
            this.third = llIlIlllIIIII;
        }
    }
}
