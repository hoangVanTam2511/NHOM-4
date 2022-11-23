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
@Table(name = "khach_hang")
@ToString
public class KhachHang extends PrimaryEntity implements IsIdentified,Serializable{
    
    @Column(name = "ma",length = EntityProperties.LENGTH_MA)
    private String ma;
    
    @Column(name = "ten",length = EntityProperties.LENGT_SHORT_NAME)
    private String ten;
    
    @Column(name = "dia_chi",length = EntityProperties.LENGT_ADDRESS)
    private String diaChi;
    
    @Column(name = "sdt",length = EntityProperties.LENGT_PHONE)
    private String sdt;
    
    @Column(name = "mat_khau")
    private String matKhau;
    
}
