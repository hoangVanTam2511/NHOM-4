/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.Imei;
import responsitiories.impl.SanPhamReponsitoryImpl;
import services.IService;
import reponces.QlImei;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SanPhamServiceImpl implements IService<QlImei>{

    private SanPhamReponsitoryImpl sanPhamReponsitoryImpl;
    public SanPhamServiceImpl(){
        this.sanPhamReponsitoryImpl = new SanPhamReponsitoryImpl();
    }
    
    @Override
    public List<QlImei> findAll() {
      List<QlImei> listSanPham = new ArrayList<>();
      this.sanPhamReponsitoryImpl.findAll().forEach(sanPham->{
          listSanPham.add(FormUtil.convertFromSanPhamToQlSanPham(sanPham));
      });
      return listSanPham;
    }

    @Override
    public QlImei findOne(String ma) {
        return new QlImei();
//       SanPham sanPham = this.sanPhamReponsitoryImpl.findOneByMa(ma);
//       return FormUtil.convertFromSanPhamToQlSanPham(sanPham);
    }

    @Override
    public String save(QlImei t) {
         Imei sanPham = FormUtil.convertFromQlSanPhamToSanPham(t);
         return this.sanPhamReponsitoryImpl.save(sanPham)==true?"Thêm thành công":"Thêm thất bại";
    }

    @Override
    public String delete(QlImei t) {
        Imei sanPham = FormUtil.convertFromQlSanPhamToSanPham(t);
        return this.sanPhamReponsitoryImpl.delete(sanPham)==true?"Xóa thành công":"Xóa thất bại";
    }

    @Override
    public String update(QlImei t) {
      Imei sanPham = FormUtil.convertFromQlSanPhamToSanPham(t);
      return this.sanPhamReponsitoryImpl.update(sanPham)==true?"Sửa thành công":"Sửa thất bại";
    }
    
    public static void main(String[] args) {
        System.out.println(new SanPhamServiceImpl().findAll() );
    }
}
