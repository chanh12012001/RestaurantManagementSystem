package GUI;

import GUI.Component.FoodManager.FoodGroupLayout;
import GUI.Component.FoodManager.FoodInfoListLayout;
import GUI.Component.FoodManager.FoodManagerLayout;
import GUI.Component.FoodManager.NavigationLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JPanel;

public class FoodManagerJFrame extends JPanel{
    private final Dimension dimension;

    public FoodManagerJFrame(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;
        
        navigationLayout = new NavigationLayout(dimension);
        foodManagerLayout = new FoodManagerLayout(dimension);
        foodInfoLayout = new FoodInfoListLayout(dimension);
        foodGroupLayout = new FoodGroupLayout(dimension);
        
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setBackground(new Color(0, 0, 0, 0));
        setPreferredSize(new Dimension(bodyWidth, bodyHeight));

        add(navigationLayout, BorderLayout.NORTH);
        add(foodManagerLayout, BorderLayout.SOUTH); 
        navigationLayout.addEventTabSelected((var index) -> {
            
            switch (index) {
                case 0 -> foodManagerLayout.show(foodInfoLayout);
                case 1 -> foodManagerLayout.show(foodGroupLayout);
            
                default -> throw new AssertionError();
            }
        });
        
    }
    
    // Variables declaration - do not modify     
    private GUI.Component.FoodManager.NavigationLayout navigationLayout;
    private GUI.Component.FoodManager.FoodManagerLayout foodManagerLayout;
    private GUI.Component.FoodManager.FoodInfoListLayout foodInfoLayout;
    private GUI.Component.FoodManager.FoodGroupLayout foodGroupLayout;
    // nd of variables declaration 
}
