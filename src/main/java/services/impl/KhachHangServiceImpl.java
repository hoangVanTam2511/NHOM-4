/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import domainmodels.KhachHang;
import responsitiories.impl.KhachHangReponsitoryImpl;
import services.IService;
import reponces.QlKhachHang;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangServiceImpl implements IService<QlKhachHang> {

    private KhachHangReponsitoryImpl khachHangReponsitoryImpl;
    private List<QlKhachHang> listKhachHang;

    public KhachHangServiceImpl() {
        this.khachHangReponsitoryImpl = new KhachHangReponsitoryImpl();
        this.listKhachHang = new ArrayList<QlKhachHang>();
    }


    public List<QlKhachHang> findAll(String diaChi) {
        listKhachHang = new ArrayList<>();
        this.khachHangReponsitoryImpl.findAll(diaChi).forEach(khachHang -> {
            listKhachHang.add(FormUtil.convertFromKhachHangToQlKhachHang(khachHang));
        });
        return listKhachHang;
    }

    @Override
    public QlKhachHang findOne(String ma) {
        KhachHang khachHang = this.khachHangReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertFromKhachHangToQlKhachHang(khachHang);
    }

    @Override
    public String save(QlKhachHang t) {
        KhachHang khachHang = FormUtil.convertFromQlKhachHangToKhachHang(t);
        return this.khachHangReponsitoryImpl.save(khachHang)==true?"Thêm thành công":"Thêm thất bại";
    }

    @Override
    public String delete(QlKhachHang t) {
        KhachHang khachHang = FormUtil.convertFromQlKhachHangToKhachHang(t);
        return this.khachHangReponsitoryImpl.delete(khachHang)==true?"Xóa thành công":"Xóa thất bại";
    }

    @Override
    public String update(QlKhachHang t) {
        KhachHang khachHang = FormUtil.convertFromQlKhachHangToKhachHang(t);
        return this.khachHangReponsitoryImpl.update(khachHang)==true?"Sửa thành công":"Sửa thất bại";
    }

    public String checkDuLieu(QlKhachHang qlKhachHang) {
      if(qlKhachHang.getDiaChi().isBlank() || qlKhachHang.getTen().isBlank()|| qlKhachHang.getTenDem().isBlank() || qlKhachHang.getHo().isBlank() || qlKhachHang.getMa().isBlank()){
          return "Bạn phải nhập đầy đủ thông tin ";
      }
      if(qlKhachHang.getDiaChi().matches("\\d+")){
          return "Địa chỉ phải là chữ";
      }
      if(qlKhachHang.getTen().matches("\\d+")){
          return "Tên phải là chữ";
      }
      if(qlKhachHang.getTenDem().matches("\\d+")){
          return "Tên đệm phải là chữ";
      }
      if(qlKhachHang.getHo().matches("\\d+")){
          return "Họ phải là chữ";
      }
      if(!qlKhachHang.getMa().matches("PH+\\d{8}")){
          return "Mã phải đúng định dạng";
      }
      for(QlKhachHang qlKhachHang1 : listKhachHang){
          if(qlKhachHang.getMa().equals(qlKhachHang1.getMa())){
              return "Mã không được trùng";
          }
      }
      return "";
    }

    @Override
    public List<QlKhachHang> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}
