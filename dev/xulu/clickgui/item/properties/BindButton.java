package dev.xulu.clickgui.item.properties;

import dev.xulu.clickgui.item.*;
import java.io.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.util.*;
import org.lwjgl.input.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import dev.xulu.settings.*;
import dev.xulu.clickgui.*;

public class BindButton extends Button
{
    private /* synthetic */ boolean listening;
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    @Override
    public boolean keyTyped(final char llIIlIIlllIIIll, final int llIIlIIlllIIIlI) throws IOException {
        if (this.listening) {
            if (llIIlIIlllIIIlI != 1) {
                this.property.getParentMod().setKey(llIIlIIlllIIIlI);
            }
            else {
                this.property.getParentMod().setKey(0);
            }
            this.listening = false;
            return true;
        }
        return super.keyTyped(llIIlIIlllIIIll, llIIlIIlllIIIlI);
    }
    
    @Override
    public void toggle() {
    }
    
    @Override
    public boolean getState() {
        return false;
    }
    
    @Override
    public void drawScreen(final int llIIlIIlllllllI, final int llIIlIIllllllIl, final float llIIlIIllllllII) {
        XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height, this.getState() ? ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 200) : 290805077, -1);
        if (this.isHovering(llIIlIIlllllllI, llIIlIIllllllIl)) {
            if (this.getState()) {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 25), -1);
            }
            else {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.WHITE, 25), -1);
            }
        }
        final String llIIlIIlllllIll = this.listening ? "..." : Keyboard.getKeyName(this.property.getValue().getNum());
        if (ExeterGui.getCF()) {
            Xulu.cFontRenderer.drawStringWithShadow(String.format("%s§7 %s", this.getLabel(), llIIlIIlllllIll), this.x + 2.3f, this.y + 3.0f, this.getState() ? -1 : -1);
        }
        else {
            BindButton.fontRenderer.drawStringWithShadow(String.format("%s§7 %s", this.getLabel(), llIIlIIlllllIll), this.x + 2.3f, this.y + 4.0f, this.getState() ? -1 : -1);
        }
    }
    
    @Override
    public void mouseClicked(final int llIIlIIllllIIIl, final int llIIlIIllllIIII, final int llIIlIIlllIllll) {
        super.mouseClicked(llIIlIIllllIIIl, llIIlIIllllIIII, llIIlIIlllIllll);
        if (this.isHovering(llIIlIIllllIIIl, llIIlIIllllIIII)) {
            if (ExeterGui.getSound()) {
                BindButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            if (llIIlIIlllIllll == 0) {
                this.listening = true;
            }
        }
    }
    
    public BindButton(final Value llIIlIlIIIIIlII) {
        super(llIIlIlIIIIIlII.getName(), null);
        this.setValue(llIIlIlIIIIIlII);
    }
}
