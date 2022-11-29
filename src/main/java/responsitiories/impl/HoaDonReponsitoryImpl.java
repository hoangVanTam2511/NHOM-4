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
public class HoaDonReponsitoryImpl implements IHoaDonReponsitory{

   
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

    @Override
    public HoaDon findOneByMa(String ma) {
        HoaDon hoaDon = new HoaDon();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM HoaDon c WHERE c.ma = :id";
            Query query = session.createQuery(hql, HoaDon.class);
            query.setParameter("id", ma);
            hoaDon = (HoaDon) query.getSingleResult();
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
    
    
//    public int getTuoiHienTai(Date date){
//        int tuoi = 0;
//        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
//             String sql = "SELECT "
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
}