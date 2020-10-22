package org.reflections;

import org.slf4j.*;
import java.util.function.*;
import java.lang.reflect.*;
import java.util.regex.*;
import java.util.stream.*;
import java.lang.annotation.*;
import org.reflections.scanners.*;
import java.net.*;
import org.reflections.vfs.*;
import java.util.concurrent.*;
import java.util.*;
import org.reflections.serializers.*;
import org.reflections.util.*;
import java.io.*;

public class Reflections
{
    public static /* synthetic */ Logger log;
    protected final transient /* synthetic */ Configuration configuration;
    protected /* synthetic */ Store store;
    
    public Set<Method> getMethodsWithAnyParamAnnotated(final Annotation llllllllllllllllIlllllllllIIlllI) {
        return Utils.filter(this.getMethodsWithAnyParamAnnotated(llllllllllllllllIlllllllllIIlllI.annotationType()), ReflectionUtils.withAnyParameterAnnotation(llllllllllllllllIlllllllllIIlllI));
    }
    
    public Set<Constructor> getConstructorsWithAnyParamAnnotated(final Annotation llllllllllllllllIllllllllIlIlllI) {
        return (Set<Constructor>)Utils.filter(this.getConstructorsWithAnyParamAnnotated(llllllllllllllllIllllllllIlIlllI.annotationType()), ReflectionUtils.withAnyParameterAnnotation(llllllllllllllllIllllllllIlIlllI));
    }
    
    public Store getStore() {
        return this.store;
    }
    
    private static String producingDescription(final Store lllllllllllllllllIIIIIIIlIllllll) {
        int lllllllllllllllllIIIIIIIlIlllllI = 0;
        int lllllllllllllllllIIIIIIIlIllllIl = 0;
        for (final String lllllllllllllllllIIIIIIIllIIIIII : lllllllllllllllllIIIIIIIlIllllll.keySet()) {
            lllllllllllllllllIIIIIIIlIlllllI += lllllllllllllllllIIIIIIIlIllllll.keys(lllllllllllllllllIIIIIIIllIIIIII).size();
            lllllllllllllllllIIIIIIIlIllllIl += lllllllllllllllllIIIIIIIlIllllll.values(lllllllllllllllllIIIIIIIllIIIIII).size();
        }
        return String.format("%d keys and %d values", lllllllllllllllllIIIIIIIlIlllllI, lllllllllllllllllIIIIIIIlIllllIl);
    }
    
    public Reflections(final String lllllllllllllllllIIIIIIIlllIlIIl, final Scanner... lllllllllllllllllIIIIIIIlllIlIll) {
        this(new Object[] { lllllllllllllllllIIIIIIIlllIlIIl, lllllllllllllllllIIIIIIIlllIlIll });
    }
    
    public List<String> getConstructorParamNames(final Constructor llllllllllllllllIllllllllIIIIlIl) {
        final Set<String> llllllllllllllllIllllllllIIIIlII = this.store.get(MethodParameterNamesScanner.class, Utils.name(llllllllllllllllIllllllllIIIIlIl));
        return (llllllllllllllllIllllllllIIIIlII.size() == 1) ? Arrays.asList(llllllllllllllllIllllllllIIIIlII.iterator().next().split(", ")) : Collections.emptyList();
    }
    
    public Configuration getConfiguration() {
        return this.configuration;
    }
    
    public Reflections merge(final Reflections lllllllllllllllllIIIIIIIIlIlIlII) {
        this.store.merge(lllllllllllllllllIIIIIIIIlIlIlII.store);
        return this;
    }
    
    public Reflections collect(final File lllllllllllllllllIIIIIIIIlIllIll) {
        FileInputStream lllllllllllllllllIIIIIIIIlIlllIl = null;
        try {
            lllllllllllllllllIIIIIIIIlIlllIl = new FileInputStream(lllllllllllllllllIIIIIIIIlIllIll);
            return this.collect(lllllllllllllllllIIIIIIIIlIlllIl);
        }
        catch (FileNotFoundException lllllllllllllllllIIIIIIIIllIIIII) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not obtain input stream from file ").append(lllllllllllllllllIIIIIIIIlIllIll)), lllllllllllllllllIIIIIIIIllIIIII);
        }
        finally {
            Utils.close(lllllllllllllllllIIIIIIIIlIlllIl);
        }
    }
    
    public Set<Method> getMethodsMatchParams(final Class<?>... llllllllllllllllIllllllllllIIIII) {
        return Utils.getMethodsFromDescriptors(this.store.get(MethodParameterScanner.class, Utils.names(llllllllllllllllIllllllllllIIIII).toString()), this.loaders());
    }
    
    public Set<Class<?>> getTypesAnnotatedWith(final Annotation lllllllllllllllllIIIIIIIIIIIIlII, final boolean lllllllllllllllllIIIIIIIIIIIlIIl) {
        final Set<String> lllllllllllllllllIIIIIIIIIIIlIII = this.store.get(TypeAnnotationsScanner.class, lllllllllllllllllIIIIIIIIIIIIlII.annotationType().getName());
        final Set<Class<?>> lllllllllllllllllIIIIIIIIIIIIlll = Utils.filter(ReflectionUtils.forNames(lllllllllllllllllIIIIIIIIIIIlIII, this.loaders()), ReflectionUtils.withAnnotation(lllllllllllllllllIIIIIIIIIIIIlII));
        final Set<Class<?>> lllllllllllllllllIIIIIIIIIIIIllI = ReflectionUtils.forNames(Utils.filter(this.getAllAnnotated(Utils.names(lllllllllllllllllIIIIIIIIIIIIlll), lllllllllllllllllIIIIIIIIIIIIlII.annotationType(), lllllllllllllllllIIIIIIIIIIIlIIl), llllllllllllllllIlllllllIIllIIll -> !lllllllllllllllllIIIIIIIIIIIlIII.contains(llllllllllllllllIlllllllIIllIIll)), this.loaders());
        lllllllllllllllllIIIIIIIIIIIIlll.addAll(lllllllllllllllllIIIIIIIIIIIIllI);
        return lllllllllllllllllIIIIIIIIIIIIlll;
    }
    
    public Set<Constructor> getConstructorsMatchParams(final Class<?>... llllllllllllllllIllllllllIlllIlI) {
        return Utils.getConstructorsFromDescriptors(this.store.get(MethodParameterScanner.class, Utils.names(llllllllllllllllIllllllllIlllIlI).toString()), this.loaders());
    }
    
    public Set<Member> getMethodUsage(final Method llllllllllllllllIlllllllIlllIlIl) {
        return Utils.getMembersFromDescriptors(this.store.get(MemberUsageScanner.class, Utils.name(llllllllllllllllIlllllllIlllIlIl)), new ClassLoader[0]);
    }
    
    public Reflections(final Object... lllllllllllllllllIIIIIIIlllIIIlI) {
        this(ConfigurationBuilder.build(lllllllllllllllllIIIIIIIlllIIIlI));
    }
    
    public Set<String> getResources(final Predicate<String> llllllllllllllllIllllllllIIllIlI) {
        final Set<String> llllllllllllllllIllllllllIIlllII = Utils.filter(this.store.keys(Utils.index(ResourcesScanner.class)), llllllllllllllllIllllllllIIllIlI);
        return this.store.get(ResourcesScanner.class, llllllllllllllllIllllllllIIlllII);
    }
    
    public File save(final String llllllllllllllllIlllllllIlIlIIll, final Serializer llllllllllllllllIlllllllIlIlIIlI) {
        final File llllllllllllllllIlllllllIlIlIlIl = llllllllllllllllIlllllllIlIlIIlI.save(this, llllllllllllllllIlllllllIlIlIIll);
        if (Reflections.log != null) {
            Reflections.log.info(String.valueOf(new StringBuilder().append("Reflections successfully saved in ").append(llllllllllllllllIlllllllIlIlIlIl.getAbsolutePath()).append(" using ").append(llllllllllllllllIlllllllIlIlIIlI.getClass().getSimpleName())));
        }
        return llllllllllllllllIlllllllIlIlIlIl;
    }
    
    public Reflections collect(final InputStream lllllllllllllllllIIIIIIIIllIIlll) {
        try {
            this.merge(this.configuration.getSerializer().read(lllllllllllllllllIIIIIIIIllIIlll));
            if (Reflections.log != null) {
                Reflections.log.info(String.valueOf(new StringBuilder().append("Reflections collected metadata from input stream using serializer ").append(this.configuration.getSerializer().getClass().getName())));
            }
        }
        catch (Exception lllllllllllllllllIIIIIIIIllIlIll) {
            throw new ReflectionsException("could not merge input stream", lllllllllllllllllIIIIIIIIllIlIll);
        }
        return this;
    }
    
    public Set<Field> getFieldsAnnotatedWith(final Class<? extends Annotation> llllllllllllllllIllllllllIlIlIII) {
        return this.store.get(FieldAnnotationsScanner.class, llllllllllllllllIllllllllIlIlIII.getName()).stream().map(llllllllllllllllIlllllllIlIIIIlI -> Utils.getFieldFromString(llllllllllllllllIlllllllIlIIIIlI, this.loaders())).collect((Collector<? super Object, ?, Set<Field>>)Collectors.toSet());
    }
    
    public Set<Class<?>> getTypesAnnotatedWith(final Class<? extends Annotation> lllllllllllllllllIIIIIIIIIIllIlI, final boolean lllllllllllllllllIIIIIIIIIIlllIl) {
        final Set<String> lllllllllllllllllIIIIIIIIIIlllII = this.store.get(TypeAnnotationsScanner.class, lllllllllllllllllIIIIIIIIIIllIlI.getName());
        lllllllllllllllllIIIIIIIIIIlllII.addAll(this.getAllAnnotated(lllllllllllllllllIIIIIIIIIIlllII, lllllllllllllllllIIIIIIIIIIllIlI, lllllllllllllllllIIIIIIIIIIlllIl));
        return ReflectionUtils.forNames(lllllllllllllllllIIIIIIIIIIlllII, this.loaders());
    }
    
    private void expandSupertypes(final Store lllllllllllllllllIIIIIIIIIllIlII, final String lllllllllllllllllIIIIIIIIIllIIll, final Class<?> lllllllllllllllllIIIIIIIIIllIIlI) {
        for (final Class<?> lllllllllllllllllIIIIIIIIIlllIlI : ReflectionUtils.getSuperTypes(lllllllllllllllllIIIIIIIIIllIIlI)) {
            if (lllllllllllllllllIIIIIIIIIllIlII.put(SubTypesScanner.class, lllllllllllllllllIIIIIIIIIlllIlI.getName(), lllllllllllllllllIIIIIIIIIllIIll)) {
                if (Reflections.log != null) {
                    Reflections.log.debug("expanded subtype {} -> {}", (Object)lllllllllllllllllIIIIIIIIIlllIlI.getName(), (Object)lllllllllllllllllIIIIIIIIIllIIll);
                }
                this.expandSupertypes(lllllllllllllllllIIIIIIIIIllIlII, lllllllllllllllllIIIIIIIIIlllIlI.getName(), lllllllllllllllllIIIIIIIIIlllIlI);
            }
        }
    }
    
    public Set<Method> getMethodsAnnotatedWith(final Annotation llllllllllllllllIllllllllllIIlII) {
        return Utils.filter(this.getMethodsAnnotatedWith(llllllllllllllllIllllllllllIIlII.annotationType()), ReflectionUtils.withAnnotation(llllllllllllllllIllllllllllIIlII));
    }
    
    public <T> Set<Class<? extends T>> getSubTypesOf(final Class<T> lllllllllllllllllIIIIIIIIIlIllII) {
        return (Set<Class<? extends T>>)ReflectionUtils.forNames(this.store.getAll(SubTypesScanner.class, lllllllllllllllllIIIIIIIIIlIllII.getName()), this.loaders());
    }
    
    public Reflections(final Configuration lllllllllllllllllIIIIIIIllllIlIl) {
        this.configuration = lllllllllllllllllIIIIIIIllllIlIl;
        this.store = new Store();
        if (lllllllllllllllllIIIIIIIllllIlIl.getScanners() != null && !lllllllllllllllllIIIIIIIllllIlIl.getScanners().isEmpty()) {
            for (final Scanner lllllllllllllllllIIIIIIIllllIlll : lllllllllllllllllIIIIIIIllllIlIl.getScanners()) {
                lllllllllllllllllIIIIIIIllllIlll.setConfiguration(lllllllllllllllllIIIIIIIllllIlIl);
            }
            this.scan();
            if (lllllllllllllllllIIIIIIIllllIlIl.shouldExpandSuperTypes()) {
                this.expandSuperTypes();
            }
        }
    }
    
    public static Reflections collect() {
        return collect("META-INF/reflections/", new FilterBuilder().include(".*-reflections.xml"), new Serializer[0]);
    }
    
    public Set<Method> getMethodsReturn(final Class llllllllllllllllIlllllllllIllIII) {
        return Utils.getMethodsFromDescriptors(this.store.get(MethodParameterScanner.class, Utils.names(llllllllllllllllIlllllllllIllIII)), this.loaders());
    }
    
    private ClassLoader[] loaders() {
        return this.configuration.getClassLoaders();
    }
    
    protected Collection<String> getAllAnnotated(final Collection<String> llllllllllllllllIlllllllllllIlll, final Class<? extends Annotation> llllllllllllllllIlllllllllllIllI, final boolean llllllllllllllllIlllllllllllIIIl) {
        if (!llllllllllllllllIlllllllllllIIIl) {
            final Collection<String> llllllllllllllllIllllllllllllIIl = this.store.getAllIncluding(TypeAnnotationsScanner.class, llllllllllllllllIlllllllllllIlll);
            return this.store.getAllIncluding(SubTypesScanner.class, llllllllllllllllIllllllllllllIIl);
        }
        if (llllllllllllllllIlllllllllllIllI.isAnnotationPresent(Inherited.class)) {
            final Class<?> llllllllllllllllIlllllllIIllllII;
            final Set<String> llllllllllllllllIllllllllllllIlI = this.store.get(SubTypesScanner.class, Utils.filter(llllllllllllllllIlllllllllllIlll, llllllllllllllllIlllllllIIlllIlI -> {
                llllllllllllllllIlllllllIIllllII = ReflectionUtils.forName(llllllllllllllllIlllllllIIlllIlI, this.loaders());
                return llllllllllllllllIlllllllIIllllII != null && !llllllllllllllllIlllllllIIllllII.isInterface();
            }));
            return this.store.getAllIncluding(SubTypesScanner.class, llllllllllllllllIllllllllllllIlI);
        }
        return llllllllllllllllIlllllllllllIlll;
    }
    
    public Set<Member> getConstructorUsage(final Constructor llllllllllllllllIlllllllIllIllll) {
        return Utils.getMembersFromDescriptors(this.store.get(MemberUsageScanner.class, Utils.name(llllllllllllllllIlllllllIllIllll)), new ClassLoader[0]);
    }
    
    public Set<Method> getMethodsAnnotatedWith(final Class<? extends Annotation> llllllllllllllllIllllllllllIllII) {
        return Utils.getMethodsFromDescriptors(this.store.get(MethodAnnotationsScanner.class, llllllllllllllllIllllllllllIllII.getName()), this.loaders());
    }
    
    public Set<Member> getFieldUsage(final Field llllllllllllllllIlllllllIlllllIl) {
        return Utils.getMembersFromDescriptors(this.store.get(MemberUsageScanner.class, Utils.name(llllllllllllllllIlllllllIlllllIl)), new ClassLoader[0]);
    }
    
    protected void scan(final URL lllllllllllllllllIIIIIIIlIIlllll) {
        final Vfs.Dir lllllllllllllllllIIIIIIIlIlIIIIl = Vfs.fromURL(lllllllllllllllllIIIIIIIlIIlllll);
        try {
            for (final Vfs.File lllllllllllllllllIIIIIIIlIlIIlII : lllllllllllllllllIIIIIIIlIlIIIIl.getFiles()) {
                final Predicate<String> lllllllllllllllllIIIIIIIlIlIIlll = this.configuration.getInputsFilter();
                final String lllllllllllllllllIIIIIIIlIlIIllI = lllllllllllllllllIIIIIIIlIlIIlII.getRelativePath();
                final String lllllllllllllllllIIIIIIIlIlIIlIl = lllllllllllllllllIIIIIIIlIlIIllI.replace('/', '.');
                if (lllllllllllllllllIIIIIIIlIlIIlll == null || lllllllllllllllllIIIIIIIlIlIIlll.test(lllllllllllllllllIIIIIIIlIlIIllI) || lllllllllllllllllIIIIIIIlIlIIlll.test(lllllllllllllllllIIIIIIIlIlIIlIl)) {
                    Object lllllllllllllllllIIIIIIIlIlIlIII = null;
                    for (final Scanner lllllllllllllllllIIIIIIIlIlIlIIl : this.configuration.getScanners()) {
                        try {
                            if (!lllllllllllllllllIIIIIIIlIlIlIIl.acceptsInput(lllllllllllllllllIIIIIIIlIlIIllI) && !lllllllllllllllllIIIIIIIlIlIlIIl.acceptsInput(lllllllllllllllllIIIIIIIlIlIIlIl)) {
                                continue;
                            }
                            lllllllllllllllllIIIIIIIlIlIlIII = lllllllllllllllllIIIIIIIlIlIlIIl.scan(lllllllllllllllllIIIIIIIlIlIIlII, lllllllllllllllllIIIIIIIlIlIlIII, this.store);
                        }
                        catch (Exception lllllllllllllllllIIIIIIIlIlIlIlI) {
                            if (Reflections.log == null) {
                                continue;
                            }
                            Reflections.log.debug("could not scan file {} in url {} with scanner {}", new Object[] { lllllllllllllllllIIIIIIIlIlIIlII.getRelativePath(), lllllllllllllllllIIIIIIIlIIlllll.toExternalForm(), lllllllllllllllllIIIIIIIlIlIlIIl.getClass().getSimpleName(), lllllllllllllllllIIIIIIIlIlIlIlI });
                        }
                    }
                }
            }
        }
        finally {
            lllllllllllllllllIIIIIIIlIlIIIIl.close();
        }
    }
    
    protected Reflections() {
        this.configuration = new ConfigurationBuilder();
        this.store = new Store();
    }
    
    public void expandSuperTypes() {
        final String lllllllllllllllllIIIIIIIIlIIlIII = Utils.index(SubTypesScanner.class);
        final Set<String> lllllllllllllllllIIIIIIIIlIIIlll = this.store.keys(lllllllllllllllllIIIIIIIIlIIlIII);
        lllllllllllllllllIIIIIIIIlIIIlll.removeAll(this.store.values(lllllllllllllllllIIIIIIIIlIIlIII));
        for (final String lllllllllllllllllIIIIIIIIlIIlIlI : lllllllllllllllllIIIIIIIIlIIIlll) {
            final Class<?> lllllllllllllllllIIIIIIIIlIIlIll = ReflectionUtils.forName(lllllllllllllllllIIIIIIIIlIIlIlI, this.loaders());
            if (lllllllllllllllllIIIIIIIIlIIlIll != null) {
                this.expandSupertypes(this.store, lllllllllllllllllIIIIIIIIlIIlIlI, lllllllllllllllllIIIIIIIIlIIlIll);
            }
        }
    }
    
    public Set<Class<?>> getTypesAnnotatedWith(final Class<? extends Annotation> lllllllllllllllllIIIIIIIIIlIIllI) {
        return this.getTypesAnnotatedWith(lllllllllllllllllIIIIIIIIIlIIllI, false);
    }
    
    protected void scan() {
        if (this.configuration.getUrls() == null || this.configuration.getUrls().isEmpty()) {
            if (Reflections.log != null) {
                Reflections.log.warn("given scan urls are empty. set urls in the configuration");
            }
            return;
        }
        if (Reflections.log != null && Reflections.log.isDebugEnabled()) {
            Reflections.log.debug("going to scan these urls: {}", (Object)this.configuration.getUrls());
        }
        final long lllllllllllllllllIIIIIIIllIlIIIl = System.currentTimeMillis();
        int lllllllllllllllllIIIIIIIllIlIIII = 0;
        final ExecutorService lllllllllllllllllIIIIIIIllIIllll = this.configuration.getExecutorService();
        final List<Future<?>> lllllllllllllllllIIIIIIIllIIlllI = new ArrayList<Future<?>>();
        for (final URL lllllllllllllllllIIIIIIIllIlIlIl : this.configuration.getUrls()) {
            try {
                if (lllllllllllllllllIIIIIIIllIIllll != null) {
                    final URL lllllllllllllllllIIIIIIIlIIlllll;
                    lllllllllllllllllIIIIIIIllIIlllI.add(lllllllllllllllllIIIIIIIllIIllll.submit(() -> {
                        if (Reflections.log != null) {
                            Reflections.log.debug("[{}] scanning {}", (Object)Thread.currentThread().toString(), (Object)lllllllllllllllllIIIIIIIlIIlllll);
                        }
                        this.scan(lllllllllllllllllIIIIIIIlIIlllll);
                        return;
                    }));
                }
                else {
                    this.scan(lllllllllllllllllIIIIIIIllIlIlIl);
                }
                ++lllllllllllllllllIIIIIIIllIlIIII;
            }
            catch (ReflectionsException lllllllllllllllllIIIIIIIllIlIllI) {
                if (Reflections.log == null) {
                    continue;
                }
                Reflections.log.warn("could not create Vfs.Dir from url. ignoring the exception and continuing", (Throwable)lllllllllllllllllIIIIIIIllIlIllI);
            }
        }
        if (lllllllllllllllllIIIIIIIllIIllll != null) {
            for (final Future lllllllllllllllllIIIIIIIllIlIIll : lllllllllllllllllIIIIIIIllIIlllI) {
                try {
                    lllllllllllllllllIIIIIIIllIlIIll.get();
                }
                catch (Exception lllllllllllllllllIIIIIIIllIlIlII) {
                    throw new RuntimeException(lllllllllllllllllIIIIIIIllIlIlII);
                }
            }
        }
        if (lllllllllllllllllIIIIIIIllIIllll != null) {
            lllllllllllllllllIIIIIIIllIIllll.shutdown();
        }
        if (Reflections.log != null) {
            Reflections.log.info(String.format("Reflections took %d ms to scan %d urls, producing %s %s", System.currentTimeMillis() - lllllllllllllllllIIIIIIIllIlIIIl, lllllllllllllllllIIIIIIIllIlIIII, producingDescription(this.store), (lllllllllllllllllIIIIIIIllIIllll instanceof ThreadPoolExecutor) ? String.format("[using %d cores]", ((ThreadPoolExecutor)lllllllllllllllllIIIIIIIllIIllll).getMaximumPoolSize()) : ""));
        }
    }
    
    public Set<Class<?>> getTypesAnnotatedWith(final Annotation lllllllllllllllllIIIIIIIIIIlIIlI) {
        return this.getTypesAnnotatedWith(lllllllllllllllllIIIIIIIIIIlIIlI, false);
    }
    
    public Set<Constructor> getConstructorsWithAnyParamAnnotated(final Class<? extends Annotation> llllllllllllllllIllllllllIllIlII) {
        return Utils.getConstructorsFromDescriptors(this.store.get(MethodParameterScanner.class, llllllllllllllllIllllllllIllIlII.getName()), this.loaders());
    }
    
    public Set<Field> getFieldsAnnotatedWith(final Annotation llllllllllllllllIllllllllIlIIlII) {
        return Utils.filter(this.getFieldsAnnotatedWith(llllllllllllllllIllllllllIlIIlII.annotationType()), ReflectionUtils.withAnnotation(llllllllllllllllIllllllllIlIIlII));
    }
    
    public Set<String> getResources(final Pattern llllllllllllllllIllllllllIIlIIll) {
        return this.getResources(llllllllllllllllIlllllllIlIIlIlI -> llllllllllllllllIllllllllIIlIIll.matcher(llllllllllllllllIlllllllIlIIlIlI).matches());
    }
    
    public Set<String> getAllTypes() {
        final Set<String> llllllllllllllllIlllllllIllIlIll = new HashSet<String>(this.store.getAll(SubTypesScanner.class, Object.class.getName()));
        if (llllllllllllllllIlllllllIllIlIll.isEmpty()) {
            throw new ReflectionsException("Couldn't find subtypes of Object. Make sure SubTypesScanner initialized to include Object class - new SubTypesScanner(false)");
        }
        return llllllllllllllllIlllllllIllIlIll;
    }
    
    public static Reflections collect(final String lllllllllllllllllIIIIIIIIllllIll, final Predicate<String> lllllllllllllllllIIIIIIIIllllIlI, final Serializer... lllllllllllllllllIIIIIIIlIIIIIIl) {
        final Serializer lllllllllllllllllIIIIIIIlIIIIIII = (lllllllllllllllllIIIIIIIlIIIIIIl != null && lllllllllllllllllIIIIIIIlIIIIIIl.length == 1) ? lllllllllllllllllIIIIIIIlIIIIIIl[0] : new XmlSerializer();
        final Collection<URL> lllllllllllllllllIIIIIIIIlllllll = ClasspathHelper.forPackage(lllllllllllllllllIIIIIIIIllllIll, new ClassLoader[0]);
        if (lllllllllllllllllIIIIIIIIlllllll.isEmpty()) {
            return null;
        }
        final long lllllllllllllllllIIIIIIIIllllllI = System.currentTimeMillis();
        final Reflections lllllllllllllllllIIIIIIIIlllllIl = new Reflections();
        final Iterable<Vfs.File> lllllllllllllllllIIIIIIIIlllllII = Vfs.findFiles(lllllllllllllllllIIIIIIIIlllllll, lllllllllllllllllIIIIIIIIllllIll, lllllllllllllllllIIIIIIIIllllIlI);
        for (final Vfs.File lllllllllllllllllIIIIIIIlIIIIlII : lllllllllllllllllIIIIIIIIlllllII) {
            InputStream lllllllllllllllllIIIIIIIlIIIIlIl = null;
            try {
                lllllllllllllllllIIIIIIIlIIIIlIl = lllllllllllllllllIIIIIIIlIIIIlII.openInputStream();
                lllllllllllllllllIIIIIIIIlllllIl.merge(lllllllllllllllllIIIIIIIlIIIIIII.read(lllllllllllllllllIIIIIIIlIIIIlIl));
            }
            catch (IOException lllllllllllllllllIIIIIIIlIIIIllI) {
                throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not merge ").append(lllllllllllllllllIIIIIIIlIIIIlII)), lllllllllllllllllIIIIIIIlIIIIllI);
            }
            finally {
                Utils.close(lllllllllllllllllIIIIIIIlIIIIlIl);
            }
        }
        if (Reflections.log != null) {
            Reflections.log.info(String.format("Reflections took %d ms to collect %d url, producing %s", System.currentTimeMillis() - lllllllllllllllllIIIIIIIIllllllI, lllllllllllllllllIIIIIIIIlllllll.size(), producingDescription(lllllllllllllllllIIIIIIIIlllllIl.store)));
        }
        return lllllllllllllllllIIIIIIIIlllllIl;
    }
    
    public Set<Method> getMethodsWithAnyParamAnnotated(final Class<? extends Annotation> llllllllllllllllIlllllllllIlIlII) {
        return Utils.getMethodsFromDescriptors(this.store.get(MethodParameterScanner.class, llllllllllllllllIlllllllllIlIlII.getName()), this.loaders());
    }
    
    public File save(final String llllllllllllllllIlllllllIlIlllll) {
        return this.save(llllllllllllllllIlllllllIlIlllll, this.configuration.getSerializer());
    }
    
    public Set<Constructor> getConstructorsAnnotatedWith(final Annotation llllllllllllllllIlllllllllIIIIII) {
        return (Set<Constructor>)Utils.filter(this.getConstructorsAnnotatedWith(llllllllllllllllIlllllllllIIIIII.annotationType()), ReflectionUtils.withAnnotation(llllllllllllllllIlllllllllIIIIII));
    }
    
    public List<String> getMethodParamNames(final Method llllllllllllllllIllllllllIIIlIll) {
        final Set<String> llllllllllllllllIllllllllIIIllIl = this.store.get(MethodParameterNamesScanner.class, Utils.name(llllllllllllllllIllllllllIIIlIll));
        return (llllllllllllllllIllllllllIIIllIl.size() == 1) ? Arrays.asList(llllllllllllllllIllllllllIIIllIl.iterator().next().split(", ")) : Collections.emptyList();
    }
    
    static {
        Reflections.log = Utils.findLogger(Reflections.class);
    }
    
    public Set<Constructor> getConstructorsAnnotatedWith(final Class<? extends Annotation> llllllllllllllllIlllllllllIIIllI) {
        return Utils.getConstructorsFromDescriptors(this.store.get(MethodAnnotationsScanner.class, llllllllllllllllIlllllllllIIIllI.getName()), this.loaders());
    }
}
