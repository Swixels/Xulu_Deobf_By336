package com.elementars.eclient.util;

import com.elementars.eclient.module.render.*;
import java.util.function.*;
import java.util.*;

public class SpotSet extends HashSet<LogoutSpots.LogoutPos>
{
    public Pair<Boolean, LogoutSpots.LogoutPos> removeIfReturn(final Predicate<? super LogoutSpots.LogoutPos> llllllllllllllllllIIlIllllIlllIl) {
        final Set<LogoutSpots.LogoutPos> llllllllllllllllllIIlIllllIlllII = new HashSet<LogoutSpots.LogoutPos>(this);
        final boolean llllllllllllllllllIIlIllllIllIll = this.removeIf(llllllllllllllllllIIlIllllIlllIl);
        if (llllllllllllllllllIIlIllllIllIll) {
            LogoutSpots.LogoutPos llllllllllllllllllIIlIllllIlllll = null;
            for (final LogoutSpots.LogoutPos llllllllllllllllllIIlIlllllIIIII : llllllllllllllllllIIlIllllIlllII) {
                if (!this.contains(llllllllllllllllllIIlIlllllIIIII)) {
                    llllllllllllllllllIIlIllllIlllll = llllllllllllllllllIIlIlllllIIIII;
                    break;
                }
            }
            return new Pair<Boolean, LogoutSpots.LogoutPos>(true, llllllllllllllllllIIlIllllIlllll);
        }
        return new Pair<Boolean, LogoutSpots.LogoutPos>(false, null);
    }
}
