package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import com.elementars.eclient.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.elementars.eclient.command.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.events.*;
import java.util.*;

public class LogoutSpots extends Module
{
    private final /* synthetic */ Value<Integer> max_distance;
    private final /* synthetic */ Value<Boolean> box;
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ SpotSet spots;
    private final /* synthetic */ Value<String> color;
    private final /* synthetic */ RenderUtils renderUtils;
    private final /* synthetic */ Value<Boolean> cf;
    private final /* synthetic */ Value<Boolean> render;
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ Value<Boolean> print_message;
    private final /* synthetic */ Value<Boolean> coords;
    private final /* synthetic */ Value<Boolean> watermark;
    private final /* synthetic */ Value<Integer> green;
    
    public void renderNametag(final EntityPlayer lIIllIIIIllIII, final double lIIllIIIIlIlll, final double lIIllIIIIIllII, final double lIIllIIIIlIlIl) {
        GlStateManager.pushMatrix();
        final FontRenderer lIIllIIIIlIlII = Wrapper.getMinecraft().fontRenderer;
        String lIIllIIIIlIIll = String.valueOf(new StringBuilder().append(lIIllIIIIllIII.getName()).append(" ").append(this.coords.getValue() ? String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append("(").append((int)lIIllIIIIllIII.posX).append(", ").append((int)lIIllIIIIllIII.posY).append(", ").append((int)lIIllIIIIllIII.posZ).append(")")) : String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append("").append(Math.round(LogoutSpots.mc.player.getDistance((Entity)lIIllIIIIllIII))))));
        lIIllIIIIlIIll = lIIllIIIIlIIll.replace(".0", "");
        final float lIIllIIIIlIIlI = LogoutSpots.mc.player.getDistance((Entity)lIIllIIIIllIII);
        final float lIIllIIIIlIIIl = ((lIIllIIIIlIIlI / 5.0f <= 2.0f) ? 2.0f : (lIIllIIIIlIIlI / 5.0f * 1.5075471f)) * 2.5f * 0.005075472f;
        GL11.glTranslated((double)(float)lIIllIIIIlIlll, (float)lIIllIIIIIllII + 2.5 + ((lIIllIIIIlIIlI / 5.0f > 2.0f) ? (lIIllIIIIlIIlI / 12.0f - 0.7) : 0.0), (double)(float)lIIllIIIIlIlIl);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-LogoutSpots.mc.renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(LogoutSpots.mc.renderManager.playerViewX, (LogoutSpots.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-lIIllIIIIlIIIl, -lIIllIIIIlIIIl, lIIllIIIIlIIIl);
        this.renderUtils.disableGlCap(2896, 2929);
        this.renderUtils.enableGlCap(3042);
        GL11.glBlendFunc(770, 771);
        int lIIllIIIIlIIII = 0;
        if (this.cf.getValue()) {
            final int lIIllIIIIllIlI = Xulu.cFontRenderer.getStringWidth(lIIllIIIIlIIll) / 2 + 1;
        }
        else {
            lIIllIIIIlIIII = lIIllIIIIlIlII.getStringWidth(lIIllIIIIlIIll) / 2;
        }
        if (this.box.getValue()) {
            Gui.drawRect(-lIIllIIIIlIIII - 2, 10, lIIllIIIIlIIII + 1, 20, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 120));
        }
        if (this.cf.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(lIIllIIIIlIIll, -lIIllIIIIlIIII, 10.0, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
        }
        else {
            LogoutSpots.mc.fontRenderer.drawStringWithShadow(lIIllIIIIlIIll, (float)(-lIIllIIIIlIIII), 11.0f, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
        }
        this.renderUtils.resetCaps();
        GlStateManager.resetColor();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public LogoutSpots() {
        super("LogoutSpot", "show where a player logs out", 0, Category.RENDER, true);
        this.renderUtils = new RenderUtils();
        this.cf = this.register(new Value<Boolean>("Custom Font", this, false));
        this.render = this.register(new Value<Boolean>("Render", this, true));
        this.box = this.register(new Value<Boolean>("Box", this, false));
        this.coords = this.register(new Value<Boolean>("Coordinates", this, true));
        this.max_distance = this.register(new Value<Integer>("Max Distance", this, 320, 0, 1000));
        this.print_message = this.register(new Value<Boolean>("Print Message", this, true));
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.color = this.register(new Value<String>("Color", this, "White", ColorTextUtils.colors));
        this.red = this.register(new Value<Integer>("Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 0, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 0, 0, 255));
        this.spots = new SpotSet();
    }
    
    @Override
    public void onEnable() {
        LogoutSpots.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onWorldUnload(final WorldEvent.Unload lIIlIlllllIIll) {
        this.reset();
    }
    
    private void reset() {
        synchronized (this.spots) {
            this.spots.clear();
        }
    }
    
    @EventTarget
    public void onPlayerDisconnect(final EventPlayerConnect.Leave lIIllIIIlIllII) {
        if (LogoutSpots.mc.world == null) {
            return;
        }
        final EntityPlayer lIIllIIIlIlIll = LogoutSpots.mc.world.getPlayerEntityByUUID(lIIllIIIlIllII.getUuid());
        if (lIIllIIIlIlIll != null && LogoutSpots.mc.player != null && !LogoutSpots.mc.player.equals((Object)lIIllIIIlIlIll)) {
            final AxisAlignedBB lIIllIIIlIlllI = lIIllIIIlIlIll.getEntityBoundingBox();
            synchronized (this.spots) {
                if (this.spots.add(new LogoutPos(lIIllIIIlIllII.getUuid(), lIIllIIIlIlIll.getName(), new Vec3d(lIIllIIIlIlllI.maxX, lIIllIIIlIlllI.maxY, lIIllIIIlIlllI.maxZ), new Vec3d(lIIllIIIlIlllI.minX, lIIllIIIlIlllI.minY, lIIllIIIlIlllI.minZ), lIIllIIIlIlllI, lIIllIIIlIlIll)) && this.print_message.getValue()) {
                    if (this.watermark.getValue()) {
                        Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(String.format("%s has disconnected!", lIIllIIIlIlIll.getName()))));
                    }
                    else {
                        Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(String.format("%s has disconnected!", lIIllIIIlIlIll.getName()))));
                    }
                }
            }
        }
    }
    
    @EventTarget
    public void onPlayerUpdate(final LocalPlayerUpdateEvent lIIlIllllllIIl) {
        if (this.max_distance.getValue() > 0) {
            synchronized (this.spots) {
                this.spots.removeIf(lIIlIllllIlIII -> LogoutSpots.mc.player.getPositionVector().distanceTo(lIIlIllllIlIII.getTopVec()) > this.max_distance.getValue());
            }
        }
    }
    
    @EventTarget
    public void onPlayerConnect(final EventPlayerConnect.Join lIIllIIIllllIl) {
        synchronized (this.spots) {
            final Pair<Boolean, LogoutPos> lIIllIIIllllll = this.spots.removeIfReturn(lIIlIlllIIllll -> lIIlIlllIIllll.getId().equals(lIIllIIIllllIl.getUuid()));
            if (lIIllIIIllllll.getKey() && this.print_message.getValue()) {
                final double lIIllIIlIIIIlI = lIIllIIIllllll.getValue().player.lastTickPosX;
                final double lIIllIIlIIIIIl = lIIllIIIllllll.getValue().player.lastTickPosY;
                final double lIIllIIlIIIIII = lIIllIIIllllll.getValue().player.lastTickPosZ;
                if (this.watermark.getValue()) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(String.format("%s has joined (%s, %s, %s)!", lIIllIIIllllIl.getName(), (int)lIIllIIlIIIIlI, (int)lIIllIIlIIIIIl, (int)lIIllIIlIIIIII))));
                }
                else {
                    Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append(String.format("%s has joined (%s, %s, %s)!", lIIllIIIllllIl.getName(), (int)lIIllIIlIIIIlI, (int)lIIllIIlIIIIIl, (int)lIIllIIlIIIIII))));
                }
            }
        }
    }
    
    @Override
    public void onWorldRender(final RenderEvent lIIllIIIIIIIIl) {
        if (!this.render.getValue()) {
            return;
        }
        GlStateManager.pushMatrix();
        synchronized (this.spots) {
            final double lIIlIlllIllllI;
            final double lIIlIlllIlllIl;
            final double lIIlIlllIlllII;
            final AxisAlignedBB lIIlIlllIllIll;
            final AxisAlignedBB lIIlIlllIllIlI;
            this.spots.forEach(lIIlIlllIlllll -> {
                lIIlIlllIllllI = lIIlIlllIlllll.lastTickPosX + (lIIlIlllIlllll.posX - lIIlIlllIlllll.lastTickPosX) * LogoutSpots.mc.timer.renderPartialTicks - LogoutSpots.mc.renderManager.renderPosX;
                lIIlIlllIlllIl = lIIlIlllIlllll.lastTickPosY + (lIIlIlllIlllll.posY - lIIlIlllIlllll.lastTickPosY) * LogoutSpots.mc.timer.renderPartialTicks - LogoutSpots.mc.renderManager.renderPosY;
                lIIlIlllIlllII = lIIlIlllIlllll.lastTickPosZ + (lIIlIlllIlllll.posZ - lIIlIlllIlllll.lastTickPosZ) * LogoutSpots.mc.timer.renderPartialTicks - LogoutSpots.mc.renderManager.renderPosZ;
                lIIlIlllIllIll = lIIlIlllIlllll.bb;
                lIIlIlllIllIlI = new AxisAlignedBB(lIIlIlllIllIll.minX - lIIlIlllIlllll.posX + lIIlIlllIllllI - 0.05, lIIlIlllIllIll.minY - lIIlIlllIlllll.posY + lIIlIlllIlllIl, lIIlIlllIllIll.minZ - lIIlIlllIlllll.posZ + lIIlIlllIlllII - 0.05, lIIlIlllIllIll.maxX - lIIlIlllIlllll.posX + lIIlIlllIllllI + 0.05, lIIlIlllIllIll.maxY - lIIlIlllIlllll.posY + lIIlIlllIlllIl + 0.15, lIIlIlllIllIll.maxZ - lIIlIlllIlllll.posZ + lIIlIlllIlllII + 0.05);
                XuluTessellator.drawBoundingBox(lIIlIlllIllIlI, 1.5f, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
                this.renderNametag(lIIlIlllIlllll.player, lIIlIlllIllllI, lIIlIlllIlllIl, lIIlIlllIlllII);
                return;
            });
        }
        GlStateManager.popMatrix();
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load lIIlIllllIllll) {
        this.reset();
    }
    
    @Override
    public void onDisable() {
        LogoutSpots.EVENT_BUS.unregister((Object)this);
        this.reset();
    }
    
    public class LogoutPos
    {
        final /* synthetic */ String name;
        final /* synthetic */ EntityPlayer player;
        final /* synthetic */ double posZ;
        final /* synthetic */ double lastTickPosX;
        final /* synthetic */ double lastTickPosZ;
        final /* synthetic */ double lastTickPosY;
        final /* synthetic */ AxisAlignedBB bb;
        final /* synthetic */ Vec3d maxs;
        final /* synthetic */ double posX;
        final /* synthetic */ Vec3d mins;
        final /* synthetic */ double posY;
        final /* synthetic */ UUID id;
        
        @Override
        public boolean equals(final Object lllllllllllllllllIllIlIIllllIlIl) {
            return this == lllllllllllllllllIllIlIIllllIlIl || (lllllllllllllllllIllIlIIllllIlIl instanceof LogoutPos && this.getId().equals(((LogoutPos)lllllllllllllllllIllIlIIllllIlIl).getId()));
        }
        
        @Override
        public int hashCode() {
            return this.getId().hashCode();
        }
        
        public AxisAlignedBB getBb() {
            return this.bb;
        }
        
        public UUID getId() {
            return this.id;
        }
        
        public EntityPlayer getPlayer() {
            return this.player;
        }
        
        private LogoutPos(final UUID lllllllllllllllllIllIlIlIIIllIll, final String lllllllllllllllllIllIlIlIIIlIIlI, final Vec3d lllllllllllllllllIllIlIlIIIlIIIl, final Vec3d lllllllllllllllllIllIlIlIIIlIIII, final AxisAlignedBB lllllllllllllllllIllIlIlIIIlIlll, final EntityPlayer lllllllllllllllllIllIlIlIIIIlllI) {
            this.id = lllllllllllllllllIllIlIlIIIllIll;
            this.name = lllllllllllllllllIllIlIlIIIlIIlI;
            this.maxs = lllllllllllllllllIllIlIlIIIlIIIl;
            this.mins = lllllllllllllllllIllIlIlIIIlIIII;
            this.bb = lllllllllllllllllIllIlIlIIIlIlll;
            this.player = lllllllllllllllllIllIlIlIIIIlllI;
            this.posX = lllllllllllllllllIllIlIlIIIIlllI.posX;
            this.posY = lllllllllllllllllIllIlIlIIIIlllI.posY;
            this.posZ = lllllllllllllllllIllIlIlIIIIlllI.posZ;
            this.lastTickPosX = lllllllllllllllllIllIlIlIIIIlllI.lastTickPosX;
            this.lastTickPosY = lllllllllllllllllIllIlIlIIIIlllI.lastTickPosY;
            this.lastTickPosZ = lllllllllllllllllIllIlIlIIIIlllI.lastTickPosZ;
        }
        
        public Vec3d getMaxs() {
            return this.maxs;
        }
        
        public Vec3d getMins() {
            return this.mins;
        }
        
        public Vec3d getTopVec() {
            return new Vec3d((this.getMins().x + this.getMaxs().x) / 2.0, this.getMaxs().y, (this.getMins().z + this.getMaxs().z) / 2.0);
        }
        
        public String getName() {
            return this.name;
        }
    }
}
