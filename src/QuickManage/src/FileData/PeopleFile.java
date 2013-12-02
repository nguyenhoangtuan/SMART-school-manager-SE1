/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

/**
 *
 * @author Lyn
 */
import Model.People;
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
public class PeopleFile {

    public PeopleFile() {
    }

    public void writeFile(ArrayList<People> accs) throws FileNotFoundException {

        try {
            FileOutputStream fl = new FileOutputStream("PeopleFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);

            oops.writeObject(accs);
         
            oops.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<People> readFile() {
        ArrayList<People> accs = new ArrayList<People>();
        try {
            FileInputStream saveFile = new FileInputStream("PeopleFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);

            accs = (ArrayList) restore.readObject();
          
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}