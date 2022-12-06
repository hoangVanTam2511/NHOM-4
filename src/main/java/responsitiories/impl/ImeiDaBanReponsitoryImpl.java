/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

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
    public ImeiDaBan findOneByMa(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
    public static void main(String[] args) {
        System.out.println(new ImeiDaBanReponsitoryImpl().findOneByImei("926593465555"));
    }

}
