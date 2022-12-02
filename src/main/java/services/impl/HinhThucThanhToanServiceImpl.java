/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import reponces.QlHinhThucThanhToan;
import responsitiories.impl.HinhThucThanhToanReponsitoryImpl;
import services.IService;

/**
 *
 * @author Admin
 */
public class HinhThucThanhToanServiceImpl implements IService<QlHinhThucThanhToan>{
    
    private HinhThucThanhToanReponsitoryImpl hinhThucThanhToanReponsitory;

    public HinhThucThanhToanServiceImpl() {
    this.hinhThucThanhToanReponsitory = new HinhThucThanhToanReponsitoryImpl();
    }

    @Override
    public List<QlHinhThucThanhToan> findAll() {
       List<QlHinhThucThanhToan> qlHinhThucThanhToans = new ArrayList<>();
       this.hinhThucThanhToanReponsitory.findAll().forEach(hinhThucThanhToan ->{
           qlHinhThucThanhToans.add(FormUtil.covertQlHinhThucThanhToanToHinhThucThanhToan(hinhThucThanhToan));
       });
       return qlHinhThucThanhToans;
    }

    @Override
    public QlHinhThucThanhToan findOne(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String save(QlHinhThucThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(QlHinhThucThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(QlHinhThucThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        System.out.println(new HinhThucThanhToanServiceImpl().findAll());
    }
}
