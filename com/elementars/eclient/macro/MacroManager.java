package com.elementars.eclient.macro;

import java.util.*;

public class MacroManager
{
    private /* synthetic */ ArrayList<Macro> macros;
    
    public void delMacro(final int lllllllllllllllllIllIllllIlIllIl) {
        this.macros.stream().filter(lllllllllllllllllIllIllllIIlIlII -> lllllllllllllllllIllIllllIIlIlII.getKey() == lllllllllllllllllIllIllllIlIllIl).forEach(lllllllllllllllllIllIllllIIllIII -> this.macros.remove(lllllllllllllllllIllIllllIIllIII));
    }
    
    public MacroManager() {
        this.macros = new ArrayList<Macro>();
    }
    
    public void runMacros(final int lllllllllllllllllIllIllllIlIIllI) {
        this.macros.stream().filter(lllllllllllllllllIllIllllIlIIIII -> lllllllllllllllllIllIllllIlIIIII.getKey() == lllllllllllllllllIllIllllIlIIllI).forEach(Macro::runMacro);
    }
    
    public ArrayList<Macro> getMacros() {
        return this.macros;
    }
    
    public void addMacro(final String lllllllllllllllllIllIllllIllIlII, final int lllllllllllllllllIllIllllIllIIll) {
        this.macros.add(new Macro(lllllllllllllllllIllIllllIllIlII, lllllllllllllllllIllIllllIllIIll));
    }
}
