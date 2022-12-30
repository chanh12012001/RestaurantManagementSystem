package GUI.Component.Dashboard;

import GUI.BillManagerJFrame;
import GUI.FoodManagerJFrame;
import GUI.LoginJFrame;
import GUI.StaffManagerJFrame;
import GUI.StatisticJFrame;
import GUI.TableManagerJFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DashboardLayout extends JPanel {

    private final Dimension dimension;
    private String accountType = "NV";

    public DashboardLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }

    public DashboardLayout(Dimension dimension, String accountType) {
        this.dimension = dimension;
        this.accountType = accountType;
        initComponents();
        setOpaque(false);
    }

    private void initComponents() {
        int width = dimension.width;
        int height = dimension.height;

        menuLayout = new MenuLayout(new Dimension(width / 4, height - 10));
        bodyLayout = new BodyLayout(new Dimension(width - width / 4 - 15, height - 10));

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(flowLayout);

        add(menuLayout);
        add(bodyLayout);

        menuLayout.getMenu().addEventMenuSelected((var index) -> {
            Dimension dimensionBodyLayout = new Dimension(width - width / 4 - 15, height - 10);
            switch (index) {
                case 0 ->
                    bodyLayout.show(new TableManagerJFrame(dimensionBodyLayout));
                case 1 -> {
                    if (accountType.equals("QL")) {
                        bodyLayout.show(new FoodManagerJFrame(dimensionBodyLayout));
                    } else {
                        JOptionPane.showMessageDialog(bodyLayout, "Bạn không đủ thẩm quyền", "Thẩm quyền", JOptionPane.WARNING_MESSAGE);
                    }
                }

                case 2 -> {
                    if (accountType.equals("QL")) {
                        bodyLayout.show(new StaffManagerJFrame(dimensionBodyLayout));
                    } else {
                        JOptionPane.showMessageDialog(bodyLayout, "Bạn không đủ thẩm quyền", "Thẩm quyền", JOptionPane.WARNING_MESSAGE);
                    }
                }

                case 3 ->
                    bodyLayout.show(new BillManagerJFrame(dimensionBodyLayout));
                case 4 -> {
                    if (accountType.equals("QL")) {
                        bodyLayout.show(new StatisticJFrame(dimensionBodyLayout));
                    } else {
                        JOptionPane.showMessageDialog(bodyLayout, "Bạn không đủ thẩm quyền", "Thẩm quyền", JOptionPane.WARNING_MESSAGE);
                    }
                }
                case 5 ->
                    optionPaneConfirmLogout();
                default ->
                    throw new AssertionError();
            }

        });

    }

    void optionPaneConfirmLogout() {
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            JFrame topFrame= (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
            new LoginJFrame().setVisible(true);
        } else if (result == JOptionPane.NO_OPTION) {
            System.err.println("No");
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(graphics);
    }

    // Variables declaration - do not modify   
    private GUI.Component.Dashboard.MenuLayout menuLayout;
    private GUI.Component.Dashboard.BodyLayout bodyLayout;
    // End of variables declaration   
}
