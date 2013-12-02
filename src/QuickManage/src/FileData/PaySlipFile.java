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
import Model.PaySlip;
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
public class PaySlipFile {

    public PaySlipFile() {
    }

    public void writeFile(ArrayList<PaySlip> accs) throws FileNotFoundException {
        System.out.println("Check save class");
        try {
            FileOutputStream fl = new FileOutputStream("PaySlipFile.dat");
            ObjectOutputStream oops = new ObjectOutputStream(fl);
            oops.writeObject(accs);
             System.out.println("Check save class 1");
            oops.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<PaySlip> readFile() {
        System.out.println("Check load class");
        ArrayList<PaySlip> accs = new ArrayList<PaySlip>();
        try {
            FileInputStream saveFile = new FileInputStream("PaySlipFile.dat");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            System.out.println("Check load class 2");
            accs = (ArrayList) restore.readObject();
            restore.close();
        } catch (Exception e) {
        }
        return accs;
    }
}