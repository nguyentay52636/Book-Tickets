drop database bookticket;
create database bookticket;

use bookticket;

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
GioBatDau DATETIME,
GioKetThuc DATETIME
 
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE TheLoaiPhim (
 MaLoaiPhim INT AUTO_INCREMENT PRIMARY KEY,
 TenLoaiPhim VARCHAR(20) 

 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE Phim (
 MaPhim INT AUTO_INCREMENT PRIMARY KEY,
 MaLoaiPhim INT,
 TenPhim VARCHAR(20),
 ThoiLuong TIME,
 DaoDien VARCHAR(60),
 NamSanXuat DATETIME,
 AnhMauPhim VARCHAR(999),
 
 FOREIGN KEY (MaLoaiPhim) REFERENCES TheLoaiPhim(MaLoaiPhim)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE SanPham (
 MaSanPham INT AUTO_INCREMENT PRIMARY KEY,
 TenSanPham VARCHAR(100),
  HinhAnh VARCHAR(255),
 GiaBan INT,
 KichThuoc VARCHAR(4),
 SoLuong INT,
 TrangThai VARCHAR(10)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE Ghe (
 MaGhe INT AUTO_INCREMENT PRIMARY KEY,
 MaPhong INT, 
 MaLoaiGhe INT,
 LoaiGhe VARCHAR(5),
 soThuTu INT,
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
 NgayLapHoaDon DATE,
 SoLuongVe INT,
 TongTienVe INT,
 TongTienSanPham INT,
 MaKhuyenMai INT,
 TongTienGiam INT,
 TongThanhToan INT,

 
 FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
 FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE ChiTietHoaDon (
 MaHoaDon INT,
 MaVe INT,
 DGVE INT,
  
 FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
 FOREIGN KEY (MaVe) REFERENCES Ve(MaVe)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 CREATE TABLE ChiTietSanPham (
 MaHoaDon INT,
 MaSanPham INT NULL,
 Soluong INT,
 ThanhTien INT,
  
 FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
 FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

USE bookticket;

-- Insert into KhachHang
INSERT INTO KhachHang (HoTen, SDT, NgaySinh) VALUES 
('Nguyen Van A', '0123456789', '1990-01-01'),
('Tran Thi B', '0123456790', '1992-02-02'),
('Le Van C', '0123456791', '1985-03-03'),
('Pham Thi D', '0123456792', '1995-04-04'),
('Hoang Van E', '0123456793', '1988-05-05'),
('Vu Thi F', '0123456794', '1993-06-06'),
('Dang Van G', '0123456795', '1991-07-07');

-- Insert into NhanVien
INSERT INTO NhanVien (HoTen, NgaySinh, NgayVaoLam, LuongCoBan) VALUES 
('Nguyen Thi X', '1980-01-01', '2020-01-01', 10000000.000),
('Tran Van Y', '1982-02-02', '2021-02-02', 12000000.000),
('Le Thi Z', '1975-03-03', '2019-03-03', 15000000.000),
('Pham Van W', '1985-04-04', '2022-04-04', 11000000.000),
('Hoang Thi V', '1990-05-05', '2023-05-05', 13000000.000),
('Vu Van U', '1988-06-06', '2020-06-06', 14000000.000),
('Dang Thi T', '1992-07-07', '2021-07-07', 16000000.000);

-- Insert into LoaiGhe
INSERT INTO LoaiGhe (TenLoai, PhuThu, MauSacHienThi) VALUES 
('Thuong', 0.000, 'gray'),
('VIP', 20000.000, 'gold'),
('Couple', 30000.000, 'red'),
('Premium', 15000.000, 'blue'),
('Standard', 5000.000, 'green'),
('Economy', 0.000, 'black'),
('Luxury', 40000.000, 'purple');

-- Insert into PhongChieu
INSERT INTO PhongChieu (TenPhong, LoaiPhong) VALUES 
('Phong 1', '2D'),
('Phong 2', '3D'),
('Phong 3', 'IMAX'),
('Phong 4', '2D'),
('Phong 5', '3D'),
('Phong 6', '4D'),
('Phong 7', '2D');

-- Insert into SuatChieu
INSERT INTO SuatChieu (TenSuat, GioBatDau, GioKetThuc) VALUES 
('Suat sang 1', '2026-03-10 09:00:00', '2026-03-10 11:00:00'),
('Suat sang 2', '2026-03-10 10:00:00', '2026-03-10 12:00:00'),
('Suat chieu 1', '2026-03-10 13:00:00', '2026-03-10 15:00:00'),
('Suat chieu 2', '2026-03-10 14:00:00', '2026-03-10 16:00:00'),
('Suat toi 1', '2026-03-10 18:00:00', '2026-03-10 20:00:00'),
('Suat toi 2', '2026-03-10 19:00:00', '2026-03-10 21:00:00'),
('Suat dem', '2026-03-10 22:00:00', '2026-03-11 00:00:00');

-- Insert into TheLoaiPhim
INSERT INTO TheLoaiPhim (TenLoaiPhim) VALUES 
('Hanh dong'),
('Kinh di'),
('Hai huoc'),
('Lang man'),
('Khoa hoc vien tuong'),
('Hoat hinh'),
('Tai lieu');

-- Insert into SanPham
INSERT INTO SanPham (TenSanPham, GiaBan, KichThuoc, HinhAnh) VALUES 
('Popcorn', 50000, 'M', 'popcorn.jpg'),
('Nuoc ngot', 30000, 'S', 'soda.jpg'),
('Banh mi', 40000, 'L', 'snack.jpg'),
('Keo', 20000, 'S', 'candy.jpg'),
('Tra sua', 45000, 'M', 'tea.jpg'),
('Hot dog', 60000, 'L', 'hotdog.jpg'),
('Nachos', 55000, 'M', 'nachos.jpg');

-- Insert into Phim (after TheLoaiPhim)
INSERT INTO Phim (MaLoaiPhim, TenPhim, ThoiLuong, DaoDien, NamSanXuat, AnhMauPhim) VALUES 
(1, 'Phim 1', '02:00:00', 'Dao dien A', '2020-01-01 00:00:00', 'phim1.jpg'),
(2, 'Phim 2', '01:45:00', 'Dao dien B', '2021-01-01 00:00:00', 'phim2.jpg'),
(3, 'Phim 3', '02:15:00', 'Dao dien C', '2022-01-01 00:00:00', 'phim3.jpg'),
(4, 'Phim 4', '01:30:00', 'Dao dien D', '2023-01-01 00:00:00', 'phim4.jpg'),
(5, 'Phim 5', '02:30:00', 'Dao dien E', '2024-01-01 00:00:00', 'phim5.jpg'),
(6, 'Phim 6', '01:50:00', 'Dao dien F', '2025-01-01 00:00:00', 'phim6.jpg'),
(7, 'Phim 7', '02:10:00', 'Dao dien G', '2026-01-01 00:00:00', 'phim7.jpg');

-- Insert into Ghe (after LoaiGhe and PhongChieu)
INSERT INTO Ghe (MaPhong, MaLoaiGhe, LoaiGhe, soThuTu, TrangThai) VALUES 
(1, 1, 'A', 1, 'San sang'),
(1, 2, 'B', 2, 'San sang'),
(2, 3, 'C', 3, 'Da ban'),
(2, 4, 'D', 4, 'San sang'),
(3, 5, 'E', 5, 'San sang'),
(3, 6, 'F', 6, 'Da ban'),
(4, 7, 'G', 7, 'San sang');

-- Insert into SuatChieuPhim (after Phim, SuatChieu, PhongChieu)
INSERT INTO SuatChieuPhim (MaPhim, MaPhong, MaSuatChieu, NgayChieu, GiaVeGoc) VALUES 
(1, 1, 1, '2026-03-10', 100000),
(2, 2, 2, '2026-03-11', 120000),
(3, 3, 3, '2026-03-12', 150000),
(4, 4, 4, '2026-03-13', 110000),
(5, 5, 5, '2026-03-14', 130000),
(6, 6, 6, '2026-03-15', 140000),
(7, 7, 7, '2026-03-16', 160000);

-- Insert into Ve (after Ghe and SuatChieu)
INSERT INTO Ve (MaGhe, MaSuatChieu, GiaVe, TrangThai) VALUES 
(1, 1, 100000, 'Da ban'),
(2, 2, 120000, 'San sang'),
(3, 3, 150000, 'Da ban'),
(4, 4, 110000, 'San sang'),
(5, 5, 130000, 'Da ban'),
(6, 6, 140000, 'San sang'),
(7, 7, 160000, 'Da ban');

-- Insert into HoaDon (after KhachHang and NhanVien)
INSERT INTO HoaDon (MaKH, MaNV, NgayLapHoaDon, SoLuongVe, TongTienVe, TongTienSanPham, MaKhuyenMai, TongTienGiam, TongThanhToan) VALUES 
(1, 1, '2026-03-10', 2, 200000, 50000, 1, 10000, 240000),
(2, 2, '2026-03-11', 1, 120000, 30000, 2, 5000, 145000),
(3, 3, '2026-03-12', 3, 450000, 40000, 3, 20000, 470000),
(4, 4, '2026-03-13', 2, 220000, 20000, 4, 10000, 230000),
(5, 5, '2026-03-14', 1, 130000, 45000, 5, 15000, 160000),
(6, 6, '2026-03-15', 4, 560000, 60000, 6, 30000, 590000),
(7, 7, '2026-03-16', 2, 320000, 55000, 7, 20000, 355000);

-- Insert into ChiTietHoaDon (after HoaDon and Ve)
INSERT INTO ChiTietHoaDon (MaHoaDon, MaVe, DGVE) VALUES 
(1, 1, 100000),
(1, 2, 100000),
(2, 3, 120000),
(3, 4, 150000),
(3, 5, 150000),
(3, 6, 150000),
(4, 7, 110000);

-- Insert into ChiTietSanPham (after HoaDon and SanPham)
INSERT INTO ChiTietSanPham (MaHoaDon, MaSanPham, Soluong, ThanhTien) VALUES 
(1, 1, 1, 50000),
(2, 2, 1, 30000),
(3, 3, 1, 40000),
(4, 4, 1, 20000),
(5, 5, 1, 45000),
(6, 6, 1, 60000),
(7, 7, 1, 55000);