import org.example.bluemoon.models.NopTien;
import org.example.bluemoon.services.NopTienService;

import java.util.List;

public class TestGet10LatestRows {
    public static void main(String[] args) {
        List<NopTien> latestNopTien = new NopTienService().getLatestNopTien();
        if (latestNopTien == null || latestNopTien.isEmpty()) {
            System.out.println("Danh sách rỗng hoặc null!");
        } else {
            for (NopTien nopTien : latestNopTien) {
                System.out.println(nopTien.toString());
            }
        }
    }
}
