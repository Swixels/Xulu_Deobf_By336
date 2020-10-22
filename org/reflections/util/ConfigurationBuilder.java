package org.reflections.util;

import java.net.*;
import java.util.function.*;
import org.reflections.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
import org.reflections.scanners.*;
import java.util.stream.*;
import org.reflections.serializers.*;
import org.reflections.adapters.*;
import java.util.*;

public class ConfigurationBuilder implements Configuration
{
    private /* synthetic */ ClassLoader[] classLoaders;
    private /* synthetic */ boolean expandSuperTypes;
    protected /* synthetic */ MetadataAdapter metadataAdapter;
    private /* synthetic */ Set<URL> urls;
    private /* synthetic */ Predicate<String> inputsFilter;
    private /* synthetic */ Serializer serializer;
    private /* synthetic */ ExecutorService executorService;
    private /* synthetic */ Set<Scanner> scanners;
    
    @Override
    public Set<URL> getUrls() {
        return this.urls;
    }
    
    public static ConfigurationBuilder build(final Object... llIIlIIlIIllIll) {
        final ConfigurationBuilder llIIlIIlIlIIIll = new ConfigurationBuilder();
        final List<Object> llIIlIIlIlIIIlI = new ArrayList<Object>();
        if (llIIlIIlIIllIll != null) {
            final double llIIlIIlIIlIlll = llIIlIIlIIllIll.length;
            for (char llIIlIIlIIlIllI = '\0'; llIIlIIlIIlIllI < llIIlIIlIIlIlll; ++llIIlIIlIIlIllI) {
                final Object llIIlIIlIlIIlll = llIIlIIlIIllIll[llIIlIIlIIlIllI];
                if (llIIlIIlIlIIlll != null) {
                    if (llIIlIIlIlIIlll.getClass().isArray()) {
                        final float llIIlIIlIIlIlII = (float)llIIlIIlIlIIlll;
                        final int length = llIIlIIlIIlIlII.length;
                        for (char llIIlIIlIIlIIlI = '\0'; llIIlIIlIIlIIlI < length; ++llIIlIIlIIlIIlI) {
                            final Object llIIlIIlIlIlIIl = llIIlIIlIIlIlII[llIIlIIlIIlIIlI];
                            if (llIIlIIlIlIlIIl != null) {
                                llIIlIIlIlIIIlI.add(llIIlIIlIlIlIIl);
                            }
                        }
                    }
                    else if (llIIlIIlIlIIlll instanceof Iterable) {
                        for (final Object llIIlIIlIlIlIII : (Iterable)llIIlIIlIlIIlll) {
                            if (llIIlIIlIlIlIII != null) {
                                llIIlIIlIlIIIlI.add(llIIlIIlIlIlIII);
                            }
                        }
                    }
                    else {
                        llIIlIIlIlIIIlI.add(llIIlIIlIlIIlll);
                    }
                }
            }
        }
        final List<ClassLoader> llIIlIIlIlIIIII = new ArrayList<ClassLoader>();
        final double llIIlIIlIIlIlll = (double)llIIlIIlIlIIIlI.iterator();
        while (((Iterator)llIIlIIlIIlIlll).hasNext()) {
            final Object llIIlIIlIlIIllI = ((Iterator<Object>)llIIlIIlIIlIlll).next();
            if (llIIlIIlIlIIllI instanceof ClassLoader) {
                llIIlIIlIlIIIII.add((ClassLoader)llIIlIIlIlIIllI);
            }
        }
        final ClassLoader[] llIIlIIlIIlllll = (ClassLoader[])(llIIlIIlIlIIIII.isEmpty() ? null : ((ClassLoader[])llIIlIIlIlIIIII.toArray(new ClassLoader[llIIlIIlIlIIIII.size()])));
        final FilterBuilder llIIlIIlIIllllI = new FilterBuilder();
        final List<Scanner> llIIlIIlIIlllIl = new ArrayList<Scanner>();
        final float llIIlIIlIIlIlII = (float)llIIlIIlIlIIIlI.iterator();
        while (((Iterator)llIIlIIlIIlIlII).hasNext()) {
            final Object llIIlIIlIlIIlIl = ((Iterator<Object>)llIIlIIlIIlIlII).next();
            if (llIIlIIlIlIIlIl instanceof String) {
                llIIlIIlIlIIIll.addUrls(ClasspathHelper.forPackage((String)llIIlIIlIlIIlIl, llIIlIIlIIlllll));
                llIIlIIlIIllllI.includePackage((String)llIIlIIlIlIIlIl);
            }
            else if (llIIlIIlIlIIlIl instanceof Class) {
                if (Scanner.class.isAssignableFrom((Class<?>)llIIlIIlIlIIlIl)) {
                    try {
                        llIIlIIlIlIIIll.addScanners(((Class)llIIlIIlIlIIlIl).newInstance());
                    }
                    catch (Exception ex) {}
                }
                llIIlIIlIlIIIll.addUrls(ClasspathHelper.forClass((Class<?>)llIIlIIlIlIIlIl, llIIlIIlIIlllll));
                llIIlIIlIIllllI.includePackage((Class<?>)llIIlIIlIlIIlIl);
            }
            else if (llIIlIIlIlIIlIl instanceof Scanner) {
                llIIlIIlIIlllIl.add((Scanner)llIIlIIlIlIIlIl);
            }
            else if (llIIlIIlIlIIlIl instanceof URL) {
                llIIlIIlIlIIIll.addUrls((URL)llIIlIIlIlIIlIl);
            }
            else {
                if (llIIlIIlIlIIlIl instanceof ClassLoader) {
                    continue;
                }
                if (llIIlIIlIlIIlIl instanceof Predicate) {
                    llIIlIIlIIllllI.add((Predicate<String>)llIIlIIlIlIIlIl);
                }
                else if (llIIlIIlIlIIlIl instanceof ExecutorService) {
                    llIIlIIlIlIIIll.setExecutorService((ExecutorService)llIIlIIlIlIIlIl);
                }
                else {
                    if (Reflections.log != null) {
                        throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not use param ").append(llIIlIIlIlIIlIl)));
                    }
                    continue;
                }
            }
        }
        if (llIIlIIlIlIIIll.getUrls().isEmpty()) {
            if (llIIlIIlIIlllll != null) {
                llIIlIIlIlIIIll.addUrls(ClasspathHelper.forClassLoader(llIIlIIlIIlllll));
            }
            else {
                llIIlIIlIlIIIll.addUrls(ClasspathHelper.forClassLoader());
            }
        }
        llIIlIIlIlIIIll.filterInputsBy(llIIlIIlIIllllI);
        if (!llIIlIIlIIlllIl.isEmpty()) {
            llIIlIIlIlIIIll.setScanners((Scanner[])llIIlIIlIIlllIl.toArray(new Scanner[llIIlIIlIIlllIl.size()]));
        }
        if (!llIIlIIlIlIIIII.isEmpty()) {
            llIIlIIlIlIIIll.addClassLoaders(llIIlIIlIlIIIII);
        }
        return llIIlIIlIlIIIll;
    }
    
    public ConfigurationBuilder setSerializer(final Serializer llIIIllllIIIlIl) {
        this.serializer = llIIIllllIIIlIl;
        return this;
    }
    
    @Override
    public boolean shouldExpandSuperTypes() {
        return this.expandSuperTypes;
    }
    
    public ConfigurationBuilder addUrls(final Collection<URL> llIIlIIIIllllII) {
        this.urls.addAll(llIIlIIIIllllII);
        return this;
    }
    
    public ConfigurationBuilder useParallelExecutor(final int llIIIllllIlllll) {
        final ThreadFactory llIIIllllIllllI = new ThreadFactory() {
            private final /* synthetic */ AtomicInteger threadNumber = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable lIIIlIIIllllIl) {
                final Thread lIIIlIIIllllll = new Thread(lIIIlIIIllllIl);
                lIIIlIIIllllll.setName(String.valueOf(new StringBuilder().append("org.reflections-scanner-").append(this.threadNumber.getAndIncrement())));
                lIIIlIIIllllll.setDaemon(true);
                return lIIIlIIIllllll;
            }
        };
        this.setExecutorService(Executors.newFixedThreadPool(llIIIllllIlllll, llIIIllllIllllI));
        return this;
    }
    
    public ConfigurationBuilder() {
        this.expandSuperTypes = true;
        this.scanners = new HashSet<Scanner>(Arrays.asList(new TypeAnnotationsScanner(), new SubTypesScanner()));
        this.urls = new HashSet<URL>();
    }
    
    @Override
    public Predicate<String> getInputsFilter() {
        return this.inputsFilter;
    }
    
    public ConfigurationBuilder setExecutorService(final ExecutorService llIIIllllllIllI) {
        this.executorService = llIIIllllllIllI;
        return this;
    }
    
    public ConfigurationBuilder setScanners(final Scanner... llIIlIIIllIIllI) {
        this.scanners.clear();
        return this.addScanners(llIIlIIIllIIllI);
    }
    
    public ConfigurationBuilder setMetadataAdapter(final MetadataAdapter llIIlIIIIIlIIll) {
        this.metadataAdapter = llIIlIIIIIlIIll;
        return this;
    }
    
    public ConfigurationBuilder setExpandSuperTypes(final boolean llIIIlllIlllIIl) {
        this.expandSuperTypes = llIIIlllIlllIIl;
        return this;
    }
    
    public ConfigurationBuilder addClassLoaders(final Collection<ClassLoader> llIIIlllIIIlIII) {
        return this.addClassLoaders((ClassLoader[])llIIIlllIIIlIII.toArray(new ClassLoader[llIIIlllIIIlIII.size()]));
    }
    
    @Override
    public Set<Scanner> getScanners() {
        return this.scanners;
    }
    
    public void setClassLoaders(final ClassLoader[] llIIIlllIlIllIl) {
        this.classLoaders = llIIIlllIlIllIl;
    }
    
    public ConfigurationBuilder filterInputsBy(final Predicate<String> llIIIllllllllll) {
        this.inputsFilter = llIIIllllllllll;
        return this;
    }
    
    @Override
    public ExecutorService getExecutorService() {
        return this.executorService;
    }
    
    public ConfigurationBuilder addClassLoaders(final ClassLoader... llIIIlllIIlIIlI) {
        this.classLoaders = ((this.classLoaders == null) ? llIIIlllIIlIIlI : Stream.concat(Stream.concat((Stream<?>)Arrays.stream((T[])this.classLoaders), (Stream<?>)Arrays.stream((T[])llIIIlllIIlIIlI)), (Stream<?>)Stream.of((T)ClassLoader.class)).distinct().toArray(ClassLoader[]::new));
        return this;
    }
    
    public ConfigurationBuilder setUrls(final URL... llIIlIIIlIIlIll) {
        this.urls = new HashSet<URL>(Arrays.asList(llIIlIIIlIIlIll));
        return this;
    }
    
    public ConfigurationBuilder forPackages(final String... llIIlIIIllllllI) {
        final float llIIlIIIllllIll = (Object)llIIlIIIllllllI;
        final int llIIlIIIllllIIl = llIIlIIIllllIll.length;
        for (byte llIIlIIIllllIII = 0; llIIlIIIllllIII < llIIlIIIllllIIl; ++llIIlIIIllllIII) {
            final String llIIlIIlIIIIIII = llIIlIIIllllIll[llIIlIIIllllIII];
            this.addUrls(ClasspathHelper.forPackage(llIIlIIlIIIIIII, new ClassLoader[0]));
        }
        return this;
    }
    
    public void setInputsFilter(final Predicate<String> llIIlIIIIIIIlIl) {
        this.inputsFilter = llIIlIIIIIIIlIl;
    }
    
    public ConfigurationBuilder useParallelExecutor() {
        return this.useParallelExecutor(Runtime.getRuntime().availableProcessors());
    }
    
    @Override
    public Serializer getSerializer() {
        return (this.serializer != null) ? this.serializer : (this.serializer = new XmlSerializer());
    }
    
    public ConfigurationBuilder addScanners(final Scanner... llIIlIIIlIlllII) {
        this.scanners.addAll(Arrays.asList(llIIlIIIlIlllII));
        return this;
    }
    
    @Override
    public ClassLoader[] getClassLoaders() {
        return this.classLoaders;
    }
    
    public ConfigurationBuilder addUrls(final URL... llIIlIIIIlIllII) {
        this.urls.addAll(new HashSet<URL>(Arrays.asList(llIIlIIIIlIllII)));
        return this;
    }
    
    public ConfigurationBuilder setUrls(final Collection<URL> llIIlIIIlIlIlIl) {
        this.urls = new HashSet<URL>(llIIlIIIlIlIlIl);
        return this;
    }
    
    public ConfigurationBuilder addClassLoader(final ClassLoader llIIIlllIIlllIl) {
        return this.addClassLoaders(llIIIlllIIlllIl);
    }
    
    @Override
    public MetadataAdapter getMetadataAdapter() {
        if (this.metadataAdapter != null) {
            return this.metadataAdapter;
        }
        try {
            final JavassistAdapter metadataAdapter = new JavassistAdapter();
            this.metadataAdapter = metadataAdapter;
            return metadataAdapter;
        }
        catch (Throwable llIIlIIIIlIIIll) {
            if (Reflections.log != null) {
                Reflections.log.warn("could not create JavassistAdapter, using JavaReflectionAdapter", llIIlIIIIlIIIll);
            }
            final JavaReflectionAdapter metadataAdapter2 = new JavaReflectionAdapter();
            this.metadataAdapter = metadataAdapter2;
            return metadataAdapter2;
        }
    }
}
