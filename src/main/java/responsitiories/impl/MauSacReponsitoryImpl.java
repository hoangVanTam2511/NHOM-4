/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.MauSac;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;
import reponces.QlMauSac;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 */
public class MauSacReponsitoryImpl implements IReponsitory<MauSac> {

    @Override
    public List<MauSac> findAll() {
        List<MauSac> listMauSac = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m  FROM MauSac m";
            TypedQuery typedQuery = session.createQuery(hql, MauSac.class);
            listMauSac = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMauSac;
    }

    @Override
    public MauSac findOneByMa(String ma) {
        MauSac mauSac = new MauSac();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m  FROM MauSac m where m.ma = :id";
            TypedQuery typedQuery = session.createQuery(hql, MauSac.class);
            typedQuery.setParameter("id", ma);
            mauSac = (MauSac) typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSac;
    }
    
       @Override
    public MauSac findOneByID(UUID id) {
        MauSac mauSac = new MauSac();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m  FROM MauSac m where m.id = :id";
            TypedQuery typedQuery = session.createQuery(hql, MauSac.class);
            typedQuery.setParameter("id", id);
            mauSac = (MauSac) typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSac;
    }

    @Override
    public boolean save(MauSac mauSac) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.getTransaction();
            tran.begin();
            session.save(mauSac);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(MauSac mauSac) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.getTransaction();
            tran.begin();
            session.delete(mauSac);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update( MauSac mauSac) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            session.update(mauSac);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
