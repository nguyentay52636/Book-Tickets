// package org.example.ConnectDB;

// import java.sql.Connection;

// public class UtilsJDBC {
//     private static final String URL = "jdbc:mysql://localhost:3306/tv?useSSL=false&serverTimezone=UTC";
//     private static final String USER = "root";
//     private static final String PASSWORD = "";

//     private static Connection connection = null;

//     // Phương thức lấy kết nối
//     public static Connection getConnectDB() {
//         if (connection == null) {
//             try {
//                 Class.forName("com.mysql.cj.jdbc.Driver");
//                 connection = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
//                 System.out.println("✅ Kết nối CSDL thành công!");
//             } catch (ClassNotFoundException e) {
//                 System.err.println("❌ Không tìm thấy driver MySQL!");
//                 e.printStackTrace();
//             } catch (java.sql.SQLException e) {
//                 System.err.println("❌ Lỗi kết nối CSDL: " + e.getMessage());
//                 e.printStackTrace();
//             }
//         }
//         return connection;
//     }

//     // Phương thức đóng kết nối
//     public static void closeConnection() {
//         try {
//             if (connection != null) {
//                 connection.close();
//                 connection = null;
//                 System.out.println("✅ Đã đóng kết nối CSDL.");
//             }
//         } catch (java.sql.SQLException e) {
//             System.err.println("❌ Lỗi khi đóng kết nối: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }

//     // 🔥 Hàm main để test kết nối
//     public static void main(String[] args) {
//         System.out.println("🔄 Đang thử kết nối đến MySQL...");

//         // Gọi phương thức để lấy kết nối
//         Connection conn = getConnectDB();

//         if (conn != null) {
//             System.out.println("🎉 Kết nối thành công! Bắt đầu xử lý dữ liệu...");
//         } else {
//             System.out.println("❌ Kết nối thất bại!");
//         }

//         // Đóng kết nối sau khi kiểm tra xong
//         closeConnection();
//     }
// }
