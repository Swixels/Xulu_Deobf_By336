package net.jodah.typetools;

import sun.misc.*;
import java.security.*;
import java.lang.ref.*;
import java.util.*;
import java.lang.reflect.*;

public final class TypeResolver
{
    private static /* synthetic */ Method GET_CONSTANT_POOL;
    private static final /* synthetic */ Map<Class<?>, Class<?>> PRIMITIVE_WRAPPERS;
    private static final /* synthetic */ Map<Class<?>, Reference<Map<TypeVariable<?>, Type>>> TYPE_VARIABLE_CACHE;
    private static final /* synthetic */ Double JAVA_VERSION;
    private static /* synthetic */ Method GET_CONSTANT_POOL_SIZE;
    private static /* synthetic */ boolean RESOLVES_LAMBDAS;
    private static volatile /* synthetic */ boolean CACHE_ENABLED;
    private static /* synthetic */ Method GET_CONSTANT_POOL_METHOD_AT;
    private static final /* synthetic */ Map<String, Method> OBJECT_METHODS;
    
    private static Member getConstantPoolMethodAt(final Object lllllllllllllllllllIllllllIlIIlI, final int lllllllllllllllllllIllllllIlIIIl) {
        try {
            return (Member)TypeResolver.GET_CONSTANT_POOL_METHOD_AT.invoke(lllllllllllllllllllIllllllIlIIlI, lllllllllllllllllllIllllllIlIIIl);
        }
        catch (Exception lllllllllllllllllllIllllllIlIIll) {
            return null;
        }
    }
    
    private static Class<?> resolveRawClass(Type llllllllllllllllllllIIIIIlllllIl, final Class<?> llllllllllllllllllllIIIIIlllllll, final Class<?> llllllllllllllllllllIIIIIllllIll) {
        if (llllllllllllllllllllIIIIIlllllIl instanceof Class) {
            return (Class<?>)llllllllllllllllllllIIIIIlllllIl;
        }
        if (llllllllllllllllllllIIIIIlllllIl instanceof ParameterizedType) {
            return resolveRawClass(((ParameterizedType)llllllllllllllllllllIIIIIlllllIl).getRawType(), llllllllllllllllllllIIIIIlllllll, llllllllllllllllllllIIIIIllllIll);
        }
        if (llllllllllllllllllllIIIIIlllllIl instanceof GenericArrayType) {
            final GenericArrayType llllllllllllllllllllIIIIlIIIIIll = (GenericArrayType)llllllllllllllllllllIIIIIlllllIl;
            final Class<?> llllllllllllllllllllIIIIlIIIIIlI = resolveRawClass(llllllllllllllllllllIIIIlIIIIIll.getGenericComponentType(), llllllllllllllllllllIIIIIlllllll, llllllllllllllllllllIIIIIllllIll);
            return Array.newInstance(llllllllllllllllllllIIIIlIIIIIlI, 0).getClass();
        }
        if (llllllllllllllllllllIIIIIlllllIl instanceof TypeVariable) {
            final TypeVariable<?> llllllllllllllllllllIIIIlIIIIIIl = (TypeVariable<?>)llllllllllllllllllllIIIIIlllllIl;
            llllllllllllllllllllIIIIIlllllIl = getTypeVariableMap(llllllllllllllllllllIIIIIlllllll, llllllllllllllllllllIIIIIllllIll).get(llllllllllllllllllllIIIIlIIIIIIl);
            llllllllllllllllllllIIIIIlllllIl = ((llllllllllllllllllllIIIIIlllllIl == null) ? resolveBound(llllllllllllllllllllIIIIlIIIIIIl) : resolveRawClass((Type)llllllllllllllllllllIIIIIlllllIl, llllllllllllllllllllIIIIIlllllll, llllllllllllllllllllIIIIIllllIll));
        }
        return (Class<?>)((llllllllllllllllllllIIIIIlllllIl instanceof Class) ? ((Class)llllllllllllllllllllIIIIIlllllIl) : Unknown.class);
    }
    
    private static void populateSuperTypeArgs(final Type[] llllllllllllllllllllIIIIIlIlIlll, final Map<TypeVariable<?>, Type> llllllllllllllllllllIIIIIlIlIllI, final boolean llllllllllllllllllllIIIIIlIllIII) {
        final boolean llllllllllllllllllllIIIIIlIlIlII = (Object)llllllllllllllllllllIIIIIlIlIlll;
        final char llllllllllllllllllllIIIIIlIlIIll = (char)llllllllllllllllllllIIIIIlIlIlII.length;
        for (short llllllllllllllllllllIIIIIlIlIIlI = 0; llllllllllllllllllllIIIIIlIlIIlI < llllllllllllllllllllIIIIIlIlIIll; ++llllllllllllllllllllIIIIIlIlIIlI) {
            final Type llllllllllllllllllllIIIIIlIllIll = llllllllllllllllllllIIIIIlIlIlII[llllllllllllllllllllIIIIIlIlIIlI];
            if (llllllllllllllllllllIIIIIlIllIll instanceof ParameterizedType) {
                final ParameterizedType llllllllllllllllllllIIIIIlIlllIl = (ParameterizedType)llllllllllllllllllllIIIIIlIllIll;
                if (!llllllllllllllllllllIIIIIlIllIII) {
                    populateTypeArgs(llllllllllllllllllllIIIIIlIlllIl, llllllllllllllllllllIIIIIlIlIllI, llllllllllllllllllllIIIIIlIllIII);
                }
                final Type llllllllllllllllllllIIIIIlIlllII = llllllllllllllllllllIIIIIlIlllIl.getRawType();
                if (llllllllllllllllllllIIIIIlIlllII instanceof Class) {
                    populateSuperTypeArgs(((Class)llllllllllllllllllllIIIIIlIlllII).getGenericInterfaces(), llllllllllllllllllllIIIIIlIlIllI, llllllllllllllllllllIIIIIlIllIII);
                }
                if (llllllllllllllllllllIIIIIlIllIII) {
                    populateTypeArgs(llllllllllllllllllllIIIIIlIlllIl, llllllllllllllllllllIIIIIlIlIllI, llllllllllllllllllllIIIIIlIllIII);
                }
            }
            else if (llllllllllllllllllllIIIIIlIllIll instanceof Class) {
                populateSuperTypeArgs(((Class)llllllllllllllllllllIIIIIlIllIll).getGenericInterfaces(), llllllllllllllllllllIIIIIlIlIllI, llllllllllllllllllllIIIIIlIllIII);
            }
        }
    }
    
    private static int getConstantPoolSize(final Object lllllllllllllllllllIllllllIllIIl) {
        try {
            return (int)TypeResolver.GET_CONSTANT_POOL_SIZE.invoke(lllllllllllllllllllIllllllIllIIl, new Object[0]);
        }
        catch (Exception lllllllllllllllllllIllllllIllIlI) {
            return 0;
        }
    }
    
    public static Class<?> resolveRawArgument(final Type llllllllllllllllllllIIIIllIIlIlI, final Class<?> llllllllllllllllllllIIIIllIIlIIl) {
        final Class<?>[] llllllllllllllllllllIIIIllIIlIII = resolveRawArguments(llllllllllllllllllllIIIIllIIlIlI, llllllllllllllllllllIIIIllIIlIIl);
        if (llllllllllllllllllllIIIIllIIlIII == null) {
            return Unknown.class;
        }
        if (llllllllllllllllllllIIIIllIIlIII.length != 1) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Expected 1 argument for generic type ").append(llllllllllllllllllllIIIIllIIlIlI).append(" but found ").append(llllllllllllllllllllIIIIllIIlIII.length)));
        }
        return llllllllllllllllllllIIIIllIIlIII[0];
    }
    
    public static void enableCache() {
        TypeResolver.CACHE_ENABLED = true;
    }
    
    private static Member getMemberRef(final Class<?> lllllllllllllllllllIlllllllIllIl) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: aload_0         /* lllllllllllllllllllIlllllllIlIlI */
        //     4: iconst_0       
        //     5: anewarray       Ljava/lang/Object;
        //     8: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    11: astore_1        /* lllllllllllllllllllIlllllllIlIIl */
        //    12: goto            18
        //    15: astore_2        /* lllllllllllllllllllIllllllllIIII */
        //    16: aconst_null    
        //    17: areturn        
        //    18: aconst_null    
        //    19: astore_2        /* lllllllllllllllllllIlllllllIlIll */
        //    20: aload_1         /* lllllllllllllllllllIlllllllIllII */
        //    21: invokestatic    net/jodah/typetools/TypeResolver.getConstantPoolSize:(Ljava/lang/Object;)I
        //    24: iconst_1       
        //    25: isub           
        //    26: istore_3        /* lllllllllllllllllllIlllllllIIlll */
        //    27: iload_3         /* lllllllllllllllllllIlllllllIlllI */
        //    28: iflt            117
        //    31: aload_1         /* lllllllllllllllllllIlllllllIllII */
        //    32: iload_3         /* lllllllllllllllllllIlllllllIlllI */
        //    33: invokestatic    net/jodah/typetools/TypeResolver.getConstantPoolMethodAt:(Ljava/lang/Object;I)Ljava/lang/reflect/Member;
        //    36: astore          lllllllllllllllllllIlllllllIllll
        //    38: aload           lllllllllllllllllllIlllllllIllll
        //    40: ifnull          111
        //    43: aload           lllllllllllllllllllIlllllllIllll
        //    45: instanceof      Ljava/lang/reflect/Constructor;
        //    48: ifeq            69
        //    51: aload           lllllllllllllllllllIlllllllIllll
        //    53: invokeinterface java/lang/reflect/Member.getDeclaringClass:()Ljava/lang/Class;
        //    58: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    61: ldc             "java.lang.invoke.SerializedLambda"
        //    63: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    66: ifne            111
        //    69: aload           lllllllllllllllllllIlllllllIllll
        //    71: invokeinterface java/lang/reflect/Member.getDeclaringClass:()Ljava/lang/Class;
        //    76: aload_0         /* lllllllllllllllllllIlllllllIlIlI */
        //    77: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    80: ifeq            86
        //    83: goto            111
        //    86: aload           lllllllllllllllllllIlllllllIllll
        //    88: astore_2        /* lllllllllllllllllllIlllllllIlIll */
        //    89: aload           lllllllllllllllllllIlllllllIllll
        //    91: instanceof      Ljava/lang/reflect/Method;
        //    94: ifeq            117
        //    97: aload           lllllllllllllllllllIlllllllIllll
        //    99: checkcast       Ljava/lang/reflect/Method;
        //   102: invokestatic    net/jodah/typetools/TypeResolver.isAutoBoxingMethod:(Ljava/lang/reflect/Method;)Z
        //   105: ifne            111
        //   108: goto            117
        //   111: iinc            lllllllllllllllllllIlllllllIlllI, -1
        //   114: goto            27
        //   117: aload_2         /* lllllllllllllllllllIlllllllIlIll */
        //   118: areturn        
        //    Signature:
        //  (Ljava/lang/Class<*>;)Ljava/lang/reflect/Member;
        //    StackMapTable: 00 07 4F 07 00 1D FC 00 02 07 00 04 FD 00 08 07 00 2D 01 FC 00 29 07 00 2D 10 18 FA 00 05
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      12     15     18     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2895)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
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
    
    static {
        TYPE_VARIABLE_CACHE = Collections.synchronizedMap(new WeakHashMap<Class<?>, Reference<Map<TypeVariable<?>, Type>>>());
        TypeResolver.CACHE_ENABLED = true;
        OBJECT_METHODS = new HashMap<String, Method>();
        JAVA_VERSION = Double.parseDouble(System.getProperty("java.specification.version", "0"));
        try {
            final Unsafe lllllllllllllllllllIllllllIIIIlI = AccessController.doPrivileged((PrivilegedExceptionAction<Unsafe>)new PrivilegedExceptionAction<Unsafe>() {
                @Override
                public Unsafe run() throws Exception {
                    final Field lllllllllllllllllIlIIIIlIIlIIllI = Unsafe.class.getDeclaredField("theUnsafe");
                    lllllllllllllllllIlIIIIlIIlIIllI.setAccessible(true);
                    return (Unsafe)lllllllllllllllllIlIIIIlIIlIIllI.get(null);
                }
            });
            TypeResolver.GET_CONSTANT_POOL = Class.class.getDeclaredMethod("getConstantPool", (Class<?>[])new Class[0]);
            final String lllllllllllllllllllIllllllIIIIIl = (TypeResolver.JAVA_VERSION < 9.0) ? "sun.reflect.ConstantPool" : "jdk.internal.reflect.ConstantPool";
            final Class<?> lllllllllllllllllllIllllllIIIIII = Class.forName(lllllllllllllllllllIllllllIIIIIl);
            TypeResolver.GET_CONSTANT_POOL_SIZE = lllllllllllllllllllIllllllIIIIII.getDeclaredMethod("getSize", (Class<?>[])new Class[0]);
            TypeResolver.GET_CONSTANT_POOL_METHOD_AT = lllllllllllllllllllIllllllIIIIII.getDeclaredMethod("getMethodAt", Integer.TYPE);
            final Field lllllllllllllllllllIlllllIllllll = AccessibleObject.class.getDeclaredField("override");
            final long lllllllllllllllllllIlllllIlllllI = lllllllllllllllllllIllllllIIIIlI.objectFieldOffset(lllllllllllllllllllIlllllIllllll);
            lllllllllllllllllllIllllllIIIIlI.putBoolean(TypeResolver.GET_CONSTANT_POOL, lllllllllllllllllllIlllllIlllllI, true);
            lllllllllllllllllllIllllllIIIIlI.putBoolean(TypeResolver.GET_CONSTANT_POOL_SIZE, lllllllllllllllllllIlllllIlllllI, true);
            lllllllllllllllllllIllllllIIIIlI.putBoolean(TypeResolver.GET_CONSTANT_POOL_METHOD_AT, lllllllllllllllllllIlllllIlllllI, true);
            final Object lllllllllllllllllllIlllllIllllIl = TypeResolver.GET_CONSTANT_POOL.invoke(Object.class, new Object[0]);
            TypeResolver.GET_CONSTANT_POOL_SIZE.invoke(lllllllllllllllllllIlllllIllllIl, new Object[0]);
            final boolean lllllllllllllllllllIlllllIllIlIl = (Object)Object.class.getDeclaredMethods();
            final char lllllllllllllllllllIlllllIllIlII = (char)lllllllllllllllllllIlllllIllIlIl.length;
            for (short lllllllllllllllllllIlllllIllIIll = 0; lllllllllllllllllllIlllllIllIIll < lllllllllllllllllllIlllllIllIlII; ++lllllllllllllllllllIlllllIllIIll) {
                final Method lllllllllllllllllllIllllllIIIIll = lllllllllllllllllllIlllllIllIlIl[lllllllllllllllllllIlllllIllIIll];
                TypeResolver.OBJECT_METHODS.put(lllllllllllllllllllIllllllIIIIll.getName(), lllllllllllllllllllIllllllIIIIll);
            }
            TypeResolver.RESOLVES_LAMBDAS = true;
        }
        catch (Exception ex) {}
        final Map<Class<?>, Class<?>> lllllllllllllllllllIlllllIllllII = new HashMap<Class<?>, Class<?>>();
        lllllllllllllllllllIlllllIllllII.put(Boolean.TYPE, Boolean.class);
        lllllllllllllllllllIlllllIllllII.put(Byte.TYPE, Byte.class);
        lllllllllllllllllllIlllllIllllII.put(Character.TYPE, Character.class);
        lllllllllllllllllllIlllllIllllII.put(Double.TYPE, Double.class);
        lllllllllllllllllllIlllllIllllII.put(Float.TYPE, Float.class);
        lllllllllllllllllllIlllllIllllII.put(Integer.TYPE, Integer.class);
        lllllllllllllllllllIlllllIllllII.put(Long.TYPE, Long.class);
        lllllllllllllllllllIlllllIllllII.put(Short.TYPE, Short.class);
        lllllllllllllllllllIlllllIllllII.put(Void.TYPE, Void.class);
        PRIMITIVE_WRAPPERS = Collections.unmodifiableMap((Map<? extends Class<?>, ? extends Class<?>>)lllllllllllllllllllIlllllIllllII);
    }
    
    public static Type resolveGenericType(final Class<?> llllllllllllllllllllIIIIlIIllIlI, final Type llllllllllllllllllllIIIIlIIlIlIl) {
        Class<?> llllllllllllllllllllIIIIlIIllIII = null;
        if (llllllllllllllllllllIIIIlIIlIlIl instanceof ParameterizedType) {
            final Class<?> llllllllllllllllllllIIIIlIIllllI = (Class<?>)((ParameterizedType)llllllllllllllllllllIIIIlIIlIlIl).getRawType();
        }
        else {
            llllllllllllllllllllIIIIlIIllIII = (Class<?>)llllllllllllllllllllIIIIlIIlIlIl;
        }
        if (llllllllllllllllllllIIIIlIIllIlI.equals(llllllllllllllllllllIIIIlIIllIII)) {
            return llllllllllllllllllllIIIIlIIlIlIl;
        }
        if (llllllllllllllllllllIIIIlIIllIlI.isInterface()) {
            final Type[] genericInterfaces = llllllllllllllllllllIIIIlIIllIII.getGenericInterfaces();
            final Exception llllllllllllllllllllIIIIlIIlIIIl = (Exception)genericInterfaces.length;
            for (boolean llllllllllllllllllllIIIIlIIlIIII = false; llllllllllllllllllllIIIIlIIlIIII < llllllllllllllllllllIIIIlIIlIIIl; ++llllllllllllllllllllIIIIlIIlIIII) {
                final Type llllllllllllllllllllIIIIlIIlllII = genericInterfaces[llllllllllllllllllllIIIIlIIlIIII];
                final Type llllllllllllllllllllIIIIlIIlllIl;
                if (llllllllllllllllllllIIIIlIIlllII != null && !llllllllllllllllllllIIIIlIIlllII.equals(Object.class) && (llllllllllllllllllllIIIIlIIlllIl = resolveGenericType(llllllllllllllllllllIIIIlIIllIlI, llllllllllllllllllllIIIIlIIlllII)) != null) {
                    return llllllllllllllllllllIIIIlIIlllIl;
                }
            }
        }
        final Type llllllllllllllllllllIIIIlIIlIlll = llllllllllllllllllllIIIIlIIllIII.getGenericSuperclass();
        final Type llllllllllllllllllllIIIIlIIllIll;
        if (llllllllllllllllllllIIIIlIIlIlll != null && !llllllllllllllllllllIIIIlIIlIlll.equals(Object.class) && (llllllllllllllllllllIIIIlIIllIll = resolveGenericType(llllllllllllllllllllIIIIlIIllIlI, llllllllllllllllllllIIIIlIIlIlll)) != null) {
            return llllllllllllllllllllIIIIlIIllIll;
        }
        return null;
    }
    
    public static <T, S extends T> Class<?> resolveRawArgument(final Class<T> llllllllllllllllllllIIIIllIIllll, final Class<S> llllllllllllllllllllIIIIllIlIIII) {
        return resolveRawArgument(resolveGenericType(llllllllllllllllllllIIIIllIIllll, llllllllllllllllllllIIIIllIlIIII), llllllllllllllllllllIIIIllIlIIII);
    }
    
    private static Class<?> wrapPrimitives(final Class<?> lllllllllllllllllllIllllllIllllI) {
        return lllllllllllllllllllIllllllIllllI.isPrimitive() ? TypeResolver.PRIMITIVE_WRAPPERS.get(lllllllllllllllllllIllllllIllllI) : lllllllllllllllllllIllllllIllllI;
    }
    
    public static Type resolveBound(final TypeVariable<?> llllllllllllllllllllIIIIIIlIlIll) {
        final Type[] llllllllllllllllllllIIIIIIlIlIlI = llllllllllllllllllllIIIIIIlIlIll.getBounds();
        if (llllllllllllllllllllIIIIIIlIlIlI.length == 0) {
            return Unknown.class;
        }
        Type llllllllllllllllllllIIIIIIlIlIIl = llllllllllllllllllllIIIIIIlIlIlI[0];
        if (llllllllllllllllllllIIIIIIlIlIIl instanceof TypeVariable) {
            llllllllllllllllllllIIIIIIlIlIIl = resolveBound((TypeVariable<?>)llllllllllllllllllllIIIIIIlIlIIl);
        }
        return (llllllllllllllllllllIIIIIIlIlIIl == Object.class) ? Unknown.class : llllllllllllllllllllIIIIIIlIlIIl;
    }
    
    public static Class<?> resolveRawClass(final Type llllllllllllllllllllIIIIlIIIlIlI, final Class<?> llllllllllllllllllllIIIIlIIIlIIl) {
        return resolveRawClass(llllllllllllllllllllIIIIlIIIlIlI, llllllllllllllllllllIIIIlIIIlIIl, null);
    }
    
    public static <T, S extends T> Class<?>[] resolveRawArguments(final Class<T> llllllllllllllllllllIIIIllIIIIlI, final Class<S> llllllllllllllllllllIIIIlIllllll) {
        return resolveRawArguments(resolveGenericType(llllllllllllllllllllIIIIllIIIIlI, llllllllllllllllllllIIIIlIllllll), llllllllllllllllllllIIIIlIllllll);
    }
    
    private static Map<TypeVariable<?>, Type> getTypeVariableMap(final Class<?> llllllllllllllllllllIIIIIlllIIII, final Class<?> llllllllllllllllllllIIIIIllIllll) {
        final Reference<Map<TypeVariable<?>, Type>> llllllllllllllllllllIIIIIllIlllI = TypeResolver.TYPE_VARIABLE_CACHE.get(llllllllllllllllllllIIIIIlllIIII);
        Map<TypeVariable<?>, Type> llllllllllllllllllllIIIIIllIllIl = (llllllllllllllllllllIIIIIllIlllI != null) ? llllllllllllllllllllIIIIIllIlllI.get() : null;
        if (llllllllllllllllllllIIIIIllIllIl == null) {
            llllllllllllllllllllIIIIIllIllIl = new HashMap<TypeVariable<?>, Type>();
            if (llllllllllllllllllllIIIIIllIllll != null) {
                populateLambdaArgs(llllllllllllllllllllIIIIIllIllll, llllllllllllllllllllIIIIIlllIIII, llllllllllllllllllllIIIIIllIllIl);
            }
            populateSuperTypeArgs(llllllllllllllllllllIIIIIlllIIII.getGenericInterfaces(), llllllllllllllllllllIIIIIllIllIl, llllllllllllllllllllIIIIIllIllll != null);
            Type llllllllllllllllllllIIIIIlllIIlI = llllllllllllllllllllIIIIIlllIIII.getGenericSuperclass();
            for (Class<?> llllllllllllllllllllIIIIIlllIIIl = llllllllllllllllllllIIIIIlllIIII.getSuperclass(); llllllllllllllllllllIIIIIlllIIIl != null && !Object.class.equals(llllllllllllllllllllIIIIIlllIIIl); llllllllllllllllllllIIIIIlllIIIl = llllllllllllllllllllIIIIIlllIIIl.getSuperclass()) {
                if (llllllllllllllllllllIIIIIlllIIlI instanceof ParameterizedType) {
                    populateTypeArgs((ParameterizedType)llllllllllllllllllllIIIIIlllIIlI, llllllllllllllllllllIIIIIllIllIl, false);
                }
                populateSuperTypeArgs(llllllllllllllllllllIIIIIlllIIIl.getGenericInterfaces(), llllllllllllllllllllIIIIIllIllIl, false);
                llllllllllllllllllllIIIIIlllIIlI = llllllllllllllllllllIIIIIlllIIIl.getGenericSuperclass();
            }
            for (Class<?> llllllllllllllllllllIIIIIlllIIIl = llllllllllllllllllllIIIIIlllIIII; llllllllllllllllllllIIIIIlllIIIl.isMemberClass(); llllllllllllllllllllIIIIIlllIIIl = llllllllllllllllllllIIIIIlllIIIl.getEnclosingClass()) {
                llllllllllllllllllllIIIIIlllIIlI = llllllllllllllllllllIIIIIlllIIIl.getGenericSuperclass();
                if (llllllllllllllllllllIIIIIlllIIlI instanceof ParameterizedType) {
                    populateTypeArgs((ParameterizedType)llllllllllllllllllllIIIIIlllIIlI, llllllllllllllllllllIIIIIllIllIl, llllllllllllllllllllIIIIIllIllll != null);
                }
            }
            if (TypeResolver.CACHE_ENABLED) {
                TypeResolver.TYPE_VARIABLE_CACHE.put(llllllllllllllllllllIIIIIlllIIII, new WeakReference<Map<TypeVariable<?>, Type>>(llllllllllllllllllllIIIIIllIllIl));
            }
        }
        return llllllllllllllllllllIIIIIllIllIl;
    }
    
    private TypeResolver() {
    }
    
    public static Class<?>[] resolveRawArguments(final Type llllllllllllllllllllIIIIlIlIllIl, final Class<?> llllllllllllllllllllIIIIlIlIllII) {
        Class<?>[] llllllllllllllllllllIIIIlIlIllll = null;
        Class<?> llllllllllllllllllllIIIIlIlIlllI = null;
        if (TypeResolver.RESOLVES_LAMBDAS && llllllllllllllllllllIIIIlIlIllII.isSynthetic()) {
            final Class<?> llllllllllllllllllllIIIIlIllIlll = (Class<?>)((llllllllllllllllllllIIIIlIlIllIl instanceof ParameterizedType && ((ParameterizedType)llllllllllllllllllllIIIIlIlIllIl).getRawType() instanceof Class) ? ((ParameterizedType)llllllllllllllllllllIIIIlIlIllIl).getRawType() : ((llllllllllllllllllllIIIIlIlIllIl instanceof Class) ? ((Class)llllllllllllllllllllIIIIlIlIllIl) : null));
            if (llllllllllllllllllllIIIIlIllIlll != null && llllllllllllllllllllIIIIlIllIlll.isInterface()) {
                llllllllllllllllllllIIIIlIlIlllI = llllllllllllllllllllIIIIlIllIlll;
            }
        }
        if (llllllllllllllllllllIIIIlIlIllIl instanceof ParameterizedType) {
            final ParameterizedType llllllllllllllllllllIIIIlIllIlIl = (ParameterizedType)llllllllllllllllllllIIIIlIlIllIl;
            final Type[] llllllllllllllllllllIIIIlIllIlII = llllllllllllllllllllIIIIlIllIlIl.getActualTypeArguments();
            llllllllllllllllllllIIIIlIlIllll = (Class<?>[])new Class[llllllllllllllllllllIIIIlIllIlII.length];
            for (int llllllllllllllllllllIIIIlIllIllI = 0; llllllllllllllllllllIIIIlIllIllI < llllllllllllllllllllIIIIlIllIlII.length; ++llllllllllllllllllllIIIIlIllIllI) {
                llllllllllllllllllllIIIIlIlIllll[llllllllllllllllllllIIIIlIllIllI] = resolveRawClass(llllllllllllllllllllIIIIlIllIlII[llllllllllllllllllllIIIIlIllIllI], llllllllllllllllllllIIIIlIlIllII, llllllllllllllllllllIIIIlIlIlllI);
            }
        }
        else if (llllllllllllllllllllIIIIlIlIllIl instanceof TypeVariable) {
            llllllllllllllllllllIIIIlIlIllll = (Class<?>[])new Class[] { resolveRawClass(llllllllllllllllllllIIIIlIlIllIl, llllllllllllllllllllIIIIlIlIllII, llllllllllllllllllllIIIIlIlIlllI) };
        }
        else if (llllllllllllllllllllIIIIlIlIllIl instanceof Class) {
            final TypeVariable<?>[] llllllllllllllllllllIIIIlIllIIlI = (TypeVariable<?>[])((Class)llllllllllllllllllllIIIIlIlIllIl).getTypeParameters();
            llllllllllllllllllllIIIIlIlIllll = (Class<?>[])new Class[llllllllllllllllllllIIIIlIllIIlI.length];
            for (int llllllllllllllllllllIIIIlIllIIll = 0; llllllllllllllllllllIIIIlIllIIll < llllllllllllllllllllIIIIlIllIIlI.length; ++llllllllllllllllllllIIIIlIllIIll) {
                llllllllllllllllllllIIIIlIlIllll[llllllllllllllllllllIIIIlIllIIll] = resolveRawClass(llllllllllllllllllllIIIIlIllIIlI[llllllllllllllllllllIIIIlIllIIll], llllllllllllllllllllIIIIlIlIllII, llllllllllllllllllllIIIIlIlIlllI);
            }
        }
        return llllllllllllllllllllIIIIlIlIllll;
    }
    
    private static boolean isAutoBoxingMethod(final Method lllllllllllllllllllIlllllllIIIll) {
        final Class<?>[] lllllllllllllllllllIlllllllIIIlI = lllllllllllllllllllIlllllllIIIll.getParameterTypes();
        return lllllllllllllllllllIlllllllIIIll.getName().equals("valueOf") && lllllllllllllllllllIlllllllIIIlI.length == 1 && lllllllllllllllllllIlllllllIIIlI[0].isPrimitive() && wrapPrimitives(lllllllllllllllllllIlllllllIIIlI[0]).equals(lllllllllllllllllllIlllllllIIIll.getDeclaringClass());
    }
    
    public static void disableCache() {
        TypeResolver.TYPE_VARIABLE_CACHE.clear();
        TypeResolver.CACHE_ENABLED = false;
    }
    
    private static void populateTypeArgs(final ParameterizedType llllllllllllllllllllIIIIIIlllIII, final Map<TypeVariable<?>, Type> llllllllllllllllllllIIIIIIllIlll, final boolean llllllllllllllllllllIIIIIIlllIIl) {
        if (llllllllllllllllllllIIIIIIlllIII.getRawType() instanceof Class) {
            final TypeVariable<?>[] llllllllllllllllllllIIIIIIllllIl = (TypeVariable<?>[])((Class)llllllllllllllllllllIIIIIIlllIII.getRawType()).getTypeParameters();
            final Type[] llllllllllllllllllllIIIIIIllllII = llllllllllllllllllllIIIIIIlllIII.getActualTypeArguments();
            if (llllllllllllllllllllIIIIIIlllIII.getOwnerType() != null) {
                final Type llllllllllllllllllllIIIIIlIIIlII = llllllllllllllllllllIIIIIIlllIII.getOwnerType();
                if (llllllllllllllllllllIIIIIlIIIlII instanceof ParameterizedType) {
                    populateTypeArgs((ParameterizedType)llllllllllllllllllllIIIIIlIIIlII, llllllllllllllllllllIIIIIIllIlll, llllllllllllllllllllIIIIIIlllIIl);
                }
            }
            for (int llllllllllllllllllllIIIIIIlllllI = 0; llllllllllllllllllllIIIIIIlllllI < llllllllllllllllllllIIIIIIllllII.length; ++llllllllllllllllllllIIIIIIlllllI) {
                final TypeVariable<?> llllllllllllllllllllIIIIIlIIIIII = llllllllllllllllllllIIIIIIllllIl[llllllllllllllllllllIIIIIIlllllI];
                final Type llllllllllllllllllllIIIIIIllllll = llllllllllllllllllllIIIIIIllllII[llllllllllllllllllllIIIIIIlllllI];
                if (llllllllllllllllllllIIIIIIllllll instanceof Class) {
                    llllllllllllllllllllIIIIIIllIlll.put(llllllllllllllllllllIIIIIlIIIIII, llllllllllllllllllllIIIIIIllllll);
                }
                else if (llllllllllllllllllllIIIIIIllllll instanceof GenericArrayType) {
                    llllllllllllllllllllIIIIIIllIlll.put(llllllllllllllllllllIIIIIlIIIIII, llllllllllllllllllllIIIIIIllllll);
                }
                else if (llllllllllllllllllllIIIIIIllllll instanceof ParameterizedType) {
                    llllllllllllllllllllIIIIIIllIlll.put(llllllllllllllllllllIIIIIlIIIIII, llllllllllllllllllllIIIIIIllllll);
                }
                else if (llllllllllllllllllllIIIIIIllllll instanceof TypeVariable) {
                    final TypeVariable<?> llllllllllllllllllllIIIIIlIIIIlI = (TypeVariable<?>)llllllllllllllllllllIIIIIIllllll;
                    if (llllllllllllllllllllIIIIIIlllIIl) {
                        final Type llllllllllllllllllllIIIIIlIIIIll = llllllllllllllllllllIIIIIIllIlll.get(llllllllllllllllllllIIIIIlIIIIII);
                        if (llllllllllllllllllllIIIIIlIIIIll != null) {
                            llllllllllllllllllllIIIIIIllIlll.put(llllllllllllllllllllIIIIIlIIIIlI, llllllllllllllllllllIIIIIlIIIIll);
                            continue;
                        }
                    }
                    Type llllllllllllllllllllIIIIIlIIIIIl = llllllllllllllllllllIIIIIIllIlll.get(llllllllllllllllllllIIIIIlIIIIlI);
                    if (llllllllllllllllllllIIIIIlIIIIIl == null) {
                        llllllllllllllllllllIIIIIlIIIIIl = resolveBound(llllllllllllllllllllIIIIIlIIIIlI);
                    }
                    llllllllllllllllllllIIIIIIllIlll.put(llllllllllllllllllllIIIIIlIIIIII, llllllllllllllllllllIIIIIlIIIIIl);
                }
            }
        }
    }
    
    private static boolean isDefaultMethod(final Method lllllllllllllllllllIllllllllIlll) {
        return TypeResolver.JAVA_VERSION >= 1.8 && lllllllllllllllllllIllllllllIlll.isDefault();
    }
    
    private static void populateLambdaArgs(final Class<?> llllllllllllllllllllIIIIIIIIlIll, final Class<?> llllllllllllllllllllIIIIIIIIlIlI, final Map<TypeVariable<?>, Type> llllllllllllllllllllIIIIIIIIlIIl) {
        if (TypeResolver.RESOLVES_LAMBDAS) {
            final short llllllllllllllllllllIIIIIIIIIlIl = (Object)llllllllllllllllllllIIIIIIIIlIll.getMethods();
            final float llllllllllllllllllllIIIIIIIIIlII = llllllllllllllllllllIIIIIIIIIlIl.length;
            for (byte llllllllllllllllllllIIIIIIIIIIll = 0; llllllllllllllllllllIIIIIIIIIIll < llllllllllllllllllllIIIIIIIIIlII; ++llllllllllllllllllllIIIIIIIIIIll) {
                final Method llllllllllllllllllllIIIIIIIIllII = llllllllllllllllllllIIIIIIIIIlIl[llllllllllllllllllllIIIIIIIIIIll];
                if (!isDefaultMethod(llllllllllllllllllllIIIIIIIIllII) && !Modifier.isStatic(llllllllllllllllllllIIIIIIIIllII.getModifiers()) && !llllllllllllllllllllIIIIIIIIllII.isBridge()) {
                    final Method llllllllllllllllllllIIIIIIIlIIll = TypeResolver.OBJECT_METHODS.get(llllllllllllllllllllIIIIIIIIllII.getName());
                    if (llllllllllllllllllllIIIIIIIlIIll == null || !Arrays.equals(llllllllllllllllllllIIIIIIIIllII.getTypeParameters(), llllllllllllllllllllIIIIIIIlIIll.getTypeParameters())) {
                        final Type llllllllllllllllllllIIIIIIIlIIlI = llllllllllllllllllllIIIIIIIIllII.getGenericReturnType();
                        final Type[] llllllllllllllllllllIIIIIIIlIIIl = llllllllllllllllllllIIIIIIIIllII.getGenericParameterTypes();
                        final Member llllllllllllllllllllIIIIIIIlIIII = getMemberRef(llllllllllllllllllllIIIIIIIIlIlI);
                        if (llllllllllllllllllllIIIIIIIlIIII == null) {
                            return;
                        }
                        if (llllllllllllllllllllIIIIIIIlIIlI instanceof TypeVariable) {
                            Class<?> llllllllllllllllllllIIIIIIIlIllI = (llllllllllllllllllllIIIIIIIlIIII instanceof Method) ? ((Method)llllllllllllllllllllIIIIIIIlIIII).getReturnType() : ((Constructor)llllllllllllllllllllIIIIIIIlIIII).getDeclaringClass();
                            llllllllllllllllllllIIIIIIIlIllI = wrapPrimitives(llllllllllllllllllllIIIIIIIlIllI);
                            if (!llllllllllllllllllllIIIIIIIlIllI.equals(Void.class)) {
                                llllllllllllllllllllIIIIIIIIlIIl.put((TypeVariable<?>)llllllllllllllllllllIIIIIIIlIIlI, llllllllllllllllllllIIIIIIIlIllI);
                            }
                        }
                        final Class<?>[] llllllllllllllllllllIIIIIIIIllll = (llllllllllllllllllllIIIIIIIlIIII instanceof Method) ? ((Method)llllllllllllllllllllIIIIIIIlIIII).getParameterTypes() : ((Constructor)llllllllllllllllllllIIIIIIIlIIII).getParameterTypes();
                        int llllllllllllllllllllIIIIIIIIlllI = 0;
                        if (llllllllllllllllllllIIIIIIIlIIIl.length > 0 && llllllllllllllllllllIIIIIIIlIIIl[0] instanceof TypeVariable && llllllllllllllllllllIIIIIIIlIIIl.length == llllllllllllllllllllIIIIIIIIllll.length + 1) {
                            final Class<?> llllllllllllllllllllIIIIIIIlIlIl = llllllllllllllllllllIIIIIIIlIIII.getDeclaringClass();
                            llllllllllllllllllllIIIIIIIIlIIl.put((TypeVariable<?>)llllllllllllllllllllIIIIIIIlIIIl[0], llllllllllllllllllllIIIIIIIlIlIl);
                            llllllllllllllllllllIIIIIIIIlllI = 1;
                        }
                        int llllllllllllllllllllIIIIIIIIllIl = 0;
                        if (llllllllllllllllllllIIIIIIIlIIIl.length < llllllllllllllllllllIIIIIIIIllll.length) {
                            llllllllllllllllllllIIIIIIIIllIl = llllllllllllllllllllIIIIIIIIllll.length - llllllllllllllllllllIIIIIIIlIIIl.length;
                        }
                        for (int llllllllllllllllllllIIIIIIIlIlII = 0; llllllllllllllllllllIIIIIIIlIlII + llllllllllllllllllllIIIIIIIIllIl < llllllllllllllllllllIIIIIIIIllll.length; ++llllllllllllllllllllIIIIIIIlIlII) {
                            if (llllllllllllllllllllIIIIIIIlIIIl[llllllllllllllllllllIIIIIIIlIlII] instanceof TypeVariable) {
                                llllllllllllllllllllIIIIIIIIlIIl.put((TypeVariable<?>)llllllllllllllllllllIIIIIIIlIIIl[llllllllllllllllllllIIIIIIIlIlII + llllllllllllllllllllIIIIIIIIlllI], wrapPrimitives(llllllllllllllllllllIIIIIIIIllll[llllllllllllllllllllIIIIIIIlIlII + llllllllllllllllllllIIIIIIIIllIl]));
                            }
                        }
                        return;
                    }
                }
            }
        }
    }
    
    public static final class Unknown
    {
        private Unknown() {
        }
    }
}
