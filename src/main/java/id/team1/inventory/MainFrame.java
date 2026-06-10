/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package id.team1.inventory;

/**
 *
 * @author team1
 */
public class MainFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    private static final java.awt.Color NAVY_950 = new java.awt.Color(8, 27, 58);
    private static final java.awt.Color NAVY_900 = new java.awt.Color(15, 45, 88);
    private static final java.awt.Color BLUE_800 = new java.awt.Color(30, 64, 175);
    private static final java.awt.Color BLUE_700 = new java.awt.Color(29, 78, 216);
    private static final java.awt.Color BLUE_500 = new java.awt.Color(59, 130, 246);
    private static final java.awt.Color BLUE_200 = new java.awt.Color(191, 219, 254);
    private static final java.awt.Color BLUE_100 = new java.awt.Color(219, 234, 254);
    private static final java.awt.Color BLUE_50 = new java.awt.Color(239, 246, 255);
    private static final java.awt.Color BACKGROUND_COLOR = BLUE_50;
    private static final java.awt.Color SURFACE_COLOR = java.awt.Color.WHITE;
    private static final java.awt.Color TEXT_COLOR = NAVY_950;
    private static final java.awt.Color MUTED_TEXT_COLOR = new java.awt.Color(100, 116, 139);
    private static final java.awt.Color BORDER_COLOR = BLUE_200;
    private static final java.awt.Color PRIMARY_COLOR = BLUE_800;
    private static final java.awt.Color EDIT_COLOR = BLUE_500;
    private static final java.awt.Color DANGER_COLOR = new java.awt.Color(185, 28, 28);
    private static final java.awt.Color NEUTRAL_COLOR = NAVY_900;
    private static final java.awt.Color SEARCH_COLOR = BLUE_700;
    private final AppUser loggedInUser;
    private final java.sql.Connection conn = Koneksi.createConnection();
    private final javax.swing.JLabel lblUserInfo = new javax.swing.JLabel("User: -");
    private final javax.swing.JLabel lblKategoriInfo = new javax.swing.JLabel("Kategori: 0");
    private final javax.swing.JLabel lblBarangInfo = new javax.swing.JLabel("Barang: 0");
    private final javax.swing.JLabel lblTransaksiInfo = new javax.swing.JLabel("Transaksi: 0");
    private final javax.swing.JLabel lblFooterStatus = new javax.swing.JLabel("Siap digunakan");

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        this(AppUser.defaultAdmin());
    }

    public MainFrame(AppUser loggedInUser) {
        this.loggedInUser = loggedInUser;
        initComponents();
        applyModernStyle();
        setupTableRenderers();
        btnCariTransaksi.addActionListener(this::btnCariTransaksiActionPerformed);
        loadKategori();
        loadBarang();
        loadTransaksi();
    }

    private void applyModernStyle() {
        setTitle("Sistem Inventory Barang");
        setMinimumSize(new java.awt.Dimension(880, 620));
        getContentPane().setBackground(BACKGROUND_COLOR);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jTabbedPane1.setBackground(BACKGROUND_COLOR);
        jTabbedPane1.setForeground(TEXT_COLOR);
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 18, 18, 18));
        jTabbedPane1.setToolTipTextAt(0, "Data kategori barang");
        jTabbedPane1.setToolTipTextAt(1, "Data barang dan stok");
        jTabbedPane1.setToolTipTextAt(2, "Riwayat transaksi stok");

        stylePanels(BACKGROUND_COLOR, panelKategori, panelBarang, panelTransaksi);
        styleLabels(TEXT_COLOR, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11);
        styleFields(txtCariKategori, txtNamaKategori, txtCariBarang, txtNamaBarang, txtStokBarang, txtCariTransaksi, txtJumlahTransaksi, txtPesanTransaksi);
        styleCombos(cmbKategoriBarang, cmbBarangTransaksi, cmbTipeTransaksi);
        styleTooltips();

        styleButton(btnCariKategori, SEARCH_COLOR);
        styleButton(btnCariBarang, SEARCH_COLOR);
        styleButton(btnCariTransaksi, SEARCH_COLOR);

        styleButton(btnSimpanKategori, PRIMARY_COLOR);
        styleButton(btnSimpanBarang, PRIMARY_COLOR);
        styleButton(btnSimpanTransaksi, PRIMARY_COLOR);

        styleButton(btnEditKategori, EDIT_COLOR);
        styleButton(btnEditBarang, EDIT_COLOR);

        styleButton(btnHapusKategori, DANGER_COLOR);
        styleButton(btnHapusBarang, DANGER_COLOR);

        styleButton(btnResetKategori, NEUTRAL_COLOR);
        styleButton(btnResetBarang, NEUTRAL_COLOR);
        styleButton(btnResetTransaksi, NEUTRAL_COLOR);

        styleTable(tblKategori, jScrollPane1);
        styleTable(tblBarang, jScrollPane2);
        styleTable(tblTransaksi, jScrollPane3);
        configureColumnWidths();
        lblUserInfo.setText("User: " + loggedInUser.getDisplayName());
        rebuildMainLayout();

        pack();
        setLocationRelativeTo(null);
    }

    private void stylePanels(java.awt.Color background, javax.swing.JPanel... panels) {
        for (javax.swing.JPanel panel : panels) {
            panel.setBackground(background);
            panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }

    private void styleLabels(java.awt.Color text, javax.swing.JLabel... labels) {
        java.awt.Font labelFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
        for (javax.swing.JLabel label : labels) {
            label.setFont(labelFont);
            label.setForeground(text);
        }
    }

    private void styleFields(javax.swing.JTextField... fields) {
        java.awt.Font inputFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13);

        for (javax.swing.JTextField field : fields) {
            field.setFont(inputFont);
            field.setForeground(TEXT_COLOR);
            field.setDisabledTextColor(MUTED_TEXT_COLOR);
            field.setBackground(SURFACE_COLOR);
            field.setCaretColor(PRIMARY_COLOR);
            field.setPreferredSize(new java.awt.Dimension(220, 36));
            applyInputBorder(field, BORDER_COLOR);
            field.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    applyInputBorder(field, PRIMARY_COLOR);
                }

                @Override
                public void focusLost(java.awt.event.FocusEvent evt) {
                    applyInputBorder(field, BORDER_COLOR);
                }
            });
        }
    }

    @SafeVarargs
    private void styleCombos(javax.swing.JComboBox<String>... combos) {
        java.awt.Font inputFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13);
        for (javax.swing.JComboBox<String> combo : combos) {
            combo.setFont(inputFont);
            combo.setForeground(TEXT_COLOR);
            combo.setBackground(SURFACE_COLOR);
            combo.setPreferredSize(new java.awt.Dimension(220, 36));
            combo.setBorder(javax.swing.BorderFactory.createLineBorder(BORDER_COLOR));
        }
    }

    private void styleButton(javax.swing.JButton button, java.awt.Color background) {
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        button.setForeground(java.awt.Color.WHITE);
        button.setBackground(background);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(background.darker(), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(8, 14, 8, 14)
        ));
        button.setOpaque(true);
    }

    private void styleTable(javax.swing.JTable table, javax.swing.JScrollPane scrollPane) {
        table.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        table.setForeground(TEXT_COLOR);
        table.setBackground(SURFACE_COLOR);
        table.setRowHeight(34);
        table.setIntercellSpacing(new java.awt.Dimension(0, 1));
        table.setGridColor(BLUE_100);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setSelectionBackground(BLUE_100);
        table.setSelectionForeground(NAVY_950);
        table.setAutoCreateRowSorter(true);

        table.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        table.getTableHeader().setBackground(NAVY_900);
        table.getTableHeader().setForeground(java.awt.Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new java.awt.Dimension(table.getTableHeader().getWidth(), 38));

        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setForeground(isSelected ? table.getSelectionForeground() : TEXT_COLOR);
                c.setBackground(isSelected ? table.getSelectionBackground() : (row % 2 == 0 ? SURFACE_COLOR : BLUE_50));
                if (c instanceof javax.swing.JLabel label) {
                    label.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
                }
                return c;
            }
        });

        scrollPane.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(BORDER_COLOR),
                javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        scrollPane.getViewport().setBackground(SURFACE_COLOR);
    }

    private void applyInputBorder(javax.swing.JTextField field, java.awt.Color color) {
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(color, 1, true),
                javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10)
        ));
    }

    private void styleTooltips() {
        txtCariKategori.setToolTipText("Cari kategori berdasarkan nama");
        txtNamaKategori.setToolTipText("Masukkan nama kategori");
        txtCariBarang.setToolTipText("Cari barang berdasarkan nama atau kategori");
        txtNamaBarang.setToolTipText("Masukkan nama barang");
        txtStokBarang.setToolTipText("Stok awal hanya dipakai saat menambah barang baru");
        txtCariTransaksi.setToolTipText("Cari transaksi berdasarkan ID, barang, kategori, tipe, atau pesan");
        txtJumlahTransaksi.setToolTipText("Masukkan jumlah transaksi stok");
        txtPesanTransaksi.setToolTipText("Catatan transaksi");
        btnCariKategori.setToolTipText("Cari kategori");
        btnCariBarang.setToolTipText("Cari barang");
        btnCariTransaksi.setToolTipText("Cari transaksi");
        btnResetKategori.setToolTipText("Kosongkan form kategori");
        btnResetBarang.setToolTipText("Kosongkan form barang");
        btnResetTransaksi.setToolTipText("Kosongkan form transaksi");
    }

    private void rebuildMainLayout() {
        javax.swing.JPanel rootPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        rootPanel.setBackground(BACKGROUND_COLOR);
        rootPanel.add(createHeaderPanel(), java.awt.BorderLayout.NORTH);
        rootPanel.add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        rootPanel.add(createFooterPanel(), java.awt.BorderLayout.SOUTH);

        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(rootPanel, java.awt.BorderLayout.CENTER);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private javax.swing.JPanel createHeaderPanel() {
        javax.swing.JPanel headerPanel = new javax.swing.JPanel(new java.awt.BorderLayout(16, 0));
        headerPanel.setBackground(NAVY_950);
        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 24, 18, 24));

        javax.swing.JPanel titlePanel = new javax.swing.JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new javax.swing.BoxLayout(titlePanel, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Sistem Inventory Barang");
        titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        titleLabel.setForeground(java.awt.Color.WHITE);

        javax.swing.JLabel subtitleLabel = new javax.swing.JLabel("Dashboard Operasional");
        subtitleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        subtitleLabel.setForeground(BLUE_100);

        titlePanel.add(titleLabel);
        titlePanel.add(javax.swing.Box.createVerticalStrut(3));
        titlePanel.add(subtitleLabel);

        javax.swing.JPanel infoPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 4));
        infoPanel.setOpaque(false);
        infoPanel.add(createInfoChip(lblUserInfo));
        infoPanel.add(createInfoChip(lblKategoriInfo));
        infoPanel.add(createInfoChip(lblBarangInfo));
        infoPanel.add(createInfoChip(lblTransaksiInfo));

        headerPanel.add(titlePanel, java.awt.BorderLayout.WEST);
        headerPanel.add(infoPanel, java.awt.BorderLayout.EAST);
        return headerPanel;
    }

    private javax.swing.JPanel createFooterPanel() {
        javax.swing.JPanel footerPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        footerPanel.setBackground(SURFACE_COLOR);
        footerPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR),
                javax.swing.BorderFactory.createEmptyBorder(10, 24, 10, 24)
        ));

        lblFooterStatus.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblFooterStatus.setForeground(MUTED_TEXT_COLOR);
        footerPanel.add(lblFooterStatus, java.awt.BorderLayout.WEST);
        return footerPanel;
    }

    private javax.swing.JLabel createInfoChip(javax.swing.JLabel label) {
        label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        label.setForeground(NAVY_950);
        label.setBackground(BLUE_50);
        label.setOpaque(true);
        label.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(BLUE_200, 1, true),
                javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        return label;
    }

    private void configureColumnWidths() {
        setColumnWidths(tblKategori, 70, 260);
        setColumnWidths(tblBarang, 70, 190, 170, 80);
        setColumnWidths(tblTransaksi, 70, 145, 130, 80, 80, 165, 130, 130);
    }

    private void setColumnWidths(javax.swing.JTable table, int... widths) {
        javax.swing.table.TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length && i < columnModel.getColumnCount(); i++) {
            javax.swing.table.TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(widths[i]);
            if (i == 0) {
                column.setMaxWidth(90);
            }
        }
    }

    private void updateSummaryStatus(String message) {
        lblKategoriInfo.setText("Kategori: " + tblKategori.getRowCount());
        lblBarangInfo.setText("Barang: " + tblBarang.getRowCount());
        lblTransaksiInfo.setText("Transaksi: " + tblTransaksi.getRowCount());
        lblFooterStatus.setText(message);
    }

    private void setupTableRenderers() {
        tblBarang.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                int stok = 0;
                try {
                    Object stokValue = table.getValueAt(row, 3);
                    if (stokValue != null) {
                        stok = Integer.parseInt(stokValue.toString());
                    }
                } catch (NumberFormatException e) {
                    stok = 0;
                }

                if (stok < 5) {
                    c.setBackground(isSelected ? table.getSelectionBackground() : new java.awt.Color(254, 226, 226));
                    c.setForeground(isSelected ? table.getSelectionForeground() : new java.awt.Color(153, 27, 27));
                } else {
                    if (isSelected) {
                        c.setBackground(table.getSelectionBackground());
                        c.setForeground(table.getSelectionForeground());
                    } else {
                        c.setBackground(row % 2 == 0 ? SURFACE_COLOR : BLUE_50);
                        c.setForeground(TEXT_COLOR);
                    }
                }
                if (c instanceof javax.swing.JLabel label) {
                    label.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
                }
                return c;
            }
        });
    }

    private void loadKategoriToCombo() {
        cmbKategoriBarang.removeAllItems();
        try {
            if (conn != null) {
                java.sql.ResultSet res = Kategori.get(conn);
                while (res != null && res.next()) {
                    cmbKategoriBarang.addItem(res.getString("NamaKategori"));
                }
            }
        } catch (java.sql.SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error loading kategori to combo", e);
        }
    }

    private void loadTransaksiToCombo() {
        cmbBarangTransaksi.removeAllItems();
        try {
            if (conn != null) {
                java.sql.ResultSet res = Barang.get(conn);
                while (res != null && res.next()) {
                    cmbBarangTransaksi.addItem(res.getInt("IdBarang") + " - " + res.getString("NamaBarang"));
                }
            }
        } catch (java.sql.SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error loading barang to combo", e);
        }
    }

    private void loadBarang() {
        loadTransaksiToCombo();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblBarang.getModel();
        model.setRowCount(0);
        try {
            if (conn != null) {
                java.sql.ResultSet res = Barang.get(conn);
                while (res != null && res.next()) {
                    model.addRow(new Object[]{
                        res.getInt("IdBarang"),
                        res.getString("NamaBarang"),
                        res.getString("NamaKategori"),
                        res.getInt("JumlahBarang")
                    });
                }
            }
        } catch (java.sql.SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error loading barang", e);
        }
        updateSummaryStatus("Data barang dimuat");
    }

    private void loadKategori() {
        loadKategoriToCombo();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblKategori.getModel();
        model.setRowCount(0);
        try {
            if (conn != null) {
                java.sql.ResultSet res = Kategori.get(conn);
                while (res != null && res.next()) {
                    model.addRow(new Object[]{
                        res.getInt("IdKategori"),
                        res.getString("NamaKategori")
                    });
                }
            }
        } catch (java.sql.SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error loading kategori", e);
        }
        updateSummaryStatus("Data kategori dimuat");
    }

    private void loadTransaksi() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblTransaksi.getModel();
        model.setRowCount(0);
        try {
            if (conn != null) {
                java.sql.ResultSet res = Transaksi.get(conn);
                while (res != null && res.next()) {
                    addTransaksiRow(model, res);
                }
            }
        } catch (java.sql.SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error loading transaksi", e);
        }
        updateSummaryStatus("Data transaksi dimuat");
    }

    private void addTransaksiRow(javax.swing.table.DefaultTableModel model, java.sql.ResultSet res) throws java.sql.SQLException {
        model.addRow(new Object[]{
            res.getInt("IdTransaksi"),
            res.getString("NamaBarang"),
            res.getString("NamaKategori"),
            res.getString("TipeTransaksi"),
            res.getInt("JumlahTransaksi"),
            res.getString("Pesan"),
            formatUser(res),
            formatTimestamp(res.getTimestamp("Timestamp"))
        });
    }

    private String formatUser(java.sql.ResultSet res) throws java.sql.SQLException {
        String namaLengkap = res.getString("NamaLengkap");
        if (namaLengkap != null && !namaLengkap.isBlank()) {
            return namaLengkap;
        }

        String username = res.getString("Username");
        if (username != null && !username.isBlank()) {
            return username;
        }

        return "-";
    }

    private String formatTimestamp(java.sql.Timestamp timestamp) {
        if (timestamp == null) {
            return "-";
        }
        return timestamp.toLocalDateTime().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelKategori = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCariKategori = new javax.swing.JTextField();
        btnCariKategori = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKategori = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNamaKategori = new javax.swing.JTextField();
        btnSimpanKategori = new javax.swing.JButton();
        btnEditKategori = new javax.swing.JButton();
        btnHapusKategori = new javax.swing.JButton();
        btnResetKategori = new javax.swing.JButton();
        panelBarang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCariBarang = new javax.swing.JTextField();
        btnCariBarang = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbKategoriBarang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtStokBarang = new javax.swing.JTextField();
        btnSimpanBarang = new javax.swing.JButton();
        btnEditBarang = new javax.swing.JButton();
        btnHapusBarang = new javax.swing.JButton();
        btnResetBarang = new javax.swing.JButton();
        panelTransaksi = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCariTransaksi = new javax.swing.JTextField();
        btnCariTransaksi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cmbBarangTransaksi = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbTipeTransaksi = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtJumlahTransaksi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPesanTransaksi = new javax.swing.JTextField();
        btnSimpanTransaksi = new javax.swing.JButton();
        btnResetTransaksi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem Inventory");

        jLabel1.setText("Cari :");

        btnCariKategori.setText("Cari");
        btnCariKategori.addActionListener(this::btnCariKategoriActionPerformed);

        tblKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Kategori"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKategoriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKategori);

        jLabel2.setText("Nama Kategori :");

        btnSimpanKategori.setText("Simpan");
        btnSimpanKategori.addActionListener(this::btnSimpanKategoriActionPerformed);

        btnEditKategori.setText("Edit");
        btnEditKategori.addActionListener(this::btnEditKategoriActionPerformed);

        btnHapusKategori.setText("Hapus");
        btnHapusKategori.addActionListener(this::btnHapusKategoriActionPerformed);

        btnResetKategori.setText("Reset");
        btnResetKategori.addActionListener(this::btnResetKategoriActionPerformed);

        javax.swing.GroupLayout panelKategoriLayout = new javax.swing.GroupLayout(panelKategori);
        panelKategori.setLayout(panelKategoriLayout);
        panelKategoriLayout.setHorizontalGroup(
            panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKategoriLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKategoriLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariKategori))
                    .addGroup(panelKategoriLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(txtNamaKategori)
                            .addGroup(panelKategoriLayout.createSequentialGroup()
                                .addGroup(panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSimpanKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHapusKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnResetKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEditKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelKategoriLayout.setVerticalGroup(
            panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKategoriLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariKategori))
                .addGap(18, 18, 18)
                .addGroup(panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelKategoriLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpanKategori)
                            .addComponent(btnEditKategori))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHapusKategori)
                            .addComponent(btnResetKategori))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Kategori", panelKategori);

        jLabel3.setText("Cari :");

        btnCariBarang.setText("Cari");
        btnCariBarang.addActionListener(this::btnCariBarangActionPerformed);

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Barang", "Kategori", "Stok"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBarang);

        jLabel4.setText("Nama Barang :");

        jLabel5.setText("Kategori :");

        jLabel6.setText("Stok Awal :");

        btnSimpanBarang.setText("Simpan");
        btnSimpanBarang.addActionListener(this::btnSimpanBarangActionPerformed);

        btnEditBarang.setText("Edit");
        btnEditBarang.addActionListener(this::btnEditBarangActionPerformed);

        btnHapusBarang.setText("Hapus");
        btnHapusBarang.addActionListener(this::btnHapusBarangActionPerformed);

        btnResetBarang.setText("Reset");
        btnResetBarang.addActionListener(this::btnResetBarangActionPerformed);

        javax.swing.GroupLayout panelBarangLayout = new javax.swing.GroupLayout(panelBarang);
        panelBarang.setLayout(panelBarangLayout);
        panelBarangLayout.setHorizontalGroup(
            panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarangLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBarangLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariBarang))
                    .addGroup(panelBarangLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(txtNamaBarang)
                            .addGroup(panelBarangLayout.createSequentialGroup()
                                .addGroup(panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSimpanBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnResetBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEditBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5)
                            .addComponent(cmbKategoriBarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(txtStokBarang))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelBarangLayout.setVerticalGroup(
            panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarangLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariBarang))
                .addGap(18, 18, 18)
                .addGroup(panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBarangLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbKategoriBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpanBarang)
                            .addComponent(btnEditBarang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHapusBarang)
                            .addComponent(btnResetBarang))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Barang", panelBarang);

        jLabel7.setText("Cari :");

        btnCariTransaksi.setText("Cari");

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Barang", "Kategori", "Tipe", "Jumlah", "Pesan", "User", "Waktu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblTransaksi);

        jLabel8.setText("Barang :");

        jLabel9.setText("Tipe :");

        cmbTipeTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masuk", "Keluar" }));

        jLabel10.setText("Jumlah :");

        jLabel11.setText("Pesan :");

        btnSimpanTransaksi.setText("Simpan");
        btnSimpanTransaksi.addActionListener(this::btnSimpanTransaksiActionPerformed);

        btnResetTransaksi.setText("Reset");
        btnResetTransaksi.addActionListener(this::btnResetTransaksiActionPerformed);

        javax.swing.GroupLayout panelTransaksiLayout = new javax.swing.GroupLayout(panelTransaksi);
        panelTransaksi.setLayout(panelTransaksiLayout);
        panelTransaksiLayout.setHorizontalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariTransaksi))
                    .addGroup(panelTransaksiLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addGroup(panelTransaksiLayout.createSequentialGroup()
                                .addComponent(btnSimpanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnResetTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)
                            .addComponent(cmbBarangTransaksi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(txtJumlahTransaksi)
                            .addComponent(jLabel11)
                            .addComponent(txtPesanTransaksi)
                            .addComponent(cmbTipeTransaksi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelTransaksiLayout.setVerticalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCariTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariTransaksi))
                .addGap(18, 18, 18)
                .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBarangTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJumlahTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpanTransaksi)
                            .addComponent(btnResetTransaksi))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transaksi", panelTransaksi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanKategoriActionPerformed
        String nama = txtNamaKategori.getText().trim();
        if (nama.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nama kategori tidak boleh kosong!");
            return;
        }

        if (conn != null) {
            Kategori.create(conn, nama);
            txtNamaKategori.setText("");
            loadKategori();
        }
    }//GEN-LAST:event_btnSimpanKategoriActionPerformed

    private void btnHapusKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKategoriActionPerformed
        int selectedRow = tblKategori.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih kategori yang ingin dihapus!");
            return;
        }

        int id = (int) tblKategori.getValueAt(selectedRow, 0);
        String nama = (String) tblKategori.getValueAt(selectedRow, 1);

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Apakah Anda yakin ingin menghapus kategori '" + nama + "'?",
            "Konfirmasi Hapus",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            if (conn != null) {
                Kategori.delete(conn, id);
                txtNamaKategori.setText("");
                loadKategori();
            }
        }
    }//GEN-LAST:event_btnHapusKategoriActionPerformed

    private void btnEditKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditKategoriActionPerformed
        int selectedRow = tblKategori.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih kategori yang ingin diubah!");
            return;
        }

        int id = (int) tblKategori.getValueAt(selectedRow, 0);
        String nama = txtNamaKategori.getText().trim();

        if (nama.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nama kategori tidak boleh kosong!");
            return;
        }

        if (conn != null) {
            Kategori.update(conn, id, nama);
            txtNamaKategori.setText("");
            loadKategori();
        }
    }//GEN-LAST:event_btnEditKategoriActionPerformed

    private void tblKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKategoriMouseClicked
        int selectedRow = tblKategori.getSelectedRow();
        if (selectedRow != -1) {
            txtNamaKategori.setText(tblKategori.getValueAt(selectedRow, 1).toString());
        }
    }//GEN-LAST:event_tblKategoriMouseClicked

    private void btnCariKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKategoriActionPerformed
        String query = txtCariKategori.getText().trim();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblKategori.getModel();
        model.setRowCount(0);

        try {
            if (conn != null) {
                java.sql.ResultSet res = Kategori.find(conn, query);
                while (res != null && res.next()) {
                    model.addRow(new Object[]{
                        res.getInt("IdKategori"),
                        res.getString("NamaKategori")
                    });
                }
            }
        } catch (java.sql.SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error searching kategori", ex);
        }
        updateSummaryStatus("Hasil pencarian kategori: " + tblKategori.getRowCount());
    }//GEN-LAST:event_btnCariKategoriActionPerformed

    private void btnResetKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetKategoriActionPerformed
        txtCariKategori.setText("");
        txtNamaKategori.setText("");
        loadKategori();
    }//GEN-LAST:event_btnResetKategoriActionPerformed


    private void btnCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBarangActionPerformed
        String query = txtCariBarang.getText().trim();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblBarang.getModel();
        model.setRowCount(0);

        try {
            if (conn != null) {
                java.sql.ResultSet res = Barang.find(conn, query);
                while (res != null && res.next()) {
                    model.addRow(new Object[]{
                        res.getInt("IdBarang"),
                        res.getString("NamaBarang"),
                        res.getString("NamaKategori"),
                        res.getInt("JumlahBarang")
                    });
                }
            }
        } catch (java.sql.SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error searching barang", ex);
        }
        updateSummaryStatus("Hasil pencarian barang: " + tblBarang.getRowCount());
    }//GEN-LAST:event_btnCariBarangActionPerformed

    private void btnSimpanBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanBarangActionPerformed
        String namaBarang = txtNamaBarang.getText().trim();
        String stokText = txtStokBarang.getText().trim();
        String namaKategori = (String) cmbKategoriBarang.getSelectedItem();

        if (namaBarang.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nama barang tidak boleh kosong!");
            return;
        }

        int stokAwal = 0;
        if (!stokText.isEmpty()) {
            try {
                stokAwal = Integer.parseInt(stokText);
                if (stokAwal < 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Stok awal tidak boleh negatif!");
                    return;
                }
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Stok awal harus berupa angka valid!");
                return;
            }
        }

        if (namaKategori == null || namaKategori.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih kategori terlebih dahulu!");
            return;
        }

        try {
            if (conn != null) {
                // Find exact IdKategori
                int idKategori = -1;
                try (java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT IdKategori FROM Kategori WHERE NamaKategori = ?")) {
                    stmt.setString(1, namaKategori);
                    try (java.sql.ResultSet res = stmt.executeQuery()) {
                        if (res.next()) {
                            idKategori = res.getInt("IdKategori");
                        }
                    }
                }

                if (idKategori == -1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Kategori tidak ditemukan di database!");
                    return;
                }

                Barang.create(conn, idKategori, namaBarang, stokAwal, loggedInUser.getIdUser());
                txtNamaBarang.setText("");
                txtStokBarang.setText("");
                loadBarang();
                loadTransaksi();
            }
        } catch (java.sql.SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error saving barang", ex);
        }
    }//GEN-LAST:event_btnSimpanBarangActionPerformed

    private void btnHapusBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusBarangActionPerformed
        int selectedRow = tblBarang.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih barang yang ingin dihapus!");
            return;
        }

        int id = (int) tblBarang.getValueAt(selectedRow, 0);
        String nama = (String) tblBarang.getValueAt(selectedRow, 1);

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Apakah Anda yakin ingin menghapus barang '" + nama + "'?",
            "Konfirmasi Hapus",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            if (conn != null) {
                Barang.delete(conn, id);
                txtNamaBarang.setText("");
                txtStokBarang.setText("");
                loadBarang();
                loadTransaksi();
            }
        }
    }//GEN-LAST:event_btnHapusBarangActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        int selectedRow = tblBarang.getSelectedRow();
        if (selectedRow != -1) {
            txtNamaBarang.setText(tblBarang.getValueAt(selectedRow, 1).toString());
            cmbKategoriBarang.setSelectedItem(tblBarang.getValueAt(selectedRow, 2).toString());
            // Stok awal tidak diubah saat update barang karena update stok lewat transaksi
            txtStokBarang.setText(tblBarang.getValueAt(selectedRow, 3).toString());
            txtStokBarang.setEnabled(false); // disable edit stok
        }
    }//GEN-LAST:event_tblBarangMouseClicked

    private void btnEditBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBarangActionPerformed
        int selectedRow = tblBarang.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih barang yang ingin diubah!");
            return;
        }

        int id = (int) tblBarang.getValueAt(selectedRow, 0);
        String namaBarang = txtNamaBarang.getText().trim();
        String namaKategori = (String) cmbKategoriBarang.getSelectedItem();

        if (namaBarang.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nama barang tidak boleh kosong!");
            return;
        }

        if (namaKategori == null || namaKategori.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih kategori terlebih dahulu!");
            return;
        }

        try {
            if (conn != null) {
                int idKategori = -1;
                try (java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT IdKategori FROM Kategori WHERE NamaKategori = ?")) {
                    stmt.setString(1, namaKategori);
                    try (java.sql.ResultSet res = stmt.executeQuery()) {
                        if (res.next()) {
                            idKategori = res.getInt("IdKategori");
                        }
                    }
                }

                if (idKategori == -1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Kategori tidak ditemukan di database!");
                    return;
                }

                Barang.update(conn, id, idKategori, namaBarang);
                txtNamaBarang.setText("");
                txtStokBarang.setText("");
                txtStokBarang.setEnabled(true);
                loadBarang();
                loadTransaksi();
            }
        } catch (java.sql.SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error updating barang", ex);
        }
    }//GEN-LAST:event_btnEditBarangActionPerformed

    private void btnResetBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetBarangActionPerformed
        txtCariBarang.setText("");
        txtNamaBarang.setText("");
        txtStokBarang.setText("");
        txtStokBarang.setEnabled(true);
        if (cmbKategoriBarang.getItemCount() > 0) {
            cmbKategoriBarang.setSelectedIndex(0);
        }
        loadBarang();
    }//GEN-LAST:event_btnResetBarangActionPerformed

    private void btnSimpanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTransaksiActionPerformed
        String selectedBarang = (String) cmbBarangTransaksi.getSelectedItem();
        String tipe = (String) cmbTipeTransaksi.getSelectedItem();
        String jumlahText = txtJumlahTransaksi.getText().trim();

        if (selectedBarang == null || selectedBarang.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih barang terlebih dahulu!");
            return;
        }

        if (jumlahText.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Jumlah transaksi tidak boleh kosong!");
            return;
        }

        int jumlah = 0;
        try {
            jumlah = Integer.parseInt(jumlahText);
            if (jumlah <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Jumlah transaksi harus lebih dari 0!");
                return;
            }
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Jumlah transaksi harus berupa angka valid!");
            return;
        }

        int idBarang = Integer.parseInt(selectedBarang.split(" - ")[0]);

        try {
            if (conn != null) {
                // If "Keluar", check if enough stock
                if ("Keluar".equals(tipe)) {
                    try (java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT JumlahBarang FROM Barang WHERE IdBarang = ?")) {
                        stmt.setInt(1, idBarang);
                        try (java.sql.ResultSet res = stmt.executeQuery()) {
                            if (res.next()) {
                                int stok = res.getInt("JumlahBarang");
                                if (stok < jumlah) {
                                    javax.swing.JOptionPane.showMessageDialog(this, "Stok tidak mencukupi! Stok saat ini: " + stok);
                                    return;
                                }
                            }
                        }
                    }
                }

                String pesan = txtPesanTransaksi.getText().trim();
                Transaksi.create(conn, idBarang, loggedInUser.getIdUser(), tipe, jumlah, pesan);
                txtJumlahTransaksi.setText("");
                txtPesanTransaksi.setText("");
                loadTransaksi();
                loadBarang(); // Refresh barang to show updated stock
            }
        } catch (java.sql.SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error saving transaksi", ex);
        }
    }//GEN-LAST:event_btnSimpanTransaksiActionPerformed

    private void btnResetTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTransaksiActionPerformed
        txtCariTransaksi.setText("");
        txtJumlahTransaksi.setText("");
        txtPesanTransaksi.setText("");
        if (cmbBarangTransaksi.getItemCount() > 0) {
            cmbBarangTransaksi.setSelectedIndex(0);
        }
        cmbTipeTransaksi.setSelectedIndex(0);
        loadTransaksi();
    }//GEN-LAST:event_btnResetTransaksiActionPerformed

    private void btnCariTransaksiActionPerformed(java.awt.event.ActionEvent evt) {
        String query = txtCariTransaksi.getText().trim();
        if (query.isEmpty()) {
            loadTransaksi();
            return;
        }

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblTransaksi.getModel();
        model.setRowCount(0);

        String sql = "SELECT Transaksi.*, Barang.NamaBarang, Kategori.NamaKategori, Users.Username, Users.NamaLengkap "
                + "FROM Transaksi "
                + "LEFT OUTER JOIN Barang USING (IdBarang) "
                + "LEFT OUTER JOIN Kategori USING (IdKategori) "
                + "LEFT OUTER JOIN Users USING (IdUser) "
                + "WHERE CAST(IdTransaksi AS CHAR) LIKE ? "
                + "OR NamaBarang LIKE ? "
                + "OR NamaKategori LIKE ? "
                + "OR TipeTransaksi LIKE ? "
                + "OR Pesan LIKE ? "
                + "OR Username LIKE ? "
                + "OR NamaLengkap LIKE ? "
                + "ORDER BY Timestamp DESC";

        try {
            if (conn != null) {
                try (java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
                    String keyword = Koneksi.prepareLike(query);
                    for (int i = 1; i <= 7; i++) {
                        stmt.setString(i, keyword);
                    }
                    try (java.sql.ResultSet res = stmt.executeQuery()) {
                        while (res.next()) {
                            addTransaksiRow(model, res);
                        }
                    }
                }
            }
        } catch (java.sql.SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error searching transaksi", ex);
        }
        updateSummaryStatus("Hasil pencarian transaksi: " + tblTransaksi.getRowCount());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariBarang;
    private javax.swing.JButton btnCariKategori;
    private javax.swing.JButton btnCariTransaksi;
    private javax.swing.JButton btnEditBarang;
    private javax.swing.JButton btnEditKategori;
    private javax.swing.JButton btnHapusBarang;
    private javax.swing.JButton btnHapusKategori;
    private javax.swing.JButton btnResetBarang;
    private javax.swing.JButton btnResetKategori;
    private javax.swing.JButton btnResetTransaksi;
    private javax.swing.JButton btnSimpanBarang;
    private javax.swing.JButton btnSimpanKategori;
    private javax.swing.JButton btnSimpanTransaksi;
    private javax.swing.JComboBox<String> cmbBarangTransaksi;
    private javax.swing.JComboBox<String> cmbKategoriBarang;
    private javax.swing.JComboBox<String> cmbTipeTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelBarang;
    private javax.swing.JPanel panelKategori;
    private javax.swing.JPanel panelTransaksi;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTable tblKategori;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtCariBarang;
    private javax.swing.JTextField txtCariKategori;
    private javax.swing.JTextField txtCariTransaksi;
    private javax.swing.JTextField txtJumlahTransaksi;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNamaKategori;
    private javax.swing.JTextField txtPesanTransaksi;
    private javax.swing.JTextField txtStokBarang;
    // End of variables declaration//GEN-END:variables
}
