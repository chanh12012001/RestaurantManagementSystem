package GUI.Component.FoodManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

public class FoodManagerLayout extends JPanel{
    private final Dimension dimension;

    public FoodManagerLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;
        
        foodInfoListLayout = new FoodInfoListLayout(dimension);
        
        setPreferredSize(new Dimension(bodyWidth, bodyHeight - bodyHeight / 22 - 10));  
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());
        
        show(foodInfoListLayout);
        
    }
      
    public void show(Component com) {
        removeAll();
        add(com);
        repaint();
        revalidate();
    }
    
    private GUI.Component.FoodManager.FoodInfoListLayout foodInfoListLayout;
}