package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import dev.xulu.newgui.elements.*;
import com.elementars.eclient.dummy.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;
import java.util.*;
import java.util.function.*;
import dev.xulu.newgui.*;

public class DummyCommand extends Command
{
    @Override
    public void execute(final String[] llllllllllllllllllIIlllIlllllIlI) {
        if (llllllllllllllllllIIlllIlllllIlI.length < 2) {
            Command.sendChatMessage("Try .enemy add or .enemy del");
            return;
        }
        if (llllllllllllllllllIIlllIlllllIlI[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllllIIlllIlllllIlI[0]);
            Command.sendChatMessage("Usage 1: .dummymod add (name)");
            Command.sendChatMessage("Usage 2: .dummymod add (name) (info)");
            Command.sendChatMessage("Info is what is displayed in brackets in the featurelist");
        }
        if (llllllllllllllllllIIlllIlllllIlI.length < 3) {
            Command.sendChatMessage("Specify a name");
            return;
        }
        if (llllllllllllllllllIIlllIlllllIlI.length == 3) {
            final DummyMod llllllllllllllllllIIllllIIIIIIll = new DummyMod(llllllllllllllllllIIlllIlllllIlI[2]);
            if (llllllllllllllllllIIlllIlllllIlI[1].equalsIgnoreCase("add")) {
                if (!Xulu.MODULE_MANAGER.getModules().contains(llllllllllllllllllIIllllIIIIIIll)) {
                    Xulu.MODULE_MANAGER.getModules().add(llllllllllllllllllIIllllIIIIIIll);
                    final Panel llllllllllllllllllIIllllIIIIIlll = NewGUI.getPanelByName("Dummy");
                    if (llllllllllllllllllIIllllIIIIIlll != null) {
                        llllllllllllllllllIIllllIIIIIlll.Elements.add(new ModuleButton(llllllllllllllllllIIllllIIIIIIll, llllllllllllllllllIIllllIIIIIlll));
                    }
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("Dummy mod ").append(llllllllllllllllllIIllllIIIIIIll.getName()).append(" added.")));
                }
                else {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllllIIllllIIIIIIll.getName()).append(" already exists!")));
                }
            }
            else if (llllllllllllllllllIIlllIlllllIlI[1].equalsIgnoreCase("del")) {
                boolean llllllllllllllllllIIllllIIIIIlII = false;
                for (final Module llllllllllllllllllIIllllIIIIIlIl : Xulu.MODULE_MANAGER.getModules()) {
                    if (llllllllllllllllllIIllllIIIIIlIl.getName().equalsIgnoreCase(llllllllllllllllllIIllllIIIIIIll.getName())) {
                        Xulu.MODULE_MANAGER.getModules().remove(llllllllllllllllllIIllllIIIIIlIl);
                        final Panel llllllllllllllllllIIllllIIIIIllI = NewGUI.getPanelByName("Dummy");
                        if (llllllllllllllllllIIllllIIIIIllI != null) {
                            final Module module;
                            final Panel panel;
                            llllllllllllllllllIIllllIIIIIllI.Elements.stream().filter(llllllllllllllllllIIlllIllIlllIl -> llllllllllllllllllIIlllIllIlllIl instanceof ModuleButton).map(llllllllllllllllllIIlllIlllIIIIl -> llllllllllllllllllIIlllIlllIIIIl).forEach(llllllllllllllllllIIlllIlllIIIll -> {
                                if (llllllllllllllllllIIlllIlllIIIll.mod.getName().equalsIgnoreCase(module.getName())) {
                                    panel.Elements.remove(llllllllllllllllllIIlllIlllIIIll);
                                }
                                return;
                            });
                        }
                        Command.sendChatMessage(String.valueOf(new StringBuilder().append("Dummy mod ").append(llllllllllllllllllIIllllIIIIIIll.getName()).append(" removed.")));
                        llllllllllllllllllIIllllIIIIIlII = true;
                    }
                }
                if (!llllllllllllllllllIIllllIIIIIlII) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllllIIllllIIIIIIll.getName()).append(" isn't a mod!")));
                }
            }
            else {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Unknown attribute '").append(llllllllllllllllllIIlllIlllllIlI[1]).append("'")));
            }
        }
        if (llllllllllllllllllIIlllIlllllIlI.length == 4) {
            final DummyMod llllllllllllllllllIIlllIlllllllI = new DummyMod(llllllllllllllllllIIlllIlllllIlI[2], llllllllllllllllllIIlllIlllllIlI[3]);
            if (llllllllllllllllllIIlllIlllllIlI[1].equalsIgnoreCase("add")) {
                if (!Xulu.MODULE_MANAGER.getModules().contains(llllllllllllllllllIIlllIlllllllI)) {
                    Xulu.MODULE_MANAGER.getModules().add(llllllllllllllllllIIlllIlllllllI);
                    final Panel llllllllllllllllllIIllllIIIIIIlI = NewGUI.getPanelByName("Dummy");
                    if (llllllllllllllllllIIllllIIIIIIlI != null) {
                        llllllllllllllllllIIllllIIIIIIlI.Elements.add(new ModuleButton(llllllllllllllllllIIlllIlllllllI, llllllllllllllllllIIllllIIIIIIlI));
                    }
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("Dummy mod ").append(llllllllllllllllllIIlllIlllllllI.getName()).append(" added.")));
                }
                else {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllllIIlllIlllllllI.getName()).append(" already exists!")));
                }
            }
            else if (llllllllllllllllllIIlllIlllllIlI[1].equalsIgnoreCase("del")) {
                boolean llllllllllllllllllIIlllIllllllll = false;
                for (final Module llllllllllllllllllIIllllIIIIIIII : Xulu.MODULE_MANAGER.getModules()) {
                    if (llllllllllllllllllIIllllIIIIIIII.getName().equalsIgnoreCase(llllllllllllllllllIIlllIlllllllI.getName())) {
                        Xulu.MODULE_MANAGER.getModules().remove(llllllllllllllllllIIllllIIIIIIII);
                        final Panel llllllllllllllllllIIllllIIIIIIIl = NewGUI.getPanelByName("Dummy");
                        if (llllllllllllllllllIIllllIIIIIIIl != null) {
                            final Module module2;
                            final Panel panel2;
                            llllllllllllllllllIIllllIIIIIIIl.Elements.stream().filter(Objects::nonNull).forEach(llllllllllllllllllIIlllIlllIllll -> {
                                if (llllllllllllllllllIIlllIlllIllll.mod.getName().equalsIgnoreCase(module2.getName())) {
                                    panel2.Elements.remove(llllllllllllllllllIIlllIlllIllll);
                                }
                                return;
                            });
                        }
                        Command.sendChatMessage(String.valueOf(new StringBuilder().append("Dummy mod ").append(llllllllllllllllllIIlllIlllllllI.getName()).append(" removed.")));
                        llllllllllllllllllIIlllIllllllll = true;
                    }
                }
                if (!llllllllllllllllllIIlllIllllllll) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllllIIlllIlllllllI.getName()).append(" isn't a mod!")));
                }
            }
            else {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Unknown attribute '").append(llllllllllllllllllIIlllIlllllIlI[1]).append("'")));
            }
        }
    }
    
    public DummyCommand() {
        super("dummymod", "Makes some fake modules >:)", new String[] { "add", "del" });
    }
}
