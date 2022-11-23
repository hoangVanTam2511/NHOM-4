/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import DomainModels.NhanVien;
import Repositories.IReponsitory;
import Utilities.HibernateUtil;
import ViewModels.QlNhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class NhanVienReponsitoryImpl implements IReponsitory<NhanVien> {

    @Override
    public List<NhanVien> findAll() {
        List<NhanVien> listNhanVien = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            Query query = session.createQuery("SELECT n FROM NhanVien n");
            listNhanVien = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanVien;
    }

    @Override
    public NhanVien findOneByMa(String ma) {
        List<NhanVien> nhanViens = new ArrayList<>() ;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "FROM NhanVien where ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            nhanViens =  query.getResultList();
            if(nhanViens.size()==0)return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanViens.get(0);
    }
    
       @Override
    public NhanVien findOneByID(UUID id) {
        List<NhanVien> nhanViens = new ArrayList<>() ;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "FROM NhanVien where id = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", id);
            nhanViens =  query.getResultList();
            if(nhanViens.size()==0)return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanViens.get(0);
    }

    @Override
    public boolean save(NhanVien nhanVien) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(nhanVien);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(NhanVien nv) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.delete(nv);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(NhanVien nhanVien) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(nhanVien);
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return false;
            }
        }
        return true;
    }

    //test
    public static void main(String[] args) {
        // test du an mau
        NhanVien nv = new NhanVienReponsitoryImpl().findOneByMa("NV01");
        System.out.println(nv.toString());
        nv.setDiaChi("ha noi ");
        nv.setTen("tam");
        System.out.println(nv);
        System.out.println(new NhanVienReponsitoryImpl().update(nv));
    }
}
