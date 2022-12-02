/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QlSanPham {

   private UUID id;
   
   private int delected;
   
   private String soImei;
   
   private String ten;

    public QlSanPham(String soImei, String ten) {
        this.soImei = soImei;
        this.ten = ten;
    }
   
    public Object getData() {
      return new Object[]{
        this.id,this.soImei,this.ten  
      };
    } 
}
