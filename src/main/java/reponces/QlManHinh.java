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

public class QlManHinh {
    private UUID id;
    private String doPhanGiai;
    private int kichThuoc;

    public QlManHinh() {
    }

    public QlManHinh(UUID id, String doPhanGiai, int kichThuoc) {
        this.id = id;
        this.doPhanGiai = doPhanGiai;
        this.kichThuoc = kichThuoc;
    }
    
    public Object getData(){
        return new Object[]{
            this.doPhanGiai, this.kichThuoc
        };
    }
}
