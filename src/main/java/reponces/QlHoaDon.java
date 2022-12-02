/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.KhachHang;
import domainmodels.User;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@ToString
public class QlHoaDon {
    
    private UUID id;
    
    private int delected;
    
    private String ma;
    
    private String sdt;
    
    private String diaChi;
    
    private Date created;
    
    private Date updated;
    
    private Date ngayThanhToan;
    
    private int tinhTrang;
    
    private User idNhanVien;
    
    private KhachHang idKhachHang;

    public QlHoaDon() {
    }
    
    public QlHoaDon(String ma, Date created, int tinhTrang, User idNhanVien, KhachHang idKhachHang) {
        this.ma = ma;
        this.created = created;
        this.tinhTrang = tinhTrang;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
    }
    
    public Object getData(int stt){
        return new Object[]{
            stt,this.ma,this.created,this.idNhanVien.getMa(),this.idKhachHang.getMa()
        };
    }
    
}
