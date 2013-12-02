/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import Model.ClassType;
import Model.Classes;
import Model.Model;
import Model.Ultility;
import View.ClassGUI;
import View.CustomComponent.CPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


// Calendar class
/**
 *
 * @author s3255203
 */
public class ClassInput extends JDialog implements Ultility {

    private JTextField className = new JTextField(15);
//    public String[] skillData = {"piano", "guitar", "photography", "hiphop", "violin", "organ", "ballet", "painting", "singing"};
//    private JComboBox skillList = new JComboBox(skillData);
    private JComboBox skillList = new JComboBox();
    private JTextField classTextbook = new JTextField(15);
//    private JTextField classFee = new JTextField(15);
    private JTextField classStartDate = new JTextField(15);
    private JButton calendar1 = new JButton("calendar");
    private JButton calendar2 = new JButton("calendar");
    private JTextField classEndDate = new JTextField(15);
    public String[] hourData = {"45", "60"};
    public double hourArray[] = {1, 1.5, 2, 2.5, 3};
    private JComboBox hourList = new JComboBox(hourData);
    private Model model = Model.getModel();
    private ClassGUI cGUI;
    private String id = "";
    ResourceBundle resourse = model.getResources();
//    private ArrayList<Classes> classList;

    public ClassInput(ClassGUI c) {

        super();
        cGUI = c;
        setTitle("Add new class");
        add(getAddClassInputPanel());
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private CPanel getAddClassInputPanel() {

        CPanel mainPanel = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
        CPanel contentPanel = new CPanel(new BorderLayout());
        CPanel labelPanel = new CPanel(new GridLayout(8, 2));
        CPanel textPanel = new CPanel(new GridLayout(8, 2));
        CPanel startDatePanel = new CPanel(new FlowLayout());
        CPanel endDatePanel = new CPanel(new FlowLayout());

        labelPanel.add(new JLabel(resourse.getString("NAME_TXT")));
        textPanel.add(className);
        labelPanel.add(new JLabel(resourse.getString("SUBJECT_TXT")));
        textPanel.add(skillList);
        for (ClassType ct : model.getClassTypeList()) {
            skillList.addItem(ct);
        }
        labelPanel.add(new JLabel(resourse.getString("TEXTBOOK_TXT")));
        textPanel.add(classTextbook);
//        labelPanel.add(new JLabel("Tuition fee(VND) :"));
//        textPanel.add(classFee);
        labelPanel.add(new JLabel(resourse.getString("START_DATE_TXT")));
        textPanel.add(startDatePanel);
        startDatePanel.add(classStartDate);
        classStartDate.setEditable(false);
        startDatePanel.add(calendar1);
        labelPanel.add(new JLabel(resourse.getString("END_DATE_TXT")));
        textPanel.add(endDatePanel);
        endDatePanel.add(classEndDate);
        classEndDate.setEditable(false);
        endDatePanel.add(calendar2);
        labelPanel.add(new JLabel(resourse.getString("TIME_CLASS_TXT")));
        textPanel.add(hourList);



        //create button panel
        CPanel buttonPanel = new CPanel();

        //create Add button
        JButton addButton = new JButton(resourse.getString("NEXT_TXT"));

        
                
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cName = className.getText().trim();
                String cTextBook = classTextbook.getText().trim();
//                String cFee = classFee.getText().trim();
                String cStartDate = classStartDate.getText().trim();
                String cEndDate = classEndDate.getText().trim();
                ClassType cSkill = (ClassType)skillList.getSelectedItem();
                int cHour = hourList.getSelectedIndex();
                if (cHour == 0) {
                    cHour = 45;
                } else {
                    cHour = 60;
                }
                //double cFee = (Double.parseDouble(classFee));
                String message = "";

                if (cName.isEmpty()) {
                    message += resourse.getString("EMPTY_CLASS_NAME_TEXT_ERROR");
                } else if (!cName.matches("^[a-zA-Z0-9 ]*")) {
                    message += resourse.getString("INVALID_CLASSNAME_FORMAT_ERROR");
                } else if (model.findClassByClassName(cName) == true) {
                    message += resourse.getString("SAME_CLASSNAME_ERROR");
                }
                if (cTextBook.isEmpty()) {
                    message += resourse.getString("EMPTY_CLASS_TEXTBOOK_TEXT_ERROR");
                } else if (!cTextBook.matches("^[a-zA-Z0-9 ]*")) {
                    message += resourse.getString("INVALID_TEXTBOOK_FORMAT_ERROR");
                }
//                if (cFee.isEmpty()) {
//                    message += EMPTY_CLASS_FEE_TEXT_ERROR;
//                } else if (!cFee.matches("[0-9]*|\\d+\\.\\d+")) {
//                    message += INVALID_CLASSFEE_FORMAT_ERROR;
//                }
                if (cStartDate.isEmpty()) {
                    message += resourse.getString("EMPTY_CLASS_STARTDATE_TEXT_ERROR");
                }
                if (cEndDate.isEmpty()) {
                    message += resourse.getString("EMPTY_CLASS_ENDDATE_TEXT_ERROR");
                }
                if (message.isEmpty()) {
                    try {
                        System.out.println("String start: " + cStartDate);
                        System.out.println("String end: " + cEndDate);
                        Date start = model.textToDate(cStartDate);
                        Date end = model.textToDate(cEndDate);
                        System.out.println("s: " + start + ", end: " + end);
                        
                        id = model.newClassId();
                        model.addClass(new Classes(id, cName, cTextBook, start, end, cSkill.getId(), cHour));
                    } catch (ParseException ex) {
                        Logger.getLogger(ClassInput.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Class has been added successful!");
                    cGUI.updateContainerPanel();
                    new ClassRoomFrame(model.findClassById(id));
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });

        //create cancel button and add dispose listener
        JButton cancelButton = new JButton(resourse.getString("CANCEL_TXT"));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        calendar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classStartDate.setText(new Calendar().setPickedDate());
            }
        });

        calendar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classEndDate.setText(new Calendar().setPickedDate());
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(labelPanel, BorderLayout.WEST);
        contentPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.setBorder(BorderFactory.createTitledBorder("ADD CLASS"));
        return mainPanel;

    }

    // EDIT CLASS
    public ClassInput(Classes c, ClassGUI cg) {

        super();
        cGUI = cg;
        setTitle("Edit class");
        add(editClassInputPanel(c));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private CPanel editClassInputPanel(final Classes c) {

        CPanel mainPanel = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
        CPanel contentPanel = new CPanel(new BorderLayout());
        CPanel labelPanel = new CPanel(new GridLayout(8, 2));
        CPanel textPanel = new CPanel(new GridLayout(8, 2));
        CPanel startDatePanel = new CPanel(new FlowLayout());
        CPanel endDatePanel = new CPanel(new FlowLayout());


        labelPanel.add(new JLabel(resourse.getString("NAME_TXT")));
        textPanel.add(className);
        className.setText(c.getName());
        labelPanel.add(new JLabel(resourse.getString("SUBJECT_TXT")));
//        skillList.setSelectedIndex(c.getSkill());
        for (ClassType ca : model.getClassTypeList()) {
            skillList.addItem(ca);
        }
        skillList.setSelectedItem(model.findClassTypeById(c.getClassTypeid()));
        
        textPanel.add(skillList);
        labelPanel.add(new JLabel(resourse.getString("TEXTBOOK_TXT")));
        textPanel.add(classTextbook);
        classTextbook.setText(c.getTextbook());
//        labelPanel.add(new JLabel("Tuition fee: :"));
//        textPanel.add(classFee);
//        classFee.setText(c.getFee() + "");

        labelPanel.add(new JLabel(resourse.getString("START_DATE_TXT")));
        textPanel.add(startDatePanel);
        startDatePanel.add(classStartDate);
        classStartDate.setEditable(false);
        startDatePanel.add(calendar1);
        classStartDate.setText(new SimpleDateFormat(
                "dd-MM-yyyy").format(c.getStartDate()));

        labelPanel.add(new JLabel(resourse.getString("END_DATE_TXT")));
        textPanel.add(endDatePanel);
        endDatePanel.add(classEndDate);
        classEndDate.setEditable(false);
        endDatePanel.add(calendar2);
        classEndDate.setText(new SimpleDateFormat(
                "dd-MM-yyyy").format(c.getEndDate()));

        labelPanel.add(new JLabel(resourse.getString("TIME_CLASS_TXT")));
        textPanel.add(hourList);
        int hourr = c.getHour();
        if (hourr == 45) {
            hourList.setSelectedIndex(0);
        } else {
            hourList.setSelectedIndex(1);
        }
//        System.out.println(c.getHour());
//        hourList.setSelectedItem((Double.toString(c.getHour())));


        calendar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classStartDate.setText(new Calendar().setPickedDate());
            }
        });

        calendar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classEndDate.setText(new Calendar().setPickedDate());
            }
        });

        //create button panel
        CPanel buttonPanel = new CPanel();

        //create Edit button
        JButton editButton = new JButton(resourse.getString("SAVE_TXT"));

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cName = className.getText().trim();
                String cTextBook = classTextbook.getText().trim();
//                String cFee = classFee.getText().trim();
                String cStartDate = classStartDate.getText().trim();
                String cEndDate = classEndDate.getText().trim();
                String message = "";

                if (cName.isEmpty()) {
                    message += resourse.getString("EMPTY_CLASS_NAME_TEXT_ERROR");
                } else if (!cName.matches("^[a-zA-Z0-9 ]*")) {
                    message += resourse.getString("INVALID_CLASSNAME_FORMAT_ERROR");
                } else if (!c.getName().equals(cName)) {
                    if (model.findClassByClassName(cName) == true) {
                        message += resourse.getString("SAME_CLASSNAME_ERROR");
                    }
                }
                if (cTextBook.isEmpty()) {
                    message += resourse.getString("EMPTY_CLASS_TEXTBOOK_TEXT_ERROR");
                } else if (!cTextBook.matches("^[a-zA-Z0-9 ]*")) {
                    message += resourse.getString("INVALID_TEXTBOOK_FORMAT_ERROR");
                }
//                if (cFee.isEmpty()) {
//                    message += EMPTY_CLASS_FEE_TEXT_ERROR;
//                } else if (!cFee.matches("[0-9]*|\\d+\\.\\d+")) {
//                    message += INVALID_CLASSFEE_FORMAT_ERROR;
//                }
                if (cStartDate.isEmpty()) {
                    message += resourse.getString("EMPTY_CLASS_STARTDATE_TEXT_ERROR");
                }
                if (cEndDate.isEmpty()) {
                    message += resourse.getString("EMPTY_CLASS_ENDDATE_TEXT_ERROR");
                }
                if (message.isEmpty()) {
                    try {
                        c.setName(cName);
                        c.setClassTypeid( ((ClassType)skillList.getSelectedItem()).getId() );
                        
//                        c.setSkill(skillList.getSelectedIndex());
                        c.setTextbook(cTextBook);
//                        c.setFee(Double.parseDouble(cFee));
                        c.setStartDate(model.textToDate(cStartDate));
                        c.setEndDate(model.textToDate(cEndDate));
//                        c.setHour(hourArray[hourList.getSelectedIndex()]);
                        int i = hourList.getSelectedIndex();
                        if (i == 0) {
                            i = 45;
                        } else {
                           i = 60 ; 
                        }
                        c.setHour(i);
                        JOptionPane.showMessageDialog(null, "Class information has been changed successful!");
                        cGUI.updateContainerPanel();
                        
                        dispose();
                    } catch (ParseException ex) {
                        Logger.getLogger(ClassInput.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });
        //create cancel button and add dispose listener
        JButton cancelButton = new JButton(resourse.getString("CANCEL_TXT"));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(labelPanel, BorderLayout.WEST);
        contentPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createTitledBorder(resourse.getString("EDIT_TXT")));
        return mainPanel;
    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
    
    
}
final class Calendar {

    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    ;
        JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JDialog d;
    JButton[] button = new JButton[49];

    public Calendar() {
        d = new JDialog();
        d.setModal(true);
        String[] header = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
        CPanel p1 = new CPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(430, 120));

        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white);
            if (x > 6) {
                button[x].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        d.dispose();
                    }
                });
            }
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.red);
            }
            p1.add(button[x]);
        }
        CPanel p2 = new CPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<< Previous");
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                month--;
                displayDate();
            }
        });
        p2.add(previous);
        p2.add(l);
        JButton next = new JButton("Next >>");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate();
            }
        });
        p2.add(next);
        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        // d.setLocationRelativeTo(parent);
        displayDate();
        d.setVisible(true);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public void displayDate() {
        for (int x = 7; x < button.length; x++) {
            button[x].setText("");
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
            button[x].setText("" + day);
        }
        l.setText(sdf.format(cal.getTime()));
        d.setTitle("Calendar");
    }

    public String setPickedDate() {
        if (day.equals("")) {
            return day;
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "dd-MM-yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
    
    
}
