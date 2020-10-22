package org.newdawn.slick.command;

public class BasicCommand implements Command
{
    private /* synthetic */ String name;
    
    @Override
    public boolean equals(final Object lllllllllllllllllllIIIlIllIIIlll) {
        return lllllllllllllllllllIIIlIllIIIlll instanceof BasicCommand && ((BasicCommand)lllllllllllllllllllIIIlIllIIIlll).name.equals(this.name);
    }
    
    public BasicCommand(final String lllllllllllllllllllIIIlIllIlIIll) {
        this.name = lllllllllllllllllllIIIlIllIlIIll;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("[Command=").append(this.name).append("]"));
    }
    
    public String getName() {
        return this.name;
    }
}
