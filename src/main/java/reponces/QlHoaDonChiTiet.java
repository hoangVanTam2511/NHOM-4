/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.ChiTietSanPham;
import domainmodels.HoaDon;

/**
 *
 * @author Admin
 */
public class QlHoaDonChiTiet {

    private HoaDon idHoaDon;
    private ChiTietSanPham idChiTietSanPham;
    private double donGia;
    private int soLuong;

    public QlHoaDonChiTiet() {
    }

    public QlHoaDonChiTiet(HoaDon idHoaDon, ChiTietSanPham idChiTietSanPham, double donGia, int soLuong) {
        this.idHoaDon = idHoaDon;
        this.idChiTietSanPham = idChiTietSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public HoaDon getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(HoaDon idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public ChiTietSanPham getIdChiTietSanPham() {
        return idChiTietSanPham;
    }

    public void setIdChiTietSanPham(ChiTietSanPham idChiTietSanPham) {
        this.idChiTietSanPham = idChiTietSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Object getData(int stt) {
        double thanhTien = this.soLuong * this.donGia;
        return new Object[]{
            stt, this.idChiTietSanPham.getIdSanPham().getMa(), this.idChiTietSanPham.getIdSanPham().getTen(), this.soLuong, this.donGia, thanhTien
        };
    }

    @Override
    public String toString() {
        return "QlHoaDonChiTiet{" + "idHoaDon=" + idHoaDon + ", idChiTietSanPham=" + idChiTietSanPham + ", donGia=" + donGia + ", soLuong=" + soLuong + '}';
    }

}
