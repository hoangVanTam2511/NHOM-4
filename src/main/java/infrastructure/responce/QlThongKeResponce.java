/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infrastructure.responce;

import infrastructure.convert.FormUtil;
import javax.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QlThongKeResponce {
    
    @Column(name = "ten")
    private String ten;
    
    @Column(name = "so_luong_theo_thang")
    private Long soLuongTheoThang;
    
    @Column(name = "don_gia")
    private double donGia;
    
    @Column(name = "tong_tien_theo_thang")
    private double tongTienTheoThang;
    
    public Object getData(int stt){
        return new Object[]{
            stt,this.ten,this.soLuongTheoThang,FormUtil.convertNumber(this.donGia),FormUtil.convertNumber(this.tongTienTheoThang)
        };
    }
    
    
}
