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
public class QlAnh {
    
    private UUID id;
    
    private String ten;
    
    private String duongDan;
    
    private int trangThai;
    
    private int delected;
    
}
