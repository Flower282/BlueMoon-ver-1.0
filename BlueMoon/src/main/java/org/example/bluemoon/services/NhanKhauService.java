package org.example.bluemoon.services;

import org.example.bluemoon.dao.NhanKhauDAO;

public class NhanKhauService {
    private static final NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();

    public Long tongSoNhanKhau() {
        return nhanKhauDAO.tongSoNhanKhau();
    }
}
