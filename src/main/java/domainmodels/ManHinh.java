/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.PrimaryEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowSysOut;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "man_hinh")
public class ManHinh extends PrimaryEntity implements Serializable{
  
    @Column(name = "kich_thuoc")
    private int kichThuoc;
    
    @Column(name = "do_phan_giai")
    private String doPhanGiai;
    
}
