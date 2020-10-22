package dev.xulu.clickgui.item.properties;

import dev.xulu.clickgui.item.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;
import net.minecraft.util.math.*;
import dev.xulu.clickgui.*;
import dev.xulu.settings.*;

public class NumberSlider extends Item implements Helper
{
    private /* synthetic */ boolean dragging;
    
    @Override
    public void mouseClicked(final int lllllllllllllllllllIIlIlIlllIIlI, final int lllllllllllllllllllIIlIlIlllIIIl, final int lllllllllllllllllllIIlIlIllIIlll) {
        if (this.isHovering(lllllllllllllllllllIIlIlIlllIIlI, lllllllllllllllllllIIlIlIlllIIIl) && lllllllllllllllllllIIlIlIllIIlll == 0) {
            if (ExeterGui.getSound()) {
                NumberSlider.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            this.dragging = true;
            return;
        }
        super.mouseClicked(lllllllllllllllllllIIlIlIlllIIlI, lllllllllllllllllllIIlIlIlllIIIl, lllllllllllllllllllIIlIlIllIIlll);
    }
    
    @Override
    public void drawScreen(final int lllllllllllllllllllIIlIllIIIlIII, final int lllllllllllllllllllIIlIllIIIIllI, final float lllllllllllllllllllIIlIllIIlIIII) {
        String lllllllllllllllllllIIlIllIIIlllI = null;
        if (this.property.getValue() instanceof Integer) {
            final String lllllllllllllllllllIIlIlllIIIlII = String.valueOf(new StringBuilder().append("").append(Math.round(this.property.getValue() * 100.0) / 100.0));
        }
        else if (this.property.getValue() instanceof Short) {
            final String lllllllllllllllllllIIlIlllIIIIll = String.valueOf(new StringBuilder().append("").append(Math.round(this.property.getValue() * 100.0) / 100.0));
        }
        else if (this.property.getValue() instanceof Long) {
            final String lllllllllllllllllllIIlIllIlllIlI = String.valueOf(new StringBuilder().append("").append(Math.round(this.property.getValue() * 100.0) / 100.0));
        }
        else if (this.property.getValue() instanceof Float) {
            final String lllllllllllllllllllIIlIllIlllIIl = String.valueOf(new StringBuilder().append("").append(Math.round(this.property.getValue() * 100.0) / 100.0));
        }
        else if (this.property.getValue() instanceof Double) {
            final String lllllllllllllllllllIIlIllIlllIII = String.valueOf(new StringBuilder().append("").append(Math.round(this.property.getValue() * 100.0) / 100.0));
        }
        else {
            lllllllllllllllllllIIlIllIIIlllI = "";
        }
        double lllllllllllllllllllIIlIllIIIllII = 0.0;
        if (this.property.getValue() instanceof Integer) {
            final double lllllllllllllllllllIIlIllIllIlll = this.property.getValue();
            final double lllllllllllllllllllIIlIllIllIllI = (lllllllllllllllllllIIlIllIllIlll - this.property.getMin()) / (this.property.getMax() - this.property.getMin());
        }
        else if (this.property.getValue() instanceof Short) {
            final double lllllllllllllllllllIIlIllIllIlIl = this.property.getValue();
            final double lllllllllllllllllllIIlIllIllIlII = (lllllllllllllllllllIIlIllIllIlIl - this.property.getMin()) / ((short)this.property.getMax() - (short)this.property.getMin());
        }
        else if (this.property.getValue() instanceof Long) {
            final double lllllllllllllllllllIIlIllIllIIll = this.property.getValue();
            final double lllllllllllllllllllIIlIllIllIIlI = (lllllllllllllllllllIIlIllIllIIll - this.property.getMin()) / (this.property.getMax() - this.property.getMin());
        }
        else if (this.property.getValue() instanceof Float) {
            final double lllllllllllllllllllIIlIllIllIIII = (this.property.getValue() - this.property.getMin()) / (this.property.getMax() - this.property.getMin());
        }
        else if (this.property.getValue() instanceof Double) {
            final double lllllllllllllllllllIIlIllIlIlllI = (this.property.getValue() - this.property.getMin()) / (this.property.getMax() - this.property.getMin());
        }
        else {
            lllllllllllllllllllIIlIllIIIllII = 0.0;
        }
        XuluTessellator.drawRectGradient(this.x, this.y, this.x + lllllllllllllllllllIIlIllIIIllII * (this.width + 7.4f), this.y + this.height, ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 200), -1);
        if (this.isHovering(lllllllllllllllllllIIlIllIIIlIII, lllllllllllllllllllIIlIllIIIIllI)) {
            XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 25), -1);
        }
        if (ExeterGui.getCF()) {
            Xulu.cFontRenderer.drawStringWithShadow(String.format("%s§7 %s", this.getLabel(), lllllllllllllllllllIIlIllIIIlllI), this.x + 2.3f, this.y + 3.0f, -1);
        }
        else {
            NumberSlider.fontRenderer.drawStringWithShadow(String.format("%s§7 %s", this.getLabel(), lllllllllllllllllllIIlIllIIIlllI), this.x + 2.3f, this.y + 4.0f, -1);
        }
        if (this.dragging) {
            Number lllllllllllllllllllIIlIllIIlIlll = null;
            if (this.property.getValue() instanceof Integer) {
                final int lllllllllllllllllllIIlIllIlIllII = this.property.getMax() - this.property.getMin();
                final Number lllllllllllllllllllIIlIllIlIlIll = this.property.getMin() + MathHelper.clamp((lllllllllllllllllllIIlIllIIIlIII - this.x) / (this.width + 7.4f), 0.0f, 1.0f) * lllllllllllllllllllIIlIllIlIllII;
            }
            else if (this.property.getValue() instanceof Short) {
                final short lllllllllllllllllllIIlIllIlIlIlI = (short)((short)this.property.getMax() - (short)this.property.getMin());
                final Number lllllllllllllllllllIIlIllIlIlIIl = this.property.getMin() + MathHelper.clamp((lllllllllllllllllllIIlIllIIIlIII - this.x) / (this.width + 7.4f), 0.0f, 1.0f) * lllllllllllllllllllIIlIllIlIlIlI;
            }
            else if (this.property.getValue() instanceof Long) {
                final long lllllllllllllllllllIIlIllIlIlIII = this.property.getMax() - this.property.getMin();
                final Number lllllllllllllllllllIIlIllIlIIlll = this.property.getMin() + MathHelper.clamp((lllllllllllllllllllIIlIllIIIlIII - this.x) / (this.width + 7.4f), 0.0f, 1.0f) * lllllllllllllllllllIIlIllIlIlIII;
            }
            else if (this.property.getValue() instanceof Float) {
                final float lllllllllllllllllllIIlIllIlIIllI = this.property.getMax() - this.property.getMin();
                final Number lllllllllllllllllllIIlIllIlIIlIl = this.property.getMin() + MathHelper.clamp((lllllllllllllllllllIIlIllIIIlIII - this.x) / (this.width + 7.4f), 0.0f, 1.0f) * lllllllllllllllllllIIlIllIlIIllI;
            }
            else if (this.property.getValue() instanceof Double) {
                final double lllllllllllllllllllIIlIllIlIIIll = this.property.getMax() - this.property.getMin();
                final Number lllllllllllllllllllIIlIllIlIIIlI = this.property.getMin() + MathHelper.clamp((lllllllllllllllllllIIlIllIIIlIII - this.x) / (this.width + 7.4f), 0.0f, 1.0f) * lllllllllllllllllllIIlIllIlIIIll;
            }
            else {
                lllllllllllllllllllIIlIllIIlIlll = 0.0;
            }
            Number lllllllllllllllllllIIlIllIIlIlIl = null;
            if (this.property.getValue() instanceof Integer) {
                final Number lllllllllllllllllllIIlIllIlIIIIl = lllllllllllllllllllIIlIllIIlIlll.intValue();
            }
            else if (this.property.getValue() instanceof Short) {
                final Number lllllllllllllllllllIIlIllIIlllll = lllllllllllllllllllIIlIllIIlIlll.shortValue();
            }
            else if (this.property.getValue() instanceof Long) {
                final Number lllllllllllllllllllIIlIllIIlllIl = lllllllllllllllllllIIlIllIIlIlll.longValue();
            }
            else if (this.property.getValue() instanceof Float) {
                final Number lllllllllllllllllllIIlIllIIllIll = lllllllllllllllllllIIlIllIIlIlll.floatValue();
            }
            else if (this.property.getValue() instanceof Double) {
                final Number lllllllllllllllllllIIlIllIIllIIl = lllllllllllllllllllIIlIllIIlIlll.doubleValue();
            }
            else {
                lllllllllllllllllllIIlIllIIlIlIl = 0;
            }
            this.property.setValue(lllllllllllllllllllIIlIllIIlIlIl);
        }
    }
    
    private boolean isHovering(final int lllllllllllllllllllIIlIlIlIIllIl, final int lllllllllllllllllllIIlIlIlIIllII) {
        for (final Panel lllllllllllllllllllIIlIlIlIIllll : ClickGui.getClickGui().getPanels()) {
            if (lllllllllllllllllllIIlIlIlIIllll.drag) {
                return false;
            }
        }
        return lllllllllllllllllllIIlIlIlIIllIl >= this.getX() && lllllllllllllllllllIIlIlIlIIllIl <= this.getX() + this.getWidth() && lllllllllllllllllllIIlIlIlIIllII >= this.getY() && lllllllllllllllllllIIlIlIlIIllII <= this.getY() + this.height;
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    private float getValueWidth() {
        return this.property.getMax().floatValue() - this.property.getMin().floatValue() + this.property.getValue().floatValue();
    }
    
    public NumberSlider(final Value lllllllllllllllllllIIllIIIIIlllI) {
        super(lllllllllllllllllllIIllIIIIIlllI.getName());
        this.setValue(lllllllllllllllllllIIllIIIIIlllI);
    }
    
    @Override
    public void mouseReleased(final int lllllllllllllllllllIIlIlIllIIIII, final int lllllllllllllllllllIIlIlIlIlllll, final int lllllllllllllllllllIIlIlIlIllllI) {
        this.dragging = false;
    }
}
