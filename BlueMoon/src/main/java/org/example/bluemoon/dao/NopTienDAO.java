package org.example.bluemoon.dao;

import org.example.bluemoon.dto.ThongKeResult;
import org.example.bluemoon.models.NopTien;
import org.example.bluemoon.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class NopTienDAO implements CrudDAO<NopTien, Integer> {
    @Override
    public void save(NopTien nopTien) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(nopTien);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Deprecated
    @Override
    public void update(NopTien nopTien) {
        throw new UnsupportedOperationException("Không được phép cập nhật thông tin nộp tiền!");
    }

    @Deprecated
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Không được phép xóa thông tin nộp tiền!");
    }

    @Override
    public NopTien get(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(NopTien.class, id);
        }
    }

    @Override
    public List<NopTien> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<NopTien> query = session.createQuery(
                    "FROM NopTien ORDER BY ngayNop DESC",
                    NopTien.class
            );
            return query.getResultList();
        }
    }

    public List<NopTien> get10LatestRows() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<NopTien> query = session.createQuery(
                    "FROM NopTien ORDER BY ngayNop DESC",
                    NopTien.class
            );
            query.setMaxResults(10);
            return query.getResultList();
        }
    }

    // Thống kê tỷ lệ nộp tiền theo tháng
    public List<ThongKeResult> thongKeNopTien() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sql = """
                        SELECT
                            to_char(kt.ngay_tao, 'MM/YYYY') AS thang_thu,
                            ROUND(
                                (COALESCE(COUNT(DISTINCT nt.ma_ho_khau), 0) * 100.0) /
                                NULLIF(COUNT(DISTINCT hgd.ma_ho_khau), 0), 2
                            ) ::DOUBLE PRECISION AS ti_le_nop_tien
                        FROM schema_duong."KhoanThu" kt
                        LEFT JOIN schema_duong.nop_tien nt ON nt.ma_khoan_thu = kt.id
                        LEFT JOIN schema_duong."HoKhau" hgd ON hgd.ngay_chuyen_den < kt.ngay_tao
                        WHERE kt.loai_khoan_thu = 'Bắt buộc'
                        AND kt.ngay_tao >= CURRENT_DATE - INTERVAL '6 months'
                        GROUP BY kt.ngay_tao
                        ORDER BY kt.ngay_tao
                    """;

            Query<Object[]> query = session.createNativeQuery(sql);

            List<ThongKeResult> thongKeResults = query.getResultList().stream()
                    .map(row -> new ThongKeResult(
                            (String) row[0],  // thangThu
                            (Double) row[1]   // tiLeNopTien
                    ))
                    .toList();

            return thongKeResults;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi thống kê tiền nộp", e);
        }
    }

}