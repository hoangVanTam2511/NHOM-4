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

public class PhieuBaoHanh extends PrimaryEntity {

    @Column(name = "ma")
    private String maPhieuBaoHanh;
    
    @Column(name = "ten")
    private String tenPhieuBaoHanh;
    
    @Column(name = "thoiGianBaoHanh")
    private int thoiGianBaoHanh;
    
    @Column(name = "mo_ta",nullable = true)
    private String moTa;
    
}
