/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Main.Main;
import View.CustomComponent.CPanel;
import Model.Ultility;
import View.CustomComponent.CButton;
import View.CustomComponent.CLabel;
import View.CustomComponent.CTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import Model.Model;
import View.AccountsGUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author anh
 */
public final class LoginGUI extends CPanel implements Ultility {

    private CPanel namePanel, passPanel, confirmPanel, mainPanel, bigPanel, InforPanel, topPanel;
    private JButton loginButton, backButton;
    CTextField nameField;
    JPasswordField passField;
    CLabel nameLabel, passLabel, logoLb, loginLb;
    JButton langBt;
//    Model modeln = new Model();
    Model model = Model.getModel();
    ResourceBundle resourse = model.getResources();

    public LoginGUI() {

        super(new GridBagLayout(), BACKGROUND_PIC); //background picture

        bigPanel = new CPanel();
        bigPanel.add(getLoginPanel(), BorderLayout.CENTER);
        add(bigPanel);

        //press Enter key to login.
        KeyAdapter ka = new enterKey();
        nameField.addKeyListener(ka);
        passField.addKeyListener(ka);
        
        
//        nameField.setText("manager");
//        passField.setText("manager");

    }

    private CPanel getLoginPanel() {

        mainPanel = new CPanel(new GridLayout(1, 1), LOGIN_PIC);
        mainPanel.setBorder(new EmptyBorder(10, 25, 0, 25));

        // mainPanel.setPreferredSize(new Dimension(1000, 520));
        InforPanel = new CPanel();
        InforPanel.setLayout(new GridLayout(4, 1));
        topPanel = new CPanel();
        logoLb = new CLabel(LOGO_TXT, 35);
        loginLb = new CLabel(LOGIN_TXT, 10);
        topPanel.add(logoLb, BorderLayout.CENTER);
        topPanel.add(loginLb, BorderLayout.CENTER);
        InforPanel.add(topPanel);

        namePanel = new CPanel();
        passPanel = new CPanel();
        confirmPanel = new CPanel();

        nameField = new CTextField(18);
        passField = new JPasswordField(18);

        //nameLabel = new CLabel(USERNAME_TXT, 15);  theo cach cu~, ong chi can thay resourse.getString("USERNAME_TXT") vao cai  USERNAME_TXT
       //passLabel = new CLabel(PASSWORD_TXT, 15); 
        nameLabel = new CLabel(resourse.getString("USERNAME_TXT"), 15); // cach moi
        passLabel = new CLabel(resourse.getString("PASSWORD_TXT"), 15);

        loginButton = new JButton(resourse.getString("LOGIN_TXT"));
        backButton = new JButton(resourse.getString("BACK_TXT"));
        langBt = new JButton(m.getResources().getString("LANGUAGE_TXT"));

//        loginButton.setPreferredSize(new Dimension(70, 20));
//        backButton.setPreferredSize(new Dimension(70, 20));
        
        langBt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.switchLanguage();
                Main.changePanel(new LoginGUI());
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginHandling();
            }
        });
        
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.saveAllData();
                    System.exit(0);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        InforPanel.add(namePanel);
        InforPanel.add(passPanel);
        InforPanel.add(confirmPanel);

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        passPanel.add(passLabel);
        passPanel.add(passField);

        confirmPanel.setLayout(new FlowLayout());
        confirmPanel.add(loginButton);
        confirmPanel.add(backButton);
        confirmPanel.add(langBt);

        mainPanel.add(InforPanel);
        return mainPanel;
    }
    
    

    public void loginHandling() {

        String username = nameField.getText().trim();
        String password = String.valueOf(passField.getPassword()).trim();

        if (username.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, resourse.getString("EMPTY_USER_PASS_TEXT_ERROR"), "Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocusInWindow();
        } else if (username.equals("")) {
            JOptionPane.showMessageDialog(null, resourse.getString("EMPTY_USER_TEXT_ERROR"), "Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocusInWindow();
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, resourse.getString("EMPTY_PASS_TEXT_ERROR"), "Error", JOptionPane.ERROR_MESSAGE);
            passField.requestFocusInWindow();
        } else if (model.adminLogin(username, password)) {
            new Dashboard();
            Main.changePanel(new AccountsGUI(username));

        } else if (model.login(username, password)) {
            new Dashboard();
            Main.changePanel(new TeacherGUI(username));

        } else {
            //announce wrong username or password
            JOptionPane.showMessageDialog(null, resourse.getString("WRONG_USER_PASS_ERROR"), "Error", JOptionPane.ERROR_MESSAGE);
            passField.requestFocusInWindow();
        }
    }

    private class enterKey extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //press Enter key to login...
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                loginHandling();
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }
}
