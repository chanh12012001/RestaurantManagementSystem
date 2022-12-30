package GUI.Component.TableManager;

import BUS.DinnerTable_BUS;
import BUS.TableItemUI;
import BUS.BillDetail_BUS;
import BUS.FoodGroup_BUS;
import BUS.Food_BUS;
import BUS.OrderBill_BUS;
import DTO.BillDetail_DTO;
import DTO.DinnerTable_DTO;
import DTO.FoodGroup_DTO;
import DTO.Food_DTO;
import DTO.OrderBill_DTO;
import DTO.OrderDetail_DTO;
import DTO.TableModelItemUI;
import GUI.Component.RoundedButton;
import GUI.Component.TableManager.FoodCard.FoodCard;
import Interface.EventTextChange;
import Utils.DateUtils;
import Utils.ImageUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;

public class TableMapLayout extends JPanel {

    private final Dimension dimension;
    public int indexTable = -1;

    public TableMapLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
    }

    private void initComponents() {
        int width = dimension.width;
        int height = dimension.height;

        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setPreferredSize(new Dimension(width, height - height / 22 - 10));

        /**
         *
         *
         * build grid view TableItem layout UI include table map layout and food
         * list layout
         *
         *
         */
        // init component
        gridviewTableItem = new JPanel();
        btnOpenTable = new RoundedButton();
        btnBack = new RoundedButton();
        lbDateOpenTable = new JLabel();
        lbTableName = new JLabel("Chưa chọn");
        lbTableStatus = new JLabel("Không có");
        dateUtils = new DateUtils();
        List<TableModelItemUI> tables = TableItemUI.getAllTableDinnerUI();

        int widthGridviewTableItem = width - width / 3 - 5;
        int heightGridviewTableItem = height - height / 22 - 10;
        gridviewTableItem.setLayout(new BorderLayout());
        gridviewTableItem.setPreferredSize(new Dimension(widthGridviewTableItem, heightGridviewTableItem));

        /**
         * build header layout UI
         */
        int heightHeaderLayout = (int) (height - height / 1.4 - height / 22 - 20);
        JPanel headerLayout = new JPanel(new BorderLayout());
        headerLayout.setPreferredSize(new Dimension(widthGridviewTableItem, heightHeaderLayout));

        // left layout include button open table and button move table
        JPanel leftLayout = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        leftLayout.setLayout(gridBagLayout);
        leftLayout.setPreferredSize(new Dimension(widthGridviewTableItem / 2, heightHeaderLayout));

        // build button open table UI
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnOpenTable.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenTable.setText("Mở bàn");
        btnOpenTable.setColor(new java.awt.Color(71, 209, 71));
        btnOpenTable.setColorOver(new java.awt.Color(51, 153, 255));
        btnOpenTable.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        btnOpenTable.setRadius(10);
        btnOpenTable.setBorderPainted(false);
        btnOpenTable.setFocusPainted(false);
        btnOpenTable.setPreferredSize(new Dimension(widthGridviewTableItem / 4, height / 22));
        btnOpenTable.setBorderColor(Color.red);
        btnOpenTable.addActionListener((ActionEvent evt) -> {
            btnOpenTableActionPerformed(evt);
        });
        leftLayout.add(btnOpenTable, gbc);

        // build button back from choose food to table map
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 1;

        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Quay lại");
        btnBack.setColor(Color.GRAY);
        btnBack.setColorOver(Color.GRAY);
        btnBack.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        btnBack.setRadius(10);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setPreferredSize(new Dimension(widthGridviewTableItem / 4, height / 24));
        btnBack.setBorderColor(Color.red);
        btnBack.addActionListener((ActionEvent evt) -> {
            btnBackActionPerformed(evt);
        });

        btnBack.setVisible(false);
        leftLayout.add(btnBack, gbc);

        //right layout include table info
        JPanel rightLayout = new JPanel();
        rightLayout.setPreferredSize(new Dimension(widthGridviewTableItem / 2, heightHeaderLayout));

        rightLayout.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(5, 5, 5, 5);
        gbc1.anchor = GridBagConstraints.EAST;

        // lable text
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        JLabel lbTextDate = new JLabel("Ngày:");
        lbTextDate.setFont(new Font("sansserif", 1, 15));
        lbTextDate.setForeground(Color.BLACK);
        rightLayout.add(lbTextDate, gbc1);

        // build lable UI display date now
        gbc1.gridx = 1;
        gbc1.gridy = 0;
        gbc1.anchor = GridBagConstraints.WEST;
        lbDateOpenTable.setText(dateUtils.getDateNow());
        rightLayout.add(lbDateOpenTable, gbc1);

        // lable text
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        gbc1.anchor = GridBagConstraints.EAST;
        JLabel lbTextTable = new JLabel("Bàn:");
        lbTextTable.setFont(new Font("sansserif", 1, 15));
        lbTextTable.setForeground(Color.BLACK);
        rightLayout.add(lbTextTable, gbc1);

        // build lable UI display table name
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        gbc1.anchor = GridBagConstraints.WEST;
        lbTableName.setFont(new Font("sansserif", 1, 15));
        lbTableName.setForeground(Color.RED);
        rightLayout.add(lbTableName, gbc1);

        // lable text
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        gbc1.anchor = GridBagConstraints.EAST;
        JLabel lbTextTableStatus = new JLabel("Trạng thái:");
        lbTextTableStatus.setFont(new Font("sansserif", 1, 15));
        lbTextTableStatus.setForeground(Color.BLACK);
        rightLayout.add(lbTextTableStatus, gbc1);

        // build lable UI display table status
        gbc1.gridx = 1;
        gbc1.gridy = 2;
        gbc1.anchor = GridBagConstraints.WEST;
        lbTableStatus.setFont(new Font("sansserif", 1, 15));
        lbTableStatus.setForeground(Color.RED);
        rightLayout.add(lbTableStatus, gbc1);

        headerLayout.add(leftLayout, BorderLayout.WEST);
        headerLayout.add(rightLayout, BorderLayout.EAST);

        /**
         * build body layout include table map and food list layout
         */
        // build body layout is table map
        bodyLayout = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#d7d2cc"), 0, getHeight(), Color.decode("#d7d2cc"));
                g2.setPaint(gradientPaint);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
                super.paintComponent(graphics);
            }
        };

        bodyLayout.setOpaque(false);

        // add each table Item UI to the map layout
        loadTable();

        bodyLayout.setPreferredSize(new Dimension(widthGridviewTableItem, getNumberRowTableMap(tables) * (int) (widthGridviewTableItem / 4.7)));

        scrollPane = new JScrollPane(bodyLayout, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(widthGridviewTableItem, (int) (height / 1.4)));
        JScrollBar bar = scrollPane.getVerticalScrollBar();
        bar.setPreferredSize(new Dimension(10, 0));

        // build food list layout UI
        foodChooserLayout = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#d7d2cc"), 0, getHeight(), Color.decode("#d7d2cc"));
                g2.setPaint(gradientPaint);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
                super.paintComponent(graphics);
            }
        };

        foodChooserLayout.setOpaque(false);
        foodChooserLayout.setPreferredSize(new Dimension(widthGridviewTableItem, (int) (height / 1.4)));

        // build filter Food Group Layout UI
        JPanel filterFoodGroupLayout = new JPanel();
        filterFoodGroupLayout.setBackground(new Color(235, 240, 236));

        loadFoodGroup(filterFoodGroupLayout);

        JScrollPane scrollPaneFilterFoodGroup = new JScrollPane(filterFoodGroupLayout, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneFilterFoodGroup.setPreferredSize(new Dimension(widthGridviewTableItem, (int) (height / 14)));
        JScrollBar barFilterFoodGroup = scrollPaneFilterFoodGroup.getHorizontalScrollBar();
        barFilterFoodGroup.setPreferredSize(new Dimension(0, 5));

        // food list card
        foodChooserLayout.add(scrollPaneFilterFoodGroup);

        loadFoodCard("Tất cả");

        foodChooserLayout.setVisible(false);
        gridviewTableItem.add(headerLayout, BorderLayout.NORTH);
        gridviewTableItem.add(scrollPane, BorderLayout.CENTER);
        gridviewTableItem.add(foodChooserLayout, BorderLayout.SOUTH);

        /**
         *
         *
         * build layout bill By Table UI
         *
         *
         */
        billByTable = new JPanel();
        int widthBillByTable = width / 3;
        int heighTBillByTable = height - height / 22 - 10;
        String[][] billDetailItem = {{"N/A", "N/A", "N/A", "N/A"},};
        String[] properties = {"Tên món", "SL", "Đơn giá", "Thành tiền"};

        billByTable.setLayout(new BoxLayout(billByTable, BoxLayout.PAGE_AXIS));
        billByTable.setPreferredSize(new Dimension(widthBillByTable, heighTBillByTable));

        // init component
        DefaultTableModel model = new DefaultTableModel(billDetailItem, properties) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tbBillDetails = new JTable(model);
        lbTitle = new JLabel("HOÁ ĐƠN THEO BÀN");
        billDetailsLayout = new JPanel();
        adjustNumberOfFoodsLayout = new JPanel();
        btnDecreaseAmountFood = new JButton();
        btnIncreaseAmountFood = new JButton();
        btnDeleteFood = new JButton();
        totalPayableLayout = new JPanel();
        JLabel lbTextProvisionalAmount = new JLabel("Tổng tiền");
        tfProvisionalAmount = new JTextField();
        JLabel lbTextUnit1 = new JLabel("Đồng");
        JLabel lbTextUnit3 = new JLabel("%");

        JLabel lbTextPromotion2 = new JLabel("Khuyến mãi (%)");
        tfPromotionPercentUnit = new JTextField();

        tfPromotionPercentUnit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
//            String value = tfPrice.getText();
//            int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    tfPromotionPercentUnit.setEditable(true);
                    if (ke.getKeyCode() != KeyEvent.VK_BACK_SPACE
                            && Double.parseDouble(tfPromotionPercentUnit.getText() + ke.getKeyChar()) > 100) {
                        tfPromotionPercentUnit.setText("100");
                        tfPromotionPercentUnit.setEditable(false);
                    }
                } else {
                    tfPromotionPercentUnit.setEditable(false);
                }
            }
        });

        JLabel lbTextTotalPayable = new JLabel("Thành tiền:");
        lbTotalPayable = new JLabel("0.000");
        JLabel lbTextTotalPayableUnit = new JLabel("Đồng");
        paymentLayout = new JPanel();
        btnPayment = new RoundedButton();

        // title container 
        titleLayout = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#a8c0ff"), 0, getHeight(), Color.decode("#3f2b96"));
                g2.setPaint(gradientPaint);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(graphics);
            }
        ;
        };
       
       titleLayout.setLayout(new BorderLayout());
        titleLayout.setOpaque(false);
        titleLayout.setPreferredSize(new Dimension(widthBillByTable, (int) (0.15 * height)));

        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setFont(new Font("sansserif", 1, 16));
        lbTitle.setForeground(Color.WHITE);

        titleLayout.add(lbTitle, BorderLayout.CENTER);

        // table bill by dinner table layout
        tbBillDetails.setPreferredSize(new Dimension(widthBillByTable / 2, height - height / 8));
        tbBillDetails.setFocusable(false);
        tbBillDetails.setIntercellSpacing(new Dimension(0, 0));
        tbBillDetails.setRowHeight(33);
        tbBillDetails.setSelectionBackground(Color.lightGray);
        tbBillDetails.setShowVerticalLines(false);
        tbBillDetails.setFont(new Font("Segoe UI", 0, 12));

        tbBillDetails.getTableHeader().setOpaque(false);
        tbBillDetails.getTableHeader().setReorderingAllowed(false);
        tbBillDetails.getTableHeader().setForeground(Color.BLACK);
        tbBillDetails.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbBillDetails.getTableHeader().setPreferredSize(new Dimension(widthBillByTable, 30));
        tbBillDetails.getTableHeader().setBackground(Color.red);

        // set column size
        tbBillDetails.getColumnModel().getColumn(1).setPreferredWidth(30);

        // scroll panel
        JScrollPane jsp = new JScrollPane(tbBillDetails);
        billDetailsLayout.setLayout(new BorderLayout());
        billDetailsLayout.setPreferredSize(new Dimension(widthBillByTable, (int) (0.4 * height)));
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        billDetailsLayout.add(jsp, BorderLayout.CENTER);

        /**
         * set layout adjust the number of foods
         */
        //set properties for button decrease amount food
        btnDecreaseAmountFood.setBackground(new Color(0, 0, 0, 0));
        btnDecreaseAmountFood.setBorderPainted(false);
        btnDecreaseAmountFood.setFocusable(false);
        ImageIcon iconMinus = new ImageIcon(getClass().getResource("/assets/ic_minus.png"));
        Image imgMinus = iconMinus.getImage();
        Image newimgMinus = imgMinus.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconMinus = new ImageIcon(newimgMinus);
        btnDecreaseAmountFood.setIcon(iconMinus);
        btnDecreaseAmountFood.setPreferredSize(new Dimension(30, 30));
        btnDecreaseAmountFood.addActionListener((e) -> {
            btnDecreaseFoodAmountPerformed(e);
        });

        //set properties for button increase amount food
        btnIncreaseAmountFood.setBackground(new Color(0, 0, 0, 0));
        btnIncreaseAmountFood.setBorderPainted(false);
        btnIncreaseAmountFood.setFocusable(false);
        ImageIcon iconPlus = new ImageIcon(getClass().getResource("/assets/ic_plus.png"));
        Image imgPlus = iconPlus.getImage();
        Image newimgPlus = imgPlus.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconPlus = new ImageIcon(newimgPlus);
        btnIncreaseAmountFood.setIcon(iconPlus);
        btnIncreaseAmountFood.setPreferredSize(new Dimension(30, 30));
        btnIncreaseAmountFood.addActionListener((e) -> {
            btnIncreaseFoodAmountPerformed(e);
        });

        //set properties for button delete food
        btnDeleteFood.setBackground(new Color(0, 0, 0, 0));
        btnDeleteFood.setBorderPainted(false);
        btnDeleteFood.setFocusable(false);
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/assets/ic_delete.png"));
        Image imgDetete = iconDelete.getImage();
        Image newimgDelete = imgDetete.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconDelete = new ImageIcon(newimgDelete);
        btnDeleteFood.setIcon(iconDelete);
        btnDeleteFood.setPreferredSize(new Dimension(30, 30));
        btnDeleteFood.addActionListener((e) -> {
            btnRemoveFoodPerfomed(e);
        });

        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        adjustNumberOfFoodsLayout.setLayout(flowLayout);
        adjustNumberOfFoodsLayout.setPreferredSize(new Dimension(width, (int) (0.06 * height)));
        adjustNumberOfFoodsLayout.add(btnIncreaseAmountFood);
        adjustNumberOfFoodsLayout.add(btnDecreaseAmountFood);
        adjustNumberOfFoodsLayout.add(btnDeleteFood);

        /**
         * set layout provisional Amount
         */
        provisionalAmountLayout = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2 = (Graphics2D) graphics;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#485563"), 0, getHeight(), Color.decode("#29323c"));
                g2.setPaint(gradientPaint);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
                super.paintComponent(graphics);
            }
        ;
        }; 
       
       // row 1 of provisional Amount Layout
       lbTextProvisionalAmount.setFont(new Font("Segoe UI", 0, 14));
        lbTextProvisionalAmount.setForeground(Color.WHITE);
        lbTextProvisionalAmount.setPreferredSize(new Dimension((int) (0.5 * widthBillByTable), 20));

        tfProvisionalAmount.setPreferredSize(new Dimension((int) (0.3 * widthBillByTable), 20));
        tfProvisionalAmount.setEditable(false);

        lbTextUnit1.setFont(new Font("Segoe UI", 0, 14));
        lbTextUnit1.setForeground(Color.WHITE);
        lbTextUnit1.setPreferredSize(new Dimension((int) (0.2 * widthBillByTable), 20));

        JPanel jPanel1 = new JPanel();
        BoxLayout boxLayout1 = new BoxLayout(jPanel1, BoxLayout.X_AXIS);
        jPanel1.setLayout(boxLayout1);
        jPanel1.setBackground(new Color(51, 51, 51));

        jPanel1.add(lbTextProvisionalAmount);
        jPanel1.add(tfProvisionalAmount);

        jPanel1.add(lbTextUnit1);

        JPanel jPanel2 = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(jPanel2, BoxLayout.X_AXIS);
        jPanel2.setLayout(boxLayout2);
        jPanel2.setBackground(new Color(51, 51, 51));

        // row 3 of provisional Amount Layout
        lbTextPromotion2.setFont(new Font("Segoe UI", 0, 14));
        lbTextPromotion2.setForeground(Color.WHITE);
        lbTextPromotion2.setPreferredSize(new Dimension((int) (0.5 * widthBillByTable), 20));

        lbTextUnit3.setFont(new Font("Segoe UI", 0, 14));
        lbTextUnit3.setForeground(Color.WHITE);
        lbTextUnit3.setPreferredSize(new Dimension((int) (0.2 * widthBillByTable), 20));

        tfPromotionPercentUnit.setPreferredSize(new Dimension((int) (0.3 * widthBillByTable), 20));
        tfPromotionPercentUnit.getDocument().addDocumentListener((EventTextChange) (DocumentEvent evt) -> {
            tfPromotionPercentTextChangeActionPerformed(evt);
        });

        JPanel jPanel3 = new JPanel();
        BoxLayout boxLayout3 = new BoxLayout(jPanel3, BoxLayout.X_AXIS);
        jPanel3.setLayout(boxLayout3);
        jPanel3.setBackground(new Color(51, 51, 51));

        jPanel3.add(lbTextPromotion2);
        jPanel3.add(tfPromotionPercentUnit);
        jPanel3.add(lbTextUnit3);

        provisionalAmountLayout.add(jPanel1);
        provisionalAmountLayout.add(jPanel2);
        provisionalAmountLayout.add(jPanel3);

        provisionalAmountLayout.setLayout(new BoxLayout(provisionalAmountLayout, BoxLayout.Y_AXIS));
        provisionalAmountLayout.setPreferredSize(new Dimension(widthBillByTable, (int) (0.15 * height)));
        provisionalAmountLayout.setOpaque(false);

        /**
         * layout provisional Amount
         */
        totalPayableLayout.setLayout(new BorderLayout());

        lbTextTotalPayable.setHorizontalAlignment(JLabel.CENTER);
        lbTextTotalPayable.setFont(new Font("sansserif", 1, 18));
        lbTextTotalPayable.setForeground(Color.WHITE);
        lbTextTotalPayable.setPreferredSize(new Dimension((int) (0.45 * widthBillByTable), 10));

        lbTotalPayable.setHorizontalAlignment(JLabel.CENTER);
        lbTotalPayable.setFont(new Font("sansserif", 1, 14));
        lbTotalPayable.setForeground(Color.BLUE);
        lbTotalPayable.setPreferredSize(new Dimension((int) (0.3 * widthBillByTable), 10));

        lbTextTotalPayableUnit.setHorizontalAlignment(JLabel.CENTER);
        lbTextTotalPayableUnit.setFont(new Font("sansserif", 1, 14));
        lbTextTotalPayableUnit.setForeground(Color.BLUE);
        lbTextTotalPayableUnit.setPreferredSize(new Dimension((int) (0.25 * widthBillByTable), 10));

        totalPayableLayout.add(lbTextTotalPayable, BorderLayout.WEST);
        totalPayableLayout.add(lbTotalPayable, BorderLayout.CENTER);
        totalPayableLayout.add(lbTextTotalPayableUnit, BorderLayout.EAST);

        totalPayableLayout.setPreferredSize(new Dimension(widthBillByTable, (int) (0.12 * height)));
        totalPayableLayout.setBackground(Color.GRAY);

        /**
         * set payment Layout
         */
        // set properties for btnTableManagerTab
        btnPayment.setForeground(new java.awt.Color(255, 255, 255));
        btnPayment.setText("THANH TOÁN");
        btnPayment.setColor(new Color(0, 204, 204));
        btnPayment.setColorOver(new java.awt.Color(0, 153, 153));
        btnPayment.setFont(new java.awt.Font("Helvetica Neue", Font.BOLD, 20)); // NOI18N
        btnPayment.setRadius(10);
        btnPayment.setBorderPainted(false);
        btnPayment.setFocusPainted(false);
        btnPayment.setPreferredSize(new Dimension(widthBillByTable, (int) (0.08 * height)));
        btnPayment.setBorderColor(Color.red);
        btnPayment.addActionListener((ActionEvent evt) -> {
            btnPaymentActionPerformed(evt);
        });

        paymentLayout.setPreferredSize(new Dimension(widthBillByTable, (int) (0.09 * height)));
        paymentLayout.add(btnPayment);

        billByTable.add(titleLayout);
        billByTable.add(billDetailsLayout);
        billByTable.add(adjustNumberOfFoodsLayout);
        billByTable.add(provisionalAmountLayout);
        billByTable.add(totalPayableLayout);
        billByTable.add(paymentLayout);

        add(billByTable, BorderLayout.EAST);
        add(gridviewTableItem, BorderLayout.WEST);
    }

    private void loadTable() {
        List<TableModelItemUI> tables = TableItemUI.getAllTableDinnerUI();
        int widthGridviewTableItem = dimension.width - dimension.width / 3 - 5;
        bodyLayout.removeAll();
        for (int i = 0; i <= tables.size() - 1; i++) {
            final int indexTemp = i;
            TableItem tableItem = new TableItem(new ImageIcon(getClass().getResource("Trống".equals(tables.get(i).getStatus()) ? "/assets/ic_tableware.png" : "/assets/ic_tableware_selected.png")), tables.get(i).getName());
            tableItem.setPreferredSize(new Dimension((int) (widthGridviewTableItem / 5.4), (int) (widthGridviewTableItem / 5)));
            tableItem.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    indexTable = tables.get(indexTemp).getiD();
                    tableItemMouseClicked(evt);
                }
            });
            bodyLayout.add(tableItem);
        }
    }

    private void loadFoodGroup(JPanel filterFoodGroupLayout) {
        ArrayList<FoodGroup_DTO> foodGroups = FoodGroup_BUS.getAllFoodGroups();

        //Set All button
        RoundedButton btnButtonAll = btnFoodGroupItem("Tất cả");
        btnButtonAll.setRadius(5);
        btnButtonAll.setColor(new Color(109, 213, 247));
        btnButtonAll.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        btnButtonAll.setForeground(Color.WHITE);
        btnButtonAll.addActionListener((ActionEvent evt) -> {

            btnClickFoodGroup(evt, btnButtonAll.getText());
        });
        filterFoodGroupLayout.add(btnButtonAll);

        for (int i = 0; i < foodGroups.size(); i++) {
            RoundedButton btnButton = btnFoodGroupItem(foodGroups.get(i).getName());
            btnButton.setRadius(5);
            btnButton.setColor(new Color(109, 213, 247));
            btnButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
            btnButton.setForeground(Color.WHITE);
            btnButton.addActionListener((ActionEvent evt) -> {
                btnClickFoodGroup(evt, btnButton.getText());
            });
            filterFoodGroupLayout.add(btnButton);
        }
    }

    private void loadFoodCard(String name) {
        int widthGridviewTableItem = dimension.width - dimension.width / 3 - 5;

        int foodCardListLayoutHeigth = (int) (dimension.height / 1.4 - dimension.height / 14);
        JPanel foodCardListLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));
        foodCardListLayout.setBackground(new Color(240, 244, 245));

        JScrollPane scrollPaneFoodCardList = new JScrollPane(foodCardListLayout, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneFoodCardList.setPreferredSize(new Dimension(widthGridviewTableItem, (int) (dimension.height / 1.4 - dimension.width / 14)));
        JScrollBar barFoodCardList = scrollPaneFoodCardList.getVerticalScrollBar();
        barFoodCardList.setPreferredSize(new Dimension(7, 0));

        ArrayList<Food_DTO> foods;

        if (name.equals("Tất cả")) {

            foods = Food_BUS.getAllFoods();
        } else {
            foods = Food_BUS.findFoodsByGroupName(name);
        }

        for (int i = 0; i < foods.size(); i++) {
            FoodCard foodCard = new FoodCard(foods.get(i).getName(), Integer.toString(foods.get(i).getPrice()),
                    ImageUtils.convertByteArrayToImageIcon(foods.get(i).getImage()),
                    new Dimension(widthGridviewTableItem, foodCardListLayoutHeigth));
            final int tempFoodId = foods.get(i).getId();
            final double tempFoodPrice = foods.get(i).getPrice();
            final String tempFoodName = foods.get(i).getName();
            foodCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() % 2 == 0) {
                        for (int i = 0; i < billDetailList.size(); i++) {
                            if (billDetailList.get(i).getFoodName().equals(tempFoodName)) {
                                OrderBill_BUS.updateAmountFood(new OrderDetail_DTO(
                                        selectedOrderBillId, tempFoodId,
                                        billDetailList.get(i).getQuantity() + 1,
                                        (billDetailList.get(i).getQuantity() + 1) * tempFoodPrice
                                ));
                                loadBillDetailByTableDinnerId(indexTable);
                                return;
                            }
                        }
                        OrderBill_BUS.insertFood(new OrderDetail_DTO(
                                selectedOrderBillId, tempFoodId, 1, tempFoodPrice));
                        loadBillDetailByTableDinnerId(indexTable);
                    }
                }
            });
            foodCardListLayout.add(foodCard);
        }

        foodCardListLayout.setPreferredSize(new Dimension(widthGridviewTableItem,
                getNumberRowFoodCard(foods.size()) * (int) (foodCardListLayoutHeigth / 2.8)));

        if (foodChooserLayout.getComponentCount() > 1) {
            foodChooserLayout.remove(1);
            foodChooserLayout.add(scrollPaneFoodCardList);
        } else {
            foodChooserLayout.add(scrollPaneFoodCardList);
        }

        foodChooserLayout.setVisible(false);
        foodChooserLayout.setVisible(true);
    }

    private void tableItemMouseClicked(MouseEvent evt) {
        tfPromotionPercentUnit.setText("");
        tfPromotionPercentUnit.repaint();

        if (evt.getClickCount() == 1) {
            getInfoDinnerTable(indexTable);
            loadBillDetailByTableDinnerId(indexTable);
            selectedOrderBillId = OrderBill_BUS.getCurrentBillId(indexTable);

        }
        if (evt.getClickCount() == 2) {
            DinnerTable_DTO dinnerTableInfo = DinnerTable_BUS.getTableInfoByTableId(indexTable);
            if ("Trống".equals(dinnerTableInfo.getStatus())) {
                int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn mở " + dinnerTableInfo.getName(), "Mở bàn", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    DinnerTable_BUS.setStatusOccupied(dinnerTableInfo.getId());
                    getInfoDinnerTable(indexTable);
                    selectedOrderBillId = OrderBill_BUS.getCurrentBillId(indexTable);
                    loadTable();
                    displayFoodChooserListLayout(true);
                } else {
                    // code here
                }
            } else {
                displayFoodChooserListLayout(true);
            }
        }
    }

    private void btnOpenTableActionPerformed(ActionEvent evt) {
        DinnerTable_DTO dinnerTableInfo = DinnerTable_BUS.getTableInfoByTableId(indexTable);
        if ("Trống".equals(dinnerTableInfo.getStatus())) {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn mở " + dinnerTableInfo.getName(), "Mở bàn", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                DinnerTable_BUS.setStatusOccupied(dinnerTableInfo.getId());
                getInfoDinnerTable(indexTable);
                selectedOrderBillId = OrderBill_BUS.getCurrentBillId(indexTable);
                loadTable();
                displayFoodChooserListLayout(true);
            }
        } else {
            displayFoodChooserListLayout(true);
        }
    }

    private void btnBackActionPerformed(ActionEvent evt) {
        displayFoodChooserListLayout(false);
    }

    private void btnPaymentActionPerformed(ActionEvent evt) {
        OrderBill_BUS.checkoutBill(new OrderBill_DTO(
                selectedOrderBillId,
                indexTable,
                "",
                "1",
                dateUtils.getDateNow(),
                Double.parseDouble(lbTotalPayable.getText())
        ));
        loadBillDetailByTableDinnerId(indexTable);
        loadTable();
        displayFoodChooserListLayout(false);
    }

    private void btnClickFoodGroup(ActionEvent evt, String foodGroupName) {
        System.out.println(foodGroupName + " CLICK");
        loadFoodCard(foodGroupName);
    }

    private void btnIncreaseFoodAmountPerformed(ActionEvent evt) {
        int row = tbBillDetails.getSelectedRow();
        if (row != -1) {

            OrderBill_BUS.updateAmountFood(new OrderDetail_DTO(
                    selectedOrderBillId, billDetailList.get(row).getiDFood(),
                    billDetailList.get(row).getQuantity() + 1,
                    billDetailList.get(row).getPrice() * billDetailList.get(row).getQuantity()
            ));
            loadBillDetailByTableDinnerId(indexTable);
            tbBillDetails.setRowSelectionInterval(row, row);
        }
    }

    private void btnDecreaseFoodAmountPerformed(ActionEvent evt) {
        int row = tbBillDetails.getSelectedRow();
        if (row != -1) {

            if (billDetailList.get(row).getQuantity() > 0) {
                OrderBill_BUS.updateAmountFood(new OrderDetail_DTO(
                        selectedOrderBillId, billDetailList.get(row).getiDFood(),
                        billDetailList.get(row).getQuantity() - 1,
                        billDetailList.get(row).getPrice() * billDetailList.get(row).getQuantity()
                ));
                loadBillDetailByTableDinnerId(indexTable);
                tbBillDetails.setRowSelectionInterval(row, row);
            }
        }
    }

    private void btnRemoveFoodPerfomed(ActionEvent evt) {
        int row = tbBillDetails.getSelectedRow();
        if (row != -1) {
            OrderBill_BUS.deleteOrderDetail(new OrderDetail_DTO(
                    selectedOrderBillId, billDetailList.get(row).getiDFood(),
                    0,
                    0
            ));
            loadBillDetailByTableDinnerId(indexTable);
        }
    }

    private void tfPromotionPercentTextChangeActionPerformed(DocumentEvent evt) {

        if (!tfProvisionalAmount.getText().equals("")
                && !tfPromotionPercentUnit.getText().equals("")) {
            double total = Double.parseDouble(tfProvisionalAmount.getText());
            double percent;
            if (Double.parseDouble(tfPromotionPercentUnit.getText()) > 100) {
                percent = 100;
            } else {
                percent = Double.parseDouble(tfPromotionPercentUnit.getText());
            }
            lbTotalPayable.setText(Double.toString((total * (100 - percent)) / 100));
        }
    }

    private void displayFoodChooserListLayout(boolean isDislpay) {
        System.out.println("DISPLAY FOOD CHOOSER");
        foodChooserLayout.setVisible(isDislpay);
        scrollPane.setVisible(!isDislpay);
        btnBack.setVisible(isDislpay);
    }

    private void getInfoDinnerTable(int indexTable) {
        System.out.println("DISPLAY INFO DINNER TABLE");

        if (indexTable != -1) {
            DinnerTable_DTO dinnerTableInfo = DinnerTable_BUS.getTableInfoByTableId(indexTable);
            lbTableName.setText(dinnerTableInfo.getName());
            lbTableStatus.setText(dinnerTableInfo.getStatus());
            if (!"Trống".equals(dinnerTableInfo.getStatus())) {
                lbTableName.setForeground(new Color(15, 189, 116));
                lbTableStatus.setForeground(new Color(15, 189, 116));
            } else {
                lbTableName.setForeground(Color.red);
                lbTableStatus.setForeground(Color.red);
            }
        }
    }

    // Load bill detail by dinner table id
    private void loadBillDetailByTableDinnerId(int tableId) {
        int totalMoney = 0;
        System.out.println("LOAD BILL");

        billDetailList = BillDetail_BUS.loadBillDetailByTableId((DefaultTableModel) tbBillDetails.getModel(), tableId);  // lấy hóa đơn của bàn đang được click
        if (billDetailList == null) {
            tfProvisionalAmount.setText("0");
            lbTotalPayable.setText("0");
        } else {
            for (int i = 0; i < tbBillDetails.getRowCount(); i++) {
                totalMoney += Integer.parseInt(tbBillDetails.getModel().getValueAt(i, 3).toString());
            }

            tfProvisionalAmount.setText(String.valueOf(totalMoney));
            lbTotalPayable.setText(tfProvisionalAmount.getText());
        }
    }

    private int getNumberRowTableMap(List<TableModelItemUI> tables) {
        if (tables.size() % 5 == 0) {
            return tables.size() / 5;
        }
        return tables.size() / 5 + 1;
    }

    private int getNumberRowFoodCard(int length) {
        if (length % 4 == 0) {
            return length / 5;
        }
        return length / 4 + 1;
    }

    private RoundedButton btnFoodGroupItem(String foodGroupName) {
        RoundedButton btnFoodGroup = new RoundedButton();
        btnFoodGroup.setText(foodGroupName);
        return btnFoodGroup;
    }

    private void LoadTabel() {
        List<TableModelItemUI> tables = TableItemUI.getAllTableDinnerUI();
        int widthGridviewTableItem = dimension.width - dimension.width / 3 - 5;
        bodyLayout.removeAll();
        for (int i = 0; i <= tables.size() - 1; i++) {
            final int indexTemp = i;
            TableItem tableItem = new TableItem(new ImageIcon(getClass().getResource("Trống".equals(tables.get(i).getStatus()) ? "/assets/ic_tableware.png" : "/assets/ic_tableware_selected.png")), tables.get(i).getName());
            tableItem.setPreferredSize(new Dimension((int) (widthGridviewTableItem / 5.4), (int) (widthGridviewTableItem / 5)));
            tableItem.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    indexTable = tables.get(indexTemp).getiD();
                    tableItemMouseClicked(evt);
                }
            });
            bodyLayout.add(tableItem);
        }
    }

    // Variables declaration - do not modify   
    private javax.swing.JPanel billByTable;
    private javax.swing.JPanel gridviewTableItem;
    private GUI.Component.RoundedButton btnOpenTable;
    private GUI.Component.RoundedButton btnBack;
    private javax.swing.JLabel lbDateOpenTable;
    private javax.swing.JLabel lbTableName;
    private javax.swing.JLabel lbTableStatus;
    private javax.swing.JPanel foodChooserLayout;
    private javax.swing.JScrollPane scrollPane;
    private Utils.DateUtils dateUtils;

    private javax.swing.JPanel titleLayout;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTable tbBillDetails;
    private javax.swing.JPanel billDetailsLayout;
    private javax.swing.JPanel adjustNumberOfFoodsLayout;
    private javax.swing.JButton btnIncreaseAmountFood;
    private javax.swing.JButton btnDecreaseAmountFood;
    private javax.swing.JButton btnDeleteFood;
    private javax.swing.JPanel provisionalAmountLayout;
    private javax.swing.JPanel totalPayableLayout;
    private javax.swing.JTextField tfProvisionalAmount;
    private javax.swing.JTextField tfPromotionPercentUnit;
    private javax.swing.JLabel lbTotalPayable;
    private javax.swing.JPanel paymentLayout;
    private GUI.Component.RoundedButton btnPayment;
    private javax.swing.JPanel bodyLayout;
    private List<BillDetail_DTO> billDetailList;
    private int selectedOrderBillId;
    // nd of variables declaration 
}
