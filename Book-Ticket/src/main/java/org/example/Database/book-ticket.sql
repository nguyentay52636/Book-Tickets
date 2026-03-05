DROP DATABASE IF EXISTS ticket;
CREATE DATABASE ticket;

USE ticket;

-- ==================== BẢNG CHÍNH ====================

CREATE TABLE KhachHang (
    MaKH INT AUTO_INCREMENT PRIMARY KEY,
    HoTen VARCHAR(100) NOT NULL,
    SDT VARCHAR(15) UNIQUE,
    NgaySinh DATE,
    DiemTichLuy INT DEFAULT 0,
    HangThanhVien VARCHAR(50) DEFAULT N'Thành viên mới'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE NhanVien (
    MaNV INT AUTO_INCREMENT PRIMARY KEY,
    HoTen VARCHAR(100) NOT NULL,
    NgaySinh DATE,
    NgayVaoLam DATE,
    LuongCoBan DECIMAL(15,3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE LoaiGhe (
    MaLoaiGhe INT AUTO_INCREMENT PRIMARY KEY,
    TenLoai VARCHAR(50) DEFAULT N'Thường',
    PhuThu DECIMAL(15,3) DEFAULT 0,
    MauSacHienThi VARCHAR(10) DEFAULT '#ffffff'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE PhongChieu (
    MaPhong INT AUTO_INCREMENT PRIMARY KEY,
    TenPhong VARCHAR(20) NOT NULL,
    LoaiPhong VARCHAR(10) DEFAULT '2D'   -- 2D, 3D, 4D...
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE SuatChieu (
    MaSuatChieu INT AUTO_INCREMENT PRIMARY KEY,
    TenSuat VARCHAR(50) NOT NULL,        -- tăng độ dài
    GBD DATETIME NOT NULL,
    GKT DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE TheLoaiPhim (
    MaTheLoaiPhim INT AUTO_INCREMENT PRIMARY KEY,
    TenTheLoaiPhim VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Phim (
    MaPhim INT AUTO_INCREMENT PRIMARY KEY,
    MaTheLoaiPhim INT,
    TenPhim VARCHAR(255) NOT NULL,       -- tăng lên 255
    ThoiLuong TIME NOT NULL,
    DaoDien VARCHAR(100),
    NamSanXuat INT,
    GioiHanTuoi INT DEFAULT 0,
    PosterURL VARCHAR(500),
    
    FOREIGN KEY (MaTheLoaiPhim) REFERENCES TheLoaiPhim(MaTheLoaiPhim)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE SanPham (
    MaSanPham INT AUTO_INCREMENT PRIMARY KEY,
    TenSanPham VARCHAR(100) NOT NULL,
    DinhGia INT NOT NULL,
    Size VARCHAR(10),
    ExamplePicture TEXT,
    LoaiSanPham VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Ghe (
    MaGhe INT AUTO_INCREMENT PRIMARY KEY,
    MaPhong INT NOT NULL,
    MaLoaiGhe INT NOT NULL,
    HANG VARCHAR(5) NOT NULL,
    SO INT NOT NULL,
    -- Bỏ TrangThai vì sẽ kiểm tra qua bảng Ve
    
    FOREIGN KEY (MaLoaiGhe) REFERENCES LoaiGhe(MaLoaiGhe),
    FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong),
    UNIQUE KEY uk_ghe (MaPhong, HANG, SO)   -- 1 ghế chỉ tồn tại 1 lần trong 1 phòng
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==================== BẢNG SUẤT CHIẾU CỤ THỂ ====================

CREATE TABLE SuatChieuPhim (
    MaSuatChieuPhim INT AUTO_INCREMENT PRIMARY KEY,   -- ← thêm PK mới
    MaPhim INT NOT NULL,
    MaPhong INT NOT NULL,
    MaSuatChieu INT NOT NULL,
    NgayChieu DATE NOT NULL,
    GiaVeGoc INT NOT NULL,
    
    FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim),
    FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong),
    FOREIGN KEY (MaSuatChieu) REFERENCES SuatChieu(MaSuatChieu),
    UNIQUE KEY uk_suat_chieu (MaPhong, MaSuatChieu, NgayChieu)  -- không cho 2 phim cùng 1 suất
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==================== VÉ & HÓA ĐƠN ====================

CREATE TABLE Ve (
    MaVe INT AUTO_INCREMENT PRIMARY KEY,
    MaGhe INT NOT NULL,
    MaSuatChieuPhim INT NOT NULL,        -- ← sửa thành reference SuatChieuPhim
    GiaVe INT NOT NULL,
    TrangThai VARCHAR(20) DEFAULT 'Chưa bán',  -- Đã bán / Đã hủy / Đã check-in...
    
    FOREIGN KEY (MaGhe) REFERENCES Ghe(MaGhe),
    FOREIGN KEY (MaSuatChieuPhim) REFERENCES SuatChieuPhim(MaSuatChieuPhim)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng Khuyến mãi (mới thêm)
CREATE TABLE KhuyenMai (
    MaKhuyenMai INT AUTO_INCREMENT PRIMARY KEY,
    TenKhuyenMai VARCHAR(100) NOT NULL,
    LoaiGiam ENUM('phan_tram', 'tien_mat') NOT NULL,
    GiaTriGiam DECIMAL(10,2) NOT NULL,
    NgayBatDau DATE NOT NULL,
    NgayKetThuc DATE NOT NULL,
    DieuKien INT DEFAULT 0 COMMENT 'Số tiền tối thiểu hoặc 0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE HoaDon (
    MaHoaDon INT AUTO_INCREMENT PRIMARY KEY,
    MaKH INT,
    MaNV INT NOT NULL,
    NgayBan DATETIME DEFAULT CURRENT_TIMESTAMP,
    SoLuongVe INT DEFAULT 0,
    TongTienVe INT DEFAULT 0,
    TongTienSanPham INT DEFAULT 0,
    MaKhuyenMai INT,
    TongTienGiam INT DEFAULT 0,
    TongThanhToan INT DEFAULT 0,
    
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKhuyenMai) REFERENCES KhuyenMai(MaKhuyenMai)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE CTHD_Ve (
    MaHoaDon INT NOT NULL,
    MaVe INT NOT NULL,
    DGVE INT NOT NULL,
    
    PRIMARY KEY (MaHoaDon, MaVe),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaVe) REFERENCES Ve(MaVe)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE CTHD_SP (
    MaHoaDon INT NOT NULL,
    MaSanPham INT NOT NULL,              -- bỏ NULL vì không có lý do để NULL
    SoLuong INT NOT NULL,
    ThanhTien INT NOT NULL,
    
    PRIMARY KEY (MaHoaDon, MaSanPham),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;