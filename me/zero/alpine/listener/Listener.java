package me.zero.alpine.listener;

import java.util.function.*;
import net.jodah.typetools.*;

public final class Listener<T> implements EventHook<T>
{
    private final /* synthetic */ byte priority;
    private final /* synthetic */ EventHook<T> hook;
    private final /* synthetic */ Predicate<T>[] filters;
    private final /* synthetic */ Class<T> target;
    
    public final byte getPriority() {
        return this.priority;
    }
    
    @SafeVarargs
    public Listener(final EventHook<T> lllllllllllllllllIIlIlllIIlIIIll, final byte lllllllllllllllllIIlIlllIIlIIllI, final Predicate<T>... lllllllllllllllllIIlIlllIIlIIIIl) {
        this.hook = lllllllllllllllllIIlIlllIIlIIIll;
        this.priority = lllllllllllllllllIIlIlllIIlIIllI;
        this.target = (Class<T>)TypeResolver.resolveRawArgument(EventHook.class, lllllllllllllllllIIlIlllIIlIIIll.getClass());
        this.filters = lllllllllllllllllIIlIlllIIlIIIIl;
    }
    
    public final Class<T> getTarget() {
        return this.target;
    }
    
    @SafeVarargs
    public Listener(final EventHook<T> lllllllllllllllllIIlIlllIIlIlllI, final Predicate<T>... lllllllllllllllllIIlIlllIIlIllIl) {
        this(lllllllllllllllllIIlIlllIIlIlllI, (byte)3, (Predicate[])lllllllllllllllllIIlIlllIIlIllIl);
    }
    
    @Override
    public final void invoke(final T lllllllllllllllllIIlIlllIIIlIIII) {
        if (this.filters.length > 0) {
            final char lllllllllllllllllIIlIlllIIIIllll = (Object)this.filters;
            final float lllllllllllllllllIIlIlllIIIIlllI = lllllllllllllllllIIlIlllIIIIllll.length;
            for (short lllllllllllllllllIIlIlllIIIIllIl = 0; lllllllllllllllllIIlIlllIIIIllIl < lllllllllllllllllIIlIlllIIIIlllI; ++lllllllllllllllllIIlIlllIIIIllIl) {
                final Predicate<T> lllllllllllllllllIIlIlllIIIlIlII = lllllllllllllllllIIlIlllIIIIllll[lllllllllllllllllIIlIlllIIIIllIl];
                if (!lllllllllllllllllIIlIlllIIIlIlII.test(lllllllllllllllllIIlIlllIIIlIIII)) {
                    return;
                }
            }
        }
        this.hook.invoke(lllllllllllllllllIIlIlllIIIlIIII);
    }
}
