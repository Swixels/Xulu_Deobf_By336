package me.zero.alpine;

import java.lang.reflect.*;
import java.util.stream.*;
import java.util.function.*;
import me.zero.alpine.listener.*;
import java.lang.annotation.*;
import java.util.*;

public class EventManager implements EventBus
{
    private final /* synthetic */ Map<Object, List<Listener>> SUBSCRIPTION_CACHE;
    private final /* synthetic */ Map<Class<?>, List<Listener>> SUBSCRIPTION_MAP;
    private final /* synthetic */ List<EventBus> ATTACHED_BUSES;
    
    @Override
    public void subscribe(final Object llllllllllllllllllIlIlIIIIIIIlII) {
        final List<Listener> llllllllllllllllllIlIlIIIIIIIllI = this.SUBSCRIPTION_CACHE.computeIfAbsent(llllllllllllllllllIlIlIIIIIIIlII, llllllllllllllllllIlIIlllIIIllll -> Arrays.stream(llllllllllllllllllIlIIlllIIIllll.getClass().getDeclaredFields()).filter(EventManager::isValidField).map(llllllllllllllllllIlIIlllIIIlIll -> asListener(llllllllllllllllllIlIIlllIIIllll, llllllllllllllllllIlIIlllIIIlIll)).filter(Objects::nonNull).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        llllllllllllllllllIlIlIIIIIIIllI.forEach(this::subscribe);
        if (!this.ATTACHED_BUSES.isEmpty()) {
            this.ATTACHED_BUSES.forEach(llllllllllllllllllIlIIlllIIlIlII -> llllllllllllllllllIlIIlllIIlIlII.subscribe(llllllllllllllllllIlIlIIIIIIIlII));
        }
    }
    
    @Override
    public void subscribe(final Object... llllllllllllllllllIlIIllllllllll) {
        Arrays.stream(llllllllllllllllllIlIIllllllllll).forEach(this::subscribe);
    }
    
    @Override
    public void attach(final EventBus llllllllllllllllllIlIIllllIlIIll) {
        if (!this.ATTACHED_BUSES.contains(llllllllllllllllllIlIIllllIlIIll)) {
            this.ATTACHED_BUSES.add(llllllllllllllllllIlIIllllIlIIll);
        }
    }
    
    @Override
    public void unsubscribe(final Object... llllllllllllllllllIlIIlllllIlIII) {
        Arrays.stream(llllllllllllllllllIlIIlllllIlIII).forEach(this::unsubscribe);
    }
    
    @Override
    public void unsubscribe(final Object llllllllllllllllllIlIIlllllIllll) {
        final List<Listener> llllllllllllllllllIlIIllllllIIIl = this.SUBSCRIPTION_CACHE.get(llllllllllllllllllIlIIlllllIllll);
        if (llllllllllllllllllIlIIllllllIIIl == null) {
            return;
        }
        final List list;
        this.SUBSCRIPTION_MAP.values().forEach(llllllllllllllllllIlIIlllIIllIII -> {
            Objects.requireNonNull(list);
            llllllllllllllllllIlIIlllIIllIII.removeIf(list::contains);
            return;
        });
        if (!this.ATTACHED_BUSES.isEmpty()) {
            this.ATTACHED_BUSES.forEach(llllllllllllllllllIlIIlllIIllllI -> llllllllllllllllllIlIIlllIIllllI.unsubscribe(llllllllllllllllllIlIIlllllIllll));
        }
    }
    
    @Override
    public void post(final Object llllllllllllllllllIlIIllllIllIlI) {
        final List<Listener> llllllllllllllllllIlIIllllIlllII = this.SUBSCRIPTION_MAP.get(llllllllllllllllllIlIIllllIllIlI.getClass());
        if (llllllllllllllllllIlIIllllIlllII != null) {
            llllllllllllllllllIlIIllllIlllII.forEach(llllllllllllllllllIlIIlllIlIIlII -> llllllllllllllllllIlIIlllIlIIlII.invoke(llllllllllllllllllIlIIllllIllIlI));
        }
        if (!this.ATTACHED_BUSES.isEmpty()) {
            this.ATTACHED_BUSES.forEach(llllllllllllllllllIlIIlllIlIlIlI -> llllllllllllllllllIlIIlllIlIlIlI.post(llllllllllllllllllIlIIllllIllIlI));
        }
    }
    
    private static boolean isValidField(final Field llllllllllllllllllIlIIllllIIlIlI) {
        return llllllllllllllllllIlIIllllIIlIlI.isAnnotationPresent(EventHandler.class) && Listener.class.isAssignableFrom(llllllllllllllllllIlIIllllIIlIlI.getType());
    }
    
    @Override
    public void unsubscribe(final Iterable<Object> llllllllllllllllllIlIIlllllIIIlI) {
        llllllllllllllllllIlIIlllllIIIlI.forEach(this::unsubscribe);
    }
    
    public EventManager() {
        this.SUBSCRIPTION_CACHE = new HashMap<Object, List<Listener>>();
        this.SUBSCRIPTION_MAP = new HashMap<Class<?>, List<Listener>>();
        this.ATTACHED_BUSES = new ArrayList<EventBus>();
    }
    
    @Override
    public void detach(final EventBus llllllllllllllllllIlIIllllIIllIl) {
        if (this.ATTACHED_BUSES.contains(llllllllllllllllllIlIIllllIIllIl)) {
            this.ATTACHED_BUSES.remove(llllllllllllllllllIlIIllllIIllIl);
        }
    }
    
    @Override
    public void subscribe(final Iterable<Object> llllllllllllllllllIlIIlllllllIIl) {
        llllllllllllllllllIlIIlllllllIIl.forEach(this::subscribe);
    }
    
    private static Listener asListener(final Object llllllllllllllllllIlIIllllIIIIlI, final Field llllllllllllllllllIlIIlllIllllll) {
        try {
            final boolean llllllllllllllllllIlIIllllIIIlIl = llllllllllllllllllIlIIlllIllllll.isAccessible();
            llllllllllllllllllIlIIlllIllllll.setAccessible(true);
            final Listener llllllllllllllllllIlIIllllIIIlII = (Listener)llllllllllllllllllIlIIlllIllllll.get(llllllllllllllllllIlIIllllIIIIlI);
            llllllllllllllllllIlIIlllIllllll.setAccessible(llllllllllllllllllIlIIllllIIIlIl);
            if (llllllllllllllllllIlIIllllIIIlII == null) {
                return null;
            }
            if (llllllllllllllllllIlIIllllIIIlII.getPriority() > 5 || llllllllllllllllllIlIIllllIIIlII.getPriority() < 1) {
                throw new RuntimeException("Event Priority out of bounds! %s");
            }
            return llllllllllllllllllIlIIllllIIIlII;
        }
        catch (IllegalAccessException llllllllllllllllllIlIIllllIIIIll) {
            return null;
        }
    }
    
    private void subscribe(final Listener llllllllllllllllllIlIIlllIllIlll) {
        List<Listener> llllllllllllllllllIlIIlllIllIllI;
        int llllllllllllllllllIlIIlllIllIlIl;
        for (llllllllllllllllllIlIIlllIllIllI = this.SUBSCRIPTION_MAP.computeIfAbsent(llllllllllllllllllIlIIlllIllIlll.getTarget(), llllllllllllllllllIlIIlllIllIIII -> new ArrayList()), llllllllllllllllllIlIIlllIllIlIl = 0; llllllllllllllllllIlIIlllIllIlIl < llllllllllllllllllIlIIlllIllIllI.size() && llllllllllllllllllIlIIlllIllIlll.getPriority() >= llllllllllllllllllIlIIlllIllIllI.get(llllllllllllllllllIlIIlllIllIlIl).getPriority(); ++llllllllllllllllllIlIIlllIllIlIl) {}
        llllllllllllllllllIlIIlllIllIllI.add(llllllllllllllllllIlIIlllIllIlIl, llllllllllllllllllIlIIlllIllIlll);
    }
}
