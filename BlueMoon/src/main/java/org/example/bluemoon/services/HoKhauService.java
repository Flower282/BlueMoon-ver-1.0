package org.example.bluemoon.services;

import org.example.bluemoon.dao.HoKhauDAO;

public class HoKhauService {
    private static final HoKhauDAO hoKhauDAO = new HoKhauDAO();
    private Long cacheTongSoHoKhau = null;

    public Long tongSoHoKhau() {
        if (cacheTongSoHoKhau == null) {
            cacheTongSoHoKhau = hoKhauDAO.tongSoHoKhau();
        }
        return cacheTongSoHoKhau;
    }

    public void refreshCacheTongSoHoKhau() {
        cacheTongSoHoKhau = hoKhauDAO.tongSoHoKhau();
    }
}
