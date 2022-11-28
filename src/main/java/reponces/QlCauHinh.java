/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.Chip;
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
    private int delected;
    private ManHinh id_man_hinh;
    private Ram id_ram;
    private Rom id_rom;
    private Chip id_chip;

    public QlCauHinh(UUID id, ManHinh id_man_hinh, Ram id_ram, Rom id_rom, Chip id_chip) {
        this.id = id;
        this.id_man_hinh = id_man_hinh;
        this.id_ram = id_ram;
        this.id_rom = id_rom;
        this.id_chip = id_chip;
    }

    public QlCauHinh() {
    }

}
