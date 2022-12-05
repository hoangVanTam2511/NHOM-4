/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subRepository.Impl;

import domainmodels.Chip;
import domainmodels.DongSp;
import domainmodels.HeDieuHanh;
import domainmodels.MauSac;
import domainmodels.NSX;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import subRepository.ISubReposHdhChipMsDspTypeOpt;
import support.SubMessTypeOpt;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class SubReposHdhChipMsDspTypeOptImpl implements ISubReposHdhChipMsDspTypeOpt {

    @Override
    public List<Chip> getAllDataChip() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Chip";
            Query query = session.createQuery(queryCheck);
            List<Chip> data = query.getResultList();
            return data;
        }
    }

    @Override
    public List<HeDieuHanh> getAllDataHDH() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From HeDieuHanh";
            Query query = session.createQuery(queryCheck);
            List<HeDieuHanh> data = query.getResultList();
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
    public SubMessTypeOpt.TypeStatus addChip(Chip ch) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Chip item where item.maChip = :opt";
            Query<Chip> query = session.createQuery(queryCheck);
            query.setParameter("opt", ch.getMaChip());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(ch);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakechip(Chip ch) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Chip item where item.maChip = :opt";
            Query<Chip> query = session.createQuery(queryStr);
            query.setParameter("opt", ch.getMaChip());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Chip item = query.getSingleResult();
            item.setTenChip(ch.getTenChip());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeChip(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Chip item where item.maChip = :opt";
            Query<Chip> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Chip item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addHDH(HeDieuHanh ch) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From HeDieuHanh item where item.ma = :opt";
            Query<HeDieuHanh> query = session.createQuery(queryCheck);
            query.setParameter("opt", ch.getMa());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(ch);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeHDH(HeDieuHanh ch) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From HeDieuHanh item where item.ma = :opt";
            Query<HeDieuHanh> query = session.createQuery(queryStr);
            query.setParameter("opt", ch.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            HeDieuHanh item = query.getSingleResult();
            item.setTen(ch.getTen());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeHDH(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From HeDieuHanh item where item.ma = :opt";
            Query<HeDieuHanh> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            HeDieuHanh item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
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
            String queryCheck = "From  DongSp item where item.ma = :opt";
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
            String queryCheck = "From  MauSac item where item.ma = :opt";
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

}
