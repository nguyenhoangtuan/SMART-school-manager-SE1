/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import Model.Ultility;
import View.CustomComponent.CLabel;
import View.CustomComponent.CPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author anh
 */
public class Dashboard extends JDialog implements Ultility {

    private CPanel mainPn;
    Model model = Model.getModel();
    private CLabel content;
    private JButton closeBt;
    private Date currentDate;

    public Dashboard() {
        this.setTitle(DASH_BOARD_TXT);

        init();
        generateContentLb();
        generateMainPn();
        this.add(mainPn);
        setVisible(true);
        setResizable(false);
        pack();
    }

    private void init() {
        mainPn = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
        content = new CLabel("", TEXT_SIZE);
        closeBt = new JButton(CLOSE_TXT);
        currentDate = new Date();
        mainPn.setBorder(BorderFactory.createTitledBorder("Dash board"));
        closeBt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void generateMainPn() {
        mainPn.add(content, BorderLayout.CENTER);
        mainPn.add(closeBt, BorderLayout.SOUTH);
    }

    private void generateContentLb() {
        String cont =
                "<html>"
                + "<p>"
                + "<br>Number of classes: " + model.getClassList().size() + "</br>"
                + "<br>Number of student: " + model.getStudentList().size() + "</br>"
                + "<br>Number of teacher: " + model.getTeacherList().size() + "</br>"
                + "<br>Capacity utilization ratio: " + "</br>" //INCOMPLETE
                + "<br>Total paid tuition fee of last month: " + model.getTotalFeeLastMonth(currentDate) + "</br>"
                + "<br>Total unpaid tuition fee of last month: " + model.getTotalUnpaidFeeLastMonth(currentDate) + "</br>"
                + "<br>Total paid tuition fee of current month: " + model.getTotalFeeCurrMonth(currentDate) + "</br>"
                + "<br>Total unpaid tuition fee of current month: " + model.getTotalUnpaidFeeCurrMonth(currentDate) + "</br>"
                + "</p>"
                + "</html>";
        
        content.setText(cont);

    }
}
