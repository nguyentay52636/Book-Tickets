drop database aa;
create database ticket;

use ticket;

CREATE TABLE KhachHang (
    MaKH INT AUTO_INCREMENT  PRIMARY KEY,
    HoTen VARCHAR(100) NOT NULL,
    SDT VARCHAR(15) UNIQUE,
    NgaySinh DATE,
    DiemTichLuy INT DEFAULT 0,
    HangThanhVien VARCHAR(50) DEFAULT N'Thành viên mới'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE NhanVien (
	MaNV INT AUTO_INCREMENT PRIMARY KEY,
	HoTen VARCHAR(100) NOT NULL,
	NgaySinh DATE,
	NgayVaoLam DATE,
	LuongCoBAn DECIMAL(15,3) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE LoaiGhe (
 MaLoaiGhe INT AUTO_INCREMENT PRIMARY KEY,
 TenLoai VARCHAR (50) default N'Thường',
 PhuThu DECIMAL (15,3),
 MauSacHienThi VARCHAR(10)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE PhongChieu (
MaPhong INT AUTO_INCREMENT PRIMARY KEY,
TenPhong VARCHAR(20),
LoaiPhong VARCHAR(5)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

 CREATE TABLE SuatChieu (
 MaSuatChieu INT AUTO_INCREMENT PRIMARY KEY,
 TenSuat VARCHAR(20), 
 GBD DATETIME,
 GKT DATETIME
 
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE TheLoaiPhim (
 MaTheLoaiPhim INT AUTO_INCREMENT PRIMARY KEY,
 TenTheLoaiPhim VARCHAR(20) 
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE Phim (
 MaPhim INT AUTO_INCREMENT PRIMARY KEY,
 MaTheLoaiPhim INT,
 TenPhim VARCHAR(20),
 ThoiLuong TIME,
 DaoDien VARCHAR(60),
 NamSanXuat INT,
 GioiHanTuoi INT,
 PosterURL VARCHAR(999),
 
 FOREIGN KEY (MaTheLoaiPhim) REFERENCES TheLoaiPhim(MaTheLoaiPhim)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE SanPham (
 MaSanPham INT AUTO_INCREMENT PRIMARY KEY,
 TenSanPham VARCHAR(20),
 DinhGia INT,
 Size VARCHAR(4),
 ExamplePicture text,
 LoaiSanPham VARCHAR(20)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE Ghe (
 MaGhe INT AUTO_INCREMENT PRIMARY KEY,
 MaPhong INT, 
 MaLoaiGhe INT,
 HANG VARCHAR(5),
 SO INT,
 TrangThai VARCHAR(10),
 
 FOREIGN KEY (MaLoaiGhe) REFERENCES LoaiGhe(MaLoaiGhe),
 FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
CREATE TABLE SuatChieuPhim (
 MaPhim INT,
 MaPhong INT,
 MaSuatChieu INT,
 NgayChieu DATE,
 GiaVeGoc INT,
 
 FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim),
 FOREIGN KEY (MaSuatChieu) REFERENCES SuatChieu(MaSuatChieu),
 FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
CREATE TABLE Ve (
 MaVe INT AUTO_INCREMENT  PRIMARY KEY,
 MaGhe INT,
 MaSuatChieu INT,
 GiaVe INT,
 TrangThai varchar(20),
 
 FOREIGN KEY (MaGhe) REFERENCES Ghe(MaGhe),
 FOREIGN KEY (MaSuatChieu) REFERENCES SuatChieu(MaSuatChieu)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE HoaDon (
 MaHoaDon INT AUTO_INCREMENT  PRIMARY KEY,
 MaKH INT,
 MaNV INT,
 NgayBan DATE,
 SoLuongVe INT,
 TongTienVe INT,
 TongTienSanPham INT,
 MaKhuyenMai INT,
 TongTienGiam INT,
 TongThanhToan INT,

 
 FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
 FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE CTHD_Ve (
 MaHoaDon INT,
 MaVe INT,
 DGVE INT,
  
 FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
 FOREIGN KEY (MaVe) REFERENCES Ve(MaVe)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE CTHD_SP (
 MaHoaDon INT,
 MaSanPham INT NULL,
 Soluong INT,
 ThanhTien INT,
  
 FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
 FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
