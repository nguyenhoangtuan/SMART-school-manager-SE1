/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.text.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lyn
 */
public class ModelTest {

    public ModelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findHourAvalableOfRoom method, of class Model.
     */
    @Test
    public void testFindHourAvalableOfRoom() {


        System.out.println("findHourAvalableOfRoom");
        HashMap<String, ArrayList<String>> schedule = new HashMap<>();
        ArrayList<String> array = new ArrayList<>();
        array.add("2-8.5-10.5-C000123");
        schedule.put("1.1", array);
        Model model = new Model();
        model.setSchedule(schedule);

        // testcase 1 normal case
        Room r = new Room("1.1", "Room 1", "Dance");
        // testcase 2 there is no room with that id
        Room r1 = new Room("1.2", "Room 2", "Dance");
        // testcase 3 there is no schedual yet 
        Room r2 = new Room("1.2", "Room 2", "Dance");
        Model model1 = new Model();

        double expResult = 13 * 6 - (10.5 - 8.5);
        double expResult1 = 13 * 6;
        double expResult2 = 13 * 6 - 0;

        double result = model.findHourAvalableOfRoom(r);
        double result1 = model.findHourAvalableOfRoom(r1);
        double result2 = model1.findHourAvalableOfRoom(r1);

        assertEquals(expResult, result, 0.1);
        assertEquals(expResult1, result1, 0.1);
        assertEquals(expResult2, result2, 0.1);
    }

    /**
     * Test of findHourAvalableOfAllRoom method, of class Model.
     */
    @Test
    public void testFindHourAvalableOfAllRoom() {
        System.out.println("findHourAvalableOfAllRoom");



        //test case 1: normal case
        HashMap<String, ArrayList<String>> schedule = new HashMap<>();
        ArrayList<String> array = new ArrayList<>();
        array.add("2-8.5-10.5-C000123");
        schedule.put("1.1", array);
        Model model1 = new Model();
        model1.setSchedule(schedule);

        double expResult = 13 * 6 * 9 - 2;
        double result = model1.findHourAvalableOfAllRoom();
        assertEquals(expResult, result, 0.1);

        //testcase 2: no scheduel yet
        Model model = new Model();
        double expResult1 = 13 * 6 * 9;
        double result1 = model.findHourAvalableOfAllRoom();
        assertEquals(expResult1, result1, 0.1);





    }

    /**
     * Test of findHourPayRateOfTeacher method, of class Model.
     */
    @Test
    public void testFindHourPayRateOfTeacher() {
        System.out.println("findHourPayRateOfTeacher");

        // testcase 1 : there is no arraylist for skill teacher
        Teacher t = new Teacher("T00001", "", "", "", "", "", true, "", null, 1991, null);
        String skill = "Dance";
        Model model = new Model();
        double expResult = 0.0;
        double result = model.findHourPayRateOfTeacher(t, skill);
        assertEquals(expResult, result, 0.0);

        //testcase 2 : normal case 
        ArrayList<String> arrat = new ArrayList<String>();
        arrat.add("Dance");
        arrat.add("Violin");
        ArrayList<Double> arrdou = new ArrayList<Double>();
        arrdou.add(100.0);
        arrdou.add(120.0);

        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", arrat, 1991, arrdou);
        String skill1 = "Dance";
        String skill2 = "Violin";
        Model model1 = new Model();
        double expResult1 = 100.0;
        double expResult2 = 120.0;

        double result1 = model.findHourPayRateOfTeacher(t1, skill1);
        double result2 = model.findHourPayRateOfTeacher(t1, skill2);
        assertEquals(expResult1, result1, 0.1);
        assertEquals(expResult2, result2, 0.1);



    }

    /**
     * Test of findNumberOfLessonOfClass method, of class Model.
     */
    @Test
    public void testFindNumberOfLessonOfClass() {
        System.out.println("findNumberOfLessonOfClass");


        // testcase 1 : there is no scheduel
        Classes c = new Classes("C0001", "", "", new Date(), new Date(), 0, 45);
        Model model = new Model();
        int expResult = -1;
        int result = model.findNumberOfLessonOfClass(c);
        assertEquals(expResult, result);

        // testcase 2 : normal case

        HashMap<String, ArrayList<String>> schedule = new HashMap<>();
        ArrayList<String> array = new ArrayList<>();
        array.add("2-8.5-10.5-C0001");
        array.add("3-8.5-10.5-C0001");
        schedule.put("1.1", array);
        Model model1 = new Model();
        model1.setSchedule(schedule);


        Classes c1 = new Classes("C0001", "", "", new Date(), new Date(), 0, 45);
        int expResult1 = 2;
        int result1 = model1.findNumberOfLessonOfClass(c1);
        assertEquals(expResult1, result1);

    }

    /**
     * Test of generateSkill method, of class Model.
     */
    @Test
    public void testGenerateSkill() {
        System.out.println("generateSkill");

        //testcase1 : null array
        Model model = new Model();
        model.setClasstypeList(null);

        ArrayList expResult = null;
        ArrayList result = model.generateSkill();
        assertEquals(expResult, result);

        //testcase2 : normal array

        Model model1 = new Model();
        model1.getClassTypeList().removeAll(model1.getClassTypeList());
        ClassType ct1 = new ClassType(1, "Piano", 0, 2, 100, 100, "check");
        model1.addClassType(ct1);

        ClassType ct2 = new ClassType(2, "Vorcal", 1, 1, 100, 100, "check");
        model1.addClassType(ct2);
        ClassType ct3 = new ClassType(3, "Guitar", 2, 2, 100, 100, "check");
        model1.addClassType(ct3);
        ClassType ct4 = new ClassType(4, "Guitar", 1, 1, 100, 100, "check");
        model1.addClassType(ct4);
        ClassType ct5 = new ClassType(5, "Guitar", 0, 2, 100, 100, "check");
        model1.addClassType(ct5);



        ArrayList<String> expResult1 = new ArrayList<String>();
        expResult1.add("Piano");
        expResult1.add("Vorcal");
        expResult1.add("Guitar");

        ArrayList result1 = model1.generateSkill();
        assertEquals(expResult1, result1);


    }

    /**
     * Test of addClassType method, of class Model.
     */
    @Test
    public void testAddClassType() {
        System.out.println("addClassType");
        //testcase 1 add one new class
        Model model1 = new Model();
        model1.getClassTypeList().removeAll(model1.getClassTypeList());
        ClassType ct1 = new ClassType(1, "Piano", 0, 2, 100, 100, "check");
        model1.addClassType(ct1);

        ArrayList<ClassType> arra = new ArrayList<ClassType>();
        arra.add(ct1);
        model1.getClassTypeList();
        assertEquals(arra, model1.getClassTypeList());

    }

    /**
     * Test of deleteClassType method, of class Model.
     */
    @Test
    public void testDeleteClassType() {
        System.out.println("deleteClassType");
        Model model1 = new Model();
        model1.getClassTypeList().removeAll(model1.getClassTypeList());
        ClassType ct1 = new ClassType(1, "Piano", 0, 2, 100, 100, "check");
        model1.addClassType(ct1);
        model1.deleteClassType(ct1);

        ArrayList<ClassType> arra = new ArrayList<ClassType>();
//        arra.add(ct1);

        model1.getClassTypeList();
        assertEquals(arra, model1.getClassTypeList());
    }

    /**
     * Test of findClassTypeById method, of class Model.
     */
    @Test
    public void testFindClassTypeById() {
        System.out.println("findClassTypeById");
        // testcase 1 : normal case
        Model model1 = new Model();
        model1.getClassTypeList().removeAll(model1.getClassTypeList());
        ClassType ct1 = new ClassType(1, "Piano", 0, 2, 100, 100, "check");
        model1.addClassType(ct1);
        ClassType ct2 = model1.findClassTypeById(1);
        assertEquals(ct1, ct2);

        //testcase 2 : there is no ID in classtypelist

        Model model = new Model();
        model.getClassTypeList().removeAll(model.getClassTypeList());
        ClassType ct = new ClassType(1, "Piano", 0, 2, 100, 100, "check");
        model1.addClassType(ct);
        ClassType ct3 = model1.findClassTypeById(2);
        assertEquals(null, ct3);
    }

    /**
     * Test of getActiveTeacherList method, of class Model.
     */
    @Test
    public void testGetActiveAccountList() {
        System.out.println("getActiveAccountList");
        Model m = new Model();

        Account a1 = new Account("1", "", "", "", "", 1, "");
        a1.setActive(true);
        m.addAccount(a1);
        Account a2 = new Account("2", "", "", "", "", 1, "");
        a2.setActive(false);
        m.addAccount(a2);

        ArrayList<Account> expResult = new ArrayList<Account>();
        expResult.add(a1);

        assertEquals(expResult, m.getActiveAccountList());

    }

    /**
     * Test of getActiveTeacherList method, of class Model.
     */
    @Test
    public void testGetActiveTeacherList() {
        System.out.println("getActiveTeacherList");
        Model m = new Model();
        ArrayList<String> arrat = new ArrayList<String>();
        arrat.add("Dance");
        arrat.add("Violin");
        ArrayList<Double> arrdou = new ArrayList<Double>();
        arrdou.add(100.0);
        arrdou.add(120.0);

        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", arrat, 1991, arrdou);
        t1.setActive(true);
        m.addTeacher(t1);
        Teacher t2 = new Teacher("T00002", "", "", "", "", "", true, "", arrat, 1991, arrdou);
        t2.setActive(false);
        m.addTeacher(t2);


        ArrayList<Teacher> expResult = new ArrayList<Teacher>();
        expResult.add(t1);

        assertEquals(expResult, m.getActiveTeacherList());

    }

    /**
     * Test of getActiveStudentList method, of class Model.
     */
    @Test
    public void testGetActiveStudentList() {
        System.out.println("getActiveStudentList");
        Model m = new Model();
        Student s1 = new Student("", "", "", "1", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);
        Student s2 = new Student("", "", "", "2", "", "", "", "", "", true, "", 1);
        s2.setActive(false);
        m.addStudent(s2);

        ArrayList<Student> expArray = new ArrayList<Student>();
        expArray.add(s1);

        assertEquals(expArray, m.getActiveStudentList());


    }

    /**
     * Test of addAccount method, of class Model.
     */
    @Test
    public void testAddAccount() {
        System.out.println("addAccount");
        Model model1 = new Model();
        Account a1 = new Account("1", "", "", "", "", 1, "");
        model1.addAccount(a1);

        ArrayList<Account> arra = new ArrayList<Account>();
        arra.add(a1);
        assertEquals(arra, model1.getAccountList());

    }

    /**
     * Test of addStudent method, of class Model.
     */
    @Test
    public void testAddStudent() {
        System.out.println("addStudent");
        Model m = new Model();
        Student s1 = new Student("", "", "", "1", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);

        ArrayList<Student> arra = new ArrayList<Student>();
        arra.add(s1);
        assertEquals(arra, m.getStudentList());
    }

    /**
     * Test of addTeacher method, of class Model.
     */
    @Test
    public void testAddTeacher() {
        System.out.println("addTeacher");
        Model m = new Model();
        ArrayList<String> arrat = new ArrayList<String>();
        arrat.add("Dance");
        arrat.add("Violin");
        ArrayList<Double> arrdou = new ArrayList<Double>();
        arrdou.add(100.0);
        arrdou.add(120.0);

        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", arrat, 1991, arrdou);
        t1.setActive(true);
        m.addTeacher(t1);

        ArrayList<Teacher> arra = new ArrayList<Teacher>();
        arra.add(t1);
        assertEquals(arra, m.getTeacherList());
    }

    /**
     * Test of addClass method, of class Model.
     */
    @Test
    public void testAddClass() {
        System.out.println("addClass");
        Model m = new Model();
        Classes c1 = new Classes("1", "", "", null, null, 1, 45);
        m.addClass(c1);
        ArrayList<Classes> arra = new ArrayList<Classes>();
        arra.add(c1);
        assertEquals(arra, m.getClassList());
    }

    /**
     * Test of addStudentEnroll method, of class Model.
     */
    @Test
    public void testAddStudentEnroll() {
        System.out.println("addStudentEnroll");
        // testcase 1 : null case
        Model m1 = new Model();
        String classId = "1";
        String peopleId = "1";
        boolean expResult = true;
        boolean result = m1.addStudentEnroll(classId, peopleId);
        assertEquals(expResult, result);

        // testcase 2 : normal case

        String classId1 = "14";
        String peopleId1 = "12";
        boolean expResult1 = true;
        boolean result1 = m1.addStudentEnroll(classId1, peopleId1);
        assertEquals(expResult1, result1);



    }

    /**
     * Test of addClassSchedule method, of class Model.
     */
    @Test
    public void testAddClassSchedule() {
        System.out.println("addClassSchedule");

        // testcase 1 null case
        String roomId = "";
        String code = "";
        Model m = new Model();
        boolean expResult = true;
        boolean result = m.addClassSchedule(roomId, code);
        assertEquals(expResult, result);

        // testcase 2 normalcase

        String roomId1 = "1.1";
        String code1 = "123-213-123-C00001";
        Model m1 = new Model();
        boolean expResult1 = true;
        boolean result1 = m.addClassSchedule(roomId1, code1);
        assertEquals(expResult1, result1);

    }

    /**
     * Test of addTeacherEnroll method, of class Model.
     */
    @Test
    public void testAddTeacherEnroll() {
        System.out.println("addTeacherEnroll");
        // testcase 1 : null case
        Model m1 = new Model();
        String classId = "";
        String peopleId = "";
        boolean expResult = true;
        boolean result = m1.addTeacherEnroll(classId, peopleId);
        assertEquals(expResult, result);

        // testcase 2 : normal case
        String classId1 = "C00001";
        String peopleId1 = "L00001";
        boolean expResult1 = true;
        boolean result1 = m1.addTeacherEnroll(classId1, peopleId1);
        assertEquals(expResult1, result1);
    }

    /**
     * Test of deleteClass method, of class Model.
     */
    @Test
    public void testDeleteClass() {
        System.out.println("deleteClass");

        Model m = new Model();
        Classes c = new Classes("1", "", "", null, null, 1, 60);
        m.getClassList().add(c);
        m.deleteClass(c);
        ArrayList<Classes> expResut = new ArrayList<Classes>();

        assertEquals(expResut, m.getClassList());
    }

    /**
     * Test of deleteAccount method, of class Model.
     */
    @Test
    public void testDeleteAccount() {
        System.out.println("deleteAccount");
        Model m = new Model();
        Account a1 = new Account("1", "", "", "", "", 1, "");
        a1.setActive(true);
        m.addAccount(a1);
        m.deleteAccount(a1.getId());

        ArrayList<Classes> expResut = new ArrayList<Classes>();

        assertEquals(expResut, m.getClassList());
    }

    /**
     * Test of changeActivationAccount method, of class Model.
     */
    @Test
    public void testChangeActivationAccount() {
        System.out.println("changeActivationAccount");

        Model m = new Model();
        Account a1 = new Account("1", "", "", "", "", 1, "");
        a1.setActive(true);
        m.addAccount(a1);
        Account a2 = new Account("2", "", "", "", "", 1, "");
        a2.setActive(true);
        m.addAccount(a2);
        // test case 1 :there are 2 accs
        m.changeActivationAccount("1");
        boolean expResult = true;

        assertEquals(expResult, a1.isActive());

        // test case 2 : there is one account

        m.changeActivationAccount("2");
        m.deleteAccount(a1.getId());
        expResult = true;

        assertEquals(expResult, a2.isActive());


    }

    /**
     * Test of isTeacherInClass method, of class Model.
     */
    @Test
    public void testIsTeacherInClass() {
        System.out.println("isTeacherInClass");

        Model m = new Model();
        ArrayList<String> arrat = new ArrayList<String>();
        arrat.add("Dance");
        arrat.add("Violin");
        ArrayList<Double> arrdou = new ArrayList<Double>();
        arrdou.add(100.0);
        arrdou.add(120.0);

        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", arrat, 1991, arrdou);
        t1.setActive(true);
        m.addTeacher(t1);

        // test case 1 : there is a teacher
        ArrayList<String> array = new ArrayList<String>();
        array.add("T00001");
        m.getEnrollMap().put("C0001", array);
        boolean expResult = true;
        boolean result = m.isTeacherInClass("C0001");
        assertEquals(expResult, result);

        //test case 2 : there is no teacher

        ArrayList<String> array1 = new ArrayList<String>();
//        array.add("T00001");
        m.getEnrollMap().put("C0001", array1);
        expResult = false;
        result = m.isTeacherInClass("C0001");
        assertEquals(expResult, result);

    }

    /**
     * Test of isMaxStudent method, of class Model.
     */
    @Test
    public void testIsMaxStudent() {
        System.out.println("isMaxStudent");

        Model m = new Model();

        ArrayList<String> array = new ArrayList<String>();
        array.add("T00001");
        m.getEnrollMap().put("1", array);
        m.getEnrollMap().put("2", array);
        ClassType ct1 = new ClassType(1, "Piano", 0, 2, 100, 100, "check");
        m.addClassType(ct1);

        ClassType ct2 = new ClassType(2, "Vorcal", 1, 1, 100, 100, "check");
        m.addClassType(ct2);
        Classes c = new Classes("1", "", "", null, null, 1, 60);
        m.getClassList().add(c);

        // test case 1 : max student at indivudal type
        boolean expResult = true;
        boolean result = m.isMaxStudent("1");
        assertEquals(expResult, result);
        //test case 2 : not max student at dual type
        Classes c1 = new Classes("2", "", "", null, null, 2, 60);
        m.getClassList().add(c1);
        expResult = false;
        result = m.isMaxStudent("2");
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassFromArray method, of class Model.
     */
    @Test
    public void testGetClassFromArray() {
        System.out.println("getClassFromArray");

        Model m = new Model();
        Classes c1 = new Classes("2", "", "", null, null, 2, 60);
        m.getClassList().add(c1);
        //testcase 1 : correct sting id
        Classes expResult = new Classes("2", "", "", null, null, 2, 60);
        assertEquals(c1, m.getClassFromArray("2"));

        //testcase 2 : not correct string id
        expResult = null;
        assertEquals(expResult, m.getClassFromArray("3"));

    }

    /**
     * Test of getAccountFromArray method, of class Model.
     */
    @Test
    public void testGetAccountFromArray() {
        System.out.println("getAccountFromArray");
        Model m = new Model();
        Account a1 = new Account("1", "", "", "", "", 1, "");
        a1.setActive(true);
        m.addAccount(a1);
        //testcase 1 : correct sting id
        Account expResult = new Account("1", "", "", "", "", 1, "");
        assertEquals(a1, m.getAccountFromArray("1"));

        //testcase 2 : not correct string id
        expResult = null;
        assertEquals(expResult, m.getClassFromArray("3"));

    }

    /**
     * Test of login method, of class Model.
     */
    @Test
    public void testLogin() {
        System.out.println("login");

        Model m = new Model();
        Account a1 = new Account("1", "123456", "123", "", "", 1, "");
        a1.setActive(true);
        m.addAccount(a1);
        //testcase 1 : correct id and pass

        boolean expResult = true;
        boolean result = m.login("123", "123456");
        assertEquals(expResult, result);

        // test case 2 : not correct id

        expResult = false;
        result = m.login("12344", "123456");
        assertEquals(expResult, result);

        // test case 3 : not correct password

        expResult = false;
        result = m.login("123", "123453246");
        assertEquals(expResult, result);

        // test case 4 : not correct password and id

        expResult = false;
        result = m.login("121233", "123453246");
        assertEquals(expResult, result);
    }

    /**
     * Test of adminLogin method, of class Model.
     */
    @Test
    public void testAdminLogin() {
        System.out.println("adminLogin");
        Model m = new Model();
        Account a1 = new Account("1", "123456", "123", "", "", 1, "");
        a1.setActive(true);
        a1.setRole(Ultility.ADMIN);
        m.addAccount(a1);
        //testcase 1 : correct id and pass

        boolean expResult = true;
        boolean result = m.login("123", "123456");
        assertEquals(expResult, result);

        // test case 2 : not correct id

        expResult = false;
        result = m.login("12344", "123456");
        assertEquals(expResult, result);

        // test case 3 : not correct password

        expResult = false;
        result = m.login("123", "123453246");
        assertEquals(expResult, result);

        // test case 4 : not correct password and id

        expResult = false;
        result = m.login("121233", "123453246");
        assertEquals(expResult, result);
    }

    /**
     * Test of findMaxIdStudent method, of class Model.
     */
    @Test
    public void testFindMaxIdStudent() {
        System.out.println("findMaxIdStudent");

        Model m = new Model();
        //testcase 1 : null array

        String expResult = null;
        String result = m.findMaxIdStudent();
        assertEquals(expResult, result);

        //testcase 2 : 2 students
        Student s1 = new Student("0001", "", "", "0001", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);
        Student s2 = new Student("0002", "", "", "0002", "", "", "", "", "", true, "", 1);
        s2.setActive(true);
        m.addStudent(s2);

        expResult = "0002";
        result = m.findMaxIdStudent();
        assertEquals(expResult, result);

        //testcase 3 : 3 students 

        Student s3 = new Student("0002", "", "", "0003", "", "", "", "", "", true, "", 1);
        s3.setActive(true);
        m.addStudent(s3);

        expResult = "0003";
        result = m.findMaxIdStudent();
        assertEquals(expResult, result);

    }

    /**
     * Test of findMaxIdTeacher method, of class Model.
     */
    @Test
    public void testFindMaxIdTeacher() {
        System.out.println("findMaxIdTeacher");
        Model m = new Model();
        //testcase 1 : null array

        String expResult = null;
        String result = m.findMaxIdTeacher();
        assertEquals(expResult, result);

        //testcase 2 : 2 students
        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", null, 1991, null);
        t1.setActive(true);
        m.addTeacher(t1);
        Teacher t2 = new Teacher("T00002", "", "", "", "", "", true, "", null, 1991, null);
        t2.setActive(true);
        m.addTeacher(t2);

        expResult = "T00002";
        result = m.findMaxIdTeacher();
        assertEquals(expResult, result);

        //testcase 3 : 3 students 

        Teacher t3 = new Teacher("T00003", "", "", "", "", "", true, "", null, 1991, null);
        t3.setActive(true);
        m.addTeacher(t3);

        expResult = "T00003";
        result = m.findMaxIdTeacher();
        assertEquals(expResult, result);

    }

    /**
     * Test of findClassById method, of class Model.
     */
    @Test
    public void testFindClassById() {
        System.out.println("findClassById");
        Model m = new Model();
        Classes c1 = new Classes("2", "", "", null, null, 2, 60);
        m.getClassList().add(c1);
        //testcase 1 : correct sting id
        Classes expResult = new Classes("2", "", "", null, null, 2, 60);
        assertEquals(c1, m.findClassById("2"));

        //testcase 2 : not correct string id
        expResult = null;
        assertEquals(expResult, m.findClassById("3"));
    }

    /**
     * Test of findAccountByUserName method, of class Model.
     */
    @Test
    public void testFindAccountByUserName() {
        System.out.println("findAccountByUserName");
        Model m = new Model();
        Account a1 = new Account("1", "", "check", "", "", 1, "");
        a1.setActive(true);
        m.addAccount(a1);
        //testcase 1 : correct sting id
        Account expResult = new Account("1", "", "check", "", "", 1, "");
        boolean expBoolean = true;
        assertEquals(expBoolean, m.findAccountByUserName("check"));

        //testcase 2 : not correct string id
        expResult = null;
        expBoolean = false;
        assertEquals(expBoolean, m.findAccountByUserName("check3"));
    }

    /**
     * Test of findClassByClassName method, of class Model.
     */
    @Test
    public void testFindClassByClassName() {
        System.out.println("findClassByClassName");
        Model m = new Model();
        Classes c1 = new Classes("2", "class 1", "", null, null, 2, 60);
        m.getClassList().add(c1);
        //testcase 1 : correct class name
        boolean expResult = true;
        boolean result = m.findClassByClassName("class 1");
        assertEquals(expResult, result);

        //testcase 2 : not correct string id
        expResult = false;
        result = m.findClassByClassName("class 2");
        assertEquals(expResult, result);
    }

    /**
     * Test of textToDate method, of class Model.
     */
    @Test
    public void testTextToDate() throws Exception {
        System.out.println("textToDate");

        Model m = new Model();
        String input = "12-12-2001";
        // test case 1 : correct input
        Date expResult = new Date(2001 - 1900, 11, 12);
        Date result = m.textToDate(input);
        assertEquals(expResult, result);
        // test case 2 : not correct input
          expResult = new Date(2001, 11, 12);
         result = m.textToDate(input);
        assertNotSame(expResult, result);
        
    }

    /**
     * Test of dateToString method, of class Model.
     */
    @Test
    public void testDateToString() throws Exception {
        System.out.println("dateToString");
     
        Model m = new Model();
        Date dat = new Date(2001 - 1900, 11, 12);
        
        // test case 1 : correct input
        String expResult = "12-00-2001";
        String result = m.dateToString(dat);
        assertEquals(expResult, result);
        
        // test case 2 : incorrect input
        expResult = "12-11-2001";
        result = m.dateToString(dat);
        assertNotSame(expResult, result);
    }

    /**
     * Test of convertGenderToString method, of class Model.
     */
    @Test
    public void testConvertGenderToString() {
        System.out.println("convertGenderToString");
        boolean gender = false;
        Model m = new Model();

        // test case 1: female
        String expResult = "Female";
        String result = m.convertGenderToString(gender);
        assertEquals(expResult, result);

        // test case 1: male
        gender = true;
        expResult = "Male";
        result = m.convertGenderToString(gender);
        assertEquals(expResult, result);

    }

    /**
     * Test of isManager method, of class Model.
     */
    @Test
    public void testIsManager() {
        System.out.println("isManager");

        String username = "";
        Model m = new Model();

        // test case 1 : null array
        boolean expResult = true;
        boolean result = m.isManager(username);
        assertEquals(expResult, result);

        // test case 2 : normal array  and correct name
        Account a1 = new Account("1", "", "check", "", "", 1, "");
        a1.setActive(true);
        a1.setRole(Ultility.ADMIN);
        m.addAccount(a1);
        username = "check";
        expResult = false;
        result = m.isManager(username);
        assertEquals(expResult, result);

        // test case 3 : normal array  and incorrect name

        username = "check1";
        expResult = true;
        result = m.isManager(username);
        assertEquals(expResult, result);

        // test case 4 : normal array  and correct name and not admin
        a1.setRole(Ultility.STAFF);
        username = "check1";
        expResult = true;
        result = m.isManager(username);
        assertEquals(expResult, result);


    }

    /**
     * Test of findStudentById method, of class Model.
     */
    @Test
    public void testFindStudentById() {
        System.out.println("findStudentById");
        Model m = new Model();
        Student s1 = new Student("0001", "", "", "0001", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);

        //testcase 1 : correct sting id
        Student expResult = new Student("0001", "", "", "0001", "", "", "", "", "", true, "", 1);
        assertEquals(s1, m.findStudentById("0001"));

        //testcase 2 : not correct string id
        expResult = null;
        assertEquals(expResult, m.findClassById("3"));
    }

    /**
     * Test of findTeacherById method, of class Model.
     */
    @Test
    public void testFindTeacherById() {
        System.out.println("findTeacherById");
        Model m = new Model();
        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", null, 1991, null);
        t1.setActive(true);
        m.addTeacher(t1);

        //testcase 1 : correct sting id
        Teacher expResult = new Teacher("T00001", "", "", "", "", "", true, "", null, 1991, null);
        assertEquals(t1, m.findTeacherById("T00001"));

        //testcase 2 : not correct string id
        expResult = null;
        assertEquals(expResult, m.findTeacherById("T0002"));
    }

    /**
     * Test of deleteTeacher method, of class Model.
     */
    @Test
    public void testDeleteTeacher() {
        System.out.println("deleteTeacher");

        Model m = new Model();
        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", null, 1991, null);
        t1.setActive(true);
        m.addTeacher(t1);
        // test case 1 : teacher is deletable
        m.deleteTeacher(t1);

        ArrayList<Teacher> array = new ArrayList<Teacher>();
        assertEquals(array, m.getTeacherList());

    }

    /**
     * Test of deactivePeople method, of class Model.
     */
    @Test
    public void testDeactivePeople() {
        System.out.println("deactivePeople");
        Model m = new Model();
        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", null, 1991, null);
        t1.setActive(true);
        m.addTeacher(t1);

        // test case 1 : teacher is changeable
        boolean expResult = false;
        m.deactivePeople(t1);
        assertEquals(expResult, t1.isActive());
    }

    /**
     * Test of deleteStudent method, of class Model.
     */
    @Test
    public void testDeleteStudent() {
        System.out.println("deleteStudent");
        Model m = new Model();
        Student s1 = new Student("0001", "", "", "0001", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);
        // test case 1 : student is deletable
        m.deleteStudent(s1);

        ArrayList<Student> array = new ArrayList<Student>();
        assertEquals(array, m.getStudentList());
    }

    /**
     * Test of isClassAtSchedule method, of class Model.
     */
    @Test
    public void testIsClassAtSchedule() {
        System.out.println("isClassAtSchedule");
        Model m = new Model();

        Classes c1 = new Classes("C000123", "class 1", "", null, null, 2, 60);
        m.getClassList().add(c1);
        // test case 1 : the schedule is empty
        boolean expResult = false;
        boolean result = m.isClassAtSchedule("00001");
        assertEquals(expResult, result);

        // test case 2 : the schedule is not empty but the id of class is in correct
        ArrayList<String> array = new ArrayList<String>();
        array.add("2-8.5-10.5-C000122");
        m.getSchedule().put("2.1", array);
        expResult = false;
        result = m.isClassAtSchedule("C000123");
        assertEquals(expResult, result);

        // test case 3 : the schedule is not empty but the id of class is correct
        expResult = true;
        result = m.isClassAtSchedule("C000122");
        assertEquals(expResult, result);
    }

    /**
     * Test of isClassHavePeople method, of class Model.
     */
    @Test
    public void testIsClassHavePeople() {
        System.out.println("isClassHavePeople");
        Model m = new Model();

        // test case 1 : the hash map is empty
        boolean expResult = false;
        boolean result = m.isClassHavePeople("check");
        assertEquals(expResult, result);

        // test case 2 : incorect id
        ArrayList<String> array = new ArrayList<String>();
        array.add("S0001");
        m.getEnrollMap().put("0001", array);
        expResult = false;
        result = m.isClassHavePeople("0002");
        assertEquals(expResult, result);

        // test case 3 : correct id 
        expResult = true;
        result = m.isClassHavePeople("0001");
        assertEquals(expResult, result);

    }

    /**
     * Test of copyClasses method, of class Model.
     */
    @Test
    public void testCopyClasses() {
        System.out.println("copyClasses");
        Model m = new Model();
        Classes c1 = new Classes("C000123", "class 1", "", null, null, 2, 60);
        m.getClassList().add(c1);

        int expResult = 2;
        m.copyClasses(c1);
        int result = m.getClassList().size();
        assertEquals(expResult, result);

    }

    /**
     * Test of findRoomByClassScheduleCode method, of class Model.
     */
    @Test
    public void testFindRoomByClassScheduleCode() {
        System.out.println("findRoomByClassScheduleCode");

        Model m = new Model();
        // test case 1 : schedule is empty

        String expResult = null;
        String result = m.findRoomByClassScheduleCode("code");
        assertEquals(expResult, result);

        // test case 2 : schedule is not empty but wrong input
        ArrayList<String> array = new ArrayList<String>();
        array.add("2-8.5-10.5-C000122");
        m.getSchedule().put("2.1", array);
        expResult = null;
        result = m.findRoomByClassScheduleCode("C000122");
        assertEquals(expResult, result);

        //test case 3 : correct input
        expResult = "2.1";
        result = m.findRoomByClassScheduleCode("2-8.5-10.5-C000122");
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentTimeStamp method, of class Model.
     */
    @Test
    public void testGetCurrentTimeStamp() {
        System.out.println("getCurrentTimeStamp");
        Model m = new Model();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        String[] arr = strDate.split("-");
        String expResult = arr[0].substring(2, 4) + arr[1];
        String result = m.getCurrentTimeStamp();
        assertEquals(expResult, result);

    }

    /**
     * Test of autoGenerateId method, of class Model.
     */
    @Test
    public void testAutoGenerateId() {
        System.out.println("autoGenerateId");
        Model m = new Model();

        // test case 1 : schedul is empty
        String expResult = m.getCurrentTimeStamp() + "001";
        String result = m.autoGenerateId();
        assertEquals(expResult, result);

        // test case 2 : chedule is not empty
        Student s1 = new Student("0001", "", "", "S00001", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);
        expResult = m.getCurrentTimeStamp() + m.findMaxIdOfPeople();
        result = m.autoGenerateId();
        assertEquals(expResult, result);


    }

    /**
     * Test of getCurrentTimeStamp2 method, of class Model.
     */
    @Test
    public void testGetCurrentTimeStamp2() {
        System.out.println("getCurrentTimeStamp2");
        Model m = new Model();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");//dd/MM/yyyy
        Date now = new Date();
        String expResult = sdfDate.format(now);
        String result = m.getCurrentTimeStamp2();
        assertEquals(expResult, result);

    }

    /**
     * Test of autoGenerateInvoiceId method, of class Model.
     */
    @Test
    public void testAutoGenerateInvoiceId() {
        System.out.println("autoGenerateInvoiceId");

        Model m = new Model();
        Student s1 = new Student("0001", "", "", "S00001", "", "", "", "", "", true, "", 1);
        Student s2 = null;
        s1.setActive(true);
        m.addStudent(s1);
        // test case 1 : normal case
        String expResult = "S00001-" + m.getCurrentTimeStamp2();
        String result = m.autoGenerateInvoiceId(s1);

        assertEquals(expResult, result);

        // test case 2 : null case 
        expResult = null;
        result = m.autoGenerateInvoiceId(s2);

        assertEquals(expResult, result);

    }

    /**
     * Test of newIDforInvoice method, of class Model.
     */
    @Test
    public void testNewIDforInvoice() {
        System.out.println("newIDforInvoice");

        Model m = new Model();
        Student s1 = new Student("0001", "", "", "S00001", "", "", "", "", "", true, "", 1);
        Student s2 = null;
        s1.setActive(true);
        m.addStudent(s1);

        // test case 1 : normal case 
        String expResult = "S00001-" + m.getCurrentTimeStamp2() + "-1";
        String result = m.newIDforInvoice(s1);

        assertEquals(expResult, result);

        // test case 2 : 2 invoice cases
        Invoice iv = new Invoice("S00001-" + m.getCurrentTimeStamp2() + "-1");
        m.getInvoiceList().add(iv);

        expResult = "S00001-" + m.getCurrentTimeStamp2() + "-2";
        result = m.newIDforInvoice(s1);

        assertEquals(expResult, result);


    }

    /**
     * Test of findMaxIdOfPeople method, of class Model.
     */
    @Test
    public void testFindMaxIdOfPeople() {
        System.out.println("findMaxIdOfPeople");
        Model m = new Model();
        // test case 1 : schedul is empty
        String expResult = null;
        String result = m.findMaxIdOfPeople();
        assertEquals(expResult, result);

        // test case 2 : chedule is not empty  and 1 student
        Student s1 = new Student("0001", "", "", "S00001", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);
        expResult = "002";
        result = m.findMaxIdOfPeople();
        assertEquals(expResult, result);

        // test case 3 : chedule is not empty  and 2 student
        Student s2 = new Student("0001", "", "", "S00002", "", "", "", "", "", true, "", 1);
        s2.setActive(true);
        m.addStudent(s2);
        expResult = "003";
        result = m.findMaxIdOfPeople();
        assertEquals(expResult, result);

    }

    /**
     * Test of getStudentInvoice method, of class Model.
     */
    @Test
    public void testGetStudentInvoice() {
        System.out.println("getStudentInvoice");
        Model m = new Model();
        Student s1 = new Student("0001", "", "", "S00001", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);
        // test case 1 : schedul is empty
        Invoice expResult = null;
        Invoice result = m.getStudentInvoice(s1);
        assertEquals(expResult, result);

    }

    /**
     * Test of studentOrTeacherCheck method, of class Model.
     */
    @Test
    public void testStudentOrTeacherCheck() {
        System.out.println("studentOrTeacherCheck");
        Model m = new Model();
        Student s1 = new Student("0001", "", "", "S00001", "", "", "", "", "", true, "", 1);
        s1.setActive(true);
        m.addStudent(s1);
        Teacher t1 = new Teacher("T00001", "", "", "", "", "", true, "", null, 1991, null);
        t1.setActive(true);
        m.addTeacher(t1);

        // test case 1: not correct id 
        String expResult = null;
        String result = m.studentOrTeacherCheck("check");
        assertEquals(expResult, result);

        // test case 2 : id of student
        expResult = "Student";
        result = m.studentOrTeacherCheck("S00001");
        assertEquals(expResult, result);


        // test case 3 : id of teacher
        expResult = "Teacher";
        result = m.studentOrTeacherCheck("T00001");
        assertEquals(expResult, result);

    }

    /**
     * Test of isPaid method, of class Model.
     */
    @Test
    public void testIsPaid() {
        System.out.println("isPaid");
        Model m = new Model();
        Student s1 = new Student("0001", "", "", "S00001", "", "", "", "", "", true, "", 1);
        s1.setActive(true);

        m.addStudent(s1);
        Student s2 = new Student("0001", "", "", "S00002", "", "", "", "", "", true, "", 1);
        s2.setActive(true);

        m.addStudent(s2);
        Invoice iv = new Invoice("S00001-" + m.getCurrentTimeStamp2() + "-1");
        m.getInvoiceList().add(iv);
        iv.setPaid(true);
        m.getStudentInvoiceMap().put("S00001", iv);

        // test case 1 : is paid

        boolean expResult = true;
        boolean result = m.isPaid(s1);
        assertEquals(expResult, result);
        // test case 2 : is not paid
        iv.setPaid(false);
        expResult = false;
        result = m.isPaid(s1);
        assertEquals(expResult, result);

        // test case 3 : not in the inovice map 
        expResult = false;
        result = m.isPaid(s2);
        assertEquals(expResult, result);


    }

   
    /**
     * Test of switchLanguage method, of class Model.
     */
    @Test
    public void testSwitchLanguage() {
        System.out.println("switchLanguage");

        Model m = new Model();
        // testcase 1 : for enlgish resource
        ResourceBundle expresources = ResourceBundle.getBundle("Properties/EngLish");
        m.setIsEng(false);
        m.switchLanguage();
        assertNotSame(expresources, m.getResources());
        // testcase 1 : for vietnamese resource
        expresources = ResourceBundle.getBundle("Properties/Vietnamese");
        m.setIsEng(true);
        m.switchLanguage();
        assertSame(expresources, m.getResources());

        
    }

    
}
