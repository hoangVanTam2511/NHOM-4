/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.PrimaryEntity;
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
import lombok.ToString;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "khuyen_mai")
@ToString
public class KhuyenMai extends PrimaryEntity {

    @Column(name = "ma_khuyen_mai")
    private String maKhuyenMai;
    
    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;
    
    @Column(name = "muc_giam_gia_phan_tram")
    private Double mucGiamGiaPhanTram;
    
    @Column(name = "muc_giam_gia_tien_mat")
    private Double mucGiamGiaTienMat;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_phieu_bao_hanh",referencedColumnName = "id")
    private PhieuBaoHanh idPhieuBaoHanh;    

}
