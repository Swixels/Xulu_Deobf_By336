package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import com.elementars.eclient.friend.*;
import java.awt.*;
import com.elementars.eclient.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.event.events.*;

public class Chams extends Module
{
    public final /* synthetic */ Value<Float> width;
    public final /* synthetic */ Value<Integer> b;
    public final /* synthetic */ Value<Integer> Vb;
    public final /* synthetic */ Value<Integer> a;
    private static /* synthetic */ Value<Boolean> players;
    public final /* synthetic */ Value<Integer> Wg;
    public final /* synthetic */ Value<Integer> Vg;
    public final /* synthetic */ Value<Boolean> lines;
    private static /* synthetic */ Value<Boolean> mobs;
    public static /* synthetic */ Value<Boolean> crystals;
    public static /* synthetic */ Value<String> mode;
    public final /* synthetic */ Value<Integer> g;
    public final /* synthetic */ Value<Integer> Wr;
    private static /* synthetic */ Value<Boolean> animals;
    public final /* synthetic */ Value<Integer> Wb;
    public final /* synthetic */ Value<Integer> Vr;
    public final /* synthetic */ Value<Boolean> rainbow;
    public final /* synthetic */ Value<Integer> r;
    public final /* synthetic */ Value<Boolean> hand;
    public final /* synthetic */ Value<Boolean> friendColor;
    
    public static boolean renderChams(final Entity lIlIIlIlIllIlI) {
        return !Chams.mode.getValue().equalsIgnoreCase("ESP") && ((lIlIIlIlIllIlI instanceof EntityPlayer) ? Chams.players.getValue() : (EntityUtil.isPassive(lIlIIlIlIllIlI) ? Chams.animals.getValue() : ((boolean)Chams.mobs.getValue())));
    }
    
    public Chams() {
        super("Chams", "See entities through walls", 0, Category.RENDER, true);
        Chams.mode = this.register(new Value<String>("Mode", this, "ESP", new String[] { "ESP", "Normal", "Walls" }));
        this.Vr = this.register(new Value<Integer>("Visible Red", this, 255, 0, 255)).visibleWhen(lIlIIlIIlIllll -> Chams.mode.getValue().equalsIgnoreCase("Walls"));
        this.Vg = this.register(new Value<Integer>("Visible Green", this, 0, 0, 255)).visibleWhen(lIlIIlIIllIIII -> Chams.mode.getValue().equalsIgnoreCase("Walls"));
        this.Vb = this.register(new Value<Integer>("Visible Blue", this, 0, 0, 255)).visibleWhen(lIlIIlIIllIIIl -> Chams.mode.getValue().equalsIgnoreCase("Walls"));
        this.Wr = this.register(new Value<Integer>("Wall Red", this, 0, 0, 255)).visibleWhen(lIlIIlIIllIIlI -> Chams.mode.getValue().equalsIgnoreCase("Walls"));
        this.Wg = this.register(new Value<Integer>("Wall Green", this, 255, 0, 255)).visibleWhen(lIlIIlIIllIIll -> Chams.mode.getValue().equalsIgnoreCase("Walls"));
        this.Wb = this.register(new Value<Integer>("Wall Blue", this, 0, 0, 255)).visibleWhen(lIlIIlIIllIlII -> Chams.mode.getValue().equalsIgnoreCase("Walls"));
        this.hand = this.register(new Value<Boolean>("Hand", this, true)).visibleWhen(lIlIIlIIllIlIl -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        this.lines = this.register(new Value<Boolean>("Lines", this, false)).visibleWhen(lIlIIlIIllIllI -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        this.width = this.register(new Value<Float>("Width", this, 1.0f, 0.0f, 10.0f)).visibleWhen(lIlIIlIIllIlll -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        this.friendColor = this.register(new Value<Boolean>("Friends", this, true)).visibleWhen(lIlIIlIIlllIII -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false)).visibleWhen(lIlIIlIIlllIIl -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        this.r = this.register(new Value<Integer>("Red", this, 0, 0, 255)).visibleWhen(lIlIIlIIlllIlI -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        this.g = this.register(new Value<Integer>("Green", this, 255, 0, 255)).visibleWhen(lIlIIlIIlllIll -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        this.b = this.register(new Value<Integer>("Blue", this, 255, 0, 255)).visibleWhen(lIlIIlIIllllII -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        this.a = this.register(new Value<Integer>("Alpha", this, 63, 0, 255)).visibleWhen(lIlIIlIIllllIl -> Chams.mode.getValue().equalsIgnoreCase("ESP"));
        Chams.players = this.register(new Value<Boolean>("Players", this, true));
        Chams.animals = this.register(new Value<Boolean>("Animals", this, false));
        Chams.mobs = this.register(new Value<Boolean>("Mobs", this, false));
        Chams.crystals = this.register(new Value<Boolean>("Crystals", this, true)).visibleWhen(lIlIIlIIlllllI -> Chams.mode.getValue().equalsIgnoreCase("ESP") || Chams.mode.getValue().equalsIgnoreCase("Walls"));
    }
    
    @EventTarget
    public void renderPre(final EventModelRender lIlIIlIlIlIlII) {
        if (Chams.mode.getValue().equalsIgnoreCase("Walls")) {
            if (lIlIIlIlIlIlII.entity instanceof EntityOtherPlayerMP && !Chams.players.getValue()) {
                return;
            }
            if (EntityUtil.isPassive(lIlIIlIlIlIlII.entity) && !Chams.animals.getValue()) {
                return;
            }
            if (!EntityUtil.isPassive(lIlIIlIlIlIlII.entity) && !Chams.mobs.getValue()) {
                return;
            }
            GlStateManager.pushMatrix();
            GL11.glDisable(2929);
            GL11.glColor4f(this.Wr.getValue() / 255.0f, this.Wg.getValue() / 255.0f, this.Wb.getValue() / 255.0f, 1.0f);
            GL11.glDisable(3553);
            lIlIIlIlIlIlII.modelBase.render(lIlIIlIlIlIlII.entity, lIlIIlIlIlIlII.limbSwing, lIlIIlIlIlIlII.limbSwingAmount, lIlIIlIlIlIlII.ageInTicks, lIlIIlIlIlIlII.netHeadYaw, lIlIIlIlIlIlII.headPitch, lIlIIlIlIlIlII.scaleFactor);
            GL11.glEnable(2929);
            GL11.glColor4f(this.Vr.getValue() / 255.0f, this.Vg.getValue() / 255.0f, this.Vb.getValue() / 255.0f, 1.0f);
            lIlIIlIlIlIlII.modelBase.render(lIlIIlIlIlIlII.entity, lIlIIlIlIlIlII.limbSwing, lIlIIlIlIlIlII.limbSwingAmount, lIlIIlIlIlIlII.ageInTicks, lIlIIlIlIlIlII.netHeadYaw, lIlIIlIlIlIlII.headPitch, lIlIIlIlIlIlII.scaleFactor);
            GL11.glEnable(3553);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.popMatrix();
            lIlIIlIlIlIlII.setCancelled(true);
        }
        else if (Chams.mode.getValue().equalsIgnoreCase("ESP")) {
            final Color lIlIIlIlIlIllI = (this.friendColor.getValue() && Friends.isFriend(lIlIIlIlIlIlII.entity.getName())) ? new Color(0.27f, 0.7f, 0.92f) : (this.rainbow.getValue() ? new Color(Xulu.rgb) : new Color(this.r.getValue(), this.g.getValue(), this.b.getValue()));
            if (lIlIIlIlIlIlII.getEventState() == Event.State.PRE && !(lIlIIlIlIlIlII.entity instanceof EntityOtherPlayerMP)) {
                if (EntityUtil.isPassive(lIlIIlIlIlIlII.entity) && Chams.animals.getValue()) {
                    GL11.glPushMatrix();
                    GL11.glEnable(32823);
                    GL11.glPolygonOffset(1.0f, -100000.0f);
                    GL11.glPushAttrib(1048575);
                    if (!this.lines.getValue()) {
                        GL11.glPolygonMode(1028, 6914);
                    }
                    else {
                        GL11.glPolygonMode(1028, 6913);
                    }
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glDisable(2929);
                    GL11.glEnable(2848);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glColor4f(lIlIIlIlIlIllI.getRed() / 255.0f, lIlIIlIlIlIllI.getGreen() / 255.0f, lIlIIlIlIlIllI.getBlue() / 255.0f, this.a.getValue() / 255.0f);
                    if (this.lines.getValue()) {
                        GL11.glLineWidth((float)this.width.getValue());
                    }
                    lIlIIlIlIlIlII.modelBase.render(lIlIIlIlIlIlII.entity, lIlIIlIlIlIlII.limbSwing, lIlIIlIlIlIlII.limbSwingAmount, lIlIIlIlIlIlII.ageInTicks, lIlIIlIlIlIlII.netHeadYaw, lIlIIlIlIlIlII.headPitch, lIlIIlIlIlIlII.scaleFactor);
                    GL11.glPopAttrib();
                    GL11.glPolygonOffset(1.0f, 100000.0f);
                    GL11.glDisable(32823);
                    GL11.glPopMatrix();
                    lIlIIlIlIlIlII.setCancelled(true);
                }
                else if (!EntityUtil.isPassive(lIlIIlIlIlIlII.entity) && Chams.mobs.getValue()) {
                    GL11.glPushMatrix();
                    GL11.glEnable(32823);
                    GL11.glPolygonOffset(1.0f, -100000.0f);
                    GL11.glPushAttrib(1048575);
                    if (!this.lines.getValue()) {
                        GL11.glPolygonMode(1028, 6914);
                    }
                    else {
                        GL11.glPolygonMode(1028, 6913);
                    }
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glDisable(2929);
                    GL11.glEnable(2848);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glColor4f(lIlIIlIlIlIllI.getRed() / 255.0f, lIlIIlIlIlIllI.getGreen() / 255.0f, lIlIIlIlIlIllI.getBlue() / 255.0f, this.a.getValue() / 255.0f);
                    if (this.lines.getValue()) {
                        GL11.glLineWidth((float)this.width.getValue());
                    }
                    lIlIIlIlIlIlII.modelBase.render(lIlIIlIlIlIlII.entity, lIlIIlIlIlIlII.limbSwing, lIlIIlIlIlIlII.limbSwingAmount, lIlIIlIlIlIlII.ageInTicks, lIlIIlIlIlIlII.netHeadYaw, lIlIIlIlIlIlII.headPitch, lIlIIlIlIlIlII.scaleFactor);
                    GL11.glPopAttrib();
                    GL11.glPolygonOffset(1.0f, 100000.0f);
                    GL11.glDisable(32823);
                    GL11.glPopMatrix();
                    lIlIIlIlIlIlII.setCancelled(true);
                }
            }
        }
    }
    
    @EventTarget
    public void onPlayerModel(final EventModelPlayerRender lIlIIlIlIIIIlI) {
        if (Chams.mode.getValue().equalsIgnoreCase("ESP") && Chams.players.getValue()) {
            final Color lIlIIlIlIIIlII = (this.friendColor.getValue() && Friends.isFriend(lIlIIlIlIIIIlI.entity.getName())) ? new Color(0.27f, 0.7f, 0.92f) : (this.rainbow.getValue() ? new Color(Xulu.rgb) : new Color(this.r.getValue(), this.g.getValue(), this.b.getValue()));
            switch (lIlIIlIlIIIIlI.getEventState()) {
                case PRE: {
                    GL11.glPushMatrix();
                    GL11.glEnable(32823);
                    GL11.glPolygonOffset(1.0f, -1.0E7f);
                    GL11.glPushAttrib(1048575);
                    if (!this.lines.getValue()) {
                        GL11.glPolygonMode(1028, 6914);
                    }
                    else {
                        GL11.glPolygonMode(1028, 6913);
                    }
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glDisable(2929);
                    GL11.glEnable(2848);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glColor4f(lIlIIlIlIIIlII.getRed() / 255.0f, lIlIIlIlIIIlII.getGreen() / 255.0f, lIlIIlIlIIIlII.getBlue() / 255.0f, this.a.getValue() / 255.0f / 2.0f);
                    if (this.lines.getValue()) {
                        GL11.glLineWidth((float)this.width.getValue());
                        break;
                    }
                    break;
                }
                case POST: {
                    GL11.glPopAttrib();
                    GL11.glPolygonOffset(1.0f, 1.0E7f);
                    GL11.glDisable(32823);
                    GL11.glPopMatrix();
                    break;
                }
            }
        }
    }
    
    @EventTarget
    public void renderPost(final EventPostRenderLayers lIlIIlIlIIlIll) {
        if (Chams.mode.getValue().equalsIgnoreCase("ESP")) {
            if (!lIlIIlIlIIlIll.renderer.bindEntityTexture((Entity)lIlIIlIlIIlIll.entity)) {
                return;
            }
            final Color lIlIIlIlIIllIl = (this.friendColor.getValue() && Friends.isFriend(lIlIIlIlIIlIll.entity.getName())) ? new Color(0.27f, 0.7f, 0.92f) : (this.rainbow.getValue() ? new Color(Xulu.rgb) : new Color(this.r.getValue(), this.g.getValue(), this.b.getValue()));
            if (lIlIIlIlIIlIll.getEventState() == Event.State.PRE && lIlIIlIlIIlIll.entity instanceof EntityOtherPlayerMP && Chams.players.getValue()) {
                GL11.glPushMatrix();
                GL11.glEnable(32823);
                GL11.glPolygonOffset(1.0f, -100000.0f);
                GL11.glPushAttrib(1048575);
                if (!this.lines.getValue()) {
                    GL11.glPolygonMode(1028, 6914);
                }
                else {
                    GL11.glPolygonMode(1028, 6913);
                }
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glDisable(2929);
                GL11.glEnable(2848);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glColor4f(lIlIIlIlIIllIl.getRed() / 255.0f, lIlIIlIlIIllIl.getGreen() / 255.0f, lIlIIlIlIIllIl.getBlue() / 255.0f, this.a.getValue() / 255.0f / 2.0f);
                if (this.lines.getValue()) {
                    GL11.glLineWidth((float)this.width.getValue());
                }
                lIlIIlIlIIlIll.modelBase.render((Entity)lIlIIlIlIIlIll.entity, lIlIIlIlIIlIll.limbSwing, lIlIIlIlIIlIll.limbSwingAmount, lIlIIlIlIIlIll.ageInTicks, lIlIIlIlIIlIll.netHeadYaw, lIlIIlIlIIlIll.headPitch, lIlIIlIlIIlIll.scaleIn);
                GL11.glPopAttrib();
                GL11.glPolygonOffset(1.0f, 100000.0f);
                GL11.glDisable(32823);
                GL11.glPopMatrix();
            }
        }
    }
}
