/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subRepository.Impl;

import domainmodels.Camera;
import domainmodels.ManHinh;
import domainmodels.Pin;
import domainmodels.Ram;
import domainmodels.Rom;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import subRepository.ISubReposBntMhRamPinCamTypeOpt;
import support.SubMessTypeOpt;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class SubReposBntMhRamPinCamTypeOptImpl implements ISubReposBntMhRamPinCamTypeOpt {

    @Override
    public List<Rom> getAllDataRom() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Rom";
            Query query = session.createQuery(queryCheck);
            List<Rom> data = query.getResultList();
            return data;
        }
    }

    @Override
    public List<ManHinh> getAllDataMH() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From ManHinh";
            Query query = session.createQuery(queryCheck);
            List<ManHinh> data = query.getResultList();
            return data;
        }
    }

    @Override
    public List<Ram> getAllDataRam() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Ram";
            Query query = session.createQuery(queryCheck);
            List<Ram> data = query.getResultList();
            return data;
        }
    }

    @Override
    public List<Pin> getAllDataPin() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Pin";
            Query query = session.createQuery(queryCheck);
            List<Pin> data = query.getResultList();
            return data;
        }
    }

    @Override
    public List<Camera> getAllDataCamera() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Camera";
            Query query = session.createQuery(queryCheck);
            List<Camera> data = query.getResultList();
            return data;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addRom(Rom ch) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Rom item where item.ma = :opt";
            Query<Rom> query = session.createQuery(queryCheck);
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
    public SubMessTypeOpt.TypeStatus remakeRom(Rom ch) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Rom item where item.ma = :opt";
            Query<Rom> query = session.createQuery(queryStr);
            query.setParameter("opt", ch.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Rom item = query.getSingleResult();
            item.setBoNho(ch.getBoNho());
            item.setKichThuoc(ch.getKichThuoc());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeRom(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Rom item where item.ma = :opt";
            Query<Rom> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Rom item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addMH(ManHinh MH) {
      try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From ManHinh item where item.ma = :opt";
            Query<ManHinh> query = session.createQuery(queryCheck);
            query.setParameter("opt", MH.getMa());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(MH);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeMH(ManHinh MH) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From ManHinh item where item.ma = :opt";
            Query<ManHinh> query = session.createQuery(queryStr);
            query.setParameter("opt", MH.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            ManHinh item = query.getSingleResult();
            item.setDoPhanGiai(MH.getDoPhanGiai());
            item.setKichThuoc(MH.getKichThuoc());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeMH(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From ManHinh item where item.ma = :opt";
            Query<ManHinh> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            ManHinh item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addRam(Ram ram) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Ram item where item.ma = :opt";
            Query<Ram> query = session.createQuery(queryCheck);
            query.setParameter("opt", ram.getMa());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(ram);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeRam(Ram ram) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Ram item where item.ma = :opt";
            Query<Ram> query = session.createQuery(queryStr);
            query.setParameter("opt", ram.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Ram item = query.getSingleResult();
            item.setTocDoRam(ram.getTocDoRam());
            item.setKichThuoc(ram.getKichThuoc());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeRam(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Ram item where item.ma = :opt";
            Query<Ram> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Ram item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addPin(Pin dongSP) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Pin item where item.maPin = :opt";
            Query<Pin> query = session.createQuery(queryCheck);
            query.setParameter("opt", dongSP.getMaPin());
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
    public SubMessTypeOpt.TypeStatus remakePin(Pin dong) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Pin item where item.maPin = :opt";
            Query<Pin> query = session.createQuery(queryStr);
            query.setParameter("opt", dong.getMaPin());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Pin item = query.getSingleResult();
            item.setTenPin(dong.getTenPin());
            item.setKichThuoc(dong.getKichThuoc());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removePin(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Pin item where item.maPin = :opt";
            Query<Pin> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Pin item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addCamera(Camera cam) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From Camera item where item.ma = :opt";
            Query<Camera> query = session.createQuery(queryCheck);
            query.setParameter("opt", cam.getMa());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            session.getTransaction().begin();
            session.save(cam);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeCamera(Camera mauSac) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Camera item where item.ma = :opt";
            Query<Camera> query = session.createQuery(queryStr);
            query.setParameter("opt", mauSac.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Camera item = query.getSingleResult();
            item.setTen(mauSac.getTen());
            item.setKichThuoc(mauSac.getKichThuoc());
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus removeCamera(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From Camera item where item.ma = :opt";
            Query<Camera> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            Camera item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

}
