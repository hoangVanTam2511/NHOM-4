/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import responsitiories.IReponsitory;
import domainmodels.HoaDonChiTiet;
import infrastructure.convert.FormUtil;
import responsitiories.IHoaDonChiTietReponsitory;
import ultilities.HibernateUtil;
import infrastructure.responce.QlHoaDonChiTietReponce;
import infrastructure.responce.QlThongKeResponce;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietReponsitoryImpl implements IHoaDonChiTietReponsitory {

    @Override
    public List<HoaDonChiTiet> findAll() {
        List<HoaDonChiTiet> listDongSp = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM HoaDonChiTiet p";
            TypedQuery typedQuery = session.createQuery(hql);
            listDongSp = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDongSp;
    }

    @Override
    public HoaDonChiTiet findOneByMaSanPhamAndMaHoaDon(String ma, String maSanPham) {
        HoaDonChiTiet dongSp = new HoaDonChiTiet();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM HoaDonChiTiet p WHERE p.HoaDonChiTietId.idHoaDon.ma = :id and p.HoaDonChiTietId.idChiTietSanPham.idSanPham.soImei = :maSanPham";
            Query query = session.createQuery(hql);
            query.setParameter("id", ma);
            query.setParameter("maSanPham", maSanPham);
            if (query.getSingleResult() == null) {
                dongSp = null;
            } else {
                dongSp = (HoaDonChiTiet) query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dongSp = null;
        }
        return dongSp;

    }

    @Override
    public List<HoaDonChiTiet> getAllByMaHoaDon(String ma) {
        List<HoaDonChiTiet> listDongSp = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM HoaDonChiTiet p  WHERE p.HoaDonChiTietId.idHoaDon.ma = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", ma);
            listDongSp = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDongSp;
    }

    @Override
    public double tinhTongTien(String ma) {
        double tongTien = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(p.donGia * p.soLuong) FROM HoaDonChiTiet p WHERE p.HoaDonChiTietId.idHoaDon.ma = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", ma);
            if (query.getSingleResult() == null) {
                tongTien = 0;
            } else {
                tongTien = (double) query.getSingleResult();
            }

        }
        return tongTien;
    }

    @Override
    public HoaDonChiTiet findOneByID(UUID id) {
        HoaDonChiTiet dongSp = new HoaDonChiTiet();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM HoaDonChiTiet p WHERE p.id = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            dongSp = query.getSingleResult() == null ? null : (HoaDonChiTiet) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dongSp;
    }

    @Override
    public boolean save(HoaDonChiTiet t) {
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
    public boolean update(HoaDonChiTiet t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(t);
                trans.commit();
            } catch (Exception e) {
                trans.rollback();
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean delete(HoaDonChiTiet t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();// L???y transaction tu??ng ???ng 
            trans.begin();
            session.delete(t);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<QlHoaDonChiTietReponce> findGioHang(String ma) {
        List<QlHoaDonChiTietReponce> listQlHoaDonChiTiets = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT new "
                    + "infrastructure.responce.QlHoaDonChiTietReponce(p.HoaDonChiTietId.idChiTietSanPham.idSanPham.soImei,"
                    + "p.HoaDonChiTietId.idChiTietSanPham.idSanPham.ten,p.soLuong,p.donGia,SUM(p.soLuong * p.donGia)) FROM HoaDonChiTiet p "
                    + "WHERE  p.HoaDonChiTietId.idHoaDon.ma = :ma "
                    + "GROUP BY p.HoaDonChiTietId.idChiTietSanPham.idSanPham.soImei,p.HoaDonChiTietId.idChiTietSanPham.idSanPham.ten,p.soLuong,p.donGia";
            Query query = session.createQuery(hql, QlHoaDonChiTietReponce.class);
            query.setParameter("ma", ma);
            listQlHoaDonChiTiets = query.getResultList();
        }
        return listQlHoaDonChiTiets;
    }

    public double tinhTongTienBanDau(String maHoaDon) {
        double tongTien = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(p.HoaDonChiTietId.idChiTietSanPham.donGia * p.soLuong) FROM HoaDonChiTiet p WHERE p.HoaDonChiTietId.idHoaDon.ma = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", maHoaDon);
            if (query.getSingleResult() == null) {
                tongTien = 0;
            } else {
                tongTien = (double) query.getSingleResult();
            }

        }
        return tongTien;
    }
    
    public List<QlThongKeResponce> getSanPhamThongKeTheoThang(int month,int year){
        List<QlThongKeResponce> qlThongKeResponces = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT new infrastructure.responce.QlThongKeResponce( "
                    + " p.HoaDonChiTietId.idChiTietSanPham.idSanPham.ten,"
                    + "SUM(p.soLuong) as so_luong_theo_thang,p.donGia,"
                    + "SUM(p.tongTien) as tong_tien_theo_thang ) "
                    + "FROM HoaDonChiTiet p "
                    + "WHERE MONTH(p.HoaDonChiTietId.idHoaDon.created) = :month AND  YEAR(p.HoaDonChiTietId.idHoaDon.created) = :year "
                    + " GROUP BY p.donGia,p.HoaDonChiTietId.idChiTietSanPham.idSanPham.ten";
            Query query = session.createQuery(hql);
            query.setParameter("month", month);
            query.setParameter("year", year);
            qlThongKeResponces  = query.getResultList();
        }
        return qlThongKeResponces;
    }

    public static void main(String[] args) {
        System.out.println(new HoaDonChiTietReponsitoryImpl().getSanPhamThongKeTheoThang(11, 2022));       
    }

}
