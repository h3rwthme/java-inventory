/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package id.team1.inventory;

/**
 *
 * @author team1
 */
public class LoginFrame extends javax.swing.JFrame {

    private static final java.awt.Color NAVY_950 = new java.awt.Color(8, 27, 58);
    private static final java.awt.Color BLUE_800 = new java.awt.Color(30, 64, 175);
    private static final java.awt.Color BLUE_700 = new java.awt.Color(29, 78, 216);
    private static final java.awt.Color BLUE_200 = new java.awt.Color(191, 219, 254);
    private static final java.awt.Color BLUE_100 = new java.awt.Color(219, 234, 254);
    private static final java.awt.Color BLUE_50 = new java.awt.Color(239, 246, 255);
    private static final java.awt.Color BACKGROUND_COLOR = BLUE_50;
    private static final java.awt.Color SURFACE_COLOR = java.awt.Color.WHITE;
    private static final java.awt.Color TEXT_COLOR = NAVY_950;
    private static final java.awt.Color MUTED_TEXT_COLOR = new java.awt.Color(100, 116, 139);
    private static final java.awt.Color BORDER_COLOR = BLUE_200;
    private static final java.awt.Color PRIMARY_COLOR = BLUE_800;
    private static final java.awt.Color DANGER_COLOR = new java.awt.Color(185, 28, 28);

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        applyModernStyle();
    }

    private void applyModernStyle() {
        setMinimumSize(new java.awt.Dimension(760, 520));
        setPreferredSize(new java.awt.Dimension(820, 560));

        rootPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBackground(NAVY_950);
        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 24, 18, 24));
        titlePanel.setOpaque(false);
        infoPanel.setOpaque(false);
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(32, 32, 32, 32));
        cardPanel.setBackground(SURFACE_COLOR);
        cardPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(BORDER_COLOR, 1, true),
                javax.swing.BorderFactory.createEmptyBorder(28, 30, 28, 30)
        ));
        footerPanel.setBackground(SURFACE_COLOR);
        footerPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR),
                javax.swing.BorderFactory.createEmptyBorder(10, 24, 10, 24)
        ));

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblHeaderTitle.setForeground(java.awt.Color.WHITE);
        lblHeaderSubtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblHeaderSubtitle.setForeground(BLUE_100);
        styleInfoChip(lblHeaderChip);

        lblLoginTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblLoginTitle.setForeground(TEXT_COLOR);
        lblLoginSubtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        lblLoginSubtitle.setForeground(MUTED_TEXT_COLOR);
        styleLabel(lblUsername);
        styleLabel(lblPassword);
        styleInput(txtUsername);
        styleInput(txtPassword);
        styleCheckBox(chkShowPassword);
        styleButton(btnLogin);

        lblError.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblError.setForeground(DANGER_COLOR);
        lblFooter.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblFooter.setForeground(MUTED_TEXT_COLOR);

        pack();
        setLocationRelativeTo(null);
    }

    private void styleInfoChip(javax.swing.JLabel label) {
        label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        label.setForeground(NAVY_950);
        label.setBackground(BLUE_50);
        label.setOpaque(true);
        label.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(BLUE_200, 1, true),
                javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
    }

    private void styleLabel(javax.swing.JLabel label) {
        label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        label.setForeground(TEXT_COLOR);
    }

    private void styleInput(javax.swing.JTextField field) {
        field.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        field.setForeground(TEXT_COLOR);
        field.setBackground(SURFACE_COLOR);
        field.setCaretColor(PRIMARY_COLOR);
        applyInputBorder(field, BORDER_COLOR);
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                applyInputBorder(field, BLUE_700);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                applyInputBorder(field, BORDER_COLOR);
            }
        });
    }

    private void applyInputBorder(javax.swing.JTextField field, java.awt.Color color) {
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(color, 1, true),
                javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleCheckBox(javax.swing.JCheckBox checkBox) {
        checkBox.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        checkBox.setForeground(TEXT_COLOR);
        checkBox.setBackground(SURFACE_COLOR);
        checkBox.setFocusPainted(false);
    }

    private void styleButton(javax.swing.JButton button) {
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        button.setForeground(java.awt.Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(PRIMARY_COLOR.darker(), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        button.setOpaque(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        rootPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        lblHeaderTitle = new javax.swing.JLabel();
        lblHeaderSubtitle = new javax.swing.JLabel();
        infoPanel = new javax.swing.JPanel();
        lblHeaderChip = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        cardPanel = new javax.swing.JPanel();
        lblLoginTitle = new javax.swing.JLabel();
        lblLoginSubtitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        chkShowPassword = new javax.swing.JCheckBox();
        lblError = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        footerPanel = new javax.swing.JPanel();
        lblFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Sistem Inventory");

        rootPanel.setLayout(new java.awt.BorderLayout());

        headerPanel.setLayout(new java.awt.BorderLayout(16, 0));

        titlePanel.setLayout(new javax.swing.BoxLayout(titlePanel, javax.swing.BoxLayout.Y_AXIS));

        lblHeaderTitle.setText("Sistem Inventory Barang");
        titlePanel.add(lblHeaderTitle);

        lblHeaderSubtitle.setText("Dashboard Operasional");
        titlePanel.add(lblHeaderSubtitle);

        headerPanel.add(titlePanel, java.awt.BorderLayout.WEST);

        infoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 4));

        lblHeaderChip.setText("Login");
        infoPanel.add(lblHeaderChip);

        headerPanel.add(infoPanel, java.awt.BorderLayout.EAST);

        rootPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        contentPanel.setLayout(new java.awt.GridBagLayout());

        cardPanel.setLayout(new java.awt.GridBagLayout());

        lblLoginTitle.setText("Login Inventory");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        cardPanel.add(lblLoginTitle, gridBagConstraints);

        lblLoginSubtitle.setText("Masuk dengan akun yang tersimpan di database");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 22, 0);
        cardPanel.add(lblLoginSubtitle, gridBagConstraints);

        lblUsername.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        cardPanel.add(lblUsername, gridBagConstraints);

        txtUsername.setPreferredSize(new java.awt.Dimension(340, 38));
        txtUsername.setToolTipText("Masukkan username");
        txtUsername.addActionListener(this::btnLoginActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 14, 0);
        cardPanel.add(txtUsername, gridBagConstraints);

        lblPassword.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        cardPanel.add(lblPassword, gridBagConstraints);

        txtPassword.setPreferredSize(new java.awt.Dimension(340, 38));
        txtPassword.setToolTipText("Masukkan password");
        txtPassword.addActionListener(this::btnLoginActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        cardPanel.add(txtPassword, gridBagConstraints);

        chkShowPassword.setText("Tampilkan password");
        chkShowPassword.addActionListener(this::chkShowPasswordActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        cardPanel.add(chkShowPassword, gridBagConstraints);

        lblError.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        cardPanel.add(lblError, gridBagConstraints);

        btnLogin.setText("Masuk");
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        cardPanel.add(btnLogin, gridBagConstraints);

        contentPanel.add(cardPanel, new java.awt.GridBagConstraints());

        rootPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        footerPanel.setLayout(new java.awt.BorderLayout());

        lblFooter.setText("Siap login");
        footerPanel.add(lblFooter, java.awt.BorderLayout.WEST);

        rootPanel.add(footerPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(rootPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPasswordActionPerformed
        txtPassword.setEchoChar(chkShowPassword.isSelected() ? (char) 0 : (char) 0x2022);
    }//GEN-LAST:event_chkShowPasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Username dan password wajib diisi");
            return;
        }

        try (java.sql.Connection conn = Koneksi.createConnection()) {
            if (conn == null) {
                lblError.setText("Koneksi database gagal");
                return;
            }

            AppUser user = AppUser.authenticate(conn, username, password);
            if (user != null) {
                new MainFrame(user).setVisible(true);
                dispose();
                return;
            }

            lblError.setText("Username atau password salah");
            txtPassword.setText("");
            txtPassword.requestFocusInWindow();
        } catch (java.sql.SQLException ex) {
            lblError.setText("Login gagal. Cek tabel Users di database");
            java.util.logging.Logger.getLogger(LoginFrame.class.getName())
                    .log(java.util.logging.Level.SEVERE, "Error login user", ex);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblHeaderChip;
    private javax.swing.JLabel lblHeaderSubtitle;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblLoginSubtitle;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
