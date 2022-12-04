/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infrastructure.responce;

import infrastructure.convert.FormUtil;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@ToString
public class QlHoaDonChiTietReponce {
    
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public QlHoaDonChiTietReponce(String maSanPham, String tenSanPham, int soLuong, double donGia, double thanhTien) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public QlHoaDonChiTietReponce() {
        
    }
    public Object getData(int stt){
        return new Object[]{
          stt,this.maSanPham,this.tenSanPham,this.soLuong,FormUtil.convertNumber(donGia),FormUtil.convertNumber(thanhTien)
        };
    }
    
}
