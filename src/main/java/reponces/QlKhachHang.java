/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

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
public class QlKhachHang {

    private UUID id;

    private int delected;

    private String ma;

    private String ten;

    private String diaChi;

    private String sdt;

    private String matKhau;

     public Object getData(int stt) {
        return new Object[]{
           stt,this.ma,this.ten ,this.diaChi
        };
    }
;
}
