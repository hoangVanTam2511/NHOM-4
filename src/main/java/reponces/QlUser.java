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
    
    private boolean gioiTinh;
    
    private String cccd;
    
    private int trangThai;
    
    private String anh;
    
    private String matKhau;
    
    private ChucVu idChucVu;
    
    public String getStringGioiTinh(){
        return this.gioiTinh == true ?"Nam" :"Nữ";
    }
    
    public String getStringTrangThai(){
        return this.trangThai== 1?"Hoạt động" :"Không hoạt động";
    }

    public Object getData(int stt) {
        return new Object[]{
           stt,this.ma,this.ten ,this.diaChi,this.sdt,getStringGioiTinh(),this.cccd,getStringTrangThai(),this.matKhau,this.idChucVu.getTen()
        };
    }
}
