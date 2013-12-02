/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import Model.ClassInvoiceDetail;
import Model.Model;
import Model.Ultility;
import Model.Invoice;
import Model.PaySlip;
import Model.PaySlipDetail;
import View.CustomComponent.CLabel;
import View.CustomComponent.CPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author anh
 */
public class PaySlipDetailJDialog extends JDialog implements Ultility {
    
    private CPanel mainPn, viewPn, paidPn, buttonPn, infoPn, contentPn;
    private CLabel invoiceInfoLb, detailLb;
    private JButton  cancelBt;
    Model model = Model.getModel();
    private PaySlip paySlip;
    private JComboBox paidList;
    ResourceBundle resourse = model.getResources();
    public PaySlipDetailJDialog(PaySlip i) {
        
        
        setVisible(true);
//        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        paySlip = i;
        init();
        add(mainPn);
        generateViewPanel();
        generateInfoPanel();
        addListener();
        getButtonPanel();
        generateMainPanel();
        generatePaidPanel();
//        pack();
        setSize(500, 700);
        
    }
    
    private void init() {
        mainPn = new CPanel(new GridLayout(4, 1), INPUT_PANEL_PIC);
        mainPn.setBorder(BorderFactory.createTitledBorder("Invoice"));
        viewPn = new CPanel();
        viewPn.setBorder(BorderFactory.createTitledBorder("More Detail"));
        paidPn = new CPanel(new GridLayout(5, 1));
        paidPn.setBorder(BorderFactory.createTitledBorder("Paid"));
        String info = "<html><ul>";
            info += "<li>Id: " + paySlip.getId() + "</li>"
                    + "<li>Teacher Id: " + model.findTeacherByPayID(paySlip.getId()).getId() + "</li>"
                    + "<li>Teacher Name: " + model.findTeacherByPayID(paySlip.getId()).getLastName() + "</li>"
                    + "<li>Total fees: " + paySlip.getTotalSalary() + "</li>";
        
        info += "</ul></html>";
        invoiceInfoLb = new CLabel(info, TEXT_SIZE);

        detailLb = new CLabel("", TEXT_SIZE);
        cancelBt = new JButton(resourse.getString("CANCEL_TXT"));
        paidList = new JComboBox(PAID_METHOD);
        paidList.setVisible(false);
        buttonPn = new CPanel();
        infoPn = new CPanel(new GridLayout(1, 1));
        infoPn.setBorder(BorderFactory.createTitledBorder("Infomation"));
        contentPn = new CPanel(new GridLayout(20, 1));
        contentPn.setVisible(true);
        
    }
    
    private void generateMainPanel() {
        mainPn.add(infoPn);
        mainPn.add(viewPn);
        mainPn.add(paidPn);
        mainPn.add(buttonPn);
    }
    
    private void generateInfoPanel() {
        infoPn.add(invoiceInfoLb);
//        infoPn.add(lb1);
//        infoPn.add(lb2);
//        infoPn.add(lb3);

    }
    
    private void generateViewPanel() {
        viewPn.add(contentPn);
    }
    
    private void generatePaidPanel() {
        paidPn.add(paidList);
    }
    
    private void getButtonPanel() {
        buttonPn.add(cancelBt);
    }
    
    private void addListener() {
                String detail =
                        "<html>"
                        + "<table>"
                        + "<th>Class Id:</th>"
                        + "<th>Number of Lesson:</th>"
                        + "<th>Total teaching hour:</th>"
                        + "<th>Hourly pay rate:</th>"
                        + "<th>Fee:</th>";
                for (PaySlipDetail cid : paySlip.getpList()) {
                    
                    detail += "<tr>"
                            + "<td>" + cid.getClassId() + "</td>"
                            + "<td>" + cid.getNoLesson() + "</td> "
                            + "<td>" + cid.getTeachingHour()+ "</td>"
                            + "<td>" + cid.getHourlyPayRate() + " VND/week</td>"
                            + "<td>" + cid.getFee() + "</td>"
                            + "</tr>";
                    
                    
                }
                detail += " </table> </html>";
                detailLb.setText(detail);
                contentPn.add(detailLb);
        
        cancelBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
    }
}
