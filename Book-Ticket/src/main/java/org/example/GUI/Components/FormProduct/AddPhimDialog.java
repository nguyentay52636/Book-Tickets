package org.example.GUI.Components.FormProduct;

import org.example.BUS.PhimBUS;
import org.example.DTO.PhimDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class AddPhimDialog extends JDialog {

    private final PhimBUS phimBUS = new PhimBUS();
    private final FormProduct parent;
    private JComboBox<Integer> cbTheLoai;
    private JTextField txtTenPhim;
    private JTextField txtThoiLuong;
    private JTextField txtDaoDien;
    private JTextField txtNamSX;
    private JTextField txtGioiHanTuoi;
    private JTextField txtPosterURL;

    public AddPhimDialog(FormProduct parent) {
        this.parent = parent;
        setTitle("Thêm Phim Mới");
        setSize(500, 400);
        setModal(true);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel lblTheLoai = createStyledLabel("Thể loại:", labelFont);
        cbTheLoai = new JComboBox<>();
        loadTheLoai();
        cbTheLoai.setFont(textFont);

        JLabel lblTenPhim = createStyledLabel("Tên phim:", labelFont);
        txtTenPhim = createStyledTextField("", textFont);

        JLabel lblThoiLuong = createStyledLabel("Thời lượng (HH:MM:SS):", labelFont);
        txtThoiLuong = createStyledTextField("", textFont);

        JLabel lblDaoDien = createStyledLabel("Đạo diễn:", labelFont);
        txtDaoDien = createStyledTextField("", textFont);

        JLabel lblNamSX = createStyledLabel("Năm sản xuất:", labelFont);
        txtNamSX = createStyledTextField("", textFont);

        JLabel lblGioiHanTuoi = createStyledLabel("Giới hạn tuổi:", labelFont);
        txtGioiHanTuoi = createStyledTextField("", textFont);

        JLabel lblPosterURL = createStyledLabel("Poster URL:", labelFont);
        txtPosterURL = createStyledTextField("", textFont);

        panel.add(lblTheLoai);
        panel.add(cbTheLoai);
        panel.add(lblTenPhim);
        panel.add(txtTenPhim);
        panel.add(lblThoiLuong);
        panel.add(txtThoiLuong);
        panel.add(lblDaoDien);
        panel.add(txtDaoDien);
        panel.add(lblNamSX);
        panel.add(txtNamSX);
        panel.add(lblGioiHanTuoi);
        panel.add(txtGioiHanTuoi);
        panel.add(lblPosterURL);
        panel.add(txtPosterURL);

        JButton btnAdd = new JButton("Thêm");
        btnAdd.setBackground(new Color(0, 123, 255));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e -> addPhim());

        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(220, 53, 69));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnCancel);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadTheLoai() {
        ArrayList<Integer> listMaTheLoai = phimBUS.getListMaTheLoai();
        for (int ma : listMaTheLoai) {
            cbTheLoai.addItem(ma);
        }
    }

    private void addPhim() {
        int maTheLoai = (int) cbTheLoai.getSelectedItem();
        String tenPhim = txtTenPhim.getText();
        String thoiLuong = txtThoiLuong.getText();
        String daoDien = txtDaoDien.getText();
        int namSX = Integer.parseInt(txtNamSX.getText());
        int gioiHanTuoi = Integer.parseInt(txtGioiHanTuoi.getText());
        String posterURL = txtPosterURL.getText();

        PhimDTO phim = new PhimDTO(0, maTheLoai, tenPhim, thoiLuong, daoDien, namSX, gioiHanTuoi, posterURL);
        phimBUS.add(phim);
        parent.refreshTable();
        dispose();
    }

    private JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(new Color(33, 37, 41));
        return label;
    }

    private JTextField createStyledTextField(String text, Font font) {
        JTextField field = new JTextField(text);
        field.setFont(font);
        field.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        field.setBackground(Color.WHITE);
        return field;
    }
}

