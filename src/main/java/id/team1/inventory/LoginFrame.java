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

    // ── Premium Light Color Palette ──
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color BG_SOFT = new java.awt.Color(248, 250, 252);
    private static final java.awt.Color BRAND_BLUE = new java.awt.Color(59, 130, 246);
    private static final java.awt.Color BRAND_DARK = new java.awt.Color(30, 58, 138);
    private static final java.awt.Color BRAND_LIGHT = new java.awt.Color(219, 234, 254);
    private static final java.awt.Color INPUT_BG = new java.awt.Color(249, 250, 251);
    private static final java.awt.Color INPUT_BORDER = new java.awt.Color(209, 213, 219);
    private static final java.awt.Color INPUT_FOCUS = new java.awt.Color(59, 130, 246);
    private static final java.awt.Color TEXT_DARK = new java.awt.Color(17, 24, 39);
    private static final java.awt.Color TEXT_MUTED = new java.awt.Color(107, 114, 128);
    private static final java.awt.Color DANGER = new java.awt.Color(220, 38, 38);
    private static final java.awt.Color ACCENT_HOVER = new java.awt.Color(37, 99, 235);

    private java.awt.image.BufferedImage loginImage;

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        setUndecorated(false);
        setResizable(false);
        loadImage();
        initComponents();
        applyModernStyle();
    }

    private void loadImage() {
        try {
            java.io.InputStream is = getClass().getResourceAsStream("/images/login_bg.png");
            if (is != null) {
                loginImage = javax.imageio.ImageIO.read(is);
            }
        } catch (Exception e) {
            loginImage = null;
        }
    }

    private void applyModernStyle() {
        setMinimumSize(new java.awt.Dimension(900, 560));
        setPreferredSize(new java.awt.Dimension(900, 560));
        getContentPane().setBackground(WHITE);
        pack();
        setLocationRelativeTo(null);
    }

    // ── Gradient Button ──
    private static class GradientButton extends javax.swing.JButton {
        private boolean hovered = false;
        private float animProgress = 0f;

        public GradientButton(String text) {
            super(text);
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15));
            setForeground(WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 24, 15, 24));
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseEntered(java.awt.event.MouseEvent e) { hovered = true; repaint(); }
                @Override public void mouseExited(java.awt.event.MouseEvent e) { hovered = false; repaint(); }
                @Override public void mousePressed(java.awt.event.MouseEvent e) { animProgress = 1f; repaint(); }
                @Override public void mouseReleased(java.awt.event.MouseEvent e) { animProgress = 0f; repaint(); }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            java.awt.Color start = hovered ? ACCENT_HOVER : BRAND_BLUE;
            java.awt.Color end = hovered ? new java.awt.Color(29, 78, 216) : new java.awt.Color(99, 102, 241);
            java.awt.GradientPaint gp = new java.awt.GradientPaint(0, 0, start, getWidth(), getHeight(), end);
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);

            // Subtle shadow effect
            if (!hovered) {
                g2.setColor(new java.awt.Color(59, 130, 246, 30));
                g2.fillRoundRect(2, 4, getWidth() - 4, getHeight() - 2, 14, 14);
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ── Modern rounded text field ──
    private static class ModernTextField extends javax.swing.JTextField {
        private boolean focused = false;
        private final String placeholder;

        public ModernTextField(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            setForeground(TEXT_DARK);
            setCaretColor(INPUT_FOCUS);
            setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 16, 14, 16));
            addFocusListener(new java.awt.event.FocusAdapter() {
                @Override public void focusGained(java.awt.event.FocusEvent e) { focused = true; repaint(); }
                @Override public void focusLost(java.awt.event.FocusEvent e) { focused = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            // Background
            g2.setColor(focused ? WHITE : INPUT_BG);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

            // Border with shadow when focused
            if (focused) {
                g2.setColor(new java.awt.Color(59, 130, 246, 25));
                g2.setStroke(new java.awt.BasicStroke(4f));
                g2.drawRoundRect(-1, -1, getWidth() + 1, getHeight() + 1, 14, 14);
                g2.setColor(INPUT_FOCUS);
                g2.setStroke(new java.awt.BasicStroke(2f));
            } else {
                g2.setColor(INPUT_BORDER);
                g2.setStroke(new java.awt.BasicStroke(1f));
            }
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
            g2.dispose();

            super.paintComponent(g);

            // Placeholder text
            if (getText().isEmpty() && !focused) {
                java.awt.Graphics2D g3 = (java.awt.Graphics2D) g.create();
                g3.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                g3.setColor(new java.awt.Color(156, 163, 175));
                g3.setFont(getFont());
                java.awt.FontMetrics fm = g3.getFontMetrics();
                g3.drawString(placeholder, 16, (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                g3.dispose();
            }
        }
    }

    // ── Modern rounded password field ──
    private static class ModernPasswordField extends javax.swing.JPasswordField {
        private boolean focused = false;
        private final String placeholder;

        public ModernPasswordField(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            setForeground(TEXT_DARK);
            setCaretColor(INPUT_FOCUS);
            setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 16, 14, 16));
            addFocusListener(new java.awt.event.FocusAdapter() {
                @Override public void focusGained(java.awt.event.FocusEvent e) { focused = true; repaint(); }
                @Override public void focusLost(java.awt.event.FocusEvent e) { focused = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(focused ? WHITE : INPUT_BG);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

            if (focused) {
                g2.setColor(new java.awt.Color(59, 130, 246, 25));
                g2.setStroke(new java.awt.BasicStroke(4f));
                g2.drawRoundRect(-1, -1, getWidth() + 1, getHeight() + 1, 14, 14);
                g2.setColor(INPUT_FOCUS);
                g2.setStroke(new java.awt.BasicStroke(2f));
            } else {
                g2.setColor(INPUT_BORDER);
                g2.setStroke(new java.awt.BasicStroke(1f));
            }
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
            g2.dispose();

            super.paintComponent(g);

            // Placeholder text
            if (getPassword().length == 0 && !focused) {
                java.awt.Graphics2D g3 = (java.awt.Graphics2D) g.create();
                g3.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                g3.setColor(new java.awt.Color(156, 163, 175));
                g3.setFont(getFont());
                java.awt.FontMetrics fm = g3.getFontMetrics();
                g3.drawString(placeholder, 16, (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                g3.dispose();
            }
        }
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
        contentPanel = new javax.swing.JPanel();
        cardPanel = new javax.swing.JPanel();
        lblLoginTitle = new javax.swing.JLabel();
        lblLoginSubtitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new ModernTextField("Masukkan username");
        lblPassword = new javax.swing.JLabel();
        txtPassword = new ModernPasswordField("Masukkan password");
        chkShowPassword = new javax.swing.JCheckBox();
        lblError = new javax.swing.JLabel();
        btnLogin = new GradientButton("Sign In");
        footerPanel = new javax.swing.JPanel();
        lblFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Inventory System");

        // ── SPLIT LAYOUT: Image left + Form right ──
        rootPanel.setLayout(new java.awt.BorderLayout());
        rootPanel.setBackground(WHITE);

        // ── LEFT PANEL: Illustration ──
        javax.swing.JPanel leftPanel = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                // Soft gradient background
                java.awt.GradientPaint bgGrad = new java.awt.GradientPaint(
                    0, 0, new java.awt.Color(239, 246, 255),
                    0, getHeight(), new java.awt.Color(219, 234, 254)
                );
                g2.setPaint(bgGrad);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Draw subtle decorative circles
                g2.setColor(new java.awt.Color(59, 130, 246, 15));
                g2.fillOval(-40, -40, 200, 200);
                g2.fillOval(getWidth() - 100, getHeight() - 150, 200, 200);
                g2.setColor(new java.awt.Color(99, 102, 241, 10));
                g2.fillOval(getWidth() / 2 - 80, getHeight() - 100, 160, 160);

                // Draw image centered
                if (loginImage != null) {
                    int imgSize = Math.min(getWidth() - 60, getHeight() - 160);
                    imgSize = Math.min(imgSize, 320);
                    int imgX = (getWidth() - imgSize) / 2;
                    int imgY = (getHeight() - imgSize) / 2 - 20;
                    g2.drawImage(loginImage, imgX, imgY, imgSize, imgSize, null);
                }

                // Branding text at bottom
                g2.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
                g2.setColor(BRAND_DARK);
                String brand = "Inventory System";
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(brand)) / 2;
                int textY = getHeight() - 70;
                g2.drawString(brand, textX, textY);

                g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
                g2.setColor(TEXT_MUTED);
                String sub = "Kelola stok dengan mudah";
                fm = g2.getFontMetrics();
                g2.drawString(sub, (getWidth() - fm.stringWidth(sub)) / 2, textY + 22);

                g2.dispose();
            }
        };
        leftPanel.setPreferredSize(new java.awt.Dimension(380, 560));
        rootPanel.add(leftPanel, java.awt.BorderLayout.WEST);

        // ── RIGHT PANEL: Login Form ──
        javax.swing.JPanel rightPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        rightPanel.setBackground(WHITE);

        contentPanel.setLayout(new java.awt.GridBagLayout());
        contentPanel.setBackground(WHITE);

        cardPanel.setLayout(new java.awt.GridBagLayout());
        cardPanel.setBackground(WHITE);
        cardPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 48, 20, 48));
        cardPanel.setOpaque(true);

        // Logo icon (emoji-based)
        javax.swing.JLabel lblIcon = new javax.swing.JLabel("📦");
        lblIcon.setFont(new java.awt.Font("Segoe UI Emoji", java.awt.Font.PLAIN, 36));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        cardPanel.add(lblIcon, gridBagConstraints);

        // Title
        lblLoginTitle.setText("Welcome back");
        lblLoginTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        lblLoginTitle.setForeground(TEXT_DARK);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        cardPanel.add(lblLoginTitle, gridBagConstraints);

        lblLoginSubtitle.setText("Sign in to continue");
        lblLoginSubtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        lblLoginSubtitle.setForeground(TEXT_MUTED);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 36, 0);
        cardPanel.add(lblLoginSubtitle, gridBagConstraints);

        // Username
        lblUsername.setText("Username");
        lblUsername.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblUsername.setForeground(TEXT_MUTED);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 6, 0);
        cardPanel.add(lblUsername, gridBagConstraints);

        txtUsername.setPreferredSize(new java.awt.Dimension(320, 48));
        txtUsername.addActionListener(this::btnLoginActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        cardPanel.add(txtUsername, gridBagConstraints);

        // Password
        lblPassword.setText("Password");
        lblPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblPassword.setForeground(TEXT_MUTED);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 6, 0);
        cardPanel.add(lblPassword, gridBagConstraints);

        txtPassword.setPreferredSize(new java.awt.Dimension(320, 48));
        txtPassword.addActionListener(this::btnLoginActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        cardPanel.add(txtPassword, gridBagConstraints);

        // Show password checkbox
        chkShowPassword.setText("Show password");
        chkShowPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        chkShowPassword.setForeground(TEXT_MUTED);
        chkShowPassword.setBackground(WHITE);
        chkShowPassword.setFocusPainted(false);
        chkShowPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkShowPassword.addActionListener(this::chkShowPasswordActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        cardPanel.add(chkShowPassword, gridBagConstraints);

        // Error label
        lblError.setText(" ");
        lblError.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblError.setForeground(DANGER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        cardPanel.add(lblError, gridBagConstraints);

        // Login button
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        cardPanel.add(btnLogin, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        contentPanel.add(cardPanel, gridBagConstraints);

        rightPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        // Footer
        footerPanel.setLayout(new java.awt.BorderLayout());
        footerPanel.setBackground(WHITE);
        footerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 12, 24));

        lblFooter.setText("© 2026 Team 1 — Inventory System");
        lblFooter.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        lblFooter.setForeground(new java.awt.Color(156, 163, 175));
        lblFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        footerPanel.add(lblFooter, java.awt.BorderLayout.CENTER);

        rightPanel.add(footerPanel, java.awt.BorderLayout.SOUTH);
        rootPanel.add(rightPanel, java.awt.BorderLayout.CENTER);

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
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblLoginSubtitle;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
