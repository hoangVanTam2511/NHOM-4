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

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PhieuBaoHanh")
public class PhieuBaoHanh extends PrimaryEntity{
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHoaDon")
    private HoaDon idHoaDon;
    
    @Column(name = "moTa")
    private String moTa;
    
    @Column(name = "ngayBatDau")
    private Date ngayBatDau;
    
    @Column(name = "ngayKetThuc")
    private Date ngayKetThuc;
    
    @Column(name = "trangThai")
    private int trangThai;
    
    @Column(name = "soImei")
    private int soImei;
}
