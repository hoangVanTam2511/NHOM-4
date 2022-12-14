
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.IsIdentified;
import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "nhan_vien")
public class User extends PrimaryEntity implements IsIdentified, Serializable {

    @Column(name = "ma", length = EntityProperties.LENGTH_MA)
    private String ma;

    @Column(name = "ten", length = EntityProperties.LENGT_FULL_NAME)
    private String ten;

    @Column(name = "dia_chi", length = EntityProperties.LENGT_ADDRESS)
    private String diaChi;

    @Column(name = "sdt", length = EntityProperties.LENGT_PHONE)
    private String sdt;

    @Column(name = "cccd", length = EntityProperties.LENGT_PHONE)
    private String cccd;
    
    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @Column(name = "trang_thai")
    private int trangThai;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "img")
    private String anh;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_chuc_vu", nullable = false)
    private ChucVu idChucVu;

    @Override
    public String toString() {
        return "NhanVien{" + "ma=" + ma + ", ten=" + ten + "id gui bc" + '}';
    }

}
