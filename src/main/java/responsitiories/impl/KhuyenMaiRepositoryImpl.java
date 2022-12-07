/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author FPT Shop
 */
public class KhuyenMaiRepositoryImpl implements IReponsitory<KhuyenMai> {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    private String fromTable = "FROM KhuyenMai";

    @Override
    public List<KhuyenMai> findAll() {
        Query query = session.createQuery(fromTable, KhuyenMai.class);
        return query.getResultList();
    }

    @Override
    public KhuyenMai findOneByMa(String ma) {
        String sql = "From KhuyenMai WHERE maKhuyenMai =: ma";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("ma", ma);
        return (KhuyenMai) query.getResultList().get(0);
    }

    @Override
    public KhuyenMai findOneByID(UUID id) {
        String sql = fromTable + "WHERE id =: id";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("id", id);
        return (KhuyenMai) query.getSingleResult();
    }

    @Override
    public boolean save(KhuyenMai t) {
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
    public boolean update(KhuyenMai t) {
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

    @Override
    public boolean delete(KhuyenMai t) {
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
    
     public List<String> getMaKhuyenMai(){
        List<String> listTen  = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT  c.maKhuyenMai FROM KhuyenMai c ORDER BY c.maKhuyenMai ";
            Query query = session.createQuery(hql);
            listTen = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listTen;
    }

    public static void main(String[] args) {
    }
}
