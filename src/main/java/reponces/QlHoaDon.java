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
public class QlHoaDon {
    
    private UUID id;
    private String ma;
    private String tenNguoiNhan;
    private String sdt;
    private String diaChi;
    private Date ngayNhan;
    private Date ngayTao;
    private Date ngayThanhToan;
    private int tinhTrang;
    private User idNhanVien;
    private KhachHang idKhachHang;

    public QlHoaDon() {
    }

    public QlHoaDon(UUID id, String ma, String tenNguoiNhan, String sdt, String diaChi, Date ngayNhan, Date ngayTao, Date ngayThanhToan, int tinhTrang, User idNhanVien, KhachHang idKhachHang) {
        this.id = id;
        this.ma = ma;
        this.tenNguoiNhan = tenNguoiNhan;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayNhan = ngayNhan;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
    }
    
     public QlHoaDon(UUID id, String ma,Date ngayTao, int tinhTrang, User idNhanVien, KhachHang idKhachHang) {
        this.id = id;
        this.ma = ma;
        this.tenNguoiNhan = tenNguoiNhan;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayNhan = ngayNhan;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
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

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public User getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(User idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public KhachHang getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(KhachHang idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    public String covertTinhTrang(){
        // 0: Hủy đơn hàng
        // 1: Chờ thanh toán
        // 2: Đã thanh toán
        if(this.tinhTrang == 0){
            return "Hủy";
        }else if(this.tinhTrang == 1){
            return "Chờ thanh toán";
        }else if(this.tinhTrang == 2){
            return "Đã thanh toán";
        }else{
            return "";
        }
    }

    @Override
    public String toString() {
        return "QlHoaDon{" + "id=" + id + ", ma=" + ma + ", tenNguoiNhan=" + tenNguoiNhan + ", sdt=" + sdt + ", diaChi=" + diaChi + ", ngayNhan=" + ngayNhan + ", ngayTao=" + ngayTao + ", ngayThanhToan=" + ngayThanhToan + ", tinhTrang=" + tinhTrang + ", idNhanVien=" + idNhanVien + ", idKhachHang=" + idKhachHang + '}';
    }
    
    
    public Object getData(int stt){
        return new Object[]{
            stt,this.ma,this.ngayTao,this.idNhanVien.getTen(),covertTinhTrang()
        };
    }
    
}
