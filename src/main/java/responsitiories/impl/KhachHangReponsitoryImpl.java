/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.KhachHang;
import responsitiories.IKhachHangService;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import java.util.ArrayList;
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
public class KhachHangReponsitoryImpl implements IKhachHangService{

    public List<KhachHang> findAll(String diaChi) {
        List<KhachHang> listCuaHang = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM KhachHang c WHERE c.diaChi like CONCAT('%',:diaChi,'%')";
            TypedQuery typedQuery = session.createQuery(hql, KhachHang.class);
            typedQuery.setParameter("diaChi", diaChi);
            listCuaHang = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCuaHang;
    }

    @Override
    public KhachHang findOneByMa(String ma) {
        KhachHang khachHang = new KhachHang();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM KhachHang c WHERE c.ma = :id";
            Query query = session.createQuery(hql, KhachHang.class);
            query.setParameter("id", ma);
            khachHang = (KhachHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    @Override
    public KhachHang findOneByID(UUID id) {
        KhachHang khachHang = new KhachHang();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM CuaHang c WHERE c.ma = :id";
            Query query = session.createQuery(hql, KhachHang.class);
            query.setParameter("id", id);
            khachHang = (KhachHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    @Override
    public boolean save(KhachHang t) {
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
    public boolean delete(KhachHang t) {
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
    public boolean update(KhachHang t) {
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

}
