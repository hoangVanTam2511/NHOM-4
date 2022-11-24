/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.Anh;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import reponces.QlAnh;
import responsitiories.impl.AnhReponsitoryImpl;
import services.IService;

/**
 *
 * @author Admin
 */
public class AnhServiceImpl implements IService<QlAnh> {

    private AnhReponsitoryImpl anhReponsitoryImpl;

    public AnhServiceImpl() {
        this.anhReponsitoryImpl = new AnhReponsitoryImpl();
    }

    @Override
    public List<QlAnh> findAll() {
      List<QlAnh> listQlAnh = new ArrayList<>();
      this.anhReponsitoryImpl.findAll().forEach(anh ->{
          listQlAnh.add(FormUtil.convertQlAnhToAnh(anh));
      });
      return listQlAnh;
    }

    @Override
    public QlAnh findOne(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String save(QlAnh t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(QlAnh t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(QlAnh t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        System.out.println(new AnhServiceImpl().findAll());
    }
    
}
