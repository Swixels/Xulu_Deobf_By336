package org.reflections.util;

import java.util.function.*;
import org.slf4j.*;
import java.util.stream.*;
import org.reflections.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public abstract class Utils
{
    public static List<String> names(final Collection<Class<?>> llllllllllllllllIlllIlllIIIllIll) {
        return llllllllllllllllIlllIlllIIIllIll.stream().map((Function<? super Class<?>, ?>)Utils::name).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
    }
    
    public static String join(final Collection<?> llllllllllllllllIlllIllIllllllll, final String llllllllllllllllIlllIlllIIIIIIIl) {
        return llllllllllllllllIlllIllIllllllll.stream().map((Function<?, ?>)Object::toString).collect((Collector<? super Object, ?, String>)Collectors.joining(llllllllllllllllIlllIlllIIIIIIIl));
    }
    
    public static <T> Set<T> filter(final Collection<T> llllllllllllllllIlllIllIlllllIII, final Predicate<? super T>... llllllllllllllllIlllIllIllllIlIl) {
        return llllllllllllllllIlllIllIlllllIII.stream().filter(and((Predicate[])llllllllllllllllIlllIllIllllIlIl)).collect((Collector<? super T, ?, Set<T>>)Collectors.toSet());
    }
    
    public static void close(final InputStream llllllllllllllllIlllIlllIIlIllIl) {
        try {
            if (llllllllllllllllIlllIlllIIlIllIl != null) {
                llllllllllllllllIlllIlllIIlIllIl.close();
            }
        }
        catch (IOException llllllllllllllllIlllIlllIIlIllll) {
            if (Reflections.log != null) {
                Reflections.log.warn("Could not close InputStream", (Throwable)llllllllllllllllIlllIlllIIlIllll);
            }
        }
    }
    
    public static Logger findLogger(final Class<?> llllllllllllllllIlllIlllIIlIlIII) {
        try {
            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            return LoggerFactory.getLogger((Class)llllllllllllllllIlllIlllIIlIlIII);
        }
        catch (Throwable llllllllllllllllIlllIlllIIlIlIIl) {
            return null;
        }
    }
    
    public static String repeat(final String llllllllllllllllIlllIllllIlIIIll, final int llllllllllllllllIlllIllllIlIIlII) {
        return IntStream.range(0, llllllllllllllllIlllIllllIlIIlII).mapToObj(llllllllllllllllIlllIllIllIlIIII -> llllllllllllllllIlllIllllIlIIIll).collect((Collector<? super Object, ?, String>)Collectors.joining());
    }
    
    public static Field getFieldFromString(final String llllllllllllllllIlllIlllIIllIllI, final ClassLoader... llllllllllllllllIlllIlllIIllIlIl) {
        final String llllllllllllllllIlllIlllIIlllIII = llllllllllllllllIlllIlllIIllIllI.substring(0, llllllllllllllllIlllIlllIIllIllI.lastIndexOf(46));
        final String llllllllllllllllIlllIlllIIllIlll = llllllllllllllllIlllIlllIIllIllI.substring(llllllllllllllllIlllIlllIIllIllI.lastIndexOf(46) + 1);
        try {
            return ReflectionUtils.forName(llllllllllllllllIlllIlllIIlllIII, llllllllllllllllIlllIlllIIllIlIl).getDeclaredField(llllllllllllllllIlllIlllIIllIlll);
        }
        catch (NoSuchFieldException llllllllllllllllIlllIlllIIlllIll) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("Can't resolve field named ").append(llllllllllllllllIlllIlllIIllIlll)), llllllllllllllllIlllIlllIIlllIll);
        }
    }
    
    public static Member getMemberFromDescriptor(final String llllllllllllllllIlllIllllIIIlIII, final ClassLoader... llllllllllllllllIlllIlllIlllllIl) throws ReflectionsException {
        final int llllllllllllllllIlllIllllIIIIllI = llllllllllllllllIlllIllllIIIlIII.lastIndexOf(40);
        final String llllllllllllllllIlllIllllIIIIlIl = (llllllllllllllllIlllIllllIIIIllI != -1) ? llllllllllllllllIlllIllllIIIlIII.substring(0, llllllllllllllllIlllIllllIIIIllI) : llllllllllllllllIlllIllllIIIlIII;
        final String llllllllllllllllIlllIllllIIIIlII = (llllllllllllllllIlllIllllIIIIllI != -1) ? llllllllllllllllIlllIllllIIIlIII.substring(llllllllllllllllIlllIllllIIIIllI + 1, llllllllllllllllIlllIllllIIIlIII.lastIndexOf(41)) : "";
        final int llllllllllllllllIlllIllllIIIIIll = Math.max(llllllllllllllllIlllIllllIIIIlIl.lastIndexOf(46), llllllllllllllllIlllIllllIIIIlIl.lastIndexOf("$"));
        final String llllllllllllllllIlllIllllIIIIIlI = llllllllllllllllIlllIllllIIIIlIl.substring(llllllllllllllllIlllIllllIIIIlIl.lastIndexOf(32) + 1, llllllllllllllllIlllIllllIIIIIll);
        final String llllllllllllllllIlllIllllIIIIIIl = llllllllllllllllIlllIllllIIIIlIl.substring(llllllllllllllllIlllIllllIIIIIll + 1);
        Class<?>[] llllllllllllllllIlllIllllIIIIIII = null;
        if (!isEmpty(llllllllllllllllIlllIllllIIIIlII)) {
            final String[] llllllllllllllllIlllIllllIIIlIlI = llllllllllllllllIlllIllllIIIIlII.split(",");
            llllllllllllllllIlllIllllIIIIIII = Arrays.stream(llllllllllllllllIlllIllllIIIlIlI).map(llllllllllllllllIlllIllIllIlIlIl -> ReflectionUtils.forName(llllllllllllllllIlllIllIllIlIlIl.trim(), llllllllllllllllIlllIlllIlllllIl)).toArray(Class[]::new);
        }
        Class<?> llllllllllllllllIlllIlllIlllllll = ReflectionUtils.forName(llllllllllllllllIlllIllllIIIIIlI, llllllllllllllllIlllIlllIlllllIl);
        while (llllllllllllllllIlllIlllIlllllll != null) {
            try {
                if (!llllllllllllllllIlllIllllIIIlIII.contains("(")) {
                    return llllllllllllllllIlllIlllIlllllll.isInterface() ? llllllllllllllllIlllIlllIlllllll.getField(llllllllllllllllIlllIllllIIIIIIl) : llllllllllllllllIlllIlllIlllllll.getDeclaredField(llllllllllllllllIlllIllllIIIIIIl);
                }
                if (isConstructor(llllllllllllllllIlllIllllIIIlIII)) {
                    return llllllllllllllllIlllIlllIlllllll.isInterface() ? llllllllllllllllIlllIlllIlllllll.getConstructor(llllllllllllllllIlllIllllIIIIIII) : llllllllllllllllIlllIlllIlllllll.getDeclaredConstructor(llllllllllllllllIlllIllllIIIIIII);
                }
                return llllllllllllllllIlllIlllIlllllll.isInterface() ? llllllllllllllllIlllIlllIlllllll.getMethod(llllllllllllllllIlllIllllIIIIIIl, llllllllllllllllIlllIllllIIIIIII) : llllllllllllllllIlllIlllIlllllll.getDeclaredMethod(llllllllllllllllIlllIllllIIIIIIl, llllllllllllllllIlllIllllIIIIIII);
            }
            catch (Exception llllllllllllllllIlllIllllIIIlIIl) {
                llllllllllllllllIlllIlllIlllllll = llllllllllllllllIlllIlllIlllllll.getSuperclass();
                continue;
            }
            break;
        }
        throw new ReflectionsException(String.valueOf(new StringBuilder().append("Can't resolve member named ").append(llllllllllllllllIlllIllllIIIIIIl).append(" for class ").append(llllllllllllllllIlllIllllIIIIIlI)));
    }
    
    public static String name(Class llllllllllllllllIlllIlllIIIllllI) {
        if (!llllllllllllllllIlllIlllIIIllllI.isArray()) {
            return llllllllllllllllIlllIlllIIIllllI.getName();
        }
        int llllllllllllllllIlllIlllIIlIIIII = 0;
        while (llllllllllllllllIlllIlllIIIllllI.isArray()) {
            ++llllllllllllllllIlllIlllIIlIIIII;
            llllllllllllllllIlllIlllIIIllllI = llllllllllllllllIlllIlllIIIllllI.getComponentType();
        }
        return String.valueOf(new StringBuilder().append(llllllllllllllllIlllIlllIIIllllI.getName()).append(repeat("[]", llllllllllllllllIlllIlllIIlIIIII)));
    }
    
    public static boolean isConstructor(final String llllllllllllllllIlllIlllIIlIIlII) {
        return llllllllllllllllIlllIlllIIlIIlII.contains("init>");
    }
    
    public static String name(final Method llllllllllllllllIlllIlllIIIlIIIl) {
        return String.valueOf(new StringBuilder().append(llllllllllllllllIlllIlllIIIlIIIl.getDeclaringClass().getName()).append(".").append(llllllllllllllllIlllIlllIIIlIIIl.getName()).append("(").append(join(names(llllllllllllllllIlllIlllIIIlIIIl.getParameterTypes()), ", ")).append(")"));
    }
    
    public static String name(final Field llllllllllllllllIlllIlllIIIIllll) {
        return String.valueOf(new StringBuilder().append(llllllllllllllllIlllIlllIIIIllll.getDeclaringClass().getName()).append(".").append(llllllllllllllllIlllIlllIIIIllll.getName()));
    }
    
    public static String name(final Constructor llllllllllllllllIlllIlllIIIlIlIl) {
        return String.valueOf(new StringBuilder().append(llllllllllllllllIlllIlllIIIlIlIl.getName()).append(".<init>(").append(join(names((Class<?>[])llllllllllllllllIlllIlllIIIlIlIl.getParameterTypes()), ", ")).append(")"));
    }
    
    public static <T> Set<T> filter(final Collection<T> llllllllllllllllIlllIllIllllIIlI, final Predicate<? super T> llllllllllllllllIlllIllIllllIIIl) {
        return llllllllllllllllIlllIllIllllIIlI.stream().filter(llllllllllllllllIlllIllIllllIIIl).collect((Collector<? super T, ?, Set<T>>)Collectors.toSet());
    }
    
    public static Set<Constructor> getConstructorsFromDescriptors(final Iterable<String> llllllllllllllllIlllIlllIlIllIlI, final ClassLoader... llllllllllllllllIlllIlllIlIllIIl) {
        final Set<Constructor> llllllllllllllllIlllIlllIlIllIII = new HashSet<Constructor>();
        for (final String llllllllllllllllIlllIlllIlIllIll : llllllllllllllllIlllIlllIlIllIlI) {
            if (isConstructor(llllllllllllllllIlllIlllIlIllIll)) {
                final Constructor llllllllllllllllIlllIlllIlIlllII = (Constructor)getMemberFromDescriptor(llllllllllllllllIlllIlllIlIllIll, llllllllllllllllIlllIlllIlIllIIl);
                if (llllllllllllllllIlllIlllIlIlllII == null) {
                    continue;
                }
                llllllllllllllllIlllIlllIlIllIII.add(llllllllllllllllIlllIlllIlIlllII);
            }
        }
        return llllllllllllllllIlllIlllIlIllIII;
    }
    
    public static String index(final Class<?> llllllllllllllllIlllIlllIIIIllII) {
        return llllllllllllllllIlllIlllIIIIllII.getSimpleName();
    }
    
    public static boolean isEmpty(final String llllllllllllllllIlllIllllIIlllll) {
        return llllllllllllllllIlllIllllIIlllll == null || llllllllllllllllIlllIllllIIlllll.length() == 0;
    }
    
    public static Set<Member> getMembersFromDescriptors(final Iterable<String> llllllllllllllllIlllIlllIlIIIllI, final ClassLoader... llllllllllllllllIlllIlllIlIIlIII) {
        final Set<Member> llllllllllllllllIlllIlllIlIIIlll = new HashSet<Member>();
        for (final String llllllllllllllllIlllIlllIlIIlIlI : llllllllllllllllIlllIlllIlIIIllI) {
            try {
                llllllllllllllllIlllIlllIlIIIlll.add(getMemberFromDescriptor(llllllllllllllllIlllIlllIlIIlIlI, llllllllllllllllIlllIlllIlIIlIII));
            }
            catch (ReflectionsException llllllllllllllllIlllIlllIlIIlIll) {
                throw new ReflectionsException(String.valueOf(new StringBuilder().append("Can't resolve member named ").append(llllllllllllllllIlllIlllIlIIlIlI)), llllllllllllllllIlllIlllIlIIlIll);
            }
        }
        return llllllllllllllllIlllIlllIlIIIlll;
    }
    
    public static File prepareFile(final String llllllllllllllllIlllIllllIIllIII) {
        final File llllllllllllllllIlllIllllIIllIlI = new File(llllllllllllllllIlllIllllIIllIII);
        final File llllllllllllllllIlllIllllIIllIIl = llllllllllllllllIlllIllllIIllIlI.getAbsoluteFile().getParentFile();
        if (!llllllllllllllllIlllIllllIIllIIl.exists()) {
            llllllllllllllllIlllIllllIIllIIl.mkdirs();
        }
        return llllllllllllllllIlllIllllIIllIlI;
    }
    
    public static <T> Predicate<T> and(final Predicate... llllllllllllllllIlllIlllIIIIIlll) {
        return Arrays.stream(llllllllllllllllIlllIlllIIIIIlll).reduce(llllllllllllllllIlllIllIlllIIIlI -> true, Predicate::and);
    }
    
    public static List<String> names(final Class<?>... llllllllllllllllIlllIlllIIIlIlll) {
        return names(Arrays.asList(llllllllllllllllIlllIlllIIIlIlll));
    }
    
    public static Set<Method> getMethodsFromDescriptors(final Iterable<String> llllllllllllllllIlllIlllIllIlIII, final ClassLoader... llllllllllllllllIlllIlllIllIIlll) {
        final Set<Method> llllllllllllllllIlllIlllIllIlIIl = new HashSet<Method>();
        for (final String llllllllllllllllIlllIlllIllIllII : llllllllllllllllIlllIlllIllIlIII) {
            if (!isConstructor(llllllllllllllllIlllIlllIllIllII)) {
                final Method llllllllllllllllIlllIlllIllIllIl = (Method)getMemberFromDescriptor(llllllllllllllllIlllIlllIllIllII, llllllllllllllllIlllIlllIllIIlll);
                if (llllllllllllllllIlllIlllIllIllIl == null) {
                    continue;
                }
                llllllllllllllllIlllIlllIllIlIIl.add(llllllllllllllllIlllIlllIllIllIl);
            }
        }
        return llllllllllllllllIlllIlllIllIlIIl;
    }
    
    public static <T> Set<T> filter(final T[] llllllllllllllllIlllIllIlllIIllI, final Predicate<? super T>... llllllllllllllllIlllIllIlllIIIll) {
        return Arrays.stream(llllllllllllllllIlllIllIlllIIllI).filter(and((Predicate[])llllllllllllllllIlllIllIlllIIIll)).collect((Collector<? super T, ?, Set<T>>)Collectors.toSet());
    }
}
