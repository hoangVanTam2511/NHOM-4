/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import domainmodels.PhieuBaoHanh;
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
public class PhieuBaoHanhRepositoryImpl implements IReponsitory<PhieuBaoHanh> {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    private String fromTable = "FROM PhieuBaoHanh";

    @Override
    public List<PhieuBaoHanh> findAll() {
        Query query = session.createQuery(fromTable, PhieuBaoHanh.class);
        return query.getResultList();
    }

    @Override
    public PhieuBaoHanh findOneByMa(String ma) {
        String sql = "From KhachHang WHERE ma =: ma";
        Query query = session.createQuery(sql, PhieuBaoHanh.class);
        query.setParameter("ma", ma);
        return (PhieuBaoHanh) query.getSingleResult();
    }

    @Override
    public PhieuBaoHanh findOneByID(UUID id) {
        String sql = fromTable + "WHERE id =: id";
        Query query = session.createQuery(sql, PhieuBaoHanh.class);
        query.setParameter("id", id);
        return (PhieuBaoHanh) query.getSingleResult();
    }

    @Override
    public boolean save(PhieuBaoHanh t) {
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
    public boolean update(PhieuBaoHanh t) {
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
    public boolean delete(PhieuBaoHanh t) {
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
    public ArrayList<String> getListTenAnh(){
        ArrayList<String> listTen = new ArrayList<>();// Lấy tên của tất cả các gói bảo hành có sẵen cho từng nhóm 
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction trans = session.getTransaction();
            String hql = "SELECT c.tenPhieuBaoHanh  FROM PhieuBaoHanh c ";
            Query query = session.createQuery(hql);
            listTen  = (ArrayList<String>) query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listTen;
    }
    
     public ArrayList<String> getListmaPBH(){
        ArrayList<String> listTen = new ArrayList<>();// Lấy tên của tất cả các gói bảo hành có sẵen cho từng nhóm 
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction trans = session.getTransaction();
            String hql = "SELECT c.maPhieuBaoHanh  FROM PhieuBaoHanh c ORDER BY  c.maPhieuBaoHanh  ASC";
            Query query = session.createQuery(hql);
            listTen  = (ArrayList<String>) query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listTen;
    }
    
    public String getMaPhieuBaoHanh(String txt){
        String maPhieuBaoHanh  = "";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT  c.maPhieuBaoHanh FROM PhieuBaoHanh c.maPhieuBaoHanh = :maPhieuBaoHanh";
            Query query = session.createQuery(hql);
            query.setParameter("maPhieuBaoHanh", txt);
            maPhieuBaoHanh = (String) query.getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return maPhieuBaoHanh;
    }

    public static void main(String[] args) {
        System.out.println(new PhieuBaoHanhRepositoryImpl().getListTenAnh());
    }
}
