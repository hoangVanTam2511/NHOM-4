/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import reponces.QlHoaDonChiTiet;
import infrastructure.responce.QlHoaDonChiTietReponce;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonChiTietService {
    
    QlHoaDonChiTiet findOne(String maHoaDon,String maSanPham);
    
    String update(QlHoaDonChiTiet qlHoaDonChiTiet);
    
    String delete(QlHoaDonChiTiet qlHoaDonChiTiet);
    
    String save(QlHoaDonChiTiet qlHoaDonChiTiet);
    
    List<QlHoaDonChiTiet> getAllByMaHoaDon(String ma);
    
    List<QlHoaDonChiTiet> findAll();
    
    double tinhTongTien(String ma);
    
    List<QlHoaDonChiTietReponce> findGioHangByMaHoaDon(String ma);
}
