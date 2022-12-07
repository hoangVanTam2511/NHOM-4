/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.Imei;
import domainmodels.ImeiDaBan;
import domainmodels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class ImeiDaBanReponsitoryImpl implements IReponsitory<ImeiDaBan> {

    @Override
    public List<ImeiDaBan> findAll() {
        List<ImeiDaBan> listHoaDons = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM ImeiDaBan c";
            TypedQuery typedQuery = session.createQuery(hql, ImeiDaBan.class);
            listHoaDons = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoaDons;
    }

    @Override
    public ImeiDaBan findOneByMa(String soImei) {
        ImeiDaBan imeiDaBan = new ImeiDaBan();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM ImeiDaBan ct where ct.soImei = :imei";
            TypedQuery query = session.createQuery(hql, ImeiDaBan.class);
            query.setParameter("imei", soImei);
            return (ImeiDaBan) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imeiDaBan;
    }

    @Override
    public ImeiDaBan findOneByID(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(ImeiDaBan t) {
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
    public boolean update(ImeiDaBan t) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean delete(ImeiDaBan t) {
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

    public void setTinhTrangImeiKhiMuaHang(String imei) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "UPDATE ImeiDaBan a SET a.tinhTrang = 0 WHERE a.soImei = :ma ";
            Query query = session.createQuery(hql);
            query.setParameter("ma", imei);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ImeiDaBan findOneByImei(String imei) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sql = "From ImeiDaBan c WHERE c.soImei  = :ma";
            Query query = session.createQuery(sql);
            query.setParameter("ma", imei);
            return (ImeiDaBan) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long getSoLuongDaBan(UUID ma) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT  count(a.id) FROM ImeiDaBan a "
                    + " WHERE a.idHoaDonChiTiet.id = :ma AND a.trangThai = 0 "
                    + " GROUP BY a.idHoaDonChiTiet.id";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            if (query.getSingleResult() == null) {
                return 0l;
            }
            return (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public List<ImeiDaBan> getDanhSachImeiTheoTungMaSanPham(UUID idHoaDon) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT  a FROM ImeiDaBan a "
                    + " WHERE a.idHoaDonChiTiet.id = :ma AND a.trangThai = 0 ";
            Query query = session.createQuery(hql);
            query.setParameter("ma", idHoaDon);
            if (query.getResultList() == null) {
                return null;
            }
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTinhTrangImeiDaBanKhiTraLai(String soImei) {
        ImeiDaBan imeiDaBan = new ImeiDaBanReponsitoryImpl().findOneByMa(soImei);
        imeiDaBan.setTrangThai(true);
        new ImeiDaBanReponsitoryImpl().update(imeiDaBan);
    }

    
    public void setTinhTrangImeiDaBanKhiTraLaiHetHang(String soImei) {
        ImeiDaBan imeiDaBan = new ImeiDaBanReponsitoryImpl().findOneByMa(soImei);
        imeiDaBan.setTrangThai(true);
        imeiDaBan.setIdHoaDonChiTiet(null);
        new ImeiDaBanReponsitoryImpl().update(imeiDaBan);
    }
    public static void main(String[] args) {
       new ImeiDaBanReponsitoryImpl().setTinhTrangImeiDaBanKhiTraLaiHetHang("926593426593");
    }

}
