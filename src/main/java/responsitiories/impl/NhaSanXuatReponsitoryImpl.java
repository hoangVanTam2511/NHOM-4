/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.NSX;
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
public class NhaSanXuatReponsitoryImpl implements IReponsitory<NSX> {

    @Override
    public List<NSX> findAll() {
        List<NSX> listNhaSanXuat = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT nsx FROM NSX nsx";
            TypedQuery typedQuery = session.createQuery(hql,NSX.class);
            listNhaSanXuat = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhaSanXuat;
    }

    @Override
    public NSX findOneByMa(String ma) {
        NSX nhaSanXuat = new NSX();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT nsx FROM NSX  nsx WHERE nsx.ma = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", ma);
            nhaSanXuat = (NSX) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhaSanXuat;
    }
    
      @Override
    public NSX findOneByID(UUID id) {
        NSX nhaSanXuat = new NSX();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT nsx FROM NSX  nsx WHERE nsx.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            nhaSanXuat = (NSX) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhaSanXuat;
    }

    @Override
    public boolean save(NSX nhaSanXuat) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            session.save(nhaSanXuat);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(NSX nsx) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "DELETE NSX WHERE id = :id";
            Transaction trans = session.getTransaction();
            trans.begin();
            session.delete(nsx);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update( NSX nhaSanXuat) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            session.update(nhaSanXuat);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
