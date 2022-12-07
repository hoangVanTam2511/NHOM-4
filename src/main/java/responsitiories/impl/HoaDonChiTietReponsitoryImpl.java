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
import infrastructure.responce.QlThongKeChartReponce;
import infrastructure.responce.QlThongKeResponce;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
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
            String hql = "SELECT p FROM HoaDonChiTiet p WHERE p.idHoaDon.ma = :id and p.idChiTietSanPham.ma = :maSanPham";
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
            String hql = "SELECT p FROM HoaDonChiTiet p  WHERE p.idHoaDon.ma = :id ";
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
            String hql = "SELECT SUM(p.donGia * p.soLuong) FROM HoaDonChiTiet p WHERE p.idHoaDon.ma = :id";
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
            Transaction trans = session.getTransaction();// Lấy transaction tuơng ứng 
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
                    + "infrastructure.responce.QlHoaDonChiTietReponce(p.HoaDonChiTietId.idChiTietSanPham.ma,"
                    + "p.idChiTietSanPham.ten,p.soLuong,p.donGia,SUM(p.soLuong * p.donGia)) FROM HoaDonChiTiet p "
                    + "WHERE  p.idHoaDon.ma = :ma "
                    + "GROUP BY p.idChiTietSanPham.idSanPham.soImei,p.idChiTietSanPham.idSanPham.ten,p.soLuong,p.donGia";
            Query query = session.createQuery(hql, QlHoaDonChiTietReponce.class);
            query.setParameter("ma", ma);
            listQlHoaDonChiTiets = query.getResultList();
        }
        return listQlHoaDonChiTiets;
    }

    public double tinhTongTienBanDau(String maHoaDon) {
        double tongTien = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(p.idChiTietSanPham.donGia * p.soLuong) FROM HoaDonChiTiet p WHERE p.idHoaDon.ma = :id";
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

    public List<QlThongKeResponce> getSanPhamThongKeTheoThang(Date ngayBatDau, Date ngayKetThuc) {
        List<QlThongKeResponce> qlThongKeResponces = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT new infrastructure.responce.QlThongKeResponce( "
                    + " p.idChiTietSanPham.ten,"
                    + "SUM(p.soLuong) as so_luong_theo_thang,p.donGia,"
                    + "SUM(p.tongTien) as tong_tien_theo_thang ) "
                    + "FROM HoaDonChiTiet p "
                    + "WHERE p.idHoaDon.created > :ngayBatDau AND  p.idHoaDon.created < :ngayKetThuc "
                    + " GROUP BY p.donGia,p.idChiTietSanPham.ten";
            Query query = session.createQuery(hql);
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("ngayKetThuc", ngayKetThuc);
            qlThongKeResponces = query.getResultList();
        }
        return qlThongKeResponces;
    }

//    public List<Long> getSanPhamThongKeTheoThangToChartSoLuong(Date ngayBatDau, Date ngayKetThuc) {
//        List<Long> qlThongKeResponces = new ArrayList<>();
//        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "SELECT "
//                    + " SUM(p.soLuong) as so_luong "
//                    + " FROM HoaDonChiTiet p "
//                    + "WHERE p.HoaDonChiTietId.idHoaDon.created > :ngayBatDau AND  p.HoaDonChiTietId.idHoaDon.created < :ngayKetThuc "
//                    + " GROUP BY FORMAT(p.HoaDonChiTietId.idHoaDon.created, 'd') order by FORMAT(p.HoaDonChiTietId.idHoaDon.created, 'd') desc";
//            Query query = session.createQuery(hql);
//            query.setParameter("ngayBatDau", ngayBatDau);
//            query.setParameter("ngayKetThuc", ngayKetThuc);
//            qlThongKeResponces = query.getResultList();
//        }
//        return qlThongKeResponces;
//    }
//
//    public List<Long> getSanPhamThongKeTheoThangToChartNgay(Date ngayBatDau, Date ngayKetThuc) {
//        List<Long> qlThongKeResponces = new ArrayList<>();
//        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "SELECT "
//                    + " Day(p.HoaDonChiTietId.idHoaDon.created) as so_luong "
//                    + " FROM HoaDonChiTiet p "
//                    + "WHERE p.HoaDonChiTietId.idHoaDon.created > :ngayBatDau AND  p.HoaDonChiTietId.idHoaDon.created < :ngayKetThuc "
//                    + " GROUP BY Day(p.HoaDonChiTietId.idHoaDon.created) order by Day(p.HoaDonChiTietId.idHoaDon.created) asc";
//            Query query = session.createQuery(hql);
//            query.setParameter("ngayBatDau", ngayBatDau);
//            query.setParameter("ngayKetThuc", ngayKetThuc);
//            qlThongKeResponces = query.getResultList();
//        }
//        return qlThongKeResponces;
//    }
//
//    public List<String> getSanPhamThongKeTheoThangToChartThang(Date ngayBatDau, Date ngayKetThuc) {
//        List<String> qlThongKeResponces = new ArrayList<>();
//        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "SELECT "
//                    + " Convert(nvarchar(50),p.HoaDonChiTietId.idHoaDon.created, 103) "
//                    + " FROM HoaDonChiTiet p "
//                    + "WHERE p.HoaDonChiTietId.idHoaDon.created > :ngayBatDau AND  p.HoaDonChiTietId.idHoaDon.created < :ngayKetThuc "
//                    + " GROUP BY Convert(nvarchar(50),p.HoaDonChiTietId.idHoaDon.created, 103) order by Convert(nvarchar(50),p.HoaDonChiTietId.idHoaDon.created, 103) asc";
//            Query query = session.createQuery(hql);
//            query.setParameter("ngayBatDau", ngayBatDau);
//            query.setParameter("ngayKetThuc", ngayKetThuc);
//            qlThongKeResponces = query.getResultList();
//        }
//        return qlThongKeResponces;
//    }
//
//    public List<Long> getSanPhamThongKeTheoThangToChartNam(Date ngayBatDau, Date ngayKetThuc) {
//        List<Long> qlThongKeResponces = new ArrayList<>();
//        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "SELECT "
//                    + " Year(p.HoaDonChiTietId.idHoaDon.created) as so_luong "
//                    + " FROM HoaDonChiTiet p "
//                    + "WHERE p.HoaDonChiTietId.idHoaDon.created > :ngayBatDau AND  p.HoaDonChiTietId.idHoaDon.created < :ngayKetThuc "
//                    + " GROUP BY Year(p.HoaDonChiTietId.idHoaDon.created) order by Year(p.HoaDonChiTietId.idHoaDon.created) asc";
//            Query query = session.createQuery(hql);
//            query.setParameter("ngayBatDau", ngayBatDau);
//            query.setParameter("ngayKetThuc", ngayKetThuc);
//            qlThongKeResponces = query.getResultList();
//        }
//        return qlThongKeResponces;
//    }

//    public List<QlThongKeChartReponce> getSanPhamThongKeTheoThangToChar(Date ngayBatDau,Date ngayKetThuc) {
//        List<QlThongKeChartReponce> getDataToCharts = new ArrayList<>();
//        List<Long> soluongs = getSanPhamThongKeTheoThangToChartSoLuong(ngayBatDau, ngayKetThuc);
//        List<Long> ngays = getSanPhamThongKeTheoThangToChartNgay(ngayBatDau, ngayKetThuc);
//        List<Long> thangs = getSanPhamThongKeTheoThangToChartThang(ngayBatDau, ngayKetThuc);
//        List<Long> nams = getSanPhamThongKeTheoThangToChartNam(ngayBatDau, ngayKetThuc);
//      
//    }

    public static void main(String[] args) {
        System.out.println(new HoaDonChiTietReponsitoryImpl().findOneByMaSanPhamAndMaHoaDon("HD00", "SP1"));
    }

}
