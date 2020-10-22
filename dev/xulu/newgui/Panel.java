package dev.xulu.newgui;

import java.util.*;
import com.elementars.eclient.*;
import java.awt.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.util.*;
import dev.xulu.newgui.util.*;
import dev.xulu.newgui.elements.*;
import com.elementars.eclient.module.core.*;

public class Panel
{
    public /* synthetic */ int rgb;
    public /* synthetic */ NewGUI clickgui;
    public /* synthetic */ double x;
    public /* synthetic */ boolean dragging;
    public /* synthetic */ boolean visible;
    public /* synthetic */ double y;
    private /* synthetic */ double y2;
    private /* synthetic */ double x2;
    public /* synthetic */ boolean extended;
    public /* synthetic */ double width;
    public /* synthetic */ String title;
    public /* synthetic */ double height;
    public /* synthetic */ ArrayList<ModuleButton> Elements;
    
    public boolean isHovered(final int llllllllllllllllIlIlIllllIIIllIl, final int llllllllllllllllIlIlIllllIIIlIIl) {
        return llllllllllllllllIlIlIllllIIIllIl >= this.x && llllllllllllllllIlIlIllllIIIllIl <= this.x + this.width && llllllllllllllllIlIlIllllIIIlIIl >= this.y && llllllllllllllllIlIlIllllIIIlIIl <= this.y + this.height;
    }
    
    public void mouseReleased(final int llllllllllllllllIlIlIllllIIlIllI, final int llllllllllllllllIlIlIllllIIlIlIl, final int llllllllllllllllIlIlIllllIIlIIlI) {
        if (!this.visible) {
            return;
        }
        if (llllllllllllllllIlIlIllllIIlIIlI == 0) {
            this.dragging = false;
        }
    }
    
    public void setup() {
    }
    
    public boolean mouseClicked(final int llllllllllllllllIlIlIllllIlIIIlI, final int llllllllllllllllIlIlIllllIIlllIl, final int llllllllllllllllIlIlIllllIIlllII) {
        if (!this.visible) {
            return false;
        }
        if (llllllllllllllllIlIlIllllIIlllII == 0 && this.isHovered(llllllllllllllllIlIlIllllIlIIIlI, llllllllllllllllIlIlIllllIIlllIl)) {
            this.x2 = this.x - llllllllllllllllIlIlIllllIlIIIlI;
            this.y2 = this.y - llllllllllllllllIlIlIllllIIlllIl;
            this.dragging = true;
            return true;
        }
        if (llllllllllllllllIlIlIllllIIlllII == 1 && this.isHovered(llllllllllllllllIlIlIllllIlIIIlI, llllllllllllllllIlIlIllllIIlllIl)) {
            this.extended = !this.extended;
            return true;
        }
        if (this.extended) {
            for (final ModuleButton llllllllllllllllIlIlIllllIlIIlII : this.Elements) {
                if (llllllllllllllllIlIlIllllIlIIlII.mouseClicked(llllllllllllllllIlIlIllllIlIIIlI, llllllllllllllllIlIlIllllIIlllIl, llllllllllllllllIlIlIllllIIlllII)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void drawScreen(final int llllllllllllllllIlIlIllllIllIlll, final int llllllllllllllllIlIlIllllIllllIl, final float llllllllllllllllIlIlIllllIllllII) {
        if (!this.visible) {
            return;
        }
        if (this.dragging) {
            this.x = this.x2 + llllllllllllllllIlIlIllllIllIlll;
            this.y = this.y2 + llllllllllllllllIlIlIllllIllllIl;
        }
        this.rgb = Xulu.rgb;
        final Color llllllllllllllllIlIlIllllIlllIll = ColorUtil.getClickGUIColor();
        int llllllllllllllllIlIlIllllIlllIlI = new Color(llllllllllllllllIlIlIllllIlllIll.getRed(), llllllllllllllllIlIlIllllIlllIll.getGreen(), llllllllllllllllIlIlIllllIlllIll.getBlue(), 200).getRGB();
        if (NewGui.rainbowgui.getValue()) {
            llllllllllllllllIlIlIllllIlllIlI = ColorUtils.changeAlpha(this.rgb, 200);
        }
        int llllllllllllllllIlIlIllllIlllIIl = ColorUtils.changeAlpha(llllllllllllllllIlIlIllllIlllIlI, 225);
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 225));
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), llllllllllllllllIlIlIllllIlllIlI);
        Gui.drawRect((int)(this.x + 4.0), (int)(this.y + 2.0), (int)(this.x + 4.3), (int)(this.y + this.height - 2.0), -5592406);
        if (this.extended) {
            Gui.drawRect((int)this.x, (int)(this.y + this.height - 1.0), (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 30));
        }
        if (NewGui.outline.getValue()) {
            if (this.extended) {
                XuluTessellator.drawRectOutline((int)this.x - 1, (int)this.y - 1, (int)(this.x + this.width) + 1, (int)(this.y + this.height), (int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), llllllllllllllllIlIlIllllIlllIIl);
            }
            else {
                XuluTessellator.drawRectOutline((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), 1.0, llllllllllllllllIlIlIllllIlllIIl);
            }
        }
        if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(this.title, (float)(this.x + 4.0), (float)(this.y + this.height / 2.0 - 4.0), -1052689);
        }
        else {
            FontUtil.drawStringWithShadow(this.title, this.x + 4.0, this.y + this.height / 2.0 - 4.0, -1052689);
        }
        if (this.extended && !this.Elements.isEmpty()) {
            double llllllllllllllllIlIlIlllllIIIIlI = this.y + this.height;
            final int llllllllllllllllIlIlIlllllIIIIIl = ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, NewGui.bgAlpha.getValue());
            final int llllllllllllllllIlIlIlllllIIIIII = ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 30);
            for (final ModuleButton llllllllllllllllIlIlIlllllIIIIll : this.Elements) {
                if (NewGui.rainbowgui.getValue()) {
                    final int updateRainbow = this.updateRainbow(this.rgb);
                    this.rgb = updateRainbow;
                    llllllllllllllllIlIlIllllIlllIIl = ColorUtils.changeAlpha(updateRainbow, 225);
                }
                Gui.drawRect((int)this.x, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width), (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0), llllllllllllllllIlIlIlllllIIIIIl);
                if (NewGui.outline.getValue()) {
                    if (this.Elements.indexOf(llllllllllllllllIlIlIlllllIIIIll) == this.Elements.size() - 1 && !llllllllllllllllIlIlIlllllIIIIll.extended) {
                        XuluTessellator.drawRectOutline((int)this.x - 1, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width) + 1, (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0) + 1, (int)this.x, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width), (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0), llllllllllllllllIlIlIllllIlllIIl);
                    }
                    else {
                        XuluTessellator.drawRectOutline((int)this.x - 1, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width) + 1, (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0), (int)this.x, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width), (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0), llllllllllllllllIlIlIllllIlllIIl);
                    }
                }
                if (NewGui.moduleSetting.getValue().equalsIgnoreCase("MiniButton")) {
                    Gui.drawRect((int)this.x + 2, (int)llllllllllllllllIlIlIlllllIIIIlI + 1, (int)(this.x + this.width) - 2, (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height), llllllllllllllllIlIlIlllllIIIIII);
                }
                llllllllllllllllIlIlIlllllIIIIll.x = this.x + 2.0;
                llllllllllllllllIlIlIlllllIIIIll.y = llllllllllllllllIlIlIlllllIIIIlI;
                llllllllllllllllIlIlIlllllIIIIll.width = this.width - 4.0;
                llllllllllllllllIlIlIlllllIIIIll.drawScreen(llllllllllllllllIlIlIllllIllIlll, llllllllllllllllIlIlIllllIllllIl, llllllllllllllllIlIlIllllIllllII);
                llllllllllllllllIlIlIlllllIIIIlI += llllllllllllllllIlIlIlllllIIIIll.height + 1.0;
                if (llllllllllllllllIlIlIlllllIIIIll.extended) {
                    for (final Element llllllllllllllllIlIlIlllllIIIlII : llllllllllllllllIlIlIlllllIIIIll.menuelements) {
                        if (!llllllllllllllllIlIlIlllllIIIlII.set.isVisible()) {
                            continue;
                        }
                        Gui.drawRect((int)this.x, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width), (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0), llllllllllllllllIlIlIlllllIIIIIl);
                        if (NewGui.outline.getValue()) {
                            if (this.Elements.indexOf(llllllllllllllllIlIlIlllllIIIIll) == this.Elements.size() - 1 && llllllllllllllllIlIlIlllllIIIIll.menuelements.indexOf(llllllllllllllllIlIlIlllllIIIlII) == llllllllllllllllIlIlIlllllIIIIll.menuelements.size() - 1) {
                                XuluTessellator.drawRectOutline((int)this.x - 1, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width) + 1, (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0) + 1, (int)this.x, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width), (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0), llllllllllllllllIlIlIllllIlllIIl);
                            }
                            else {
                                XuluTessellator.drawRectOutline((int)this.x - 1, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width) + 1, (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0), (int)this.x, (int)llllllllllllllllIlIlIlllllIIIIlI, (int)(this.x + this.width), (int)(llllllllllllllllIlIlIlllllIIIIlI + llllllllllllllllIlIlIlllllIIIIll.height + 1.0), llllllllllllllllIlIlIllllIlllIIl);
                            }
                        }
                        llllllllllllllllIlIlIlllllIIIIlI += llllllllllllllllIlIlIlllllIIIIll.height + 1.0;
                    }
                }
            }
            Gui.drawRect((int)this.x, (int)(llllllllllllllllIlIlIlllllIIIIlI + 1.0), (int)(this.x + this.width), (int)(llllllllllllllllIlIlIlllllIIIIlI + 1.0), llllllllllllllllIlIlIlllllIIIIIl);
        }
    }
    
    public Panel(final String llllllllllllllllIlIlIllllllIIIIl, final double llllllllllllllllIlIlIllllllIlIII, final double llllllllllllllllIlIlIlllllIlllll, final double llllllllllllllllIlIlIlllllIllllI, final double llllllllllllllllIlIlIlllllIlllIl, final boolean llllllllllllllllIlIlIllllllIIlII, final NewGUI llllllllllllllllIlIlIllllllIIIll) {
        this.Elements = new ArrayList<ModuleButton>();
        this.title = llllllllllllllllIlIlIllllllIIIIl;
        this.x = llllllllllllllllIlIlIllllllIlIII;
        this.y = llllllllllllllllIlIlIlllllIlllll;
        this.width = llllllllllllllllIlIlIlllllIllllI;
        this.height = llllllllllllllllIlIlIlllllIlllIl;
        this.extended = llllllllllllllllIlIlIllllllIIlII;
        this.dragging = false;
        this.visible = true;
        this.clickgui = llllllllllllllllIlIlIllllllIIIll;
        this.setup();
    }
    
    public int updateRainbow(final int llllllllllllllllIlIlIlllllIlIlII) {
        float llllllllllllllllIlIlIlllllIlIlIl = Color.RGBtoHSB(new Color(llllllllllllllllIlIlIlllllIlIlII).getRed(), new Color(llllllllllllllllIlIlIlllllIlIlII).getGreen(), new Color(llllllllllllllllIlIlIlllllIlIlII).getBlue(), null)[0];
        llllllllllllllllIlIlIlllllIlIlIl += NewGui.rainbowspeed.getValue() / 1000.0f;
        if (llllllllllllllllIlIlIlllllIlIlIl > 1.0f) {
            --llllllllllllllllIlIlIlllllIlIlIl;
        }
        return Color.HSBtoRGB(llllllllllllllllIlIlIlllllIlIlIl, Global.rainbowSaturation.getValue() / 255.0f, Global.rainbowLightness.getValue() / 255.0f);
    }
}
