package org.newdawn.slick.muffin;

import java.util.*;
import java.io.*;

public interface Muffin
{
    HashMap loadFile(final String p0) throws IOException;
    
    void saveFile(final HashMap p0, final String p1) throws IOException;
}
