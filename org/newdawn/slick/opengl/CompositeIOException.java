package org.newdawn.slick.opengl;

import java.io.*;
import java.util.*;

public class CompositeIOException extends IOException
{
    private /* synthetic */ ArrayList exceptions;
    
    @Override
    public String getMessage() {
        String llllllllllllllllIllIlllIlIlIIIII = "Composite Exception: \n";
        for (int llllllllllllllllIllIlllIlIlIIIlI = 0; llllllllllllllllIllIlllIlIlIIIlI < this.exceptions.size(); ++llllllllllllllllIllIlllIlIlIIIlI) {
            llllllllllllllllIllIlllIlIlIIIII = String.valueOf(new StringBuilder().append(llllllllllllllllIllIlllIlIlIIIII).append("\t").append(this.exceptions.get(llllllllllllllllIllIlllIlIlIIIlI).getMessage()).append("\n"));
        }
        return llllllllllllllllIllIlllIlIlIIIII;
    }
    
    public CompositeIOException() {
        this.exceptions = new ArrayList();
    }
    
    public void addException(final Exception llllllllllllllllIllIlllIlIlIlllI) {
        this.exceptions.add(llllllllllllllllIllIlllIlIlIlllI);
    }
}
