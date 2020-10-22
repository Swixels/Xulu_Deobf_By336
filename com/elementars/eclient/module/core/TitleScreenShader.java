package com.elementars.eclient.module.core;

import dev.xulu.settings.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;

public class TitleScreenShader extends Module
{
    public static /* synthetic */ Value<Integer> fps;
    public final /* synthetic */ Value<String> mode;
    public final /* synthetic */ Value<GLSLShaders> shader;
    
    public TitleScreenShader() {
        super("TitleScreenShader", "Displays cool graphics for the main menu background", 0, Category.CORE, false);
        this.mode = this.register(new Value<String>("Mode", this, "Random", new String[] { "Random", "Select" }));
        this.shader = this.register(new Value<GLSLShaders>("Shader", this, GLSLShaders.ICYFIRE, GLSLShaders.values()));
        TitleScreenShader.fps = this.register(new Value<Integer>("FPS", this, 60, 5, 60));
    }
}
