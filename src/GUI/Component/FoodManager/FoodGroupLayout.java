package GUI.Component.FoodManager;

import BUS.RestaurantManagementFacade;
import DTO.FoodGroup_DTO;
import GUI.Component.RoundedButton;
import GUI.Component.RoundedTextField;
import Interface.EventTextChange;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class FoodGroupLayout extends JPanel{
    private final Dimension dimension;
    
    String[] properties = { "Mã nhóm món ", "Tên nhóm món"};
    RestaurantManagementFacade restaurantManagementFacade;

    public FoodGroupLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
       int foodGroupWidth = dimension.width;
       int foodGroupHeight = dimension.height - dimension.height / 22 - 10;
       restaurantManagementFacade = RestaurantManagementFacade.getInstance();
       
       
       tfFoodGroupID = new RoundedTextField();
       tfFoodGroupName = new RoundedTextField();
       tfSearch = new RoundedTextField();
       btnAddFoodGroup = new RoundedButton();
       btnUpdateFoodGroup = new RoundedButton();
       btnDeleteFoodGroup = new RoundedButton();
       dtmTableModel = new DefaultTableModel(properties, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tbFoodGroup = new JTable(dtmTableModel);
        tbFoodGroup.getSelectionModel().addListSelectionListener((e) -> {
            rowSelectedListener(e);
        });

        restaurantManagementFacade.getAllFoodGroups(dtmTableModel);
       
        /**
         * info Staff Form Layout
         */
        JPanel infoFoodGroupFormLayout = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#FFFFFF"), 0, getHeight(), Color.decode("#A7BFE8"));
                g2.setPaint(gradientPaint);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(graphics); 
            }
        };
        infoFoodGroupFormLayout.setOpaque(false);
        infoFoodGroupFormLayout.setBorder(BorderFactory.createTitledBorder(null, "Thông tin nhóm món ăn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font("Helvetica Neue", 1, 22), new Color(65, 72, 204))); // NOI18N
        infoFoodGroupFormLayout.setPreferredSize(new Dimension(foodGroupWidth, foodGroupHeight / 3));
        infoFoodGroupFormLayout.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // label Mã nhóm
        gbc.gridx = 0;
        gbc.gridy = 0;      
        gbc.insets = new Insets(0, 0, 35, 20);
        gbc.anchor = GridBagConstraints.EAST;
        
        JLabel lbTextFoodGroupID = new JLabel("Mã nhóm");
        lbTextFoodGroupID.setFont(new Font("sansserif", 0, 15));
        lbTextFoodGroupID.setForeground(Color.BLACK);
        infoFoodGroupFormLayout.add(lbTextFoodGroupID, gbc);
              
        // textfield mã nhóm
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.anchor = GridBagConstraints.WEST;
        
        tfFoodGroupID.setBorderColor(new java.awt.Color(204, 204, 204));
        tfFoodGroupID.setBorderWidth(1);
        tfFoodGroupID.setHintText("");
        tfFoodGroupID.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfFoodGroupID.setRound(20);
        tfFoodGroupID.setPreferredSize(new Dimension((int) (foodGroupWidth / 3.5) , 35));
        tfFoodGroupID.setBackground(new Color(225, 229, 235));
        tfFoodGroupID.setEditable(false);
        
        infoFoodGroupFormLayout.add(tfFoodGroupID, gbc);
        
        // label tên nhóm
        gbc.gridx = 0;
        gbc.gridy = 1;      
        gbc.insets = new Insets(0, 0, 0, 20);
        gbc.anchor = GridBagConstraints.EAST;
        
        JLabel lbTextFoodGroupName = new JLabel("Tên nhóm");
        lbTextFoodGroupName.setFont(new Font("sansserif", 0, 15));
        lbTextFoodGroupName.setForeground(Color.BLACK);
        infoFoodGroupFormLayout.add(lbTextFoodGroupName, gbc);
              
        // textfield tên nhóm
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        
        tfFoodGroupName.setBorderColor(new java.awt.Color(204, 204, 204));
        tfFoodGroupName.setBorderWidth(1);
        tfFoodGroupName.setHintText("");
        tfFoodGroupName.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfFoodGroupName.setRound(20);
        tfFoodGroupName.setPreferredSize(new Dimension((int) (foodGroupWidth / 3.5) , 35));
        
        infoFoodGroupFormLayout.add(tfFoodGroupName, gbc);
        
        /**
         * Business Layout
         */
        JPanel businessLayout = new JPanel();
        businessLayout.setPreferredSize(new Dimension(foodGroupWidth, (int) (foodGroupHeight / 10)));
        businessLayout.setLayout(new GridBagLayout());
        GridBagConstraints gbcBusiness = new GridBagConstraints();
        
        // label tìm kiếm
        gbcBusiness.gridx = 0;
        gbcBusiness.gridy = 0;    
        gbcBusiness.insets = new Insets(0, 0, 0, 10);
        gbcBusiness.anchor = GridBagConstraints.EAST;
        
        JLabel lbTextSearch = new JLabel("Tìm kiếm");
        lbTextSearch.setFont(new Font("sansserif", 0, 15));
        lbTextSearch.setForeground(Color.BLACK);
        businessLayout.add(lbTextSearch, gbcBusiness);
        
        // textfield tìm kiếm
        gbcBusiness.gridx = 1;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 40);
        gbcBusiness.anchor = GridBagConstraints.WEST;
        
        tfSearch.setBorderColor(new java.awt.Color(204, 204, 204));
        tfSearch.setBorderWidth(1);
        tfSearch.setHintText("Nhập tên cần tìm ...");
        tfSearch.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfSearch.setRound(20);
        tfSearch.setPreferredSize(new Dimension((int) (foodGroupWidth / 3.5) , 35));
        
        tfSearch.getDocument().addDocumentListener((EventTextChange) (DocumentEvent evt) -> {
            tfSearchTextChangeActionPerformed(evt);
        });

        
        businessLayout.add(tfSearch, gbcBusiness);
        
        
        // button thêm nhân viên
        gbcBusiness.gridx = 2;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 15);
        gbcBusiness.anchor = GridBagConstraints.WEST;
        
        btnAddFoodGroup.setForeground(new java.awt.Color(255, 255, 255));
        btnAddFoodGroup.setText("THÊM");
        btnAddFoodGroup.setColor(new java.awt.Color(6, 208, 214));
        btnAddFoodGroup.setColorOver(new java.awt.Color(51, 153, 255));
        btnAddFoodGroup.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnAddFoodGroup.setRadius(20);
        btnAddFoodGroup.setBorderPainted(false);
        btnAddFoodGroup.setFocusPainted(false);     
        btnAddFoodGroup.setPreferredSize(new Dimension(foodGroupWidth / 6, (int) (foodGroupWidth / 15)));
        btnAddFoodGroup.setBorderColor(Color.red);
        ImageIcon iconAddPerson = new ImageIcon(getClass().getResource("/assets/ic_add_food_group.png"));
        Image imgAddPerson = iconAddPerson.getImage();
        Image newimgAddPerson = imgAddPerson.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconAddPerson = new ImageIcon(newimgAddPerson); 
        btnAddFoodGroup.setIcon(iconAddPerson);
        btnAddFoodGroup.addActionListener((ActionEvent evt) -> {
           btnAddFoodGroupActionPerformed(evt);
        });
        
        businessLayout.add(btnAddFoodGroup, gbcBusiness);

        
        // button cập nhật nhân viên
        gbcBusiness.gridx = 3;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 15);
        gbcBusiness.anchor = GridBagConstraints.WEST;
        
        btnUpdateFoodGroup.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateFoodGroup.setText("CẬP NHẬT");
        btnUpdateFoodGroup.setColor(new java.awt.Color(235, 147, 33));
        btnUpdateFoodGroup.setColorOver(new java.awt.Color(51, 153, 255));
        btnUpdateFoodGroup.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnUpdateFoodGroup.setRadius(20);
        btnUpdateFoodGroup.setBorderPainted(false);
        btnUpdateFoodGroup.setFocusPainted(false);     
        btnUpdateFoodGroup.setPreferredSize(new Dimension(foodGroupWidth / 6, (int) (foodGroupWidth / 15)));
        btnUpdateFoodGroup.setBorderColor(Color.red);
        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/assets/ic_update.png"));
        Image imgUpdate = iconUpdate.getImage();
        Image newimgUpdate = imgUpdate.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconUpdate = new ImageIcon(newimgUpdate); 
        btnUpdateFoodGroup.setIcon(iconUpdate);
        btnUpdateFoodGroup.addActionListener((ActionEvent evt) -> {
            btnUpdateFoodGroupActionPerformed(evt);
        });
        
        businessLayout.add(btnUpdateFoodGroup, gbcBusiness);
        
        
        // button xoá nhân viên
        gbcBusiness.gridx = 4;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 0);
        gbcBusiness.anchor = GridBagConstraints.WEST;
        
        btnDeleteFoodGroup.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteFoodGroup.setText("XOÁ");
        btnDeleteFoodGroup.setColor(new java.awt.Color(227, 14, 42));
        btnDeleteFoodGroup.setColorOver(new java.awt.Color(51, 153, 255));
        btnDeleteFoodGroup.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnDeleteFoodGroup.setRadius(20);
        btnDeleteFoodGroup.setBorderPainted(false);
        btnDeleteFoodGroup.setFocusPainted(false);     
        btnDeleteFoodGroup.setPreferredSize(new Dimension(foodGroupWidth / 6, (int) (foodGroupWidth / 15)));
        btnDeleteFoodGroup.setBorderColor(Color.red);
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/assets/ic_delete.png"));
        Image imgDelete = iconDelete.getImage();
        Image newimgDelete = imgDelete.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconDelete = new ImageIcon(newimgDelete); 
        btnDeleteFoodGroup.setIcon(iconDelete);
        btnDeleteFoodGroup.addActionListener((ActionEvent evt) -> {
            btnDeleteFoodGroupActionPerformed(evt);
        });
        
        businessLayout.add(btnDeleteFoodGroup, gbcBusiness);
        
        /**
         * Table Staff Layout
         */
        JPanel tableLayout = new JPanel();
        tableLayout.setPreferredSize(new Dimension(foodGroupWidth, (int) (foodGroupHeight - foodGroupHeight/3 - foodGroupHeight / 10)));
        tableLayout.setLayout(new BorderLayout());
         
//        tbFoodInfoList.setPreferredSize(new Dimension(bodyWidth, (int) (bodyHeight - bodyHeight / 2 - bodyHeight / 10)));
        tbFoodGroup.setFocusable(false);
        tbFoodGroup.setIntercellSpacing(new Dimension(0, 0));
        tbFoodGroup.setRowHeight(33);
        tbFoodGroup.setSelectionBackground(Color.lightGray);
        tbFoodGroup.setShowVerticalLines(true);
        tbFoodGroup.setFont(new Font("Segoe UI", 0, 13));

        
        tbFoodGroup.getTableHeader().setOpaque(false);
        tbFoodGroup.getTableHeader().setReorderingAllowed(false);
        tbFoodGroup.getTableHeader().setForeground(Color.BLACK);
        tbFoodGroup.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbFoodGroup.getTableHeader().setPreferredSize(new Dimension(foodGroupWidth, 30));
        tbFoodGroup.getTableHeader().setBackground(Color.red);
       
        // set column size
        // tbFoodInfoList.getColumnModel().getColumn(3).setPreferredWidth(35);

       
        JScrollPane jsp = new JScrollPane(tbFoodGroup);
        tableLayout.setLayout(new BorderLayout());
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableLayout.add(jsp, BorderLayout.CENTER);
       
        setPreferredSize(new Dimension(foodGroupWidth, foodGroupHeight));     
        add(infoFoodGroupFormLayout);
        add(businessLayout);
        add(tableLayout);
    }
    
    private final int FOOD_GROUP_ID_ROW = 0;
    private final int FOOD_GROUP_NAME_ROW = 1;
    
    private void rowSelectedListener(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tbFoodGroup.getSelectedRow();
            if (selectedRow != -1) {
                tfFoodGroupID.setText(tbFoodGroup.getValueAt(selectedRow, FOOD_GROUP_ID_ROW).toString());
                tfFoodGroupName.setText(tbFoodGroup.getValueAt(selectedRow, FOOD_GROUP_NAME_ROW).toString());
            }
        }
    }
    
    private void btnAddFoodGroupActionPerformed(ActionEvent evt) {  
        restaurantManagementFacade.addFoodGroup(new FoodGroup_DTO(tfFoodGroupName.getText()));
        restaurantManagementFacade.getAllFoodGroups((DefaultTableModel) tbFoodGroup.getModel());
    } 
    
    private void btnUpdateFoodGroupActionPerformed(ActionEvent evt) {  
        try {
            restaurantManagementFacade.updateFoodGroup(new FoodGroup_DTO(Integer.parseInt(tfFoodGroupID.getText()), tfFoodGroupName.getText()));
            restaurantManagementFacade.getAllFoodGroups((DefaultTableModel) tbFoodGroup.getModel());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhóm món ăn cần cập nhật", "Cập nhật nhóm món ăn",
                    JOptionPane.WARNING_MESSAGE);
        }    
    } 
    
    private void btnDeleteFoodGroupActionPerformed(ActionEvent evt) { 
        try {
            restaurantManagementFacade.deleteFoodGroup(Integer.parseInt(tfFoodGroupID.getText()));
            restaurantManagementFacade.getAllFoodGroups((DefaultTableModel) tbFoodGroup.getModel());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhóm món ăn cần xoá", "Xoá nhóm món ăn",
                    JOptionPane.WARNING_MESSAGE);
        }    
        
    } 
    
    private void tfSearchTextChangeActionPerformed(DocumentEvent evt) {
        restaurantManagementFacade.findFoodGroups((DefaultTableModel) tbFoodGroup.getModel(), tfSearch.getText());
    }
    
    // Variables declaration - do not modify 
    private GUI.Component.RoundedTextField tfFoodGroupID;    
    private GUI.Component.RoundedTextField tfFoodGroupName; 
    private GUI.Component.RoundedTextField tfSearch;
    private GUI.Component.RoundedButton btnAddFoodGroup;
    private GUI.Component.RoundedButton btnUpdateFoodGroup;
    private GUI.Component.RoundedButton btnDeleteFoodGroup;
    private javax.swing.JTable tbFoodGroup;
    private javax.swing.table.DefaultTableModel dtmTableModel;
    // nd of variables declaration 
}
