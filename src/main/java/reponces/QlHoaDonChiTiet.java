/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.ChiTietSanPham;
import domainmodels.HoaDon;
import infrastructure.convert.FormUtil;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import responsitiories.impl.ImeiDaBanReponsitoryImpl;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QlHoaDonChiTiet {

    private UUID id;
    private int delected;
    private HoaDon idHoaDon;
    private ChiTietSanPham idChiTietSanPham;
    private double donGia;
    private int soLuong;
    private double tongTien;

    public QlHoaDonChiTiet(HoaDon idHoaDon, ChiTietSanPham idChiTietSanPham,double donGia,int soLuong) {
        this.delected =1;
        this.idHoaDon = idHoaDon;
        this.idChiTietSanPham = idChiTietSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = this.soLuong * this.donGia;
    }
    

    public Object getData1() {
        return new Object[]{
          this.idHoaDon.getMa(),this.idChiTietSanPham.getMa(),this.idChiTietSanPham.getTen(),new ImeiDaBanReponsitoryImpl().getSoLuongDaBan(id),this.donGia,this.tongTien
        };
    }
     public Object getData() {
        return new Object[]{
          this.idHoaDon.getMa(),this.idChiTietSanPham.getMa(),this.idChiTietSanPham.getTen(),new ImeiDaBanReponsitoryImpl().getSoLuongDaBan(id),FormUtil.convertNumber(donGia),FormUtil.convertNumber(tongTien)
        };
    }

     
    @Override
    public String toString() {
        return "QlHoaDonChiTiet{" + "idHoaDon=" + idHoaDon + ", idChiTietSanPham=" + idChiTietSanPham + ", donGia=" + donGia + ", soLuong=" + soLuong + '}';
    }

}
