/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.ManHinh;
import domainmodels.Ram;
import domainmodels.Rom;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Home
 */

@Getter
@Setter
public class QlCauHinh {
    private UUID id;
    private ManHinh id_man_hinh;
    private Ram id_ram;
    private Rom id_rom;

    public QlCauHinh(UUID id, ManHinh id_man_hinh, Ram id_ram, Rom id_rom) {
        this.id = id;
        this.id_man_hinh = id_man_hinh;
        this.id_ram = id_ram;
        this.id_rom = id_rom;
    }

    public Object getData(int stt){
        return new Object[]{
            stt, this.id_man_hinh.getDoPhanGiai(), this.id_ram.getKichThuoc(), this.id_rom.getKichThuoc()
        };
    }
    
    
}
