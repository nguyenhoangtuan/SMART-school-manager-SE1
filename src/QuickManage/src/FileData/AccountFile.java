/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

/**
 *
 * @author Lyn
 */
import Model.Account;
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
public class AccountFile {

    public AccountFile() {
    }

    public void writeFile(ArrayList<Account> accs) throws FileNotFoundException {

        try {
            FileOutputStream fl = new FileOutputStream("AccountFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);
            oops.writeObject(accs);
            oops.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<Account> readFile() {
        ArrayList<Account> accs = new ArrayList<Account>();
        try {
            FileInputStream saveFile = new FileInputStream("AccountFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            accs = (ArrayList<Account>) restore.readObject();
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}