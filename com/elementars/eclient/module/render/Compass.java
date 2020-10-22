package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;

public class Compass extends Module
{
    public final /* synthetic */ Value<Float> position;
    public final /* synthetic */ Value<Boolean> axis;
    public final /* synthetic */ Value<Integer> scale;
    public final /* synthetic */ Value<Integer> xposition;
    
    private double getX(final double llIlIllllIllIlI) {
        return Math.sin(llIlIllllIllIlI) * (this.scale.getValue() * 10);
    }
    
    private double getY(final double llIlIllllIlIlII) {
        final double llIlIllllIlIIll = MathHelper.clamp(Compass.mc.player.rotationPitch + 30.0f, -90.0f, 90.0f);
        final double llIlIllllIlIIlI = Math.toRadians(llIlIllllIlIIll);
        return Math.cos(llIlIllllIlIlII) * Math.sin(llIlIllllIlIIlI) * (this.scale.getValue() * 10);
    }
    
    @Override
    public void onRender() {
        GlStateManager.pushMatrix();
        final double llIlIlllllIlIIl = Compass.mc.displayWidth / 4 + this.xposition.getValue();
        final double llIlIlllllIlIII = Compass.mc.displayHeight / 2 * (this.position.getValue() / 10.0f);
        final float llIlIlllllIIlII = (Object)Direction.values();
        final byte llIlIlllllIIIll = (byte)llIlIlllllIIlII.length;
        for (String llIlIlllllIIIlI = (String)0; llIlIlllllIIIlI < llIlIlllllIIIll; ++llIlIlllllIIIlI) {
            final Direction llIlIlllllIlIll = llIlIlllllIIlII[llIlIlllllIIIlI];
            final double llIlIlllllIllII = getPosOnCompass(llIlIlllllIlIll);
            Compass.mc.fontRenderer.drawStringWithShadow(((boolean)this.axis.getValue()) ? llIlIlllllIlIll.getAlternate() : llIlIlllllIlIll.name(), (float)(llIlIlllllIlIIl + this.getX(llIlIlllllIllII)), (float)(llIlIlllllIlIII + this.getY(llIlIlllllIllII)), (llIlIlllllIlIll == Direction.N) ? ColorUtils.Colors.RED : ColorUtils.Colors.WHITE);
        }
        GlStateManager.popMatrix();
    }
    
    private static double getPosOnCompass(final Direction llIlIllllIIIlll) {
        final double llIlIllllIIlIIl = Math.toRadians(MathHelper.wrapDegrees(Compass.mc.player.rotationYaw));
        final int llIlIllllIIlIII = llIlIllllIIIlll.ordinal();
        return llIlIllllIIlIIl + llIlIllllIIlIII * 1.5707963267948966;
    }
    
    public Compass() {
        super("Compass", "Credit to fr1kin", 0, Category.RENDER, true);
        this.axis = this.register(new Value<Boolean>("Axis", this, false));
        this.scale = this.register(new Value<Integer>("Scale", this, 3, 1, 10));
        this.position = this.register(new Value<Float>("Y Position", this, 8.0f, 0.0f, 10.0f));
        this.xposition = this.register(new Value<Integer>("X Position", this, 0, -500, 500));
    }
    
    private enum Direction
    {
        private /* synthetic */ String alternate;
        
        E("+X"), 
        N("-Z"), 
        W("-X"), 
        S("+Z");
        
        private Direction(final String llllllllllllllllllIlIIlIlIIIIlII) {
            this.alternate = llllllllllllllllllIlIIlIlIIIIlII;
        }
        
        public String getAlternate() {
            return this.alternate;
        }
    }
}
