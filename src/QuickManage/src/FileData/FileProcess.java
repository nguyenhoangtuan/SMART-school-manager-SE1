package FileData;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileProcess {

    public static boolean copyFile(String sourceFile, String targetFile) {
        return FileProcess.copyFile(new File(sourceFile), new File(targetFile));
    }

    public static boolean copyFile(File sourceFile, File targetFile) {
        boolean result = false;
        try {
            FileInputStream is = new FileInputStream(sourceFile);
            FileOutputStream os = new FileOutputStream(targetFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            is.close();
            os.close();
            result = true;
            return result;
        } catch (IOException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
      //  JFileChooser FC = new JFileChooser("C:/");
        JFrame jf = new JFrame();
        jf.setLayout(new FlowLayout());
        jf.setVisible(true);
        jf.setSize(200, 200);
        JButton bt = new JButton("save");
        jf.add(bt);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    if (checkExt(chooser.getSelectedFile().getPath())) {
                        String path = "Images/" + generateName(findExt(chooser.getSelectedFile().getName()));
                        System.out.println(path);
                        FileProcess.copyFile(chooser.getSelectedFile().getPath(), path);
                    }
                }
            }
        });
    }

    public static boolean checkExt(String path) {
        String extension = "";
        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i + 1);
        }
        if (extension.equals("jpg") || extension.equals("png") || extension.equals("gif") || extension.equals("bmp")) {
            return true;
        } else {
            return false;
        }

    }

    public static String findExt(String path) {
        String extension = "";

        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i + 1);
        }
        if (extension.equals("jpg") || extension.equals("png") || extension.equals("gif") || extension.equals("bmp")) {
            return extension;
        } else {
            return null;
        }
    }

    public static String generateName(String ext) {
        Random generator = new Random();
        int n = 10000;
        int randomIndex = generator.nextInt(n);
        return Integer.toString(randomIndex) + "image." + ext;
    }
    
}
