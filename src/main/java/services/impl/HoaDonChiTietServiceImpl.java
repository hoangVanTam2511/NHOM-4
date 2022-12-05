/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.HoaDonChiTiet;
import domainmodels.NSX;
import responsitiories.impl.HoaDonChiTietReponsitoryImpl;
import services.IHoaDonChiTietService;
import services.IService;
import reponces.QlHoaDonChiTiet;
import reponces.QlNhaSanXuat;
import infrastructure.convert.FormUtil;
import infrastructure.responce.QlHoaDonChiTietReponce;
import infrastructure.responce.QlThongKeResponce;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietServiceImpl implements IHoaDonChiTietService {

    private HoaDonChiTietReponsitoryImpl hoaDonChiTietReponsitory;

    public HoaDonChiTietServiceImpl() {
        this.hoaDonChiTietReponsitory = new HoaDonChiTietReponsitoryImpl();
    }

    @Override
    public List<QlHoaDonChiTiet> findAll() {
        List<QlHoaDonChiTiet> listHoaDonChiTiets = new ArrayList<>();
        this.hoaDonChiTietReponsitory.findAll().forEach(qlHoaDonChiTiet -> {
            listHoaDonChiTiets.add(FormUtil.convertHoaDonChiTietToQLHoaDonChiTiet(qlHoaDonChiTiet));
        });
        return listHoaDonChiTiets;
    }

    public List<QlHoaDonChiTiet> getAllByMaHoaDon(String ma) {
        List<QlHoaDonChiTiet> listHoaDonChiTiets = new ArrayList<>();
        this.hoaDonChiTietReponsitory.getAllByMaHoaDon(ma).forEach(qlHoaDonChiTiet -> {
            listHoaDonChiTiets.add(FormUtil.convertHoaDonChiTietToQLHoaDonChiTiet(qlHoaDonChiTiet));
        });
        return listHoaDonChiTiets;
    }

    @Override
    public String save(QlHoaDonChiTiet qlHoaDonChiTiet) {
        HoaDonChiTiet hoaDonChiTiet = FormUtil.convertQlHoaDonChiTietToHoaDonChiTiet(qlHoaDonChiTiet);
        return this.hoaDonChiTietReponsitory.save(hoaDonChiTiet) == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String delete(QlHoaDonChiTiet qlHoaDonChiTiet) {
        HoaDonChiTiet hoaDonChiTiet = FormUtil.convertQlHoaDonChiTietToHoaDonChiTiet(qlHoaDonChiTiet);
        return this.hoaDonChiTietReponsitory.delete(hoaDonChiTiet) == true ? "Xóa thành công" : "Xóa thất bại";
    }

    @Override
    public String update(QlHoaDonChiTiet qlHoaDonChiTiet) {
        HoaDonChiTiet hoaDonChiTiet = FormUtil.convertQlHoaDonChiTietToHoaDonChiTiet(qlHoaDonChiTiet);
        return hoaDonChiTietReponsitory.update(hoaDonChiTiet) == true ? "Sửa thành công" : "sửa thất bại";
    }

    @Override
    public QlHoaDonChiTiet findOne(String maHoaDon, String maSanPham) {
        HoaDonChiTiet hoaDonChiTiet = this.hoaDonChiTietReponsitory.findOneByMaSanPhamAndMaHoaDon(maHoaDon, maSanPham);
        if (hoaDonChiTiet == null) {
            return null;
        } else {
            return FormUtil.convertHoaDonChiTietToQLHoaDonChiTiet(hoaDonChiTiet);
        }
    }

    @Override
    public double tinhTongTien(String ma) {
        return this.hoaDonChiTietReponsitory.tinhTongTien(ma);
    }

    @Override
    public List<QlHoaDonChiTietReponce> findGioHangByMaHoaDon(String ma) {
        return this.hoaDonChiTietReponsitory.findGioHang(ma);
    }

    public double tinhTienThua(double tongTien, double tienKhachDua) {
        return tongTien - tienKhachDua;
    }

    public double tinhTongTienBanDau(String maHoaDon) {
        return this.hoaDonChiTietReponsitory.tinhTongTienBanDau(maHoaDon);
    }
    
    public List<QlThongKeResponce> getSanPhamThongKeTheoThang(Date ngayBatDau, Date ngayKetThuc){
       return this.hoaDonChiTietReponsitory.getSanPhamThongKeTheoThang(ngayBatDau, ngayKetThuc);
    }

    public static void main(String[] args) {
        System.out.println(new HoaDonChiTietServiceImpl().findOne("HD00", "SP01"));
    }

}
