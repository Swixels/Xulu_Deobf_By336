package com.elementars.eclient.module.render;

import com.elementars.eclient.module.*;
import com.elementars.eclient.command.*;
import net.minecraft.entity.monster.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.pathfinding.*;
import com.elementars.eclient.event.events.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.init.*;
import net.minecraft.block.material.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;

public class Pathfind extends Module
{
    public static /* synthetic */ ArrayList<PathPoint> points;
    static /* synthetic */ PathPoint to;
    
    public Pathfind() {
        super("Pathfind", "Finds a path lol", 0, Category.RENDER, true);
    }
    
    static {
        Pathfind.points = new ArrayList<PathPoint>();
        Pathfind.to = null;
    }
    
    @Override
    public void onUpdate() {
        final PathPoint lllllllllllllllllIIIIIlIllIIIIIl = Pathfind.points.stream().min(Comparator.comparing(lllllllllllllllllIIIIIlIlIlllIlI -> Pathfind.mc.player.getDistance((double)lllllllllllllllllIIIIIlIlIlllIlI.x, (double)lllllllllllllllllIIIIIlIlIlllIlI.y, (double)lllllllllllllllllIIIIIlIlIlllIlI.z))).orElse(null);
        if (lllllllllllllllllIIIIIlIllIIIIIl == null) {
            return;
        }
        if (Pathfind.mc.player.getDistance((double)lllllllllllllllllIIIIIlIllIIIIIl.x, (double)lllllllllllllllllIIIIIlIllIIIIIl.y, (double)lllllllllllllllllIIIIIlIllIIIIIl.z) > 0.8) {
            return;
        }
        final Iterator<PathPoint> lllllllllllllllllIIIIIlIllIIIIII = Pathfind.points.iterator();
        while (lllllllllllllllllIIIIIlIllIIIIII.hasNext()) {
            if (lllllllllllllllllIIIIIlIllIIIIII.next() == lllllllllllllllllIIIIIlIllIIIIIl) {
                lllllllllllllllllIIIIIlIllIIIIII.remove();
                break;
            }
            lllllllllllllllllIIIIIlIllIIIIII.remove();
        }
        if (Pathfind.points.size() <= 1 && Pathfind.to != null) {
            final boolean lllllllllllllllllIIIIIlIllIIIlII = createPath(Pathfind.to);
            final boolean lllllllllllllllllIIIIIlIllIIIIll = Pathfind.points.size() <= 4;
            if ((lllllllllllllllllIIIIIlIllIIIlII && lllllllllllllllllIIIIIlIllIIIIll) || lllllllllllllllllIIIIIlIllIIIIll) {
                Pathfind.points.clear();
                Pathfind.to = null;
                if (lllllllllllllllllIIIIIlIllIIIlII) {
                    Command.sendChatMessage("Arrived!");
                }
                else {
                    Command.sendChatMessage("Can't go on: pathfinder has hit dead end");
                }
            }
        }
    }
    
    public static boolean createPath(final PathPoint lllllllllllllllllIIIIIlIllIlllIl) {
        Pathfind.to = lllllllllllllllllIIIIIlIllIlllIl;
        final WalkNodeProcessor lllllllllllllllllIIIIIlIllIlllII = new AnchoredWalkNodeProcessor(new PathPoint((int)Pathfind.mc.player.posX, (int)Pathfind.mc.player.posY, (int)Pathfind.mc.player.posZ));
        final EntityZombie lllllllllllllllllIIIIIlIllIllIll = new EntityZombie((World)Pathfind.mc.world);
        lllllllllllllllllIIIIIlIllIllIll.setPathPriority(PathNodeType.WATER, 16.0f);
        lllllllllllllllllIIIIIlIllIllIll.posX = Pathfind.mc.player.posX;
        lllllllllllllllllIIIIIlIllIllIll.posY = Pathfind.mc.player.posY;
        lllllllllllllllllIIIIIlIllIllIll.posZ = Pathfind.mc.player.posZ;
        final PathFinder lllllllllllllllllIIIIIlIllIllIlI = new PathFinder((NodeProcessor)lllllllllllllllllIIIIIlIllIlllII);
        final Path lllllllllllllllllIIIIIlIllIllIIl = lllllllllllllllllIIIIIlIllIllIlI.findPath((IBlockAccess)Pathfind.mc.world, (EntityLiving)lllllllllllllllllIIIIIlIllIllIll, new BlockPos(lllllllllllllllllIIIIIlIllIlllIl.x, lllllllllllllllllIIIIIlIllIlllIl.y, lllllllllllllllllIIIIIlIllIlllIl.z), Float.MAX_VALUE);
        lllllllllllllllllIIIIIlIllIllIll.setPathPriority(PathNodeType.WATER, 0.0f);
        if (lllllllllllllllllIIIIIlIllIllIIl == null) {
            Command.sendChatMessage("Failed to create path!");
            return false;
        }
        Pathfind.points = new ArrayList<PathPoint>(Arrays.asList(lllllllllllllllllIIIIIlIllIllIIl.points));
        return Pathfind.points.get(Pathfind.points.size() - 1).distanceTo(lllllllllllllllllIIIIIlIllIlllIl) <= 1.0f;
    }
    
    @Override
    public void onWorldRender(final RenderEvent lllllllllllllllllIIIIIlIllIIllIl) {
        if (Pathfind.points.isEmpty()) {
            return;
        }
        GL11.glDisable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glLineWidth(1.5f);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GlStateManager.disableDepth();
        GL11.glBegin(1);
        final PathPoint lllllllllllllllllIIIIIlIllIIllII = Pathfind.points.get(0);
        GL11.glVertex3d(lllllllllllllllllIIIIIlIllIIllII.x - Pathfind.mc.getRenderManager().renderPosX + 0.5, lllllllllllllllllIIIIIlIllIIllII.y - Pathfind.mc.getRenderManager().renderPosY, lllllllllllllllllIIIIIlIllIIllII.z - Pathfind.mc.getRenderManager().renderPosZ + 0.5);
        for (int lllllllllllllllllIIIIIlIllIIllll = 0; lllllllllllllllllIIIIIlIllIIllll < Pathfind.points.size() - 1; ++lllllllllllllllllIIIIIlIllIIllll) {
            final PathPoint lllllllllllllllllIIIIIlIllIlIIII = Pathfind.points.get(lllllllllllllllllIIIIIlIllIIllll);
            GL11.glVertex3d(lllllllllllllllllIIIIIlIllIlIIII.x - Pathfind.mc.getRenderManager().renderPosX + 0.5, lllllllllllllllllIIIIIlIllIlIIII.y - Pathfind.mc.getRenderManager().renderPosY, lllllllllllllllllIIIIIlIllIlIIII.z - Pathfind.mc.getRenderManager().renderPosZ + 0.5);
            if (lllllllllllllllllIIIIIlIllIIllll != Pathfind.points.size() - 1) {
                GL11.glVertex3d(lllllllllllllllllIIIIIlIllIlIIII.x - Pathfind.mc.getRenderManager().renderPosX + 0.5, lllllllllllllllllIIIIIlIllIlIIII.y - Pathfind.mc.getRenderManager().renderPosY, lllllllllllllllllIIIIIlIllIlIIII.z - Pathfind.mc.getRenderManager().renderPosZ + 0.5);
            }
        }
        GL11.glEnd();
        GlStateManager.enableDepth();
    }
    
    private static class AnchoredWalkNodeProcessor extends WalkNodeProcessor
    {
        /* synthetic */ PathPoint from;
        
        public PathPoint getStart() {
            return this.from;
        }
        
        public PathNodeType getPathNodeType(final IBlockAccess llIIlIlllIIllll, final int llIIlIlllIIlIII, final int llIIlIlllIIIlll, final int llIIlIlllIIIllI) {
            PathNodeType llIIlIlllIIlIll = this.getPathNodeTypeRaw(llIIlIlllIIllll, llIIlIlllIIlIII, llIIlIlllIIIlll, llIIlIlllIIIllI);
            if (llIIlIlllIIlIll == PathNodeType.OPEN && llIIlIlllIIIlll >= 1) {
                final Block llIIlIlllIlIIlI = llIIlIlllIIllll.getBlockState(new BlockPos(llIIlIlllIIlIII, llIIlIlllIIIlll - 1, llIIlIlllIIIllI)).getBlock();
                final PathNodeType llIIlIlllIlIIIl = this.getPathNodeTypeRaw(llIIlIlllIIllll, llIIlIlllIIlIII, llIIlIlllIIIlll - 1, llIIlIlllIIIllI);
                llIIlIlllIIlIll = ((llIIlIlllIlIIIl != PathNodeType.WALKABLE && llIIlIlllIlIIIl != PathNodeType.OPEN && llIIlIlllIlIIIl != PathNodeType.LAVA) ? PathNodeType.WALKABLE : PathNodeType.OPEN);
                if (llIIlIlllIlIIIl == PathNodeType.DAMAGE_FIRE || llIIlIlllIlIIlI == Blocks.MAGMA) {
                    llIIlIlllIIlIll = PathNodeType.DAMAGE_FIRE;
                }
                if (llIIlIlllIlIIIl == PathNodeType.DAMAGE_CACTUS) {
                    llIIlIlllIIlIll = PathNodeType.DAMAGE_CACTUS;
                }
            }
            llIIlIlllIIlIll = this.checkNeighborBlocks(llIIlIlllIIllll, llIIlIlllIIlIII, llIIlIlllIIIlll, llIIlIlllIIIllI, llIIlIlllIIlIll);
            return llIIlIlllIIlIll;
        }
        
        public AnchoredWalkNodeProcessor(final PathPoint llIIlIllllIIIlI) {
            this.from = llIIlIllllIIIlI;
        }
        
        public boolean getCanEnterDoors() {
            return true;
        }
        
        public boolean getCanSwim() {
            return true;
        }
        
        protected PathNodeType getPathNodeTypeRaw(final IBlockAccess llIIlIllIlIllll, final int llIIlIllIllIlll, final int llIIlIllIllIllI, final int llIIlIllIlIllII) {
            final BlockPos llIIlIllIllIlII = new BlockPos(llIIlIllIllIlll, llIIlIllIllIllI, llIIlIllIlIllII);
            final IBlockState llIIlIllIllIIll = llIIlIllIlIllll.getBlockState(llIIlIllIllIlII);
            final Block llIIlIllIllIIlI = llIIlIllIllIIll.getBlock();
            final Material llIIlIllIllIIIl = llIIlIllIllIIll.getMaterial();
            final PathNodeType llIIlIllIllIIII = llIIlIllIllIIlI.getAiPathNodeType(llIIlIllIllIIll, llIIlIllIlIllll, llIIlIllIllIlII);
            if (llIIlIllIllIIII != null) {
                return llIIlIllIllIIII;
            }
            if (llIIlIllIllIIIl == Material.AIR) {
                return PathNodeType.OPEN;
            }
            if (llIIlIllIllIIlI == Blocks.TRAPDOOR || llIIlIllIllIIlI == Blocks.IRON_TRAPDOOR || llIIlIllIllIIlI == Blocks.WATERLILY) {
                return PathNodeType.TRAPDOOR;
            }
            if (llIIlIllIllIIlI == Blocks.FIRE) {
                return PathNodeType.DAMAGE_FIRE;
            }
            if (llIIlIllIllIIlI == Blocks.CACTUS) {
                return PathNodeType.DAMAGE_CACTUS;
            }
            if (llIIlIllIllIIlI instanceof BlockDoor && llIIlIllIllIIIl == Material.WOOD && !(boolean)llIIlIllIllIIll.getValue((IProperty)BlockDoor.OPEN)) {
                return PathNodeType.DOOR_WOOD_CLOSED;
            }
            if (llIIlIllIllIIlI instanceof BlockDoor && llIIlIllIllIIIl == Material.IRON && !(boolean)llIIlIllIllIIll.getValue((IProperty)BlockDoor.OPEN)) {
                return PathNodeType.DOOR_IRON_CLOSED;
            }
            if (llIIlIllIllIIlI instanceof BlockDoor && (boolean)llIIlIllIllIIll.getValue((IProperty)BlockDoor.OPEN)) {
                return PathNodeType.DOOR_OPEN;
            }
            if (llIIlIllIllIIlI instanceof BlockRailBase) {
                return PathNodeType.RAIL;
            }
            if (llIIlIllIllIIlI instanceof BlockFence || llIIlIllIllIIlI instanceof BlockWall || (llIIlIllIllIIlI instanceof BlockFenceGate && !(boolean)llIIlIllIllIIll.getValue((IProperty)BlockFenceGate.OPEN))) {
                return PathNodeType.FENCE;
            }
            if (llIIlIllIllIIIl == Material.WATER) {
                return PathNodeType.WALKABLE;
            }
            if (llIIlIllIllIIIl == Material.LAVA) {
                return PathNodeType.LAVA;
            }
            return llIIlIllIllIIlI.isPassable(llIIlIllIlIllll, llIIlIllIllIlII) ? PathNodeType.OPEN : PathNodeType.BLOCKED;
        }
    }
}
