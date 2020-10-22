package com.elementars.eclient.module.misc;

import com.elementars.eclient.event.events.*;
import net.minecraft.client.gui.inventory.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.module.*;
import net.minecraft.tileentity.*;
import com.elementars.eclient.command.*;
import java.io.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.text.*;

public class ColorSign extends Module
{
    @EventTarget
    private void onGui(final GuiScreenEvent.Displayed lllllllllllllllllIllIlllIIIlIllI) {
        if (lllllllllllllllllIllIlllIIIlIllI.getScreen() instanceof GuiEditSign && this.isToggled()) {
            lllllllllllllllllIllIlllIIIlIllI.setScreen((GuiScreen)new KamiGuiEditSign(((GuiEditSign)lllllllllllllllllIllIlllIIIlIllI.getScreen()).tileSign));
        }
    }
    
    public ColorSign() {
        super("ColorSign", "Allows writing with colors on signs", 0, Category.MISC, true);
    }
    
    private class KamiGuiEditSign extends GuiEditSign
    {
        public KamiGuiEditSign(final TileEntitySign lllllIllIIlllll) {
            super(lllllIllIIlllll);
        }
        
        protected void keyTyped(final char lllllIllIIIllIl, final int lllllIllIIIlIII) throws IOException {
            super.keyTyped(lllllIllIIIllIl, lllllIllIIIlIII);
            String lllllIllIIIlIll = ((TextComponentString)this.tileSign.signText[this.editLine]).getText();
            lllllIllIIIlIll = lllllIllIIIlIll.replace("&", String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("")));
            this.tileSign.signText[this.editLine] = (ITextComponent)new TextComponentString(lllllIllIIIlIll);
        }
        
        public void initGui() {
            super.initGui();
        }
        
        protected void actionPerformed(final GuiButton lllllIllIIlIIll) throws IOException {
            if (lllllIllIIlIIll.id == 0) {
                this.tileSign.signText[this.editLine] = (ITextComponent)new TextComponentString(this.tileSign.signText[this.editLine].getFormattedText().replaceAll(String.valueOf(new StringBuilder().append("(").append(Command.SECTIONSIGN()).append(")(.)")), "$1$1$2$2"));
            }
            super.actionPerformed(lllllIllIIlIIll);
        }
    }
}
