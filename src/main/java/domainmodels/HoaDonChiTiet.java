/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.PrimaryEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet extends PrimaryEntity  implements Serializable  {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hoa_don", nullable = false)
    private HoaDon idHoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chi_tietsp", nullable = false)
    private ChiTietSanPham idChiTietSanPham;

    @Column(name = "don_gia")
    private double donGia;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "tong_tien")
    private double tongTien;

}
