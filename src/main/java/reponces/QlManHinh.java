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
 * @author Home
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QlManHinh {
    private UUID id;
    
    private String doPhanGiai;
    
    private int kichThuoc;
    
    private int delected;
    
    public Object getData(){
        return new Object[]{
            this.doPhanGiai, this.kichThuoc
        };
    }
}
