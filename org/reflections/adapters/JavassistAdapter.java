package org.reflections.adapters;

import org.reflections.vfs.*;
import org.reflections.*;
import java.io.*;
import org.reflections.util.*;
import javassist.bytecode.annotation.*;
import javassist.bytecode.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;

public class JavassistAdapter implements MetadataAdapter<ClassFile, FieldInfo, MethodInfo>
{
    public static /* synthetic */ boolean includeInvisibleTag;
    
    private List<String> splitDescriptorToTypeNames(final String lllIIIllIIIIIl) {
        List<String> lllIIIllIIIIlI = new ArrayList<String>();
        if (lllIIIllIIIIIl != null && lllIIIllIIIIIl.length() != 0) {
            final List<Integer> lllIIIllIIIllI = new ArrayList<Integer>();
            final Descriptor.Iterator lllIIIllIIIlIl = new Descriptor.Iterator(lllIIIllIIIIIl);
            while (lllIIIllIIIlIl.hasNext()) {
                lllIIIllIIIllI.add(lllIIIllIIIlIl.next());
            }
            lllIIIllIIIllI.add(lllIIIllIIIIIl.length());
            final List<Integer> list;
            lllIIIllIIIIlI = IntStream.range(0, lllIIIllIIIllI.size() - 1).mapToObj(lllIIIIlIllIlI -> Descriptor.toString(lllIIIllIIIIIl.substring(list.get(lllIIIIlIllIlI), list.get(lllIIIIlIllIlI + 1)))).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
        }
        return lllIIIllIIIIlI;
    }
    
    @Override
    public String getSuperclassName(final ClassFile lllIIIllIllIll) {
        return lllIIIllIllIll.getSuperclass();
    }
    
    @Override
    public String getReturnTypeName(final MethodInfo lllIIlIIIlIlll) {
        String lllIIlIIIllIIl = lllIIlIIIlIlll.getDescriptor();
        lllIIlIIIllIIl = lllIIlIIIllIIl.substring(lllIIlIIIllIIl.lastIndexOf(")") + 1);
        return this.splitDescriptorToTypeNames(lllIIlIIIllIIl).get(0);
    }
    
    @Override
    public List<String> getParameterNames(final MethodInfo lllIIlIlIIllll) {
        String lllIIlIlIIlllI = lllIIlIlIIllll.getDescriptor();
        lllIIlIlIIlllI = lllIIlIlIIlllI.substring(lllIIlIlIIlllI.indexOf("(") + 1, lllIIlIlIIlllI.lastIndexOf(")"));
        return this.splitDescriptorToTypeNames(lllIIlIlIIlllI);
    }
    
    @Override
    public String getMethodName(final MethodInfo lllIIlIlIlIlIl) {
        return lllIIlIlIlIlIl.getName();
    }
    
    static {
        JavassistAdapter.includeInvisibleTag = true;
    }
    
    @Override
    public ClassFile getOrCreateClassObject(final Vfs.File lllIIlIIIIIlll) {
        InputStream lllIIlIIIIlIII = null;
        try {
            lllIIlIIIIlIII = lllIIlIIIIIlll.openInputStream();
            final DataInputStream lllIIlIIIIllII = new DataInputStream(new BufferedInputStream(lllIIlIIIIlIII));
            final short lllIIlIIIIIlII = (short)new ClassFile(lllIIlIIIIllII);
            return (ClassFile)lllIIlIIIIIlII;
        }
        catch (IOException lllIIlIIIIlIll) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not create class file from ").append(lllIIlIIIIIlll.getName())), lllIIlIIIIlIll);
        }
        finally {
            Utils.close(lllIIlIIIIlIII);
        }
    }
    
    @Override
    public List<String> getParameterAnnotationNames(final MethodInfo lllIIlIIlIlIll, final int lllIIlIIlIIlIl) {
        final List<String> lllIIlIIlIlIIl = new ArrayList<String>();
        final List<ParameterAnnotationsAttribute> lllIIlIIlIlIII = Arrays.asList((ParameterAnnotationsAttribute)lllIIlIIlIlIll.getAttribute("RuntimeVisibleParameterAnnotations"), (ParameterAnnotationsAttribute)lllIIlIIlIlIll.getAttribute("RuntimeInvisibleParameterAnnotations"));
        for (final ParameterAnnotationsAttribute lllIIlIIlIllIl : lllIIlIIlIlIII) {
            if (lllIIlIIlIllIl != null) {
                final Annotation[][] lllIIlIIlIlllI = lllIIlIIlIllIl.getAnnotations();
                if (lllIIlIIlIIlIl >= lllIIlIIlIlllI.length) {
                    continue;
                }
                final Annotation[] lllIIlIIlIllll = lllIIlIIlIlllI[lllIIlIIlIIlIl];
                lllIIlIIlIlIIl.addAll(this.getAnnotationNames(lllIIlIIlIllll));
            }
        }
        return lllIIlIIlIlIIl;
    }
    
    @Override
    public List<String> getMethodAnnotationNames(final MethodInfo lllIIlIIlllIll) {
        return this.getAnnotationNames((AnnotationsAttribute)lllIIlIIlllIll.getAttribute("RuntimeVisibleAnnotations"), JavassistAdapter.includeInvisibleTag ? ((AnnotationsAttribute)lllIIlIIlllIll.getAttribute("RuntimeInvisibleAnnotations")) : null);
    }
    
    @Override
    public String getMethodModifier(final MethodInfo lllIIIlllllllI) {
        final int lllIIIllllllIl = lllIIIlllllllI.getAccessFlags();
        return AccessFlag.isPrivate(lllIIIllllllIl) ? "private" : (AccessFlag.isProtected(lllIIIllllllIl) ? "protected" : (this.isPublic(lllIIIllllllIl) ? "public" : ""));
    }
    
    @Override
    public boolean isPublic(final Object lllIIIlllIIlII) {
        final Integer lllIIIlllIIlIl = (lllIIIlllIIlII instanceof ClassFile) ? ((ClassFile)lllIIIlllIIlII).getAccessFlags() : ((lllIIIlllIIlII instanceof FieldInfo) ? ((FieldInfo)lllIIIlllIIlII).getAccessFlags() : ((lllIIIlllIIlII instanceof MethodInfo) ? Integer.valueOf(((MethodInfo)lllIIIlllIIlII).getAccessFlags()) : null));
        return lllIIIlllIIlIl != null && AccessFlag.isPublic((int)lllIIIlllIIlIl);
    }
    
    private List<String> getAnnotationNames(final Annotation[] lllIIIllIIllII) {
        return Arrays.stream(lllIIIllIIllII).map((Function<? super Annotation, ?>)Annotation::getTypeName).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
    }
    
    @Override
    public String getMethodKey(final ClassFile lllIIIllllIllI, final MethodInfo lllIIIllllIlIl) {
        return String.valueOf(new StringBuilder().append(this.getMethodName(lllIIIllllIlIl)).append("(").append(Utils.join(this.getParameterNames(lllIIIllllIlIl), ", ")).append(")"));
    }
    
    @Override
    public List<String> getClassAnnotationNames(final ClassFile lllIIlIlIIIlll) {
        return this.getAnnotationNames((AnnotationsAttribute)lllIIlIlIIIlll.getAttribute("RuntimeVisibleAnnotations"), JavassistAdapter.includeInvisibleTag ? ((AnnotationsAttribute)lllIIlIlIIIlll.getAttribute("RuntimeInvisibleAnnotations")) : null);
    }
    
    @Override
    public boolean acceptsInput(final String lllIIIllIlIlII) {
        return lllIIIllIlIlII.endsWith(".class");
    }
    
    @Override
    public List<MethodInfo> getMethods(final ClassFile lllIIlIlIllIII) {
        return (List<MethodInfo>)lllIIlIlIllIII.getMethods();
    }
    
    @Override
    public String getClassName(final ClassFile lllIIIlllIIIII) {
        return lllIIIlllIIIII.getName();
    }
    
    @Override
    public List<String> getFieldAnnotationNames(final FieldInfo lllIIlIlIIIIIl) {
        return this.getAnnotationNames((AnnotationsAttribute)lllIIlIlIIIIIl.getAttribute("RuntimeVisibleAnnotations"), JavassistAdapter.includeInvisibleTag ? ((AnnotationsAttribute)lllIIlIlIIIIIl.getAttribute("RuntimeInvisibleAnnotations")) : null);
    }
    
    private List<String> getAnnotationNames(final AnnotationsAttribute... lllIIIllIlIIII) {
        if (lllIIIllIlIIII != null) {
            return Arrays.stream(lllIIIllIlIIII).filter(Objects::nonNull).flatMap(lllIIIIlIllIII -> Arrays.stream(lllIIIIlIllIII.getAnnotations())).map((Function<? super Object, ?>)Annotation::getTypeName).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
        }
        return Collections.emptyList();
    }
    
    @Override
    public String getFieldName(final FieldInfo lllIIlIIIlIIll) {
        return lllIIlIIIlIIll.getName();
    }
    
    @Override
    public String getMethodFullKey(final ClassFile lllIIIlllIlIll, final MethodInfo lllIIIlllIlIlI) {
        return String.valueOf(new StringBuilder().append(this.getClassName(lllIIIlllIlIll)).append(".").append(this.getMethodKey(lllIIIlllIlIll, lllIIIlllIlIlI)));
    }
    
    @Override
    public List<String> getInterfacesNames(final ClassFile lllIIIllIlIlll) {
        return Arrays.asList(lllIIIllIlIlll.getInterfaces());
    }
    
    @Override
    public List<FieldInfo> getFields(final ClassFile lllIIlIlIlllII) {
        return (List<FieldInfo>)lllIIlIlIlllII.getFields();
    }
}
