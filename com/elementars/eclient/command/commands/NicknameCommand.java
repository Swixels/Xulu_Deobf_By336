package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.friend.*;

public class NicknameCommand extends Command
{
    @Override
    public void execute(final String[] llllllllllllllllllIllIlIlIIIIlll) {
        if (llllllllllllllllllIllIlIlIIIIlll.length < 2) {
            Command.sendChatMessage("Usage: .nickname set/remove (name) (nickname)");
            return;
        }
        if (llllllllllllllllllIllIlIlIIIIlll[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllllIllIlIlIIIIlll[0]);
        }
        if (llllllllllllllllllIllIlIlIIIIlll.length < 3) {
            Command.sendChatMessage("Specify a username");
            return;
        }
        if (llllllllllllllllllIllIlIlIIIIlll.length < 4 && !llllllllllllllllllIllIlIlIIIIlll[1].equalsIgnoreCase("remove")) {
            Command.sendChatMessage("Specify a nickname");
            return;
        }
        if (llllllllllllllllllIllIlIlIIIIlll[1].equalsIgnoreCase("set")) {
            Nicknames.addNickname(llllllllllllllllllIllIlIlIIIIlll[2], llllllllllllllllllIllIlIlIIIIlll[3]);
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("Set nickname for &b").append(llllllllllllllllllIllIlIlIIIIlll[2])));
        }
        else if (llllllllllllllllllIllIlIlIIIIlll[1].equalsIgnoreCase("remove")) {
            if (Nicknames.hasNickname(llllllllllllllllllIllIlIlIIIIlll[2])) {
                Nicknames.removeNickname(llllllllllllllllllIllIlIlIIIIlll[2]);
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Nickname has been removed for &b").append(llllllllllllllllllIllIlIlIIIIlll[2])));
            }
            else {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("&b").append(llllllllllllllllllIllIlIlIIIIlll[2]).append("&f doesn't have a nickname")));
            }
        }
        else {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("Unknown attribute '").append(llllllllllllllllllIllIlIlIIIIlll[1]).append("'")));
        }
    }
    
    public NicknameCommand() {
        super("nickname", "adds or deletes friends", new String[] { "set", "remove" });
    }
}
