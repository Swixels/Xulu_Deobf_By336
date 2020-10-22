package com.elementars.eclient.guirewrite.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeNotes extends JFrame
{
    /* synthetic */ JLabel jl;
    /* synthetic */ JButton jb;
    /* synthetic */ JPanel jp;
    /* synthetic */ JTextField jt;
    
    public static void initTextBox() {
        final WelcomeNotes lllllllllllllllllIIlIlIllIIlIllI = new WelcomeNotes();
    }
    
    public WelcomeNotes() {
        this.jp = new JPanel();
        this.jl = new JLabel();
        this.jt = new JTextField(30);
        this.jb = new JButton("Set Welcome Message (use NAME to mark the name)");
        this.setTitle("Welcome Message");
        this.setVisible(true);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(1);
        this.jp.add(this.jt);
        this.jt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent llllllllIIIIllI) {
                final String llllllllIIIIlIl = WelcomeNotes.this.jt.getText();
                WelcomeNotes.this.jl.setText(llllllllIIIIlIl);
            }
        });
        this.jp.add(this.jb);
        this.jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent lllllllllllllllllIIlIIIIIIlIIIIl) {
                final String lllllllllllllllllIIlIIIIIIlIIIII = WelcomeNotes.this.jt.getText();
                Welcome.handleWelcome(lllllllllllllllllIIlIIIIIIlIIIII);
            }
        });
        this.jp.add(this.jl);
        this.add(this.jp);
    }
}
