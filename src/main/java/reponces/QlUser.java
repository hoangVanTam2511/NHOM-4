/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.ChucVu;
import domainmodels.User;
import infrastructure.convert.FormUtil;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class QlUser {

    private UUID id;
    private String ma;
    private String ho;
    private String ten;
    private String tenDem;
    private Date ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String sdt;
    private String matKhau;
    private int trangThai;
    private ChucVu idChucVu;

    public QlUser(UUID id, String ma, String ho, String ten, String tenDem, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String matKhau, int trangThai, ChucVu idChucVu, User idGuiBc) {
        this.id = id;
        this.ma = ma;
        this.ho = ho;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.idChucVu = idChucVu;
    }

    public QlUser(UUID id, String ma, String fullName, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String matKhau, int trangThai, ChucVu idChucVu, User idGuiBc) {
        String title[] = fullName.split(" ");
        this.id = id;
        this.ma = ma;
        this.ho =title[0];
        this.ten = title[title.length-1];
        String tenDem ="";
        for(int i = 1; i < title.length-1;i++){
            tenDem += title[i];
        }
        this.tenDem = tenDem.trim();
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.idChucVu = idChucVu;
    }

    public QlUser() {
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

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public Date getNgaySinh() {
        return this.ngaySinh;
    }
    public String getNgaySinhString(){
        return FormUtil.convertDateToString(this.ngaySinh);
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public ChucVu getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(ChucVu idChucVu) {
        this.idChucVu = idChucVu;
    }

    public String getFullName() {
        return this.ho + " " + this.tenDem + " " + this.ten;
    }

    public String getTrangThaiNhanVien() {
        String trangThai = "Đang làm";
        if (this.trangThai == 1) {
            trangThai = "Đã về hưu";
        }
        return trangThai;
    }

    public Object getData() {
        String fullName = getFullName();
        String trangThai = getTrangThaiNhanVien();
        return new Object[]{
            this.ma, fullName, this.gioiTinh,getNgaySinhString(), this.sdt,this.diaChi
        };
    }
    
    
    @Override
    public String toString() {
        return "QlNhanVien{" + "id=" + id + ", ma=" + ma + ", ho=" + ho + ", ten=" + ten + ", tenDem=" + tenDem + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", sdt=" + sdt + ", matKhau=" + matKhau + ", trangThai=" + trangThai  + ", idChucVu=" + idChucVu  + '}';
    }

}
