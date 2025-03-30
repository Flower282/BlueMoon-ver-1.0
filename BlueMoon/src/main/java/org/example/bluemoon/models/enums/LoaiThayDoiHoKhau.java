package org.example.bluemoon.models.enums;

public enum LoaiThayDoiHoKhau {
    ADD("Thêm vào hộ khẩu"),
    REMOVE("Xóa ra khỏi hộ khẩu");

    private final String LoaiThayDoiHoKhauVN;

    LoaiThayDoiHoKhau(String loaiThayDoiHoKhauVN) {
        LoaiThayDoiHoKhauVN = loaiThayDoiHoKhauVN;
    }

    public String getLoaiThayDoiHoKhauVN() {
        return LoaiThayDoiHoKhauVN;
    }


}
