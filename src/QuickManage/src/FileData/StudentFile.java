/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

/**
 *
 * @author Lyn
 */
import Model.Student;
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
public class StudentFile {

    public StudentFile() {
    }

    public void writeFile(ArrayList<Student> accs) throws FileNotFoundException {

        try {
            FileOutputStream fl = new FileOutputStream("StudentFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);

            oops.writeObject(accs);
         
            oops.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<Student> readFile() {
        ArrayList<Student> accs = new ArrayList<Student>();
        try {
            FileInputStream saveFile = new FileInputStream("StudentFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);

            accs = (ArrayList) restore.readObject();
          
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}