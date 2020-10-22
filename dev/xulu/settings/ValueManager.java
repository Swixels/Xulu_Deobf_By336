package dev.xulu.settings;

import java.util.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;

public class ValueManager
{
    private /* synthetic */ ArrayList<Value<?>> values;
    
    public <T> Value<T> getValueByMod(final Module llIllIllIIlll, final String llIllIllIIllI) {
        for (final Value llIllIllIllII : this.getValues()) {
            if (llIllIllIllII.getParentMod().equals(llIllIllIIlll) && llIllIllIllII.getName().equalsIgnoreCase(llIllIllIIllI)) {
                return (Value<T>)llIllIllIllII;
            }
        }
        return null;
    }
    
    public <T> Value<T> register(final Value<T> llIlllIIlIIll) {
        this.values.add(llIlllIIlIIll);
        return llIlllIIlIIll;
    }
    
    public ArrayList<Value<?>> getValuesByMod(final Module llIllIllllIII) {
        final ValueList llIllIlllIlll = new ValueList();
        for (final Value<?> llIllIllllIlI : this.getValues()) {
            if (llIllIllllIlI.getParentMod().equals(llIllIllllIII)) {
                llIllIlllIlll.add(llIllIllllIlI);
            }
        }
        if (llIllIlllIlll.isEmpty()) {
            return null;
        }
        return llIllIlllIlll;
    }
    
    public <T> Value<T> getValueT(final String llIllIlIIlllI, final Class<? extends Module> llIllIlIIllIl) {
        for (final Value<?> llIllIlIlIIll : this.getValues()) {
            if (llIllIlIlIIll.getName().equalsIgnoreCase(llIllIlIIlllI) && llIllIlIlIIll.getParentMod().equals(Xulu.MODULE_MANAGER.getModule(llIllIlIIllIl))) {
                return (Value<T>)llIllIlIlIIll;
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("[Xulu] Error Setting NOT found: '").append(llIllIlIIlllI).append("'!")));
        return null;
    }
    
    public ValueManager() {
        this.values = new ArrayList<Value<?>>();
    }
    
    public ArrayList<Value<?>> getSettingsByMod(final Module llIlllIIIIIll) {
        final ArrayList<Value<?>> llIlllIIIIlIl = new ArrayList<Value<?>>();
        for (final Value<?> llIlllIIIlIII : this.getValues()) {
            if (llIlllIIIlIII.getParentMod().equals(llIlllIIIIIll)) {
                llIlllIIIIlIl.add(llIlllIIIlIII);
            }
        }
        if (llIlllIIIIlIl.isEmpty()) {
            return null;
        }
        return llIlllIIIIlIl;
    }
    
    public <T> Value<T> getValueByName(final String llIllIlIlllIl) {
        for (final Value<?> llIllIlIlllll : this.getValues()) {
            if (llIllIlIlllll.getName().equalsIgnoreCase(llIllIlIlllIl)) {
                return (Value<T>)llIllIlIlllll;
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("[Xulu] Error Setting NOT found: '").append(llIllIlIlllIl).append("'!")));
        return null;
    }
    
    public ArrayList<Value<?>> getValues() {
        return this.values;
    }
}
