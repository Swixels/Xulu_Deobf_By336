package com.elementars.eclient.guirewrite.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextNotes extends JFrame
{
    /* synthetic */ JTextField jt;
    /* synthetic */ JLabel jl;
    /* synthetic */ JButton jb;
    /* synthetic */ JPanel jp;
    
    public static void initTextBox() {
        final TextNotes lllllllllllllllllllIlIIllIlllIlI = new TextNotes();
    }
    
    public TextNotes() {
        this.jp = new JPanel();
        this.jl = new JLabel();
        this.jt = new JTextField(30);
        this.jb = new JButton("Set Text");
        this.setTitle("TextNotes");
        this.setVisible(true);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(1);
        this.jp.add(this.jt);
        this.jt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent lIlIlllIllllIIl) {
                final String lIlIlllIllllIII = TextNotes.this.jt.getText();
                TextNotes.this.jl.setText(lIlIlllIllllIII);
            }
        });
        this.jp.add(this.jb);
        this.jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent llllIllIlIIII) {
                final String llllIllIIllll = TextNotes.this.jt.getText();
                StickyNotes.processText(llllIllIIllll);
            }
        });
        this.jp.add(this.jl);
        this.add(this.jp);
    }
}
