package org.newdawn.slick.font.effects;

import java.awt.image.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class EffectUtil
{
    private static /* synthetic */ BufferedImage scratchImage;
    
    public static ConfigurableEffect.Value booleanValue(final String llIllIIIlllllII, final boolean llIllIIIllllIll, final String llIllIIIllllIlI) {
        return new DefaultValue(llIllIIIlllllII, String.valueOf(llIllIIIllllIll)) {
            @Override
            public Object getObject() {
                return Boolean.valueOf(this.value);
            }
            
            @Override
            public void showDialog() {
                final JCheckBox lllllllIIlIIIll = new JCheckBox();
                lllllllIIlIIIll.setSelected(llIllIIIllllIll);
                if (this.showValueDialog(lllllllIIlIIIll, llIllIIIllllIlI)) {
                    this.value = String.valueOf(lllllllIIlIIIll.isSelected());
                }
            }
        };
    }
    
    public static Color fromString(final String llIllIIIlIlllII) {
        if (llIllIIIlIlllII == null || llIllIIIlIlllII.length() != 6) {
            return Color.white;
        }
        return new Color(Integer.parseInt(llIllIIIlIlllII.substring(0, 2), 16), Integer.parseInt(llIllIIIlIlllII.substring(2, 4), 16), Integer.parseInt(llIllIIIlIlllII.substring(4, 6), 16));
    }
    
    static {
        EffectUtil.scratchImage = new BufferedImage(256, 256, 2);
    }
    
    public static ConfigurableEffect.Value floatValue(final String llIllIIlIIIIlII, final float llIllIIlIIIlIII, final float llIllIIlIIIIlll, final float llIllIIlIIIIllI, final String llIllIIlIIIIlIl) {
        return new DefaultValue(llIllIIlIIIIlII, String.valueOf(llIllIIlIIIlIII)) {
            @Override
            public Object getObject() {
                return Float.valueOf(this.value);
            }
            
            @Override
            public void showDialog() {
                final JSpinner lllllllllllllllllIIIIlIllIlIllIl = new JSpinner(new SpinnerNumberModel(llIllIIlIIIlIII, llIllIIlIIIIlll, llIllIIlIIIIllI, 0.10000000149011612));
                if (this.showValueDialog(lllllllllllllllllIIIIlIllIlIllIl, llIllIIlIIIIlIl)) {
                    this.value = String.valueOf(((Double)lllllllllllllllllIIIIlIllIlIllIl.getValue()).floatValue());
                }
            }
        };
    }
    
    public static String toString(final Color llIllIIIllIIllI) {
        if (llIllIIIllIIllI == null) {
            throw new IllegalArgumentException("color cannot be null.");
        }
        String llIllIIIllIIlIl = Integer.toHexString(llIllIIIllIIllI.getRed());
        if (llIllIIIllIIlIl.length() == 1) {
            llIllIIIllIIlIl = String.valueOf(new StringBuilder().append("0").append(llIllIIIllIIlIl));
        }
        String llIllIIIllIIlII = Integer.toHexString(llIllIIIllIIllI.getGreen());
        if (llIllIIIllIIlII.length() == 1) {
            llIllIIIllIIlII = String.valueOf(new StringBuilder().append("0").append(llIllIIIllIIlII));
        }
        String llIllIIIllIIIll = Integer.toHexString(llIllIIIllIIllI.getBlue());
        if (llIllIIIllIIIll.length() == 1) {
            llIllIIIllIIIll = String.valueOf(new StringBuilder().append("0").append(llIllIIIllIIIll));
        }
        return String.valueOf(new StringBuilder().append(llIllIIIllIIlIl).append(llIllIIIllIIlII).append(llIllIIIllIIIll));
    }
    
    public static ConfigurableEffect.Value colorValue(final String llIllIIlIIllIIl, final Color llIllIIlIIllIlI) {
        return new DefaultValue(llIllIIlIIllIIl, toString(llIllIIlIIllIlI)) {
            @Override
            public Object getObject() {
                return EffectUtil.fromString(this.value);
            }
            
            @Override
            public void showDialog() {
                final Color llllllllllllllllIllIIIlllIlIllll = JColorChooser.showDialog(null, "Choose a color", EffectUtil.fromString(this.value));
                if (llllllllllllllllIllIIIlllIlIllll != null) {
                    this.value = EffectUtil.toString(llllllllllllllllIllIIIlllIlIllll);
                }
            }
        };
    }
    
    public static ConfigurableEffect.Value optionValue(final String llIllIIIlllIIlI, final String llIllIIIlllIIIl, final String[][] llIllIIIllIllII, final String llIllIIIllIllll) {
        return new DefaultValue(llIllIIIlllIIlI, llIllIIIlllIIIl.toString()) {
            private String getValue(final int llllllllllllllllIllIlIllIlIlIlll) {
                if (llIllIIIllIllII[llllllllllllllllIllIlIllIlIlIlll].length == 1) {
                    return llIllIIIllIllII[llllllllllllllllIllIlIllIlIlIlll][0];
                }
                return llIllIIIllIllII[llllllllllllllllIllIlIllIlIlIlll][1];
            }
            
            @Override
            public String toString() {
                for (int llllllllllllllllIllIlIllIlIlIlII = 0; llllllllllllllllIllIlIllIlIlIlII < llIllIIIllIllII.length; ++llllllllllllllllIllIlIllIlIlIlII) {
                    if (this.getValue(llllllllllllllllIllIlIllIlIlIlII).equals(this.value)) {
                        return llIllIIIllIllII[llllllllllllllllIllIlIllIlIlIlII][0].toString();
                    }
                }
                return "";
            }
            
            @Override
            public Object getObject() {
                return this.value;
            }
            
            @Override
            public void showDialog() {
                int llllllllllllllllIllIlIllIllIIIll = -1;
                final DefaultComboBoxModel llllllllllllllllIllIlIllIllIIIlI = new DefaultComboBoxModel();
                for (int llllllllllllllllIllIlIllIllIIlIl = 0; llllllllllllllllIllIlIllIllIIlIl < llIllIIIllIllII.length; ++llllllllllllllllIllIlIllIllIIlIl) {
                    llllllllllllllllIllIlIllIllIIIlI.addElement(llIllIIIllIllII[llllllllllllllllIllIlIllIllIIlIl][0]);
                    if (this.getValue(llllllllllllllllIllIlIllIllIIlIl).equals(llIllIIIlllIIIl)) {
                        llllllllllllllllIllIlIllIllIIIll = llllllllllllllllIllIlIllIllIIlIl;
                    }
                }
                final JComboBox llllllllllllllllIllIlIllIllIIIIl = new JComboBox(llllllllllllllllIllIlIllIllIIIlI);
                llllllllllllllllIllIlIllIllIIIIl.setSelectedIndex(llllllllllllllllIllIlIllIllIIIll);
                if (this.showValueDialog(llllllllllllllllIllIlIllIllIIIIl, llIllIIIllIllll)) {
                    this.value = this.getValue(llllllllllllllllIllIlIllIllIIIIl.getSelectedIndex());
                }
            }
        };
    }
    
    public static ConfigurableEffect.Value intValue(final String llIllIIlIIlIIIl, final int llIllIIlIIlIIll, final String llIllIIlIIIllll) {
        return new DefaultValue(llIllIIlIIlIIIl, String.valueOf(llIllIIlIIlIIll)) {
            @Override
            public void showDialog() {
                final JSpinner llllllllllllllllIllIIIIIIIllIlll = new JSpinner(new SpinnerNumberModel(llIllIIlIIlIIll, -32768, 32767, 1));
                if (this.showValueDialog(llllllllllllllllIllIIIIIIIllIlll, llIllIIlIIIllll)) {
                    this.value = String.valueOf(llllllllllllllllIllIIIIIIIllIlll.getValue());
                }
            }
            
            @Override
            public Object getObject() {
                return Integer.valueOf(this.value);
            }
        };
    }
    
    public static BufferedImage getScratchImage() {
        final Graphics2D llIllIIlIIlllll = (Graphics2D)EffectUtil.scratchImage.getGraphics();
        llIllIIlIIlllll.setComposite(AlphaComposite.Clear);
        llIllIIlIIlllll.fillRect(0, 0, 256, 256);
        llIllIIlIIlllll.setComposite(AlphaComposite.SrcOver);
        llIllIIlIIlllll.setColor(Color.white);
        return EffectUtil.scratchImage;
    }
    
    private abstract static class DefaultValue implements ConfigurableEffect.Value
    {
        /* synthetic */ String value;
        /* synthetic */ String name;
        
        @Override
        public String getString() {
            return this.value;
        }
        
        @Override
        public String getName() {
            return this.name;
        }
        
        @Override
        public String toString() {
            if (this.value == null) {
                return "";
            }
            return this.value.toString();
        }
        
        public DefaultValue(final String llIIllIlIIllIIl, final String llIIllIlIIlIlIl) {
            this.value = llIIllIlIIlIlIl;
            this.name = llIIllIlIIllIIl;
        }
        
        @Override
        public void setString(final String llIIllIlIIIllll) {
            this.value = llIIllIlIIIllll;
        }
        
        public boolean showValueDialog(final JComponent llIIllIlIIIIIII, final String llIIllIIllllIll) {
            final ValueDialog llIIllIIllllllI = new ValueDialog(llIIllIlIIIIIII, this.name, llIIllIIllllIll);
            llIIllIIllllllI.setTitle(this.name);
            llIIllIIllllllI.setLocationRelativeTo(null);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JComponent llIlIIlllIlllI = llIIllIlIIIIIII;
                    if (llIlIIlllIlllI instanceof JSpinner) {
                        llIlIIlllIlllI = ((JSpinner.DefaultEditor)((JSpinner)llIIllIlIIIIIII).getEditor()).getTextField();
                    }
                    llIlIIlllIlllI.requestFocusInWindow();
                }
            });
            llIIllIIllllllI.setVisible(true);
            return llIIllIIllllllI.okPressed;
        }
    }
    
    private static class ValueDialog extends JDialog
    {
        public /* synthetic */ boolean okPressed;
        
        public ValueDialog(final JComponent llIlllIIlIlllIl, final String llIlllIIlIlllII, final String llIlllIIlIlIlII) {
            this.okPressed = false;
            this.setDefaultCloseOperation(2);
            this.setLayout(new GridBagLayout());
            this.setModal(true);
            if (llIlllIIlIlllIl instanceof JSpinner) {
                ((JSpinner.DefaultEditor)((JSpinner)llIlllIIlIlllIl).getEditor()).getTextField().setColumns(4);
            }
            final JPanel llIlllIIlIllIlI = new JPanel();
            llIlllIIlIllIlI.setLayout(new GridBagLayout());
            this.getContentPane().add(llIlllIIlIllIlI, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
            llIlllIIlIllIlI.setBackground(Color.white);
            llIlllIIlIllIlI.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
            final JTextArea llIlllIIllIIIIl = new JTextArea(llIlllIIlIlIlII);
            llIlllIIlIllIlI.add(llIlllIIllIIIIl, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
            llIlllIIllIIIIl.setWrapStyleWord(true);
            llIlllIIllIIIIl.setLineWrap(true);
            llIlllIIllIIIIl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            llIlllIIllIIIIl.setEditable(false);
            final JPanel llIlllIIlIllIIl = new JPanel();
            this.getContentPane().add(llIlllIIlIllIIl, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, 10, 0, new Insets(5, 5, 0, 5), 0, 0));
            llIlllIIlIllIIl.add(new JLabel(String.valueOf(new StringBuilder().append(llIlllIIlIlllII).append(":"))));
            llIlllIIlIllIIl.add(llIlllIIlIlllIl);
            final JPanel llIlllIIlIllIII = new JPanel();
            this.getContentPane().add(llIlllIIlIllIII, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
            final JButton llIlllIIllIIIII = new JButton("OK");
            llIlllIIlIllIII.add(llIlllIIllIIIII);
            llIlllIIllIIIII.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent llllllllllllllllllIlllllIllllIII) {
                    ValueDialog.this.okPressed = true;
                    ValueDialog.this.setVisible(false);
                }
            });
            final JButton llIlllIIlIlllll = new JButton("Cancel");
            llIlllIIlIllIII.add(llIlllIIlIlllll);
            llIlllIIlIlllll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent llIIIIlIllIlI) {
                    ValueDialog.this.setVisible(false);
                }
            });
            this.setSize(new Dimension(320, 175));
        }
    }
}
