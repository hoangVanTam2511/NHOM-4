/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.HoaDon;
import responsitiories.IHoaDonReponsitory;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class HoaDonReponsitoryImpl implements IHoaDonReponsitory {

    @Override
    public List<HoaDon> findAll(int status) {
        List<HoaDon> listHoaDons = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM HoaDon c WHERE c.tinhTrang = :tinhTrang ORDER BY c.ma ASC";
            TypedQuery typedQuery = session.createQuery(hql, HoaDon.class);
            typedQuery.setParameter("tinhTrang", status);
            listHoaDons = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoaDons;
    }

    @Override
    public List<HoaDon> findAll() {
        List<HoaDon> listHoaDons = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM HoaDon c  ORDER BY c.ma ASC";
            TypedQuery typedQuery = session.createQuery(hql, HoaDon.class);
            listHoaDons = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoaDons;
    }

    public List<HoaDon> findAllByName(String text) {
        List<HoaDon> listHoaDons = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM HoaDon c WHERE c.idKhachHang.ten LIKE CONCAT('%',:ten,'%') OR c.idKhachHang.ma LIKE CONCAT('%',:ten,'%') ORDER BY c.ma ASC";
            TypedQuery typedQuery = session.createQuery(hql, HoaDon.class);
            typedQuery.setParameter("ten", text);
            listHoaDons = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoaDons;
    }

    @Override
    public HoaDon findOneByMa(String ma) {
        HoaDon hoaDon = new HoaDon();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM HoaDon c WHERE c.ma = :id";
            Query query = session.createQuery(hql, HoaDon.class);
            query.setParameter("id", ma);
            try {
                hoaDon = (HoaDon) query.getSingleResult();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    @Override
    public HoaDon findOneByID(UUID id) {
        HoaDon hoaDon = new HoaDon();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM HoaDon c WHERE c.id = :id";
            Query query = session.createQuery(hql, HoaDon.class);
            query.setParameter("id", id);
            hoaDon = (HoaDon) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    @Override
    public boolean save(HoaDon t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            session.save(t);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(HoaDon t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();// Lấy transaction tuơng ứng 
            trans.begin();
            session.delete(t);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(HoaDon t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            session.update(t);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Double getDoanhThu(int month, int year) {
        Double doanhThu = 0.0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(c.thanhToan) FROM HoaDon c WHERE MONTH(c.created) = :month AND YEAR(c.created) = :year GROUP BY MONTH(c.created),YEAR(c.created) ";
            Query query = session.createQuery(hql);
            query.setParameter("month", month);
            query.setParameter("year", year);
            doanhThu = (Double) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }

    public Long getSoHoaDonDaTao(int month, int year) {
        Long soHoaDon = 0L;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(c.id) FROM HoaDon c  WHERE MONTH(c.created) = :month AND YEAR(c.created) = :year GROUP BY MONTH(c.created),YEAR(c.created) ";
            Query query = session.createQuery(hql);
            query.setParameter("month", month);
            query.setParameter("year", year);
            soHoaDon = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soHoaDon;
    }

    public Long getSoHoaDonDaHuy(int month, int year) {
        Long soHoaDon = 0L;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(c.id) FROM HoaDon c WHERE c.tinhTrang = 3  AND MONTH(c.created) = :month AND YEAR(c.created) = :year GROUP BY MONTH(c.created),YEAR(c.created) ";
            Query query = session.createQuery(hql);
            query.setParameter("month", month);
            query.setParameter("year", year);
            soHoaDon = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soHoaDon;
    }

    public Long getSoKhachHang() {
        Long soHoaDon = 0L;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(c.id) FROM KhachHang c ";
            Query query = session.createQuery(hql);
            soHoaDon = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soHoaDon;
    }

//    public int getTuoiHienTai(Date date){
//        int tuoi = 0;
//        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
//             String sql = "SELECT "
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
    public static void main(String[] args) {
        System.out.println("Doanh thu của quán là : " + new HoaDonReponsitoryImpl().getDoanhThu(11, 2022));
    }
}
