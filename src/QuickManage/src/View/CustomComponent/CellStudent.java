/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CustomComponent;

import Model.Model;
import View.Input.EnrollmentFrame;
import Model.Student;
import Model.Ultility;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Lyn
 */
public class CellStudent extends CPanel implements Ultility {

    public int x;
    public int y;
    private JLabel labelId = new JLabel();
//    private String status = "None";
    private Student student = null;
    private JLabel toplab = new JLabel();
    private EnrollmentFrame ef;
    Model model = Model.getModel();
     ResourceBundle resourse = model.getResources();
    
    public CellStudent(int x, int y, EnrollmentFrame ef) {
        super(new BorderLayout());
        this.x = x;
        this.y = y;
        this.ef = ef;
//        setBorder(new LineBorder(Color.white, 1));
        checkStudent();
    }

    public void checkStudent() {
        if (student != null) {
            CPanel top = new CPanel(new ImageIcon("Images/"+student.getPhoto()));
            this.add(top, BorderLayout.CENTER);
            this.add(labelId, BorderLayout.SOUTH);
            if (model.isPaid(student)) {
                labelId.setText("<html><b><font color = 'blue'>ID :" + student.getId() + "</font></b></html>");
            } else {
                labelId.setText("<html><b><font color = 'red'>ID :" + student.getId() + "</font></b></html>");
            }
            
            setRightClickMenu();
        } else {
//            CPanel top = new CPanel(new ImageIcon(Ultility.class.getResource("/images/user.png")));
//            this.add(top, BorderLayout.CENTER);
            this.add(labelId, BorderLayout.SOUTH);
            labelId.setText("<html><b>ID :.......... </b></html>");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        checkStudent();
        repaint();
    }

    public void setRightClickMenu() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    doPop(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    doPop(e);
                }
            }

            private void doPop(MouseEvent e) {
                final JPopupMenu menu = new JPopupMenu();
                JMenuItem anItem2 = new JMenuItem(resourse.getString("DELETE_T_TXT"));
                menu.add(anItem2);
                anItem2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ef.deletedStudentInClasss(student);
                    }
                });
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
