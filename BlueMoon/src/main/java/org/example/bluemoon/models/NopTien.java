package org.example.bluemoon.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "nop_tien", schema = "schema_duong")
@NoArgsConstructor
@Setter
@Getter
public class NopTien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //mỗi hộ có thê nộp nhiều khoản thu
    @ManyToOne
    @JoinColumn(name = "ma_khoan_thu", nullable = false)
    private KhoanThu khoanThu;

    //Mỗi khoản thu có thể có nhiều hộ nộp
    @ManyToOne
    @JoinColumn(name = "ma_ho_khau", nullable = false)
    private HoKhau hoNop;

    //Mỗi người nộp có thể nộp nhiều lần
    @ManyToOne
    @JoinColumn(name = "ma_nhan_khau", nullable = false)
    private NhanKhau nguoiNop;

    @Column(name = "so_tien")
    private Long soTien;

    @Column(name = "ngay_nop")
    private LocalDate ngayNop;

    @Builder
    public NopTien(KhoanThu khoanThu, HoKhau hoNop, NhanKhau nguoiNop, Long soTien, LocalDate ngayNop) {
        this.khoanThu = khoanThu;
        this.hoNop = hoNop;
        this.nguoiNop = nguoiNop;
        this.soTien = soTien;
        this.ngayNop = ngayNop;
    }

    @Override
    public String toString() {
        return "NopTien{" +
                "khoanThuId=" + (khoanThu != null ? khoanThu.getId() : "null") +
                ", hoNopId=" + (hoNop != null ? hoNop.getMaHoKhau() : "null") +
                ", nguoiNopTen='" + (nguoiNop != null ? nguoiNop.getHoTen() : "null") + '\'' +
                ", soTien=" + soTien +
                ", ngayNop=" + ngayNop +
                '}';
    }
}
