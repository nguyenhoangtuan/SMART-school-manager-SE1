/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.Account;
import Model.Model;
import Model.Ultility;
import View.LoginGUI;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author anh
 */
public class Main extends JFrame implements Ultility {

    private static JPanel currentPanel;
    private static Main mainFrame;
    Model model = Model.getModel();

    public Main() {
        currentPanel = new JPanel();
        setTitle("SMART");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
//        setResizable(false);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
         model.loadAllData();
         if(model.getAccountList().isEmpty()){
             model.addAccount(new Account(model.newAccountId(), "manager", "manager", "manager@rmit.edu.vn", "093-462-1919", ADMIN, "user.png"));
         }
        add(currentPanel);
    }

    public static Main getMainFrame() {
        return mainFrame;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(laf.getName())) {
                    UIManager.setLookAndFeel(laf.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Cannot load the Nimbus theme!!!");
        }
        
        
        mainFrame = new Main();
        changePanel(new LoginGUI());
    }

        @Override
    protected void processWindowEvent(WindowEvent e) {

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            
           
            try {
                
                model.saveAllData();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
    }

    
    public static void changePanel(JPanel newPanel) {

        mainFrame.remove(currentPanel);
        currentPanel = newPanel;
        mainFrame.add(currentPanel);
        mainFrame.validate();
        System.gc();
    }
}
