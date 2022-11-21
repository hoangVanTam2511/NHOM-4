/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.PrimaryEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.PrimaryKey;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@Entity
@Table(name = "ram")
public class Ram  extends PrimaryEntity{   
    @Column(name = "kichThuoc")
    private int kichThuoc;
 
}
