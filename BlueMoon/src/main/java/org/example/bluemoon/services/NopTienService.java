package org.example.bluemoon.services;

import org.example.bluemoon.dao.NopTienDAO;
import org.example.bluemoon.dto.ThongKeResult;
import org.example.bluemoon.models.HoKhau;
import org.example.bluemoon.models.KhoanThu;
import org.example.bluemoon.models.NhanKhau;
import org.example.bluemoon.models.NopTien;

import java.time.LocalDate;
import java.util.List;

public class NopTienService {
    private final NopTienDAO nopTienDAO = new NopTienDAO();

    public List<NopTien> getLatestNopTien() {

        return nopTienDAO.get10LatestRows();
    }

    public void addNopTien(int maKhoanThu, int maCanHo, String nguoiNopTien, Long soTien) {
        NopTien nopTien = NopTien.builder()
                .khoanThu(new KhoanThu(maKhoanThu)) // Sử dụng maKhoanThu chứ không phải id
                .hoNop(new HoKhau(maCanHo))
                .nguoiNop(new NhanKhau(nguoiNopTien))
                .soTien(soTien)
                .ngayNop(LocalDate.now())
                .build();
        nopTienDAO.save(nopTien);
    }

    public List<ThongKeResult> getThongKeNopTien() {
        return nopTienDAO.thongKeNopTien();
    }
}
