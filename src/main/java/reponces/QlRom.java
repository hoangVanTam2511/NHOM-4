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
public class QlRom {
    private UUID id;
    private int kichThuoc;
    private int delected;
    private String tenRom;

}
