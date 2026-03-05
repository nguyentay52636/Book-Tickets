package org.example.BUS;

import org.example.DTO.PhimDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Stub implementation using in-memory data so that FormBooking can run
 * without a real database. Replace with JDBC logic later.
 */
public class PhimBUS {

    private final List<PhimDTO> list = new ArrayList<>();

    public PhimBUS() {
        // Dữ liệu mẫu
        list.add(new PhimDTO(1, 1, "Avengers: Endgame", "03:01", "Anthony Russo",
                2019, 13, null));
        list.add(new PhimDTO(2, 2, "Your Name", "01:46", "Makoto Shinkai",
                2016, 13, null));
    }

    public ArrayList<String> getListTheLoai() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Tất cả");
        result.add("Hành động");
        result.add("Tình cảm");
        return result;
    }

    public ArrayList<PhimDTO> getListByTheLoai(String theLoai) {
        if (theLoai == null || theLoai.equals("Tất cả")) {
            return new ArrayList<>(list);
        }
        // Ở đây chỉ lọc đơn giản theo mã thể loại
        int maTheLoai = theLoai.equals("Hành động") ? 1 : 2;
        return list.stream()
                .filter(p -> p.getMaTheLoaiPhim() == maTheLoai)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public PhimDTO getById(int maPhim) {
        return list.stream()
                .filter(p -> p.getMaPhim() == maPhim)
                .findFirst()
                .orElse(null);
    }
}

