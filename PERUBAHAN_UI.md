# Perubahan UI Inventory

File ini merangkum tambahan yang dibuat untuk memperbagus tampilan dan pengalaman pakai aplikasi inventory.

## Palet Warna Profesional

Palet utama dibuat dari biru tua sampai biru muda:

| Nama Constant | HEX | RGB | Dipakai Untuk |
| --- | --- | --- | --- |
| `NAVY_950` | `#081B3A` | `8, 27, 58` | Header utama dan warna teks utama |
| `NAVY_900` | `#0F2D58` | `15, 45, 88` | Tombol reset dan header tabel |
| `BLUE_800` | `#1E40AF` | `30, 64, 175` | Tombol utama / simpan |
| `BLUE_700` | `#1D4ED8` | `29, 78, 216` | Tombol cari |
| `BLUE_500` | `#3B82F6` | `59, 130, 246` | Tombol edit |
| `BLUE_200` | `#BFDBFE` | `191, 219, 254` | Border input, combo box, chip, dan panel |
| `BLUE_100` | `#DBEAFE` | `219, 234, 254` | Seleksi tabel dan subtitle header |
| `BLUE_50` | `#EFF6FF` | `239, 246, 255` | Background aplikasi dan zebra row |

Catatan: `DANGER_COLOR` tetap merah `#B91C1C` khusus tombol hapus dan stok rendah supaya aksi berbahaya tetap kebaca jelas.

## File yang Diubah

- `src/main/java/id/team1/inventory/Inventory.java`
  - Menambahkan Nimbus Look and Feel supaya komponen Swing tampil lebih modern.
  - Menambahkan fallback logging kalau Nimbus gagal dipakai.

- `src/main/java/id/team1/inventory/MainFrame.java`
  - Menambahkan tema warna terpusat untuk background, teks, border, dan tombol.
  - Menambahkan header aplikasi dengan judul dan ringkasan jumlah data.
  - Menambahkan footer status untuk menampilkan kondisi load dan hasil pencarian.
  - Merapikan font, warna, ukuran input, combo box, tombol, tab, tabel, dan scroll pane.
  - Menambahkan focus border pada text field agar input yang aktif lebih jelas.
  - Menambahkan tooltip pada input, tab, dan tombol utama.
  - Menambahkan pengaturan lebar kolom tabel agar data lebih mudah dibaca.
  - Menambahkan zebra row dan highlight stok rendah yang lebih halus.
  - Memformat waktu transaksi menjadi `dd/MM/yyyy HH:mm`.
  - Mengaktifkan tombol `Cari` di tab Transaksi dengan pencarian berdasarkan ID, barang, kategori, tipe, atau pesan.

## Kode yang Ditambahkan

- `Inventory.java`
  - `javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");`
  - `try-catch` untuk fallback logging kalau Nimbus gagal dipakai.

- `MainFrame.java`
  - Constant palet warna: `NAVY_950`, `NAVY_900`, `BLUE_800`, `BLUE_700`, `BLUE_500`, `BLUE_200`, `BLUE_100`, `BLUE_50`, `BACKGROUND_COLOR`, `SURFACE_COLOR`, `TEXT_COLOR`, `MUTED_TEXT_COLOR`, `BORDER_COLOR`, `PRIMARY_COLOR`, `EDIT_COLOR`, `DANGER_COLOR`, `NEUTRAL_COLOR`, `SEARCH_COLOR`.
  - Label status baru: `lblKategoriInfo`, `lblBarangInfo`, `lblTransaksiInfo`, `lblFooterStatus`.
  - Method styling: `applyModernStyle()`, `stylePanels()`, `styleLabels()`, `styleFields()`, `styleCombos()`, `styleButton()`, `styleTable()`, `applyInputBorder()`, `styleTooltips()`.
  - Method layout tambahan: `rebuildMainLayout()`, `createHeaderPanel()`, `createFooterPanel()`, `createInfoChip()`.
  - Method tabel/status: `configureColumnWidths()`, `setColumnWidths()`, `updateSummaryStatus()`.
  - Method transaksi: `addTransaksiRow()`, `formatTimestamp()`, `btnCariTransaksiActionPerformed()`.
  - Action listener baru: `btnCariTransaksi.addActionListener(this::btnCariTransaksiActionPerformed);`.

## Kode yang Dihapus atau Diubah

- Tidak ada file yang dihapus.
- Warna lama yang campur biru, hijau, ungu, dan abu diganti menjadi palet biru profesional.
- `SUCCESS_COLOR` hijau diganti konsepnya menjadi `EDIT_COLOR` biru muda untuk tombol edit.
- Header tabel yang sebelumnya memakai warna hardcoded diganti memakai `NAVY_900`.
- Background aplikasi dan zebra row diganti ke `BLUE_50`.
- Border input yang sebelumnya abu-abu diganti ke `BLUE_200`.
- Highlight seleksi tabel diganti ke `BLUE_100`.
- Tampilan stok rendah di tabel barang diubah dari merah terang penuh menjadi merah muda halus dengan teks merah gelap.
- Kolom waktu transaksi diubah dari `Timestamp` mentah menjadi format `dd/MM/yyyy HH:mm`.
- Tombol `Cari` di tab Transaksi yang sebelumnya belum punya action sekarang aktif dan mencari ke beberapa kolom.

## Catatan Implementasi

- Blok `initComponents()` dari GUI Builder tidak diubah langsung.
- Layout utama dibungkus ulang lewat `rebuildMainLayout()` agar header dan footer bisa ditambahkan tanpa merusak form bawaan NetBeans.
- Status ringkasan di header diperbarui setiap data kategori, barang, atau transaksi dimuat ulang.

## Verifikasi

Kalau Maven tersedia, jalankan compile dari folder project:

```bash
mvn -q -DskipTests compile
```

Alternatif cek syntax Java yang dipakai saat perubahan ini dibuat:

```bash
mkdir -p target/codex-classes
javac -d target/codex-classes $(find src/main/java -name "*.java")
```
