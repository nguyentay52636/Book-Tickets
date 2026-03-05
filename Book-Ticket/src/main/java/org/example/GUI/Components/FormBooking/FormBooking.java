package org.example.GUI.Components.FormBooking;

import org.example.BUS.PhimBUS;
import org.example.BUS.SuatChieuPhimBUS;
import org.example.BUS.VeBUS;
import org.example.BUS.HoaDonBUS;
import org.example.DTO.PhimDTO;
import org.example.DTO.SuatChieuPhimDTO;
import org.example.DTO.VeDTO;
import org.example.DTO.HoaDonDTO;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class FormBooking extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cbTheLoaiPhim;
    private JTable tablePhim;
    private DefaultTableModel modelPhim;
    private PhimBUS phimBUS = new PhimBUS();
    private SuatChieuPhimBUS suatChieuPhimBUS = new SuatChieuPhimBUS();
    private VeBUS veBUS = new VeBUS();
    private HoaDonBUS hoaDonBUS = new HoaDonBUS();
    private ArrayList<PhimDTO> listPhim;
    private JButton btnXemChiTiet;
    private JButton btnDatVe;
    private JButton btnLamMoi;
    private JDialog chiTietDialog;
    private JDialog datVeDialog;
    private int selectedMaPhim = -1;
    private int maKH; // Giả sử lấy từ session hoặc input
    private int maNV; // Giả sử lấy từ session

    public FormBooking(int maKH, int maNV) {
        this.maKH = maKH;
        this.maNV = maNV;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));

        // Panel trên cho chọn thể loại và nút
        JPanel up = new JPanel();
        up.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JLabel lblTheLoai = new JLabel("Thể loại phim:");
        cbTheLoaiPhim = new JComboBox<>();
        loadTheLoaiPhim();
        cbTheLoaiPhim.addActionListener(e -> loadDanhSachPhim());

        up.add(lblTheLoai);
        up.add(cbTheLoaiPhim);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setPreferredSize(new Dimension(110, 43));
        btnLamMoi.setIcon(loadImageIcon("/org/example/GUI/resources/images/icons8_data_backup_30px.png"));
        btnLamMoi.addActionListener(e -> loadDanhSachPhim());
        up.add(btnLamMoi);

        btnXemChiTiet = new JButton("Xem chi tiết");
        btnXemChiTiet.setPreferredSize(new Dimension(144, 43));
        btnXemChiTiet.setIcon(loadImageIcon("/org/example/GUI/resources/images/view.png"));
        up.add(btnXemChiTiet);

        btnXemChiTiet.addActionListener(e -> {
            int selectedRow = tablePhim.getSelectedRow();
            if (selectedRow != -1) {
                selectedMaPhim = Integer.parseInt(tablePhim.getValueAt(selectedRow, 0).toString());
                showDialogChiTietPhim(selectedMaPhim);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một phim để xem chi tiết", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnDatVe = new JButton("Đặt vé");
        btnDatVe.setPreferredSize(new Dimension(144, 43));
        btnDatVe.setIcon(loadImageIcon("/org/example/GUI/resources/images/plus.png"));
        up.add(btnDatVe);

        btnDatVe.addActionListener(e -> {
            int selectedRow = tablePhim.getSelectedRow();
            if (selectedRow != -1) {
                selectedMaPhim = Integer.parseInt(tablePhim.getValueAt(selectedRow, 0).toString());
                showDialogDatVe(selectedMaPhim);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một phim để đặt vé", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Bảng danh sách phim
        tablePhim = new JTable();
        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        Font fontHeader = new Font("Segoe UI", Font.BOLD, 16);
        tablePhim.setFont(font);
        tablePhim.getTableHeader().setFont(fontHeader);
        tablePhim.setRowHeight(30);

        modelPhim = new DefaultTableModel();
        modelPhim.addColumn("Mã Phim");
        modelPhim.addColumn("Tên Phim");
        modelPhim.addColumn("Thời Lượng");
        modelPhim.addColumn("Đạo Diễn");
        modelPhim.addColumn("Năm Sản Xuất");
        modelPhim.addColumn("Giới Hạn Tuổi");

        loadDanhSachPhim(); // Load ban đầu
        tablePhim.setModel(modelPhim);

        JScrollPane scrollPane = new JScrollPane(tablePhim);
        scrollPane.setPreferredSize(new Dimension(740, 402));

        add(up, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
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

    private void loadTheLoaiPhim() {
        // Giả sử PhimBUS có phương thức getListTheLoai()
        ArrayList<String> listTheLoai = phimBUS.getListTheLoai();
        for (String theLoai : listTheLoai) {
            cbTheLoaiPhim.addItem(theLoai);
        }
    }

    private void loadDanhSachPhim() {
        modelPhim.setRowCount(0);
        String theLoai = (String) cbTheLoaiPhim.getSelectedItem();
        listPhim = phimBUS.getListByTheLoai(theLoai);
        for (PhimDTO phim : listPhim) {
            Object[] row = {
                phim.getMaPhim(),
                phim.getTenPhim(),
                phim.getThoiLuong(),
                phim.getDaoDien(),
                phim.getNamSanXuat(),
                phim.getGioiHanTuoi()
            };
            modelPhim.addRow(row);
        }
    }

    private void showDialogChiTietPhim(int maPhim) {
        chiTietDialog = new JDialog((Frame) null, "Chi tiết phim", true);
        chiTietDialog.setSize(500, 400);

        PhimDTO phim = phimBUS.getById(maPhim);
        if (phim == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phim", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 5, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTenPhim = new JLabel("Tên phim:");
        JTextField txtTenPhim = new JTextField(phim.getTenPhim());
        txtTenPhim.setEnabled(false);

        JLabel lblThoiLuong = new JLabel("Thời lượng:");
        JTextField txtThoiLuong = new JTextField(phim.getThoiLuong().toString());
        txtThoiLuong.setEnabled(false);

        JLabel lblDaoDien = new JLabel("Đạo diễn:");
        JTextField txtDaoDien = new JTextField(phim.getDaoDien());
        txtDaoDien.setEnabled(false);

        JLabel lblNamSX = new JLabel("Năm sản xuất:");
        JTextField txtNamSX = new JTextField(String.valueOf(phim.getNamSanXuat()));
        txtNamSX.setEnabled(false);

        JLabel lblGioiHanTuoi = new JLabel("Giới hạn tuổi:");
        JTextField txtGioiHanTuoi = new JTextField(String.valueOf(phim.getGioiHanTuoi()));
        txtGioiHanTuoi.setEnabled(false);

        JLabel lblPoster = new JLabel("Poster:");
        JLabel imgPoster = new JLabel();
        if (phim.getPosterURL() != null) {
            imgPoster.setIcon(new ImageIcon(phim.getPosterURL())); // Giả sử load hình
        }

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
        panel.add(lblPoster);
        panel.add(imgPoster);

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> chiTietDialog.dispose());
        panel.add(btnClose);

        chiTietDialog.getContentPane().add(panel);
        chiTietDialog.setLocationRelativeTo(null);
        chiTietDialog.setVisible(true);
    }

    private void showDialogDatVe(int maPhim) {
        datVeDialog = new JDialog((Frame) null, "Đặt vé phim", true);
        datVeDialog.setSize(600, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 5, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Load danh sách suất chiếu cho phim
        JLabel lblPhong = new JLabel("Phòng chiếu:");
        JComboBox<Integer> cbPhong = new JComboBox<>();
        JLabel lblSuatChieu = new JLabel("Suất chiếu:");
        JComboBox<Integer> cbSuatChieu = new JComboBox<>();
        JLabel lblNgayChieu = new JLabel("Ngày chiếu:");
        // Dùng JSpinner với SpinnerDateModel để chọn ngày, không cần thư viện ngoài
        SpinnerDateModel dateModel = new SpinnerDateModel(new java.util.Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));

        JButton btnTimGhe = new JButton("Tìm ghế trống");
        JList<String> listGhe = new JList<>(); // Danh sách ghế trống
        JScrollPane scrollGhe = new JScrollPane(listGhe);

        JLabel lblGiaVe = new JLabel("Giá vé:");
        JTextField txtGiaVe = new JTextField();
        txtGiaVe.setEnabled(false);

        loadPhongVaSuat(maPhim, cbPhong, cbSuatChieu);

        final int[] currentMaSuatChieuPhim = { -1 };

        btnTimGhe.addActionListener(e -> {
            int maPhong = (int) cbPhong.getSelectedItem();
            int maSuatChieu = (int) cbSuatChieu.getSelectedItem();
            java.util.Date utilDate = (java.util.Date) dateSpinner.getValue();
            Date ngayChieu = new Date(utilDate.getTime());
            int maSuatChieuPhim = suatChieuPhimBUS.getMaSuatChieuPhim(maPhim, maPhong, maSuatChieu, ngayChieu);
            if (maSuatChieuPhim != -1) {
                currentMaSuatChieuPhim[0] = maSuatChieuPhim;
                loadGheTrong(maSuatChieuPhim, listGhe);
                // Tính giá vé gốc + phụ thu ghế (giả sử)
                int giaVeGoc = suatChieuPhimBUS.getGiaVeGoc(maSuatChieuPhim);
                txtGiaVe.setText(String.valueOf(giaVeGoc)); // Cập nhật sau khi chọn ghế
            } else {
                JOptionPane.showMessageDialog(datVeDialog, "Không tìm thấy suất chiếu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(lblPhong);
        panel.add(cbPhong);
        panel.add(lblSuatChieu);
        panel.add(cbSuatChieu);
        panel.add(lblNgayChieu);
        panel.add(dateSpinner);
        panel.add(btnTimGhe);
        panel.add(scrollGhe);
        panel.add(lblGiaVe);
        panel.add(txtGiaVe);

        JButton btnXacNhan = new JButton("Xác nhận đặt vé");
        btnXacNhan.addActionListener(e -> {
            // Lấy ghế selected
            String selectedGhe = listGhe.getSelectedValue();
            if (selectedGhe == null) {
                JOptionPane.showMessageDialog(datVeDialog, "Chọn ghế", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (currentMaSuatChieuPhim[0] == -1) {
                JOptionPane.showMessageDialog(datVeDialog, "Vui lòng tìm ghế trước khi đặt vé", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int maGhe = parseMaGheFromString(selectedGhe); // Hàm parse tự viết
            int maSuatChieuPhim = currentMaSuatChieuPhim[0];
            int giaVe = Integer.parseInt(txtGiaVe.getText());

            // Tạo vé
            VeDTO ve = new VeDTO(0, maGhe, maSuatChieuPhim, giaVe, "Đã bán");
            int maVe = veBUS.add(ve);

            // Tạo hóa đơn
            HoaDonDTO hoaDon = new HoaDonDTO(0, maKH, maNV, new Date(System.currentTimeMillis()), 1, giaVe, 0, null, 0, giaVe);
            int maHoaDon = hoaDonBUS.add(hoaDon);

            // Thêm chi tiết hóa đơn vé
            hoaDonBUS.addCTHDVe(maHoaDon, maVe, giaVe);

            JOptionPane.showMessageDialog(datVeDialog, "Đặt vé thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            datVeDialog.dispose();
        });

        panel.add(btnXacNhan);

        datVeDialog.getContentPane().add(panel);
        datVeDialog.setLocationRelativeTo(null);
        datVeDialog.setVisible(true);
    }

    private void loadPhongVaSuat(int maPhim, JComboBox<Integer> cbPhong, JComboBox<Integer> cbSuatChieu) {
        // Giả sử BUS có phương thức load
        ArrayList<Integer> listPhong = suatChieuPhimBUS.getListPhongByPhim(maPhim);
        for (int phong : listPhong) {
            cbPhong.addItem(phong);
        }
        ArrayList<Integer> listSuat = suatChieuPhimBUS.getListSuatByPhim(maPhim);
        for (int suat : listSuat) {
            cbSuatChieu.addItem(suat);
        }
    }

    private void loadGheTrong(int maSuatChieuPhim, JList<String> listGhe) {
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<String> gheTrong = veBUS.getGheTrong(maSuatChieuPhim);
        for (String ghe : gheTrong) {
            model.addElement(ghe);
        }
        listGhe.setModel(model);
    }

    private int parseMaGheFromString(String gheStr) {
        // Ví dụ: "Ghế A-1 (MaGhe: 123)" -> return 123
        return Integer.parseInt(gheStr.split("\\(MaGhe: ")[1].replace(")", ""));
    }
}