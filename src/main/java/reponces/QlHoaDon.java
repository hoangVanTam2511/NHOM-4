/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.HinhThucThanhToan;
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
@AllArgsConstructor
@NoArgsConstructor
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

    private double tongTien;
    
    private double thanhToan;
    
    private double tienThua;
    
    private HinhThucThanhToan idHinhThucThanhToan;

    public QlHoaDon(String ma, Date date, int i, User user, KhachHang khachHang) {
     this.ma = ma;
     this.created = date;
     this.delected  =i;
     this.idNhanVien = user;
     this.idKhachHang = khachHang;
    }
    
    public Object getData1(){
        return new Object[]{
          this.ma,this.tongTien,this.thanhToan,this.tienThua,this.idHinhThucThanhToan,this.created,this.tinhTrang,this.idNhanVien.getMa(),this.idNhanVien.getTen(),this.idKhachHang.getMa(),this.idKhachHang.getTen()
        };
    }
    
    public Object getData(int stt){
        return new Object[]{
          stt,this.ma,this.created,this.idNhanVien.getMa(),this.idKhachHang.getMa()
        };
    }
}
