/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import FileData.AccountFile;
import FileData.ClassFile;
import FileData.ClassTypeFile;
import FileData.EnrollFile;
import FileData.InvoiceFile;
import FileData.InvoiceMapFile;
import FileData.PaySlipFile;
import FileData.ScheduleFile;
import FileData.StudentFile;
import FileData.TeacherFile;
import FileData.TeacherPaySlipFile;
import java.io.FileNotFoundException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author anh
 */
public final class Model implements Ultility {
    
    private static Model model = new Model();
    private ArrayList<Account> accountList = new ArrayList<>();
    private ArrayList<Classes> classList = new ArrayList<>();
    private ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Teacher> teacherList = new ArrayList<>();
    private ArrayList<Invoice> invoiceList = new ArrayList<>();
    private ArrayList<ClassType> classtypeList = new ArrayList<>();
    private ArrayList<PaySlip> paySlipList = new ArrayList<>();
    private ResourceBundle resources = ResourceBundle.getBundle("Properties/English");
    private boolean isEng = true;
    // key teacherId    values = Payslip
    private HashMap<String, PaySlip> teacherPaySlipMap = new HashMap<>();
    // key studentId    values = invoice 
    private HashMap<String, Invoice> studentInvoiceMap = new HashMap<>();
    //key = classId   values = peopleId
    private HashMap<String, ArrayList<String>> enrollMap = new HashMap<>();
    //key is room id, String is day-time-class id   eg: 2-8.5-10.5-C000123
    private HashMap<String, ArrayList<String>> schedule = new HashMap<>();
    
    public Model() {

        roomList.add(new Room("1.1", "Room Dance", "Dance"));
        roomList.add(new Room("2.1", "Room Organ", "Organ"));
        roomList.add(new Room("3.1", "Room Vorcal", "Vorcal"));
        roomList.add(new Room("4.1", "Room Painting", "Painting"));
        roomList.add(new Room("5.1", "Room Guitar", "Guitar"));
        roomList.add(new Room("6.1", "Room Violin", "Violin"));
        roomList.add(new Room("7.1", "Room Piano.1", "Piano"));
        roomList.add(new Room("7.2", "Room Piano.2", "Piano"));
        roomList.add(new Room("7.3", "Room Piano.3", "Piano"));
        
        
        
//        
//        ClassType ct1 = new ClassType(newClassTypeId(), "Piano", 0, 2, 100, 100, "check");
//        addClassType(ct1);
//        ClassType ct2 = new ClassType(newClassTypeId(), "Vorcal", 1, 1, 100, 100, "check");
//        addClassType(ct2);
//        ClassType ct3 = new ClassType(newClassTypeId(), "Guitar", 2, 2, 100, 100, "check");
//        addClassType(ct3);
//        ClassType ct4 = new ClassType(newClassTypeId(), "Guitar", 1, 1, 100, 100, "check");
//        addClassType(ct4);
//        ClassType ct5 = new ClassType(newClassTypeId(), "Guitar", 0, 2, 100, 100, "check");
//        addClassType(ct5);
        
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }
    
    

    public ArrayList<PaySlip> getPaySlipList() {
        return paySlipList;
    }

    public void setPaySlipList(ArrayList<PaySlip> paySlipList) {
        this.paySlipList = paySlipList;
    }

    public boolean isIsEng() {
        return isEng;
    }

    public void setIsEng(boolean isEng) {
        this.isEng = isEng;
    }

    public HashMap<String, PaySlip> getTeacherPaySlipMap() {
        return teacherPaySlipMap;
    }

    public void setTeacherPaySlipMap(HashMap<String, PaySlip> teacherPaySlipMap) {
        this.teacherPaySlipMap = teacherPaySlipMap;
    }
    
    
    public void setSchedule(HashMap<String, ArrayList<String>> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<ClassType> getClasstypeList() {
        return classtypeList;
    }

    public void setClasstypeList(ArrayList<ClassType> classtypeList) {
        this.classtypeList = classtypeList;
    }
    
    

    public double findHourAvalableOfRoom(Room r) {
        if (!schedule.isEmpty()) {
            ArrayList<String> strs = schedule.get(r.getId());
            if (strs == null) {
                return 13 * 6;
            }
            double allhour = 0;
            for (String item : strs) {
                String[] tmp = item.split("-");
                double hour = Double.parseDouble(tmp[2]) - Double.parseDouble(tmp[1]);
                
                allhour = allhour + hour;
            }
            
            double avalableHour = 13 * 6 - allhour;
            return avalableHour;
        } else {
            return 13 * 6;
        }
        
    }
    
    public double findHourAvalableOfAllRoom() {
        double d = 0;
        for (Room r : roomList) {
            d = d + findHourAvalableOfRoom(r);
        }
        
        return d;
    }
    
    public double findHourPayRateOfTeacher(Teacher t, String skill) {
        int count = 0;
        if (t.getSkillArray() == null) {
            return 0;
        }
        for (String item : t.getSkillArray()) {
            
            if (item.equals(skill)) {
                return t.getFeeSkill().get(count);
            } else {
                count++;
            }
            
        }
        return 0;
    }
    
    public int findNumberOfLessonOfClass(Classes c) {
        if (!schedule.isEmpty()) {
            int count = 0;
            Set<String> arr = schedule.keySet();
            for (String str : arr) {
                ArrayList<String> strs = schedule.get(str);
                for (String st : strs) {
                    String[] tmp = st.split("-");
                    if (tmp[3].equals(c.getId())) {
                        count++;
                    }
                }
            }
            return count;
        } else {
            return -1;
        }
        
    }
    
    public ArrayList<String> generateSkill() {
        if (classtypeList == null) {
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        for (ClassType c : classtypeList) {
            if (!result.contains(c.getName())) {
                result.add(c.getName());
            }
        }
        return result;
    }
    
    public ArrayList<ClassType> getClassTypeList() {
        return classtypeList;
    }
    
    public void addClassType(ClassType cs) {
        classtypeList.add(cs);
    }
    
    public void deleteClassType(ClassType cs) {
        classtypeList.remove(cs);
    }
    
    public ClassType findClassTypeById(int id) {
        for (ClassType cla : classtypeList) {
            if (id == cla.getId()) {
                return cla;
            }
        }
        return null;
        
    }
    
    public ArrayList<Invoice> getInvoiceList() {
        return invoiceList;
    }
    
    public void setInvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
    
    public HashMap<String, Invoice> getStudentInvoiceMap() {
        return studentInvoiceMap;
    }
    
    public void setStudentInvoiceMap(HashMap<String, Invoice> studentInvoiceMap) {
        this.studentInvoiceMap = studentInvoiceMap;
    }
    
    public ArrayList<Room> getRoomList() {
        return roomList;
    }
    
    public static Model getModel() {
        return Model.model;
    }
    
    public HashMap<String, ArrayList<String>> getSchedule() {
        return schedule;
    }
    
    public ArrayList<Account> getAccountList() {
        return accountList;
    }
    
    public ArrayList<Account> getActiveAccountList() {
        ArrayList<Account> aList = new ArrayList<Account>();
        for (Account a : accountList) {
            if (a.isActive() == ACTIVE) {
                aList.add(a);
            }
        }
        
        return aList;
    }
    
    public ArrayList<Teacher> getActiveTeacherList() {
        ArrayList<Teacher> aList = new ArrayList<Teacher>();
        for (Teacher a : teacherList) {
            if (a.isActive() == ACTIVE) {
                aList.add(a);
            }
        }
        
        return aList;
    }
    
    public ArrayList<Student> getActiveStudentList() {
        ArrayList<Student> aList = new ArrayList<Student>();
        for (Student a : studentList) {
            if (a.isActive() == ACTIVE) {
                aList.add(a);
            }
        }
        
        return aList;
    }
    
    public ArrayList<Classes> getClassList() {
        return classList;
    }
    
    public HashMap<String, ArrayList<String>> getEnrollMap() {
        return enrollMap;
    }
    
    public void addAccount(Account a) {
        accountList.add(a);
    }
    
    public void addStudent(Student s) {
        studentList.add(s);
    }
    
    public void addTeacher(Teacher t) {
        teacherList.add(t);
    }
    
    public void addClass(Classes c) {
        classList.add(c);
    }
    
    public boolean addStudentEnroll(String classId, String peopleId) {
        
        if (enrollMap.containsKey(classId)) {
            
            if (isMaxStudent(classId) == false) {
                enrollMap.get(classId).add(peopleId);
                return true;
            } else {
                return false;
            }
            
        } else {
            ArrayList<String> array = new ArrayList<String>();
            array.add(peopleId);
            enrollMap.put(classId, array);
            return true;
        }
    }
    
    public boolean addClassSchedule(String roomId, String code) {
        //code = date-time-classId
        // eg. 2-10-12-C000002
        if (schedule.containsKey(roomId)) {
            schedule.get(roomId).add(code);
            return true;
        } else {
            ArrayList<String> array = new ArrayList<String>();
            array.add(code);
            schedule.put(roomId, array);
            return true;
        }
    }
    
    public boolean addTeacherEnroll(String classId, String peopleId) {
        if (enrollMap.containsKey(classId)) {
            
            if (isTeacherInClass(classId) == false) {
                enrollMap.get(classId).add(peopleId);
                return true;
            } else {
                return false;
            }
            
        } else {
            ArrayList<String> array = new ArrayList<String>();
            array.add(peopleId);
            enrollMap.put(classId, array);
            return true;
        }
    }
    
    public void deleteClass(Classes c) {
        enrollMap.remove(c.getId());
        classList.remove(c);
        if (!schedule.isEmpty()) {
            Set<String> arr = schedule.keySet();
            if (!arr.isEmpty() && arr.size() != 0) {
                for (String str : arr) {
                    ArrayList<String> strs = schedule.get(str);
                    for (String st : strs) {
                        String[] parts = st.split("-");
                        String part4 = parts[3];
                        if (part4.equals(c.getId())) {
                            strs.remove(st);
                        }
                    }
                }
            }
        }
    }

    //delete account; if there is <2 account, delete canceled
    public void deleteAccount(String accountId) {
        int count = 0;
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getRole() == Ultility.ADMIN) {
                count++;
            }
        }
        
        if (count > 1) {
            accountList.remove(getAccountFromArray(accountId));
        }
    }
    
    private boolean changeActivation(boolean b) {
        if (b = Ultility.ACTIVE) {
            return Ultility.DEACTIVE;
        }
        return Ultility.ACTIVE;
        
    }

    //active / deactive account; if there is <2 account,else canceled
    public void changeActivationAccount(String accountId) {
        int count = 0;
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getRole() == Ultility.ADMIN) {
                count++;
            }
        }
        if (count > 1) {
            getAccountFromArray(accountId).setActive(changeActivation(getAccountFromArray(accountId).isActive()));
        }
    }
    
    public boolean isTeacherInClass(String classId) {
        for (int i = 0; i < enrollMap.get(classId).size(); i++) {
            if (studentOrTeacherCheck(enrollMap.get(classId).get(i)) == "Teacher") {
                return true;
            }
        }
        return false;
    }

    // find if the class has maximum student
    public boolean isMaxStudent(String classId) {
        int max;
        int i = findClassTypeById(findClassById(classId).getClassTypeid()).getType();
        if (i == ONE_ONE) {
            max = QUANTITY_1;
        } else if (i == DUAL) {
            max = QUANTITY_2;
        } else {
            max = QUANTITY_3;
        }
        if (isTeacherInClass(classId)) {
            if ((enrollMap.get(classId).size() + -1) == max) {
                return true;
            }
            return false;
        } else {
            if ((enrollMap.get(classId).size() + -0) == max) {
                return true;
            }
            return false;
        }
        
    }

    // get a class object from array by Id
    public Classes getClassFromArray(String classId) {
        
        for (int i = 0; i < classList.size(); i++) {
            if (classList.get(i).getId().equalsIgnoreCase(classId)) {
                return classList.get(i);
            }
        }
        return null;
    }

    // get  account object from array by Id
    public Account getAccountFromArray(String accoutId) {
        
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getId().equalsIgnoreCase(accoutId)) {
                return accountList.get(i);
            }
        }
        return null;
    }

    //login by admin & staff
    public boolean login(String userName, String password) {
        for (int i = 0; i < accountList.size(); i++) {
            if ((accountList.get(i).getUserName().equals(userName))
                    && (accountList.get(i).getPassword().equals(password))
                    && (accountList.get(i).isActive() == Ultility.ACTIVE)) {
                return true;
            }
        }
        return false;
    }

    //login by admin
    public boolean adminLogin(String userName, String password) {
        for (int i = 0; i < accountList.size(); i++) {
            if ((accountList.get(i).getUserName().equals(userName))
                    && (accountList.get(i).getPassword().equals(password))
                    && (accountList.get(i).isActive() == Ultility.ACTIVE)
                    && (accountList.get(i).getRole() == Ultility.ADMIN)) {
                return true;
            }
        }
        return false;
    }
    
    private String findMaxIdAccount() {
        if (accountList.isEmpty() == true) {
            return null;
        }
        String maxId = "";
        for (Account acc : accountList) {
            if (acc.getId().compareToIgnoreCase(maxId) >= 0) {
                maxId = acc.getId();
            }
        }
        return maxId;
    }
    
    public String findMaxIdStudent() {
        if (studentList.isEmpty() == true) {
            return null;
        }
        String maxId = "";
        for (Student per : studentList) {
            if (per.getId().compareToIgnoreCase(maxId) >= 0) {
                maxId = per.getId();
            }
        }
        return maxId;
    }
    
    public String findMaxIdTeacher() {
        if (teacherList.isEmpty() == true) {
            return null;
        }
        String maxId = "";
        for (Teacher per : teacherList) {
            if (per.getId().compareToIgnoreCase(maxId) >= 0) {
                maxId = per.getId();
            }
        }
        return maxId;
    }
    
    private String findMaxClassId() {
        String maxId = "";
        if (classList.isEmpty() == true) {
            return null;
        } else {
            for (Classes cla : classList) {
                if (cla.getId().compareToIgnoreCase(maxId) >= 0) {
                    maxId = cla.getId();
                }
            }
        }
        return maxId;
    }
    
    private int findMaxClassTypeId() {
        int maxId = 0;
        if (classtypeList.isEmpty() == true) {
            return 0;
        } else {
            for (ClassType cla : classtypeList) {
                if (cla.getId() >= maxId) {
                    maxId = cla.getId();
                }
            }
        }
        return maxId;
    }
    
    private String generateClassId(String maxId) {
        if (maxId == null) {
            return "C000001";
        }
        
        String begin = maxId.substring(0, 1);
        int intMax = Integer.parseInt(maxId.substring(1));
        intMax++;
        String intMaxS = Integer.toString(intMax);
        for (int i = intMaxS.length(); i < 6; i++) {
            intMaxS = "0" + intMaxS;
        }
        intMaxS = begin + intMaxS;
        return intMaxS;
    }
    
    private String generatePeopleId(String maxId, String code) {// code here to differentiate between lecture and student use "L" or "S"
        if (maxId == null) {
            if (code.equals("L")) {
                return "L000001";
            } else {
                return "S000001";
            }
        }
        
        String begin = maxId.substring(0, 1);
        int intMax = Integer.parseInt(maxId.substring(1));
        intMax++;
        String intMaxS = Integer.toString(intMax);
        for (int i = intMaxS.length(); i < 6; i++) {
            intMaxS = "0" + intMaxS;
        }
        intMaxS = begin + intMaxS;
        return intMaxS;
    }
    
    private String generateAccountId(String maxId) {
        if (maxId == null) {
            return "000001";
        } else {
            String max = Integer.toString(Integer.parseInt(maxId) + 1);
            for (int i = max.length(); i < 6; i++) {
                max = "0" + max;
            }
            return max;
        }
    }
    
    private int generateClassTypeId(int maxId) {
        if (maxId == 0) {
            return 1;
        } else {
            int max = maxId + 1;
            return max;
        }
    }
    
    public String newStudentId() {
        return generatePeopleId(findMaxIdStudent(), "S");
    }
    
    public int newClassTypeId() {
        return generateClassTypeId(findMaxClassTypeId());
    }
    
    public String newLectureId() {
        return generatePeopleId(findMaxIdTeacher(), "L");
    }
    
    public String newAccountId() {
        return generateAccountId(findMaxIdAccount());
    }
    
    public String newClassId() {
        return generateClassId(findMaxClassId());
    }
    
    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }
    
    public void setClassList(ArrayList<Classes> classList) {
        this.classList = classList;
    }
    
    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }
    
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    
    public void setTeacherList(ArrayList<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
    
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    
    public ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }
// check whether the username is the same or not

    public Classes findClassById(String id) {
        for (Classes cla : classList) {
            if (cla.getId().equals(id)) {
                return cla;
            }
        }
        return null;
    }
    
    public boolean findAccountByUserName(String aName) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUserName().equals(aName)) {
                return true;
            }
        }
        return false;
    }
//check whether the class is the same or not

    public boolean findClassByClassName(String cName) {
        for (int i = 0; i < classList.size(); i++) {
            if (classList.get(i).getName().equals(cName)) {
                return true;
            }
        }
        return false;
    }
// convert from text to Date type

    public Date textToDate(String s) throws ParseException {
        //12-11-2001
        DateFormat formatter;
        Date date = null;
        try {
            
            formatter = new SimpleDateFormat("d-M-y");
            date = (Date) formatter.parse(s);
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }
        return date;
    }
    
    public String dateToString(Date d) throws ParseException {
        
        DateFormat formatter = null;
        String s;
        formatter = new SimpleDateFormat("dd-mm-yyyy");
        s = formatter.format(d);
        return s;
    }
    
    public String convertGenderToString(boolean gender) {
        if (gender == MALE) {
            return "Male";
        }
        return "Female";
    }
    
    public boolean isManager(String username) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUserName().equals(username)) {
                if (accountList.get(i).getRole() == ADMIN) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Student findStudentById(String code) {
        for (Student stu : studentList) {
            if (stu.getId().equals(code)) {
                return stu;
            }
        }
        return null;
    }
    
    public Teacher findTeacherById(String code) {
        for (Teacher stu : teacherList) {
            if (stu.getId().equals(code)) {
                return stu;
            }
        }
        return null;
    }
    // check for avoiding the same date input between startdate and end date
//
//    public static boolean isOnSameDay(Timestamp[] dates) {
//        SimpleDateFormat fmt = new SimpleDateFormat("dd-mm-yyyy");
//        String date1 = fmt.format(dates[0]);
//        for (Timestamp date : dates) {
//            if (!fmt.format(date).equals(date1)) {
//                return false;
//            }
//        }
//        return true;
//    }
//    
    public void deleteTeacher(Teacher p) {
        System.out.println("en " + enrollMap.size());
        if (enrollMap.isEmpty() || enrollMap.size() == 0) {
            teacherList.remove(p);
        } else {
            for (Map.Entry<String, ArrayList<String>> entry : enrollMap.entrySet()) {
                if (!entry.getValue().contains(p.getId())) {
                    teacherList.remove(p);
                } else {
                    JOptionPane.showMessageDialog(null, "Can not delete lecture who already in classes !!");
                }
                
            }
        }
    }
    
    public void deactivePeople(People p) {
        if (enrollMap.isEmpty() || enrollMap.size() == 0) {
            p.setActive(false);
        } else {
            for (Map.Entry<String, ArrayList<String>> entry : enrollMap.entrySet()) {
                if (!entry.getValue().contains(p.getId())) {
                    p.setActive(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Can not deactive person who already in classes !!");
                }
            }
        }
    }
    
    public void deleteStudent(Student p) {
        if (enrollMap.isEmpty() || enrollMap.size() == 0) {
            studentList.remove(p);
        } else {
            for (Map.Entry<String, ArrayList<String>> entry : enrollMap.entrySet()) {
                if (!entry.getValue().contains(p.getId())) {
                    studentList.remove(p);
                } else {
                    JOptionPane.showMessageDialog(null, "Can not delete student who already in classes !!");
                }
            }
        }
    }
    
    private boolean isTeaching(String peopleId) {
        for (int i = 0; i < classList.size(); i++) {
            for (int j = 0; j < enrollMap.get(classList.get(i)).size(); j++) {
                if (enrollMap.get(classList.get(i)).get(i).equals(peopleId)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void loadAllData() {
        AccountFile af = new AccountFile();
        ClassFile cf = new ClassFile();
        EnrollFile ef = new EnrollFile();
        ScheduleFile sf = new ScheduleFile();
        StudentFile sfi = new StudentFile();
        TeacherFile tf = new TeacherFile();
        InvoiceFile iif = new InvoiceFile();
        InvoiceMapFile imf = new InvoiceMapFile();
        PaySlipFile psf = new PaySlipFile();
        ClassTypeFile ctf = new ClassTypeFile();
        TeacherPaySlipFile tpsf = new TeacherPaySlipFile();
        
        classtypeList = ctf.readFile();
        accountList = af.readFile();
        classList = cf.readFile();
        enrollMap = ef.readFile();
        schedule = sf.readFile();
        studentList = sfi.readFile();
        teacherList = tf.readFile();
        studentInvoiceMap = imf.readFile();
        invoiceList = iif.readFile();
        paySlipList = psf.readFile();
        teacherPaySlipMap = tpsf.readFile();
    }
    
    public void saveAllData() throws FileNotFoundException {
        AccountFile af = new AccountFile();
        ClassFile cf = new ClassFile();
        EnrollFile ef = new EnrollFile();
        ScheduleFile sf = new ScheduleFile();
        StudentFile sfi = new StudentFile();
        TeacherFile tf = new TeacherFile();
        InvoiceFile iif = new InvoiceFile();
        InvoiceMapFile imf = new InvoiceMapFile();
        PaySlipFile psf = new PaySlipFile();
        ClassTypeFile ctf = new ClassTypeFile();
        TeacherPaySlipFile tpsf = new TeacherPaySlipFile();
        
        af.writeFile(accountList);
        cf.writeFile(classList);
        ef.writeFile(enrollMap);
        sf.writeFile(schedule);
        sfi.writeFile(studentList);
        tf.writeFile(teacherList);
        iif.writeFile(invoiceList);
        imf.writeFile(studentInvoiceMap);
        psf.writeFile(paySlipList);
        ctf.writeFile(classtypeList);
        tpsf.writeFile(teacherPaySlipMap);
        
        
//        System.out.println("check checkl");
    }
    
    public boolean isClassAtSchedule(String classId) {
        if (!schedule.isEmpty()) {
            Set<String> arr = schedule.keySet();
            for (String str : arr) {
                ArrayList<String> strs = schedule.get(str);
                for (String st : strs) {
                    String[] parts = st.split("-");
                    String part4 = parts[3];
                    if (part4.equals(classId)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    
    public boolean isClassHavePeople(String classId) {
        if (!enrollMap.isEmpty()) {
            if (enrollMap.containsKey(classId)) {
                if (enrollMap.get(classId) == null || enrollMap.get(classId).size() == 0) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }
    
    public void copyClasses(Classes c) {
        classList.add(new Classes(newClassId(), c.getName(), c.getTextbook(), c.getStartDate(), c.getEndDate(), c.getClassTypeid(), c.getHour()));
    }
    
    public String findRoomByClassScheduleCode(String code) {
        if (!schedule.isEmpty()) {
            Set<String> arr = schedule.keySet();
            for (String str : arr) {
                ArrayList<String> strs = schedule.get(str);
                for (String st : strs) {
                    if (st.equals(code)) {
                        return str;
                    }
                }
            }
        }
        return null;
    }
    
    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        String[] arr = strDate.split("-");
        String finalstr = arr[0].substring(2, 4) + arr[1];
        return finalstr;
    }
    
    public String autoGenerateId() {
        if (findMaxIdOfPeople() == null) {
            return getCurrentTimeStamp() + "001";
        } else {
            return getCurrentTimeStamp() + findMaxIdOfPeople();
        }
    }
    
    public String getCurrentTimeStamp2() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public String autoGenerateInvoiceId(People s) {
        
        if (s == null) {
            return null;
        }
//        if (findMaxIdOfPeople() == null ) {
//            return getCurrentTimeStamp() + "001";
//        } else {
//            return getCurrentTimeStamp2();
//        }
        return s.getId() + "-" + getCurrentTimeStamp2();
    }
    
     public String autoGeneratePaySlipId(People s) {

        return s.getId() + "-" + getCurrentTimeStamp2();
    }
    
    
    public String newIDforInvoice(Student s) {
        String ss = autoGenerateInvoiceId(s);
        String finall = ss + "";
        int count = 0;
        for (Invoice inv : invoiceList) {
            String[] idtmp = inv.getId().split("-");
            if ((idtmp[0] + "-" + idtmp[1]).equals(ss) ) {
                count++;
            }
        }
        if (count == 0 ) {
            finall = finall + "-1";
        } else {
            finall = finall + "-" + (count+ 1);
        }
        
        
        return finall;
    }
    

    public String findMaxIdOfPeople() {
        int max = 0;
        String maxNew;
        if (studentList.isEmpty() && teacherList.isEmpty()) {
            return null;
        } else {
            
            for (Student item : studentList) {
                String idstu = item.getId().substring(4);
                int idst = Integer.parseInt(idstu);
                if (idst > max) {
                    max = idst;
                }
            }
            for (Teacher item : teacherList) {
                String idstu = item.getId().substring(4);
                int idst = Integer.parseInt(idstu);
                if (idst > max) {
                    max = idst;
                }
            }
            max = max + 1;
            maxNew = Integer.toString(max);
            for (int i = maxNew.length(); i < 3; i++) {
                maxNew = "0" + maxNew;
            }
        }
        return maxNew;
    }
    
    public Invoice getStudentInvoice(Student s) {
        if (studentInvoiceMap.containsKey(s.getId())) {
            return studentInvoiceMap.get(s.getId());
        }
        
        return null;
    }
    
    public String studentOrTeacherCheck(String id) {
        if (findStudentById(id) != null) {
            return "Student";
        } else if (findTeacherById(id) != null) {
            return "Teacher";
        }
        return null;
    }
    
    public boolean isPaid(Student s) {
        if (studentInvoiceMap.containsKey(s.getId())) {
            if (studentInvoiceMap.get(s.getId()).isPaid() == true) {
                return true;
            }
        }
        
        
        return false;
        
    }
    private final Vector<Date> reportDateList = new Vector<>();
    
    private void generateMonthList() {
        Date temp;
        for (Invoice iv : invoiceList) {
            if (iv.getPaidDate() != null) {
                temp = new Date(iv.getPaidDate().getYear(), iv.getPaidDate().getMonth(), 0);
                if (!reportDateList.contains(temp)) {
                    reportDateList.add(temp);
                }
            }
        }
    }
    
    public HashMap<String, Invoice> getPaidStudents(Date date) {
        HashMap<String, Invoice> result = new HashMap<>();
        for (Map.Entry<String, Invoice> entry : studentInvoiceMap.entrySet()) {
            if (entry.getValue().isPaid() == true) {
                if (((entry.getValue().getPaidDate().getMonth() == date.getMonth())
                        && (entry.getValue().getPaidDate().getYear() == date.getYear()))
                        || (date.compareTo(entry.getValue().getPaidDate()) < 0)) {
                    result.put(entry.getKey(), entry.getValue());
                }
            }
        }
        
        return result;
    }
    
    public ArrayList<String> getUnPaidSudent(Date date) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Invoice> entry : studentInvoiceMap.entrySet()) {
            if ((entry.getValue().isPaid() == false) && (entry.getValue().getPaidDate().compareTo(date) <= 0)) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

  

    public Vector<Date> getReportDateList() {
        generateMonthList();
        return reportDateList;
    }
    
    public double getTotalFeeLastMonth(Date curr) {
        double result = 0;
        int lastMonth;
        int year = curr.getYear();
        if (curr.getMonth() != 1) {
            lastMonth = curr.getMonth() - 1;
        } else {
            lastMonth = 12;
            year -= 1;
        }
        for (Map.Entry<String, Invoice> entry : studentInvoiceMap.entrySet()) {
            Invoice i = entry.getValue();
            if (i.isPaid() == true) {
                if (((i.getPaidDate().getMonth() == lastMonth) && (i.getPaidDate().getYear() == year))
                        || ((i.getPaidDate().getMonth() == lastMonth) && (i.getPaidDate().getYear() == year + 1))) {
                    result += i.getTotalFee();
                }
            }
        }
        return result;
    }
    
    public double getTotalUnpaidFeeLastMonth(Date curr) {
        double result = 0;
        int lastMonth;
        int year = curr.getYear();
        if (curr.getMonth() != 1) {
            lastMonth = curr.getMonth() - 1;
        } else {
            lastMonth = 12;
            year -= 1;
        }
        
        for (Map.Entry<String, Invoice> entry : studentInvoiceMap.entrySet()) {
            Invoice i = entry.getValue();
            i.generateTotalFee();
            if ((i.isPaid() == false)
                    || ((i.getPaidDate().getMonth() == lastMonth) && (i.getPaidDate().getYear() == year))
                    || ((i.getPaidDate().getMonth() == lastMonth) && (i.getPaidDate().getYear() - 1 == year))) {
                result += i.getTotalFee();
            }
        }
        return result;
    }
    
    public double getTotalFeeCurrMonth(Date curr) {
        double result = 0;
        for (Map.Entry<String, Invoice> entry : studentInvoiceMap.entrySet()) {
            Invoice i = entry.getValue();
            if ((i.isPaid() == true)
                    && (curr.getMonth() == i.getPaidDate().getMonth())
                    && (curr.getYear() == i.getPaidDate().getYear())) {
                result += i.getTotalFee();
            }
        }
        return result;
    }
    
    public double getTotalUnpaidFeeCurrMonth(Date curr) {
        double result = 0;
        for (Map.Entry<String, Invoice> entry : studentInvoiceMap.entrySet()) {
            Invoice i = entry.getValue();
            i.generateTotalFee();
            if ((i.isPaid() == false)
                    && (curr.getMonth() == i.getPaidDate().getMonth())
                    && (curr.getYear() == i.getPaidDate().getYear())) {
                result += i.getTotalFee();
            }
        }
        return result;
    }
    
    public void switchLanguage() {
        if (isEng == true) {
            resources = ResourceBundle.getBundle("Properties/Vietnamese");
            
            isEng = false;
        } else {
            resources = ResourceBundle.getBundle("Properties/English");
            isEng = true;
        }
        System.out.println(Ultility.LANGUAGE_TXT);
    }

    
    public void generateTeacherPaySlip(Teacher t) {
        
        ArrayList<Classes> clist = new ArrayList<>();
        //generate cList
        for (Map.Entry<String, ArrayList<String>> entry : enrollMap.entrySet()) {
            if (entry.getValue().contains(t.getId())) {
                clist.add(findClassById(entry.getKey()));
            }
        }

        //generate paySlip
        PaySlip ps = null;
        if(teacherPaySlipMap.containsKey(t.getId())){
            ps = teacherPaySlipMap.get(t.getId());
        }else{
            ps = new PaySlip(autoGeneratePaySlipId(t));
        }
        
//        PaySlip ps = null;
        int noLesson = 0;
        int teachingHour = 0;
        double hourPayRate = 0;
        double fee = 0;
        for (Classes cl : clist) {
            ClassType ct = findClassTypeById(cl.getClassTypeid());
            noLesson += ct.getLessonWeek() * FOUR_WEEK;
            teachingHour += cl.getHour() * noLesson;
            hourPayRate += findHourPayRateOfTeacher(t, ct.getName());
            fee = teachingHour * hourPayRate;
            ps.getpList().add(new PaySlipDetail(cl.getId(), noLesson, teachingHour, hourPayRate, fee));
        }
        
        ps.init();
        
        if (teacherPaySlipMap.containsKey(t.getId())) {
            for (int j = 0; j < paySlipList.size(); j++) {
                if (paySlipList.get(j).getId().equals(ps.getId())) {
                    if (paySlipList.size() == 1) {
                        paySlipList.clear();
                    } else {
                        paySlipList.remove(paySlipList.get(j));
                    }
                }
            }
        }
        
        paySlipList.add(0, ps);
        teacherPaySlipMap.put(t.getId(), ps);
        
    }
    

    //automatically generate Invoice for student 
    public void generateStudentInvoice(Student s) {
        
        Invoice i = new Invoice(autoGenerateInvoiceId(s));
        for (Map.Entry<String, ArrayList<String>> entry : enrollMap.entrySet()) {
            if (entry.getValue().contains(s.getId())) {
                Classes c = findClassById(entry.getKey());
                i.getClassList().add(new ClassInvoiceDetail(c.getId(), c.getName(), c.getStartDate(), c.getEndDate(), c.getFee()));
            }
        }
        i.init();
        if (studentInvoiceMap.containsKey(s.getId())) {
            for (int j = 0; j < invoiceList.size(); j++) {
                if (invoiceList.get(j).getId().contains(s.getId())) {
                    if (invoiceList.size() == 1) {
                        invoiceList.clear();
                    } else {
                        invoiceList.remove(invoiceList.get(j));
                    }
                }
            }
        }
        invoiceList.add(0, i);
        
        studentInvoiceMap.put(s.getId(), i);
        
        
    }
    
    public String[] codeContructorForPeople(String code) {
        return code.split(",");       
    }
    
    
    public Teacher findTeacherByPayID(String id){
        for(Map.Entry<String,PaySlip> entry:teacherPaySlipMap.entrySet()){
            if(entry.getValue().getId().equals(id)){
                return findTeacherById(entry.getKey());
            }
        }
        
        return null;
    }
}
