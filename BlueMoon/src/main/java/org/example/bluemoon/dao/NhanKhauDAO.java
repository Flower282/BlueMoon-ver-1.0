package org.example.bluemoon.dao;

import org.example.bluemoon.models.NhanKhau;
import org.example.bluemoon.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class NhanKhauDAO implements CrudDAO<NhanKhau, Integer> {
    @Override
    public void save(NhanKhau nhanKhau) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(nhanKhau);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void update(NhanKhau nhanKhau) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(nhanKhau);
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
            NhanKhau nhanKhau = session.get(NhanKhau.class, id);
            if (nhanKhau != null) {
                nhanKhau.setDeleted(true);
                session.merge(nhanKhau);
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
    public NhanKhau get(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<NhanKhau> query = session.createQuery(
                    "FROM NhanKhau WHERE id = :id AND isDeleted = false",
                    NhanKhau.class
            );
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public List<NhanKhau> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<NhanKhau> query = session.createQuery(
                    "FROM NhanKhau WHERE isDeleted = false ORDER BY id",
                    NhanKhau.class
            );
            return query.getResultList();
        }
    }

    public long tongSoNhanKhau() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(n.id) FROM NhanKhau n",
                    Long.class
            );
            return query.getSingleResult();
        }
    }
}