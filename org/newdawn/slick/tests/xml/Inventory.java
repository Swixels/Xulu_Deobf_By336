package org.newdawn.slick.tests.xml;

import java.util.*;

public class Inventory
{
    private /* synthetic */ ArrayList items;
    
    public void dump(final String lllllllllllllllllIIlIIIIIlIlIlll) {
        System.out.println(String.valueOf(new StringBuilder().append(lllllllllllllllllIIlIIIIIlIlIlll).append("Inventory")));
        for (int lllllllllllllllllIIlIIIIIlIllIIl = 0; lllllllllllllllllIIlIIIIIlIllIIl < this.items.size(); ++lllllllllllllllllIIlIIIIIlIllIIl) {
            this.items.get(lllllllllllllllllIIlIIIIIlIllIIl).dump(String.valueOf(new StringBuilder().append(lllllllllllllllllIIlIIIIIlIlIlll).append("\t")));
        }
    }
    
    public Inventory() {
        this.items = new ArrayList();
    }
    
    private void add(final Item lllllllllllllllllIIlIIIIIlIlllll) {
        this.items.add(lllllllllllllllllIIlIIIIIlIlllll);
    }
}
