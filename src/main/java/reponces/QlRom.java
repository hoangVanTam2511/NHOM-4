/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Home
 */
@Getter
@Setter
public class QlRom {
    private UUID id;
    private int kichThuoc;

    public QlRom() {
    }

    public QlRom(UUID id, int kichThuoc) {
        this.id = id;
        this.kichThuoc = kichThuoc;
    }
    
    public Object getData(){
        return new Object[]{
            this.kichThuoc
        };
    }
}
