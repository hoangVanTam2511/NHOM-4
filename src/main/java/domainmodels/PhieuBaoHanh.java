/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.PrimaryEntity;
import java.util.Date;
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
@Table(name = "phieu_bao_hanh")
@ToString
public class PhieuBaoHanh extends PrimaryEntity{
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hoa_don")
    private HoaDon idHoaDon;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;
    
    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;
    
    @Column(name = "trang_thai")
    private int trangThai;
    
    @Column(name = "so_imei")
    private int soImei;
}
