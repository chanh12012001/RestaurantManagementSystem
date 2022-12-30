package GUI.Component.StaffManager;

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
       
       btnStaffInfoTab = new RoundedButton();
       btnAccountInfoTab = new RoundedButton();
              
       FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
       setLayout(flowLayout);
       setBackground(new Color(0, 0, 0, 0));
       // setPreferredSize(new Dimension(width, height / 10));
       
       // set properties for btnStaffInfoTab
       btnStaffInfoTab.setForeground(new java.awt.Color(255, 255, 255));
       btnStaffInfoTab.setText("Danh sách nhân viên");
       btnStaffInfoTab.setColor(new java.awt.Color(153, 153, 255));
       btnStaffInfoTab.setColorOver(new java.awt.Color(51, 153, 255));
       btnStaffInfoTab.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
       btnStaffInfoTab.setRadius(10);
       btnStaffInfoTab.setBorderPainted(false);
       btnStaffInfoTab.setFocusPainted(false);
       btnStaffInfoTab.setPreferredSize(new Dimension(width / 4, height / 22));
       btnStaffInfoTab.setBorderColor(Color.red);
       btnStaffInfoTab.addActionListener((ActionEvent evt) -> {
           btnStaffInfoTabActionPerformed(evt);
       });
       
       // set properties for btnAccountInfoTab
       btnAccountInfoTab.setForeground(new java.awt.Color(255, 255, 255));
       btnAccountInfoTab.setText("Tài khoản & phân quyền");
       btnAccountInfoTab.setColor(new java.awt.Color(153, 153, 255));
       btnAccountInfoTab.setColorOver(new java.awt.Color(51, 153, 255));
       btnAccountInfoTab.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
       btnAccountInfoTab.setRadius(10);
       btnAccountInfoTab.setBorderPainted(false);
       btnAccountInfoTab.setFocusPainted(false);
       btnAccountInfoTab.setPreferredSize(new Dimension(width / 4, height / 22));
       btnAccountInfoTab.setBorderColor(Color.red);
       btnAccountInfoTab.addActionListener((ActionEvent evt) -> {
          btnAccountInfoTabActionPerformed(evt);
       });
       
       add(btnStaffInfoTab);
       add(btnAccountInfoTab);
    }
    
    // btnTableManagerTab mouse−click events
    private void btnStaffInfoTabActionPerformed(ActionEvent evt) {  
        //distinguish two tabs on click
        setBorderWidthButtonOnClick(btnStaffInfoTab, btnAccountInfoTab);
        if (event != null) event.selected(0);
    } 
    
     // btnTableInfoTab mouse−click events
    private void btnAccountInfoTabActionPerformed(ActionEvent evt) {  
        // distinguish two tabs on click
        setBorderWidthButtonOnClick(btnAccountInfoTab, btnStaffInfoTab);
        if (event != null) event.selected(1);
    } 
    
    
    // set border button on click
    private void setBorderWidthButtonOnClick(RoundedButton btnClick, RoundedButton btnNoneClick) {
        btnClick.setBorderWidth(2);
        btnNoneClick.setBorderWidth(0);
        btnNoneClick.repaint();
    }
    
    // Variables declaration - do not modify     
    private GUI.Component.RoundedButton btnStaffInfoTab;
    private GUI.Component.RoundedButton btnAccountInfoTab;   
    // nd of variables declaration 
}


