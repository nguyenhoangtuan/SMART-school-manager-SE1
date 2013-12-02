/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import Model.Classes;
import Model.ImportExportSCV;
import Model.Model;
import Model.Student;
import Model.Teacher;
import Model.Ultility;
import View.CustomComponent.CellStudent;
import View.CustomComponent.CPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Lyn
 */
public class EnrollmentFrame extends JDialog implements Ultility {
    
    Model model = Model.getModel();
    ResourceBundle resourse = model.getResources();
    ArrayList<Classes> classList;
    ArrayList<Student> studentlist;
    ArrayList<Teacher> teacherList;
    private CPanel leftComponent = new CPanel();
    private CPanel rightComponent = new CPanel();
    private CPanel studentPanel = new CPanel();
    private CPanel lecturePanel = new CPanel();
    private JTextField lecSearch = new JTextField();
    private JTextField stuSearch = new JTextField();
    private JButton lecBtn = new JButton(resourse.getString("SEARCH_T_TXT"));
    private JButton stuBtn = new JButton(resourse.getString("SEARCH_T_TXT"));
    DefaultListModel modellec = new DefaultListModel();
    DefaultListModel modelstu = new DefaultListModel();
    DefaultListModel modecla = new DefaultListModel();
    DefaultListModel modeLecInClass = new DefaultListModel();
    private CPanel mainPn = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
    private CPanel buttonPn = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
    private JButton closeBt = new JButton(resourse.getString("CLOSE_T_TXT"));
    private JButton backBt = new JButton(resourse.getString("BACK_T_TXT"));
    private JList listlec = new JList(modellec);
    private JList liststu = new JList(modelstu);
    private JScrollPane scrollPacLec = new JScrollPane(listlec);
    private JScrollPane scrollPacStu = new JScrollPane(liststu);
    private CPanel northLecPan = new CPanel();
    private CPanel northStuPan = new CPanel();
    private JButton enrolBtnLec = new JButton(">>>");
    private JButton enrolBtnStu = new JButton(">>>");
    private JList listClassStudent = new JList(modecla);
    private JList listClassLecture = new JList(modeLecInClass);
    private JScrollPane classesScroll = new JScrollPane(listClassStudent);
    private JScrollPane classesLecScroll = new JScrollPane(listClassLecture);
    private CPanel northClass = new CPanel();
    private JComboBox comboClass = new JComboBox<>();
    private final HashMap<String, ArrayList<String>> enrolment;
    private final HashMap<String, ArrayList<String>> schedule;
    private CPanel studentDisplay = new CPanel();
    private static CellStudent[][] cells;
    private Classes c;

    public EnrollmentFrame() {
        setSize(800, 600);
        setVisible(true);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        enrolment = model.getEnrollMap();
        classList = model.getClassList();
        studentlist = model.getActiveStudentList();
        teacherList = model.getActiveTeacherList();
        schedule = model.getSchedule();

        add(mainPn);
        mainPn.add(leftComponent, BorderLayout.WEST);
        mainPn.add(rightComponent, BorderLayout.CENTER);
        mainPn.add(buttonPn, BorderLayout.SOUTH);
        buttonPn.add(backBt);
        buttonPn.add(closeBt);

        backBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassRoomFrame();
                dispose();
            }
        });

        closeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        leftComponent.setLayout(new GridLayout(1, 3));
        rightComponent.setLayout(new BorderLayout());
        leftComponent.setBorder(BorderFactory.createTitledBorder(resourse.getString("STUDENT_AND_LECTURE_T_TXT")));
        rightComponent.setBorder(BorderFactory.createTitledBorder(resourse.getString("CLASS_AVA_T_TXT")));

        leftComponent.add(lecturePanel);
        lecturePanel.setLayout(new BorderLayout());
        lecturePanel.setPreferredSize(new Dimension(120, 500));
        lecturePanel.add(northLecPan, BorderLayout.NORTH);
        northLecPan.setLayout(new FlowLayout());
        northLecPan.setPreferredSize(new Dimension(100, 100));
        northLecPan.add(new JLabel("<html><b>"+resourse.getString("LECTURE_ID_T_TXT")+"</b></html>"));
        northLecPan.add(lecSearch);
        northLecPan.add(lecBtn);
        lecSearch.setPreferredSize(new Dimension(120, 30));
        lecturePanel.add(scrollPacLec, BorderLayout.CENTER);
        lecturePanel.add(enrolBtnLec, BorderLayout.SOUTH);

        leftComponent.add(studentPanel);
        studentPanel.setLayout(new BorderLayout());
        studentPanel.setPreferredSize(new Dimension(120, 500));
        studentPanel.add(northStuPan, BorderLayout.NORTH);
        northStuPan.setLayout(new FlowLayout());
        northStuPan.setPreferredSize(new Dimension(100, 100));
        northStuPan.add(new JLabel("<html><b>"+resourse.getString("STUDENT_ID_T_TXT")+"</b></html>"));
        northStuPan.add(stuSearch);
        northStuPan.add(stuBtn);
        stuSearch.setPreferredSize(new Dimension(120, 30));
        studentPanel.add(scrollPacStu, BorderLayout.CENTER);
        studentPanel.add(enrolBtnStu, BorderLayout.SOUTH);

        rightComponent.setLayout(new BorderLayout());
        rightComponent.add(northClass, BorderLayout.NORTH);
        northClass.add(new JLabel("<html><b>"+resourse.getString("CLASS_ID_T_TXT")+" </b></html>"));
        northClass.add(comboClass);
        comboClass.setPreferredSize(new Dimension(100, 30));
        rightComponent.add(classesLecScroll, BorderLayout.SOUTH);
        rightComponent.add(studentDisplay, BorderLayout.CENTER);
        studentDisplay.setLayout(new GridLayout(4, 5));

        setCell();
        createComboClass();
        fillListStudent();
        fillListLecture();
        setStudentCell();
        addListenerBtn();
        addTeacherIntoClass();
        createListenerForLectureCLass();
        createSearchBtn();
//        
//        ImportExportSCV ie = new ImportExportSCV();
////        ie.exportSCVAccount("account.txt");
////        ie.importSCV("C:/account.txt", 4);
////        ie.activeImport(4);
//        ie.activeExport(4);
//        ie.exportSCVStudent("sudent.txt");
//        ie.importSCV("sudent.txt", 2);
        
//        ie.exportSCVTeacher("teac.txt");
//        ie.importSCV("teac.txt", 3);
        
//        ie.exportSCVClassType("lyn.txt");
//        ie.importSCV("lyn.txt", 1);
    }

    public EnrollmentFrame(Classes c) {

        this.c = c;

        setSize(800, 600);
        setVisible(true);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        enrolment = model.getEnrollMap();
        classList = model.getClassList();
        studentlist = model.getActiveStudentList();
        teacherList = model.getActiveTeacherList();
        schedule = model.getSchedule();

        add(mainPn);
        mainPn.add(leftComponent, BorderLayout.WEST);
        mainPn.add(rightComponent, BorderLayout.CENTER);
        mainPn.add(buttonPn, BorderLayout.SOUTH);
        buttonPn.add(backBt);
        buttonPn.add(closeBt);

        backBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassRoomFrame();
                dispose();
            }
        });

        closeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        leftComponent.setLayout(new GridLayout(1, 3));
        rightComponent.setLayout(new BorderLayout());
        leftComponent.setBorder(BorderFactory.createTitledBorder(resourse.getString("STUDENT_AND_LECTURE_T_TXT")));
        rightComponent.setBorder(BorderFactory.createTitledBorder(resourse.getString("CLASS_AVA_T_TXT")));

        leftComponent.add(lecturePanel);
        lecturePanel.setLayout(new BorderLayout());
        lecturePanel.setPreferredSize(new Dimension(120, 500));
        lecturePanel.add(northLecPan, BorderLayout.NORTH);
        northLecPan.setLayout(new FlowLayout());
        northLecPan.setPreferredSize(new Dimension(100, 100));
        northLecPan.add(new JLabel("<html><b>"+resourse.getString("LECTURE_ID_T_TXT")+"</b></html>"));
        northLecPan.add(lecSearch);
        northLecPan.add(lecBtn);
        lecSearch.setPreferredSize(new Dimension(120, 30));
        lecturePanel.add(scrollPacLec, BorderLayout.CENTER);
        lecturePanel.add(enrolBtnLec, BorderLayout.SOUTH);

        leftComponent.add(studentPanel);
        studentPanel.setLayout(new BorderLayout());
        studentPanel.setPreferredSize(new Dimension(120, 500));
        studentPanel.add(northStuPan, BorderLayout.NORTH);
        northStuPan.setLayout(new FlowLayout());
        northStuPan.setPreferredSize(new Dimension(100, 100));
        northStuPan.add(new JLabel("<html><b>"+resourse.getString("STUDENT_ID_T_TXT")+"</b></html>"));
        northStuPan.add(stuSearch);
        northStuPan.add(stuBtn);
        stuSearch.setPreferredSize(new Dimension(120, 30));
        studentPanel.add(scrollPacStu, BorderLayout.CENTER);
        studentPanel.add(enrolBtnStu, BorderLayout.SOUTH);

        rightComponent.setLayout(new BorderLayout());
        rightComponent.add(northClass, BorderLayout.NORTH);
        northClass.add(new JLabel("<html><b>"+resourse.getString("CLASS_ID_T_TXT")+" </b></html>"));
        northClass.add(comboClass);
        comboClass.setPreferredSize(new Dimension(100, 30));
        rightComponent.add(classesLecScroll, BorderLayout.SOUTH);
        rightComponent.add(studentDisplay, BorderLayout.CENTER);
        studentDisplay.setLayout(new GridLayout(4, 5));

        setCell();
        createComboClass();
        fillListStudent();
        fillListLecture();
        setStudentCell();
        addListenerBtn();
        addTeacherIntoClass();
        createListenerForLectureCLass();
        createSearchBtn();
    }

    public void fillListStudent() {
        modelstu.removeAllElements();
        for (Student stu : studentlist) {
            if (comboClass.getSelectedItem() != null) {
                String idclass = ((Classes) comboClass.getSelectedItem()).getId();
                if (enrolment.containsKey(idclass)) {
                    int count = 0;
                    for (String str : enrolment.get(idclass)) {
                        if (str.equals(stu.getId())) {
                            count++;
                        }
                    }
                    if (count == 0) {
                        modelstu.addElement(stu);
                    }
                } else {
                    modelstu.addElement(stu);
                }
            }
        }
    }

    public void fillListStudentSearch(String search) {
        modelstu.removeAllElements();
        for (Student stu : studentlist) {
            if (comboClass.getSelectedItem() != null) {
                String idclass = ((Classes) comboClass.getSelectedItem()).getId();
                if (enrolment.containsKey(idclass)) {
                    int count = 0;
                    for (String str : enrolment.get(idclass)) {
                        if (str.equals(stu.getId())) {
                            count++;
                        }
                    }
                    if (count == 0) {
//                        modelstu.addElement(stu);
                        if (stu.getId().contains(search)) {
                            modelstu.addElement(stu);
                        }
                    }
                } else {
                    if (stu.getId().contains(search)) {
                        modelstu.addElement(stu);
                    }
//                    modelstu.addElement(stu);
                }
            }
        }
    }

    public void fillListLecture() {
        modellec.removeAllElements();
        for (Teacher stu : teacherList) {
            if (comboClass.getSelectedItem() != null) {
                String idclass = ((Classes) comboClass.getSelectedItem()).getId();
                if (enrolment.containsKey(idclass)) {
                    int count = 0;
                    for (String str : enrolment.get(idclass)) {
                        if (str.equals(stu.getId())) {
                            count++;
                        }
                    }
                    if (count == 0) {
                        for (String item : stu.getSkillArray()) {
                            if (model.findClassTypeById(model.findClassById(idclass).
                                    getClassTypeid()).getName().equals(item)) {
                                modellec.addElement(stu);
                            }
                        }


                    }
                } else {
                    for (String item : stu.getSkillArray()) {
                        if (model.findClassTypeById(model.findClassById(idclass).
                                    getClassTypeid()).getName().equals(item)) {
                            modellec.addElement(stu);
                        }
                    }
                }
            }
        }
    }

    public void fillListLectureSearch(String search) {
        modellec.removeAllElements();
        for (Teacher stu : teacherList) {
            if (comboClass.getSelectedItem() != null) {
                String idclass = ((Classes) comboClass.getSelectedItem()).getId();
                if (enrolment.containsKey(idclass)) {
                    int count = 0;
                    for (String str : enrolment.get(idclass)) {
                        if (str.equals(stu.getId())) {
                            count++;
                        }
                    }
                    if (count == 0) {
//                        modellec.addElement(stu);
                        if (stu.getId().contains(search)) {
                            for (String item : stu.getSkillArray()) {
                                if (model.findClassTypeById(model.findClassById(idclass).
                                    getClassTypeid()).getName().equals(item)) {
                                    modellec.addElement(stu);
                                }
                            }
                        }
                    }
                } else {
//                    modellec.addElement(stu);
                    if (stu.getId().contains(search)) {
                        for (String item : stu.getSkillArray()) {
                            if (model.findClassTypeById(model.findClassById(idclass).
                                    getClassTypeid()).getName().equals(item)) {
                                modellec.addElement(stu);
                            }
                        }
                    }
                }
            }
        }
    }

    public void createSearchBtn() {
        stuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = stuSearch.getText();
                if (search.equals("")) {
//                    modelstu.removeAllElements();
//                    for (Student stu : studentlist) {
//                        modelstu.addElement(stu);
//                    }
                    fillListStudent();
                } else {
//                    modelstu.removeAllElements();
//                    for (Student stu : studentlist) {
//                        if (stu.getId().contains(search)) {
//                            modelstu.addElement(stu);
//                        }
//                    }
                    fillListStudentSearch(search);
                }
            }
        });
        lecBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = lecSearch.getText();
                if (search.equals("")) {
//                    modellec.removeAllElements();
//                    for (Teacher stu : teacherList) {
//                        modellec.addElement(stu);
//                    }
                    fillListLecture();
                } else {
//                    modellec.removeAllElements();
//                    for (Teacher stu : teacherList) {
//                        if (stu.getId().contains(search)) {
//                            modellec.addElement(stu);
//                        }
//                    }
                    fillListLectureSearch(search);
                }
            }
        });
        stuSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String search = stuSearch.getText();
                    if (search.equals("")) {
//                        modelstu.removeAllElements();
//                        for (Student stu : studentlist) {
//                            modelstu.addElement(stu);
//                        }
                        fillListStudent();
                    } else {
//                        modelstu.removeAllElements();
//                        for (Student stu : studentlist) {
//                            if (stu.getId().contains(search)) {
//                                modelstu.addElement(stu);
//                            }
//                        }
                        fillListStudentSearch(search);
                    }
                }
            }
        });
        lecSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String search = lecSearch.getText();
                    if (search.equals("")) {
//                        modellec.removeAllElements();
//                        for (Teacher stu : teacherList) {
//                            modellec.addElement(stu);
//                        }
                        fillListLecture();
                    } else {
//                        modellec.removeAllElements();
//                        for (Teacher stu : teacherList) {
//                            if (stu.getId().contains(search)) {
//                                modellec.addElement(stu);
//                            }
//                        }
                        fillListLectureSearch(search);
                    }
                }
            }
        });
    }

    public void createListenerForLectureCLass() {

        listClassLecture.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    doPop(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    doPop(e);
                }
            }

            private void doPop(MouseEvent e) {
                if (listClassLecture.getSelectedValue() != null) {
                    final JPopupMenu menu = new JPopupMenu();
                    JMenuItem anItem2 = new JMenuItem(resourse.getString("DELETE_T_TXT"));
                    menu.add(anItem2);
                    anItem2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            deletedLectureInClass((Teacher) listClassLecture.getSelectedValue());
                        }
                    });
                    menu.show(e.getComponent(), e.getX(), e.getY());

                }

            }
        });
    }

    public void deletedLectureInClass(Teacher tea) {
        if (!enrolment.isEmpty()) {
            if (comboClass.getSelectedItem() != null) {
                String stri = "";
                for (String str : enrolment.get(((Classes) comboClass.getSelectedItem()).getId())) {
                    if (tea.getId().equals(str)) {
                        stri = str;
                    }
                }
                enrolment.get(((Classes) comboClass.getSelectedItem()).getId()).remove(stri);
            }
        }
        modeLecInClass.removeElement(tea);
        fillListLecture();
    }

    public void addListenerBtn() {
        enrolBtnStu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liststu.getSelectedValue() != null) {
                    if (validStudentInClassWithOtherClasses((Student) liststu.getSelectedValue(), (Classes) comboClass.getSelectedItem())) {
                        model.addStudentEnroll(((Classes) comboClass.getSelectedItem()).getId(),
                                ((Student) liststu.getSelectedValue()).getId());
                        repaintAll();
                        fillListStudent();
                    } else {
                        JOptionPane.showMessageDialog(null, resourse.getString("CANNOT_ADD_STUDENT_TTB_CLF_T_TXT"));

                    }

                }
            }
        });

        enrolBtnLec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listlec.getSelectedValue() != null) {
                    if (validTeacherInClassWithOtherClasses((Teacher) listlec.getSelectedValue(), (Classes) comboClass.getSelectedItem())) {
                        model.addTeacherEnroll(((Classes) comboClass.getSelectedItem()).getId(),
                                ((Teacher) listlec.getSelectedValue()).getId());
                        addTeacherIntoClass();
                        fillListLecture();
                    } else {
                        JOptionPane.showMessageDialog(null,resourse.getString("CANNOT_ADD_LECTURE_TTB_CLF_T_TXT"));

                    }

                }
            }
        });
    }

    public void addTeacherIntoClass() {
        modeLecInClass.removeAllElements();
        if (!enrolment.isEmpty()) {
            if (enrolment.containsKey(((Classes) comboClass.getSelectedItem()).getId())) {
                ArrayList<String> array = enrolment.get(((Classes) comboClass.getSelectedItem()).getId());
                for (String str : array) {
                    if (model.studentOrTeacherCheck(str).equals("Teacher")) {
                        modeLecInClass.addElement(model.findTeacherById(str));
                    }
                }
            }
        }
    }

    public void setCell() {
        cells = new CellStudent[5][4];
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 5; x++) {
                cells[x][y] = new CellStudent(x, y, this);
                studentDisplay.add(cells[x][y]);
            }
        }
    }

    public void createComboClass() {
        if (c != null) {
            comboClass.addItem(c);

        } else {
            for (Classes cla : classList) {
                if (model.isClassAtSchedule(cla.getId())) {
                    comboClass.addItem(cla);
                }
            }
        }

        comboClass.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addTeacherIntoClass();
                        fillListLecture();
                        fillListStudent();
                        repaintAll();
                    }
                });

    }

    public void repaintAll() {
        studentDisplay.removeAll();
        setCell();
        setStudentCell();
        validate();
        repaint();
    }

    public void setStudentCell() {
        if (!enrolment.isEmpty() ) {
            if (enrolment.containsKey(((Classes) comboClass.getSelectedItem()).getId())) {
                ArrayList<String> array = enrolment.get(((Classes) comboClass.getSelectedItem()).getId());
                for (String str : array) {
                    if (model.studentOrTeacherCheck(str).equals("Student")) {
                        System.out.println("OK");
                        addStudentIntoCell(model.findStudentById(str));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(laf.getName())) {
                    UIManager.setLookAndFeel(laf.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Cannot load the Nimbus theme!!!");
        }
        JFrame f = new JFrame();
        EnrollmentFrame a = new EnrollmentFrame();
        f.add(a);
        f.setVisible(true);
        f.setSize(680, 600);
        f.setDefaultCloseOperation(2);



    }

    public void addStudentIntoCell(Student stu) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 5; x++) {
                if (cells[x][y].getStudent() == null) {

                    cells[x][y].setStudent(stu);
                    return;

//                        JOptionPane.showMessageDialog(null, "Can not add this student! the timetable is conflicted!!");


                }
            }
        }
    }

    public void deletedStudentInClasss(Student student) {
        String classID = (((Classes) comboClass.getSelectedItem()).getId());
        String stri = "";
        for (String str : enrolment.get(classID)) {
            if (str.equals(student.getId())) {
                stri = str;
            }
        }
        enrolment.get(classID).remove(stri);
        repaintAll();
        fillListStudent();
    }

    public boolean validStudentInClassWithOtherClasses(Student student, Classes cla) {
        ArrayList<String> currentClassTime = new ArrayList<>();
        ArrayList<String> otherClassTimeOfStudent = new ArrayList<>();

        currentClassTime = findClassByClassIdAtSchedule(cla.getId());
        otherClassTimeOfStudent = findClassTimeByStudent(student);

        if (currentClassTime == null) {
            return true;
        } else if (otherClassTimeOfStudent == null) {
            return true;
        }
        if (isOverlapCompareTwoHour(currentClassTime, otherClassTimeOfStudent)) {
            return false;
        }

        return true;
    }

    public boolean validTeacherInClassWithOtherClasses(Teacher student, Classes cla) {
        ArrayList<String> currentClassTime = new ArrayList<>();
        ArrayList<String> otherClassTimeOfStudent = new ArrayList<>();

        currentClassTime = findClassByClassIdAtSchedule(cla.getId());
        otherClassTimeOfStudent = findClassTimeByTeacher(student);

        if (currentClassTime == null) {
            return true;
        } else if (otherClassTimeOfStudent == null) {
            return true;
        }
        if (isOverlapCompareTwoHour(currentClassTime, otherClassTimeOfStudent)) {
            return false;
        }

        return true;
    }

    public boolean isOverlapCompareTwoHour(ArrayList<String> one, ArrayList<String> two) {
        for (String itemone : one) {
            for (String itemtwo : two) {
                String[] firstarr = itemone.split("-");
                String[] secondarr = itemtwo.split("-");

                if (firstarr[0].equals(secondarr[0])) {
                    double a = Double.parseDouble(firstarr[1]);
                    double b = Double.parseDouble(firstarr[2]);
                    double c = Double.parseDouble(secondarr[1]);
                    double d = Double.parseDouble(secondarr[2]);

                    if (c < a && a < d) {
                        return true;
                    } else if (c < b && b < d) {
                        return true;
                    } else if (a < c && d < b) {
                        return true;
                    } else if (a == c && b == d) {
                        return true;
                    } 

                }
            }
        }
        return false;
    }

    public ArrayList<String> findClassTimeByStudent(Student student) {
        ArrayList<String> finalarray = new ArrayList<String>();
        String studentId = student.getId();
        if (!enrolment.isEmpty()) {
            Set<String> arr = enrolment.keySet();

            for (String str : arr) {
                ArrayList<String> strs = enrolment.get(str);

                for (String st : strs) {
                    if (st.equals(studentId)) {

                        for (String newstr : findClassByClassIdAtSchedule(str)) {

                            finalarray.add(newstr);
                        }
                    }
                }
            }
            return finalarray;
        }
        return null;
    }

    public ArrayList<String> findClassTimeByTeacher(Teacher student) {
        ArrayList<String> finalarray = new ArrayList<String>();
        String studentId = student.getId();
        if (!enrolment.isEmpty()) {
            Set<String> arr = enrolment.keySet();

            for (String str : arr) {
                ArrayList<String> strs = enrolment.get(str);

                for (String st : strs) {
                    if (st.equals(studentId)) {

                        for (String newstr : findClassByClassIdAtSchedule(str)) {

                            finalarray.add(newstr);
                        }
                    }
                }
            }
            return finalarray;
        }
        return null;
    }

    public ArrayList<String> findClassByClassIdAtSchedule(String classId) {

        ArrayList<String> code = new ArrayList<>();
        if (!schedule.isEmpty()) {
            Set<String> arr = schedule.keySet();

            for (String str : arr) {
                ArrayList<String> strs = schedule.get(str);
                for (String st : strs) {
                    String[] parts = st.split("-");
                    String part4 = parts[3];

                    System.out.println(part4);
                    if (part4.equals(classId)) {

                        code.add(st);

                    }
                }
            }
            return code;
        }
        return null;
    }
}
