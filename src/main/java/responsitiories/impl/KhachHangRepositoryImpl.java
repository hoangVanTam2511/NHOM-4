/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author FPT Shop
 */
public class KhachHangRepositoryImpl implements IReponsitory<KhachHang> {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    private String fromTable = "FROM KhachHang"; 

    @Override
    public List<KhachHang> findAll() {
        Query query = session.createQuery(fromTable, KhachHang.class);
        return query.getResultList();
    }

    @Override
    public KhachHang findOneByMa(String ma) {
        String sql = "From KhachHang WHERE ma =: ma";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("ma", ma);
        return (KhachHang) query.getSingleResult();
    }

    @Override
    public KhachHang findOneByID(UUID id) {
        String sql = fromTable + "WHERE id =: id";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("id", id);
        return (KhachHang) query.getSingleResult();
    }

    @Override
    public boolean save(KhachHang t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean delete(KhachHang t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean update(KhachHang t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
    
    public List<String> getMa(){
        List<String> mas = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "select ma FROM KhachHang ORDER BY ma";
            TypedQuery query = session.createQuery(hql);
            return query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return mas;
    }

    public static void main(String[] args) {
        System.out.println(new KhachHangRepositoryImpl().getMa());
    }
}
