package GUI.Component.TableManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

public class TableManagerLayout extends JPanel{
    private final Dimension dimension;

    public TableManagerLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;
        
        tableMapLayout = new TableMapLayout(dimension);
        
        setPreferredSize(new Dimension(bodyWidth, bodyHeight - bodyHeight / 22 - 10));  
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());
        
        show(tableMapLayout);
        
    }

    
    public void show(Component com) {
        removeAll();
        add(com);
        repaint();
        revalidate();
    }
    
    private GUI.Component.TableManager.TableMapLayout tableMapLayout;
}
