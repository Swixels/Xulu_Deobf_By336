package com.elementars.eclient.util;

import com.elementars.eclient.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import com.elementars.eclient.event.*;
import net.minecraft.util.math.*;
import java.util.*;

public class LagCompensator implements EventListener
{
    private /* synthetic */ int nextIndex;
    public static /* synthetic */ LagCompensator INSTANCE;
    private final /* synthetic */ float[] tickRates;
    private /* synthetic */ long timeLastTimeUpdate;
    
    public LagCompensator() {
        this.tickRates = new float[20];
        this.nextIndex = 0;
        Xulu.EVENT_MANAGER.register(this);
        this.reset();
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket lIllIIlllllI) {
        if (lIllIIlllllI.getPacket() instanceof SPacketTimeUpdate) {
            LagCompensator.INSTANCE.onTimeUpdate();
        }
    }
    
    public void onTimeUpdate() {
        if (this.timeLastTimeUpdate != -1L) {
            final float lIllIIlIIIlI = (System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0f;
            this.tickRates[this.nextIndex % this.tickRates.length] = MathHelper.clamp(20.0f / lIllIIlIIIlI, 0.0f, 20.0f);
            ++this.nextIndex;
        }
        this.timeLastTimeUpdate = System.currentTimeMillis();
    }
    
    public void reset() {
        this.nextIndex = 0;
        this.timeLastTimeUpdate = -1L;
        Arrays.fill(this.tickRates, 0.0f);
    }
    
    public float getTickRate() {
        float lIllIIlIllIl = 0.0f;
        float lIllIIlIllII = 0.0f;
        final double lIllIIlIlIII = (Object)this.tickRates;
        final boolean lIllIIlIIlll = lIllIIlIlIII.length != 0;
        for (float lIllIIlIIllI = 0; lIllIIlIIllI < (lIllIIlIIlll ? 1 : 0); ++lIllIIlIIllI) {
            final float lIllIIlIllll = lIllIIlIlIII[lIllIIlIIllI];
            if (lIllIIlIllll > 0.0f) {
                lIllIIlIllII += lIllIIlIllll;
                ++lIllIIlIllIl;
            }
        }
        return MathHelper.clamp(lIllIIlIllII / lIllIIlIllIl, 0.0f, 20.0f);
    }
}
