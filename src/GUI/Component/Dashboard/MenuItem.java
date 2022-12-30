package GUI.Component.Dashboard;

import DTO.MenuModel;
import GUI.Component.ImageComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuItem extends JPanel{
    
//    private final MenuModel menuModel;
    private boolean selected;
    private final Dimension dimension;
    
    public MenuItem(MenuModel menuModel, Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setBackground(Color.red);
        setOpaque(false);
        if (menuModel.getType() == MenuModel.MenuType.MENU) {
            iconMenu.setImage(menuModel.toIcon());
            lbMenuName.setText(menuModel.getName());
        } else if (menuModel.getType() == MenuModel.MenuType.TITLE) {
//            iconMenu.setText(menuModel.getName());
            iconMenu.setFont(new Font("sansserif", 1, 12));
            lbMenuName.setVisible(false);
        } else {
            lbMenuName.setText(" ");
        }
    }
    
     private void initComponents() {
         
        int itemWidth = (int)(dimension.width * 0.8);
        //int itemHeight = dimension.height;
                
        iconMenu = new ImageComponent();
        lbMenuName = new JLabel();
        
        setPreferredSize(new Dimension(itemWidth, 60));
        
        iconMenu.setForeground(new java.awt.Color(255, 255, 255));
        iconMenu.setPreferredSize(new Dimension(45, 45));
        
        lbMenuName.setForeground(new java.awt.Color(255, 255, 255));
        lbMenuName.setText("Menu Name");
        lbMenuName.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
//        flowLayout.setHgap(30);
//        flowLayout.setVgap(20);
        setLayout(flowLayout);
                 
        add(iconMenu);
        add(lbMenuName);
    }
   
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 80));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        }
        super.paintComponent(grphcs);
    }
    
   
    
    // Variables declaration - do not modify     
    private GUI.Component.ImageComponent iconMenu;
    private javax.swing.JLabel lbMenuName;
    // nd of variables declaration   
}
