package dev.xulu.clickgui.item;

import com.elementars.eclient.util.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import dev.xulu.clickgui.*;
import java.util.*;
import com.elementars.eclient.*;
import dev.xulu.settings.*;
import com.elementars.eclient.guirewrite.*;
import dev.xulu.clickgui.item.properties.*;
import com.elementars.eclient.module.*;
import java.io.*;

public class ModuleButton extends Button implements Helper
{
    private final /* synthetic */ Module module;
    private /* synthetic */ List<Item> items;
    private /* synthetic */ boolean subOpen;
    
    @Override
    public void mouseClicked(final int llllllllllllllllllIllIIlllllIIlI, final int llllllllllllllllllIllIIlllllIIIl, final int llllllllllllllllllIllIIlllllIIII) {
        super.mouseClicked(llllllllllllllllllIllIIlllllIIlI, llllllllllllllllllIllIIlllllIIIl, llllllllllllllllllIllIIlllllIIII);
        if (!this.items.isEmpty()) {
            if (llllllllllllllllllIllIIlllllIIII == 1 && this.isHovering(llllllllllllllllllIllIIlllllIIlI, llllllllllllllllllIllIIlllllIIIl)) {
                this.subOpen = !this.subOpen;
                if (ExeterGui.getSound()) {
                    ModuleButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
                }
            }
            if (this.subOpen) {
                for (final Item llllllllllllllllllIllIIlllllIllI : this.items) {
                    if (!llllllllllllllllllIllIIlllllIllI.property.isVisible()) {
                        continue;
                    }
                    llllllllllllllllllIllIIlllllIllI.mouseClicked(llllllllllllllllllIllIIlllllIIlI, llllllllllllllllllIllIIlllllIIIl, llllllllllllllllllIllIIlllllIIII);
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(final int llllllllllllllllllIllIIlllIllIIl, final int llllllllllllllllllIllIIlllIlIlll, final int llllllllllllllllllIllIIlllIlIllI) {
        this.items.forEach(llllllllllllllllllIllIIllIIIIlIl -> {
            if (llllllllllllllllllIllIIllIIIIlIl.property.isVisible()) {
                llllllllllllllllllIllIIllIIIIlIl.mouseReleased(llllllllllllllllllIllIIlllIllIIl, llllllllllllllllllIllIIlllIlIlll, llllllllllllllllllIllIIlllIlIllI);
            }
            return;
        });
        super.mouseReleased(llllllllllllllllllIllIIlllIllIIl, llllllllllllllllllIllIIlllIlIlll, llllllllllllllllllIllIIlllIlIllI);
    }
    
    @Override
    public int getHeight() {
        if (this.subOpen) {
            int llllllllllllllllllIllIIllIlIIllI = 14;
            for (final Item llllllllllllllllllIllIIllIlIlIII : this.items) {
                if (!llllllllllllllllllIllIIllIlIlIII.property.isVisible()) {
                    continue;
                }
                llllllllllllllllllIllIIllIlIIllI += llllllllllllllllllIllIIllIlIlIII.getHeight() + 1;
            }
            return llllllllllllllllllIllIIllIlIIllI + 2;
        }
        return 14;
    }
    
    public ModuleButton(final Module llllllllllllllllllIllIlIIIlIIIlI, final Panel llllllllllllllllllIllIlIIIlIIIIl) {
        super(llllllllllllllllllIllIlIIIlIIIlI.getName(), llllllllllllllllllIllIlIIIlIIIIl);
        this.items = new ArrayList<Item>();
        this.module = llllllllllllllllllIllIlIIIlIIIlI;
        if (Xulu.VALUE_MANAGER.getSettingsByMod(llllllllllllllllllIllIlIIIlIIIlI) != null) {
            for (final Value llllllllllllllllllIllIlIIIlIIlll : Xulu.VALUE_MANAGER.getSettingsByMod(llllllllllllllllllIllIlIIIlIIIlI)) {
                if (llllllllllllllllllIllIlIIIlIIlll.isToggle()) {
                    this.items.add(new BooleanButton(llllllllllllllllllIllIlIIIlIIlll));
                }
                else if (llllllllllllllllllIllIlIIIlIIlll.isNumber()) {
                    this.items.add(new NumberSlider(llllllllllllllllllIllIlIIIlIIlll));
                }
                else if (llllllllllllllllllIllIlIIIlIIlll.isMode()) {
                    this.items.add(new ModeButton(llllllllllllllllllIllIlIIIlIIlll));
                }
                else if (llllllllllllllllllIllIlIIIlIIlll.isEnum()) {
                    this.items.add(new EnumButton(llllllllllllllllllIllIlIIIlIIlll));
                }
                else if (llllllllllllllllllIllIlIIIlIIlll.isBind() && !(llllllllllllllllllIllIlIIIlIIlll.getParentMod() instanceof Element)) {
                    this.items.add(new BindButton(llllllllllllllllllIllIlIIIlIIlll));
                }
                else {
                    if (!llllllllllllllllllIllIlIIIlIIlll.isText()) {
                        continue;
                    }
                    this.items.add(new TextButton(llllllllllllllllllIllIlIIIlIIlll));
                }
            }
        }
    }
    
    @Override
    public boolean getState() {
        return this.module.isToggled();
    }
    
    @Override
    public void drawScreen(final int llllllllllllllllllIllIlIIIIIllIl, final int llllllllllllllllllIllIlIIIIIlIll, final float llllllllllllllllllIllIlIIIIIlIIl) {
        super.drawScreen(llllllllllllllllllIllIlIIIIIllIl, llllllllllllllllllIllIlIIIIIlIll, llllllllllllllllllIllIlIIIIIlIIl);
        if (!this.items.isEmpty()) {
            if (Xulu.VALUE_MANAGER.getValuesByMod(this.module) != null) {
                if (ExeterGui.getCF()) {
                    Xulu.cFontRenderer.drawStringWithShadow("...", this.x + this.width - Xulu.cFontRenderer.getStringWidth("...") - 3.0f, this.y + 3.0f, -1);
                }
                else {
                    ModuleButton.fontRenderer.drawStringWithShadow("...", this.x + this.width - ModuleButton.fontRenderer.getStringWidth("...") - 2.0f, this.y + 4.0f, -1);
                }
            }
            if (this.subOpen) {
                float llllllllllllllllllIllIlIIIIlIlIl = 1.0f;
                for (final Item llllllllllllllllllIllIlIIIIlIllI : this.items) {
                    if (!llllllllllllllllllIllIlIIIIlIllI.property.isVisible()) {
                        continue;
                    }
                    llllllllllllllllllIllIlIIIIlIlIl += 15.0f;
                    llllllllllllllllllIllIlIIIIlIllI.setLocation(this.x + 1.0f, this.y + llllllllllllllllllIllIlIIIIlIlIl);
                    llllllllllllllllllIllIlIIIIlIllI.setHeight(15);
                    llllllllllllllllllIllIlIIIIlIllI.setWidth(this.width - 9);
                    llllllllllllllllllIllIlIIIIlIllI.drawScreen(llllllllllllllllllIllIlIIIIIllIl, llllllllllllllllllIllIlIIIIIlIll, llllllllllllllllllIllIlIIIIIlIIl);
                }
            }
        }
    }
    
    @Override
    public void toggle() {
        if (this.module.getName().equalsIgnoreCase("HudEditor") || !this.module.getCategory().equals(Category.HUD)) {
            this.module.toggle();
        }
    }
    
    @Override
    public boolean keyTyped(final char llllllllllllllllllIllIIllIllllIl, final int llllllllllllllllllIllIIllIlllIll) throws IOException {
        for (final Item llllllllllllllllllIllIIlllIIIIIl : this.items) {
            if (!llllllllllllllllllIllIIlllIIIIIl.property.isVisible()) {
                continue;
            }
            try {
                if (llllllllllllllllllIllIIlllIIIIIl.keyTyped(llllllllllllllllllIllIIllIllllIl, llllllllllllllllllIllIIllIlllIll)) {
                    return true;
                }
                continue;
            }
            catch (IOException llllllllllllllllllIllIIlllIIIIll) {
                llllllllllllllllllIllIIlllIIIIll.printStackTrace();
            }
        }
        return super.keyTyped(llllllllllllllllllIllIIllIllllIl, llllllllllllllllllIllIIllIlllIll);
    }
}
