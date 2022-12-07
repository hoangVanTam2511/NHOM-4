/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.ChiTietSanPham;
import domainmodels.Imei;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamReponsitoryImpl implements IReponsitory<ChiTietSanPham> {

    @Override
    public List<ChiTietSanPham> findAll() {
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM ChiTietSanPham ct";
            TypedQuery query = session.createQuery(hql, ChiTietSanPham.class);
            chiTietSanPhams = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ChiTietSanPham findOneByImei(String imei) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM ChiTietSanPham ct where ct.idSanPham.soImei = :imei";
            TypedQuery query = session.createQuery(hql, ChiTietSanPham.class);
            query.setParameter("imei", imei);
            chiTietSanPham = (ChiTietSanPham) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPham;
    }

    public List<ChiTietSanPham> findAllByName(String ten) {
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM ChiTietSanPham ct   "
                    + "WHERE ct.ten like CONCAT('%',:ten,'%') ";
            TypedQuery query = session.createQuery(hql, ChiTietSanPham.class);
            query.setParameter("ten", ten);
            chiTietSanPhams = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    @Override
    public ChiTietSanPham findOneByMa(String ma) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT ct FROM ChiTietSanPham ct WHERE ct.ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            chiTietSanPham = query.getSingleResult() == null ? null : (ChiTietSanPham) query.getSingleResult();
        }
        return chiTietSanPham;
    }

    @Override
    public ChiTietSanPham findOneByID(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(ChiTietSanPham t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(ChiTietSanPham t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(t);
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
    public boolean delete(ChiTietSanPham t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.delete(t);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
            return true;
        }
    }

    public boolean setDeleted() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                String hql = "UPDATE chi_tietsp SET delected = 1 ";
                NativeQuery query = session.createNativeQuery(hql);
                System.out.println(hql);
                trans.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Long getSoLuongTonTheoTungMaSanPham(String maSP) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT  count(a.id) FROM Imei a "
                    + " WHERE a.idChiTietSanPham.ma = :ma AND a.trangThai = 0 "
                    + " GROUP BY a.idChiTietSanPham.ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", maSP);
            if (query.getSingleResult() == null) {
                return 0l;
            }
            return (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public List<Imei> getDanhSachImeiTheoTungMaSanPham(String maSP) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT  a FROM Imei a "
                    + " WHERE a.idChiTietSanPham.ma = :ma AND a.trangThai = 0 ";
            Query query = session.createQuery(hql);
            query.setParameter("ma", maSP);
            if (query.getResultList() == null) {
                return null;
            }
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Imei getOneImei(String soImei) {
        Imei imei = new Imei();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM Imei ct where ct.soImei = :imei";
            TypedQuery query = session.createQuery(hql, Imei.class);
            query.setParameter("imei", soImei);
            return (Imei) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }
    
     public boolean updateImei(Imei imei) {
         try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(imei);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    public void setTinhTrangImeiKhiMuaHang(String soImei) {
        Imei imei = new ChiTietSanPhamReponsitoryImpl().getOneImei(soImei);
        imei.setTrangThai(true);
        new ChiTietSanPhamReponsitoryImpl().updateImei(imei);
    }

    public static void main(String[] args) {
        new ChiTietSanPhamReponsitoryImpl().setTinhTrangImeiKhiMuaHang("926593465555");
    }
}
