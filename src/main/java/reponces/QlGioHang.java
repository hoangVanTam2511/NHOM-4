/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.KhachHang;
import domainmodels.User;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class QlGioHang {
    
    private UUID id;
    private String ma;
    private String tenNguoiNhan;
    private String sdt;
    private String diaChi;
    private Date ngayTao;
    private int tinhTrang;
    private KhachHang idKhachHang;
    private User idNhanVien;

    public QlGioHang() {
    }

    public QlGioHang(UUID id, String ma, String tenNguoiNhan, String sdt, String diaChi, Date ngayTao, int tinhTrang, KhachHang idKhachHang, User idNhanVien) {
        this.id = id;
        this.ma = ma;
        this.tenNguoiNhan = tenNguoiNhan;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.tinhTrang = tinhTrang;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public KhachHang getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(KhachHang idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public User getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(User idNhanVien) {
        this.idNhanVien = idNhanVien;
    }
    
    public Object getData(){
        return new Object[]{
          this.ma,this.tenNguoiNhan,this.sdt,this.diaChi,this.ngayTao,this.tinhTrang
        };
    }
    
}
