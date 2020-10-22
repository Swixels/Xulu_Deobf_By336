package org.reflections.util;

import java.util.function.*;
import java.util.*;
import org.reflections.*;
import java.util.regex.*;

public class FilterBuilder implements Predicate<String>
{
    private final /* synthetic */ List<Predicate<String>> chain;
    
    public FilterBuilder include(final String llllllllllllllllllIlIllIlIIllIll) {
        return this.add(new Include(llllllllllllllllllIlIllIlIIllIll));
    }
    
    public FilterBuilder includePackage(final Class<?> llllllllllllllllllIlIllIlIIIIllI) {
        return this.add(new Include(packageNameRegex(llllllllllllllllllIlIllIlIIIIllI)));
    }
    
    private FilterBuilder(final Collection<Predicate<String>> llllllllllllllllllIlIllIlIlIlIIl) {
        this.chain = new ArrayList<Predicate<String>>(llllllllllllllllllIlIllIlIlIlIIl);
    }
    
    public FilterBuilder exclude(final String llllllllllllllllllIlIllIlIIlIIII) {
        this.add(new Exclude(llllllllllllllllllIlIllIlIIlIIII));
        return this;
    }
    
    public FilterBuilder excludePackage(final Class<?> llllllllllllllllllIlIllIlIIIIIII) {
        return this.add(new Exclude(packageNameRegex(llllllllllllllllllIlIllIlIIIIIII)));
    }
    
    public FilterBuilder add(final Predicate<String> llllllllllllllllllIlIllIlIIIllII) {
        this.chain.add(llllllllllllllllllIlIllIlIIIllII);
        return this;
    }
    
    public FilterBuilder excludePackage(final String llllllllllllllllllIlIllIIllIlIIl) {
        return this.add(new Exclude(prefix(llllllllllllllllllIlIllIIllIlIIl)));
    }
    
    private static String packageNameRegex(final Class<?> llllllllllllllllllIlIllIIllIIlll) {
        return prefix(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIllIIllIIlll.getPackage().getName()).append(".")));
    }
    
    @Override
    public String toString() {
        return Utils.join(this.chain, ", ");
    }
    
    public static FilterBuilder parse(final String llllllllllllllllllIlIllIIlIIIIII) {
        final List<Predicate<String>> llllllllllllllllllIlIllIIIllllll = new ArrayList<Predicate<String>>();
        if (!Utils.isEmpty(llllllllllllllllllIlIllIIlIIIIII)) {
            final String llllllllllllllllllIlIllIIIllllII = (Object)llllllllllllllllllIlIllIIlIIIIII.split(",");
            final float llllllllllllllllllIlIllIIIlllIll = llllllllllllllllllIlIllIIIllllII.length;
            for (String llllllllllllllllllIlIllIIIlllIlI = (String)0; llllllllllllllllllIlIllIIIlllIlI < llllllllllllllllllIlIllIIIlllIll; ++llllllllllllllllllIlIllIIIlllIlI) {
                final String llllllllllllllllllIlIllIIlIIIIIl = llllllllllllllllllIlIllIIIllllII[llllllllllllllllllIlIllIIIlllIlI];
                final String llllllllllllllllllIlIllIIlIIIlIl = llllllllllllllllllIlIllIIlIIIIIl.trim();
                final char llllllllllllllllllIlIllIIlIIIlII = llllllllllllllllllIlIllIIlIIIlIl.charAt(0);
                final String llllllllllllllllllIlIllIIlIIIIll = llllllllllllllllllIlIllIIlIIIlIl.substring(1);
                switch (llllllllllllllllllIlIllIIlIIIlII) {
                    case '+': {
                        final Predicate<String> llllllllllllllllllIlIllIIlIIIlll = new Include(llllllllllllllllllIlIllIIlIIIIll);
                        break;
                    }
                    case '-': {
                        final Predicate<String> llllllllllllllllllIlIllIIlIIIllI = new Exclude(llllllllllllllllllIlIllIIlIIIIll);
                        break;
                    }
                    default: {
                        throw new ReflectionsException("includeExclude should start with either + or -");
                    }
                }
                final Predicate<String> llllllllllllllllllIlIllIIlIIIIlI;
                llllllllllllllllllIlIllIIIllllll.add(llllllllllllllllllIlIllIIlIIIIlI);
            }
            return new FilterBuilder(llllllllllllllllllIlIllIIIllllll);
        }
        return new FilterBuilder();
    }
    
    public FilterBuilder includePackage(final String... llllllllllllllllllIlIllIIlllIIll) {
        final int llllllllllllllllllIlIllIIlllIIlI = (Object)llllllllllllllllllIlIllIIlllIIll;
        final short llllllllllllllllllIlIllIIlllIIIl = (short)llllllllllllllllllIlIllIIlllIIlI.length;
        for (byte llllllllllllllllllIlIllIIlllIIII = 0; llllllllllllllllllIlIllIIlllIIII < llllllllllllllllllIlIllIIlllIIIl; ++llllllllllllllllllIlIllIIlllIIII) {
            final String llllllllllllllllllIlIllIIlllIlll = llllllllllllllllllIlIllIIlllIIlI[llllllllllllllllllIlIllIIlllIIII];
            this.add(new Include(prefix(llllllllllllllllllIlIllIIlllIlll)));
        }
        return this;
    }
    
    public static String prefix(final String llllllllllllllllllIlIllIIllIIIll) {
        return String.valueOf(new StringBuilder().append(llllllllllllllllllIlIllIIllIIIll.replace(".", "\\.")).append(".*"));
    }
    
    @Override
    public boolean test(final String llllllllllllllllllIlIllIIlIllIII) {
        boolean llllllllllllllllllIlIllIIlIlIlll = this.chain.isEmpty() || this.chain.get(0) instanceof Exclude;
        for (final Predicate<String> llllllllllllllllllIlIllIIlIllIlI : this.chain) {
            if (llllllllllllllllllIlIllIIlIlIlll && llllllllllllllllllIlIllIIlIllIlI instanceof Include) {
                continue;
            }
            if (!llllllllllllllllllIlIllIIlIlIlll && llllllllllllllllllIlIllIIlIllIlI instanceof Exclude) {
                continue;
            }
            llllllllllllllllllIlIllIIlIlIlll = llllllllllllllllllIlIllIIlIllIlI.test(llllllllllllllllllIlIllIIlIllIII);
            if (!llllllllllllllllllIlIllIIlIlIlll && llllllllllllllllllIlIllIIlIllIlI instanceof Exclude) {
                break;
            }
        }
        return llllllllllllllllllIlIllIIlIlIlll;
    }
    
    public FilterBuilder() {
        this.chain = new ArrayList<Predicate<String>>();
    }
    
    public static FilterBuilder parsePackages(final String llllllllllllllllllIlIllIIIlIIIIl) {
        final List<Predicate<String>> llllllllllllllllllIlIllIIIlIIIlI = new ArrayList<Predicate<String>>();
        if (!Utils.isEmpty(llllllllllllllllllIlIllIIIlIIIIl)) {
            final double llllllllllllllllllIlIllIIIIlllll = (Object)llllllllllllllllllIlIllIIIlIIIIl.split(",");
            final short llllllllllllllllllIlIllIIIIllllI = (short)llllllllllllllllllIlIllIIIIlllll.length;
            for (float llllllllllllllllllIlIllIIIIlllIl = 0; llllllllllllllllllIlIllIIIIlllIl < llllllllllllllllllIlIllIIIIllllI; ++llllllllllllllllllIlIllIIIIlllIl) {
                final String llllllllllllllllllIlIllIIIlIIlII = llllllllllllllllllIlIllIIIIlllll[llllllllllllllllllIlIllIIIIlllIl];
                final String llllllllllllllllllIlIllIIIlIlIII = llllllllllllllllllIlIllIIIlIIlII.trim();
                final char llllllllllllllllllIlIllIIIlIIlll = llllllllllllllllllIlIllIIIlIlIII.charAt(0);
                String llllllllllllllllllIlIllIIIlIIllI = llllllllllllllllllIlIllIIIlIlIII.substring(1);
                if (!llllllllllllllllllIlIllIIIlIIllI.endsWith(".")) {
                    llllllllllllllllllIlIllIIIlIIllI = String.valueOf(new StringBuilder().append(llllllllllllllllllIlIllIIIlIIllI).append("."));
                }
                llllllllllllllllllIlIllIIIlIIllI = prefix(llllllllllllllllllIlIllIIIlIIllI);
                switch (llllllllllllllllllIlIllIIIlIIlll) {
                    case '+': {
                        final Predicate<String> llllllllllllllllllIlIllIIIlIlIlI = new Include(llllllllllllllllllIlIllIIIlIIllI);
                        break;
                    }
                    case '-': {
                        final Predicate<String> llllllllllllllllllIlIllIIIlIlIIl = new Exclude(llllllllllllllllllIlIllIIIlIIllI);
                        break;
                    }
                    default: {
                        throw new ReflectionsException("includeExclude should start with either + or -");
                    }
                }
                final Predicate<String> llllllllllllllllllIlIllIIIlIIlIl;
                llllllllllllllllllIlIllIIIlIIIlI.add(llllllllllllllllllIlIllIIIlIIlIl);
            }
            return new FilterBuilder(llllllllllllllllllIlIllIIIlIIIlI);
        }
        return new FilterBuilder();
    }
    
    public static class Include extends Matcher
    {
        @Override
        public boolean test(final String llllllllllllllllllIlIlIlIIIllIlI) {
            return this.pattern.matcher(llllllllllllllllllIlIlIlIIIllIlI).matches();
        }
        
        public Include(final String llllllllllllllllllIlIlIlIIIllllI) {
            super(llllllllllllllllllIlIlIlIIIllllI);
        }
        
        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append("+").append(super.toString()));
        }
    }
    
    public abstract static class Matcher implements Predicate<String>
    {
        final /* synthetic */ Pattern pattern;
        
        public Matcher(final String llllllllllllllllIllllllIIllIllll) {
            this.pattern = Pattern.compile(llllllllllllllllIllllllIIllIllll);
        }
        
        @Override
        public String toString() {
            return this.pattern.pattern();
        }
    }
    
    public static class Exclude extends Matcher
    {
        public Exclude(final String llllllllllllllllIllIllIlIllIIllI) {
            super(llllllllllllllllIllIllIlIllIIllI);
        }
        
        @Override
        public boolean test(final String llllllllllllllllIllIllIlIllIIIlI) {
            return !this.pattern.matcher(llllllllllllllllIllIllIlIllIIIlI).matches();
        }
        
        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append("-").append(super.toString()));
        }
    }
}
