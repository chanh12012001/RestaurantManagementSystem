package GUI;

import BUS.Account_BUS;
import BUS.RestaurantManagementFacade;
import DTO.Account_DTO;
import java.awt.Color;
import javax.swing.JOptionPane;

public class LoginJFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginJFrame
     */
    RestaurantManagementFacade restaurantManagementFacade;
    public LoginJFrame() {
        initComponents();
        getContentPane().setBackground(new Color(224, 235, 235));
        setLocationRelativeTo(null);
        restaurantManagementFacade = RestaurantManagementFacade.getInstance();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundLogin = new GUI.Component.Background();
        btnLogin = new GUI.Component.RoundedButton();
        img_background = new GUI.Component.ImageComponent();
        tfUserName = new GUI.Component.RoundedTextField();
        lbAppName = new javax.swing.JLabel();
        btnCloseApp = new javax.swing.JToggleButton();
        lbLogin = new javax.swing.JLabel();
        tfPassword = new GUI.Component.RoundedPasswordField();
        icUserName = new GUI.Component.ImageComponent();
        icPassword = new GUI.Component.ImageComponent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        backgroundLogin.setBackground(new java.awt.Color(255, 255, 255));

        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("ĐĂNG NHẬP");
        btnLogin.setColor(new java.awt.Color(153, 153, 255));
        btnLogin.setColorOver(new java.awt.Color(51, 153, 255));
        btnLogin.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnLogin.setRadius(20);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        img_background.setImage(new javax.swing.ImageIcon(getClass().getResource("/assets/img_background.png"))); // NOI18N

        tfUserName.setBorderColor(new java.awt.Color(204, 204, 204));
        tfUserName.setBorderWidth(1);
        tfUserName.setHintText("Tài khoản");
        tfUserName.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfUserName.setRound(20);
        tfUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUserNameActionPerformed(evt);
            }
        });

        lbAppName.setFont(new java.awt.Font("Palatino", 1, 40)); // NOI18N
        lbAppName.setForeground(new java.awt.Color(102, 102, 255));
        lbAppName.setText("Restaurant Management");

        btnCloseApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_close.png"))); // NOI18N
        btnCloseApp.setBorder(null);
        btnCloseApp.setBorderPainted(false);
        btnCloseApp.setPreferredSize(new java.awt.Dimension(30, 30));
        btnCloseApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lbLogin.setFont(new java.awt.Font("Palatino", 1, 24)); // NOI18N
        lbLogin.setText("ĐĂNG NHẬP");

        tfPassword.setBorderColor(new java.awt.Color(204, 204, 204));
        tfPassword.setBorderWidth(1);
        tfPassword.setHintText("Mật khẩu");
        tfPassword.setRound(20);

        icUserName.setImage(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_username.png"))); // NOI18N

        icPassword.setImage(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_password.png"))); // NOI18N

        javax.swing.GroupLayout backgroundLoginLayout = new javax.swing.GroupLayout(backgroundLogin);
        backgroundLogin.setLayout(backgroundLoginLayout);
        backgroundLoginLayout.setHorizontalGroup(
            backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLoginLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLoginLayout.createSequentialGroup()
                        .addComponent(lbAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(btnCloseApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(backgroundLoginLayout.createSequentialGroup()
                        .addComponent(img_background, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundLoginLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(icPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(icUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
                            .addGroup(backgroundLoginLayout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(lbLogin)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        backgroundLoginLayout.setVerticalGroup(
            backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLoginLayout.createSequentialGroup()
                .addGroup(backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLoginLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(backgroundLoginLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnCloseApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addGroup(backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLoginLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(icUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(icPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(img_background, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUserNameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Account_DTO account = restaurantManagementFacade.Login(tfUserName.getText(), String.valueOf(tfPassword.getPassword()));
        if (account != null) {
            this.dispose();
            new DashboardJFrame(account.getAccountType()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sai tài khoản hoặc mật khẩu vui lòng thử lại", "Login", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát khỏi hệ thống?", "Thoát", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (result == JOptionPane.NO_OPTION) {
            System.err.println("No");
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Component.Background backgroundLogin;
    private javax.swing.JToggleButton btnCloseApp;
    private GUI.Component.RoundedButton btnLogin;
    private GUI.Component.ImageComponent icPassword;
    private GUI.Component.ImageComponent icUserName;
    private GUI.Component.ImageComponent img_background;
    private javax.swing.JLabel lbAppName;
    private javax.swing.JLabel lbLogin;
    private GUI.Component.RoundedPasswordField tfPassword;
    private GUI.Component.RoundedTextField tfUserName;
    // End of variables declaration//GEN-END:variables
}
