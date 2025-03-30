package org.example.bluemoon.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"HoKhau\"", schema = "schema_duong")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class HoKhau {
    @Id
    @Column(name = "ma_ho_khau")
    private int maHoKhau;

    //Chủ hộ của hộ khẩu
    //Bảng chứa khóa ngoại sẽ tạo @JoinColumn để tham chiếu đến khóa chính của bảng chứa
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_chu_ho", referencedColumnName = "id", unique = true)
    private NhanKhau chuHo;

    @Column(name = "so_thanh_vien")
    private int soThanhVien;

    @Column(name = "ngay_chuyen_den")
    private LocalDate ngayChuyenDen;

    @Column(name = "so_nha")
    private String soNha;

    @Column(name = "duong")
    private String duong;

    @Column(name = "phuong")
    private String phuong;

    @Column(name = "quan")
    private String quan;

    //Danh sách nhân khẩu trong hộ khẩu
    @OneToMany(mappedBy = "hoKhau", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NhanKhau> nhanKhauList = new ArrayList<>();

    //Danh sách khoản thu của hộ khẩu đã nộp
    @OneToMany(mappedBy = "hoNop", cascade = CascadeType.ALL)
    private List<NopTien> nopTienList = new ArrayList<>();

    //Lịch sử thay đổi hộ khẩu
    @OneToMany(mappedBy = "hoKhau", cascade = CascadeType.ALL)
    private List<ThayDoiLichSu> thayDoiLichSuList = new ArrayList<>();

    public HoKhau(int maHoKhau) {
        this.maHoKhau = maHoKhau;
    }
}
