package org.newdawn.slick;

import java.util.*;
import java.io.*;
import org.newdawn.slick.muffin.*;
import org.newdawn.slick.util.*;

public class SavedState
{
    private /* synthetic */ HashMap numericData;
    private /* synthetic */ Muffin muffin;
    private /* synthetic */ String fileName;
    private /* synthetic */ HashMap stringData;
    
    public void save() throws IOException {
        this.muffin.saveFile(this.numericData, String.valueOf(new StringBuilder().append(this.fileName).append("_Number")));
        this.muffin.saveFile(this.stringData, String.valueOf(new StringBuilder().append(this.fileName).append("_String")));
    }
    
    public double getNumber(final String llllllllllllllllllIIllIlIIlIlIll, final double llllllllllllllllllIIllIlIIlIlllI) {
        final Double llllllllllllllllllIIllIlIIlIllIl = this.numericData.get(llllllllllllllllllIIllIlIIlIlIll);
        if (llllllllllllllllllIIllIlIIlIllIl == null) {
            return llllllllllllllllllIIllIlIIlIlllI;
        }
        return llllllllllllllllllIIllIlIIlIllIl;
    }
    
    public String getString(final String llllllllllllllllllIIllIlIIIllIlI) {
        return this.getString(llllllllllllllllllIIllIlIIIllIlI, null);
    }
    
    public void load() throws IOException {
        this.numericData = this.muffin.loadFile(String.valueOf(new StringBuilder().append(this.fileName).append("_Number")));
        this.stringData = this.muffin.loadFile(String.valueOf(new StringBuilder().append(this.fileName).append("_String")));
    }
    
    public SavedState(final String llllllllllllllllllIIllIlIIllllII) throws SlickException {
        this.numericData = new HashMap();
        this.stringData = new HashMap();
        this.fileName = llllllllllllllllllIIllIlIIllllII;
        if (this.isWebstartAvailable()) {
            this.muffin = new WebstartMuffin();
        }
        else {
            this.muffin = new FileMuffin();
        }
        try {
            this.load();
        }
        catch (IOException llllllllllllllllllIIllIlIlIIIIII) {
            throw new SlickException("Failed to load state on startup", llllllllllllllllllIIllIlIlIIIIII);
        }
    }
    
    public double getNumber(final String llllllllllllllllllIIllIlIIllIlIl) {
        return this.getNumber(llllllllllllllllllIIllIlIIllIlIl, 0.0);
    }
    
    private boolean isWebstartAvailable() {
        try {
            Class.forName("javax.jnlp.ServiceManager");
            Log.info("Webstart detected using Muffins");
        }
        catch (Exception llllllllllllllllllIIllIIlllllIlI) {
            Log.info("Using Local File System");
            return false;
        }
        return true;
    }
    
    public void setNumber(final String llllllllllllllllllIIllIlIIlIIIIl, final double llllllllllllllllllIIllIlIIlIIIII) {
        this.numericData.put(llllllllllllllllllIIllIlIIlIIIIl, new Double(llllllllllllllllllIIllIlIIlIIIII));
    }
    
    public void setString(final String llllllllllllllllllIIllIlIIIIIllI, final String llllllllllllllllllIIllIlIIIIlIII) {
        this.stringData.put(llllllllllllllllllIIllIlIIIIIllI, llllllllllllllllllIIllIlIIIIlIII);
    }
    
    public String getString(final String llllllllllllllllllIIllIlIIIlIIII, final String llllllllllllllllllIIllIlIIIIllll) {
        final String llllllllllllllllllIIllIlIIIlIIlI = this.stringData.get(llllllllllllllllllIIllIlIIIlIIII);
        if (llllllllllllllllllIIllIlIIIlIIlI == null) {
            return llllllllllllllllllIIllIlIIIIllll;
        }
        return llllllllllllllllllIIllIlIIIlIIlI;
    }
    
    public void clear() {
        this.numericData.clear();
        this.stringData.clear();
    }
}
