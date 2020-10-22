package dev.xulu.clickgui.item.properties;

import dev.xulu.clickgui.item.*;
import dev.xulu.settings.*;
import dev.xulu.clickgui.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;

public class BooleanButton extends Button
{
    @Override
    public void toggle() {
        this.property.setValue(!this.property.getValue());
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    public BooleanButton(final Value llIIIlIllllIlll) {
        super(llIIIlIllllIlll.getName(), null);
        this.setValue(llIIIlIllllIlll);
        this.width = 15;
    }
    
    @Override
    public void mouseClicked(final int llIIIlIlllIIIll, final int llIIIlIlllIIIlI, final int llIIIlIlllIIlIl) {
        super.mouseClicked(llIIIlIlllIIIll, llIIIlIlllIIIlI, llIIIlIlllIIlIl);
        if (this.isHovering(llIIIlIlllIIIll, llIIIlIlllIIIlI) && ExeterGui.getSound()) {
            BooleanButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public boolean getState() {
        return this.property.getValue();
    }
    
    @Override
    public void drawScreen(final int llIIIlIlllIlllI, final int llIIIlIlllIllIl, final float llIIIlIllllIIII) {
        XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height, this.getState() ? ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 200) : 290805077, -1);
        if (this.isHovering(llIIIlIlllIlllI, llIIIlIlllIllIl)) {
            if (this.getState()) {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 25), -1);
            }
            else {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.WHITE, 25), -1);
            }
        }
        if (ExeterGui.getCF()) {
            Xulu.cFontRenderer.drawStringWithShadow(this.getLabel(), this.x + 2.3f, this.y + 3.0f, this.getState() ? -1 : -5592406);
        }
        else {
            BooleanButton.fontRenderer.drawStringWithShadow(this.getLabel(), this.x + 2.3f, this.y + 4.0f, this.getState() ? -1 : -5592406);
        }
    }
}
