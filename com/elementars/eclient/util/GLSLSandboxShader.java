package com.elementars.eclient.util;

import org.lwjgl.opengl.*;
import java.io.*;
import java.nio.charset.*;

public class GLSLSandboxShader
{
    private final /* synthetic */ int mouseUniform;
    private final /* synthetic */ int resolutionUniform;
    private final /* synthetic */ int programId;
    private final /* synthetic */ int timeUniform;
    
    public void useShader(final int lllllllllllllllllIIlIIIlIllIlIlI, final int lllllllllllllllllIIlIIIlIllIlIIl, final float lllllllllllllllllIIlIIIlIllIlIII, final float lllllllllllllllllIIlIIIlIllIIlll, final float lllllllllllllllllIIlIIIlIllIIllI) {
        GL20.glUseProgram(this.programId);
        GL20.glUniform2f(this.resolutionUniform, (float)lllllllllllllllllIIlIIIlIllIlIlI, (float)lllllllllllllllllIIlIIIlIllIlIIl);
        GL20.glUniform2f(this.mouseUniform, lllllllllllllllllIIlIIIlIllIlIII / lllllllllllllllllIIlIIIlIllIlIlI, 1.0f - lllllllllllllllllIIlIIIlIllIIlll / lllllllllllllllllIIlIIIlIllIlIIl);
        GL20.glUniform1f(this.timeUniform, lllllllllllllllllIIlIIIlIllIIllI);
    }
    
    private int createShader(final String lllllllllllllllllIIlIIIlIlIllllI, final InputStream lllllllllllllllllIIlIIIlIlIlIlll, final int lllllllllllllllllIIlIIIlIlIlllII) throws IOException {
        final int lllllllllllllllllIIlIIIlIlIllIll = GL20.glCreateShader(lllllllllllllllllIIlIIIlIlIlllII);
        GL20.glShaderSource(lllllllllllllllllIIlIIIlIlIllIll, (CharSequence)this.readStreamToString(lllllllllllllllllIIlIIIlIlIlIlll));
        GL20.glCompileShader(lllllllllllllllllIIlIIIlIlIllIll);
        final int lllllllllllllllllIIlIIIlIlIllIlI = GL20.glGetShaderi(lllllllllllllllllIIlIIIlIlIllIll, 35713);
        if (lllllllllllllllllIIlIIIlIlIllIlI == 0) {
            System.err.println(GL20.glGetShaderInfoLog(lllllllllllllllllIIlIIIlIlIllIll, GL20.glGetShaderi(lllllllllllllllllIIlIIIlIlIllIll, 35716)));
            System.err.println(String.valueOf(new StringBuilder().append("Caused by ").append(lllllllllllllllllIIlIIIlIlIllllI)));
            throw new IllegalStateException(String.valueOf(new StringBuilder().append("Failed to compile shader: ").append(lllllllllllllllllIIlIIIlIlIllllI)));
        }
        return lllllllllllllllllIIlIIIlIlIllIll;
    }
    
    private String readStreamToString(final InputStream lllllllllllllllllIIlIIIlIlIIlllI) throws IOException {
        final ByteArrayOutputStream lllllllllllllllllIIlIIIlIlIIllIl = new ByteArrayOutputStream();
        final byte[] lllllllllllllllllIIlIIIlIlIIllII = new byte[512];
        int lllllllllllllllllIIlIIIlIlIIlIll;
        while ((lllllllllllllllllIIlIIIlIlIIlIll = lllllllllllllllllIIlIIIlIlIIlllI.read(lllllllllllllllllIIlIIIlIlIIllII, 0, lllllllllllllllllIIlIIIlIlIIllII.length)) != -1) {
            lllllllllllllllllIIlIIIlIlIIllIl.write(lllllllllllllllllIIlIIIlIlIIllII, 0, lllllllllllllllllIIlIIIlIlIIlIll);
        }
        return new String(lllllllllllllllllIIlIIIlIlIIllIl.toByteArray(), StandardCharsets.UTF_8);
    }
    
    public GLSLSandboxShader(final String lllllllllllllllllIIlIIIlIllllIlI) throws IOException {
        final int lllllllllllllllllIIlIIIlIlllllIl = GL20.glCreateProgram();
        GL20.glAttachShader(lllllllllllllllllIIlIIIlIlllllIl, this.createShader("/shaders/passthrough.vsh", GLSLSandboxShader.class.getResourceAsStream("/shaders/passthrough.vsh"), 35633));
        GL20.glAttachShader(lllllllllllllllllIIlIIIlIlllllIl, this.createShader(lllllllllllllllllIIlIIIlIllllIlI, GLSLSandboxShader.class.getResourceAsStream(lllllllllllllllllIIlIIIlIllllIlI), 35632));
        GL20.glLinkProgram(lllllllllllllllllIIlIIIlIlllllIl);
        final int lllllllllllllllllIIlIIIlIlllllII = GL20.glGetProgrami(lllllllllllllllllIIlIIIlIlllllIl, 35714);
        if (lllllllllllllllllIIlIIIlIlllllII == 0) {
            System.err.println(GL20.glGetProgramInfoLog(lllllllllllllllllIIlIIIlIlllllIl, GL20.glGetProgrami(lllllllllllllllllIIlIIIlIlllllIl, 35716)));
            throw new IllegalStateException("Shader failed to link");
        }
        this.programId = lllllllllllllllllIIlIIIlIlllllIl;
        GL20.glUseProgram(lllllllllllllllllIIlIIIlIlllllIl);
        this.timeUniform = GL20.glGetUniformLocation(lllllllllllllllllIIlIIIlIlllllIl, (CharSequence)"time");
        this.mouseUniform = GL20.glGetUniformLocation(lllllllllllllllllIIlIIIlIlllllIl, (CharSequence)"mouse");
        this.resolutionUniform = GL20.glGetUniformLocation(lllllllllllllllllIIlIIIlIlllllIl, (CharSequence)"resolution");
        GL20.glUseProgram(0);
    }
}
