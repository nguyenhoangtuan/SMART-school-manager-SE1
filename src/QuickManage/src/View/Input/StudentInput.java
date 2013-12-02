/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import FileData.FileProcess;
import Model.Model;
import Model.Student;
import Model.Ultility;
import View.CustomComponent.CPanel;
import View.StudentGUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class StudentInput extends JDialog implements Ultility {

    private Model model = Model.getModel();
    private StudentGUI sGUI;
    ResourceBundle resourse = model.getResources();
    private final int limit = 3;
    private final int limit1 = 4;
    private JTextField studentFirstName = new JTextField(10);
    private JTextField studentLastName = new JTextField(10);
    public String[] codeData = {"08", "090", "091", "092", "093", "094", "095", "096", "097", "098", "099", "0123", "0124", "0125", "0126", "0127", "0128", "0129"};
    private JComboBox cbCode = new JComboBox(codeData);
    private JComboBox cbCode1 = new JComboBox(codeData);
    private JTextField studentPhone1 = new JTextField(3);
    private JTextField studentPhone2 = new JTextField(3);
    private JTextArea studentAddress = new JTextArea(3, 1);
    private JTextField studentEmail = new JTextField(10);
    private JRadioButton studentMale = new JRadioButton(resourse.getString("MALE_TXT"), true);
    private JRadioButton studentFemale = new JRadioButton(resourse.getString("FEMALE_TXT"));
    private ButtonGroup genderGroup = new ButtonGroup();
    private JTextField studentPicture = new JTextField(10);
    private JButton picButton = new JButton(resourse.getString("UPLOAD_TXT"));
    private JTextField contactName = new JTextField(10);
    private JTextField contactPhone1 = new JTextField(3);
    private JTextField contactPhone2 = new JTextField(3);
    private JTextField contactEmail = new JTextField(10);
    public String[] relativeData = {"Father", "Mother", "Grant-Mother", "Grant-Father", "Brother", "Sister"};
    private JComboBox cbRelative = new JComboBox(relativeData);
    
    // private ArrayList<Student> sutdentList;

    public StudentInput(StudentGUI s) {

        super();
        sGUI = s;
        setTitle("Add new student");
        add(getAddStudentInputPanel());
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private CPanel getAddStudentInputPanel() {

        CPanel mainPanel = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
        CPanel contentPanel1 = new CPanel(new BorderLayout());
        CPanel contentPanel2 = new CPanel(new BorderLayout());
        CPanel labelPanel1 = new CPanel(new GridLayout(7, 1));
        CPanel textPanel1 = new CPanel(new GridLayout(7, 1));
        CPanel addressPanel = new CPanel(new BorderLayout());
        CPanel labelPanel2 = new CPanel(new GridLayout(4, 1));
        CPanel textPanel2 = new CPanel(new GridLayout(4, 1));
        CPanel phonePanel = new CPanel(new FlowLayout());
        CPanel contactPhonePanel = new CPanel(new FlowLayout());
        CPanel picPanel = new CPanel(new FlowLayout());
        CPanel wrapPanel = new CPanel(new BorderLayout());

        wrapPanel.add(new JScrollPane(studentAddress), BorderLayout.CENTER);

        phonePanel.add(cbCode);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(studentPhone1);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(studentPhone2);

        contactPhonePanel.add(cbCode1);
        contactPhonePanel.add(new JLabel("-"));
        contactPhonePanel.add(contactPhone1);
        contactPhonePanel.add(new JLabel("-"));
        contactPhonePanel.add(contactPhone2);


        genderGroup.add(studentMale);
        genderGroup.add(studentFemale);

        labelPanel1.add(new JLabel(resourse.getString("NAME_TXT")));
        textPanel1.add(studentFirstName);
        labelPanel1.add(new JLabel(resourse.getString("LAST_NAME_TXT")));
        textPanel1.add(studentLastName);
        labelPanel1.add(new JLabel(resourse.getString("PHONE_TXT")));
        textPanel1.add(phonePanel);
        labelPanel1.add(new JLabel(resourse.getString("EMAIL_TXT")));
        textPanel1.add(studentEmail);

        labelPanel1.add(new JLabel(resourse.getString("GENDER_TXT")));
        textPanel1.add(studentMale);
        labelPanel1.add(new JLabel(""));
        textPanel1.add(studentFemale);

        labelPanel1.add(new JLabel(resourse.getString("IMAGE_TXT")));
        picPanel.add(studentPicture);
        studentPicture.setEditable(false);
        studentPicture.setText(DEFAULT_PHOTO);
        picPanel.add(picButton);
        textPanel1.add(picPanel);

        addressPanel.add(new JLabel(resourse.getString("ADDRESS_TXT")), BorderLayout.WEST);
        addressPanel.add(wrapPanel, BorderLayout.CENTER);

        labelPanel2.add(new JLabel(resourse.getString("REL_NAME_TXT")));
        textPanel2.add(contactName);
        labelPanel2.add(new JLabel(resourse.getString("REL_PHONE_TXT")));
        textPanel2.add(contactPhonePanel);
        labelPanel2.add(new JLabel(resourse.getString("REL_EMAIL_TXT")));
        textPanel2.add(contactEmail);
        labelPanel2.add(new JLabel(resourse.getString("RELATIONSHIP_TXT")));
        textPanel2.add(cbRelative);

        // actionlistener for combobox
        cbCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPhone1.setText("");
                studentPhone2.setText("");

                studentPhone2.setDocument(new PlainDocument() {
                    @Override
                    public void insertString(int offs, String str, AttributeSet a)
                            throws BadLocationException {
                        if (getLength() + str.length() <= limit1) {
                            super.insertString(offs, str, a);
                        }
                    }
                });

                for (KeyListener kl : studentPhone1.getKeyListeners()) {
                    studentPhone1.removeKeyListener(kl);
                }

                if (cbCode.getSelectedItem().equals("08")) {
                    System.out.println("check");

                    studentPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = studentPhone1.getText();
                            System.out.println("check1");
                            if (value.length() == 4) {
                                studentPhone2.requestFocus();
                            }
                        }
                    });

                    studentPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    studentPhone2.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });
                } else {
                    studentPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = studentPhone1.getText();
                            System.out.println("check2");
                            if (value.length() == 3) {
                                studentPhone2.requestFocus();
                            }
                        }
                    });

                    studentPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    studentPhone2.setDocument(new PlainDocument() {
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
        ////////////////////////////////////////////////////////////////////////

        // actionlistener for combobox cbCode1
        cbCode1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contactPhone1.setText("");
                contactPhone2.setText("");
                contactPhone2.setDocument(new PlainDocument() {
                    @Override
                    public void insertString(int offs, String str, AttributeSet a)
                            throws BadLocationException {
                        if (getLength() + str.length() <= limit1) {
                            super.insertString(offs, str, a);
                        }
                    }
                });

                for (KeyListener kl : contactPhone1.getKeyListeners()) {
                    contactPhone1.removeKeyListener(kl);
                }

                if (cbCode1.getSelectedItem().equals("08")) {
                    System.out.println("check");

                    contactPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = contactPhone1.getText();
                            System.out.println("check1");
                            if (value.length() == 4) {
                                contactPhone2.requestFocus();
                            }
                        }
                    });

                    contactPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    contactPhone2.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });
                } else {
                    contactPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = contactPhone1.getText();
                            System.out.println("check2");
                            if (value.length() == 3) {
                                contactPhone2.requestFocus();
                            }
                        }
                    });

                    contactPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    contactPhone2.setDocument(new PlainDocument() {
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
        cbCode1.setSelectedIndex(0);
        ////////////////////////////////////////////////////////////////////////
        picButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showSaveDialog(null);
                System.out.println("0");
                if (option == JFileChooser.APPROVE_OPTION) {
                    System.out.println("1");
                    System.out.println(FileProcess.checkExt(chooser.getSelectedFile().getPath()));
                    if (FileProcess.checkExt(chooser.getSelectedFile().getPath())) {
                        String name = FileProcess.generateName(FileProcess.findExt(chooser.getSelectedFile().getName()));
                        String path = "Images/" + name;

                        FileProcess.copyFile(chooser.getSelectedFile().getPath(), path);
                        studentPicture.setText(name);
                    }
                }
            }
        });

        //create button panel
        CPanel buttonPanel = new CPanel();

        JButton addButton = new JButton(resourse.getString("ADD_TXT"));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sFirstName = studentFirstName.getText().trim();
                String sLastName = studentLastName.getText().trim();
                String sPhone = cbCode.getSelectedItem() + "-" + studentPhone1.getText().trim() + "-" + studentPhone2.getText().trim();
                String sAddress = studentAddress.getText().trim();
                String sEmail = studentEmail.getText().trim();
                String conName = contactName.getText().trim();
                String conPhone = cbCode.getSelectedItem() + "-" + contactPhone1.getText().trim() + "-" + contactPhone2.getText().trim();
                String conEmail = contactEmail.getText().trim();
                int conRelate = cbRelative.getSelectedIndex();
                String message = "";
                boolean gender;
                if (studentFemale.isSelected()) {
                    gender = FEMALE;
                } else {
                    gender = MALE;
                }

                if (sFirstName.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_FIRSTNAME_ERROR");
                } else if (!sFirstName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_FIRSTNAME_FORMAT_ERROR");
                }
                if (sLastName.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_LASTNAME_ERROR");
                } else if (!sLastName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_LASTNAME_FORMAT_ERROR");
                }
                if (sPhone.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_PHONE_ERROR");
                } else if (!sPhone.matches("(((09[\\d]{1,1}|01[\\d]{2,2})-[\\d]{3,3}-[\\d]{4,4}|[\\d]{4,4}-[\\d]{4,4})|08-[\\d]{4,4}-[\\d]{4,4})")) {
                    message = resourse.getString("INVALID_PHONE_FORMAT_ERROR");
                }
                if (sAddress.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_ADDRESS_ERROR");
                } else if (!sAddress.matches("^[A-Za-z0-9 ]*")) {
                    message += resourse.getString("INVALID_ADDRESS_FORMAT_ERROR");
                }
                if (sEmail.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_EMAIL_ERROR");
                } else if (!sEmail.matches("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[\\w]{2,4}$")) {
                    message = resourse.getString("INVALID_EMAIL_FORMAT_ERROR");
                }
                if (conName.isEmpty()) {
                    message += resourse.getString("EMPTY_CONTACT_NAME_ERROR");
                } else if (!conName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_CONTACT_NAME_FORMAT_ERROR");
                }
                if (conPhone.isEmpty()) {
                    message += resourse.getString("EMPTY_CONTACT_PHONE_ERROR");
                } else if (!conPhone.matches("(((09[\\d]{1,1}|01[\\d]{2,2})-[\\d]{3,3}-[\\d]{4,4}|[\\d]{4,4}-[\\d]{4,4})|08-[\\d]{4,4}-[\\d]{4,4})")) {
                    message += resourse.getString("INVALID_CONTACT_PHONE_FORMAT_ERROR");
                }
                if (conEmail.isEmpty()) {
                    message += resourse.getString("EMPTY_CONTACT_EMAIL_ERROR");
                } else if (!conEmail.matches("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[\\w]{2,4}$")) {
                    message += resourse.getString("INVALID_CONTACT_EMAIL_FORMAT_ERROR");
                }
                if (message.isEmpty()) {
                    model.addStudent(new Student(conName, conPhone, conEmail, model.autoGenerateId(), sFirstName, sLastName, sPhone, sAddress, sEmail, gender, studentPicture.getText(), conRelate));
                    JOptionPane.showMessageDialog(null, "Student has been added successful!");
                    sGUI.updateContainerPanel();
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
        contentPanel1.add(labelPanel1, BorderLayout.WEST);
        contentPanel1.add(textPanel1, BorderLayout.CENTER);
        contentPanel1.add(addressPanel, BorderLayout.SOUTH);
        contentPanel2.add(labelPanel2, BorderLayout.WEST);
        contentPanel2.add(textPanel2, BorderLayout.CENTER);
        mainPanel.add(contentPanel1, BorderLayout.NORTH);
        mainPanel.add(contentPanel2, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel1.setBorder(BorderFactory.createTitledBorder("ADD STUDENT"));
        contentPanel2.setBorder(BorderFactory.createTitledBorder("CONTACT"));
        return mainPanel;

    }

    // EDIT STUDENT
    public StudentInput(Student s, StudentGUI sg) {

        super();
        sGUI = sg;
        setTitle("Edit student");
        add(editStudentInputPanel(s));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private CPanel editStudentInputPanel(final Student s) {

        CPanel mainPanel = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
        CPanel contentPanel1 = new CPanel(new BorderLayout());
        CPanel contentPanel2 = new CPanel(new BorderLayout());
        CPanel labelPanel1 = new CPanel(new GridLayout(7, 1));
        CPanel textPanel1 = new CPanel(new GridLayout(7, 1));
        CPanel addressPanel = new CPanel(new BorderLayout());
        CPanel labelPanel2 = new CPanel(new GridLayout(4, 1));
        CPanel textPanel2 = new CPanel(new GridLayout(4, 1));
        CPanel phonePanel = new CPanel(new FlowLayout());
        CPanel contactPhonePanel = new CPanel(new FlowLayout());
        CPanel picPanel = new CPanel(new FlowLayout());
        CPanel wrapPanel = new CPanel(new BorderLayout());

        wrapPanel.add(new JScrollPane(studentAddress), BorderLayout.CENTER);

        phonePanel.add(cbCode);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(studentPhone1);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(studentPhone2);

        contactPhonePanel.add(cbCode1);
        contactPhonePanel.add(new JLabel("-"));
        contactPhonePanel.add(contactPhone1);
        contactPhonePanel.add(new JLabel("-"));
        contactPhonePanel.add(contactPhone2);




        genderGroup.add(studentMale);
        genderGroup.add(studentFemale);

        labelPanel1.add(new JLabel(resourse.getString("NAME_TXT")));
        textPanel1.add(studentFirstName);
        labelPanel1.add(new JLabel(resourse.getString("LAST_NAME_TXT")));
        textPanel1.add(studentLastName);
        labelPanel1.add(new JLabel(resourse.getString("PHONE_TXT")));
        textPanel1.add(phonePanel);
        labelPanel1.add(new JLabel(resourse.getString("EMAIL_TXT")));
        textPanel1.add(studentEmail);
        labelPanel1.add(new JLabel(resourse.getString("GENDER_TXT")));
        textPanel1.add(studentMale);
        labelPanel1.add(new JLabel(""));
        textPanel1.add(studentFemale);
        labelPanel1.add(new JLabel(resourse.getString("IMAGE_TXT")));
        picPanel.add(studentPicture);
        studentPicture.setEditable(false);
        picPanel.add(picButton);
        textPanel1.add(picPanel);

        addressPanel.add(new JLabel(resourse.getString("ADDRESS_TXT")), BorderLayout.WEST);
        addressPanel.add(wrapPanel, BorderLayout.CENTER);



        labelPanel2.add(new JLabel(resourse.getString("REL_NAME_TXT")));
        textPanel2.add(contactName);
        labelPanel2.add(new JLabel(resourse.getString("REL_PHONE_TXT")));
        textPanel2.add(contactPhonePanel);
        labelPanel2.add(new JLabel(resourse.getString("REL_EMAIL_TXT")));
        textPanel2.add(contactEmail);
        labelPanel2.add(new JLabel(resourse.getString("RELATIONSHIP_TXT")));
        textPanel2.add(cbRelative);

        studentFirstName.setText(s.getFirstName());
        studentLastName.setText(s.getLastName());
        studentPicture.setText(s.getPhoto());
        studentAddress.setText(s.getAddress());
        studentEmail.setText(s.getEmail());
        if (s.isGender() == MALE) {
            studentMale.setSelected(true);
        } else {
            studentFemale.setSelected(true);
        }
        cbRelative.setSelectedIndex(s.getRelationship());

        contactName.setText(s.getContactName());
        contactEmail.setText(s.getContactEmail());


        ////////////////////////////////////////////////////////////////////////

        // actionlistener for combobox
        cbCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPhone1.setText("");
                studentPhone2.setText("");

                studentPhone2.setDocument(new PlainDocument() {
                    @Override
                    public void insertString(int offs, String str, AttributeSet a)
                            throws BadLocationException {
                        if (getLength() + str.length() <= limit1) {
                            super.insertString(offs, str, a);
                        }
                    }
                });

                for (KeyListener kl : studentPhone1.getKeyListeners()) {
                    studentPhone1.removeKeyListener(kl);
                }

                if (cbCode.getSelectedItem().equals("08")) {
                    System.out.println("check");

                    studentPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = studentPhone1.getText();
                            System.out.println("check1");
                            if (value.length() == 4) {
                                studentPhone2.requestFocus();
                            }
                        }
                    });

                    studentPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    studentPhone2.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });
                } else {
                    studentPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = studentPhone1.getText();
                            System.out.println("check2");
                            if (value.length() == 3) {
                                studentPhone2.requestFocus();
                            }
                        }
                    });

                    studentPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    studentPhone2.setDocument(new PlainDocument() {
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
        ////////////////////////////////////////////////////////////////////////

        // actionlistener for combobox cbCode1
        cbCode1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contactPhone1.setText("");
                contactPhone2.setText("");
                contactPhone2.setDocument(new PlainDocument() {
                    @Override
                    public void insertString(int offs, String str, AttributeSet a)
                            throws BadLocationException {
                        if (getLength() + str.length() <= limit1) {
                            super.insertString(offs, str, a);
                        }
                    }
                });

                for (KeyListener kl : contactPhone1.getKeyListeners()) {
                    contactPhone1.removeKeyListener(kl);
                }

                if (cbCode1.getSelectedItem().equals("08")) {
                    System.out.println("check");

                    contactPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = contactPhone1.getText();
                            System.out.println("check1");
                            if (value.length() == 4) {
                                contactPhone2.requestFocus();
                            }
                        }
                    });

                    contactPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    contactPhone2.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit1) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });
                } else {
                    contactPhone1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String value = contactPhone1.getText();
                            System.out.println("check2");
                            if (value.length() == 3) {
                                contactPhone2.requestFocus();
                            }
                        }
                    });

                    contactPhone1.setDocument(new PlainDocument() {
                        @Override
                        public void insertString(int offs, String str, AttributeSet a)
                                throws BadLocationException {
                            if (getLength() + str.length() <= limit) {
                                super.insertString(offs, str, a);
                            }
                        }
                    });

                    contactPhone2.setDocument(new PlainDocument() {
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
        cbCode1.setSelectedIndex(0);
        ////////////////////////////////////////////////////////////////////////

        String[] phoneParts = s.getPhoneNumber().split("-");
        cbCode.setSelectedItem(phoneParts[0]);
        studentPhone1.setText(phoneParts[1]);
        studentPhone2.setText(phoneParts[2]);

        String[] phonePartss = s.getPhoneNumber().split("-");
        cbCode1.setSelectedItem(phonePartss[0]);
        contactPhone1.setText(phonePartss[1]);
        contactPhone2.setText(phonePartss[2]);

        picButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showSaveDialog(null);
                System.out.println("0");
                if (option == JFileChooser.APPROVE_OPTION) {
                    System.out.println("1");
                    System.out.println(FileProcess.checkExt(chooser.getSelectedFile().getPath()));
                    if (FileProcess.checkExt(chooser.getSelectedFile().getPath())) {
                        String name = FileProcess.generateName(FileProcess.findExt(chooser.getSelectedFile().getName()));
                        String path = "Images/" + name;

                        FileProcess.copyFile(chooser.getSelectedFile().getPath(), path);
                        studentPicture.setText(name);
                    }
                }
            }
        });

        //create button panel
        CPanel buttonPanel = new CPanel();


        //create Save button
        JButton addButton = new JButton(resourse.getString("SAVE_TXT"));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sFirstName = studentFirstName.getText().trim();
                String sLastName = studentLastName.getText().trim();
                String sPhone = cbCode.getSelectedItem() + "-" + studentPhone1.getText().trim() + "-" + studentPhone2.getText().trim();
                String sAddress = studentAddress.getText().trim();
                String sEmail = studentEmail.getText().trim();
                String conName = contactName.getText().trim();
                String conPhone = cbCode.getSelectedItem() + "-" + contactPhone1.getText().trim() + "-" + contactPhone2.getText().trim();
                String conEmail = contactEmail.getText().trim();
                //int conRelate = cbRelative.getSelectedIndex();
                String message = "";
                boolean gender;
                if (studentFemale.isSelected()) {
                    gender = FEMALE;
                } else {
                    gender = MALE;
                }

                if (sFirstName.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_FIRSTNAME_ERROR");
                } else if (!sFirstName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_FIRSTNAME_FORMAT_ERROR");
                }
                if (sLastName.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_LASTNAME_ERROR");
                } else if (!sLastName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_LASTNAME_FORMAT_ERROR");
                }
                if (sPhone.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_PHONE_ERROR");
                } else if (!sPhone.matches("(((09[\\d]{1,1}|01[\\d]{2,2})-[\\d]{3,3}-[\\d]{4,4}|[\\d]{4,4}-[\\d]{4,4})|08-[\\d]{4,4}-[\\d]{4,4})")) {

                    message = resourse.getString("INVALID_PHONE_FORMAT_ERROR");
                }
                if (sAddress.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_ADDRESS_ERROR");
                } else if (!sAddress.matches("^[A-Za-z0-9 ]*")) {
                    message += resourse.getString("INVALID_ADDRESS_FORMAT_ERROR");
                }
                if (sEmail.isEmpty()) {
                    message += resourse.getString("EMPTY_STUDENT_EMAIL_ERROR");
                } else if (!sEmail.matches("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[\\w]{2,4}$")) {
                    message = resourse.getString("INVALID_EMAIL_FORMAT_ERROR");
                }
                if (conName.isEmpty()) {
                    message += resourse.getString("EMPTY_CONTACT_NAME_ERROR");
                } else if (!conName.matches("^[a-zA-Z ]*")) {
                    message += resourse.getString("INVALID_CONTACT_NAME_FORMAT_ERROR");
                }
                if (conPhone.isEmpty()) {
                    message += resourse.getString("EMPTY_CONTACT_PHONE_ERROR");
                } else if (!conPhone.matches("(((09[\\d]{1,1}|01[\\d]{2,2})-[\\d]{3,3}-[\\d]{4,4}|[\\d]{4,4}-[\\d]{4,4})|08-[\\d]{4,4}-[\\d]{4,4})")) {
                    message += resourse.getString("INVALID_CONTACT_PHONE_FORMAT_ERROR");
                }
                if (conEmail.isEmpty()) {
                    message += resourse.getString("EMPTY_CONTACT_EMAIL_ERROR");
                } else if (!conEmail.matches("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[\\w]{2,4}$")) {
                    message += resourse.getString("INVALID_CONTACT_EMAIL_FORMAT_ERROR");
                }
                if (message.isEmpty()) {
                    s.setFirstName(sFirstName);
                    s.setLastName(sLastName);
                    s.setPhoneNumber(sPhone);
                    s.setAddress(sAddress);
                    s.setEmail(sEmail);
                    s.setGender(gender);

                    s.setContactName(conName);
                    s.setContactPhone(conPhone);
                    s.setContactEmail(conEmail);

                    s.setPhoto(studentPicture.getText());
                    s.setRelationship(cbRelative.getSelectedIndex());

                    JOptionPane.showMessageDialog(null, "Student has been changed successful!");
                    sGUI.updateContainerPanel();
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
        contentPanel1.add(labelPanel1, BorderLayout.WEST);
        contentPanel1.add(textPanel1, BorderLayout.CENTER);
        contentPanel1.add(addressPanel, BorderLayout.SOUTH);
        contentPanel2.add(labelPanel2, BorderLayout.WEST);
        contentPanel2.add(textPanel2, BorderLayout.CENTER);
        mainPanel.add(contentPanel1, BorderLayout.NORTH);
        mainPanel.add(contentPanel2, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel1.setBorder(BorderFactory.createTitledBorder("EDIT STUDENT"));
        contentPanel2.setBorder(BorderFactory.createTitledBorder("EDIT CONTACT"));
        return mainPanel;

    }
}