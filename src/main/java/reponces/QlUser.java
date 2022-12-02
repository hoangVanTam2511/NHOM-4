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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QlUser {

    private String ma;
    
    private String ten;
    
    private String diaChi;
    
    private String sdt;
    
    private String cccd;
    
    private boolean gioiTinh;
    
    private int trangThai;
    
    private String anh;
    
    private String matKhau;

    private ChucVu idChucVu;

    public QlUser() {
    }

    public QlUser(String ma, String ten, String diaChi, String sdt, String cccd, boolean gioiTinh, int trangThai, String anh, String matKhau, ChucVu idChucVu) {
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.cccd = cccd;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.anh = anh;
        this.matKhau = matKhau;
        this.idChucVu = idChucVu;
    }
    
    

    public Object getData(int stt) {
        return new Object[]{
           stt,this.ma,this.ten ,this.diaChi,this.sdt,this.hienthigt(),this.cccd,this.hienthitrangthai(),this.matKhau,this.idChucVu.getTen()
        };
    }
    public String hienthigt(){
        if(gioiTinh==false){
            return "Nam";
        }else{
            return "Nữ";
        }
    }
    public String hienthitrangthai(){
        if(trangThai==0){
            return "Hoạt động";
        }else{
            return "Không hoạt động";
        }
    }
//    

   
   
}
