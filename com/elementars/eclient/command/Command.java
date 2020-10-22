package com.elementars.eclient.command;

import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.text.*;
import java.util.regex.*;

public class Command implements Helper
{
    private static /* synthetic */ String prefix;
    protected /* synthetic */ String[] syntax;
    protected /* synthetic */ String name;
    protected /* synthetic */ String description;
    
    private static String[] getBrackets(final String lllllllllllllllllIIIllllIIIIllII) {
        if (lllllllllllllllllIIIllllIIIIllII.equalsIgnoreCase("[]")) {
            return new String[] { "[", "]" };
        }
        if (lllllllllllllllllIIIllllIIIIllII.equalsIgnoreCase("<>")) {
            return new String[] { "<", ">" };
        }
        if (lllllllllllllllllIIIllllIIIIllII.equalsIgnoreCase("()")) {
            return new String[] { "(", ")" };
        }
        if (lllllllllllllllllIIIllllIIIIllII.equalsIgnoreCase("{}")) {
            return new String[] { "{", "}" };
        }
        if (lllllllllllllllllIIIllllIIIIllII.equalsIgnoreCase("-==-")) {
            return new String[] { "-=", "=-" };
        }
        return new String[] { "[", "]" };
    }
    
    public void execute(final String[] lllllllllllllllllIIIlllIllllIlIl) {
    }
    
    public static void sendRawChatMessage(final String lllllllllllllllllIIIlllIlllllIII) {
        try {
            Wrapper.getPlayer().sendMessage((ITextComponent)new ChatMessage(lllllllllllllllllIIIlllIlllllIII));
        }
        catch (Exception lllllllllllllllllIIIlllIlllllIlI) {
            lllllllllllllllllIIIlllIlllllIlI.printStackTrace();
        }
    }
    
    public static char SECTIONSIGN() {
        return '§';
    }
    
    public String getName() {
        return this.name;
    }
    
    public static void setPrefix(final String lllllllllllllllllIIIlllIlllIllII) {
        Command.prefix = lllllllllllllllllIIIlllIlllIllII;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void showSyntax(final String lllllllllllllllllIIIlllIllIllIII) {
        sendChatMessage(String.valueOf(new StringBuilder().append("Options for ").append(lllllllllllllllllIIIlllIllIllIII)));
        if (this.syntax.length == 0) {
            sendChatMessage("No options for this command.");
            return;
        }
        final long lllllllllllllllllIIIlllIllIlIlll = (Object)this.syntax;
        final byte lllllllllllllllllIIIlllIllIlIllI = (byte)lllllllllllllllllIIIlllIllIlIlll.length;
        for (Exception lllllllllllllllllIIIlllIllIlIlIl = (Exception)0; lllllllllllllllllIIIlllIllIlIlIl < lllllllllllllllllIIIlllIllIlIllI; ++lllllllllllllllllIIIlllIllIlIlIl) {
            final String lllllllllllllllllIIIlllIllIlllII = lllllllllllllllllIIIlllIllIlIlll[lllllllllllllllllIIIlllIllIlIlIl];
            sendChatMessage(String.valueOf(new StringBuilder().append(" - ").append(lllllllllllllllllIIIlllIllIlllII)));
        }
    }
    
    public void syntaxCheck(final String[] lllllllllllllllllIIIlllIllllIIIl) {
        if (lllllllllllllllllIIIlllIllllIIIl.length > 1 && lllllllllllllllllIIIlllIllllIIIl[1].equalsIgnoreCase("help")) {
            this.showSyntax(lllllllllllllllllIIIlllIllllIIIl[0]);
            return;
        }
        this.execute(lllllllllllllllllIIIlllIllllIIIl);
    }
    
    public static String getPrefix() {
        return Command.prefix;
    }
    
    public String[] getSyntax() {
        return this.syntax;
    }
    
    public static void sendStringChatMessage(final String[] lllllllllllllllllIIIllllIIIIIIIl) {
        sendChatMessage("");
        final boolean lllllllllllllllllIIIllllIIIIIIII = (Object)lllllllllllllllllIIIllllIIIIIIIl;
        final float lllllllllllllllllIIIlllIllllllll = lllllllllllllllllIIIllllIIIIIIII.length;
        for (Exception lllllllllllllllllIIIlllIlllllllI = (Exception)0; lllllllllllllllllIIIlllIlllllllI < lllllllllllllllllIIIlllIllllllll; ++lllllllllllllllllIIIlllIlllllllI) {
            final String lllllllllllllllllIIIllllIIIIIIll = lllllllllllllllllIIIllllIIIIIIII[lllllllllllllllllIIIlllIlllllllI];
            sendRawChatMessage(lllllllllllllllllIIIllllIIIIIIll);
        }
    }
    
    public Command(final String lllllllllllllllllIIIllllIIIlIlIl, final String lllllllllllllllllIIIllllIIIlIlII, final String[] lllllllllllllllllIIIllllIIIlIIll) {
        this.name = lllllllllllllllllIIIllllIIIlIlIl;
        this.description = lllllllllllllllllIIIllllIIIlIlII;
        this.syntax = lllllllllllllllllIIIllllIIIlIIll;
        Command.prefix = ".";
    }
    
    public static void sendChatMessage(final String lllllllllllllllllIIIllllIIIIlIlI) {
        sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(Global.command2.getValue())).append(getBrackets(Global.command3.getValue())[0]).append(ColorTextUtils.getColor(Global.command1.getValue())).append("Xulu").append(ColorTextUtils.getColor(Global.command2.getValue())).append(getBrackets(Global.command3.getValue())[1]).append(" &r").append(lllllllllllllllllIIIllllIIIIlIlI)));
    }
    
    public static class ChatMessage extends TextComponentBase
    {
        /* synthetic */ String text;
        
        public ITextComponent createCopy() {
            return (ITextComponent)new ChatMessage(this.text);
        }
        
        public String getUnformattedComponentText() {
            return this.text;
        }
        
        public ChatMessage(final String llIlIlIlIlllI) {
            final Pattern llIlIlIlIllIl = Pattern.compile("&[0123456789abcdefrlosmk]");
            final Matcher llIlIlIlIllII = llIlIlIlIllIl.matcher(llIlIlIlIlllI);
            final StringBuffer llIlIlIlIlIll = new StringBuffer();
            while (llIlIlIlIllII.find()) {
                final String llIlIlIllIIII = String.valueOf(new StringBuilder().append("§").append(llIlIlIlIllII.group().substring(1)));
                llIlIlIlIllII.appendReplacement(llIlIlIlIlIll, llIlIlIllIIII);
            }
            llIlIlIlIllII.appendTail(llIlIlIlIlIll);
            this.text = llIlIlIlIlIll.toString();
        }
    }
}
