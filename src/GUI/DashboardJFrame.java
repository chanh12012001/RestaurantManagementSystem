package GUI;

import GUI.Component.Dashboard.DashboardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class DashboardJFrame extends JFrame {

    private String accountType = "NV";

    public DashboardJFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public DashboardJFrame(String accountType) {
        this.accountType = accountType;
        initComponents();
        setLocationRelativeTo(null);
    }

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    private void initComponents() {
        int width = (int) (size.width * 0.8);
        int height = (int) (size.height * 0.8);

        setPreferredSize(new Dimension(width, height));

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        dashboardLayout = new DashboardLayout(new Dimension(width, height), accountType);

        getContentPane().add(dashboardLayout);

        pack();
    }

    // Variables declaration - do not modify                     
    private GUI.Component.Dashboard.DashboardLayout dashboardLayout;
    // End of variables declaration      
}
