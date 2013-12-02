/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import FileData.FileProcess;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Lyn
 */
public class ImportExportSCV {

    Model m = Model.getModel();
    ImportExportSCV ie;
    String eol = System.getProperty("line.separator");
    
    public ImportExportSCV() {
        ie = this;
        
    }

    public void importSCV(String fileName, int flag) {
        // flag == 1 : classtype
        // flag == 2 : Student
        // flag == 3 : Teacher
        // flag == 4 : Account

        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                if (flag == 1) {
                    ClassType ct = new ClassType(line);
                    m.addClassType(ct);
                } else if (flag == 2) {
                    Student st = new Student(m.codeContructorForPeople(line));
                    m.addStudent(st);
                } else if (flag == 3) {
                    Teacher te = new Teacher(m.codeContructorForPeople(line));
                    m.addTeacher(te);
                } else if (flag == 4) {
                    Account ac = new Account(line);
                    m.addAccount(ac);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportSCVClassType(String fileName) {
        try {
            FileWriter writer = new FileWriter(new File(fileName));

            for (ClassType cat : m.getClassTypeList()) {
                writer.append(cat.exportSCV());
                writer.append(eol);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportSCVStudent(String fileName) {
        try {
            FileWriter writer = new FileWriter(new File(fileName));

            for (Student cat : m.getStudentList()) {
                writer.append(cat.exportSCV());
                writer.append(eol);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportSCVTeacher(String fileName) {
        try {
            FileWriter writer = new FileWriter(new File(fileName));

            for (Teacher cat : m.getTeacherList()) {
                writer.append(cat.exportSCV());
                writer.append(eol);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportSCVAccount(String fileName) {
        try {
             
            FileWriter writer = new FileWriter(new File(fileName));

            for (Account cat : m.getAccountList()) {
                writer.append(cat.exportSCV());
                writer.append(eol);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void activeImport(final int flag) {
        // flag == 1 : classtype
        // flag == 2 : Student
        // flag == 3 : Teacher
        // flag == 4 : Account
//        FileProcess fp = new FileProcess();
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {

            String path = chooser.getSelectedFile().getPath();
            System.out.println(path);
            ie.importSCV(path, flag);


        }
    }

    public void activeExport(int flag) {
        // flag == 1 : classtype
        // flag == 2 : Student
        // flag == 3 : Teacher
        // flag == 4 : Account
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose diretory ");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            if (flag == 1) {
                ie.exportSCVClassType(chooser.getSelectedFile().getPath() + "\\classtype.txt");
            } else if (flag == 2) {
                ie.exportSCVStudent(chooser.getSelectedFile().getPath() + "\\student.txt");
            }else if (flag == 3) {
                ie.exportSCVTeacher(chooser.getSelectedFile().getPath() + "\\teacher.txt");
            }else if (flag == 4) {
                ie.exportSCVAccount(chooser.getSelectedFile().getPath() + "\\account.txt");
            }
        }
    }
    
    public static void main(String[] args) {
        ImportExportSCV a = new ImportExportSCV();
        a.activeExport(4);
    }
}
