/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Input;

import Model.Classes;
import Model.Model;
import Model.Student;
import Model.Teacher;
import Model.Ultility;
import View.CustomComponent.CellTeacherTimeTable;
import View.CustomComponent.CPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Lyn
 */
public class TeacherTimeTable extends JDialog implements Ultility{

    private final HashMap<String, ArrayList<String>> enrolment;
    private final HashMap<String, ArrayList<String>> schedule;
    ArrayList<Classes> classList;
    ArrayList<Student> studentlist;
    ArrayList<Teacher> teacherList;
    Model model = Model.getModel();
    Teacher current;
    CPanel wrapStudentCell = new CPanel();
    CPanel motherPanel = new CPanel(INPUT_PANEL_PIC);
    private CPanel timeLeftSide = new CPanel();
    private CPanel dateAtTop = new CPanel();
    private CPanel wrapTop = new CPanel();
    private static CellTeacherTimeTable[][] cells;
    ArrayList<String> codeStudent = new ArrayList<>();
     private int rows = 52;
      ResourceBundle resourse = model.getResources();

    public TeacherTimeTable(Teacher t) {
        enrolment = model.getEnrollMap();
        classList = model.getClassList();
        studentlist = model.getActiveStudentList();
        teacherList = model.getActiveTeacherList();
        schedule = model.getSchedule();


        current =t;

        ArrayList<String> testt = new ArrayList<String>();
        if (findClassTimeByStudent(current) != null) {
            codeStudent = findClassTimeByStudent(current);
        }
        System.out.println(current.getId());

        for (String aaa : codeStudent) {
            System.out.println(aaa);
        }


        this.setLayout(new FlowLayout());
        this.add(motherPanel);
        motherPanel.setLayout(new BorderLayout());
        motherPanel.add(wrapStudentCell, BorderLayout.CENTER);
        motherPanel.add(wrapTop, BorderLayout.NORTH);
        motherPanel.add(timeLeftSide, BorderLayout.WEST);
        setTimeAtLeftSide();
        wrapTop.add(dateAtTop);
        wrapTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setDateAtTopComponent();
        motherPanel.setBorder(BorderFactory.createTitledBorder(resourse.getString("TIME_TABLE_TEACHER_T_TXT")));
        wrapStudentCell.setLayout(new GridLayout(rows, 6));
        wrapStudentCell.setPreferredSize(new Dimension(600, 650));

        addCellToTable();

        setGridMap();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();


    }

    public void addCellToTable() {
        cells = new CellTeacherTimeTable[6][rows];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < 6; x++) {
                cells[x][y] = new CellTeacherTimeTable(x, y, this);
                wrapStudentCell.add(cells[x][y]);
            }
        }
    }

    private void setTimeAtLeftSide() {
        timeLeftSide.setLayout(new GridLayout(26, 1));
        for (double i = 8.00; i < 21.00; i = i + 0.50) {
            double y = i;
            if (!(y % 1 == 0)) {
                y = y - 0.2;
            }
            timeLeftSide.add(new JLabel(String.format("%.2f", y)));
        }
    }

    private void setDateAtTopComponent() {
        dateAtTop.setLayout(new GridLayout(1, 6));
        dateAtTop.setPreferredSize(new Dimension(555, 20));

        dateAtTop.add(new JLabel("<html><b>Mon</b></html>"));
        dateAtTop.add(new JLabel("<html><b>Tue</b></html>"));
        dateAtTop.add(new JLabel("<html><b>Wed</b></html>"));
        dateAtTop.add(new JLabel("<html><b>Thu</b></html>"));
        dateAtTop.add(new JLabel("<html><b>Fri</b></html>"));
        dateAtTop.add(new JLabel("<html><b>Sat</b></html>"));
    }


    public ArrayList<String> findClassTimeByStudent(Teacher student) {
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

    public void setMap(String code) {
        //code = date-time-classId
        // eg. 2-10-12-C000002
        String[] parts = code.split("-");
        int part1 = Integer.parseInt(parts[0]);
        double part2 = Double.parseDouble(parts[1]);
        double part3 = Double.parseDouble(parts[2]);
        String part4 = parts[3];

        cells[part1 - 2][convertHoursToNumber(part2)].setTitleForLabel(part4);
        for (; part2 < part3; part2 = part2 + 0.25) {

            cells[part1 - 2][convertHoursToNumber(part2)].setRoomId(model.findRoomByClassScheduleCode(code));
            cells[part1 - 2][convertHoursToNumber(part2)].setStatus("In");
            cells[part1 - 2][convertHoursToNumber(part2)].setTextForLabel(part4);

        }

    }

    public double convertNumberToHour(double number) {
        for (int i = 0; i < Ultility.ARRAYNUMBER.length; i++) {
            if (number == Ultility.ARRAYNUMBER[i]) {
                return Ultility.ARRAYHOURS[i];
            }
        }
        return -1;
    }

    public int convertHoursToNumber(double hour) {
        for (int i = 0; i < Ultility.ARRAYHOURS.length; i++) {
            if (hour == Ultility.ARRAYHOURS[i]) {
                return Ultility.ARRAYNUMBER[i];
            }
        }
        return -1;
    }

    public void setGridMap() {
        if (!codeStudent.isEmpty() && codeStudent.size() != 0) {
            for (String item : codeStudent) {
                setMap(item);
            }
        }
    }
}
