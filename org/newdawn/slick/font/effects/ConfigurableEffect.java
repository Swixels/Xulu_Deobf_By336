package org.newdawn.slick.font.effects;

import java.util.*;

public interface ConfigurableEffect extends Effect
{
    void setValues(final List p0);
    
    List getValues();
    
    public interface Value
    {
        String getString();
        
        String getName();
        
        void showDialog();
        
        Object getObject();
        
        void setString(final String p0);
    }
}
