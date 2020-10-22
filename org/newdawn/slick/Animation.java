package org.newdawn.slick;

import java.util.*;
import org.lwjgl.*;
import org.newdawn.slick.util.*;

public class Animation implements Renderable
{
    private /* synthetic */ long timeLeft;
    private /* synthetic */ float speed;
    private /* synthetic */ boolean stopped;
    private /* synthetic */ int direction;
    private /* synthetic */ long lastUpdate;
    private /* synthetic */ SpriteSheet spriteSheet;
    private /* synthetic */ boolean firstUpdate;
    private /* synthetic */ int currentFrame;
    private /* synthetic */ boolean autoUpdate;
    private /* synthetic */ boolean loop;
    private /* synthetic */ long nextChange;
    private /* synthetic */ ArrayList frames;
    private /* synthetic */ boolean pingPong;
    private /* synthetic */ int stopAt;
    
    public void setSpeed(final float llIIIIIllIlIlI) {
        if (llIIIIIllIlIlI > 0.0f) {
            this.nextChange = (long)(this.nextChange * this.speed / llIIIIIllIlIlI);
            this.speed = llIIIIIllIlIlI;
        }
    }
    
    public void draw(final float llIIIIIlIIIIll, final float llIIIIIIlllllI, final Color llIIIIIIllllIl) {
        this.draw(llIIIIIlIIIIll, llIIIIIIlllllI, (float)this.getWidth(), (float)this.getHeight(), llIIIIIIllllIl);
    }
    
    public Animation copy() {
        final Animation lIllllIllIllll = new Animation();
        lIllllIllIllll.spriteSheet = this.spriteSheet;
        lIllllIllIllll.frames = this.frames;
        lIllllIllIllll.autoUpdate = this.autoUpdate;
        lIllllIllIllll.direction = this.direction;
        lIllllIllIllll.loop = this.loop;
        lIllllIllIllll.pingPong = this.pingPong;
        lIllllIllIllll.speed = this.speed;
        return lIllllIllIllll;
    }
    
    public void setCurrentFrame(final int lIllllllIIIIIl) {
        this.currentFrame = lIllllllIIIIIl;
    }
    
    public void drawFlash(final float lIlllllllllIII, final float lIllllllllIIlI, final float lIllllllllIIIl, final float lIllllllllIIII) {
        this.drawFlash(lIlllllllllIII, lIllllllllIIlI, lIllllllllIIIl, lIllllllllIIII, Color.white);
    }
    
    public Image getCurrentFrame() {
        final Frame lIlllllIlIllll = this.frames.get(this.currentFrame);
        return lIlllllIlIllll.image;
    }
    
    @Override
    public String toString() {
        String lIllllIlllIlll = String.valueOf(new StringBuilder().append("[Animation (").append(this.frames.size()).append(") "));
        for (int lIllllIllllIIl = 0; lIllllIllllIIl < this.frames.size(); ++lIllllIllllIIl) {
            final Frame lIllllIllllIlI = this.frames.get(lIllllIllllIIl);
            lIllllIlllIlll = String.valueOf(new StringBuilder().append(lIllllIlllIlll).append(lIllllIllllIlI.duration).append(","));
        }
        lIllllIlllIlll = String.valueOf(new StringBuilder().append(lIllllIlllIlll).append("]"));
        return lIllllIlllIlll;
    }
    
    private void nextFrame(final long lIlllllIlIIlll) {
        if (this.stopped) {
            return;
        }
        if (this.frames.size() == 0) {
            return;
        }
        this.nextChange -= lIlllllIlIIlll;
        while (this.nextChange < 0L && !this.stopped) {
            if (this.currentFrame == this.stopAt) {
                this.stopped = true;
                break;
            }
            if (this.currentFrame == this.frames.size() - 1 && !this.loop && !this.pingPong) {
                this.stopped = true;
                break;
            }
            this.currentFrame = (this.currentFrame + this.direction) % this.frames.size();
            if (this.pingPong) {
                if (this.currentFrame <= 0) {
                    this.currentFrame = 0;
                    this.direction = 1;
                    if (!this.loop) {
                        this.stopped = true;
                        break;
                    }
                }
                else if (this.currentFrame >= this.frames.size() - 1) {
                    this.currentFrame = this.frames.size() - 1;
                    this.direction = -1;
                }
            }
            final int lIlllllIlIlIIl = (int)(this.frames.get(this.currentFrame).duration / this.speed);
            this.nextChange += lIlllllIlIlIIl;
        }
    }
    
    public int getFrame() {
        return this.currentFrame;
    }
    
    public Animation(final boolean llIIIIllllIIIl) {
        this.frames = new ArrayList();
        this.currentFrame = -1;
        this.nextChange = 0L;
        this.stopped = false;
        this.speed = 1.0f;
        this.stopAt = -2;
        this.firstUpdate = true;
        this.autoUpdate = true;
        this.direction = 1;
        this.loop = true;
        this.spriteSheet = null;
        this.currentFrame = 0;
        this.autoUpdate = llIIIIllllIIIl;
    }
    
    public Animation(final SpriteSheet llIIIIlIllIIII, final int llIIIIlIlIllll, final int llIIIIlIlIlllI, final int llIIIIlIlIllIl, final int llIIIIlIllIlIl, final boolean llIIIIlIlIlIll, final int llIIIIlIlIlIlI, final boolean llIIIIlIllIIlI) {
        this.frames = new ArrayList();
        this.currentFrame = -1;
        this.nextChange = 0L;
        this.stopped = false;
        this.speed = 1.0f;
        this.stopAt = -2;
        this.firstUpdate = true;
        this.autoUpdate = true;
        this.direction = 1;
        this.loop = true;
        this.spriteSheet = null;
        this.autoUpdate = llIIIIlIllIIlI;
        if (!llIIIIlIlIlIll) {
            for (int llIIIIlIllllIl = llIIIIlIlIllll; llIIIIlIllllIl <= llIIIIlIlIllIl; ++llIIIIlIllllIl) {
                for (int llIIIIlIlllllI = llIIIIlIlIlllI; llIIIIlIlllllI <= llIIIIlIllIlIl; ++llIIIIlIlllllI) {
                    this.addFrame(llIIIIlIllIIII.getSprite(llIIIIlIllllIl, llIIIIlIlllllI), llIIIIlIlIlIlI);
                }
            }
        }
        else {
            for (int llIIIIlIlllIll = llIIIIlIlIlllI; llIIIIlIlllIll <= llIIIIlIllIlIl; ++llIIIIlIlllIll) {
                for (int llIIIIlIllllII = llIIIIlIlIllll; llIIIIlIllllII <= llIIIIlIlIllIl; ++llIIIIlIllllII) {
                    this.addFrame(llIIIIlIllIIII.getSprite(llIIIIlIllllII, llIIIIlIlllIll), llIIIIlIlIlIlI);
                }
            }
        }
    }
    
    public int getFrameCount() {
        return this.frames.size();
    }
    
    public void setPingPong(final boolean llIIIIIlllIlIl) {
        this.pingPong = llIIIIIlllIlIl;
    }
    
    public Animation(final Image[] llIIIIlllllIll, final int[] llIIIIlllllIlI) {
        this(llIIIIlllllIll, llIIIIlllllIlI, true);
    }
    
    @Deprecated
    public void updateNoDraw() {
        if (this.autoUpdate) {
            final long lIllllllIlIIll = this.getTime();
            long lIllllllIlIIlI = lIllllllIlIIll - this.lastUpdate;
            if (this.firstUpdate) {
                lIllllllIlIIlI = 0L;
                this.firstUpdate = false;
            }
            this.lastUpdate = lIllllllIlIIll;
            this.nextFrame(lIllllllIlIIlI);
        }
    }
    
    public Animation(final SpriteSheet llIIIIllIIlllI, final int llIIIIllIIlIlI) {
        this(llIIIIllIIlllI, 0, 0, llIIIIllIIlllI.getHorizontalCount() - 1, llIIIIllIIlllI.getVerticalCount() - 1, true, llIIIIllIIlIlI, true);
    }
    
    public Image getImage(final int lIlllllIllIlll) {
        final Frame lIlllllIlllIIl = this.frames.get(lIlllllIllIlll);
        return lIlllllIlllIIl.image;
    }
    
    public void draw() {
        this.draw(0.0f, 0.0f);
    }
    
    public void drawFlash(final float lIlllllllIIlII, final float lIllllllIlllII, final float lIlllllllIIIlI, final float lIlllllllIIIIl, final Color lIllllllIllIIl) {
        if (this.frames.size() == 0) {
            return;
        }
        if (this.autoUpdate) {
            final long lIlllllllIIlll = this.getTime();
            long lIlllllllIIllI = lIlllllllIIlll - this.lastUpdate;
            if (this.firstUpdate) {
                lIlllllllIIllI = 0L;
                this.firstUpdate = false;
            }
            this.lastUpdate = lIlllllllIIlll;
            this.nextFrame(lIlllllllIIllI);
        }
        final Frame lIllllllIlllll = this.frames.get(this.currentFrame);
        lIllllllIlllll.image.drawFlash(lIlllllllIIlII, lIllllllIlllII, lIlllllllIIIlI, lIlllllllIIIIl, lIllllllIllIIl);
    }
    
    public int getHeight() {
        return this.frames.get(this.currentFrame).image.getHeight();
    }
    
    public int getWidth() {
        return this.frames.get(this.currentFrame).image.getWidth();
    }
    
    public int getDuration(final int lIlllllIIlIIIl) {
        return this.frames.get(lIlllllIIlIIIl).duration;
    }
    
    public void draw(final float llIIIIIIIllIll, final float llIIIIIIlIIIIl, final float llIIIIIIlIIIII, final float llIIIIIIIlllll, final Color llIIIIIIIllllI) {
        if (this.frames.size() == 0) {
            return;
        }
        if (this.autoUpdate) {
            final long llIIIIIIlIIlIl = this.getTime();
            long llIIIIIIlIIlII = llIIIIIIlIIlIl - this.lastUpdate;
            if (this.firstUpdate) {
                llIIIIIIlIIlII = 0L;
                this.firstUpdate = false;
            }
            this.lastUpdate = llIIIIIIlIIlIl;
            this.nextFrame(llIIIIIIlIIlII);
        }
        final Frame llIIIIIIIlllIl = this.frames.get(this.currentFrame);
        llIIIIIIIlllIl.image.draw(llIIIIIIIllIll, llIIIIIIlIIIIl, llIIIIIIlIIIII, llIIIIIIIlllll, llIIIIIIIllllI);
    }
    
    public void setAutoUpdate(final boolean llIIIIIlllllIl) {
        this.autoUpdate = llIIIIIlllllIl;
    }
    
    public boolean isStopped() {
        return this.stopped;
    }
    
    public void setLooping(final boolean lIlllllIlIIIII) {
        this.loop = lIlllllIlIIIII;
    }
    
    public Animation(final Image[] llIIIIllIllIlI, final int[] llIIIIllIllIIl, final boolean llIIIIllIlIlII) {
        this.frames = new ArrayList();
        this.currentFrame = -1;
        this.nextChange = 0L;
        this.stopped = false;
        this.speed = 1.0f;
        this.stopAt = -2;
        this.firstUpdate = true;
        this.autoUpdate = true;
        this.direction = 1;
        this.loop = true;
        this.spriteSheet = null;
        this.autoUpdate = llIIIIllIlIlII;
        if (llIIIIllIllIlI.length != llIIIIllIllIIl.length) {
            throw new RuntimeException("There must be one duration per frame");
        }
        for (int llIIIIllIlllII = 0; llIIIIllIlllII < llIIIIllIllIlI.length; ++llIIIIllIlllII) {
            this.addFrame(llIIIIllIllIlI[llIIIIllIlllII], llIIIIllIllIIl[llIIIIllIlllII]);
        }
        this.currentFrame = 0;
    }
    
    public void renderInUse(final int llIIIIIIIIlIII, final int llIIIIIIIIlIll) {
        if (this.frames.size() == 0) {
            return;
        }
        if (this.autoUpdate) {
            final long llIIIIIIIIllll = this.getTime();
            long llIIIIIIIIlllI = llIIIIIIIIllll - this.lastUpdate;
            if (this.firstUpdate) {
                llIIIIIIIIlllI = 0L;
                this.firstUpdate = false;
            }
            this.lastUpdate = llIIIIIIIIllll;
            this.nextFrame(llIIIIIIIIlllI);
        }
        final Frame llIIIIIIIIlIlI = this.frames.get(this.currentFrame);
        this.spriteSheet.renderInUse(llIIIIIIIIlIII, llIIIIIIIIlIll, llIIIIIIIIlIlI.x, llIIIIIIIIlIlI.y);
    }
    
    public void stop() {
        if (this.frames.size() == 0) {
            return;
        }
        this.timeLeft = this.nextChange;
        this.stopped = true;
    }
    
    public Animation() {
        this(true);
    }
    
    public void setDuration(final int lIlllllIIIlIIl, final int lIlllllIIIlIll) {
        this.frames.get(lIlllllIIIlIIl).duration = lIlllllIIIlIll;
    }
    
    public Animation(final SpriteSheet llIIIIlIIlIlll, final int[] llIIIIlIIlIllI, final int[] llIIIIlIIllIll) {
        this.frames = new ArrayList();
        this.currentFrame = -1;
        this.nextChange = 0L;
        this.stopped = false;
        this.speed = 1.0f;
        this.stopAt = -2;
        this.firstUpdate = true;
        this.autoUpdate = true;
        this.direction = 1;
        this.loop = true;
        this.spriteSheet = null;
        this.spriteSheet = llIIIIlIIlIlll;
        int llIIIIlIIllIlI = -1;
        int llIIIIlIIllIIl = -1;
        for (int llIIIIlIIlllll = 0; llIIIIlIIlllll < llIIIIlIIlIllI.length / 2; ++llIIIIlIIlllll) {
            llIIIIlIIllIlI = llIIIIlIIlIllI[llIIIIlIIlllll * 2];
            llIIIIlIIllIIl = llIIIIlIIlIllI[llIIIIlIIlllll * 2 + 1];
            this.addFrame(llIIIIlIIllIll[llIIIIlIIlllll], llIIIIlIIllIlI, llIIIIlIIllIIl);
        }
    }
    
    public void restart() {
        if (this.frames.size() == 0) {
            return;
        }
        this.stopped = false;
        this.currentFrame = 0;
        this.nextChange = (int)(this.frames.get(0).duration / this.speed);
        this.firstUpdate = true;
        this.lastUpdate = 0L;
    }
    
    public void update(final long lIllllllIIlIlI) {
        this.nextFrame(lIllllllIIlIlI);
    }
    
    private long getTime() {
        return Sys.getTime() * 1000L / Sys.getTimerResolution();
    }
    
    public Animation(final Image[] llIIIIlllIlIIl, final int llIIIIlllIIlII, final boolean llIIIIlllIIIll) {
        this.frames = new ArrayList();
        this.currentFrame = -1;
        this.nextChange = 0L;
        this.stopped = false;
        this.speed = 1.0f;
        this.stopAt = -2;
        this.firstUpdate = true;
        this.autoUpdate = true;
        this.direction = 1;
        this.loop = true;
        this.spriteSheet = null;
        for (int llIIIIlllIlIll = 0; llIIIIlllIlIll < llIIIIlllIlIIl.length; ++llIIIIlllIlIll) {
            this.addFrame(llIIIIlllIlIIl[llIIIIlllIlIll], llIIIIlllIIlII);
        }
        this.currentFrame = 0;
        this.autoUpdate = llIIIIlllIIIll;
    }
    
    public void start() {
        if (!this.stopped) {
            return;
        }
        if (this.frames.size() == 0) {
            return;
        }
        this.stopped = false;
        this.nextChange = this.timeLeft;
    }
    
    public void stopAt(final int lIlllllIIlIlll) {
        this.stopAt = lIlllllIIlIlll;
    }
    
    public void addFrame(final Image llIIIIIlIllIIl, final int llIIIIIlIlIlIl) {
        if (llIIIIIlIlIlIl == 0) {
            Log.error(String.valueOf(new StringBuilder().append("Invalid duration: ").append(llIIIIIlIlIlIl)));
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Invalid duration: ").append(llIIIIIlIlIlIl)));
        }
        if (this.frames.isEmpty()) {
            this.nextChange = (int)(llIIIIIlIlIlIl / this.speed);
        }
        this.frames.add(new Frame(llIIIIIlIllIIl, llIIIIIlIlIlIl));
        this.currentFrame = 0;
    }
    
    public int[] getDurations() {
        final int[] lIlllllIIIIIlI = new int[this.frames.size()];
        for (int lIlllllIIIIlII = 0; lIlllllIIIIlII < this.frames.size(); ++lIlllllIIIIlII) {
            lIlllllIIIIIlI[lIlllllIIIIlII] = this.getDuration(lIlllllIIIIlII);
        }
        return lIlllllIIIIIlI;
    }
    
    public float getSpeed() {
        return this.speed;
    }
    
    public Animation(final Image[] llIIIlIIIIIIIl, final int llIIIlIIIIIIll) {
        this(llIIIlIIIIIIIl, llIIIlIIIIIIll, true);
    }
    
    @Override
    public void draw(final float llIIIIIlIIlIlI, final float llIIIIIlIIllII) {
        this.draw(llIIIIIlIIlIlI, llIIIIIlIIllII, (float)this.getWidth(), (float)this.getHeight());
    }
    
    public void addFrame(final int llIIIIlIIIIllI, final int llIIIIlIIIlIIl, final int llIIIIlIIIIIll) {
        if (llIIIIlIIIIllI == 0) {
            Log.error(String.valueOf(new StringBuilder().append("Invalid duration: ").append(llIIIIlIIIIllI)));
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Invalid duration: ").append(llIIIIlIIIIllI)));
        }
        if (this.frames.isEmpty()) {
            this.nextChange = (int)(llIIIIlIIIIllI / this.speed);
        }
        this.frames.add(new Frame(llIIIIlIIIIllI, llIIIIlIIIlIIl, llIIIIlIIIIIll));
        this.currentFrame = 0;
    }
    
    public void draw(final float llIIIIIIllIllI, final float llIIIIIIllIIII, final float llIIIIIIlIllll, final float llIIIIIIlIlllI) {
        this.draw(llIIIIIIllIllI, llIIIIIIllIIII, llIIIIIIlIllll, llIIIIIIlIlllI, Color.white);
    }
    
    private class Frame
    {
        public /* synthetic */ int y;
        public /* synthetic */ int x;
        public /* synthetic */ Image image;
        public /* synthetic */ int duration;
        
        public Frame(final int lllllllllllllllllIllllIIIlIllIlI, final int lllllllllllllllllIllllIIIlIllIIl, final int lllllllllllllllllIllllIIIlIlllIl) {
            this.x = -1;
            this.y = -1;
            this.image = Animation.this.spriteSheet.getSubImage(lllllllllllllllllIllllIIIlIllIIl, lllllllllllllllllIllllIIIlIlllIl);
            this.duration = lllllllllllllllllIllllIIIlIllIlI;
            this.x = lllllllllllllllllIllllIIIlIllIIl;
            this.y = lllllllllllllllllIllllIIIlIlllIl;
        }
        
        public Frame(final Image lllllllllllllllllIllllIIIllIIlll, final int lllllllllllllllllIllllIIIllIlIlI) {
            this.x = -1;
            this.y = -1;
            this.image = lllllllllllllllllIllllIIIllIIlll;
            this.duration = lllllllllllllllllIllllIIIllIlIlI;
        }
    }
}
