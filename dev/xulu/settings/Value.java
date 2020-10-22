package dev.xulu.settings;

import com.elementars.eclient.module.*;
import java.util.function.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.*;
import java.util.*;

public class Value<T>
{
    private /* synthetic */ Predicate<T> visibleCheck;
    private /* synthetic */ Mode mode;
    private /* synthetic */ T value;
    private /* synthetic */ T min;
    private /* synthetic */ Predicate<T> filter;
    private /* synthetic */ T max;
    private /* synthetic */ ArrayList<T> options;
    private /* synthetic */ Module parent;
    private /* synthetic */ Consumer<OnChangedValue<T>> changeTask;
    private /* synthetic */ String name;
    private /* synthetic */ String filterError;
    
    public void setValue(final T lIlllIlIlllIIlI) {
        if (this.value != lIlllIlIlllIIlI) {
            if (this.filter != null && !this.filter.test(lIlllIlIlllIIlI)) {
                if (this.filterError != null) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("&c").append(this.filterError)));
                }
                return;
            }
            final T lIlllIlIlllIlII = this.value;
            this.value = lIlllIlIlllIIlI;
            if (this.changeTask != null) {
                this.changeTask.accept(new OnChangedValue<T>(lIlllIlIlllIlII, lIlllIlIlllIIlI));
            }
        }
    }
    
    public boolean isMode() {
        return this.mode == Mode.MODE;
    }
    
    public Module getParentMod() {
        return this.parent;
    }
    
    public boolean isText() {
        return this.mode == Mode.TEXT;
    }
    
    public void setEnumValue(final String lIlllIllIIIlIII) {
        final int lIlllIllIIIIlIl = (Object)((Enum)this.value).getClass().getEnumConstants();
        final long lIlllIllIIIIlII = lIlllIllIIIIlIl.length;
        for (final Enum lIlllIllIIIlIlI : lIlllIllIIIIlIl) {
            if (lIlllIllIIIlIlI.name().equalsIgnoreCase(lIlllIllIIIlIII)) {
                final T lIlllIllIIIlIll = this.value;
                this.value = (T)lIlllIllIIIlIlI;
                if (this.changeTask != null) {
                    this.changeTask.accept(new OnChangedValue<T>(lIlllIllIIIlIll, (T)lIlllIllIIIlIlI));
                }
            }
        }
    }
    
    public boolean isEnum() {
        return this.mode == Mode.ENUM;
    }
    
    public Value<T> onChanged(final Consumer<OnChangedValue<T>> lIlllIlIlIllIIl) {
        this.changeTask = lIlllIlIlIllIIl;
        return this;
    }
    
    public T getCorrectOption(final String lIlllIllIIlIlll) {
        if (this.mode == Mode.ENUM) {
            for (final T lIlllIllIIllIIl : this.options) {
                if (lIlllIllIIllIIl.toString().equalsIgnoreCase(lIlllIllIIlIlll)) {
                    return lIlllIllIIllIIl;
                }
            }
            return null;
        }
        return null;
    }
    
    public ArrayList<T> getOptions() {
        return this.options;
    }
    
    public String getCorrectString(final String lIlllIllIlIIIlI) {
        if (this.value instanceof String) {
            for (final String lIlllIllIlIIlIl : this.options) {
                if (lIlllIllIlIIlIl.equalsIgnoreCase(lIlllIllIlIIIlI)) {
                    return lIlllIllIlIIlIl;
                }
            }
            return null;
        }
        if (this.mode == Mode.ENUM) {
            for (final T lIlllIllIlIIlII : this.options) {
                if (lIlllIllIlIIlII.toString().equalsIgnoreCase(lIlllIllIlIIIlI)) {
                    return Xulu.getTitle(lIlllIllIlIIlII.toString());
                }
            }
            return null;
        }
        return null;
    }
    
    public Value<T> withFilterError(final String lIlllIlIlIIIlIl) {
        this.filterError = lIlllIlIlIIIlIl;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Value<T> newValueFilter(final Predicate<T> lIlllIlIlIIlIll) {
        this.filter = lIlllIlIlIIlIll;
        return this;
    }
    
    public T getMax() {
        return this.max;
    }
    
    public Value(final String lIlllIllIllIlll, final Module lIlllIllIllllII, final T lIlllIllIllIlIl, final T lIlllIllIlllIlI, final T lIlllIllIllIIll) {
        this.changeTask = null;
        this.visibleCheck = null;
        this.filter = null;
        this.filterError = null;
        this.name = lIlllIllIllIlll;
        this.parent = lIlllIllIllllII;
        this.value = lIlllIllIllIlIl;
        this.min = lIlllIllIlllIlI;
        this.max = lIlllIllIllIIll;
        this.mode = Mode.NUMBER;
    }
    
    public boolean isToggle() {
        return this.mode == Mode.TOGGLE;
    }
    
    public Value<T> visibleWhen(final Predicate<T> lIlllIlIlIlIIll) {
        this.visibleCheck = lIlllIlIlIlIIll;
        return this;
    }
    
    public T getMin() {
        return this.min;
    }
    
    public boolean isBind() {
        return this.mode == Mode.BIND;
    }
    
    public Value(final String lIlllIlllIlIlII, final Module lIlllIlllIllIII, final T lIlllIlllIlIlll, final T[] lIlllIlllIlIllI) {
        this.changeTask = null;
        this.visibleCheck = null;
        this.filter = null;
        this.filterError = null;
        this.name = lIlllIlllIlIlII;
        this.parent = lIlllIlllIllIII;
        this.value = lIlllIlllIlIlll;
        this.options = new ArrayList<T>((Collection<? extends T>)Arrays.asList(lIlllIlllIlIllI));
        this.mode = Mode.MODE;
        if (lIlllIlllIlIlll instanceof Enum) {
            this.mode = Mode.ENUM;
        }
    }
    
    public Value(final String lIlllIllllIIIll, final Module lIlllIllllIIlll, final T lIlllIllllIIllI, final ArrayList<T> lIlllIllllIIIII) {
        this.changeTask = null;
        this.visibleCheck = null;
        this.filter = null;
        this.filterError = null;
        this.name = lIlllIllllIIIll;
        this.parent = lIlllIllllIIlll;
        this.value = lIlllIllllIIllI;
        this.options = lIlllIllllIIIII;
        this.mode = Mode.MODE;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public boolean isVisible() {
        return this.visibleCheck == null || this.visibleCheck.test(this.value);
    }
    
    public Value(final String lIlllIlllIIIlll, final Module lIlllIlllIIlIlI, final T lIlllIlllIIIlIl) {
        this.changeTask = null;
        this.visibleCheck = null;
        this.filter = null;
        this.filterError = null;
        this.name = lIlllIlllIIIlll;
        this.parent = lIlllIlllIIlIlI;
        this.value = lIlllIlllIIIlIl;
        this.mode = Mode.UNKNOWN;
        if (lIlllIlllIIIlIl instanceof Boolean) {
            this.mode = Mode.TOGGLE;
        }
        else if (lIlllIlllIIIlIl instanceof Bind) {
            this.mode = Mode.BIND;
        }
        else if (lIlllIlllIIIlIl instanceof TextBox) {
            this.mode = Mode.TEXT;
        }
    }
    
    public boolean isNumber() {
        return this.mode == Mode.NUMBER;
    }
    
    enum Mode
    {
        BIND, 
        ENUM, 
        TEXT, 
        MODE, 
        TOGGLE, 
        UNKNOWN, 
        NUMBER;
    }
}
