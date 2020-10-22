package com.elementars.eclient.event.events;

import net.minecraft.client.gui.*;

public class GuiScreenEvent
{
    private /* synthetic */ GuiScreen screen;
    
    public GuiScreen getScreen() {
        return this.screen;
    }
    
    public void setScreen(final GuiScreen lllllllllllllllllIllllIIIlIIlIIl) {
        this.screen = lllllllllllllllllIllllIIIlIIlIIl;
    }
    
    public GuiScreenEvent(final GuiScreen lllllllllllllllllIllllIIIlIlIIlI) {
        this.screen = lllllllllllllllllIllllIIIlIlIIlI;
    }
    
    public static class Displayed extends GuiScreenEvent
    {
        public Displayed(final GuiScreen lllIlllIllllI) {
            super(lllIlllIllllI);
        }
    }
    
    public static class Closed extends GuiScreenEvent
    {
        public Closed(final GuiScreen lllllllllllllllllIllIllllIIIlIII) {
            super(lllllllllllllllllIllIllllIIIlIII);
        }
    }
}
