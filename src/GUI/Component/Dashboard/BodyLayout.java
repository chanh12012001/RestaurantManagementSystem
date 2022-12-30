package GUI.Component.Dashboard;

import GUI.TableManagerJFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class BodyLayout extends JPanel{
    private final Dimension dimension;
    int selectedIndex = MenuList.selectedIndex;


    public BodyLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;
        
        tableManagerJFrame = new TableManagerJFrame(dimension);
        
        setPreferredSize(new Dimension(bodyWidth, bodyHeight));  
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());
        
        show(tableManagerJFrame);
        
    }
      
//    @Override
//    protected void paintComponent(Graphics graphics) {
//        Graphics2D g2 = (Graphics2D) graphics;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#6190E8"), 0, getHeight(), Color.decode("#A7BFE8"));
//        g2.setPaint(gradientPaint);
//        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
//        super.paintComponent(graphics); 
//    }
    
    public void show(Component com) {
        removeAll();
        add(com);
        repaint();
        revalidate();
    }
    
    private GUI.TableManagerJFrame tableManagerJFrame;
}
