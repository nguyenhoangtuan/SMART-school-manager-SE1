/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileData;

import Model.Invoice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Lyn
 */
public class InvoiceMapFile {
    
      public void writeFile(HashMap<String,Invoice> accs) throws FileNotFoundException {

        try {
            FileOutputStream fl = new FileOutputStream("InvoiceMapFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);
            oops.writeObject(accs);
//            oops.writeObject("aaaaaaa");
            oops.close();
        } catch (Exception e) {
        }
    }

    public HashMap<String, Invoice> readFile() {
        HashMap<String, Invoice> accs = new HashMap<String, Invoice> ();
        try {
            FileInputStream saveFile = new FileInputStream("InvoiceMapFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            accs = (HashMap<String, Invoice>) restore.readObject();
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
