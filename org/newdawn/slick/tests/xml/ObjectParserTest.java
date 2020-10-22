package org.newdawn.slick.tests.xml;

import org.newdawn.slick.util.xml.*;

public class ObjectParserTest
{
    public static void main(final String[] lllllllllllllllllIIlllIllllIlIlI) throws SlickXMLException {
        final ObjectTreeParser lllllllllllllllllIIlllIllllIlIIl = new ObjectTreeParser("org.newdawn.slick.tests.xml");
        lllllllllllllllllIIlllIllllIlIIl.addElementMapping("Bag", ItemContainer.class);
        final GameData lllllllllllllllllIIlllIllllIlIII = (GameData)lllllllllllllllllIIlllIllllIlIIl.parse("testdata/objxmltest.xml");
        lllllllllllllllllIIlllIllllIlIII.dump("");
    }
}
