package GUI;

import BUS.RestaurantManagementFacade;
import DTO.Statistic_DTO;
import GUI.Component.RoundedButton;
import Utils.DateUtils;
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
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatisticJFrame extends JPanel {

    private final Dimension dimension;
    RestaurantManagementFacade restaurantManagementFacade;

    public StatisticJFrame(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }

    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height;
        restaurantManagementFacade = RestaurantManagementFacade.getInstance();
        
        lbDateNow = new JLabel();
        lbTurnover = new JLabel();
        lbTextFromDate = new JLabel();
        lbTextToDate = new JLabel();
        lbTextYearChoose = new JLabel();
        cbYearChooser = new JComboBox<>();
        dtpFromDate = new JDateChooser();
        dtpToDate = new JDateChooser();
        btnStatistic = new RoundedButton();
        cbStatisticType = new JComboBox<>();

        setPreferredSize(new Dimension(bodyWidth, bodyHeight));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 0, 60, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        JLabel lbText = new JLabel("THỐNG KÊ DOANH THU");
        lbText.setFont(new Font("sansserif", 1, 22));
        lbText.setForeground(Color.BLACK);
        add(lbText, gbc);

        gbc.insets = new Insets(0, 50, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lbTextDate = new JLabel("Hôm nay:");
        lbTextDate.setFont(new Font("sansserif", 1, 16));
        lbTextDate.setForeground(Color.BLACK);
        add(lbTextDate, gbc);

        gbc.insets = new Insets(0, 0, 0, 40);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        Statistic_DTO statisticNow
                = restaurantManagementFacade.calculateIncomeByDay(DateUtils.formatDate(new Date()));

        lbDateNow = new JLabel(statisticNow.getIndex());
        lbDateNow.setFont(new Font("sansserif", 1, 16));
        lbDateNow.setForeground(Color.BLACK);
        add(lbDateNow, gbc);

        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lbTextTurnover = new JLabel("Doanh thu:");
        lbTextTurnover.setFont(new Font("sansserif", 1, 16));
        lbTextTurnover.setForeground(Color.BLACK);
        add(lbTextTurnover, gbc);

        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        lbTurnover = new JLabel(Double.toString(statisticNow.getIncome()));
        lbTurnover.setFont(new Font("sansserif", 1, 16));
        lbTurnover.setForeground(Color.BLACK);
        add(lbTurnover, gbc);

        gbc.insets = new Insets(20, 0, 15, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        JSeparator jSeparator = new JSeparator();
        jSeparator.setForeground(new java.awt.Color(153, 153, 255));
        jSeparator.setPreferredSize(new java.awt.Dimension(bodyWidth - 100, 10));
        add(jSeparator, gbc);

        /**
         * Filter
         */
        gbc.insets = new Insets(10, 0, 30, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JPanel filterLayout = new JPanel(new GridBagLayout());
        filterLayout.setPreferredSize(new Dimension(bodyWidth, bodyHeight / 10));
        GridBagConstraints gbcFilter = new GridBagConstraints();

        gbcFilter.insets = new Insets(0, 0, 0, 10);
        gbcFilter.anchor = GridBagConstraints.WEST;
        gbcFilter.gridx = 0;
        gbcFilter.gridy = 0;
        JLabel lbTextStatisticType = new JLabel("Thống kê theo");
        lbTextStatisticType.setFont(new Font("sansserif", 0, 14));
        lbTextStatisticType.setForeground(Color.BLACK);
        filterLayout.add(lbTextStatisticType, gbcFilter);

        // compobox chọn kiểu thống kê
        gbcFilter.gridx = 1;
        gbcFilter.gridy = 0;
        gbcFilter.insets = new Insets(0, 0, 0, 20);
        gbcFilter.anchor = GridBagConstraints.WEST;

        cbStatisticType.setModel(new DefaultComboBoxModel<>(new String[]{"Ngày", "Tháng", "Năm"}));
        cbStatisticType.setFocusable(false);
        cbStatisticType.setPreferredSize(new Dimension(100, 35));
        cbStatisticType.setFont(new java.awt.Font("sansserif", 0, 14));
        cbStatisticType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatisticTypeActionPerformed(evt);
            }
        });

        filterLayout.add(cbStatisticType, gbcFilter);

        // label text
        gbcFilter.insets = new Insets(0, 0, 0, 10);
        gbcFilter.anchor = GridBagConstraints.WEST;
        gbcFilter.gridx = 2;
        gbcFilter.gridy = 0;
        lbTextFromDate = new JLabel("Từ ngày");
        lbTextFromDate.setFont(new Font("sansserif", 0, 14));
        lbTextFromDate.setForeground(Color.BLACK);
        filterLayout.add(lbTextFromDate, gbcFilter);

        // date time picker từ ngày
        gbcFilter.gridx = 3;
        gbcFilter.gridy = 0;
        gbcFilter.insets = new Insets(0, 0, 0, 20);
        gbcFilter.anchor = GridBagConstraints.WEST;

        dtpFromDate.setFocusable(false);
        dtpFromDate.setPreferredSize(new Dimension((int) (bodyWidth / 5), 35));
        dtpFromDate.setFont(new java.awt.Font("sansserif", 0, 14));
        ((JTextFieldDateEditor) dtpFromDate.getDateEditor()).setEditable(false);
        filterLayout.add(dtpFromDate, gbcFilter);

        gbcFilter.insets = new Insets(0, 0, 0, 10);
        gbcFilter.anchor = GridBagConstraints.WEST;
        gbcFilter.gridx = 4;
        gbcFilter.gridy = 0;
        lbTextToDate = new JLabel("Đến ngày");
        lbTextToDate.setFont(new Font("sansserif", 0, 14));
        lbTextToDate.setForeground(Color.BLACK);
        filterLayout.add(lbTextToDate, gbcFilter);

        // date time picker đến ngày
        gbcFilter.gridx = 5;
        gbcFilter.gridy = 0;
        gbcFilter.insets = new Insets(0, 0, 0, 20);
        gbcFilter.anchor = GridBagConstraints.WEST;

        dtpToDate.setFocusable(false);
        dtpToDate.setPreferredSize(new Dimension((int) (bodyWidth / 5), 35));
        dtpToDate.setFont(new java.awt.Font("sansserif", 0, 14));
        ((JTextFieldDateEditor) dtpToDate.getDateEditor()).setEditable(false);
        filterLayout.add(dtpToDate, gbcFilter);

        // label text chọn năm thống kê
        gbcFilter.insets = new Insets(0, 0, 0, 10);
        gbcFilter.anchor = GridBagConstraints.WEST;
        gbcFilter.gridx = 2;
        gbcFilter.gridy = 0;
        lbTextYearChoose = new JLabel("Chọn năm: ");
        lbTextYearChoose.setFont(new Font("sansserif", 0, 14));
        lbTextYearChoose.setForeground(Color.BLACK);
        lbTextYearChoose.setVisible(false);
        filterLayout.add(lbTextYearChoose, gbcFilter);

        // combobox chọn năm thống kê
        gbcFilter.gridx = 3;
        gbcFilter.gridy = 0;
        gbcFilter.insets = new Insets(0, 0, 0, 20);
        gbcFilter.anchor = GridBagConstraints.WEST;

        cbYearChooser.setModel(new DefaultComboBoxModel<>(new String[]{"2019", "2021", "2022", "2023"}));
        cbYearChooser.setFocusable(false);
        cbYearChooser.setPreferredSize(new Dimension(100, 35));
        cbYearChooser.setFont(new java.awt.Font("sansserif", 0, 14));
        cbYearChooser.setVisible(false);
        cbYearChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cbYearChooserActionPerformed(evt);
            }
        });

        filterLayout.add(cbYearChooser, gbcFilter);

        // button filter
        gbcFilter.insets = new Insets(0, 0, 0, 0);
        gbcFilter.anchor = GridBagConstraints.WEST;
        gbcFilter.gridx = 6;
        gbcFilter.gridy = 0;
        btnStatistic.setForeground(new java.awt.Color(255, 255, 255));
        btnStatistic.setText("");
        btnStatistic.setColor(new java.awt.Color(77, 148, 255));
        btnStatistic.setColorOver(new java.awt.Color(51, 153, 255));
        btnStatistic.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        btnStatistic.setRadius(10);
        btnStatistic.setBorderPainted(false);
        btnStatistic.setFocusPainted(false);
        btnStatistic.setPreferredSize(new Dimension(35, 35));
        ImageIcon iconFilter = new ImageIcon(getClass().getResource("/assets/ic_statistic.png"));
        Image imgFilter = iconFilter.getImage();
        Image newimg = imgFilter.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        iconFilter = new ImageIcon(newimg);
        btnStatistic.setIcon(iconFilter);
        btnStatistic.addActionListener((ActionEvent evt) -> {
            btnStatisticActionPerformed(evt);
        });

        filterLayout.add(btnStatistic, gbcFilter);

        add(filterLayout, gbc);

        gbc.insets = new Insets(10, 0, 30, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 4;
        chartLayout = new JPanel(new BorderLayout());
        chartLayout.setPreferredSize(new Dimension(bodyWidth - 100, bodyHeight / 2));

        ArrayList<Statistic_DTO> statisticList = new ArrayList<>();
        statisticList.add(new Statistic_DTO("", 0));
        loadChart(statisticList, "Daily");

        add(chartLayout, gbc);

    }

    private void btnStatisticActionPerformed(ActionEvent evt) {
        //Because btn would show in day -> not need to check statistic type

        if (dtpFromDate.getDate() != null && dtpToDate.getDate() != null) {
            String fromDay = DateUtils.formatDate(dtpFromDate.getDate());
            String toDay = DateUtils.formatDate(dtpToDate.getDate());
            loadChart(restaurantManagementFacade.calculateIncomeByDate(fromDay, toDay), "Daily");
        } else {
            ArrayList<Statistic_DTO> statisticList = new ArrayList<>();
            statisticList.add(new Statistic_DTO("", 0));
            loadChart(statisticList, "Daily");
        }
    }

    private void cbStatisticTypeActionPerformed(ActionEvent evt) {
        switch (cbStatisticType.getSelectedItem().toString()) {
            case "Ngày" -> {
                btnStatistic.setVisible(true);
                displayUIFormDateStatistic(true);
                displayUIFormMonthStatistic(false);
                btnStatisticActionPerformed(null);
            }
            case "Tháng" -> {
                btnStatistic.setVisible(false);
                displayUIFormDateStatistic(false);
                displayUIFormMonthStatistic(true);
                loadChart(restaurantManagementFacade.calculateIncomeByMonth(cbYearChooser.getSelectedItem().toString()), "Monthly");
            }
            case "Năm" -> {
                btnStatistic.setVisible(false);
                displayUIFormDateStatistic(false);
                displayUIFormMonthStatistic(false);
                loadChart(restaurantManagementFacade.calculateIncomeByYear(), "Yearly");
            }
            default ->
                throw new AssertionError();
        }
        System.out.println(cbStatisticType.getSelectedItem());
    }

    void displayUIFormDateStatistic(boolean isVisible) {
        lbTextFromDate.setVisible(isVisible);
        lbTextToDate.setVisible(isVisible);
        dtpFromDate.setVisible(isVisible);
        dtpToDate.setVisible(isVisible);
    }

    void displayUIFormMonthStatistic(boolean isVisible) {
        lbTextYearChoose.setVisible(isVisible);
        cbYearChooser.setVisible(isVisible);
    }

    private void cbYearChooserActionPerformed(ActionEvent evt) {
        System.out.println(cbYearChooser.getSelectedItem());
        loadChart(restaurantManagementFacade.calculateIncomeByMonth(cbYearChooser.getSelectedItem().toString()), "Monthly");
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

    //Load chart by day, by month or by year
    private void loadChart(ArrayList<Statistic_DTO> statisticList, String name) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (!statisticList.isEmpty()) {
            for (int i = 0; i < statisticList.size(); i++) {
                dataset.setValue(statisticList.get(i).getIncome(), "Income", statisticList.get(i).getIndex());
            }

            JFreeChart chart = ChartFactory.createBarChart("Biểu đồ", name, "amount", dataset, PlotOrientation.VERTICAL, false, true, false);

            CategoryPlot categoryPlot = chart.getCategoryPlot();
            categoryPlot.setRangeGridlinePaint(Color.BLUE);
            categoryPlot.setBackgroundPaint(Color.WHITE);
            BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
            Color clr3 = new Color(204, 0, 51);
            renderer.setSeriesPaint(0, clr3);

            ChartPanel barpChartPanel = new ChartPanel(chart);

            chartLayout.removeAll();
            chartLayout.add(barpChartPanel, BorderLayout.CENTER);
            chartLayout.updateUI();
        }
    }

    // Variables declaration - do not modify     
    private javax.swing.JLabel lbDateNow;
    private javax.swing.JLabel lbTurnover;
    private javax.swing.JLabel lbTextFromDate;
    private javax.swing.JLabel lbTextToDate;
    private com.toedter.calendar.JDateChooser dtpFromDate;
    private com.toedter.calendar.JDateChooser dtpToDate;
    private javax.swing.JLabel lbTextYearChoose;
    private javax.swing.JComboBox<String> cbYearChooser;
    private GUI.Component.RoundedButton btnStatistic;
    private javax.swing.JComboBox<String> cbStatisticType;
    private javax.swing.JPanel chartLayout;
    // nd of variables declaration 
}
