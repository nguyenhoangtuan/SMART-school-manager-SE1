/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

/**
 *
 * @author Lyn
 */
import Model.ClassType;
import Model.Classes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Lyn
 */
public class ClassTypeFile {

    public ClassTypeFile() {
    }

    public void writeFile(ArrayList<ClassType> accs) throws FileNotFoundException {
        System.out.println("Check save class");
        try {
            FileOutputStream fl = new FileOutputStream("ClassTypeFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);
            oops.writeObject(accs);
             System.out.println("Check save class 1");
            oops.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<ClassType> readFile() {
        System.out.println("Check load class");
        ArrayList<ClassType> accs = new ArrayList<ClassType>();
        try {
            FileInputStream saveFile = new FileInputStream("ClassTypeFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            System.out.println("Check load class 2");
            accs = (ArrayList) restore.readObject();
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}