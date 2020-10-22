package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import com.mojang.realmsclient.gui.*;
import com.elementars.eclient.*;
import java.awt.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import java.util.*;

public class Waypoints extends Module
{
    private final /* synthetic */ Value<Boolean> render;
    public static final /* synthetic */ Set<Waypoint> WAYPOINTS;
    private final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ RenderUtils renderUtils;
    private final /* synthetic */ Value<Boolean> cf;
    
    @Override
    public void onRender() {
        synchronized (Waypoints.WAYPOINTS) {
            Waypoints.WAYPOINTS.forEach(lllIIlIIlllIllI -> {
                if (Waypoints.mc.player.dimension == lllIIlIIlllIllI.dimension) {
                    this.renderNametag2(lllIIlIIlllIllI);
                }
            });
        }
    }
    
    static {
        WAYPOINTS = new HashSet<Waypoint>();
    }
    
    private void renderNametag2(final Waypoint lllIIlIlIIlIIIl) {
        final String lllIIlIlIIlIlII = String.valueOf(new StringBuilder().append(lllIIlIlIIlIIIl.getName()).append(this.mode.getValue().equalsIgnoreCase("Safe") ? "" : String.valueOf(new StringBuilder().append(" ").append(this.mode.getValue().equalsIgnoreCase("Coordinates") ? String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append("(").append(lllIIlIlIIlIIIl.getPos().x).append(", ").append(lllIIlIlIIlIIIl.getPos().y).append(", ").append(lllIIlIlIIlIIIl.getPos().z).append(")")) : String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append("").append(Math.round(Waypoints.mc.player.getDistance((double)lllIIlIlIIlIIIl.getPos().x, (double)lllIIlIlIIlIIIl.getPos().y, (double)lllIIlIlIIlIIIl.getPos().z))))))));
        final Plane lllIIlIlIIlIIll = VectorUtils.toScreen(lllIIlIlIIlIIIl.getPos().getX() + 0.5, lllIIlIlIIlIIIl.getPos().getY() + 1.5, lllIIlIlIIlIIIl.getPos().getZ() + 0.5);
        if (this.cf.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(lllIIlIlIIlIlII, (float)lllIIlIlIIlIIll.getX() - Xulu.cFontRenderer.getStringWidth(lllIIlIlIIlIlII) / 2, (float)lllIIlIlIIlIIll.getY() - Xulu.cFontRenderer.getHeight() / 2.0f, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
        }
        else {
            Waypoints.fontRenderer.drawStringWithShadow(lllIIlIlIIlIlII, (float)lllIIlIlIIlIIll.getX() - Waypoints.fontRenderer.getStringWidth(lllIIlIlIIlIlII) / 2, (float)lllIIlIlIIlIIll.getY() - Waypoints.fontRenderer.FONT_HEIGHT / 2, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
        }
    }
    
    @Override
    public void onDisable() {
        Waypoints.EVENT_BUS.unregister((Object)this);
    }
    
    public Waypoints() {
        super("Waypoints", "Shows locations of waypoints", 0, Category.RENDER, true);
        this.renderUtils = new RenderUtils();
        this.cf = this.register(new Value<Boolean>("Custom Font", this, false));
        this.render = this.register(new Value<Boolean>("Render", this, true));
        this.mode = this.register(new Value<String>("Mode", this, "Coordinates", new String[] { "Coordinates", "Distance", "Safe" }));
        this.red = this.register(new Value<Integer>("Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 0, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 0, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 150, 0, 255));
    }
    
    @Override
    public void onEnable() {
        Waypoints.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onWorldRender(final RenderEvent lllIIlIlIIIIIll) {
        if (!this.render.getValue()) {
            return;
        }
        GlStateManager.pushMatrix();
        synchronized (Waypoints.WAYPOINTS) {
            Waypoints.WAYPOINTS.forEach(lllIIlIIlllllII -> {
                if (Waypoints.mc.player.dimension == lllIIlIIlllllII.dimension) {
                    XuluTessellator.prepare(7);
                    XuluTessellator.drawBox(lllIIlIIlllllII.getPos(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()).getRGB(), 63);
                    XuluTessellator.release();
                }
                return;
            });
        }
        GlStateManager.popMatrix();
    }
    
    public static class Waypoint
    {
        final /* synthetic */ AxisAlignedBB bb;
        final /* synthetic */ BlockPos pos;
        final /* synthetic */ UUID id;
        final /* synthetic */ String name;
        final /* synthetic */ int dimension;
        
        public String getName() {
            return this.name;
        }
        
        public int getDimension() {
            return this.dimension;
        }
        
        @Override
        public int hashCode() {
            return this.getId().hashCode();
        }
        
        public BlockPos getPos() {
            return this.pos;
        }
        
        public UUID getId() {
            return this.id;
        }
        
        public AxisAlignedBB getBb() {
            return this.bb;
        }
        
        public Waypoint(final UUID llllllllllllllllIlIlllIllIlllIIl, final String llllllllllllllllIlIlllIllIlllIII, final BlockPos llllllllllllllllIlIlllIllIllIIIl, final AxisAlignedBB llllllllllllllllIlIlllIllIllIIII, final int llllllllllllllllIlIlllIllIllIlIl) {
            this.id = llllllllllllllllIlIlllIllIlllIIl;
            this.name = llllllllllllllllIlIlllIllIlllIII;
            this.pos = llllllllllllllllIlIlllIllIllIIIl;
            this.bb = llllllllllllllllIlIlllIllIllIIII;
            this.dimension = llllllllllllllllIlIlllIllIllIlIl;
        }
        
        @Override
        public boolean equals(final Object llllllllllllllllIlIlllIlIlllllll) {
            return this == llllllllllllllllIlIlllIlIlllllll || (llllllllllllllllIlIlllIlIlllllll instanceof Waypoint && this.getId().equals(((Waypoint)llllllllllllllllIlIlllIlIlllllll).getId()));
        }
    }
}
