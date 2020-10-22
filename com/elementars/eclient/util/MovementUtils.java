package com.elementars.eclient.util;

import net.minecraft.client.*;
import net.minecraft.potion.*;

public final class MovementUtils implements Helper
{
    public static void setSpeedStrafe(final double lllllllllllllllllIIIllIIllIlIlIl) {
        Minecraft.getMinecraft().player.motionX = -Math.sin(getDirection()) * lllllllllllllllllIIIllIIllIlIlIl;
        Minecraft.getMinecraft().player.motionZ = Math.cos(getDirection()) * lllllllllllllllllIIIllIIllIlIlIl;
    }
    
    public static double getSpeedStrafe() {
        return Math.sqrt(Math.pow(Minecraft.getMinecraft().player.motionX, 2.0) + Math.pow(Minecraft.getMinecraft().player.motionX, 2.0));
    }
    
    public static double[] forward2(final double lllllllllllllllllIIIllIIlllIlIlI) {
        float lllllllllllllllllIIIllIIllllIIIl = MovementUtils.mc.player.movementInput.moveForward;
        float lllllllllllllllllIIIllIIllllIIII = MovementUtils.mc.player.movementInput.moveStrafe;
        float lllllllllllllllllIIIllIIlllIllll = MovementUtils.mc.player.prevRotationYaw + (MovementUtils.mc.player.rotationYaw - MovementUtils.mc.player.prevRotationYaw) * MovementUtils.mc.getRenderPartialTicks();
        if (lllllllllllllllllIIIllIIllllIIIl != 0.0f) {
            if (lllllllllllllllllIIIllIIllllIIII > 0.0f) {
                lllllllllllllllllIIIllIIlllIllll += ((lllllllllllllllllIIIllIIllllIIIl > 0.0f) ? -45 : 45);
            }
            else if (lllllllllllllllllIIIllIIllllIIII < 0.0f) {
                lllllllllllllllllIIIllIIlllIllll += ((lllllllllllllllllIIIllIIllllIIIl > 0.0f) ? 45 : -45);
            }
            lllllllllllllllllIIIllIIllllIIII = 0.0f;
            if (lllllllllllllllllIIIllIIllllIIIl > 0.0f) {
                lllllllllllllllllIIIllIIllllIIIl = 1.0f;
            }
            else if (lllllllllllllllllIIIllIIllllIIIl < 0.0f) {
                lllllllllllllllllIIIllIIllllIIIl = -1.0f;
            }
        }
        final double lllllllllllllllllIIIllIIlllIlllI = Math.sin(Math.toRadians(lllllllllllllllllIIIllIIlllIllll + 90.0f));
        final double lllllllllllllllllIIIllIIlllIllIl = Math.cos(Math.toRadians(lllllllllllllllllIIIllIIlllIllll + 90.0f));
        final double lllllllllllllllllIIIllIIlllIllII = lllllllllllllllllIIIllIIllllIIIl * lllllllllllllllllIIIllIIlllIlIlI * lllllllllllllllllIIIllIIlllIllIl + lllllllllllllllllIIIllIIllllIIII * lllllllllllllllllIIIllIIlllIlIlI * lllllllllllllllllIIIllIIlllIlllI;
        final double lllllllllllllllllIIIllIIlllIlIll = lllllllllllllllllIIIllIIllllIIIl * lllllllllllllllllIIIllIIlllIlIlI * lllllllllllllllllIIIllIIlllIlllI - lllllllllllllllllIIIllIIllllIIII * lllllllllllllllllIIIllIIlllIlIlI * lllllllllllllllllIIIllIIlllIllIl;
        return new double[] { lllllllllllllllllIIIllIIlllIllII, lllllllllllllllllIIIllIIlllIlIll };
    }
    
    public static boolean hasMotion() {
        return Wrapper.getMinecraft().player.motionX != 0.0 && Wrapper.getMinecraft().player.motionZ != 0.0 && Wrapper.getMinecraft().player.motionY != 0.0;
    }
    
    public static boolean isMoving() {
        return Wrapper.getMinecraft().player != null && (Wrapper.getMinecraft().player.movementInput.moveForward != 0.0f || Wrapper.getMinecraft().player.movementInput.moveStrafe != 0.0f);
    }
    
    public static double getBaseMoveSpeed() {
        double lllllllllllllllllIIIllIIllIllIIl = 0.2873;
        if (MovementUtils.mc.player != null && MovementUtils.mc.player.isPotionActive(Potion.getPotionById(1))) {
            final int lllllllllllllllllIIIllIIllIllIlI = MovementUtils.mc.player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier();
            lllllllllllllllllIIIllIIllIllIIl *= 1.0 + 0.2 * (lllllllllllllllllIIIllIIllIllIlI + 1);
        }
        return lllllllllllllllllIIIllIIllIllIIl;
    }
    
    public static void forward(final double lllllllllllllllllIIIllIIllllllII) {
        final double lllllllllllllllllIIIllIIllllllIl = Math.toRadians(Wrapper.getMinecraft().player.rotationYaw);
        Wrapper.getMinecraft().player.setPosition(Wrapper.getMinecraft().player.posX + -Math.sin(lllllllllllllllllIIIllIIllllllIl) * lllllllllllllllllIIIllIIllllllII, Wrapper.getMinecraft().player.posY, Wrapper.getMinecraft().player.posZ + Math.cos(lllllllllllllllllIIIllIIllllllIl) * lllllllllllllllllIIIllIIllllllII);
    }
    
    public static float getSpeed() {
        return (float)Math.sqrt(Wrapper.getMinecraft().player.motionX * Wrapper.getMinecraft().player.motionX + Wrapper.getMinecraft().player.motionZ * Wrapper.getMinecraft().player.motionZ);
    }
    
    public static void strafe() {
        strafe(getSpeed());
    }
    
    public static double getDirectionStrafe() {
        return Math.toRadians(Minecraft.getMinecraft().player.rotationYaw);
    }
    
    public static double getDirection() {
        float lllllllllllllllllIIIllIIlllIIIII = Wrapper.getMinecraft().player.rotationYaw;
        if (Wrapper.getMinecraft().player.moveForward < 0.0f) {
            lllllllllllllllllIIIllIIlllIIIII += 180.0f;
        }
        float lllllllllllllllllIIIllIIllIlllll = 1.0f;
        if (Wrapper.getMinecraft().player.moveForward < 0.0f) {
            lllllllllllllllllIIIllIIllIlllll = -0.5f;
        }
        else if (Wrapper.getMinecraft().player.moveForward > 0.0f) {
            lllllllllllllllllIIIllIIllIlllll = 0.5f;
        }
        if (Wrapper.getMinecraft().player.moveStrafing > 0.0f) {
            lllllllllllllllllIIIllIIlllIIIII -= 90.0f * lllllllllllllllllIIIllIIllIlllll;
        }
        if (Wrapper.getMinecraft().player.moveStrafing < 0.0f) {
            lllllllllllllllllIIIllIIlllIIIII += 90.0f * lllllllllllllllllIIIllIIllIlllll;
        }
        return Math.toRadians(lllllllllllllllllIIIllIIlllIIIII);
    }
    
    public static void strafe(final float lllllllllllllllllIIIllIlIIIIIlII) {
        if (!isMoving()) {
            return;
        }
        final double lllllllllllllllllIIIllIlIIIIIIll = getDirection();
        Wrapper.getMinecraft().player.motionX = -Math.sin(lllllllllllllllllIIIllIlIIIIIIll) * lllllllllllllllllIIIllIlIIIIIlII;
        Wrapper.getMinecraft().player.motionZ = Math.cos(lllllllllllllllllIIIllIlIIIIIIll) * lllllllllllllllllIIIllIlIIIIIlII;
    }
}
