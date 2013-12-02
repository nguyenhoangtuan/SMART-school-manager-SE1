/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.swing.ImageIcon;

/**
 *
 * @author anh
 */
public interface Ultility {
    Model m = Model.getModel();
//    ResourceBundle resource = m.getResources(); 
    public static final int ONE_ONE =0, DUAL=1, MANY=2;
    public static final int QUANTITY_1=1, QUANTITY_2=2, QUANTITY_3 = 20 ;
    public static final boolean MALE=true, FEMALE=false;
    public static final int ROOM_TYPE_1=1, ROOM_TYPE_2=2, ROOM_TYPE_3=3;
    public static final boolean ACTIVE=true, DEACTIVE=false;
    public static final int ADMIN=0, STAFF=1;
    public static final int CASH=0, BANK=1, CREDIT=2;
    public static final int PIANO=0, GUITAR=1, PHOTOGRAPHY=2, HIP_HOP=3, VIOLIN=4, ORGAN=5, BALLET=6, PAINTING=7, SINGING=8;
    public static final char LECTURE='L', STUDENT='S';
    public static final int PANEL_WIDTH = 800, PANEL_HEIGHT = 600;
    public static final int TEXT_SIZE = 13;
    public static final int BORDER_LEFT = 80;
    public static final int FOUR_WEEK = 4;
    public static final int 
            CLASS_TYPE_PORT = 1
            ,STUDENT_PORT = 2
            ,TEACHER_PORT = 3
            ,ACCOUNT_PORT = 4;
    
    public static final String EMPTY_USER_PASS_TEXT_ERROR = m.getResources().getString("EMPTY_USER_PASS_TEXT_ERROR")
            ,EMPTY_USER_TEXT_ERROR = m.getResources().getString("EMPTY_USER_TEXT_ERROR")
            ,EMPTY_PASS_TEXT_ERROR = m.getResources().getString("EMPTY_PASS_TEXT_ERROR")
            ,EMPTY_PHONE_TEXT_ERROR =  m.getResources().getString("EMPTY_PHONE_TEXT_ERROR")
            ,EMPTY_EMAIL_TEXT_ERROR =  m.getResources().getString("EMPTY_EMAIL_TEXT_ERROR")
            ,EMPTY_CLASS_NAME_TEXT_ERROR =  m.getResources().getString("EMPTY_CLASS_NAME_TEXT_ERROR")
            ,EMPTY_CLASS_TEXTBOOK_TEXT_ERROR =  m.getResources().getString("EMPTY_CLASS_TEXTBOOK_TEXT_ERROR")
            ,EMPTY_CLASS_FEE_TEXT_ERROR=  m.getResources().getString("EMPTY_CLASS_FEE_TEXT_ERROR")
            ,EMPTY_CLASS_STARTDATE_TEXT_ERROR=  m.getResources().getString("EMPTY_CLASS_STARTDATE_TEXT_ERROR")
            ,EMPTY_CLASS_ENDDATE_TEXT_ERROR=  m.getResources().getString("EMPTY_CLASS_ENDDATE_TEXT_ERROR")
            ,EMPTY_STUDENT_FIRSTNAME_ERROR = m.getResources().getString("EMPTY_STUDENT_FIRSTNAME_ERROR")
            ,EMPTY_STUDENT_LASTNAME_ERROR = m.getResources().getString("EMPTY_STUDENT_LASTNAME_ERROR")
            ,EMPTY_STUDENT_PHONE_ERROR = m.getResources().getString("EMPTY_STUDENT_PHONE_ERROR")
            ,EMPTY_STUDENT_ADDRESS_ERROR = m.getResources().getString("EMPTY_STUDENT_ADDRESS_ERROR")
            ,EMPTY_STUDENT_EMAIL_ERROR = m.getResources().getString("EMPTY_STUDENT_EMAIL_ERROR")
            ,EMPTY_TEACHER_FIRSTNAME_ERROR = m.getResources().getString("EMPTY_TEACHER_FIRSTNAME_ERROR")
            ,EMPTY_TEACHER_LASTNAME_ERROR = m.getResources().getString("EMPTY_TEACHER_LASTNAME_ERROR")
            ,EMPTY_TEACHER_PHONE_ERROR = m.getResources().getString("EMPTY_TEACHER_PHONE_ERROR")
            ,EMPTY_TEACHER_ADDRESS_ERROR = m.getResources().getString("EMPTY_TEACHER_ADDRESS_ERROR")
            ,EMPTY_TEACHER_EMAIL_ERROR = m.getResources().getString("EMPTY_TEACHER_EMAIL_ERROR")
            ,EMPTY_TEACHER_STARTYEAR_ERROR = m.getResources().getString("EMPTY_TEACHER_STARTYEAR_ERROR")
            ,EMPTY_CONTACT_NAME_ERROR = m.getResources().getString("EMPTY_CONTACT_NAME_ERROR")
            ,EMPTY_CONTACT_PHONE_ERROR = m.getResources().getString("EMPTY_CONTACT_PHONE_ERROR")
            ,EMPTY_CONTACT_EMAIL_ERROR = m.getResources().getString("EMPTY_CONTACT_EMAIL_ERROR")
            ,EMPTY_CLASSTYPE_FEE45_OR_FEE60 =  m.getResources().getString("EMPTY_CLASSTYPE_FEE45_OR_FEE60")
            ,EMPTY_CLASSTYPE_INFORMATION =  m.getResources().getString("EMPTY_CLASSTYPE_INFORMATION")
            ,SAME_USERNAME_ERROR =  m.getResources().getString("SAME_USERNAME_ERROR")
            ,SAME_CLASSNAME_ERROR =  m.getResources().getString("SAME_CLASSNAME_ERROR")
            ,INVALID_ACCOUNT_NAME_FORMAT_ERROR =  m.getResources().getString("INVALID_ACCOUNT_NAME_FORMAT_ERROR")
            ,INVALID_CLASSNAME_FORMAT_ERROR =  m.getResources().getString("INVALID_CLASSNAME_FORMAT_ERROR")
            ,INVALID_PHONE_FORMAT_ERROR =  m.getResources().getString("INVALID_PHONE_FORMAT_ERROR")
            ,INVALID_EMAIL_FORMAT_ERROR =  m.getResources().getString("INVALID_EMAIL_FORMAT_ERROR")
            ,INVALID_TEXTBOOK_FORMAT_ERROR = m.getResources().getString("INVALID_TEXTBOOK_FORMAT_ERROR")
            ,INVALID_CLASSFEE_FORMAT_ERROR =  m.getResources().getString("INVALID_CLASSFEE_FORMAT_ERROR")
            ,INVALID_FIRSTNAME_FORMAT_ERROR =  m.getResources().getString("INVALID_FIRSTNAME_FORMAT_ERROR")
            ,INVALID_LASTNAME_FORMAT_ERROR =  m.getResources().getString("INVALID_LASTNAME_FORMAT_ERROR")
            ,INVALID_ADDRESS_FORMAT_ERROR =  m.getResources().getString("INVALID_ADDRESS_FORMAT_ERROR")
            ,INVALID_CONTACT_NAME_FORMAT_ERROR =  m.getResources().getString("INVALID_CONTACT_NAME_FORMAT_ERROR")
            ,INVALID_CONTACT_PHONE_FORMAT_ERROR =  m.getResources().getString("INVALID_CONTACT_PHONE_FORMAT_ERROR")
            ,INVALID_CONTACT_EMAIL_FORMAT_ERROR =  m.getResources().getString("INVALID_CONTACT_EMAIL_FORMAT_ERROR")
            ,INVALID_TEACHER_STARTYEAR_FORMAT_ERROR =  m.getResources().getString("INVALID_TEACHER_STARTYEAR_FORMAT_ERROR")
            ,INVALID_FEE45 =  m.getResources().getString("INVALID_FEE45")
            ,INVALID_FEE60 =  m.getResources().getString("INVALID_FEE60")
            ,WRONG_USER_PASS_ERROR =  m.getResources().getString("WRONG_USER_PASS_ERROR");
    
    public static final String QUIT_CONFRIM_TXT =  m.getResources().getString("QUIT_CONFRIM_TXT");
    
    public static final String USERNAME_TXT  =  m.getResources().getString("USERNAME_TXT")
            ,PASSWORD_TXT =  m.getResources().getString("PASSWORD_TXT")
            ,LOGIN_TXT =  m.getResources().getString("LOGIN_TXT")
            ,BACK_TXT =  m.getResources().getString("BACK_TXT")
            ,LOGO_TXT =  m.getResources().getString("LOGO_TXT");
    
    public static final String ACCOUNT_TXT  = m.getResources().getString("ACCOUNT_TXT")
            ,TEACHER_TXT = m.getResources().getString("TEACHER_TXT")
            ,STUDENT_TXT = m.getResources().getString("STUDENT_TXT")
            ,ENROLLMENT_TXT =m.getResources().getString("ENROLLMENT_TXT")
            ,SCHEDULE_TXT = m.getResources().getString("SCHEDULE_TXT")
            ,CLASS_TXT = m.getResources().getString("CLASS_TXT")
            ,DASH_BOARD_TXT = m.getResources().getString("DASH_BOARD_TXT")
            ,CLASS_TYPE_TXT = m.getResources().getString("CLASS_TYPE_TXT")
            ,INVOICES_TXT = m.getResources().getString("INVOICES_TXT");
    
    public static final String FILE_MENU = m.getResources().getString("FILE_MENU")
            ,VIEW_MENU = m.getResources().getString("VIEW_MENU")
            ,HELP_MENU = m.getResources().getString("HELP_MENU")
            ,TOOL_MENU = m.getResources().getString("TOOL_MENU")
            ,EXIT_TXT = m.getResources().getString("EXIT_TXT")
            ,SWITCH_TXT = m.getResources().getString("SWITCH_TXT")
            ,USER_GUIDE_TXT = m.getResources().getString("USER_GUIDE_TXT")
            ,ADD_TXT = m.getResources().getString("ADD_TXT")
            ,EDIT_TXT = m.getResources().getString("EDIT_TXT")
            ,CANCEL_TXT = m.getResources().getString("CANCEL_TXT")
            ,PAID_TXT = m.getResources().getString("PAID_TXT")
            ,UN_PAID_TXT = m.getResources().getString("UN_PAID_TXT")
            ,SAVE_TXT = m.getResources().getString("SAVE_TXT")
            ,COPY_TXT = m.getResources().getString("COPY_TXT")
            ,REPORT_TXT = m.getResources().getString("REPORT_TXT")
            ,DELETE_TXT = m.getResources().getString("DELETE_TXT")
            ,DELETE_ALL_TXT = m.getResources().getString("DELETE_ALL_TXT")
            ,ABOUT_TXT = m.getResources().getString("ABOUT_TXT")
            ,ACTIVE_TXT = m.getResources().getString("ACTIVE_TXT")
            ,INVOICE_TXT = m.getResources().getString("INVOICE_TXT")
            ,TIMETABLE_TXT = m.getResources().getString("TIMETABLE_TXT")
            ,CLOSE_TXT = m.getResources().getString("CLOSE_TXT")
            ,PRINT_TXT = m.getResources().getString("PRINT_TXT")
            ,LOGOUT_TXT = m.getResources().getString("LOGOUT_TXT")
            ,LANGUAGE_TXT = m.getResources().getString("LANGUAGE_TXT")
            ,NEXT_TXT = m.getResources().getString("NEXT_TXT");
    
    
    public static final ImageIcon TOP_PANEL_PIC = new ImageIcon(("Images/topPanel.png"))
            ,BACKGROUND_PIC = new ImageIcon(("Images/background.png"))
            ,LEFT_PANEL_PIC = new ImageIcon(("Images/leftPanel.png"))
            ,LOGIN_PIC = new ImageIcon(("Images/loginPanel.png"))
            ,ITEM_PANEL_PIC = new ImageIcon(("Images/itemPanel.png"))
            ,ITEM_MENU_PIC = new ImageIcon(("Images/menuItem.png"))
            ,INPUT_PANEL_PIC = new ImageIcon(("Images/inputPanel.png"))           
            ,SELECTED_ITEM_PANEL_PIC = new ImageIcon(("Images/itemPanelSelected.png"));
    
    public static final ImageIcon LOGIN_BUTTON = new ImageIcon(("Images/loginButton.png"))
            ,EXIT_BUTTON = new ImageIcon(("Images/exitButton.png"))
            ,EDIT_BUTTON = new ImageIcon(("Images/editButton.png"))
            ,DELETE_BUTTON = new ImageIcon(("Images/deleteButton.png"))
            ,IMPORT_BUTTON = new ImageIcon(("Images/importButton.png"))
            ,EXPORT_BUTTON = new ImageIcon(("Images/exportButton.png"))
            ,DELETE_ALL_BUTTON = new ImageIcon(("Images/deleteAllButton.png"))
            ,ACTIVE_BUTTON = new ImageIcon(("Images/activeButton.png"))
            ,LOG_OUT_BUTTON = new ImageIcon(("Images/logoutButton.png"))
            ,ENROLLMENT_BUTTON = new ImageIcon(("Images/enrollmentButton.png"))
            ,COPY_BUTTON = new ImageIcon(("Images/copyButton.png"))   
            ,INVOICE_BUTTON = new ImageIcon(("Images/invoiceButton.png"))
            ,TIMETABLE_BUTTON = new ImageIcon(("Images/timetableButton.png"))
            ,REPORT_BUTTON = new ImageIcon(("Images/reportButton.png"))
            ,LIST_BUTTON = new ImageIcon(("Images/reportButton.png"))
            ,ADD_BUTTON = new ImageIcon(("Images/addButton.png"));
    
    public static final String[] ROLE= {"Admin","Staff"};
    public static final String[] CLASS_TYPE = {"Invidual","Dual","Group"};
    public static final String[] PAID_METHOD= {"cash","bank tranfer","credit card"};
    public static final String DEFAULT_PHOTO = "user.png";

    
    public static final int MON = 2;
    public static final int TUE = 3;
    public static final int WED = 4;
    public static final int THU = 5;
    public static final int FRI = 6;
    public static final int SAT = 7;

    
//    public static final double ARRAYHOURS[] = {8,8.5,9,9.5,10,10.5,11,11.5,12,12.5,13,13.5,14,14.5,15,15.5,16,16.5,17,17.5,18,18.5,19,19.5,20,20.5};
    
    public static final int ARRAYNUMBER[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27
            ,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51};
    
     public static final double ARRAYHOURS[] = {8,8.25,8.5,8.75,9,9.25,9.5,9.75,10,10.25,10.5,10.75,11,11.25
             ,11.5,11.75,12,12.25,12.5,12.75,13,13.25,13.5,13.75,14,14.25,14.5,14.75,15,15.25,15.5,15.75,16,16.25,
             16.5,16.75,17,17.25,17.5,17.75,18,18.25,18.5,18.75,19,19.25,19.5,19.75,20,20.25,20.5, 20.75 ,21};

}
