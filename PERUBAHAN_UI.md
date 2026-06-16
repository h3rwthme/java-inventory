# 📦 Inventory System — Dokumentasi Perubahan UI/UX

> **Versi:** 2.0 — Redesign Profesional  
> **Tanggal:** 16 Juni 2026  
> **Tim:** Team 1

---

## 🎯 Ringkasan Perubahan

Redesign total UI/UX aplikasi dari tampilan standar menjadi **premium, modern, dan client-ready**. Fokus utama pada pengalaman pengguna yang bersih, profesional, dan mudah digunakan.

---

## 🔐 Halaman Login — Redesign Total

### Layout Baru: Split Panel
| Sebelum | Sesudah |
|---------|---------|
| Form login sederhana di tengah | **Split panel** — ilustrasi kiri + form kanan |
| Tidak ada visual branding | Ilustrasi warehouse profesional dengan branding |
| Tampilan plain/kosong | Layout premium seperti SaaS modern |

### Fitur Baru Login
- **Ilustrasi visual** — Gambar inventory/warehouse di panel kiri
- **Branding** — Nama sistem + tagline di panel ilustrasi
- **Placeholder text** — Input menampilkan hint saat kosong
- **Focus glow effect** — Input field bercahaya biru saat aktif
- **Gradient button** — Tombol Sign In dengan efek gradient + hover
- **Decorative elements** — Lingkaran dekoratif di background
- **Logo emoji** — Ikon 📦 sebagai logo visual

### Warna Login
| Elemen | Warna |
|--------|-------|
| Background | `#FFFFFF` Putih bersih |
| Panel ilustrasi | Gradasi `#EFF6FF` → `#DBEAFE` (biru muda) |
| Input border | `#D1D5DB` (abu terang) |
| Input focus | `#3B82F6` (biru aksen) |
| Tombol | Gradasi `#3B82F6` → `#6366F1` |
| Teks utama | `#111827` (hitam lembut) |

---

## 📊 Dashboard Inventory — Perubahan Besar

### Header Baru
| Sebelum | Sesudah |
|---------|---------|
| Header biru gelap solid | **Header putih** dengan garis aksen biru di bawah |
| Teks putih di background gelap | Teks biru navy di background putih |
| Tanpa subtitle | Subtitle "Manajemen Stok & Transaksi" |
| Chip user gelap | **Chip user** warna biru muda `#EFF6FF` |
| Tombol logout merah solid | Tombol logout **outline merah muda** (lebih soft) |

### Tab Navigation
- Setiap tab kini memiliki **ikon emoji**:
  - 📂 Kategori
  - 📦 Barang
  - 📋 Transaksi

### Tabel Data
| Sebelum | Sesudah |
|---------|---------|
| Header tabel biru gelap/navy | **Header abu-abu terang** `#F1F5F9` |
| Teks header putih | Teks header **slate gelap** `#334155` |
| Tanpa bottom accent | **Garis biru** 2px di bawah header |
| Row height 34px | Row height **38px** (lebih lapang) |
| Grid color biru | Grid **abu-abu halus** `#E2E8F0` |
| Cell padding 10px | Cell padding **12px** |

### User Info
- Chip user menampilkan **👤 + Nama** dengan background biru muda
- Tombol logout menggunakan **warna merah muda** (bukan merah solid)
- Hover effect yang smooth dan subtle

---

## 🐛 Bug Fixes

### 1. Tombol Simpan Tidak Aktif Setelah Reset (Barang)
- **Masalah:** Setelah klik baris tabel, `btnSimpanBarang` di-disable. Klik "Reset" tidak mengaktifkan kembali.
- **Solusi:** `btnResetBarang` sekarang memanggil `btnSimpanBarang.setEnabled(true)` + `tblBarang.clearSelection()`

### 2. Tombol Simpan Tidak Aktif Setelah Reset (Kategori)
- **Masalah:** Sama seperti barang, tombol simpan kategori tetap disabled setelah reset.
- **Solusi:** `btnResetKategori` sekarang memanggil `tblKategori.clearSelection()` untuk reset state

### 3. Seleksi Tabel Tidak Dibersihkan
- **Masalah:** Setelah reset form, baris tabel masih terseleksi, menyebabkan confusion.
- **Solusi:** Semua fungsi reset sekarang memanggil `clearSelection()` pada tabel terkait

---

## 🎨 Palet Warna Keseluruhan

Seluruh aplikasi kini menggunakan **tema terang/bright** yang konsisten:

| Nama | Hex | Kegunaan |
|------|-----|----------|
| White | `#FFFFFF` | Background utama, card |
| Slate 50 | `#F8FAFC` | Background panel |
| Slate 100 | `#F1F5F9` | Header tabel |
| Blue 50 | `#EFF6FF` | Baris zebra, chip user |
| Blue 100 | `#DBEAFE` | Selection highlight |
| Blue 500 | `#3B82F6` | Aksen utama, tombol |
| Blue 900 | `#1E3A8A` | Teks judul header |
| Red 50 | `#FEF2F2` | Background tombol logout |
| Red 500 | `#DC2626` | Teks error, tombol hapus |

---

## 🏗️ Struktur File

```
src/main/java/id/team1/inventory/
├── Inventory.java        ← Entry point (Nimbus L&F + anti-aliasing)
├── LoginFrame.java       ← 🔄 Redesign total (split panel + ilustrasi)
├── MainFrame.java        ← 🔄 Header putih, tabel terang, bug fixes
├── AppUser.java           ← Model user (tidak berubah)
├── Barang.java            ← Model barang (tidak berubah)
├── Kategori.java          ← Model kategori (tidak berubah)
├── Koneksi.java           ← Database connection (tidak berubah)
└── Transaksi.java         ← Model transaksi (tidak berubah)

src/main/resources/images/
└── login_bg.png           ← 🆕 Ilustrasi warehouse untuk login
```

---

## ⚙️ Look & Feel

Aplikasi sekarang menggunakan **Nimbus Look & Feel** dengan kustomisasi warna:
- Base: Biru (`#3B82F6`)
- Background: Putih (`#FFFFFF`)
- Selection: Biru muda (`#DBEAFE`)
- Text: Slate gelap (`#1E293B`)
- Anti-aliasing diaktifkan secara global

---

## 📋 Cara Menjalankan

```bash
# Compile & Run
mvn clean compile exec:java

# Atau build JAR
mvn clean package
java -jar target/java-inventory-1.0.jar
```

### Default Login
| Username | Password |
|----------|----------|
| `admin`  | `admin123` |

---

*© 2026 Team 1 — Inventory Management System*
