/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.IsIdentified;
import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imei_da_ban")
public class ImeiDaBan extends PrimaryEntity implements IsIdentified, Serializable {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hoa_don_chi_tiet", nullable = false)
    private ChiTietSanPham idChiTietSanPham;

    @Column(name = "so_imei", length = EntityProperties.LENGTH_MA)
    private String soImei;

    @Column(name = "trang_thai", length = EntityProperties.LENGT_SHORT_NAME)
    private boolean trangThai;
}
