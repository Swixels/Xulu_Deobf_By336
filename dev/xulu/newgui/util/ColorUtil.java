package dev.xulu.newgui.util;

import java.awt.*;
import com.elementars.eclient.module.render.*;

public class ColorUtil
{
    public static Color getClickGUIColor() {
        return new Color(NewGui.red.getValue(), NewGui.green.getValue(), NewGui.blue.getValue());
    }
}
