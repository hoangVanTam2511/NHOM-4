/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.ChiTietKhuyenMai;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class ChiTietKhuyenMaiReponsitoryImpl implements IReponsitory<ChiTietKhuyenMai> {

    @Override
    public List<ChiTietKhuyenMai> findAll() {
        List<ChiTietKhuyenMai> listChiTietKhuyenMai = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT a  FROM ChiTietKhuyenMai a";
            Query query = session.createQuery(hql);
            listChiTietKhuyenMai = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listChiTietKhuyenMai;
    }

    @Override
    public ChiTietKhuyenMai findOneByMa(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietKhuyenMai findOneByID(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(ChiTietKhuyenMai t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(t);
                trans.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ChiTietKhuyenMai t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(t);
                trans.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(ChiTietKhuyenMai t) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.delete(t);
                trans.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getSoTienSauKhiTruKhuyenMai(String ma) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
             String hql = "SELECT SUM((c.chiTietKhuyenMaiId.idKhuyenMai.mucGiamGiaPhanTram)*c.chiTietKhuyenMaiId.idChiTietSanPham.donGia/100 + c.chiTietKhuyenMaiId.idKhuyenMai.mucGiamGiaTienMat) FROM ChiTietKhuyenMai c  WHERE c.chiTietKhuyenMaiId.idChiTietSanPham.ma = :ma GROUP BY c.chiTietKhuyenMaiId.idChiTietSanPham";
             Query query = session.createQuery(hql);
             query.setParameter("ma", ma);
             return (double) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static void main(String[] args) {
    }
}
