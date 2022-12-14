/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.User;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import reponces.QlUser;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class UserReponsitoryImpl implements IReponsitory<User> {

    @Override
    public List<User> findAll() {
        List<User> listNhanVien = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            Query query = session.createQuery("SELECT n FROM User n");
            listNhanVien = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanVien;
    }

    public List<User> findAllByNV() {
        List<User> listNhanVien = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            Query query = session.createQuery("SELECT n FROM User n WHERE  n.idChucVu.ma = 'NV'");
            listNhanVien = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanVien;
    }

    @Override
    public User findOneByMa(String ma) {
        List<User> nhanViens = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "FROM User where ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            nhanViens = query.getResultList();
            if (nhanViens.size() == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanViens.get(0);
    }

    @Override
    public User findOneByID(UUID id) {
        List<User> nhanViens = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "FROM User where id = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", id);
            nhanViens = query.getResultList();
            if (nhanViens.size() == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanViens.get(0);
    }

    @Override
    public boolean save(User nhanVien) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(nhanVien);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(User nv) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.delete(nv);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(User nhanVien) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(nhanVien);
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    public User checkSoDienthoaiAnhPass(String sdt, String pass) {
        User user = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "FROM User p WHERE p.matKhau = :pass and p.sdt = :sdt ";
            Query query = session.createQuery(hql);
            query.setParameter("pass", pass);
            query.setParameter("sdt", sdt);
            user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String checkSoDienthoaiAnhMaNhanVien(String sdt, String cccd) {
        String pass = "";
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT a.matKhau FROM User a WHERE a.sdt =  :sdt AND a.cccd = :cccd ";
            Query query = session.createQuery(hql);
            query.setParameter("sdt", sdt);
            query.setParameter("cccd", cccd);
            pass = (String) query.getSingleResult();
            return pass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new UserReponsitoryImpl().findAll());
    }

    public List<User> findAllByName(String name) {
        List<User> nhanViens = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "FROM User b where  b.ten like CONCAT('%',:ten,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("ten", name);
            nhanViens = query.getResultList();
            if (nhanViens.size() == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanViens;
    }
}
