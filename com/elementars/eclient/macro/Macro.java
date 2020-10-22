package com.elementars.eclient.macro;

import com.elementars.eclient.command.*;
import com.elementars.eclient.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class Macro
{
    private /* synthetic */ String macro;
    private /* synthetic */ int key;
    
    public String getMacro() {
        return this.macro;
    }
    
    public void runMacro() {
        if (this.macro.startsWith(".")) {
            CommandManager.runCommand(this.macro.substring(1));
        }
        else if (Wrapper.getMinecraft().getConnection() != null) {
            Wrapper.getMinecraft().getConnection().sendPacket((Packet)new CPacketChatMessage(this.macro));
        }
    }
    
    public Macro(final String lIllIIllIIIllIl, final int lIllIIllIIIllll) {
        this.macro = lIllIIllIIIllIl;
        this.key = lIllIIllIIIllll;
    }
    
    public int getKey() {
        return this.key;
    }
}
