package org.example.bluemoon.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.example.bluemoon.dao.KhoanThuDAO;
import org.example.bluemoon.models.KhoanThu;
import org.example.bluemoon.models.enums.LoaiKhoanThu;

import java.time.LocalDate;
import java.util.List;

public class KhoanThuService {
    private static final KhoanThuDAO khoanThuDAO = new KhoanThuDAO();
    private List<KhoanThu> danhSachKhoanThu; // Chỉ lấy từ DB một lần

    //Hàm thêm KhoanThu
    public void addKhoanThuTuNguyen(String maKhoanThu, String tenKhoanThu, LocalDate thoiHan) {
        KhoanThu khoanThu = KhoanThu.builder().
                id(Integer.parseInt(maKhoanThu)).
                tenKhoanThu(tenKhoanThu).
                loaiKhoanThu(LoaiKhoanThu.TU_NGUYEN).
                ngayTao(java.time.LocalDate.now()).
                thoiHan(thoiHan).
                build();
        khoanThuDAO.save(khoanThu);
    }

    public void addKhoanThuBatBuoc(String maKhoanThu, String tenKhoanThu, Long soTien, LocalDate thoiHan) {
        KhoanThu khoanThu = KhoanThu.builder().
                id(Integer.parseInt(maKhoanThu)).
                tenKhoanThu(tenKhoanThu).
                loaiKhoanThu(LoaiKhoanThu.BAT_BUOC).
                soTien(soTien).
                ngayTao(java.time.LocalDate.now()).
                thoiHan(thoiHan).
                build();
        khoanThuDAO.save(khoanThu);
    }

    // Hàm truy vấn database một lần duy nhất
    private void loadData() {
        if (danhSachKhoanThu == null) {  // Tránh truy vấn nhiều lần
            danhSachKhoanThu = khoanThuDAO.getAll();
        }
    }

    public void refreshData() {
        danhSachKhoanThu = khoanThuDAO.getAll(); // Cập nhật lại dữ liệu từ DB
    }

    // Hàm trả về danh sách KhoanThu
    public List<KhoanThu> getKhoanThuList() {
        loadData(); // Đảm bảo đã lấy dữ liệu
        return danhSachKhoanThu;
    }


}
