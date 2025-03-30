package org.example.bluemoon.models.enums;

public enum TrangThaiTTTV {
    TAM_TRU("Tạm trú"),
    TAM_VANG("Tạm vắng");

    private final String TrangThaiTTTVVN;

    TrangThaiTTTV(String trangThaiTTTVVN) {
        TrangThaiTTTVVN = trangThaiTTTVVN;
    }

    public String getTrangThaiTTTVVN() {
        return TrangThaiTTTVVN;
    }
}
