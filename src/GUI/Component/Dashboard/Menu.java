package GUI.Component.Dashboard;

import DTO.MenuModel;
import GUI.Component.ImageComponent;
import Interface.EventMenuSelected;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu extends JPanel{
    private final Dimension dimension;
     
    public Menu(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
        menuList.setOpaque(false);
//        menuList.setFixedCellHeight(60);
        initData();
    }
    
    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        menuList.addEventMenuSelected(event);
    }
    
    private void initData() {
        menuList.addItem(new MenuModel("ic_table", "Quản Lý Bàn Ăn", MenuModel.MenuType.MENU));
        menuList.addItem(new MenuModel("ic_noodle", "Quản Lý Thực Đơn", MenuModel.MenuType.MENU));
        menuList.addItem(new MenuModel("ic_staff", "Quản Lý Nhân Viên", MenuModel.MenuType.MENU));
        menuList.addItem(new MenuModel("ic_bill", "Quản Lý Hoá Đơn", MenuModel.MenuType.MENU));
        menuList.addItem(new MenuModel("ic_statistics", "Thống Kê - Báo Cáo", MenuModel.MenuType.MENU));
        menuList.addItem(new MenuModel("ic_logout", "Thoát", MenuModel.MenuType.MENU));
        menuList.addItem(new MenuModel("", " ", MenuModel.MenuType.EMPTY));
    }

    
    private void initComponents() {
        
        imgLogoApp = new ImageComponent();
        menuList = new MenuList<>(new Dimension(dimension.width, dimension.height - dimension.width));
        
        
//        menuList.setFixedCellHeight(200);
        
        // get size menu
        int width = dimension.width;
        int height = dimension.height;
        setPreferredSize(dimension);
        
        //set layout
        FlowLayout flowLayout = new FlowLayout();
        // flowLayout.setHgap(50);
        flowLayout.setVgap(30);
        setLayout(flowLayout);
        
        // logo App container
        JPanel logoContainner = new JPanel();

        int widthLogoContainner = (int) (width * 0.8);
        logoContainner.setPreferredSize(new Dimension(widthLogoContainner, widthLogoContainner));
        logoContainner.setBackground(new Color(0, 0, 0, 0));
         
        imgLogoApp.setPreferredSize(new Dimension(widthLogoContainner, widthLogoContainner));
        imgLogoApp.setImage(new ImageIcon(getClass().getResource("/assets/ic_app.png")));
        logoContainner.setLayout(new BorderLayout());
        logoContainner.add(imgLogoApp);
                
        add(logoContainner);
        add(menuList);
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#A3E4FC"), 0, getHeight(), Color.decode("#2980B9"));
        g2.setPaint(gradientPaint);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(graphics); 
    }
    
    // Variables declaration - do not modify     
    private GUI.Component.ImageComponent imgLogoApp;
    private GUI.Component.Dashboard.MenuList<String> menuList;

    // nd of variables declaration   

}
