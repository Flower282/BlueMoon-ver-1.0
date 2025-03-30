import org.example.bluemoon.models.KhoanThu;
import org.example.bluemoon.services.KhoanThuService;

import java.util.List;

public class TestGetAllKhoanThu {
    public static void main(String[] args) {
        KhoanThuService khoanThuService = new KhoanThuService();
        List<KhoanThu> khoanThuList = khoanThuService.getKhoanThuList();
        for (KhoanThu khoanThu : khoanThuList) {
            System.out.println(khoanThu);
        }
    }
}
