/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author FPT Shop
 */
@Getter
@Setter
@ToString
public class QlKhuyenMai {
    private String ten;
    private int mucKhuyenMai;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public QlKhuyenMai() {
    }

    public QlKhuyenMai(String ten, int mucKhuyenMai, Date ngayBatDau, Date ngayKetThuc) {
        this.ten = ten;
        this.mucKhuyenMai = mucKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getMucKhuyenMai() {
        return mucKhuyenMai;
    }

    public void setMucKhuyenMai(int mucKhuyenMai) {
        this.mucKhuyenMai = mucKhuyenMai;
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
    
}
