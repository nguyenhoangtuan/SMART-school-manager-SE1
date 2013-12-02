/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import Model.ClassInvoiceDetail;
import Model.Model;
import Model.Ultility;
import Model.Invoice;
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
public class InvoiceFrame extends JDialog implements Ultility {
    
    private CPanel mainPn, viewPn, paidPn, buttonPn, infoPn, contentPn;
    private CLabel invoiceInfoLb, detailLb, lb1, lb2, lb3, lb4, lb5, lb6;
    private JButton saveBt, cancelBt, viewBt;
    private JCheckBox paidCb;
    private JTextArea note;
    Model model = Model.getModel();
    private Invoice invoice;
    private JScrollPane scroll;
    private JComboBox paidList;
    private String username;
    ResourceBundle resourse = model.getResources();
    public InvoiceFrame(Invoice i, String username) {
        
        
        setVisible(true);
//        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        invoice = i;
        username = username;
        
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
        if (invoice.getStartDate() != null) {
            info += "<li>Id: " + invoice.getId() + "</li>"
                    + "<li>Start date: " + new SimpleDateFormat("dd-MM-yyyy").format(invoice.getStartDate()) + "</li>"
                    + "<li>End date: " + new SimpleDateFormat("dd-MM-yyyy").format(invoice.getEndDate()) + "</li>"
                    + "<li>Total fees: " + invoice.getTotalFee() + "</li>";
        } else {
            System.out.println("asdasdasd");
            info += "<li>Id: " + invoice.getId().toString() + "</li>"
                    + "<li>Start date: </li>"
                    + "<li>End date: </li>"
                    + "<li>Total fees: " + invoice.getTotalFee() + "</li>";
        }
        info += "</ul></html>";
        invoiceInfoLb = new CLabel(info, TEXT_SIZE);
//        lb1 = new CLabel("Start date: " + invoice.getStartDate().toString(), TEXT_SIZE);
//        lb2 = new CLabel("End date: " + invoice.getEndDate().toString(), TEXT_SIZE);
//        lb3 = new CLabel("Total fees:: " + invoice.getTotalFee(), TEXT_SIZE);
        detailLb = new CLabel("", TEXT_SIZE);
        saveBt = new JButton(resourse.getString("SAVE_TXT"));
        cancelBt = new JButton(resourse.getString("CANCEL_TXT"));
        viewBt = new JButton(resourse.getString("VIEW_MENU"));
        paidCb = new JCheckBox(resourse.getString("PAID_TXT"));
        paidCb.setOpaque(false);
        paidList = new JComboBox(PAID_METHOD);
        paidList.setVisible(false);
        buttonPn = new CPanel();
        infoPn = new CPanel(new GridLayout(1, 1));
        infoPn.setBorder(BorderFactory.createTitledBorder("Infomation"));
        contentPn = new CPanel(new GridLayout(20, 1));
        contentPn.setVisible(false);
        note = new JTextArea(3, 1);
        scroll = new JScrollPane(note);
        scroll.setVisible(false);
        
        if (invoice.isPaid() == true) {
            paidCb.setSelected(true);
            paidList.setVisible(true);
            scroll.setVisible(true);
            paidList.setSelectedIndex(invoice.getPaidMethod());
            note.setText(invoice.getNote());
        }
        
        if (!model.isManager(username)) {
            paidCb.setEnabled(false);
        }
        
        
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
        viewPn.add(viewBt);
        viewPn.add(contentPn);
    }
    
    private void generatePaidPanel() {
        paidPn.add(paidCb);
        paidPn.add(paidList);
        paidPn.add(scroll);
    }
    
    private void getButtonPanel() {
        buttonPn.add(saveBt);
        buttonPn.add(cancelBt);
    }
    
    private void addListener() {
        viewBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPn.removeAll();
                
                String detail =
                        "<html>"
                        + "<table>"
                        + "<th>Class Id:</th>"
                        + "<th>Class Name:</th>"
                        + "<th>Start Date:</th>"
                        + "<th>End Date:</th>"
                        + "<th>Fee:</th>";
                for (ClassInvoiceDetail cid : invoice.getClassList()) {
                    
                    detail += "<tr>"
                            + "<td>" + cid.getClassId() + "</td>"
                            + "<td>" + cid.getClassName() + "</td> "
                            + "<td>" + new SimpleDateFormat("dd-MM-yyyy").format(cid.getStarDate()) + "</td>"
                            + "<td>" + new SimpleDateFormat("dd-MM-yyyy").format(cid.getEndDate()) + "</td>"
                            + "<td>" + cid.getFee() + "</td>"
                            + "</tr>";
                    
                    
                }
                detail += " </table> </html>";
                detailLb.setText(detail);
                contentPn.add(detailLb);
                if (contentPn.isVisible() == false) {
                    contentPn.setVisible(true);
                } else {
                    contentPn.setVisible(false);
                }
            }
        });
        
        paidCb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paidCb.isSelected()) {
                    paidList.setVisible(true);
                    scroll.setVisible(true);
                } else {
                    paidList.setVisible(false);
                    scroll.setVisible(false);
                }
            }
        });
        
        saveBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paidCb.isSelected()) {
                    invoice.setNote(note.getText());
                    invoice.setPaid(true);
                    invoice.setPaidDate(new Date());
                    invoice.setPaidMethod(paidList.getSelectedIndex());
                }else{
                    invoice.setPaid(false);
                }
                dispose();
            }
        });
        
        cancelBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
    }
}
