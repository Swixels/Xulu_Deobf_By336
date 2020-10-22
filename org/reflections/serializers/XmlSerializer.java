package org.reflections.serializers;

import java.io.*;
import org.reflections.util.*;
import org.reflections.*;
import org.dom4j.io.*;
import org.dom4j.*;
import java.lang.reflect.*;

public class XmlSerializer implements Serializer
{
    private Document createDocument(final Reflections lllllllllllllllllIllIllIlIIIllIl) {
        final Store lllllllllllllllllIllIllIlIIIllII = lllllllllllllllllIllIllIlIIIllIl.getStore();
        final Document lllllllllllllllllIllIllIlIIIlIll = DocumentFactory.getInstance().createDocument();
        final Element lllllllllllllllllIllIllIlIIIlIlI = lllllllllllllllllIllIllIlIIIlIll.addElement("Reflections");
        for (final String lllllllllllllllllIllIllIlIIIllll : lllllllllllllllllIllIllIlIIIllII.keySet()) {
            final Element lllllllllllllllllIllIllIlIIlIIII = lllllllllllllllllIllIllIlIIIlIlI.addElement(lllllllllllllllllIllIllIlIIIllll);
            for (final String lllllllllllllllllIllIllIlIIlIIIl : lllllllllllllllllIllIllIlIIIllII.keys(lllllllllllllllllIllIllIlIIIllll)) {
                final Element lllllllllllllllllIllIllIlIIlIIll = lllllllllllllllllIllIllIlIIlIIII.addElement("entry");
                lllllllllllllllllIllIllIlIIlIIll.addElement("key").setText(lllllllllllllllllIllIllIlIIlIIIl);
                final Element lllllllllllllllllIllIllIlIIlIIlI = lllllllllllllllllIllIllIlIIlIIll.addElement("values");
                for (final String lllllllllllllllllIllIllIlIIlIlII : lllllllllllllllllIllIllIlIIIllII.get(lllllllllllllllllIllIllIlIIIllll, lllllllllllllllllIllIllIlIIlIIIl)) {
                    lllllllllllllllllIllIllIlIIlIIlI.addElement("value").setText(lllllllllllllllllIllIllIlIIlIlII);
                }
            }
        }
        return lllllllllllllllllIllIllIlIIIlIll;
    }
    
    @Override
    public File save(final Reflections lllllllllllllllllIllIllIlIllIllI, final String lllllllllllllllllIllIllIlIlllIIl) {
        final File lllllllllllllllllIllIllIlIlllIII = Utils.prepareFile(lllllllllllllllllIllIllIlIlllIIl);
        try {
            final Document lllllllllllllllllIllIllIlIllllll = this.createDocument(lllllllllllllllllIllIllIlIllIllI);
            final XMLWriter lllllllllllllllllIllIllIlIlllllI = new XMLWriter((OutputStream)new FileOutputStream(lllllllllllllllllIllIllIlIlllIII), OutputFormat.createPrettyPrint());
            lllllllllllllllllIllIllIlIlllllI.write(lllllllllllllllllIllIllIlIllllll);
            lllllllllllllllllIllIllIlIlllllI.close();
        }
        catch (IOException lllllllllllllllllIllIllIlIllllIl) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not save to file ").append(lllllllllllllllllIllIllIlIlllIIl)), lllllllllllllllllIllIllIlIllllIl);
        }
        catch (Throwable lllllllllllllllllIllIllIlIllllII) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Could not save to file ").append(lllllllllllllllllIllIllIlIlllIIl).append(". Make sure relevant dependencies exist on classpath.")), lllllllllllllllllIllIllIlIllllII);
        }
        return lllllllllllllllllIllIllIlIlllIII;
    }
    
    @Override
    public String toString(final Reflections lllllllllllllllllIllIllIlIlIlIII) {
        final Document lllllllllllllllllIllIllIlIlIIlll = this.createDocument(lllllllllllllllllIllIllIlIlIlIII);
        try {
            final StringWriter lllllllllllllllllIllIllIlIlIllII = new StringWriter();
            final XMLWriter lllllllllllllllllIllIllIlIlIlIll = new XMLWriter((Writer)lllllllllllllllllIllIllIlIlIllII, OutputFormat.createPrettyPrint());
            lllllllllllllllllIllIllIlIlIlIll.write(lllllllllllllllllIllIllIlIlIIlll);
            lllllllllllllllllIllIllIlIlIlIll.close();
            return lllllllllllllllllIllIllIlIlIllII.toString();
        }
        catch (IOException lllllllllllllllllIllIllIlIlIlIlI) {
            throw new RuntimeException();
        }
    }
    
    @Override
    public Reflections read(final InputStream lllllllllllllllllIllIllIllIlIlIl) {
        Reflections lllllllllllllllllIllIllIllIlIlII;
        try {
            final Constructor<Reflections> lllllllllllllllllIllIllIlllIIlII = Reflections.class.getDeclaredConstructor((Class<?>[])new Class[0]);
            lllllllllllllllllIllIllIlllIIlII.setAccessible(true);
            final Reflections lllllllllllllllllIllIllIlllIIIll = lllllllllllllllllIllIllIlllIIlII.newInstance(new Object[0]);
        }
        catch (Exception lllllllllllllllllIllIllIlllIIIlI) {
            lllllllllllllllllIllIllIllIlIlII = new Reflections(new ConfigurationBuilder());
        }
        try {
            final Document lllllllllllllllllIllIllIllIllIIl = new SAXReader().read(lllllllllllllllllIllIllIllIlIlIl);
            for (final Object lllllllllllllllllIllIllIllIllIlI : lllllllllllllllllIllIllIllIllIIl.getRootElement().elements()) {
                final Element lllllllllllllllllIllIllIllIllIll = (Element)lllllllllllllllllIllIllIllIllIlI;
                for (final Object lllllllllllllllllIllIllIllIlllII : lllllllllllllllllIllIllIllIllIll.elements()) {
                    final Element lllllllllllllllllIllIllIllIlllll = (Element)lllllllllllllllllIllIllIllIlllII;
                    final Element lllllllllllllllllIllIllIllIllllI = lllllllllllllllllIllIllIllIlllll.element("key");
                    final Element lllllllllllllllllIllIllIllIlllIl = lllllllllllllllllIllIllIllIlllll.element("values");
                    for (final Object lllllllllllllllllIllIllIlllIIIII : lllllllllllllllllIllIllIllIlllIl.elements()) {
                        final Element lllllllllllllllllIllIllIlllIIIIl = (Element)lllllllllllllllllIllIllIlllIIIII;
                        lllllllllllllllllIllIllIllIlIlII.getStore().put(lllllllllllllllllIllIllIllIllIll.getName(), lllllllllllllllllIllIllIllIllllI.getText(), lllllllllllllllllIllIllIlllIIIIl.getText());
                    }
                }
            }
        }
        catch (DocumentException lllllllllllllllllIllIllIllIllIII) {
            throw new ReflectionsException("could not read.", (Throwable)lllllllllllllllllIllIllIllIllIII);
        }
        catch (Throwable lllllllllllllllllIllIllIllIlIlll) {
            throw new RuntimeException("Could not read. Make sure relevant dependencies exist on classpath.", lllllllllllllllllIllIllIllIlIlll);
        }
        return lllllllllllllllllIllIllIllIlIlII;
    }
}
