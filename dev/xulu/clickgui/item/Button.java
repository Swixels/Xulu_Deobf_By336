package dev.xulu.clickgui.item;

import com.elementars.eclient.module.render.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import dev.xulu.clickgui.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;

public class Button extends Item implements Helper, Labeled
{
    private /* synthetic */ Panel parent;
    private /* synthetic */ boolean state;
    
    public Button(final String llllllllllllllllllllllIIIllIlllI, final Panel llllllllllllllllllllllIIIllIllIl) {
        super(llllllllllllllllllllllIIIllIlllI);
        this.parent = llllllllllllllllllllllIIIllIllIl;
        this.height = 15;
    }
    
    @Override
    public void mouseClicked(final int llllllllllllllllllllllIIIlIlllIl, final int llllllllllllllllllllllIIIlIlllII, final int llllllllllllllllllllllIIIlIllIll) {
        if (llllllllllllllllllllllIIIlIllIll == 0 && this.isHovering(llllllllllllllllllllllIIIlIlllIl, llllllllllllllllllllllIIIlIlllII)) {
            this.state = !this.state;
            this.toggle();
            if (ExeterGui.getSound()) {
                Button.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
        }
    }
    
    public boolean getState() {
        return this.state;
    }
    
    protected boolean isHovering(final int llllllllllllllllllllllIIIlIIIlll, final int llllllllllllllllllllllIIIlIIIllI) {
        for (final Panel llllllllllllllllllllllIIIlIIllII : ClickGui.getClickGui().getPanels()) {
            if (llllllllllllllllllllllIIIlIIllII.drag) {
                return false;
            }
        }
        return llllllllllllllllllllllIIIlIIIlll >= this.getX() && llllllllllllllllllllllIIIlIIIlll <= this.getX() + this.getWidth() && llllllllllllllllllllllIIIlIIIllI >= this.getY() && llllllllllllllllllllllIIIlIIIllI <= this.getY() + this.height;
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    public void toggle() {
    }
    
    @Override
    public void drawScreen(final int llllllllllllllllllllllIIIllIIlII, final int llllllllllllllllllllllIIIllIIlll, final float llllllllllllllllllllllIIIllIIllI) {
        XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, this.getState() ? ColorUtils.changeAlpha(ExeterGui.getRainbow() ? this.parent.rgb : ColorUtil.getClickGUIColor().getRGB(), 225) : 861230421, -1);
        if (this.isHovering(llllllllllllllllllllllIIIllIIlII, llllllllllllllllllllllIIIllIIlll)) {
            if (this.getState()) {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 30), -1);
            }
            else {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.WHITE, 30), -1);
            }
        }
        if (ExeterGui.getCF()) {
            Xulu.cFontRenderer.drawStringWithShadow(this.getLabel(), this.x + 2.3f, this.y + 3.0f, this.getState() ? -1 : -5592406);
        }
        else {
            Button.fontRenderer.drawStringWithShadow(this.getLabel(), this.x + 2.3f, this.y + 4.0f, this.getState() ? -1 : -5592406);
        }
    }
}
