package org.example.bluemoon.models.enums;

public enum Role {
    KE_TOAN("Kế toán"),
    TO_TRUONG("Tổ trưởng"),
    TO_PHO("Tổ phó"),
    ADMIN("Quản trị viên");
    private final String roleVN;

    Role(String roleVN) {
        this.roleVN = roleVN;
    }

    public String getRoleVN() {
        return roleVN;
    }
}
