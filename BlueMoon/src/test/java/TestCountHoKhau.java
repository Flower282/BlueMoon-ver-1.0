import org.example.bluemoon.dao.HoKhauDAO;
import org.example.bluemoon.models.HoKhau;

import java.util.List;

public class TestCountHoKhau {
    public static void main(String[] args) {
        long count = new HoKhauDAO().tongSoHoKhau();
        System.out.println("Tong so ho khau: " + count);
    }
}
