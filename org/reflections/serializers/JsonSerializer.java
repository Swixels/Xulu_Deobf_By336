package org.reflections.serializers;

import com.google.gson.*;
import org.reflections.*;
import org.reflections.util.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.io.*;

public class JsonSerializer implements Serializer
{
    private /* synthetic */ Gson gson;
    
    private Gson getGson() {
        if (this.gson == null) {
            this.gson = new GsonBuilder().setPrettyPrinting().create();
        }
        return this.gson;
    }
    
    @Override
    public File save(final Reflections llIllIllIlIIII, final String llIllIllIlIIlI) {
        try {
            final File llIllIllIlIllI = Utils.prepareFile(llIllIllIlIIlI);
            Files.write(llIllIllIlIllI.toPath(), this.toString(llIllIllIlIIII).getBytes(Charset.defaultCharset()), new OpenOption[0]);
            return llIllIllIlIllI;
        }
        catch (IOException llIllIllIlIlIl) {
            throw new RuntimeException(llIllIllIlIlIl);
        }
    }
    
    @Override
    public String toString(final Reflections llIllIllIIlIII) {
        return this.getGson().toJson((Object)llIllIllIIlIII);
    }
    
    @Override
    public Reflections read(final InputStream llIllIllIlllIl) {
        return (Reflections)this.getGson().fromJson((Reader)new InputStreamReader(llIllIllIlllIl), (Class)Reflections.class);
    }
}
