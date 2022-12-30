package GUI.Component.StaffManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

public class StaffManagerLayout extends JPanel{
    private final Dimension dimension;

    public StaffManagerLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;
        
        staffInfoListLayout = new StaffInfoListLayout(dimension);
        
        setPreferredSize(new Dimension(bodyWidth, bodyHeight - bodyHeight / 22 - 10));  
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());
        
        show(staffInfoListLayout);
        
    }
      
    public void show(Component com) {
        removeAll();
        add(com);
        repaint();
        revalidate();
    }
    
    private GUI.Component.StaffManager.StaffInfoListLayout staffInfoListLayout;
}
