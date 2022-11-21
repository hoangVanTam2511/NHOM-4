/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.CuaHang;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import reponces.QlCuaHang;
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
public class CuaHangReponsitoryImpl implements IReponsitory<CuaHang>{


    @Override
    public List<CuaHang> findAll() {
        List<CuaHang> listCuaHang = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM CuaHang c";
            TypedQuery typedQuery = session.createQuery(hql, CuaHang.class);
            listCuaHang = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCuaHang;
    }

    @Override
    public CuaHang findOneByMa(String ma) {
        CuaHang cuaHang = new CuaHang();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM CuaHang c WHERE c.ma = :id";
            Query query = session.createQuery(hql, CuaHang.class);
            query.setParameter("id", ma);
            cuaHang = (CuaHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cuaHang;
    }
    
     @Override
    public CuaHang findOneByID(UUID id) {
        CuaHang cuaHang = new CuaHang();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM CuaHang c WHERE c.id = :id";
            Query query = session.createQuery(hql, CuaHang.class);
            query.setParameter("id", id);
            cuaHang = (CuaHang) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cuaHang;
    }

    @Override
    public boolean save(CuaHang cuaHang) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            session.save(cuaHang);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(CuaHang cuaHang) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();// Lấy transaction tuơng ứng 
            trans.begin();
            session.delete(cuaHang);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(CuaHang cuaHang) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            session.update(cuaHang);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(new CuaHangReponsitoryImpl().findAll());
    }
}
