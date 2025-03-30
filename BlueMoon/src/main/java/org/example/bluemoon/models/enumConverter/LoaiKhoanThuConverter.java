package org.example.bluemoon.models.enumConverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.bluemoon.models.enums.LoaiKhoanThu;

@Converter
public class LoaiKhoanThuConverter implements AttributeConverter<LoaiKhoanThu, String> {

    @Override
    public String convertToDatabaseColumn(LoaiKhoanThu attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLoaiKhoanThuVN();
    }

    @Override
    public LoaiKhoanThu convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (LoaiKhoanThu loaiKhoanThu : LoaiKhoanThu.values()) {
            if (loaiKhoanThu.getLoaiKhoanThuVN().equals(dbData)) {
                return loaiKhoanThu;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}