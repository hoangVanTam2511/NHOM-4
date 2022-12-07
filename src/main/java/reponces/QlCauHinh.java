/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.Camera;
import domainmodels.Chip;
import domainmodels.HeDieuHanh;
import domainmodels.Pin;
import domainmodels.ManHinh;
import domainmodels.Ram;
import domainmodels.Rom;
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
public class QlCauHinh {

    private UUID id;
    private int delected;
    private ManHinh id_man_hinh;
    private Ram id_ram;
    private Rom id_rom;
    private Chip id_chip;
    private HeDieuHanh idHeDieuHanh;
    private Pin idPin;
    private Camera idCamera;
   
}
