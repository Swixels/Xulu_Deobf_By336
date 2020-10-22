package org.reflections.serializers;

import org.reflections.scanners.*;
import org.reflections.util.*;
import java.util.stream.*;
import org.reflections.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.*;
import java.io.*;

public class JavaCodeSerializer implements Serializer
{
    @Override
    public String toString(final Reflections llllllllllllllllIlIlIlIlllIIIIll) {
        if (llllllllllllllllIlIlIlIlllIIIIll.getStore().keys(Utils.index(TypeElementsScanner.class)).isEmpty() && Reflections.log != null) {
            Reflections.log.warn("JavaCodeSerializer needs TypeElementsScanner configured");
        }
        final StringBuilder llllllllllllllllIlIlIlIlllIIlIII = new StringBuilder();
        List<String> llllllllllllllllIlIlIlIlllIIIlll = new ArrayList<String>();
        int llllllllllllllllIlIlIlIlllIIIllI = 1;
        final List<String> llllllllllllllllIlIlIlIlllIIIlIl = new ArrayList<String>(llllllllllllllllIlIlIlIlllIIIIll.getStore().keys(Utils.index(TypeElementsScanner.class)));
        Collections.sort(llllllllllllllllIlIlIlIlllIIIlIl);
        for (final String llllllllllllllllIlIlIlIlllIIllII : llllllllllllllllIlIlIlIlllIIIlIl) {
            List<String> llllllllllllllllIlIlIlIlllIlIlII;
            int llllllllllllllllIlIlIlIlllIlIIll;
            for (llllllllllllllllIlIlIlIlllIlIlII = Arrays.asList(llllllllllllllllIlIlIlIlllIIllII.split("\\.")), llllllllllllllllIlIlIlIlllIlIIll = 0; llllllllllllllllIlIlIlIlllIlIIll < Math.min(llllllllllllllllIlIlIlIlllIlIlII.size(), llllllllllllllllIlIlIlIlllIIIlll.size()) && llllllllllllllllIlIlIlIlllIlIlII.get(llllllllllllllllIlIlIlIlllIlIIll).equals(llllllllllllllllIlIlIlIlllIIIlll.get(llllllllllllllllIlIlIlIlllIlIIll)); ++llllllllllllllllIlIlIlIlllIlIIll) {}
            for (int llllllllllllllllIlIlIlIllllIIIIl = llllllllllllllllIlIlIlIlllIIIlll.size(); llllllllllllllllIlIlIlIllllIIIIl > llllllllllllllllIlIlIlIlllIlIIll; --llllllllllllllllIlIlIlIllllIIIIl) {
                llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", --llllllllllllllllIlIlIlIlllIIIllI)).append("}\n");
            }
            for (int llllllllllllllllIlIlIlIllllIIIII = llllllllllllllllIlIlIlIlllIlIIll; llllllllllllllllIlIlIlIllllIIIII < llllllllllllllllIlIlIlIlllIlIlII.size() - 1; ++llllllllllllllllIlIlIlIllllIIIII) {
                llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIIllI++)).append("public interface ").append(this.getNonDuplicateName(llllllllllllllllIlIlIlIlllIlIlII.get(llllllllllllllllIlIlIlIllllIIIII), llllllllllllllllIlIlIlIlllIlIlII, llllllllllllllllIlIlIlIllllIIIII)).append(" {\n");
            }
            final String llllllllllllllllIlIlIlIlllIlIIlI = llllllllllllllllIlIlIlIlllIlIlII.get(llllllllllllllllIlIlIlIlllIlIlII.size() - 1);
            final List<String> llllllllllllllllIlIlIlIlllIlIIIl = new ArrayList<String>();
            final List<String> llllllllllllllllIlIlIlIlllIlIIII = new ArrayList<String>();
            final List<String> llllllllllllllllIlIlIlIlllIIllll = new ArrayList<String>();
            final Iterable<String> llllllllllllllllIlIlIlIlllIIlllI = llllllllllllllllIlIlIlIlllIIIIll.getStore().get(Utils.index(TypeElementsScanner.class), llllllllllllllllIlIlIlIlllIIllII);
            final List<String> llllllllllllllllIlIlIlIlllIIllIl = StreamSupport.stream(llllllllllllllllIlIlIlIlllIIlllI.spliterator(), false).sorted().collect((Collector<? super String, ?, List<String>>)Collectors.toList());
            for (final String llllllllllllllllIlIlIlIlllIllIlI : llllllllllllllllIlIlIlIlllIIllIl) {
                if (llllllllllllllllIlIlIlIlllIllIlI.startsWith("@")) {
                    llllllllllllllllIlIlIlIlllIlIIIl.add(llllllllllllllllIlIlIlIlllIllIlI.substring(1));
                }
                else if (llllllllllllllllIlIlIlIlllIllIlI.contains("(")) {
                    if (llllllllllllllllIlIlIlIlllIllIlI.startsWith("<")) {
                        continue;
                    }
                    final int llllllllllllllllIlIlIlIlllIlllll = llllllllllllllllIlIlIlIlllIllIlI.indexOf(40);
                    final String llllllllllllllllIlIlIlIlllIllllI = llllllllllllllllIlIlIlIlllIllIlI.substring(0, llllllllllllllllIlIlIlIlllIlllll);
                    final String llllllllllllllllIlIlIlIlllIlllIl = llllllllllllllllIlIlIlIlllIllIlI.substring(llllllllllllllllIlIlIlIlllIlllll + 1, llllllllllllllllIlIlIlIlllIllIlI.indexOf(")"));
                    String llllllllllllllllIlIlIlIlllIlllII = "";
                    if (llllllllllllllllIlIlIlIlllIlllIl.length() != 0) {
                        llllllllllllllllIlIlIlIlllIlllII = String.valueOf(new StringBuilder().append("_").append(llllllllllllllllIlIlIlIlllIlllIl.replace(".", "_").replace(", ", "__").replace("[]", "$$")));
                    }
                    final String llllllllllllllllIlIlIlIlllIllIll = String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIlIlllIllllI).append(llllllllllllllllIlIlIlIlllIlllII));
                    if (!llllllllllllllllIlIlIlIlllIIllll.contains(llllllllllllllllIlIlIlIlllIllllI)) {
                        llllllllllllllllIlIlIlIlllIIllll.add(llllllllllllllllIlIlIlIlllIllllI);
                    }
                    else {
                        llllllllllllllllIlIlIlIlllIIllll.add(llllllllllllllllIlIlIlIlllIllIll);
                    }
                }
                else {
                    if (Utils.isEmpty(llllllllllllllllIlIlIlIlllIllIlI)) {
                        continue;
                    }
                    llllllllllllllllIlIlIlIlllIlIIII.add(llllllllllllllllIlIlIlIlllIllIlI);
                }
            }
            llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIIllI++)).append("public interface ").append(this.getNonDuplicateName(llllllllllllllllIlIlIlIlllIlIIlI, llllllllllllllllIlIlIlIlllIlIlII, llllllllllllllllIlIlIlIlllIlIlII.size() - 1)).append(" {\n");
            if (!llllllllllllllllIlIlIlIlllIlIIII.isEmpty()) {
                llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIIllI++)).append("public interface fields {\n");
                for (final String llllllllllllllllIlIlIlIlllIllIIl : llllllllllllllllIlIlIlIlllIlIIII) {
                    llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIIllI)).append("public interface ").append(this.getNonDuplicateName(llllllllllllllllIlIlIlIlllIllIIl, llllllllllllllllIlIlIlIlllIlIlII)).append(" {}\n");
                }
                llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", --llllllllllllllllIlIlIlIlllIIIllI)).append("}\n");
            }
            if (!llllllllllllllllIlIlIlIlllIIllll.isEmpty()) {
                llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIIllI++)).append("public interface methods {\n");
                for (final String llllllllllllllllIlIlIlIlllIlIlll : llllllllllllllllIlIlIlIlllIIllll) {
                    final String llllllllllllllllIlIlIlIlllIllIII = this.getNonDuplicateName(llllllllllllllllIlIlIlIlllIlIlll, llllllllllllllllIlIlIlIlllIlIIII);
                    llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIIllI)).append("public interface ").append(this.getNonDuplicateName(llllllllllllllllIlIlIlIlllIllIII, llllllllllllllllIlIlIlIlllIlIlII)).append(" {}\n");
                }
                llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", --llllllllllllllllIlIlIlIlllIIIllI)).append("}\n");
            }
            if (!llllllllllllllllIlIlIlIlllIlIIIl.isEmpty()) {
                llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIIllI++)).append("public interface annotations {\n");
                for (String llllllllllllllllIlIlIlIlllIlIllI : llllllllllllllllIlIlIlIlllIlIIIl) {
                    final String llllllllllllllllIlIlIlIlllIlIlIl = llllllllllllllllIlIlIlIlllIlIllI;
                    llllllllllllllllIlIlIlIlllIlIllI = this.getNonDuplicateName(llllllllllllllllIlIlIlIlllIlIllI, llllllllllllllllIlIlIlIlllIlIlII);
                    llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIIllI)).append("public interface ").append(llllllllllllllllIlIlIlIlllIlIllI).append(" {}\n");
                }
                llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", --llllllllllllllllIlIlIlIlllIIIllI)).append("}\n");
            }
            llllllllllllllllIlIlIlIlllIIIlll = llllllllllllllllIlIlIlIlllIlIlII;
        }
        for (int llllllllllllllllIlIlIlIlllIIlIll = llllllllllllllllIlIlIlIlllIIIlll.size(); llllllllllllllllIlIlIlIlllIIlIll >= 1; --llllllllllllllllIlIlIlIlllIIlIll) {
            llllllllllllllllIlIlIlIlllIIlIII.append(Utils.repeat("\t", llllllllllllllllIlIlIlIlllIIlIll)).append("}\n");
        }
        return String.valueOf(llllllllllllllllIlIlIlIlllIIlIII);
    }
    
    private String getNonDuplicateName(final String llllllllllllllllIlIlIlIllIlIIIII, final List<String> llllllllllllllllIlIlIlIllIIlllll, final int llllllllllllllllIlIlIlIllIIllllI) {
        final String llllllllllllllllIlIlIlIllIlIIIlI = this.normalize(llllllllllllllllIlIlIlIllIlIIIII);
        for (int llllllllllllllllIlIlIlIllIlIIlll = 0; llllllllllllllllIlIlIlIllIlIIlll < llllllllllllllllIlIlIlIllIIllllI; ++llllllllllllllllIlIlIlIllIlIIlll) {
            if (llllllllllllllllIlIlIlIllIlIIIlI.equals(llllllllllllllllIlIlIlIllIIlllll.get(llllllllllllllllIlIlIlIllIlIIlll))) {
                return this.getNonDuplicateName(String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIlIllIlIIIlI).append("_")), llllllllllllllllIlIlIlIllIIlllll, llllllllllllllllIlIlIlIllIIllllI);
            }
        }
        return llllllllllllllllIlIlIlIllIlIIIlI;
    }
    
    public static Class<?> resolveClass(final Class llllllllllllllllIlIlIlIlIlllllll) {
        try {
            return resolveClassOf(llllllllllllllllIlIlIlIlIlllllll);
        }
        catch (Exception llllllllllllllllIlIlIlIllIIIIIII) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not resolve to class ").append(llllllllllllllllIlIlIlIlIlllllll.getName())), llllllllllllllllIlIlIlIllIIIIIII);
        }
    }
    
    private String getNonDuplicateName(final String llllllllllllllllIlIlIlIllIIlIIII, final List<String> llllllllllllllllIlIlIlIllIIlIIlI) {
        return this.getNonDuplicateName(llllllllllllllllIlIlIlIllIIlIIII, llllllllllllllllIlIlIlIllIIlIIlI, llllllllllllllllIlIlIlIllIIlIIlI.size());
    }
    
    public static Method resolveMethod(final Class llllllllllllllllIlIlIlIlIlIIllll) {
        final String llllllllllllllllIlIlIlIlIlIIlllI = llllllllllllllllIlIlIlIlIlIIllll.getSimpleName();
        try {
            String llllllllllllllllIlIlIlIlIlIlIIll;
            Class<?>[] llllllllllllllllIlIlIlIlIlIlIIlI;
            if (llllllllllllllllIlIlIlIlIlIIlllI.contains("_")) {
                final String llllllllllllllllIlIlIlIlIlIlIlIl = llllllllllllllllIlIlIlIlIlIIlllI.substring(0, llllllllllllllllIlIlIlIlIlIIlllI.indexOf("_"));
                final String[] llllllllllllllllIlIlIlIlIlIlIllI = llllllllllllllllIlIlIlIlIlIIlllI.substring(llllllllllllllllIlIlIlIlIlIIlllI.indexOf("_") + 1).split("__");
                final Class<?>[] llllllllllllllllIlIlIlIlIlIlIlII = (Class<?>[])new Class[llllllllllllllllIlIlIlIlIlIlIllI.length];
                for (int llllllllllllllllIlIlIlIlIlIlIlll = 0; llllllllllllllllIlIlIlIlIlIlIlll < llllllllllllllllIlIlIlIlIlIlIllI.length; ++llllllllllllllllIlIlIlIlIlIlIlll) {
                    final String llllllllllllllllIlIlIlIlIlIllIII = llllllllllllllllIlIlIlIlIlIlIllI[llllllllllllllllIlIlIlIlIlIlIlll].replace("$$", "[]").replace("_", ".");
                    llllllllllllllllIlIlIlIlIlIlIlII[llllllllllllllllIlIlIlIlIlIlIlll] = ReflectionUtils.forName(llllllllllllllllIlIlIlIlIlIllIII, new ClassLoader[0]);
                }
            }
            else {
                llllllllllllllllIlIlIlIlIlIlIIll = llllllllllllllllIlIlIlIlIlIIlllI;
                llllllllllllllllIlIlIlIlIlIlIIlI = null;
            }
            final Class<?> llllllllllllllllIlIlIlIlIlIlIIIl = (Class<?>)llllllllllllllllIlIlIlIlIlIIllll.getDeclaringClass().getDeclaringClass();
            return resolveClassOf(llllllllllllllllIlIlIlIlIlIlIIIl).getDeclaredMethod(llllllllllllllllIlIlIlIlIlIlIIll, llllllllllllllllIlIlIlIlIlIlIIlI);
        }
        catch (Exception llllllllllllllllIlIlIlIlIlIlIIII) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not resolve to method ").append(llllllllllllllllIlIlIlIlIlIIllll.getName())), llllllllllllllllIlIlIlIlIlIlIIII);
        }
    }
    
    @Override
    public File save(final Reflections llllllllllllllllIlIlIllIIIIIIIII, String llllllllllllllllIlIlIlIlllllllll) {
        if (((String)llllllllllllllllIlIlIlIlllllllll).endsWith("/")) {
            llllllllllllllllIlIlIlIlllllllll = ((String)llllllllllllllllIlIlIlIlllllllll).substring(0, ((String)llllllllllllllllIlIlIlIlllllllll).length() - 1);
        }
        final String llllllllllllllllIlIlIllIIIIIIllI = ((String)llllllllllllllllIlIlIlIlllllllll).replace('.', '/').concat(".java");
        final File llllllllllllllllIlIlIllIIIIIIlIl = Utils.prepareFile(llllllllllllllllIlIlIllIIIIIIllI);
        final int llllllllllllllllIlIlIllIIIIIIIlI = ((String)llllllllllllllllIlIlIlIlllllllll).lastIndexOf(46);
        String llllllllllllllllIlIlIllIIIIIIlII = null;
        String llllllllllllllllIlIlIllIIIIIIIll = null;
        if (llllllllllllllllIlIlIllIIIIIIIlI == -1) {
            final String llllllllllllllllIlIlIllIIIIIllIl = "";
            final String llllllllllllllllIlIlIllIIIIIllII = ((String)llllllllllllllllIlIlIlIlllllllll).substring(((String)llllllllllllllllIlIlIlIlllllllll).lastIndexOf(47) + 1);
        }
        else {
            llllllllllllllllIlIlIllIIIIIIlII = ((String)llllllllllllllllIlIlIlIlllllllll).substring(((String)llllllllllllllllIlIlIlIlllllllll).lastIndexOf(47) + 1, llllllllllllllllIlIlIllIIIIIIIlI);
            llllllllllllllllIlIlIllIIIIIIIll = ((String)llllllllllllllllIlIlIlIlllllllll).substring(llllllllllllllllIlIlIllIIIIIIIlI + 1);
        }
        try {
            final StringBuilder llllllllllllllllIlIlIllIIIIIlIll = new StringBuilder();
            llllllllllllllllIlIlIllIIIIIlIll.append("//generated using Reflections JavaCodeSerializer").append(" [").append(new Date()).append("]").append("\n");
            if (llllllllllllllllIlIlIllIIIIIIlII.length() != 0) {
                llllllllllllllllIlIlIllIIIIIlIll.append("package ").append(llllllllllllllllIlIlIllIIIIIIlII).append(";\n");
                llllllllllllllllIlIlIllIIIIIlIll.append("\n");
            }
            llllllllllllllllIlIlIllIIIIIlIll.append("public interface ").append(llllllllllllllllIlIlIllIIIIIIIll).append(" {\n\n");
            llllllllllllllllIlIlIllIIIIIlIll.append(this.toString(llllllllllllllllIlIlIllIIIIIIIII));
            llllllllllllllllIlIlIllIIIIIlIll.append("}\n");
            Files.write(new File(llllllllllllllllIlIlIllIIIIIIllI).toPath(), String.valueOf(llllllllllllllllIlIlIllIIIIIlIll).getBytes(Charset.defaultCharset()), new OpenOption[0]);
        }
        catch (IOException llllllllllllllllIlIlIllIIIIIlIlI) {
            throw new RuntimeException();
        }
        return llllllllllllllllIlIlIllIIIIIIlIl;
    }
    
    public static Field resolveField(final Class llllllllllllllllIlIlIlIlIlllIllI) {
        try {
            final String llllllllllllllllIlIlIlIlIllllIIl = llllllllllllllllIlIlIlIlIlllIllI.getSimpleName();
            final Class<?> llllllllllllllllIlIlIlIlIllllIII = (Class<?>)llllllllllllllllIlIlIlIlIlllIllI.getDeclaringClass().getDeclaringClass();
            return resolveClassOf(llllllllllllllllIlIlIlIlIllllIII).getDeclaredField(llllllllllllllllIlIlIlIlIllllIIl);
        }
        catch (Exception llllllllllllllllIlIlIlIlIlllIlll) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not resolve to field ").append(llllllllllllllllIlIlIlIlIlllIllI.getName())), llllllllllllllllIlIlIlIlIlllIlll);
        }
    }
    
    public static Annotation resolveAnnotation(final Class llllllllllllllllIlIlIlIlIllIIlIl) {
        try {
            final String llllllllllllllllIlIlIlIlIllIllII = llllllllllllllllIlIlIlIlIllIIlIl.getSimpleName().replace("_", ".");
            final Class<?> llllllllllllllllIlIlIlIlIllIlIll = (Class<?>)llllllllllllllllIlIlIlIlIllIIlIl.getDeclaringClass().getDeclaringClass();
            final Class<?> llllllllllllllllIlIlIlIlIllIlIlI = resolveClassOf(llllllllllllllllIlIlIlIlIllIlIll);
            final Class<? extends Annotation> llllllllllllllllIlIlIlIlIllIlIIl = (Class<? extends Annotation>)ReflectionUtils.forName(llllllllllllllllIlIlIlIlIllIllII, new ClassLoader[0]);
            final Annotation llllllllllllllllIlIlIlIlIllIlIII = llllllllllllllllIlIlIlIlIllIlIlI.getAnnotation(llllllllllllllllIlIlIlIlIllIlIIl);
            return llllllllllllllllIlIlIlIlIllIlIII;
        }
        catch (Exception llllllllllllllllIlIlIlIlIllIIlll) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not resolve to annotation ").append(llllllllllllllllIlIlIlIlIllIIlIl.getName())), llllllllllllllllIlIlIlIlIllIIlll);
        }
    }
    
    static {
        pathSeparator = "_";
        arrayDescriptor = "$$";
        dotSeparator = ".";
        tokenSeparator = "_";
        doubleSeparator = "__";
    }
    
    public static Class<?> resolveClassOf(final Class llllllllllllllllIlIlIlIllIIIlIlI) throws ClassNotFoundException {
        Class<?> llllllllllllllllIlIlIlIllIIIlIIl = (Class<?>)llllllllllllllllIlIlIlIllIIIlIlI;
        final LinkedList<String> llllllllllllllllIlIlIlIllIIIlIII = new LinkedList<String>();
        while (llllllllllllllllIlIlIlIllIIIlIIl != null) {
            llllllllllllllllIlIlIlIllIIIlIII.addFirst(llllllllllllllllIlIlIlIllIIIlIIl.getSimpleName());
            llllllllllllllllIlIlIlIllIIIlIIl = llllllllllllllllIlIlIlIllIIIlIIl.getDeclaringClass();
        }
        final String llllllllllllllllIlIlIlIllIIIIlll = Utils.join(llllllllllllllllIlIlIlIllIIIlIII.subList(1, llllllllllllllllIlIlIlIllIIIlIII.size()), ".").replace(".$", "$");
        return Class.forName(llllllllllllllllIlIlIlIllIIIIlll);
    }
    
    @Override
    public Reflections read(final InputStream llllllllllllllllIlIlIllIIIIlIlll) {
        throw new UnsupportedOperationException("read is not implemented on JavaCodeSerializer");
    }
    
    private String normalize(final String llllllllllllllllIlIlIlIllIIllIII) {
        return llllllllllllllllIlIlIlIllIIllIII.replace(".", "_");
    }
}
