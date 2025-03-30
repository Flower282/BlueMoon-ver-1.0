package org.example.bluemoon.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import org.example.bluemoon.models.enums.LoaiThayDoiHoKhau;

import java.time.LocalDate;

@Entity
@Table(name = "thay_doi_lich_su", schema = "schema_duong")
@NoArgsConstructor
@Getter
@Setter
public class ThayDoiLichSu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Mỗi hộ khẩu có thể có nhiều lịch sử thay đổi
    @ManyToOne
    @JoinColumn(name = "ma_ho_khau", nullable = false)
    private HoKhau hoKhau;

    //Mỗi nhân khẩu có thể có nhiều lịch sử thay đổi
    @ManyToOne
    @JoinColumn(name = "nhankhau_id", nullable = false)
    private NhanKhau nhanKhau;

    @Column(name = "loai_thay_doi")
    @Enumerated(EnumType.STRING)
    private LoaiThayDoiHoKhau loaiThayDoi;

    @Column(name = "thoi_gian")
    private LocalDate thoiGian;

    @Builder
    public ThayDoiLichSu(HoKhau hoKhau, NhanKhau nhanKhau, LoaiThayDoiHoKhau loaiThayDoi, LocalDate thoiGian) {
        this.hoKhau = hoKhau;
        this.nhanKhau = nhanKhau;
        this.loaiThayDoi = loaiThayDoi;
        this.thoiGian = thoiGian;
    }
}
