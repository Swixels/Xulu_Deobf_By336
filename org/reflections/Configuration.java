package org.reflections;

import java.util.function.*;
import org.reflections.adapters.*;
import java.util.concurrent.*;
import java.util.*;
import java.net.*;
import org.reflections.scanners.*;
import org.reflections.serializers.*;

public interface Configuration
{
    Predicate<String> getInputsFilter();
    
    MetadataAdapter getMetadataAdapter();
    
    ExecutorService getExecutorService();
    
    Set<URL> getUrls();
    
    ClassLoader[] getClassLoaders();
    
    Set<Scanner> getScanners();
    
    boolean shouldExpandSuperTypes();
    
    Serializer getSerializer();
}
