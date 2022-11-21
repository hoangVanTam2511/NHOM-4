/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.PrimaryEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "KhuyenMai")
public class KhuyenMai extends PrimaryEntity{
    
    @Column(name = "ten")
    private String ten;
    
    @Column(name = "mucKhuyenMai")
    private int mucKhuyenMai;
    
    @Column(name = "ngayBatDau")
    private Date ngayBatDau;
    
    @Column(name = "ngayKetThuc")
    private Date ngayKetThuc;
    
}
