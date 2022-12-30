package GUI;

import BUS.Bill_BUS;
import Utils.DateUtils;
import GUI.Component.RoundedButton;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
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
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class BillManagerJFrame extends JPanel {

    private final Dimension dimension;
    String[] properties = {"Số Hoá Đơn ", "Tên bàn", "Ngày Thanh toán", "Số tiền"};

    public BillManagerJFrame(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }

    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;

        dtpFromDate = new JDateChooser();
        dtpToDate = new JDateChooser();
        btnFilter = new RoundedButton();
        btnShowAll = new RoundedButton();
        dtmTableModel = new DefaultTableModel(properties, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tbBills = new JTable(dtmTableModel);
        Bill_BUS.getAllBills(dtmTableModel);

        setPreferredSize(new Dimension(bodyWidth, bodyHeight));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 80, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.gridy = 0;
        JLabel lbText = new JLabel("DANH SÁCH HOÁ ĐƠN");
        lbText.setFont(new Font("sansserif", 1, 22));
        lbText.setForeground(Color.BLACK);
        add(lbText, gbc);

        /**
         * Filter bill
         */
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lbTextFromDate = new JLabel("Từ ngày");
        lbTextFromDate.setFont(new Font("sansserif", 0, 14));
        lbTextFromDate.setForeground(Color.BLACK);
        add(lbTextFromDate, gbc);

        // date time picker từ ngày
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 25);
        gbc.anchor = GridBagConstraints.WEST;

        dtpFromDate.setFocusable(false);
        dtpFromDate.setDateFormatString("dd/MM/yyyy");
        dtpFromDate.setDate(new Date());
        dtpFromDate.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));
        dtpFromDate.setFont(new java.awt.Font("sansserif", 0, 14));
        ((JTextFieldDateEditor) dtpFromDate.getDateEditor()).setEditable(false);
        add(dtpFromDate, gbc);

        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lbTextToDate = new JLabel("Đến ngày");
        lbTextToDate.setFont(new Font("sansserif", 0, 14));
        lbTextToDate.setForeground(Color.BLACK);
        add(lbTextToDate, gbc);

        // date time picker đến ngày
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;

        dtpToDate.setFocusable(false);
        dtpToDate.setDateFormatString("dd/MM/yyyy");
        dtpToDate.setDate(new Date());
        dtpToDate.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));
        dtpToDate.setFont(new java.awt.Font("sansserif", 0, 14));
        ((JTextFieldDateEditor) dtpToDate.getDateEditor()).setEditable(false);
        add(dtpToDate, gbc);

        // button filter
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        btnFilter.setForeground(new java.awt.Color(255, 255, 255));
        btnFilter.setText("");
        btnFilter.setColor(new java.awt.Color(77, 148, 255));
        btnFilter.setColorOver(new java.awt.Color(51, 153, 255));
        btnFilter.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        btnFilter.setRadius(10);
        btnFilter.setBorderPainted(false);
        btnFilter.setFocusPainted(false);
        btnFilter.setPreferredSize(new Dimension(35, 35));
        ImageIcon iconFilter = new ImageIcon(getClass().getResource("/assets/ic_filter.png"));
        Image imgFilter = iconFilter.getImage();
        Image newimg = imgFilter.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        iconFilter = new ImageIcon(newimg);
        btnFilter.setIcon(iconFilter);
        btnFilter.addActionListener((ActionEvent evt) -> {
            btnFilterActionPerformed(evt);
        });

        add(btnFilter, gbc);

        // button show all
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        btnShowAll.setForeground(new java.awt.Color(255, 255, 255));
        btnShowAll.setText("");
        btnShowAll.setColor(new java.awt.Color(66, 185, 214));
        btnShowAll.setColorOver(new java.awt.Color(51, 153, 255));
        btnShowAll.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        btnShowAll.setRadius(10);
        btnShowAll.setBorderPainted(false);
        btnShowAll.setFocusPainted(false);
        btnShowAll.setPreferredSize(new Dimension(35, 35));
        ImageIcon iconShowAll = new ImageIcon(getClass().getResource("/assets/ic_load.png"));
        Image imgShowAll = iconShowAll.getImage();
        Image newimg1 = imgShowAll.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        iconShowAll = new ImageIcon(newimg1);
        btnShowAll.setIcon(iconShowAll);
        btnShowAll.addActionListener((ActionEvent evt) -> {
            btnShowAllActionPerformed(evt);
        });

        add(btnShowAll, gbc);

        /**
         * Table Staff Layout
         */
        gbc.insets = new Insets(25, 0, 0, 0);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        JPanel tableLayout = new JPanel();
        tableLayout.setPreferredSize(new Dimension(bodyWidth - 40, (int) (bodyHeight - bodyHeight / 2)));
        tableLayout.setLayout(new BorderLayout());

        tbBills.setPreferredSize(new Dimension(bodyWidth - 40, (int) (bodyHeight - bodyHeight / 2)));
        tbBills.setFocusable(false);
        tbBills.setIntercellSpacing(new Dimension(0, 0));
        tbBills.setRowHeight(33);
        tbBills.setSelectionBackground(Color.lightGray);
        tbBills.setShowVerticalLines(true);
        tbBills.setFont(new Font("Segoe UI", 0, 13));

        tbBills.getTableHeader().setOpaque(false);
        tbBills.getTableHeader().setReorderingAllowed(false);
        tbBills.getTableHeader().setForeground(Color.BLACK);
        tbBills.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbBills.getTableHeader().setPreferredSize(new Dimension(bodyWidth, 30));
        tbBills.getTableHeader().setBackground(Color.red);

        // set column size
//        tbBills.getColumnModel().getColumn(3).setPreferredWidth(35);
//        tbBills.getColumnModel().getColumn(6).setPreferredWidth(40);
        JScrollPane jsp = new JScrollPane(tbBills);
        tableLayout.setLayout(new BorderLayout());
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        tableLayout.add(jsp, BorderLayout.CENTER);

        add(tableLayout, gbc);
    }

    private void btnFilterActionPerformed(ActionEvent evt) {
//        if ("Tất cả".equals(String.valueOf(cbFilterFoodGroup.getSelectedItem()))) {
//            Food_BUS.getAllFoods((DefaultTableModel) tbFoodInfoList.getModel());
//        } else {
//        }
        Bill_BUS.getAllBillsBetweenFromDayAndToDay((DefaultTableModel) tbBills.getModel(), DateUtils.formatDate(dtpFromDate.getDate()), DateUtils.formatDate(dtpToDate.getDate()));
    }

    private void btnShowAllActionPerformed(ActionEvent evt) {
        Bill_BUS.getAllBills((DefaultTableModel) tbBills.getModel());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#FFFFFF"), 0, getHeight(), Color.decode("#A7BFE8"));
        g2.setPaint(gradientPaint);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(graphics);
    }

    // Variables declaration - do not modify     
    private com.toedter.calendar.JDateChooser dtpFromDate;
    private com.toedter.calendar.JDateChooser dtpToDate;
    private GUI.Component.RoundedButton btnFilter;
    private GUI.Component.RoundedButton btnShowAll;
    private javax.swing.JTable tbBills;
    private javax.swing.table.DefaultTableModel dtmTableModel;
    // nd of variables declaration 
}
