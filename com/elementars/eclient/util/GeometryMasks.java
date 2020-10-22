package com.elementars.eclient.util;

import java.util.*;
import net.minecraft.util.*;

public final class GeometryMasks
{
    public static final /* synthetic */ HashMap<EnumFacing, Integer> FACEMAP;
    
    static {
        (FACEMAP = new HashMap<EnumFacing, Integer>()).put(EnumFacing.DOWN, 1);
        GeometryMasks.FACEMAP.put(EnumFacing.WEST, 16);
        GeometryMasks.FACEMAP.put(EnumFacing.NORTH, 4);
        GeometryMasks.FACEMAP.put(EnumFacing.SOUTH, 8);
        GeometryMasks.FACEMAP.put(EnumFacing.EAST, 32);
        GeometryMasks.FACEMAP.put(EnumFacing.UP, 2);
    }
    
    public static final class Quad
    {
        static {
            DOWN = 1;
            NORTH = 4;
            UP = 2;
            SOUTH = 8;
            EAST = 32;
            WEST = 16;
            ALL = 63;
        }
    }
    
    public static final class Line
    {
        static {
            UP_WEST = 18;
            ALL = 63;
            DOWN_WEST = 17;
            NORTH_EAST = 36;
            DOWN_SOUTH = 9;
            DOWN_EAST = 33;
            UP_SOUTH = 10;
            SOUTH_EAST = 40;
            UP_EAST = 34;
            SOUTH_WEST = 24;
            UP_NORTH = 6;
            DOWN_NORTH = 5;
            NORTH_WEST = 20;
        }
    }
}
