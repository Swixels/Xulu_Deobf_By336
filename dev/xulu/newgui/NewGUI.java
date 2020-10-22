package dev.xulu.newgui;

import dev.xulu.settings.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import java.awt.*;
import dev.xulu.newgui.elements.*;
import org.lwjgl.opengl.*;
import dev.xulu.newgui.util.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.util.*;
import java.io.*;
import dev.xulu.newgui.elements.menu.*;
import com.elementars.eclient.module.*;
import java.util.*;
import org.lwjgl.input.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class NewGUI extends GuiScreen
{
    public static /* synthetic */ ArrayList<Panel> rpanels;
    public static /* synthetic */ ArrayList<Panel> panels;
    private /* synthetic */ ModuleButton mb;
    public /* synthetic */ ValueManager setmgr;
    
    public static Panel getPanelByName(final String lllllllllllllllllIIlIlIIlIllllIl) {
        for (final Panel lllllllllllllllllIIlIlIIlIlllllI : getPanels()) {
            if (lllllllllllllllllIIlIlIIlIlllllI.title.equalsIgnoreCase(lllllllllllllllllIIlIlIIlIllllIl)) {
                return lllllllllllllllllIIlIlIIlIlllllI;
            }
        }
        return null;
    }
    
    public void closeAllSettings() {
        for (final Panel lllllllllllllllllIIlIIllllllIlIl : NewGUI.rpanels) {
            if (lllllllllllllllllIIlIIllllllIlIl != null && lllllllllllllllllIIlIIllllllIlIl.visible && lllllllllllllllllIIlIIllllllIlIl.extended && lllllllllllllllllIIlIIllllllIlIl.Elements != null && lllllllllllllllllIIlIIllllllIlIl.Elements.size() > 0) {
                for (final ModuleButton lllllllllllllllllIIlIIllllllIllI : lllllllllllllllllIIlIIllllllIlIl.Elements) {
                    lllllllllllllllllIIlIIllllllIllI.extended = false;
                }
            }
        }
    }
    
    public void drawScreen(final int lllllllllllllllllIIlIlIIIlllIlll, final int lllllllllllllllllIIlIlIIIlllIllI, final float lllllllllllllllllIIlIlIIIlllIlIl) {
        for (final Panel lllllllllllllllllIIlIlIIlIIIIIlI : NewGUI.rpanels) {
            lllllllllllllllllIIlIlIIlIIIIIlI.drawScreen(lllllllllllllllllIIlIlIIIlllIlll, lllllllllllllllllIIlIlIIIlllIllI, lllllllllllllllllIIlIlIIIlllIlIl);
            if (lllllllllllllllllIIlIlIIlIIIIIlI.extended && lllllllllllllllllIIlIlIIlIIIIIlI.visible && lllllllllllllllllIIlIlIIlIIIIIlI.Elements != null) {
                for (final ModuleButton lllllllllllllllllIIlIlIIlIIIIIll : lllllllllllllllllIIlIlIIlIIIIIlI.Elements) {
                    if (lllllllllllllllllIIlIlIIlIIIIIll.extended && lllllllllllllllllIIlIlIIlIIIIIll.menuelements != null && !lllllllllllllllllIIlIlIIlIIIIIll.menuelements.isEmpty()) {
                        double lllllllllllllllllIIlIlIIlIIIIllI = lllllllllllllllllIIlIlIIlIIIIIll.height + 1.0;
                        Color lllllllllllllllllIIlIlIIlIIIIlIl = ColorUtil.getClickGUIColor().darker();
                        if (NewGui.rainbowgui.getValue()) {
                            lllllllllllllllllIIlIlIIlIIIIlIl = new Color(Xulu.rgb).darker();
                        }
                        final int lllllllllllllllllIIlIlIIlIIIIlII = new Color(lllllllllllllllllIIlIlIIlIIIIlIl.getRed(), lllllllllllllllllIIlIlIIlIIIIlIl.getGreen(), lllllllllllllllllIIlIlIIlIIIIlIl.getBlue(), 170).getRGB();
                        for (final Element lllllllllllllllllIIlIlIIlIIIIlll : lllllllllllllllllIIlIlIIlIIIIIll.menuelements) {
                            if (!lllllllllllllllllIIlIlIIlIIIIlll.set.isVisible()) {
                                continue;
                            }
                            lllllllllllllllllIIlIlIIlIIIIlll.offset = lllllllllllllllllIIlIlIIlIIIIllI;
                            lllllllllllllllllIIlIlIIlIIIIlll.update();
                            lllllllllllllllllIIlIlIIlIIIIlll.drawScreen(lllllllllllllllllIIlIlIIIlllIlll, lllllllllllllllllIIlIlIIIlllIllI, lllllllllllllllllIIlIlIIIlllIlIl);
                            lllllllllllllllllIIlIlIIlIIIIllI += lllllllllllllllllIIlIlIIlIIIIlll.height;
                        }
                    }
                }
            }
        }
        final ScaledResolution lllllllllllllllllIIlIlIIIllllIIl = new ScaledResolution(this.mc);
        this.mb = null;
    Label_0426:
        for (final Panel lllllllllllllllllIIlIlIIlIIIIIII : NewGUI.rpanels) {
            if (lllllllllllllllllIIlIlIIlIIIIIII != null && lllllllllllllllllIIlIlIIlIIIIIII.visible && lllllllllllllllllIIlIlIIlIIIIIII.extended && lllllllllllllllllIIlIlIIlIIIIIII.Elements != null && lllllllllllllllllIIlIlIIlIIIIIII.Elements.size() > 0) {
                for (final ModuleButton lllllllllllllllllIIlIlIIlIIIIIIl : lllllllllllllllllIIlIlIIlIIIIIII.Elements) {
                    if (lllllllllllllllllIIlIlIIlIIIIIIl.listening) {
                        this.mb = lllllllllllllllllIIlIlIIlIIIIIIl;
                        break Label_0426;
                    }
                }
            }
        }
        if (this.mb != null) {
            drawRect(0, 0, this.width, this.height, -2012213232);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(lllllllllllllllllIIlIlIIIllllIIl.getScaledWidth() / 2), (float)(lllllllllllllllllIIlIlIIIllllIIl.getScaledHeight() / 2), 0.0f);
            GL11.glScalef(4.0f, 4.0f, 0.0f);
            FontUtil.drawTotalCenteredStringWithShadow("Listening...", 0.0, -10.0, -1);
            GL11.glScalef(0.5f, 0.5f, 0.0f);
            FontUtil.drawTotalCenteredStringWithShadow(String.valueOf(new StringBuilder().append("Press 'ESCAPE' to unbind ").append(this.mb.mod.getName()).append((this.mb.mod.getKey() > -1) ? String.valueOf(new StringBuilder().append(" (").append(Keyboard.getKeyName(this.mb.mod.getKey())).append(")")) : "")), 0.0, 0.0, -1);
            GL11.glScalef(0.25f, 0.25f, 0.0f);
            FontUtil.drawTotalCenteredStringWithShadow("by HeroCode", 0.0, 20.0, -1);
            GL11.glPopMatrix();
        }
        for (final Panel lllllllllllllllllIIlIlIIIllllllI : NewGUI.panels) {
            if (!lllllllllllllllllIIlIlIIIllllllI.extended) {
                continue;
            }
            for (final ModuleButton lllllllllllllllllIIlIlIIIlllllll : lllllllllllllllllIIlIlIIIllllllI.Elements) {
                if (lllllllllllllllllIIlIlIIIlllllll.mod instanceof com.elementars.eclient.guirewrite.Element) {
                    continue;
                }
                if (!lllllllllllllllllIIlIlIIIlllllll.isHovered(lllllllllllllllllIIlIlIIIlllIlll, lllllllllllllllllIIlIlIIIlllIllI)) {
                    continue;
                }
                if (NewGui.customfont.getValue()) {
                    Gui.drawRect(lllllllllllllllllIIlIlIIIlllIlll + 6, lllllllllllllllllIIlIlIIIlllIllI + 6, lllllllllllllllllIIlIlIIIlllIlll + Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIIlIlIIIlllllll.mod.getDesc()) + 11, (int)(lllllllllllllllllIIlIlIIIlllIllI + Xulu.cFontRenderer.getHeight() + 10.0f), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, NewGui.bgAlpha.getValue()));
                    if (NewGui.outline.getValue()) {
                        XuluTessellator.drawRectOutline(lllllllllllllllllIIlIlIIIlllIlll + 6, lllllllllllllllllIIlIlIIIlllIllI + 6, lllllllllllllllllIIlIlIIIlllIlll + Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIIlIlIIIlllllll.mod.getDesc()) + 11, (int)(lllllllllllllllllIIlIlIIIlllIllI + Xulu.cFontRenderer.getHeight() + 10.0f), 1.0, ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 225));
                    }
                    Xulu.cFontRenderer.drawStringWithShadow(lllllllllllllllllIIlIlIIIlllllll.mod.getDesc(), lllllllllllllllllIIlIlIIIlllIlll + 8, lllllllllllllllllIIlIlIIIlllIllI + 7, ColorUtils.Colors.WHITE);
                }
                else {
                    Gui.drawRect(lllllllllllllllllIIlIlIIIlllIlll + 6, lllllllllllllllllIIlIlIIIlllIllI + 6, lllllllllllllllllIIlIlIIIlllIlll + Wrapper.getMinecraft().fontRenderer.getStringWidth(lllllllllllllllllIIlIlIIIlllllll.mod.getDesc()) + 11, lllllllllllllllllIIlIlIIIlllIllI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT + 10, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, NewGui.bgAlpha.getValue()));
                    if (NewGui.outline.getValue()) {
                        XuluTessellator.drawRectOutline(lllllllllllllllllIIlIlIIIlllIlll + 6, lllllllllllllllllIIlIlIIIlllIllI + 6, lllllllllllllllllIIlIlIIIlllIlll + Wrapper.getMinecraft().fontRenderer.getStringWidth(lllllllllllllllllIIlIlIIIlllllll.mod.getDesc()) + 11, lllllllllllllllllIIlIlIIIlllIllI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT + 10, 1.0, ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 225));
                    }
                    Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(lllllllllllllllllIIlIlIIIlllllll.mod.getDesc(), (float)(lllllllllllllllllIIlIlIIIlllIlll + 9), (float)(lllllllllllllllllIIlIlIIIlllIllI + 9), ColorUtils.Colors.WHITE);
                }
            }
        }
        super.drawScreen(lllllllllllllllllIIlIlIIIlllIlll, lllllllllllllllllIIlIlIIIlllIllI, lllllllllllllllllIIlIlIIIlllIlIl);
    }
    
    public void mouseReleased(final int lllllllllllllllllIIlIlIIIIlllIll, final int lllllllllllllllllIIlIlIIIIlllIlI, final int lllllllllllllllllIIlIlIIIIlllIIl) {
        if (this.mb != null) {
            return;
        }
        for (final Panel lllllllllllllllllIIlIlIIIlIIIIlI : NewGUI.rpanels) {
            if (lllllllllllllllllIIlIlIIIlIIIIlI.extended && lllllllllllllllllIIlIlIIIlIIIIlI.visible && lllllllllllllllllIIlIlIIIlIIIIlI.Elements != null) {
                for (final ModuleButton lllllllllllllllllIIlIlIIIlIIIIll : lllllllllllllllllIIlIlIIIlIIIIlI.Elements) {
                    if (lllllllllllllllllIIlIlIIIlIIIIll.extended) {
                        for (final Element lllllllllllllllllIIlIlIIIlIIIlII : lllllllllllllllllIIlIlIIIlIIIIll.menuelements) {
                            if (!lllllllllllllllllIIlIlIIIlIIIlII.set.isVisible()) {
                                continue;
                            }
                            lllllllllllllllllIIlIlIIIlIIIlII.mouseReleased(lllllllllllllllllIIlIlIIIIlllIll, lllllllllllllllllIIlIlIIIIlllIlI, lllllllllllllllllIIlIlIIIIlllIIl);
                        }
                    }
                }
            }
        }
        for (final Panel lllllllllllllllllIIlIlIIIlIIIIIl : NewGUI.rpanels) {
            lllllllllllllllllIIlIlIIIlIIIIIl.mouseReleased(lllllllllllllllllIIlIlIIIIlllIll, lllllllllllllllllIIlIlIIIIlllIlI, lllllllllllllllllIIlIlIIIIlllIIl);
        }
        super.mouseReleased(lllllllllllllllllIIlIlIIIIlllIll, lllllllllllllllllIIlIlIIIIlllIlI, lllllllllllllllllIIlIlIIIIlllIIl);
    }
    
    protected void keyTyped(final char lllllllllllllllllIIlIlIIIIlIIlIl, final int lllllllllllllllllIIlIlIIIIlIIlII) {
        for (final Panel lllllllllllllllllIIlIlIIIIlIlIII : NewGUI.rpanels) {
            if (lllllllllllllllllIIlIlIIIIlIlIII != null && lllllllllllllllllIIlIlIIIIlIlIII.visible && lllllllllllllllllIIlIlIIIIlIlIII.extended && lllllllllllllllllIIlIlIIIIlIlIII.Elements != null && lllllllllllllllllIIlIlIIIIlIlIII.Elements.size() > 0) {
                for (final ModuleButton lllllllllllllllllIIlIlIIIIlIlIIl : lllllllllllllllllIIlIlIIIIlIlIII.Elements) {
                    try {
                        if (lllllllllllllllllIIlIlIIIIlIlIIl.keyTyped(lllllllllllllllllIIlIlIIIIlIIlIl, lllllllllllllllllIIlIlIIIIlIIlII)) {
                            return;
                        }
                        continue;
                    }
                    catch (IOException lllllllllllllllllIIlIlIIIIlIlIlI) {
                        lllllllllllllllllIIlIlIIIIlIlIlI.printStackTrace();
                    }
                }
            }
        }
        try {
            super.keyTyped(lllllllllllllllllIIlIlIIIIlIIlIl, lllllllllllllllllIIlIlIIIIlIIlII);
        }
        catch (IOException lllllllllllllllllIIlIlIIIIlIIlll) {
            lllllllllllllllllIIlIlIIIIlIIlll.printStackTrace();
        }
    }
    
    public static ArrayList<Panel> getPanels() {
        return NewGUI.panels;
    }
    
    public void onGuiClosed() {
        if (this.mc.entityRenderer.getShaderGroup() != null) {
            this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        for (final Panel lllllllllllllllllIIlIlIIIIIIIIll : NewGUI.rpanels) {
            if (lllllllllllllllllIIlIlIIIIIIIIll.extended && lllllllllllllllllIIlIlIIIIIIIIll.visible && lllllllllllllllllIIlIlIIIIIIIIll.Elements != null) {
                for (final ModuleButton lllllllllllllllllIIlIlIIIIIIIlII : lllllllllllllllllIIlIlIIIIIIIIll.Elements) {
                    if (lllllllllllllllllIIlIlIIIIIIIlII.extended) {
                        for (final Element lllllllllllllllllIIlIlIIIIIIIlIl : lllllllllllllllllIIlIlIIIIIIIlII.menuelements) {
                            if (lllllllllllllllllIIlIlIIIIIIIlIl instanceof ElementSlider) {
                                ((ElementSlider)lllllllllllllllllIIlIlIIIIIIIlIl).dragging = false;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public NewGUI() {
        this.mb = null;
        this.setmgr = Xulu.VALUE_MANAGER;
        NewGUI.panels = new ArrayList<Panel>();
        final double lllllllllllllllllIIlIlIIlIlIIllI = 100.0;
        final double lllllllllllllllllIIlIlIIlIlIIlIl = 13.0;
        final double lllllllllllllllllIIlIlIIlIlIIlII = 10.0;
        double lllllllllllllllllIIlIlIIlIlIIIll = 10.0;
        final double lllllllllllllllllIIlIlIIlIlIIIlI = lllllllllllllllllIIlIlIIlIlIIlIl + 10.0;
        int lllllllllllllllllIIlIlIIlIIllIll = (Object)Category.values();
        final Exception lllllllllllllllllIIlIlIIlIIllIlI = (Exception)lllllllllllllllllIIlIlIIlIIllIll.length;
        for (short lllllllllllllllllIIlIlIIlIIllIIl = 0; lllllllllllllllllIIlIlIIlIIllIIl < lllllllllllllllllIIlIlIIlIIllIlI; ++lllllllllllllllllIIlIlIIlIIllIIl) {
            final Category lllllllllllllllllIIlIlIIlIlIlIIl = lllllllllllllllllIIlIlIIlIIllIll[lllllllllllllllllIIlIlIIlIIllIIl];
            if (lllllllllllllllllIIlIlIIlIlIlIIl != Category.HIDDEN) {
                if (lllllllllllllllllIIlIlIIlIlIlIIl != Category.HUD) {
                    boolean lllllllllllllllllIIlIlIIlIlIlIll = true;
                    for (final Module lllllllllllllllllIIlIlIIlIlIllII : Xulu.MODULE_MANAGER.getModules()) {
                        if (!lllllllllllllllllIIlIlIIlIlIllII.getCategory().equals(lllllllllllllllllIIlIlIIlIlIlIIl)) {
                            continue;
                        }
                        lllllllllllllllllIIlIlIIlIlIlIll = false;
                    }
                    if (!lllllllllllllllllIIlIlIIlIlIlIll) {
                        final String lllllllllllllllllIIlIlIIlIlIlIlI = String.valueOf(new StringBuilder().append(Character.toUpperCase(lllllllllllllllllIIlIlIIlIlIlIIl.name().toLowerCase().charAt(0))).append(lllllllllllllllllIIlIlIIlIlIlIIl.name().toLowerCase().substring(1)));
                        NewGUI.panels.add(new Panel(lllllllllllllllllIIlIlIIlIlIlIlI, lllllllllllllllllIIlIlIIlIlIIlII, lllllllllllllllllIIlIlIIlIlIIIll, lllllllllllllllllIIlIlIIlIlIIllI, lllllllllllllllllIIlIlIIlIlIIlIl, false, this) {
                            @Override
                            public void setup() {
                                for (final Module llllllllllllllllIlIIlllllllIlllI : Xulu.MODULE_MANAGER.getModules()) {
                                    if (!llllllllllllllllIlIIlllllllIlllI.getCategory().equals(lllllllllllllllllIIlIlIIlIlIlIIl)) {
                                        continue;
                                    }
                                    this.Elements.add(new ModuleButton(llllllllllllllllIlIIlllllllIlllI, this));
                                }
                            }
                        });
                        lllllllllllllllllIIlIlIIlIlIIIll += lllllllllllllllllIIlIlIIlIlIIIlI;
                    }
                }
            }
        }
        NewGUI.rpanels = new ArrayList<Panel>();
        lllllllllllllllllIIlIlIIlIIllIll = (int)NewGUI.panels.iterator();
        while (((Iterator)lllllllllllllllllIIlIlIIlIIllIll).hasNext()) {
            final Panel lllllllllllllllllIIlIlIIlIlIlIII = ((Iterator<Panel>)lllllllllllllllllIIlIlIIlIIllIll).next();
            if (lllllllllllllllllIIlIlIIlIlIlIII.Elements.isEmpty()) {
                continue;
            }
            NewGUI.rpanels.add(lllllllllllllllllIIlIlIIlIlIlIII);
        }
        Collections.reverse(NewGUI.rpanels);
    }
    
    public void mouseClicked(final int lllllllllllllllllIIlIlIIIlIllIll, final int lllllllllllllllllIIlIlIIIlIlIllI, final int lllllllllllllllllIIlIlIIIlIllIIl) {
        if (this.mb != null) {
            return;
        }
        for (final Panel lllllllllllllllllIIlIlIIIlIlllll : NewGUI.rpanels) {
            if (lllllllllllllllllIIlIlIIIlIlllll.extended && lllllllllllllllllIIlIlIIIlIlllll.visible && lllllllllllllllllIIlIlIIIlIlllll.Elements != null) {
                for (final ModuleButton lllllllllllllllllIIlIlIIIllIIIII : lllllllllllllllllIIlIlIIIlIlllll.Elements) {
                    if (lllllllllllllllllIIlIlIIIllIIIII.extended) {
                        for (final Element lllllllllllllllllIIlIlIIIllIIIIl : lllllllllllllllllIIlIlIIIllIIIII.menuelements) {
                            if (!lllllllllllllllllIIlIlIIIllIIIIl.set.isVisible()) {
                                continue;
                            }
                            if (lllllllllllllllllIIlIlIIIllIIIIl.mouseClicked(lllllllllllllllllIIlIlIIIlIllIll, lllllllllllllllllIIlIlIIIlIlIllI, lllllllllllllllllIIlIlIIIlIllIIl)) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        for (final Panel lllllllllllllllllIIlIlIIIlIllllI : NewGUI.rpanels) {
            if (lllllllllllllllllIIlIlIIIlIllllI.mouseClicked(lllllllllllllllllIIlIlIIIlIllIll, lllllllllllllllllIIlIlIIIlIlIllI, lllllllllllllllllIIlIlIIIlIllIIl)) {
                return;
            }
        }
        try {
            super.mouseClicked(lllllllllllllllllIIlIlIIIlIllIll, lllllllllllllllllIIlIlIIIlIlIllI, lllllllllllllllllIIlIlIIIlIllIIl);
        }
        catch (IOException lllllllllllllllllIIlIlIIIlIlllIl) {
            lllllllllllllllllIIlIlIIIlIlllIl.printStackTrace();
        }
    }
    
    public void handleMouseInput() throws IOException {
        final int lllllllllllllllllIIlIlIIIIIlIlII = 5;
        if (Mouse.getEventDWheel() > 0) {
            for (final Panel panel : NewGUI.rpanels) {
                final Panel lllllllllllllllllIIlIlIIIIIlIlll = panel;
                panel.y += lllllllllllllllllIIlIlIIIIIlIlII;
            }
        }
        if (Mouse.getEventDWheel() < 0) {
            for (final Panel panel2 : NewGUI.rpanels) {
                final Panel lllllllllllllllllIIlIlIIIIIlIllI = panel2;
                panel2.y -= lllllllllllllllllIIlIlIIIIIlIlII;
            }
        }
        super.handleMouseInput();
    }
    
    public void initGui() {
        if (OpenGlHelper.shadersSupported && this.mc.getRenderViewEntity() instanceof EntityPlayer && NewGui.blur.getValue()) {
            if (this.mc.entityRenderer.getShaderGroup() != null) {
                this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
            this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
    }
}
