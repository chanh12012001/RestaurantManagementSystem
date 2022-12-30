package GUI;

import GUI.Component.TableManager.NavigationLayout;
import GUI.Component.TableManager.TableInfomationLayout;
import GUI.Component.TableManager.TableManagerLayout;
import GUI.Component.TableManager.TableMapLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class TableManagerJFrame extends JPanel{
    private final Dimension dimension;

    public TableManagerJFrame(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;
        
        navigationLayout = new NavigationLayout(dimension);
        tableManagerLayout = new TableManagerLayout(dimension);
        tableMapLayout = new TableMapLayout(dimension);
        tableInfomationLayout = new TableInfomationLayout(dimension);
        
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setBackground(new Color(0, 0, 0, 0));
        setPreferredSize(new Dimension(bodyWidth, bodyHeight));

        add(navigationLayout, BorderLayout.NORTH);
        add(tableManagerLayout, BorderLayout.SOUTH); 
        navigationLayout.addEventTabSelected((var index) -> {
            switch (index) {
                case 0 -> tableManagerLayout.show(tableMapLayout);
                case 1 -> tableManagerLayout.show(tableInfomationLayout);
            
                default -> throw new AssertionError();
            }
        });
    }
    
    // Variables declaration - do not modify     
    private GUI.Component.TableManager.NavigationLayout navigationLayout;
    private GUI.Component.TableManager.TableManagerLayout tableManagerLayout;
    private GUI.Component.TableManager.TableMapLayout tableMapLayout;
    private GUI.Component.TableManager.TableInfomationLayout tableInfomationLayout;
    // nd of variables declaration 
}
