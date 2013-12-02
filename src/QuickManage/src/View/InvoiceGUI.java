/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Main.Main;
import Model.Invoice;
import Model.Model;
import Model.Ultility;
import View.CustomComponent.CButton;
import View.CustomComponent.CLabel;
import View.CustomComponent.CPanel;
import View.Input.InvoiceFrame;
import View.Input.Report;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author anh
 */
public class InvoiceGUI extends CPanel implements Ultility {

    private String username;
    private CPanel topPanel, menuPanel, mainPanel, invoicePanel, contentPanel, botPanel, smallPanel;
    private JMenu fileMenu, viewMenu, helpMenu, toolMenu;
    private JMenuItem exitAction, switchViewAction, aboutAction, userGuideAction, logoutAction;
    private JMenuItem editAction;
    private CButton accountBt, teacherBt, studentBt, classBt, invoiceBt, reportBt, classTypeBt;
    private CButton editBt, logoutBt;
    private ArrayList<Invoice> selectedItems = new ArrayList<>();
    private static int index = 0;
    Model model = Model.getModel();
    ResourceBundle resourse = model.getResources();

    public InvoiceGUI(String username) {

        super(new BorderLayout(), BACKGROUND_PIC);
        this.username = username;
        topPanel = new CPanel(TOP_PANEL_PIC);
        mainPanel = new CPanel(new BorderLayout());
        menuPanel = new CPanel(new BorderLayout());

        menuPanel.add(getMenuBar(), BorderLayout.NORTH);
        menuPanel.add(getToolBar(), BorderLayout.CENTER);

        checkButton();

        contentPanel = getContentPanel();
        topPanel.add(menuPanel);
        mainPanel.add(getLeftPanel(), BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

    }

    private CPanel getLeftPanel() {
        CPanel leftPanel = new CPanel(new GridLayout(8, 1), LEFT_PANEL_PIC);
        accountBt = new CButton(resourse.getString("ACCOUNT_TXT"));
        teacherBt = new CButton(resourse.getString("TEACHER_TXT"));
        studentBt = new CButton(resourse.getString("STUDENT_TXT"));
        classBt = new CButton(resourse.getString("CLASS_TXT"));
        invoiceBt = new CButton(resourse.getString("INVOICES_TXT"));
        classTypeBt = new CButton(resourse.getString("CLASS_TYPE_TXT"));


        CPanel thisPanel = new CPanel(new BorderLayout(), ITEM_MENU_PIC);
        leftPanel.add(accountBt);


        leftPanel.add(teacherBt);
        leftPanel.add(studentBt);
        leftPanel.add(classTypeBt);
        leftPanel.add(classBt);
        thisPanel.add(invoiceBt);
        leftPanel.add(thisPanel);


        accountBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new AccountsGUI(username));
            }
        });

        teacherBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new TeacherGUI(username));
            }
        });

        studentBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new StudentGUI(username));
            }
        });
        classBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new ClassGUI(username));
            }
        });

        invoiceBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new InvoiceGUI(username));
            }
        });
        classTypeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new ClassTypeGUI(username));
            }
        });



        leftPanel.setBorder(new EmptyBorder(0, 5, 0, 10));

        return leftPanel;
    }

    private JToolBar getToolBar() {
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
//

        logoutBt = new CButton(LOG_OUT_BUTTON);
        editBt = new CButton(EDIT_BUTTON);
        reportBt = new CButton(REPORT_BUTTON);
//

        toolBar.add(editBt);
        toolBar.add(reportBt);
        JButton langBt = new JButton(model.getResources().getString("LANGUAGE_TXT"));
        toolBar.add(langBt);
        langBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.switchLanguage();
                Main.changePanel(new InvoiceGUI(username));
            }
        });
        toolBar.add(logoutBt);


//

        logoutBt.setToolTipText(resourse.getString("LOGOUT_TXT"));
        editBt.setToolTipText(resourse.getString("EDIT_TXT"));
        reportBt.setToolTipText(resourse.getString("REPORT_TXT"));

        reportBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report();
            }
        });

        editBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InvoiceFrame(selectedItems.get(0), username);
                updateInvoicePanel();
                checkButton();
            }
        });

        logoutBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new LoginGUI());
            }
        });

        toolBar.setOpaque(false);
        toolBar.setBorder(new EmptyBorder(5, 0, 0, 0));
        return toolBar;
    }

    //menu bar and Menu
    private JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        fileMenu = new JMenu(resourse.getString("FILE_MENU"));
        viewMenu = new JMenu(resourse.getString("VIEW_MENU"));
        helpMenu = new JMenu(resourse.getString("HELP_MENU"));
        toolMenu = new JMenu(resourse.getString("TOOL_MENU"));

        //JMenuItem
        exitAction = new JMenuItem(resourse.getString("EXIT_TXT"));
        switchViewAction = new JMenuItem(resourse.getString("SWITCH_TXT"));
        aboutAction = new JMenuItem(resourse.getString("ABOUT_TXT"));
        userGuideAction = new JMenuItem(resourse.getString("USER_GUIDE_TXT"));
//        addAction = new JMenuItem(ADD_TXT);
        editAction = new JMenuItem(resourse.getString("EDIT_TXT"));
//        deleteAction = new JMenuItem(DELETE_TXT);
//        deleteAllAction = new JMenuItem(DELETE_ALL_TXT);
//        activeAction = new JMenuItem(ACTIVE_TXT);
        logoutAction = new JMenuItem(resourse.getString("LOGOUT_TXT"));

        editAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InvoiceFrame(selectedItems.get(0), username);
                updateInvoicePanel();
                checkButton();
            }
        });

        exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (getDialogConfirmation(resourse.getString("QUIT_CONFRIM_TXT")) == JOptionPane.YES_OPTION) {
                    try {
                        model.saveAllData();
                        System.exit(0);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TeacherGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        logoutAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new LoginGUI());
            }
        });

        userGuideAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String htmlFilePath = "userguide/index.html";
                File htmlFile = new File(htmlFilePath);
                try {
                    Desktop.getDesktop().browse(htmlFile.toURI());
                } catch (IOException ex) {
                    Logger.getLogger(AccountsGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Desktop.getDesktop().open(htmlFile);
                } catch (IOException ex) {
                    Logger.getLogger(AccountsGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        //add shorcut

        logoutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
        exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        aboutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        userGuideAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));

        //Combine Menu

        fileMenu.add(logoutAction);
        fileMenu.add(exitAction);
        fileMenu.add(editAction);
        viewMenu.add(switchViewAction);
        helpMenu.add(aboutAction);
        helpMenu.add(userGuideAction);


        menuBar.add(fileMenu);
        menuBar.add(toolMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        menuBar.setOpaque(false);


        return menuBar;
    }

    private CPanel getContentPanel() {
//        contentPanel = new CPanel(new GridLayout(1, 1));
        contentPanel = new CPanel(new GridLayout(1, 1));
        smallPanel = new CPanel(new BorderLayout());

        invoicePanel = getInvoicePanel();
        botPanel = getBotPanel();
        smallPanel.add(invoicePanel, BorderLayout.CENTER);
        smallPanel.add(botPanel, BorderLayout.SOUTH);

        contentPanel.add(smallPanel);
        contentPanel.updateUI();


        return contentPanel;

    }

    private CPanel getInvoicePanel() {
        int a = 0;
        invoicePanel = new CPanel(new GridLayout(10, 1));
        if (index < getTotalIndex()) {
            a = 9;
        } else {
            a = model.getInvoiceList().size() % 9;
        }
        for (int i = 0; i < a; i++) {
            invoicePanel.add(getInvoiceDetailPanel(model.getInvoiceList().get(((index) * 9) + i)));
        }

        invoicePanel.updateUI();

        return invoicePanel;
    }

    private CPanel getInvoiceDetailPanel(final Invoice a) {
//        final CPanel itemPanel = new CPanel(new GridLayout(2, 3), new Color(255, 255, 255, 28));
        final CPanel mPanel = new CPanel(new BorderLayout(), ITEM_PANEL_PIC);
        final CPanel itemPanel = new CPanel(new GridLayout(2, 3));
        mPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mPanel.isIsSelected() == false) {
                    mPanel.setImage(SELECTED_ITEM_PANEL_PIC);
//                    itemPanel.setBackground(Color.LIGHT_GRAY);
                    mPanel.updateUI();
                    selectedItems.add(a);
                    mPanel.setIsSelected(true);
                    checkButton();
                } else {
//                  itemPanel.setBackground(new Color(255, 255, 255, 28));
                    mPanel.setImage(ITEM_PANEL_PIC);
                    mPanel.updateUI();
                    selectedItems.remove(a);
                    mPanel.setIsSelected(false);
                    checkButton();
                }
            }
        });

        itemPanel.add(new CLabel("ID: " + a.getId(), TEXT_SIZE));
        try {
            itemPanel.add(new CLabel("Start Date: " + model.dateToString(a.getStartDate()), TEXT_SIZE));
            itemPanel.add(new CLabel("End Date: " + model.dateToString(a.getEndDate()), TEXT_SIZE));
        } catch (ParseException ex) {
            Logger.getLogger(InvoiceGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        itemPanel.add(new CLabel("TotalFee: " + a.getTotalFee(), TEXT_SIZE));

        itemPanel.updateUI();

        mPanel.add(itemPanel);

        return mPanel;
    }

    private CPanel getBotPanel() {
        botPanel = new CPanel(new BorderLayout());

        CPanel p = new CPanel();

        model.getInvoiceList().size();
        for (int i = 0; i <= getTotalIndex(); i++) {
            p.add(new CButton(i, index, this));
        }
        botPanel.add(p, BorderLayout.CENTER);
        return botPanel;
    }

    private int getTotalIndex() {
        int a = 0, b = 0, c = 0, d = 0;
        a = model.getInvoiceList().size();
        b = 9;
        if (a > 9) {
            c = a % b;
            d = (a - c) / b;
        }
        return d;
    }

    private void checkButton() {
        if (model.getInvoiceList().isEmpty()) {
            editBt.setEnabled(false);

            editAction.setEnabled(false);

        } else if (selectedItems.size() == 1) {
            editBt.setEnabled(true);

            editAction.setEnabled(true);

        } else if (selectedItems.size() > 1) {
            editBt.setEnabled(false);

            editAction.setEnabled(false);

        } else if (selectedItems.isEmpty()) {
            editBt.setEnabled(false);

            editAction.setEnabled(false);

        }
    }

    public static void setIndex(int index) {
        InvoiceGUI.index = index;
    }

    public void updateInvoicePanel() {
        selectedItems.clear();
        smallPanel.removeAll();
        smallPanel.updateUI();
        invoicePanel = getInvoicePanel();
        botPanel = getBotPanel();
        smallPanel.add(invoicePanel, BorderLayout.CENTER);
        smallPanel.add(botPanel, BorderLayout.SOUTH);

    }

    private int getDialogConfirmation(String message) {
        int choose = JOptionPane.showConfirmDialog(null, message, "Information", JOptionPane.YES_NO_OPTION);
        return choose;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    private InvoiceGUI getThis() {
        return this;
    }
}
