package dev.xulu.newgui.elements.menu;

import com.elementars.eclient.module.render.*;
import dev.xulu.newgui.elements.*;
import dev.xulu.settings.*;
import com.elementars.eclient.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import dev.xulu.newgui.util.*;
import net.minecraft.util.math.*;

public class ElementSlider extends Element
{
    public /* synthetic */ boolean dragging;
    
    @Override
    public boolean mouseClicked(final int lIIIIlIIIIll, final int lIIIIlIIIIlI, final int lIIIIlIIIIIl) {
        if (lIIIIlIIIIIl == 0) {
            if (NewGui.sliderSetting.getValue().equalsIgnoreCase("Line")) {
                if (!this.isSliderHovered(lIIIIlIIIIll, lIIIIlIIIIlI)) {
                    return super.mouseClicked(lIIIIlIIIIll, lIIIIlIIIIlI, lIIIIlIIIIIl);
                }
            }
            else if (!this.isHovered(lIIIIlIIIIll, lIIIIlIIIIlI)) {
                return super.mouseClicked(lIIIIlIIIIll, lIIIIlIIIIlI, lIIIIlIIIIIl);
            }
            this.dragging = true;
            return true;
        }
        return super.mouseClicked(lIIIIlIIIIll, lIIIIlIIIIlI, lIIIIlIIIIIl);
    }
    
    @Override
    public void mouseReleased(final int lIIIIIlllllI, final int lIIIIIllllIl, final int lIIIIIllllII) {
        this.dragging = false;
    }
    
    public ElementSlider(final ModuleButton lIIIlIllIIll, final Value lIIIlIlIllll) {
        this.parent = lIIIlIllIIll;
        this.set = lIIIlIlIllll;
        this.dragging = false;
        super.setup();
    }
    
    @Override
    public void drawScreen(final int lIIIIlIlIllI, final int lIIIIlIlIlIl, final float lIIIIlIllIII) {
        if (NewGui.sliderSetting.getValue().equalsIgnoreCase("Line")) {
            String lIIIlIIIIlIl = null;
            if (this.set.getValue() instanceof Integer) {
                final String lIIIlIlIIIll = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Short) {
                final String lIIIlIlIIIlI = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Long) {
                final String lIIIlIlIIIIl = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Float) {
                final String lIIIlIlIIIII = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Double) {
                final String lIIIlIIlllll = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else {
                lIIIlIIIIlIl = "";
            }
            final boolean lIIIlIIIIlII = this.isSliderHovered(lIIIIlIlIllI, lIIIIlIlIlIl) || this.dragging;
            Color lIIIlIIIIIll = ColorUtil.getClickGUIColor();
            if (NewGui.rainbowgui.getValue()) {
                lIIIlIIIIIll = new Color(Xulu.rgb).darker();
            }
            final int lIIIlIIIIIlI = new Color(lIIIlIIIIIll.getRed(), lIIIlIIIIIll.getGreen(), lIIIlIIIIIll.getBlue(), lIIIlIIIIlII ? 225 : 225).getRGB();
            final int lIIIlIIIIIIl = new Color(lIIIlIIIIIll.getRed(), lIIIlIIIIIll.getGreen(), lIIIlIIIIIll.getBlue(), lIIIlIIIIlII ? 225 : 225).getRGB();
            double lIIIlIIIIIII = 0.0;
            if (this.set.getValue() instanceof Integer) {
                final double lIIIlIIllllI = this.set.getValue();
                final double lIIIlIIlllIl = (lIIIlIIllllI - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
            }
            else if (this.set.getValue() instanceof Short) {
                final double lIIIlIIlllII = this.set.getValue();
                final double lIIIlIIllIll = (lIIIlIIlllII - this.set.getMin()) / ((short)this.set.getMax() - (short)this.set.getMin());
            }
            else if (this.set.getValue() instanceof Long) {
                final double lIIIlIIllIlI = this.set.getValue();
                final double lIIIlIIllIIl = (lIIIlIIllIlI - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
            }
            else if (this.set.getValue() instanceof Float) {
                final double lIIIlIIllIII = (this.set.getValue() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
            }
            else if (this.set.getValue() instanceof Double) {
                final double lIIIlIIlIlll = (this.set.getValue() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
            }
            else {
                lIIIlIIIIIII = 0.0;
            }
            Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 30));
            if (NewGui.customfont.getValue()) {
                Xulu.cFontRenderer.drawString(this.setstrg, (float)(this.x + 2.0), (float)(this.y + 2.0), -1);
                Xulu.cFontRenderer.drawString(lIIIlIIIIlIl, (float)(this.x + this.width - Xulu.cFontRenderer.getStringWidth(lIIIlIIIIlIl)), (float)(this.y + 2.0), -1);
            }
            else {
                FontUtil.drawString(this.setstrg, this.x + 2.0, this.y + 2.0, -1);
                FontUtil.drawString(lIIIlIIIIlIl, this.x + this.width - FontUtil.getStringWidth(lIIIlIIIIlIl), this.y + 2.0, -1);
            }
            Gui.drawRect((int)this.x, (int)(this.y + 12.0), (int)(this.x + this.width), (int)(this.y + 13.5), ColorUtils.changeAlpha(-15724528, 30));
            Gui.drawRect((int)this.x, (int)(this.y + 12.0), (int)(this.x + lIIIlIIIIIII * this.width), (int)(this.y + 13.5), lIIIlIIIIIlI);
            if (lIIIlIIIIIII > 0.0 && lIIIlIIIIIII < 1.0) {
                Gui.drawRect((int)(this.x + lIIIlIIIIIII * this.width - 1.0), (int)(this.y + 12.0), (int)(this.x + Math.min(lIIIlIIIIIII * this.width, this.width)), (int)(this.y + 13.5), lIIIlIIIIIIl);
            }
            if (this.dragging) {
                Double lIIIlIIIIlll = null;
                if (this.set.getValue() instanceof Integer) {
                    final int lIIIlIIlIllI = this.set.getMax() - this.set.getMin();
                    final Double lIIIlIIlIlIl = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIlIIlIllI;
                }
                else if (this.set.getValue() instanceof Short) {
                    final short lIIIlIIlIlII = (short)((short)this.set.getMax() - (short)this.set.getMin());
                    final Double lIIIlIIlIIll = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIlIIlIlII;
                }
                else if (this.set.getValue() instanceof Long) {
                    final long lIIIlIIlIIlI = this.set.getMax() - this.set.getMin();
                    final Double lIIIlIIlIIIl = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIlIIlIIlI;
                }
                else if (this.set.getValue() instanceof Float) {
                    final float lIIIlIIlIIII = this.set.getMax() - this.set.getMin();
                    final Double lIIIlIIIllll = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIlIIlIIII;
                }
                else if (this.set.getValue() instanceof Double) {
                    final double lIIIlIIIlllI = this.set.getMax() - this.set.getMin();
                    final Double lIIIlIIIllIl = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIlIIIlllI;
                }
                else {
                    lIIIlIIIIlll = 0.0;
                }
                Number lIIIlIIIIllI = null;
                if (this.set.getValue() instanceof Integer) {
                    final Number lIIIlIIIllII = lIIIlIIIIlll.intValue();
                }
                else if (this.set.getValue() instanceof Short) {
                    final Number lIIIlIIIlIll = lIIIlIIIIlll.shortValue();
                }
                else if (this.set.getValue() instanceof Long) {
                    final Number lIIIlIIIlIlI = lIIIlIIIIlll.longValue();
                }
                else if (this.set.getValue() instanceof Float) {
                    final Number lIIIlIIIlIIl = lIIIlIIIIlll.floatValue();
                }
                else if (this.set.getValue() instanceof Double) {
                    final Number lIIIlIIIlIII = (double)lIIIlIIIIlll;
                }
                else {
                    lIIIlIIIIllI = 0;
                }
                this.set.setValue(lIIIlIIIIllI);
            }
        }
        else if (NewGui.sliderSetting.getValue().equalsIgnoreCase("Box")) {
            String lIIIIllIIIIl = null;
            if (this.set.getValue() instanceof Integer) {
                final String lIIIIlllllll = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Short) {
                final String lIIIIllllllI = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Long) {
                final String lIIIIlllllIl = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Float) {
                final String lIIIIlllllII = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Double) {
                final String lIIIIllllIll = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getValue() * 100.0) / 100.0));
            }
            else {
                lIIIIllIIIIl = "";
            }
            boolean b = false;
            Label_2169: {
                Label_2164: {
                    if (NewGui.sliderSetting.getValue().equalsIgnoreCase("Line")) {
                        if (this.isSliderHovered(lIIIIlIlIllI, lIIIIlIlIlIl)) {
                            break Label_2164;
                        }
                    }
                    else if (this.isHovered(lIIIIlIlIllI, lIIIIlIlIlIl)) {
                        break Label_2164;
                    }
                    if (!this.dragging) {
                        b = false;
                        break Label_2169;
                    }
                }
                b = true;
            }
            final boolean lIIIIllIIIII = b;
            Color lIIIIlIlllll = ColorUtil.getClickGUIColor().darker();
            if (NewGui.rainbowgui.getValue()) {
                lIIIIlIlllll = new Color(Xulu.rgb).darker();
            }
            final int lIIIIlIllllI = new Color(lIIIIlIlllll.getRed(), lIIIIlIlllll.getGreen(), lIIIIlIlllll.getBlue(), lIIIIllIIIII ? 50 : 30).getRGB();
            final int lIIIIlIlllIl = new Color(lIIIIlIlllll.getRed(), lIIIIlIlllll.getGreen(), lIIIIlIlllll.getBlue(), lIIIIllIIIII ? 255 : 230).getRGB();
            double lIIIIlIlllII = 0.0;
            if (this.set.getValue() instanceof Integer) {
                final double lIIIIllllIlI = this.set.getValue();
                final double lIIIIllllIIl = (lIIIIllllIlI - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
            }
            else if (this.set.getValue() instanceof Short) {
                final double lIIIIllllIII = this.set.getValue();
                final double lIIIIlllIlll = (lIIIIllllIII - this.set.getMin()) / ((short)this.set.getMax() - (short)this.set.getMin());
            }
            else if (this.set.getValue() instanceof Long) {
                final double lIIIIlllIllI = this.set.getValue();
                final double lIIIIlllIlIl = (lIIIIlllIllI - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
            }
            else if (this.set.getValue() instanceof Float) {
                final double lIIIIlllIlII = (this.set.getValue() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
            }
            else if (this.set.getValue() instanceof Double) {
                final double lIIIIlllIIll = (this.set.getValue() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
            }
            else {
                lIIIIlIlllII = 0.0;
            }
            Gui.drawRect((int)(this.x + lIIIIlIlllII * this.width), (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
            Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + lIIIIlIlllII * this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(lIIIIlIllllI, 225));
            if (NewGui.customfont.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(this.setstrg, (float)(this.x + 2.0), (float)(this.y + 2.0), -1);
                Xulu.cFontRenderer.drawStringWithShadow(lIIIIllIIIIl, (float)(this.x + this.width - Xulu.cFontRenderer.getStringWidth(lIIIIllIIIIl)), (float)(this.y + 2.0), -1);
            }
            else {
                FontUtil.drawStringWithShadow(this.setstrg, this.x + 2.0, this.y + 2.0, -1);
                FontUtil.drawStringWithShadow(lIIIIllIIIIl, this.x + this.width - FontUtil.getStringWidth(lIIIIllIIIIl), this.y + 2.0, -1);
            }
            if (this.dragging) {
                Double lIIIIllIIIll = null;
                if (this.set.getValue() instanceof Integer) {
                    final int lIIIIlllIIlI = this.set.getMax() - this.set.getMin();
                    final Double lIIIIlllIIIl = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIIlllIIlI;
                }
                else if (this.set.getValue() instanceof Short) {
                    final short lIIIIlllIIII = (short)((short)this.set.getMax() - (short)this.set.getMin());
                    final Double lIIIIllIllll = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIIlllIIII;
                }
                else if (this.set.getValue() instanceof Long) {
                    final long lIIIIllIlllI = this.set.getMax() - this.set.getMin();
                    final Double lIIIIllIllIl = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIIllIlllI;
                }
                else if (this.set.getValue() instanceof Float) {
                    final float lIIIIllIllII = this.set.getMax() - this.set.getMin();
                    final Double lIIIIllIlIll = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIIllIllII;
                }
                else if (this.set.getValue() instanceof Double) {
                    final double lIIIIllIlIlI = this.set.getMax() - this.set.getMin();
                    final Double lIIIIllIlIIl = this.set.getMin() + MathHelper.clamp((lIIIIlIlIllI - this.x) / this.width, 0.0, 1.0) * lIIIIllIlIlI;
                }
                else {
                    lIIIIllIIIll = 0.0;
                }
                Number lIIIIllIIIlI = null;
                if (this.set.getValue() instanceof Integer) {
                    final Number lIIIIllIlIII = lIIIIllIIIll.intValue();
                }
                else if (this.set.getValue() instanceof Short) {
                    final Number lIIIIllIIlll = lIIIIllIIIll.shortValue();
                }
                else if (this.set.getValue() instanceof Long) {
                    final Number lIIIIllIIllI = lIIIIllIIIll.longValue();
                }
                else if (this.set.getValue() instanceof Float) {
                    final Number lIIIIllIIlIl = lIIIIllIIIll.floatValue();
                }
                else if (this.set.getValue() instanceof Double) {
                    final Number lIIIIllIIlII = (double)lIIIIllIIIll;
                }
                else {
                    lIIIIllIIIlI = 0;
                }
                this.set.setValue(lIIIIllIIIlI);
            }
        }
    }
    
    public boolean isSliderHovered(final int lIIIIIllIllI, final int lIIIIIllIIlI) {
        return lIIIIIllIllI >= this.x && lIIIIIllIllI <= this.x + this.width && lIIIIIllIIlI >= this.y + 11.0 && lIIIIIllIIlI <= this.y + 14.0;
    }
}
