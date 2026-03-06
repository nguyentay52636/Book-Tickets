package org.example.GUI.Components.FormProduct;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class FormProduct extends JPanel {

    private static final long serialVersionUID = 1L;

    public FormProduct() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 250));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 250));
        topPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Quản Lý Phim",
                TitledBorder.LEFT,
                TitledBorder.TOP));

        // Button bar (không gắn chức năng)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        buttonPanel.setOpaque(false);

        JButton btnView = createStyledButton("Xem");
        JButton btnEdit = createStyledButton("Sửa");
        JButton btnDelete = createStyledButton("Xóa");
        JButton btnAdd = createStyledButton("Thêm");

        // Icon và màu cho các nút (giống FormTypeProduct)
        btnAdd.setBackground(new Color(40, 167, 69)); // xanh lá
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setIcon(loadImageIcon("/org/example/GUI/resources/images/plus.png"));

        btnView.setBackground(new Color(0, 123, 255)); // xanh dương
        btnView.setForeground(Color.WHITE);
        btnView.setIcon(loadImageIcon("/org/example/GUI/resources/images/view.png"));

        btnEdit.setBackground(new Color(255, 193, 7)); // vàng/cam
        btnEdit.setForeground(Color.BLACK);
        btnEdit.setIcon(loadImageIcon("/org/example/GUI/resources/images/editing.png"));

        btnDelete.setIcon(loadImageIcon("/org/example/GUI/resources/images/bin.png"));

        buttonPanel.add(btnView);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnAdd);

        // Thanh tìm kiếm (chỉ giao diện)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchPanel.setOpaque(false);

        String[] options = { "MaPhim", "TenPhim", "DaoDien", "NamSanXuat" };
        JComboBox<String> comboBoxTimKiem = new JComboBox<>(options);
        comboBoxTimKiem.setPreferredSize(new Dimension(100, 30));
        comboBoxTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTextField txtTimKiem = new JTextField(20);
        txtTimKiem.setPreferredSize(new Dimension(200, 30));
        txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTimKiem.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));

        JButton btnLamMoi = createStyledButton("Làm mới");

        searchPanel.add(comboBoxTimKiem);
        searchPanel.add(txtTimKiem);
        searchPanel.add(btnLamMoi);

        topPanel.add(buttonPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        // Bảng dữ liệu chỉ hiển thị, không load từ BUS
        String[] columnNames = {
                "Mã Phim", "Thể Loại", "Tên Phim",
                "Thời Lượng", "Đạo Diễn", "Năm SX", "Giới Hạn Tuổi"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable tablePhim = new JTable(model);

        tablePhim.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablePhim.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablePhim.getTableHeader().setBackground(new Color(66, 103, 178));
        tablePhim.getTableHeader().setForeground(Color.WHITE);
        tablePhim.setRowHeight(40);

        JScrollPane scrollPane = new JScrollPane(tablePhim);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(new Color(230, 230, 235));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    private ImageIcon loadImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Không thể tải hình ảnh: " + path);
            return null;
        }
    }

    public void refreshTable() {
        // Intentional no-op: data loading will be implemented later
    }
}

