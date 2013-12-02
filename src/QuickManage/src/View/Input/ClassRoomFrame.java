package View.Input;

import Model.Model;
import Model.Ultility;
import Model.Room;
import Model.Classes;
import View.CustomComponent.Cell;
import View.CustomComponent.CPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lyn
 */
public class ClassRoomFrame extends JDialog implements Ultility {

    private CPanel panTimetable = new CPanel();
    private CPanel classChoose = new CPanel();
    private CPanel timeTableGrid = new CPanel();
    private CPanel timeLeftSide = new CPanel();
    private CPanel dateAtTop = new CPanel();
    private CPanel wrapTop = new CPanel();
    private CPanel mainPn = new CPanel(new BorderLayout(), INPUT_PANEL_PIC);
    ArrayList<Classes> classlist;
    ArrayList<Room> roomlist;
    private final HashMap<String, ArrayList<String>> schedule;
    private static Cell[][] cells;
    Model model = Model.getModel();
    ResourceBundle resourse = model.getResources();
    private int lengthHours;
    final JComboBox comboRoom = new JComboBox();
    final JComboBox comboLength = new JComboBox();
    final JComboBox comboClass = new JComboBox();
    private final JButton nextBt = new JButton(resourse.getString("NEXT_T_TXT"));
    private final JButton closeBt = new JButton(resourse.getString("CLOSE_T_TXT"));
    private final CPanel buttonPn = new CPanel();
    private Classes c;
    
    private int rowOfTable = 52;

    public ClassRoomFrame() {

        setSize(800, 780);

        schedule = model.getSchedule();
        classlist = model.getClassList();
        roomlist = model.getRoomList();
//        setLayout(new BorderLayout());

        add(mainPn);
        panTimetable.setBorder(BorderFactory.createTitledBorder(resourse.getString("TIME_TABLE_T_TXT")));
        mainPn.add(panTimetable, BorderLayout.CENTER);
        panTimetable.setLayout(new BorderLayout());
        panTimetable.add(timeTableGrid, BorderLayout.CENTER);
        timeTableGrid.setLayout(new GridLayout(rowOfTable, 6));
        setDateAtTopComponent();
        panTimetable.add(wrapTop, BorderLayout.NORTH);
        wrapTop.add(dateAtTop);
        wrapTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setTimeAtLeftSide();
        panTimetable.add(timeLeftSide, BorderLayout.WEST);
        classChoose.setBorder(BorderFactory.createTitledBorder(resourse.getString("ROOM_AVA_T_TXT")));
        mainPn.add(classChoose, BorderLayout.WEST);
        classChoose.setLayout(new GridLayout(20, 1));
        createComboRoom();
        classChoose.add(new JLabel(resourse.getString("ROOM_T_TXT")));
        classChoose.add(comboRoom);
        createComboClass();
        classChoose.add(new JLabel(resourse.getString("CLASS_T_TXT")));
        classChoose.add(comboClass);
        createComboLenght();
        classChoose.add(new JLabel(resourse.getString("LENGTH_T_TXT")));
        classChoose.add(comboLength);
      

        closeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPn.add(buttonPn, BorderLayout.SOUTH);
       
        buttonPn.add(closeBt);
        setCells();
//        setGridLayOut();
        setVisible(true);

        if (comboClass.getItemCount() != 0) {
            comboClass.setSelectedIndex(0);
        }
    }

    public ClassRoomFrame(final Classes c) {

        this.c = c;

        setSize(800, 780);


        schedule = model.getSchedule();
        classlist = model.getClassList();
        roomlist = model.getRoomList();
//        setLayout(new BorderLayout());

        add(mainPn);
        panTimetable.setBorder(BorderFactory.createTitledBorder(resourse.getString("TIME_TABLE_T_TXT")));
        mainPn.add(panTimetable, BorderLayout.CENTER);
        panTimetable.setLayout(new BorderLayout());
        panTimetable.add(timeTableGrid, BorderLayout.CENTER);
        timeTableGrid.setLayout(new GridLayout(rowOfTable, 6));
        setDateAtTopComponent();
        panTimetable.add(wrapTop, BorderLayout.NORTH);
        wrapTop.add(dateAtTop);
        wrapTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setTimeAtLeftSide();
        panTimetable.add(timeLeftSide, BorderLayout.WEST);
        classChoose.setBorder(BorderFactory.createTitledBorder(resourse.getString("ROOM_AVA_T_TXT")));
        mainPn.add(classChoose, BorderLayout.WEST);
        classChoose.setLayout(new GridLayout(20, 1));
        createComboRoom();
        classChoose.add(new JLabel(resourse.getString("ROOM_T_TXT")));
        classChoose.add(comboRoom);
        createComboClass();
        classChoose.add(new JLabel(resourse.getString("CLASS_T_TXT")));
        classChoose.add(comboClass);
        createComboLenght();
        classChoose.add(new JLabel(resourse.getString("LENGTH_T_TXT")));
        classChoose.add(comboLength);
        nextBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EnrollmentFrame(c);
                dispose();
            }
        });

        closeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPn.add(buttonPn, BorderLayout.SOUTH);
        buttonPn.add(nextBt);
        buttonPn.add(closeBt);
        setCells();
        setGridLayOut();
        setVisible(true);

        if (comboClass.getItemCount() != 0) {
            comboClass.setSelectedIndex(0);
        }
    }

    public void setTargetNew(int x, int y) {
        for (int i = 1; i <= 6; i++) {
            if (i == lengthHours -1) {
                for (int j = 1; j <= lengthHours -1; j++) {
                    if (y + j <= rowOfTable - 1) {
                        cells[x][y + j].setStatus("MousePosition");
                    }
                }
            }
        }
    }

    public void clearTarget(int x, int y) {
        for (int i = 1; i <= 6; i++) {
            if (i == lengthHours -1) {
                for (int j = 1; j <= lengthHours - 1; j++) {
                    if (y + j <= rowOfTable - 1) {
                        cells[x][y + j].setStatus("Out");
                    }
                }
            }
        }
    }

    public void setLengthHours(int lengthHours) {
        this.lengthHours = lengthHours;
    }

    public void setMap(String code, int flag) {
        //code = date-time-classId
        // eg. 2-10-12-C000002
        String[] parts = code.split("-");
        int part1 = Integer.parseInt(parts[0]);
        double part2 = Double.parseDouble(parts[1]);
        double part3 = Double.parseDouble(parts[2]);
        String part4 = parts[3];
        if (flag == 1) {
            cells[part1 - 2][convertHoursToNumber(part2)].setTitleForLabel(part4);
            for (; part2 < part3; part2 = part2 + 0.25) {
                cells[part1 - 2][convertHoursToNumber(part2)].setLocationClass("Seted");
                cells[part1 - 2][convertHoursToNumber(part2)].setInfo(code);
                cells[part1 - 2][convertHoursToNumber(part2)].setTextForLabel(part4);
            }
        }
        if (flag == 2) {
            cells[part1 - 2][convertHoursToNumber(part2)].setTitleForLabel(part4);
            for (; part2 < part3; part2 = part2 + 0.25) {
                cells[part1 - 2][convertHoursToNumber(part2)].setClassLocationAtOtherRoom("Yes");
                cells[part1 - 2][convertHoursToNumber(part2)].setInfo(code);
                cells[part1 - 2][convertHoursToNumber(part2)].setTextForLabel(part4);
            }
        }
    }

    public boolean checkValidation(String code) {
        String[] parts = code.split("-");
        int part1 = Integer.parseInt(parts[0]);
        double part2 = Double.parseDouble(parts[1]);
        double part3 = Double.parseDouble(parts[2]);
        String part4 = parts[3];
        for (; part2 < part3; part2 = part2 + 0.25) {
            if (cells[part1 - 2][convertHoursToNumber(part2)].getLocationClass().equals("Seted")) {
                return false;
            }
        }
        return true;
    }

    public int convertHoursToNumber(double hour) {
        for (int i = 0; i < Ultility.ARRAYHOURS.length; i++) {
            if (hour == Ultility.ARRAYHOURS[i]) {
                return Ultility.ARRAYNUMBER[i];
            }
        }
        return -1;
    }

    public double convertNumberToHour(double number) {
        for (int i = 0; i < Ultility.ARRAYNUMBER.length; i++) {
            if (number == Ultility.ARRAYNUMBER[i]) {
                return Ultility.ARRAYHOURS[i];
            }
        }
        return -1;
    }

    private void setCells() {
        cells = new Cell[6][rowOfTable];
        for (int y = 0; y < rowOfTable; y++) {
            for (int x = 0; x < 6; x++) {
                cells[x][y] = new Cell(x, y, this);
                timeTableGrid.add(cells[x][y]);
                final int x1 = x;
                final int y1 = y;

                cells[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
//                        if (!model.isClassHavePeople(((Classes) comboClass.getSelectedItem()).getId())) {
                        if (comboClass.getSelectedItem() != null) {
                            if (!model.isClassHavePeople(((Classes) comboClass.getSelectedItem()).getId())) {
                                System.out.println(model.findNumberOfLessonOfClass((Classes) comboClass.getSelectedItem()));
                                System.out.println(model.findClassTypeById(((Classes) comboClass.getSelectedItem()).getClassTypeid() ).getLessonWeek());
                                if (model.findNumberOfLessonOfClass((Classes) comboClass.getSelectedItem()) 
                                        < model.findClassTypeById(((Classes) comboClass.getSelectedItem()).getClassTypeid() ).getLessonWeek()) {
                                    mouseClickFunction(x1, y1);
                                } else {
                                    JOptionPane.showMessageDialog(cells[x1][y1], resourse.getString("CANNOT_ADD_SCHEDULE_1"));
                                }
                                
                            } else {
                                JOptionPane.showMessageDialog(cells[x1][y1], resourse.getString("CANNOT_ADD_SCHEDULE_2"));
                            }
                        }
//                        } else {
//                              JOptionPane.showMessageDialog(cells[x1][y1], "Can not add new schedule for class which already fill with students !");
//                        }

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        setTargetNew(x1, y1);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        clearTarget(x1, y1);
                    }
                });
            }
        }
    }

    public void repaintAll() {
        validate();
        repaint();
    }

    private void setGridLayOut() {
        String roomid = ((Room) comboRoom.getSelectedItem()).getId();
        if (schedule.containsKey(roomid)) {
            ArrayList<String> timeTable = schedule.get(((Room) comboRoom.getSelectedItem()).getId());
            for (String str : timeTable) {
                setMap(str, 1);
            }
        }
        if (comboClass.getSelectedItem() != null) {
            ArrayList<String> otherRoom = findCLassIdAtOtherRoom(roomid, ((Classes) comboClass.getSelectedItem()).getId());
            for (String str : otherRoom) {
                setMap(str, 2);
            }
        }
    }

    private ArrayList<String> findCLassIdAtOtherRoom(String currRoomId, String classId) {
        ArrayList<String> content = new ArrayList<>();
        for (Room ro : roomlist) {
            if (!ro.getId().equals(currRoomId)) {
                if (!schedule.isEmpty()) {
                    if (schedule.containsKey(ro.getId())) {
                        ArrayList<String> arr = schedule.get(ro.getId());
                        if (!arr.isEmpty()) {
                            for (String str : arr) {
                                if (str.contains(classId)) {
                                    content.add(str);
                                }
                            }
                        }
                    }
                }
            }
        }

        return content;
    }

    public void mouseClickFunction(int x1, int y1) {
        // roomid   date-time-classId
        String mapSet = "";
        String room = ((Room) comboRoom.getSelectedItem()).getId();
        String date = Integer.toString(x1 + 2);
        String time1 = Double.toString(convertNumberToHour(y1));
//        double length = Double.parseDouble((String) comboLength.getSelectedItem());
        double length = lengthHours;
        if (length == 3) {
            length = 0.75;
        } else if (length == 4) {
            length = 1;
        }
        String time2 = Double.toString(convertNumberToHour(y1) + length);
        String classId = ((Classes) comboClass.getSelectedItem()).getId();
        mapSet = date + "-" + time1 + "-" + time2 + "-" + classId;

        if ((convertNumberToHour(y1) + length) <= 21) {
            if (checkValidation(mapSet)) {
                model.addClassSchedule(room, mapSet);
                timeTableGrid.removeAll();
                setCells();
                repaintAll();
                setGridLayOut();
            }
        }
    }

    public void deleteClassSchedule(String code) {
        String[] parts = code.split("-");
        String part4 = parts[3];
        if ((!model.isClassHavePeople(part4))) {


            String idRoom = (((Room) comboRoom.getSelectedItem()).getId());
            String stri = "";
            for (String str : schedule.get(idRoom)) {
                if (str.equals(code)) {
                    stri = str;
                }
            }
            schedule.get(idRoom).remove(stri);

            timeTableGrid.removeAll();
            setCells();
            repaintAll();
            setGridLayOut();
        } else {
            JOptionPane.showMessageDialog(this, resourse.getString("CANNOT_DELETE_CLASS_WITH_STUDENT"));
        }
    }

    public void editClassSchedule(String code) {
        String[] parts = code.split("-");
        String part4 = parts[3];
        if (!model.isClassHavePeople(part4)) {

            comboClass.setSelectedItem(model.findClassById(part4));
            String idRoom = (((Room) comboRoom.getSelectedItem()).getId());
            String stri = "";
            for (String str : schedule.get(idRoom)) {
                if (str.equals(code)) {
                    stri = str;
                }
            }
            schedule.get(idRoom).remove(stri);
            timeTableGrid.removeAll();
            setCells();
            repaintAll();
            setGridLayOut();
        } else {
            JOptionPane.showMessageDialog(this, resourse.getString("CANNOT_EDIT_CLASS_WITH_STUDENT"));
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

    private void createComboRoom() {
        if (c == null) {
            for (Room rooms : roomlist) {
                comboRoom.addItem(rooms);
                comboRoom.setSelectedIndex(0);
            }
        } else {
            for (Room rooms : roomlist) {
                if (model.findClassTypeById(c.getClassTypeid()).getName().equals(rooms.getRoomType()) ) {
                    comboRoom.addItem(rooms);
                    comboRoom.setSelectedIndex(0);
                }

            }
        }



        comboRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeTableGrid.removeAll();
                setCells();
                repaintAll();
                setGridLayOut();

                comboClass.removeAllItems();
                createComboClass();
            }
        });
    }

    private void createComboClass() {
        int count = 0;
        for (Classes cla : classlist) {
            if (model.findClassTypeById(cla.getClassTypeid()).getName().equals
               ( ((Room) comboRoom.getSelectedItem()).getRoomType()) ){
                if (c != null) {
                    if (count == 0) {

                        comboClass.addItem(c);
                        count++;
                    }
                } else {
                    comboClass.addItem(cla);
                    count++;
                }
            }
        }
        System.out.println(count);
        if (count >= 1) {
            comboLength.addItem("45'");
            comboLength.addItem("60'");
          


            comboClass.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (((Classes) comboClass.getSelectedItem()) != null) {
                        if (((Classes) comboClass.getSelectedItem()).getHour() == 45) {
                            comboLength.setSelectedIndex(0);
                        }
                        if (((Classes) comboClass.getSelectedItem()).getHour() == 60) {
                            comboLength.setSelectedIndex(1);
                        }
//                        if (((Classes) comboClass.getSelectedItem()).getHour() == 2) {
//                            comboLength.setSelectedIndex(2);
//                        }
//                        if (((Classes) comboClass.getSelectedItem()).getHour() == 2.5) {
//                            comboLength.setSelectedIndex(3);
//                        }
//                        if (((Classes) comboClass.getSelectedItem()).getHour() == 3) {
//                            comboLength.setSelectedIndex(4);
//                        }
                    }
                    timeTableGrid.removeAll();
                    setCells();
                    repaintAll();
                    setGridLayOut();
                }
            });

            if (!classlist.isEmpty()) {
                comboClass.setSelectedIndex(0);
              
            }

//            if (c!=null) {
//                comboClass.setSelectedItem(c);
//            }
        }
    }

    private void createComboLenght() {
        comboLength.setEnabled(false);
        comboLength.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) comboLength.getSelectedItem();
                if (s.equals("45'")) {
                    setLengthHours(3);
                }
                if (s.equals("60'")) {
                    setLengthHours(4);
                }
//                if (s.equals("2.00")) {
//                    setLengthHours(3);
//                }
//                if (s.equals("2.30")) {
//                    setLengthHours(4);
//                }
//                if (s.equals("3.00")) {
//                    setLengthHours(5);
//                }
            }
        });

    }

}
// fix the layout
// change 
