/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.CauHinh;
import domainmodels.DongSp;
import domainmodels.MauSac;
import domainmodels.NSX;
import domainmodels.SanPham;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class QlChiTietSanPham {

    private UUID id;

    private double giaBan;
    
    private double giaNhap;

    private String moTa;

    private int namBh;
    
    private int soLuongTon;
    
    private DongSp idDongSp;
    
    private MauSac idMauSac;
    
    private CauHinh idCauHinh;
    
    private Anh idAnh;
    
    private NSX idNsx;
    
    private SanPham idSanPham;

    public QlChiTietSanPham(UUID id, double giaBan, double giaNhap, String moTa, int namBh, int soLuongTon, DongSp idDongSp, MauSac idMauSac, NSX idNsx, SanPham idSanPham) {
        this.id = id;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.moTa = moTa;
        this.namBh = namBh;
        this.soLuongTon = soLuongTon;
        this.idDongSp = idDongSp;
        this.idMauSac = idMauSac;
        this.idNsx = idNsx;
        this.idSanPham = idSanPham;
    }

    public QlChiTietSanPham() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getNamBh() {
        return namBh;
    }

    public void setNamBh(int namBh) {
        this.namBh = namBh;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public DongSp getIdDongSp() {
        return idDongSp;
    }

    public void setIdDongSp(DongSp idDongSp) {
        this.idDongSp = idDongSp;
    }

    public MauSac getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(MauSac idMauSac) {
        this.idMauSac = idMauSac;
    }

    public NSX getIdNsx() {
        return idNsx;
    }

    public void setIdNsx(NSX idNsx) {
        this.idNsx = idNsx;
    }

    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Object getData(int stt){
        return new Object[]{
         stt,this.idSanPham.getSoImei(),this.idSanPham.getTen(),this.namBh,this.moTa,this.soLuongTon,this.giaNhap,this.giaBan
        };
    }
   
}
