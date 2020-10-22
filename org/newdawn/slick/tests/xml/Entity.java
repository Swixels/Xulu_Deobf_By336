package org.newdawn.slick.tests.xml;

public class Entity
{
    private /* synthetic */ float x;
    private /* synthetic */ Inventory invent;
    private /* synthetic */ float y;
    private /* synthetic */ Stats stats;
    
    private void add(final Stats llllllllllllllllIlllllIlIlIllllI) {
        this.stats = llllllllllllllllIlllllIlIlIllllI;
    }
    
    private void add(final Inventory llllllllllllllllIlllllIlIllIIlII) {
        this.invent = llllllllllllllllIlllllIlIllIIlII;
    }
    
    public void dump(final String llllllllllllllllIlllllIlIlIllIII) {
        System.out.println(String.valueOf(new StringBuilder().append(llllllllllllllllIlllllIlIlIllIII).append("Entity ").append(this.x).append(",").append(this.y)));
        this.invent.dump(String.valueOf(new StringBuilder().append(llllllllllllllllIlllllIlIlIllIII).append("\t")));
        this.stats.dump(String.valueOf(new StringBuilder().append(llllllllllllllllIlllllIlIlIllIII).append("\t")));
    }
}
