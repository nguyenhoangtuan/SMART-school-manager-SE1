/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

/**
 *
 * @author Lyn
 */
import Model.PaySlip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Lyn
 */
public class TeacherPaySlipFile {

    public TeacherPaySlipFile() {
    }

    public void writeFile(HashMap<String, PaySlip> accs) throws FileNotFoundException {
        try {
            FileOutputStream fl = new FileOutputStream("TeacherPaySlipFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);
            oops.writeObject(accs);
            oops.close();
        } catch (Exception e) {
        }
    }

    public HashMap<String, PaySlip> readFile() {
        HashMap<String, PaySlip> accs = new HashMap<String,PaySlip> ();
        try {
            FileInputStream saveFile = new FileInputStream("TeacherPaySlipFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            accs = (HashMap<String,PaySlip>) restore.readObject();
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}