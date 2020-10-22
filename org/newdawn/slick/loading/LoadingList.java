package org.newdawn.slick.loading;

import java.util.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.openal.*;

public class LoadingList
{
    private /* synthetic */ int total;
    private /* synthetic */ ArrayList deferred;
    private static /* synthetic */ LoadingList single;
    
    public void remove(final DeferredResource lllllllllllllllllIlllIIIIIIlIIll) {
        Log.info(String.valueOf(new StringBuilder().append("Early loading of deferred resource due to req: ").append(lllllllllllllllllIlllIIIIIIlIIll.getDescription())));
        --this.total;
        this.deferred.remove(lllllllllllllllllIlllIIIIIIlIIll);
    }
    
    public static LoadingList get() {
        return LoadingList.single;
    }
    
    public static void setDeferredLoading(final boolean lllllllllllllllllIlllIIIIIlIIIIl) {
        LoadingList.single = new LoadingList();
        InternalTextureLoader.get().setDeferredLoading(lllllllllllllllllIlllIIIIIlIIIIl);
        SoundStore.get().setDeferredLoading(lllllllllllllllllIlllIIIIIlIIIIl);
    }
    
    public int getTotalResources() {
        return this.total;
    }
    
    private LoadingList() {
        this.deferred = new ArrayList();
    }
    
    static {
        LoadingList.single = new LoadingList();
    }
    
    public int getRemainingResources() {
        return this.deferred.size();
    }
    
    public DeferredResource getNext() {
        if (this.deferred.size() == 0) {
            return null;
        }
        return this.deferred.remove(0);
    }
    
    public static boolean isDeferredLoading() {
        return InternalTextureLoader.get().isDeferredLoading();
    }
    
    public void add(final DeferredResource lllllllllllllllllIlllIIIIIIllIIl) {
        ++this.total;
        this.deferred.add(lllllllllllllllllIlllIIIIIIllIIl);
    }
}
