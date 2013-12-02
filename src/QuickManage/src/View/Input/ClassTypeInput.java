/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import Model.ClassType;
import Model.Model;
import Model.Teacher;
import Model.Ultility;
import View.ClassTypeGUI;
import View.CustomComponent.CPanel;
import View.TeacherGUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author s3255203
 */
public final class ClassTypeInput extends JDialog implements Ultility {

    private Model model = Model.getModel();
    ResourceBundle resourse = model.getResources();
    public String[] ClassNameData = {"Dance", "Organ", "Vorcal", "Painting", "Guitar", "Violin", "Piano"};
    private JComboBox cbClassName = new JComboBox(ClassNameData);
    public String[] ClassTypeData = {resourse.getString("INVIDUAL_TXT"), resourse.getString("DUAL_TXT"), resourse.getString("GROUP_TXT")};
    private JComboBox cbClassType = new JComboBox(ClassTypeData);
    public String[] weekData = {"1", "2"};
    private JComboBox lessonWeek = new JComboBox(weekData);
    private JTextField feeLesson45 = new JTextField(5);
    private JTextField feeLesson60 = new JTextField(5);
    private JTextArea txtaInfo = new JTextArea(3, 1);
    private ClassTypeGUI ctGUI;

    public ClassTypeInput(ClassTypeGUI ctg) {

        super();
        setTitle("Add new class type");
        add(getClassTypeInputPanel());
        this.ctGUI = ctg;
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    public CPanel getClassTypeInputPanel() {

        CPanel mainPanel = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
        CPanel contentPanel = new CPanel(new BorderLayout());
        CPanel labelPanel = new CPanel(new GridLayout(6, 1));
        CPanel textPanel = new CPanel(new GridLayout(6, 1));
        CPanel wrapPanel = new CPanel(new BorderLayout());
        CPanel infoPanel = new CPanel(new BorderLayout());

        wrapPanel.add(new JScrollPane(txtaInfo), BorderLayout.CENTER);

        labelPanel.add(new JLabel(resourse.getString("NAME_TXT")));
        textPanel.add(cbClassName);
        labelPanel.add(new JLabel(resourse.getString("TYPE_TXT")));
        textPanel.add(cbClassType);
        labelPanel.add(new JLabel(resourse.getString("LESSON_WEEK_TXT")));
        textPanel.add(lessonWeek);
        labelPanel.add(new JLabel(resourse.getString("FEE_45_TXT")));
        textPanel.add(feeLesson45);
        labelPanel.add(new JLabel(resourse.getString("FEE_60_TXT")));
        textPanel.add(feeLesson60);
        infoPanel.add(new JLabel(resourse.getString("INFO_TXT")), BorderLayout.WEST);
        infoPanel.add(wrapPanel, BorderLayout.CENTER);

        //create button panel
        CPanel buttonPanel = new CPanel();
        //create Add button
        JButton addButton = new JButton(resourse.getString("ADD_TXT"));


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ctName = cbClassName.getSelectedItem().toString();
                int ctType = cbClassType.getSelectedIndex();
                int ctWeek = lessonWeek.getSelectedIndex() + 1;
                String ct45 = feeLesson45.getText().trim();
                String ct60 = feeLesson60.getText().trim();
                String ctInfo = txtaInfo.getText().trim();
                String message = "";


                if (ct45.isEmpty() && ct60.isEmpty()) {
                    message = resourse.getString("EMPTY_CLASSTYPE_FEE45_OR_FEE60");
                } else {
                    if (ct45.isEmpty()) {
                        ct45 = "0";
                    } else if (!ct45.matches("^[0-9.]*$")) {
                        message += resourse.getString("INVALID_FEE45");
                    }
                    if (ct60.isEmpty()) {
                        ct60 = "0";
                    } else if (!ct60.matches("^[0-9.]*$")) {
                        message += resourse.getString("INVALID_FEE60");
                    }
                }
                if (ctInfo.isEmpty()) {
                    message = "";
                } else if (!ctInfo.matches("^[a-zA-Z0-9]$")) {
                    message += "";
                }

                if (message.isEmpty()) {
                    model.addClassType(new ClassType(model.newClassTypeId(), ctName, ctType, ctWeek, Float.parseFloat(ct45),
                            Float.parseFloat(ct60), ctInfo));
                    JOptionPane.showMessageDialog(null, "Class type has been added successful!");
                    ctGUI.updateContainerPanel();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });

        //create cancel button and add dispose listener
        JButton cancelButton = new JButton(resourse.getString("CANCEL_TXT"));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(labelPanel, BorderLayout.WEST);
        contentPanel.add(textPanel, BorderLayout.CENTER);
        contentPanel.add(infoPanel, BorderLayout.SOUTH);
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createTitledBorder("ADD CLASS TYPE"));

        return mainPanel;

    }

    //EDIT CLASS TYPE
    public ClassTypeInput(ClassType ct, ClassTypeGUI ctg) {

        super();
        setTitle("Add new class type");
        add(editClassTypeInputPanel(ct));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.ctGUI = ctg;
    }

    public CPanel editClassTypeInputPanel(final ClassType ct) {


        CPanel mainPanel = new CPanel(new BorderLayout(),INPUT_PANEL_PIC);
        CPanel contentPanel = new CPanel(new BorderLayout());
        CPanel labelPanel = new CPanel(new GridLayout(6, 1));
        CPanel textPanel = new CPanel(new GridLayout(6, 1));
        CPanel wrapPanel = new CPanel(new BorderLayout());
        CPanel infoPanel = new CPanel(new BorderLayout());

        wrapPanel.add(new JScrollPane(txtaInfo), BorderLayout.CENTER);

        labelPanel.add(new JLabel(resourse.getString("NAME_TXT")));
        textPanel.add(cbClassName);
        cbClassName.setSelectedItem(ct.getName());
        labelPanel.add(new JLabel(resourse.getString("TYPE_TXT")));
        textPanel.add(cbClassType);

        cbClassType.setSelectedIndex(ct.getType());
        labelPanel.add(new JLabel(resourse.getString("LESSON_WEEK_TXT")));
        textPanel.add(lessonWeek);
        lessonWeek.setSelectedItem(ct.getLessonWeek() + "");
        labelPanel.add(new JLabel(resourse.getString("FEE_45_TXT")));
        textPanel.add(feeLesson45);
        feeLesson45.setText(ct.getFeeLesson45() + "");
        labelPanel.add(new JLabel(resourse.getString("FEE_60_TXT")));
        textPanel.add(feeLesson60);
        feeLesson60.setText(ct.getFeeLesson60() + "");
        infoPanel.add(new JLabel(resourse.getString("INFO_TXT")), BorderLayout.WEST);
        infoPanel.add(wrapPanel, BorderLayout.CENTER);
        txtaInfo.setText(ct.getInfo());
        //create button panel
        CPanel buttonPanel = new CPanel();
        //create Add button
        JButton editButton = new JButton(resourse.getString("SAVE_TXT"));


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ctName = cbClassName.getSelectedItem().toString();
                int ctType = cbClassType.getSelectedIndex();
                int ctWeek = lessonWeek.getSelectedIndex() + 1;
                String ct45 = feeLesson45.getText().trim();
                String ct60 = feeLesson60.getText().trim();
                String ctInfo = txtaInfo.getText().trim();
                String message = "";

                if (ct45.isEmpty() && ct60.isEmpty()) {
                    message = resourse.getString("EMPTY_CLASSTYPE_FEE45_OR_FEE60");
                } else {
                    if (ct45.isEmpty()) {
                        ct45 = "0";
                    } else if (!ct45.matches("^[0-9.]*$")) {
                        message += resourse.getString("INVALID_FEE45");
                    }
                    if (ct60.isEmpty()) {
                        ct60 = "0";
                    } else if (!ct60.matches("^[0-9.]*$")) {
                        message += resourse.getString("INVALID_FEE60");
                    }
                }
                if (ctInfo.isEmpty()) {
                    message = "";
                } else if (!ctInfo.matches("^[a-zA-Z0-9]*")) {
                    message += "";
                }

                if (message.isEmpty()) {
                    ct.setName(ctName);
                    ct.setType(ctType);
                    ct.setLessonWeek(ctWeek);
                    ct.setFeeLesson45(Float.parseFloat(ct45));
                    ct.setFeeLesson60(Float.parseFloat(ct60));
                    JOptionPane.showMessageDialog(null, "Account has been changed successful!");
                    ctGUI.updateContainerPanel();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });


        //create cancel button and add dispose listener
        JButton cancelButton = new JButton(resourse.getString("CANCEL_TXT"));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(labelPanel, BorderLayout.WEST);
        contentPanel.add(textPanel, BorderLayout.CENTER);
        contentPanel.add(infoPanel, BorderLayout.SOUTH);
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createTitledBorder(resourse.getString("EDIT_TXT")));



        return mainPanel;

    }
}
