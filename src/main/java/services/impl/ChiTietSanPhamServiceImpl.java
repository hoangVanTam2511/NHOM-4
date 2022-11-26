/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.ChiTietSanPham;
import responsitiories.impl.ChiTietSanPhamReponsitoryImpl;
import services.IService;
import reponces.QlChiTietSanPham;
import reponces.QlHoaDonChiTiet;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamServiceImpl implements IService<QlChiTietSanPham> {

    private ChiTietSanPhamReponsitoryImpl chiTietSanPhamReponsitoryImpl;

    public ChiTietSanPhamServiceImpl() {
        this.chiTietSanPhamReponsitoryImpl = new ChiTietSanPhamReponsitoryImpl();
    }

    @Override
    public List<QlChiTietSanPham> findAll() {
        List<QlChiTietSanPham> listChiTietSanPham = new ArrayList<>();
        this.chiTietSanPhamReponsitoryImpl.findAll().forEach(chiTietSanPham -> {
            listChiTietSanPham.add(FormUtil.convertFromChiTietSanPhamToQlChiTietSanPham(chiTietSanPham));
        });
        return listChiTietSanPham;
    }

    public List<QlChiTietSanPham> findAllByName(String name) {
        List<QlChiTietSanPham> listChiTietSanPham = new ArrayList<>();
        this.chiTietSanPhamReponsitoryImpl.findAllByName(name).forEach(chiTietSanPham -> {
            listChiTietSanPham.add(FormUtil.convertFromChiTietSanPhamToQlChiTietSanPham(chiTietSanPham));
        });
        return listChiTietSanPham;
    }

    @Override
    public QlChiTietSanPham findOne(String ma) {
        ChiTietSanPham chiTietSanPham = this.chiTietSanPhamReponsitoryImpl.findOneByImei(ma);
        return FormUtil.convertFromChiTietSanPhamToQlChiTietSanPham(chiTietSanPham);
    }
    
     public QlChiTietSanPham findOneByImei(String imei) {
        ChiTietSanPham chiTietSanPham = this.chiTietSanPhamReponsitoryImpl.findOneByImei(imei);
        return FormUtil.convertFromChiTietSanPhamToQlChiTietSanPham(chiTietSanPham);
    }

    @Override
    public String save(QlChiTietSanPham t) {
        ChiTietSanPham chiTietSanPham = FormUtil.convertFromQlChiTietSanPhamToChiTietSanPham(t);
        return this.chiTietSanPhamReponsitoryImpl.save(chiTietSanPham) == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String delete(QlChiTietSanPham t) {
        ChiTietSanPham chiTietSanPham = FormUtil.convertFromQlChiTietSanPhamToChiTietSanPham(t);
        return this.chiTietSanPhamReponsitoryImpl.delete(chiTietSanPham) == true ? "Xóa thành công" : "Xóa thất bại";
    }

    @Override
    public String update(QlChiTietSanPham t) {
        ChiTietSanPham chiTietSanPham = FormUtil.convertFromQlChiTietSanPhamToChiTietSanPham(t);
        return this.chiTietSanPhamReponsitoryImpl.update(chiTietSanPham) == true ? "Sửa thành công" : "Sửa thất bại";
    }

    public boolean kiemTraSoLuongSanPhamTonKho(int soLuong, String maSanPham) {
        ChiTietSanPham chiTietSanPham = this.chiTietSanPhamReponsitoryImpl.findOneByMa(maSanPham);
        int soLuongTon = chiTietSanPham.getSoLuongTon();
        if (soLuong > soLuongTon) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean kiemTraSoLuongSoLuongTrenHoaDon(int soLuong, QlHoaDonChiTiet qlChiTietSanPham) {
        int soLuongDangChon = qlChiTietSanPham.getSoLuong();
        if (soLuong > soLuongDangChon) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ChiTietSanPhamServiceImpl().findAll());
    }
}
