/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.IsIdentified;
import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
@Table(name = "MauSac")
public class MauSac extends PrimaryEntity implements  Serializable,IsIdentified{
    
    @Column(length = EntityProperties.LENGTH_MA,name ="ma",nullable = false)
    private String ma;
    
    @Column(name = "ten",length = EntityProperties.LENGT_SHORT_NAME)
    private String ten;

    @Override
    public String toString() {
        return "MauSac{" +"id="+getId()+ "ma=" + ma + ", ten=" + ten + '}';
    }

    public MauSac(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public MauSac() {
    }
    
   
    
}
