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
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
@Data
@Table(name = "DongSp")
public class DongSp extends PrimaryEntity implements IsIdentified,Serializable{ 
    
    @Column(name = "ma",length = EntityProperties.LENGTH_MA,nullable = false)
    private String ma;
    
    @Column(name = "ten",length = EntityProperties.LENGT_SHORT_NAME,nullable = false)
    private String ten;

    public DongSp(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public DongSp() {
    }
    
}
