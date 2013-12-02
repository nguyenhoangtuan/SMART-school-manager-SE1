/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

/**
 *
 * @author Lyn
 */
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
public class ClassFile {

    public ClassFile() {
    }

    public void writeFile(ArrayList<Classes> accs) throws FileNotFoundException {
        System.out.println("Check save class");
        try {
            FileOutputStream fl = new FileOutputStream("ClassFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);
            oops.writeObject(accs);
             System.out.println("Check save class 1");
            oops.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<Classes> readFile() {
        System.out.println("Check load class");
        ArrayList<Classes> accs = new ArrayList<Classes>();
        try {
            FileInputStream saveFile = new FileInputStream("ClassFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            System.out.println("Check load class 2");
            accs = (ArrayList) restore.readObject();
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}