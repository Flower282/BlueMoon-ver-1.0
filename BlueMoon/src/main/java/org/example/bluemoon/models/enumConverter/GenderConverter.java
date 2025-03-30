package org.example.bluemoon.models.enumConverter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.bluemoon.models.enums.Gender;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender != null ? gender.getNameVN() : null;
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (Gender gender : Gender.values()) {
            if (gender.getNameVN().equals(dbData)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Giá trị không hợp lệ: " + dbData);
    }
}