/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.SanPham;
import responsitiories.impl.SanPhamReponsitoryImpl;
import services.IService;
import reponces.QlSanPham;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author Admin
 */
public class SanPhamServiceImpl implements IService<QlSanPham>{

    private SanPhamReponsitoryImpl sanPhamReponsitoryImpl;
    public SanPhamServiceImpl(){
        this.sanPhamReponsitoryImpl = new SanPhamReponsitoryImpl();
    }
    
    @Override
    public List<QlSanPham> findAll() {
      List<QlSanPham> listSanPham = new ArrayList<>();
      this.sanPhamReponsitoryImpl.findAll().forEach(sanPham->{
          listSanPham.add(FormUtil.convertFromSanPhamToQlSanPham(sanPham));
      });
      return listSanPham;
    }

    @Override
    public QlSanPham findOne(String ma) {
        return new QlSanPham();
//       SanPham sanPham = this.sanPhamReponsitoryImpl.findOneByMa(ma);
//       return FormUtil.convertFromSanPhamToQlSanPham(sanPham);
    }
     public QlSanPham findOne(UUID ma) {
      SanPham ram = this.sanPhamReponsitoryImpl.findOneByID(ma);
        return FormUtil.convertFromSanPhamToQlSanPham(ram);
    }
    public QlSanPham findOneByLmei(String imei) {
        SanPham sp = this.sanPhamReponsitoryImpl.findOneByLmei(imei);
        return FormUtil.convertFromSanPhamToQlSanPham(sp);
    }
     public QlSanPham findOneByTen(String Ten) {
        SanPham sp = this.sanPhamReponsitoryImpl.findOneByTen(Ten);
        return FormUtil.convertFromSanPhamToQlSanPham(sp);
    }
    @Override
    public String save(QlSanPham t) {
         SanPham sanPham = FormUtil.convertFromQlSanPhamToSanPham(t);
         return this.sanPhamReponsitoryImpl.save(sanPham)==true?"Thêm thành công":"Thêm thất bại";
    }

    @Override
    public String delete(QlSanPham t) {
        SanPham sanPham = FormUtil.convertFromQlSanPhamToSanPham(t);
        return this.sanPhamReponsitoryImpl.delete(sanPham)==true?"Xóa thành công":"Xóa thất bại";
    }

    @Override
    public String update(QlSanPham t) {
      SanPham sanPham = FormUtil.convertFromQlSanPhamToSanPham(t);
      return this.sanPhamReponsitoryImpl.update(sanPham)==true?"Sửa thành công":"Sửa thất bại";
    }
    
    public static void main(String[] args) {
        System.out.println(new SanPhamServiceImpl().findAll() );
    }
}
