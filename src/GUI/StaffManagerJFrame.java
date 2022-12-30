package GUI;

import GUI.Component.StaffManager.AccountAndAuthorizationLayout;
import GUI.Component.StaffManager.NavigationLayout;
import GUI.Component.StaffManager.StaffInfoListLayout;
import GUI.Component.StaffManager.StaffManagerLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class StaffManagerJFrame extends JPanel{
    private final Dimension dimension;

    public StaffManagerJFrame(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;
        
        navigationLayout = new NavigationLayout(dimension);
        staffManagerLayout = new StaffManagerLayout(dimension);
        staffInfoLayout = new StaffInfoListLayout(dimension);
        accountAndAuthorizationLayout = new AccountAndAuthorizationLayout(dimension);
        
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setBackground(new Color(0, 0, 0, 0));
        setPreferredSize(new Dimension(bodyWidth, bodyHeight));

        add(navigationLayout, BorderLayout.NORTH);
        add(staffManagerLayout, BorderLayout.SOUTH); 
        navigationLayout.addEventTabSelected((var index) -> {
            switch (index) {
                case 0 -> staffManagerLayout.show(staffInfoLayout);
                case 1 -> staffManagerLayout.show(accountAndAuthorizationLayout);
            
                default -> throw new AssertionError();
            }
        });
    }
    
    // Variables declaration - do not modify     
    private GUI.Component.StaffManager.NavigationLayout navigationLayout;
    private GUI.Component.StaffManager.StaffManagerLayout staffManagerLayout;
    private GUI.Component.StaffManager.StaffInfoListLayout staffInfoLayout;
    private GUI.Component.StaffManager.AccountAndAuthorizationLayout accountAndAuthorizationLayout;
    // nd of variables declaration 
}
