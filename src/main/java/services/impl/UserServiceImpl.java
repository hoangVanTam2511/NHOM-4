/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.ChucVu;
import domainmodels.User;
import responsitiories.impl.ChucVuReponsitoryImpl;
import responsitiories.impl.UserReponsitoryImpl;
import services.IService;
import reponces.QlUser;
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
public class UserServiceImpl {

    private UserReponsitoryImpl userReponsitoryImpl;
    private ChucVuReponsitoryImpl chucVuReponsitory;

    public UserServiceImpl() {
        this.userReponsitoryImpl = new UserReponsitoryImpl();
        this.chucVuReponsitory = new ChucVuReponsitoryImpl();
    }

    public List<QlUser> findAll() {
        List<QlUser> qlNhanViens = new ArrayList<>();
        this.userReponsitoryImpl.findAll().forEach(nv -> {
            qlNhanViens.add(FormUtil.convertFromNhanVienToQlNhanVien(nv));
        });
        return qlNhanViens;
    }
    
     public List<QlUser> findAllByMaNv() {
        List<QlUser> qlNhanViens = new ArrayList<>();
        this.userReponsitoryImpl.findAllByNV().forEach(nv -> {
            qlNhanViens.add(FormUtil.convertFromNhanVienToQlNhanVien(nv));
        });
        return qlNhanViens;
    }

    public QlUser findOne(String ma) {
        User nv = this.userReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertFromNhanVienToQlNhanVien(nv);
    }

    public String save(QlUser qlNhanVien) {
        User nhanVien = FormUtil.convertFromQlNhanVienToNhanVien(qlNhanVien);
        return this.userReponsitoryImpl.save(nhanVien) == true ? "Thêm thành công" : "Thêm thât bại";
    }

    public String delete(QlUser qlNhanVien) {
        return this.userReponsitoryImpl.delete(FormUtil.convertFromQlNhanVienToNhanVien(qlNhanVien)) == true
                ? "Xóa nhân viên thành công" : "Xóa nhân viên thất bại";
    }

    public String update(QlUser qlNhanVien) {
        User nhanVien = FormUtil.convertFromQlNhanVienToNhanVien(qlNhanVien);
        return this.userReponsitoryImpl.update(nhanVien) == true ? "Sửa thành công"
                : "Sửa thất bại";

    }

    public String validate(String ma, String hoVaTen, String ngaySinh, String diaChi, String sdt, String matKhau) {
        if (ma.isBlank() || hoVaTen.isBlank() || ngaySinh.isBlank() || diaChi.isBlank()) {
            return "Bạn phải nhập đủ các trường dữ liệu";
        }
        if (!matKhau.matches("\\w{8}")) {
            return "mật khẩu phải đủ 8 ký tự";
        }
        if (!sdt.matches("0+\\d{9}")) {
            return " Số điện thoại phải đủ 10 số và số 0 phải ở đầu";
        }

        String ngaySinhs[] = ngaySinh.split("-");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/");
        Date date = new Date();
        String day = simpleDateFormat.format(date);
        String days[] = ngaySinh.split("/");
        if ((Integer.parseInt(days[2]) - Integer.parseInt(ngaySinhs[2])) < 18) {
            return "Tuổi phải lớn hơn 18";
        }
        return "";
    }

    public QlUser checkSdtAndPass(String sdt, String pass) {
        User user = this.userReponsitoryImpl.checkSoDienthoaiAnhPass(sdt, pass);
        if (user == null) {
            return null;
        } else {
            QlUser qlUser = FormUtil.convertFromNhanVienToQlNhanVien(user);
            return qlUser;
        }
    }

}
