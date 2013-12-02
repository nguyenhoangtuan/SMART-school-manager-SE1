/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import FileData.FileProcess;
import Model.ClassType;
import Model.Model;
import Model.Teacher;
import Model.Ultility;
import View.CustomComponent.CPanel;
import View.TeacherGUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author s3255203
 */
public class TeacherInput extends JDialog implements Ultility {

    private Model model = Model.getModel();
    private ArrayList<Double> payRateList = new ArrayList<Double>();
    ResourceBundle resourse = model.getResources();
    private final int limit = 3;
    private final int limit1 = 4;
    private JTextField teacherFirstName = new JTextField(5);
    private JTextField teacherLastName = new JTextField(5);
    public String[] codeData = {"08", "090", "091", "092", "093", "094", "095", "096", "097", "098", "099", "0123", "0124", "0125", "0126", "0127", "0128", "0129"};
    private JComboBox cbCode = new JComboBox(codeData);
    private JTextField teacherPhone1 = new JTextField(3);
    private JTextField teacherPhone2 = new JTextField(3);
    private JTextArea teacherAddress = new JTextArea(3, 1);
    private JTextField teacherEmail = new JTextField(5);
    private JRadioButton teacherMale = new JRadioButton(resourse.getString("MALE_TXT"), true);
    private JRadioButton teacherFemale = new JRadioButton(resourse.getString("FEMALE_TXT"));
    private ButtonGroup genderGroup = new ButtonGroup();
    private JTextField teacherPicture = new JTextField(10);
    private JButton picButton = new JButton(resourse.getString("UPLOAD_TXT"));
    private JTextField teacherStartYear = new JTextField(5);
    public String[] skillData = {"piano", "guitar", "photography", "hiphop", "violin", "organ", "ballet", "painting", "singing"};
    private JComboBox teacherSkillList = new JComboBox();
    private TeacherGUI tGUI;
    private JLabel teacherSkillArray = new JLabel();
    private JLabel teacherPayrateArray = new JLabel();
    private String tempSkill = "";
    private ArrayList<String> skillString = new ArrayList<String>();
    private ArrayList<Integer> skillInteger = new ArrayList<Integer>();
    private ArrayList<ClassType> classTypeList = new ArrayList<ClassType>();
    private ArrayList<String> stringClassTypeList = new ArrayList<String>();
    private JButton clear = new JButton(resourse.getString("CLEAR_TXT"));
    private JTextField skillPayRate = new JTextField(7);
    private JButton addPayRate = new JButton(resourse.getString("ADD_TXT"));

    public TeacherInput(TeacherGUI t) {

        super();
        tGUI = t;
        setTitle("Add new teacher");
        add(getAddTeacherInputPanel());
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        classTypeList = model.getClassTypeList();
        for (ClassType cla : classTypeList) {
            System.out.println(cla.getId());
        }
    }

    private CPanel getAddTeacherInputPanel() {
        classTypeList = model.getClassTypeList();
        stringClassTypeList = model.generateSkill();

        skillString.removeAll(skillString);
        teacherSkillArray.setText("");
        skillInteger.removeAll(skillInteger);

        CPanel mainPanel = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
        CPanel contentPanel = new CPanel(new BorderLayout());
        CPanel labelPanel = new CPanel(new GridLayout(13, 1));
        CPanel textPanel = new CPanel(new GridLayout(13, 1));
        CPanel addressPanel = new CPanel(new BorderLayout());
        CPanel phonePanel = new CPanel(new FlowLayout());
        CPanel picPanel = new CPanel(new FlowLayout());
        CPanel wrapPanel = new CPanel(new BorderLayout());
        CPanel wraphourRate = new CPanel(new FlowLayout());

        wraphourRate.add(skillPayRate);
        wraphourRate.add(addPayRate);

        phonePanel.add(cbCode);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(teacherPhone1);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(teacherPhone2);

        wrapPanel.add(new JScrollPane(teacherAddress), BorderLayout.CENTER);

        genderGroup.add(teacherMale);
        genderGroup.add(teacherFemale);

        labelPanel.add(new JLabel(resourse.getString("NAME_TXT")));
        textPanel.add(teacherFirstName);
        labelPanel.add(new JLabel(resourse.getString("LAST_NAME_TXT")));
        textPanel.add(teacherLastName);
        labelPanel.add(new JLabel(resourse.getString("PHONE_TXT")));
        textPanel.add(phonePanel);
        labelPanel.add(new JLabel(resourse.getString("EMAIL_TXT")));
        textPanel.add(teacherEmail);
        labelPanel.add(new JLabel(resourse.getString("GENDER_TXT")));
        textPanel.add(teacherMale);
        labelPanel.add(new JLabel(""));
        textPanel.add(teacherFemale);
        labelPanel.add(new JLabel(resourse.getString("START_YEAR_TXT")));
        textPanel.add(teacherStartYear);

        labelPanel.add(new JLabel(resourse.getString("IMAGE_TXT")));
        picPanel.add(teacherPicture);
        picPanel.add(picButton);
        teacherPicture.setEditable(false);
        teacherPicture.setText(DEFAULT_PHOTO);
        textPanel.add(picPanel);

        labelPanel.add(new JLabel(resourse.getString("SUBJECT_TXT")));
        textPanel.add(teacherSkillList);

        labelPanel.add(new JLabel(resourse.getString("HOURLY_PAY_TXT")));

        textPanel.add(wraphourRate);

        System.out.println(classTypeList.size());
        for (String cla : stringClassTypeList) {
            teacherSkillList.addItem(cla);
        }


        labelPanel.add(new JLabel(resourse.getString("SUBJECT_TXT")));
        textPanel.add(teacherSkillArray);
        labelPanel.add(new JLabel(resourse.getString("HOURLY_PAY_TXT")));
        textPanel.add(teacherPayrateArray);

        textPanel.add(clear);

        addressPanel.add(new JLabel(resourse.getString("ADDRESS_TXT")), BorderLayout.WEST);
        addressPanel.add(wrapPanel, BorderLayout.CENTER);


        addPayRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!skillPayRate.getText().isEmpty()) {
                    String s = skillPayRate.getText();
                    double pr = Double.parseDouble(s);
                    if (skillString.size() > payRateList.size()) {
                        payRateList.add(pr);
                        tempSkill = "";
                        for (Double item : payRateList) {
                            if (tempSkill.equals("")) {
                                tempSkill = item + "";
                            } else {
                                tempSkill = tempSkill + " - " + item;
                            }
                        }
                        teacherPayrateArray.setText(tempSkill);
                    }



                }
            }
        });

        //actionlistner for teacherlist
        teacherSkillList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = (String) teacherSkillList.getSelectedItem();
                int check = 0;
                for (String str : skillString) {
                    if (str.equals(temp)) {
                        check = 1;
                    }
                }
                if (check == 0) {
                    skillString.add((String) teacherSkillList.getSelectedItem());
                    tempSkill = "";
                    for (String item : skillString) {
                        if (tempSkill.equals("")) {
                            tempSkill = item;
                        } else {
                            tempSkill = tempSkill + " - " + item;
                        }
                    }
                    teacherSkillArray.setText(tempSkill);
                }

            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skillString.removeAll(skillString);
                teacherSkillArray.setText("");
                skillInteger.removeAll(skillInteger);

                payRateList.removeAll(payRateList);
                teacherPayrateArray.setText("");
//                for (Integer item : skillInteger) {
//                    System.out.println(item);
//                }
            }
        });

        // actionlistener for combobox
        cbCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                teacherPhone1.setText("");
                teacherPhone2.setText("");

                //  accountPhone2.removeKeyListener(accountPhone2.getKeyListeners());
                for (KeyListener kl : teacherPhone1.getKeyListeners()) {
                    teacherPhone1.removeKeyListener(kl);
                }

                if (cbCode.getSelectedItem().equals("08")) {
                    //      System.out.println("check");

                    teacherPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = teacherPhone1.getText();
                            System.out.println("check1");
                            if (value.length() == 4) {
                                teacherPhone2.requestFocus();
                            }
                        }
                    });
                    teacherPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });
                    teacherPhone2.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });
                } else {
                    teacherPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = teacherPhone1.getText();
                            System.out.println("check2");
                            if (value.length() == 3) {
                                teacherPhone2.requestFocus();
                            }
                        }
                    });

                    teacherPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    teacherPhone2.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });
                }
            }
        });
        cbCode.setSelectedIndex(0);
        //keep account phone in length of 4
        teacherPhone2.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if (getLength() + str.length() <= limit1) {
                    super.insertString(offs, str, a);
                }
            }
        });


        picButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showSaveDialog(null);
                System.out.println("0");
                if (option == JFileChooser.APPROVE_OPTION) {
//                    System.out.println("1");
//                    System.out.println(FileProcess.checkExt(chooser.getSelectedFile().getPath()));
                    if (FileProcess.checkExt(chooser.getSelectedFile().getPath())) {
                        String name = FileProcess.generateName(FileProcess.findExt(chooser.getSelectedFile().getName()));
                        String path = "Images/" + name;
                        FileProcess.copyFile(chooser.getSelectedFile().getPath(), path);
                        teacherPicture.setText(name);
                    }
                }
            }
        });

        //create button panel
        CPanel buttonPanel = new CPanel();

        //create Add button
        JButton addButton = new JButton(resourse.getString("ADD_TXT"));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tFirstName = teacherFirstName.getText().trim();
                String tLastName = teacherLastName.getText().trim();
                String tPhone = cbCode.getSelectedItem() + "-" + teacherPhone1.getText().trim() + "-" + teacherPhone2.getText().trim();
                String tAddress = teacherAddress.getText().trim();
                String tEmail = teacherEmail.getText().trim();
                String tStartYear = teacherStartYear.getText().trim();
                int tSkill = teacherSkillList.getSelectedIndex();
                boolean tgender = true;
                String tphoto = teacherPicture.getText();
                String message = "";

                if (teacherMale.isSelected()) {
                    tgender = MALE;
                } else {
                    tgender = FEMALE;
                }

                if (tFirstName.isEmpty()) {
                    message = resourse.getString("EMPTY_TEACHER_FIRSTNAME_ERROR");
                } else if (!tFirstName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_FIRSTNAME_FORMAT_ERROR");
                }
                if (tLastName.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_LASTNAME_ERROR");
                } else if (!tLastName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_LASTNAME_FORMAT_ERROR");
                }
                if (tPhone.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_PHONE_ERROR");
                } else if (!tPhone.matches("(((09[\\d]{1,1}|01[\\d]{2,2})-[\\d]{3,3}-[\\d]{4,4}|[\\d]{4,4}-[\\d]{4,4})|08-[\\d]{4,4}-[\\d]{4,4})")) {
                    message += resourse.getString("INVALID_PHONE_FORMAT_ERROR");
                }
                if (tAddress.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_ADDRESS_ERROR");
                } else if (!tAddress.matches("^[a-z A-Z0-9]*$")) {
                    message += resourse.getString("INVALID_ADDRESS_FORMAT_ERROR");
                }
                if (tEmail.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_EMAIL_ERROR");
                } else if (!tEmail.matches("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[\\w]{2,4}$")) {
                    message += resourse.getString("INVALID_EMAIL_FORMAT_ERROR");
                }
                if (tStartYear.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_STARTYEAR_ERROR");
                } else if (!tStartYear.matches("199[0-9]{1}|200[0-9]{1}|2010|2011|2012|2013")) {
                    message += resourse.getString("INVALID_TEACHER_STARTYEAR_FORMAT_ERROR");
                }
                
                if (skillString.size() != payRateList.size()) {
                    message += resourse.getString("INVALID_ARAY_ERORR");
                }
                if (skillString.size() == 0 || payRateList.size() == 0) {
                    message += resourse.getString("INVALID_ARAY_EMPTY_ERORR");
                }
                
                if (message.isEmpty()) {

//                    for (Integer item : skillInteger) {
//                        System.out.println(item);
//                    }
                    model.addTeacher(new Teacher(model.autoGenerateId(), tFirstName, tLastName,
                            tPhone, tAddress, tEmail, tgender, tphoto,
                            skillString, Integer.parseInt(tStartYear), payRateList));
                    JOptionPane.showMessageDialog(null, "Teacher has been added successful!");
                    tGUI.updateTeacherPanel();
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
        contentPanel.add(addressPanel, BorderLayout.SOUTH);
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createTitledBorder(resourse.getString("ADD_TXT")));

        return mainPanel;
    }

    public TeacherInput(Teacher t, TeacherGUI tg) {

        super();
        tGUI = tg;
        setTitle("Edit new teacher");
        add(editTeacherInputPanel(t));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private CPanel editTeacherInputPanel(final Teacher t) {



        final ArrayList<String> newList = new ArrayList<String>(t.getSkillArray());
        final ArrayList<String> secondList = new ArrayList<String>();


        classTypeList = model.getClassTypeList();
        stringClassTypeList = model.generateSkill();
        payRateList = t.getFeeSkill();


        CPanel mainPanel = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
        CPanel contentPanel = new CPanel(new BorderLayout());
        CPanel labelPanel = new CPanel(new GridLayout(13, 1));
        CPanel textPanel = new CPanel(new GridLayout(13, 1));
        CPanel addressPanel = new CPanel(new BorderLayout());
        CPanel phonePanel = new CPanel(new FlowLayout());
        CPanel picPanel = new CPanel(new FlowLayout());
        CPanel wrapPanel = new CPanel(new BorderLayout());
        CPanel wraphourRate = new CPanel(new FlowLayout());

        phonePanel.add(cbCode);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(teacherPhone1);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(teacherPhone2);

        wraphourRate.add(skillPayRate);
        wraphourRate.add(addPayRate);

        wrapPanel.add(new JScrollPane(teacherAddress), BorderLayout.CENTER);

        genderGroup.add(teacherMale);
        genderGroup.add(teacherFemale);

        if (t.isGender() == Ultility.MALE) {
            teacherMale.setSelected(true);
        } else {
            teacherFemale.setSelected(true);
        }

        labelPanel.add(new JLabel(resourse.getString("NAME_TXT")));
        textPanel.add(teacherFirstName);
        teacherFirstName.setText(t.getFirstName());

        labelPanel.add(new JLabel(resourse.getString("LAST_NAME_TXT")));
        textPanel.add(teacherLastName);
        teacherLastName.setText(t.getLastName());

        labelPanel.add(new JLabel(resourse.getString("PHONE_TXT")));
        textPanel.add(phonePanel);


        labelPanel.add(new JLabel(resourse.getString("EMAIL_TXT")));
        textPanel.add(teacherEmail);
        teacherEmail.setText(t.getEmail());

        if (t.isGender() == Ultility.MALE) {
            teacherMale.setSelected(true);

        } else {
            teacherFemale.setSelected(true);
        }
        labelPanel.add(new JLabel(resourse.getString("GENDER_TXT")));
        textPanel.add(teacherMale);
        labelPanel.add(new JLabel(""));
        textPanel.add(teacherFemale);

        labelPanel.add(new JLabel(resourse.getString("START_YEAR_TXT")));
        textPanel.add(teacherStartYear);
        teacherStartYear.setText(t.getStartYear() + "");

        labelPanel.add(new JLabel(resourse.getString("IMAGE_TXT")));
        picPanel.add(teacherPicture);
        picPanel.add(picButton);
        teacherPicture.setEditable(false);
        teacherPicture.setText(t.getPhoto());
        textPanel.add(picPanel);

        labelPanel.add(new JLabel(resourse.getString("SUBJECT_TXT")));
        textPanel.add(teacherSkillList);

        for (String cla : stringClassTypeList) {
            teacherSkillList.addItem(cla);
        }
        labelPanel.add(new JLabel(resourse.getString("HOURLY_PAY_TXT")));
        textPanel.add(wraphourRate);


        labelPanel.add(new JLabel(resourse.getString("SUBJECT_TXT")));
        textPanel.add(teacherSkillArray);
        labelPanel.add(new JLabel(resourse.getString("HOURLY_PAY_TXT")));
        textPanel.add(teacherPayrateArray);
        textPanel.add(clear);

        addressPanel.add(new JLabel(resourse.getString("ADDRESS_TXT")), BorderLayout.WEST);
        addressPanel.add(wrapPanel, BorderLayout.CENTER);
        teacherAddress.setText(t.getAddress());
        tempSkill = "";
        for (Double item : payRateList) {
            if (tempSkill.equals("")) {
                tempSkill = item + "";
            } else {
                tempSkill = tempSkill + " - " + item;
            }
        }
        teacherPayrateArray.setText(tempSkill);

        addPayRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!skillPayRate.getText().isEmpty()) {
                    String s = skillPayRate.getText();
                    double pr = Double.parseDouble(s);
                    if (skillString.size() > payRateList.size()) {
                        payRateList.add(pr);
                        tempSkill = "";
                        for (Double item : payRateList) {
                            if (tempSkill.equals("")) {
                                tempSkill = item + "";
                            } else {
                                tempSkill = tempSkill + " - " + item;
                            }
                        }
                        teacherPayrateArray.setText(tempSkill);
                    }



                }
            }
        });


        teacherSkillList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = (String) teacherSkillList.getSelectedItem();
                int check = 0;
                for (String str : skillString) {
                    if (str.equals(temp)) {
                        check = 1;
                    }
                }
                if (check == 0) {
                    skillString.add((String) teacherSkillList.getSelectedItem());
                    secondList.add((String) teacherSkillList.getSelectedItem());
                    tempSkill = "";
                    for (String item : skillString) {
                        if (tempSkill.equals("")) {
                            tempSkill = item;
                        } else {
                            tempSkill = tempSkill + " - " + item;
                        }

                    }
                    teacherSkillArray.setText(tempSkill);
                }

            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skillString.removeAll(skillString);
                teacherSkillArray.setText("");
                newList.removeAll(newList);
                secondList.removeAll(secondList);
                 payRateList.removeAll(payRateList);
                teacherPayrateArray.setText("");
            }
        });

        for (String item : newList) {
            teacherSkillList.setSelectedItem(item);
        }


        cbCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                teacherPhone1.setText("");
                teacherPhone2.setText("");

                //keep account phone in length of 4
                teacherPhone2.setDocument(new PlainDocument() {
                    @Override
                    public void insertString(int offs, String str, AttributeSet a)
                            throws BadLocationException {
                        if (getLength() + str.length() <= limit1) {
                            super.insertString(offs, str, a);
                        }
                    }
                });

                //  accountPhone2.removeKeyListener(accountPhone2.getKeyListeners());
                for (KeyListener kl : teacherPhone1.getKeyListeners()) {
                    teacherPhone1.removeKeyListener(kl);
                }

                if (cbCode.getSelectedItem().equals("08")) {
                    //      System.out.println("check");

                    teacherPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = teacherPhone1.getText();
                            System.out.println("check1");
                            if (value.length() == 4) {
                                teacherPhone2.requestFocus();
                            }
                        }
                    });
                } else {
                    teacherPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = teacherPhone1.getText();
                            System.out.println("check2");
                            if (value.length() == 3) {
                                teacherPhone2.requestFocus();
                            }
                        }
                    });
                }
            }
        });



        if (cbCode.getSelectedItem().equals("08")) {
            //      System.out.println("check");

            teacherPhone1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String value = teacherPhone1.getText();
                    System.out.println("check1");
                    if (value.length() == 4) {
                        teacherPhone2.requestFocus();
                    }
                }
            });
            teacherPhone1.setDocument(new PlainDocument() {
                @Override
                public void insertString(int offs, String str, AttributeSet a)
                        throws BadLocationException {
                    if (getLength() + str.length() <= limit1) {
                        super.insertString(offs, str, a);
                    }
                }
            });
            teacherPhone2.setDocument(new PlainDocument() {
                @Override
                public void insertString(int offs, String str, AttributeSet a)
                        throws BadLocationException {
                    if (getLength() + str.length() <= limit1) {
                        super.insertString(offs, str, a);
                    }
                }
            });
        }

        String[] phoneParts = t.getPhoneNumber().split("-");
        cbCode.setSelectedItem(phoneParts[0]);
        teacherPhone1.setText(phoneParts[1]);
        teacherPhone2.setText(phoneParts[2]);
        picButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showSaveDialog(null);
                System.out.println("0");
                if (option == JFileChooser.APPROVE_OPTION) {
//                    System.out.println("1");
//                    System.out.println(FileProcess.checkExt(chooser.getSelectedFile().getPath()));
                    if (FileProcess.checkExt(chooser.getSelectedFile().getPath())) {
                        String name = FileProcess.generateName(FileProcess.findExt(chooser.getSelectedFile().getName()));
                        String path = "Images/" + name;
//                        System.out.println(path);
//                        System.out.println("2");
                        FileProcess.copyFile(chooser.getSelectedFile().getPath(), path);
                        teacherPicture.setText(name);
                    }
                }
            }
        });

        //create button panel
        CPanel buttonPanel = new CPanel();

        //create EDIT button
        JButton editButton = new JButton(resourse.getString("SAVE_TXT"));

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tFirstName = teacherFirstName.getText().trim();
                String tLastName = teacherLastName.getText().trim();
                String tPhone = cbCode.getSelectedItem() + "-" + teacherPhone1.getText().trim() + "-" + teacherPhone2.getText().trim();
                String tAddress = teacherAddress.getText().trim();
                String tEmail = teacherEmail.getText().trim();
                String tStartYear = teacherStartYear.getText().trim();
                String message = "";

                if (tFirstName.isEmpty()) {
                    message = resourse.getString("EMPTY_TEACHER_FIRSTNAME_ERROR");
                } else if (!tFirstName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_FIRSTNAME_FORMAT_ERROR");
                }
                if (tLastName.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_LASTNAME_ERROR");
                } else if (!tLastName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_LASTNAME_FORMAT_ERROR");
                }
                if (tPhone.isEmpty()) {
                    message += resourse.getString("EMPTY_PHONE_TEXT_ERROR");
                } else if (!tPhone.matches("(((09[\\d]{1,1}|01[\\d]{2,2})-[\\d]{3,3}-[\\d]{4,4}|[\\d]{4,4}-[\\d]{4,4})|08-[\\d]{4,4}-[\\d]{4,4})")) {
                    message += resourse.getString("INVALID_PHONE_FORMAT_ERROR");
                }
                if (tAddress.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_ADDRESS_ERROR");
                } else if (!tAddress.matches("^[a-z A-Z0-9]*$")) {
                    message += resourse.getString("INVALID_ADDRESS_FORMAT_ERROR");
                }
                if (tEmail.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_EMAIL_ERROR");
                } else if (!tEmail.matches("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[\\w]{2,4}$")) {
                    message += resourse.getString("INVALID_EMAIL_FORMAT_ERROR");
                }
                if (tStartYear.isEmpty()) {
                    message += resourse.getString("EMPTY_TEACHER_STARTYEAR_ERROR");
                } else if (!tStartYear.matches("199[0-9]{1}|200[0-9]{1}|2010|2011|2012|2013")) {
                    message += resourse.getString("INVALID_TEACHER_STARTYEAR_FORMAT_ERROR");
                }
                  if (skillString.size() != payRateList.size()) {
                    message += resourse.getString("INVALID_ARAY_ERORR");
                }
                if (skillString.size() == 0 || payRateList.size() == 0) {
                    message += resourse.getString("INVALID_ARAY_EMPTY_ERORR");
                }
                
                if (message.isEmpty()) {
                    t.setFirstName(tFirstName);
                    t.setLastName(tLastName);
                    t.setAddress(tAddress);
//                    t.setPhoneNumber(tPhone);
                    t.setEmail(tEmail);

                    if (teacherMale.isSelected()) {
                        t.setGender(MALE);
                    } else {
                        t.setGender(FEMALE);
                    }
                    t.setStartYear(Integer.parseInt(tStartYear));
                    // t.setSkill(teacherSkillList.getSelectedIndex());
                    t.setSkillArray(secondList);
                    t.setFeeSkill(payRateList);
                    //t.setStartYear(tStartYear);
                    JOptionPane.showMessageDialog(null, "Teacher has been edited successful!");
                    tGUI.updateTeacherPanel();
                    dispose();
                } else {
                    //System.out.println(message);
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
        contentPanel.add(addressPanel, BorderLayout.SOUTH);
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createTitledBorder(resourse.getString("EDIT_TXT")));


        return mainPanel;
    }

    public String getTempSkill() {
        return tempSkill;
    }

    public void setTempSkill(String tempSkill) {
        this.tempSkill = tempSkill;
    }

    public void setSkillTeacher(int a) {
        teacherSkillList.setSelectedIndex(a);
    }
}
