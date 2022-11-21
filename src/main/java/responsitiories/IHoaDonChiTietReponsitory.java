/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import reponces.QlHoaDonChiTiet;
import infrastructure.responce.QlHoaDonChiTietReponce;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IHoaDonChiTietReponsitory {

    List<HoaDonChiTiet> findAll();

    HoaDonChiTiet findOneByMaSanPhamAndMaHoaDon(String ma,String maSanPham);

    HoaDonChiTiet findOneByID(UUID id);

    boolean save(HoaDonChiTiet t);

    boolean update(HoaDonChiTiet t);

    boolean delete(HoaDonChiTiet t);
    
    double tinhTongTien(String ma);
    
    List<HoaDonChiTiet> getAllByMaHoaDon(String ma);
}
