package com.elementars.eclient.guirewrite;

import dev.xulu.settings.*;
import dev.xulu.newgui.elements.*;
import java.io.*;
import com.elementars.eclient.*;
import java.util.*;
import dev.xulu.newgui.*;
import com.elementars.eclient.module.*;
import org.lwjgl.input.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.module.render.*;
import java.awt.*;
import net.minecraft.client.gui.*;

public class HUD extends GuiScreen
{
    private /* synthetic */ ModuleButton mb;
    public static /* synthetic */ ArrayList<Frame> frames;
    public static /* synthetic */ ArrayList<Frame> rframes;
    public /* synthetic */ ValueManager setmgr;
    public /* synthetic */ Panel hudPanel;
    
    public void mouseClicked(final int lIIIllIIlllllI, final int lIIIllIIlllIIl, final int lIIIllIIlllIII) {
        if (this.hudPanel.extended && this.hudPanel.visible && this.hudPanel.Elements != null) {
            for (final ModuleButton lIIIllIlIIIIlI : this.hudPanel.Elements) {
                if (lIIIllIlIIIIlI.extended) {
                    for (final Element lIIIllIlIIIIll : lIIIllIlIIIIlI.menuelements) {
                        if (lIIIllIlIIIIll.mouseClicked(lIIIllIIlllllI, lIIIllIIlllIIl, lIIIllIIlllIII)) {
                            return;
                        }
                    }
                }
            }
        }
        if (this.hudPanel.mouseClicked(lIIIllIIlllllI, lIIIllIIlllIIl, lIIIllIIlllIII)) {
            return;
        }
        for (final Frame lIIIllIlIIIIIl : HUD.frames) {
            if (lIIIllIlIIIIIl.mouseClicked(lIIIllIIlllllI, lIIIllIIlllIIl, lIIIllIIlllIII)) {
                return;
            }
        }
        try {
            super.mouseClicked(lIIIllIIlllllI, lIIIllIIlllIIl, lIIIllIIlllIII);
        }
        catch (IOException lIIIllIlIIIIII) {
            lIIIllIlIIIIII.printStackTrace();
        }
    }
    
    public HUD() {
        this.mb = null;
        this.setmgr = Xulu.VALUE_MANAGER;
        HUD.frames = new ArrayList<Frame>();
        final double lIIIllIllllIlI = 80.0;
        final double lIIIllIllllIIl = 15.0;
        final double lIIIllIllllIII = 10.0;
        final double lIIIllIlllIlll = 10.0;
        final double lIIIllIlllIllI = lIIIllIllllIIl + 10.0;
        HUD.frames.add(new Frame("PvPInfo", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Totems", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Obsidian", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Crystals", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Gapples", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("InvPreview", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("TextRadar", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("FeatureList", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Player", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Welcome", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("OldName", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("TheGoons", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Potions", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("StickyNotes", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Exp", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("HoleHud", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Info", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Armor", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("CraftingPreview", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("GodInfo", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Watermark", 2.0, 2.0, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Logo", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.frames.add(new Frame("Target", lIIIllIllllIII, lIIIllIlllIlll, lIIIllIllllIlI, lIIIllIllllIIl, false, this));
        HUD.rframes = new ArrayList<Frame>();
        for (final Frame lIIIllIlllllII : HUD.frames) {
            HUD.rframes.add(lIIIllIlllllII);
        }
        Collections.reverse(HUD.rframes);
        this.hudPanel = new Panel("Elements", lIIIllIllllIII, lIIIllIlllIlll, 100.0, 13.0, true, Xulu.newGUI) {
            @Override
            public void setup() {
                for (final Module llIIIllIIlIllIl : Xulu.MODULE_MANAGER.getModules()) {
                    if (!llIIIllIIlIllIl.getCategory().equals(Category.HUD) && !(llIIIllIIlIllIl instanceof com.elementars.eclient.guirewrite.Element)) {
                        continue;
                    }
                    System.out.println("[HUD] We adding a modulebutton");
                    this.Elements.add(new ModuleButton(llIIIllIIlIllIl, this));
                }
            }
        };
    }
    
    public void refreshPanel() {
        this.hudPanel = new Panel("Elements", 10.0, 10.0, 100.0, 13.0, true, Xulu.newGUI) {
            @Override
            public void setup() {
                for (final Module lllllllllllllllllIIlIlIlllIlIllI : Xulu.MODULE_MANAGER.getModules()) {
                    if (!lllllllllllllllllIIlIlIlllIlIllI.getCategory().equals(Category.HUD) && !(lllllllllllllllllIIlIlIlllIlIllI instanceof com.elementars.eclient.guirewrite.Element)) {
                        continue;
                    }
                    this.Elements.add(new ModuleButton(lllllllllllllllllIIlIlIlllIlIllI, this));
                }
            }
        };
    }
    
    public static void registerElements() {
        for (final Module lIIIlllIIIlIlI : Xulu.MODULE_MANAGER.getModules()) {
            if (lIIIlllIIIlIlI instanceof com.elementars.eclient.guirewrite.Element) {
                ((com.elementars.eclient.guirewrite.Element)lIIIlllIIIlIlI).registerFrame();
            }
        }
    }
    
    public void handleMouseInput() throws IOException {
        final int lIIIllIIIIlIIl = 5;
        if (Mouse.getEventDWheel() > 0) {
            for (final Frame frame : HUD.rframes) {
                final Frame lIIIllIIIIllII = frame;
                frame.y += lIIIllIIIIlIIl;
            }
            final Panel hudPanel = this.hudPanel;
            hudPanel.y += lIIIllIIIIlIIl;
        }
        if (Mouse.getEventDWheel() < 0) {
            for (final Frame frame2 : HUD.rframes) {
                final Frame lIIIllIIIIlIll = frame2;
                frame2.y -= lIIIllIIIIlIIl;
            }
            final Panel hudPanel2 = this.hudPanel;
            hudPanel2.y -= lIIIllIIIIlIIl;
        }
        super.handleMouseInput();
    }
    
    protected void keyTyped(final char lIIIllIIIlIIll, final int lIIIllIIIlIlIl) {
        try {
            super.keyTyped(lIIIllIIIlIIll, lIIIllIIIlIlIl);
        }
        catch (IOException lIIIllIIIllIII) {
            lIIIllIIIllIII.printStackTrace();
        }
    }
    
    public static Frame getframeByName(final String lIIIlllIIIllll) {
        for (final Frame lIIIlllIIlIIIl : getFrames()) {
            if (lIIIlllIIlIIIl.title.equalsIgnoreCase(lIIIlllIIIllll)) {
                return lIIIlllIIlIIIl;
            }
        }
        return null;
    }
    
    public void drawScreen(final int lIIIllIlIlIlIl, final int lIIIllIlIllIIl, final float lIIIllIlIllIII) {
        this.hudPanel.drawScreen(lIIIllIlIlIlIl, lIIIllIlIllIIl, lIIIllIlIllIII);
        if (this.hudPanel.extended && this.hudPanel.visible && this.hudPanel.Elements != null) {
            for (final ModuleButton lIIIllIlIllllI : this.hudPanel.Elements) {
                if (lIIIllIlIllllI.extended && lIIIllIlIllllI.menuelements != null && !lIIIllIlIllllI.menuelements.isEmpty()) {
                    double lIIIllIllIIIIl = lIIIllIlIllllI.height + 1.0;
                    Color lIIIllIllIIIII = ColorUtil.getClickGUIColor().darker();
                    if (NewGui.rainbowgui.getValue()) {
                        lIIIllIllIIIII = new Color(Xulu.rgb).darker();
                    }
                    final int lIIIllIlIlllll = new Color(lIIIllIllIIIII.getRed(), lIIIllIllIIIII.getGreen(), lIIIllIllIIIII.getBlue(), 170).getRGB();
                    for (final Element lIIIllIllIIIlI : lIIIllIlIllllI.menuelements) {
                        lIIIllIllIIIlI.offset = lIIIllIllIIIIl;
                        lIIIllIllIIIlI.update();
                        lIIIllIllIIIlI.drawScreen(lIIIllIlIlIlIl, lIIIllIlIllIIl, lIIIllIlIllIII);
                        lIIIllIllIIIIl += lIIIllIllIIIlI.height;
                    }
                }
            }
        }
        this.mb = null;
        if (this.hudPanel != null && this.hudPanel.visible && this.hudPanel.extended && this.hudPanel.Elements != null && this.hudPanel.Elements.size() > 0) {
            for (final ModuleButton lIIIllIlIlllIl : this.hudPanel.Elements) {
                if (lIIIllIlIlllIl.listening) {
                    this.mb = lIIIllIlIlllIl;
                    break;
                }
            }
        }
        for (final Frame lIIIllIlIlllII : HUD.frames) {
            lIIIllIlIlllII.drawScreen(lIIIllIlIlIlIl, lIIIllIlIllIIl, lIIIllIlIllIII);
        }
        final ScaledResolution lIIIllIlIlIlll = new ScaledResolution(this.mc);
        super.drawScreen(lIIIllIlIlIlIl, lIIIllIlIllIIl, lIIIllIlIllIII);
    }
    
    public static ArrayList<Frame> getFrames() {
        return HUD.frames;
    }
    
    public void mouseReleased(final int lIIIllIIlIIlll, final int lIIIllIIlIIllI, final int lIIIllIIlIIIIl) {
        if (this.hudPanel.extended && this.hudPanel.visible && this.hudPanel.Elements != null) {
            for (final ModuleButton lIIIllIIlIlIlI : this.hudPanel.Elements) {
                if (lIIIllIIlIlIlI.extended) {
                    for (final Element lIIIllIIlIlIll : lIIIllIIlIlIlI.menuelements) {
                        lIIIllIIlIlIll.mouseReleased(lIIIllIIlIIlll, lIIIllIIlIIllI, lIIIllIIlIIIIl);
                    }
                }
            }
        }
        this.hudPanel.mouseReleased(lIIIllIIlIIlll, lIIIllIIlIIllI, lIIIllIIlIIIIl);
        for (final Frame lIIIllIIlIlIIl : HUD.rframes) {
            lIIIllIIlIlIIl.mouseReleased(lIIIllIIlIIlll, lIIIllIIlIIllI, lIIIllIIlIIIIl);
        }
        super.mouseReleased(lIIIllIIlIIlll, lIIIllIIlIIllI, lIIIllIIlIIIIl);
    }
}
