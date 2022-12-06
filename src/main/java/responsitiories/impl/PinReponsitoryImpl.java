/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories.impl;

import domainmodels.Pin;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import responsitiories.IReponsitory;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class PinReponsitoryImpl implements IReponsitory<Pin>{

    @Override
    public List<Pin> findAll() {
        List<Pin> chiTietSanPhams = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.clear();
            String hql = "SELECT ct FROM Pin ct";
            TypedQuery query = session.createQuery(hql, Pin.class);
            chiTietSanPhams = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    @Override
    public Pin findOneByMa(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Pin findOneByID(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(Pin t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Pin t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Pin t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        System.out.println(new PinReponsitoryImpl().findAll());
    }
    
}
