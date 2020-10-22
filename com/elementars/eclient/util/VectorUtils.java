package com.elementars.eclient.util;

import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.util.vector.*;
import net.minecraft.client.gui.*;
import net.minecraft.entity.*;

public class VectorUtils implements Helper
{
    static /* synthetic */ Matrix4f modelMatrix;
    static /* synthetic */ Matrix4f projectionMatrix;
    
    @Deprecated
    public static ScreenPos _toScreen(final double llIlIlllIIIIlI, final double llIlIlllIIIlIl, final double llIlIlllIIIIII) {
        final Plane llIlIlllIIIIll = toScreen(llIlIlllIIIIlI, llIlIlllIIIlIl, llIlIlllIIIIII);
        return new ScreenPos(llIlIlllIIIIll.getX(), llIlIlllIIIIll.getY(), llIlIlllIIIIll.isVisible());
    }
    
    public static double getCrosshairDistance(final Vec3d llIlIllIlIlIll, final Vec3d llIlIllIlIlIlI, final Vec3d llIlIllIlIllII) {
        return llIlIllIlIllII.subtract(llIlIllIlIlIll).normalize().subtract(llIlIllIlIlIlI).lengthSquared();
    }
    
    public static Plane toScreen(final double llIlIlllIllIll, final double llIlIlllIllIlI, final double llIlIlllIllIIl) {
        final Entity llIlIllllIIllI = VectorUtils.mc.getRenderViewEntity();
        if (llIlIllllIIllI == null) {
            return new Plane(0.0, 0.0, false);
        }
        final Vec3d llIlIllllIIlIl = ActiveRenderInfo.position;
        final Vec3d llIlIllllIIlII = ActiveRenderInfo.projectViewFromEntity(llIlIllllIIllI, (double)VectorUtils.mc.getRenderPartialTicks());
        final float llIlIllllIIIll = (float)(llIlIllllIIlIl.x + llIlIllllIIlII.x - (float)llIlIlllIllIll);
        final float llIlIllllIIIlI = (float)(llIlIllllIIlIl.y + llIlIllllIIlII.y - (float)llIlIlllIllIlI);
        final float llIlIllllIIIIl = (float)(llIlIllllIIlIl.z + llIlIllllIIlII.z - (float)llIlIlllIllIIl);
        final Vector4f llIlIllllIIIII = new Vector4f(llIlIllllIIIll, llIlIllllIIIlI, llIlIllllIIIIl, 1.0f);
        VectorUtils.modelMatrix.load(ActiveRenderInfo.MODELVIEW.asReadOnlyBuffer());
        VectorUtils.projectionMatrix.load(ActiveRenderInfo.PROJECTION.asReadOnlyBuffer());
        VecTransformCoordinate(llIlIllllIIIII, VectorUtils.modelMatrix);
        VecTransformCoordinate(llIlIllllIIIII, VectorUtils.projectionMatrix);
        if (llIlIllllIIIII.w > 0.0f) {
            final Vector4f vector4f = llIlIllllIIIII;
            vector4f.x *= -100000.0f;
            final Vector4f vector4f2 = llIlIllllIIIII;
            vector4f2.y *= -100000.0f;
        }
        else {
            final float llIlIllllIlIlI = 1.0f / llIlIllllIIIII.w;
            final Vector4f vector4f3 = llIlIllllIIIII;
            vector4f3.x *= llIlIllllIlIlI;
            final Vector4f vector4f4 = llIlIllllIIIII;
            vector4f4.y *= llIlIllllIlIlI;
        }
        final ScaledResolution llIlIlllIlllll = new ScaledResolution(VectorUtils.mc);
        final float llIlIlllIllllI = llIlIlllIlllll.getScaledWidth() / 2.0f;
        final float llIlIlllIlllIl = llIlIlllIlllll.getScaledHeight() / 2.0f;
        llIlIllllIIIII.x = llIlIlllIllllI + (0.5f * llIlIllllIIIII.x * llIlIlllIlllll.getScaledWidth() + 0.5f);
        llIlIllllIIIII.y = llIlIlllIlllIl - (0.5f * llIlIllllIIIII.y * llIlIlllIlllll.getScaledHeight() + 0.5f);
        boolean llIlIlllIlllII = true;
        if (llIlIllllIIIII.x < 0.0f || llIlIllllIIIII.y < 0.0f || llIlIllllIIIII.x > llIlIlllIlllll.getScaledWidth() || llIlIllllIIIII.y > llIlIlllIlllll.getScaledHeight()) {
            llIlIlllIlllII = false;
        }
        return new Plane(llIlIllllIIIII.x, llIlIllllIIIII.y, llIlIlllIlllII);
    }
    
    public static Vec3d copy(final Vec3d llIlIllIllIIll) {
        return new Vec3d(llIlIllIllIIll.x, llIlIllIllIIll.y, llIlIllIllIIll.z);
    }
    
    static {
        VectorUtils.modelMatrix = new Matrix4f();
        VectorUtils.projectionMatrix = new Matrix4f();
    }
    
    public static Vec3d multiplyBy(final Vec3d llIlIllIlllIII, final Vec3d llIlIllIllIlIl) {
        return new Vec3d(llIlIllIlllIII.x * llIlIllIllIlIl.x, llIlIllIlllIII.y * llIlIllIllIlIl.y, llIlIllIlllIII.z * llIlIllIllIlIl.z);
    }
    
    @Deprecated
    public static ScreenPos _toScreen(final Vec3d llIlIllIllllII) {
        return _toScreen(llIlIllIllllII.x, llIlIllIllllII.y, llIlIllIllllII.z);
    }
    
    private static void VecTransformCoordinate(final Vector4f llIllIIIIIlIIl, final Matrix4f llIllIIIIIllIl) {
        final float llIllIIIIIllII = llIllIIIIIlIIl.x;
        final float llIllIIIIIlIll = llIllIIIIIlIIl.y;
        final float llIllIIIIIlIlI = llIllIIIIIlIIl.z;
        llIllIIIIIlIIl.x = llIllIIIIIllII * llIllIIIIIllIl.m00 + llIllIIIIIlIll * llIllIIIIIllIl.m10 + llIllIIIIIlIlI * llIllIIIIIllIl.m20 + llIllIIIIIllIl.m30;
        llIllIIIIIlIIl.y = llIllIIIIIllII * llIllIIIIIllIl.m01 + llIllIIIIIlIll * llIllIIIIIllIl.m11 + llIllIIIIIlIlI * llIllIIIIIllIl.m21 + llIllIIIIIllIl.m31;
        llIllIIIIIlIIl.z = llIllIIIIIllII * llIllIIIIIllIl.m02 + llIllIIIIIlIll * llIllIIIIIllIl.m12 + llIllIIIIIlIlI * llIllIIIIIllIl.m22 + llIllIIIIIllIl.m32;
        llIllIIIIIlIIl.w = llIllIIIIIllII * llIllIIIIIllIl.m03 + llIllIIIIIlIll * llIllIIIIIllIl.m13 + llIllIIIIIlIlI * llIllIIIIIllIl.m23 + llIllIIIIIllIl.m33;
    }
    
    public static Plane toScreen(final Vec3d llIlIlllIIlIll) {
        return toScreen(llIlIlllIIlIll.x, llIlIlllIIlIll.y, llIlIlllIIlIll.z);
    }
    
    @Deprecated
    public static Object vectorAngle(final Vec3d llIlIllIlllIll) {
        return null;
    }
    
    @Deprecated
    public static class ScreenPos
    {
        public final /* synthetic */ boolean isVisible;
        public final /* synthetic */ int x;
        public final /* synthetic */ double xD;
        public final /* synthetic */ double yD;
        public final /* synthetic */ int y;
        
        public ScreenPos(final double llllllllllllllllllIlIlIIIIIlIIIl, final double llllllllllllllllllIlIlIIIIIlIIII, final boolean llllllllllllllllllIlIlIIIIIlIIll) {
            this.x = (int)llllllllllllllllllIlIlIIIIIlIIIl;
            this.y = (int)llllllllllllllllllIlIlIIIIIlIIII;
            this.xD = llllllllllllllllllIlIlIIIIIlIIIl;
            this.yD = llllllllllllllllllIlIlIIIIIlIIII;
            this.isVisible = llllllllllllllllllIlIlIIIIIlIIll;
        }
    }
}
