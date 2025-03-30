package org.example.bluemoon.models.enums;

public enum Gender {
    NAM("Nam"),
    NU("Nữ"),
    KHAC("Khác");
    private final String nameVN;

    Gender(String nameVN) {
        this.nameVN = nameVN;
    }

    public String getNameVN() {
        return nameVN;
    }
}
