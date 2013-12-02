/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CustomComponent;

import View.Input.TeacherTimeTable;
import Model.Classes;
import Model.Model;
import View.CustomComponent.CPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lyn
 */
public class CellTeacherTimeTable extends CPanel {

    int x;
    int y;
    TeacherTimeTable stt;
    public String status = "Out";
    private JLabel lab = new JLabel();
    public String roomId = "";
     Model m = Model.getModel();
      ResourceBundle resourse = m.getResources();

    public CellTeacherTimeTable(int x, int y, TeacherTimeTable crf) {
        setBorder(new LineBorder(Color.black, 1));
        setLayout(null);
        this.stt = crf;
        this.x = x;
        this.y = y;

        this.add(lab);
        lab.setBounds(5, -2, 55, 20);
        lab.setFont(new Font("Time New Roman", Font.PLAIN, 10));
        setOpaque(true);

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (status.equals("In")) {
            setBackground(Color.pink);
        }


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
//        lab.setToolTipText("<html>" + "Room ID : "+ roomId +"<br>"+"Class ID : " + a.getId() + "<br>" + "Name : " + a.getName() + "<br>"
//                + "Length : " + a.getHour() + "<br>" + "Method : " + str + "</html>");
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    
    
}
