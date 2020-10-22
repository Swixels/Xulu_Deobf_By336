package com.elementars.eclient.module.render;

import java.util.*;
import net.minecraft.block.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;

public class Xray extends Module
{
    public static /* synthetic */ Xray INSTANCE;
    private static final /* synthetic */ ArrayList<Block> BLOCKS;
    
    @EventTarget
    public void onRender(final EventRenderBlock lIIIIlllIllIlII) {
        final Block lIIIIlllIllIIll = lIIIIlllIllIlII.getBlockState().getBlock();
        if (shouldXray(lIIIIlllIllIIll) && Xray.mc.getBlockRendererDispatcher().getBlockModelRenderer().renderModelFlat(lIIIIlllIllIlII.getBlockAccess(), lIIIIlllIllIlII.getBakedModel(), lIIIIlllIllIlII.getBlockState(), lIIIIlllIllIlII.getBlockPos(), lIIIIlllIllIlII.getBufferBuilder(), lIIIIlllIllIlII.isCheckSides(), lIIIIlllIllIlII.getRand())) {
            lIIIIlllIllIlII.setRenderable(true);
        }
        lIIIIlllIllIlII.setCancelled(true);
    }
    
    public Xray() {
        super("Xray", "See through blocks!", 0, Category.RENDER, true);
        initblocks();
        Xray.INSTANCE = this;
    }
    
    public static void initblocks() {
        Xray.BLOCKS.add(Block.getBlockFromName("coal_ore"));
        Xray.BLOCKS.add(Block.getBlockFromName("iron_ore"));
        Xray.BLOCKS.add(Block.getBlockFromName("gold_ore"));
        Xray.BLOCKS.add(Block.getBlockFromName("redstone_ore"));
        Xray.BLOCKS.add(Block.getBlockById(74));
        Xray.BLOCKS.add(Block.getBlockFromName("lapis_ore"));
        Xray.BLOCKS.add(Block.getBlockFromName("diamond_ore"));
        Xray.BLOCKS.add(Block.getBlockFromName("emerald_ore"));
        Xray.BLOCKS.add(Block.getBlockFromName("quartz_ore"));
        Xray.BLOCKS.add(Block.getBlockFromName("clay"));
        Xray.BLOCKS.add(Block.getBlockFromName("glowstone"));
        Xray.BLOCKS.add(Block.getBlockById(8));
        Xray.BLOCKS.add(Block.getBlockById(9));
        Xray.BLOCKS.add(Block.getBlockById(10));
        Xray.BLOCKS.add(Block.getBlockById(11));
        Xray.BLOCKS.add(Block.getBlockFromName("crafting_table"));
        Xray.BLOCKS.add(Block.getBlockById(61));
        Xray.BLOCKS.add(Block.getBlockById(62));
        Xray.BLOCKS.add(Block.getBlockFromName("torch"));
        Xray.BLOCKS.add(Block.getBlockFromName("ladder"));
        Xray.BLOCKS.add(Block.getBlockFromName("tnt"));
        Xray.BLOCKS.add(Block.getBlockFromName("coal_block"));
        Xray.BLOCKS.add(Block.getBlockFromName("iron_block"));
        Xray.BLOCKS.add(Block.getBlockFromName("gold_block"));
        Xray.BLOCKS.add(Block.getBlockFromName("diamond_block"));
        Xray.BLOCKS.add(Block.getBlockFromName("emerald_block"));
        Xray.BLOCKS.add(Block.getBlockFromName("redstone_block"));
        Xray.BLOCKS.add(Block.getBlockFromName("lapis_block"));
        Xray.BLOCKS.add(Block.getBlockFromName("fire"));
        Xray.BLOCKS.add(Block.getBlockFromName("mossy_cobblestone"));
        Xray.BLOCKS.add(Block.getBlockFromName("mob_spawner"));
        Xray.BLOCKS.add(Block.getBlockFromName("end_portal_frame"));
        Xray.BLOCKS.add(Block.getBlockFromName("enchanting_table"));
        Xray.BLOCKS.add(Block.getBlockFromName("bookshelf"));
        Xray.BLOCKS.add(Block.getBlockFromName("command_block"));
    }
    
    @Override
    public void onDisable() {
        Xray.mc.renderGlobal.loadRenderers();
    }
    
    @Override
    public void onEnable() {
        Xray.mc.renderGlobal.loadRenderers();
    }
    
    static {
        BLOCKS = new ArrayList<Block>();
    }
    
    public static boolean shouldXray(final Block lIIIIlllIlIlllI) {
        return Xray.BLOCKS.contains(lIIIIlllIlIlllI);
    }
    
    public static boolean addBlock(final String lIIIIlllIlIllII) {
        if (Block.getBlockFromName(lIIIIlllIlIllII) != null) {
            Xray.BLOCKS.add(Block.getBlockFromName(lIIIIlllIlIllII));
            return true;
        }
        return false;
    }
    
    public static boolean delBlock(final String lIIIIlllIlIlIII) {
        if (Block.getBlockFromName(lIIIIlllIlIlIII) != null) {
            Xray.BLOCKS.remove(Block.getBlockFromName(lIIIIlllIlIlIII));
            return true;
        }
        return false;
    }
    
    public static ArrayList<Block> getBLOCKS() {
        return Xray.BLOCKS;
    }
}
