/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import reponces.QlKhuyenMai;
import responsitiories.impl.KhuyenMaiRepositoryImpl;
import services.IService;
import ultilities.HibernateUtil;

/**
 *
 * @author FPT Shop
 */
public class KhuyenMaiServiceImpl implements IService<QlKhuyenMai>{

    private KhuyenMaiRepositoryImpl khuyenMaiRepositoryImpl = new KhuyenMaiRepositoryImpl();
    
    @Override
    public List<QlKhuyenMai> findAll() {
        
    }

    @Override
    public QlKhuyenMai findOne(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String save(QlKhuyenMai t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(QlKhuyenMai t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(QlKhuyenMai t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
