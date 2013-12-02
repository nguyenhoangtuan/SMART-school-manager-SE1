/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CustomComponent;

import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author anh
 */
public class CLabel extends JLabel{

    public CLabel(String text, int size) {
        super(text);
        setFont(new Font("Time New Roman", CENTER, size ));
        setOpaque(false);
    }

    public CLabel(String image) {
        
        ImageIcon ic = new ImageIcon("/Images/user.png");
        this.setIcon(ic);
    }
    
    
    
}
