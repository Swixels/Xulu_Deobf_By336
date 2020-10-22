package org.reflections.adapters;

import java.util.function.*;
import java.util.stream.*;
import java.lang.annotation.*;
import org.reflections.vfs.*;
import org.reflections.util.*;
import java.lang.reflect.*;
import org.reflections.*;
import java.util.*;

public class JavaReflectionAdapter implements MetadataAdapter<Class, Field, Member>
{
    @Override
    public List<String> getInterfacesNames(final Class llllllllllllllllllIIlIlIIllIIIll) {
        final Class[] llllllllllllllllllIIlIlIIllIIlII = llllllllllllllllllIIlIlIIllIIIll.getInterfaces();
        return (List<String>)((llllllllllllllllllIIlIlIIllIIlII != null) ? Arrays.stream(llllllllllllllllllIIlIlIIllIIlII).map((Function<? super Class, ?>)Class::getName).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()) : Collections.emptyList());
    }
    
    @Override
    public String getMethodFullKey(final Class llllllllllllllllllIIlIlIIlllllll, final Member llllllllllllllllllIIlIlIIllllllI) {
        return String.valueOf(new StringBuilder().append(this.getClassName(llllllllllllllllllIIlIlIIlllllll)).append(".").append(this.getMethodKey(llllllllllllllllllIIlIlIIlllllll, llllllllllllllllllIIlIlIIllllllI)));
    }
    
    @Override
    public boolean acceptsInput(final String llllllllllllllllllIIlIlIIlIlllll) {
        return llllllllllllllllllIIlIlIIlIlllll.endsWith(".class");
    }
    
    @Override
    public String getMethodName(final Member llllllllllllllllllIIlIlIllIIllll) {
        return (llllllllllllllllllIIlIlIllIIllll instanceof Method) ? llllllllllllllllllIIlIlIllIIllll.getName() : ((llllllllllllllllllIIlIlIllIIllll instanceof Constructor) ? "<init>" : null);
    }
    
    private List<String> getAnnotationNames(final Annotation[] llllllllllllllllllIIlIlIIlIllIll) {
        return Arrays.stream(llllllllllllllllllIIlIlIIlIllIll).map(llllllllllllllllllIIlIIlllllIlII -> llllllllllllllllllIIlIIlllllIlII.annotationType().getName()).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
    }
    
    @Override
    public List<String> getParameterNames(final Member llllllllllllllllllIIlIlIllIIlIll) {
        final Class<?>[] llllllllllllllllllIIlIlIllIIlIlI = (llllllllllllllllllIIlIlIllIIlIll instanceof Method) ? ((Method)llllllllllllllllllIIlIlIllIIlIll).getParameterTypes() : ((llllllllllllllllllIIlIlIllIIlIll instanceof Constructor) ? ((Constructor)llllllllllllllllllIIlIlIllIIlIll).getParameterTypes() : null);
        return (List<String>)((llllllllllllllllllIIlIlIllIIlIlI != null) ? Arrays.stream(llllllllllllllllllIIlIlIllIIlIlI).map((Function<? super Class<?>, ?>)JavaReflectionAdapter::getName).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()) : Collections.emptyList());
    }
    
    @Override
    public Class getOrCreateClassObject(final Vfs.File llllllllllllllllllIIlIlIlIIllIIl) throws Exception {
        return this.getOrCreateClassObject(llllllllllllllllllIIlIlIlIIllIIl, (ClassLoader[])null);
    }
    
    @Override
    public List<String> getClassAnnotationNames(final Class llllllllllllllllllIIlIlIllIIIIlI) {
        return this.getAnnotationNames(llllllllllllllllllIIlIlIllIIIIlI.getDeclaredAnnotations());
    }
    
    @Override
    public List<String> getMethodAnnotationNames(final Member llllllllllllllllllIIlIlIlIllIlII) {
        final Annotation[] llllllllllllllllllIIlIlIlIllIllI = (llllllllllllllllllIIlIlIlIllIlII instanceof Method) ? ((Method)llllllllllllllllllIIlIlIlIllIlII).getDeclaredAnnotations() : ((llllllllllllllllllIIlIlIlIllIlII instanceof Constructor) ? ((Constructor)llllllllllllllllllIIlIlIlIllIlII).getDeclaredAnnotations() : null);
        return this.getAnnotationNames(llllllllllllllllllIIlIlIlIllIllI);
    }
    
    public static String getName(final Class llllllllllllllllllIIlIlIIlIlIlII) {
        if (llllllllllllllllllIIlIlIIlIlIlII.isArray()) {
            try {
                Class llllllllllllllllllIIlIlIIlIlIllI = llllllllllllllllllIIlIlIIlIlIlII;
                int llllllllllllllllllIIlIlIIlIlIlIl = 0;
                while (llllllllllllllllllIIlIlIIlIlIllI.isArray()) {
                    ++llllllllllllllllllIIlIlIIlIlIlIl;
                    llllllllllllllllllIIlIlIIlIlIllI = llllllllllllllllllIIlIlIIlIlIllI.getComponentType();
                }
                return String.valueOf(new StringBuilder().append(llllllllllllllllllIIlIlIIlIlIllI.getName()).append(Utils.repeat("[]", llllllllllllllllllIIlIlIIlIlIlIl)));
            }
            catch (Throwable t) {}
        }
        return llllllllllllllllllIIlIlIIlIlIlII.getName();
    }
    
    @Override
    public List<String> getFieldAnnotationNames(final Field llllllllllllllllllIIlIlIlIllllII) {
        return this.getAnnotationNames(llllllllllllllllllIIlIlIlIllllII.getDeclaredAnnotations());
    }
    
    @Override
    public boolean isPublic(final Object llllllllllllllllllIIlIlIIlllIlIl) {
        final Integer llllllllllllllllllIIlIlIIlllIllI = (llllllllllllllllllIIlIlIIlllIlIl instanceof Class) ? ((Class)llllllllllllllllllIIlIlIIlllIlIl).getModifiers() : ((llllllllllllllllllIIlIlIIlllIlIl instanceof Member) ? Integer.valueOf(((Member)llllllllllllllllllIIlIlIIlllIlIl).getModifiers()) : null);
        return llllllllllllllllllIIlIlIIlllIllI != null && Modifier.isPublic(llllllllllllllllllIIlIlIIlllIllI);
    }
    
    @Override
    public String getMethodModifier(final Member llllllllllllllllllIIlIlIlIIIlIll) {
        return Modifier.toString(llllllllllllllllllIIlIlIlIIIlIll.getModifiers());
    }
    
    @Override
    public String getSuperclassName(final Class llllllllllllllllllIIlIlIIllIlIlI) {
        final Class llllllllllllllllllIIlIlIIllIlIll = llllllllllllllllllIIlIlIIllIlIlI.getSuperclass();
        return (llllllllllllllllllIIlIlIIllIlIll != null) ? llllllllllllllllllIIlIlIIllIlIll.getName() : "";
    }
    
    @Override
    public List<Field> getFields(final Class llllllllllllllllllIIlIlIllIllIll) {
        return Arrays.asList(llllllllllllllllllIIlIlIllIllIll.getDeclaredFields());
    }
    
    @Override
    public String getReturnTypeName(final Member llllllllllllllllllIIlIlIlIlIIlII) {
        return ((Method)llllllllllllllllllIIlIlIlIlIIlII).getReturnType().getName();
    }
    
    @Override
    public List<String> getParameterAnnotationNames(final Member llllllllllllllllllIIlIlIlIlIllIl, final int llllllllllllllllllIIlIlIlIlIlIII) {
        final Annotation[][] llllllllllllllllllIIlIlIlIlIlIll = (llllllllllllllllllIIlIlIlIlIllIl instanceof Method) ? ((Method)llllllllllllllllllIIlIlIlIlIllIl).getParameterAnnotations() : ((llllllllllllllllllIIlIlIlIlIllIl instanceof Constructor) ? ((Constructor)llllllllllllllllllIIlIlIlIlIllIl).getParameterAnnotations() : null);
        return this.getAnnotationNames((Annotation[])((llllllllllllllllllIIlIlIlIlIlIll != null) ? llllllllllllllllllIIlIlIlIlIlIll[llllllllllllllllllIIlIlIlIlIlIII] : null));
    }
    
    public Class getOrCreateClassObject(final Vfs.File llllllllllllllllllIIlIlIlIIlIlII, final ClassLoader... llllllllllllllllllIIlIlIlIIlIIll) throws Exception {
        final String llllllllllllllllllIIlIlIlIIlIIlI = llllllllllllllllllIIlIlIlIIlIlII.getRelativePath().replace("/", ".").replace(".class", "");
        return ReflectionUtils.forName(llllllllllllllllllIIlIlIlIIlIIlI, llllllllllllllllllIIlIlIlIIlIIll);
    }
    
    @Override
    public List<Member> getMethods(final Class llllllllllllllllllIIlIlIllIlIllI) {
        final List<Member> llllllllllllllllllIIlIlIllIlIlIl = new ArrayList<Member>();
        llllllllllllllllllIIlIlIllIlIlIl.addAll(Arrays.asList(llllllllllllllllllIIlIlIllIlIllI.getDeclaredMethods()));
        llllllllllllllllllIIlIlIllIlIlIl.addAll(Arrays.asList(llllllllllllllllllIIlIlIllIlIllI.getDeclaredConstructors()));
        return llllllllllllllllllIIlIlIllIlIlIl;
    }
    
    @Override
    public String getMethodKey(final Class llllllllllllllllllIIlIlIlIIIIlll, final Member llllllllllllllllllIIlIlIlIIIIlII) {
        return String.valueOf(new StringBuilder().append(this.getMethodName(llllllllllllllllllIIlIlIlIIIIlII)).append("(").append(Utils.join(this.getParameterNames(llllllllllllllllllIIlIlIlIIIIlII), ", ")).append(")"));
    }
    
    @Override
    public String getClassName(final Class llllllllllllllllllIIlIlIIlllIIIl) {
        return llllllllllllllllllIIlIlIIlllIIIl.getName();
    }
    
    @Override
    public String getFieldName(final Field llllllllllllllllllIIlIlIlIlIIIII) {
        return llllllllllllllllllIIlIlIlIlIIIII.getName();
    }
}
