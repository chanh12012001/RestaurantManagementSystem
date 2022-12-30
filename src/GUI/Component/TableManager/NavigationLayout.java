package GUI.Component.TableManager;

import GUI.Component.RoundedButton;
import Interface.EventMenuSelected;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class NavigationLayout extends JPanel{
    private final Dimension dimension;
    private EventMenuSelected event;

    public NavigationLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
//        setOpaque(false);
    }
    
     public void addEventTabSelected(EventMenuSelected event) {
        this.event = event;
    }
    
    private void initComponents() {
       int width = dimension.width;
       int height = dimension.height;
       
       btnTableMapTab = new RoundedButton();
       btnTableInfoTab = new RoundedButton();
              
       FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
       setLayout(flowLayout);
       setBackground(new Color(0, 0, 0, 0));
       // setPreferredSize(new Dimension(width, height / 10));
       
       // set properties for btnTableManagerTab
       btnTableMapTab.setForeground(new java.awt.Color(255, 255, 255));
       btnTableMapTab.setText("Sơ đồ bàn ăn");
       btnTableMapTab.setColor(new java.awt.Color(153, 153, 255));
       btnTableMapTab.setColorOver(new java.awt.Color(51, 153, 255));
       btnTableMapTab.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
       btnTableMapTab.setRadius(10);
       btnTableMapTab.setBorderPainted(false);
       btnTableMapTab.setFocusPainted(false);
       btnTableMapTab.setPreferredSize(new Dimension(width / 4, height / 22));
       btnTableMapTab.setBorderColor(Color.red);
       btnTableMapTab.addActionListener((ActionEvent evt) -> {
           btnTableMapTabActionPerformed(evt);
       });
       
       // set properties for btnTableManagerTab
       btnTableInfoTab.setForeground(new java.awt.Color(255, 255, 255));
       btnTableInfoTab.setText("Thông tin bàn ăn");
       btnTableInfoTab.setColor(new java.awt.Color(153, 153, 255));
       btnTableInfoTab.setColorOver(new java.awt.Color(51, 153, 255));
       btnTableInfoTab.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
       btnTableInfoTab.setRadius(10);
       btnTableInfoTab.setBorderPainted(false);
       btnTableInfoTab.setFocusPainted(false);
       btnTableInfoTab.setPreferredSize(new Dimension(width / 4, height / 22));
       btnTableInfoTab.setBorderColor(Color.red);
       btnTableInfoTab.addActionListener((ActionEvent evt) -> {
           btnTableInfoTabActionPerformed(evt);
       });
       
       add(btnTableMapTab);
       add(btnTableInfoTab);
    }
    
    // btnTableManagerTab mouse−click events
    private void btnTableMapTabActionPerformed(ActionEvent evt) {  
        //distinguish two tabs on click
        setBorderWidthButtonOnClick(btnTableMapTab, btnTableInfoTab);
        if (event != null) event.selected(0);
    } 
    
     // btnTableInfoTab mouse−click events
    private void btnTableInfoTabActionPerformed(ActionEvent evt) {  
        // distinguish two tabs on click
        setBorderWidthButtonOnClick(btnTableInfoTab, btnTableMapTab);
        if (event != null) event.selected(1);
    } 
    
    
    // set border button on click
    private void setBorderWidthButtonOnClick(RoundedButton btnClick, RoundedButton btnNoneClick) {
        btnClick.setBorderWidth(2);
        btnNoneClick.setBorderWidth(0);
        btnNoneClick.repaint();
    }
    
    // Variables declaration - do not modify     
    private GUI.Component.RoundedButton btnTableMapTab;
    private GUI.Component.RoundedButton btnTableInfoTab;   
    // nd of variables declaration 
}
