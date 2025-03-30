import org.example.bluemoon.dao.NopTienDAO;
import org.example.bluemoon.dto.ThongKeResult;

import java.util.List;

public class TestThongKeNopTienDAO {
    public static void main(String[] args) {
        NopTienDAO nopTienDAO = new NopTienDAO();
        List<ThongKeResult> results = nopTienDAO.thongKeNopTien();
        for (ThongKeResult result : results) {
            System.out.println("Tháng: " + result.getThangThu() + ", Tỉ lệ nộp tiền: " + result.getTiLeNopTien());
        }
    }
}
