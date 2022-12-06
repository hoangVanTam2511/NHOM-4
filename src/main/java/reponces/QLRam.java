/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import java.util.UUID;
import java.util.Vector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Home
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLRam {

    private UUID id;
    private int kichThuoc;
    private int delected;
    private String tenRam;

    public QLRam(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public Object getData() {
      return new Object[]{
        this.id,this.kichThuoc  
      };
    }  
}
