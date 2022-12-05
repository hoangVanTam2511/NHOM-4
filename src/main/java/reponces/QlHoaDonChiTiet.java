/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.ChiTietSanPham;
import domainmodels.HoaDon;
import infrastructure.convert.FormUtil;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QlHoaDonChiTiet {

    private HoaDon idHoaDon;
    private ChiTietSanPham idChiTietSanPham;
    private double donGia;
    private int soLuong;
    private double tongTien;

    public QlHoaDonChiTiet(HoaDon idHoaDon, ChiTietSanPham idChiTietSanPham,double donGia,int soLuong) {
        this.idHoaDon = idHoaDon;
        this.idChiTietSanPham = idChiTietSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = this.soLuong * this.donGia;
    }
    

    public Object getData1() {
        return new Object[]{
          this.idHoaDon.getMa(),this.idChiTietSanPham.getMa(),this.idChiTietSanPham.getTen(),this.soLuong,this.donGia,this.tongTien
        };
    }
     public Object getData(int stt) {
        return new Object[]{
          stt,this.idHoaDon.getMa(),this.idChiTietSanPham.getMa(),this.idChiTietSanPham.getTen(),this.soLuong,FormUtil.convertNumber(donGia),FormUtil.convertNumber(tongTien)
        };
    }

     
    @Override
    public String toString() {
        return "QlHoaDonChiTiet{" + "idHoaDon=" + idHoaDon + ", idChiTietSanPham=" + idChiTietSanPham + ", donGia=" + donGia + ", soLuong=" + soLuong + '}';
    }

}
