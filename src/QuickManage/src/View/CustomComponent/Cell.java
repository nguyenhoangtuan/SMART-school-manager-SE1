/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CustomComponent;

import View.Input.ClassRoomFrame;
import Model.Classes;
import Model.Model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lyn
 */
public class Cell extends JPanel {

    public String status = "";
    public String locationClass = "None";
    public String classLocationAtOtherRoom = "No";
    private int x;
    private int y;
    private JLabel lab = new JLabel();
    public String info;
    Model m = Model.getModel();
    
    ClassRoomFrame crf;
     ResourceBundle resourse = m.getResources();

    public Cell(int x, int y, ClassRoomFrame crf) {
        setBorder(new LineBorder(Color.black, 1));
        setLayout(null);
        this.crf = crf;
        this.x = x;
        this.y = y;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (locationClass.equals("None")) {
                    setStatus("MousePosition");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setStatus("Out");
            }
        });
        this.add(lab);
        lab.setBounds(5, -2, 55, 20);
        lab.setFont(new Font("Time New Roman", Font.PLAIN, 10));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (status.equals("MousePosition")) {
            Dimension d = getSize();
            g.drawLine(0, 0, d.width, d.height);
            g.drawLine(d.width, 0, 0, d.height);
        }
        if (status.equals("Out")) {
        }

        if (classLocationAtOtherRoom.equals("Yes")) {
//            setBackground(Color.blue);
            setBorder(new LineBorder(Color.white, 10));
//            setBackground(Color.white);
        }
        if (locationClass.equals("Seted")) {
            setBackground(Color.pink);
        }
    }

    public String getLocationClass() {
        return locationClass;
    }

    public void setLocationClass(String locationClass) {
        this.locationClass = locationClass;
        repaint();
    }

    public String getClassLocationAtOtherRoom() {
        return classLocationAtOtherRoom;
    }

    public void setClassLocationAtOtherRoom(String classLocationAtOtherRoom) {
        this.classLocationAtOtherRoom = classLocationAtOtherRoom;
        setLocationClass("Seted");
        repaint();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        repaint();
        this.status = status;
    }

    public void setTitleForLabel(String txt) {
        lab.setText("<html>" + "<b>" + txt + "</b>" + "</html>");
    }

    public void setTextForLabel(String txt) {
//        lab.setText("<html>" + "<b>" + txt + "</b>" + "</html>");
        Classes a = m.findClassById(txt);
        String str;
        if (m.findClassTypeById(a.getClassTypeid()).getType() == 0) {
            str = resourse.getString("INDIVIDUAL_T_TXT");
        } else if (m.findClassTypeById(a.getClassTypeid()).getType() == 1) {
            str = resourse.getString("DUAL_T_TXT");
        } else {
            str = resourse.getString("GROUP_T_TXT");
        }
        lab.setToolTipText("<html>" + resourse.getString("CLASS_ID_T_TXT1") + a.getId() + "<br>" + resourse.getString("NAME_T_TXT1") + a.getName() + "<br>"
                + resourse.getString("LENGTH_T_TXT1") + a.getHour() + "<br>" + resourse.getString("METHOD_T_TXT1") + str + "</html>");
        lab.addMouseListener(new MouseAdapter() {
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
                if (classLocationAtOtherRoom.equals("No")) {
                    final JPopupMenu menu = new JPopupMenu();
                    JMenuItem anItem1 = new JMenuItem("Edit");
                    JMenuItem anItem2 = new JMenuItem("Delete ! ");
                    menu.add(anItem1);
                    menu.add(anItem2);

                    anItem1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            crf.editClassSchedule(info);
                        }
                    });

                    anItem2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            crf.deleteClassSchedule(info);
                        }
                    });

                    menu.show(e.getComponent(), e.getX(), e.getY());
                } else {
                    final JPopupMenu menu = new JPopupMenu();
                    JMenuItem anItem1 = new JMenuItem("Cannot Edit Here");
                    JMenuItem anItem2 = new JMenuItem("Cannot Delete Here! ");
                    menu.add(anItem1);
                    menu.add(anItem2);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getClassAtCell() {
        String[] parts = info.split("-");
        String part4 = parts[3];
        return part4;
    }
}
// make the class at other room is not edit or delete able, 
// fix the colour of class at other room