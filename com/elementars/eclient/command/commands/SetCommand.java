package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.*;
import dev.xulu.settings.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;

public class SetCommand extends Command
{
    @Override
    public void execute(final String[] llllllllllllllllllIlllIIlIIIIlII) {
        if (llllllllllllllllllIlllIIlIIIIlII.length < 2) {
            Command.sendChatMessage("Please specify a module");
            return;
        }
        if (llllllllllllllllllIlllIIlIIIIlII[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllllIlllIIlIIIIlII[0]);
            return;
        }
        if (llllllllllllllllllIlllIIlIIIIlII.length < 3) {
            Command.sendChatMessage("Please specify which setting you would like to change");
            return;
        }
        if (llllllllllllllllllIlllIIlIIIIlII.length < 4) {
            Command.sendChatMessage("Please enter a value you would like to set");
            return;
        }
        final Module llllllllllllllllllIlllIIlIIIIIll = Xulu.MODULE_MANAGER.getModuleByName(llllllllllllllllllIlllIIlIIIIlII[1]);
        if (llllllllllllllllllIlllIIlIIIIIll == null) {
            Command.sendChatMessage("Module not found!");
            return;
        }
        Value llllllllllllllllllIlllIIlIIIIIlI = null;
        for (final Value llllllllllllllllllIlllIIlIIIIlll : Xulu.VALUE_MANAGER.getValuesByMod(llllllllllllllllllIlllIIlIIIIIll)) {
            if (llllllllllllllllllIlllIIlIIIIlll.getName().equalsIgnoreCase(llllllllllllllllllIlllIIlIIIIlII[2])) {
                llllllllllllllllllIlllIIlIIIIIlI = llllllllllllllllllIlllIIlIIIIlll;
            }
        }
        if (llllllllllllllllllIlllIIlIIIIIlI == null) {
            Command.sendChatMessage("Setting not found!");
            return;
        }
        if (llllllllllllllllllIlllIIlIIIIIlI.getParentMod() != llllllllllllllllllIlllIIlIIIIIll) {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllllIlllIIlIIIIIll.getDisplayName()).append(" has no setting ").append(llllllllllllllllllIlllIIlIIIIIlI.getName())));
            return;
        }
        try {
            if (llllllllllllllllllIlllIIlIIIIIlI.isToggle()) {
                llllllllllllllllllIlllIIlIIIIIlI.setValue(Boolean.parseBoolean(llllllllllllllllllIlllIIlIIIIlII[3]));
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Set ").append(llllllllllllllllllIlllIIlIIIIIlI.getName()).append(" to ").append(llllllllllllllllllIlllIIlIIIIlII[3].toUpperCase())));
            }
            else if (llllllllllllllllllIlllIIlIIIIIlI.isMode()) {
                if (llllllllllllllllllIlllIIlIIIIIlI.getOptions().contains(llllllllllllllllllIlllIIlIIIIlII[3])) {
                    llllllllllllllllllIlllIIlIIIIIlI.setValue(llllllllllllllllllIlllIIlIIIIlII[3]);
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("Set ").append(llllllllllllllllllIlllIIlIIIIIlI.getName()).append(" to ").append(llllllllllllllllllIlllIIlIIIIlII[3].toUpperCase())));
                }
                else {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("Option ").append(llllllllllllllllllIlllIIlIIIIlII[3]).append(" not found!")));
                }
            }
            else if (llllllllllllllllllIlllIIlIIIIIlI.isNumber()) {
                if (Wrapper.getFileManager().determineNumber(llllllllllllllllllIlllIIlIIIIIlI.getValue()).equalsIgnoreCase("INTEGER")) {
                    llllllllllllllllllIlllIIlIIIIIlI.setValue(Integer.parseInt(llllllllllllllllllIlllIIlIIIIlII[3]));
                }
                else if (Wrapper.getFileManager().determineNumber(llllllllllllllllllIlllIIlIIIIIlI.getValue()).equalsIgnoreCase("FLOAT")) {
                    llllllllllllllllllIlllIIlIIIIIlI.setValue(Float.parseFloat(llllllllllllllllllIlllIIlIIIIlII[3]));
                }
                else if (Wrapper.getFileManager().determineNumber(llllllllllllllllllIlllIIlIIIIIlI.getValue()).equalsIgnoreCase("DOUBLE")) {
                    llllllllllllllllllIlllIIlIIIIIlI.setValue(Double.parseDouble(llllllllllllllllllIlllIIlIIIIlII[3]));
                }
                else {
                    Command.sendChatMessage("UNKNOWN NUMBER VALUE");
                }
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Set ").append(llllllllllllllllllIlllIIlIIIIIlI.getName()).append(" to ").append(llllllllllllllllllIlllIIlIIIIlII[3])));
            }
        }
        catch (Exception llllllllllllllllllIlllIIlIIIIllI) {
            llllllllllllllllllIlllIIlIIIIllI.printStackTrace();
            Command.sendChatMessage("Error occured when setting value.");
        }
    }
    
    public SetCommand() {
        super("set", "Sets the settings of a module", new String[] { Xulu.MODULE_MANAGER.getModules().toString() });
    }
}
