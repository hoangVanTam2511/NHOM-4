/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.HinhThucThanhToan;
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
public class HinhThucThanhToanReponsitoryImpl implements IReponsitory<HinhThucThanhToan> {

    private Session sesion = HibernateUtil.getSessionFactory().openSession();

    private String fromtable = "from HinhThucThanhToan";

    @Override
    public List<HinhThucThanhToan> findAll() {
        Query query = sesion.createQuery(fromtable);
        return query.getResultList();
    }

    @Override
    public HinhThucThanhToan findOneByID(UUID id) {
        String sql = fromtable + "where id =: id";//cái 1 lấy từ model, cái 2 từ sql
        Query query = sesion.createQuery(sql, HinhThucThanhToan.class);
        query.setParameter("id", id);//lấy từ sql
        return (HinhThucThanhToan) query.getSingleResult();
    }

    @Override
    public boolean save(HinhThucThanhToan t) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean delete(HinhThucThanhToan t) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean update(HinhThucThanhToan t) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public HinhThucThanhToan findOneByMa(String ma) {
        String sql = fromtable + " where maHinhThuc = :maHinhThuc";//cái 1 lấy từ model, cái 2 từ sql
        Query query = sesion.createQuery(sql, HinhThucThanhToan.class);
        query.setParameter("maHinhThuc", ma);//lấy từ sql
        return (HinhThucThanhToan) query.getSingleResult();
    }

    public static void main(String[] args) {
        System.out.println(new HinhThucThanhToanReponsitoryImpl().findOneByMa("BITCOIN"));
        
        
        
    }

}
