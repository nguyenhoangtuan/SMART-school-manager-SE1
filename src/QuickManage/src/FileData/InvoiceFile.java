/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

import Model.Account;
import Model.Invoice;
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
public class InvoiceFile {

    public void writeFile(ArrayList<Invoice> accs) throws FileNotFoundException {
        System.out.println("check invoice save");
        try {
            System.out.println("check size = " +accs.size());
            for (Invoice item : accs) {
                System.out.println(item.toString());
                
         
            }
            FileOutputStream fl = new FileOutputStream("InvoiceFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);
            System.out.println("check invoice save1");
            oops.writeObject(accs);
            System.out.println("check invoice save2");
            oops.close();
            System.out.println("check invoice save3");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    public ArrayList<Invoice> readFile() {
        ArrayList<Invoice> accs = new ArrayList<Invoice>();
        try {
            FileInputStream saveFile = new FileInputStream("InvoiceFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            accs = (ArrayList<Invoice>) restore.readObject();
            restore.close();
        } catch (Exception e) {
        }
        System.out.println("check invoice load");
        return accs;
    }
}
