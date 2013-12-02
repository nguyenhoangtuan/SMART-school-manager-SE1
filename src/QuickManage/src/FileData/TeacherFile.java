/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

/**
 *
 * @author Lyn
 */
import Model.Teacher;
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
public class TeacherFile {

    public TeacherFile() {
    }

    public void writeFile(ArrayList<Teacher> accs) throws FileNotFoundException {

        try {
            FileOutputStream fl = new FileOutputStream("TeacherFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);

            oops.writeObject(accs);
         
            oops.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<Teacher> readFile() {
        ArrayList<Teacher> accs = new ArrayList<Teacher>();
        try {
            FileInputStream saveFile = new FileInputStream("TeacherFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);

            accs = (ArrayList) restore.readObject();
          
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}