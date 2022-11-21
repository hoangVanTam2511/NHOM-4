/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import java.util.UUID;

/**
 *
 * @author Admin
 */
public class QlCuaHang {
    
    private UUID id;
    
    private String ma;
    
    private String quocGia;
    
    private String ten;
    
    private String diaChi;
    
    private String thanhPho;

    public QlCuaHang(UUID id, String ma, String quocGia, String ten, String diaChi, String thanhPho) {
        this.id = id;
        this.ma = ma;
        this.quocGia = quocGia;
        this.ten = ten;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
    }
    
    public QlCuaHang() {
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

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String Ten) {
        this.ten = Ten;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public Object getData(){
        return new Object[]{
          this.ma,this.ten,this.diaChi,this.thanhPho,this.quocGia
        };
    }

    @Override
    public String toString() {
        return "QlCuaHang{" + "id=" + id + ", ma=" + ma + ", quocGia=" + quocGia + ", ten=" + ten + ", diaChi=" + diaChi + ", thanhPho=" + thanhPho + '}';
    }
    
    
    
}
