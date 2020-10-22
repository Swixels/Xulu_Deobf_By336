package com.elementars.eclient.util;

import java.util.*;
import dev.xulu.settings.*;

public class ValueList extends ArrayList<Value<?>>
{
    @Override
    public boolean isEmpty() {
        if (super.isEmpty()) {
            return true;
        }
        boolean llIlIlIIIIIIlI = true;
        for (final Value<?> llIlIlIIIIIlII : this) {
            if (!(llIlIlIIIIIlII.getValue() instanceof Bind)) {
                llIlIlIIIIIIlI = false;
                break;
            }
        }
        return llIlIlIIIIIIlI;
    }
}
