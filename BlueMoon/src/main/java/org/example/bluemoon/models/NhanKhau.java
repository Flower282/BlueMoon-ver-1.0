package org.example.bluemoon.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.bluemoon.models.enumConverter.GenderConverter;
import org.example.bluemoon.models.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"NhanKhau\"", schema = "schema_duong")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class NhanKhau {
    @Id
    private int id;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh")
    @Convert(converter = GenderConverter.class)
    private Gender gioiTinh;

    @Column(name = "dan_toc")
    private String danToc;

    @Column(name = "ton_giao")
    private String tonGiao;

    @Column(name = "nghe_nghiep")
    private String ngheNghiep;

    @Column(name = "so_cccd")
    private String soCCCD;

    @Column(name = "ngay_cap")
    private LocalDate ngayCap;

    @Column(name = "noi_cap")
    private String noiCap;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted = false;


    //Mỗi nhân khẩu có thể có nhiều tạm trú tạm vắng
    //mappedBy: trỏ đến tên biến nhanKhau ở trong class TamTruTamVang
    //cascade: xử lý các thao tác CRUD trên nhân khẩu sẽ được áp dụng lên tạm trú tạm vắng
    //orphanRemoval = false (default): không xóa tạm trú tạm vắng khi nhân khẩu bị xóa
    @OneToMany(mappedBy = "nhanKhau", cascade = CascadeType.ALL)
    private List<TamTruTamVang> tamTruTamVangList = new ArrayList<>();

    //Mỗi người đại diện có thể nộp tiền nhiều lần
    @OneToMany(mappedBy = "nguoiNop", cascade = CascadeType.ALL)
    private List<NopTien> nopTienList = new ArrayList<>();

    //Mỗi nhân khẩu có thể thay đổi thông tin nhiều lần
    @OneToMany(mappedBy = "nhanKhau", cascade = CascadeType.ALL)
    private List<ThayDoiLichSu> thayDoiLichSuList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ma_ho_khau")
    private HoKhau hoKhau;

    @Column(name = "ngay_them_nhan_khau")
    private LocalDate ngayThemNhanKhau;

    @Column(name = "quan_he_voi_chu_ho")
    private String quanHeVoiChuHo;

    public NhanKhau(String hoTen) {
        this.hoTen = hoTen;
    }
}
