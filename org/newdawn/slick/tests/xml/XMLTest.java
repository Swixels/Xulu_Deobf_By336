package org.newdawn.slick.tests.xml;

import org.newdawn.slick.*;
import org.newdawn.slick.util.xml.*;

public class XMLTest
{
    private static void fail(final String llllllllllllllllIlIlllIlllIlIllI) {
        throw new RuntimeException(llllllllllllllllIlIlllIlllIlIllI);
    }
    
    private static void assertEquals(final Object llllllllllllllllIlIlllIlllIIIIlI, final Object llllllllllllllllIlIlllIlllIIIIll) {
        if (!llllllllllllllllIlIlllIlllIIIIlI.equals(llllllllllllllllIlIlllIlllIIIIll)) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("TEST FAILS: ").append(llllllllllllllllIlIlllIlllIIIIlI).append(" should be ").append(llllllllllllllllIlIlllIlllIIIIll)));
        }
    }
    
    private static void assertNotNull(final Object llllllllllllllllIlIlllIlllIlIlII) {
        if (llllllllllllllllIlIlllIlllIlIlII == null) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("TEST FAILS: ").append(llllllllllllllllIlIlllIlllIlIlII).append(" must not be null")));
        }
    }
    
    private static void assertEquals(final int llllllllllllllllIlIlllIlllIIlIlI, final int llllllllllllllllIlIlllIlllIIIlll) {
        if (llllllllllllllllIlIlllIlllIIlIlI != llllllllllllllllIlIlllIlllIIIlll) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("TEST FAILS: ").append(llllllllllllllllIlIlllIlllIIlIlI).append(" should be ").append(llllllllllllllllIlIlllIlllIIIlll)));
        }
    }
    
    public static void main(final String[] llllllllllllllllIlIlllIllIIlllII) throws SlickException {
        final XMLParser llllllllllllllllIlIlllIllIIllIll = new XMLParser();
        final XMLElement llllllllllllllllIlIlllIllIIllIIl = llllllllllllllllIlIlllIllIIllIll.parse("testdata/test.xml");
        assertEquals(llllllllllllllllIlIlllIllIIllIIl.getName(), "testRoot");
        System.out.println(llllllllllllllllIlIlllIllIIllIIl);
        assertNotNull(llllllllllllllllIlIlllIllIIllIIl.getChildrenByName("simple").get(0).getContent());
        System.out.println(llllllllllllllllIlIlllIllIIllIIl.getChildrenByName("simple").get(0).getContent());
        final XMLElement llllllllllllllllIlIlllIllIIlIlll = llllllllllllllllIlIlllIllIIllIIl.getChildrenByName("parent").get(0);
        assertEquals(llllllllllllllllIlIlllIllIIlIlll.getChildrenByName("grandchild").size(), 0);
        assertEquals(llllllllllllllllIlIlllIllIIlIlll.getChildrenByName("child").size(), 2);
        assertEquals(llllllllllllllllIlIlllIllIIlIlll.getChildrenByName("child").get(0).getChildren().size(), 2);
        final XMLElement llllllllllllllllIlIlllIllIIlIllI = llllllllllllllllIlIlllIllIIlIlll.getChildrenByName("child").get(0).getChildren().get(0);
        final String llllllllllllllllIlIlllIllIIlIlIl = llllllllllllllllIlIlllIllIIlIllI.getAttribute("name");
        final String llllllllllllllllIlIlllIllIIlIlII = llllllllllllllllIlIlllIllIIlIllI.getAttribute("nothere", "defaultValue");
        final int llllllllllllllllIlIlllIllIIlIIlI = llllllllllllllllIlIlllIllIIlIllI.getIntAttribute("age");
        assertEquals(llllllllllllllllIlIlllIllIIlIlIl, "bob");
        assertEquals(llllllllllllllllIlIlllIllIIlIlII, "defaultValue");
        assertEquals(llllllllllllllllIlIlllIllIIlIIlI, 1);
        final XMLElement llllllllllllllllIlIlllIllIIlIIII = llllllllllllllllIlIlllIllIIllIIl.getChildrenByName("other").get(0);
        final float llllllllllllllllIlIlllIllIIIllll = (float)llllllllllllllllIlIlllIllIIlIIII.getDoubleAttribute("x");
        final float llllllllllllllllIlIlllIllIIIlllI = (float)llllllllllllllllIlIlllIllIIlIIII.getDoubleAttribute("y", 1.0);
        float llllllllllllllllIlIlllIllIIIllIl = (float)llllllllllllllllIlIlllIllIIlIIII.getDoubleAttribute("z", 83.0);
        assertEquals(llllllllllllllllIlIlllIllIIIllll, 5.3f);
        assertEquals(llllllllllllllllIlIlllIllIIIlllI, 5.4f);
        assertEquals(llllllllllllllllIlIlllIllIIIllIl, 83.0f);
        try {
            llllllllllllllllIlIlllIllIIIllIl = (float)llllllllllllllllIlIlllIllIIlIIII.getDoubleAttribute("z");
            fail("Attribute z as a double should fail");
        }
        catch (SlickException ex) {}
    }
    
    private static void assertEquals(final float llllllllllllllllIlIlllIlllIlIIII, final float llllllllllllllllIlIlllIlllIIllll) {
        if (llllllllllllllllIlIlllIlllIlIIII != llllllllllllllllIlIlllIlllIIllll) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("TEST FAILS: ").append(llllllllllllllllIlIlllIlllIlIIII).append(" should be ").append(llllllllllllllllIlIlllIlllIIllll)));
        }
    }
}
