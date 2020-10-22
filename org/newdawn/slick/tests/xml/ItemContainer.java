package org.newdawn.slick.tests.xml;

import java.util.*;

public class ItemContainer extends Item
{
    private /* synthetic */ ArrayList items;
    
    private void add(final Item lIIIIIIIlIIIIlI) {
        this.items.add(lIIIIIIIlIIIIlI);
    }
    
    public ItemContainer() {
        this.items = new ArrayList();
    }
    
    @Override
    public void dump(final String lIIIIIIIIlIllII) {
        System.out.println(String.valueOf(new StringBuilder().append(lIIIIIIIIlIllII).append("Item Container ").append(this.name).append(",").append(this.condition)));
        for (int lIIIIIIIIllIIII = 0; lIIIIIIIIllIIII < this.items.size(); ++lIIIIIIIIllIIII) {
            this.items.get(lIIIIIIIIllIIII).dump(String.valueOf(new StringBuilder().append(lIIIIIIIIlIllII).append("\t")));
        }
    }
    
    private void setName(final String lIIIIIIIIllllII) {
        this.name = lIIIIIIIIllllII;
    }
    
    private void setCondition(final int lIIIIIIIIllIllI) {
        this.condition = lIIIIIIIIllIllI;
    }
}
