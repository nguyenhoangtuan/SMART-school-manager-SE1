/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CustomComponent;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 *
 * @author anh
 */


public class CDialog extends JDialog{
    
     public Image image;

    public CDialog(ImageIcon icon) {
        image = icon.getImage();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

    }
    
    
    
}
