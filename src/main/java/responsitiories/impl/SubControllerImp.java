/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import Support.SubMessTypeOpt;
import domainmodels.Anh;
import domainmodels.DongSp;
import domainmodels.MauSac;
import domainmodels.NSX;
import domainmodels.SanPham;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import responsitiories.ISubController;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class SubControllerImp implements ISubController {

    @Override
    public SubMessTypeOpt.TypeStatus addSanPham(SanPham sp) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From SanPham item where item.soImei = :opt";
            Query<SanPham> query = session.createQuery(queryCheck);
            query.setParameter("opt", sp.getSoImei());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(sp);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeSanPham(SanPham sp) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From SanPham item where item.soImei = :opt";
            Query<SanPham> query = session.createQuery(queryStr);
            query.setParameter("opt", sp.getSoImei());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            SanPham item = query.getSingleResult();
            item.setTen(sp.getTen());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }

    }

    @Override
    public SubMessTypeOpt.TypeStatus removeSanPham(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From SanPham item where item.soImei = :opt";
            Query<SanPham> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            SanPham item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public List<SanPham> getAllDataSanPham() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From SanPham";
            Query query = session.createQuery(queryCheck);
            List<SanPham> data = query.getResultList();
            return data;
        }
    }

    @Override
    public List<DongSp> getAllDataDongSP() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From DongSp";
            Query query = session.createQuery(queryCheck);
            List<DongSp> data = query.getResultList();
            return data;
        }
    }

    @Override
    public List<MauSac> getAllDataMauSac() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From MauSac";
            Query query = session.createQuery(queryCheck);
            List<MauSac> data = query.getResultList();
            return data;
        }
    }

    @Override
    public List<NSX> getAllDataNSX() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From NSX";
            Query query = session.createQuery(queryCheck);
            List<NSX> data = query.getResultList();
            return data;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addNSX(NSX nsx) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From NSX item where item.ma = :opt";
            Query<NSX> query = session.createQuery(queryCheck);
            query.setParameter("opt", nsx.getMa());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(nsx);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeNSX(NSX nsx) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From NSX item where item.ma = :opt";
            Query<NSX> query = session.createQuery(queryStr);
            query.setParameter("opt", nsx.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            NSX item = query.getSingleResult();
            item.setTen(nsx.getTen());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeNSX(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From NSX item where item.ma = :opt";
            Query<NSX> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            NSX item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addDongSP(DongSp dongSP) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From DongSp item where item.ma = :opt";
            Query<DongSp> query = session.createQuery(queryCheck);
            query.setParameter("opt", dongSP.getMa());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(dongSP);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeDongSP(DongSp dongSP) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From DongSp item where item.ma = :opt";
            Query<DongSp> query = session.createQuery(queryStr);
            query.setParameter("opt", dongSP.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            DongSp item = query.getSingleResult();
            item.setTen(dongSP.getTen());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeDongSP(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From DongSp item where item.ma = :opt";
            Query<DongSp> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            DongSp item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addMauSac(MauSac mauSac) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From MauSac item where item.ma = :opt";
            Query<MauSac> query = session.createQuery(queryCheck);
            query.setParameter("opt", mauSac.getMa());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(mauSac);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeMauSac(MauSac mauSac) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From MauSac item where item.ma = :opt";
            Query<MauSac> query = session.createQuery(queryStr);
            query.setParameter("opt", mauSac.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            MauSac item = query.getSingleResult();
            item.setTen(mauSac.getTen());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeMauSac(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From MauSac item where item.ma = :opt";
            Query<MauSac> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            MauSac item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public List<Anh> getAllDataAnh() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Anh";
            Query query = session.createQuery(queryCheck);
            List<Anh> data = query.getResultList();
            return data;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addAnh(Anh anh) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Anh item where item.duongDan = :opt";
            Query<Anh> query = session.createQuery(queryCheck);
            query.setParameter("opt", anh.getDuongDan());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(anh);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeAnh(Anh anh) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Anh item where item.duongDan = :opt";
            Query<Anh> query = session.createQuery(queryStr);
            query.setParameter("opt", anh.getDuongDan());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Anh item = query.getSingleResult();
            item.setTen(anh.getTen());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeAnh(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Anh item where item.duongDan = :opt";
            Query<Anh> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Anh item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

}
