/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.HoaDon;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FPT Shop
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QlPhieuBaoHanh {

    private UUID id;
    private int delected;
    private HoaDon idHoaDon;
    private String moTa;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int trangThai;
    private int soImei;

}
