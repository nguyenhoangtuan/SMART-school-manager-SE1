/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import Model.Model;
import Model.Ultility;
import Model.Invoice;
import View.CustomComponent.CLabel;
import View.CustomComponent.CPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author anh
 */
public class Report extends JDialog implements Ultility, Printable {

    Model model = Model.getModel();
    ResourceBundle resourse = model.getResources();
    private CPanel content, buttonPn, main;
    private Vector<Date> dateList = model.getReportDateList();
    private JComboBox<Date> dateCB = new JComboBox<>(dateList);
    private JButton printBt = new JButton(resourse.getString("PRINT_TXT"));
    private JButton closeBt = new JButton(resourse.getString("CLOSE_TXT"));
    HashMap<String, Invoice> part1Map = new HashMap<>();
    private JLabel contentLb;
    private PrinterJob printer;
    private Date currentDate;

    
    public Report() {

        generateContent();
        generateButtonPn();
        generateMain();

        printBt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    printer = PrinterJob.getPrinterJob();
                    printer.setPrintable(getThis());
                    printer.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        closeBt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        
        add(main);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void generateMain() {
        main = new CPanel(new BorderLayout());

        main.add(dateCB, BorderLayout.NORTH);
        main.add(content);
        main.add(buttonPn,BorderLayout.SOUTH);
        main.setOpaque(true);
        main.setBackground(Color.white);
    }

    private void generateContent() {
        currentDate = dateCB.getItemAt(0);
        content = new CPanel();
        
        String table =
                "<table>"
                + "<th>Student ID</th>"
                + "<th>Amount</th>"
                + "<th>Paid Date</th>"
                + "<th>Paid Method</th>"
                + "<th>Paid Note</th>";
        for(Map.Entry<String,Invoice> entry: model.getPaidStudents(currentDate).entrySet()){
            try {
                System.out.println("SID: "+entry.getKey());
                table+=
                        "<tr>"
                        +"<td>"+entry.getKey()+"</td>"
                        +"<td>"+entry.getValue().getTotalFee()+"</td>"
                        +"<td>"+model.dateToString(entry.getValue().getPaidDate())+"</td>"
                        +"<td>"+PAID_METHOD[entry.getValue().getPaidMethod()]+"</td>"
                        +"<td>"+entry.getValue().getNote()+"</td>"
                        + "</tr>";
            } catch (ParseException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        table+="</table>";
        double total=0;
         for(Map.Entry<String,Invoice> entry: model.getPaidStudents(currentDate).entrySet()){
             total+= entry.getValue().getTotalFee();
         }
        String table2="<ul>";
        for(String s:model.getUnPaidSudent(currentDate)){
            table2+="<li>"+s+"</li>";
        }
        table2 +="</ul>";
        
        String textContent = 
                "<html>"
                + "<h1>Report</h1>"
                + "<h2>"
                + "PART1: PAID STUDENT"
                + "</h2>"
                + "<p>"
                + table
                + "TOTAL: "+total
                + "</p>"
                + "<h2>"
                + "PART2: UNPAID STUDENT"
                + "</h2>"
                + "<p>"
                + table2
                + "</p>"
                + "</html>";
        contentLb = new JLabel(textContent);
        content.add(contentLb);

    }

    private void generateButtonPn() {
        buttonPn = new CPanel();
        buttonPn.add(printBt);
        buttonPn.add(closeBt);
    }

    public static void main(String[] args) {

        Model model = Model.getModel();
        model.getReportDateList().add(new Date(2013, 12, 0));
        new Report();
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        double w,h;
        
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        w = pageFormat.getImageableWidth();
        h = pageFormat.getHeight();

        this.setSize((int) w, (int) h);
        this.setPreferredSize(new Dimension((int) w, (int) h));
        this.doLayout();

        this.printAll(g2d);
        return PAGE_EXISTS;

    }
    
    private Report getThis(){
        return this;
    }
    
    
}
