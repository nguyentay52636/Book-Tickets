## 🎬 Book Tickets – Ứng dụng đặt vé xem phim

[![Java](https://img.shields.io/badge/Java-8%2B-007396?style=for-the-badge&logo=openjdk&logoColor=white)]()
[![Java Swing](https://img.shields.io/badge/Java-Swing-5382A1?style=for-the-badge&logo=java&logoColor=white)]()
[![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)]()
[![Desktop App](https://img.shields.io/badge/Platform-WinForms%20Style-0E76A8?style=for-the-badge)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)]()

---

## 📦 Database Diagram
🔗 [Xem chi tiết DB tại dbdiagram](https://dbdiagram.io/)

## 🧾 Giới thiệu

Ứng dụng **Book Tickets** là phần mềm desktop viết bằng **Java Swing** kết nối **MySQL**, mô phỏng quy trình **đặt vé xem phim** trên giao diện kiểu **WinForms** (cửa sổ, form, button…).

## 🌟 Tính năng chính

- **Quản lý phim**
  - Thêm / sửa / xóa thông tin phim
  - Quản lý thể loại, thời lượng, mô tả, poster
- **Quản lý suất chiếu**
  - Tạo lịch chiếu theo phòng, giờ chiếu, ngày chiếu
  - Liên kết phim ↔ phòng chiếu
- **Đặt vé / bán vé**
  - Chọn phim → chọn suất chiếu → chọn ghế
  - Tính tiền, áp dụng khuyến mãi (nếu có)
  - In / xuất hóa đơn (PDF hoặc in trực tiếp, tùy cấu hình dự án)
- **Quản lý khách hàng**
  - Lưu thông tin khách hàng, lịch sử mua vé
- **Quản lý người dùng & phân quyền**
  - Đăng nhập, đổi mật khẩu
  - Phân quyền: quản trị, nhân viên bán vé, v.v.
- **Thống kê – báo cáo**
  - Doanh thu theo ngày / tháng / năm
  - Thống kê số vé bán ra, suất chiếu bán chạy

## 🧱 Công nghệ sử dụng

- **Ngôn ngữ**: Java (JDK 8+ hoặc 11+)
- **Giao diện**: Java Swing (WinForms-style UI)
- **CSDL**: MySQL
- **Thư viện khác** (tùy dự án thực tế):
  - **JDBC** để kết nối MySQL
  - Thư viện xuất PDF / chart / date picker (nếu có)

## ⚙️ Yêu cầu hệ thống

- **JDK**: 8 trở lên (khuyến nghị 11+)
- **MySQL Server**: 5.7+ hoặc 8.x
- **IDE**: IntelliJ IDEA / NetBeans / Eclipse (hoặc IDE bất kỳ hỗ trợ Java)
- **Hệ điều hành**: Windows / macOS / Linux

## 🗄️ Cấu trúc thư mục (tổng quan)

- **`src/main/java/Layout/models/BackEnd`**:
  - **`DTO`**: lớp dữ liệu (Phim, Khách hàng, Vé, Suất chiếu, Nhân viên, v.v.)
  - **`DAO`**: truy vấn database (INSERT, UPDATE, DELETE, SELECT)
  - **`BUS`**: xử lý nghiệp vụ (check dữ liệu, logic đặt vé, khuyến mãi, v.v.)
- **`src/main/java/Layout/models/FrontEnd`**:
  - Các `Form` giao diện: đăng nhập, đặt vé, quản lý phim, khách hàng, thống kê, v.v.
- **`src/main/resources`**:
  - File cấu hình, tài nguyên (nếu có)

*(Tên package/thư mục có thể khác đôi chút tùy chỉnh sửa thực tế của bạn.)*

## 🛠️ Cài đặt & cấu hình

1. **Clone project**
   ```bash
   git clone https://github.com/nguyentay52636/Book-Tickets.git
   cd Book-Tickets
   ```

2. **Tạo database MySQL**
   - Tạo database, ví dụ:
     ```sql
     CREATE DATABASE book_tickets CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
     ```
   - Import file `.sql` cấu trúc & dữ liệu mẫu (nếu có trong thư mục `data/`).

3. **Cấu hình kết nối database**
   - Mở class kết nối (ví dụ `ConnectionDB.java` hoặc tương đương).
   - Sửa **URL / username / password** cho phù hợp MySQL trên máy bạn:
     ```java
     String url = "jdbc:mysql://localhost:3306/book_tickets?useSSL=false&serverTimezone=UTC";
     String user = "root";
     String password = "your_password";
     ```

4. **Build & run**
   - Mở project bằng IDE (IntelliJ / NetBeans / Eclipse).
   - Đảm bảo đã add thư viện MySQL JDBC Driver.
   - Chạy class `Main` hoặc `MainFrame` (tùy entry point của dự án).

## ▶️ Cách sử dụng nhanh

- **Bước 1**: Đăng nhập bằng tài khoản mặc định (admin) nếu có sẵn trong database.
- **Bước 2**: Vào màn hình **Quản lý phim** để kiểm tra / thêm phim.
- **Bước 3**: Vào **Lịch chiếu** để tạo suất chiếu cho phim.
- **Bước 4**: Vào **Bán vé / Đặt vé**, chọn phim → suất chiếu → ghế → xác nhận.
- **Bước 5**: Kiểm tra **Thống kê** để xem báo cáo doanh thu, số vé bán, v.v.

## 📌 Ghi chú

- Nếu thay đổi tên database, user, password MySQL, hãy cập nhật lại trong class kết nối.
- Nếu có lỗi font tiếng Việt, kiểm tra **encoding** của IDE & font sử dụng trong Swing.

## 📄 Giấy phép

Dự án được phát triển cho mục đích **học tập / đồ án môn học**.  
Bạn có thể tự do fork, chỉnh sửa và mở rộng theo nhu cầu cá nhân.


mvn clean install
mvn exec:java
mvn exec:java -Dexec.mainClass="org.example.Main"