/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.ChiTietSanPham;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import infrastructure.constant.EntityProperties;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QlImeiDaBan {

    private UUID id;

    private int delected;
    
    private HoaDonChiTiet idHoaDonChiTiet;

    private String soImei;

    private boolean trangThai;

    public QlImeiDaBan(HoaDonChiTiet idHoaDonChiTiet, String soImei) {
        this.idHoaDonChiTiet = idHoaDonChiTiet;
        this.soImei = soImei;
        this.trangThai = false;
    }
    
    
}
