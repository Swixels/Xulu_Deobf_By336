package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.util.*;
import java.util.function.*;
import java.awt.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.*;
import net.minecraft.entity.player.*;

public class Arrows extends Module
{
    public final /* synthetic */ Value<Integer> Fb;
    public final /* synthetic */ Value<Mode> mode;
    public final /* synthetic */ Value<Integer> green;
    public final /* synthetic */ Value<Integer> Fg;
    public final /* synthetic */ Value<Boolean> hostile;
    public final /* synthetic */ Value<Boolean> black;
    public final /* synthetic */ Value<Integer> blue;
    public final /* synthetic */ Value<String> colorMode;
    public final /* synthetic */ Value<Integer> sizeY;
    public final /* synthetic */ Value<Boolean> friendly;
    public final /* synthetic */ Value<Integer> sizeX;
    public final /* synthetic */ Value<Integer> red;
    public final /* synthetic */ Value<Integer> alpha;
    public final /* synthetic */ Value<Boolean> players;
    public final /* synthetic */ Value<Float> sizeT;
    public final /* synthetic */ Value<Boolean> antialias;
    public final /* synthetic */ Value<Boolean> rainbow;
    public final /* synthetic */ Value<Boolean> outline;
    public final /* synthetic */ Value<Integer> Fr;
    
    @Override
    public void onRender() {
        final ScaledResolution lIIIIlIIIlIllII = new ScaledResolution(Arrows.mc);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableTexture2D();
        GlStateManager.enableAlpha();
        if (this.antialias.getValue()) {
            GL11.glEnable(2881);
            GL11.glEnable(2848);
        }
        final Mode lIIIIlIIIlIlIll = this.mode.getValue();
        final double lIIIIlIIIlIlIlI = lIIIIlIIIlIllII.getScaledWidth_double() / 2.0;
        final double lIIIIlIIIlIlIIl = lIIIIlIIIlIllII.getScaledHeight_double() / 2.0;
        final Entity lIIIIIlllIIIlll;
        final RelationState lIIIIIlllIIIllI;
        final Vec3d lIIIIIlllIIIlIl;
        final Plane lIIIIIlllIIIlII;
        final Color lIIIIIlllIIIIll;
        float lIIIIIllllIIlll;
        Color lIIIIIllllIlIIl;
        Color lIIIIIllllIlIII;
        final Enum enum1;
        final double n;
        double lIIIIIllllIIIlI;
        final double n2;
        double lIIIIIllllIIIIl;
        double lIIIIIllllIIIII;
        double lIIIIIlllIlllll;
        double lIIIIIlllIlllIl;
        double lIIIIIlllIlllII;
        double lIIIIIlllIllIll;
        double lIIIIIlllIllIlI;
        double lIIIIIlllIllIIl;
        double lIIIIIlllIllIII;
        double lIIIIIlllIlIlll;
        final ScaledResolution scaledResolution;
        double lIIIIIlllIlIllI;
        double lIIIIIlllIlIlIl;
        double lIIIIIlllIlIlII;
        double lIIIIIlllIlIIll;
        double lIIIIIlllIlIIIl;
        double lIIIIIlllIlIIIl2;
        int lIIIIIlllIlIIII;
        float lIIIIIllllIIlII;
        Color lIIIIIllllIIllI;
        Color lIIIIIllllIIlIl;
        Arrows.mc.world.loadedEntityList.stream().filter(lIIIIIllIIlIIll -> !Objects.equals(lIIIIIllIIlIIll, Arrows.mc.player)).filter(EntityLivingBase.class::isInstance).map(lIIIIIllIIlIlll -> new EntityRelations(lIIIIIllIIlIlll)).filter(lIIIIIllIIllIll -> !lIIIIIllIIllIll.getRelationship().equals(RelationState.INVALID)).filter(EntityRelations::isOptionEnabled).forEach(lIIIIIllIllllIl -> {
            lIIIIIlllIIIlll = lIIIIIllIllllIl.getEntity();
            lIIIIIlllIIIllI = lIIIIIllIllllIl.getRelationship();
            lIIIIIlllIIIlIl = EntityUtil.getInterpolatedEyePos(lIIIIIlllIIIlll, Arrows.mc.getRenderPartialTicks());
            lIIIIIlllIIIlII = VectorUtils.toScreen(lIIIIIlllIIIlIl);
            lIIIIIlllIIIIll = lIIIIIllIllllIl.getColor();
            if (this.colorMode.getValue().equalsIgnoreCase("Tracers")) {
                lIIIIIllllIIlll = Arrows.mc.player.getDistance(lIIIIIlllIIIlll);
                if (lIIIIIllllIIlll <= 32.0f) {
                    lIIIIIllllIlIIl = new Color(1.0f - lIIIIIllllIIlll / 32.0f / 2.0f, lIIIIIllllIIlll / 32.0f, 0.0f);
                    GL11.glColor4f(lIIIIIllllIlIIl.getRed() / 255.0f, lIIIIIllllIlIIl.getGreen() / 255.0f, lIIIIIllllIlIIl.getBlue() / 255.0f, this.alpha.getValue() / 255.0f);
                }
                else {
                    lIIIIIllllIlIII = new Color(0.0f, 0.9f, 0.0f);
                    GL11.glColor4f(lIIIIIllllIlIII.getRed() / 255.0f, lIIIIIllllIlIII.getGreen() / 255.0f, lIIIIIllllIlIII.getBlue() / 255.0f, this.alpha.getValue() / 255.0f);
                }
            }
            else {
                GL11.glColor4f(lIIIIIlllIIIIll.getRed() / 255.0f, lIIIIIlllIIIIll.getGreen() / 255.0f, lIIIIIlllIIIIll.getBlue() / 255.0f, this.alpha.getValue() / 255.0f);
            }
            GlStateManager.translate(0.0f, 0.0f, lIIIIIllIllllIl.getDepth());
            if ((enum1.equals(Mode.BOTH) || enum1.equals(Mode.ARROWS)) && !lIIIIIlllIIIlII.isVisible()) {
                lIIIIIllllIIIlI = n - this.sizeX.getValue();
                lIIIIIllllIIIIl = n2 - this.sizeY.getValue();
                lIIIIIllllIIIII = (lIIIIIlllIIIlII.getX() - n) / lIIIIIllllIIIlI;
                lIIIIIlllIlllll = (lIIIIIlllIIIlII.getY() - n2) / lIIIIIllllIIIIl;
                lIIIIIlllIlllIl = Math.abs(Math.sqrt(lIIIIIllllIIIII * lIIIIIllllIIIII + lIIIIIlllIlllll * lIIIIIlllIlllll));
                lIIIIIlllIlllII = lIIIIIllllIIIII / lIIIIIlllIlllIl;
                lIIIIIlllIllIll = lIIIIIlllIlllll / lIIIIIlllIlllIl;
                lIIIIIlllIllIlI = n + lIIIIIlllIlllII * lIIIIIllllIIIlI;
                lIIIIIlllIllIIl = n2 + lIIIIIlllIllIll * lIIIIIllllIIIIl;
                lIIIIIlllIllIII = lIIIIIlllIllIlI - n;
                lIIIIIlllIlIlll = lIIIIIlllIllIIl - n2;
                lIIIIIlllIlIllI = scaledResolution.getScaledWidth_double();
                lIIIIIlllIlIlIl = 0.0;
                lIIIIIlllIlIlII = Math.sqrt(lIIIIIlllIlIllI * lIIIIIlllIlIllI + lIIIIIlllIlIlIl * lIIIIIlllIlIlIl);
                lIIIIIlllIlIIll = Math.sqrt(lIIIIIlllIllIII * lIIIIIlllIllIII + lIIIIIlllIlIlll * lIIIIIlllIlIlll);
                lIIIIIlllIlIIIl = Math.toDegrees(Math.acos((lIIIIIlllIlIllI * lIIIIIlllIllIII + lIIIIIlllIlIlIl * lIIIIIlllIlIlll) / (lIIIIIlllIlIlII * lIIIIIlllIlIIll)));
                if (lIIIIIlllIlIIIl == Double.NaN) {
                    lIIIIIlllIlIIIl = 0.0;
                }
                if (lIIIIIlllIllIIl < n2) {
                    lIIIIIlllIlIIIl *= -1.0;
                }
                lIIIIIlllIlIIIl2 = (float)AngleHelper.normalizeInDegrees(lIIIIIlllIlIIIl);
                lIIIIIlllIlIIII = (lIIIIIlllIIIllI.equals(RelationState.PLAYER) ? 8 : 5);
                GlStateManager.pushMatrix();
                GlStateManager.translate(lIIIIIlllIllIlI, lIIIIIlllIllIIl, 0.0);
                GlStateManager.rotate((float)lIIIIIlllIlIIIl2, 0.0f, 0.0f, lIIIIIlllIlIIII / 2.0f);
                if (this.colorMode.getValue().equalsIgnoreCase("Tracers")) {
                    lIIIIIllllIIlII = Arrows.mc.player.getDistance(lIIIIIlllIIIlll);
                    if (lIIIIIllllIIlII <= 32.0f) {
                        lIIIIIllllIIllI = new Color(1.0f - lIIIIIllllIIlII / 32.0f / 2.0f, lIIIIIllllIIlII / 32.0f, 0.0f);
                        GL11.glColor4f(lIIIIIllllIIllI.getRed() / 255.0f, lIIIIIllllIIllI.getGreen() / 255.0f, lIIIIIllllIIllI.getBlue() / 255.0f, this.alpha.getValue() / 255.0f);
                    }
                    else {
                        lIIIIIllllIIlIl = new Color(0.0f, 0.9f, 0.0f);
                        GL11.glColor4f(lIIIIIllllIIlIl.getRed() / 255.0f, lIIIIIllllIIlIl.getGreen() / 255.0f, lIIIIIllllIIlIl.getBlue() / 255.0f, this.alpha.getValue() / 255.0f);
                    }
                }
                else {
                    GL11.glColor4f(lIIIIIlllIIIIll.getRed() / 255.0f, lIIIIIlllIIIIll.getGreen() / 255.0f, lIIIIIlllIIIIll.getBlue() / 255.0f, this.alpha.getValue() / 255.0f);
                }
                GlStateManager.glBegin(4);
                GL11.glVertex2d(0.0, 0.0);
                GL11.glVertex2d((double)(-lIIIIIlllIlIIII), (double)(-lIIIIIlllIlIIII / this.sizeT.getValue()));
                GL11.glVertex2d((double)(-lIIIIIlllIlIIII), (double)(lIIIIIlllIlIIII / this.sizeT.getValue()));
                GlStateManager.glEnd();
                if (this.outline.getValue()) {
                    GL11.glPushAttrib(1048575);
                    GL11.glPolygonMode(1032, 6913);
                    GL11.glLineWidth(1.5f);
                    if (this.black.getValue()) {
                        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
                    }
                    GlStateManager.glBegin(4);
                    GL11.glVertex2d(0.0, 0.0);
                    GL11.glVertex2d((double)(-lIIIIIlllIlIIII), (double)(-lIIIIIlllIlIIII / this.sizeT.getValue()));
                    GL11.glVertex2d((double)(-lIIIIIlllIlIIII), (double)(lIIIIIlllIlIIII / this.sizeT.getValue()));
                    GlStateManager.glEnd();
                    GL11.glPopAttrib();
                }
                GlStateManager.popMatrix();
            }
            if (enum1.equals(Mode.BOTH) || enum1.equals(Mode.LINES)) {
                GlStateManager.glBegin(1);
                GL11.glVertex2d(n, n2);
                GL11.glVertex2d(lIIIIIlllIIIlII.getX(), lIIIIIlllIIIlII.getY());
                GlStateManager.glEnd();
            }
            GlStateManager.translate(0.0f, 0.0f, -lIIIIIllIllllIl.getDepth());
            return;
        });
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glDisable(2881);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public Arrows() {
        super("Arrows", "2d Tracers", 0, Category.RENDER, true);
        this.mode = this.register(new Value<Mode>("Mode", this, Mode.ARROWS, Mode.values()));
        this.sizeX = this.register(new Value<Integer>("Dimension X", this, 450, 0, 1000));
        this.sizeY = this.register(new Value<Integer>("Dimension Y", this, 285, 0, 1000));
        this.sizeT = this.register(new Value<Float>("Triangle Size", this, 2.0f, 1.0f, 5.0f));
        this.rainbow = this.register(new Value<Boolean>("Rainbow Players", this, false));
        this.colorMode = this.register(new Value<String>("Color Mode", this, "RGB", new String[] { "RGB", "Tracers" }));
        this.red = this.register(new Value<Integer>("Player Red", this, 0, 0, 255)).visibleWhen(lIIIIIlIlllIIIl -> this.colorMode.getValue().equalsIgnoreCase("RGB"));
        this.green = this.register(new Value<Integer>("Player Green", this, 255, 0, 255)).visibleWhen(lIIIIIlIlllIlIl -> this.colorMode.getValue().equalsIgnoreCase("RGB"));
        this.blue = this.register(new Value<Integer>("Player Blue", this, 0, 0, 255)).visibleWhen(lIIIIIlIllllIll -> this.colorMode.getValue().equalsIgnoreCase("RGB"));
        this.Fr = this.register(new Value<Integer>("Friend Red", this, 0, 0, 255));
        this.Fg = this.register(new Value<Integer>("Friend Green", this, 200, 0, 255));
        this.Fb = this.register(new Value<Integer>("Friend Blue", this, 255, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 255, 0, 255));
        this.outline = this.register(new Value<Boolean>("Outline", this, false));
        this.black = this.register(new Value<Boolean>("Black Outline", this, true)).visibleWhen(lIIIIIllIIIlIIl -> this.outline.getValue());
        this.antialias = this.register(new Value<Boolean>("Antialias", this, true));
        this.players = this.register(new Value<Boolean>("Players", this, true));
        this.hostile = this.register(new Value<Boolean>("Mobs", this, true));
        this.friendly = this.register(new Value<Boolean>("Animals", this, true));
    }
    
    enum Mode
    {
        LINES, 
        BOTH, 
        ARROWS;
    }
    
    private class EntityRelations implements Comparable<EntityRelations>
    {
        private final /* synthetic */ Entity entity;
        private final /* synthetic */ RelationState relationship;
        
        public float getDepth() {
            switch (this.relationship) {
                case PLAYER: {
                    return 15.0f;
                }
                case HOSTILE: {
                    return 10.0f;
                }
                default: {
                    return 0.0f;
                }
            }
        }
        
        public Color getColor() {
            switch (this.relationship) {
                case PLAYER: {
                    if (Friends.isFriend(this.getEntity().getName())) {
                        return new Color(Arrows.this.Fr.getValue(), Arrows.this.Fg.getValue(), Arrows.this.Fb.getValue());
                    }
                    if (Arrows.this.rainbow.getValue()) {
                        return new Color(Xulu.rgb);
                    }
                    return new Color(Arrows.this.red.getValue(), Arrows.this.green.getValue(), Arrows.this.blue.getValue());
                }
                case HOSTILE: {
                    return Color.RED;
                }
                default: {
                    return Color.YELLOW;
                }
            }
        }
        
        @Override
        public int compareTo(final EntityRelations llllllllllllllllllllIllIlIIIIIlI) {
            return this.getRelationship().compareTo(llllllllllllllllllllIllIlIIIIIlI.getRelationship());
        }
        
        public Entity getEntity() {
            return this.entity;
        }
        
        public RelationState getRelationship() {
            return this.relationship;
        }
        
        public boolean isOptionEnabled() {
            switch (this.relationship) {
                case PLAYER: {
                    return Arrows.this.players.getValue();
                }
                case HOSTILE: {
                    return Arrows.this.hostile.getValue();
                }
                default: {
                    return Arrows.this.friendly.getValue();
                }
            }
        }
        
        public EntityRelations(final Entity llllllllllllllllllllIllIlIIlIlIl) {
            Objects.requireNonNull(llllllllllllllllllllIllIlIIlIlIl);
            this.entity = llllllllllllllllllllIllIlIIlIlIl;
            if (EntityUtil.isFakeLocalPlayer(llllllllllllllllllllIllIlIIlIlIl)) {
                this.relationship = RelationState.INVALID;
            }
            else if (llllllllllllllllllllIllIlIIlIlIl instanceof EntityPlayer) {
                this.relationship = RelationState.PLAYER;
            }
            else if (EntityUtil.isPassive(llllllllllllllllllllIllIlIIlIlIl)) {
                this.relationship = RelationState.FRIENDLY;
            }
            else {
                this.relationship = RelationState.HOSTILE;
            }
        }
    }
    
    public enum RelationState
    {
        INVALID, 
        HOSTILE, 
        FRIENDLY, 
        PLAYER;
    }
}
