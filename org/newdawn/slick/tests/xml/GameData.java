package org.newdawn.slick.tests.xml;

import java.util.*;

public class GameData
{
    private /* synthetic */ ArrayList entities;
    
    private void add(final Entity lllllllllllllllllllllIlIIIIllllI) {
        this.entities.add(lllllllllllllllllllllIlIIIIllllI);
    }
    
    public GameData() {
        this.entities = new ArrayList();
    }
    
    public void dump(final String lllllllllllllllllllllIlIIIIllIII) {
        System.out.println(String.valueOf(new StringBuilder().append(lllllllllllllllllllllIlIIIIllIII).append("GameData")));
        for (int lllllllllllllllllllllIlIIIIllIlI = 0; lllllllllllllllllllllIlIIIIllIlI < this.entities.size(); ++lllllllllllllllllllllIlIIIIllIlI) {
            this.entities.get(lllllllllllllllllllllIlIIIIllIlI).dump(String.valueOf(new StringBuilder().append(lllllllllllllllllllllIlIIIIllIII).append("\t")));
        }
    }
}
