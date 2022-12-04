/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */

@Getter
@Setter
@Embeddable
public class ChiTietPhieuBaoHanhId implements Serializable{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chi_tietsp", nullable = false)
    private ChiTietSanPham idChiTietSanPham;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hoa_don", nullable = false)
    private HoaDon idHoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_phieu_bao_hanh", nullable = false)
    private PhieuBaoHanh idPhieuBaoHanh;

}
