package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.*;
import com.elementars.eclient.font.*;

public class CustomFontCommand extends Command
{
    public CustomFontCommand() {
        super("customfont", "Tweaks the custom font", new String[] { "default", "defaultxdolf", "defaultinfo", "currentfont", "currentfontxdolf", "fonts", "setfont", "setfontxdolf" });
    }
    
    @Override
    public void execute(final String[] lllIllIlIlllIII) {
        if (lllIllIlIlllIII.length > 1) {
            if (lllIllIlIlllIII[1].equalsIgnoreCase("help")) {
                this.showSyntax(lllIllIlIlllIII[0]);
                return;
            }
            if (lllIllIlIlllIII[1].equalsIgnoreCase("default")) {
                Xulu.setcFontRendererDefault();
            }
            if (lllIllIlIlllIII[1].equalsIgnoreCase("defaultxdolf")) {
                Xulu.setXdolfFontRendererDefault();
            }
            if (lllIllIlIlllIII[1].equalsIgnoreCase("defaultinfo")) {
                Command.sendChatMessage("Font: Verdana, Size: 18");
            }
            if (lllIllIlIlllIII[1].equalsIgnoreCase("currentfont")) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Font: ").append(CFontManager.customFont.getFont().getFontName()).append(", Size: ").append(CFontManager.customFont.getFont().getSize())));
            }
            if (lllIllIlIlllIII[1].equalsIgnoreCase("currentfontxdolf")) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Font: ").append(CFontManager.xFontRenderer.getFont().getFont().getName()).append(", Size: ").append(CFontManager.xFontRenderer.getFont().getFont().getSize())));
            }
            if (lllIllIlIlllIII[1].equalsIgnoreCase("fonts")) {
                Command.sendChatMessage("Fonts:");
                String lllIllIlIlllIll = "";
                boolean lllIllIlIlllIlI = true;
                final long lllIllIlIllIIll = (Object)Xulu.getFonts();
                final String lllIllIlIllIIlI = (String)lllIllIlIllIIll.length;
                for (float lllIllIlIllIIIl = 0; lllIllIlIllIIIl < lllIllIlIllIIlI; ++lllIllIlIllIIIl) {
                    final String lllIllIlIllllII = lllIllIlIllIIll[lllIllIlIllIIIl];
                    if (lllIllIlIlllIlI) {
                        lllIllIlIlllIll = lllIllIlIllllII;
                    }
                    else {
                        lllIllIlIlllIll = String.valueOf(new StringBuilder().append(lllIllIlIlllIll).append(", ").append(lllIllIlIllllII));
                    }
                    lllIllIlIlllIlI = false;
                }
                Command.sendChatMessage(lllIllIlIlllIll);
            }
            if (lllIllIlIlllIII[1].equalsIgnoreCase("setfont")) {
                if (lllIllIlIlllIII.length < 3) {
                    Command.sendChatMessage("Specify your font!");
                    return;
                }
                if (lllIllIlIlllIII.length < 4) {
                    Command.sendChatMessage("Specify your font size!");
                    return;
                }
                if (Integer.parseInt(lllIllIlIlllIII[3]) == 0) {
                    return;
                }
                Xulu.setCFontRenderer(lllIllIlIlllIII[2], Integer.parseInt(lllIllIlIlllIII[3]));
            }
            if (lllIllIlIlllIII[1].equalsIgnoreCase("setfontxdolf")) {
                if (lllIllIlIlllIII.length < 3) {
                    Command.sendChatMessage("Specify your font!");
                    return;
                }
                if (lllIllIlIlllIII.length < 4) {
                    Command.sendChatMessage("Specify your font size!");
                    return;
                }
                if (Integer.parseInt(lllIllIlIlllIII[3]) == 0) {
                    return;
                }
                Xulu.setXdolfFontRenderer(lllIllIlIlllIII[2], Integer.parseInt(lllIllIlIlllIII[3]));
            }
        }
        else {
            Command.sendChatMessage("Do .customfont help for options");
        }
    }
}
