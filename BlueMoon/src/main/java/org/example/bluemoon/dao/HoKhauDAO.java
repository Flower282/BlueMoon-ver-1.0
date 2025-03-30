package org.example.bluemoon.dao;

import org.example.bluemoon.models.HoKhau;
import org.example.bluemoon.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HoKhauDAO implements CrudDAO<HoKhau, Integer> {
    @Override
    public void save(HoKhau hoKhau) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(hoKhau);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void update(HoKhau hoKhau) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(hoKhau);
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
            HoKhau hoKhau = session.get(HoKhau.class, id);
            if (hoKhau != null) {
                session.remove(hoKhau);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public HoKhau get(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(HoKhau.class, id);
        }
    }

    @Override
    public List<HoKhau> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<HoKhau> query = session.createQuery(
                    "FROM HoKhau",
                    HoKhau.class
            );
            return query.getResultList();
        }
    }

    public long tongSoHoKhau() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(h.maHoKhau) FROM HoKhau h",
                    Long.class
            );
            return query.getSingleResult();
        }
    }
}