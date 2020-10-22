package com.elementars.eclient.command;

import com.elementars.eclient.util.*;
import java.util.*;
import com.elementars.eclient.command.commands.*;

public class CommandManager
{
    public static /* synthetic */ ArrayList<Command> commands;
    public static /* synthetic */ ArrayList<String> rcommands;
    
    static {
        CommandManager.commands = new ArrayList<Command>();
        CommandManager.rcommands = new ArrayList<String>();
    }
    
    public static void runCommand(final String lllllllllllllllllIIlIIIllllIIIlI) {
        final String[] lllllllllllllllllIIlIIIllllIIIIl = lllllllllllllllllIIlIIIllllIIIlI.split(" ");
        for (int lllllllllllllllllIIlIIIllllIIlIl = 0; lllllllllllllllllIIlIIIllllIIlIl < lllllllllllllllllIIlIIIllllIIIIl.length; ++lllllllllllllllllIIlIIIllllIIlIl) {
            if (lllllllllllllllllIIlIIIllllIIIIl[lllllllllllllllllIIlIIIllllIIlIl] != null) {
                lllllllllllllllllIIlIIIllllIIIIl[lllllllllllllllllIIlIIIllllIIlIl] = lllllllllllllllllIIlIIIllllIIIIl[lllllllllllllllllIIlIIIllllIIlIl].replaceAll("<>", " ");
            }
        }
        try {
            Wrapper.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(String.valueOf(new StringBuilder().append(Command.getPrefix()).append(lllllllllllllllllIIlIIIllllIIIlI)));
            for (final Command lllllllllllllllllIIlIIIllllIIlII : CommandManager.commands) {
                if (lllllllllllllllllIIlIIIllllIIlII.getName().equalsIgnoreCase(lllllllllllllllllIIlIIIllllIIIIl[0])) {
                    lllllllllllllllllIIlIIIllllIIlII.syntaxCheck(lllllllllllllllllIIlIIIllllIIIIl);
                    return;
                }
            }
        }
        catch (Exception lllllllllllllllllIIlIIIllllIIIll) {
            Command.sendChatMessage("Error occured when running command!");
        }
        Command.sendChatMessage("Command not found. Try .help for a list of commands");
    }
    
    public void init() {
        CommandManager.commands.add(new AboutCommand());
        CommandManager.commands.add(new CreditsCommand());
        CommandManager.commands.add(new HelpCommand());
        CommandManager.commands.add(new MacroCommand());
        CommandManager.commands.add(new BindCommand());
        CommandManager.commands.add(new ToggleCommand());
        CommandManager.commands.add(new SetCommand());
        CommandManager.commands.add(new SetStringCommand());
        CommandManager.commands.add(new DrawnCommand());
        CommandManager.commands.add(new XrayCommand());
        CommandManager.commands.add(new SearchCommand());
        CommandManager.commands.add(new SaveCommand());
        CommandManager.commands.add(new ReloadCommand());
        CommandManager.commands.add(new PrefixCommand());
        CommandManager.commands.add(new FriendCommand());
        CommandManager.commands.add(new EnemyCommand());
        CommandManager.commands.add(new CustomFontCommand());
        CommandManager.commands.add(new YawCommand());
        CommandManager.commands.add(new LogspotCommand());
        CommandManager.commands.add(new WaypointCommand());
        CommandManager.commands.add(new AntiVoidCommand());
        CommandManager.commands.add(new DummyCommand());
        CommandManager.commands.add(new NicknameCommand());
    }
    
    public static ArrayList<Command> getCommands() {
        return CommandManager.commands;
    }
}
