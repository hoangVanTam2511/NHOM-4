/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.HoaDon;
import domainmodels.KhachHang;
import domainmodels.User;
import responsitiories.impl.HoaDonReponsitoryImpl;
import responsitiories.impl.UserReponsitoryImpl;
import services.IHoaDonService;
import services.IService;
import reponces.QlHoaDon;
import reponces.QlUser;
import infrastructure.convert.FormUtil;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import reponces.QlKhachHang;
import responsitiories.impl.KhachHangRepositoryImpl;

/**
 *
 * @author Admin
 */
public class HoaDonServiceImpl implements IHoaDonService {

    private HoaDonReponsitoryImpl hoaDonReponsitory;
    private List<QlHoaDon> listQlHoaDon;

    public HoaDonServiceImpl() {
        this.hoaDonReponsitory = new HoaDonReponsitoryImpl();
        listQlHoaDon = new ArrayList<>();
    }

    @Override
    public List<QlHoaDon> findAll() {
        listQlHoaDon.clear();
        this.hoaDonReponsitory.findAll().forEach(hoaDon -> {
            listQlHoaDon.add(FormUtil.convertFromHoaDonToQlHoaDon(hoaDon));
        });
        return listQlHoaDon;
    }

    @Override
    public List<QlHoaDon> findAll(int status) {
        listQlHoaDon.clear();
        this.hoaDonReponsitory.findAll(status).forEach(hoaDon -> {
            listQlHoaDon.add(FormUtil.convertFromHoaDonToQlHoaDon(hoaDon));
        });
        return listQlHoaDon;
    }

    @Override
    public QlHoaDon findOne(String ma) {
        HoaDon hoaDon = this.hoaDonReponsitory.findOneByMa(ma);
        return FormUtil.convertFromHoaDonToQlHoaDon(hoaDon);
    }

    @Override
    public String save(QlHoaDon t) {
        HoaDon hoaDon = FormUtil.convertFromQlHoaDonToHoaDon(t);
        return this.hoaDonReponsitory.save(hoaDon) == true ? "Tạo hóa đơn thành công" : "Tạo hóa đơn thất bại";
    }

    @Override
    public String delete(QlHoaDon t) {
        HoaDon hoaDon = FormUtil.convertFromQlHoaDonToHoaDon(t);
        return this.hoaDonReponsitory.delete(hoaDon) == true ? "Xóa thành công" : "Xóa thất bại";
    }

    @Override
    public String update(QlHoaDon t) {
        HoaDon hoaDon = FormUtil.convertFromQlHoaDonToHoaDon(t);
        return this.hoaDonReponsitory.update(hoaDon) == true ? "Thanh toán thành công" : "Thanh toán thất bại";
    }

//    public QlHoaDon createHoaDon(){
//        Date today = new Date();
//        User nv = new UserReponsitoryImpl().findOneByMa("NV02");
//        KhachHang kh = new KhachHangRepositoryImpl().findOneByMa("KH01");
//        String ma = genMaTuDong(); 
//        return new QlHoaDon(null, ma, today, 1, nv, kh);
//    }
//    
    public String genMaTuDong() {
        listQlHoaDon = findAll();
        if (listQlHoaDon == null) {
            return "HD00";
        }
        String maHoaDonHienTai = listQlHoaDon.get(listQlHoaDon.size() - 1).getMa();
        int index = Integer.parseInt(maHoaDonHienTai.substring(2));
        index++;
        String ma = "HD";
        if (index > 1 && index < 10) {
            ma += "0" + index;
        } else {
            ma += index;
        }
        return ma;
    }

    public QlHoaDon createHoaDon(QlUser qlUser, QlKhachHang qlKhachHang) {
        String ma = genMaTuDong();
        Date date = new Date();
        User user = FormUtil.convertFromQlNhanVienToNhanVien(qlUser);
        KhachHang khachHang = FormUtil.convertFromQlKhachHangToKhachHang(qlKhachHang);
        return new QlHoaDon(ma, date, 1, user, khachHang);
    }

    public static void main(String[] args) {
    }

}