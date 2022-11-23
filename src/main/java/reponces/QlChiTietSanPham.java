/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.Anh;
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

   private double donGia;

    private String moTa;

    private int namBh;
    
    private int soLuongTon;
    
    private DongSp idDongSp;
    
    private MauSac idMauSac;
    
    private CauHinh idCauHinh;
    
    private Anh idAnh;
    
    private NSX idNsx;
    
    private SanPham idSanPham;

    public QlChiTietSanPham(UUID id, double donGia, String moTa, int namBh, int soLuongTon, DongSp idDongSp, MauSac idMauSac, CauHinh idCauHinh, Anh idAnh, NSX idNsx, SanPham idSanPham) {
        this.id = id;
        this.donGia = donGia;
        this.moTa = moTa;
        this.namBh = namBh;
        this.soLuongTon = soLuongTon;
        this.idDongSp = idDongSp;
        this.idMauSac = idMauSac;
        this.idCauHinh = idCauHinh;
        this.idAnh = idAnh;
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
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

    public CauHinh getIdCauHinh() {
        return idCauHinh;
    }

    public void setIdCauHinh(CauHinh idCauHinh) {
        this.idCauHinh = idCauHinh;
    }

    public Anh getIdAnh() {
        return idAnh;
    }

    public void setIdAnh(Anh idAnh) {
        this.idAnh = idAnh;
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
        stt,this.idSanPham.getSoImei(),this.idSanPham.getTen(),this.idNsx.getTen(),this.idDongSp.getTen(),this.idMauSac.getTen(),this.idCauHinh.getIdManHinh().getDoPhanGiai(),this.idCauHinh.getIdManHinh().getKichThuoc(),this.idCauHinh.getIdRam().getKichThuoc(),this.idCauHinh.getIdRom().getKichThuoc(),this.soLuongTon,this.getDonGia()
        };
    }
   
}
