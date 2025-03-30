package org.example.bluemoon.models.enums;

public enum LoaiKhoanThu {
    TU_NGUYEN("Tự nguyện"),
    BAT_BUOC("Bắt buộc");
    private final String LoaiKhoanThuVN;

    LoaiKhoanThu(String loaiKhoanThuVN) {
        LoaiKhoanThuVN = loaiKhoanThuVN;
    }

    public String getLoaiKhoanThuVN() {
        return LoaiKhoanThuVN;
    }

    @Override
    public String toString() {
        return LoaiKhoanThuVN;
    }
}
