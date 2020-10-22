package org.newdawn.slick.util.xml;

import java.util.*;

public class XMLElementList
{
    private /* synthetic */ ArrayList list;
    
    public void addAllTo(final Collection llllllllllllllllllIllIlIIIlIllll) {
        llllllllllllllllllIllIlIIIlIllll.addAll(this.list);
    }
    
    public XMLElementList() {
        this.list = new ArrayList();
    }
    
    public boolean contains(final XMLElement llllllllllllllllllIllIlIIIllIlIl) {
        return this.list.contains(llllllllllllllllllIllIlIIIllIlIl);
    }
    
    public void add(final XMLElement llllllllllllllllllIllIlIIlIIIlII) {
        this.list.add(llllllllllllllllllIllIlIIlIIIlII);
    }
    
    public int size() {
        return this.list.size();
    }
    
    public XMLElement get(final int llllllllllllllllllIllIlIIIlllIIl) {
        return this.list.get(llllllllllllllllllIllIlIIIlllIIl);
    }
}
