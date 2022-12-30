package GUI.Component.StaffManager;

import BUS.Account_BUS;
import BUS.Food_BUS;
import BUS.Staff_BUS;
import DTO.Account_DTO;
import DTO.Food_DTO;
import DTO.Staff_DTO;
import GUI.Component.RoundedButton;
import GUI.Component.RoundedTextField;
import Interface.EventTextChange;
import Utils.DateUtils;
import Utils.ImageUtils;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class StaffInfoListLayout extends JPanel {

    private final Dimension dimension;
    String[] properties = {"Mã nhân viên ", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại", "Chức vụ", "Lương", "Địa chỉ"};
    ArrayList<Integer> IDs = new ArrayList<>();

    public StaffInfoListLayout(Dimension dimension) {
        this.dimension = dimension;
        initComponents();
        setOpaque(false);
    }

    private void initComponents() {
        int bodyWidth = dimension.width;
        int bodyHeight = dimension.height - dimension.height / 22 - 10;

        dateUtils = new DateUtils();
        tfFullName = new RoundedTextField();
        cbPositionType = new JComboBox<>();
        dtpDateOfBirth = new JDateChooser();
        tfPosition = new RoundedTextField();
        tfPhoneNumber = new RoundedTextField();
        tfSalary = new RoundedTextField();
        rbMaleGender = new JRadioButton();
        rbFemaleGender = new JRadioButton();
        taAddress = new JTextArea();
        tfSearch = new RoundedTextField();
        btnAddStaff = new RoundedButton();
        btnUpdateStaff = new RoundedButton();
        btnDeleteStaff = new RoundedButton();
        dtmTableModel = new DefaultTableModel(properties, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tbStaffInfo = new JTable(dtmTableModel);
        tbStaffInfo.getSelectionModel().addListSelectionListener((e) -> {
            rowSelectedListener(e);
        });

        Staff_BUS.getAllStaff(dtmTableModel);
        IDs = Staff_BUS.getAllStaffID();

        /**
         * info Staff Form Layout
         */
        JPanel infoStaffFormLayout = new JPanel() {
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
        infoStaffFormLayout.setOpaque(false);
        infoStaffFormLayout.setBorder(BorderFactory.createTitledBorder(null, "Thông tin nhân viên", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font("Helvetica Neue", 1, 22), new Color(65, 72, 204))); // NOI18N
        infoStaffFormLayout.setPreferredSize(new Dimension(bodyWidth, (int) (bodyHeight / 2)));
        infoStaffFormLayout.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 0, 25, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lbTextFullName = new JLabel("Họ và tên");
        lbTextFullName.setFont(new Font("sansserif", 0, 15));
        lbTextFullName.setForeground(Color.BLACK);
        infoStaffFormLayout.add(lbTextFullName, gbc);

        // textfield Họ và tên
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 25, 30);
        gbc.anchor = GridBagConstraints.WEST;

        tfFullName.setBorderColor(new java.awt.Color(204, 204, 204));
        tfFullName.setBorderWidth(1);
        tfFullName.setHintText("");
        tfFullName.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfFullName.setRound(20);
        tfFullName.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));

        System.out.println(tfFullName.getWidth());

        infoStaffFormLayout.add(tfFullName, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 25, 10);
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lbTextPositionType = new JLabel("Loại chức vụ");
        lbTextPositionType.setFont(new Font("sansserif", 0, 15));
        lbTextPositionType.setForeground(Color.BLACK);
        infoStaffFormLayout.add(lbTextPositionType, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 25, 0);
        gbc.anchor = GridBagConstraints.WEST;

        cbPositionType.setModel(new DefaultComboBoxModel<>(new String[]{"Nhân viên", "Quản lý"}));
        cbPositionType.setFocusable(false);
        cbPositionType.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));
        cbPositionType.setFont(new java.awt.Font("sansserif", 0, 14));

        infoStaffFormLayout.add(cbPositionType, gbc);

        // label Ngày sinh
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 25, 10);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextDateOfBirth = new JLabel("Ngày sinh");
        lbTextDateOfBirth.setFont(new Font("sansserif", 0, 15));
        lbTextDateOfBirth.setForeground(Color.BLACK);
        infoStaffFormLayout.add(lbTextDateOfBirth, gbc);

        // date time picker ngày sinh
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 25, 30);
        gbc.anchor = GridBagConstraints.WEST;

        dtpDateOfBirth.setFocusable(false);
        dtpDateOfBirth.setDateFormatString("dd/MM/yyyy");
        dtpDateOfBirth.setDate(new Date());
        dtpDateOfBirth.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));
        dtpDateOfBirth.setFont(new java.awt.Font("sansserif", 0, 14));
        ((JTextFieldDateEditor) dtpDateOfBirth.getDateEditor()).setEditable(false);
        infoStaffFormLayout.add(dtpDateOfBirth, gbc);

        // label Chức vụ cụ thể
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 25, 10);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextPosition = new JLabel("Chức vụ");
        lbTextPosition.setFont(new Font("sansserif", 0, 15));
        lbTextPosition.setForeground(Color.BLACK);
        infoStaffFormLayout.add(lbTextPosition, gbc);

        // textfield chức vụ cụ thể
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.anchor = GridBagConstraints.WEST;

        tfPosition.setBorderColor(new java.awt.Color(204, 204, 204));
        tfPosition.setBorderWidth(1);
        tfPosition.setHintText("");
        tfPosition.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfPosition.setRound(20);
        tfPosition.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));
        infoStaffFormLayout.add(tfPosition, gbc);

        // label số điện thoại
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 25, 10);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextPhoneNumber = new JLabel("Số điện thoại");
        lbTextPhoneNumber.setFont(new Font("sansserif", 0, 15));
        lbTextPhoneNumber.setForeground(Color.BLACK);
        infoStaffFormLayout.add(lbTextPhoneNumber, gbc);

        // textfield số điện thoại
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.anchor = GridBagConstraints.WEST;

        tfPhoneNumber.setBorderColor(new java.awt.Color(204, 204, 204));
        tfPhoneNumber.setBorderWidth(1);
        tfPhoneNumber.setHintText("");
        tfPhoneNumber.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfPhoneNumber.setRound(20);
        tfPhoneNumber.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));

        tfPhoneNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || tfPhoneNumber.getText().length() < 10) {
                        tfPhoneNumber.setEditable(true);
                    } else {
                        tfPhoneNumber.setEditable(false);
                    }
                } else {
                    tfPhoneNumber.setEditable(false);
                }
            }
        });

        infoStaffFormLayout.add(tfPhoneNumber, gbc);

        // label lương
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 25, 10);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextWage = new JLabel("Lương");
        lbTextWage.setFont(new Font("sansserif", 0, 15));
        lbTextWage.setForeground(Color.BLACK);
        infoStaffFormLayout.add(lbTextWage, gbc);

        // textfield lương
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.anchor = GridBagConstraints.WEST;

        tfSalary.setBorderColor(new java.awt.Color(204, 204, 204));
        tfSalary.setBorderWidth(1);
        tfSalary.setHintText("");
        tfSalary.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfSalary.setRound(20);
        tfSalary.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));

        tfSalary.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    tfSalary.setEditable(true);
                } else {
                    tfSalary.setEditable(false);
                }
            }
        });

        infoStaffFormLayout.add(tfSalary, gbc);

        // label Giới tính
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 25, 10);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextSex = new JLabel("Giới tính");
        lbTextSex.setFont(new Font("sansserif", 0, 15));
        lbTextSex.setForeground(Color.BLACK);
        infoStaffFormLayout.add(lbTextSex, gbc);

        // group radio button giới tính
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 25, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JPanel sexLayout = new JPanel();
        GridBagLayout gridBagSexLayout = new GridBagLayout();
        GridBagConstraints gbcSex = new GridBagConstraints();
        sexLayout.setLayout(gridBagSexLayout);
        sexLayout.setBackground(new Color(245, 245, 245));
        ButtonGroup btnGroupSex = new ButtonGroup();

        rbMaleGender.setText("Nam");
        rbMaleGender.setSelected(true);
        rbMaleGender.setFont(new Font("sansserif", 0, 15));
        rbMaleGender.setForeground(Color.BLACK);
        gbcSex.gridx = 0;
        gbcSex.gridy = 0;
        gbcSex.insets = new Insets(0, 0, 0, 40);

        rbFemaleGender.setText("Nữ");
        rbFemaleGender.setFont(new Font("sansserif", 0, 15));
        rbFemaleGender.setForeground(Color.BLACK);

        sexLayout.add(rbMaleGender, gbcSex);

        gbcSex.gridx = 1;
        gbcSex.gridy = 0;
        gbcSex.insets = new Insets(0, 0, 0, 0);
        sexLayout.add(rbFemaleGender, gbcSex);

        btnGroupSex.add(rbMaleGender);
        btnGroupSex.add(rbFemaleGender);

        sexLayout.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 40));

        infoStaffFormLayout.add(sexLayout, gbc);

        // label địa chỉ
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 25, 10);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lbTextAddress = new JLabel("Địa chỉ");
        lbTextAddress.setFont(new Font("sansserif", 0, 15));
        lbTextAddress.setForeground(Color.BLACK);
        infoStaffFormLayout.add(lbTextAddress, gbc);

        // textarea địa chỉ
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.anchor = GridBagConstraints.WEST;

        taAddress.setFont(new Font("sansserif", 0, 15));
        taAddress.setForeground(Color.BLACK);
        taAddress.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 40));
        infoStaffFormLayout.add(taAddress, gbc);

        /**
         * Business Layout
         */
        JPanel businessLayout = new JPanel();
        businessLayout.setPreferredSize(new Dimension(bodyWidth, (int) (bodyHeight / 10)));
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
        tfSearch.setPreferredSize(new Dimension((int) (bodyWidth / 3.5), 35));
        tfSearch.getDocument().addDocumentListener((EventTextChange) (DocumentEvent evt) -> {
            tfSearchTextChangeActionPerformed(evt);
        });

        businessLayout.add(tfSearch, gbcBusiness);

        // button thêm nhân viên
        gbcBusiness.gridx = 2;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 15);
        gbcBusiness.anchor = GridBagConstraints.WEST;

        btnAddStaff.setForeground(new java.awt.Color(255, 255, 255));
        btnAddStaff.setText("THÊM");
        btnAddStaff.setColor(new java.awt.Color(6, 208, 214));
        btnAddStaff.setColorOver(new java.awt.Color(51, 153, 255));
        btnAddStaff.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnAddStaff.setRadius(20);
        btnAddStaff.setBorderPainted(false);
        btnAddStaff.setFocusPainted(false);
        btnAddStaff.setPreferredSize(new Dimension(bodyWidth / 6, (int) (bodyHeight / 14)));
        btnAddStaff.setBorderColor(Color.red);
        ImageIcon iconAddPerson = new ImageIcon(getClass().getResource("/assets/ic_add_person.png"));
        Image imgAddPerson = iconAddPerson.getImage();
        Image newimgAddPerson = imgAddPerson.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconAddPerson = new ImageIcon(newimgAddPerson);
        btnAddStaff.setIcon(iconAddPerson);
        btnAddStaff.addActionListener((ActionEvent evt) -> {
            btnAddStaffActionPerformed(evt);
        });

        businessLayout.add(btnAddStaff, gbcBusiness);

        // button cập nhật nhân viên
        gbcBusiness.gridx = 3;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 15);
        gbcBusiness.anchor = GridBagConstraints.WEST;

        btnUpdateStaff.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateStaff.setText("CẬP NHẬT");
        btnUpdateStaff.setColor(new java.awt.Color(235, 147, 33));
        btnUpdateStaff.setColorOver(new java.awt.Color(51, 153, 255));
        btnUpdateStaff.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnUpdateStaff.setRadius(20);
        btnUpdateStaff.setBorderPainted(false);
        btnUpdateStaff.setFocusPainted(false);
        btnUpdateStaff.setPreferredSize(new Dimension(bodyWidth / 6, (int) (bodyHeight / 14)));
        btnUpdateStaff.setBorderColor(Color.red);
        ImageIcon iconUpdate = new ImageIcon(getClass().getResource("/assets/ic_update.png"));
        Image imgUpdate = iconUpdate.getImage();
        Image newimgUpdate = imgUpdate.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconUpdate = new ImageIcon(newimgUpdate);
        btnUpdateStaff.setIcon(iconUpdate);
        btnUpdateStaff.addActionListener((ActionEvent evt) -> {
            btnUpdateStaffActionPerformed(evt);
        });

        businessLayout.add(btnUpdateStaff, gbcBusiness);

        // button xoá nhân viên
        gbcBusiness.gridx = 4;
        gbcBusiness.gridy = 0;
        gbcBusiness.insets = new Insets(0, 0, 0, 0);
        gbcBusiness.anchor = GridBagConstraints.WEST;

        btnDeleteStaff.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteStaff.setText("XOÁ");
        btnDeleteStaff.setColor(new java.awt.Color(227, 14, 42));
        btnDeleteStaff.setColorOver(new java.awt.Color(51, 153, 255));
        btnDeleteStaff.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnDeleteStaff.setRadius(20);
        btnDeleteStaff.setBorderPainted(false);
        btnDeleteStaff.setFocusPainted(false);
        btnDeleteStaff.setPreferredSize(new Dimension(bodyWidth / 6, (int) (bodyHeight / 14)));
        btnDeleteStaff.setBorderColor(Color.red);
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/assets/ic_delete.png"));
        Image imgDelete = iconDelete.getImage();
        Image newimgDelete = imgDelete.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        iconDelete = new ImageIcon(newimgDelete);
        btnDeleteStaff.setIcon(iconDelete);
        btnDeleteStaff.addActionListener((ActionEvent evt) -> {
            btnDeleteStaffActionPerformed(evt);
        });

        businessLayout.add(btnDeleteStaff, gbcBusiness);

        /**
         * Table Staff Layout
         */
        JPanel tableLayout = new JPanel();
        tableLayout.setPreferredSize(new Dimension(bodyWidth, (int) (bodyHeight - bodyHeight / 2 - bodyHeight / 10)));
        tableLayout.setLayout(new BorderLayout());

        tbStaffInfo.setFocusable(false);
        tbStaffInfo.setIntercellSpacing(new Dimension(0, 0));
        tbStaffInfo.setRowHeight(33);
        tbStaffInfo.setSelectionBackground(Color.lightGray);
        tbStaffInfo.setShowVerticalLines(true);
        tbStaffInfo.setFont(new Font("Segoe UI", 0, 13));

        tbStaffInfo.getTableHeader().setOpaque(false);
        tbStaffInfo.getTableHeader().setReorderingAllowed(false);
        tbStaffInfo.getTableHeader().setForeground(Color.BLACK);
        tbStaffInfo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbStaffInfo.getTableHeader().setPreferredSize(new Dimension(bodyWidth, 30));
        tbStaffInfo.getTableHeader().setBackground(Color.red);

        // set column size
        tbStaffInfo.getColumnModel().getColumn(3).setPreferredWidth(35);
        tbStaffInfo.getColumnModel().getColumn(6).setPreferredWidth(40);

        JScrollPane jsp = new JScrollPane(tbStaffInfo);
        tableLayout.setLayout(new BorderLayout());
        JScrollBar bar = jsp.getVerticalScrollBar();
        bar.setPreferredSize(new Dimension(7, 0));
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        tableLayout.add(jsp, BorderLayout.CENTER);

        setPreferredSize(new Dimension(bodyWidth, bodyHeight));
        setLayout(new BorderLayout());
        add(infoStaffFormLayout, BorderLayout.NORTH);
        add(businessLayout, BorderLayout.CENTER);
        add(tableLayout, BorderLayout.SOUTH);

    }

    private String staffID = "";
    private final int STAFF_ID_ROW = 0;
    private final int STAFF_NAME_ROW = 1;
    private final int STAFF_DATE_OF_BIRTH_ROW = 2;
    private final int STAFF_SEX_ROW = 3;
    private final int STAFF_PHONE_NUMBER_ROW = 4;
    private final int STAFF_POSITION_ROW = 5;
    private final int STAFF_SALARY_ROW = 6;
    private final int STAFF_ADDRESS_ROW = 7;

    private void rowSelectedListener(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tbStaffInfo.getSelectedRow();
            if (selectedRow != -1) {
                staffID = tbStaffInfo.getValueAt(selectedRow, STAFF_ID_ROW).toString();
                tfFullName.setText(tbStaffInfo.getValueAt(selectedRow, STAFF_NAME_ROW).toString());
                dtpDateOfBirth.setDate(dateUtils.convertStringToDate(tbStaffInfo.getValueAt(selectedRow, STAFF_DATE_OF_BIRTH_ROW).toString()));
                if ("Nam".equals(tbStaffInfo.getValueAt(selectedRow, STAFF_SEX_ROW).toString())) {
                    rbMaleGender.setSelected(true);
                } else {
                    rbFemaleGender.setSelected(true);
                }

                tfPhoneNumber.setText(tbStaffInfo.getValueAt(selectedRow, STAFF_PHONE_NUMBER_ROW).toString());
                tfPosition.setText(tbStaffInfo.getValueAt(selectedRow, STAFF_POSITION_ROW).toString());
                tfSalary.setText(tbStaffInfo.getValueAt(selectedRow, STAFF_SALARY_ROW).toString());
                taAddress.setText(tbStaffInfo.getValueAt(selectedRow, STAFF_ADDRESS_ROW).toString());

            }
        }
    }

    private void loadStaff() {
        Staff_BUS.getAllStaff((DefaultTableModel) tbStaffInfo.getModel());
        IDs.clear();
        IDs = Staff_BUS.getAllStaffID();
    }

    private void btnAddStaffActionPerformed(ActionEvent evt) {

        if ("".equals(tfFullName.getText())
                || "".equals(tfPhoneNumber.getText())
                || "".equals(tfPosition.getText())
                || "".equals(tfSalary.getText())
                || "".equals(taAddress.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập đầy đủ thông tin", "Thông tin", JOptionPane.WARNING_MESSAGE);
        } else if (tfPhoneNumber.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ có 10 chữ số", "Thông tin", JOptionPane.WARNING_MESSAGE);
        } else {
            Staff_DTO newStaff = new Staff_DTO(autoCreateNewStaffID(), tfFullName.getText(), DateUtils.formatDate(dtpDateOfBirth.getDate()), getStaffSex(), tfPhoneNumber.getText(), tfPosition.getText(), tfSalary.getText(), taAddress.getText());

            Staff_BUS.addStaff(newStaff, cbPositionType.getSelectedItem().toString());

            loadStaff();
            clearData();
        }
    }

    private void btnUpdateStaffActionPerformed(ActionEvent evt) {
        Staff_DTO staff = Staff_BUS.getStaffById(staffID);

        if (staff != null) {

            Staff_DTO staffUpdate = new Staff_DTO(staff.getStaffID(), tfFullName.getText(), dateUtils.formatDate(dtpDateOfBirth.getDate()), getStaffSex(), tfPhoneNumber.getText(), tfPosition.getText(), tfSalary.getText(), taAddress.getText());
            Staff_BUS.updateStaff(staffUpdate);
            loadStaff();
            clearData();

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần cập nhật", "Cập nhật nhân viên",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnDeleteStaffActionPerformed(ActionEvent evt) {
        Staff_BUS.deleteStaff(staffID);
        loadStaff();
        clearData();
    }

    private void tfSearchTextChangeActionPerformed(DocumentEvent evt) {
        Staff_BUS.findStaffsByName((DefaultTableModel) tbStaffInfo.getModel(), tfSearch.getText());
    }

    private String autoCreateNewStaffID() {
        if (!IDs.contains(1)) {
            return createStaffID(1);
        } else {
            for (int i = 0; i < IDs.size() - 1; i++) {
                if (IDs.get(i) + 1 != IDs.get(i + 1)) {
                    return createStaffID((IDs.get(i) + 1));
                }
            }
            return createStaffID((IDs.get(IDs.size() - 1) + 1));
        }
    }

    private String createStaffID(int staffID) {
        String tempString;
        tempString = String.valueOf(staffID);
        while (tempString.length() < 3) {
            tempString = tempString.substring(0, 0) + '0' + tempString.substring(0);
        }
        System.out.println(cbPositionType.getSelectedItem().toString());
        if ("Nhân viên".equals(cbPositionType.getSelectedItem().toString())) {

            tempString = "NV" + tempString;
        } else {
            tempString = "QL" + tempString;
        }
        return tempString;
    }

    private String getStaffSex() {
        if (rbMaleGender.isSelected()) {
            return "Nam";
        }
        return "Nữ";
    }

    private void clearData() {
        staffID = "";
        tfFullName.setText("");
        dtpDateOfBirth.setDate(new Date());
        tfPhoneNumber.setText("");
        rbMaleGender.setSelected(true);
        tfPosition.setText("");
        tfSalary.setText("");
        taAddress.setText("");
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
    private GUI.Component.RoundedTextField tfFullName;
    private javax.swing.JComboBox<String> cbPositionType;
    private com.toedter.calendar.JDateChooser dtpDateOfBirth;
    private GUI.Component.RoundedTextField tfPosition;
    private GUI.Component.RoundedTextField tfPhoneNumber;
    private GUI.Component.RoundedTextField tfSalary;
    private javax.swing.JRadioButton rbMaleGender;
    private javax.swing.JRadioButton rbFemaleGender;
    private javax.swing.JTextArea taAddress;
    private GUI.Component.RoundedTextField tfSearch;
    private GUI.Component.RoundedButton btnAddStaff;
    private GUI.Component.RoundedButton btnUpdateStaff;
    private GUI.Component.RoundedButton btnDeleteStaff;
    private javax.swing.JTable tbStaffInfo;
    private javax.swing.table.DefaultTableModel dtmTableModel;
    private Utils.DateUtils dateUtils;
    // nd of variables declaration 
}
