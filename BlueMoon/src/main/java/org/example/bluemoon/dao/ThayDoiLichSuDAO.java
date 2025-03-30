package org.example.bluemoon.dao;

import org.example.bluemoon.models.ThayDoiLichSu;
import org.example.bluemoon.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ThayDoiLichSuDAO implements CrudDAO<ThayDoiLichSu, Integer> {
    @Override
    public void save(ThayDoiLichSu thayDoiLichSu) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(thayDoiLichSu);
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
    public void update(ThayDoiLichSu entity) {
        throw new UnsupportedOperationException("Không được sửa thông tin Thay đổi lịch sử!");
    }

    @Deprecated
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Không được xóa thông tin Thay đổi lịch sử!");
    }

    @Override
    public ThayDoiLichSu get(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ThayDoiLichSu.class, id);
        }
    }

    @Override
    public List<ThayDoiLichSu> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ThayDoiLichSu> query = session.createQuery(
                    "FROM ThayDoiLichSu ORDER BY thoiGian DESC",
                    ThayDoiLichSu.class
            );
            return query.list();
        }
    }
}