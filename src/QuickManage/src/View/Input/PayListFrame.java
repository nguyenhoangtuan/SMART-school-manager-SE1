/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import Model.*;
import View.CustomComponent.CPanel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author anh
 */
public class PayListFrame extends JDialog implements Ultility {

    private Vector<String> collumName;
    private Vector<Vector> dataVec;
    JTable table;
    Model m = Model.getModel();
    JScrollPane scrollPane;
    private CPanel mainPn;

    public PayListFrame() {
        mainPn = new CPanel(INPUT_PANEL_PIC);

        collumName = new Vector<>();
        dataVec = new Vector<>();
        collumName.add("Pay Slip Id");
        collumName.add("Teacher Id");
        collumName.add("Teacher Name");
        collumName.add("Total Salary");
        collumName.add("Action");

        for (final PaySlip ps : m.getPaySlipList()) {
            Vector v = new Vector();
            v.add(ps.getId());
            v.add(m.findTeacherByPayID(ps.getId()).getId());
            v.add(m.findTeacherByPayID(ps.getId()).getLastName());
            v.add(ps.getTotalSalary());
            dataVec.add(v);
        }
        table = new JTable(dataVec, collumName);
        table.setEnabled(false);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Left mouse click
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // Do something
                } // Right mouse click
                else if (SwingUtilities.isRightMouseButton(e)) {
                    // get the coordinates of the mouse click

                    Point p = e.getPoint();

                    // get the row index that contains that coordinate
                    int rowNumber = table.rowAtPoint(p);
                    // Get the ListSelectionModel of the JTable
//                    if (e.isPopupTrigger()) {
                    doPop(e, rowNumber);
                    
//                    }
                }


            }

            private void doPop(MouseEvent e, final int row) {
                final JPopupMenu menu = new JPopupMenu();
                JMenuItem anItem1 = new JMenuItem("More Detail");
                menu.add(anItem1);
                menu.show(e.getComponent(), e.getX(), e.getY());
                anItem1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new PaySlipDetailJDialog(m.getPaySlipList().get(row));
                    }
                });
            }
        });


        table.setOpaque(false);
        scrollPane = new JScrollPane(table);
        mainPn.add(scrollPane);
        this.add(mainPn);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        setSize(800, 600);
    }
}
