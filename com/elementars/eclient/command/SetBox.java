package com.elementars.eclient.command;

import dev.xulu.settings.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetBox extends JFrame
{
    /* synthetic */ JButton jb;
    /* synthetic */ JPanel jp;
    /* synthetic */ JTextField jt;
    /* synthetic */ Value<String> setting;
    /* synthetic */ JLabel jl;
    
    public SetBox(final Value<String> lIlIlllIIlllIII) {
        this.jp = new JPanel();
        this.jl = new JLabel();
        this.jt = new JTextField(30);
        this.jb = new JButton("Set");
        this.setting = lIlIlllIIlllIII;
        this.setTitle("Setting");
        this.setVisible(true);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(1);
        this.jp.add(this.jt);
        this.jt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent lIIIIlIllIlllll) {
                final String lIIIIlIllIllllI = SetBox.this.jt.getText();
                SetBox.this.jl.setText(lIIIIlIllIllllI);
            }
        });
        this.jp.add(this.jb);
        this.jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent lllllllIIlllIlI) {
                final String lllllllIIlllIIl = SetBox.this.jt.getText();
                lIlIlllIIlllIII.setValue(lllllllIIlllIIl);
            }
        });
        this.jp.add(this.jl);
        this.add(this.jp);
    }
    
    public static void initTextBox(final Value<String> lIlIlllIIllIIll) {
        final SetBox lIlIlllIIllIlII = new SetBox(lIlIlllIIllIIll);
    }
}
