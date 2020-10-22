package org.newdawn.slick.util.xml;

import java.util.*;
import org.newdawn.slick.util.*;
import java.lang.reflect.*;
import java.io.*;

public class ObjectTreeParser
{
    private /* synthetic */ String addMethod;
    private /* synthetic */ ArrayList ignored;
    private /* synthetic */ String defaultPackage;
    private /* synthetic */ HashMap nameToClass;
    
    public void addIgnoredElement(final String llIlIIIIIIllll) {
        this.ignored.add(llIlIIIIIIllll);
    }
    
    private Object traverse(final XMLElement llIIlllIIllIll, Object llIIlllIIllIlI) throws SlickXMLException {
        final String llIIlllIIllllI = llIIlllIIllIll.getName();
        if (this.ignored.contains(llIIlllIIllllI)) {
            return null;
        }
        Class llIIlllIIlllIl = null;
        if (llIIlllIIllIlI == null) {
            final Class llIIlllIllIlIl = this.getClassForElementName(llIIlllIIllllI);
        }
        else {
            llIIlllIIlllIl = llIIlllIIllIlI.getClass();
        }
        if (llIIlllIIlllIl == null) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Unable to map element ").append(llIIlllIIllllI).append(" to a class, define the mapping")));
        }
        try {
            if (llIIlllIIllIlI == null) {
                llIIlllIIllIlI = llIIlllIIlllIl.newInstance();
                final Method llIIlllIllIlII = this.getMethod(llIIlllIIlllIl, "setXMLElementName", new Class[] { String.class });
                if (llIIlllIllIlII != null) {
                    this.invoke(llIIlllIllIlII, llIIlllIIllIlI, new Object[] { llIIlllIIllllI });
                }
                final Method llIIlllIllIIll = this.getMethod(llIIlllIIlllIl, "setXMLElementContent", new Class[] { String.class });
                if (llIIlllIllIIll != null) {
                    this.invoke(llIIlllIllIIll, llIIlllIIllIlI, new Object[] { llIIlllIIllIll.getContent() });
                }
            }
            final String[] llIIlllIlIIlIl = llIIlllIIllIll.getAttributeNames();
            for (int llIIlllIlIlIll = 0; llIIlllIlIlIll < llIIlllIlIIlIl.length; ++llIIlllIlIlIll) {
                final String llIIlllIlIllIl = String.valueOf(new StringBuilder().append("set").append(llIIlllIlIIlIl[llIIlllIlIlIll]));
                final Method llIIlllIlIllII = this.findMethod(llIIlllIIlllIl, llIIlllIlIllIl);
                if (llIIlllIlIllII == null) {
                    final Field llIIlllIllIIII = this.findField(llIIlllIIlllIl, llIIlllIlIIlIl[llIIlllIlIlIll]);
                    if (llIIlllIllIIII != null) {
                        final String llIIlllIllIIlI = llIIlllIIllIll.getAttribute(llIIlllIlIIlIl[llIIlllIlIlIll]);
                        final Object llIIlllIllIIIl = this.typeValue(llIIlllIllIIlI, llIIlllIllIIII.getType());
                        this.setField(llIIlllIllIIII, llIIlllIIllIlI, llIIlllIllIIIl);
                    }
                    else {
                        Log.info(String.valueOf(new StringBuilder().append("Unable to find property on: ").append(llIIlllIIlllIl).append(" for attribute: ").append(llIIlllIlIIlIl[llIIlllIlIlIll])));
                    }
                }
                else {
                    final String llIIlllIlIllll = llIIlllIIllIll.getAttribute(llIIlllIlIIlIl[llIIlllIlIlIll]);
                    final Object llIIlllIlIlllI = this.typeValue(llIIlllIlIllll, llIIlllIlIllII.getParameterTypes()[0]);
                    this.invoke(llIIlllIlIllII, llIIlllIIllIlI, new Object[] { llIIlllIlIlllI });
                }
            }
            final XMLElementList llIIlllIlIIlII = llIIlllIIllIll.getChildren();
            for (int llIIlllIlIIllI = 0; llIIlllIlIIllI < llIIlllIlIIlII.size(); ++llIIlllIlIIllI) {
                final XMLElement llIIlllIlIlIII = llIIlllIlIIlII.get(llIIlllIlIIllI);
                final Object llIIlllIlIIlll = this.traverse(llIIlllIlIlIII);
                if (llIIlllIlIIlll != null) {
                    final String llIIlllIlIlIlI = this.addMethod;
                    final Method llIIlllIlIlIIl = this.findMethod(llIIlllIIlllIl, llIIlllIlIlIlI, llIIlllIlIIlll.getClass());
                    if (llIIlllIlIlIIl == null) {
                        Log.info(String.valueOf(new StringBuilder().append("Unable to find method to add: ").append(llIIlllIlIIlll).append(" to ").append(llIIlllIIlllIl)));
                    }
                    else {
                        this.invoke(llIIlllIlIlIIl, llIIlllIIllIlI, new Object[] { llIIlllIlIIlll });
                    }
                }
            }
            return llIIlllIIllIlI;
        }
        catch (InstantiationException llIIlllIlIIIll) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Unable to instance ").append(llIIlllIIlllIl).append(" for element ").append(llIIlllIIllllI).append(", no zero parameter constructor?")), llIIlllIlIIIll);
        }
        catch (IllegalAccessException llIIlllIlIIIlI) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Unable to instance ").append(llIIlllIIlllIl).append(" for element ").append(llIIlllIIllllI).append(", no zero parameter constructor?")), llIIlllIlIIIlI);
        }
    }
    
    private Class getClassForElementName(final String llIIllllIIlIlI) {
        final Class llIIllllIIllII = this.nameToClass.get(llIIllllIIlIlI);
        if (llIIllllIIllII != null) {
            return llIIllllIIllII;
        }
        if (this.defaultPackage != null) {
            try {
                return Class.forName(String.valueOf(new StringBuilder().append(this.defaultPackage).append(".").append(llIIllllIIlIlI)));
            }
            catch (ClassNotFoundException ex) {}
        }
        return null;
    }
    
    private Method findMethod(final Class llIIllIllIIllI, final String llIIllIllIlIII) {
        final Method[] llIIllIllIIlll = llIIllIllIIllI.getDeclaredMethods();
        for (int llIIllIllIlIll = 0; llIIllIllIlIll < llIIllIllIIlll.length; ++llIIllIllIlIll) {
            if (llIIllIllIIlll[llIIllIllIlIll].getName().equalsIgnoreCase(llIIllIllIlIII)) {
                final Method llIIllIllIllIl = llIIllIllIIlll[llIIllIllIlIll];
                final Class[] llIIllIllIllII = llIIllIllIllIl.getParameterTypes();
                if (llIIllIllIllII.length == 1) {
                    return llIIllIllIllIl;
                }
            }
        }
        return null;
    }
    
    public Object parseOnto(final String llIIlllllIIllI, final Object llIIlllllIlIII) throws SlickXMLException {
        return this.parseOnto(llIIlllllIIllI, ResourceLoader.getResourceAsStream(llIIlllllIIllI), llIIlllllIlIII);
    }
    
    public void setAddMethodName(final String llIlIIIIIIlIIl) {
        this.addMethod = llIlIIIIIIlIIl;
    }
    
    private Field findField(final Class llIIllIllllIlI, final String llIIllIlllIllI) {
        final Field[] llIIllIllllIII = llIIllIllllIlI.getDeclaredFields();
        for (int llIIllIlllllII = 0; llIIllIlllllII < llIIllIllllIII.length; ++llIIllIlllllII) {
            if (llIIllIllllIII[llIIllIlllllII].getName().equalsIgnoreCase(llIIllIlllIllI)) {
                if (llIIllIllllIII[llIIllIlllllII].getType().isPrimitive()) {
                    return llIIllIllllIII[llIIllIlllllII];
                }
                if (llIIllIllllIII[llIIllIlllllII].getType() == String.class) {
                    return llIIllIllllIII[llIIllIlllllII];
                }
            }
        }
        return null;
    }
    
    public ObjectTreeParser(final String llIlIIIIIllllI) {
        this.nameToClass = new HashMap();
        this.ignored = new ArrayList();
        this.addMethod = "add";
        this.defaultPackage = llIlIIIIIllllI;
    }
    
    private void invoke(final Method llIIllIIllIIIl, final Object llIIllIIllIIII, final Object[] llIIllIIlIllII) throws SlickXMLException {
        try {
            llIIllIIllIIIl.setAccessible(true);
            llIIllIIllIIIl.invoke(llIIllIIllIIII, llIIllIIlIllII);
        }
        catch (IllegalArgumentException llIIllIIllIlIl) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Failed to invoke: ").append(llIIllIIllIIIl).append(" for an XML attribute, is it valid?")), llIIllIIllIlIl);
        }
        catch (IllegalAccessException llIIllIIllIlII) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Failed to invoke: ").append(llIIllIIllIIIl).append(" for an XML attribute, is it valid?")), llIIllIIllIlII);
        }
        catch (InvocationTargetException llIIllIIllIIll) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Failed to invoke: ").append(llIIllIIllIIIl).append(" for an XML attribute, is it valid?")), llIIllIIllIIll);
        }
        finally {
            llIIllIIllIIIl.setAccessible(false);
        }
    }
    
    public Object parse(final String llIIllllllIIIl, final InputStream llIIllllllIIII) throws SlickXMLException {
        final XMLParser llIIllllllIlII = new XMLParser();
        final XMLElement llIIllllllIIll = llIIllllllIlII.parse(llIIllllllIIIl, llIIllllllIIII);
        return this.traverse(llIIllllllIIll);
    }
    
    public Object parseOnto(final String llIIllllIlIlll, final InputStream llIIllllIlIllI, final Object llIIllllIlIlIl) throws SlickXMLException {
        final XMLParser llIIllllIllIlI = new XMLParser();
        final XMLElement llIIllllIllIIl = llIIllllIllIlI.parse(llIIllllIlIlll, llIIllllIlIllI);
        return this.traverse(llIIllllIllIIl, llIIllllIlIlIl);
    }
    
    private Method findMethod(final Class llIIllIlIlIlIl, final String llIIllIlIlIIII, final Class llIIllIlIlIIll) {
        final Method[] llIIllIlIlIIlI = llIIllIlIlIlIl.getDeclaredMethods();
        for (int llIIllIlIlIlll = 0; llIIllIlIlIlll < llIIllIlIlIIlI.length; ++llIIllIlIlIlll) {
            if (llIIllIlIlIIlI[llIIllIlIlIlll].getName().equalsIgnoreCase(llIIllIlIlIIII)) {
                final Method llIIllIlIllIIl = llIIllIlIlIIlI[llIIllIlIlIlll];
                final Class[] llIIllIlIllIII = llIIllIlIllIIl.getParameterTypes();
                if (llIIllIlIllIII.length == 1 && llIIllIlIllIIl.getParameterTypes()[0].isAssignableFrom(llIIllIlIlIIll)) {
                    return llIIllIlIllIIl;
                }
            }
        }
        return null;
    }
    
    private Object traverse(final XMLElement llIIllllIIIIlI) throws SlickXMLException {
        return this.traverse(llIIllllIIIIlI, null);
    }
    
    public void setDefaultPackage(final String llIlIIIIIIIIll) {
        this.defaultPackage = llIlIIIIIIIIll;
    }
    
    private Object typeValue(final String llIIlllIIIIlll, Class llIIlllIIIIllI) throws SlickXMLException {
        if (llIIlllIIIIllI == String.class) {
            return llIIlllIIIIlll;
        }
        try {
            llIIlllIIIIllI = this.mapPrimitive(llIIlllIIIIllI);
            return llIIlllIIIIllI.getConstructor(String.class).newInstance(llIIlllIIIIlll);
        }
        catch (Exception llIIlllIIIllII) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Failed to convert: ").append(llIIlllIIIIlll).append(" to the expected primitive type: ").append(llIIlllIIIIllI)), llIIlllIIIllII);
        }
    }
    
    public Object parse(final String llIIllllllllll) throws SlickXMLException {
        return this.parse(llIIllllllllll, ResourceLoader.getResourceAsStream(llIIllllllllll));
    }
    
    public ObjectTreeParser() {
        this.nameToClass = new HashMap();
        this.ignored = new ArrayList();
        this.addMethod = "add";
    }
    
    private void setField(final Field llIIllIlIIIIlI, final Object llIIllIlIIIIIl, final Object llIIllIlIIIIII) throws SlickXMLException {
        try {
            llIIllIlIIIIlI.setAccessible(true);
            llIIllIlIIIIlI.set(llIIllIlIIIIIl, llIIllIlIIIIII);
        }
        catch (IllegalArgumentException llIIllIlIIIlIl) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Failed to set: ").append(llIIllIlIIIIlI).append(" for an XML attribute, is it valid?")), llIIllIlIIIlIl);
        }
        catch (IllegalAccessException llIIllIlIIIlII) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Failed to set: ").append(llIIllIlIIIIlI).append(" for an XML attribute, is it valid?")), llIIllIlIIIlII);
        }
        finally {
            llIIllIlIIIIlI.setAccessible(false);
        }
    }
    
    private Method getMethod(final Class llIIllIIlIIIlI, final String llIIllIIlIIIIl, final Class[] llIIllIIlIIIII) {
        try {
            return llIIllIIlIIIlI.getMethod(llIIllIIlIIIIl, (Class[])llIIllIIlIIIII);
        }
        catch (SecurityException llIIllIIlIIlIl) {
            return null;
        }
        catch (NoSuchMethodException llIIllIIlIIlII) {
            return null;
        }
    }
    
    private Class mapPrimitive(final Class llIIlllIIIIIIl) {
        if (llIIlllIIIIIIl == Integer.TYPE) {
            return Integer.class;
        }
        if (llIIlllIIIIIIl == Double.TYPE) {
            return Double.class;
        }
        if (llIIlllIIIIIIl == Float.TYPE) {
            return Float.class;
        }
        if (llIIlllIIIIIIl == Boolean.TYPE) {
            return Boolean.class;
        }
        if (llIIlllIIIIIIl == Long.TYPE) {
            return Long.class;
        }
        throw new RuntimeException(String.valueOf(new StringBuilder().append("Unsupported primitive: ").append(llIIlllIIIIIIl)));
    }
    
    public void addElementMapping(final String llIlIIIIIlIllI, final Class llIlIIIIIlIlIl) {
        this.nameToClass.put(llIlIIIIIlIllI, llIlIIIIIlIlIl);
    }
}
