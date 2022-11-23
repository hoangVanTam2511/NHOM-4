/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.HoaDon;
import java.util.Date;

/**
 *
 * @author FPT Shop
 */
public class QlPhieuBaoHanh {
     private HoaDon idHoaDon;
     private String moTa;
     private Date ngayBatDau;
     private Date ngayKetThuc;
     private int trangThai;
     private int soImei;

    public QlPhieuBaoHanh() {
    }

    public QlPhieuBaoHanh(HoaDon idHoaDon, String moTa, Date ngayBatDau, Date ngayKetThuc, int trangThai, int soImei) {
        this.idHoaDon = idHoaDon;
        this.moTa = moTa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.soImei = soImei;
    }

    public HoaDon getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(HoaDon idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoImei() {
        return soImei;
    }

    public void setSoImei(int soImei) {
        this.soImei = soImei;
    }
     
}
