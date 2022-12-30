package GUI.Component.Dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

public class MenuLayout extends JPanel{
    
    private final Dimension dimension;
    
    public MenuLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
        menu.addMouseListener(new MouseAdapter() {});
    }
      
    private void initComponents() {
        menu = new Menu(dimension);
        
        System.out.println(dimension.width);
        
        setPreferredSize(dimension);
        
        setLayout(new BorderLayout());
        
        add(menu, BorderLayout.CENTER);
    }
    
      @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(graphics); 
    }
    
     public Menu getMenu() {
        return menu;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Component.Dashboard.Menu menu;
    // End of variables declaration//GEN-END:variables
}
