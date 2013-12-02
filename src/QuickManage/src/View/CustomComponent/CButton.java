/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CustomComponent;

import View.AccountsGUI;
import View.ClassGUI;
import View.ClassTypeGUI;
import View.InvoiceGUI;
import View.StudentGUI;
import View.TeacherGUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author anh
 */
public class CButton extends JButton {

    int index;

    public CButton(Icon icon) {
        super(icon);
        setOpaque(false);
    }

    public int getIndex() {
        return index;
    }

    public CButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    public CButton(final int index, int input, final AccountsGUI p) {
        this.index = index;
        setText(index + "");
        if (index == input) {
            setBackground(Color.red);
        }
        setOpaque(true);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setIndex(index);
                p.updateAccountPanel();
            }
        });


    }

    public CButton(final int index, int input, final TeacherGUI p) {
        this.index = index;
        setText(index + "");
        if (index == input) {
            setBackground(Color.red);
        }
        setOpaque(true);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setIndex(index);
                p.updateTeacherPanel();
            }
        });
    }

    public CButton(final int index, int input, final StudentGUI p) {
        this.index = index;
        setText(index + "");
        if (index == input) {
            setBackground(Color.red);
        }
        setOpaque(true);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setIndex(index);
                p.updateContainerPanel();
            }
        });
    }

    public CButton(final int index, int input, final ClassGUI p) {
        this.index = index;
        setText(index + "");
        if (index == input) {
            setBackground(Color.red);
        }
        setOpaque(true);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setIndex(index);
                p.updateContainerPanel();
            }
        });
    }
    
     public CButton(final int index, int input, final InvoiceGUI p) {
        this.index = index;
        setText(index + "");
        if (index == input) {
            setBackground(Color.red);
        }
        setOpaque(true);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setIndex(index);
                p.updateInvoicePanel();
            }
        });
    }
     
          public CButton(final int index, int input, final ClassTypeGUI p) {
        this.index = index;
        setText(index + "");
        if (index == input) {
            setBackground(Color.red);
        }
        setOpaque(true);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setIndex(index);
                p.updateContainerPanel();
            }
        });
    }
}
