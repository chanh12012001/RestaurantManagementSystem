package GUI.Component.FoodManager;

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
       
       btnFoodInfoTab = new RoundedButton();
       btnFoodGroupTab = new RoundedButton();
              
       FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
       setLayout(flowLayout);
       setBackground(new Color(0, 0, 0, 0));
       // setPreferredSize(new Dimension(width, height / 10));
       
       // set properties for btnTableManagerTab
       btnFoodInfoTab.setForeground(new java.awt.Color(255, 255, 255));
       btnFoodInfoTab.setText("Danh sách món ăn");
       btnFoodInfoTab.setColor(new java.awt.Color(153, 153, 255));
       btnFoodInfoTab.setColorOver(new java.awt.Color(51, 153, 255));
       btnFoodInfoTab.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
       btnFoodInfoTab.setRadius(10);
       btnFoodInfoTab.setBorderPainted(false);
       btnFoodInfoTab.setFocusPainted(false);
       btnFoodInfoTab.setPreferredSize(new Dimension(width / 4, height / 22));
       btnFoodInfoTab.setBorderColor(Color.red);
       btnFoodInfoTab.addActionListener((ActionEvent evt) -> {
           btnFoodInfoTabActionPerformed(evt);
       });
       
       // set properties for btnTableManagerTab
       btnFoodGroupTab.setForeground(new java.awt.Color(255, 255, 255));
       btnFoodGroupTab.setText("Nhóm món ăn");
       btnFoodGroupTab.setColor(new java.awt.Color(153, 153, 255));
       btnFoodGroupTab.setColorOver(new java.awt.Color(51, 153, 255));
       btnFoodGroupTab.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
       btnFoodGroupTab.setRadius(10);
       btnFoodGroupTab.setBorderPainted(false);
       btnFoodGroupTab.setFocusPainted(false);
       btnFoodGroupTab.setPreferredSize(new Dimension(width / 4, height / 22));
       btnFoodGroupTab.setBorderColor(Color.red);
       btnFoodGroupTab.addActionListener((ActionEvent evt) -> {
          btnFoodGroupTabActionPerformed(evt);
       });
       
       add(btnFoodInfoTab);
       add(btnFoodGroupTab);
    }
    
    // btnTableManagerTab mouse−click events
    private void btnFoodInfoTabActionPerformed(ActionEvent evt) {  
        //distinguish two tabs on click
        setBorderWidthButtonOnClick(btnFoodInfoTab, btnFoodGroupTab);
        if (event != null) event.selected(0);
    } 
    
     // btnTableInfoTab mouse−click events
    private void btnFoodGroupTabActionPerformed(ActionEvent evt) {  
        // distinguish two tabs on click
        setBorderWidthButtonOnClick(btnFoodGroupTab, btnFoodInfoTab);
        if (event != null) event.selected(1);
    } 
    
    
    // set border button on click
    private void setBorderWidthButtonOnClick(RoundedButton btnClick, RoundedButton btnNoneClick) {
        btnClick.setBorderWidth(2);
        btnNoneClick.setBorderWidth(0);
        btnNoneClick.repaint();
    }
    
    // Variables declaration - do not modify     
    private GUI.Component.RoundedButton btnFoodInfoTab;
    private GUI.Component.RoundedButton btnFoodGroupTab;   
    // nd of variables declaration 
}

