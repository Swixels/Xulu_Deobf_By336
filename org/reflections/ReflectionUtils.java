package org.reflections;

import java.lang.annotation.*;
import org.reflections.util.*;
import java.util.*;
import java.util.regex.*;
import java.lang.reflect.*;
import java.util.function.*;
import java.util.stream.*;

public abstract class ReflectionUtils
{
    private static /* synthetic */ List<String> primitiveDescriptors;
    private static /* synthetic */ List<String> primitiveNames;
    private static /* synthetic */ List<Class> primitiveTypes;
    public static /* synthetic */ boolean includeObject;
    
    public static <T extends AnnotatedElement> Predicate<T> withAnnotation(final Class<? extends Annotation> lllllllllllllllllIIIIlllIIlIIllI) {
        return lllllllllllllllllIIIIllIIIlIIllI -> lllllllllllllllllIIIIllIIIlIIllI != null && lllllllllllllllllIIIIllIIIlIIllI.isAnnotationPresent(lllllllllllllllllIIIIlllIIlIIllI);
    }
    
    public static Predicate<Class<?>> withClassModifier(final int lllllllllllllllllIIIIllIlllllIIl) {
        return lllllllllllllllllIIIIllIlIIllIII -> lllllllllllllllllIIIIllIlIIllIII != null && (lllllllllllllllllIIIIllIlIIllIII.getModifiers() & lllllllllllllllllIIIIllIlllllIIl) != 0x0;
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withAnnotations(final Annotation... lllllllllllllllllIIIIlllIIIlllII) {
        Annotation[] lllllllllllllllllIIIIllIIlIIIlII;
        return lllllllllllllllllIIIIllIIlIIIIlI -> {
            if (lllllllllllllllllIIIIllIIlIIIIlI != null) {
                lllllllllllllllllIIIIllIIlIIIlII = lllllllllllllllllIIIIllIIlIIIIlI.getAnnotations();
                if (lllllllllllllllllIIIIllIIlIIIlII.length == lllllllllllllllllIIIIlllIIIlllII.length) {
                    return IntStream.range(0, lllllllllllllllllIIIIllIIlIIIlII.length).allMatch(lllllllllllllllllIIIIllIIIlllIIl -> areAnnotationMembersMatching(lllllllllllllllllIIIIllIIlIIIlII[lllllllllllllllllIIIIllIIIlllIIl], lllllllllllllllllIIIIlllIIIlllII[lllllllllllllllllIIIIllIIIlllIIl]));
                }
            }
            return true;
        };
    }
    
    public static Set<Method> getAllMethods(final Class<?> lllllllllllllllllIIIIlllIlllllIl, final Predicate<? super Method>... lllllllllllllllllIIIIlllIlllllll) {
        final Set<Method> lllllllllllllllllIIIIlllIllllllI = new HashSet<Method>();
        for (final Class<?> lllllllllllllllllIIIIllllIIIIIIl : getAllSuperTypes(lllllllllllllllllIIIIlllIlllllIl, (Predicate<? super Class<?>>[])new Predicate[0])) {
            lllllllllllllllllIIIIlllIllllllI.addAll(getMethods(lllllllllllllllllIIIIllllIIIIIIl, lllllllllllllllllIIIIlllIlllllll));
        }
        return lllllllllllllllllIIIIlllIllllllI;
    }
    
    private static List<String> getPrimitiveDescriptors() {
        initPrimitives();
        return ReflectionUtils.primitiveDescriptors;
    }
    
    public static Predicate<Member> withParametersAssignableFrom(final Class... lllllllllllllllllIIIIlllIIIlIlII) {
        return lllllllllllllllllIIIIllIIlIlIllI -> isAssignable(parameterTypes(lllllllllllllllllIIIIllIIlIlIllI), lllllllllllllllllIIIIlllIIIlIlII);
    }
    
    public static <T> Predicate<Field> withTypeAssignableTo(final Class<T> lllllllllllllllllIIIIlllIIIIIlII) {
        return lllllllllllllllllIIIIllIlIIIIIII -> lllllllllllllllllIIIIllIlIIIIIII != null && lllllllllllllllllIIIIlllIIIIIlII.isAssignableFrom(lllllllllllllllllIIIIllIlIIIIIII.getType());
    }
    
    public static Set<Class<?>> getAllSuperTypes(final Class<?> lllllllllllllllllIIIIllllIIllIlI, final Predicate<? super Class<?>>... lllllllllllllllllIIIIllllIIllIIl) {
        final Set<Class<?>> lllllllllllllllllIIIIllllIIllIII = new LinkedHashSet<Class<?>>();
        if (lllllllllllllllllIIIIllllIIllIlI != null && (ReflectionUtils.includeObject || !lllllllllllllllllIIIIllllIIllIlI.equals(Object.class))) {
            lllllllllllllllllIIIIllllIIllIII.add(lllllllllllllllllIIIIllllIIllIlI);
            for (final Class<?> lllllllllllllllllIIIIllllIIllIll : getSuperTypes(lllllllllllllllllIIIIllllIIllIlI)) {
                lllllllllllllllllIIIIllllIIllIII.addAll(getAllSuperTypes(lllllllllllllllllIIIIllllIIllIll, (Predicate<? super Class<?>>[])new Predicate[0]));
            }
        }
        return Utils.filter(lllllllllllllllllIIIIllllIIllIII, lllllllllllllllllIIIIllllIIllIIl);
    }
    
    public static Set<Method> getMethods(final Class<?> lllllllllllllllllIIIIlllIlllIllI, final Predicate<? super Method>... lllllllllllllllllIIIIlllIlllIIll) {
        return Utils.filter(lllllllllllllllllIIIIlllIlllIllI.isInterface() ? lllllllllllllllllIIIIlllIlllIllI.getMethods() : lllllllllllllllllIIIIlllIlllIllI.getDeclaredMethods(), lllllllllllllllllIIIIlllIlllIIll);
    }
    
    public static <T> Set<Class<? extends T>> forNames(final Collection<String> lllllllllllllllllIIIIllIllIlIllI, final ClassLoader... lllllllllllllllllIIIIllIllIlIlIl) {
        return lllllllllllllllllIIIIllIllIlIllI.stream().map(lllllllllllllllllIIIIllIlIIlllII -> forName(lllllllllllllllllIIIIllIlIIlllII, lllllllllllllllllIIIIllIllIlIlIl)).filter(Objects::nonNull).collect((Collector<? super Object, ?, Set<Class<? extends T>>>)Collectors.toCollection((Supplier<R>)LinkedHashSet::new));
    }
    
    public static <T extends Member> Predicate<T> withName(final String lllllllllllllllllIIIIlllIIlIllll) {
        return lllllllllllllllllIIIIllIIIIlIlII -> lllllllllllllllllIIIIllIIIIlIlII != null && lllllllllllllllllIIIIllIIIIlIlII.getName().equals(lllllllllllllllllIIIIlllIIlIllll);
    }
    
    public static Predicate<Member> withAnyParameterAnnotation(final Class<? extends Annotation> lllllllllllllllllIIIIlllIIIIlllI) {
        return lllllllllllllllllIIIIllIIllIlIII -> lllllllllllllllllIIIIllIIllIlIII != null && annotationTypes(parameterAnnotations(lllllllllllllllllIIIIllIIllIlIII)).stream().anyMatch(lllllllllllllllllIIIIllIIllIIIII -> lllllllllllllllllIIIIllIIllIIIII.equals(lllllllllllllllllIIIIlllIIIIlllI));
    }
    
    public static Set<Constructor> getConstructors(final Class<?> lllllllllllllllllIIIIlllIllIIIII, final Predicate<? super Constructor>... lllllllllllllllllIIIIlllIllIIIIl) {
        return (Set<Constructor>)Utils.filter(lllllllllllllllllIIIIlllIllIIIII.getDeclaredConstructors(), (Predicate<? super Constructor<?>>[])lllllllllllllllllIIIIlllIllIIIIl);
    }
    
    private static Set<Annotation> parameterAnnotations(final Member lllllllllllllllllIIIIllIllIIllll) {
        final Annotation[][] lllllllllllllllllIIIIllIllIIlllI = (lllllllllllllllllIIIIllIllIIllll instanceof Method) ? ((Method)lllllllllllllllllIIIIllIllIIllll).getParameterAnnotations() : ((lllllllllllllllllIIIIllIllIIllll instanceof Constructor) ? ((Constructor)lllllllllllllllllIIIIllIllIIllll).getParameterAnnotations() : null);
        return Arrays.stream(lllllllllllllllllIIIIllIllIIlllI).flatMap((Function<? super Annotation[], ? extends Stream<?>>)Arrays::stream).collect((Collector<? super Object, ?, Set<Annotation>>)Collectors.toSet());
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withAnnotation(final Annotation lllllllllllllllllIIIIlllIIIlllll) {
        return lllllllllllllllllIIIIllIIIllIIII -> lllllllllllllllllIIIIllIIIllIIII != null && lllllllllllllllllIIIIllIIIllIIII.isAnnotationPresent(lllllllllllllllllIIIIlllIIIlllll.annotationType()) && areAnnotationMembersMatching(lllllllllllllllllIIIIllIIIllIIII.getAnnotation(lllllllllllllllllIIIIlllIIIlllll.annotationType()), lllllllllllllllllIIIIlllIIIlllll);
    }
    
    static {
        ReflectionUtils.includeObject = false;
    }
    
    public static Set<Class<?>> getSuperTypes(final Class<?> lllllllllllllllllIIIIllllIIIlllI) {
        final Set<Class<?>> lllllllllllllllllIIIIllllIIIllIl = new LinkedHashSet<Class<?>>();
        final Class<?> lllllllllllllllllIIIIllllIIIllII = lllllllllllllllllIIIIllllIIIlllI.getSuperclass();
        final Class<?>[] lllllllllllllllllIIIIllllIIIlIll = lllllllllllllllllIIIIllllIIIlllI.getInterfaces();
        if (lllllllllllllllllIIIIllllIIIllII != null && (ReflectionUtils.includeObject || !lllllllllllllllllIIIIllllIIIllII.equals(Object.class))) {
            lllllllllllllllllIIIIllllIIIllIl.add(lllllllllllllllllIIIIllllIIIllII);
        }
        if (lllllllllllllllllIIIIllllIIIlIll != null && lllllllllllllllllIIIIllllIIIlIll.length > 0) {
            lllllllllllllllllIIIIllllIIIllIl.addAll(Arrays.asList(lllllllllllllllllIIIIllllIIIlIll));
        }
        return lllllllllllllllllIIIIllllIIIllIl;
    }
    
    public static <T extends AnnotatedElement> Set<Annotation> getAnnotations(final T lllllllllllllllllIIIIlllIIlllIII, final Predicate<Annotation>... lllllllllllllllllIIIIlllIIlllIIl) {
        return Utils.filter(lllllllllllllllllIIIIlllIIlllIII.getDeclaredAnnotations(), (Predicate<? super Annotation>[])lllllllllllllllllIIIIlllIIlllIIl);
    }
    
    public static <T extends Member> Predicate<T> withPrefix(final String lllllllllllllllllIIIIlllIIlIllII) {
        return lllllllllllllllllIIIIllIIIIllIII -> lllllllllllllllllIIIIllIIIIllIII != null && lllllllllllllllllIIIIllIIIIllIII.getName().startsWith(lllllllllllllllllIIIIlllIIlIllII);
    }
    
    private static Class<? extends Annotation>[] annotationTypes(final Annotation[] lllllllllllllllllIIIIllIllIIIllI) {
        return Arrays.stream(lllllllllllllllllIIIIllIllIIIllI).map((Function<? super Annotation, ?>)Annotation::annotationType).toArray(Class[]::new);
    }
    
    public static Set<Field> getAllFields(final Class<?> lllllllllllllllllIIIIlllIlIlIlIl, final Predicate<? super Field>... lllllllllllllllllIIIIlllIlIlIlII) {
        final Set<Field> lllllllllllllllllIIIIlllIlIlIllI = new HashSet<Field>();
        for (final Class<?> lllllllllllllllllIIIIlllIlIllIIl : getAllSuperTypes(lllllllllllllllllIIIIlllIlIlIlIl, (Predicate<? super Class<?>>[])new Predicate[0])) {
            lllllllllllllllllIIIIlllIlIlIllI.addAll(getFields(lllllllllllllllllIIIIlllIlIllIIl, lllllllllllllllllIIIIlllIlIlIlII));
        }
        return lllllllllllllllllIIIIlllIlIlIllI;
    }
    
    public static <T> Predicate<Method> withReturnType(final Class<T> lllllllllllllllllIIIIlllIIIIIIIl) {
        return lllllllllllllllllIIIIllIlIIIIlII -> lllllllllllllllllIIIIllIlIIIIlII != null && lllllllllllllllllIIIIllIlIIIIlII.getReturnType().equals(lllllllllllllllllIIIIlllIIIIIIIl);
    }
    
    public static Predicate<Member> withParametersCount(final int lllllllllllllllllIIIIlllIIIlIIII) {
        return lllllllllllllllllIIIIllIIlIlllII -> lllllllllllllllllIIIIllIIlIlllII != null && parameterTypes(lllllllllllllllllIIIIllIIlIlllII).length == lllllllllllllllllIIIIlllIIIlIIII;
    }
    
    private static boolean areAnnotationMembersMatching(final Annotation lllllllllllllllllIIIIllIlIllllII, final Annotation lllllllllllllllllIIIIllIlIlllIIl) {
        if (lllllllllllllllllIIIIllIlIlllIIl != null && lllllllllllllllllIIIIllIlIllllII.annotationType() == lllllllllllllllllIIIIllIlIlllIIl.annotationType()) {
            final byte lllllllllllllllllIIIIllIlIlllIII = (Object)lllllllllllllllllIIIIllIlIllllII.annotationType().getDeclaredMethods();
            final long lllllllllllllllllIIIIllIlIllIlll = lllllllllllllllllIIIIllIlIlllIII.length;
            for (char lllllllllllllllllIIIIllIlIllIllI = '\0'; lllllllllllllllllIIIIllIlIllIllI < lllllllllllllllllIIIIllIlIllIlll; ++lllllllllllllllllIIIIllIlIllIllI) {
                final Method lllllllllllllllllIIIIllIlIllllIl = lllllllllllllllllIIIIllIlIlllIII[lllllllllllllllllIIIIllIlIllIllI];
                try {
                    if (!lllllllllllllllllIIIIllIlIllllIl.invoke(lllllllllllllllllIIIIllIlIllllII, new Object[0]).equals(lllllllllllllllllIIIIllIlIllllIl.invoke(lllllllllllllllllIIIIllIlIlllIIl, new Object[0]))) {
                        return false;
                    }
                }
                catch (Exception lllllllllllllllllIIIIllIlIlllllI) {
                    throw new ReflectionsException(String.format("could not invoke method %s on annotation %s", lllllllllllllllllIIIIllIlIllllIl.getName(), lllllllllllllllllIIIIllIlIllllII.annotationType()), lllllllllllllllllIIIIllIlIlllllI);
                }
            }
            return true;
        }
        return false;
    }
    
    private static void initPrimitives() {
        if (ReflectionUtils.primitiveNames == null) {
            ReflectionUtils.primitiveNames = Arrays.asList("boolean", "char", "byte", "short", "int", "long", "float", "double", "void");
            ReflectionUtils.primitiveTypes = (List<Class>)Arrays.asList(Boolean.TYPE, Character.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE);
            ReflectionUtils.primitiveDescriptors = Arrays.asList("Z", "C", "B", "S", "I", "J", "F", "D", "V");
        }
    }
    
    private static boolean isAssignable(final Class[] lllllllllllllllllIIIIllIlIllIIIl, final Class[] lllllllllllllllllIIIIllIlIllIIII) {
        if (lllllllllllllllllIIIIllIlIllIIIl == null) {
            return lllllllllllllllllIIIIllIlIllIIII == null || lllllllllllllllllIIIIllIlIllIIII.length == 0;
        }
        return lllllllllllllllllIIIIllIlIllIIIl.length == lllllllllllllllllIIIIllIlIllIIII.length && IntStream.range(0, lllllllllllllllllIIIIllIlIllIIIl.length).noneMatch(lllllllllllllllllIIIIllIlIlIIlIl -> !lllllllllllllllllIIIIllIlIllIIII[lllllllllllllllllIIIIllIlIlIIlIl].isAssignableFrom(lllllllllllllllllIIIIllIlIllIIIl[lllllllllllllllllIIIIllIlIlIIlIl]) || (lllllllllllllllllIIIIllIlIllIIII[lllllllllllllllllIIIIllIlIlIIlIl] == Object.class && lllllllllllllllllIIIIllIlIllIIIl[lllllllllllllllllIIIIllIlIlIIlIl] != Object.class));
    }
    
    public static <T extends AnnotatedElement> Set<Annotation> getAllAnnotations(final T lllllllllllllllllIIIIlllIlIIIIIl, final Predicate<Annotation>... lllllllllllllllllIIIIlllIlIIIIll) {
        final Set<Annotation> lllllllllllllllllIIIIlllIlIIIIlI = new HashSet<Annotation>();
        if (lllllllllllllllllIIIIlllIlIIIIIl instanceof Class) {
            for (final Class<?> lllllllllllllllllIIIIlllIlIIIlIl : getAllSuperTypes((Class<?>)lllllllllllllllllIIIIlllIlIIIIIl, (Predicate<? super Class<?>>[])new Predicate[0])) {
                lllllllllllllllllIIIIlllIlIIIIlI.addAll(getAnnotations(lllllllllllllllllIIIIlllIlIIIlIl, lllllllllllllllllIIIIlllIlIIIIll));
            }
        }
        else {
            lllllllllllllllllIIIIlllIlIIIIlI.addAll(getAnnotations((AnnotatedElement)lllllllllllllllllIIIIlllIlIIIIIl, lllllllllllllllllIIIIlllIlIIIIll));
        }
        return lllllllllllllllllIIIIlllIlIIIIlI;
    }
    
    public static <T> Predicate<Method> withReturnTypeAssignableTo(final Class<T> lllllllllllllllllIIIIllIlllllllI) {
        return lllllllllllllllllIIIIllIlIIIlIlI -> lllllllllllllllllIIIIllIlIIIlIlI != null && lllllllllllllllllIIIIllIlllllllI.isAssignableFrom(lllllllllllllllllIIIIllIlIIIlIlI.getReturnType());
    }
    
    private static List<Class> getPrimitiveTypes() {
        initPrimitives();
        return ReflectionUtils.primitiveTypes;
    }
    
    public static Predicate<Member> withAnyParameterAnnotation(final Annotation lllllllllllllllllIIIIlllIIIIlIll) {
        return lllllllllllllllllIIIIllIIlllIIlI -> lllllllllllllllllIIIIllIIlllIIlI != null && parameterAnnotations(lllllllllllllllllIIIIllIIlllIIlI).stream().anyMatch(lllllllllllllllllIIIIllIIllIllII -> areAnnotationMembersMatching(lllllllllllllllllIIIIlllIIIIlIll, lllllllllllllllllIIIIllIIllIllII));
    }
    
    private static List<String> getPrimitiveNames() {
        initPrimitives();
        return ReflectionUtils.primitiveNames;
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withPattern(final String lllllllllllllllllIIIIlllIIlIlIIl) {
        return lllllllllllllllllIIIIllIIIIllllI -> Pattern.matches(lllllllllllllllllIIIIlllIIlIlIIl, lllllllllllllllllIIIIllIIIIllllI.toString());
    }
    
    public static Class<?> forName(final String lllllllllllllllllIIIIllIlllIIlIl, final ClassLoader... lllllllllllllllllIIIIllIlllIIIlI) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //     4: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //     9: ifeq            33
        //    12: invokestatic    org/reflections/ReflectionUtils.getPrimitiveTypes:()Ljava/util/List;
        //    15: invokestatic    org/reflections/ReflectionUtils.getPrimitiveNames:()Ljava/util/List;
        //    18: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //    19: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //    24: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    29: checkcast       Ljava/lang/Class;
        //    32: areturn        
        //    33: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //    34: ldc_w           "["
        //    37: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    40: ifeq            160
        //    43: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //    44: ldc_w           "["
        //    47: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    50: istore_3        /* lllllllllllllllllIIIIllIlllIlllI */
        //    51: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //    52: iconst_0       
        //    53: iload_3         /* lllllllllllllllllIIIIllIlllIlllI */
        //    54: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    57: astore_2        /* lllllllllllllllllIIIIllIlllIIIIl */
        //    58: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //    59: iload_3         /* lllllllllllllllllIIIIllIlllIlllI */
        //    60: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    63: ldc_w           "]"
        //    66: ldc_w           ""
        //    69: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //    72: astore          lllllllllllllllllIIIIllIlllIllIl
        //    74: invokestatic    org/reflections/ReflectionUtils.getPrimitiveNames:()Ljava/util/List;
        //    77: aload_2         /* lllllllllllllllllIIIIllIlllIllII */
        //    78: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //    83: ifeq            110
        //    86: invokestatic    org/reflections/ReflectionUtils.getPrimitiveDescriptors:()Ljava/util/List;
        //    89: invokestatic    org/reflections/ReflectionUtils.getPrimitiveNames:()Ljava/util/List;
        //    92: aload_2         /* lllllllllllllllllIIIIllIlllIllII */
        //    93: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //    98: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   103: checkcast       Ljava/lang/String;
        //   106: astore_2        /* lllllllllllllllllIIIIllIlllIllII */
        //   107: goto            137
        //   110: new             Ljava/lang/StringBuilder;
        //   113: dup            
        //   114: invokespecial   java/lang/StringBuilder.<init>:()V
        //   117: ldc_w           "L"
        //   120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: aload_2         /* lllllllllllllllllIIIIllIlllIllII */
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: ldc_w           ";"
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   136: astore_2        /* lllllllllllllllllIIIIllIlllIllII */
        //   137: new             Ljava/lang/StringBuilder;
        //   140: dup            
        //   141: invokespecial   java/lang/StringBuilder.<init>:()V
        //   144: aload           lllllllllllllllllIIIIllIlllIllIl
        //   146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   149: aload_2         /* lllllllllllllllllIIIIllIlllIllII */
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   156: astore_2        /* lllllllllllllllllIIIIllIlllIllII */
        //   157: goto            162
        //   160: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //   161: astore_2        /* lllllllllllllllllIIIIllIlllIIIIl */
        //   162: new             Ljava/util/ArrayList;
        //   165: dup            
        //   166: invokespecial   java/util/ArrayList.<init>:()V
        //   169: astore_3        /* lllllllllllllllllIIIIllIlllIIllI */
        //   170: aload_1         /* lllllllllllllllllIIIIllIlllIIlII */
        //   171: invokestatic    org/reflections/util/ClasspathHelper.classLoaders:([Ljava/lang/ClassLoader;)[Ljava/lang/ClassLoader;
        //   174: astore          lllllllllllllllllIIIIllIllIlllll
        //   176: aload           lllllllllllllllllIIIIllIllIlllll
        //   178: arraylength    
        //   179: istore          5
        //   181: iconst_0       
        //   182: istore          lllllllllllllllllIIIIllIllIlllIl
        //   184: iload           lllllllllllllllllIIIIllIllIlllIl
        //   186: iload           5
        //   188: if_icmpge       305
        //   191: aload           lllllllllllllllllIIIIllIllIlllll
        //   193: iload           lllllllllllllllllIIIIllIllIlllIl
        //   195: aaload         
        //   196: astore          lllllllllllllllllIIIIllIlllIlIIl
        //   198: aload_2         /* lllllllllllllllllIIIIllIlllIIlll */
        //   199: ldc_w           "["
        //   202: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   205: ifeq            254
        //   208: aload_2         /* lllllllllllllllllIIIIllIlllIIlll */
        //   209: iconst_0       
        //   210: aload           lllllllllllllllllIIIIllIlllIlIIl
        //   212: invokestatic    java/lang/Class.forName:(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
        //   215: areturn        
        //   216: astore          lllllllllllllllllIIIIllIlllIlIll
        //   218: aload_3         /* lllllllllllllllllIIIIllIlllIIllI */
        //   219: new             Lorg/reflections/ReflectionsException;
        //   222: dup            
        //   223: new             Ljava/lang/StringBuilder;
        //   226: dup            
        //   227: invokespecial   java/lang/StringBuilder.<init>:()V
        //   230: ldc_w           "could not get type for name "
        //   233: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   236: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //   237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   243: aload           lllllllllllllllllIIIIllIlllIlIll
        //   245: invokespecial   org/reflections/ReflectionsException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   248: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   253: pop            
        //   254: aload           lllllllllllllllllIIIIllIlllIlIIl
        //   256: aload_2         /* lllllllllllllllllIIIIllIlllIIlll */
        //   257: invokevirtual   java/lang/ClassLoader.loadClass:(Ljava/lang/String;)Ljava/lang/Class;
        //   260: areturn        
        //   261: astore          lllllllllllllllllIIIIllIlllIlIlI
        //   263: aload_3         /* lllllllllllllllllIIIIllIlllIIllI */
        //   264: new             Lorg/reflections/ReflectionsException;
        //   267: dup            
        //   268: new             Ljava/lang/StringBuilder;
        //   271: dup            
        //   272: invokespecial   java/lang/StringBuilder.<init>:()V
        //   275: ldc_w           "could not get type for name "
        //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   288: aload           lllllllllllllllllIIIIllIlllIlIlI
        //   290: invokespecial   org/reflections/ReflectionsException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   293: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   298: pop            
        //   299: iinc            lllllllllllllllllIIIIllIllIlllIl, 1
        //   302: goto            184
        //   305: getstatic       org/reflections/Reflections.log:Lorg/slf4j/Logger;
        //   308: ifnull          380
        //   311: aload_3         /* lllllllllllllllllIIIIllIlllIIllI */
        //   312: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   317: astore          lllllllllllllllllIIIIllIllIlllll
        //   319: aload           lllllllllllllllllIIIIllIllIlllll
        //   321: invokeinterface java/util/Iterator.hasNext:()Z
        //   326: ifeq            380
        //   329: aload           lllllllllllllllllIIIIllIllIlllll
        //   331: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   336: checkcast       Lorg/reflections/ReflectionsException;
        //   339: astore          lllllllllllllllllIIIIllIlllIlIII
        //   341: getstatic       org/reflections/Reflections.log:Lorg/slf4j/Logger;
        //   344: new             Ljava/lang/StringBuilder;
        //   347: dup            
        //   348: invokespecial   java/lang/StringBuilder.<init>:()V
        //   351: ldc_w           "could not get type for name "
        //   354: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   357: aload_0         /* lllllllllllllllllIIIIllIlllIIIll */
        //   358: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: ldc_w           " from any class loader"
        //   364: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   367: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   370: aload           lllllllllllllllllIIIIllIlllIlIII
        //   372: invokeinterface org/slf4j/Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   377: goto            319
        //   380: aconst_null    
        //   381: areturn        
        //    Signature:
        //  (Ljava/lang/String;[Ljava/lang/ClassLoader;)Ljava/lang/Class<*>;
        //    StackMapTable: 00 0C 21 FE 00 4C 07 02 87 01 07 02 87 1A F8 00 16 FC 00 01 07 02 87 FF 00 15 00 07 07 02 87 07 03 53 07 02 87 07 03 4B 07 03 53 01 01 00 00 FF 00 1F 00 08 07 02 87 07 03 53 07 02 87 07 03 4B 07 03 53 01 01 07 03 58 00 01 07 03 12 25 46 07 03 12 FA 00 2B FF 00 0D 00 07 07 02 87 07 03 53 07 02 87 07 03 4B 07 00 90 00 01 00 00 FF 00 3C 00 07 07 02 87 07 03 53 07 02 87 07 03 4B 07 00 04 00 01 00 00
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  208    215    216    254    Ljava/lang/Throwable;
        //  254    260    261    305    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Thread.java:748)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static <T extends AnnotatedElement> Set<T> getAll(final Set<T> lllllllllllllllllIIIIlllIIllIIlI, final Predicate<? super T>... lllllllllllllllllIIIIlllIIllIIIl) {
        return Utils.filter(lllllllllllllllllIIIIlllIIllIIlI, lllllllllllllllllIIIIlllIIllIIIl);
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withAnnotations(final Class<? extends Annotation>... lllllllllllllllllIIIIlllIIlIIIll) {
        return lllllllllllllllllIIIIllIIIlIllII -> lllllllllllllllllIIIIllIIIlIllII != null && Arrays.equals(lllllllllllllllllIIIIlllIIlIIIll, annotationTypes(lllllllllllllllllIIIIllIIIlIllII.getAnnotations()));
    }
    
    public static Set<Constructor> getAllConstructors(final Class<?> lllllllllllllllllIIIIlllIllIlIIl, final Predicate<? super Constructor>... lllllllllllllllllIIIIlllIllIlIll) {
        final Set<Constructor> lllllllllllllllllIIIIlllIllIlIlI = new HashSet<Constructor>();
        for (final Class<?> lllllllllllllllllIIIIlllIllIllIl : getAllSuperTypes(lllllllllllllllllIIIIlllIllIlIIl, (Predicate<? super Class<?>>[])new Predicate[0])) {
            lllllllllllllllllIIIIlllIllIlIlI.addAll(getConstructors(lllllllllllllllllIIIIlllIllIllIl, lllllllllllllllllIIIIlllIllIlIll));
        }
        return lllllllllllllllllIIIIlllIllIlIlI;
    }
    
    public static <T> Predicate<Field> withType(final Class<T> lllllllllllllllllIIIIlllIIIIIlll) {
        return lllllllllllllllllIIIIllIIllllIlI -> lllllllllllllllllIIIIllIIllllIlI != null && lllllllllllllllllIIIIllIIllllIlI.getType().equals(lllllllllllllllllIIIIlllIIIIIlll);
    }
    
    private static Set<Class<? extends Annotation>> annotationTypes(final Collection<Annotation> lllllllllllllllllIIIIllIllIIlIlI) {
        return lllllllllllllllllIIIIllIllIIlIlI.stream().map((Function<? super Annotation, ?>)Annotation::annotationType).collect((Collector<? super Object, ?, Set<Class<? extends Annotation>>>)Collectors.toSet());
    }
    
    public static <T extends Member> Predicate<T> withModifier(final int lllllllllllllllllIIIIllIlllllIll) {
        return lllllllllllllllllIIIIllIlIIlIIlI -> lllllllllllllllllIIIIllIlIIlIIlI != null && (lllllllllllllllllIIIIllIlIIlIIlI.getModifiers() & lllllllllllllllllIIIIllIlllllIll) != 0x0;
    }
    
    public static Set<Field> getFields(final Class<?> lllllllllllllllllIIIIlllIlIIllII, final Predicate<? super Field>... lllllllllllllllllIIIIlllIlIIllIl) {
        return Utils.filter(lllllllllllllllllIIIIlllIlIIllII.getDeclaredFields(), lllllllllllllllllIIIIlllIlIIllIl);
    }
    
    public static Predicate<Member> withParametersAssignableTo(final Class... lllllllllllllllllIIIIlllIIIlIlll) {
        return lllllllllllllllllIIIIllIIlIlIIII -> isAssignable(lllllllllllllllllIIIIlllIIIlIlll, parameterTypes(lllllllllllllllllIIIIllIIlIlIIII));
    }
    
    public static Predicate<Member> withParameters(final Class<?>... lllllllllllllllllIIIIlllIIIllIlI) {
        return lllllllllllllllllIIIIllIIlIIlIlI -> Arrays.equals(parameterTypes(lllllllllllllllllIIIIllIIlIIlIlI), lllllllllllllllllIIIIlllIIIllIlI);
    }
    
    private static Class[] parameterTypes(final Member lllllllllllllllllIIIIllIllIlIIll) {
        return (Class[])((lllllllllllllllllIIIIllIllIlIIll != null) ? ((lllllllllllllllllIIIIllIllIlIIll.getClass() == Method.class) ? ((Method)lllllllllllllllllIIIIllIllIlIIll).getParameterTypes() : ((lllllllllllllllllIIIIllIllIlIIll.getClass() == Constructor.class) ? ((Constructor)lllllllllllllllllIIIIllIllIlIIll).getParameterTypes() : null)) : null);
    }
}
