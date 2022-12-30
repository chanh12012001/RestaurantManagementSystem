package GUI.Component.TableManager;

import BUS.DinnerTable_BUS;
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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class TableInfomationLayout extends JPanel {

    private final Dimension dimension;
    String[] properties = {"ID Bàn", "Tên bàn", "Trạng thái"};

    public TableInfomationLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }

    private void initComponents() {
        int tableInfoWidth = dimension.width;
        int tableInfoHeight = dimension.height - dimension.height / 22 - 10;

        tfTableID = new RoundedTextField();
        tfTableName = new RoundedTextField();
        lbTableStatus = new JLabel();
        lbNumberCurrentTable = new JLabel();
        tfSearch = new RoundedTextField();
        btnAddTable = new RoundedButton();
        btnUpdateTable = new RoundedButton();
        btnDeleteTable = new RoundedButton();
        dtmTableModel = new DefaultTableModel(properties, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tbTable = new JTable(dtmTableModel);
        tbTable.getSelectionModel().addListSelectionListener((e) -> {
            rowSelectedListener(e);
        });

        DinnerTable_BUS.getAllTableInfo(dtmTableModel);

        /**
         * info Staff Form Layout
         */
        JPanel tableInfoFormLayout = new JPanel() {
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
        int tableInfoFormLayoutWidth = tableInfoWidth;
        int tableInfoFormLayoutHeight = tableInfoHeight / 3;
        tableInfoFormLayout.setOpaque(false);
        tableInfoFormLayout.setBorder(BorderFactory.createTitledBorder(null, "Thông tin bàn ăn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font("Helvetica Neue", 1, 22), new Color(65, 72, 204))); // NOI18N
        tableInfoFormLayout.setPreferredSize(new Dimension(tableInfoFormLayoutWidth, tableInfoFormLayoutHeight));
        tableInfoFormLayout.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//        // label Mã bàn
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(0, 0, 35, 20);
//        gbc.anchor = GridBagConstraints.EAST;
//
//        JLabel lbTextFoodGroupID = new JLabel("ID bàn");
//        lbTextFoodGroupID.setFont(new Font("sansserif", 0, 15));
//        lbTextFoodGroupID.setForeground(Color.BLACK);
//        tableInfoFormLayout.add(lbTextFoodGroupID, gbc);
//
//        // textfield mã bàn
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(0, 0, 30, 40);
//        gbc.anchor = GridBagConstraints.WEST;
//
//        tfTableID.setBorderColor(new java.awt.Color(204, 204, 204));
//        tfTableID.setBorderWidth(1);
//        tfTableID.setHintText("");
//        tfTableID.setMargin(new java.awt.Insets(2, 10, 2, 6));
//        tfTableID.setRound(20);
//        tfTableID.setPreferredSize(new Dimension((int) (tableInfoWidth / 3.5), 35));
//        tfTableID.setEnabled(false);
//        tableInfoFormLayout.add(tfTableID, gbc);
        // label tên bàn
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 35, 20);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextFoodGroupName = new JLabel("Tên bàn");
        lbTextFoodGroupName.setFont(new Font("sansserif", 0, 15));
        lbTextFoodGroupName.setForeground(Color.BLACK);
        tableInfoFormLayout.add(lbTextFoodGroupName, gbc);

        // textfield tên bàn
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.anchor = GridBagConstraints.WEST;

        tfTableName.setBorderColor(new java.awt.Color(204, 204, 204));
        tfTableName.setBorderWidth(1);
        tfTableName.setHintText("");
        tfTableName.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfTableName.setRound(20);
        tfTableName.setPreferredSize(new Dimension((int) (tableInfoWidth / 3.5), 35));

        tableInfoFormLayout.add(tfTableName, gbc);

        // label text trạng thái
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 35, 20);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextTableStatus = new JLabel("Trạng thái");
        lbTextTableStatus.setFont(new Font("sansserif", 0, 15));
        lbTextTableStatus.setForeground(Color.BLACK);
        tableInfoFormLayout.add(lbTextTableStatus, gbc);

        // trạng thái 
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 35, 20);
        gbc.anchor = GridBagConstraints.WEST;

        lbTableStatus.setText("Trống");
        lbTableStatus.setFont(new Font("sansserif", 1, 15));
        lbTableStatus.setForeground(Color.RED);
        tableInfoFormLayout.add(lbTableStatus, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 20);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextNumTable = new JLabel("Số bàn hiện có");
        lbTextNumTable.setFont(new Font("sansserif", 0, 15));
        lbTextNumTable.setForeground(Color.BLACK);
        tableInfoFormLayout.add(lbTextNumTable, gbc);

        // số bàn hiện có
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;

        final int HEADER_COUNT = 1;
        lbNumberCurrentTable.setText(String.valueOf(tbTable.getRowCount() - HEADER_COUNT));
        lbNumberCurrentTable.setFont(new Font("sansserif", 1, 15));
        lbNumberCurrentTable.setForeground(Color.BLACK);
        tableInfoFormLayout.add(lbNumberCurrentTable, gbc);

        /**
         * Business Layout
         */
        JPanel businessLayout = new JPanel();
        int businessLayoutWidth = tableInfoWidth;
        int businessLayoutHeight = tableInfoHeight / 10;
        businessLayout.setPreferredSize(new Dimension(businessLayoutWidth, businessLayoutHeight));
        businessLayout.setLayout(new GridBagLayout());
        businessLayout.setBackground(new Color(240, 244, 247));
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
        tfSearch.setPreferredSize(new Dimension((int) (tableInfoWidth / 3.5), 35));
        
        tfSearch.getDocument().addDocumentListener((EventTextChange) (DocumentEvent evt) -> {
            tfSearchTextChangeActionPerformed(evt);
        });

        businessLayout.add(tfSearch, gbcBusiness);

        // button bàn mới
        gbcBusiness.gridx = 2;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 15);
        gbcBusiness.anchor = GridBagConstraints.WEST;

        btnAddTable.setForeground(new java.awt.Color(255, 255, 255));
        btnAddTable.setText("THÊM");
        btnAddTable.setColor(new java.awt.Color(6, 208, 214));
        btnAddTable.setColorOver(new java.awt.Color(51, 153, 255));
        btnAddTable.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnAddTable.setRadius(20);
        btnAddTable.setBorderPainted(false);
        btnAddTable.setFocusPainted(false);
        btnAddTable.setPreferredSize(new Dimension(tableInfoWidth / 6, (int) (tableInfoWidth / 15)));
        btnAddTable.setBorderColor(Color.red);
        ImageIcon iconAddPerson = new ImageIcon(getClass().getResource("/assets/ic_add_table.png"));
        Image imgAddPerson = iconAddPerson.getImage();
        Image newimgAddPerson = imgAddPerson.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconAddPerson = new ImageIcon(newimgAddPerson);
        btnAddTable.setIcon(iconAddPerson);
        btnAddTable.addActionListener((ActionEvent evt) -> {
            btnAddTableActionPerformed(evt);
        });

        businessLayout.add(btnAddTable, gbcBusiness);

        // button cập nhật nhân viên
        gbcBusiness.gridx = 3;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 15);
        gbcBusiness.anchor = GridBagConstraints.WEST;

        btnUpdateTable.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateTable.setText("CẬP NHẬT");
        btnUpdateTable.setColor(new java.awt.Color(235, 147, 33));
        btnUpdateTable.setColorOver(new java.awt.Color(51, 153, 255));
        btnUpdateTable.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnUpdateTable.setRadius(20);
        btnUpdateTable.setBorderPainted(false);
        btnUpdateTable.setFocusPainted(false);
        btnUpdateTable.setPreferredSize(new Dimension(tableInfoWidth / 6, (int) (tableInfoWidth / 15)));
        btnUpdateTable.setBorderColor(Color.red);
        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/assets/ic_update.png"));
        Image imgUpdate = iconUpdate.getImage();
        Image newimgUpdate = imgUpdate.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconUpdate = new ImageIcon(newimgUpdate);
        btnUpdateTable.setIcon(iconUpdate);
        btnUpdateTable.addActionListener((ActionEvent evt) -> {
            btnUpdateTableActionPerformed(evt);
        });

        businessLayout.add(btnUpdateTable, gbcBusiness);

        // button xoá nhân viên
        gbcBusiness.gridx = 4;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 0);
        gbcBusiness.anchor = GridBagConstraints.WEST;

        btnDeleteTable.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteTable.setText("XOÁ");
        btnDeleteTable.setColor(new java.awt.Color(227, 14, 42));
        btnDeleteTable.setColorOver(new java.awt.Color(51, 153, 255));
        btnDeleteTable.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnDeleteTable.setRadius(20);
        btnDeleteTable.setBorderPainted(false);
        btnDeleteTable.setFocusPainted(false);
        btnDeleteTable.setPreferredSize(new Dimension(tableInfoWidth / 6, (int) (tableInfoWidth / 15)));
        btnDeleteTable.setBorderColor(Color.red);
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/assets/ic_delete.png"));
        Image imgDelete = iconDelete.getImage();
        Image newimgDelete = imgDelete.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconDelete = new ImageIcon(newimgDelete);
        btnDeleteTable.setIcon(iconDelete);
        btnDeleteTable.addActionListener((ActionEvent evt) -> {
            btnDeleteTableGroupActionPerformed(evt);
        });

        businessLayout.add(btnDeleteTable, gbcBusiness);

        /**
         * Table Staff Layout
         */
        JPanel tableLayout = new JPanel();
        tableLayout.setPreferredSize(new Dimension(tableInfoWidth, (int) (tableInfoHeight - tableInfoFormLayoutHeight - businessLayoutHeight - 10)));
        tableLayout.setLayout(new BorderLayout());

        tbTable.setFocusable(false);
        tbTable.setIntercellSpacing(new Dimension(0, 0));
        tbTable.setRowHeight(33);
        tbTable.setSelectionBackground(Color.lightGray);
        tbTable.setShowVerticalLines(true);
        tbTable.setFont(new Font("Segoe UI", 0, 13));

        tbTable.getTableHeader().setOpaque(false);
        tbTable.getTableHeader().setReorderingAllowed(false);
        tbTable.getTableHeader().setForeground(Color.BLACK);
        tbTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbTable.getTableHeader().setPreferredSize(new Dimension(tableInfoWidth, 30));
        tbTable.getTableHeader().setBackground(Color.red);

        // set column size
        // tbFoodInfoList.getColumnModel().getColumn(3).setPreferredWidth(35);
        JScrollPane jsp = new JScrollPane(tbTable);
        tableLayout.setLayout(new BorderLayout());
        JScrollBar bar = jsp.getVerticalScrollBar();
        bar.setPreferredSize(new Dimension(10, 0));
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        tableLayout.add(jsp, BorderLayout.CENTER);

        setPreferredSize(new Dimension(tableInfoWidth, tableInfoHeight));
        add(tableInfoFormLayout);
        add(businessLayout);
        add(tableLayout);
    }

    private final int DINNER_TABLE_ID_ROW = 0;
    private final int DINNER_TABLE_NAME_ROW = 1;
    private final int DINNER_TABLE_STATUS_ROW = 2;

    private void rowSelectedListener(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tbTable.getSelectedRow();
            if (selectedRow != -1) {
                tfTableID.setText(tbTable.getValueAt(selectedRow, DINNER_TABLE_ID_ROW).toString());
                tfTableName.setText(tbTable.getValueAt(selectedRow, DINNER_TABLE_NAME_ROW).toString());
                lbTableStatus.setText(tbTable.getValueAt(selectedRow, DINNER_TABLE_STATUS_ROW).toString());
            }
        }
    }

    private void btnAddTableActionPerformed(ActionEvent evt) {
        DinnerTable_BUS.addTableInfo(tfTableName.getText());
        DinnerTable_BUS.getAllTableInfo((DefaultTableModel) tbTable.getModel());
    }

    private void btnUpdateTableActionPerformed(ActionEvent evt) {
        DinnerTable_BUS.updateTableInfo(Integer.parseInt(tfTableID.getText()), tfTableName.getText());
        DinnerTable_BUS.getAllTableInfo((DefaultTableModel) tbTable.getModel());
    }

    private void btnDeleteTableGroupActionPerformed(ActionEvent evt) {
        DinnerTable_BUS.deleteTableInfo(tfTableID.getText());
        DinnerTable_BUS.getAllTableInfo((DefaultTableModel) tbTable.getModel());
    }
    
    private void tfSearchTextChangeActionPerformed(DocumentEvent evt) {
        DinnerTable_BUS.findTableInfos((DefaultTableModel) tbTable.getModel(), tfSearch.getText());
    }

    // Variables declaration - do not modify 
    private GUI.Component.RoundedTextField tfTableID;
    private GUI.Component.RoundedTextField tfTableName;
    private javax.swing.JLabel lbTableStatus;
    private javax.swing.JLabel lbNumberCurrentTable;
    private GUI.Component.RoundedTextField tfSearch;
    private GUI.Component.RoundedButton btnAddTable;
    private GUI.Component.RoundedButton btnUpdateTable;
    private GUI.Component.RoundedButton btnDeleteTable;
    private javax.swing.JTable tbTable;
    private javax.swing.table.DefaultTableModel dtmTableModel;
    // nd of variables declaration 
}
