package org.example.GUI.Components.FormCustomer;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FormCustomer extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtTimKiem;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnView;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnAdd;
    private JButton btnNhap;
    private JButton btnXuat;
    private JComboBox<String> comboBox;
    private JButton btnLamMoi;
    private JComboBox<String> cbHienThi;

    public FormCustomer() {
        init();
    }

    public FormCustomer(String title) {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 250));

        JPanel up = new JPanel();
        up.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        up.setBackground(new Color(245, 245, 250));
        up.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Quản Lý Khách Hàng", TitledBorder.LEFT, TitledBorder.TOP));

        btnView = createStyledButton("Xem", new Color(102, 187, 106), Color.WHITE, "/org/example/GUI/resources/images/view.png");
        btnView.addActionListener(e -> { });
        up.add(btnView);

        btnEdit = createStyledButton("Sửa", new Color(255, 193, 7), Color.WHITE, "/org/example/GUI/resources/images/editing.png");
        btnEdit.addActionListener(e -> { });
        up.add(btnEdit);

        btnDelete = createStyledButton("Xóa", new Color(220, 53, 69), Color.WHITE, "/org/example/GUI/resources/images/bin.png");
        btnDelete.addActionListener(e -> { });
        up.add(btnDelete);

        btnAdd = createStyledButton("Thêm", new Color(0, 123, 255), Color.WHITE, "/org/example/GUI/resources/images/plus.png");
        btnAdd.addActionListener(e -> {});
        up.add(btnAdd);

        btnNhap = createStyledButton("Nhập Excel", new Color(153, 102, 255), Color.WHITE, "/org/example/GUI/resources/images/icons8_ms_excel_30px.png");
        btnNhap.addActionListener(e -> {  });
        up.add(btnNhap);

        btnXuat = createStyledButton("Xuất Excel", new Color(153, 102, 255), Color.WHITE, "/org/example/GUI/resources/images/icons8_ms_excel_30px.png");
        btnXuat.addActionListener(e -> {});
        up.add(btnXuat);

        JPanel timkiem = new JPanel();
        timkiem.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        timkiem.setBackground(new Color(245, 245, 250));

        cbHienThi = new JComboBox<>(new String[] { "Đang hoạt động", "Đã xóa" });
        cbHienThi.setPreferredSize(new Dimension(150, 40));
        cbHienThi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbHienThi.setBackground(Color.WHITE);
        cbHienThi.addActionListener(e -> { /* chỉ giao diện */ });
        timkiem.add(cbHienThi);

        String[] options = { "MaKH", "TenKH", "SDT", "DiaChi" };
        comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(100, 40));
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.addActionListener(e -> txtTimKiem.setText((String) comboBox.getSelectedItem()));

        txtTimKiem = new JTextField(20);
        txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTimKiem.setPreferredSize(new Dimension(200, 40));
        txtTimKiem.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        txtTimKiem.setBackground(Color.WHITE);
        txtTimKiem.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel comTk = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        comTk.setBackground(new Color(245, 245, 250));
        comTk.add(comboBox);
        comTk.add(txtTimKiem);
        comTk.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Tìm kiếm"));
        timkiem.add(comTk);

        btnLamMoi = createStyledButton("Làm mới", new Color(100, 181, 246), Color.WHITE, "/org/example/GUI/resources/images/icons8_data_backup_30px.png");
        btnLamMoi.addActionListener(e -> { /* chỉ giao diện */ });
        timkiem.add(btnLamMoi);

        JPanel chucNang = new JPanel(new GridLayout(2, 1, 0, 10));
        chucNang.setBackground(new Color(245, 245, 250));
        chucNang.add(up);
        chucNang.add(timkiem);

        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JComponent) {
                    ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                }
                if (row % 2 == 0) {
                    c.setBackground(new Color(240, 240, 245));
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        };
        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        Font fontHeader = new Font("Segoe UI", Font.BOLD, 16);
        table.setFont(font);
        table.getTableHeader().setFont(fontHeader);
        table.getTableHeader().setBackground(new Color(66, 103, 178));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(40);
        table.setGridColor(new Color(220, 220, 220));
        table.setShowGrid(true);

        model = new DefaultTableModel();
        model.addColumn("Mã KH");
        model.addColumn("Tên KH");
        model.addColumn("Ngày sinh");
        model.addColumn("Địa chỉ");
        model.addColumn("SĐT");
        model.addColumn("Trạng thái");
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(chucNang, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color bg, Color fg, String iconPath) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(165, 44));
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        if (iconPath != null) {
            button.setIcon(loadImageIcon(iconPath));
        }
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bg.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bg);
            }
        });
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

    public JPanel getPanel() {
        return this;
    }

    public JPanel getPanelDisable() {
        return this;
    }
}
