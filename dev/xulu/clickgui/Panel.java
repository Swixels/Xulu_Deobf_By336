package dev.xulu.clickgui;

import java.util.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import java.awt.*;
import com.elementars.eclient.module.core.*;
import dev.xulu.clickgui.item.*;
import com.elementars.eclient.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.util.*;

public abstract class Panel implements Helper, Labeled
{
    private final /* synthetic */ ArrayList<Item> items;
    public /* synthetic */ int rgb;
    private /* synthetic */ int height;
    private /* synthetic */ int x2;
    private /* synthetic */ int y2;
    public /* synthetic */ boolean drag;
    private /* synthetic */ boolean open;
    private /* synthetic */ int y;
    private /* synthetic */ int width;
    private /* synthetic */ int x;
    private final /* synthetic */ String label;
    
    public Panel(final String lIIlIIlIllllII, final int lIIlIIllIIIIII, final int lIIlIIlIlllIlI, final boolean lIIlIIlIlllllI) {
        this.items = new ArrayList<Item>();
        this.label = lIIlIIlIllllII;
        this.x = lIIlIIllIIIIII;
        this.y = lIIlIIlIlllIlI;
        this.width = 88;
        this.height = 18;
        this.open = lIIlIIlIlllllI;
        this.setupItems();
    }
    
    public void mouseClicked(final int lIIlIIlIIIllII, final int lIIlIIlIIIIlll, final int lIIlIIlIIIlIlI) {
        if (lIIlIIlIIIlIlI == 0 && this.isHovering(lIIlIIlIIIllII, lIIlIIlIIIIlll)) {
            this.x2 = this.x - lIIlIIlIIIllII;
            this.y2 = this.y - lIIlIIlIIIIlll;
            ClickGui.getClickGui().getPanels().forEach(lIIlIIIIIlIlll -> {
                if (lIIlIIIIIlIlll.drag) {
                    lIIlIIIIIlIlll.drag = false;
                }
                return;
            });
            this.drag = true;
            return;
        }
        if (lIIlIIlIIIlIlI == 1 && this.isHovering(lIIlIIlIIIllII, lIIlIIlIIIIlll)) {
            this.open = !this.open;
            if (ExeterGui.getSound()) {
                Panel.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            return;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(lIIlIIIIlIIIII -> lIIlIIIIlIIIII.mouseClicked(lIIlIIlIIIllII, lIIlIIlIIIIlll, lIIlIIlIIIlIlI));
    }
    
    public void setX(final int lIIlIIIlIIIIIl) {
        this.x = lIIlIIIlIIIIIl;
    }
    
    private boolean isHovering(final int lIIlIIIlIlIlII, final int lIIlIIIlIlIIll) {
        return lIIlIIIlIlIlII >= this.getX() && lIIlIIIlIlIlII <= this.getX() + this.getWidth() && lIIlIIIlIlIIll >= this.getY() && lIIlIIIlIlIIll <= this.getY() + this.getHeight() - (this.open ? 2 : 0);
    }
    
    public int updateRainbow(final int lIIlIIlIllIlIl) {
        float lIIlIIlIllIlII = Color.RGBtoHSB(new Color(lIIlIIlIllIlIl).getRed(), new Color(lIIlIIlIllIlIl).getGreen(), new Color(lIIlIIlIllIlIl).getBlue(), null)[0];
        lIIlIIlIllIlII += ExeterGui.getSpeed() / 1000.0f;
        if (lIIlIIlIllIlII > 1.0f) {
            --lIIlIIlIllIlII;
        }
        return Color.HSBtoRGB(lIIlIIlIllIlII, Global.rainbowSaturation.getValue() / 255.0f, Global.rainbowLightness.getValue() / 255.0f);
    }
    
    private float getTotalItemHeight() {
        float lIIlIIIlIIlIIl = 0.0f;
        for (final Item lIIlIIIlIIlIll : this.getItems()) {
            lIIlIIIlIIlIIl += lIIlIIIlIIlIll.getHeight() + 1.5f;
        }
        return lIIlIIIlIIlIIl;
    }
    
    public final ArrayList<Item> getItems() {
        return this.items;
    }
    
    public abstract void setupItems();
    
    public void addButton(final Button lIIlIIlIIIIIlI) {
        this.items.add(lIIlIIlIIIIIlI);
    }
    
    public void setY(final int lIIlIIIIlllIll) {
        this.y = lIIlIIIIlllIll;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setOpen(final boolean lIIlIIIllIIlll) {
        this.open = lIIlIIIllIIlll;
    }
    
    private void drag(final int lIIlIIlIIlIIll, final int lIIlIIlIIlIlIl) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + lIIlIIlIIlIIll;
        this.y = this.y2 + lIIlIIlIIlIlIl;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void mouseReleased(final int lIIlIIIlllIllI, final int lIIlIIIlllIlIl, final int lIIlIIIlllIlII) {
        if (lIIlIIIlllIlII == 0) {
            this.drag = false;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(lIIlIIIIlIllIl -> lIIlIIIIlIllIl.mouseReleased(lIIlIIIlllIllI, lIIlIIIlllIlIl, lIIlIIIlllIlII));
    }
    
    public boolean getOpen() {
        return this.open;
    }
    
    public void drawScreen(final int lIIlIIlIlIIIIl, final int lIIlIIlIlIIIII, final float lIIlIIlIIlllll) {
        this.rgb = Xulu.rgb;
        this.drag(lIIlIIlIlIIIIl, lIIlIIlIlIIIII);
        final float lIIlIIlIlIIIll = this.open ? (this.getTotalItemHeight() - 2.0f) : 0.0f;
        XuluTessellator.drawRectGradient((float)this.x, this.y - 1.5f, (float)(this.x + this.width), (float)(this.y + this.height - 6), ColorUtils.changeAlpha(ExeterGui.getRainbow() ? this.rgb : ColorUtil.getClickGUIColor().getRGB(), 225), -6710887);
        if (this.open) {
            RenderMethods.drawRect((float)this.x, this.y + 12.5f, (float)(this.x + this.width), this.open ? (this.y + this.height + lIIlIIlIlIIIll) : ((float)(this.y + this.height - 1)), 1996488704);
        }
        if (ExeterGui.getCF()) {
            Xulu.cFontRenderer.drawStringWithShadow(this.getLabel(), this.x + 3.0f, this.y + 1.0f, -1);
        }
        else {
            Panel.fontRenderer.drawStringWithShadow(this.getLabel(), this.x + 3.0f, this.y + 2.0f, -1);
        }
        if (this.open) {
            float lIIlIIlIlIlIII = this.getY() + this.getHeight() - 3.0f;
            for (final Item lIIlIIlIlIlIIl : this.getItems()) {
                this.rgb = this.updateRainbow(this.rgb);
                lIIlIIlIlIlIIl.setLocation(this.x + 2.0f, lIIlIIlIlIlIII);
                lIIlIIlIlIlIIl.setWidth(this.getWidth() - 4);
                lIIlIIlIlIlIIl.drawScreen(lIIlIIlIlIIIIl, lIIlIIlIlIIIII, lIIlIIlIIlllll);
                lIIlIIlIlIlIII += lIIlIIlIlIlIIl.getHeight() + 1.5f;
            }
        }
    }
    
    @Override
    public final String getLabel() {
        return this.label;
    }
}
