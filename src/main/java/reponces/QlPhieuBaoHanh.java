/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.HoaDon;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FPT Shop
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QlPhieuBaoHanh {

    private UUID id;
    
    private int delected;
    
    private int thoiGianBaoHanh;
    
    private String maPhieuBaoHanh;
    
    private String tenPhieuBaoHanh;
    
    private String moTa;

    public QlPhieuBaoHanh(int delected, int thoiGianBaoHanh, String maPhieuBaoHanh, String tenPhieuBaoHanh, String moTa) {
        this.delected = delected;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.maPhieuBaoHanh = maPhieuBaoHanh;
        this.tenPhieuBaoHanh = tenPhieuBaoHanh;
        this.moTa = moTa;
        // tạo phiếu bảo hành tương ứng 
    }
    
    public Object getData(){
        return new Object[]{
          this.maPhieuBaoHanh,this.tenPhieuBaoHanh,this.thoiGianBaoHanh,this.moTa  
        };
    }

}
