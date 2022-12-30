package GUI.Component.StaffManager;

import BUS.Account_BUS;
import BUS.Staff_BUS;
import DTO.Account_DTO;
import GUI.Component.RoundedButton;
import GUI.Component.RoundedTextField;
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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class AccountAndAuthorizationLayout extends JPanel{
    private final Dimension dimension;
    
    String[] properties = { "Tài khoản ", "Mật khẩu", "Quyền đăng nhập"};


    public AccountAndAuthorizationLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }
    
    private void initComponents() {
       int width = dimension.width;
       int height = dimension.height - dimension.height / 22 - 10;
           
       
       tfUserName = new RoundedTextField();
       tfPassword = new RoundedTextField();
       tfSearch = new RoundedTextField();
       btnUpdateAccount = new RoundedButton();
       dtmTableModel = new DefaultTableModel(properties, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tbAccount = new JTable(dtmTableModel);
        tbAccount.getSelectionModel().addListSelectionListener((e) -> {
            rowSelectedListener(e);
        });

        Account_BUS.getAllAccount(dtmTableModel);
        /**
         * info Staff Form Layout
         */
        JPanel infoAccountFormLayout = new JPanel() {
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
        infoAccountFormLayout.setOpaque(false);
        infoAccountFormLayout.setBorder(BorderFactory.createTitledBorder(null, "Thông tin tài khoản", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font("Helvetica Neue", 1, 22), new Color(65, 72, 204))); // NOI18N
        infoAccountFormLayout.setPreferredSize(new Dimension(width, height / 3));
        infoAccountFormLayout.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // label tên tài khoản
        gbc.gridx = 0;
        gbc.gridy = 0;      
        gbc.insets = new Insets(0, 0, 35, 20);
        gbc.anchor = GridBagConstraints.EAST;
        
        JLabel lbTextFoodGroupID = new JLabel("Tài khoản");
        lbTextFoodGroupID.setFont(new Font("sansserif", 0, 15));
        lbTextFoodGroupID.setForeground(Color.BLACK);
        infoAccountFormLayout.add(lbTextFoodGroupID, gbc);
              
        // textfield tên tài khoản
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.anchor = GridBagConstraints.WEST;
        
        tfUserName.setBorderColor(new java.awt.Color(204, 204, 204));
        tfUserName.setBorderWidth(1);
        tfUserName.setHintText("");
        tfUserName.setEditable(false);
        tfUserName.setBackground(new Color(235, 238, 242));
        tfUserName.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfUserName.setRound(20);
        tfUserName.setPreferredSize(new Dimension((int) (width / 3.5) , 35));
        
        infoAccountFormLayout.add(tfUserName, gbc);
        
        // label mật khẩu
        gbc.gridx = 0;
        gbc.gridy = 1;      
        gbc.insets = new Insets(0, 0, 35, 20);
        gbc.anchor = GridBagConstraints.EAST;
        
        JLabel lbTextFoodGroupName = new JLabel("Mật khẩu");
        lbTextFoodGroupName.setFont(new Font("sansserif", 0, 15));
        lbTextFoodGroupName.setForeground(Color.BLACK);
        infoAccountFormLayout.add(lbTextFoodGroupName, gbc);
              
        // textfield mật khẩu
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.anchor = GridBagConstraints.WEST;
        
        tfPassword.setBorderColor(new java.awt.Color(204, 204, 204));
        tfPassword.setBorderWidth(1);
        tfPassword.setHintText("");
        tfPassword.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfPassword.setRound(20);
        tfPassword.setPreferredSize(new Dimension((int) (width / 3.5) , 35));
        
        infoAccountFormLayout.add(tfPassword, gbc);
                        
        /**
         * Business Layout
         */
        JPanel businessLayout = new JPanel();
        businessLayout.setPreferredSize(new Dimension(width, (int) (height / 10)));
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
        tfSearch.setHintText("Nhập tên tài khoản cần tìm ...");
        tfSearch.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfSearch.setRound(20);
        tfSearch.setPreferredSize(new Dimension((int) (width / 3.5) , 35));
        
        businessLayout.add(tfSearch, gbcBusiness);
        
        
        // button thêm nhân viên
        gbcBusiness.gridx = 2;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 15);
        gbcBusiness.anchor = GridBagConstraints.WEST;
        
        btnUpdateAccount.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateAccount.setText("CẬP NHẬT");
        btnUpdateAccount.setColor(new java.awt.Color(235, 147, 33));
        btnUpdateAccount.setColorOver(new java.awt.Color(51, 153, 255));
        btnUpdateAccount.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnUpdateAccount.setRadius(20);
        btnUpdateAccount.setBorderPainted(false);
        btnUpdateAccount.setFocusPainted(false);     
        btnUpdateAccount.setPreferredSize(new Dimension(width / 6, (int) (width / 15)));
        btnUpdateAccount.setBorderColor(Color.red);
        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/assets/ic_update.png"));
        Image imgUpdate = iconUpdate.getImage();
        Image newimgUpdate = imgUpdate.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconUpdate = new ImageIcon(newimgUpdate); 
        btnUpdateAccount.setIcon(iconUpdate);
        btnUpdateAccount.addActionListener((ActionEvent evt) -> {
            btnUpdateAccountActionPerformed(evt);
        });
        
        businessLayout.add(btnUpdateAccount, gbcBusiness);
        
        
        /**
         * Table Staff Layout
         */
        JPanel tableLayout = new JPanel();
        tableLayout.setPreferredSize(new Dimension(width, (int) (height - height/3 - height / 10 - 10)));
        tableLayout.setLayout(new BorderLayout());
         
//        tbFoodInfoList.setPreferredSize(new Dimension(bodyWidth, (int) (bodyHeight - bodyHeight / 2 - bodyHeight / 10)));
        tbAccount.setFocusable(false);
        tbAccount.setIntercellSpacing(new Dimension(0, 0));
        tbAccount.setRowHeight(33);
        tbAccount.setSelectionBackground(Color.lightGray);
        tbAccount.setShowVerticalLines(true);
        tbAccount.setFont(new Font("Segoe UI", 0, 13));

        
        tbAccount.getTableHeader().setOpaque(false);
        tbAccount.getTableHeader().setReorderingAllowed(false);
        tbAccount.getTableHeader().setForeground(Color.BLACK);
        tbAccount.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbAccount.getTableHeader().setPreferredSize(new Dimension(width, 30));
        tbAccount.getTableHeader().setBackground(Color.red);
       
        // set column size
        // tbFoodInfoList.getColumnModel().getColumn(3).setPreferredWidth(35);

       
        JScrollPane jsp = new JScrollPane(tbAccount);
        tableLayout.setLayout(new BorderLayout());
        JScrollBar bar = jsp.getVerticalScrollBar();
        bar.setPreferredSize(new Dimension(7, 0));
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        tableLayout.add(jsp, BorderLayout.CENTER);
       
        setPreferredSize(new Dimension(width, height));     
        add(infoAccountFormLayout);
        add(businessLayout);
        add(tableLayout);
    }
    
    private final int ACCOUNT_USERNAME_ROW = 0;
    private final int ACCOUNT_PASSWORD_ROW = 1;
    
    private void rowSelectedListener(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tbAccount.getSelectedRow();
            if (selectedRow != -1) {
                tfUserName.setText(tbAccount.getValueAt(selectedRow, ACCOUNT_USERNAME_ROW).toString());
                tfPassword.setText(tbAccount.getValueAt(selectedRow, ACCOUNT_PASSWORD_ROW).toString());         
            }
        }
    }
    
    private void btnUpdateAccountActionPerformed(ActionEvent evt) {
        Account_BUS.update(new Account_DTO(tfUserName.getText(), tfPassword.getText(), ""));
        Account_BUS.getAllAccount((DefaultTableModel) tbAccount.getModel());
    } 
   
    // Variables declaration - do not modify 
    private GUI.Component.RoundedTextField tfUserName;    
    private GUI.Component.RoundedTextField tfPassword; 
    private GUI.Component.RoundedTextField tfSearch;
    private GUI.Component.RoundedButton btnUpdateAccount;
    private javax.swing.JTable tbAccount;
    private javax.swing.table.DefaultTableModel dtmTableModel;

    // nd of variables declaration 
}
