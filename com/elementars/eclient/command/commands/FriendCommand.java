package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.friend.*;

public class FriendCommand extends Command
{
    @Override
    public void execute(final String[] llllllllllllllllIlIIlllIIlIIIlIl) {
        if (llllllllllllllllIlIIlllIIlIIIlIl.length < 2) {
            Command.sendChatMessage("Try .friend add or .friend del");
            return;
        }
        if (llllllllllllllllIlIIlllIIlIIIlIl[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllIlIIlllIIlIIIlIl[0]);
        }
        if (llllllllllllllllIlIIlllIIlIIIlIl.length < 3) {
            Command.sendChatMessage("Specify a username");
            return;
        }
        final Friend llllllllllllllllIlIIlllIIlIIIlII = new Friend(llllllllllllllllIlIIlllIIlIIIlIl[2]);
        if (llllllllllllllllIlIIlllIIlIIIlIl[1].equalsIgnoreCase("add")) {
            if (!Friends.getFriends().contains((Object)llllllllllllllllIlIIlllIIlIIIlII)) {
                Friends.addFriend(llllllllllllllllIlIIlllIIlIIIlII.getUsername());
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIlIIlllIIlIIIlII.getUsername()).append(" has been friended")));
            }
            else {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIlIIlllIIlIIIlII.getUsername()).append(" is already friended!")));
            }
        }
        else if (llllllllllllllllIlIIlllIIlIIIlIl[1].equalsIgnoreCase("del")) {
            if (Friends.getFriendByName(llllllllllllllllIlIIlllIIlIIIlII.getUsername()) != null) {
                Friends.delFriend(llllllllllllllllIlIIlllIIlIIIlII.getUsername());
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIlIIlllIIlIIIlII.getUsername()).append(" has been unfriended")));
            }
            else {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIlIIlllIIlIIIlII.getUsername()).append(" isn't a friend")));
            }
        }
        else {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("Unknown attribute '").append(llllllllllllllllIlIIlllIIlIIIlIl[1]).append("'")));
        }
    }
    
    public FriendCommand() {
        super("friend", "adds or deletes friends", new String[] { "add", "del" });
    }
}
