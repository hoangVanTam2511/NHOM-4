/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import DomainModels.ChucVu;
import DomainModels.CuaHang;
import DomainModels.NhanVien;
import Repositories.impl.ChucVuReponsitoryImpl;
import Repositories.impl.CuaHangReponsitoryImpl;
import Repositories.impl.NhanVienReponsitoryImpl;
import Services.IService;
import ViewModels.QlNhanVien;
import infrastructure.convert.FormUtil;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class NhanVienServiceImpl {

    private NhanVienReponsitoryImpl nhanVienReponsitory;
    private CuaHangReponsitoryImpl cuaHangReponsitory;
    private ChucVuReponsitoryImpl chucVuReponsitory;

    public NhanVienServiceImpl() {
        this.nhanVienReponsitory = new NhanVienReponsitoryImpl();
        this.cuaHangReponsitory = new CuaHangReponsitoryImpl();
        this.chucVuReponsitory = new ChucVuReponsitoryImpl();
    }

   
    public List<QlNhanVien> findAll() {
        List<QlNhanVien> qlNhanViens = new ArrayList<>();
        this.nhanVienReponsitory.findAll().forEach(nv -> {
            qlNhanViens.add(FormUtil.convertFromNhanVienToQlNhanVien(nv));
        });
        return qlNhanViens;
    }

    
    public QlNhanVien findOne(String ma) {
        NhanVien nv = this.nhanVienReponsitory.findOneByMa(ma);
        return FormUtil.convertFromNhanVienToQlNhanVien(nv);
    }

    
    public String save(QlNhanVien qlNhanVien) {
      NhanVien nhanVien = FormUtil.convertFromQlNhanVienToNhanVien(qlNhanVien);
      return this.nhanVienReponsitory.save(nhanVien)==true?"Thêm thành công":"Thêm thât bại";
    }

  
    public String delete(QlNhanVien qlNhanVien) {
        return this.nhanVienReponsitory.delete(FormUtil.convertFromQlNhanVienToNhanVien(qlNhanVien)) == true
                ? "Xóa nhân viên thành công" : "Xóa nhân viên thất bại";
    }

    
    public String update( QlNhanVien qlNhanVien) {
        NhanVien nhanVien = FormUtil.convertFromQlNhanVienToNhanVien(qlNhanVien);
        return this.nhanVienReponsitory.update(nhanVien)==true?"Sửa thành công"
                :"Sửa thất bại";
        
    }
    public String validate(String ma, String hoVaTen, String ngaySinh, String diaChi, String sdt, String matKhau) {
     if(ma.isBlank() || hoVaTen.isBlank() || ngaySinh.isBlank() || diaChi.isBlank()){
           return "Bạn phải nhập đủ các trường dữ liệu"; 
       }
      if(!matKhau.matches("\\w{8}")){
          return "mật khẩu phải đủ 8 ký tự";
      }
      if(!sdt.matches("0+\\d{9}")){
          return " Số điện thoại phải đủ 10 số và số 0 phải ở đầu";
      }
      
      String ngaySinhs[] = ngaySinh.split("-");
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/");
      Date date = new Date();
      String day = simpleDateFormat.format(date);
      String days[] = ngaySinh.split("/");
      if((Integer.parseInt(days[2]) -Integer.parseInt(ngaySinhs[2]))<18){
          return "Tuổi phải lớn hơn 18";
      }
      return "";
    }


            
}
