
package Utils;

import DAO.Food_DAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Chanh Pham
 */
public class ImageUtils {
    public static byte[] convertFileToByteArray(File imageFile) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(imageFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) {
                Logger.getLogger(Food_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Food_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public static ImageIcon convertByteArrayToImageIcon(byte[] imageData) {
            Image image= Toolkit.getDefaultToolkit().createImage(imageData);
            ImageIcon icon = new ImageIcon(image);
            return icon;
    }
               
}
