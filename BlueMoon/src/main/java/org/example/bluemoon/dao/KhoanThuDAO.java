package org.example.bluemoon.dao;

import org.example.bluemoon.models.KhoanThu;
import org.example.bluemoon.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class KhoanThuDAO implements CrudDAO<KhoanThu, Integer> {

    @Override
    public void save(KhoanThu khoanThu) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(khoanThu);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void update(KhoanThu khoanThu) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(khoanThu);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery(
                    "UPDATE KhoanThu SET isDeleted = true WHERE id = :id"
            );
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public KhoanThu get(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<KhoanThu> query = session.createQuery(
                    "FROM KhoanThu WHERE id = :id AND isDeleted = false",
                    KhoanThu.class
            );
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public List<KhoanThu> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<KhoanThu> query = session.createQuery(
                    "FROM KhoanThu WHERE isDeleted = false ORDER BY ngayTao DESC",
                    KhoanThu.class
            );
            return query.getResultList();
        }
    }
}