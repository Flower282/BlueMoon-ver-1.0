package org.example.bluemoon.dao;

import org.example.bluemoon.models.TamTruTamVang;
import org.example.bluemoon.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TamTruTamVangDAO implements CrudDAO<TamTruTamVang, Integer> {
    @Override
    public void save(TamTruTamVang tamTruTamVang) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(tamTruTamVang);
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
    public void update(TamTruTamVang tamTruTamVang) {
        throw new UnsupportedOperationException("Không được phép cập nhật thông tin tạm trú tạm vắng");
    }

    @Deprecated
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Không được phép xóa thông tin tạm trú tạm vắng");
    }

    @Override
    public TamTruTamVang get(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TamTruTamVang.class, id);
        }
    }

    @Override
    public List<TamTruTamVang> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<TamTruTamVang> query = session.createQuery(
                    "FROM TamTruTamVang ORDER BY thoiGian DESC",
                    TamTruTamVang.class
            );
            return query.list();
        }
    }
}