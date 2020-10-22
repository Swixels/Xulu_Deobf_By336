package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import org.lwjgl.input.*;
import com.elementars.eclient.*;
import com.elementars.eclient.macro.*;

public class MacroCommand extends Command
{
    public MacroCommand() {
        super("macro", "Manages macros", new String[] { "add" });
    }
    
    @Override
    public void execute(final String[] llllllllllllllllllIllIlIllIlIlII) {
        if (llllllllllllllllllIllIlIllIlIlII.length > 1 && llllllllllllllllllIllIlIllIlIlII[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllllIllIlIllIlIlII[0]);
            return;
        }
        if (llllllllllllllllllIllIlIllIlIlII.length < 2) {
            Command.sendChatMessage("Specify an option. Try doing .macro help to see command options");
            return;
        }
        if (llllllllllllllllllIllIlIllIlIlII[1].equalsIgnoreCase("add")) {
            if (llllllllllllllllllIllIlIllIlIlII.length < 3) {
                Command.sendChatMessage("Please specify a key.");
                return;
            }
            if (llllllllllllllllllIllIlIllIlIlII.length < 4) {
                Command.sendChatMessage("Needs more arguments!");
                return;
            }
            try {
                final String llllllllllllllllllIllIlIllIlllIl = llllllllllllllllllIllIlIllIlIlII[2];
                String llllllllllllllllllIllIlIllIlllII = "";
                final short llllllllllllllllllIllIlIllIlIIIl = (Object)llllllllllllllllllIllIlIllIlIlII;
                final char llllllllllllllllllIllIlIllIlIIII = (char)llllllllllllllllllIllIlIllIlIIIl.length;
                for (String llllllllllllllllllIllIlIllIIllll = (String)0; llllllllllllllllllIllIlIllIIllll < llllllllllllllllllIllIlIllIlIIII; ++llllllllllllllllllIllIlIllIIllll) {
                    final String llllllllllllllllllIllIlIllIllllI = llllllllllllllllllIllIlIllIlIIIl[llllllllllllllllllIllIlIllIIllll];
                    if (!llllllllllllllllllIllIlIllIllllI.equalsIgnoreCase("macro") && !llllllllllllllllllIllIlIllIllllI.equalsIgnoreCase("add") && !llllllllllllllllllIllIlIllIllllI.equalsIgnoreCase(llllllllllllllllllIllIlIllIlllIl)) {
                        llllllllllllllllllIllIlIllIlllII = String.valueOf(new StringBuilder().append(llllllllllllllllllIllIlIllIlllII).append(" ").append(llllllllllllllllllIllIlIllIllllI));
                    }
                }
                llllllllllllllllllIllIlIllIlllII = llllllllllllllllllIllIlIllIlllII.substring(1);
                final int llllllllllllllllllIllIlIllIllIll = Keyboard.getKeyIndex(llllllllllllllllllIllIlIllIlllIl.toUpperCase());
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Message = ").append(llllllllllllllllllIllIlIllIlllII).append(":Key = ").append(llllllllllllllllllIllIlIllIlllIl).append(":actual key = ").append(llllllllllllllllllIllIlIllIllIll)));
                if (Keyboard.getKeyName(llllllllllllllllllIllIlIllIllIll) != null) {
                    if (!Xulu.MACRO_MANAGER.getMacros().contains(new Macro(llllllllllllllllllIllIlIllIlllII, llllllllllllllllllIllIlIllIllIll))) {
                        Xulu.MACRO_MANAGER.addMacro(llllllllllllllllllIllIlIllIlllII, llllllllllllllllllIllIlIllIllIll);
                    }
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("Added Macro with the key ").append(Keyboard.getKeyName(llllllllllllllllllIllIlIllIllIll))));
                }
            }
            catch (StringIndexOutOfBoundsException llllllllllllllllllIllIlIllIllIlI) {
                Command.sendChatMessage("Unknown arguments!");
            }
        }
        else if (llllllllllllllllllIllIlIllIlIlII[1].equalsIgnoreCase("del")) {
            if (llllllllllllllllllIllIlIllIlIlII.length < 3) {
                Command.sendChatMessage("Please specify a key.");
                return;
            }
            try {
                final int llllllllllllllllllIllIlIllIllIIl = Keyboard.getKeyIndex(llllllllllllllllllIllIlIllIlIlII[2].toUpperCase());
                Xulu.MACRO_MANAGER.delMacro(llllllllllllllllllIllIlIllIllIIl);
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Deleted Macro with the key ").append(llllllllllllllllllIllIlIllIlIlII[2].toUpperCase())));
            }
            catch (Exception llllllllllllllllllIllIlIllIllIII) {
                Command.sendChatMessage("Error occured while removing macro!");
            }
        }
        else {
            Command.sendChatMessage("Unknown arguments!");
        }
    }
}
