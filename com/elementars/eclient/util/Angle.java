package com.elementars.eclient.util;

import java.util.*;
import net.minecraft.util.math.*;

public abstract class Angle
{
    private final /* synthetic */ float yaw;
    private final /* synthetic */ float roll;
    private final /* synthetic */ float pitch;
    
    public Angle setYaw(final float llllllIlIllllII) {
        return this.newInstance(this.getPitch(), llllllIlIllllII, this.getRoll());
    }
    
    public Angle sub(final Angle llllllIlIIlIlII) {
        return this.add(llllllIlIIlIlII.scale(-1.0f));
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public static Angle radians(final double lllllllIIIIIIIl, final double lllllllIIIIIIII) {
        return radians(lllllllIIIIIIIl, lllllllIIIIIIII, 0.0);
    }
    
    public abstract Angle inRadians();
    
    public float[] toArray() {
        return new float[] { this.getPitch(), this.getYaw(), this.getRoll() };
    }
    
    public Angle sub(final float llllllIlIIIIIIl, final float llllllIIlllllIl) {
        return this.sub(llllllIlIIIIIIl, llllllIIlllllIl, 0.0f);
    }
    
    protected Angle same(final Angle llllllIIlIlIIII) {
        return llllllIIlIlIIII.isInDegrees() ? this.inDegrees() : this.inRadians();
    }
    
    public Angle scale(final float llllllIIllllIIl) {
        return this.newInstance(this.getPitch() * llllllIIllllIIl, this.getYaw() * llllllIIllllIIl, this.getRoll() * llllllIIllllIIl);
    }
    
    public static Angle radians(final float lllllllIIIlIlIl, final float lllllllIIIlIlll, final float lllllllIIIlIllI) {
        return new Radians(lllllllIIIlIlIl, lllllllIIIlIlll, lllllllIIIlIllI);
    }
    
    public boolean isInRadians() {
        return !this.isInDegrees();
    }
    
    public abstract boolean isInDegrees();
    
    static {
        ZERO = degrees(0.0f, 0.0f, 0.0f);
    }
    
    @Override
    public int hashCode() {
        final Angle llllllIIlIIIllI = this.normalize().inDegrees();
        return Objects.hash(llllllIIlIIIllI.getPitch(), llllllIIlIIIllI.getYaw(), llllllIIlIIIllI.getRoll());
    }
    
    public Angle add(final Angle llllllIlIlIllIl) {
        return this.newInstance(this.getPitch() + llllllIlIlIllIl.same(this).getPitch(), this.getYaw() + llllllIlIlIllIl.same(this).getYaw(), this.getRoll() + llllllIlIlIllIl.same(this).getRoll());
    }
    
    public static Angle radians(final float lllllllIIIIlllI, final float lllllllIIIIllll) {
        return radians(lllllllIIIIlllI, lllllllIIIIllll, 0.0f);
    }
    
    @Override
    public String toString() {
        return String.format("(%.15f, %.15f, %.15f)[%s]", this.getPitch(), this.getYaw(), this.getRoll(), this.isInRadians() ? "rad" : "deg");
    }
    
    public Angle setPitch(final float llllllIllIIIIlI) {
        return this.newInstance(llllllIllIIIIlI, this.getYaw(), this.getRoll());
    }
    
    public static Angle degrees(final float llllllIllllIlll, final float llllllIllllIllI, final float llllllIllllIlIl) {
        return new Degrees(llllllIllllIlll, llllllIllllIllI, llllllIllllIlIl);
    }
    
    public Angle setRoll(final float llllllIlIlllIII) {
        return this.newInstance(this.getPitch(), this.getYaw(), llllllIlIlllIII);
    }
    
    @Override
    public boolean equals(final Object llllllIIlIIllII) {
        return this == llllllIIlIIllII || (llllllIIlIIllII instanceof Angle && AngleHelper.isEqual(this, (Angle)llllllIIlIIllII));
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getRoll() {
        return this.roll;
    }
    
    public static Angle copy(final Angle llllllIllIlllIl) {
        return llllllIllIlllIl.newInstance(llllllIllIlllIl.getPitch(), llllllIllIlllIl.getYaw(), llllllIllIlllIl.getRoll());
    }
    
    public Vec3d getDirectionVector() {
        final float llllllIIllIIIIl = MathHelper.cos(-this.inDegrees().getYaw() * 0.017453292f - 3.1415927f);
        final float llllllIIllIIIII = MathHelper.sin(-this.inDegrees().getYaw() * 0.017453292f - 3.1415927f);
        final float llllllIIlIlllll = -MathHelper.cos(-this.inDegrees().getPitch() * 0.017453292f);
        final float llllllIIlIllllI = MathHelper.sin(-this.inDegrees().getPitch() * 0.017453292f);
        return new Vec3d((double)(llllllIIllIIIII * llllllIIlIlllll), (double)llllllIIlIllllI, (double)(llllllIIllIIIIl * llllllIIlIlllll));
    }
    
    private Angle(final float llllllIllIlIlll, final float llllllIllIlIllI, final float llllllIllIlIIIl) {
        this.pitch = llllllIllIlIlll;
        this.yaw = llllllIllIlIllI;
        this.roll = llllllIllIlIIIl;
    }
    
    public abstract Angle normalize();
    
    public Angle add(final float llllllIlIIllIIl, final float llllllIlIIllIll) {
        return this.add(llllllIlIIllIIl, llllllIlIIllIll, 0.0f);
    }
    
    public double[] getForwardVector() {
        final double llllllIIlllIIII = Math.sin(this.inRadians().getPitch());
        final double llllllIIllIllll = Math.cos(this.inRadians().getPitch());
        final double llllllIIllIlllI = Math.sin(this.inRadians().getYaw());
        final double llllllIIllIllIl = Math.cos(this.inRadians().getYaw());
        return new double[] { llllllIIllIllll * llllllIIllIllIl, llllllIIlllIIII, llllllIIllIllll * llllllIIllIlllI };
    }
    
    public static Angle degrees(final double llllllIlllIlIll, final double llllllIlllIIlll, final double llllllIlllIIllI) {
        return degrees((float)AngleHelper.roundAngle(llllllIlllIlIll), (float)AngleHelper.roundAngle(llllllIlllIIlll), (float)AngleHelper.roundAngle(llllllIlllIIllI));
    }
    
    public static Angle degrees(final float llllllIllllIIII, final float llllllIllllIIIl) {
        return degrees(llllllIllllIIII, llllllIllllIIIl, 0.0f);
    }
    
    public Angle add(final float llllllIlIlIIIll, final float llllllIlIlIIIlI, final float llllllIlIlIIlIl) {
        return this.add(this.newInstance(llllllIlIlIIIll, llllllIlIlIIIlI, llllllIlIlIIlIl));
    }
    
    public static Angle degrees(final double llllllIlllIIIll, final double llllllIlllIIIlI) {
        return degrees(llllllIlllIIIll, llllllIlllIIIlI, 0.0);
    }
    
    public static Angle radians(final double lllllllIIIIlIIl, final double lllllllIIIIIlIl, final double lllllllIIIIIlII) {
        return radians((float)AngleHelper.roundAngle(lllllllIIIIlIIl), (float)AngleHelper.roundAngle(lllllllIIIIIlIl), (float)AngleHelper.roundAngle(lllllllIIIIIlII));
    }
    
    public abstract Angle inDegrees();
    
    public Angle sub(final float llllllIlIIIllII, final float llllllIlIIIlIll, final float llllllIlIIIIllI) {
        return this.add(-llllllIlIIIllII, -llllllIlIIIlIll, -llllllIlIIIIllI);
    }
    
    protected abstract Angle newInstance(final float p0, final float p1, final float p2);
    
    static class Radians extends Angle
    {
        private /* synthetic */ Degrees degrees;
        
        @Override
        public boolean isInDegrees() {
            return false;
        }
        
        @Override
        protected Angle newInstance(final float lIIIIllllIIlII, final float lIIIIllllIIIll, final float lIIIIllllIIIlI) {
            return new Radians(lIIIIllllIIlII, lIIIIllllIIIll, lIIIIllllIIIlI);
        }
        
        private Radians(final float lIIIIlllllIlIl, final float lIIIIllllllIII, final float lIIIIlllllIIll) {
            super(lIIIIlllllIlIl, lIIIIllllllIII, lIIIIlllllIIll, null);
            this.degrees = null;
        }
        
        @Override
        public Angle inRadians() {
            return this;
        }
        
        @Override
        public Angle inDegrees() {
            return (this.degrees == null) ? (this.degrees = (Degrees)Angle.degrees(Math.toDegrees(this.getPitch()), Math.toDegrees(this.getYaw()), Math.toDegrees(this.getRoll()))) : this.degrees;
        }
        
        @Override
        public Angle normalize() {
            return this.newInstance(AngleHelper.normalizeInRadians(this.getPitch()), AngleHelper.normalizeInRadians(this.getYaw()), AngleHelper.normalizeInRadians(this.getRoll()));
        }
    }
    
    static class Degrees extends Angle
    {
        private /* synthetic */ Radians radians;
        
        @Override
        public Angle inDegrees() {
            return this;
        }
        
        @Override
        public Angle inRadians() {
            return (this.radians == null) ? (this.radians = (Radians)Angle.radians(Math.toRadians(this.getPitch()), Math.toRadians(this.getYaw()), Math.toRadians(this.getRoll()))) : this.radians;
        }
        
        @Override
        protected Angle newInstance(final float llllllllllllllllllIIllIIlIllllII, final float llllllllllllllllllIIllIIlIlllIll, final float llllllllllllllllllIIllIIlIlllIlI) {
            return new Degrees(llllllllllllllllllIIllIIlIllllII, llllllllllllllllllIIllIIlIlllIll, llllllllllllllllllIIllIIlIlllIlI);
        }
        
        private Degrees(final float llllllllllllllllllIIllIIllIlIIII, final float llllllllllllllllllIIllIIllIIllll, final float llllllllllllllllllIIllIIllIlIIlI) {
            super(llllllllllllllllllIIllIIllIlIIII, llllllllllllllllllIIllIIllIIllll, llllllllllllllllllIIllIIllIlIIlI, null);
            this.radians = null;
        }
        
        @Override
        public Angle normalize() {
            return this.newInstance(AngleHelper.normalizeInDegrees(this.getPitch()), AngleHelper.normalizeInDegrees(this.getYaw()), AngleHelper.normalizeInDegrees(this.getRoll()));
        }
        
        @Override
        public boolean isInDegrees() {
            return true;
        }
    }
}
