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
@ToString
@Table(name = "HoaDon")
public class HoaDon extends PrimaryEntity implements IsIdentified,Serializable{
    
    @Column(name = "ma",length = EntityProperties.LENGTH_MA)
    private String ma;
    
    @Column(name = "sdt",length = EntityProperties.LENGT_PHONE)
    private String sdt;
    
    @Column(name = "diaChi",length = EntityProperties.LENGT_ADDRESS)
    private String diaChi;
    
    @Column(name = "created")
    private Date created;
    
    @Column(name = "updated")
    private Date updated;
    
    @Column(name = "ngayThanhToan")
    private Date ngayThanhToan;
    
    @Column(name = "tinhTrang")
    private int tinhTrang;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser",nullable = false)
    private User idNhanVien;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdKH",nullable = false)
    private KhachHang idKhachHang;
}

