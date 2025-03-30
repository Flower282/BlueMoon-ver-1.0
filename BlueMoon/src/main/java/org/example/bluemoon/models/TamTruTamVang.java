package org.example.bluemoon.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.bluemoon.models.enums.TrangThaiTTTV;

import java.time.LocalDate;

@Entity
@Table(name = "\"TamTruTamVang\"", schema = "schema_duong")
@NoArgsConstructor
@Setter
@Getter
public class TamTruTamVang {
    @Id
    @GeneratedValue
    private int id;

    //Mỗi tạm trú tạm vắng chỉ gồm một nhân khẩu
    @ManyToOne
    @JoinColumn(name = "nhankhau_id")
    private NhanKhau nhanKhau;

    @Column(name = "trang_thai")
    @Enumerated(EnumType.STRING)
    private TrangThaiTTTV trangThai;

    @Column(name = "dia_chi_tam_tru_tam_vang")
    private String diaChiTTTV;

    @Column(name = "noi_dung_de_nghi")
    private String noiDungDeNghi;

    @Column(name = "thoi_gian")
    private LocalDate thoiGian;

    @Builder
    public TamTruTamVang(NhanKhau nhanKhau, TrangThaiTTTV trangThai, String diaChiTTTV, String noiDungDeNghi, LocalDate thoiGian) {
        this.nhanKhau = nhanKhau;
        this.trangThai = trangThai;
        this.diaChiTTTV = diaChiTTTV;
        this.noiDungDeNghi = noiDungDeNghi;
        this.thoiGian = thoiGian;
    }
}
