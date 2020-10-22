package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import java.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.init.*;
import com.elementars.eclient.util.*;

public class HoleHud extends Element
{
    /* synthetic */ BlockPos SOUTH;
    /* synthetic */ BlockPos EAST;
    /* synthetic */ BlockPos NORTH;
    /* synthetic */ BlockPos WEST;
    
    @Override
    public void onRender() {
        if (HoleHud.mc.player == null || HoleHud.mc.world == null) {
            return;
        }
        switch (HoleHud.mc.getRenderViewEntity().getHorizontalFacing()) {
            case NORTH: {
                this.itemrender(this.getNorth(), (int)this.x, (int)this.y);
                break;
            }
            case EAST: {
                this.itemrender(this.getEast(), (int)this.x, (int)this.y);
                break;
            }
            case SOUTH: {
                this.itemrender(this.getSouth(), (int)this.x, (int)this.y);
                break;
            }
            case WEST: {
                this.itemrender(this.getWest(), (int)this.x, (int)this.y);
                break;
            }
        }
    }
    
    private List<ItemStack> getEast() {
        final BlockPos llllllllllllllllllIlIlIIlllIIIlI = new BlockPos(HoleHud.mc.player.posX, HoleHud.mc.player.posY, HoleHud.mc.player.posZ);
        final List<ItemStack> llllllllllllllllllIlIlIIlllIIIIl = new ArrayList<ItemStack>(Arrays.asList(this.isBrockOrObby(llllllllllllllllllIlIlIIlllIIIlI.add(this.EAST.x, this.EAST.y, this.EAST.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIlllIIIlI.add(this.EAST.x, this.EAST.y, this.EAST.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIlllIIIlI.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIlllIIIlI.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIlllIIIlI.add(this.WEST.x, this.WEST.y, this.WEST.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIlllIIIlI.add(this.WEST.x, this.WEST.y, this.WEST.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIlllIIIlI.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIlllIIIlI.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)).getBlock()) : new ItemStack(Items.AIR)));
        return llllllllllllllllllIlIlIIlllIIIIl;
    }
    
    @Override
    public void onEnable() {
        this.width = 48.0;
        this.height = 48.0;
        super.onEnable();
    }
    
    private static void preitemrender() {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.scale(1.0f, 1.0f, 0.01f);
    }
    
    private static void postitemrender() {
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
    }
    
    private List<ItemStack> getNorth() {
        final BlockPos llllllllllllllllllIlIlIIIlIIlllI = new BlockPos(HoleHud.mc.player.posX, HoleHud.mc.player.posY, HoleHud.mc.player.posZ);
        final List<ItemStack> llllllllllllllllllIlIlIIIlIIllIl = new ArrayList<ItemStack>(Arrays.asList(this.isBrockOrObby(llllllllllllllllllIlIlIIIlIIlllI.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIIlIIlllI.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIIlIIlllI.add(this.EAST.x, this.EAST.y, this.EAST.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIIlIIlllI.add(this.EAST.x, this.EAST.y, this.EAST.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIIlIIlllI.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIIlIIlllI.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIIlIIlllI.add(this.WEST.x, this.WEST.y, this.WEST.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIIlIIlllI.add(this.WEST.x, this.WEST.y, this.WEST.z)).getBlock()) : new ItemStack(Items.AIR)));
        return llllllllllllllllllIlIlIIIlIIllIl;
    }
    
    private boolean isBrockOrObby(final BlockPos llllllllllllllllllIlIlIIlllIlIII) {
        return HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIlllIlIII).getBlock() == Blocks.BEDROCK || HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIlllIlIII).getBlock() == Blocks.OBSIDIAN;
    }
    
    private List<ItemStack> getWest() {
        final BlockPos llllllllllllllllllIlIlIIllIIIlII = new BlockPos(HoleHud.mc.player.posX, HoleHud.mc.player.posY, HoleHud.mc.player.posZ);
        final List<ItemStack> llllllllllllllllllIlIlIIllIIIIll = new ArrayList<ItemStack>(Arrays.asList(this.isBrockOrObby(llllllllllllllllllIlIlIIllIIIlII.add(this.WEST.x, this.WEST.y, this.WEST.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIllIIIlII.add(this.WEST.x, this.WEST.y, this.WEST.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIllIIIlII.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIllIIIlII.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIllIIIlII.add(this.EAST.x, this.EAST.y, this.EAST.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIllIIIlII.add(this.EAST.x, this.EAST.y, this.EAST.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIllIIIlII.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIllIIIlII.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)).getBlock()) : new ItemStack(Items.AIR)));
        return llllllllllllllllllIlIlIIllIIIIll;
    }
    
    private List<ItemStack> getSouth() {
        final BlockPos llllllllllllllllllIlIlIIllIllIIl = new BlockPos(HoleHud.mc.player.posX, HoleHud.mc.player.posY, HoleHud.mc.player.posZ);
        final List<ItemStack> llllllllllllllllllIlIlIIllIllIII = new ArrayList<ItemStack>(Arrays.asList(this.isBrockOrObby(llllllllllllllllllIlIlIIllIllIIl.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIllIllIIl.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIllIllIIl.add(this.WEST.x, this.WEST.y, this.WEST.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIllIllIIl.add(this.WEST.x, this.WEST.y, this.WEST.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIllIllIIl.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIllIllIIl.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(llllllllllllllllllIlIlIIllIllIIl.add(this.EAST.x, this.EAST.y, this.EAST.z)) ? new ItemStack(HoleHud.mc.world.getBlockState(llllllllllllllllllIlIlIIllIllIIl.add(this.EAST.x, this.EAST.y, this.EAST.z)).getBlock()) : new ItemStack(Items.AIR)));
        return llllllllllllllllllIlIlIIllIllIII;
    }
    
    public HoleHud() {
        super("HoleHud");
        this.NORTH = new BlockPos(0, 0, -1);
        this.EAST = new BlockPos(1, 0, 0);
        this.SOUTH = new BlockPos(0, 0, 1);
        this.WEST = new BlockPos(-1, 0, 0);
    }
    
    private void itemrender(final List<ItemStack> llllllllllllllllllIlIlIIIIlllllI, final int llllllllllllllllllIlIlIIIlIIIIIl, final int llllllllllllllllllIlIlIIIlIIIIII) {
        final ArrayList<Pair<Integer, Integer>> llllllllllllllllllIlIlIIIIllllll = new ArrayList<Pair<Integer, Integer>>((Collection<? extends Pair<Integer, Integer>>)Arrays.asList(new Pair((T)(llllllllllllllllllIlIlIIIlIIIIIl + 16), (S)llllllllllllllllllIlIlIIIlIIIIII), new Pair((T)(llllllllllllllllllIlIlIIIlIIIIIl + 32), (S)(llllllllllllllllllIlIlIIIlIIIIII + 16)), new Pair((T)(llllllllllllllllllIlIlIIIlIIIIIl + 16), (S)(llllllllllllllllllIlIlIIIlIIIIII + 32)), new Pair((T)llllllllllllllllllIlIlIIIlIIIIIl, (S)(llllllllllllllllllIlIlIIIlIIIIII + 16))));
        for (int llllllllllllllllllIlIlIIIlIIIlII = 0; llllllllllllllllllIlIlIIIlIIIlII < 4; ++llllllllllllllllllIlIlIIIlIIIlII) {
            preitemrender();
            InvPreview.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)llllllllllllllllllIlIlIIIIlllllI.get(llllllllllllllllllIlIlIIIlIIIlII), (int)llllllllllllllllllIlIlIIIIllllll.get(llllllllllllllllllIlIlIIIlIIIlII).getKey(), (int)llllllllllllllllllIlIlIIIIllllll.get(llllllllllllllllllIlIlIIIlIIIlII).getValue());
            postitemrender();
        }
    }
}
