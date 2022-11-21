/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.IsIdentified;
import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NhanVien")
public class NhanVien extends PrimaryEntity implements  IsIdentified,Serializable{
    
    @Column(name = "ma",length = EntityProperties.LENGTH_MA)
    private String ma;
    
    @Column(name = "ten",length = EntityProperties.LENGT_FULL_NAME)
    private String ten;
    
    @Column(name = "diaChi",length = EntityProperties.LENGT_ADDRESS)
    private String diaChi;
    
    @Column(name = "sdt",length = EntityProperties.LENGT_PHONE)
    private String sdt;
    
    @Column(name = "trangThai")
    private int trangThai;
    
    @Column(name = "matKhau")
    private String matKhau;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "idChucVu",nullable = false)
    private ChucVu idChucVu;

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    @Override
    public String toString() {
        return "NhanVien{" + "ma=" + ma + ", ten=" + ten +"id gui bc"+'}';
    }
    
    
}
