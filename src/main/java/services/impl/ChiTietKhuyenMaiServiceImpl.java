/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import reponces.QlChiTietKhuyenMai;
import reponces.QlChiTietSanPham;
import reponces.QlKhuyenMai;
import responsitiories.impl.ChiTietKhuyenMaiReponsitoryImpl;
import services.IService;

/**
 *
 * @author Admin
 */
public class ChiTietKhuyenMaiServiceImpl implements IService<QlChiTietKhuyenMai> {

    private ChiTietKhuyenMaiReponsitoryImpl chiTietKhuyenMaiReponsitoryImpl;

    public ChiTietKhuyenMaiServiceImpl() {
        this.chiTietKhuyenMaiReponsitoryImpl = new ChiTietKhuyenMaiReponsitoryImpl();
    }

    @Override
    public List<QlChiTietKhuyenMai> findAll() {
        List<QlChiTietKhuyenMai> listQLChiTietKhuyenMai = new ArrayList<>();
        this.chiTietKhuyenMaiReponsitoryImpl.findAll().forEach(ctkm -> {
            listQLChiTietKhuyenMai.add(FormUtil.convertChiTietKhuyenMaiToQlChiTietKhuyenMai(ctkm));
        });
        return listQLChiTietKhuyenMai;
    }

    @Override
    public QlChiTietKhuyenMai findOne(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String save(QlChiTietKhuyenMai t) {
        return this.chiTietKhuyenMaiReponsitoryImpl.save(FormUtil.convertQlChiTietKhuyenMaiToChiTietKhuyenMai(t)) == true ? "Thành công" : "Thất bại.Vui lòng thử lại";
    }

    @Override
    public String delete(QlChiTietKhuyenMai t) {
        return this.chiTietKhuyenMaiReponsitoryImpl.delete(FormUtil.convertQlChiTietKhuyenMaiToChiTietKhuyenMai(t)) == true ? "Thành công" : "Thất bại.Vui lòng thử lại";
    }

    @Override
    public String update(QlChiTietKhuyenMai t) {
        return this.chiTietKhuyenMaiReponsitoryImpl.update(FormUtil.convertQlChiTietKhuyenMaiToChiTietKhuyenMai(t)) == true ? "Thành công" : "Thất bại.Vui lòng thử lại";
    }

    public double getSoTienSauKhuyenMai(String imei) {
        return this.chiTietKhuyenMaiReponsitoryImpl.getSoTienSauKhiTruKhuyenMai(imei);
    }

    public QlChiTietKhuyenMai createChiTietKhuyenMai(QlKhuyenMai qlKhuyenMai, QlChiTietSanPham qlChiTietSanPham) {
        Double soTienConLai = 0.0;
        if (qlKhuyenMai.getMucGiamGiaPhanTram() == 0) {
            soTienConLai = qlChiTietSanPham.getDonGia() - qlKhuyenMai.getMucGiamGiaTienMat();
        } else {
            soTienConLai = qlChiTietSanPham.getDonGia() - (qlChiTietSanPham.getDonGia() * qlKhuyenMai.getMucGiamGiaPhanTram()) / 100;
        }
        QlChiTietKhuyenMai qlChiTietKhuyenMai = new QlChiTietKhuyenMai(FormUtil.convertFromQlChiTietSanPhamToChiTietSanPham(qlChiTietSanPham), FormUtil.convertKhuyenMaiToQlKhuyenMai(qlKhuyenMai), qlChiTietSanPham.getDonGia(), soTienConLai);
        return qlChiTietKhuyenMai;
    }
    

}
