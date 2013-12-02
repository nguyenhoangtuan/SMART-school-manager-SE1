/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

/**
 *
 * @author Lyn
 */
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
public class ScheduleFile {

    public ScheduleFile() {
    }

    public void writeFile(HashMap<String, ArrayList<String>> accs) throws FileNotFoundException {

        try {
            FileOutputStream fl = new FileOutputStream("ScheduleFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);
            oops.writeObject(accs);
//            oops.writeObject("aaaaaaa");
            oops.close();
        } catch (Exception e) {
        }
    }

    public HashMap<String, ArrayList<String>> readFile() {
        HashMap<String, ArrayList<String>> accs = new HashMap<String, ArrayList<String>> ();
        try {
            FileInputStream saveFile = new FileInputStream("ScheduleFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            accs = (HashMap<String, ArrayList<String>>) restore.readObject();
//            String ss = (String)restore.readObject();
//            System.out.println(ss);
//            
//            System.out.println(accs.size());
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}